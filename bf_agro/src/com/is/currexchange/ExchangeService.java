package com.is.currexchange;

import general.General;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.AccInfo;
import com.is.ConnectionPool;
import com.is.Cr;
import com.is.CurrEx;
import com.is.CurrExch2;
import com.is.ISLogger;
import com.is.kernel.parameter.Parameters;
import com.is.sd_books.model.Credentials;
import com.is.sd_books.service.DepositService;
import com.is.VisaPay;
import com.is.utils.CheckNull;
import com.is.utils.Res;

import accounting_transaction_2.Transaction_service;

public class ExchangeService {
    private static String port;
    private static int state=0;
    private static String BANK_SCHEMA = ConnectionPool.getValue("BANK_SCHEMA");
    
    public CurrExchange getCurrExchange(int currexchangeId) {

        CurrExchange currexchange = new CurrExchange();
        Connection c = null;

        try {
                c = ConnectionPool.getConnection();
                PreparedStatement ps = c.prepareStatement("SELECT * FROM currexchange WHERE extid=?");
                ps.setInt(1, currexchangeId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                        currexchange = new CurrExchange();
                        
                        currexchange.setId(rs.getInt("id"));
                        currexchange.setBank_dt(rs.getString("bank_dt"));
                        currexchange.setAcc_dt(rs.getString("acc_dt"));
                        currexchange.setCard_dt(rs.getString("card_dt"));
                        currexchange.setBank_ct(rs.getString("bank_ct"));
                        currexchange.setAcc_ct(rs.getString("acc_ct"));
                        currexchange.setCard_ct(rs.getString("card_ct"));
                        currexchange.setAmount(rs.getInt("amount"));
                        currexchange.setDdate(rs.getDate("ddate"));
                        currexchange.setCourse(rs.getInt("course"));
                        currexchange.setOperation(rs.getInt("operation"));
                        currexchange.setState(rs.getInt("state"));
                        currexchange.setExtid(rs.getInt("extid"));
                }
                ps.close();
        } catch (Exception e) {
                e.printStackTrace();
        } finally {
                ConnectionPool.close(c);
        }
        return currexchange;
}
    
    public static CurrExch2 createEx(Cr cr,CurrEx currex){  
    	
    	CurrExch2 currexchange= new CurrExch2();
        currexchange.setBank_dt(currex.getBank_dt());
        currexchange.setAcc_dt(currex.getAcc_dt());
        currexchange.setCard_dt(currex.getCard_ct());
        currexchange.setBank_ct(currex.getBank_ct());
        currexchange.setAcc_ct(currex.getAcc_ct());
        currexchange.setCard_ct(currex.getCard_ct());
        currexchange.setAmount(currex.getAmount());
        currexchange.setOperation(currex.getOperation());
        currexchange.setExtid(currex.getExtid());
        currexchange.setDdate(new java.util.Date());
        
        currexchange  = fillCurrEx(currexchange);
        
        Res res = Exchange1( cr, currex );
        if (res.getCode()==0){
        	currexchange.setState(1);
        	currexchange.setErrors(res.getName());
        }else{
        	currexchange.setState(-1);
        	currexchange.setErrors(res.getName());
       	
        }
        
        
    	return  create( currexchange) ;
    }
    
 public static CurrExch2 createExUSD(Cr cr,CurrEx currex){  
    	
    	CurrExch2 currexchange= new CurrExch2();
        currexchange.setBank_dt(currex.getBank_dt());
        currexchange.setAcc_dt(currex.getAcc_dt());
        currexchange.setCard_dt(currex.getCard_ct());
        currexchange.setBank_ct(currex.getBank_ct());
        currexchange.setAcc_ct(currex.getAcc_ct());
        currexchange.setCard_ct(currex.getCard_ct());
        currexchange.setAmount(currex.getAmount());
        currexchange.setOperation(currex.getOperation());
        currexchange.setExtid(currex.getExtid());
        currexchange.setDdate(new java.util.Date());
        
        currexchange  = fillCurrEx(currexchange);
        
        Res res = Exchange2( cr, currex );
        if (res.getCode()==0){
        	currexchange.setState(1);
        	currexchange.setErrors(res.getName());
        }else{
        	currexchange.setState(-1);
        	currexchange.setErrors(res.getName());
       	
        }
        
        
    	return  create( currexchange) ;
    }
    
    

public static CurrExch2 create(CurrExch2 currexchange)  {

        Connection c = null;
        PreparedStatement ps = null;
        try {
                c = ConnectionPool.getConnection();
                ps = c.prepareStatement("SELECT SEQ_currexchange.NEXTVAL id FROM DUAL");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                        currexchange.setId(rs.getInt("id"));
                }
                ps = c.prepareStatement("INSERT INTO currexchange (id, bank_dt, acc_dt, card_dt, bank_ct, acc_ct, card_ct, amount, ddate, course, state, operation, Extid ) VALUES (?,?,?,?,?,?,?,?,sysdate,?,?,?,?)");
                
                ps.setInt(1,currexchange.getId());
                ps.setString(2,currexchange.getBank_dt());
                ps.setString(3,currexchange.getAcc_dt());
                ps.setString(4,currexchange.getCard_dt());
                ps.setString(5,currexchange.getBank_ct());
                ps.setString(6,currexchange.getAcc_ct());
                ps.setString(7,currexchange.getCard_ct());
                ps.setLong(8,currexchange.getAmount());
               // ps.setDate(9,currexchange.getDdate());
                ps.setInt(9, currexchange.getCourse());
                ps.setInt(10,currexchange.getState());
                ps.setInt(11,currexchange.getOperation());
                ps.setInt(12,currexchange.getExtid());
                ps.executeUpdate();
                c.commit();
                ps.close();
        } catch (Exception e) {
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }
        return currexchange;
}



