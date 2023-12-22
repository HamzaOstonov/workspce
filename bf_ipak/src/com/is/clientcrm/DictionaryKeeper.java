package com.is.clientcrm;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.is.clientcrm.utils.DbUtils;
import com.is.clientcrm.utils.Util;
import com.is.utils.RefData;

public class DictionaryKeeper {
	private String alias;
	
	private static List<RefData> gender;
	
	public String getAlias() {
		return alias;
	}

	public static List<RefData> getGender() {
		return gender;
	}

	public static List<RefData> getPassportTypes() {
		return passportTypes;
	}

	public static List<RefData> getSs_dblink_branches() {
		return ss_dblink_branches;
	}

	public static List<RefData> getClientTypes() {
		return clientTypes;
	}

	public static List<RefData> getClientStates() {
		return clientStates;
	}

	public static List<Action> getClientActions() {
		return clientActions;
	}

	public static List<RefData> getCountries() {
		return countries;
	}

	public static List<RefData> getResidentTypes() {
		return residentTypes;
	}

	public static List<RefData> getVsbs() {
		return vsbs;
	}

	public static List<RefData> getCapacity() {
		return capacity;
	}

	public static List<RefData> getBranches() {
		return branches;
	}

	public static List<RefData> getBanks() {
		return banks;
	}

	public static List<RefData> getRegions() {
		return regions;
	}

	public static List<RefData> getDistricts() {
		return districts;
	}

	public static List<RefData> getCreditklass() {
		return creditklass;
	}

	public static List<RefData> getGni() {
		return gni;
	}

	public static List<RefData> getOpf() {
		return opf;
	}

	public static List<RefData> getOked() {
		return oked;
	}

	public static List<RefData> getCodeSectorOld() {
		return codeSectorOld;
	}

	public static List<RefData> getSoogun() {
		return soogun;
	}

	public static List<RefData> getNations() {
		return nations;
	}

	public static List<RefData> getNonResidentTypes() {
		return nonResidentTypes;
	}

	public static List<RefData> getSklass() {
		return sklass;
	}

	public static List<RefData> getPersonKind() {
		return personKind;
	}

	public static HashMap<String, String> getClientLetters() {
		return clientLetters;
	}

	public static Map<Integer, String> getClientActionsMap() {
		return clientActionsMap;
	}

	public static Map<String, Soato> getSoatoMap() {
		return soatoMap;
	}

	public static List<RefData> getSwift() {
		return swift;
	}

	public static List<RefData> getActivityType() {
		return activityType;
	}

	//public static List<RefData> getSignRegistrs() {
	//	return signRegistrs;
	//}

	public static HashMap<String, String> get_Operation() {
		return _Operation;
	}

	public static Logger getLogger() {
		return logger;
	}

	private static List<RefData> passportTypes;
	
	private static List<RefData> ss_dblink_branches;
	
	private static List<RefData> clientTypes;
	
	private static List<RefData> clientStates;
	
	private static List<Action> clientActions;
	
	private static List<RefData> countries;
	
	private static List<RefData> residentTypes;
	
	private static List<RefData> vsbs;
	
	private static List<RefData> capacity;
	
	private static List<RefData> branches;
	
	private static List<RefData> banks;
	
	private static List<RefData> regions;
	
	private static List<RefData> districts;
	
	private static List<RefData> creditklass;
	
	private static List<RefData> gni;
	
	private static List<RefData> opf;
	
	private static List<RefData> oked;
	
	private static List<RefData> codeSectorOld;
	
	private static List<RefData> soogun;
	
	private static List<RefData> attachTypes;
	
	private static List<RefData> nations;
	
	private static List<RefData> nonResidentTypes;
	
	private static List<RefData> sklass;
	
	private static List<RefData> personKind;
	private static HashMap<String, String> clientStatesMap;
	private static HashMap<String, String> attachMap;
	
	private static HashMap<String, String> clientLetters;
	
	private static Map<Integer, String> clientActionsMap;
	private static HashMap<String, String> personKindMap;
	
	private static Map<String, Soato> soatoMap;
	
	private static List<RefData> swift;
	
	private static List<RefData> activityType;
	
	//private static List<RefData> signRegistrs;

	private static HashMap<String, String> _Operation = null;

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
			if (capacity == null || capacity.isEmpty())
				capacity = ClientDictionaries.getCapacity(tm.getConnection(), alias);
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
			if (nonResidentTypes == null || nonResidentTypes.isEmpty())
				nonResidentTypes = ClientDictionaries.getNonResidentTypes(tm.getConnection(), alias);
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
			//if (signRegistrs == null || signRegistrs.isEmpty())
			//	signRegistrs = ClientDictionaries.getSignRegistrs(tm.getConnection(), alias);
		} catch (SQLException e) {
			logger.error(e.getStackTrace());
		} finally {
			tm.closeCon();
		}
	}

	public static HashMap<String, String> getClientStatesMap() {
		if (clientStatesMap == null) {
			clientStatesMap = Util.listToMap(getClientStates());
		}
		return clientStatesMap;
	}


	public HashMap<String, String> getPersonKindMap() {
		if (personKindMap == null) {
			personKindMap = Util.listToMap(getPersonKind());
		}
		return personKindMap;
	}

	public Map<Integer, String> getAvailableActionsMap(ClientA client, String username, String pwd, String alias) {
		return ClientDictionaries.getAvailableActions(client, username, pwd, alias);
	}

	public static HashMap<String, String> getHOperation(String alias) {
		if (_Operation == null) {
			_Operation = DbUtils.getHRefData("select id data, user_name label from users where rownum<20", alias);
		}
		return _Operation;
	}

}
