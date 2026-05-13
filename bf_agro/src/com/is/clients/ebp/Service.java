package com.is.clients.ebp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.base.utils.DbUtils;
import com.is.clients.ebp.models.Res;
import com.is.clients.ebp.models.individual.Individual;
import com.is.clients.ebp.models.individualdetails.IndividualDetails;
import com.is.clients.ebp.models.legalentity.LegalEntity;
import com.is.clients.ebp.models.legalentitydetails.LegalEntityDetails;
import com.is.utils.CheckNull;

/**
 * Created by DEN on 27.03.2017.
 */
public class Service {

    private static Map<String, Object> map;

    private static SimpleDateFormat dateFormat;

    static {
        dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    }    
    
    public static Individual identifyIndividual(String branch, String inn, long id) throws IOException, SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        Individual individual;
        Res res = new Res();
        //long id = -1;
        res.setCode(-1);

        String url = new StringBuilder().append(ConnectionPool.getValue("TPP_CONNECT_URL").trim())
                .append("/cbu/identify_individual?branch=")
                .append(branch)
                .append("&tin=")
                .append(inn)
                .toString();
        if (url.isEmpty()) {
            url = "http://10.22.61.130:8085";
        }
        ISLogger.getLogger().info(url);

        individual = objectMapper.readValue(run(url), Individual.class);

        return individual;
    }

    public static LegalEntity identifyLegalEntity(String branch, String inn) throws IOException, SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        LegalEntity legalEntity;
        Res res = new Res();
        //long id = -1;
        res.setCode(-1);

        String url = new StringBuilder().append(ConnectionPool.getValue("TPP_CONNECT_URL").trim())
                .append("/cbu/identify_legal_entity?branch=")
                .append(branch)
                .append("&tin=")
                .append(inn)
                .toString();
        ISLogger.getLogger().info(url);
        legalEntity = objectMapper.readValue(run(url), LegalEntity.class);

        return legalEntity;
    }

    public static IndividualDetails individualDetails(String branch, String inn, String client, String clientKey, long id) throws IOException, SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        IndividualDetails individualDetails;
        Res res = new Res();
        res.setCode(-1);
        //long id = -1;

        String url = new StringBuilder().append(ConnectionPool.getValue("TPP_CONNECT_URL").trim())
                .append("/cbu/individual_details?branch=")
                .append(branch)
                .append("&tin=")
                .append(inn)
                .append("&client=")
                .append(client)
                .append("&client_key=")
                .append(clientKey)
                .toString();
        ISLogger.getLogger().info(url);
        individualDetails = objectMapper.readValue(run(url), IndividualDetails.class);

        return individualDetails;
    }

    public static LegalEntityDetails legalEntityDetails(String branch, String inn, String client, String clientKey, long id) throws IOException, SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        LegalEntityDetails legalEntityDetails;
        Res res = new Res();
        res.setCode(-1);
        //long id = -1;

        String url = new StringBuilder().append(ConnectionPool.getValue("TPP_CONNECT_URL").trim())
                .append("/cbu/legal_entity_details?branch=")
                .append(branch)
                .append("&tin=")
                .append(inn)
                .append("&client=")
                .append(client)
                .append("&client_key=")
                .append(clientKey)
                .toString();
        ISLogger.getLogger().info(url);
        legalEntityDetails = objectMapper.readValue(run(url), LegalEntityDetails.class);

        return legalEntityDetails;
    }



    private static String run(String url) throws IOException {
        HttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        HttpResponse response;
        String result;

        response = client.execute(request);
        HttpEntity entity = response.getEntity();
        result = EntityUtils.toString(entity);

        return result;
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
