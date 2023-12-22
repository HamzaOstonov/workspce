package com.is.clients.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.base.History;
import com.is.base.SqlScripts;
import com.is.base.utils.DbUtils;
import com.is.clients.models.ClientJ;
import com.is.clients.models.RefDataExt;
import com.is.clients.models.S_spr_oked;
import com.is.clients.models.Soato;
import com.is.utils.CheckNull;
import com.is.utils.RefData;
import com.is.utils.RefDataService;

public class ClientDictionaries {

    private static Logger logger = Logger.getLogger(ClientDictionaries.class);

    public static List<RefData> getAllBanks(Connection c, String alias) {
        return c != null
                ? DbUtils.getRefData(c, SqlScripts.ALL_BANKS.getSql())
                : RefDataService.getRefData(SqlScripts.ALL_BANKS.getSql(), alias);
    }

    public static List<RefData> getSSDBLinkBranch(Connection c, String alias) {
        return c != null
                ? DbUtils.getRefData(c, SqlScripts.SS_DBLINK_BRANCH.getSql())
                : RefDataService.getRefData(SqlScripts.SS_DBLINK_BRANCH.getSql(), alias);
    }

    public static List<RefData> getClientStates(Connection c, String alias, int deal_id) {
        return c != null
                ? DbUtils.getRefData(c, SqlScripts.CLIENT_STATE.getSql())
                : RefDataService.getRefData(SqlScripts.CLIENT_STATE.getSql(), alias);
    }

    public static List<RefData> getActivityTypes(Connection c, String alias) {
        return c != null
                ? DbUtils.getRefData(c, SqlScripts.ACTIVITY_TYPE.getSql())
                : RefDataService.getRefData(SqlScripts.ACTIVITY_TYPE.getSql(), alias);
    }
    
    public static List<RefData> getSignRegistrs(Connection c, String alias) {
        return c != null
                ? DbUtils.getRefData(c, SqlScripts.SIGN_REGISTR_J.getSql())
                : RefDataService.getRefData(SqlScripts.SIGN_REGISTR_J.getSql(), alias);
    }
    
//	public static List<RefData> getCountries(Connection c,String alias) {
//		return c!=null
//				?DbUtils.getRefData(c, SqlScripts.COUNTRIES.getSql())
//				:RefDataService.getRefData(SqlScripts.COUNTRIES.getSql(), alias);
//	}
//	
//	public static List<RefData> getRezKl(Connection c, String alias) {
//		return c != null 
//				?DbUtils.getRefData(c, SqlScripts.RESIDENT.getSql())
//				:RefDataService.getRefData(SqlScripts.RESIDENT.getSql(), alias);
//	}

    public static List<RefData> getTypeKl(Connection c, String alias) {
        return c != null
                ? DbUtils.getRefData(c, SqlScripts.CLIENT_TYPE.getSql())
                : RefDataService.getRefData(SqlScripts.CLIENT_TYPE.getSql(), alias);
    }

