package com.is.comjpayment;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.is.ConnectionPool;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.Res;

public class ComJpaymentService {
    private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ? order by id desc";
    private static String msql ="SELECT * FROM BF_COM_JPAYMENTS ";



   



  public static Res doAction(String un,String pw, ComJpayment comjpayment,int actionid, String alias) {
     Res res =null;
     SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
     Connection c = null;
     CallableStatement cs = null;
     CallableStatement acs = null;
     CallableStatement ccs = null;

     try {
             c = ConnectionPool.getConnection(un,pw, alias);
             cs = c.prepareCall("{ call Param.SetParam(?,?) }");
             acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
             ccs = c.prepareCall("{ call Param.clearparam() }");
             ccs.execute();
             ccs = c.prepareCall("{? = call Param.getparam('ID') }");
             ccs.registerOutParameter(1, java.sql.Types.NUMERIC);

             
              if(!CheckNull.isEmpty(comjpayment.getId())){
              cs.setString(1, "ID");  cs.setString(2,comjpayment.getId()+""); cs.execute();
              }
                       
              
              cs.setString(1, "SERVICES_LIST_ID");  cs.setString(2,comjpayment.getServices_list_id()+""); cs.execute();
              if(!CheckNull.isEmpty(comjpayment.getP_name())){
              cs.setString(1, "P_NAME");  cs.setString(2,comjpayment.getP_name()); cs.execute();
              }
              if(!CheckNull.isEmpty(comjpayment.getP_number())){
              cs.setString(1, "P_NUMBER");  cs.setString(2,comjpayment.getP_number()); cs.execute();
              }
              if(!CheckNull.isEmpty(comjpayment.getDistrict_id())){
                  cs.setString(1, "District_id");  cs.setInt(2,comjpayment.getDistrict_id()); cs.execute();
                  }
              if(!CheckNull.isEmpty(comjpayment.getFrom_date())){
              cs.setString(1, "FROM_DATE");  cs.setString(2,bdf.format(comjpayment.getFrom_date())); cs.execute();
              }
              if(!CheckNull.isEmpty(comjpayment.getTo_date())){
              cs.setString(1, "TO_DATE");  cs.setString(2,bdf.format(comjpayment.getTo_date())); cs.execute();
              }
              if(!CheckNull.isEmpty(comjpayment.getFrom_value())){
              cs.setString(1, "FROM_VALUE");  cs.setString(2,comjpayment.getFrom_value()); cs.execute();
              }
              if(!CheckNull.isEmpty(comjpayment.getTo_value())){
              cs.setString(1, "TO_VALUE");  cs.setString(2,comjpayment.getTo_value()); cs.execute();
              }
              if(!CheckNull.isEmpty(comjpayment.getDifference())){
              cs.setString(1, "DIFFERENCE");  cs.setString(2,comjpayment.getDifference()); cs.execute();
              }
//              cs.setString(1, "PENALTY_AMOUNT");  cs.setString(2,comjpayment.getPenalty_amount()); cs.execute();
              cs.setString(1, "AMOUNT");  cs.setString(2,comjpayment.getAmount()+""); cs.execute();
//              cs.setString(1, "FULL_AMOUNT");  cs.setString(2,comjpayment.getFull_amount()); cs.execute();
              cs.setString(1, "CURRENCY");  cs.setString(2,comjpayment.getCurrency()); cs.execute();
//              cs.setString(1, "PROVIDER_AMOUNT");  cs.setString(2,comjpayment.getProvider_amount()); cs.execute();
//              cs.setString(1, "FEE_AMOUNT");  cs.setString(2,comjpayment.getFee_amount()); cs.execute();
//              cs.setString(1, "CUSTOMER_ID");  cs.setString(2,comjpayment.getCustomer_id()); cs.execute();
//              cs.setString(1, "DEAL_ID");  cs.setString(2,comjpayment.getDealId()); cs.execute();
              cs.setString(1, "STATE");  cs.setString(2,comjpayment.getState()+""); cs.execute();
              cs.setString(1, "CUSTOMERJ_ID");  cs.setString(2,comjpayment.getCustomerj_id()+""); cs.execute();
//              cs.setString(1, "DOCUMENT_ID");  cs.setString(2,comjpayment.getDocument_id()); cs.execute();
//              cs.setString(1, "TRANSACTION_ID");  cs.setString(2,comjpayment.getTransaction_id()); cs.execute();
              cs.setString(1, "BRANCH_ID");  cs.setString(2,comjpayment.getBranch_id()+""); cs.execute();
              cs.setString(1, "SUBBRANCH_ID");  cs.setString(2,comjpayment.getSubbranch_id()+""); cs.execute();
 //             cs.setString(1, "DATE_COMPLETE");  cs.setString(2,comjpayment.getDate_complete()); cs.execute();
              cs.setString(1, "OPERATION_ID");  cs.setString(2,comjpayment.getOperation_id()+""); cs.execute();
//              cs.setString(1, "PARENT_ID");  cs.setString(2,comjpayment.getParentId()); cs.execute();
//              cs.setString(1, "PARENT_GROUP_ID");  cs.setString(2,comjpayment.getParentGroupId()); cs.execute();
              cs.setString(1, "PAYMENT_TYPE_ID");  cs.setString(2,comjpayment.getPayment_type_id()+""); cs.execute();
//              cs.setString(1, "PRT_ID");  cs.setString(2,comjpayment.getPrt_id()); cs.execute();
              if(!CheckNull.isEmpty(comjpayment.getClient_address())){
              cs.setString(1, "CLIENT_ADDRESS");  cs.setString(2,comjpayment.getClient_address()); cs.execute();
              }
//              cs.setString(1, "PROVIDER_DISCOUNT_AMOUNT");  cs.setString(2,comjpayment.getProvider_discount_amount()); cs.execute();
              if(!CheckNull.isEmpty(comjpayment.getBudget_inn())){
              cs.setString(1, "BUDGET_INN");  cs.setString(2,comjpayment.getBudget_inn()); cs.execute();
              }
              if(!CheckNull.isEmpty(comjpayment.getBudget_account())){
              cs.setString(1, "BUDGET_ACCOUNT");  cs.setString(2,comjpayment.getBudget_account()); cs.execute();
              }
             acs.setInt(1, 147);
             acs.setInt(2, 1);
             acs.setInt(3,actionid);
             acs.execute();
             c.commit();
             ccs.execute();
             res = new Res(0,ccs.getString(1));


 } catch (Exception e) {
         res = new Res(-1, e.getMessage());
 } finally {
         ConnectionPool.close(c);
 }
 return res;
}

