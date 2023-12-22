package com.is.customer_.sap.service.endpoint;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.zkoss.zk.ui.Executions;

import com.is.ISLogger;
import com.is.customer_.ActionsEnum;

public class SAPEnpointService {
	private static Map<String, String> endpoints = new HashMap<String, String>();
	private static SAPEndpoint endpoint = null;

	public static final String SAP_PROPERTIES = "sap.properties";

	static {
	    String path = System.getenv("CATALINA_HOME") + "\\webapps\\bf\\sap.properties";
		Properties props = new Properties();
		InputStream io = null;
		try {
		    io = new FileInputStream(path);
			props.load(io);
			for (Entry<Object, Object> entry : props.entrySet()) {
				endpoints.put((String) entry.getKey(), (String) entry.getValue());
			}
		} catch (FileNotFoundException e) {
			ISLogger.getLogger().error(e.getStackTrace());
		} catch (IOException e) {
			ISLogger.getLogger().error(e.getStackTrace());
		} finally {
			IOUtils.closeQuietly(io);
		}
		endpoint = new SAPEndpoint();
		endpoint.setUsername(endpoints.get(ActionsEnum.SAP_USERNAME.value()));
		endpoint.setPassword(endpoints.get(ActionsEnum.SAP_PASSWORD.value()));
		endpoint.setContentEndpoint(endpoints.get(ActionsEnum.SAP_ENDPOINT_CONTENT_TYPE.value()));
		endpoint.setRelationshipEndpoint(endpoints.get(ActionsEnum.SAP_ENDPOINT_RELATIONSHIP.value()));
		endpoint.setCustomerEndpoint(endpoints.get(ActionsEnum.SAP_ENDPOINT_CUSTOMER.value()));
		endpoint.setRolesEndpoint(endpoints.get(ActionsEnum.SAP_ENDPOINT_ROLE.value()));
		endpoint.setContentDeleteEndpoint(endpoints.get(ActionsEnum.SAP_ENDPOINT_CONTENT_DELETE.value()));
		
	}

	private static String getRelativePath(String path) {
		return Executions.getCurrent().getDesktop().getWebApp().getRealPath(path);
	}

	public static SAPEndpoint getEndpoint(){
		return endpoint;
	}
}
