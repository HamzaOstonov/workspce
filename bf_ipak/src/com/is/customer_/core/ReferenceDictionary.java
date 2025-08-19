package com.is.customer_.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import lombok.Getter;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.clients.models.RefDataExt;
import com.is.clients.services.ClientDictionaries;
import com.is.customer_.core.model.Soato;
import com.is.utils.CheckNull;
import com.is.utils.RefData;
import com.is.utils.RefDataService;

public class ReferenceDictionary {
	public static List<RefData> clientTypes;
	public static List<RefData> clientTypesFromCentralBank;
	public static List<RefData> sSDBLinkBranch;
	public static List<RefData> clientStates;
	public static List<RefData> countries;
	public static List<RefData> rezKl;
	public static List<RefData> typeKl;
	public static List<RefData> vSBS;
	public static List<RefData> personKind;
	public static List<RefData> documentTypes;
	public static List<RefData> nations;
	public static List<Soato> soatoList;
	public static List<RefData> pasportTypes;
	public static List<RefData> genderTypes;
	public static List<RefData> regions;
	public static List<RefData> regions_spr_104;
	//public static List<RefData> allRegions;
	//public static List<RefData> allDistricts;
	private static List<RefData> listDistr;
	private static HashMap<String, List<RefData>> listDistrByRegion;	
	private static HashMap<String, List<RefData>> listDistrByRegion_spr_104;
	public static List<RefData> capacity;
	public static List<RefData> sKlass;
	public static List<RefData> gNI;
	public static List<RefData> oPF;
	public static List<RefData> codeSector;
	public static List<RefData> soogun;
	public static List<RefData> state;
	public static List<RefData> personKinds;
	public static List<RefData> reltypes;
	public static List<RefData> contactTypes;
	public static List<RefData> appsTypes;
	private static HashMap<String, List<RefData>> subsidiaries_map;
	private static HashMap<String, List<RefData>> subbranchs_map;


	private ReferenceDictionary() {
	}

	public static List<RefData> getClientTypes(String alias) {
		if (clientTypes == null)
			clientTypes = RefDataService.getRefData("select name_ru label, id data from SS_CLIENT_TYPES", alias);		
		return clientTypes;
	}

	public static List<RefData> getClientTypesFromCentralBank(String alias) {
		if (clientTypesFromCentralBank == null)
			clientTypesFromCentralBank = RefDataService.getRefData("select name_k2 label,kod_k data from s_typekl",
					alias);
		return clientTypesFromCentralBank;
	}

	public static HashMap<String, List<RefData>> getSubsidiaries_map(String alias) {		
		if (subsidiaries_map == null) {
			String branch_="_";
			List<RefData> list2=null;
			HashMap<String, List<RefData>> map1 = new HashMap<String, List<RefData>>();
			List<RefData> list1 = RefDataService.getRefData("select branch||'-'||code data, code||'-'||name label from SS_SUBSIDIARY where STATE='A' order by 1", alias);
			for (int i = 0; i < list1.size(); i++) {
				RefData element = list1.get(i);
				String tmpBranch=element.getData();
				tmpBranch=tmpBranch.substring(0,tmpBranch.indexOf("-"));
				String tmpData=element.getData();
				tmpData=tmpData.substring(tmpData.indexOf("-")+1);
				
				if (!tmpBranch.equals(branch_)) {
					if (!branch_.equals("_")) {
						map1.put(branch_, list2);
					}
					list2 = new ArrayList<RefData>();
				}
				list2.add(new RefData(tmpData, element.getLabel()));
				branch_=tmpBranch;
			}
			if (!branch_.equals("_")) {
				map1.put(branch_, list2);
			}
			subsidiaries_map = map1;
		}
		return subsidiaries_map; 		
	}

