package com.is.SwiftBuffer;

import general.General;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import accounting_transaction.TransactionService;

import com.is.ConnectionPool;
import com.is.account.Account;
import com.is.kernel.parameter.Parameters;
import com.is.tracc.TrAcc;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.RefDataService;
import com.is.utils.Res;

public class SwiftBufferService {
	private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT /*+index_desc( b UFK_SWIFT_ID) */  * FROM swift_buffer b ";
    private static List<RefData> bic_ = null;
    private static HashMap<String, String> _trState =null;
    private static List<RefData> country_ = null;

   
    public static List<RefData> getCountry2(String branch)
    {
      if(country_==null){
    	  country_=RefDataService.getRefData("select trim(char_code) data, code_str||'  '||char_code||'  '||name label from S_str where char_code is not null order by data", branch);
      }
      return country_;
      
    }
    
    public static List<RefData> getDetailsOfCharges(String branch)
    {
      return RefDataService.getRefData("select id data, id||'  '||name label from ss_swift_detailsofcharges ", branch);
    }
    
    public static List<RefData> getCurrency(String alias)
    {
      return RefDataService.getRefData("select kod_b data, kod||'  '||kod_b||'  '||namev label from S_VAL order by data", alias);
    } 


    public static List<RefData> getBic(String alias)
    {
    	if(bic_==null){
    	// bic_=RefDataService.getRefData("select /*+index(b XUK_SWIFT_BASE)*/ bic data, city||' '||name label from swift_base b where rownum<1000", alias);
    		bic_=RefDataService.getRefData("select /*+index(b XUK_SWIFT_BUFFER_BASE)*/ b.bic_code data, b.city_heading||' '||b.institution_name label from SWIFT_BUFFER_BASE b where rownum<1000", alias);
    	}
      return bic_;
    }
    
    
    public static List<RefData> getBic(String key,String alias)
    {
    	//System.out.println(key);
    	return RefDataService.getRefData("select /*+index(b XUK_SWIFT_BUFFER_BASE)*/ b.bic_code data, b.city_heading||' '||b.institution_name label from SWIFT_BUFFER_BASE b where b.bic_code like '____'||?||'%' and rownum<1000", key,alias);
    
    }
    
    public static List<RefData> getCorAcc(String alias){
    	//System.out.println(key);
    	return RefDataService.getRefData("select id data,a.id_order||' '||name||'  '||-1*a.s_out/100||' '||'USD'   label from account a where a.state=2 and a.id like '10301840%' or a.id like '10501840%' order by s_out",alias);
    
    }

    public static HashMap<String, String> getHState(String branch) {
        if (_trState == null) {
          _trState = RefDataService.getHRefData("select distinct id data,name label from state_swift_buffer where deal_id=1 order by id", branch);
        }
        return _trState;
      }


