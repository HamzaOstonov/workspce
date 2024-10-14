package com.is.client_mass_openingCheckNotResident;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.Res;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Client_mass_opening_not_residentService {

    private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM Client_mass_opening_resident ";


    public List<Client_mass_opening_not_resident> getClient_mass_opening_resident()  {

            List<Client_mass_opening_not_resident> list = new ArrayList<Client_mass_opening_not_resident>();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    Statement s = c.createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM Client_mass_opening_resident");
                    while (rs.next()) {
                            list.add(new Client_mass_opening_not_resident(
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
                                            rs.getString("acc_group"), null, null, null, null, null, null, null, null, null, null, null));
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

    private static List<FilterField> getFilterFields(Client_mass_opening_not_residentFilter filter){
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


    public static int getCount(Client_mass_opening_not_residentFilter filter)  {

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



    public static List<Client_mass_opening_not_resident> getClient_mass_opening_residentsFl(int pageIndex, int pageSize, Client_mass_opening_not_residentFilter filter)  {

            List<Client_mass_opening_not_resident> list = new ArrayList<Client_mass_opening_not_resident>();
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
                            list.add(new Client_mass_opening_not_resident(
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
                                            rs.getString("acc_group"), msql, msql, msql, msql, msql, msql, msql, msql, msql, msql, msql));
                    }
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return list;

    }


    public Client_mass_opening_not_resident getClient_mass_opening_resident(int client_mass_opening_residentId) {

            Client_mass_opening_not_resident client_mass_opening_resident = new Client_mass_opening_not_resident();
            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("SELECT * FROM client_mass_opening_resident WHERE id=?");
                    ps.setInt(1, client_mass_opening_residentId);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            client_mass_opening_resident = new Client_mass_opening_not_resident();
                            
                     //       client_mass_opening_resident.setId(rs.getString("id"));
                       //     client_mass_opening_resident.setFile_id(rs.getString("file_id"));
                            client_mass_opening_resident.setName(rs.getString("lastname"));
                            client_mass_opening_resident.setBirthdate(rs.getString("date_birth"));
                            client_mass_opening_resident.setCode_organization(rs.getString("code_organization"));
                            client_mass_opening_resident.setType_card(rs.getString("card_type"));
                            client_mass_opening_resident.setPhone_number(rs.getString("phone"));
                            client_mass_opening_resident.setPinfl(rs.getString("pinfl"));
                            client_mass_opening_resident.setStatus(Integer.valueOf(rs.getString("status")));
                          //  client_mass_opening_resident.setResponce(rs.getString("responce"));
                            client_mass_opening_resident.setGroup_code(rs.getString("acc_group"));
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
            return client_mass_opening_resident;
    }

    public static Client_mass_opening_not_resident create(Client_mass_opening_not_resident client_mass_opening_resident)  {

            Connection c = null;
            PreparedStatement ps = null;
            try {
                    c = ConnectionPool.getConnection();
                    ps = c.prepareStatement("SELECT SQ_client_mass_opening_resident.NEXTVAL id FROM DUAL");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                            client_mass_opening_resident.setPinfl(String.valueOf(rs.getString("id")));
                    }
                    ps = c.prepareStatement("INSERT INTO client_mass_opening_resident (id, file_id, lastname, date_birth, code_organization, card_type, phone, pinfl, status, responce, acc_group, ) VALUES (?,?,?,?,?,?,?,?,?,?,?,)");
                    
                    ps.setString(1,client_mass_opening_resident.getPinfl());
                    ps.setString(2,client_mass_opening_resident.getPinfl2());
                    ps.setString(3,client_mass_opening_resident.getName());
                    ps.setString(4,client_mass_opening_resident.getBirthdate());
                    ps.setString(5,client_mass_opening_resident.getCode_organization());
                    ps.setString(6,client_mass_opening_resident.getType_card());
                    ps.setString(7,client_mass_opening_resident.getPhone_number());
                    ps.setString(8,client_mass_opening_resident.getPinfl());
                    ps.setString(9,String.valueOf(client_mass_opening_resident.getStatus()));
                    ps.setString(10,client_mass_opening_resident.getResponce());
                    ps.setString(11,client_mass_opening_resident.getGroup_code());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }
            return client_mass_opening_resident;
    }

    public static void update1(Client_mass_opening_not_resident client_mass_opening_resident)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("UPDATE client_mass_opening_resident SET id=?, file_id=?, lastname=?, date_birth=?, code_organization=?, card_type=?, phone=?, pinfl=?, status=?, responce=?, acc_group=?,  WHERE id=?");
                    
                    ps.setString(1,client_mass_opening_resident.getPinfl());
                    ps.setString(2,client_mass_opening_resident.getPinfl2());
                    ps.setString(3,client_mass_opening_resident.getName());
                    ps.setString(4,client_mass_opening_resident.getBirthdate());
                    ps.setString(5,client_mass_opening_resident.getCode_organization());
                    ps.setString(6,client_mass_opening_resident.getType_card());
                    ps.setString(7,client_mass_opening_resident.getPhone_number());
                    ps.setString(8,client_mass_opening_resident.getPinfl());
                    ps.setString(9,String.valueOf(client_mass_opening_resident.getStatus()));
                    ps.setString(10,client_mass_opening_resident.getResponce());
                    ps.setString(11,client_mass_opening_resident.getGroup_code());
                    ps.executeUpdate();
                    c.commit();
            } catch (SQLException e) {
                    e.printStackTrace();

            } finally {
                    ConnectionPool.close(c);
            }

    }
//---------------------------------------------

	public  Res doActionResident(String un,String pw,String alias,String code_bank,GetFromNibbd getFromNibbds) {// процедура отрытия клиентa в банке
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
			cs.setString(1,"P_PASSPORT_PLACE_REGISTRATION");/// НЕТУ ДАННЫХ НУЖНО ВЗЯТЬ С ПРОЦЕДУРЫ 
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
			cs.setString(2,getFromNibbds.getDomicile_address()); 
			ISLogger.getLogger().error("P_CODE_CITIZENSHIP: "+getFromNibbds.getDomicile_address());
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
			cs.setString(2, "4");
			ISLogger.getLogger().error("P_TYPE_DOCUMENT: 4 ");
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
	
	
	public  Res doActions(String un,String pw,String alias, String branch,Client_mass_opening_not_resident clientResident, GetFromNibbd getFromNibbds) {// процедура отрытия счета в банке
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
			cs.setString(2, clientResident.getGroup_code());
			ISLogger.getLogger().error("ACC_GROUP_ID: "+clientResident.getGroup_code());
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
			if(clientResident.getType_card().equals("1")) {//1 – UZCARD, 2 – HUMO, 3 - оба	
				cs.setString(2, "001");
				ISLogger.getLogger().error("ID_ORDER: 001");
			}
			if(clientResident.getType_card().equals("2")) {
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
//------------нерезиденты ---------
	
	
	
	public  Res doActionNE(String un,String pw,String alias,String code_bank,ClientNotResident clientNotResident) {// процедура отрытия счета в банке для нерезидентов
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
			acs = c.prepareCall("{ call kernel.doaction(1,2,1) }");
			ccs = c.prepareCall("{ call Param.clearparam() }");
			ccs.execute();
			ccs = c.prepareCall("{? = call Param.getparam('ID') }");
			ccs.registerOutParameter(1, java.sql.Types.NUMERIC);
			ISLogger.getLogger().error("----------------doActionNE-------------");
			cs.setString(1, "P_BIRTHDAY");
		//	Date date = inputFormat.parse(clientNotResident.getDate_birth());
			cs.setString(2,clientNotResident.getDate_birth());
			ISLogger.getLogger().error("P_BIRTHDAY: "+clientNotResident.getDate_birth());//+sdf.format(date));
			cs.execute();
			cs.setString(1, "P_POST_ADDRESS"); 
			cs.setString(2, clientNotResident.getAddress());
			ISLogger.getLogger().error("P_POST_ADDRESS: "+clientNotResident.getAddress());
			cs.execute();
			cs.setString(1, "P_PASSPORT_TYPE");
			ISLogger.getLogger().error("P_PASSPORT_TYPE N");
			cs.setString(2, "N");
			cs.execute();
			cs.setString(1, "P_PASSPORT_SERIAL");
			cs.setString(2, clientNotResident.getPassport_serial());
			ISLogger.getLogger().error("P_PASSPORT_SERIAL: "+clientNotResident.getPassport_serial());
			cs.execute();
			cs.setString(1,"P_PASSPORT_NUMBER");
			ISLogger.getLogger().error("P_PASSPORT_NUMBER: "+clientNotResident.getPassport_number());
			cs.setString(2,clientNotResident.getPassport_number());
			cs.execute();
			cs.setString(1,"P_PASSPORT_PLACE_REGISTRATION");
			ISLogger.getLogger().error("P_PASSPORT_PLACE_REGISTRATION: "+clientNotResident.getIssued_by());		
		    cs.setString(2, clientNotResident.getIssued_by() );		    
			cs.execute();
			cs.setString(1,"P_PASSPORT_DATE_REGISTRATION");
			//Date date_issue = inputFormat.parse();
			cs.setString(2, clientNotResident.getDate_issue());
			ISLogger.getLogger().error("P_PASSPORT_DATE_REGISTRATION: "+clientNotResident.getDate_issue());//+sdf.format(date_issue));
			cs.execute();
			cs.setString(1,"P_CODE_BANK");
			cs.setString(2, code_bank);
			ISLogger.getLogger().error("P_CODE_BANK: "+code_bank);
			cs.execute();
			cs.setString(1,"P_CODE_CITIZENSHIP");
			cs.setString(2,clientNotResident.getCitizenship()); 
			ISLogger.getLogger().error("P_CODE_CITIZENSHIP: "+clientNotResident.getCitizenship());
			cs.execute();
			cs.setString(1,"P_BIRTH_PLACE");
			cs.setString(2, clientNotResident.getBirth_place());
			ISLogger.getLogger().error("P_BIRTH_PLACE: "+clientNotResident.getBirth_place());
			cs.execute();
			cs.setString(1,"P_CODE_CAPACITY");
			cs.setString(2, "0801");//"inn_registrated_gni":"0313"
			ISLogger.getLogger().error("P_CODE_CAPACITY: 0801 ");
			cs.execute();
			cs.setString(1,"P_CODE_GENDER");
			cs.setString(2, clientNotResident.getCode_gender());
			ISLogger.getLogger().error("P_CODE_GENDER:  "+  clientNotResident.getCode_gender());
			cs.execute();
			cs.setString(1,"P_CODE_NATION");
			//String nation=getNationality();// переделтаь !!!!!
			cs.setString(2,clientNotResident.getNationality());
			ISLogger.getLogger().error("P_CODE_NATION:      "+clientNotResident.getNationality());
			cs.execute();
			cs.setString(1,"P_TYPE_DOCUMENT");
			cs.setString(2, "4");
			ISLogger.getLogger().error("P_TYPE_DOCUMENT: 4 ");
			cs.execute();
			cs.setString(1,"P_PASSPORT_DATE_EXPIRATION");
	//		Date date_expiry = inputFormat.parse(clientNotResident.getValidity_expire());
			cs.setString(2, clientNotResident.getValidity_expire());
			ISLogger.getLogger().error("P_PASSPORT_DATE_EXPIRATION: "+clientNotResident.getValidity_expire());//sdf.format(date_expiry));
			cs.execute();
			cs.setString(1,"P_CODE_ADR_REGION");
			cs.setString(2, clientNotResident.getRegion());
			ISLogger.getLogger().error("P_CODE_ADR_REGION: "+clientNotResident.getRegion());
			cs.execute();
			cs.setString(1,"P_CODE_ADR_DISTR");
			cs.setString(2, clientNotResident.getDistrict());
			ISLogger.getLogger().error("P_CODE_ADR_DISTR: "+clientNotResident.getDistrict());
			cs.execute();
			cs.setString(1,"P_FAMILY");
			cs.setString(2, clientNotResident.getLastname());
			ISLogger.getLogger().error("P_FAMILY: "+clientNotResident.getLastname());
			cs.execute();
			cs.setString(1,"P_FIRST_NAME");
			cs.setString(2,clientNotResident.getFirstname() );
			ISLogger.getLogger().error("P_FIRST_NAME: "+clientNotResident.getFirstname());
			cs.execute();
			cs.setString(1,"P_PATRONYMIC");
			cs.setString(2, clientNotResident.getPatronymic());
			ISLogger.getLogger().error("P_PATRONYMIC: "+clientNotResident.getPatronymic());
			ISLogger.getLogger().error("----------doActionNE---------------");
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
	
	public  Res doActionsNE(String un,String pw,String alias, String branch, ClientNotResident clientNotResident) {// процедура отрытия счета в банке
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
			ISLogger.getLogger().error("----------doActionsNE---------------");
			cs.setString(1,"NAME"); //наименование счета
			cs.setString(2, clientNotResident.getFirstname()+" "+clientNotResident.getLastname());
			ISLogger.getLogger().error("NAME: "+ clientNotResident.getFirstname()+" "+clientNotResident.getLastname());
			
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
			System.out.println("SIGN_REGISTR: "+action.getName());
			cs.setString(2, action.getName());
			ISLogger.getLogger().error("SIGN_REGISTR: ");//узнать признак ниббд
			cs.execute();
			
			cs.setString(1,"STATE");
			cs.setString(2, "0");
			ISLogger.getLogger().error("STATE:0 ");
			cs.execute();
			
			cs.setString(1,"ACC_GROUP_ID");
			cs.setString(2, clientNotResident.getAcc_group());
			ISLogger.getLogger().error("ACC_GROUP_ID: "+clientNotResident.getAcc_group());
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
			if(clientNotResident.getType_card().equals("1")) {//1 – UZCARD, 2 – HUMO, 3 - оба	
				cs.setString(2, "001");
				ISLogger.getLogger().error("ID_ORDER: 001");
			}
			if(clientNotResident.getType_card().equals("2")) {
				cs.setString(2, "200");
				ISLogger.getLogger().error("ID_ORDER: 200");
				
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
	
	//узкард открытие карты и утверждение при успешном ответе
	public  Res getUzCard (String un,String pw,String alias, String branch,ClientResident clientResident,GetFromNibbd fromNibbd ) {// процедура отрытия счета в банке для резидентов
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
			acs = c.prepareCall("{ call  kernel.doaction(121,1,1) }");
			ccs = c.prepareCall("{ call Param.clearparam() }");
			ccs.execute();
			ccs = c.prepareCall("{? = call Param.getparam('ID') }");
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
	//		cs.setString(2, clientResident.getFirstname());
			//ISLogger.getLogger().error("P_FIRST_NAME: "+ clientResident.getFirstname());
			cs.execute();
			
			cs.setString(1,"P_SECOND_NAME");
			cs.setString(2,clientResident.getLastname());
			ISLogger.getLogger().error("P_SECOND_NAME: "+clientResident.getLastname());
			cs.execute();
			
			cs.setString(1,"P_SURNAME");
			//cs.setString(2, clientResident.getPatronymic());
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
			//cs.setString(2, clientResident.getCode_gender());
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
			//cs.setString(2, clientResident.getPassport_number());
			//ISLogger.getLogger().error("P_P_ID_NUMBER: "+clientResident.getPassport_number());
			cs.execute(); 
			
			cs.setString(1,"P_P_ID_SERIES");
			//cs.setString(2, clientResident.getPassport_serial());
		//	ISLogger.getLogger().error("P_P_ID_SERIES: "+clientResident.getPassport_serial());
			cs.execute(); 
			
			cs.setString(1,"P_P_ID_AUTHORITY");//Учреждение, выдавшее документ
			//cs.setString(2,clientResident.getIssued_by() );
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
			//cs.setString(2, clientResident.getAddress());
			//ISLogger.getLogger().error("P_ADDRESS_LINE1: "+clientResident.getAddress());
			cs.execute(); 
			
			cs.setString(1,"P_ADDRESS_LINE2");
			cs.setString(2, "");
			ISLogger.getLogger().error("P_ADDRESS_LINE2: ");
			cs.execute();
			
			cs.setString(1,"P_REGION");
			//cs.setString(2, clientResident.getRegion());
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
			ISLogger.getLogger().error("P_ACCOUNT_NUMBER: ");//Номер счета Плательщика

			cs.execute();
			cs.setString(1,"P_ACCOUNT_TYPE"); // тип счета валютный сум
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
	
	//узкард открытие карты и утверждение при успешном ответе
		public  Res getUzCardNE (String un,String pw,String alias, String branch,ClientNotResident clientNotResident) {// процедура отрытия счета в банке для резидентов
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
				acs = c.prepareCall("{ call  kernel.doaction(121,1,1) }");
				ccs = c.prepareCall("{ call Param.clearparam() }");
				ccs.execute();
				ccs = c.prepareCall("{? = call Param.getparam('ID') }");
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
				cs.setString(2, clientNotResident.getFirstname());
				ISLogger.getLogger().error("P_FIRST_NAME: "+ clientNotResident.getFirstname());
				cs.execute();
				
				cs.setString(1,"P_SECOND_NAME");
				cs.setString(2,clientNotResident.getLastname());
				ISLogger.getLogger().error("P_SECOND_NAME: "+clientNotResident.getLastname());
				cs.execute();
				
				cs.setString(1,"P_SURNAME");
				cs.setString(2, clientNotResident.getPatronymic());
				ISLogger.getLogger().error("P_SURNAME: "+clientNotResident.getPatronymic());
				cs.execute();
				
				cs.setString(1,"P_BIRTH_DATE");
				cs.setString(2, clientNotResident.getDate_birth());
				ISLogger.getLogger().error("P_BIRTH_DATE: "+clientNotResident.getDate_birth());
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
				cs.setString(2, clientNotResident.getCode_gender());
				ISLogger.getLogger().error("P_SEX"+clientNotResident.getCode_gender());
				cs.execute();
				
				cs.setString(1,"P_RESIDENCE");//резидент или нерезидент 
				cs.setString(2, "2");
				ISLogger.getLogger().error("P_RESIDENCE:2 ");
				cs.execute();
				
				cs.setString(1,"P_P_ID_TYPE"); //тип док-та
				cs.setString(2, "");
				ISLogger.getLogger().error("P_P_ID_TYPE: ");
				cs.execute(); 
				
				cs.setString(1,"P_P_ID_NUMBER");
				cs.setString(2, clientNotResident.getPassport_number());
				ISLogger.getLogger().error("P_P_ID_NUMBER: "+clientNotResident.getPassport_number());
				cs.execute(); 
				
				cs.setString(1,"P_P_ID_SERIES");
				cs.setString(2, clientNotResident.getPassport_serial());
				ISLogger.getLogger().error("P_P_ID_SERIES: "+clientNotResident.getPassport_serial());
				cs.execute(); 
				
				cs.setString(1,"P_P_ID_AUTHORITY");//Учреждение, выдавшее документ
				cs.setString(2,clientNotResident.getIssued_by() );
				ISLogger.getLogger().error("P_P_ID_AUTHORITY: "+clientNotResident.getIssued_by());
				cs.execute(); 
				
				cs.setString(1,"P_P_ID_ISSUE_DATE");
				cs.setString(2, clientNotResident.getDate_issue());
				ISLogger.getLogger().error("P_P_ID_ISSUE_DATE: "+clientNotResident.getDate_issue());
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
				cs.setString(2, clientNotResident.getAddress());
				ISLogger.getLogger().error("P_ADDRESS_LINE1: "+clientNotResident.getAddress());
				cs.execute(); 
				
				cs.setString(1,"P_ADDRESS_LINE2");
				cs.setString(2, "");
				ISLogger.getLogger().error("P_ADDRESS_LINE2: ");
				cs.execute();
				
				cs.setString(1,"P_REGION");
				cs.setString(2, clientNotResident.getRegion());
				ISLogger.getLogger().error("P_REGION: "+clientNotResident.getRegion());
				cs.execute();
				
				cs.setString(1,"P_COUNTRY");
				cs.setString(2, "");
				ISLogger.getLogger().error("P_COUNTRY: ");
				cs.execute();
				
				cs.setString(1,"P_PRIMARY_PHONE");
				cs.setString(2, clientNotResident.getPhone());
				ISLogger.getLogger().error("P_PRIMARY_PHONE: "+ clientNotResident.getPhone());
				cs.execute();
				
				cs.setString(1,"P_CARD_NUMBER");
				cs.setString(2, "");
				ISLogger.getLogger().error("P_CARD_NUMBER: ");
				cs.execute();
				
				cs.setString(1,"P_CARD_TYPE");
				cs.setString(2, clientNotResident.getType_card());
				ISLogger.getLogger().error("P_CARD_TYPE: "+clientNotResident.getType_card());
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
				ISLogger.getLogger().error("P_ACCOUNT_NUMBER: ");//Номер счета Плательщика

				cs.execute();
				cs.setString(1,"P_ACCOUNT_TYPE"); // тип счета валютный сум
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
				cs.setString(2, clientNotResident.getPhone());
				ISLogger.getLogger().error("P_MOBILE_PHONE: "+clientNotResident.getPhone());
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
	
	
	
	
	

	

//---------------------------------------------------------------------------
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void remove(Client_mass_opening_not_resident client_mass_opening_resident)  {

            Connection c = null;

            try {
                    c = ConnectionPool.getConnection();
                    PreparedStatement ps = c.prepareStatement("DELETE FROM client_mass_opening_resident WHERE id=?");
                    ps.setString(1, client_mass_opening_resident.getPinfl());
                    ps.executeUpdate();
                    c.commit();
            } catch (Exception e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.close(c);
            }
    }
public String getNationality(String id) {
		
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
    public void update(int id_file, String status) {

		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("UPDATE client_mass_opening_file SET  status=?  WHERE id=?");
			ps.setString(1, status);
			ps.setInt(2, id_file);

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
						rs.getString("PHONE"),rs.getString("ACC_GROUP"),rs.getString("STATUS"));

				checkCr = getSortedClient(cr);
				if (checkCr == false) {
					updateStatusResident("0", cr.getId());// сюда инсерт в базу при
																						// неправильно обработаных полях
				}
				if (checkCr) {
					updateStatusResident("1", cr.getId());// сюда инсерт в базу при правильно
																				// обработаных полях
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
    
    
    public List<ClientResident> getClient_mass_opening_clientResidentCheck(String id) {
		ISLogger.getLogger().error("Client_id: " + id);
		List<ClientResident> clientResident = new ArrayList<ClientResident>();
		Connection c = null;
		ClientResident cr = null;

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT * FROM client_mass_opening_resident where id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				cr = new ClientResident(rs.getString("ID"), rs.getString("PINFL"), rs.getString("LASTNAME"),
						rs.getString("DATE_BIRTH"), rs.getString("CODE_ORGANIZATION"), rs.getString("CARD_TYPE"),
						rs.getString("PHONE"),rs.getString("ACC_GROUP"),rs.getString("STATUS"));								
					clientResident.add(cr);
			
			} // while
		} catch (Exception e) {
			ISLogger.getLogger().error(e);
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return clientResident;
	}
    
    public List<CheckAccountNotResident> checkAccountResident(String pinfl, String surname, String name, String middlename, String nationality, String citizenship,
    		String passport_serial, String passport_number, String birthdate, String gender, String birth_place, String passport_place_registration, String passport_date_registration, 
    		String passport_date_expiration, String code_organization, String type_card, String phone_number, String region, String area, String post_address, String group_code, 
    		String pinfl2) {// проверка есть ли клиент 
    	List<CheckAccountNotResident>	сheckAccountResidents = new ArrayList<CheckAccountNotResident>() ;																									
    		Connection c = null;
    		boolean resp = false;
    		
    		try {
    			c = ConnectionPool.getConnection();
    			PreparedStatement ps = c.prepareStatement("select  p.* from client_p p, client c where p.branch=c.branch and p.id=c.id_client and c.code_resident=? and c.code_type= ?  and p.pinfl=?");//для зирата
    					
    				//	"select  p.* from v_client_p_all_pinfl p, v_client_all_bank c where p.branch=c.branch and p.id=c.id_client and c.code_resident=? and c.code_type= ?  and p.pinfl=?");//для ипака

    			//ps.setString(1, branch);
    			ps.setString(1, pinfl);
    			ps.setString(2, surname);
    			ps.setString(3, name);
    			ps.setString(4, middlename);
    			ps.setString(5, nationality);
    			ps.setString(6, citizenship);
    			ps.setString(7, passport_serial);
    			ps.setString(8, passport_number);
    			ps.setString(9, birthdate);
    			ps.setString(10, gender);
    			ps.setString(11, birth_place);
    			ps.setString(12, passport_place_registration);
    			ps.setString(13, passport_date_registration);
    			ps.setString(14, passport_date_expiration);
    			ps.setString(15, code_organization);
    			ps.setString(16, type_card);
    			ps.setString(17, phone_number);
    			ps.setString(18, region);
    			ps.setString(19, area);
    			ps.setString(20, post_address);
    			ps.setString(21, group_code);

    			ResultSet rs = ps.executeQuery();
    			while (rs.next()) {
    				CheckAccountNotResident ca = new CheckAccountNotResident();
    				ca = new CheckAccountNotResident( pinfl, surname, name, middlename, nationality, citizenship, passport_serial, passport_number, birthdate, gender, birth_place,
    						passport_place_registration, passport_date_registration, passport_date_expiration, code_organization, type_card, phone_number, region, area, post_address, group_code, pinfl2, 0);
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

    						
    						
    				
    			} // while
    		} catch (Exception e) {
    			ISLogger.getLogger().error(e);
    			e.printStackTrace();
    		} finally {
    			ConnectionPool.close(c);
    		}
    		return list;
    	}


    	public List<ClientNotResident> getClient_mass_opening_clientNotResident(String file_id) {
    		ISLogger.getLogger().error("FILE ID: " + file_id);
    		List<ClientNotResident> clientNotResident = new ArrayList<ClientNotResident>();
    		Connection c = null;
    		ClientNotResident cr = null;
    		boolean checkCr;

    		try {
    			c = ConnectionPool.getConnection();
    			PreparedStatement ps = c.prepareStatement("SELECT * FROM client_mass_opening_not_resident where file_id=?");
    			ps.setString(1, file_id);
    			ResultSet rs = ps.executeQuery();

    			while (rs.next()) {

    				cr = new ClientNotResident(rs.getString("ID"), rs.getString("PINFL"), rs.getString("FIRSTNAME"),
    						rs.getString("LASTNAME"), rs.getString("PATRONYMIC"), rs.getString("NATIONALITY"),
    						rs.getString("CITIZENSHIP"), rs.getString("PASSPORT_SERIAL"), rs.getString("PASSPORT_NUMBER"),
    						rs.getString("DATE_BIRTH"), rs.getString("CODE_GENDER"), rs.getString("BIRTH_PLACE"),
    						rs.getString("ISSUED_BY"), rs.getString("DATE_ISSUE"), rs.getString("VALIDITY_EXPIRE"),
    						rs.getString("ORGANIZATION_CODE"), rs.getString("TYPE_CARD"), rs.getString("PHONE"),
    						rs.getString("REGION"), rs.getString("DISTRICT"), rs.getString("ADDRESS"),rs.getString("ACC_GROUP")

    				);

    				checkCr = getSorteNotClient(cr);
    				if (checkCr == false) {
    					updateStatusNotResident("0", cr.getId());// сюда инсерт в базу при
    																						// неправильно обработаных полях
    				}
    				if (checkCr) {
    					updateStatusNotResident("1", cr.getId());// сюда инсерт в базу при правильно
    					// обработаных полях
    					clientNotResident.add(cr);
    				}

    				cr = null;

    			} // while
    		} catch (Exception e) {
    			ISLogger.getLogger().error(e);
    			e.printStackTrace();
    		} finally {
    			ConnectionPool.close(c);
    		}
    		return clientNotResident;
    	}

    	public void updateStatusResident(String status, String id) {// обновление статуса для резидентов которые не прошли
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
    	public void updateStatusNotResident(String status, String id) {// обновление статуса для нерезидентов которые не прошли
    		// проверку по полям статус

    Connection c = null;

    try {
    c = ConnectionPool.getConnection();
    PreparedStatement ps = c.prepareStatement("UPDATE client_mass_opening_not_resident SET  status=?  WHERE id=?");
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
    	
    	public boolean getSortedClient(ClientResident cl) {// резиденты сортирвка полей

    		boolean res = false;

    		try {
    			if (cl.getPinfl() != null && Pattern.matches("^[0-9]+", cl.getPinfl()) && cl.getPinfl().length() == 14) {
    				if (cl.getLastname() != null && Pattern.matches("^[a-z A-Z ']+", cl.getLastname())
    						&& !(cl.getLastname().length() < 2)) {
    					if (cl.getDate_birth() != null && Pattern.matches("^\\d{2}.\\d{2}.\\d{4}$", cl.getDate_birth())) {
    						if (cl.getCode_organization() != null && Pattern.matches("^[0-9]+", cl.getCode_organization())
    								&& cl.getCode_organization().length() == 8) {
    							if (cl.getCard_type() != null && (cl.getCard_type().equals("1")
    									|| cl.getCard_type().equals("2") || cl.getCard_type().equals("3"))) {
    								if (cl.getPhone() != null && Pattern.matches("^[0-9]+", cl.getPhone())
    										&& cl.getPhone().length() == 12) {
//    									if (cl.getAcc_group() != null && Pattern.matches("^[0-9]+", cl.getAcc_group())){
//    									ISLogger.getLogger().error("correct: " + cl.toString());
//    									res = true;
//    								}
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

    	public boolean getSorteNotClient(ClientNotResident cl) {// нерезиденты

    		boolean res = false;
    		try {

    			if (cl.getPinfl() != null && Pattern.matches("^[0-9]+", cl.getPinfl()) && cl.getPinfl().length() == 14) {

    				if (cl.getFirstname() != null && Pattern.matches("^[a-z A-Z]+", cl.getFirstname())
    						&& cl.getFirstname().length() > 2) {

    					if (cl.getLastname() != null && Pattern.matches("^[a-z A-Z]+", cl.getLastname())
    							&& cl.getLastname().length() > 2) {

    						if (cl.getNationality() != null && Pattern.matches("^[0-9]+", cl.getNationality())
    								&& cl.getNationality().length() <5) {
    							ISLogger.getLogger().error("Citizenship: " + cl.getCitizenship() + "lenght: "
    									+ cl.getCitizenship().length());
    							if (cl.getCitizenship() != null && Pattern.matches("^[0-9]+", cl.getCitizenship())
    									&& cl.getCitizenship().length() > 2) {

    								ISLogger.getLogger().error("Passport_serial: " + cl.getPassport_serial() + "lenght: "
    										+ cl.getPassport_serial().length());
    								if (cl.getPassport_serial() != null
    										&& Pattern.matches("^[a-z A-Z]+", cl.getPassport_serial())
    										&& cl.getPassport_serial().length() < 3) {

    									if (cl.getPassport_number() != null
    											&& Pattern.matches("^[0-9]+", cl.getPassport_number())
    											&& cl.getPassport_number().length() < 9) {

    										if (cl.getDate_birth() != null
    												&& Pattern.matches("^\\d{2}.\\d{2}.\\d{4}$", cl.getDate_birth())) {

    											if (cl.getCode_gender() != null && (cl.getCode_gender().equals("1")
    													|| cl.getCode_gender().equals("2"))) {

    												if (cl.getBirth_place() != null) {

    													if (cl.getIssued_by() != null) {
    															

    														if (cl.getDate_issue() != null && Pattern.matches(
    																"^\\d{2}.\\d{2}.\\d{4}$", cl.getDate_issue())) {

    															if (cl.getValidity_expire() != null
    																	&& Pattern.matches("^\\d{2}.\\d{2}.\\d{4}$",
    																			cl.getValidity_expire())) {

    																if (cl.getOrganization_code() != null
    																		&& Pattern.matches("^[0-9]+",
    																				cl.getOrganization_code())
    																		&& cl.getOrganization_code().length() == 8) {

    																	if (cl.getType_card() != null
    																			&& (cl.getType_card().equals("1")
    																					|| cl.getType_card().equals("2")
    																					|| cl.getType_card().equals("3"))) {

    																		if (cl.getPhone() != null
    																				&& Pattern.matches("^[0-9]+",
    																						cl.getPhone())
    																				&& cl.getPhone().length() == 12) {

    																			if (cl.getRegion() != null
    																					&& Pattern.matches("^[0-9]+",
    																							cl.getRegion())) {

    																				if (cl.getDistrict() != null
    																						&& Pattern.matches("^[0-9]+",
    																								cl.getDistrict())) {

    																				}
    																				if (cl.getAddress() != null) {
    																					if (cl.getAcc_group() != null && Pattern.matches("^[0-9]+", cl.getAcc_group())){

    																					ISLogger.getLogger().error("correct: "
    																							+ cl.toString());
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
    												}
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





		public List<CheckAccountNotResident> checkAccountNotResident(String string, String string2, String pinfl) {
			// TODO Auto-generated method stub
			return null;
		}
		}