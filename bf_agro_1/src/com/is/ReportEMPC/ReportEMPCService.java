package com.is.ReportEMPC;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.LtLogger;

//import com.is.openway.Utils;
//import com.is.openway.model.AccInfo;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.Res;

public class ReportEMPCService {
    private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM ReportEMPC ";
    public static class actions_for_acc
    {
    	private String bank;
        private String terminal;
        private String terminal_name;
        private String tran_name;
        private Date period_begin;
        private Date period_end;
    	
        public String getBank() {
    		return bank;
    	}

    	public void setBank(String bank) {
    		this.bank = bank;
    	}

    	public String getTerminal() {
    		return terminal;
    	}

    	public void setTerminal(String terminal) {
    		this.terminal = terminal;
    	}

    	public String getTerminal_name() {
    		return terminal_name;
    	}

    	public void setTerminal_name(String terminal_name) {
    		this.terminal_name = terminal_name;
    	}

    	public String getTran_name() {
    		return tran_name;
    	}

    	public void setTran_name(String tran_name) {
    		this.tran_name = tran_name;
    	}
    	public Date getPeriod_begin() {
    		return period_begin;
    	}

    	public void setPeriod_begin(Date period_begin) {
    		this.period_begin = period_begin;
    	}

    	public Date getPeriod_end() {
    		return period_end;
    	}

    	public void setPeriod_end(Date period_end) {
    		this.period_end = period_end;
    	}
    	
		public actions_for_acc(String bank, String terminal, String terminal_name, String tran_name, Date period_begin, Date period_end) {
			super();
				this.bank = bank;
		        this.terminal = terminal;
		        this.terminal_name = terminal_name;
		        this.tran_name = tran_name;
		        this.period_begin = period_begin;
		        this.period_end = period_end;
		}
    }
    
    public static List<ReportEMPC> getRuyxatReportEMPC(String alias)  {
      List<ReportEMPC> list = new ArrayList<ReportEMPC>();
      Connection c = null;
      try {
              c = ConnectionPool.getConnection(alias);
              Statement s = c.createStatement();
              ResultSet rs = s.executeQuery("select * from empc_expt_records where rownum<200");
              while (rs.next()) {
            	  //terminal - bazadagi TERMINAL
            	  //terminal_name - bazadagi terminal nomi 1dan 
            	  //tran_name = bazadagi tran_type
            	  
            	  ReportEMPC maningRecordim = new ReportEMPC();
            	  maningRecordim.setTerminal_name(rs.getString("TERMINAL"));
            	  maningRecordim.setTran_name(rs.getString("tran_type"));
            	  list.add(maningRecordim);
                    
              }
      } catch (SQLException e) {
      	LtLogger.getLogger().error(CheckNull.getPstr(e));
              e.printStackTrace();
      } finally {
              ConnectionPool.close(c);
      }
      return list;
}
    	
//    public List<ReportEMPC> getReportEMPC(String alias)  {
//            List<ReportEMPC> list = new ArrayList<ReportEMPC>();
//            Connection c = null;
//            try {
//                    c = ConnectionPool.getConnection(alias);
//                    Statement s = c.createStatement();
//                    ResultSet rs = s.executeQuery("SELECT * FROM ReportEMPC");
//                    while (rs.next()) {
//                            list.add(new ReportEMPC(
//                                            /*rs.getString("id"),
//                                            rs.getString("title"),
//                                            rs.getString("year"),
//                                            rs.getString("score")*/
//                            "", "", "", ""		
//                            ));
//                    }
//            } catch (SQLException e) {
//            	LtLogger.getLogger().error(CheckNull.getPstr(e));
//                    e.printStackTrace();
//            } finally {
//                    ConnectionPool.close(c);
//            }
//            return list;
//    }
  
    private static String getCond(List<FilterField> flfields){
            if(flfields.size()>0){
                    return " and ";
            } else
            return " where ";
    }
    
