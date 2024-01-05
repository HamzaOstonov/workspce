package com.is.clients.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.base.Action;
import com.is.base.CommonDictionaries;
import com.is.base.LongConnection;
import com.is.base.utils.DbUtils;
import com.is.base.utils.Util;
import com.is.clients.models.ClientJ;
import com.is.clients.models.RefDataExt;
import com.is.clients.models.Soato;
import com.is.utils.RefData;

import lombok.Getter;

public class DictionaryKeeper {
	private String alias;
	@Getter
	private static List<RefData> gender;
	@Getter
	private static List<RefData> passportTypes;
	@Getter
	private static List<RefData> ss_dblink_branches;
	@Getter
	private static List<RefData> clientTypes;
	@Getter
	private static List<RefData> clientStates;
	@Getter
	private static List<Action> clientActions;
	@Getter
	private static List<RefData> countries;
	@Getter
	private static List<RefData> residentTypes;
	@Getter
	private static List<RefData> vsbs;
	@Getter
	private static List<RefData> branches;
	@Getter
	private static List<RefData> banks;
	@Getter
	private static List<RefData> regions;
	@Getter
	private static List<RefData> districts;
	@Getter
	private static List<RefData> creditklass;
	@Getter
	private static List<RefData> gni;
	@Getter
	private static List<RefData> opf;
	@Getter
	private static List<RefData> oked;
	@Getter
	private static List<RefData> codeSectorOld;
	@Getter
	private static List<RefData> soogun;
	@Getter
	private static List<RefData> attachTypes;
	@Getter
	private static List<RefData> nations;
	@Getter
	private static List<RefData> personKind;
	private static HashMap<String, String> clientStatesMap;
	private static HashMap<String, String> attachMap;
	@Getter
	private static HashMap<String, String> clientLetters;
	@Getter
	private static Map<Integer, String> clientActionsMap;
	private static HashMap<String, String> personKindMap;
	@Getter
	private static Map<String, Soato> soatoMap;
	@Getter
	private static List<RefData> swift;
	@Getter
	private static List<RefData> activityType;
	@Getter
	private static List<RefData> signRegistrs;
	@Getter
	private static HashMap<String, List<RefData>> regions_districts;

	private static HashMap<String, String> _Operation = null;

	@Getter
	private static HashMap<String, List<RefData>> subsidiaries_map;

	@Getter
	private static HashMap<String, List<RefData>> subbranchs_map;

	private static String nibbdBankUrl;
	
	private static Logger logger = Logger.getLogger(DictionaryKeeper.class);

	private DictionaryKeeper(String alias) {
		this.alias = alias;
	}

	public static DictionaryKeeper getInstance(String alias) {
		return new DictionaryKeeper(alias);
	}