    public static List<RefData> getVSBS(Connection c, String alias) {
        return c != null
                ? DbUtils.getRefData(c, SqlScripts.VSBS.getSql())
                : RefDataService.getRefData(SqlScripts.VSBS.getSql(), alias);
    }

//	public static List<RefData> getPersonKind(Connection c, String alias) {
//		return c!=null
//				?DbUtils.getRefData(c, SqlScripts.PERSON_KIND.getSql())
//				:RefDataService.getRefData(SqlScripts.PERSON_KIND.getSql(),alias);
//	}
//	
//	public static List<RefData> getDocumentTypes(Connection c, String alias) {
//		return c!=null
//				?DbUtils.getRefData(c, SqlScripts.PASSPORT_TYPE.getSql())
//				:RefDataService.getRefData(SqlScripts.PASSPORT_TYPE.getSql(), alias);
//	}
//	public static List<RefData> getNations(Connection c, String alias) {
//		return c != null 
//				?DbUtils.getRefData(c, SqlScripts.NATION.getSql())
//				:RefDataService.getRefData(SqlScripts.NATION.getSql(), alias);
//	}
//	
//	public static List<RefData> getPasportTypes(Connection c, String alias) {
//		return c != null
//				?DbUtils.getRefData(c, SqlScripts.PASSPORT_IS_NEW.getSql())
//				:RefDataService.getRefData(SqlScripts.PASSPORT_IS_NEW.getSql(), alias);
//	}
//	
//	public static List<RefData> getGenderTypes(Connection c,String alias) {
//		return c!=null
//				?DbUtils.getRefData(c, SqlScripts.GENDER.getSql())
//				:RefDataService.getRefData(SqlScripts.GENDER.getSql(), alias);
//	}
//	
//	public static List<RefData> getRegions(Connection c, String alias) {
//	    return c != null
//    			?DbUtils.getRefData(c, SqlScripts.REGION.getSql())
//				:RefDataService.getRefData(SqlScripts.REGION.getSql(),alias);
//	}
//
//	public static List<RefData> getDistricts(Connection c, String alias) {
//	    return c != null 
//	    		?DbUtils.getRefData(c, SqlScripts.DISTRICT.getSql())
//				:RefDataService.getRefData(SqlScripts.DISTRICT.getSql(), alias);
//	}// where act <> 'Z'


    public static List<RefData> getCapacity(Connection c, String alias) {
        return c != null
                ? DbUtils.getRefData(c, SqlScripts.CLIENT_CAPACITY.getSql())
                : RefDataService.getRefData(SqlScripts.CLIENT_CAPACITY.getSql(), alias);
    }

    public static List<RefData> getSKlass(Connection c, String alias) {
        return c != null
                ? DbUtils.getRefData(c, SqlScripts.CREDIT_CLASS.getSql())
                : RefDataService.getRefData(SqlScripts.CREDIT_CLASS.getSql(), alias);
    }

    public static List<RefData> getGNI(Connection c, String alias) {
        return c != null
                ? DbUtils.getRefData(c, SqlScripts.GNI.getSql())
                : RefDataService.getRefData(SqlScripts.GNI.getSql(), alias);
    }//where act <> 'Z'

    public static List<RefData> getOPF(Connection c, String alias) {
        return c != null
                ? DbUtils.getRefData(c, SqlScripts.OPF.getSql())
                : RefDataService.getRefData(SqlScripts.OPF.getSql(), alias);
    }

    public static List<RefData> getSectorOld(Connection c, String alias) {
        return c != null
                ? DbUtils.getRefData(c, SqlScripts.SECTOR_OLD.getSql())
                : RefDataService.getRefData(SqlScripts.SECTOR_OLD.getSql(), alias);
    }

    public static List<RefData> getOked(Connection c, String alias) {
        return c != null
                ? DbUtils.getRefData(c, SqlScripts.OKED.getSql())
                : RefDataService.getRefData(SqlScripts.OKED.getSql(), alias);
    }

    public static List<RefData> getSectorGroups(Connection c) {
        return DbUtils.getRefData(c, SqlScripts.OKED_GROUP.getSql());
    }

    public static List<RefData> getSoogun(Connection c, String alias) {
        return c != null
                ? DbUtils.getRefData(c, SqlScripts.SOOGUN.getSql())
                : RefDataService.getRefData(SqlScripts.SOOGUN.getSql(), alias);
    }
    
    
    public static List<RefData> getSignRoles(Connection c) {
        List<RefData> list = new ArrayList<RefData>();
        list.add(new RefData("1", "1 подпись"));
        list.add(new RefData("2", "2 подпись"));
        return list;
    }

//	public static List<RefData> getAccBal(Connection c, String alias) {
//	    return c != null
//	    		?DbUtils.getRefData(c, SqlScripts.ACC_BAL.getSql())
//				:RefDataService.getRefData(SqlScripts.ACC_BAL.getSql(),alias);
//	}
//	public static List<RefData> getCurrencies(Connection c, String alias) {
//	    return c != null
//	    		?DbUtils.getRefData(c, SqlScripts.CURRENCY.getSql())
//				:RefDataService.getRefData(SqlScripts.CURRENCY.getSql(), alias);
//	}