public static void update(CurrExchange currexchange)  {

        Connection c = null;

        try {
                c = ConnectionPool.getConnection();
                PreparedStatement ps = c.prepareStatement("UPDATE currexchange SET id=?, bank_dt=?, acc_dt=?, card_dt=?, bank_ct=?, acc_ct=?, card_ct=?, amount=?, ddate=?, course=?, state=?  WHERE id=?");
                
                ps.setInt(1,currexchange.getId());
                ps.setString(2,currexchange.getBank_dt());
                ps.setString(3,currexchange.getAcc_dt());
                ps.setString(4,currexchange.getCard_dt());
                ps.setString(5,currexchange.getBank_ct());
                ps.setString(6,currexchange.getAcc_ct());
                ps.setString(7,currexchange.getCard_ct());
                ps.setLong(8,currexchange.getAmount());
               // ps.setDate(9,currexchange.getDdate());
                ps.setInt(10,currexchange.getCourse());
                ps.setInt(11,currexchange.getState());
                ps.executeUpdate();
                c.commit();
                ps.close();
        } catch (SQLException e) {
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }

}
/*
public static Res Exchange(Cr cr, CurrEx ce ) {
    Res rs = new Res();
        Connection c = null;
        rs.setName("Ok");
        TransactionService ts;
        CallableStatement cs_prep;
        Parameters ps = new Parameters();
    try {
    	String BranchDBLink=DepositService.getSchiema(cr.getBranch());
        c = ConnectionPool.getConnection(cr.getLogin(),cr.getPassword(),BranchDBLink);
 
             ts = new TransactionService();
           ts.init(c);
            cs_prep = accounting_transaction.Service.init_get_deal_general(c);
           ps.put("branch", cr.getBranch());
           ps.put("operation_id", Long.valueOf( ce.getOperation()+""));
           ps.put("parent_group_id", 1100l);
           ps.put("parent_deal_id", 1);
           ps.put("SUMMA", ce.getAmount());
           ps.put("PARENT_ID", Long.parseLong("1"));
           ps.put("cs_prep", cs_prep);
           ps.put("DT_CARD_ACC", ce.getAcc_dt());
           ps.put("CT_CARD_ACC", ce.getAcc_ct());
           ps.put("AMNTCOUR",  ce.getAmount()*GetCource("840",4));
           
 //System.out.println("ps="+ps.toString());
                   long tr_id = 0;
                   tr_id = ts.execute_operation(
                		   (long)ce.getOperation(),
                                   ps,
                                   c);
                   List<General> lg =  accounting_transaction.Service.action_general_doc(tr_id,
                           19,
                           c);
         c.commit(); 
    } catch (Exception e) {
      rs.setName(e.toString());
      rs.setCode(-1);
      e.printStackTrace();
      ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
    } finally {
      ConnectionPool.close(c);
      
    }

    return rs;
  }
*/
public static Res Exchange1(Cr cr, CurrEx ce ) {
	long operationId = 0000;
	long account_no = 000;
	String card_acct = null;
	BigDecimal account_no2 =null;
	BigDecimal amount2=null;
	String operation=null;
	String client_code = null;
	String tieto_id = null;
	String paymentmode =null;
	
    Res rs = new Res();
        Connection c = null;
        rs.setName("Ok");
        Transaction_service transactionService = null;
        //CallableStatement cs_prep;
        Parameters ps = new Parameters();
    try {
    	String BranchDBLink=DepositService.getSchiema(cr.getBranch());
    	c = ConnectionPool.getConnection(cr.getLogin(),cr.getPassword(),BranchDBLink);
    	Res rr  = ExchangeService.getOperDay2(c);  // 1 идет процес закрытия  0 не идет
    	transactionService = new Transaction_service(c);
		long trId;
		
		
		if  (rr.getCode()==0) 
		{
	     // Dlya drugih bankov
		if (BANK_SCHEMA.equals("bank01066")||BANK_SCHEMA.equals("BANK394")||BANK_SCHEMA.equals("BANK980") ||BANK_SCHEMA.equals("BANK981"))
   		{
		paymentmode = "1";
		client_code = ce.getAcc_ct().substring(9,17);
		tieto_id = com.is.currexchange.ExchangeService.getCodeTietoVisa(client_code);  
		com.is.ISLogger.getLogger().warn("---1-- tieto_id="+tieto_id+ "  BANK_SCHEMA="+BANK_SCHEMA+ " client_code="+client_code+" state="+state);
				
   		} else if (BANK_SCHEMA.equals("IY00444"))
   		{
   		paymentmode = "2";
   	    tieto_id = com.is.currexchange.ExchangeService.getCodeTietoIpak(ce.getAcc_ct());
   		com.is.ISLogger.getLogger().warn("--2--- tieto_id="+tieto_id+ "  BANK_SCHEMA="+BANK_SCHEMA+ " client_code="+client_code+" state="+state);
   		
   		} else if  (BANK_SCHEMA.equals("bank974"))
   		{
   		paymentmode = "1";
		client_code = ce.getAcc_ct().substring(9,17);
		tieto_id = com.is.currexchange.ExchangeService.getCodeTietoVisaKapital(client_code , ce.getAcc_ct());  
		com.is.ISLogger.getLogger().warn("---4-- tieto_id="+tieto_id+ "  BANK_SCHEMA="+BANK_SCHEMA+ " client_code="+client_code+" state="+state);
					
   		} else		
   		{
		paymentmode = "1";
	    client_code = ce.getAcc_ct().substring(9,17);
	    tieto_id = com.is.currexchange.ExchangeService.getCodeTieto(client_code);
	   	com.is.ISLogger.getLogger().warn("--3--- tieto_id="+tieto_id+ "  BANK_SCHEMA="+BANK_SCHEMA+ " client_code="+client_code+" state="+state);
		
	       
   		}
	
	       
	   if (tieto_id!=null&&!tieto_id.equals("0"))
	   {
	    List<AccInfo> acc = com.is.tieto.TietoService.getAccInfo(tieto_id);
	   	if (acc.size() > 0)
	   	{
	   	for (int i = 0; i < acc.size(); i++)
	   	{
	   		if (BANK_SCHEMA.equals("bank974"))
	   		{	
	   		if (acc.get(i).getCard_acct().equals(ce.getAcc_ct()))
	   		{
	   			account_no = acc.get(i).getAccount_no();
	   			card_acct = acc.get(i).getCard_acct();
	   		}
	   		} else {
	   			if (acc.get(i).getTranz_acct().equals(ce.getAcc_ct()))
		   		{
		   			account_no = acc.get(i).getAccount_no();
		   			card_acct = acc.get(i).getCard_acct();
		   		}	
	   		}
	   	}
		com.is.ISLogger.getLogger().error("=====     account_no="+account_no+"  card_acct="+card_acct+" BANK_SCHEMA="+BANK_SCHEMA+"  tieto_id="+tieto_id);
			  
	   	 ObjectMapper objectMapper = new ObjectMapper();
		 String str1 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(acc);
		 com.is.ISLogger.getLogger().error("** Object  **************"+str1);
	    
	   	
	   	if (account_no !=000)
	   	{	
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		parameters.put("branch", cr.getBranch());
		parameters.put("operation_id", Long.valueOf( ce.getOperation()+""));
		parameters.put("parent_group_id", 1100l);
		parameters.put("parent_deal_id", 1);
		parameters.put("SUMMA", ce.getAmount());
		parameters.put("PARENT_ID", Long.parseLong("1"));
		//parameters.put("cs_prep", cs_prep);
		parameters.put("DT_CARD_ACC", ce.getAcc_dt());  
		parameters.put("CT_CARD_ACC", ce.getAcc_ct());
		//parameters.put("CARD_NUM_UZ", ce.getAcc_ct());
		parameters.put("AMNTCOUR",  ce.getAmount()*GetCource("840",4));
        
		operationId = Long.parseLong(ce.getOperation()+"");
    	com.is.ISLogger.getLogger().warn("+++Exchange servis info*********************** cr.getBranch="+cr.getBranch()+" BranchDBLink="+BranchDBLink+"  cr.getLogin()="+cr.getLogin()+"  cr.getPassword="+cr.getPassword()+" curse="+ce.getAmount()*GetCource("840",4)+" summa="+ce.getAmount()+" ; tieto_id="+tieto_id +" operationId="+operationId+"  account_no="+account_no);
    	
	
		trId = transactionService.create_pay(
				operationId, 
				cr.getBranch(), 
				ce.getAmount(), 
				1l, 
				1100l, 
				1, 
				parameters);

       transactionService.execute_transaction_action(cr.getBranch(),trId, 19);  // 19   23
		
	   	} else {
	   	   rs.setCode(-1);
	   	 ISLogger.getLogger().error("Ошибка: Нет конверсионный карты ВИЗА ! "+ce.getAcc_ct());
           rs.setName("Ошибка: Нет конверсионный карты ВИЗА !");
           return rs;
	   	}
		
		 
	  
    } else {
	   		   rs.setCode(-1);
	           rs.setName("Ошибка: Не удалось получить ответ от Виза Тието, повторите операцию позже");
	           ISLogger.getLogger().error("Ошибка: Не удалось получить ответ от Виза Тието, повторите операцию позже! "+ce.getAcc_ct());
	           return rs;
	   	}
	   } else {
		   rs.setCode(-1);
		   ISLogger.getLogger().error("Ошибка: Не удалось найти Тието Айди клиента, Обратитесь в банк! "+ce.getAcc_ct());
           rs.setName("Ошибка: Не удалось найти Тието Айди клиента, Обратитесь в банк!");
           return rs;
		   
	   }
  
   	ISLogger.getLogger().warn("********3*********:"+"account_no="+account_no+" card_acct="+card_acct+" CardNumberTo="+ ce.getAcc_ct());
   	
   	try
   	{
   	 account_no2 = new BigDecimal(account_no);
	 amount2 = new BigDecimal(ce.getAmount());
   		
   	ISLogger.getLogger().error("--PAY VISA -account_no2="+account_no2+" card_acct="+card_acct+" amount2="+amount2+" operation="+operation+" paymentmode="+paymentmode);
   rs = com.is.tieto.TietoService.sendPayment(new VisaPay(account_no2, card_acct, amount2, "USD", "110"), paymentmode);
   	} catch (Exception e) {
   		ISLogger.getLogger().error("--CAN NOT PAY VISA -account_no2="+account_no2+" card_acct="+card_acct+" amount2="+amount2+" operation="+operation+" : "+CheckNull.getPstr(e));
        
        rs.setCode(-1);
        rs.setName("Пополнение на ВИЗУ не получилось!");
        return rs;
    }
   	
      rs.setCode(0);
       rs.setName("Ok");
       c.commit(); 
		} else if (rr.getCode() == 1){
			
			ISLogger.getLogger().warn("Status zakritiya state="+rr.getCode());
			   rs.setCode(-1);
	           rs.setName("Ошибка: Идет процесс закрытия дня !");
	           return rs;
		}
   
    } catch (Exception e) {
      rs.setName(e.toString());
      rs.setCode(-1);
      e.printStackTrace();
      ISLogger.getLogger().error("**** Exchange1="+com.is.utils.CheckNull.getPstr(e));
    } finally {
      ConnectionPool.close(c);
      
    }

    return rs;
  }


