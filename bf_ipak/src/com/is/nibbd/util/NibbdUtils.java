package com.is.nibbd.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.is.ISLogger;
import com.is.base.utils.Util;
import com.is.clients.models.ClientJ;
import com.is.nibbd.models.Nibbd;
import com.is.nibbd.models.SsNibbd;
import com.is.utils.RefData;
	

public class NibbdUtils {
	private static SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	public static final int TYPE_ACCOUNT = 2;
	public static final int TYPE_CLIENT = 1;
	
	public static boolean canOpenClient(Nibbd nibbd) {
		boolean res = false;
		if(nibbd.getQuery_num() == 0 && nibbd.getState() == 4) {
			String[] query = nibbd.getQuery_out().split("~");
			if(query.length == 4) {
				String[] queryInp = query[2].split(";"); 
				if(queryInp.length > 2) {
					res = queryInp[0].equals("0");
				}
			}
		}
		return res;
	}
	
	public static boolean isErrorState(Nibbd nibbd) {
		return nibbd.getState() == 5;
	}
	
	public static boolean canReceiveQuery(Nibbd nibbd) {
		return nibbd.getState() == 5;
	}
	public static boolean canSendQuery(Nibbd nibbd) {
		return !Util.inInts(nibbd.getQuery_num(),30,40,50) && nibbd.getState() == 5;
	}
	public static boolean canDelete(Nibbd nibbd) {
		return nibbd.getState() == 1;
	}
	public static List<RefData> getQuerySets(int queryno, List<SsNibbd> list) {
		List<RefData> reflist = new ArrayList<RefData>();
		for(SsNibbd s: list) {
			if(s.getQueryNo() == queryno 
					&& s.getAct().equals("A") 
					&& s.getField() == 1) {
				reflist.add(new RefData(s.getId(), s.getValue()));
			}
		}
		return reflist;
	}
	public static String convertResident(String resident, boolean fromNumToLetter){
		if(fromNumToLetter) {
			return resident.equals("1")?"Ä":"Í";
		} else {
			return resident.equals("Ä")?"1":"2";
		}
	}
	public static String convertCodeSubject(String subject, boolean fromLatinToCyrill) {
		if(fromLatinToCyrill) {
			return subject.equals("J")?"Þ":"È";
		} else {
			return subject.equals("Þ")?"J":"I";
		}
	}
	
	public static ClientJ setClientAttrFromQueryOut(ClientJ client, String queryOut) {
		
		String[] arr = queryOut.split("~");
		String[] fields = arr[2].split(";");
		
		client.setId_client(fields[3]);
    	client.setName(fields[4]);
    	client.setJ_number_registration_doc(fields[7]);
    	client.setJ_place_regist_name(fields[8]);
    	if(client.getCode_type().equals("11")) {
    		client.setP_number_tax_registration(fields[2]);
    		client.setP_code_citizenship(fields[6]);
    		client.setP_code_adr_region(fields[22]);
    		client.setP_code_adr_distr(fields[23]);
    		client.setCode_resident(convertResident(fields[5], false));
    	} 
    	client.setJ_number_tax_registration(fields[2]);
    	client.setCode_country(fields[6]);
    	client.setCode_resident(convertResident(fields[5], false));
    	client.setJ_region(fields[22]);
    	client.setJ_distr(fields[23]);
    	try {
    		client.setJ_date_registration(df.parse(fields[9]));
		} catch (ParseException e) {
			ISLogger.getLogger().error(e.getStackTrace());
			e.printStackTrace();
		}
		client.setJ_code_bank(fields[11]);
		client.setJ_opf(fields[13]);
		client.setCode_form(fields[14]);
		client.setJ_soato(fields[15]);
		client.setJ_okpo(fields[16]);
		client.setJ_code_sector(fields[17]);
		client.setJ_code_head_organization(fields[18]);
		client.setJ_inn_head_organization(fields[19]);
		return client;
	}
	
}