   public static String doAction(String un,String pw, String branch, String id,int actionid) {
        String res ="";
        SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
        Connection c = null;
        CallableStatement cs = null;
        CallableStatement acs = null;
        CallableStatement ccs = null;
        String cn;
    try {
            c = ConnectionPool.getConnection(un,pw);
            cs = c.prepareCall("{ call Param.SetParam(?,?) }");
            acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
            ccs = c.prepareCall("{ call Param.clearparam() }");


            PreparedStatement ps = c.prepareStatement("SELECT * FROM ComJpayment WHERE branch=? and id=? order by id desc");
            ps.setString(1, branch);
            ps.setString(2, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                    ccs.execute();
                     for (int i=1;  i<=rs.getMetaData().getColumnCount();i++){
                             cn = rs.getMetaData().getColumnName(i);
                            // System.out.println(cn+"  "+ rs.getMetaData().getColumnTypeName(i));
                               if( rs.getString(cn)!=null){
                                   cs.setString(1, cn);
                                   if (rs.getMetaData().getColumnTypeName(i).equals("DATE")){
                                      cs.setString(2,bdf.format(rs.getDate(cn)));
                                   }else{
                                      cs.setString(2,rs.getString(cn));
                                   }
                                   cs.execute();
                               }
                     }

                 acs.setInt(1, 2);
                 acs.setInt(2, 2);
                 acs.setInt(3,actionid);
                 acs.execute();
                 c.commit();
            }
    } catch (Exception e) {
           // e.printStackTrace();
            res = e.getMessage();
    } finally {
            ConnectionPool.close(c);
    }
    return res;
}


    private static String getCond(List<FilterField> flfields){
            if(flfields.size()>0){
                    return " and ";
            }else
            return " where ";
    }

