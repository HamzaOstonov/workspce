package com.is.client_mass_openingCheckResident;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.Res;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.apache.commons.codec.binary.Base64;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;





public class Client_mass_opening_residentService {

    private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM Client_mass_opening_resident ";

   


    public List<Client_mass_opening_resident> getClient_mass_opening_resident()  {

            List<Client_mass_opening_resident> list = new ArrayList<Client_mass_opening_resident>();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM Client_mass_opening_resident");
                    while (rs.next()) {
                            list.add(new Client_mass_opening_resident(
                                            rs.getString("id"),
                                            rs.getString("file_id"),
                                            rs.getString("lastname"),
                                            rs.getString("date_birth"),
                                            rs.getString("code_organization"),
                                            rs.getString("card_type"),
                                            rs.getString("phone"),
                                            rs.getString("pinfl"),
                                            rs.getString("status"),
                                            rs.getString("responce"),
                                            rs.getString("acc_group")));
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

    private static List<FilterField> getFilterFields(Client_mass_opening_residentFilter filter){
            List<FilterField> flfields = new ArrayList<FilterField>();


          if(!CheckNull.isEmpty(filter.getId())){
                  flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
          }
          if(!CheckNull.isEmpty(filter.getFile_id())){
                  flfields.add(new FilterField(getCond(flfields)+ "file_id=?",filter.getFile_id()));
          }
          if(!CheckNull.isEmpty(filter.getLastname())){
                  flfields.add(new FilterField(getCond(flfields)+ "lastname=?",filter.getLastname()));
          }
          if(!CheckNull.isEmpty(filter.getDate_birth())){
                  flfields.add(new FilterField(getCond(flfields)+ "date_birth=?",filter.getDate_birth()));
          }
          if(!CheckNull.isEmpty(filter.getCode_organization())){
                  flfields.add(new FilterField(getCond(flfields)+ "code_organization=?",filter.getCode_organization()));
          }
          if(!CheckNull.isEmpty(filter.getCard_type())){
                  flfields.add(new FilterField(getCond(flfields)+ "card_type=?",filter.getCard_type()));
          }
          if(!CheckNull.isEmpty(filter.getPhone())){
                  flfields.add(new FilterField(getCond(flfields)+ "phone=?",filter.getPhone()));
          }
          if(!CheckNull.isEmpty(filter.getPinfl())){
                  flfields.add(new FilterField(getCond(flfields)+ "pinfl=?",filter.getPinfl()));
          }
          if(!CheckNull.isEmpty(filter.getStatus())){
                  flfields.add(new FilterField(getCond(flfields)+ "status=?",filter.getStatus()));
          }
          if(!CheckNull.isEmpty(filter.getResponce())){
                  flfields.add(new FilterField(getCond(flfields)+ "responce=?",filter.getResponce()));
          }
          if(!CheckNull.isEmpty(filter.getAcc_group())){
                  flfields.add(new FilterField(getCond(flfields)+ "acc_group=?",filter.getAcc_group()));
          }

          flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

            return flfields;
    }


    public static int getCount(Client_mass_opening_residentFilter filter)  {

        Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM Client_mass_opening_resident ");
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



    public static List<Client_mass_opening_resident> getClient_mass_opening_residentsFl(int pageIndex, int pageSize, Client_mass_opening_residentFilter filter)  {

            List<Client_mass_opening_resident> list = new ArrayList<Client_mass_opening_resident>();
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
                            list.add(new Client_mass_opening_resident(
                                            rs.getString("id"),
                                            rs.getString("file_id"),
                                            rs.getString("lastname"),
                                            rs.getString("date_birth"),
                                            rs.getString("code_organization"),
                                            rs.getString("card_type"),
                                            rs.getString("phone"),
                                            rs.getString("pinfl"),
                                            rs.getString("status"),
                                            rs.getString("responce"),
                                            rs.getString("acc_group"),
                                            rs.getString("passport_series"),
                                            rs.getString("passport_number")));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


    public static Client_mass_opening_resident getClient_mass_opening_resident(int client_mass_opening_residentId) {

            Client_mass_opening_resident client_mass_opening_resident = new Client_mass_opening_resident();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("SELECT * FROM client_mass_opening_resident WHERE id=?");
                    ps.setInt(1, client_mass_opening_residentId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            client_mass_opening_resident = new Client_mass_opening_resident();
                            
                            client_mass_opening_resident.setId(rs.getString("id"));
                            client_mass_opening_resident.setFile_id(rs.getString("file_id"));
                            client_mass_opening_resident.setLastname(rs.getString("lastname"));
                            client_mass_opening_resident.setDate_birth(rs.getString("date_birth"));
                            client_mass_opening_resident.setCode_organization(rs.getString("code_organization"));
                            client_mass_opening_resident.setCard_type(rs.getString("card_type"));
                            client_mass_opening_resident.setPhone(rs.getString("phone"));
                            client_mass_opening_resident.setPinfl(rs.getString("pinfl"));
                            client_mass_opening_resident.setStatus(rs.getString("status"));
                            client_mass_opening_resident.setResponce(rs.getString("responce"));
                            client_mass_opening_resident.setAcc_group(rs.getString("acc_group"));
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return client_mass_opening_resident;
    }

    public static Client_mass_opening_resident create(Client_mass_opening_resident client_mass_opening_resident)  {

            Connection c = null;
            PreparedStatement ps = null;
            try {
                    c = ConnectionPool.getConnection();
                    ps = c.prepareStatement("SELECT SQ_client_mass_opening_resident.NEXTVAL id FROM DUAL");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            client_mass_opening_resident.setId(rs.getString("id"));
                    }
                    ps = c.prepareStatement("INSERT INTO client_mass_opening_resident (id, file_id, lastname, date_birth, code_organization, card_type, phone, pinfl, status, responce, acc_group, ) VALUES (?,?,?,?,?,?,?,?,?,?,?,)");
                    
                    ps.setString(1,client_mass_opening_resident.getId());
                    ps.setString(2,client_mass_opening_resident.getFile_id());
                    ps.setString(3,client_mass_opening_resident.getLastname());
                    ps.setString(4,client_mass_opening_resident.getDate_birth());
                    ps.setString(5,client_mass_opening_resident.getCode_organization());
                    ps.setString(6,client_mass_opening_resident.getCard_type());
                    ps.setString(7,client_mass_opening_resident.getPhone());
                    ps.setString(8,client_mass_opening_resident.getPinfl());
                    ps.setString(9,client_mass_opening_resident.getStatus());
                    ps.setString(10,client_mass_opening_resident.getResponce());
                    ps.setString(11,client_mass_opening_resident.getAcc_group());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return client_mass_opening_resident;
    }

    public static void update1(Client_mass_opening_resident client_mass_opening_resident)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("UPDATE client_mass_opening_resident SET id=?, file_id=?, lastname=?, date_birth=?, code_organization=?, card_type=?, phone=?, pinfl=?, status=?, responce=?, acc_group=?,  WHERE id=?");
                    
                    ps.setString(1,client_mass_opening_resident.getId());
                    ps.setString(2,client_mass_opening_resident.getFile_id());
                    ps.setString(3,client_mass_opening_resident.getLastname());
                    ps.setString(4,client_mass_opening_resident.getDate_birth());
                    ps.setString(5,client_mass_opening_resident.getCode_organization());
                    ps.setString(6,client_mass_opening_resident.getCard_type());
                    ps.setString(7,client_mass_opening_resident.getPhone());
                    ps.setString(8,client_mass_opening_resident.getPinfl());
                    ps.setString(9,client_mass_opening_resident.getStatus());
                    ps.setString(10,client_mass_opening_resident.getResponce());
                    ps.setString(11,client_mass_opening_resident.getAcc_group());
                    ps.executeUpdate();
                    c.commit();
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }

    }
    
    
    
//---------------------------------------------
 // процедура отрытия клиентa в банке

 	Res res = null;
	public  Res doActionResident(String un,String pw,String alias,String code_bank,GetFromNibbd getFromNibbds) {
		DateFormat  sdf = new SimpleDateFormat("dd.MM.yyyy");
		DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Connection c = null;
		CallableStatement cs = null;
		CallableStatement acs = null;
		CallableStatement ccs = null;
		try {
			c = ConnectionPool.getConnection(un,pw,alias);
			cs = c.prepareCall("{ call Param.SetParam(?,?) }");
			acs = c.prepareCall("{ call kernel.doaction(1,2,1) }");
			ccs = c.prepareCall("{ call Param.clearparam() }");
			ccs.execute();
			ccs = c.prepareCall("{? = call Param.getparam('ID') }");
			ccs.registerOutParameter(1, java.sql.Types.NUMERIC);
			cs.setString(1, "P_BIRTHDAY");
			Date date = inputFormat.parse(getFromNibbds.getBirth_date());
			cs.setString(2,sdf.format(date));
			ISLogger.getLogger().error("P_BIRTHDAY: "+sdf.format(date));
			cs.execute();
			cs.setString(1, "P_POST_ADDRESS"); 
			cs.setString(2, getFromNibbds.getDomicile_address());
			ISLogger.getLogger().error("P_POST_ADDRESS: "+getFromNibbds.getDomicile_address());
			cs.execute();
			cs.setString(1, "P_PASSPORT_TYPE");
			ISLogger.getLogger().error("P_PASSPORT_TYPE N");
			cs.setString(2, "N");
			cs.execute();
			cs.setString(1, "P_PASSPORT_SERIAL");
			cs.setString(2, getFromNibbds.getPassport_seria());
			ISLogger.getLogger().error("P_PASSPORT_SERIAL: "+getFromNibbds.getPassport_seria());
			cs.execute();
			cs.setString(1,"P_PASSPORT_NUMBER");
			ISLogger.getLogger().error("P_PASSPORT_NUMBER: "+getFromNibbds.getPassport_number());
			cs.setString(2,getFromNibbds.getPassport_number());
			cs.execute();
			cs.setString(1,"P_PASSPORT_PLACE_REGISTRATION");///НЕТУ ДАННЫХ НУЖНО ВЗЯТЬ С ПРОЦЕДУРЫ 
			ISLogger.getLogger().error("P_PASSPORT_PLACE_REGISTRATION: "+getFromNibbds.getGive_place_name()+" "+getFromNibbds.getGive_place());
			if(getFromNibbds.getGive_place().isEmpty()||getFromNibbds.getGive_place()==null) {
		        cs.setString(2, getFromNibbds.getGive_place_name() );
		      }else {
		      cs.setString(2, getFromNibbds.getGive_place() );}
			cs.execute();
			cs.setString(1,"P_PASSPORT_DATE_REGISTRATION");
			Date date_issue = inputFormat.parse(getFromNibbds.getDate_issue());
			cs.setString(2, sdf.format(date_issue));
			ISLogger.getLogger().error("P_PASSPORT_DATE_REGISTRATION: "+sdf.format(date_issue));
			cs.execute();
			cs.setString(1,"P_CODE_BANK");
			cs.setString(2, code_bank);
			ISLogger.getLogger().error("P_CODE_BANK: "+code_bank);
			cs.execute();
			cs.setString(1,"P_CODE_CITIZENSHIP");
			cs.setString(2,getFromNibbds.getCitizenship());// 
			ISLogger.getLogger().error("P_CODE_CITIZENSHIP: "+getFromNibbds.getCitizenship());
			cs.execute();
			cs.setString(1,"P_BIRTH_PLACE");
			cs.setString(2, getFromNibbds.getBirth_place());
			ISLogger.getLogger().error("P_BIRTH_PLACE: "+getFromNibbds.getBirth_place());
			cs.execute();
			cs.setString(1,"P_CODE_CAPACITY");
			cs.setString(2, "0801");//"inn_registrated_gni":"0313"
			ISLogger.getLogger().error("P_CODE_CAPACITY: 0801 ");
			cs.execute();
			cs.setString(1,"P_CODE_GENDER");
			cs.setString(2, getFromNibbds.getSex());
			ISLogger.getLogger().error("P_CODE_GENDER:  "+  getFromNibbds.getSex());
			cs.execute();
			cs.setString(1,"P_CODE_NATION");
			String nation=getNationality(getFromNibbds.getNationality());
			cs.setString(2,nation);
			ISLogger.getLogger().error("P_CODE_NATION: "+nation);
			cs.execute();
			cs.setString(1,"P_TYPE_DOCUMENT");
			cs.setString(2, "6");
			ISLogger.getLogger().error("P_TYPE_DOCUMENT: 6 ");
			cs.execute();
			cs.setString(1,"P_PASSPORT_DATE_EXPIRATION");
			Date date_expiry = inputFormat.parse(getFromNibbds.getDate_expiry());
			cs.setString(2, sdf.format(date_expiry));
			ISLogger.getLogger().error("P_PASSPORT_DATE_EXPIRATION: "+sdf.format(date_expiry));
			cs.execute();
			cs.setString(1,"P_CODE_ADR_REGION");
			cs.setString(2, getFromNibbds.getDomicile_region());
			ISLogger.getLogger().error("P_CODE_ADR_REGION: "+getFromNibbds.getDomicile_region());
			cs.execute();
			cs.setString(1,"P_CODE_ADR_DISTR");
			cs.setString(2, getFromNibbds.getDomicile_district());
			ISLogger.getLogger().error("P_CODE_ADR_DISTR: "+getFromNibbds.getDomicile_district());
			cs.execute();
			cs.setString(1,"P_FAMILY");
			cs.setString(2, getFromNibbds.getLast_name());
			ISLogger.getLogger().error("P_FAMILY: "+getFromNibbds.getLast_name());
			cs.execute();
			cs.setString(1,"P_FIRST_NAME");
			cs.setString(2,getFromNibbds.getFirst_name() );
			ISLogger.getLogger().error("P_FIRST_NAME: "+getFromNibbds.getFirst_name());
			cs.execute();
			cs.setString(1,"P_PATRONYMIC");
			cs.setString(2, getFromNibbds.getPatronym());
			ISLogger.getLogger().error("P_PATRONYMIC: "+getFromNibbds.getPatronym());
			ISLogger.getLogger().error("---------------------------------------------");
			cs.execute();
			cs.setString(1,"NAME");
			cs.setString(2, getFromNibbds.getSurname() + " " + getFromNibbds.getFirst_name() + " " + getFromNibbds.getPatronym() );
			ISLogger.getLogger().error("P_PATRONYMIC: "+getFromNibbds.getSurname() + " " + getFromNibbds.getFirst_name() + " " + getFromNibbds.getPatronym());
			ISLogger.getLogger().error("---------------------------------------------");
			cs.execute();
			cs.setString(1,"CODE_COUNTRY");
			cs.setString(2, getFromNibbds.getDomicile_country() );
			ISLogger.getLogger().error("CODE_COUNTRY: " + getFromNibbds.getDomicile_country());
			ISLogger.getLogger().error("---------------------------------------------");
			System.out.println("CODE_COUNTRY: " + getFromNibbds.getDomicile_country());
			cs.execute();
			cs.setString(1,"CODE_TYPE");
			cs.setString(2, "08" );
			ISLogger.getLogger().error("CODE_TYPE: " + "08");
			ISLogger.getLogger().error("---------------------------------------------");
			System.out.println("CODE_TYPE: " + "08");
			cs.execute();
			cs.setString(1,"CODE_RESIDENT");
			cs.setString(2, "1" );
			ISLogger.getLogger().error("CODE_RESIDENT: " + "1");
			ISLogger.getLogger().error("---------------------------------------------");
			System.out.println("CODE_RESIDENT: " + "1");
			cs.execute();
			cs.setString(1,"P_CODE_CITIZENSHIP");
			cs.setString(2, "860" );
			ISLogger.getLogger().error("P_CODE_CITIZENSHIP: " + "860");
			ISLogger.getLogger().error("---------------------------------------------");
			System.out.println("P_CODE_CITIZENSHIP: " + "860");
			cs.execute();
			cs.setString(1,"P_PINFL");
			cs.setString(2, getFromNibbds.getPin() );
			ISLogger.getLogger().error("P_PINFL: " + getFromNibbds.getPin());
			ISLogger.getLogger().error("---------------------------------------------");
			System.out.println("P_PINFL: " + getFromNibbds.getPin());
			cs.execute();
			cs.setString(1,"CODE_SUBJECT");
			cs.setString(2, getFromNibbds.getSubject_state() );
			ISLogger.getLogger().error("CODE_SUBJECT: " + getFromNibbds.getSubject_state());
			ISLogger.getLogger().error("---------------------------------------------");
			System.out.println("CODE_SUBJECT: " + getFromNibbds.getSubject_state());
			cs.execute();
			acs.execute();
			ccs.execute();
			res = new Res(0, ccs.getString(1));
			c.commit();
		} catch (Exception e) {
			
			System.out.println("P_CODE_CITIZENSHIP(1): " + getFromNibbds.getCitizenship());
			System.out.println("P_CODE_CITIZENSHIP(2): " + getFromNibbds.getCitizenship_name());
			System.out.println("UZUNLIK: "+(getFromNibbds.getFirst_name()+" "+getFromNibbds.getLast_name()).length());
			System.out.println("post address: " + getFromNibbds.getDomicile_address());
			
			e.printStackTrace();
			ISLogger.getLogger().error(e);
			try {
				c.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				ISLogger.getLogger().error(CheckNull.getPstr(ex));
			}
			res = new Res(-1, e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}
		return res;
	}
	
	
	//процедура отрытия счета в банке
	public  Res doActions(String un,String pw,String alias, String branch,Client_mass_opening_resident clientResident, GetFromNibbd getFromNibbds) {
		Res res = null;
		Res action= new Res();
		DateFormat  sdf = new SimpleDateFormat("dd.MM.yyyy");
		DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Connection c = null;
		CallableStatement cs = null;
		CallableStatement acs = null;
		CallableStatement ccs = null;
		try {
			c = ConnectionPool.getConnection(un,pw,alias);
			cs = c.prepareCall("{ call Param.SetParam(?,?) }");
			acs = c.prepareCall("{ call  kernel.doaction(2,2,1) }");
			ccs = c.prepareCall("{ call Param.clearparam() }");
			ccs.execute();
			ccs = c.prepareCall("{? = call Param.getparam('ID') }");
			ccs.registerOutParameter(1, java.sql.Types.NUMERIC);
			ISLogger.getLogger().error("----------doActions---------------");
			cs.setString(1,"NAME"); //наименование счета
			cs.setString(2, getFromNibbds.getFirst_name()+" "+getFromNibbds.getLast_name());
			ISLogger.getLogger().error("NAME: "+ getFromNibbds.getFirst_name()+" "+getFromNibbds.getLast_name());
			
			cs.setString(1,"SGN");
			cs.setString(2, "22618");
			ISLogger.getLogger().error("SGN: 22618");
			cs.execute();
			
			cs.setString(1,"BAL");
			cs.setString(2, "22618");
			ISLogger.getLogger().error("BAL: 22618");
			cs.execute();
			
			cs.setString(1,"SIGN_REGISTR");
			action=	getPiznak(un,pw,alias,branch);
			ISLogger.getLogger().error("SIGN_REGISTR: "+action.getName());
			cs.setString(2, action.getName());
			ISLogger.getLogger().error("SIGN_REGISTR: ");//узнать признак ниббд
			cs.execute();
			
			cs.setString(1,"STATE");
			cs.setString(2, "0");
			ISLogger.getLogger().error("STATE:0 ");
			cs.execute();
			
			cs.setString(1,"ACC_GROUP_ID");
			cs.setString(2, clientResident.getAcc_group());
			ISLogger.getLogger().error("ACC_GROUP_ID: "+clientResident.getAcc_group());
			cs.execute();
			
			cs.setString(1,"CLIENT");
			cs.setString(2, "");
			ISLogger.getLogger().error("CLIENT: ");//подставлять из открытия клиента
			cs.execute();
			
			cs.setString(1,"ACC_BAL");
			cs.setString(2, "22618");
			ISLogger.getLogger().error("ACC_BAL: 22618 ");
			cs.execute();
			
			cs.setString(1,"CURRENCY");
			cs.setString(2, "000");
			ISLogger.getLogger().error("CURRENCY: 000 ");
			cs.execute();
			cs.setString(1,"ID_ORDER");
			if(clientResident.getCard_type().equals("1")) {//1 – HUMO, 2 - kobeyj,  3 - оба	
				cs.setString(2, "001");
				ISLogger.getLogger().error("ID_ORDER: 001");
			}
			if(clientResident.getCard_type().equals("2")) {
				cs.setString(2, "000");
				ISLogger.getLogger().error("ID_ORDER: 000");
				
			}
			ISLogger.getLogger().error("----------doActionsNE---------------");
			cs.execute();
			acs.execute();
			ccs.execute();
			res = new Res(0, ccs.getString(1));
			c.commit();
		} catch (Exception e) {

			e.printStackTrace();
			ISLogger.getLogger().error(e);
			try {
				c.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				ISLogger.getLogger().error(CheckNull.getPstr(ex));
			}
			res = new Res(-1, e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}
		return res;
	}
	
   
	 
		public   Res getKobeydjCard (String un,String pw,String alias, String branch,ClientResident  clientResident, GetFromNibbd fromNibbd) {
			System.out.println(clientResident.getLastname());
			
            
			Res res = null;
			DateFormat  sdf = new SimpleDateFormat("dd.MM.yyyy");
			DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Connection c = null;
			CallableStatement cs = null;
			CallableStatement acs = null;
			CallableStatement ccs = null;
			try {
				                                                                                                                                           
				c = ConnectionPool.getConnection(un,pw,alias);
				cs = c.prepareCall("{ call Param.SetParam(?,?) }");
				acs = c.prepareCall("{ call  kernel.doaction(121,32,1) }");       //	  SQLPar.ParamByName('GROUP_ID').Value:='121';
				ccs = c.prepareCall("{ call Param.clearparam() }");                //     SQLPar.ParamByName('DEAL_ID').Value:='32';
				ccs.execute();                                                                           //	  SQLPar.ParamByName('ACTION_ID').Value:='1';
				ccs = c.prepareCall("{? = call Param.getparam('ID') }");       //	  ExecSQL('begin kernel.doaction(:GROUP_ID,:DEAL_ID,:ACTION_ID); end;',SQLPar);
				ccs.registerOutParameter(1, java.sql.Types.NUMERIC);
				
				cs.setString(1,"P_BRANCH");
				cs.setString(2, branch);
				ISLogger.getLogger().error("P_BRANCH: "+branch);
				cs.execute();
				cs.setString(1,"P_EMP_ID");//Идентификатор оператора Банка-эмитента
				cs.setString(2, "");
				ISLogger.getLogger().error("P_EMP_ID: ");
				cs.execute();
				cs.setString(1,"P_PERSON_ID");//Идентификатор Держателя карты
				cs.setString(2, "");
				ISLogger.getLogger().error("P_PERSON_ID: ");
				cs.execute();
				cs.setString(1,"P_FIRST_NAME");
		    	cs.setString(2, "");
				//ISLogger.getLogger().error("P_FIRST_NAME: "+ clientResident.getFirstname());
				cs.execute();
				cs.setString(1,"P_SECOND_NAME");
				cs.setString(2,clientResident.getLastname());
				ISLogger.getLogger().error("P_SECOND_NAME: "+clientResident.getLastname());
				cs.execute();
				cs.setString(1,"P_SURNAME");
				cs.setString(2, "");
			//	ISLogger.getLogger().error("P_SURNAME: "+clientResident.getPatronymic());
				cs.execute();
				cs.setString(1,"P_BIRTH_DATE");
				cs.setString(2, clientResident.getDate_birth());
				ISLogger.getLogger().error("P_BIRTH_DATE: "+clientResident.getDate_birth());
				cs.execute();
				cs.setString(1,"P_P_PROC_MODE"); //узнать что это такое
				cs.setString(2, "");
				ISLogger.getLogger().error("P_P_PROC_MODE: ");
				cs.execute();
				cs.setString(1,"P_SECURITY_ID");
				cs.setString(2, "");
				ISLogger.getLogger().error("P_SECURITY_ID: ");
				cs.execute();
				cs.setString(1,"P_SEX");
				cs.setString(2, "");
				//ISLogger.getLogger().error("P_SEX"+clientResident.getCode_gender());
				cs.execute();
				cs.setString(1,"P_RESIDENCE");//резидент или нерезидент 
				cs.setString(2, "1");
				ISLogger.getLogger().error("P_RESIDENCE:1 ");
				cs.execute();
				cs.setString(1,"P_P_ID_TYPE"); //тип док-та
				cs.setString(2, "");
				ISLogger.getLogger().error("P_P_ID_TYPE: ");
				cs.execute(); 
				cs.setString(1,"P_P_ID_NUMBER");
				cs.setString(2, clientResident.getPassport_number());
				ISLogger.getLogger().error("P_P_ID_NUMBER: "+clientResident.getPassport_number());
				cs.execute(); 
				cs.setString(1,"P_P_ID_SERIES");
				cs.setString(2, clientResident.getPassport_series());
				ISLogger.getLogger().error("P_P_ID_SERIES: "+clientResident.getPassport_series());
				cs.execute(); 
				cs.setString(1,"P_P_ID_AUTHORITY");//Учреждение, выдавшее документ
				cs.setString(2,"" );
				//ISLogger.getLogger().error("P_P_ID_AUTHORITY: "+clientResident.getIssued_by());
				cs.execute(); 
				cs.setString(1,"P_P_ID_ISSUE_DATE");
				//cs.setString(2, clientResident.getDate_issue());
				//ISLogger.getLogger().error("P_P_ID_ISSUE_DATE: "+clientResident.getDate_issue());
				cs.execute(); 
				cs.setString(1,"P_ADDRESS_TYPE");
				cs.setString(2, "");
				ISLogger.getLogger().error("P_ADDRESS_TYPE: ");//Тип адреса
				cs.execute(); 
				cs.setString(1,"P_A_PROC_MODE"); //Proc mode что это такое
				cs.setString(2, "");
				ISLogger.getLogger().error("P_A_PROC_MODE: ");
				cs.execute(); 
				cs.setString(1,"P_ADDRESS_LINE1");
				cs.setString(2, "");
				//ISLogger.getLogger().error("P_ADDRESS_LINE1: "+clientResident.getAddress());
				
				cs.setString(1,"P_APP_TYPE");
				cs.setString(2, "BTRT01");
				cs.execute(); 
				
				cs.execute(); 
				cs.setString(1,"P_ADDRESS_LINE2");
				cs.setString(2, "");
				ISLogger.getLogger().error("P_ADDRESS_LINE2: ");
				cs.execute();
				cs.setString(1,"P_REGION");
				cs.setString(2, "");
				//ISLogger.getLogger().error("P_REGION: "+clientResident.getRegion());
				cs.execute();
				cs.setString(1,"P_COUNTRY");
				cs.setString(2, "");
				ISLogger.getLogger().error("P_COUNTRY: ");
				cs.execute();
				cs.setString(1,"P_PRIMARY_PHONE");
				cs.setString(2, clientResident.getPhone());
				ISLogger.getLogger().error("P_PRIMARY_PHONE: "+ clientResident.getPhone());
				cs.execute();
				cs.setString(1,"P_CARD_NUMBER");
				cs.setString(2, "");
				ISLogger.getLogger().error("P_CARD_NUMBER: ");
				cs.execute();
				cs.setString(1,"P_CARD_TYPE");
				cs.setString(2, clientResident.getCard_type());
				ISLogger.getLogger().error("P_CARD_TYPE: "+clientResident.getCard_type());
				cs.execute();
				cs.setString(1,"P_EMBOSSED_CH_NAME");//Имя держателя карты, которое будет указано на карте
				cs.setString(2, "");
				ISLogger.getLogger().error("P_EMBOSSED_CH_NAME: ");
				cs.execute();
				cs.setString(1,"P_IS_PRIMARY");
				cs.setString(2, "");
				ISLogger.getLogger().error("P_IS_PRIMARY: ");//Первичная карта
				cs.execute();
				cs.setString(1,"P_EXPIRATION_DATE");//Дата истечения карты
				cs.setString(2, "");
				ISLogger.getLogger().error("P_EXPIRATION_DATE: ");
				cs.execute();
				cs.setString(1,"P_ACCOUNT_NUMBER");
				cs.setString(2, "");
				ISLogger.getLogger().error("P_ACCOUNT_NUMBER: ");
				cs.execute();
				
				cs.setString(1,"P_ACCOUNT_TYPE");
				cs.setString(2, "");
				ISLogger.getLogger().error("P_ACCOUNT_TYPE: ");
				cs.execute();
				
				cs.setString(1,"P_CURRENCY");//Валюта карточного договора
				cs.setString(2, "");
				ISLogger.getLogger().error("P_CURRENCY: ");
				cs.execute();
				
				cs.setString(1,"P_CH_COMPANY_NAME"); //название компании
				cs.setString(2, "");
				ISLogger.getLogger().error("P_CH_COMPANY_NAME: ");
				cs.execute();
				cs.setString(1,"P_ADDRESS_ID"); //ID адреса
				cs.setString(2, "");
				ISLogger.getLogger().error("P_ADDRESS_ID: ");
				cs.execute();
				cs.setString(1,"P_MOBILE_PHONE");
				cs.setString(2, clientResident.getPhone());
				ISLogger.getLogger().error("P_MOBILE_PHONE: "+clientResident.getPhone());
				cs.execute();
				cs.setString(1,"P_EMAIL"); //мейл
				cs.setString(2, "");
				ISLogger.getLogger().error("P_EMAIL: ");
				cs.execute();			
				acs.execute();
				ccs.execute();
				res = new Res(0, ccs.getString(1));
				c.commit();
			} catch (Exception e) {
				ISLogger.getLogger().error(e);
				e.printStackTrace();
			
				try {
					c.rollback();
				} catch (Exception ex) {
					ISLogger.getLogger().error(CheckNull.getPstr(ex));
					ex.printStackTrace();
					
				}
				res = new Res(-1, e.getMessage());
			} finally {
				ConnectionPool.close(c);
			}
			return res;
    
		}
    
public String getNationality(String id) {   // определить натсиональность
		
		Connection c = null;
		String resp = null;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("select*from ss_national_gcp where id=?");
			ps.setInt(1, Integer.parseInt(id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				resp =rs.getString("MAP");
			}

		} catch (Exception e) {
			ISLogger.getLogger().error(e);
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}

		return resp;
	}


public String getNationalityNE(String name) {
	
	Connection c = null;
	String resp = null;
	try {
		c = ConnectionPool.getConnection();
		PreparedStatement ps = c.prepareStatement("select*from ss_national_gcp where id=?");
		ps.setString(1, name);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			resp =rs.getString("MAP");
		}

	} catch (Exception e) {
		ISLogger.getLogger().error(e);
		e.printStackTrace();
	} finally {
		ConnectionPool.close(c);
	}

	return resp;
}

    
    public String insertFile(String file_name, String sender ) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		Client_mass_opening_file client_mass_opening_file = new Client_mass_opening_file();
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SEQ_client_mass_opening_file.NEXTVAL id FROM DUAL");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				client_mass_opening_file.setId(rs.getInt("id"));
			}
			ps = c.prepareStatement(
					"INSERT INTO client_mass_opening_file (id, file_name, sender, v_date, status ) VALUES (?,?,?,?,?)");

			ps.setInt(1, client_mass_opening_file.getId());
			ps.setString(2, file_name);
			ps.setString(3, sender );//означает что это 1резидент 2 нерезидент
			ps.setString(4, sdf.format(System.currentTimeMillis()));
			ps.setString(5, "1");
			ps.executeUpdate();
			c.commit();

		} catch (Exception e) {
			ISLogger.getLogger().error(e);
			e.printStackTrace();
			

		} finally {
			ConnectionPool.close(c);

		}
		return client_mass_opening_file.getId().toString();

	}
    public void update(String id_file, String status) {

		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE client_mass_opening_file SET  status=?  WHERE id=?");
			ps.setString(1, status);
			ps.setString(2, id_file);

			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			ISLogger.getLogger().error(e);
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}

	}
    public List<ClientResident> getClient_mass_opening_clientResident(String id) {
		ISLogger.getLogger().error("File_id: " + id);
		List<ClientResident> clientResident = new ArrayList<ClientResident>();
		Connection c = null;
		ClientResident cr = null;
		boolean checkCr;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM client_mass_opening_resident where file_id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				cr = new ClientResident(rs.getString("ID"), rs.getString("PINFL"), rs.getString("LASTNAME"),
						rs.getString("DATE_BIRTH"), rs.getString("CODE_ORGANIZATION"), rs.getString("CARD_TYPE"),
						rs.getString("PHONE"),rs.getString("ACC_GROUP"),rs.getString("STATUS"), 
						rs.getString("PASSPORT_SERIES"), rs.getString("PASSPORT_NUMBER"));

				checkCr = getSortedClient(cr);
				if (checkCr == false) {
					updateStatusResident("0", cr.getId());// сюда инсерт в базу при // неправильно обработаных полях
					
					
				}
				if (checkCr) {
					updateStatusResident("1", cr.getId());// сюда инсерт в базу при правильно // обработаных полях
					
					
					clientResident.add(cr);
				}

				cr = null;
			} // while
		} catch (Exception e) {
			ISLogger.getLogger().error(e);
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return clientResident;
	}
    
    public static ClientResident getClientByPinfl(String pinfl) {
		ISLogger.getLogger().error("Client_id: " + pinfl);
	//	ClientResident clientResidents = new ClientResident();
		Connection c = null;
		ClientResident crt = null;

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM client_mass_opening_resident where pinfl=?");
			ps.setString(1, pinfl);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				crt = new ClientResident(rs.getString("ID"), rs.getString("PINFL"), rs.getString("LASTNAME"),
						rs.getString("DATE_BIRTH"), rs.getString("CODE_ORGANIZATION"), rs.getString("CARD_TYPE"),
						rs.getString("PHONE"),rs.getString("ACC_GROUP"),rs.getString("STATUS"),
						rs.getString("PASSPORT_SERIES"), rs.getString("PASSPORT_NUMBER"));								
			} // while
		} catch (Exception e) {
			ISLogger.getLogger().error(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return crt;
	}
	
 

    public static List<ClientResident> getClient_mass_opening_clientResidentCheck(String id) {
		ISLogger.getLogger().error("Client_id: " + id);
		 List<ClientResident> clientResidents = new ArrayList<ClientResident>();
		Connection c = null;
		ClientResident crt = null;

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM client_mass_opening_resident where id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				crt = new ClientResident(rs.getString("ID"), rs.getString("PINFL"), rs.getString("LASTNAME"),
						rs.getString("DATE_BIRTH"), rs.getString("CODE_ORGANIZATION"), rs.getString("CARD_TYPE"),
						rs.getString("PHONE"),rs.getString("ACC_GROUP"),rs.getString("STATUS"),
						rs.getString("PASSPORT_SERIES"), rs.getString("PASSPORT_NUMBER"));								
				clientResidents.add(crt);
					
			
			} // while
		} catch (Exception e) {
			ISLogger.getLogger().error(e);
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return clientResidents;
		
	}
    
   
    
 // проверка есть ли клиент 
    public List<CheckAccountResident> checkAccountResident( String code_resident, String code_type, String pinfl) {
    	List<CheckAccountResident>	сheckAccountResidents = new ArrayList<CheckAccountResident>() ;																									
    		Connection c = null;
    		boolean resp = false;
    		
    		try {
    			c = ConnectionPool.getConnection();
    			//PreparedStatement ps = c.prepareStatement("select  p.* from client_p p, client c where p.branch=c.branch and p.id=c.id_client and c.code_resident=? and c.code_type= ?  and p.pinfl=?");//для зирата
    					
    			PreparedStatement ps = c.prepareStatement("select  p.* from v_client_p_all_pinfl p, v_client_all_bank c where p.branch=c.branch and p.id=c.id_client and c.code_resident=? and c.code_type= ?  and p.pinfl=?");//для ипака

    			//ps.setString(1, branch);
    			ps.setString(1, code_resident);
    			ps.setString(2, code_type);
    			ps.setString(3, pinfl);

    			ResultSet rs = ps.executeQuery();
    			while (rs.next()) {
    				CheckAccountResident ca = new CheckAccountResident();
    				ca = new CheckAccountResident(rs.getString("BRANCH"),
    						rs.getString("ID_NIBBD"),
    						rs.getString("ID"),
    						rs.getString("PINFL"),
    						rs.getString("DATE_OPEN_NIBBD"),
    						rs.getString("DATE_CLOSE_NIBBD"),
    					    rs.getString("STATE"),
    						rs.getString("TYPE_DOCUMENT"),
    					    rs.getString("PASSPORT_SERIAL"),
    						rs.getString("PASSPORT_NUMBER"),
    				    	rs.getString("PASSPORT_PLACE_REGISTRATION"),
    						rs.getString("PASSPORT_DATE_REGISTRATION"),
    						rs.getString("PASSPORT_DATE_EXPIRATION"),
    						rs.getString("POST_ADDRESS"));
    				сheckAccountResidents.add(ca);
     
    			}

    		} catch (Exception e) {
    			ISLogger.getLogger().error(e);
    			e.printStackTrace();
    		} finally {
    			ConnectionPool.close(c);
    		}

    		return сheckAccountResidents;
    	}
    
    
    	public String getIdFromSSi_physiclal() {// проверка есть ли клиент 
    		
    		Connection c = null;
    		String resp = null;
    		try {
    			c = ConnectionPool.getConnection();
    			PreparedStatement ps = c.prepareStatement(
    					"SELECT seq_SSI_PHYSICAL.nextval id FROM dual");

    			ResultSet rs = ps.executeQuery();
    			if (rs.next()) {
    				resp =rs.getString("id");
    			}

    		} catch (Exception e) {
    			ISLogger.getLogger().error(e);
    			e.printStackTrace();
    		} finally {
    			ConnectionPool.close(c);
    		}

    		return resp;
    	}
    	
    	public static FizInfoDetailsResponse fizInfoDetailsResponse(String branch, String pinfl, String passp_ser, String passp_num) throws Exception {
    		ObjectMapper objectMapper = new ObjectMapper();
    		FizInfoDetailsResponse myObj;
    		Res res = new Res();
    		res.setCode(-1);
    		String url = "";

    		//details
    		url = "http://10.10.12.85:51000/RESTAdapter/FizInfoDetails"; 
    		FizInfoDetailsRequest infoReq = new FizInfoDetailsRequest();
    		GetDataByPinppRequest getdatareq = new GetDataByPinppRequest();
    		DataRequest datareq = new DataRequest();
    		DataByPinppRequest dbpr = new DataByPinppRequest();
    		dbpr.setPinpp(pinfl);
    		dbpr.setDocument(passp_ser+passp_num);
    		datareq.setDataByPinppRequest(dbpr);
    		getdatareq.setData(datareq);
    		infoReq.setGetDataByPinppRequest(getdatareq);
    		String p_data= objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(infoReq);
    		
    		/*String p_data="{ "+
            " \"pinpp\": \"" + pinfl+ "\"" +
            "};";*/
    		
    		String content = sendData(url, p_data);
    		//String contentTemp = "{     \"Result\": 1,     \"Data\": {\"personaldata\": {\"row\":    {        \"queryld\": \"string\",        \"pinpp\": \"31312781080071\",        \"document\": \"AB2429121\",        \"surnamelatin\": \"OSTONOV\",        \"namelatin\": \"HAMZA\",        \"patronymlatin\": \"G‘OFURJONOVICH\",        \"engsurname\": \"OSTONOV\",        \"engname\": \"KHAMZA\",        \"birth_date\": \"1978-12-13\",        \"birthplace\": \"QORAKO‘L TUMANI\",        \"birthplaceid\": \"\",        \"birthcountry\": \"УЗБЕКИСТАН\",        \"birthcountryid\": 860,        \"livestatus\": 1,        \"nationality\": \"УЗБЕК/УЗБЕЧКА\",        \"nationalityid\": \"01\",        \"citizenship\": \"УЗБЕКИСТАН\",        \"citizenshipid\": 860,        \"sex\": 1,        \"docgiveplace\": \"БУХАРСКИЙ ГОВД БУХАРСКОЙ ОБЛАСТИ\",        \"docgiveplaceid\": 6206,        \"docdatebegin\": \"2016-01-11\",        \"docdateend\": \"2026-01-10\"     }}}  }";                          
    		
    		myObj = objectMapper.readValue(content, FizInfoDetailsResponse.class);
    		//myObj = objectMapper.readValue(contentTemp, FizInfoDetailsResponse.class);
    		
    		return myObj;
    	}
    	
    	
    	public static String sendData(String p_url, String p_data) {
    		//ISLogger.getLogger().error("url = " + p_url);		
    		//ISLogger.getLogger().error("sendData data! : "+p_data);
    		String message = "";
    		String message_err = "";
    		int responseCode = 0;
    		try {
    			URL url = new URL(p_url);

    			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    			connection.setRequestMethod("POST");
    			connection.setRequestProperty("Content-Type","application/json;charset=UTF-8");

    			String auth = "piuser" + ":" + "user_for_pi!1";
    			byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(/*StandardCharsets.UTF_8*/"UTF-8"));
    			String authHeaderValue = "Basic " + new String(encodedAuth);						
    			connection.setRequestProperty("Authorization", authHeaderValue);
    			
    			connection.setDoOutput(true);
    			DataOutputStream wr = new DataOutputStream(	connection.getOutputStream());
    			wr.writeBytes(p_data);
    			wr.flush();
    			responseCode = connection.getResponseCode();
    			
    			BufferedReader br = null;
    			if (100 <= connection.getResponseCode() && connection.getResponseCode() <= 399) {
    			    br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
    			} else {
    			    br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
    			    message_err = connection.getResponseMessage() + ", code: "+ responseCode;
    			}

    			StringBuilder sb = new StringBuilder();
    			String output;
    			while ((output = br.readLine()) != null) {
    			  sb.append(output);
    			}
    			message = sb.toString();
    			if (!message_err.equals("") && message_err!="" ){
    				message=message+". error result: "+message_err;
    			}
    			
    			/*if (responseCode == HttpURLConnection.HTTP_OK) {
    				ISLogger.getLogger().error("sendData 06 ");
    				BufferedReader in = new BufferedReader(new InputStreamReader(
    						connection.getInputStream()));
    				String inputLine;
    				ISLogger.getLogger().error("sendData 06.1. ");
    				StringBuffer stringBuffer = new StringBuffer();
    				ISLogger.getLogger().error("sendData 06.2. ");
    				while ((inputLine = in.readLine()) != null) {
    					byte[] myBytes=inputLine.getBytes();
    					//stringBuffer.append(new String(inputLine.getBytes(), "UTF-8"));
    					stringBuffer.append(new String(myBytes, "UTF-8"));
    					ISLogger.getLogger().info("Resp1...: " + (new String(myBytes, "UTF-8")));
    					ISLogger.getLogger().info("Resp2...: " + (new String(myBytes, "WINDOWS-1251")));
    					ISLogger.getLogger().info("Resp3...: " + (new String(myBytes)));
    				}
    				ISLogger.getLogger().error("sendData 06.3. ");
    				in.close();
    				ISLogger.getLogger().info("Resp4"  );
    				message = stringBuffer.toString();
    				ISLogger.getLogger().info("Response: " + message);
    			} else {
    				ISLogger.getLogger().error("sendData 07 ");
    				message = connection.getResponseMessage() + ", code: "
    						+ responseCode;
    				ISLogger.getLogger().error("sendData 08 "+message);
    			}*/
    			//IOUtils.copy(connection.getInputStream(), writer, "utf-8");
    			
    			//ISLogger.getLogger().error(	"response code: " + responseCode + ". body: " + message);
    		} catch (Exception e) {
    			ISLogger.getLogger().error("url = " + p_url);		
    			ISLogger.getLogger().error("sendData data : "+p_data);
    			ISLogger.getLogger().error("responseCode: " + responseCode);
    			ISLogger.getLogger().error("sendData err Message: "+e.getMessage());
    			ISLogger.getLogger().error("sendData err Cause: "+e.getCause());
    			e.printStackTrace();
    		}
    		return message;// writer.toString();
    	}
    	
    	
    	
    	
    	public GetFromNibbd getSsi_physical(String id) {
    		
    		GetFromNibbd  list= new GetFromNibbd();
    		Connection c = null;
    		try {
    			c = ConnectionPool.getConnection();
    			PreparedStatement ps = c.prepareStatement("SELECT * FROM SSI_PHYSICAL  where id=?");
    			ps.setString(1, id);
    			ResultSet rs = ps.executeQuery();
    			
    			while (rs.next()) {
    				list.setBirth_place(rs.getString("BRANCH"));  							
    				list.setId_client(		rs.getString("ID_CLIENT"));			
    				list.setMessage(		rs.getString("MESSAGE"));					
    				list.setSubject_state(		rs.getString("SUBJECT_STATE"));			
    				list.setInn(	rs.getString("INN"));		
    				list.setInn_registrated(	rs.getString("INN_REGISTRATED"));		
    				list.setInn_registrated_gni(	rs.getString("INN_REGISTRATED_GNI"));		
    				list.setPin(	rs.getString("PIN"));		
    				list.setLast_name(	rs.getString("LAST_NAME"));				
    				list.setFirst_name	(rs.getString("FIRST_NAME"));			
    				list.setPatronym(	rs.getString("PATRONYM"));		
    				list.setSurname(rs.getString("SURNAME")	);	
    				list.setGivenname	(rs.getString("GIVENNAME"));				
    				list.setBirth_date	(	rs.getString("BIRTH_DATE"));		
    				list.setSex(	rs.getString("SEX")	);	
    				list.setPassport_seria( 	rs.getString("PASSPORT_SERIA"));	
    				list.setPassport_number	(	rs.getString("PASSPORT_NUMBER"));			
    				list.setDate_issue(	rs.getString("DATE_ISSUE"));		
    				list.setDate_expiry	(rs.getString("DATE_EXPIRY"));		
    				list.setGive_place(	rs.getString("GIVE_PLACE"));						
    				list.setGive_place_name	(rs.getString("GIVE_PLACE_NAME"));
    				list.setBirth_country	(rs.getString("BIRTH_COUNTRY"));
    				list.setBirth_country_name	(rs.getString("BIRTH_COUNTRY_NAME"));		
    				list.setBirth_place	(rs.getString("BIRTH_PLACE"));	
    				list.setNationality	(rs.getString("NATIONALITY"));	
    				list.setNationality_desc	(rs.getString("NATIONALITY_DESC")	);	
    				list.setCitizenship	(rs.getString("CITIZENSHIP"));	
    				list.setCitizenship_name	(rs.getString("CITIZENSHIP_NAME"));		
    				list.setDomicile_country	(rs.getString("DOMICILE_COUNTRY"));		
    				list.setDomicile_region	(rs.getString("DOMICILE_REGION"));	
    				list.setDomicile_district	(rs.getString("DOMICILE_DISTRICT"));
    				list.setDomicile_place_desc	(rs.getString("DOMICILE_PLACE_DESC"));	
    				list.setDomicile_street_desc	(rs.getString("DOMICILE_STREET_DESC"));		
    				list.setDomicile_address	(rs.getString("DOMICILE_ADDRESS"));	
    				list.setDomicile_house	(rs.getString("DOMICILE_HOUSE")	);
    				list.setDomicile_block	(rs.getString("DOMICILE_BLOCK"));
    				list.setDomicile_flat	(rs.getString("DOMICILE_FLAT"));
    				list.setDomicile_begin(rs.getString("DOMICILE_BEGIN")	);
    				list.setTemp_country	(rs.getString("TEMP_COUNTRY"));	
    				list.setTemp_region	(rs.getString("TEMP_REGION")	);
    				list.setTemp_district	(rs.getString("TEMP_DISTRICT"));
    				list.setTemp_place_desc	(rs.getString("TEMP_PLACE_DESC"));	
    				list.setTemp_street_desc	(rs.getString("TEMP_STREET_DESC"));
    				list.setTemp_address	(rs.getString("TEMP_ADDRESS"));
    				list.setTemp_house	(rs.getString("TEMP_HOUSE")	);
    				list.setTemp_block	(rs.getString("TEMP_BLOCK")		);
    				list.setTemp_flat	(rs.getString("TEMP_FLAT"));
    				list.setTemp_begin	(rs.getString("TEMP_BEGIN")	);
    				list.setTemp_end	(rs.getString("TEMP_END"));

    			}  
    		} catch (Exception e) {
    			ISLogger.getLogger().error(e);
    			e.printStackTrace();
    		} finally {
    			ConnectionPool.close(c);
    		}
    		return list;
    	}


