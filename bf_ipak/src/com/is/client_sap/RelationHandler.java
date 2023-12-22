package com.is.client_sap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.is.ISLogger;
import com.is.client_personmap.PersonMapUtil;
import com.is.client_personmap.model.PersonMap;
import com.is.clients.models.ClientJ;
import com.is.clients.models.ResultWrapper;
import com.is.customer_.core.model.Customer;
import com.is.customer_.sap.service.SAPServiceFactory;

import client.NCI.com.ipakyulibank.cj.BusinessOrganizationComplex;
import client.NCI.com.ipakyulibank.cj.BusinessPartnerNCI;
import relationships.NCI.com.ipakyulibank.BPRelResp;

public class RelationHandler {
	private String branch;
	
	private RelationHandler(String branch) {
		this.branch = branch; 
	}
	
	public static RelationHandler instance(String branch) {
		return new RelationHandler(branch);
	}
	
	
	public void fetchDirectors(BusinessPartnerNCI[] bpList, ClientJ client) throws Exception {
		BusinessPartnerNCI bp = getCLientMapping(bpList);
		BPRelResp[] relations = SapFactory.instance().getRelationService().getRelationsByIdAndBranchWithoutPrefix(bp.getClient_id(), bp.getBranch());
		for (BPRelResp rel : relations) {
			String relType = rel.getBp_relationships().getRel_type();
			if (relType.equals(SapEnum.REL_TYPE_DIRECTOR.getSapValue())) {
				Customer director = SAPServiceFactory.getInstance().getBusinessPartnerService().get(null, null, rel.getBp_id_02());
				client.setDirector(Mappers.mapBpToPerson(director));
			}else if (relType.equals(SapEnum.REL_TYPE_ACCOUNTANT.getSapValue())) {
				Customer accountant = SAPServiceFactory.getInstance().getBusinessPartnerService().get(null, null, rel.getBp_id_02());
				client.setAccountant(Mappers.mapBpToPerson(accountant));
			}
		}
		client.fillDirectors();
	}
	
	public List<PersonMap> slpitContactPersons(List<PersonMap> personMapList, BPRelResp[] relations) throws Exception {
		Map<String, PersonMap> map = makeMapFromList(personMapList);
		PersonMap pm = null;
		for(BPRelResp rel : relations) {
			String relType = rel.getBp_relationships().getRel_type(); 
			if (!relType.equals(
				SapEnum.REL_TYPE_DIRECTOR.getSapValue()) 
					&& 
					!relType.equals(
				SapEnum.REL_TYPE_ACCOUNTANT.getSapValue())) {
				continue;
			}
			List<Customer> bpList = SAPServiceFactory.getInstance().getMappingService().getMapping(null, null, rel.getBp_id_02());
			ResultWrapper res = mapContainsPerson(map, bpList, relType);
			if(res.isAddToList()) {
				pm = new PersonMap();
				pm.setBranch(branch);
				pm.setPerson_type(PersonMapUtil.PERSONTYPE_P);
				pm.setIdSap(rel.getBp_id_02());
				pm.setFromSap(true);
				pm.setPrson_name(rel.getBp_id_02_name());
				if(relType.equals(SapEnum.REL_TYPE_DIRECTOR.getSapValue())) {
					pm.setPerson_kind(PersonMapUtil.PERSONKIND_DIR);
				} else if(relType.equals(SapEnum.REL_TYPE_ACCOUNTANT.getSapValue())) {
					pm.setPerson_kind(PersonMapUtil.PERSONKIND_ACC);
				}
				
				if(res.isExistsInTable()) {
					pm.setPerson_id(res.getId());
				}
				personMapList.add(pm);
				markOldPerson(personMapList, pm);
			}
		}
		
		return personMapList;
	}

	
	public List<PersonMap> splitFounders(List<PersonMap> personmapList, BPRelResp[] relations, String clientIdSap) throws Exception {
		Map<String, PersonMap> map = makeMapFromList(personmapList);
		PersonMap pm = null;
		ResultWrapper res;
		String dpType;
		BusinessOrganizationComplex boc = null;
		List<Customer> bpList = null;
		for (BPRelResp rel : relations) {
			if (!rel.getBp_relationships().getRel_type()
					.equals(
				SapEnum.REL_TYPE_FOUNDER.getSapValue())
				|| !rel.getBp_id_02().equals(clientIdSap)) {
				continue;
			}
			dpType = rel.getBp_id_01_type().equals("1") 
					? PersonMapUtil.PERSONTYPE_P 
					: PersonMapUtil.PERSONTYPE_J;
			res = null; 
			if(dpType.equals(PersonMapUtil.PERSONTYPE_P)) {
				bpList = SAPServiceFactory.getInstance().getMappingService().getMapping(null, null, rel.getBp_id_01());
				res = mapContainsPerson(map, bpList, rel.getBp_relationships().getRel_type());
			} else if(dpType.equals(PersonMapUtil.PERSONTYPE_J)) {
				boc = SapFactory.instance().getOrganizationService().getDetailsBySapId(rel.getBp_id_01());
				res = mapContainsFounder(map, boc);
			}
			
			if(res.isAddToList()) {
				pm = new PersonMap();
				pm.setPerson_type(dpType);
				pm.setPerson_kind(PersonMapUtil.PERSONKIND_FOUNDER);
				pm.setBranch(branch);
				pm.setIdSap(rel.getBp_id_01());
				pm.setFromSap(true);
				pm.setPrson_name(rel.getBp_id_01_name());
				
				if(res.isExistsInTable()) {
					pm.setPerson_id(res.getId());
				}
				
				pm.getCapital().setPart_of_capital(rel.getBp_relationships().getShareholder().getCmpy_part_per());
				pm.getCapital().setShares_number(rel.getBp_relationships().getShareholder().getShares_numb());
				pm.getCapital().setSum_a(rel.getBp_relationships().getShareholder().getCmpy_part_amo());
				pm.getCapital().setIs_director(rel.getBp_relationships().getShareholder().getCmpy_control());
				pm.getCapital().setCurrency(rel.getBp_relationships().getShareholder().getCmpy_part_cur());
				pm.setDoNotSendRelationRequest(true);
				if(dpType.equals(PersonMapUtil.PERSONTYPE_P)) {
				} else if(dpType.equals(PersonMapUtil.PERSONTYPE_J)) {
					pm.setLegalEntity(Mappers.mapBocToLegal(boc));
				}
				
				personmapList.add(pm);
				
				markOldPerson(personmapList, pm);
			}
			
		}
		return personmapList;
	}
	private ResultWrapper mapContainsPerson(Map<String, PersonMap> map, List<Customer> bpList, String relType) {
		ResultWrapper res = new ResultWrapper();
		res.setExistsInTable(false);
		res.setAddToList(false);
		res.setBranch(branch);
		if(bpList == null || bpList.isEmpty()) {
			res.setAddToList(true);
			return res;
		}
		for (Customer bp : bpList) {
			PersonMap pm = map.get(bp.getId().substring(1));
			if(pm != null){
//				if(!pm.getPerson_kind().equals(SapEnum.getNci(relType))) {
//					res.setId(bp.getId().substring(1));
					res.setAddToList(false);
//				}
				res.setExistsInTable(true);
				break;
			}
			
			if(bp.getId().startsWith("A") && bp.getBranch().equals(branch)) {
				res.setAddToList(true);
				res.setExistsInTable(true);
				res.setId(bp.getId().substring(1));
			}
			ISLogger.getLogger().error(":::::::::::::::::::::::::::::::::::::::::::::::: resWrapper = " + res);

		}
		if(!res.isAddToList() && !res.isExistsInTable()) {
			res.setExistsInTable(false);
			res.setAddToList(true);
		}
		return res;
	}
	