    public static List<RefData> getRelTypes(Connection c, String alias) {
        return c != null
                ? DbUtils.getRefData(c, "select id data, name label from s_rel_types")
                : RefDataService.getRefData("select id data, name label from s_rel_types", alias);
    }

    //select bank_id id, bank_name name from s_mfo where act <> ''Z'' order by id
    public static List<RefData> getMfo(Connection c, String alias) {
        return c != null
                ? DbUtils.getRefData(c, SqlScripts.MFO.getSql())
                : RefDataService.getRefData(SqlScripts.MFO.getSql(), alias);
    }

    public static List<RefData> get_cur_bank_Mfo(Connection c, String alias) {
        return c != null
                ? DbUtils.getRefData(c, SqlScripts.CUR_BANK_MFO.getSql())
                : RefDataService.getRefData(SqlScripts.CUR_BANK_MFO.getSql(), alias);
    }

    public static List<RefData> getDistrByRegion(Connection c, String regId, String alias) {
        return c != null ?
                DbUtils.getRefData(c, SqlScripts.DISTRICT_BY_REGION.getSql(),
                        regId)
                : RefDataService.getRefData(SqlScripts.DISTRICT_BY_REGION.getSql(),
                regId, alias);
    }

    public static List<RefData> getAttachmentTypes(Connection c, String alias) {
        return c != null
                ? DbUtils.getRefData(c, SqlScripts.ATTACHMENTS_FOR_J.getSql())
                : RefDataService.getRefData(SqlScripts.ATTACHMENTS_FOR_J.getSql(), alias);
    }

    public static HashMap<String, String> getClientCodeLetter(Connection c, String alias) {
        return c != null
                ? DbUtils.getHRefData(c, SqlScripts.CLIENT_CODE_LETTER.getSql())
                : RefDataService.getHRefData(SqlScripts.CLIENT_CODE_LETTER.getSql(), alias);
    }

    public static HashMap<String, String> getClientLetterCode(Connection c, String alias) {
        return c != null
                ? DbUtils.getHRefData(c, SqlScripts.CLIENT_LETTER_CODE.getSql())
                : RefDataService.getHRefData(SqlScripts.CLIENT_LETTER_CODE.getSql(), alias);
    }

    public static Map<Integer, String> getClientActions(Connection c, String alias) {
        return c != null
                ? DbUtils.getHRefDataInt(c, SqlScripts.CLIENT_ACTION.getSql())
                : DbUtils.getHRefDataInt(SqlScripts.CLIENT_ACTION.getSql(), alias);
    }

    public static List<History> getClientHistory(String cl_id, String branch, String alias) {
        return DbUtils.getHistory(SqlScripts.CLIENT_HISTORY.getSql(), cl_id, branch, alias);
    }

    public static List<RefData> getSwift(Connection c, String alias) throws SQLException {
        return c == null || c.isClosed()
                ? DbUtils.getRefData(c, SqlScripts.SWIFT.getSql(), alias)
                : RefDataService.getRefData(SqlScripts.SWIFT.getSql(), alias);
    }


