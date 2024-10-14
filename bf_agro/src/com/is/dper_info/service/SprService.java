package com.is.dper_info.service;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;



import com.is.utils.RefData;
import com.is.utils.RefDataService;

public class SprService {
	
	
	public static List<RefData> getBranches(String alias)
	{
		return RefDataService.getRefData( "select bank_id data, bank_name label from S_mfo",alias);
	}
	public static List<RefData> getRegions(String alias)
	{
		return RefDataService.getRefData( "select region_id data, region_id||'  '||region_nam label from s_region",alias);
	}
	public static List<RefData> getKind(String alias)
	{
		return RefDataService.getRefData( "select ID data ,id||'  '||VALUE1 label from ss_dper_dop where ID_DPER=2",alias);
	}
	public static List<RefData> getCurrency(String alias)
	{
		return RefDataService.getRefData("select kod data, kod||'  '||kod_b||'  '||namev label " +
				"from s_val sv, ss_dper_dop dv where sv.kod = dv.value1 " +
				"and sv.act = dv.state and dv.id=1 and dv.id_dper=7",alias);
	}
	
	public static List<RefData> getCountries(String alias)
	{
		return RefDataService.getRefData( "select code_str data, code_str||' '||name label from S_STR order by code_str",alias);
	}
    
    public static List<RefData> getGroups(String alias)
	{
		return RefDataService.getRefData( "select id data, id||'  '||name label from Dper_region order by name",alias);
	}
    
    public static List<RefData> getUorF(String alias)
	{
		return RefDataService.getRefData( "select id data, id||'  '||name label from ss_ui_status order by name",alias);
	}
    
    public static List<RefData> getVeoper(String alias)
	{
		return RefDataService.getRefData( "select dv.id data, id||'  '||dv.value1 label "+ 
				" from ss_dper_dop dv where dv.state='A' and  dv.id_dper=12 order by value2,data",alias);
	}
    
    public static List<RefData> getRezid(String alias)
	{
		return RefDataService.getRefData( "select KOD_REZ data, KOD_REZ||'  '||TYPE_REZ label from s_rezkl order by TYPE_REZ",alias);
	}
    
    public static List<RefData> getMbranch(String alias)
	{
		return RefDataService.getRefData( "select id data, id||' '||name label from ss_subbranch order by id",alias);
	}
    public static List<RefData> getDistr(String alias)
	{
		return RefDataService.getRefData( "select distr data, distr||' '||distr_name label, region_id from s_distr order by distr",alias);
	}
    public static List<RefData> getType_document(String alias)
	{
		return RefDataService.getRefData( "select KOD_PAS data,kod_pas||' '||name_pas label from s_passport where act='A'",alias);
	}
    public static List<RefData> getState(String alias)
	{
		return RefDataService.getRefData( "select distinct id data,id||' '||name label from state_general",alias);
	}
    public static List<RefData> getActions()
	{
    	List<RefData> acts = new LinkedList<RefData>();
    	acts.add(new RefData("1","Подтвердить"));
    	acts.add(new RefData("2","Утвердить"));
    	acts.add(new RefData("3","Утвердить и провести"));
    	acts.add(new RefData("4","Провести"));
    	acts.add(new RefData("5","Отменить подтверждение"));
    	acts.add(new RefData("6","Отменить утверждение"));
    	acts.add(new RefData("7","Отменить провести"));
    	return acts;
	}
    
    public static List<RefData> getActionsPack()
	{
    	List<RefData> acts = new LinkedList<RefData>();
    	acts.add(new RefData("1","Подтвердить пакет"));
    	acts.add(new RefData("2","Утвердить пакет"));
    	acts.add(new RefData("3","Утвердить и провести пакет"));
    	acts.add(new RefData("4","Провести пакет"));
    	return acts;
	}

}
