package com.is.client_sap;

import client.NCI.com.ipakyulibank.cj.BAPIRET2;
import search.NCI.com.ipakyulibank.BPContactPerson;

public class SapUtil {
	public static final String SAP_LOG_FOLDER = "C:\\sapRequests\\";
	public static final String PROXY_ERROR = "Не удалось подключиться к сервису SAP-CRM";
	public static final String SAP_ERROR = "SAP: ";
	public static final String SAP_DISABLED = "SAP disabled";
	
	
	public static String splitMsgFromBapiret(BAPIRET2[] messages) {
		StringBuilder sb = new StringBuilder();
//		sb.append("Произошла ошибка при отправке запроса в SAP CRM  :  ");
		if(messages.length > 0){
			for(BAPIRET2 msg : messages) {
				sb.append(msg.getID()).append(" ").append(msg.getMESSAGE()).append("\n");
			}
		}
		return sb.toString();
	}
	
	public static String getIdSapFromCpList(BPContactPerson[] cpersons) {
		for(BPContactPerson cp : cpersons) {
			if(cp.getRelationship_type().equals(SapEnum.REL_TYPE_IP.getSapValue())) {
				//return cp.getId_client_sap();
                return null;
			}
		}
		return null;
	}
}
