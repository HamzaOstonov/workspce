package com.is.sd_books.service;

import java.util.List;

import com.is.utils.RefData;
import com.is.utils.RefDataService;

public class RefService {
	public static List<RefData> getTypeDocuments(String alias){
		return RefDataService.getRefData("select kod_pas data, name_pas label from s_passport", alias);
	}
	
	public static List<RefData> getResidents(String alias){
		return RefDataService.getRefData("select kod_rez data, type_rez label from s_rezkl", alias);
	}
	
	public static List<RefData> getCountries(String alias){
		return RefDataService.getRefData("select code_str data, name label from s_str", alias);
	}
	public static List<RefData> getBankName(String branch){
		return RefDataService.getRefData("select bank_name data,bank_id mfo from s_mfo where bank_id='" + branch+"' order by bank_id",branch);
	}
	
}
