package com.is.tieto_visa.fileProcessing;

import com.is.ConnectionPool;
import com.is.ISLogger;
//import com.is.file_reciever_srv.recievers.EMPC.EXPT.EXPT_record;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class FileService {

	private static String psql1 = "SELECT t.* FROM(SELECT t.*, ROWNUM rwnm FROM (SELECT * FROM (";
    private static String psql2 = " ) s ) t WHERE ROWNUM <= ? order by id desc) t WHERE t.rwnm >= ?";
    private static String msql = "select * from (select * from v_visa_files order by id desc)";
    private static SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
    public static PreparedStatement ps_setparam=null;
    public FileService() {
    }

    public List<File> getFile() {
        List<File> list = new ArrayList();
        Connection c = null;

        try {
            c = ConnectionPool.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select * from v_visa_files");

            while(rs.next()) {
                list.add(new File(rs.getLong("id"), rs.getLong("fr_file_id"), rs.getInt("file_type_id"), rs.getInt("state_id"), rs.getString("file_name"), rs.getDate("file_date"), rs.getString("file_state_name")));
            }
        } catch (SQLException var8) {
            var8.printStackTrace();
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
            ps = c.prepareStatement("SELECT * FROM v_visa_files WHERE id = ?");
            ps.setLong(1, fileId);
            
            System.out.println("fileId:"+fileId);
            rs = ps.executeQuery();
            if (rs.next()) {
                file.setId(rs.getLong("id"));
                file.setFr_file_id(rs.getLong("fr_file_id"));
                file.setFile_type_id(rs.getInt("file_type_id"));
                file.setState_id(rs.getInt("state_id"));
                file.setFile_name(rs.getString("file_name"));
                file.setFile_date(rs.getDate("file_date"));
                file.setFile_state_name(rs.getString("file_state_name"));
            }
        } catch (SQLException var10) {
            var10.printStackTrace();
        } finally {
            ConnectionPool.close(c);
        }

        return file;
    }

    private static String getCond(List<FilterField> flfields) {
        return flfields.size() > 0 ? " and " : " where ";
    }

    private static List<FilterField> getFilterFields(FileFilter filter) {
        List<FilterField> flfields = new ArrayList();
        if (!CheckNull.isEmpty(filter.getId())) {
            flfields.add(new FilterField(getCond(flfields) + "id = ?", filter.getId()));
        }

        if (!CheckNull.isEmpty(filter.getFr_file_id())) {
            flfields.add(new FilterField(getCond(flfields) + "fr_file_id = ?", filter.getFr_file_id()));
        }

        if (!CheckNull.isEmpty(filter.getFile_type_id())) {
            flfields.add(new FilterField(getCond(flfields) + "file_type_id = ?", filter.getFile_type_id()));
        }

        if (!CheckNull.isEmpty(filter.getState_id())) {
            flfields.add(new FilterField(getCond(flfields) + "state_id = ?", filter.getState_id()));
        }

        if (!CheckNull.isEmpty(filter.getFile_name())) {
            flfields.add(new FilterField(getCond(flfields) + "file_name = ?", filter.getFile_name()));
        }

        if (!CheckNull.isEmpty(filter.getFile_date())) {
            flfields.add(new FilterField(getCond(flfields) + "trunc(file_date) = ?", df.format(filter.getFile_date())));
        }

        if (!CheckNull.isEmpty(filter.getFile_state_name())) {
            flfields.add(new FilterField(getCond(flfields) + "file_state_name = ?", filter.getFile_state_name()));
        }

        //flfields.add(new FilterField(getCond(flfields) + "rownum < ?", 1001));
        return flfields;
    }

    public static int getCount(FileFilter filter) {
        Connection c = null;
        int n = 0;
        List<FilterField> flFields = getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM visa_files");
        if (flFields.size() > 0) {
            for(int i = 0; i < flFields.size(); ++i) {
                sql.append(((FilterField)flFields.get(i)).getSqlwhere());
            }
        }

        try {
            c = ConnectionPool.getConnection();
            System.out.println("SQL 77777"+sql.toString());
            PreparedStatement ps = c.prepareStatement(sql.toString());

            for(int k = 0; k < flFields.size(); ++k) {
                ps.setObject((k + 1), ((FilterField)flFields.get(k)).getColobject());
            	
                System.out.println("index:"+(k + 1)+" value:"+((FilterField)flFields.get(k)).getColobject());
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                n = rs.getInt(1);
            }
        } catch (SQLException var10) {
            var10.printStackTrace();
        } finally {
            ConnectionPool.close(c);
        }

        return n;
    }

    public static List<File> getFilesFl(int pageIndex, int pageSize, FileFilter filter) {
        List<File> list = new ArrayList();
        Connection c = null;
        int v_lowerbound = pageIndex + 1;
        int v_upperbound = v_lowerbound + pageSize - 1;
        List<FilterField> flFields = getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append(psql1);
        sql.append(msql);
     
        if (flFields.size() > 0) {
            for(int i = 0; i < flFields.size(); ++i) {
                sql.append(((FilterField)flFields.get(i)).getSqlwhere());
            }
        }

        sql.append(psql2);
        System.out.println(sql.toString());

        try {
            c = ConnectionPool.getConnection();
            PreparedStatement ps = c.prepareStatement(sql.toString());

            int params;
            for(params = 0; params < flFields.size(); ++params) {
                ps.setObject(params + 1, ((FilterField)flFields.get(params)).getColobject());
                System.out.println("Param "+params+"   "+flFields.get(params).getColobject());
            }

            ++params;
            ps.setInt(params++, v_upperbound);
            ps.setInt(params++, v_lowerbound);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                list.add(new File(rs.getLong("id"), rs.getLong("fr_file_id"), rs.getInt("file_type_id"), rs.getInt("state_id"), rs.getString("file_name"), rs.getDate("file_date"), rs.getString("file_state_name")));
                System.out.println("Record "+rs.getString("file_name"));
            }
        } catch (SQLException var15) {
            var15.printStackTrace();
        } finally {
            ConnectionPool.close(c);
        }

        return list;
    }

    public static void updateState_id(long id, int state_id) {
        Connection c = null;

        try {
            c = ConnectionPool.getConnection();
            PreparedStatement ps = c.prepareStatement("UPDATE visa_files SET state_id = ?, WHERE id = ?");
            ps.setInt(1, state_id);
            ps.setLong(2, id);
            ps.executeUpdate();
            c.commit();
        } catch (SQLException var8) {
            var8.printStackTrace();
        } finally {
            ConnectionPool.close(c);
        }

    }

    public static String getRecordStateName(String id) {
        String recordStateName = "";
        Connection c = null;

        try {
        	
        	System.out.println("Stat id: fileService line 220:"+id);
        	  c = ConnectionPool.getConnection();	
            PreparedStatement ps = c.prepareStatement("SELECT name FROM visa_expt_records_states WHERE id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                recordStateName = rs.getString("name");
            }
            
        	
        } catch (SQLException var8) {
            var8.printStackTrace();
        } finally {
            ConnectionPool.close(c);
        }

        return recordStateName;
    }

    public static EmpcExptRecord getBRecord(long recordId) {
        EmpcExptRecord empcExptRecord = new EmpcExptRecord();
        Connection c = null;

        try {
        	
        	System.out.println("getBRecord fileService line 284");
            c = ConnectionPool.getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM empc_expt_records WHERE id = ?");
            ps.setLong(1, recordId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	
            	if(!rs.getString("tran_type").equals("110")){
            	
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
            }
        } catch (Exception var9) {
            var9.printStackTrace();
        } finally {
            ConnectionPool.close(c);
        }

        return empcExptRecord;
    }
       
    public static EmpcExptRecord getEmpcExptRecord(long recordId) {
        EmpcExptRecord empcExptRecord = new EmpcExptRecord();
        Connection c = null;

        try {
            c = ConnectionPool.getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM visa_expt_records WHERE id = ? ");
            ps.setLong(1, recordId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	
            	
            	if(!rs.getString("tran_type").equals("110")){
            		
            		
                empcExptRecord = new EmpcExptRecord();
                empcExptRecord.setId(rs.getLong("id"));
                empcExptRecord.setEmpc_file_id(rs.getLong("visa_file_id"));
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
                
                empcExptRecord.setSettl_cmi(rs.getString("settl_cmi"));
                empcExptRecord.setSettl_date(rs.getDate("settl_date"));
                empcExptRecord.setSend_cmi(rs.getString("send_cmi"));
                empcExptRecord.setTran_name_uk(rs.getString("tran_name_uk"));
                empcExptRecord.setCounterparty(rs.getString("counterparty"));
                empcExptRecord.setCountry_code(rs.getString("country_code"));
                empcExptRecord.setAmount_acc(rs.getLong("amount_acc"));
                empcExptRecord.setTr_fee_code(rs.getString("tr_fee_code"));
                empcExptRecord.setTr_fee_amt(rs.getLong("tr_fee_amt"));
                empcExptRecord.setI_amount(rs.getLong("i_amount"));
                empcExptRecord.setSb_amount(rs.getLong("sb_amount"));
                empcExptRecord.setBank_code(rs.getString("bank_code"));
                empcExptRecord.setBranch(rs.getString("branch"));
                empcExptRecord.setPointcode(rs.getString("pointcode"));

            	}
            }
        } catch (Exception var9) {
            var9.printStackTrace();
        } finally {
            ConnectionPool.close(c);
        }

        return empcExptRecord;
    }
    
    public static EmpcExptRecord getTransfersFileRecord(long recordId) {
        EmpcExptRecord empcExptRecord = new EmpcExptRecord();
        Connection c = null;

        try {
            c = ConnectionPool.getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM way_transfer_records WHERE id = ? ");
            ps.setLong(1, recordId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
	
                //empcExptRecord = new EmpcExptRecord();
                empcExptRecord.setId(rs.getLong("id"));
                empcExptRecord.setEmpc_file_id(rs.getLong("visa_file_id"));
                empcExptRecord.setState_id(rs.getDouble("state_id"));
                
                empcExptRecord.setRecord_type(rs.getString("record_type"));
                empcExptRecord.setLine_number(rs.getDouble("line_number"));

                empcExptRecord.setMerchant(rs.getString("grouprefn"));
                empcExptRecord.setAbvr_name(rs.getString("synthrefn"));
                empcExptRecord.setCard(rs.getString("synthcode"));
                empcExptRecord.setTr_code(rs.getString("transferdescription"));
                empcExptRecord.setTerminal(rs.getString("analyticrefn"));
                empcExptRecord.setMcc(rs.getString("cre_analyticaccount"));
                empcExptRecord.setTran_type(rs.getString("cre_syntaccount"));
                empcExptRecord.setAccnt_amt(rs.getLong("cre_amount"));
                empcExptRecord.setProc_id(rs.getLong("cre_currency"));
                empcExptRecord.setTranz_acct(rs.getString("deb_analyticaccount"));
                empcExptRecord.setCard_acct(rs.getString("deb_syntaccount"));
                empcExptRecord.setAmount(rs.getLong("deb_amount"));
                empcExptRecord.setAccnt_ccy(rs.getString("deb_currency"));
                empcExptRecord.setTran_date_time(rs.getDate("postingdate"));
                empcExptRecord.setSlip_nr(rs.getString("docrefset_parmcode"));
                empcExptRecord.setRef_number(rs.getString("docrefset_parmvalue"));
                empcExptRecord.setRec_date(rs.getDate("localdt"));
                empcExptRecord.setDeal_desc(rs.getString("description"));
                empcExptRecord.setTran_ccy(rs.getString("amountdata_currency"));
                empcExptRecord.setTran_amt(rs.getLong("amountdata_amount"));
                            	
            }
        } catch (Exception var9) {
            var9.printStackTrace();
        } finally {
            ConnectionPool.close(c);
        }

        return empcExptRecord;
    }
    public static EmpcExptRecord getVisaOnusRecord(long recordId) {
        EmpcExptRecord empcExptRecord = new EmpcExptRecord();
        Connection c = null;

        try {
            c = ConnectionPool.getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM visa_onus_records WHERE id = ? ");
            ps.setLong(1, recordId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	
            	
            	if(!rs.getString("tran_type").equals("110")){
            		
                empcExptRecord = new EmpcExptRecord();
                empcExptRecord.setId(rs.getLong("id"));
                empcExptRecord.setEmpc_file_id(rs.getLong("visa_file_id"));
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
            }
        } catch (Exception var9) {
            var9.printStackTrace();
        } finally {
            ConnectionPool.close(c);
        }

        return empcExptRecord;
    }
     
    public static Long getFileType(long empc_file_id) {
        Long fileTypeId=0L;
        Connection c = null;

        try {
            c = ConnectionPool.getConnection();
            
            PreparedStatement ps = c.prepareStatement("SELECT file_type_id FROM v_visa_files r WHERE r.id = ?");
            ps.setLong(1, empc_file_id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
            	fileTypeId=rs.getLong("file_type_id");
            }
        } catch (Exception var9) {
            var9.printStackTrace();
        } finally {
            ConnectionPool.close(c);
        }
        
        return fileTypeId;
    }

    public static List<HumoFileRecords> getFileRecords(long empc_file_id, long fileType) {
        
    	List<HumoFileRecords> list = new ArrayList();
        Connection c = null;
               
        try {
        	
        	if(fileType==1L){ //onus
                c = ConnectionPool.getConnection();
                PreparedStatement ps = c.prepareStatement("SELECT r.id,r.state_id,r.mcc_code,r.merchant,r.abvr_name,r.card,r.tranz_acct,r.term_id,r.mcc_code,r.tran_type,nvl(r.tran_amt,0) tran_amt,r.tran_date_time,nvl(r.tran_amt2,0) tran_amt2,nvl(r.accnt_amt,0) accnt_amt,(select e.text from visa_onus_records_prc_errors e where r.id = e.record_id) errText,s.name FROM visa_onus_records r,EMPC_FILE_STATE s WHERE r.visa_file_id = ? and r.state_id=s.id order by state_id desc");
                ps.setLong(1, empc_file_id);
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    //list.add(new HumoFileRecords(rs.getLong("id"), rs.getString("name"), rs.getString("merchant"),rs.getString("abvr_name"), rs.getString("card"), rs.getString("tranz_acct"), rs.getString("term_id"), rs.getString("mcc_code"),rs.getString("tran_type"),rs.getString("tran_amt"),rs.getString("tran_date_time"),rs.getString("errText"),rs.getString("tran_amt2")));
                    list.add(new HumoFileRecords(rs.getLong("id"), rs.getString("name"), rs.getString("merchant"),rs.getString("abvr_name"), rs.getString("card"), rs.getString("tranz_acct"), rs.getString("term_id"), rs.getString("mcc_code"),rs.getString("tran_type"),rs.getString("accnt_amt"),rs.getString("tran_date_time"),rs.getString("errText"),rs.getString("tran_amt2"),rs.getString("tran_amt")));
                }
        	}
        	else if(fileType==3L){ //iias
                c = ConnectionPool.getConnection();
                PreparedStatement ps = c.prepareStatement("SELECT r.id,r.state_id,r.mcc_code,r.merchant,r.abvr_name,r.card,r.tranz_acct,r.term_id,r.mcc_code,r.tran_type,nvl(r.tran_amt,0) tran_amt,r.tran_date_time,nvl(r.tran_amt2,0) tran_amt2,nvl(r.ACCNT_AMT,0) ACCNT_AMT, (select e.text from visa_onus_records_prc_errors e where r.id = e.record_id) errText,s.name FROM visa_onus_records r,EMPC_FILE_STATE s WHERE r.visa_file_id = ? and r.state_id=s.id order by state_id desc");
                ps.setLong(1, empc_file_id);
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    list.add(new HumoFileRecords(rs.getLong("id"), rs.getString("name"), rs.getString("merchant"),rs.getString("abvr_name"), rs.getString("card"), rs.getString("tranz_acct"), rs.getString("term_id"), rs.getString("mcc_code"),rs.getString("tran_type"),rs.getString("accnt_amt"),rs.getString("tran_date_time"),rs.getString("errText"),rs.getString("tran_amt2"),rs.getString("tran_amt")));
                }
        	}
            else if(fileType==2L || fileType==6L || fileType==9L  ){ //expt-agro(davr clearing), exd-ravnaq
                c = ConnectionPool.getConnection();
                PreparedStatement ps = c.prepareStatement("SELECT r.id,r.state_id,r.mcc_code,r.merchant,r.abvr_name,r.card,r.tranz_acct,r.term_id,r.mcc_code,r.tran_type,nvl(r.tran_amt,0) tran_amt,r.tran_date_time,nvl(r.tran_amt2,0) tran_amt2,r.client,r.card_acct,r.accnt_ccy,r.slip_nr,r.ref_number,r.rec_date,r.post_date,r.deal_desc,r.deb_cred,r.tran_ccy,nvl(r.accnt_amt,0) accnt_amt,r.terminal,r.mcc_code,r.country,r.city,r.proc_id,r.internal_no,r.product,r.iss_mfo,r.term_id,r.tran_type2,r.tr_code,r.tr_fee,r.fee,r.surcharge,r.card_cond_set,nvl(r.amount,0) amount, (select e.text from visa_expt_records_prc_errors e where r.id = e.record_id) errText,s.name FROM visa_expt_records r,EMPC_FILE_STATE s WHERE r.visa_file_id = ? and r.state_id=s.id order by state_id desc");
                ps.setLong(1, empc_file_id);
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                	HumoFileRecords tmp = new HumoFileRecords();
                	tmp.setId(rs.getLong("id"));
                	tmp.setState(rs.getString("name"));
                	tmp.setMerchant(rs.getString("merchant"));
                	tmp.setAbvr_name(rs.getString("abvr_name"));
                	tmp.setCard(rs.getString("card"));
                	tmp.setTranz_acct(rs.getString("tranz_acct"));
                	tmp.setTermId(rs.getString("term_id"));
                	tmp.setMcc(rs.getString("mcc_code"));
                	tmp.setTran_type(rs.getString("tran_type"));
                	tmp.setSumma(rs.getString("tran_amt"));
                	tmp.setTran_date_time(rs.getString("tran_date_time"));
                	tmp.setErrorText(rs.getString("errText"));
                	tmp.setTran_amt2(rs.getString("tran_amt2"));
                	
                	tmp.setClient(rs.getString("client"));
                	tmp.setCard_acct(rs.getString("card_acct"));
                	tmp.setAccnt_ccy(rs.getString("accnt_ccy"));
                	tmp.setSlip_nr(rs.getString("slip_nr"));
                	tmp.setRef_number(rs.getString("ref_number"));
                	tmp.setRec_date(rs.getString("rec_date"));
                	tmp.setPost_date(rs.getString("post_date"));
                	tmp.setDeal_desc(rs.getString("deal_desc"));
                	tmp.setDeb_cred(rs.getString("deb_cred"));
                	tmp.setTran_ccy(rs.getString("tran_ccy"));
                	tmp.setTran_amt(rs.getString("tran_amt"));
                	tmp.setAccnt_amt(rs.getString("accnt_amt"));
                	tmp.setTerminal(rs.getString("terminal"));
                	tmp.setMcc_code(rs.getString("mcc_code"));
                	tmp.setCountry(rs.getString("country"));
                	tmp.setCity(rs.getString("city"));
                	tmp.setProc_id(rs.getString("proc_id"));
                	tmp.setInternal_no(rs.getString("internal_no"));
                	tmp.setProduct(rs.getString("product"));
                	tmp.setIss_mfo(rs.getString("iss_mfo"));
                	tmp.setTerm_id(rs.getString("term_id"));
                	tmp.setTran_type2(rs.getString("tran_type2"));
                	tmp.setTr_code(rs.getString("tr_code"));
                	tmp.setTr_fee(rs.getString("tr_fee"));
                	tmp.setFee(rs.getString("fee"));
                	tmp.setSurcharge(rs.getString("surcharge"));
                	tmp.setCard_cond_set(rs.getString("card_cond_set"));
                	tmp.setAmount(rs.getString("amount"));
                	                	
                    list.add(tmp);
                }
        	}
            else if(fileType==4L){ //trbeq-ipak
        		
                c = ConnectionPool.getConnection();
                PreparedStatement ps = c.prepareStatement("SELECT r.id,r.state_id,r.mcc_code,r.merchant,r.abvr_name,r.card,r.tranz_acct,r.term_id,r.mcc_code,r.tran_type,r.tran_amt,r.tran_date_time,r.tran_amt2,nvl(r.ACCNT_AMT,0) ACCNT_AMT,(select e.text from visa_trbeq_records_prc_errors e where r.id = e.record_id) errText,s.name FROM visa_trbeq_records r,EMPC_FILE_STATE s WHERE r.visa_file_id = ? and r.state_id=s.id order by state_id desc");
                ps.setLong(1, empc_file_id);
                ResultSet rs = ps.executeQuery();

                while(rs.next()) {
                    list.add(new HumoFileRecords(rs.getLong("id"), rs.getString("name"), rs.getString("merchant"),rs.getString("abvr_name"), rs.getString("card"), rs.getString("tranz_acct"), rs.getString("term_id"), rs.getString("mcc_code"),rs.getString("tran_type"),rs.getString("accnt_amt"),rs.getString("tran_date_time"),rs.getString("errText"),rs.getString("tran_amt2"),rs.getString("tran_amt")));
                }
        	}
            else if(fileType==7L  ){ //madadbank(transfers xml file)
                c = ConnectionPool.getConnection();
                PreparedStatement ps = c.prepareStatement("SELECT r.id, r.visa_file_id, r.state_id, r.record_type, r.line_number, r.grouprefn, r.synthrefn, r.synthcode, r.transferdescription, r.analyticrefn, r.cre_analyticaccount, r.cre_syntaccount, r.cre_amount, r.cre_currency, r.deb_analyticaccount, r.deb_syntaccount, r.deb_amount, r.deb_currency, r.postingdate, r.docrefset_parmcode, r.docrefset_parmvalue, r.localdt, r.description, r.amountdata_currency, r.amountdata_amount, " +
                		"(select e.text from way_transfer_RECORDS_ERRORS e where r.id = e.record_id) errText," +
                		"s.name FROM way_transfer_records r,EMPC_FILE_STATE s " +
                		"WHERE r.visa_file_id = ? and r.state_id=s.id order by state_id desc");
     
                ps.setLong(1, empc_file_id);
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                	HumoFileRecords tmp = new HumoFileRecords();
                	tmp.setId(rs.getLong("id"));
                	tmp.setState(rs.getString("name"));
                	tmp.setLine_number(rs.getDouble("line_number"));
                	tmp.setMerchant(rs.getString("grouprefn"));
                	tmp.setAbvr_name(rs.getString("synthrefn"));
                	tmp.setCard(rs.getString("synthcode"));
                	tmp.setTranz_acct(rs.getString("deb_analyticaccount"));
                	tmp.setTerminal(rs.getString("analyticrefn"));
                	tmp.setTran_type(rs.getString("cre_syntaccount"));
                	tmp.setSumma(rs.getString("amountdata_amount"));
                	tmp.setErrorText(rs.getString("errText"));
                	tmp.setCard_acct(rs.getString("deb_syntaccount"));
                	tmp.setAccnt_ccy(rs.getString("deb_currency"));
                	tmp.setSlip_nr(rs.getString("docrefset_parmcode"));
                	tmp.setRef_number(rs.getString("docrefset_parmvalue"));
                	tmp.setRec_date(rs.getString("localdt"));
                	tmp.setTran_date_time(rs.getString("postingdate"));
                	tmp.setDeal_desc(rs.getString("description"));
                   	tmp.setTran_ccy(rs.getString("amountdata_currency"));
                	tmp.setTran_amt(rs.getString("amountdata_amount"));
                	tmp.setAccnt_amt(rs.getString("cre_amount"));
                	tmp.setMcc(rs.getString("cre_analyticaccount"));
                	tmp.setProc_id(rs.getString("cre_currency"));
                	tmp.setTerm_id(rs.getString("analyticrefn"));
                	tmp.setTr_code(rs.getString("transferdescription"));
                	tmp.setAmount(rs.getString("deb_amount"));
                	                	
                    list.add(tmp);
                }
        	}
        	else{
        		
            c = ConnectionPool.getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT r.id, r.state_id, r.mcc_code, r.merchant,r.abvr_name,r.card,r.tranz_acct, r.term_id,r.tran_type,r.tran_amt,r.tran_date_time,(select e.text from EMPC_EXPT_RECORDS_PRC_ERRORS e where r.id = e.record_id) errText,s.name,r.tran_amt2,nvl(r.ACCNT_AMT,0) ACCNT_AMT FROM empc_expt_records r,EMPC_FILE_STATE s WHERE r.empc_file_id = ? and r.state_id=s.id order by state_id desc");
            ps.setLong(1, empc_file_id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
            	if(!rs.getString("tran_type").equals("110")){
                list.add(new HumoFileRecords(rs.getLong("id"), rs.getString("name"), rs.getString("merchant"),rs.getString("abvr_name"),rs.getString("card"), rs.getString("tranz_acct"), rs.getString("term_id"), rs.getString("mcc_code"), rs.getString("tran_type"),rs.getString("accnt_amt"),rs.getString("tran_date_time"),rs.getString("errText"),rs.getString("tran_amt2"),rs.getString("tran_amt")));
               
            	}
            	}
            
        	}
        } catch (Exception var9) {
            var9.printStackTrace();
        } finally {
            ConnectionPool.close(c);
        }

        return list;
    }

    public static String customFormat(String pattern, double value) {
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
            if (rs.next()) {
                tietoDir = rs.getString("dir");
            }
        } catch (SQLException var10) {
            var10.printStackTrace();
        } finally {
            ConnectionPool.close(c);
        }

        return tietoDir;
    }

    public static Long getFileState(long fileId) {
        long state = 1L;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("SELECT f.state_id FROM visa_files f WHERE f.id = ?");
            ps.setLong(1, fileId);
            rs = ps.executeQuery();
            if (rs.next()) {
                state = rs.getLong("state_id");
            }
        } catch (SQLException var11) {
            var11.printStackTrace();
        } finally {
            ConnectionPool.close(c);
        }

        return state;
    }

    public static Long getRecordState(long recordId) {
        long state = 1L;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("SELECT r.state_id FROM visa_onus_records r WHERE r.id = ?");
            ps.setLong(1, recordId);
            rs = ps.executeQuery();
            if (rs.next()) {
                state = rs.getLong("state_id");
            }
        } catch (SQLException var11) {
            var11.printStackTrace();
        } finally {
            ConnectionPool.close(c);
        }

        return state;
    }

    public static VisaOnusRecord getEXPT_record(long recordId) {
    	VisaOnusRecord exptRecord = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("SELECT * FROM empc_expt_records WHERE id = ?");
            ps.setLong(1, recordId);
            rs = ps.executeQuery();
            if (rs.next()) {
                exptRecord = new VisaOnusRecord();
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
        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            ConnectionPool.close(c);
        }

        return exptRecord;
    }
    
    public static void setParam(Connection cMFO, String name, String value) throws SQLException{
			
		ps_setparam= cMFO.prepareStatement("{call param.setParam(?, ?)}");

		ps_setparam.setString(1, name);
		ps_setparam.setString(2, value);
		ps_setparam.execute();
		
	}
          
    public static String getAmountAgro(Long fileId,String un,String pwd,String alias,String fileName) throws SQLException{
    	   
     	Long amount = null;
     	String error="Клиринг завершился";
         Connection c = null;
         CallableStatement cs = null;
         ResultSet rs = null;
         try {
         
         	String clirInst=ConnectionPool.getValue("clir_inst");
         
         	if(clirInst.equals("clir")){
         		
         		Class.forName("oracle.jdbc.driver.OracleDriver");
    	    	c = DriverManager.getConnection(
    	                "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS_LIST = (ADDRESS = (PROTOCOL = TCP)(HOST = 172.16.10.5)(PORT = 1521)))(CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = PHBY1)))", "munis", "qazwsx123");
    			
         	}
            	else if (clirInst.equals("bank")){c = ConnectionPool.getConnection(un,pwd,alias);}
             
         	else{ISLogger.getLogger().error("clir_inst не найден");}
         	
         	
         	if(UpdateFileCliren(fileId.toString(),alias)!=0){
         		ISLogger.getLogger().error("UpdateFileCliren:"+UpdateFileCliren(fileId.toString(),alias));
         		
     	setParam(c, "PARENT_GROUP_ID", "198");
 		setParam(c, "GROUP_ID", "198");
 		setParam(c, "PARENT_DEAL_ID", "106");
 		setParam(c, "ACTION_ID", "0");
 		setParam(c, "S_DEAL_ID", "106");
 		setParam(c, "FILE_NAME", fileName);
 		       
         cs = c.prepareCall("{call HUMO_CLEARING_OPERATION.Exec_out(?)}");
         System.out.println("call HUMO_CLEARING");
         ISLogger.getLogger().error("call HUMO_CLEARING");
        cs.setLong(1, fileId);
         cs.execute();
        
         	}
         	else {
         		error="Номер файла не поменялься";
         	}
     
        
      } catch (Exception e) {
       e.printStackTrace();
        ISLogger.getLogger().error(CheckNull.getPstr(e));
        error=CheckNull.getPstr(e);
       }
      finally {
        if(c!=null){
     	   c.commit();
     	   //resetTimerConnection();
     	   ConnectionPool.close(c);
     	   }
      }
        return error;
      } 
    
    public static String getAmount(Long fileId,String un,String pwd,String alias,String fileName) throws SQLException{
   
    	Long amount = null;
    	String error="Клиринг завершился";
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
        
        	
        	
        	String clirInst=ConnectionPool.getValue("clir_inst");
   
        	if(clirInst.equals("clir")){
        		
        		
        		c = ConnectionPool.getConnection_cliring();
        		
        	
        	}
           	else if (clirInst.equals("bank")){c = ConnectionPool.getConnection(un,pwd,alias);}
            
        	else{ISLogger.getLogger().error("clir_inst не найден");}
        	
      
        	
    	setParam(c, "PARENT_GROUP_ID", "198");
		setParam(c, "GROUP_ID", "198");
		setParam(c, "PARENT_DEAL_ID", "106");
		setParam(c, "ACTION_ID", "0");
		setParam(c, "S_DEAL_ID", "106");
		setParam(c, "FILE_NAME", fileName);
		       
        cs = c.prepareCall("{call HUMO_CLEARING_OPERATION.Exec_out(?)}");
        System.out.println("call HUMO_CLEARING");
        ISLogger.getLogger().error("call HUMO_CLEARING");
       cs.setLong(1, fileId);
        cs.execute();
       
     } catch (Exception e) {
      e.printStackTrace();
       ISLogger.getLogger().error(CheckNull.getPstr(e));
       error=CheckNull.getPstr(e);
      }
     finally {
       if(c!=null){
    	   c.commit();
    	   
    	   ConnectionPool.close(c);
    	   }
     }
       return error;
     }  
       
    public static String getCountSuccessEXPT(Connection c,String empcFileId) {
        String count =null;
        
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
           
            ps = c.prepareStatement("select count(*) as c from empc_expt_records t where t.empc_file_id=? and t.state_id=2");
            ps.setString(1, empcFileId);
            rs = ps.executeQuery();
            if (rs.next()) {
            	count = rs.getString("c");
            }
        } catch (SQLException e) {
            e.printStackTrace();
         
    }
        return count;
    }

    public static String getCountSuccessB(Connection c,String empcFileId) {
        String count =null;
        
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
           
            ps = c.prepareStatement("select count(*) as c from empc_files_b_transactions t where t.empc_file_id=? and t.state_id=2");
            ps.setString(1, empcFileId);
            rs = ps.executeQuery();
            if (rs.next()) {
            	count = rs.getString("c");
            }
        } catch (SQLException e) {
            e.printStackTrace();
                   

        
    }
        return count;
    }
      
    public static String getCountCommonB(Connection c,String empcFileId) {
        String count =null;
        
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
           
            ps = c.prepareStatement("select count(*) as c from empc_files_b_transactions t where t.empc_file_id=?");
            ps.setString(1, empcFileId);
            rs = ps.executeQuery();
            if (rs.next()) {
            	count = rs.getString("c");
            }
        } catch (SQLException e) {
            e.printStackTrace();
                   

        
    }
        return count;
    }
    
    public static String getCountCommonEXPT(Connection c,String empcFileId) {
        String count =null;
        
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
           
            ps = c.prepareStatement("select count(*) as c from empc_expt_records t where t.empc_file_id=? and t.tran_type<>'110'");
            ps.setString(1, empcFileId);
            rs = ps.executeQuery();
            if (rs.next()) {
            	count = rs.getString("c");
            }
        } catch (SQLException e) {
            e.printStackTrace();
         
    }
        return count;
    }
     
    public static String getCountErrorEXPT(Connection c,String empcFileId) {
        String count =null;
        
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
           
            ps = c.prepareStatement("select count(*) as c from empc_expt_records t where t.empc_file_id=? and t.state_id=3");
            ps.setString(1, empcFileId);
            rs = ps.executeQuery();
            if (rs.next()) {
            	count = rs.getString("c");
            }
        } catch (SQLException e) {
            e.printStackTrace();
         
    }
        return count;
    }

    public static String getCountErrorB(Connection c,String empcFileId) {
        String count =null;
        
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
           
            ps = c.prepareStatement("select count(*) as c from empc_files_b_transactions t where t.empc_file_id=? and t.state_id=3");
            ps.setString(1, empcFileId);
            rs = ps.executeQuery();
            if (rs.next()) {
            	count = rs.getString("c");
            }
        } catch (SQLException e) {
            e.printStackTrace();
                   

        
    }
        return count;
    }
     
    public static String getProcessInfo(Connection c,String empcFileId,String fileTypeId) {
    
    String processInfo=null;
       
    try {
    	
    	if (fileTypeId.equals("1")){
    		processInfo="Общий: "+getCountCommonB(c, empcFileId)+" обработана: "+getCountSuccessB(c, empcFileId)+" ошибка: "+getCountErrorB(c, empcFileId);
    	}
    	else {
    		processInfo="Общий: "+getCountCommonEXPT(c, empcFileId)+" обработана: "+getCountSuccessEXPT(c, empcFileId)+" ошибка: "+getCountErrorEXPT(c, empcFileId);
    	}
    	
            
            
        } catch (Exception e) {
            e.printStackTrace();
         
    }
        return processInfo;
    }
    
	public  static String HUMO_PROCESSING(Connection c,String fileId) throws SQLException
	{
		String error=null;
		try{
			
	     System.out.println("CreateJob start FilesSERVICE LINE 908 ");
	     ISLogger.getLogger().error("CreateJob start FilesSERVICE Visa file: "+fileId);
		 CallableStatement cs = null;
	
		 //ISLogger.getLogger().error("HUMO: call info.init()");
		 //cs = c.prepareCall("{ call info.init() }");
		 cs = c.prepareCall("{call visa_PROCESSING.SepCrDoc(?)}");
		// cs = c.prepareCall("{call HUMO_PROCESSING.CreateJob(?)}");
		 cs.setString(1, fileId);
		 cs.execute();
		 System.out.println("CreateJob end FilesSERVICE LINE 913 ");
		 ISLogger.getLogger().error("CreateJob end FilesSERVICE Visa file: "+fileId);
    } catch (Exception e) {
         e.printStackTrace();
         ISLogger.getLogger().error(CheckNull.getPstr(e));
         //error=CheckNull.getPstr(e);
         error=e.getMessage();
        } 
        return error;
	}
	
    public static String getCountBState1(Connection c,String empcFileId) {
        String count =null;
        
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
           
            ps = c.prepareStatement("select count(*) as c from empc_files_b_transactions t where t.empc_file_id=? and t.state_id=1");
            ps.setString(1, empcFileId);
            rs = ps.executeQuery();
            if (rs.next()) {
            	count = rs.getString("c");
            }
        } catch (SQLException e) {
            e.printStackTrace();
                   
    }
        return count;
    }
      
    public static String getCountEXPTStat1(Connection c,String empcFileId) {
        String count =null;
        
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
           
            ps = c.prepareStatement("select count(*) as c from empc_expt_records t where t.empc_file_id=? and t.state_id=1 and t.tran_type<>'110'");
            ps.setString(1, empcFileId);
            rs = ps.executeQuery();
            if (rs.next()) {
            	count = rs.getString("c");
            }
        } catch (SQLException e) {
            e.printStackTrace();
         
    }
        return count;
    }
    
    public static boolean isProcessingFile(Connection c,String empcFileId) {
        boolean process =false;
        
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
           
            ps = c.prepareStatement("select * from user_scheduler_jobs where job_name = ?");
            ps.setString(1, "HUMO_JOB"+empcFileId);
            System.out.println("HUMO_JOB"+empcFileId);
            rs = ps.executeQuery();
            
            while (rs.next()) {
            	process =true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
         
    }
        return process;
    }
    
    public static List<RefData> getState(String branch) {
        return getRefData("select id  data,name  label from empc_file_state order by id", branch);
      }
    
    public static List<RefData> getRefData(String sql, String branch)
    {
      List list = new LinkedList();
      Connection c = null;
      try
      {
        c = ConnectionPool.getConnection(branch);
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery(sql);
        while (rs.next())
          list.add(
            new RefData(rs.getString("data"), 
            rs.getString("label")));
      }
      catch (SQLException e) {
      	
       ISLogger.getLogger().error(CheckNull.getPstr(e));
      	
        e.printStackTrace();
        
        
      } finally {
        ConnectionPool.close(c);
      }
      return list;
    }
    
    public static int UpdateFileCliren(final String file,String alias) {
      
        PreparedStatement ps = null;
         Connection c=null;
        int updeteResult=0;
        try {
        	c = ConnectionPool.getConnection(alias);
            ps = c.prepareStatement("update bank9004.sim_clir_humo set file_id=?");
            ps.setString(1, file);
            updeteResult=ps.executeUpdate();
            ISLogger.getLogger().error("UpdateFileCliren: " + file+"updated:"+updeteResult);
            c.commit();
          
        }
        catch (Exception e) {
            e.printStackTrace();
            ISLogger.getLogger().error((Object)CheckNull.getPstr(e));
            updeteResult=0;
            return updeteResult;
        }
        finally {
            //Utils.close(ps);
            ConnectionPool.close(c);
        }
        return updeteResult;
    }
    
    public static List<HumoFileRecords> getReport1(Date data_, String acc_, String branch) {
    
    	List<HumoFileRecords> list = new ArrayList();
    	List<RefData> listB = new ArrayList();
        Connection c = null;    
        PreparedStatement ps=null;
        ResultSet rs = null;
        String sql1 ="";
    	try {
    		c = ConnectionPool.getConnection();

    		sql1 = "select branch_tieto, branch_bank from bf_tieto_branches order by 1";
    		ps = c.prepareStatement(sql1);
    		rs = ps.executeQuery();
    		while(rs.next()) {
    			RefData tmp = new RefData(rs.getString("branch_tieto"), rs.getString("branch_bank"));
    			listB.add(tmp);
    		}
    		
    		sql1 = "select acc1.*, substr(dtl,10,21) file_id, substr(dtl,32,21) rec_id, "+
    			"(select card||'-'|| branch "+  
    			" from visa_onus_records  "+
    			" where visa_file_id = substr(dtl,10,21) "+
    			"  and id = substr(dtl,32,21) "+
    			"union all "+
    			" select card||'-'||iss_mfo "+ 
    			"  from visa_trbeq_records  "+
    			"  where visa_file_id= substr(dtl,10,21) "+
    			"  and id=substr(dtl,32,21)) karta_branch "+
    			"from "+
    			"(select branch, acc, sum(sum_acc)/100 summa, max(to_char(oper_date,'yyyymmdd')||'-'||to_char(file_id,'99999999999999999999')||'-'||to_char(rec_id,'99999999999999999999') ) dtl from visa_overdraft_pay "+ 
    			"where oper_date<=to_date(?,'dd.mm.yyyy') and acc like ? "+
    			"group by branch, acc) acc1";
    		ps = c.prepareStatement(sql1);

    		ps.setString(1, df.format(data_));
    		ps.setString(2, acc_);
    		rs = ps.executeQuery();
    		while(rs.next()) {
    			HumoFileRecords tmp = new HumoFileRecords();
    			tmp.setAbvr_name(rs.getString("branch"));
    			tmp.setTranz_acct(rs.getString("acc"));
    			tmp.setAccnt_amt(rs.getString("summa"));
    			if (rs.getString("karta_branch")!=null && rs.getString("karta_branch").length()>=16 /*karta raqam 16 ta*/ ) {
    				tmp.setCard(rs.getString("karta_branch").substring(0,16));	
    			}
    			if (rs.getString("karta_branch")!=null && rs.getString("karta_branch").length()==20 /*karta raqam 16 ta, '-' 1ta, 3 xonalik rieto_branch, jami 20 ta*/ ) {
    				for (RefData tietoBranch : listB) {
    			        if (tietoBranch.getData().equals(rs.getString("karta_branch").substring(18))) {
    			            tmp.setIss_mfo(tietoBranch.getLabel());
    			        }
    				}
    				if (tmp.getIss_mfo()==null) {
    					tmp.setIss_mfo(rs.getString("karta_branch").substring(18)+"_"+rs.getString("karta_branch").substring(17));
    				}
    			} else if (rs.getString("karta_branch")!=null) {
    				tmp.setIss_mfo("_"+rs.getString("karta_branch")+"_");
    			} else {
    				tmp.setIss_mfo("karta_branch_is_null");
    			}
    			list.add(tmp);
    		}
    	} catch (SQLException var8) {
    		ISLogger.getLogger().error("var8: "+var8.getMessage());
            var8.printStackTrace();
    		ISLogger.getLogger().error("var8: "+var8.getCause());
        } finally {
            ConnectionPool.close(c);
        }
        return list;
    }
}