	public static HashMap<String, List<RefData>> getSubbranchs_map(String alias) {
		
		if (subbranchs_map == null) {
			String branch_="_";
			List<RefData> list2=null;
			HashMap<String, List<RefData>> map1 = new HashMap<String, List<RefData>>();
			//select bank_id data, bank_id||'-'||bank_name label, tcc_id prop_1 from s_mfo where act <> 'Z' and bank_type=info.getbanktype and bank_statu in ('10') order by tcc_id, bank_id
			List<RefData> list1 = RefDataService.getRefData("select tcc_id||'-'||bank_id data, bank_id||'-'||bank_name label from s_mfo where act <> 'Z' and bank_type in ( select bank_type from s_mfo s2 where s2.bank_id in (select branch from sets) ) and bank_statu in ('10') order by 1,2", alias);
			for (int i = 0; i < list1.size(); i++) {
				RefData element = list1.get(i);
				String tmpBranch=element.getData();
				tmpBranch=tmpBranch.substring(0,tmpBranch.indexOf("-"));
				String tmpData=element.getData();
				tmpData=tmpData.substring(tmpData.indexOf("-")+1);
				
				if (!tmpBranch.equals(branch_)) {
					if (!branch_.equals("_")) {
						map1.put(branch_, list2);
					}
					list2 = new ArrayList<RefData>();
				}
				list2.add(new RefData(tmpData, element.getLabel()));
				branch_=tmpBranch;
			}
			if (!branch_.equals("_")) {
				map1.put(branch_, list2);
			}
			subbranchs_map = map1;
		}
		return subbranchs_map; 
		
	}	
	
	public static List<RefData> getSSDBLinkBranch(String alias) {
		if (sSDBLinkBranch == null)
			sSDBLinkBranch = RefDataService
					.getRefData("select branch data, user_name label from ss_dblink_branch order by branch", alias);
		return sSDBLinkBranch;
	}

	public static List<RefData> getClientStates(int deal_id, String alias) {
		return RefDataService.getRefData("select id data, name label from STATE_CLIENT t where deal_id = " + deal_id,
				alias);
	}

	public static List<RefData> getCountries(String alias) {
		if (countries == null)
			countries = RefDataService.getRefData(
					"select code_str data, name label from s_str where act <> 'Z' order by code_str", alias);
		return countries;
	}

	public static List<RefData> getRezKl(String alias) {
		if (rezKl == null)
			rezKl = RefDataService.getRefData("select kod_rez data, type_rez label from S_REZKL", alias);
		return rezKl;
	}

	public static List<RefData> getTypeKl(String alias) {
		if (typeKl == null)
			typeKl = RefDataService.getRefData(
					"select kod_k data, name_k2 label from S_TYPEKL where kod_k <> '00' and act <> 'Z' order by kod_k",
					alias);
		return typeKl;
	}

	public static List<RefData> getVSBS(String alias) {
		if (vSBS == null)
			vSBS = RefDataService.getRefData("select kod data, name label from s_vsbs where act <> 'Z' order by kod",
					alias);
		return vSBS;
	}

	public static List<RefData> getPersonKind(String lang, String alias) {
		if (personKind == null)
			personKind = RefDataService
					.getRefData("select id data, name_" + lang + " label from SS_PERSON_KINDS order by id", alias);
		return personKind;
	}

	public static List<RefData> getDocumentTypes(String alias) {
		if (documentTypes == null)
			documentTypes = RefDataService.getRefData(
					"select code_cert data, trim(' ' from name_cert) label from s_certificate order by code_cert",
					alias);
		return documentTypes;
	}

	public static List<RefData> getNations(String alias) {
		if (nations == null)
			nations = RefDataService.getRefData(
					"select nat_id data, nat_name label from s_nation where act <> 'Z' order by nat_id", alias);
		return nations;
	}

	public static List<Soato> getSoatoList(String alias) {
		if (soatoList == null){
			soatoList = new LinkedList();
			Connection c = null;
			try
			{
				c = ConnectionPool.getConnection(alias);
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery("select s.* from s_soato s where s.act='A'");
				while (rs.next()){
					Soato ss = new Soato();
					ss.setKod_soat(rs.getString("kod_soat"));
					ss.setKod_gni(rs.getString("kod_gni"));
					ss.setRegion(rs.getString("region_id"));
					ss.setDistr(rs.getString("distr"));
					
					soatoList.add(ss);
				}
			}
			catch (SQLException e)
			{
				com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
			}
			finally
			{
				ConnectionPool.close(c);
			}
		}
		return soatoList;
	}

	public static Soato getSoatoByCode(String soatoCode, String alias) {
		Soato v_res=null;
		List<Soato> tmp = getSoatoList(alias);
		for (int i = 0; i < tmp.size(); i++) {
			if ( tmp.get(i).getKod_soat() == soatoCode || tmp.get(i).getKod_soat().equals(soatoCode)) {
				v_res=tmp.get(i);
				break;
			}
			}
		return v_res;
	}