public static Res Exchange2(Cr cr, CurrEx ce ) {
	long operationId = 0000;
	long account_no = 000;
	String card_acct = null;
	BigDecimal account_no2 =null;
	BigDecimal amount2=null;
	String operation=null;
	String client_code = null;
	String tieto_id = null;
	String paymentmode =null;
	
    Res rs = new Res();
        Connection c = null;
        rs.setName("Ok");
        Transaction_service transactionService = null;
        //CallableStatement cs_prep;
        Parameters ps = new Parameters();
    try {
    	String BranchDBLink=DepositService.getSchiema(cr.getBranch());
    	c = ConnectionPool.getConnection(cr.getLogin(),cr.getPassword(),BranchDBLink);
    	Res rr  = ExchangeService.getOperDay2(c);  // 1 идет процес закрытия  0 не идет
    	transactionService = new Transaction_service(c);
		long trId;
		
		
		if  (rr.getCode()==0) 
		{
	     // Dlya drugih bankov
		if (BANK_SCHEMA.equals("bank01066")||BANK_SCHEMA.equals("BANK394")||BANK_SCHEMA.equals("BANK980") ||BANK_SCHEMA.equals("BANK981"))
   		{
		paymentmode = "1";
		client_code = ce.getAcc_dt().substring(9,17);
		tieto_id = com.is.currexchange.ExchangeService.getCodeTietoVisa(client_code);  
		com.is.ISLogger.getLogger().warn("---1-- tieto_id="+tieto_id+ "  BANK_SCHEMA="+BANK_SCHEMA+ " client_code="+client_code+" state="+state);
				
   		} else if (BANK_SCHEMA.equals("IY00444"))
   		{
   		paymentmode = "2";
   	    tieto_id = com.is.currexchange.ExchangeService.getCodeTietoIpak(ce.getAcc_dt());
   		com.is.ISLogger.getLogger().warn("--2--- tieto_id="+tieto_id+ "  BANK_SCHEMA="+BANK_SCHEMA+ " client_code="+client_code+" state="+state);
   		
   		} else if  (BANK_SCHEMA.equals("bank974"))
   		{
   		paymentmode = "1";
		client_code = ce.getAcc_dt().substring(9,17);
		tieto_id = com.is.currexchange.ExchangeService.getCodeTietoVisaKapital(client_code , ce.getAcc_dt());  
		com.is.ISLogger.getLogger().warn("---4-- tieto_id="+tieto_id+ "  BANK_SCHEMA="+BANK_SCHEMA+ " client_code="+client_code+" state="+state);
					
   		}
		else		
   		{
		paymentmode = "1";
	    client_code = ce.getAcc_dt().substring(9,17);
	    tieto_id = com.is.currexchange.ExchangeService.getCodeTieto(client_code);
	   	com.is.ISLogger.getLogger().warn("--3--- tieto_id="+tieto_id+ "  BANK_SCHEMA="+BANK_SCHEMA+ " client_code="+client_code+" state="+state);
		
	       
   		}
	
	       
	   if (tieto_id!=null&&!tieto_id.equals("0"))
	   {
	    List<AccInfo> acc = com.is.tieto.TietoService.getAccInfo(tieto_id);
	   	if (acc.size() > 0)
	   	{
	   	for (int i = 0; i < acc.size(); i++)
	   	{
	   		if (BANK_SCHEMA.equals("bank974"))
	   		{	
	   		if (acc.get(i).getCard_acct().equals(ce.getAcc_dt()))
	   		{
	   			account_no = acc.get(i).getAccount_no();
	   			card_acct = acc.get(i).getCard_acct();
	   		}
	   		} else {
	   			if (acc.get(i).getTranz_acct().equals(ce.getAcc_dt()))
		   		{
		   			account_no = acc.get(i).getAccount_no();
		   			card_acct = acc.get(i).getCard_acct();
		   		}	
	   		}
	   	}
	   	
	   
		 com.is.ISLogger.getLogger().error("=====     account_no="+account_no+"  card_acct="+card_acct+" BANK_SCHEMA="+BANK_SCHEMA+"  tieto_id="+tieto_id);
			  
	   	 ObjectMapper objectMapper = new ObjectMapper();
		 String str1 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(acc);
		 com.is.ISLogger.getLogger().error(" * * * Visa object  * * * "+str1);
	    
	   	
	   	if (account_no !=000)
	   	{
	   		
	   		try
		   	{
		   		 account_no2 = new BigDecimal(account_no);
				 amount2 = new BigDecimal(ce.getAmount());
		   		ISLogger.getLogger().error("--PAY VISA -account_no2="+account_no2+" card_acct="+card_acct+" amount2="+amount2+" operation="+operation);
		   rs = com.is.tieto.TietoService.sendPayment(new VisaPay(account_no2, card_acct, amount2, "USD", "129"), paymentmode);
		   	} catch (Exception e) {
		   		ISLogger.getLogger().error("--CAN NOT PAY VISA 0-account_no2="+account_no2+" card_acct="+card_acct+" amount2="+amount2+" operation="+operation+" : "+CheckNull.getPstr(e));
		        rs.setCode(-1);
		        rs.setName("Пополнение на ВИЗУ не получилось!");
		        return rs;
		    }
		   	
	   	try
	   	{
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		parameters.put("branch", cr.getBranch());
		parameters.put("operation_id", Long.valueOf( ce.getOperation()+""));
		parameters.put("parent_group_id", 1100l);
		parameters.put("parent_deal_id", 1);
		parameters.put("SUMMA", ce.getAmount());
		parameters.put("PARENT_ID", Long.parseLong("1"));
		//parameters.put("cs_prep", cs_prep);
		parameters.put("DT_CARD_ACC", ce.getAcc_dt());  
		parameters.put("CT_CARD_ACC", ce.getAcc_ct());
		//parameters.put("CARD_NUM_UZ", ce.getAcc_ct());
		parameters.put("AMNTCOUR",  ce.getAmount()*GetCource("840",5));
        
		operationId = Long.parseLong(ce.getOperation()+"");
    	com.is.ISLogger.getLogger().warn("+++Exchange servis info*********************** cr.getBranch="+cr.getBranch()+" BranchDBLink="+BranchDBLink+"  cr.getLogin()="+cr.getLogin()+"  cr.getPassword="+cr.getPassword()+" curse="+ce.getAmount()*GetCource("840",4)+" summa="+ce.getAmount()+" ; tieto_id="+tieto_id +" operationId="+operationId+"  account_no="+account_no);
    	
	
		trId = transactionService.create_pay(
				operationId, 
				cr.getBranch(), 
				ce.getAmount(), 
				1l, 
				1100l, 
				1, 
				parameters);

       transactionService.execute_transaction_action(cr.getBranch(),trId, 19);  // 19   23
	   	} catch (Exception e) {
	   	ISLogger.getLogger().error("Проводка конверсии не удалась -account_no2="+account_no2+" card_acct="+card_acct+" amount2="+amount2+" operation="+operation+" : "+CheckNull.getPstr(e));
	   	 account_no2 = new BigDecimal(account_no);
		 amount2 = new BigDecimal(ce.getAmount());
         rs = com.is.tieto.TietoService.sendPayment(new VisaPay(account_no2, card_acct, amount2, "USD", "110"), paymentmode);
	     rs.setCode(-1);
	     rs.setName("Проводка конверсии не удалась!");
	     return rs;
	    }
	   	} else {
	   	   rs.setCode(-1);
	   	 ISLogger.getLogger().error("Ошибка: Нет конверсионный карты ВИЗА ! "+ce.getAcc_ct());
           rs.setName("Ошибка: Нет конверсионный карты ВИЗА !");
           return rs;
	   	}
		 
		 
	  
    } else {
	   		   rs.setCode(-1);
	           rs.setName("Ошибка: Не удалось получить ответ от Виза Тието, повторите операцию позже");
	           ISLogger.getLogger().error("Ошибка: Не удалось получить ответ от Виза Тието, повторите операцию позже! "+ce.getAcc_ct());
	           return rs;
	   	}
	   } else {
		   rs.setCode(-1);
		   ISLogger.getLogger().error("Ошибка: Не удалось найти Тието Айди клиента, Обратитесь в банк! "+ce.getAcc_ct());
           rs.setName("Ошибка: Не удалось найти Тието Айди клиента, Обратитесь в банк!");
           return rs;
		   
	   }
  
   	ISLogger.getLogger().warn("********3*********:"+"account_no="+account_no+" card_acct="+card_acct+" CardNumberTo="+ ce.getAcc_ct());
   	
   	
   	
      rs.setCode(0);
       rs.setName("Ok");
       c.commit(); 
		} else if (rr.getCode() == 1){
			
			ISLogger.getLogger().warn("Status zakritiya state="+rr.getCode());
			   rs.setCode(-1);
	           rs.setName("Ошибка: Идет процесс закрытия дня !");
	           return rs;
		}
   
    } catch (Exception e) {
      rs.setName(e.toString());
      rs.setCode(-1);
      e.printStackTrace();
      ISLogger.getLogger().error("**** Exchange1="+com.is.utils.CheckNull.getPstr(e));
    } finally {
      ConnectionPool.close(c);
      
    }

    return rs;
  }