//    
    	public void updateStatusResident(String status, String id) {// обновление статуса для нерезидентов которые не прошли
    		                                                                                                 // проверку по полям статус

    		Connection c = null;

    		try {
    			c = ConnectionPool.getConnection();
    			PreparedStatement ps = c.prepareStatement("UPDATE client_mass_opening_resident SET  status=?  WHERE id=?");
    			ps.setString(1, status);
    			ps.setString(2, id);

    			ps.executeUpdate();
    			c.commit();
    		} catch (Exception e) {
    			ISLogger.getLogger().error(e);
    			e.printStackTrace();

    		} finally {
    			ConnectionPool.close(c);
    		}

    	}

    	
    	public  Res getPiznak (String un,String pw,String alias, String branch) {
    		Res res = null;
    		DateFormat  sdf = new SimpleDateFormat("dd.MM.yyyy");
    		DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		Connection c = null;
    		CallableStatement cs = null;
    		CallableStatement acs = null;
    		CallableStatement ccs = null;
    		try {
    			c = ConnectionPool.getConnection(un,pw,alias);
    			acs = c.prepareCall("{? = call kernel.getaccreg('22618') }");
    			acs.registerOutParameter(1, Types.BIGINT);
    			acs.execute();
    			res = new Res(0, String.valueOf(acs.getLong(1)));
    			c.commit();
    		}catch (Exception e) {
    			e.printStackTrace();
    			ISLogger.getLogger().error(e);
    			try {
    				c.rollback();
    			} catch (Exception ex) {
    				ex.printStackTrace();
    				ISLogger.getLogger().error(CheckNull.getPstr(ex));
    			}
    			res = new Res(-1, e.getMessage());
    		} finally {
    			ConnectionPool.close(c);
    		}
    		return res;
    		
    		
    	}
    	
    	public static boolean getSortedClient(ClientResident cl) {// резиденты сортирвка полей

    		boolean res = false;

    		try {
    			if (cl.getPinfl() != null && Pattern.matches("^[0-9]+", cl.getPinfl()) && cl.getPinfl().length() == 14) {
    				if(cl.getPassport_series() != null && Pattern.matches("^[a-z A-Z ']+", cl.getPassport_series())
    						&& cl.getPassport_series().length() == 2 ) {
    				 if (cl.getPassport_number() != null && Pattern.matches("^[0-9]+", cl.getPassport_number()) && cl.getPassport_number().length() == 7) {
    				   if (cl.getLastname() != null && Pattern.matches("^[a-z A-Z ']+", cl.getLastname())
    						&& !(cl.getLastname().length() < 2)) {
    					if (cl.getDate_birth() != null && Pattern.matches("^\\d{2}.\\d{2}.\\d{4}$", cl.getDate_birth())) {
    						if (cl.getCode_organization() != null && Pattern.matches("^[0-9]+", cl.getCode_organization())
    								&& cl.getCode_organization().length() == 8) {
    							if (cl.getCard_type() != null && (cl.getCard_type().equals("1")
    									|| cl.getCard_type().equals("2") || cl.getCard_type().equals("3"))) {
    								if (cl.getPhone() != null && Pattern.matches("^[0-9]+", cl.getPhone())
    										&& cl.getPhone().length() == 12) {
    									if (cl.getAcc_group() != null && Pattern.matches("^[0-9]+", cl.getAcc_group())){
    									ISLogger.getLogger().error("correct: " + cl.toString());
    									res = true;
    								}
    							  }
    							}
    						}
    					}
    				}
    			}
    		}
    	}

    		} catch (Exception e) {
    			ISLogger.getLogger().error(e);
    			e.printStackTrace();
    		}

    		return res;
    		  		
    	}

}