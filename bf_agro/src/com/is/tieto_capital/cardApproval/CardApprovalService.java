package com.is.tieto_capital.cardApproval;

import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.LtLogger;
import com.is.tieto_capital.Constants;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.Res;

import java.sql.*;
import java.text.SimpleDateFormat;

public class CardApprovalService {

        private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
        private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
        private static String msql ="SELECT * FROM ti_card_approval order by state_id desc ";

        static SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        
        public List<CardApproval> getCardApproval()  {

                List<CardApproval> list = new ArrayList<CardApproval>();
                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        Statement s = c.createStatement();
                        ResultSet rs = s.executeQuery("SELECT * FROM ti_card_approval");
                        while (rs.next()) {
                                list.add(new CardApproval(
                                                rs.getLong("id"),
                                                rs.getString("card_type"),
                                                rs.getInt("approval_type_id"),
                                                rs.getString("user_id"),
                                                rs.getString("branch"),
                                                rs.getInt("state_id"),
                                                rs.getDate("v_date"),
                                                rs.getString("t_user_id"),
                                                rs.getString("new_card_account"),
                                                rs.getString("holder_name")));
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                } finally {
                        ConnectionPool.close(c);
                }
                return list;

        }



     

        private static String getCond(List<FilterField> flfields){
                if(flfields.size()>0){
                        return " and ";
                }else
                return " where ";
        }

        private static List<FilterField> getFilterFields(CardApprovalFilter filter){
                List<FilterField> flfields = new ArrayList<FilterField>();


              if(!CheckNull.isEmpty(filter.getId())){
                      flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
              }
              
              if(!CheckNull.isEmpty(filter.getCard_type())) {
            	  if(filter.getCard_type().equals("NOSMART")) {
            		  flfields.add(new FilterField(getCond(flfields)+ "card_type != ?", "Smart Money"));   
            	  }
            	  else {
            		  flfields.add(new FilterField(getCond(flfields)+ "card_type=?",filter.getCard_type()));
            	  }
              }
         
              if(!CheckNull.isEmpty(filter.getApproval_type_id())){
                      flfields.add(new FilterField(getCond(flfields)+ "approval_type_id=?",filter.getApproval_type_id()));
              }
              if(!CheckNull.isEmpty(filter.getUser_id())){
                      flfields.add(new FilterField(getCond(flfields)+ "user_id=?",filter.getUser_id()));
              }
              if(!CheckNull.isEmpty(filter.getBranch())){
                      flfields.add(new FilterField(getCond(flfields)+ "branch=?",filter.getBranch()));
              }
              if(!CheckNull.isEmpty(filter.getState_id())){
                      flfields.add(new FilterField(getCond(flfields)+ "state_id=?",filter.getState_id()));
              }
              if(!CheckNull.isEmpty(filter.getV_date())){
                      flfields.add(new FilterField(getCond(flfields)+ "v_date=?", df.format(filter.getV_date())));
              }
              if(!CheckNull.isEmpty(filter.getT_user_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "t_user_id=?",filter.getT_user_id()));
              }
              if(!CheckNull.isEmpty(filter.getNew_card_account())){
                  flfields.add(new FilterField(getCond(flfields)+ "new_card_account=?",filter.getNew_card_account()));
              }
              if(!CheckNull.isEmpty(filter.getHolder_name())){
                  flfields.add(new FilterField(getCond(flfields)+ "holder_name=?",filter.getHolder_name()));
              }

              flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