public static int GetCource(String currency, int courceType){
	Connection c = null;
	int result = 1;
	try{
		
		c = ConnectionPool.getConnection();
		PreparedStatement ps = c.prepareStatement("{call info.init()}");
		ps.executeUpdate();
		ps = c.prepareStatement("select Info.GetEqual(1,?,'000',?) state from dual");
		ps.setString(1, currency);
		ps.setInt(2, courceType);
		ResultSet rs = ps.executeQuery();
		rs.next();
		result = rs.getInt("state");
		ps.close();
		rs.close();
		
	}
	catch(SQLException e){
		ISLogger.getLogger().error(CheckNull.getPstr(e));
	}
	finally{
		
		ConnectionPool.close(c);
	}
	return result;
}

public static int GetCourceType( int dealId){
	Connection c = null;
	PreparedStatement ps = null;
	int result = 1;
	try{
		c = ConnectionPool.getConnection(); 
		ps = c.prepareStatement("select d.course_type from deal_fxdoc d where  d.deal_id=?");
		ps.setInt(1, dealId);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){ 
		   result = rs.getInt("course_type");
		}
		ps.close();
		rs.close();
	}
	catch(SQLException e){
		ISLogger.getLogger().error(CheckNull.getPstr(e));
	}
	finally{
		ConnectionPool.close(c);
	}
	return result;
}