    private static List<FilterField> getFilterFields(ComJpaymentFilter filter){
            List<FilterField> flfields = new ArrayList<FilterField>();


          if(!CheckNull.isEmpty(filter.getId())){
                  flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
          }
          if(!CheckNull.isEmpty(filter.getServices_list_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "services_list_id=?",filter.getServices_list_id()));
          }
          if(!CheckNull.isEmpty(filter.getP_name())){
                  flfields.add(new FilterField(getCond(flfields)+ "p_name=?",filter.getP_name()));
          }
          if(!CheckNull.isEmpty(filter.getP_number())){
                  flfields.add(new FilterField(getCond(flfields)+ "p_number=?",filter.getP_number()));
          }
          if(!CheckNull.isEmpty(filter.getFrom_date())){
                  flfields.add(new FilterField(getCond(flfields)+ "from_date=?",filter.getFrom_date()));
          }
          if(!CheckNull.isEmpty(filter.getTo_date())){
                  flfields.add(new FilterField(getCond(flfields)+ "to_date=?",filter.getTo_date()));
          }
          if(!CheckNull.isEmpty(filter.getFrom_value())){
                  flfields.add(new FilterField(getCond(flfields)+ "from_value=?",filter.getFrom_value()));
          }
          if(!CheckNull.isEmpty(filter.getTo_value())){
                  flfields.add(new FilterField(getCond(flfields)+ "to_value=?",filter.getTo_value()));
          }
          if(!CheckNull.isEmpty(filter.getDifference())){
                  flfields.add(new FilterField(getCond(flfields)+ "difference=?",filter.getDifference()));
          }
          if(!CheckNull.isEmpty(filter.getPenalty_amount())){
                  flfields.add(new FilterField(getCond(flfields)+ "penalty_amount=?",filter.getPenalty_amount()));
          }
          if(!CheckNull.isEmpty(filter.getAmount())){
                  flfields.add(new FilterField(getCond(flfields)+ "amount=?",filter.getAmount()));
          }
          if(!CheckNull.isEmpty(filter.getFull_amount())){
                  flfields.add(new FilterField(getCond(flfields)+ "full_amount=?",filter.getFull_amount()));
          }
          if(!CheckNull.isEmpty(filter.getCurrency())){
                  flfields.add(new FilterField(getCond(flfields)+ "currency=?",filter.getCurrency()));
          }
          if(!CheckNull.isEmpty(filter.getProvider_amount())){
                  flfields.add(new FilterField(getCond(flfields)+ "provider_amount=?",filter.getProvider_amount()));
          }
          if(!CheckNull.isEmpty(filter.getFee_amount())){
                  flfields.add(new FilterField(getCond(flfields)+ "fee_amount=?",filter.getFee_amount()));
          }
          if(!CheckNull.isEmpty(filter.getCustomer_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "customer_id=?",filter.getCustomer_id()));
          }
          if(!CheckNull.isEmpty(filter.getState())){
                  flfields.add(new FilterField(getCond(flfields)+ "state=?",filter.getState()));
          }
          if(!CheckNull.isEmpty(filter.getCustomerj_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "customerj_id=?",filter.getCustomerj_id()));
          }
          if(!CheckNull.isEmpty(filter.getDocument_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "document_id=?",filter.getDocument_id()));
          }
          if(!CheckNull.isEmpty(filter.getTransaction_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "transaction_id=?",filter.getTransaction_id()));
          }
          if(!CheckNull.isEmpty(filter.getBranch_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "branch_id=?",filter.getBranch_id()));
          }
          if(!CheckNull.isEmpty(filter.getSubbranch_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "subbranch_id=?",filter.getSubbranch_id()));
          }
          if(!CheckNull.isEmpty(filter.getDate_complete())){
                  flfields.add(new FilterField(getCond(flfields)+ "date_complete=?",filter.getDate_complete()));
          }
          if(!CheckNull.isEmpty(filter.getOperation_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "operation_id=?",filter.getOperation_id()));
          }
