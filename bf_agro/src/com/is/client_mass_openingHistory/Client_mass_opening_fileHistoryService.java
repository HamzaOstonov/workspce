package com.is.client_mass_openingHistory;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;



import java.util.HashMap;
import java.sql.*;



public class Client_mass_opening_fileHistoryService {

        private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
        private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
        private static String msql ="SELECT * FROM Client_mass_opening_file ";


        public List<Client_mass_opening_file> getClient_mass_opening_file()  {

                List<Client_mass_opening_file> list = new ArrayList<Client_mass_opening_file>();
                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        Statement s = c.createStatement();
                        ResultSet rs = s.executeQuery("SELECT * FROM Client_mass_opening_file");
                        while (rs.next()) {
                                list.add(new Client_mass_opening_file(
                                                rs.getInt("id"),
                                                rs.getString("file_name"),
                                                rs.getString("sender"),
                                                rs.getString("v_date"),
                                                rs.getString("status")));
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

        private static List<FilterField> getFilterFields(Client_mass_opening_fileFilter filter){
                List<FilterField> flfields = new ArrayList<FilterField>();


              if(!CheckNull.isEmpty(filter.getId())){
                      flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
              }
              if(!CheckNull.isEmpty(filter.getFile_name())){
                      flfields.add(new FilterField(getCond(flfields)+ "file_name=?",filter.getFile_name()));
              }
              if(!CheckNull.isEmpty(filter.getSender())){
                      flfields.add(new FilterField(getCond(flfields)+ "sender=?",filter.getSender()));
              }
              if(!CheckNull.isEmpty(filter.getV_date())){
                      flfields.add(new FilterField(getCond(flfields)+ "v_date=?",filter.getV_date()));
              }
              if(!CheckNull.isEmpty(filter.getStatus())){
                      flfields.add(new FilterField(getCond(flfields)+ "status=?",filter.getStatus()));
              }

              flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

                return flfields;
        }


        public static int getCount(Client_mass_opening_fileFilter filter)  {

            Connection c = null;
            int n = 0;
            List<FilterField> flFields =getFilterFields(filter);
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT count(*) ct FROM Client_mass_opening_file ");
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



        public static List<Client_mass_opening_file> getClient_mass_opening_filesFl(int pageIndex, int pageSize, Client_mass_opening_fileFilter filter)  {

                List<Client_mass_opening_file> list = new ArrayList<Client_mass_opening_file>();
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
                                list.add(new Client_mass_opening_file(
                                                rs.getInt("id"),
                                                rs.getString("file_name"),
                                                rs.getString("sender"),
                                                rs.getString("v_date"),
                                                rs.getString("status")));
                        }
                } catch (SQLException e) {
                        e.printStackTrace();

                } finally {
                        ConnectionPool.close(c);
                }
                return list;

        }

        public static HashMap<String, String> getHState(String alias){
            return com.is.utils.RefDataService.getHRefData("select distinct id data ,status label from client_mass_opening_status",alias);
        }
        public Client_mass_opening_file getClient_mass_opening_file(int client_mass_opening_fileId) {

                Client_mass_opening_file client_mass_opening_file = new Client_mass_opening_file();
                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        PreparedStatement ps = c.prepareStatement("SELECT * FROM client_mass_opening_file WHERE id=?");
                        ps.setInt(1, client_mass_opening_fileId);
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                                client_mass_opening_file = new Client_mass_opening_file();
                                
                                client_mass_opening_file.setId(rs.getInt("id"));
                                client_mass_opening_file.setFile_name(rs.getString("file_name"));
                                client_mass_opening_file.setSender(rs.getString("sender"));
                                client_mass_opening_file.setV_date(rs.getString("v_date"));
                                client_mass_opening_file.setStatus(rs.getString("status"));
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        ConnectionPool.close(c);
                }
                return client_mass_opening_file;
        }