	private ResultWrapper mapContainsFounder(Map<String, PersonMap> map, BusinessOrganizationComplex boc) {
		ResultWrapper res = new ResultWrapper();
		res.setExistsInTable(false);
		res.setBranch(branch);
		res.setAddToList(false);
		if(boc.getBp_list() == null) {
			return res;
		}
		
		for (BusinessPartnerNCI bp : boc.getBp_list()) {

			PersonMap pm = map.get(bp.getClient_id().substring(1));
			if(pm != null){
				res.setAddToList(false);
				res.setExistsInTable(true);
				break;
			}
			if(bp.getClient_id().startsWith("A") && bp.getBranch().equals(branch)) {// && bp.getBranch().equals(branch)
				res.setExistsInTable(true);
				res.setAddToList(true);
				res.setId(bp.getClient_id().substring(1));
			}
			ISLogger.getLogger().error(":::::::::::::::::::::::::::::::::::::::::::::::: resWrapper = " + res);
		}
		if(!res.isAddToList() && !res.isExistsInTable()) {
			res.setExistsInTable(false);
			res.setAddToList(true);
		}
		return res;
	}
	
	private void markOldPerson(List<PersonMap> list, PersonMap personmapFromSap) {
		for(PersonMap pm: list) {
			if(!pm.isFromSap() && pm.getPerson_kind().equals(personmapFromSap.getPerson_kind()) ) {
				pm.setOld(true);
			}
		}
	}
	
	private Map<String, PersonMap> makeMapFromList(List<PersonMap> list) {
		Map<String, PersonMap> map = new HashMap<String, PersonMap>();
		for(PersonMap pm: list) {
			map.put(pm.getPerson_id(), pm);
		}
		return map;
	}
	
	private BusinessPartnerNCI getCLientMapping(BusinessPartnerNCI[] bpList) throws Exception {
		if(bpList == null || bpList.length == 0) {
			throw new Exception("У клиента в SAP нет маппинга");
		}
		for (BusinessPartnerNCI bp : bpList) {
			if (bp.getClient_id().startsWith("J") && bp.getBranch() != null) {
				return bp;
			}
		}
		return null;
	}
	
	
}