	public static List<RefData> getPasportTypes(String alias) {
		if (pasportTypes == null)
			pasportTypes = RefDataService.getRefData(
					"select 'O' data, '�� ��������������' label from dual union all select 'N' data, '��������������' label from dual ",
					alias);
		return pasportTypes;
	}

	public static List<RefData> getGenderTypes(String alias) {
		if (genderTypes == null)
			genderTypes = RefDataService.getRefData(
					"select CODE_SEX data, NAME_SEX label from S_SEX where act<>'Z' order by CODE_SEX ", alias);
		return genderTypes;
	}

	public static List<RefData> getRegions(String alias) {
		if (regions == null)
			regions = RefDataService.getRefData(
					"select region_id data, region_nam label from s_region where act <> 'Z' order by region_id", alias);
		return regions;
	}

	public static List<RefData> getRegions_spr_104(String alias) {
		if (regions_spr_104 == null)
			regions_spr_104 = RefDataService.getRefData(
					"select distinct reg_code as data, reg_name as label from s_spr_104 order by 1", alias);
		return regions_spr_104;
	}

	/*public static List<RefData> getDistricts(String code, String alias) {
		return RefDataService.getRefData(
				"select distr data, distr_name label, region_id from s_distr where act <> 'Z' and region_id =" + code
						+ " order by distr",
				alias);
	}*/
	public static List<RefData> getDistrByRegion(String region_id, final String alias) {
		if (listDistrByRegion == null ) 
			listDistrByRegion = new HashMap<String, List<RefData>>();
		if ( !listDistrByRegion.containsKey(region_id) ) {
			List<RefData> lst = RefDataService
			.getRefData(
					"select distr data, distr_name label from s_distr where region_id=? and act='A' order by 1", region_id,
					alias);
		   listDistrByRegion.put(region_id, lst);
		}
		return listDistrByRegion.get(region_id);
	}

	public static List<RefData> getDistrByRegion_spr_104(String region_id, final String alias) {
		if (listDistrByRegion_spr_104 == null ) 
			listDistrByRegion_spr_104 = new HashMap<String, List<RefData>>();
		if ( !listDistrByRegion_spr_104.containsKey(region_id) ) {
			List<RefData> lst = RefDataService
			.getRefData(
					"select distinct loc_r_code data, loc_r_name label from s_spr_104 where loc_r_code like ?||'%' order by 1", region_id,
					alias);
			listDistrByRegion_spr_104.put(region_id, lst);
		}
		return listDistrByRegion_spr_104.get(region_id);
	}

	/*public static List<RefData> getAllRegions(String alias) {
		if (allRegions == null)
			allRegions = RefDataService
					.getRefData("select region_id data, region_nam label from s_region order by region_id", alias);
		return allRegions;
	}*/

	/*public static List<RefData> getAllDistricts(String alias) {
		if (allDistricts == null)
			allDistricts = RefDataService
					.getRefData("select distr data, distr_name label, region_id from s_distr order by distr", alias);
		return allDistricts;
	}*/
	public static List<RefData> getDistricts(final String alias) {
		if (listDistr==null || listDistr.size()==0)
		listDistr = RefDataService
				.getRefData(
						"select distr data, distr_name label, region_id from s_distr where act='A' order by region_id, distr",
						alias);
		 return listDistr;
	}


	public static List<RefData> getCapacity(String alias) {
		if (capacity == null)
			capacity = RefDataService.getRefData(
					"select kod_kr data, name_kr label from s_krfl where act <> 'Z' order by kod_kr", alias);
		return capacity;
	}

	public static List<RefData> getSKlass(String alias) {
		if (sKlass == null)
			sKlass = RefDataService.getRefData(
					"select klass_id data, klass_name label from s_klass where act <> 'Z' order by klass_id", alias);
		return sKlass;
	}

	public static List<RefData> getGNI(String alias) {
		if (gNI == null)
			gNI = RefDataService.getRefData(
					"select gni_id data, name_gni label from s_gni where act <> 'Z' order by gni_id", alias);
		return gNI;
	}

