package com.is.habib;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.LtLogger;

import com.is.openway.Utils;
import com.is.openway.model.AccInfo;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.Res;

public class HabibService {
    private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM temp_Movie ";
    public static class actions_for_acc
    {
        private int id;
        private String title;
        private int year;
        private Float score;
    	
        public int getid() {
		return id;
	}
	public void setid(int id) {
		this.id = id;
	}
	public String gettitle() {
		return title;
	}
	public void settitle(String title) {
		this.title = title;
	}
	public int getyear() {
		return year;
	}
	public void setyear(int year) {
		this.year = year;
	}
	public Float getscore() {
		return score;
	}
	public void setscore(Float score) {
		this.score = score;
	}
		public actions_for_acc(int id, String title, int year, Float score) {
			super();
			this.id = id;
		        this.title = title;
		        this.year = year;
		        this.score = score;
		}
    }
    public List<Movie> getMovie(String alias)  {
            List<Movie> list = new ArrayList<Movie>();
            Connection c = null;
            try {
                    c = ConnectionPool.getConnection(alias);
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM temp_Movie");
                    while (rs.next()) {
                            list.add(new Movie(
                                            /*rs.getString("id"),
                                            rs.getString("title"),
                                            rs.getString("year"),
                                            rs.getString("score")*/
                            0, "", 0, 0F		
                            ));
                    }
            } catch (SQLException e) {
            	LtLogger.getLogger().error(CheckNull.getPstr(e));
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return list;
    }
  
    private static String getCond(List<FilterField> flfields){
            if(flfields.size()>0){
                    return " and ";
            } else
            return " where ";
    }
    
    private static List<FilterField> getFilterFields(Movie filter){
            List<FilterField> flfields = new ArrayList<FilterField>();
          /*if(!CheckNull.isEmpty(filter.getBranch())){
                  flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getBranch()));
          }*/
          if(!CheckNull.isEmpty(filter.getId())){
                  flfields.add(new FilterField(getCond(flfields)+ "title=?",filter.getId()));
          }
          /*if(!CheckNull.isEmpty(filter.getAcc_bal())){
                  flfields.add(new FilterField(getCond(flfields)+ "year=?",filter.getAcc_bal()));
          }
          if(!CheckNull.isEmpty(filter.getCurrency())){
                  flfields.add(new FilterField(getCond(flfields)+ "score=?",filter.getCurrency()));
          }*/
          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));
            return flfields;
    }
    
    public static int insertMovie(Movie m) {
		int result = -1;
        Connection c = null;
        PreparedStatement ps = null;
        //boolean bool = false;
        try {
            //ISLogger.getLogger().error("insert bf_openway_contract_acc");
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement( "insert into TEMP_MOVIE(id,title) values(?, ?)" );

            ps.setInt(1, m.getId());
            ps.setString(2, m.getTitle());
            
            ps.execute();
            
            c.commit();

            result=0;
            
        } 
        
        catch (Exception e) {
        	  try {
                  if (c != null)
                      c.rollback();
              } catch (Exception ex) {
              }
        	//res = new Res(-1, e.getMessage());
              result=-1;
              ISLogger.getLogger().error("habib inserting xato "+e.getMessage());
        }
        finally {
            Utils.close(ps);
            ConnectionPool.close(c);
        }
        return result;
    }
	
}