/*
          if(!CheckNull.isEmpty(filter.getParentId())){
                  flfields.add(new FilterField(getCond(flfields)+ "parent_id=?",filter.getParentId()));
          }
          if(!CheckNull.isEmpty(filter.getParentGroupId())){
                  flfields.add(new FilterField(getCond(flfields)+ "parent_group_id=?",filter.getParentGroupId()));
          }
*/          
          if(!CheckNull.isEmpty(filter.getPayment_type_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "payment_type_id=?",filter.getPayment_type_id()));
          }
          if(!CheckNull.isEmpty(filter.getPrt_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "prt_id=?",filter.getPrt_id()));
          }
          if(!CheckNull.isEmpty(filter.getClient_address())){
                  flfields.add(new FilterField(getCond(flfields)+ "client_address=?",filter.getClient_address()));
          }
          if(!CheckNull.isEmpty(filter.getProvider_discount_amount())){
                  flfields.add(new FilterField(getCond(flfields)+ "provider_discount_amount=?",filter.getProvider_discount_amount()));
          }
          if(!CheckNull.isEmpty(filter.getBudget_inn())){
                  flfields.add(new FilterField(getCond(flfields)+ "budget_inn=?",filter.getBudget_inn()));
          }
          if(!CheckNull.isEmpty(filter.getBudget_account())){
                  flfields.add(new FilterField(getCond(flfields)+ "budget_account=?",filter.getBudget_account()));
          }

          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(ComJpaymentFilter filter, String alias)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM BF_COM_JPAYMENTS ");
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
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }
        return n;

}



    public static List<ComJpayment> getComJpaymentsFl(int pageIndex, int pageSize, ComJpaymentFilter filter,String alias)  {

            List<ComJpayment> list = new ArrayList<ComJpayment>();
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
                    }
            }
            sql.append(psql2);


            try {
                    c = ConnectionPool.getConnection(alias);
                    PreparedStatement ps = c.prepareStatement(sql.toString());
                    for(params=0;params<flFields.size();params++){
                    ps.setObject(params+1, flFields.get(params).getColobject());
                    }
                    params++;
                    ps.setInt(params++,v_upperbound);
                    ps.setInt(params++,v_lowerbound);

                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                            list.add(new ComJpayment(
                                            rs.getInt("id"),
                                            rs.getInt("services_list_id"),
                                            rs.getString("p_name"),
                                            rs.getString("p_number"),
                                            rs.getDate("from_date"),
                                            rs.getDate("to_date"),
                                            rs.getString("from_value"),
                                            rs.getString("to_value"),
                                            rs.getString("difference"),
                                            rs.getInt("penalty_amount"),
                                            rs.getInt("amount"),
                                            rs.getInt("full_amount"),
                                            rs.getString("currency"),
                                            rs.getInt("provider_amount"),
                                            rs.getInt("fee_amount"),
                                            rs.getInt("customer_id"),
                                            rs.getInt("deal_id"),
                                            rs.getInt("state"),
                                            rs.getInt("customerj_id"),
                                            rs.getInt("document_id"),
                                            rs.getInt("transaction_id"),
                                            rs.getInt("branch_id"),
                                            rs.getInt("subbranch_id"),
                                            rs.getDate("date_complete"),
                                            rs.getInt("operation_id"),
                                            rs.getInt("parent_id"),
                                            rs.getInt("parent_group_id"),
                                            rs.getInt("payment_type_id"),
                                            rs.getInt("prt_id"),
                                            rs.getString("client_address"),
                                            rs.getInt("provider_discount_amount"),
                                            rs.getString("budget_inn"),
                                            rs.getString("budget_account"),
                                            rs.getInt("district_id")));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }
    
    public static void set_addinfo(HashMap< String,String> addInfo, int payment_id, String alias)
    {
    	Connection c = null;
    	try
    	{
            c = ConnectionPool.getConnection(alias);
            
            Iterator it = addInfo.entrySet().iterator();
	    	while(it.hasNext())
	    	{
	    		//it.next();
	    		Map.Entry entry = (Map.Entry) it.next(); 
	    		PreparedStatement ps = c.prepareStatement("Insert into bf_com_jpay_addinfo (id, a_key, a_value) values (?, ?, ?)");
	    		//System.out.println("Insert into bf_com_jpay_addinfo (id, a_key, a_value) values ("+payment_id+", "+(String)entry.getKey()+", "+(String)entry.getValue()+")");
	    		ps.setInt(1, payment_id);
	    		ps.setString(2, (String)entry.getKey());
	    		ps.setString(3, (String)entry.getValue());
	    		ps.executeQuery();
	    	}
	    	c.commit();
    	}
    	catch (SQLException e)
    	{
    		e.printStackTrace();
    	}
    	finally
    	{
    		ConnectionPool.close(c);
    	}
    }
    
    
    public static HashMap< String,String> get_addinfo(int payment_id, int service_id, String lang, String alias)
    {
    	HashMap< String,String> addInfo = new HashMap< String,String>();
    	
    	Connection c = null;
    	try
    	{
            c = ConnectionPool.getConnection(alias);
            
            PreparedStatement ps = c.prepareStatement("select addinf.*, lab.name_"+lang+" caption" +
									" from bf_com_jpay_addinfo addinf, bf_com_jpay_addinfo_labels lab" +
									" where addinf.id = ?" +
									      " and addinf.a_key = lab.key_id" +
									      " and lab.service_id = ?");
    		/*System.out.println("select addinf.*, lab.name_"+lang+" caption" +
					" from bf_com_jpay_addinfo addinf, bf_com_jpay_addinfo_labels lab" +
					" where addinf.id = "+payment_id+"" +
					      " and addinf.a_key = lab.key_id" +
					      " and lab.service_id = "+service_id+"");*/
    		ps.setInt(1, payment_id);
    		ps.setInt(2, service_id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next())
            {
            	addInfo.put(rs.getString("caption"), rs.getString("a_value"));
            }
    	}
    	catch (SQLException e)
    	{
    		e.printStackTrace();
    	}
    	finally
    	{
    		ConnectionPool.close(c);
    	}
    	return addInfo;
    }

}