        public static Client_mass_opening_file create(Client_mass_opening_file client_mass_opening_file)  {

                Connection c = null;
                PreparedStatement ps = null;
                try {
                        c = ConnectionPool.getConnection();
                        ps = c.prepareStatement("SELECT SQ_client_mass_opening_file.NEXTVAL id FROM DUAL");
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                                client_mass_opening_file.setId(rs.getInt("id"));
                        }
                        ps = c.prepareStatement("INSERT INTO client_mass_opening_file (id, file_name, sender, v_date, status ) VALUES (?,?,?,?,?,)");
                        
                        ps.setInt(1,client_mass_opening_file.getId());
                        ps.setString(2,client_mass_opening_file.getFile_name());
                        ps.setString(3,client_mass_opening_file.getSender());
                        ps.setString(4,client_mass_opening_file.getV_date());
                        ps.setString(5,client_mass_opening_file.getStatus());
                        ps.executeUpdate();
                        c.commit();
                } catch (Exception e) {
                        e.printStackTrace();

                } finally {
                        ConnectionPool.close(c);
                }
                return client_mass_opening_file;
        }

        public static void update(Client_mass_opening_file client_mass_opening_file)  {

                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        PreparedStatement ps = c.prepareStatement("UPDATE client_mass_opening_file SET id=?, file_name=?, sender=?, v_date=?, status=?  WHERE id=?");
                        
                        ps.setInt(1,client_mass_opening_file.getId());
                        ps.setString(2,client_mass_opening_file.getFile_name());
                        ps.setString(3,client_mass_opening_file.getSender());
                        ps.setString(4,client_mass_opening_file.getV_date());
                        ps.setString(5,client_mass_opening_file.getStatus());
                        ps.executeUpdate();
                        c.commit();
                } catch (SQLException e) {
                        e.printStackTrace();

                } finally {
                        ConnectionPool.close(c);
                }

        }

        public static void remove(Client_mass_opening_file client_mass_opening_file)  {

                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        PreparedStatement ps = c.prepareStatement("DELETE FROM client_mass_opening_file WHERE id=?");
                        ps.setLong(1, client_mass_opening_file.getId());
                        ps.executeUpdate();
                        c.commit();
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        ConnectionPool.close(c);
                }
        }
        public List<СlientResident> getClient_mass_opening_clientResident(int file_id) {
    		System.out.println("File_id: " + file_id);
    		List<СlientResident> clientResident = new ArrayList<СlientResident>();
    		Connection c = null;
    	
    		try {
    			c = ConnectionPool.getConnection();
    			PreparedStatement ps = c.prepareStatement("SELECT * FROM client_mass_opening_resident where file_id=?");
    			ps.setInt(1, file_id);
    			ResultSet rs = ps.executeQuery();

    			while (rs.next()) {
    				СlientResident resident = new СlientResident();
    				
    						
    				resident.setPinfl(rs.getString("PINFL"));    						
    				resident.setLastname(	rs.getString("LASTNAME"));
    				resident.setDate_birth(	rs.getString("DATE_BIRTH"));
    				resident.setCode_organization(rs.getString("CODE_ORGANIZATION"));
    				resident.setCard_type(rs.getString("CARD_TYPE"));
    				resident.setPhone(	rs.getString("PHONE"));
    				resident.setStatus(	rs.getString("STATUS"));
    				resident.setResponce(	rs.getString("RESPONCE"));
    				resident.setPassport_series(rs.getString("PASSPORT_SERIES"));
    				resident.setPassport_number(rs.getString("PASSPORT_NUMBER"));
    				clientResident.add(resident);
    				    			
    			} // while
    		} catch (Exception e) {
    			e.printStackTrace();
    		} finally {
    			ConnectionPool.close(c);
    		}
    		return clientResident;
    	}
        public List<ClientNotResident> getClient_mass_opening_clientNotResident(int file_id) {
    		List<ClientNotResident> clientNotResident = new ArrayList<ClientNotResident>();
    		Connection c = null;
    		try {
    			c = ConnectionPool.getConnection();
    			PreparedStatement ps = c.prepareStatement("SELECT * FROM client_mass_opening_not_resident where file_id=?");
    			ps.setInt(1, file_id);
    			ResultSet rs = ps.executeQuery();

    			while (rs.next()) {
    				ClientNotResident clientNotResidents = new ClientNotResident();    			
    				clientNotResidents.setPinfl(rs.getString("PINFL"));
    				clientNotResidents.setFirstname( rs.getString("FIRSTNAME"));
    				clientNotResidents.setLastname(rs.getString("LASTNAME"));	 
    				clientNotResidents.setPatronymic(rs.getString("PATRONYMIC")); 
    				clientNotResidents.setNationality(rs.getString("NATIONALITY"));   
    				clientNotResidents.setCitizenship(rs.getString("CITIZENSHIP"));
    				clientNotResidents.	setPassport_serial(rs.getString("PASSPORT_SERIAL"));
    				clientNotResidents.	setPassport_number(rs.getString("PASSPORT_NUMBER"));
    				clientNotResidents.setDate_birth(rs.getString("DATE_BIRTH"));
    				clientNotResidents.	setCode_gender(rs.getString("CODE_GENDER"));
    				clientNotResidents.setBirth_place(rs.getString("BIRTH_PLACE"));
    				clientNotResidents.setIssued_by(rs.getString("ISSUED_BY")); 
    				clientNotResidents.setDate_issue	(rs.getString("DATE_ISSUE"));
    				clientNotResidents.setValidity_expire(	rs.getString("VALIDITY_EXPIRE"));
    				clientNotResidents.setOrganization_code(rs.getString("ORGANIZATION_CODE")); 
    				clientNotResidents.setType_card(	rs.getString("TYPE_CARD"));
    				clientNotResidents.setPhone	(rs.getString("PHONE"));
    				clientNotResidents.setRegion(rs.getString("REGION"));
    				clientNotResidents.setDistrict(	rs.getString("DISTRICT")); 
    				clientNotResidents.setAddress(	rs.getString("ADDRESS"));
    				clientNotResidents.setStatus(	rs.getString("STATUS"));
    				clientNotResidents.setResponce(	rs.getString("RESPONCE"));
    						clientNotResident.add(clientNotResidents);
    				

    			} // while
    		} catch (Exception e) {
    			e.printStackTrace();
    		} finally {
    			ConnectionPool.close(c);
    		}
    		return clientNotResident;
    	}
        
    	public void updateStatus(int id_file, String status) {

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
}