	public void initLists() {
		LongConnection tm = LongConnection.getInstance(alias);
		try {
			if (clientActionsMap == null || clientActionsMap.isEmpty())
				clientActionsMap = ClientDictionaries.getClientActions(tm.getConnection(), alias);
			if (clientStates == null || clientStates.isEmpty())
				clientStates = ClientDictionaries.getClientStates(tm.getConnection(), alias, 1);
			if (countries == null || countries.isEmpty())
				countries = CommonDictionaries.getCountries(tm.getConnection(), alias);
			if (residentTypes == null || residentTypes.isEmpty())
				residentTypes = CommonDictionaries.getRezKl(tm.getConnection(), alias);
			if (clientTypes == null || clientTypes.isEmpty())
				clientTypes = ClientDictionaries.getTypeKl(tm.getConnection(), alias);
			if (vsbs == null || vsbs.isEmpty())
				vsbs = ClientDictionaries.getVSBS(tm.getConnection(), alias);
			if (branches == null || branches.isEmpty())
				branches = ClientDictionaries.get_cur_bank_Mfo(tm.getConnection(), alias);
			if (passportTypes == null || passportTypes.isEmpty())
				passportTypes = CommonDictionaries.getDocumentTypes(tm.getConnection(), alias);
			if (ss_dblink_branches == null || ss_dblink_branches.isEmpty())
				ss_dblink_branches = ClientDictionaries.getSSDBLinkBranch(tm.getConnection(), alias);
			if (regions == null || regions.isEmpty())
				regions = CommonDictionaries.getRegions(tm.getConnection(), alias);
			if (districts == null || districts.isEmpty())
				districts = CommonDictionaries.getDistricts(tm.getConnection(), alias);
			if (creditklass == null || creditklass.isEmpty())
				creditklass = ClientDictionaries.getSKlass(tm.getConnection(), alias);
			if (gni == null || gni.isEmpty())
				gni = ClientDictionaries.getGNI(tm.getConnection(), alias);
			if (opf == null || opf.isEmpty())
				opf = ClientDictionaries.getOPF(tm.getConnection(), alias);
			if (codeSectorOld == null || codeSectorOld.isEmpty())
				codeSectorOld = ClientDictionaries.getSectorOld(tm.getConnection(), alias);
			if (soogun == null || soogun.isEmpty())
				soogun = ClientDictionaries.getSoogun(tm.getConnection(), alias);
			if (gender == null || gender.isEmpty())
				gender = CommonDictionaries.getGenderTypes(tm.getConnection(), alias);
			if (nations == null || nations.isEmpty())
				nations = CommonDictionaries.getNations(tm.getConnection(), alias);
			if (attachTypes == null || attachTypes.isEmpty())
				attachTypes = ClientDictionaries.getAttachmentTypes(tm.getConnection(), alias);
			if (banks == null || banks.isEmpty())
				banks = ClientDictionaries.getAllBanks(tm.getConnection(), alias);
			if (soatoMap == null || soatoMap.isEmpty())
				soatoMap = ClientDictionaries.getSoatoMap(tm.getConnection());
			if (personKind == null || personKind.isEmpty())
				personKind = CommonDictionaries.getPersonKind(tm.getConnection(), alias);
			if (clientLetters == null || clientLetters.isEmpty())
				clientLetters = ClientDictionaries.getClientLetterCode(tm.getConnection(), alias);
			if (oked == null || oked.isEmpty())
				oked = ClientDictionaries.getOked(tm.getConnection(), alias);
			if (swift == null || swift.isEmpty())
				swift = ClientDictionaries.getSwift(tm.getConnection(), alias);
			if (activityType == null || activityType.isEmpty())
				activityType = ClientDictionaries.getActivityTypes(tm.getConnection(), alias);
			if (signRegistrs == null || signRegistrs.isEmpty())
				signRegistrs = ClientDictionaries.getSignRegistrs(tm.getConnection(), alias);
			
			if (subsidiaries_map == null) {
				String branch_="_";
				List<RefData> list2=null;
				HashMap<String, List<RefData>> map1 = new HashMap<String, List<RefData>>();
				List<RefDataExt> list1 = ClientDictionaries.getSubsidiaries(tm.getConnection());
				for (int i = 0; i < list1.size(); i++) {
					RefDataExt element = list1.get(i);
					if (!element.getProp_1().equals(branch_)) {
						if (!branch_.equals("_")) {
							map1.put(branch_, list2);
						}
						list2 = new ArrayList<RefData>();
					}
					list2.add(new RefData(element.getData(), element.getLabel()));
					branch_=element.getProp_1();
				}
				if (!branch_.equals("_")) {
					map1.put(branch_, list2);
				}
				subsidiaries_map = map1;
			}
			
			if (subbranchs_map == null) {
				String branch_="_";
				List<RefData> list2=null;
				HashMap<String, List<RefData>> map1 = new HashMap<String, List<RefData>>();
				List<RefDataExt> list1 = ClientDictionaries.getSubbranchs(tm.getConnection());
				for (int i = 0; i < list1.size(); i++) {
					RefDataExt element = list1.get(i);
					if (!element.getProp_1().equals(branch_)) {
						if (!branch_.equals("_")) {
							map1.put(branch_, list2);
						}
						list2 = new ArrayList<RefData>();
					}
					list2.add(new RefData(element.getData(), element.getLabel()));
					branch_=element.getProp_1();
				}
				if (!branch_.equals("_")) {
					map1.put(branch_, list2);
				}
				subbranchs_map = map1;
			}
			
			if (regions_districts == null) {
				HashMap<String, List<RefData>> regs_dists = new HashMap<String, List<RefData>>();
				for (RefData rd : regions) {
					List<RefData> distrList = new ArrayList<RefData>();
					// distrList =
					// RefDataService.getRefData(SqlScripts.DISTRICT_BY_REGION.getSql(),
					// rd.getData(), alias);
					distrList = ClientDictionaries.getDistrByRegion(tm.getConnection(), rd.getData(), alias);
					regs_dists.put(rd.getData(), distrList);
				}
				regions_districts = regs_dists;
			}

		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		} finally {
			tm.closeCon();
		}
	}

	public HashMap<String, String> getClientStatesMap() {
		if (clientStatesMap == null) {
			clientStatesMap = Util.listToMap(getClientStates());
		}
		return clientStatesMap;
	}

	public HashMap<String, String> getAttachMap() {
		if (attachMap == null) {
			attachMap = Util.listToMap(getAttachTypes());
		}
		return attachMap;
	}

	public HashMap<String, String> getPersonKindMap() {
		if (personKindMap == null) {
			personKindMap = Util.listToMap(getPersonKind());
		}
		return personKindMap;
	}

	public Map<Integer, String> getAvailableActionsMap(ClientJ client, String username, String pwd, String alias) {
		return ClientDictionaries.getAvailableActions(client, username, pwd, alias);
	}

	public static HashMap<String, String> getHOperation(String alias) {
		if (_Operation == null) {
			_Operation = DbUtils.getHRefData("select id data, user_name label from users where rownum<20", alias);
		}
		return _Operation;
	}

	/*
	 * public static HashMap<String, List<RefData>> getRegions_districts(String
	 * alias) { if (regions_districts == null) { HashMap<String, List<RefData>>
	 * regs_dists = new HashMap<String, List<RefData>>(); for (RefData rd :
	 * getRegions()) { List<RefData> distrList = new ArrayList<RefData>();
	 * distrList =
	 * RefDataService.getRefData(SqlScripts.DISTRICT_BY_REGION.getSql(),
	 * rd.getData(), alias); regs_dists.put(rd.getData(), distrList); }
	 * regions_districts=regs_dists; } return regions_districts; }
	 */

	public static List<RefData> getDistrByRegionFromMap(String reg_id, String alias) {
		HashMap<String, List<RefData>> r_d = getRegions_districts();
		List<RefData> distrList = new ArrayList<RefData>();
		if (r_d.containsKey(reg_id))
			distrList = r_d.get(reg_id);
		/*try {
			distrList = r_d.get(reg_id);
		} catch (Exception e) {
			// TODO: handle exception
			distrList = new ArrayList<RefData>();
		}*/
		return distrList;
	}

	public static String getNibbdJurBankUrl(){
		if (nibbdBankUrl==null) {
			nibbdBankUrl = ConnectionPool.getValue("NIBBD_JUR_BANK_URL");
		}  
		return nibbdBankUrl;
	}
	

}