public static String getCodeTieto(String id)  {

		StringBuffer res = new  StringBuffer();
		Connection c = null;
	    String sm = "0";
		try {
		        c = ConnectionPool.getConnection(BANK_SCHEMA);
		        //PreparedStatement ps = c.prepareStatement("select sum(a.amount)amnt,nvl(d.name,'Всего') name from accagr a, s_id_department d where a.departament_id=d.id and a.medicalreportid=? group by rollup(d.name) ");
		        PreparedStatement ps = c.prepareStatement("select e.tieto_customer_id  from bf_tieto_customers e where e.bank_customer_id=?");
		        ps.setString(1, id);
		        ResultSet rs = ps.executeQuery();
		        while (rs.next()) {
		                sm =  rs.getString("tieto_customer_id");
		        }
		        
		        res.append(sm);
		        ps.close();    
		        
		} catch (SQLException e) {
			com.is.ISLogger.getLogger().error("********getCodeTieto******** "+e.getErrorCode()+": "+e.getMessage()+com.is.utils.CheckNull.getPstr(e));
		} finally {
			 
		        ConnectionPool.close(c);
		        
		}
		return res.toString();

		}


public static String getCodeTietoVisa(String id)  {

		StringBuffer res = new  StringBuffer();
		Connection c = null;
	    String sm = "0";
		try {
		        c = ConnectionPool.getConnection(BANK_SCHEMA);
		        //PreparedStatement ps = c.prepareStatement("select sum(a.amount)amnt,nvl(d.name,'Всего') name from accagr a, s_id_department d where a.departament_id=d.id and a.medicalreportid=? group by rollup(d.name) ");
		        PreparedStatement ps = c.prepareStatement("select e.tieto_customer_id  from bf_visa_customers e where e.bank_customer_id=?");
		        ps.setString(1, id);
		        ResultSet rs = ps.executeQuery();
		        while (rs.next()) {
		                sm =  rs.getString("tieto_customer_id");
		        }
		        
		        res.append(sm);
		        ps.close();    
		        
		} catch (SQLException e) {
			com.is.ISLogger.getLogger().error("********getCodeTietoVisa******** "+e.getErrorCode()+": "+e.getMessage()+com.is.utils.CheckNull.getPstr(e));
		} finally {
			 
		        ConnectionPool.close(c);
		        
		}
		return res.toString();

		}


