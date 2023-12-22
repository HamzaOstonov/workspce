package com.is.client_sap.abstraction;

import java.rmi.RemoteException;
import java.util.Date;

import com.is.ISLogger;
import com.is.base.utils.XmlSerializer;
import com.is.client_personmap.PersonMapUtil;
import com.is.client_personmap.model.PersonMap;
import com.is.client_sap.Endpoint;
import com.is.client_sap.SapEndpoints;
import com.is.client_sap.SapEnum;
import com.is.client_sap.SapUtil;
import com.is.client_sap.exceptions.SapException;
import com.is.client_sap.exceptions.SapRelationException;
import com.is.clients.utils.ClientUtil;
import com.is.customer_.sap.service.endpoint.AbstractEndpointService;
import com.is.utils.CheckNull;

import relationships.NCI.com.ipakyulibank.BPContent;
import relationships.NCI.com.ipakyulibank.BPContpers;
import relationships.NCI.com.ipakyulibank.BPRel;
import relationships.NCI.com.ipakyulibank.BPRelReqest;
import relationships.NCI.com.ipakyulibank.BPRelResp;
import relationships.NCI.com.ipakyulibank.BPShareholder;
import relationships.NCI.com.ipakyulibank.IdentNums;
import relationships.NCI.com.ipakyulibank.RelationshipsReqest;
import relationships.NCI.com.ipakyulibank.RelationshipsResponceRelationshipsResponce;
import relationships.NCI.com.ipakyulibank.Relationships_OutProxy;

public abstract class AbstractSapRelationService extends AbstractEndpointService{
	
	
	private final static String REL_DIRECT = "";
	public final static String REL_INVERT = "X";
	public final static String REL_MODE_CREATE = "";
	public final static String REL_MODE_DELETE = "D";
	public final static String REL_MODE_MODIFY = "M";
	
	protected void relRequest(BPContent content1, BPContent content2,String mode, String relType,BPContpers contpers, BPShareholder shareHolder) throws SapRelationException{
		Endpoint ep = SapEndpoints.getEndpoint(SapEnum.RELATION_REQ.getSapValue());

		Relationships_OutProxy proxy = new Relationships_OutProxy(
				ep.getEndpoint(),
				ep.getUsername(), ep.getPassword());
		//String user = content1.getBranch() + Executions.getCurrent().getSession().getAttribute("uid");
        String userBranch = getProfileAuthor();
		BPRel rel = new BPRel(mode, relType, REL_DIRECT, userBranch,contpers,shareHolder);
		RelationshipsReqest relationshipsReqest = new RelationshipsReqest(content1, content2, rel);
		RelationshipsResponceRelationshipsResponce[] out = null;

		try {
			out = proxy.relationships_Out(relationshipsReqest);
			XmlSerializer.write(out, SapUtil.SAP_LOG_FOLDER+"relResp"+ClientUtil.formatDateWithTime(new Date())+".xml");
			if(out == null || (out != null && out.length == 0) ) {
				throw new RuntimeException(SapUtil.SAP_ERROR +" relOut out == null || (out != null && out.length == 0)");
			}
			if (out != null && !out[0].getStatus().equals("0") ){
				StringBuilder sb = new StringBuilder();
				sb.append("Произошла ошибка при отправке запроса в SAP CRM  :  ");
				for(RelationshipsResponceRelationshipsResponce msg : out) {
					sb.append(msg.getStatus()).append(" ").append(msg.getStatus_details()).append("\n");
				}
				throw new RuntimeException(sb.toString());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getStackTrace());
			//throw new SapRelationException(SapUtil.PROXY_ERROR);
            throw new SapRelationException(e.getMessage());
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			throw new SapRelationException(SapUtil.SAP_ERROR+e.getMessage());
		}
	}
	
	protected BPRelResp[] getRelations(BPRelReqest req) throws SapRelationException, Exception {
		Endpoint ep = SapEndpoints.getEndpoint(SapEnum.RELATION_REQ.getSapValue());
		Relationships_OutProxy proxy = new Relationships_OutProxy(
				ep.getEndpoint(),
				ep.getUsername(), ep.getPassword());
		BPRelResp[] resp = null; 
		try {

			resp = proxy.getDetails(new BPRelReqest[]{req});
			
		} catch (RemoteException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getStackTrace());
			throw new SapRelationException(SapUtil.PROXY_ERROR);
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			throw new SapRelationException(SapUtil.SAP_ERROR+e.getMessage());
		}
		return resp;
	}

	protected void sendRelation(BPContent content1, BPContent content2,String relType,BPContpers contpers, BPShareholder shareHolder, String relMode) throws SapException, Exception {
		relRequest(content1, content2, relMode, relType, contpers, shareHolder);
	}
	public BPRelResp[] getRelationsByIdAndBranch(String idClient, String branch) throws Exception {
		return getRelations(new BPRelReqest(SapEnum.CLIENT_ORG.getSapValue(),
                "J"+idClient, branch, null));
	}
	
	public BPRelResp[] getRelationsByIdAndBranchWithoutPrefix(String idClient, String branch) throws Exception {
		return getRelations(new BPRelReqest(SapEnum.CLIENT_ORG.getSapValue(), idClient, branch, null));
	}
	public BPRelResp[] getRelationsByPersonId(String idSAP) throws Exception {
		return getRelations(new BPRelReqest(SapEnum.CLIENT_CUST.getSapValue(),null,null,idSAP));
	}

	public abstract void createRelation(PersonMap personMap) throws SapRelationException, Exception;
	
	public abstract void deleteRelation(PersonMap personMap) throws SapRelationException, Exception;
	
	public abstract void modifyRelation(PersonMap personMap) throws SapRelationException, Exception;

	public abstract String getIPcode(String idClient, String branch) throws Exception;
	
	public abstract BPRelResp getIPcodeByIdNci(String idClient, String branch) throws Exception;
	
	public abstract BPRelResp getIPcodeByIdSap(String idSap) throws Exception;
	
	protected BPContent makeOrgContent(String idClient, String numberTaxRegistration, String branch) {
		//String branchUser = branch + Executions.getCurrent().getSession().getAttribute("uid");
        String branchUser = getProfileAuthor();
		if(numberTaxRegistration != null) {
			IdentNums[] nums1 = { new IdentNums(SapEnum.DOC_TYPE_INN.getSapValue(), numberTaxRegistration, null, null) };
			
			return new BPContent(SapEnum.CLIENT_ORG.getSapValue(), "J"+idClient, branch, branchUser, nums1);
		} else {
			IdentNums[] nums1 = { new IdentNums(SapEnum.DOC_TYPE_NIBBD.getSapValue(), idClient, null, null) };
			
			return new BPContent(SapEnum.CLIENT_ORG.getSapValue(), "J"+idClient, branch, branchUser, nums1);
		}
	}
	protected BPContent makeCustContent(PersonMap personMap, String id, String branch, IdentNums... identNums) {
		String bpType = personMap.getPerson_type().equals(PersonMapUtil.PERSONTYPE_J)
				? SapEnum.CLIENT_ORG.getSapValue()
				: SapEnum.CLIENT_CUST.getSapValue();
		if (!personMap.getPerson_kind().equalsIgnoreCase(PersonMapUtil.PERSONKIND_IP))
			id = "A" + id;
        String branchUser = getProfileAuthor();
		return new BPContent(bpType, 
				id, branch, branchUser, identNums);
	}
	
}