	public List<SwiftBuffer> getSwiftBuffer()  {

            List<SwiftBuffer> list = new ArrayList<SwiftBuffer>();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM swift_buffer");
                    while (rs.next()) {
                            list.add(new SwiftBuffer(
                                            rs.getString("branch"),
                                            rs.getLong("id"),
                                            rs.getString("message_type"),
                                            rs.getString("application_id"),
                                            rs.getString("service_id"),
                                            rs.getString("bic_from"),
                                            rs.getString("bic_to"),
                                            rs.getString("direction"),
                                            rs.getString("country_from"),
                                            rs.getString("country_to"),
                                            rs.getDate("value_date"),
                                            rs.getDouble("amount"),
                                            rs.getString("currency"),
                                            rs.getString("reference"),
                                            rs.getString("operation_code"),
                                            rs.getString("order_party"),
                                            rs.getString("ben_party"),
                                            rs.getString("narrative"),
                                            rs.getString("DETAILSOFCHARGES"),
                                            rs.getString("message_text"),
                                            rs.getDate("insert_date"),
                                            rs.getInt("state"),
                                            rs.getInt("deal_id"),
                                            rs.getInt("parent_group_id"),
                                            rs.getLong("parent_id"),
                                            rs.getLong("batch_id"),
                                            rs.getString("file_name")));
                    }
            } catch (SQLException e) {
                    com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
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

    private static List<FilterField> getFilterFields(SwiftBufferFilter filter){
            List<FilterField> flfields = new ArrayList<FilterField>();


          if(!CheckNull.isEmpty(filter.getBranch())){
                  flfields.add(new FilterField(getCond(flfields)+ "branch=?",filter.getBranch()));
          }
          if(!CheckNull.isEmpty(filter.getId())){
                  flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
          }
          if(!CheckNull.isEmpty(filter.getMessage_type())){
                  flfields.add(new FilterField(getCond(flfields)+ "message_type=?",filter.getMessage_type()));
          }
          if(!CheckNull.isEmpty(filter.getApplication_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "application_id=?",filter.getApplication_id()));
          }
          if(!CheckNull.isEmpty(filter.getService_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "service_id=?",filter.getService_id()));
          }
          if(!CheckNull.isEmpty(filter.getBic_from())){
                  flfields.add(new FilterField(getCond(flfields)+ "bic_from=?",filter.getBic_from()));
          }
          if(!CheckNull.isEmpty(filter.getBic_to())){
                  flfields.add(new FilterField(getCond(flfields)+ "bic_to=?",filter.getBic_to()));
          }
          if(!CheckNull.isEmpty(filter.getDirection())){
                  flfields.add(new FilterField(getCond(flfields)+ "direction=?",filter.getDirection()));
          }
          if(!CheckNull.isEmpty(filter.getCountry_from())){
                  flfields.add(new FilterField(getCond(flfields)+ "country_from=?",filter.getCountry_from()));
          }
          if(!CheckNull.isEmpty(filter.getCountry_to())){
                  flfields.add(new FilterField(getCond(flfields)+ "country_to=?",filter.getCountry_to()));
          }
          if(!CheckNull.isEmpty(filter.getValue_date())){
                  flfields.add(new FilterField(getCond(flfields)+ "value_date=?",filter.getValue_date()));
          }
          if(!CheckNull.isEmpty(filter.getAmount())&&(filter.getAmount()!=0.0)){
                  flfields.add(new FilterField(getCond(flfields)+ "amount=?",filter.getAmount()));
          }
          if(!CheckNull.isEmpty(filter.getCurrency())){
                  flfields.add(new FilterField(getCond(flfields)+ "currency=?",filter.getCurrency()));
          }
          if(!CheckNull.isEmpty(filter.getReference())){
                  flfields.add(new FilterField(getCond(flfields)+ "reference=?",filter.getReference()));
          }
          if(!CheckNull.isEmpty(filter.getOperation_code())){
                  flfields.add(new FilterField(getCond(flfields)+ "operation_code=?",filter.getOperation_code()));
          }
          if(!CheckNull.isEmpty(filter.getOrder_party())){
                  flfields.add(new FilterField(getCond(flfields)+ "order_party=?",filter.getOrder_party()));
          }
          if(!CheckNull.isEmpty(filter.getBen_party())){
                  flfields.add(new FilterField(getCond(flfields)+ "ben_party=?",filter.getBen_party()));
          }
          if(!CheckNull.isEmpty(filter.getNarrative())){
                  flfields.add(new FilterField(getCond(flfields)+ "narrative=?",filter.getNarrative()));
          }
          if(!CheckNull.isEmpty(filter.getMessage_text())){
                  flfields.add(new FilterField(getCond(flfields)+ "message_text=?",filter.getMessage_text()));
          }
          if(!CheckNull.isEmpty(filter.getInsert_date())){
                  flfields.add(new FilterField(getCond(flfields)+ "insert_date=?",filter.getInsert_date()));
          }
          if(!CheckNull.isEmpty(filter.getState())){
                  flfields.add(new FilterField(getCond(flfields)+ "state=?",filter.getState()));
          }
          if(!CheckNull.isEmpty(filter.getDeal_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "deal_id=?",filter.getDeal_id()));
          }
          if(!CheckNull.isEmpty(filter.getParent_group_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "parent_group_id=?",filter.getParent_group_id()));
          }
          if(!CheckNull.isEmpty(filter.getParent_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "parent_id=?",filter.getParent_id()));
          }
          if(!CheckNull.isEmpty(filter.getBatch_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "batch_id=?",filter.getBatch_id()));
          }
          if(!CheckNull.isEmpty(filter.getFile_name())){
                  flfields.add(new FilterField(getCond(flfields)+ "file_name=?",filter.getFile_name()));
          }

          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(SwiftBufferFilter filter, String alias)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM swift_buffer ");
        if(flFields.size()>0){

                for(int i=0;i<flFields.size();i++){
                        sql.append(flFields.get(i).getSqlwhere());
                }
        }
        try {
                c = ConnectionPool.getConnection(alias);
                PreparedStatement ps = c.prepareStatement(sql.toString());

                    for(int k=0;k<flFields.size();k++){
                    ps.setObject(k+1, flFields.get(k).getColobject());
                    }
                    ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    n = rs.getInt(1);
                }
        } catch (SQLException e) {
                com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

        } finally {
                ConnectionPool.close(c);
        }
        return n;

}



    public static List<SwiftBuffer> getSwiftBuffersFl(int pageIndex, int pageSize, SwiftBufferFilter filter, String alias)  {

            List<SwiftBuffer> list = new ArrayList<SwiftBuffer>();
            Connection c = null;
            int v_lowerbound = pageIndex + 1;
            int v_upperbound = v_lowerbound + pageSize - 1;
            int params;
            List<FilterField> flFields =getFilterFields(filter);

            StringBuffer sql = new StringBuffer();
            sql.append(psql1);
            sql.append(msql);
           
            if(flFields.size()>0){

                    for(int i=0;i<flFields.size();i++){
                            sql.append(flFields.get(i).getSqlwhere());
                            System.out.println(sql);
                    }
            }
            sql.append(psql2);

            System.out.println(sql);
            try {
                    c = ConnectionPool.getConnection(alias);
                    PreparedStatement ps = c.prepareStatement(sql.toString());
                    for(params=0;params<flFields.size();params++){
                    ps.setObject(params+1, flFields.get(params).getColobject());
                    System.out.println("key "+flFields.get(params).getColobject());
                    }
                    params++;
                    ps.setInt(params++,v_upperbound);
                    ps.setInt(params++,v_lowerbound);

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                            list.add(new SwiftBuffer(
                                            rs.getString("branch"),
                                            rs.getLong("id"),
                                            rs.getString("message_type"),
                                            rs.getString("application_id"),
                                            rs.getString("service_id"),
                                            rs.getString("bic_from"),
                                            rs.getString("bic_to"),
                                            rs.getString("direction"),
                                            rs.getString("country_from"),
                                            rs.getString("country_to"),
                                            rs.getDate("value_date"),
                                            rs.getDouble("amount"),
                                            rs.getString("currency"),
                                            rs.getString("reference"),
                                            rs.getString("operation_code"),
                                            rs.getString("order_party"),
                                            rs.getString("ben_party"),
                                            rs.getString("narrative"),
                                            rs.getString("DETAILSOFCHARGES"),
                                            rs.getString("message_text"),
                                            rs.getDate("insert_date"),
                                            rs.getInt("state"),
                                            rs.getInt("deal_id"),
                                            rs.getInt("parent_group_id"),
                                            rs.getLong("parent_id"),
                                            rs.getLong("batch_id"),
                                            rs.getString("file_name"),
                                            rs.getString("order_party_acc"),
                                            rs.getString("ben_party_acc"),
                                            rs.getString("corr_acc")
                            
                            ));
                    }
            } catch (SQLException e) {
                    com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


    public SwiftBuffer getSwiftBuffer(int swiftbufferId) {

            SwiftBuffer swiftbuffer = new SwiftBuffer();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("SELECT * FROM swift_buffer WHERE id=?");
                    ps.setInt(1, swiftbufferId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            swiftbuffer = new SwiftBuffer();
                            
                            swiftbuffer.setBranch(rs.getString("branch"));
                            swiftbuffer.setId(rs.getLong("id"));
                            swiftbuffer.setMessage_type(rs.getString("message_type"));
                            swiftbuffer.setApplication_id(rs.getString("application_id"));
                            swiftbuffer.setService_id(rs.getString("service_id"));
                            swiftbuffer.setBic_from(rs.getString("bic_from"));
                            swiftbuffer.setBic_to(rs.getString("bic_to"));
                            swiftbuffer.setDirection(rs.getString("direction"));
                            swiftbuffer.setCountry_from(rs.getString("country_from"));
                            swiftbuffer.setCountry_to(rs.getString("country_to"));
                            swiftbuffer.setValue_date(rs.getDate("value_date"));
                            swiftbuffer.setAmount(rs.getDouble("amount"));
                            swiftbuffer.setCurrency(rs.getString("currency"));
                            swiftbuffer.setReference(rs.getString("reference"));
                            swiftbuffer.setOperation_code(rs.getString("operation_code"));
                            swiftbuffer.setOrder_party(rs.getString("order_party"));
                            swiftbuffer.setBen_party(rs.getString("ben_party"));
                            swiftbuffer.setNarrative(rs.getString("narrative"));
                            swiftbuffer.setMessage_text(rs.getString("message_text"));
                            swiftbuffer.setInsert_date(rs.getDate("insert_date"));
                            swiftbuffer.setState(rs.getInt("state"));
                            swiftbuffer.setDeal_id(rs.getInt("deal_id"));
                            swiftbuffer.setParent_group_id(rs.getInt("parent_group_id"));
                            swiftbuffer.setParent_id(rs.getLong("parent_id"));
                            swiftbuffer.setBatch_id(rs.getLong("batch_id"));
                            swiftbuffer.setFile_name(rs.getString("file_name"));
                    }
            } catch (Exception e) {
                    com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
            } finally {
                    ConnectionPool.close(c);
            }
            return swiftbuffer;
    }
    
    public static String getBicName(String bic) {

        String  res= "";
        Connection c = null;

        try {
                c = ConnectionPool.getConnection();
                PreparedStatement ps = c.prepareStatement("select substr(s.bic_code,5,2)||' '||s.city_heading||' '||substr(s.institution_name,1,50) name from swift_buffer_base s  where s.bic_code=?");
                ps.setString(1, bic);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                	
                	res = rs.getString("name");
                 }
        } catch (Exception e) {
                com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        } finally {
                ConnectionPool.close(c);
        }
        return res;
}


    public static SwiftBuffer create(SwiftBuffer swiftbuffer)  {

            Connection c = null;
            PreparedStatement ps = null;
            try {
                    c = ConnectionPool.getConnection();
                    ps = c.prepareStatement("SELECT SQ_swift_buffer.NEXTVAL id FROM DUAL");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            swiftbuffer.setId(rs.getLong("id"));
                    }
                    ps = c.prepareStatement("INSERT INTO swift_buffer (branch, id, message_type, application_id, service_id, bic_from, bic_to, direction, country_from, country_to, value_date, amount, currency, reference, operation_code, order_party, ben_party, narrative, message_text, insert_date, state, deal_id, parent_group_id, parent_id, batch_id, file_name) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,)");
                    
                    ps.setString(1,swiftbuffer.getBranch());
                    ps.setLong(2,swiftbuffer.getId());
                    ps.setString(3,swiftbuffer.getMessage_type());
                    ps.setString(4,swiftbuffer.getApplication_id());
                    ps.setString(5,swiftbuffer.getService_id());
                    ps.setString(6,swiftbuffer.getBic_from());
                    ps.setString(7,swiftbuffer.getBic_to());
                    ps.setString(8,swiftbuffer.getDirection());
                    ps.setString(9,swiftbuffer.getCountry_from());
                    ps.setString(10,swiftbuffer.getCountry_to());
                    ps.setDate(11,new java.sql.Date(swiftbuffer.getValue_date().getTime()));
                    ps.setDouble(12,swiftbuffer.getAmount());
                    ps.setString(13,swiftbuffer.getCurrency());
                    ps.setString(14,swiftbuffer.getReference());
                    ps.setString(15,swiftbuffer.getOperation_code());
                    ps.setString(16,swiftbuffer.getOrder_party());
                    ps.setString(17,swiftbuffer.getBen_party());
                    ps.setString(18,swiftbuffer.getNarrative());
                    ps.setString(19,swiftbuffer.getDetailsOfCharges());
                    ps.setString(20,swiftbuffer.getMessage_text());
                    ps.setDate(21,new java.sql.Date(swiftbuffer.getInsert_date().getTime()));
                    ps.setDouble(22,swiftbuffer.getState());
                    ps.setDouble(23,swiftbuffer.getDeal_id());
                    ps.setDouble(24,swiftbuffer.getParent_group_id());
                    ps.setLong(25,swiftbuffer.getParent_id());
                    ps.setLong(26,swiftbuffer.getBatch_id());
                    ps.setString(27,swiftbuffer.getFile_name());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

            } finally {
                    ConnectionPool.close(c);
            }
            return swiftbuffer;
    }

    public static void update(SwiftBuffer swiftbuffer)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("UPDATE swift_buffer SET branch=?, id=?, message_type=?, application_id=?, service_id=?, bic_from=?, bic_to=?, direction=?, country_from=?, country_to=?, value_date=?, amount=?, currency=?, reference=?, operation_code=?, order_party=?, ben_party=?, narrative=?, message_text=?, insert_date=?, state=?, deal_id=?, parent_group_id=?, parent_id=?, batch_id=?, file_name=?,  WHERE id=?");
                    
                    ps.setString(1,swiftbuffer.getBranch());
                    ps.setLong(2,swiftbuffer.getId());
                    ps.setString(3,swiftbuffer.getMessage_type());
                    ps.setString(4,swiftbuffer.getApplication_id());
                    ps.setString(5,swiftbuffer.getService_id());
                    ps.setString(6,swiftbuffer.getBic_from());
                    ps.setString(7,swiftbuffer.getBic_to());
                    ps.setString(8,swiftbuffer.getDirection());
                    ps.setString(9,swiftbuffer.getCountry_from());
                    ps.setString(10,swiftbuffer.getCountry_to());
                    ps.setDate(11,new java.sql.Date(swiftbuffer.getValue_date().getTime()));
                    ps.setDouble(12,swiftbuffer.getAmount());
                    ps.setString(13,swiftbuffer.getCurrency());
                    ps.setString(14,swiftbuffer.getReference());
                    ps.setString(15,swiftbuffer.getOperation_code());
                    ps.setString(16,swiftbuffer.getOrder_party());
                    ps.setString(17,swiftbuffer.getBen_party());
                    ps.setString(18,swiftbuffer.getNarrative());
                    ps.setString(19,swiftbuffer.getMessage_text());
                    ps.setDate(20,new java.sql.Date(swiftbuffer.getInsert_date().getTime()));
                    ps.setDouble(21,swiftbuffer.getState());
                    ps.setDouble(22,swiftbuffer.getDeal_id());
                    ps.setDouble(23,swiftbuffer.getParent_group_id());
                    ps.setLong(24,swiftbuffer.getParent_id());
                    ps.setLong(25,swiftbuffer.getBatch_id());
                    ps.setString(26,swiftbuffer.getFile_name());
                    ps.executeUpdate();
                    c.commit();
            } catch (SQLException e) {
                    com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

            } finally {
                    ConnectionPool.close(c);
            }

    }

    public static void remove(SwiftBuffer swiftbuffer)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("DELETE FROM swift_buffer WHERE id=?");
                    ps.setLong(1, swiftbuffer.getId());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
            } finally {
                    ConnectionPool.close(c);
            }
    }
    
    
    
    
    
    
    
    
    public static Res doAction( SwiftBuffer swiftbuffer,int actionid,  Connection c) {
        Res res =null;
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
         DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
        otherSymbols.setDecimalSeparator(',');
        otherSymbols.setGroupingSeparator('.'); 
        DecimalFormat nf = new DecimalFormat("0.00##");
        //Connection c = null;
        CallableStatement cs = null;
        CallableStatement acs = null;
        CallableStatement ccs = null;

        try {
                //c = ConnectionPool.getConnection(un,pw,alias);
        	     Statement st = c.createStatement();
        	    st.executeUpdate("ALTER SESSION SET NLS_NUMERIC_CHARACTERS = '. '");
                cs = c.prepareCall("{ call Param.SetParam(?,?) }");
                acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
                ccs = c.prepareCall("{ call Param.clearparam() }");
                ccs.execute();
                ccs = c.prepareCall("{? = call Param.getparam('ID') }");
                ccs.registerOutParameter(1, java.sql.Types.VARCHAR);
                
                 //cs.setString(1, "BRANCH");  cs.setString(2,swiftbuffer.getBranch()); cs.execute();
                System.out.println("Start "+c.isClosed());
                
               // System.out.println("Old 103 "+swiftbuffer.getId()+"  action "+actionid+" id "+swiftbuffer.getId()+" ;");
                if (!CheckNull.isEmpty(swiftbuffer.getId())){
                  // System.out.println("New 103 "+swiftbuffer.getAmount()+"  ben "+swiftbuffer.getBen_party());
                   cs.setString(1, "ID");  cs.setString(2,swiftbuffer.getId()+""); cs.execute();
                }else{
                	cs.setString(1, "ID");  cs.setString(2,null); cs.execute();
                }
               // System.out.println("amount "+nf.format(swiftbuffer.getAmount()).replace(",", "."));
                System.out.println("ID "+c.isClosed());
                 cs.setString(1, "MESSAGE_TYPE");  cs.setString(2,swiftbuffer.getMessage_type()); cs.execute();
                 cs.setString(1, "APPLICATION_ID");  cs.setString(2,swiftbuffer.getApplication_id()); cs.execute();
                 cs.setString(1, "SERVICE_ID");  cs.setString(2,swiftbuffer.getService_id()); cs.execute();
                 cs.setString(1, "BIC_FROM");  cs.setString(2,swiftbuffer.getBic_from()); cs.execute();
                 cs.setString(1, "BIC_TO");  cs.setString(2,swiftbuffer.getBic_to()); cs.execute();
                 cs.setString(1, "DIRECTION");  cs.setString(2,swiftbuffer.getDirection()); cs.execute();
                 cs.setString(1, "COUNTRY_FROM");  cs.setString(2,swiftbuffer.getCountry_from()); cs.execute();
                 cs.setString(1, "COUNTRY_TO");  cs.setString(2,swiftbuffer.getCountry_to()); cs.execute();
                 cs.setString(1, "VALUE_DATE");  cs.setString(2,swiftbuffer.getValue_date()!=null?df.format(swiftbuffer.getValue_date()):null); cs.execute();
                 cs.setString(1, "AMOUNT");  cs.setString(2,nf.format(swiftbuffer.getAmount()).replace(",", ".")); cs.execute();
                 cs.setString(1, "CURRENCY");  cs.setString(2,swiftbuffer.getCurrency()); cs.execute();
                 cs.setString(1, "REFERENCE");  cs.setString(2,swiftbuffer.getReference()); cs.execute();
                 cs.setString(1, "OPERATION_CODE");  cs.setString(2,swiftbuffer.getOperation_code()); cs.execute();
                 cs.setString(1, "ORDER_PARTY");  cs.setString(2,swiftbuffer.getOrder_party()); cs.execute();
                 cs.setString(1, "BEN_PARTY");  cs.setString(2,swiftbuffer.getBen_party()); cs.execute();
                 cs.setString(1, "NARRATIVE");  cs.setString(2,swiftbuffer.getNarrative()); cs.execute();
                 cs.setString(1, "DETAILSOFCHARGES");  cs.setString(2,swiftbuffer.getDetailsOfCharges()); cs.execute();
                 cs.setString(1, "ORDER_PARTY_ACC");  cs.setString(2,swiftbuffer.getOrder_party_acc()); cs.execute();
                 cs.setString(1, "BEN_PARTY_ACC");  cs.setString(2,swiftbuffer.getBen_party_acc()); cs.execute();
                 cs.setString(1, "CORR_ACC");  cs.setString(2,swiftbuffer.getCorr_acc()); cs.execute();
                 
                 
                // cs.setString(1, "MESSAGE_TEXT");  cs.setString(2,swiftbuffer.getMessage_text()); cs.execute();
                 //cs.setString(1, "INSERT_DATE");  cs.setString(2,swiftbuffer.getInsert_date()); cs.execute();
                 //cs.setString(1, "STATE");  cs.setString(2,swiftbuffer.getState()); cs.execute();
                 //cs.setString(1, "DEAL_ID");  cs.setString(2,swiftbuffer.getDealId()); cs.execute();
                 //cs.setString(1, "PARENT_GROUP_ID");  cs.setString(2,swiftbuffer.getParentGroupId()); cs.execute();
                 //cs.setString(1, "PARENT_ID");  cs.setString(2,swiftbuffer.getParentId()); cs.execute();
                 //cs.setString(1, "BATCH_ID");  cs.setString(2,swiftbuffer.getBatch_id()); cs.execute();
                 //cs.setString(1, "FILE_NAME");  cs.setString(2,swiftbuffer.getFile_name()); cs.execute();
                 System.out.println("Before doAction "+c.isClosed());
                acs.setInt(1, 191);
                acs.setInt(2, 1);
                acs.setInt(3,actionid);
                acs.execute();
                System.out.println("After doAction "+c.isClosed());
                //c.commit();
                ccs.execute();
                
                System.out.println("After getId "+c.isClosed());
                res = new Res(0,ccs.getString(1));
                System.out.println("After res "+c.isClosed());

    } catch (Exception e) {
            res = new Res(-1, e.getMessage());
           e.printStackTrace();
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
    } finally {
            //ConnectionPool.close(c);
    }
    return res;
    }
    
    
    
    
    public static List<Account> getAccount( String whr, String alias )  {

        List<Account> list = new ArrayList<Account>();
        Connection c = null;
        ResultSet rs = null;
       // String whr = "_____840%";
        String nm ="";

        try {
                c = ConnectionPool.getConnection( alias);

                
                Statement s = c.createStatement();
                rs = s.executeQuery("SELECT * FROM Account where id like '"+whr+"' and state=2 and rownum<100");
                while (rs.next()) {
                        list.add(new Account(
                                        rs.getString("branch"),
                                        rs.getString("id"),
                                        rs.getString("acc_bal"),
                                        rs.getString("currency"),
                                        rs.getString("client"),
                                        rs.getString("id_order"),
                                        rs.getString("name"),
                                        rs.getString("sgn"),
                                        rs.getString("bal"),
                                        rs.getInt("sign_registr"),
                                        rs.getLong("s_in"),
                                        rs.getLong("s_out"),
                                        rs.getLong("dt"),
                                        rs.getLong("ct"),
                                        rs.getLong("s_in_tmp"),
                                        rs.getLong("s_out_tmp"),
                                        rs.getLong("dt_tmp"),
                                        rs.getLong("ct_tmp"),
                                        rs.getDate("l_date"),
                                        rs.getDate("date_open"),
                                        rs.getDate("date_close"),
                                        rs.getInt("acc_group_id"),
                                        rs.getInt("state")));
                }
           
               
                 
                 
                
        } catch (SQLException e) {
                com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        } finally {
                ConnectionPool.close(c);
        }
        return list;

}   

    
    public static  List<Ofac> getOfac(String  fname) {
    	 List<Ofac> list = new ArrayList<Ofac>();
    	 System.out.println("Часть 0 "+fname);    	 
    	 String[] fstr = fname.split(" ");
    	
        Connection c = null;

        try {
                c = ConnectionPool.getConnection();
                PreparedStatement ps = c.prepareStatement("select c.programlist , "+
       "c.full_name   , "+
       "c.Perc        , "+
       "c.sdntype "+     
       "from (select utl_match.jaro_winkler_similarity(trim(lower('%'||?||'%')), "+
       "                                          lower(c.firstname || ' ' || "+
       "                                               c.lastname)) as Perc, "+
       "        c.firstname || ' ' || c.lastname full_name, c.* "+
       "   from ofac_main c "+
       "  where lower(c.firstname || ' ' || c.lastname) like lower('%'||?||'%')) c order by 1");
                
			for (int i = 0; i < fstr.length; i++) {
System.out.println("Часть "+i+"  "+fstr[i]);
    if (fstr[i].length()>4){
				ps.setString(1, fstr[i]);
				ps.setString(2, fstr[i]);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					list.add(new Ofac(
							rs.getString(1), 
							rs.getString(2), 
							rs.getString(3), 
							rs.getString(4)));
				}
			}
			}
        } catch (Exception e) {
                com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        } finally {
                ConnectionPool.close(c);
        }
        return list;
}
    
	public static Res crDoc(long operation_id, SwiftBuffer sb,  Connection c) {
		Res rs = new Res();
		//Connection c = null;
        rs.setName("Ok");
        TransactionService ts;
        CallableStatement cs_prep;
        Parameters ps = new Parameters();
		try {
		//	System.out.println("Doc "+c.isClosed());
			//c = ConnectionPool.getConnection(un, pw, alias);

             ts = new TransactionService();
           ts.init(c);
            cs_prep = accounting_transaction.Service.init_get_deal_general(c);
           /*
           long operation_id = 191l;
          
           if(Integer.parseInt(request.getRequest_type_code()) == 1)
                   operation_id = 2002l;
           if(Integer.parseInt(request.getRequest_type_code()) == 2)
                   operation_id = 2003l;
           if(Integer.parseInt(request.getRequest_type_code()) == 3)
                   operation_id = 2004l;
           if(Integer.parseInt(request.getRequest_type_code()) == 4)
                   operation_id = 2005l;
*/
           
           ps.put("branch", sb.getBranch());
           ps.put("operation_id", operation_id);
           ps.put("parent_group_id", 191l);
           ps.put("parent_deal_id", 1);
           ps.put("SUMMA",  ((Double)(sb.getAmount().doubleValue()*100)).longValue());
           ps.put("PARENT_ID", sb.getId());
           ps.put("cs_prep", cs_prep);
           ps.put("SWIFT_ACC_CL", sb.getOrder_party_acc());
           ps.put("SWIFT_COR_ACC", sb.getCorr_acc());
           ps.put("order_party", sb.getOrder_party());
           ps.put("ben_party", sb.getBen_party());
           ps.put("order_party_acc", sb.getOrder_party_acc());
           ps.put("ben_party_acc", sb.getBen_party_acc());
           ps.put("narrative", sb.getNarrative());
          // System.out.println("Doc After put "+c.isClosed());
           //long object_id = com.is.file_reciever_srv.ext_utils.Service.get_created_object_id(c);

                   long tr_id = 0;
                   tr_id = ts.execute_operation(
                                   operation_id,
                                   ps,
                                   c);
                   //System.out.println("Doc After operation "+c.isClosed()+" tr_id "+tr_id);        
//                   ISLogger.getLogger().info("AAAAAAAAAA1"+tr_id);
                  // request.setIs_sent_inkassa(true);
                  // update_request(request, c);

//                   c.commit();
                   List<General> lg =  accounting_transaction.Service.action_general_doc(tr_id,
                           19,
                           c);
           
           //System.out.println("Doc after  general "+c.isClosed()+" lg "+lg.size());

           //c.commit();
           //System.out.println("Doc after  commit "+c.isClosed()+" tr_id ");
           
		} catch (Exception e) {
			rs.setName(e.toString());
			rs.setCode(-1);
			e.printStackTrace();
		com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			//ConnectionPool.close(c);
			
			//cs_prep
			//ps
			//lg
		}

		return rs;
	}
	
	public static Res edDoc(long operation_id, SwiftBuffer sb,  Connection c) {
		Res rs = new Res();
        rs.setName("Ok");
        TransactionService ts;
        CallableStatement cs_prep;
        Parameters ps = new Parameters();
		try {

             ts = new TransactionService();
           ts.init(c);
            cs_prep = accounting_transaction.Service.init_get_deal_general(c);

            ps.put("id", sb.getId());
           ps.put("branch", sb.getBranch());
           ps.put("operation_id", operation_id);
           ps.put("parent_group_id", 191l);
           ps.put("parent_deal_id", 1);
           ps.put("SUMMA",  ((Double)(sb.getAmount().doubleValue()*100)).longValue());
           ps.put("PARENT_ID", sb.getId());
           ps.put("cs_prep", cs_prep);
           ps.put("SWIFT_ACC_CL", sb.getOrder_party_acc());
           ps.put("SWIFT_COR_ACC", sb.getCorr_acc());
           ps.put("order_party", sb.getOrder_party());
           ps.put("ben_party", sb.getBen_party());
           ps.put("order_party_acc", sb.getOrder_party_acc());
           ps.put("ben_party_acc", sb.getBen_party_acc());
           ps.put("narrative", sb.getNarrative());
          // System.out.println("Doc After put "+c.isClosed());
           //long object_id = com.is.file_reciever_srv.ext_utils.Service.get_created_object_id(c);

                   long tr_id = 0;
                   tr_id = ts.execute_operation(//надо сохранять
                                   operation_id,
                                   ps,
                                   c);
                   List<General> lg =  accounting_transaction.Service.action_general_doc(tr_id,
                           1,
                           c);
           
		} catch (Exception e) {
			rs.setName(e.toString());
			rs.setCode(-1);
			e.printStackTrace();
		com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
		}

		return rs;
	}
	
    
}