public static String getCodeTietoVisaKapital(String id, String VisaAcc)  {

	StringBuffer res = new  StringBuffer();
	Connection c = null;
    String sm = "0";
	try {
	        c = ConnectionPool.getConnection(BANK_SCHEMA);
	        //PreparedStatement ps = c.prepareStatement("select sum(a.amount)amnt,nvl(d.name,'Всего') name from accagr a, s_id_department d where a.departament_id=d.id and a.medicalreportid=? group by rollup(d.name) ");
	        PreparedStatement ps = c.prepareStatement("select e.tieto_customer_id  from bf_visa_customers e where e.bank_customer_id=? and  e.cur_acc=? ");
	        ps.setString(1, id);
	        ps.setString(2, VisaAcc);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	                sm =  rs.getString("tieto_customer_id");
	        }
	        
	        res.append(sm);
	        ps.close();    
	        
	} catch (SQLException e) {
		com.is.ISLogger.getLogger().error("********getCodeTietoVisa******** "+e.getErrorCode()+": "+e.getMessage()+com.is.utils.CheckNull.getPstr(e));
	} finally {
		 
	        ConnectionPool.close(c);
	        
	}
	return res.toString();

	}

public static Res getOperDay2(Connection c){
	//Connection c = null;
	Res res = new Res();
	PreparedStatement ps = null;
	int result = 0;
	try{
		//c = ConnectionPool.getConnection(); 
		ps = c.prepareStatement("select status from statusoperday where id = 10");
		//ps.setInt(1, dealId);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){ 
		   result = rs.getInt("status");
		}
		res.setCode(result);
		rs.close();
		ps.close();
		
	}
	catch(SQLException e){
		res.setCode(-1);
		ISLogger.getLogger().error(CheckNull.getPstr(e));
	}
	finally{
		//ConnectionPool.close(c);
	}
	return res;
}
public static String getOperDay(Connection c)  {

	StringBuffer res = new  StringBuffer();
	//Connection c = null;
    String sm = "0";
	try {
	      //  c = ConnectionPool.getConnection(BANK_SCHEMA);
	        //PreparedStatement ps = c.prepareStatement("select sum(a.amount)amnt,nvl(d.name,'Всего') name from accagr a, s_id_department d where a.departament_id=d.id and a.medicalreportid=? group by rollup(d.name) ");
	        PreparedStatement ps = c.prepareStatement("select status from statusoperday where id = 10");
	       
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	                sm =  rs.getString("status");
	        }
	        
	        res.append(sm);
	        ps.close();    
	        
	} catch (SQLException e) {
		com.is.ISLogger.getLogger().error("********getOperDay******** "+e.getErrorCode()+": "+e.getMessage()+com.is.utils.CheckNull.getPstr(e));
	} finally {
		 
	      //  ConnectionPool.close(c);
	        
	}
	return res.toString();

	}


