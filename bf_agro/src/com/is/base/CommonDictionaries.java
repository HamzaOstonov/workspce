package com.is.base;

import java.sql.Connection;
import java.util.List;

import com.is.base.utils.DbUtils;
import com.is.utils.RefData;
import com.is.utils.RefDataService;

public class CommonDictionaries {

	public static List<RefData> getCountries(Connection c,String alias) {
		return c!=null
				?DbUtils.getRefData(c, SqlScripts.COUNTRIES.getSql())
				:RefDataService.getRefData(SqlScripts.COUNTRIES.getSql(), alias);
	}
	
	public static List<RefData> getRezKl(Connection c, String alias) {
		return c != null 
				?DbUtils.getRefData(c, SqlScripts.RESIDENT.getSql())
				:RefDataService.getRefData(SqlScripts.RESIDENT.getSql(), alias);
	}
	
	public static List<RefData> getPersonKind(Connection c, String alias) {
		return c!=null
				?DbUtils.getRefData(c, SqlScripts.PERSON_KIND.getSql())
				:RefDataService.getRefData(SqlScripts.PERSON_KIND.getSql(),alias);
	}
	
	public static List<RefData> getDocumentTypes(Connection c, String alias) {
		return c!=null
				?DbUtils.getRefData(c, SqlScripts.PASSPORT_TYPE.getSql())
				:RefDataService.getRefData(SqlScripts.PASSPORT_TYPE.getSql(), alias);
	}
	public static List<RefData> getNations(Connection c, String alias) {
		return c != null 
				?DbUtils.getRefData(c, SqlScripts.NATION.getSql())
				:RefDataService.getRefData(SqlScripts.NATION.getSql(), alias);
	}
	
	public static List<RefData> getPasportTypes(Connection c, String alias) {
		return c != null
				?DbUtils.getRefData(c, SqlScripts.PASSPORT_IS_NEW.getSql())
				:RefDataService.getRefData(SqlScripts.PASSPORT_IS_NEW.getSql(), alias);
	}
	
	public static List<RefData> getGenderTypes(Connection c,String alias) {
		return c!=null
				?DbUtils.getRefData(c, SqlScripts.GENDER.getSql())
				:RefDataService.getRefData(SqlScripts.GENDER.getSql(), alias);
	}
	
	public static List<RefData> getRegions(Connection c, String alias) {
	    return c != null
    			?DbUtils.getRefData(c, SqlScripts.REGION.getSql())
				:RefDataService.getRefData(SqlScripts.REGION.getSql(),alias);
	}

	public static List<RefData> getDistricts(Connection c, String alias) {
	    return c != null 
	    		?DbUtils.getRefData(c, SqlScripts.DISTRICT.getSql())
				:RefDataService.getRefData(SqlScripts.DISTRICT.getSql(), alias);
	}// where act <> 'Z'
	
	public static List<RefData> getAccBal(Connection c, String alias) {
	    return c != null
	    		?DbUtils.getRefData(c, SqlScripts.ACC_BAL.getSql())
				:RefDataService.getRefData(SqlScripts.ACC_BAL.getSql(),alias);
	}
	public static List<RefData> getCurrencies(Connection c, String alias) {
	    return c != null
	    		?DbUtils.getRefData(c, SqlScripts.CURRENCY.getSql())
				:RefDataService.getRefData(SqlScripts.CURRENCY.getSql(), alias);
	}
}
