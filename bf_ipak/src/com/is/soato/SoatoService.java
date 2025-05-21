package com.is.soato;

import com.is.ConnectionPool;
import com.is.base.utils.DbUtils;
import com.is.clients.models.Soato;
import com.is.delta.core.Criteria;
import com.is.delta.core.FilterStatement;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SoatoService {
    private Logger logger = Logger.getLogger(SoatoService.class);
    private static final String HEADER = "select * from ( ";
    private static final String BODY =
            //"select rownum rownm,t.*, " +
            //"(select a.region_nam from s_region a where a.region_id = t.region_id) " +
            //"region_name from (select * from s_soato) t ";
    		"select rownum rownm,t.*, t.code KOD_SOAT, cast(null as varchar2(4)) as KOD_GNI, t.reg_name_u region_id, t.reg_name region_name, " + 
    		"t.loc_r_name_u distr, t.loc_r_name distr_ru " +
    		"from (select * from s_spr_104) t ";
    

    private static final String FOOTER_ = " ) b where b.rownm between ? and ?";

    public int getCount(Criteria criteria) {
        int count = 0;
        Connection c = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            c = ConnectionPool.getConnection();
            FilterStatement filterStatement = new FilterStatement(criteria);

            String SQLStatement = "SELECT COUNT(*) FROM s_spr_104 ";
            SQLStatement += filterStatement.generateConditions();

            preparedStatement = c.prepareStatement(SQLStatement);
            filterStatement.initStatementWithoutBounds(preparedStatement);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                count = resultSet.getInt(1);
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            DbUtils.closeResultSet(resultSet);
            DbUtils.closeStmt(preparedStatement);
            ConnectionPool.close(c);
        }
        return count;
    }

    public List<Soato> getData(Criteria criteria) {
        List<Soato> list = new ArrayList<Soato>();
        Connection c = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            c = ConnectionPool.getConnection();
            FilterStatement filterStatement = new FilterStatement(criteria);
            StringBuilder SQLStatement = new StringBuilder();
            SQLStatement.append(HEADER);
            SQLStatement.append(BODY);
            SQLStatement.append(filterStatement.generateConditions());
            SQLStatement.append(FOOTER_);

            preparedStatement = c.prepareStatement(SQLStatement.toString());
            filterStatement.initStatementParameters(preparedStatement);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Soato soato = mapResultSet(resultSet);
                list.add(soato);
            }
        } catch (SQLException e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            DbUtils.closeResultSet(resultSet);
            DbUtils.closeStmt(preparedStatement);
            ConnectionPool.close(c);
        }
        return list;
    }

    private Soato mapResultSet(ResultSet resultSet) throws SQLException {
        return new Soato(
                resultSet.getString("KOD_SOAT"),
                resultSet.getString("KOD_GNI"),
                resultSet.getString("region_id"),
                resultSet.getString("distr"),
                resultSet.getString("distr_ru"),
                resultSet.getString("region_name")
        );
    }
}