    private static List<FilterField> getFilterFields(ReportEMPC filter){
            List<FilterField> flfields = new ArrayList<FilterField>();
          /*if(!CheckNull.isEmpty(filter.getBranch())){
                  flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getBranch()));
          }*/
//          if(!CheckNull.isEmpty(filter.getId())){
//                  flfields.add(new FilterField(getCond(flfields)+ "title=?",filter.getId()));
//          }
          /*if(!CheckNull.isEmpty(filter.getAcc_bal())){
                  flfields.add(new FilterField(getCond(flfields)+ "year=?",filter.getAcc_bal()));
          }
          if(!CheckNull.isEmpty(filter.getCurrency())){
                  flfields.add(new FilterField(getCond(flfields)+ "score=?",filter.getCurrency()));
          }*/
          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));
            return flfields;
    }
    
//    public static List<ReportEMPC> getReport1(Date data_, String acc_, String branch) {
//        
//    	List<ReportEMPC> list = new ArrayList();
//    	List<RefData> listB = new ArrayList();
//        Connection c = null;    
//        PreparedStatement ps=null;
//        ResultSet rs = null;
//        String sql1 ="";
//    	try {
//    		c = ConnectionPool.getConnection();
//
//    		sql1 = "select branch_tieto, branch_bank from bf_tieto_branches order by 1";
//    		ps = c.prepareStatement(sql1);
//    		rs = ps.executeQuery();
//    		while(rs.next()) {
//    			RefData tmp = new RefData(rs.getString("branch_tieto"), rs.getString("branch_bank"));
//    			listB.add(tmp);
//    		}
//    		
//    		sql1 = "select acc1.*, substr(dtl,10,21) file_id, substr(dtl,32,21) rec_id, "+
//    			"(select card||'-'|| branch "+  
//    			" from visa_onus_records  "+
//    			" where visa_file_id = substr(dtl,10,21) "+
//    			"  and id = substr(dtl,32,21) "+
//    			"union all "+
//    			" select card||'-'||iss_mfo "+ 
//    			"  from visa_trbeq_records  "+
//    			"  where visa_file_id= substr(dtl,10,21) "+
//    			"  and id=substr(dtl,32,21)) karta_branch "+
//    			"from "+
//    			"(select branch, acc, sum(sum_acc)/100 summa, max(to_char(oper_date,'yyyymmdd')||'-'||to_char(file_id,'99999999999999999999')||'-'||to_char(rec_id,'99999999999999999999') ) dtl from visa_overdraft_pay "+ 
//    			"where oper_date<=to_date(?,'dd.mm.yyyy') and acc like ? "+
//    			"group by branch, acc) acc1";
//    		ps = c.prepareStatement(sql1);
//
//    		ps.setString(1, df.format(data_));
//    		ps.setString(2, acc_);
//    		rs = ps.executeQuery();
//    		while(rs.next()) {
//    			ReportEMPC tmp = new ReportEMPC();
//    			tmp.setBank(rs.getString("Bank"));
//    			tmp.setTerminal(rs.getString("Terminal"));
//    			tmp.setTerminal_name(rs.getString("Terminal_name"));
//    			tmp.setTran_name(rs.getString("Tran_t"));
//    			if (rs.getString("karta_branch")!=null && rs.getString("karta_branch").length()>=16 /*karta raqam 16 ta*/ ) {
//    				tmp.setCard(rs.getString("karta_branch").substring(0,16));	
//    			}
//    			if (rs.getString("karta_branch")!=null && rs.getString("karta_branch").length()==20 /*karta raqam 16 ta, '-' 1ta, 3 xonalik rieto_branch, jami 20 ta*/ ) {
//    				for (RefData tietoBranch : listB) {
//    			        if (tietoBranch.getData().equals(rs.getString("karta_branch").substring(18))) {
//    			            tmp.setIss_mfo(tietoBranch.getLabel());
//    			        }
//    				}
//    				if (tmp.getIss_mfo()==null) {
//    					tmp.setIss_mfo(rs.getString("karta_branch").substring(18)+"_"+rs.getString("karta_branch").substring(17));
//    				}
//    			} else if (rs.getString("karta_branch")!=null) {
//    				tmp.setIss_mfo("_"+rs.getString("karta_branch")+"_");
//    			} else {
//    				tmp.setIss_mfo("karta_branch_is_null");
//    			}
//    			list.add(tmp);
//    		}
//    	} catch (SQLException var8) {
//    		ISLogger.getLogger().error("var8: "+var8.getMessage());
//            var8.printStackTrace();
//    		ISLogger.getLogger().error("var8: "+var8.getCause());
//        } finally {
//            ConnectionPool.close(c);
//        }
//        return list;
//    }
}