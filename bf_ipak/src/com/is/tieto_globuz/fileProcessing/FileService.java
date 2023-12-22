package com.is.tieto_globuz.fileProcessing;

import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.tieto_globuz.terminals.Terminal;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class FileService {

    private static String psql1 = "SELECT t.* FROM(SELECT t.*, ROWNUM rwnm FROM (SELECT * FROM (";
    private static String psql2 = " ) s ) t WHERE ROWNUM <= ? order by id desc) t WHERE t.rwnm >= ?";
    private static String msql = "SELECT * FROM v_empc_files";
    
    private static SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

    public List<File> getFile() {

    	List<File> list = new ArrayList<File>();
    	Connection c = null;

    	try {
    		c = ConnectionPool.getConnection();
    		Statement s = c.createStatement();
    		ResultSet rs = s.executeQuery("SELECT * FROM v_empc_files");
    		
    		while (rs.next()) {
    			list.add(new File(
    				rs.getLong("id"),
    				rs.getLong("fr_file_id"),
    				rs.getInt("file_type_id"),
    				rs.getInt("state_id"),
    				rs.getString("file_name"),
    				rs.getDate("file_date"),
    				rs.getString("file_state_name")));
    		}
    		} catch (SQLException e) {
    			e.printStackTrace();
    		} finally {
    			ConnectionPool.close(c);
    	}
    	
    	return list;
    }   
    
    public static File getFile(long fileId) {

    	File file = new File();
    	Connection c = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;

    	try {
    		c = ConnectionPool.getConnection();
    		ps = c.prepareStatement("SELECT * FROM v_empc_files WHERE id = ?");
    		ps.setLong(1, fileId);
    		rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			file.setId(rs.getLong("id"));
    			file.setFr_file_id(rs.getLong("fr_file_id"));
    			file.setFile_type_id(rs.getInt("file_type_id"));
    			file.setState_id(rs.getInt("state_id"));
    			file.setFile_name(rs.getString("file_name"));
    			file.setFile_date(rs.getDate("file_date"));
    			file.setFile_state_name(rs.getString("file_state_name"));
    		}
    		} catch (SQLException e) {
    			e.printStackTrace();
    		} finally {
    			ConnectionPool.close(c);
    	}
    	
    	return file;
    }  
    

    private static String getCond(List<FilterField> flfields) {
    	if(flfields.size() > 0) {
    		return " and ";
    	} else {
    		return " where ";
    	}
    }
    

    private static List<FilterField> getFilterFields(FileFilter filter) {
    	List<FilterField> flfields  =  new ArrayList<FilterField>();

    	if(!CheckNull.isEmpty(filter.getId())) {
    		flfields.add(new FilterField(getCond(flfields) + "id = ?", filter.getId()));
    	}
    	if(!CheckNull.isEmpty(filter.getFr_file_id())) {
    		flfields.add(new FilterField(getCond(flfields) + "fr_file_id = ?", filter.getFr_file_id()));
    	}
    	if(!CheckNull.isEmpty(filter.getFile_type_id())) {
    		flfields.add(new FilterField(getCond(flfields) + "file_type_id = ?", filter.getFile_type_id()));
    	}
    	if(!CheckNull.isEmpty(filter.getState_id())) {
    		flfields.add(new FilterField(getCond(flfields) + "state_id = ?", filter.getState_id()));
    	}
    	if(!CheckNull.isEmpty(filter.getFile_name())) {
    		flfields.add(new FilterField(getCond(flfields) + "file_name = ?", filter.getFile_name()));
    	}
    	if(!CheckNull.isEmpty(filter.getFile_date())) {
    		flfields.add(new FilterField(getCond(flfields) + "trunc(file_date) = ?", df.format(filter.getFile_date())));
    	}
    	if(!CheckNull.isEmpty(filter.getFile_state_name())) {
    		flfields.add(new FilterField(getCond(flfields) + "file_state_name = ?", filter.getFile_state_name()));
    	}

    	flfields.add(new FilterField(getCond(flfields) + "rownum < ?", 1001));

    	return flfields;
    }


    public static int getCount(FileFilter filter)  {

    	Connection c = null;
    	int n = 0;
    	List<FilterField> flFields = getFilterFields(filter);
    	StringBuffer sql = new StringBuffer();
    	sql.append("SELECT count(*) ct FROM empc_files");
    	
    	if(flFields.size() > 0) {
    		for(int i = 0; i < flFields.size(); i++) {
    			sql.append(flFields.get(i).getSqlwhere());
    		}
    	}
    	try {
    		c = ConnectionPool.getConnection();
    		PreparedStatement ps = c.prepareStatement(sql.toString());

    		for(int k = 0; k < flFields.size(); k++) {
    			ps.setObject(k + 1, flFields.get(k).getColobject());
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


    public static List<File> getFilesFl(int pageIndex, int pageSize, FileFilter filter) {

    	List<File> list = new ArrayList<File>();
    	Connection c = null;
    	int v_lowerbound = pageIndex + 1;
    	int v_upperbound = v_lowerbound + pageSize - 1;
    	int params;
    	List<FilterField> flFields = getFilterFields(filter);

    	StringBuffer sql = new StringBuffer();
    	sql.append(psql1);
    	sql.append(msql);
    	
    	if(flFields.size() >0 ) {
    		for(int i = 0; i < flFields.size(); i++) {
    			sql.append(flFields.get(i).getSqlwhere());
    		}
    	}
    	sql.append(psql2);
    	
    	System.out.println(sql.toString());


    	try {
    		c = ConnectionPool.getConnection();
    		PreparedStatement ps = c.prepareStatement(sql.toString());
    		
    		for(params = 0; params < flFields.size(); params++) {
    			ps.setObject(params + 1, flFields.get(params).getColobject());
    		}
    		params++;
    		ps.setInt(params++, v_upperbound);
    		ps.setInt(params++, v_lowerbound);

    		ResultSet rs = ps.executeQuery();
    		while (rs.next()) {
    			list.add(new File(
    				rs.getLong("id"),
    				rs.getLong("fr_file_id"),
    				rs.getInt("file_type_id"),
    				rs.getInt("state_id"),
    				rs.getString("file_name"),
    				rs.getDate("file_date"),
    				rs.getString("file_state_name")));
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		ConnectionPool.close(c);
    	}
    	
    	return list;
    }


    public static void updateState_id(long id, int state_id) {

    	Connection c = null;

    	try {
    		c = ConnectionPool.getConnection();
    		PreparedStatement ps = c.prepareStatement("UPDATE empc_files SET state_id = ?, WHERE id = ?");
    		ps.setInt(1, state_id);
    		ps.setLong(2, id);
    		ps.executeUpdate();
    		
    		c.commit();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		ConnectionPool.close(c);
    	}
    }

    
    public static String getRecordStateName(int id) {
    	String recordStateName = "";
    	Connection c = null;

    	try {
    		c = ConnectionPool.getConnection();
    		PreparedStatement ps = c.prepareStatement("SELECT name FROM empc_expt_records_states WHERE id = ?");
    		ps.setInt(1, id);
    		ResultSet rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			recordStateName = rs.getString("name");
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		ConnectionPool.close(c);
    	}
    	
    	return recordStateName;
    }
    
    public static String getRecordErrorText(long id) {
    	String recordErrorText = "Ошибок нет";
    	Connection c = null;

    	try {
    		c = ConnectionPool.getConnection();
    		PreparedStatement ps = c.prepareStatement("SELECT text FROM empc_expt_records_prc_errors WHERE record_id = ?");
    		ps.setLong(1, id);
    		ResultSet rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			recordErrorText = rs.getString("text");
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		ConnectionPool.close(c);
    	}
    	
    	return recordErrorText;
    }


    public static EmpcExptRecord getEmpcExptRecord(long recordId) {

    	EmpcExptRecord empcExptRecord = new EmpcExptRecord();
    	Connection c = null;

    	try {
    		c = ConnectionPool.getConnection();
    		PreparedStatement ps = c.prepareStatement("SELECT * FROM empc_expt_records WHERE id = ?");
    		ps.setLong(1, recordId);
    		ResultSet rs = ps.executeQuery();
    		
    		if (rs.next()) {
    			empcExptRecord = new EmpcExptRecord();

    			empcExptRecord.setId(rs.getLong("id"));
    			empcExptRecord.setEmpc_file_id(rs.getLong("empc_file_id"));
    			empcExptRecord.setState_id(rs.getDouble("state_id"));
    			empcExptRecord.setRecord_type(rs.getString("record_type"));
    			empcExptRecord.setLine_number(rs.getDouble("line_number"));
    			empcExptRecord.setClient(rs.getString("client"));
    			empcExptRecord.setCard_acct(rs.getString("card_acct"));
    			empcExptRecord.setAccnt_ccy(rs.getString("accnt_ccy"));
    			empcExptRecord.setCard(rs.getString("card"));
    			empcExptRecord.setSlip_nr(rs.getString("slip_nr"));
    			empcExptRecord.setRef_number(rs.getString("ref_number"));
    			empcExptRecord.setTran_date_time(rs.getDate("tran_date_time"));
    			empcExptRecord.setRec_date(rs.getDate("rec_date"));
    			empcExptRecord.setPost_date(rs.getDate("post_date"));
    			empcExptRecord.setDeal_desc(rs.getString("deal_desc"));
    			empcExptRecord.setTran_type(rs.getString("tran_type"));
    			empcExptRecord.setDeb_cred(rs.getString("deb_cred"));
    			empcExptRecord.setTran_ccy(rs.getString("tran_ccy"));
    			empcExptRecord.setTran_amt(rs.getLong("tran_amt"));
    			empcExptRecord.setAccnt_amt(rs.getLong("accnt_amt"));
    			empcExptRecord.setTerminal(rs.getString("terminal"));
    			empcExptRecord.setMcc_code(rs.getString("mcc_code"));
    			empcExptRecord.setMerchant(rs.getString("merchant"));
    			empcExptRecord.setAbvr_name(rs.getString("abvr_name"));
    			empcExptRecord.setCountry(rs.getString("country"));
    			empcExptRecord.setCity(rs.getString("city"));
    			empcExptRecord.setProc_id(rs.getLong("proc_id"));
    			empcExptRecord.setInternal_no(rs.getLong("internal_no"));
    			empcExptRecord.setProduct(rs.getString("product"));
    			empcExptRecord.setIss_mfo(rs.getString("iss_mfo"));
    			empcExptRecord.setTerm_id(rs.getString("term_id"));
    			empcExptRecord.setTranz_acct(rs.getString("tranz_acct"));
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		ConnectionPool.close(c);
    	}
    	
    	return empcExptRecord;
    }
    
    public static List<EmpcExptRecord> getFileRecords(long empc_file_id) {
    	
    	List<EmpcExptRecord> list = new ArrayList<EmpcExptRecord>();
    	Connection c = null;
    	
       	try {
    		c = ConnectionPool.getConnection();
    		PreparedStatement ps = c.prepareStatement(
				"SELECT r.id, r.state_id, r.slip_nr, r.tran_amt, r.mcc_code, r.merchant, r.term_id " +
				"FROM empc_expt_records r WHERE empc_file_id = ?");
    		ps.setLong(1, empc_file_id);
    		ResultSet rs = ps.executeQuery();
    		
			while (rs.next())
			{
				list.add(new EmpcExptRecord(
					rs.getLong("id"),
					rs.getDouble("state_id"),
					rs.getString("slip_nr"),
					rs.getLong("tran_amt"),					
					rs.getString("mcc_code"),
					rs.getString("merchant"),
					rs.getString("term_id")
					));
			}
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		ConnectionPool.close(c);
    	}
    	
    	return list;
    }
    
    public static String customFormat(String pattern, double value)
    {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String output = myFormatter.format(value);
        return output;
    }
    
    public static String getTietoDirectory(long id) {
    	String tietoDir = "";
    	Connection c = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;

    	try {
    		c = ConnectionPool.getConnection();
    		ps = c.prepareStatement("SELECT dir FROM fr_files_directory WHERE id = ?");
    		ps.setLong(1, id);
    		rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			tietoDir = rs.getString("dir");
    		}
    	} 
    	catch (SQLException e) {
    		e.printStackTrace();
    	} 
    	finally {
    		ConnectionPool.close(c);
    	}
    	
    	return tietoDir;
    }
    
    public static Long getFileState(long fileId) {
    	long state = 1l;
    	Connection c = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	
    	try {
    		c = ConnectionPool.getConnection();
    		ps = c.prepareStatement("SELECT f.state_id FROM empc_files f WHERE f.id = ?");
    		ps.setLong(1, fileId);
    		rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			state = rs.getLong("state_id");
    		}
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	finally {
    		ConnectionPool.close(c);
    	}
    	
    	return state;
    }
    
    public static Long getRecordState(long recordId) {
    	long state = 1l;
    	Connection c = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	
    	try {
    		c = ConnectionPool.getConnection();
    		ps = c.prepareStatement("SELECT r.state_id FROM empc_expt_records r WHERE r.id = ?");
    		ps.setLong(1, recordId);
    		rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			state = rs.getLong("state_id");
    		}
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	finally {
    		ConnectionPool.close(c);
    	}
    	
    	return state;
    }
    
    public static com.is.file_reciever_srv.recievers.EMPC.EXPT.EXPT_record getEXPT_record(long recordId) {
    	com.is.file_reciever_srv.recievers.EMPC.EXPT.EXPT_record exptRecord = null;    	
    	Connection c = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;

    	try {
    		c = ConnectionPool.getConnection();
    		ps = c.prepareStatement("SELECT * FROM empc_expt_records WHERE id = ?");
    		ps.setLong(1, recordId);
    		rs = ps.executeQuery();
    		
    		if (rs.next()) {
    			exptRecord = new com.is.file_reciever_srv.recievers.EMPC.EXPT.EXPT_record();

    			exptRecord.setId(rs.getLong("id"));
    			exptRecord.setEmpc_file_id(rs.getLong("empc_file_id"));
    			exptRecord.setState_id(rs.getInt("state_id"));
    			exptRecord.setRecord_type(rs.getString("record_type"));
    			exptRecord.setLine_number(rs.getLong("line_number"));
    			exptRecord.setClient(rs.getString("client"));
    			exptRecord.setCard_acct(rs.getString("card_acct"));
    			exptRecord.setAccnt_ccy(rs.getString("accnt_ccy"));
    			exptRecord.setCard(rs.getString("card"));
    			exptRecord.setSlip_nr(rs.getString("slip_nr"));
    			exptRecord.setRef_number(rs.getString("ref_number"));
    			exptRecord.setTran_date_time(rs.getDate("tran_date_time"));
    			exptRecord.setRec_date(rs.getDate("rec_date"));
    			exptRecord.setPost_date(rs.getDate("post_date"));
    			exptRecord.setDeal_desc(rs.getString("deal_desc"));
    			exptRecord.setTran_type(rs.getString("tran_type"));
    			exptRecord.setDeb_cred(rs.getString("deb_cred"));
    			exptRecord.setTran_ccy(rs.getString("tran_ccy"));
    			exptRecord.setTran_amt(rs.getLong("tran_amt"));
    			exptRecord.setAccnt_amt(rs.getLong("accnt_amt"));
    			exptRecord.setTerminal(rs.getString("terminal"));
    			exptRecord.setMcc_code(rs.getString("mcc_code"));
    			exptRecord.setMerchant(rs.getString("merchant"));
    			exptRecord.setAbvr_name(rs.getString("abvr_name"));
    			exptRecord.setCountry(rs.getString("country"));
    			exptRecord.setCity(rs.getString("city"));
    			exptRecord.setProc_id(rs.getLong("proc_id"));
    			exptRecord.setInternal_no(rs.getLong("internal_no"));
    			exptRecord.setProduct(rs.getLong("product"));
    			exptRecord.setIss_mfo(rs.getString("iss_mfo"));
    			exptRecord.setTerm_id(rs.getString("term_id"));
    			exptRecord.setTranz_acct(rs.getString("tranz_acct"));
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		ConnectionPool.close(c);
    	}
    	
    	return exptRecord;
    }
   
}