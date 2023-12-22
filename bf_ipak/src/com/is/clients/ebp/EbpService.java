package com.is.clients.ebp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.base.utils.DbUtils;
import com.is.client_personmap.model.PersonMap;
import com.is.clients.ebp.models.Res;
import com.is.clients.ebp.models.individualdetails.IndividualDetails;
import com.is.clients.ebp.models.legalentitydetails.LegalEntityDetails;
import com.is.clients.models.ClientJ;
import com.is.utils.CheckNull;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by DEN on 27.03.2017.
 */
public class EbpService {
	private static final Logger logger = ISLogger.getLogger();
	private static final String LE_DETAILS = "legal_entity_details";
	private static final String IP_DETAILS = "individual_details";
	private static Map<String, Object> map;

	private static SimpleDateFormat dateFormat;

	static {
		dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	}

	// public static Individual identifyIndividual(String branch, String inn,
	// long id) throws IOException, SQLException {
	// ObjectMapper objectMapper = new ObjectMapper();
	// Individual individual;
	// Res res = new Res();
	// //long id = -1;
	// res.setCode(-1);
	//
	// String url = buildUrl(IP_DETAILS, inn);
	// if (url.isEmpty()) {
	// url = "http://10.22.61.130:8085";
	// }
	// ISLogger.getLogger().error("EbpService " + url);
	//
	// individual = objectMapper.readValue(run(url), Individual.class);
	//
	// return individual;
	// }
	//
	// public static LegalEntity identifyLegalEntity(String branch, String inn)
	// throws IOException, SQLException {
	// ObjectMapper objectMapper = new ObjectMapper();
	// LegalEntity legalEntity;
	// Res res = new Res();
	// //long id = -1;
	// res.setCode(-1);
	//
	// String url = buildUrl(LE_DETAILS, inn);
	// ISLogger.getLogger().error("EbpService " + url);
	// legalEntity = objectMapper.readValue(run(url), LegalEntity.class);
	//
	// return legalEntity;
	// }

	public static IndividualDetails individualDetails(String branch, String inn) throws IOException, SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		IndividualDetails individualDetails;
		Res res = new Res();
		res.setCode(-1);
		// long id = -1;
		String url = buildUrlInd(IP_DETAILS, inn);
		// ISLogger.getLogger().error("EbpService " + url);
		individualDetails = objectMapper.readValue(run(url), IndividualDetails.class);
		individualDetails.getConfirm().isSuccessful();
		// ISLogger.getLogger().error("EBP Individual Details " +
		// individualDetails);
		return individualDetails;
	}

	public static LegalEntityDetails legalEntityDetails(String branch, String inn) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		LegalEntityDetails legalEntityDetails;
		Res res = new Res();
		res.setCode(-1);
		String url = buildUrlJur(LE_DETAILS, inn);
		String content = run(url);
		legalEntityDetails = objectMapper.readValue(content, LegalEntityDetails.class);
		legalEntityDetails.getConfirm().isSuccessful();
		return legalEntityDetails;
	}

	
	
	public static ClientJ getClientFromEbp(ClientJ client) {
		try {
			if (client.getCode_type().equals("11")) {
				return EbpMappers.mapIndividualDetails(
						individualDetails(client.getBranch(), client.getJ_number_tax_registration()));
			} else {
				return EbpMappers.mapLegalEntityDetails(
						legalEntityDetails(client.getBranch(), client.getJ_number_tax_registration()));
			}
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
		}
		return null;
	}

	
	
	

	public static List<PersonMap> getFoundersFromIndividual(ClientJ client) {
		List<PersonMap> founders = new ArrayList<PersonMap>();

		try {
			LegalEntityDetails leDetails = legalEntityDetails(client.getBranch(),
					client.getJ_number_tax_registration());
			founders = EbpMappers.getFoundersFromLegalEntityDetails(leDetails);
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
		}

		return founders;
	}

	private static String run(String url) throws IOException {
		// 05-03-2018
		RequestConfig.Builder requestBuilder = RequestConfig.custom();
		requestBuilder = requestBuilder.setConnectTimeout(10 * 1000);
		requestBuilder = requestBuilder.setConnectionRequestTimeout(10 * 1000);
		requestBuilder = requestBuilder.setSocketTimeout(10 * 1000);

		HttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(requestBuilder.build()).build();

		HttpGet request = new HttpGet(url);
		HttpResponse response;
		String result;

		response = client.execute(request);
		HttpEntity entity = response.getEntity();
		//result = EntityUtils.toString(entity);
		result = EntityUtils.toString(entity, "UTF-8");
		
		request.releaseConnection();
		return result;
	}

	
	private static String buildUrlJur(String method, String inn) {
		return new StringBuilder().append(ConnectionPool.getValue("TPP_CONNECT_URL").trim()).append("/cbu/")
				.append(method).append("?branch=00000&client=00000000&client_key=0000000000").append("&tin=")
				.append(inn).toString();
	}

	private static String buildUrlInd(String method, String inn) {
		return new StringBuilder().append(ConnectionPool.getValue("TPP_CONNECT_URL").trim()).append("/cbu/")
				.append(method).append("?branch=00000&client=00000000&client_key=0000000000").append("&pinfl=")
				.append(inn).toString();
	}
	
	public static void woof() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM ssi_individual_details where id=1");
			resultSet = preparedStatement.executeQuery();
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			DbUtils.closeResultSet(resultSet);
			DbUtils.closeStmt(preparedStatement);
			ConnectionPool.close(connection);
		}
	}
}
