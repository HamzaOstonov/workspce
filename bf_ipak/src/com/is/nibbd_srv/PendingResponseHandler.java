package com.is.nibbd_srv;

import com.is.ConnectionPool;
import com.is.base.Dao;
import com.is.base.utils.DbUtils;
import com.is.clients.dao.DaoFactory;
import com.is.clients.models.ClientJ;
import com.is.clients.models.SsDbLinkBranch;
import com.is.clients.services.ClientJService;
import com.is.clients.services.DictionaryKeeper;
import com.is.clients.utils.ClientUtil;
import com.is.customer_.core.utils.GeneralUtils;
import com.is.nibbd.models.Nibbd;
import com.is.nibbd.service.NibbdDao;
import com.is.nibbd.util.NibbdUtils;
import com.is.utils.CheckNull;
import org.apache.log4j.Logger;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PendingResponseHandler {
    private final static Logger logger = Logger.getLogger(PendingResponseHandler.class);
    private static final String un;
    private static final String password;
    private static final String schema;
    private static HashMap<String,String> clientLetters;

    static {
        un = "DBO";
        password = "dbo123456#";
        schema = "iy00444";
    }

    public void handle() {
        clientLetters = getClientLetters(schema);
        List<SsDbLinkBranch> schemas = getSchemas();
        for (SsDbLinkBranch branch : schemas) {
            ClientJService clientJService = ClientJService.getInstance(un, password, branch.getSchema());
            try {
                List<ClientJ> pendingList = getPendingOrganizations(branch);

                for (ClientJ clientJ : pendingList) {
                    try
                    {
                        clientJService.doAction(clientJ, 51);
                    } catch (Exception e) {
                        logger.error(CheckNull.getPstr(e));
                    }
                }
            } catch (Exception e) {
                logger.error(CheckNull.getPstr(e));
            } catch (Throwable e){
                logger.error(GeneralUtils.stackTraceToString(e));
            }
        }

    }

    private HashMap<String, String> getClientLetters(String schema) {
        //logger.error("Getting clientLetters");
        HashMap<String,String> hashMap = new HashMap<String, String>();
        Connection c = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            c = ConnectionPool.getConnection(schema);
            preparedStatement = c.prepareStatement("SELECT * FROM S_TYPEKL");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                hashMap.put(
                        resultSet.getString("name_k1"),
                        resultSet.getString("kod_k"));
            }
        }
        catch (SQLException e) {
            logger.error(CheckNull.getPstr(e));
        }
        finally {
            DbUtils.closeResultSet(resultSet);
            DbUtils.closeStmt(preparedStatement);
            ConnectionPool.close(c);
        }
        return hashMap;
    }

    private List<SsDbLinkBranch> getSchemas() {
        Connection c = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<SsDbLinkBranch> schemas = new ArrayList<SsDbLinkBranch>();
        try {
            c = ConnectionPool.getConnection();
            preparedStatement = c.prepareStatement("SELECT * FROM SS_DBLINK_BRANCH");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SsDbLinkBranch branch = new SsDbLinkBranch(
                        0,
                        resultSet.getString("branch"),
                        resultSet.getString("user_name"),
                        0,
                        0
                );
                if (branch.getBranch().equals("09014"))
                        //|| branch.getBranch().equals("01054")
                        //|| branch.getBranch().equals("00444")
                        //|| branch.getBranch().equals("00421"))
                    continue;
                schemas.add(branch);
            }
        } catch (SQLException e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            DbUtils.closeResultSet(resultSet);
            DbUtils.closeStmt(preparedStatement);
            ConnectionPool.close(c);
        }
        return schemas;
    }

    private List<ClientJ> getPendingOrganizations(SsDbLinkBranch branch) throws SQLException {
        Dao<Nibbd> dao = NibbdDao.getInstance(branch.getSchema(), branch.getBranch());
        Connection c = null;
        CallableStatement info = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatementClient = null;
        ResultSet resultSet = null;
        List<ClientJ> clientJList = new ArrayList<ClientJ>();
        try {
            c = ConnectionPool.getConnection(branch.getSchema());

            info = c.prepareCall("{call info.init()}");
            info.execute();

            preparedStatementClient = c.prepareStatement(
                    "select a.branch, a.id, a.j_number_tax_registration tax_number,a.name,a.p_family,a.p_first_name,a.p_patronymic from v_client_sap a where a.branch = ? and a.state = 10");
            preparedStatementClient.setString(1, branch.getBranch());
            resultSet = preparedStatementClient.executeQuery();
            List<ClientJ> pendingClients = new ArrayList<ClientJ>();

            // Берем из SAP
            while (resultSet.next()){
                String branch_ = resultSet.getString("branch");
                String tax_number = resultSet.getString("tax_number");
                Long id_ = resultSet.getLong("id");
                String family = resultSet.getString("p_family");
                String firstName = resultSet.getString("p_first_name");
                String patronymic = resultSet.getString("p_patronymic");
                ClientJ clientJ = new ClientJ();
                clientJ.setBranch(branch_);
                clientJ.setId(id_);
                clientJ.setJ_number_tax_registration(tax_number);

                pendingClients.add(clientJ);
            }
            resultSet.close();
            for (ClientJ pendingClient : pendingClients) {
                preparedStatement = c.prepareStatement("select *\n" +
                        "  FROM NIBBD n\n" +
                        " WHERE n.QUERY_NO = 0\n" +
                        "   and n.branch = ? \n" +
                        "   and n.state = 4 and SUBSTR(n.QUERY_INP, (INSTR(n.QUERY_INP, ';')) + 1, 9) = ? ");
                preparedStatement.setString(1, branch.getBranch());
                preparedStatement.setString(2, pendingClient.getJ_number_tax_registration());
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    Long id_ = pendingClient.getId();
                    String query_out = resultSet.getString("query_out");
                    String[] tilda = query_out.split("~");
                    String[] fields = tilda[2].split(";");

                    ClientJ clientJ = parseNibbdQuery(pendingClient.getBranch(), fields, branch, id_);
                    clientJ.setState("10");
                    clientJ.setId(id_);

                    clientJList.add(clientJ);
                }
            }
            //logger.error("ClientJList size " + clientJList.size());
        } catch (SQLException e) {
            logger.error(CheckNull.getPstr(e));
            throw e;
        } finally {
            DbUtils.closeResultSet(resultSet);
            DbUtils.closeStmt(preparedStatement);
            DbUtils.closeStmt(info);
            ConnectionPool.close(c);
        }
        return clientJList;
    }

    private ClientJ parseNibbdQuery(String branch_, String[] fields, SsDbLinkBranch branch, Long id_) {
        Dao<ClientJ> dao = DaoFactory.getInstance(branch.getSchema()).getClientDao();
        ClientJ clientJ = dao.getItemByLongId(branch_, id_);
        clientJ.setBranch(branch_);
        clientJ.setSign_registr(ClientUtil.SIGN_REGISTR_PRIMARY_NOT);
        clientJ.setCode_type(clientLetters.get(fields[10]));
        //logger.error(clientJ.getCode_type() + " " + fields[10] + " " + clientLetters.get(fields[10  ]));
        clientJ.setId_client(fields[3]);
        clientJ.setName(fields[4]);

        clientJ.setJ_number_registration_doc(fields[7]);
        clientJ.setJ_place_regist_name(fields[8]);
        if (clientJ.getCode_type().equals("11")) {
            clientJ.setP_number_tax_registration(fields[2]);
            clientJ.setP_code_citizenship(fields[6]);
            clientJ.setP_code_adr_region(fields[22]);
            clientJ.setP_code_adr_distr(fields[23]);
            clientJ.setCode_resident(NibbdUtils.convertResident(fields[5], false));
        }
        clientJ.setJ_number_tax_registration(fields[2]);
        clientJ.setCode_country(fields[6]);
        clientJ.setCode_resident(NibbdUtils.convertResident(fields[5], false));
        clientJ.setJ_region(fields[22]);
        clientJ.setJ_distr(fields[23]);
        try {
            clientJ.setJ_date_registration(ClientUtil.parseDate(fields[9]));
        } catch (ParseException e) {
            logger.error(e.getStackTrace());
        }
        clientJ.setJ_code_bank(fields[11]);
        clientJ.setJ_opf(fields[13]);
        clientJ.setCode_form(fields[14]);
        clientJ.setJ_soato(fields[15]);
        clientJ.setJ_okpo(fields[16]);
        clientJ.setJ_code_sector(fields[17]);
        //    	clientJ.setJ_code_sector_old(fields[17]);
        clientJ.setJ_code_head_organization(fields[18]);
        clientJ.setJ_inn_head_organization(fields[19]);

        //int len = clientJ.getName().length();
        //clientJ.setJ_short_name(clientJ.getName().substring(0, len < 25 ? len : 25));
        return clientJ;
    }


}