                return flfields;
        }


        public static int getCount(CardApprovalFilter filter)  {

            Connection c = null;
            int n = 0;
            List<FilterField> flFields =getFilterFields(filter);
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT count(*) ct FROM ti_card_approval ");
            if(flFields.size()>0){

                    for(int i=0;i<flFields.size();i++){
                            sql.append(flFields.get(i).getSqlwhere());
                    }
            }
            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement(sql.toString());

                        for(int k=0;k<flFields.size();k++){
                        ps.setObject(k+1, flFields.get(k).getColobject());
                        }
                        ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        n = rs.getInt(1);
                    }
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return n;

    }



        public static List<CardApproval> getCardApprovalsFl(int pageIndex, int pageSize, CardApprovalFilter filter)  {

                List<CardApproval> list = new ArrayList<CardApproval>();
                Connection c = null;
                int v_lowerbound = pageIndex + 1;
                int v_upperbound = v_lowerbound + pageSize - 1;
                int params;
                List<FilterField> flFields =getFilterFields(filter);

                StringBuffer sql = new StringBuffer();
                sql.append("SELECT t.* FROM (SELECT t.*, ROWNUM rwnm FROM (SELECT * FROM (");
                if(filter.getAccess().equals("confirm") && filter.getCard_type().equals("NOSMART")) {
                	sql.append("SELECT * FROM ti_card_approval WHERE state_id = 1 AND card_type != 'Smart Money' UNION ALL SELECT * FROM ti_card_approval");
                }
                else if(filter.getAccess().equals("approve") && filter.getCard_type().equals("NOSMART")) {
                	sql.append("SELECT * FROM ti_card_approval WHERE state_id = 2 AND card_type != 'Smart Money' UNION ALL SELECT * FROM ti_card_approval");
                }
                else if(filter.getAccess().equals("confirm") && !filter.getCard_type().equals("NOSMART")) {
                	sql.append("SELECT * FROM ti_card_approval WHERE state_id = 1 AND card_type = 'Smart Money' UNION ALL SELECT * FROM ti_card_approval");
                }
                else if(filter.getAccess().equals("approve") && !filter.getCard_type().equals("NOSMART")) {
                	sql.append("SELECT * FROM ti_card_approval WHERE state_id = 2 AND card_type = 'Smart Money' UNION ALL SELECT * FROM ti_card_approval");
                }
                
                if(flFields.size()>0){

                        for(int i=0;i<flFields.size();i++){
                                sql.append(flFields.get(i).getSqlwhere());
                        }
                }
                
                if(filter.getAccess().equals("confirm")) {
                	sql.append(" AND state_id NOT IN (1) ) s ) t WHERE ROWNUM <= ?) t WHERE t.rwnm >= ? ");
                }
                else if(filter.getAccess().equals("approve")) {
                	sql.append(" AND state_id NOT IN (2) ) s ) t WHERE ROWNUM <= ?) t WHERE t.rwnm >= ? ");
                }


                try {
                        c = ConnectionPool.getConnection();
                        PreparedStatement ps = c.prepareStatement(sql.toString());
                        for(params=0;params<flFields.size();params++){
                        ps.setObject(params+1, flFields.get(params).getColobject());
                        }
                        params++;
                        ps.setInt(params++,v_upperbound);
                        ps.setInt(params++,v_lowerbound);

                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                                list.add(new CardApproval(
                                                rs.getLong("id"),
                                                rs.getString("card_type"),
                                                rs.getInt("approval_type_id"),
                                                rs.getString("user_id"),
                                                rs.getString("branch"),
                                                rs.getInt("state_id"),
                                                rs.getDate("v_date"),
                                                rs.getString("t_user_id"),
                                                rs.getString("new_card_account"),
                                                rs.getString("holder_name")));
                        }
                } catch (SQLException e) {
                        e.printStackTrace();

                } finally {
                        ConnectionPool.close(c);
                }
                return list;

        }


    public static CardApproval getCardApproval(Long cardApprovalId) {

        CardApproval cardApproval = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("SELECT * FROM ti_card_approval WHERE id = ?");
            ps.setLong(1, cardApprovalId);
            rs = ps.executeQuery();
            
            if (rs.next()) {
            	cardApproval = new CardApproval();
                    
            	cardApproval.setId(rs.getLong("id"));
            	cardApproval.setCard_type(rs.getString("card_type"));
            	cardApproval.setApproval_type_id(rs.getInt("approval_type_id"));
            	cardApproval.setUser_id(rs.getString("user_id"));
            	cardApproval.setBranch(rs.getString("branch"));
            	cardApproval.setState_id(rs.getInt("state_id"));
            	cardApproval.setV_date(rs.getDate("v_date"));
            	cardApproval.setT_user_id(rs.getString("t_user_id"));
            	cardApproval.setNew_card_account(rs.getString("new_card_account"));
            	cardApproval.setHolder_name(rs.getString("holder_name"));
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        } 
        finally {
            ConnectionPool.close(c);
        }
        
        return cardApproval;
    }







    // improved
    public static Res insertCardApproval(CardApproval cardApproval) {
		Res result = new Res();
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(
				"INSERT INTO ti_card_approval("
					+ "id, "
					+ "card_type, "
					+ "approval_type_id, "
					+ "user_id, "
					+ "branch, "
					+ "state_id, "
					+ "v_date, "
					+ "t_user_id, "
					+ "new_card_account, "
					+ "holder_name)"
					+ "values("
					+ "seq_ti_card_approval.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			ps.setString(1, cardApproval.getCard_type());
			ps.setInt(2, cardApproval.getApproval_type_id());
			ps.setString(3, cardApproval.getUser_id());
			ps.setString(4, cardApproval.getBranch());
			ps.setInt(5, cardApproval.getState_id());
			ps.setDate(6, cardApproval.getV_date());
			ps.setString(7, cardApproval.getT_user_id());
			ps.setString(8, cardApproval.getNew_card_account());
			ps.setString(9, cardApproval.getHolder_name());

        	if(ps.executeUpdate() != 1) {
        		c.rollback();
        		result.setCode(-1);
        		result.setName("Error: two or more records were inserted");
        	}
        	else {
        		c.commit();
        		result.setName("Карта открыта!");
        	}
		}
		catch(SQLException e) {
			result.setCode(-1);
			result.setName("Card hasn't been opened, error:\n" + CheckNull.getPstr(e));
		}
		finally {			
			ConnectionPool.close(c);
		}
		
		return result;
    }
    
    
	public static TiCardApprovalAction[] getActions(Integer stateBeginId) {
	       
		TiCardApprovalAction[] action = new TiCardApprovalAction[Constants.SIZE_OF_ACTION_ARRAY];		
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement(
        		"SELECT a.* FROM ti_card_approval_actions a, ti_card_approval_trans t " +
        		"WHERE t.action_id = a.id " +
        		"AND t.state_begin_id = ?");
        	ps.setInt(1, stateBeginId);
        	
        	rs = ps.executeQuery();
        	for(int i = 0; rs.next() && (i < Constants.SIZE_OF_ACTION_ARRAY); ++i) {
        		action[i] = new TiCardApprovalAction();
        		action[i].setId(rs.getInt("id"));
        		action[i].setName(rs.getString("name"));
        		action[i].setAction_method(rs.getString("action_method"));
        	}

        } 
        catch (SQLException e) {
        	LtLogger.getLogger().error("getActions() error:\n" + CheckNull.getPstr(e));
        } 
        finally {
        	ConnectionPool.close(c);
        }
        
        return action;
	}
	
	
	public static Res confirmCard(Long id) {
		Res result = new Res();
        Connection c = null;
        PreparedStatement ps = null;
        
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("UPDATE ti_card_approval SET approval_type_id = ?, state_id = ? WHERE id = ?");
        	ps.setInt(1, Constants.APPROVAL_TYPE_APPROVE);
        	ps.setInt(2, Constants.APPROVAL_STATE_CONFIRM);
        	ps.setLong(3, id);
        	
        	if(ps.executeUpdate() != 1) {
        		c.rollback();
        		result.setCode(-1);
        		result.setName("Error: two or more records were updated");
        	}
        	else {
        		c.commit();
        		result.setName("Успешно");
        	}

        } 
        catch (SQLException e) {
        	LtLogger.getLogger().error("confirmCard() error:\n" + CheckNull.getPstr(e));
        } 
        finally {
        	ConnectionPool.close(c);
        }
		
		return result;
	}
	
	public static Res approveCard(Long id) {
		Res result = new Res();
        Connection c = null;
        PreparedStatement ps = null;
        
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("UPDATE ti_card_approval SET approval_type_id = ?, state_id = ? WHERE id = ?");
        	ps.setInt(1, Constants.APPROVAL_INDETERMINATELY);
        	ps.setInt(2, Constants.APPROVAL_STATE_APPROVE);
        	ps.setLong(3, id);
        	
        	if(ps.executeUpdate() != 1) {
        		c.rollback();
        		result.setCode(-1);
        		result.setName("Error: two or more records were updated");
        	}
        	else {
        		c.commit();
        		result.setName("Успешно");
        	}

        } 
        catch (SQLException e) {
        	LtLogger.getLogger().error("confirmCard() error:\n" + CheckNull.getPstr(e));
        } 
        finally {
        	ConnectionPool.close(c);
        }
		
		return result;
	}
	
	public static Res closeCard(Long id) {
		Res result = new Res();
        Connection c = null;
        PreparedStatement ps = null;
        
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("UPDATE ti_card_approval SET approval_type_id = ?, state_id = ? WHERE id = ?");
        	ps.setInt(1, Constants.APPROVAL_INDETERMINATELY);
        	ps.setInt(2, Constants.APPROVAL_STATE_CLOSE);
        	ps.setLong(3, id);
        	
        	if(ps.executeUpdate() != 1) {
        		c.rollback();
        		result.setCode(-1);
        		result.setName("Error: two or more records were updated");
        	}
        	else {
        		c.commit();
        		result.setName("Успешно");
        	}

        } 
        catch (SQLException e) {
        	LtLogger.getLogger().error("confirmCard() error:\n" + CheckNull.getPstr(e));
        } 
        finally {
        	ConnectionPool.close(c);
        }
		
		return result;
	}
	
	
	public static String getClientName(String alias, String clientId, String branch) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String clientName = "";
		
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("SELECT c.name FROM client_p c WHERE c.id = ? AND c.branch = ?");
			ps.setString(1, clientId);
			ps.setString(2, branch);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				clientName = rs.getString("name");
			}
		}
		catch(SQLException e) {
			ISLogger.getLogger().error("getClientName error:\n" + CheckNull.getPstr(e));
		}
		finally {
			ConnectionPool.close(c);
		}
	
		return clientName;
	}
	
	public static String getApprovalType(String alias, String approvalTypeId) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String approvalType = "";
		
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("SELECT name FROM ti_card_approval_types WHERE id = ?");
			ps.setString(1, approvalTypeId);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				approvalType = rs.getString("name");
			}
		}
		catch(SQLException e) {
			ISLogger.getLogger().error("getClientName error:\n" + CheckNull.getPstr(e));
		}
		finally {
			ConnectionPool.close(c);
		}
	
		return approvalType;
	}
	
	public static String getApprovalState(String alias, String approvalStateId) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String approvalState = "";
		
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("SELECT name FROM ti_card_approval_states WHERE id = ?");
			ps.setString(1, approvalStateId);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				approvalState = rs.getString("name");
			}
		}
		catch(SQLException e) {
			ISLogger.getLogger().error("getClientName error:\n" + CheckNull.getPstr(e));
		}
		finally {
			ConnectionPool.close(c);
		}
	
		return approvalState;
	}
	
	public static List<RefData> getApprovalTypes(String alias)
	{
		return com.is.utils.RefDataService.getRefData("SELECT t.id data, t.name label FROM ti_card_approval_types t", alias);
	}	
	
	public static List<RefData> getApprovalStates(String alias)
	{
		return com.is.utils.RefDataService.getRefData("SELECT t.id data, t.name label FROM ti_card_approval_states t", alias);
	}
}