public static String getCodeTietoIpak(String id)  {

		StringBuffer res = new  StringBuffer();
		Connection c = null;
	    String sm = "0";
		try {
		        c = ConnectionPool.getConnection("iy00444");
		      PreparedStatement ps = c.prepareStatement("select e.tieto_id  from v_visa_mobile e where e.tranz_acct=?");
//		        PreparedStatement ps = c.prepareStatement("select e.tieto_customer_id  from bf_tieto_customers e where e.bank_customer_id=?");
		        ps.setString(1, id);
		        ResultSet rs = ps.executeQuery();
		        while (rs.next()) {
		                sm =  rs.getString("tieto_id");
		        }
		        res.append(sm);
		        ps.close();    
		        
		} catch (SQLException e) {
			com.is.ISLogger.getLogger().error("********getCodeTieto******** "+e.getErrorCode()+": "+e.getMessage()+com.is.utils.CheckNull.getPstr(e));
		} finally {
			 
		        ConnectionPool.close(c);
		        
		}
		return res.toString();

		}

public static CurrExch2 fillCurrEx( CurrExch2 ce){
	Connection c = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	try{
		c = ConnectionPool.getConnection(); 
		
		if (CheckNull.isEmpty(ce.getAcc_dt())){
		ps = c.prepareStatement("select c.branch,c.def_atm_account from card c where c.card_number=?");
		ps.setString(1, ce.getCard_dt());
		rs = ps.executeQuery();
		  if (rs.next()){ 
		     ce.setBank_dt(rs.getString("branch"));
		     ce.setAcc_dt(rs.getString("def_atm_account").substring(5));
		  }
		}
		
		if (CheckNull.isEmpty(ce.getAcc_ct())){
			ps = c.prepareStatement("select branch, account from ui_02_015_pre p where p.card_number=?");
			ps.setString(1, ce.getCard_ct());
			rs = ps.executeQuery();
			  if (rs.next()){ 
			     ce.setBank_ct(rs.getString("branch"));
			     ce.setAcc_ct(rs.getString("account").substring(5));
			  }
			}
		
		if (CheckNull.isEmpty(ce.getCard_dt())){
			ps = c.prepareStatement("select c.card_number  from card c where c.def_atm_account=?");
			ps.setString(1, ce.getBank_dt()+ce.getAcc_dt());
			rs = ps.executeQuery();
			  if (rs.next()){ 
			     ce.setCard_dt(rs.getString("card_number"));
			  }
			}
		
		if (CheckNull.isEmpty(ce.getCard_ct())){
			ps = c.prepareStatement("select p.card_number from ui_02_015_pre p where p.branch=? and p.account=?");
			ps.setString(1, ce.getBank_ct());
			ps.setString(2, ce.getAcc_ct());
			rs = ps.executeQuery();
			  if (rs.next()){ 
			     ce.setCard_ct(rs.getString("card_number"));
			  }
			}
		
		
		if (ps!=null)
		{
		ps.close();
		}
		if (rs!=null)
		{
		rs.close();
		}
	}
	catch(SQLException e){
		ISLogger.getLogger().error("---- fillCurrEx----- "+CheckNull.getPstr(e));
	}
	finally{
		ConnectionPool.close(c);
	}
	return ce;
}


}