    public static List<S_spr_oked> getSectors(Connection c) {
        List<S_spr_oked> list = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = c.prepareStatement("select * from s_spr_oked");
            rs = stmt.executeQuery();
            list = new ArrayList<S_spr_oked>();
            while (rs.next()) {
                list.add(new S_spr_oked(rs.getString("nci_id"), rs.getString("section_gr"), rs.getString("section_sy"),
                        rs.getString("sg_name_ru"), rs.getString("sg_name_uz"), rs.getString("section"),
                        rs.getString("s_name_ru"), rs.getString("s_name_uz"), rs.getString("groups"),
                        rs.getString("gr_name_ru"), rs.getString("gr_name_uz"), rs.getString("class"),
                        rs.getString("cl_name_ru"), rs.getString("cl_name_uz"),
                        rs.getString("code"), rs.getString("name_ru"), rs.getString("name_uz"),
                        rs.getDate("date_open"), rs.getDate("date_close"), rs.getString("active")));
            }
        } catch (SQLException e) {
            logger.error(e.getStackTrace());
            e.printStackTrace();
        } finally {
            DbUtils.closeResultSet(rs);
            DbUtils.closeStmt(stmt);
        }
        return list;
    }

    public static Map<Integer, String> getAvailableActions(ClientJ client, String username, String pwd, String alias) {
        Map<Integer, String> list = new HashMap<Integer, String>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {//"select 2 as deal_group, tc.deal_id,tc.action_id,aa.name from trans_account tc, action_account aa where tc.deal_id = 2 and state_begin = ? and aa.deal_id = 2 and aa.id = tc.action_id"
            //c = ConnectionPool.getConnection(alias);
/*            ps = c.prepareStatement("select aa.id data, aa.name label " +
                    "from trans_client tc, action_client aa " +
                    "where tc.deal_id = ? " +
                    "and state_begin = ? " +
                    "and aa.deal_id = ? " +
                    "and aa.id = tc.action_id " +
                    "and aa.manual=1");
*/
        	c=ConnectionPool.getConnection(username, pwd, alias);
            ps = c.prepareStatement("select vtc.action_id data, aa.name label from v_trans_client vtc, " +
                    "action_client aa " +
                    "where vtc.deal_id = ? " +
                    "and vtc.state_begin = ? " +
                    "and aa.deal_id = vtc.deal_id " +
                    "and aa.id = vtc.action_id");
            ps.setInt(1, client.getSign_registr());
            ps.setString(2, client.getState());
            //ps.setInt(3, client.getSign_registr());
            rs = ps.executeQuery();
            while (rs.next()) {
                list.put(rs.getInt("data"),
                        rs.getString("label"));
            }
        } catch (SQLException e) {
            com.is.ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        } finally {
            DbUtils.closeResultSet(rs);
            DbUtils.closeStmt(ps);
            ConnectionPool.close(c);
        }
        return list;
    }