	public static List<RefData> getOPF(String alias) {
		if (oPF == null)
			oPF = RefDataService.getRefData(
					"select opf_id data, opf_name label, t.* from S_OPF t where t.act <> 'Z' order by opf_id", alias);
		return oPF;
	}

	public static List<RefData> getCodeSector(String alias) {
		if (codeSector == null)
			codeSector = RefDataService.getRefData(
					"select branch_id data, branch_nam label from s_branch where branch_id >= '100' and act <> 'Z' order by branch_id",
					alias);
		return codeSector;
	}

	public static List<RefData> getSoogun(String alias) {
		if (soogun == null)
			soogun = RefDataService
					.getRefData("select soogu data, soogu1 label from s_soogun where act <> 'Z' order by soogu", alias);
		return soogun;
	}

	public static String getCountry(String code, String alias) {
		List<RefData> list = RefDataService
				.getRefData("select code_str data, name label from s_str where code_str = " + code, alias);
		if (list.size() > 0)
			return list.get(0).getLabel();
		return "";
	}

	public static String getDocumentName(String code, String alias) {
		List<RefData> list = RefDataService.getRefData(
				"select code_cert data, name_cert label from s_certificate where code_cert = " + code, alias);
		if (list.size() > 0)
			return list.get(0).getLabel();
		return "";
	}

	public static String getStateName(String code, String alias) {
		List<RefData> list = RefDataService
				.getRefData("select id data, name label from state_client where deal_id = 2 and id = " + code, alias);
		if (list.size() > 0)
			return list.get(0).getLabel();
		return "";
	}

	public static String getResidency(String code, String alias) {
		return RefDataService
				.getRefData("select kod_rez data,type_rez label from s_rezkl where kod_rez = " + code, alias).get(0)
				.getLabel();
	}

	public static List<RefData> getState(String alias) {
		if (state == null)
			state = RefDataService.getRefData(" select id data,name label from state_client s where deal_id = 2",
					alias);
		return state;
	}

	public static List<RefData> getPersonKinds(String alias) {
		if (personKinds == null)
			personKinds = RefDataService.getRefData("select id data, name_ru label from ss_person_kinds", alias);
		return personKinds;
	}

	public static List<RefData> getReltypes(String alias) {
		if (reltypes == null)
			reltypes = RefDataService.getRefData("select id data, name label from s_rel_types", alias);
		return reltypes;
	}

	public static List<RefData> getContactTypes(String alias) {
		if (contactTypes == null)
			contactTypes = RefDataService.getRefData(
					"select id data, name_ru label from " + "ss_person_kinds where id = 1 or id = 2", alias);
		return contactTypes;
	}

	public static List<RefData> getAppsTypes(String alias) {
		if (appsTypes == null)
			appsTypes = RefDataService.getRefData("select * from ss_apps_types where type = 'P' and state = 1", alias);
		return appsTypes;
	}

	public static String getRegion(String region, String alias) {
		return decode("SELECT REGION_NAM FROM S_REGION WHERE REGION_ID = ?", new String[] { region }, alias);
	}

	public static String getDistrict(String region, String district, String alias) {
		return decode("SELECT DISTR_NAME FROM S_DISTR WHERE REGION_ID = ? AND DISTR = ?",
				new String[] { region, district }, alias);
	}

	public static String decode(String sql, String param[], String schema) {
		Connection c = null;
		String result = null;
		try {
			c = ConnectionUtils.getInstance().getConnectionBySchema(schema);

			PreparedStatement preparedStatement = c.prepareStatement(sql);

			for (int i = 0; i < param.length; i++) {
				preparedStatement.setString(i + 1, param[i]);
			}

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next())
				result = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ConnectionUtils.getInstance().close(c);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public static String getGniCodeByDistrict(String distrId) {
		Connection c = null;
		String code = null;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(
					"select a.kod_gni data," + "(select s.name_gni from s_gni s where s.gni_id = a.kod_gni)label "
							+ "from s_soato a where a.distr = ? and act = 'A'");
			ps.setString(1, distrId);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				code = rs.getString("data");
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return code;
	}

	public static List<RefData> getGniByRegion(String region, String schema) {
		final String SQL = "select a.gni_id data,a.name_gni label from s_gni a where a.gni_id like '" + region + "%'";
		return RefDataService.getRefData(SQL, schema);
	}
}