/*
CREATE OR REPLACE VIEW V_TRANS_CLIENT AS
SELECT "DEAL_ID","ACTION_ID","STATE_BEGIN","STATE_END" FROM TRANS_CLIENT
WHERE (deal_id,action_id) in (select deal_id,id from ACTION_CLIENT where manual=1) and ACTION_ID IN
(SELECT ACTION_ID FROM ROLE_ACTIONS WHERE GROUP_ID=1 AND ROLE_ID IN
(SELECT ROLE_ID FROM USER_ROLES WHERE USER_ID=info.getempid));
*/    
    
    
    public static Map<String, Soato> getSoatoMap(Connection c) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<String, Soato> map = new HashMap<String, Soato>();
        try {
            ps = c.prepareStatement("select * from s_soato");
            rs = ps.executeQuery();
            while (rs.next()) {
                map.put(rs.getString("kod_soat"), new Soato(
                          rs.getString("kod_soat")
                        , rs.getString("kod_gni")
                        , rs.getString("region_id")
                        , rs.getString("distr"),
                        null,
                        null
                ));
            }

        } catch (SQLException e) {
            logger.error(e.getStackTrace());
            e.printStackTrace();
        } finally {
            DbUtils.closeResultSet(rs);
            DbUtils.closeStmt(ps);
            ConnectionPool.close(c);
        }
        return map;
    }

    public static List<RefDataExt> getSubsidiaries(Connection c) {
        List<RefDataExt> list = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = c.prepareStatement(SqlScripts.SUBSIDIARIES.getSql());
            rs = stmt.executeQuery();
            list = new ArrayList<RefDataExt>();
            while (rs.next()) {
                list.add(new RefDataExt(rs.getString("data"), rs.getString("label"), rs.getString("prop_1")));
            }
        } catch (SQLException e) {
            logger.error(e.getStackTrace());
            e.printStackTrace();
        } finally {
            DbUtils.closeResultSet(rs);
            DbUtils.closeStmt(stmt);
        }
        return list;
    }
    public static List<RefDataExt> getSubbranchs(Connection c) {
        List<RefDataExt> list = null;
        PreparedStatement stmt = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
			cs = c.prepareCall(SqlScripts.INFO_INIT.getSql());
			cs.execute();
            stmt = c.prepareStatement(SqlScripts.SUBBRANCHS.getSql());
            rs = stmt.executeQuery();
            list = new ArrayList<RefDataExt>();
            while (rs.next()) {
                list.add(new RefDataExt(rs.getString("data"), rs.getString("label"), rs.getString("prop_1")));
            }
        } catch (SQLException e) {
            logger.error(e.getStackTrace());
            e.printStackTrace();
        } finally {
            DbUtils.closeResultSet(rs);
            DbUtils.closeStmt(stmt);
            DbUtils.closeStmt(cs);
        }
        return list;
    }

    public static String getHeader(String alias) {
        Connection c = null;
        PreparedStatement ps = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String res = null;
        try {
            c = ConnectionPool.getConnection(alias);
            cs = c.prepareCall("{ call info.init()}");
            cs.execute();
            ps = c.prepareStatement("select info.getheaderID from dual");
            rs = ps.executeQuery();
            if (rs.next()) {
                res = rs.getString(1);
            }
        } catch (SQLException e) {
            logger.error(CheckNull.getPstr(e));
            e.printStackTrace();
        } finally {
            DbUtils.closeResultSet(rs);
            DbUtils.closeStmt(ps);
            DbUtils.closeStmt(cs);
            ConnectionPool.close(c);
        }
        return res;
    }

    public static List<RefData> getSapTypes() {
        List<RefData> list = new ArrayList<RefData>();
        list.add(new RefData("51", "ИНН"));
        list.add(new RefData("52", "Номер свидетельства о регистрации"));
        list.add(new RefData("53", "Код НИБДД (уникалка)"));
//		list.add(new RefData("54","ИНН ИП)"));
        return list;
    }

    public static List<S_spr_oked> getSectorByName(String name,String schema){
        Connection c = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<S_spr_oked> list = new ArrayList<S_spr_oked>();
        try{
            c = ConnectionPool.getConnection();
            preparedStatement = c.prepareStatement("SELECT * FROM S_SPR_OKED WHERE upper(name_ru) like upper(?)");
            preparedStatement.setString(1,name);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                list.add(new S_spr_oked(rs.getString("nci_id"), rs.getString("section_gr"), rs.getString("section_sy"),
                        rs.getString("sg_name_ru"), rs.getString("sg_name_uz"), rs.getString("section"),
                        rs.getString("s_name_ru"), rs.getString("s_name_uz"), rs.getString("groups"),
                        rs.getString("gr_name_ru"), rs.getString("gr_name_uz"), rs.getString("class"),
                        rs.getString("cl_name_ru"), rs.getString("cl_name_uz"),
                        rs.getString("code"), rs.getString("name_ru"), rs.getString("name_uz"),
                        rs.getDate("date_open"), rs.getDate("date_close"), rs.getString("active")));
            }
        }
        catch (Exception e){
            logger.error(CheckNull.getPstr(e));
        }
        finally {
            DbUtils.closeResultSet(rs);
            DbUtils.closeStmt(preparedStatement);
            ConnectionPool.close(c);
        }
        return list;
    }
}
