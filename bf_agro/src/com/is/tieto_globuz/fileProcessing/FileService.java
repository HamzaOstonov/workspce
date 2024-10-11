// 
// Decompiled by Procyon v0.5.36
// 

package com.is.tieto_globuz.fileProcessing;

import org.apache.tomcat.jdbc.pool.PoolConfiguration;
import org.apache.tomcat.jdbc.pool.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import com.is.tieto_globuz.Utils;
import java.util.LinkedList;
import com.is.utils.RefData;
import java.sql.CallableStatement;
import com.is.ISLogger;
import com.is.file_reciever_srv.recievers.EMPC.EXPT.EXPT_record;
import java.text.DecimalFormat;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import com.is.ConnectionPool;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

public class FileService
{
    private static String psql1;
    private static String psql2;
    private static String msql;
    private static SimpleDateFormat df;
    public static PreparedStatement ps_setparam;
    private static String bank_type="_";
    
    static {
        FileService.psql1 = "SELECT t.* FROM(SELECT t.*, ROWNUM rwnm FROM (SELECT * FROM (";
        FileService.psql2 = " ) s ) t WHERE ROWNUM <= ? order by id desc) t WHERE t.rwnm >= ?";
        FileService.msql = "select * from (select * from v_empc_files order by id desc)";
        FileService.df = new SimpleDateFormat("dd.MM.yyyy");
        FileService.ps_setparam = null;
    }
    
    public List<File> getFile() {
        final List<File> list = new ArrayList<File>();
        Connection c = null;
        try {
            c = ConnectionPool.getConnection();
            final Statement s = c.createStatement();
            final ResultSet rs = s.executeQuery("select * from v_empc_files");
            while (rs.next()) {
                list.add(new File(rs.getLong("id"), rs.getLong("fr_file_id"), rs.getInt("file_type_id"), rs.getInt("state_id"), rs.getString("file_name"), rs.getDate("file_date"), rs.getString("file_state_name")));
            }
        }
        catch (SQLException var8) {
            var8.printStackTrace();
            return list;
        }
        finally {
            ConnectionPool.close(c);
        }
        ConnectionPool.close(c);
        return list;
    }
    
    public static File getFile(final long fileId) {
        final File file = new File();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("SELECT * FROM v_empc_files WHERE id = ?");
            ps.setLong(1, fileId);
            System.out.println("fileId:" + fileId);
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
        }
        catch (SQLException var10) {
            var10.printStackTrace();
            return file;
        }
        finally {
            ConnectionPool.close(c);
        }
        ConnectionPool.close(c);
        return file;
    }
    
    private static String getCond(final List<FilterField> flfields) {
        return (flfields.size() > 0) ? " and " : " where ";
    }
    
    private static List<FilterField> getFilterFields(final FileFilter filter) {
        final List<FilterField> flfields = new ArrayList<FilterField>();
        if (!CheckNull.isEmpty(filter.getId())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "id = ?", (Object)filter.getId()));
        }
        if (!CheckNull.isEmpty(filter.getFr_file_id())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "fr_file_id = ?", (Object)filter.getFr_file_id()));
        }
        if (!CheckNull.isEmpty(filter.getFile_type_id())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "file_type_id = ?", (Object)filter.getFile_type_id()));
        }
        if (!CheckNull.isEmpty(filter.getState_id())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "state_id = ?", (Object)filter.getState_id()));
        }
        if (!CheckNull.isEmpty(filter.getFile_name())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "file_name = ?", (Object)filter.getFile_name()));
        }
        if (!CheckNull.isEmpty(filter.getFile_date())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "trunc(file_date) = ?", (Object)FileService.df.format(filter.getFile_date())));
        }
        if (!CheckNull.isEmpty(filter.getFile_state_name())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "file_state_name = ?", (Object)filter.getFile_state_name()));
        }
        return flfields;
    }
    
    public static int getCount(final FileFilter filter) {
        Connection c = null;
        int n = 0;
        final List<FilterField> flFields = getFilterFields(filter);
        final StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM empc_files");
        if (flFields.size() > 0) {
            for (int i = 0; i < flFields.size(); ++i) {
                sql.append(flFields.get(i).getSqlwhere());
            }
        }
        try {
            c = ConnectionPool.getConnection();
            System.out.println("SQL 77777" + sql.toString());
            final PreparedStatement ps = c.prepareStatement(sql.toString());
            for (int k = 0; k < flFields.size(); ++k) {
                ps.setObject(k + 1, flFields.get(k).getColobject());
                System.out.println("index:" + (k + 1) + " value:" + flFields.get(k).getColobject());
            }
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                n = rs.getInt(1);
            }
        }
        catch (SQLException var10) {
            var10.printStackTrace();
            return n;
        }
        finally {
            ConnectionPool.close(c);
        }
        ConnectionPool.close(c);
        return n;
    }
    
    public static List<File> getFilesFl(final int pageIndex, final int pageSize, final FileFilter filter) {
        final List<File> list = new ArrayList<File>();
        Connection c = null;
        final int v_lowerbound = pageIndex + 1;
        final int v_upperbound = v_lowerbound + pageSize - 1;
        final List<FilterField> flFields = getFilterFields(filter);
        final StringBuffer sql = new StringBuffer();
        sql.append(FileService.psql1);
        sql.append(FileService.msql);
        if (flFields.size() > 0) {
            for (int i = 0; i < flFields.size(); ++i) {
                sql.append(flFields.get(i).getSqlwhere());
            }
        }
        sql.append(FileService.psql2);
        System.out.println(sql.toString());
        try {
            c = ConnectionPool.getConnection();
            final PreparedStatement ps = c.prepareStatement(sql.toString());
            int params;
            for (params = 0; params < flFields.size(); ++params) {
                ps.setObject(params + 1, flFields.get(params).getColobject());
                System.out.println("Param " + params + "   " + flFields.get(params).getColobject());
            }
            ++params;
            ps.setInt(params++, v_upperbound);
            ps.setInt(params++, v_lowerbound);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new File(rs.getLong("id"), rs.getLong("fr_file_id"), rs.getInt("file_type_id"), rs.getInt("state_id"), rs.getString("file_name"), rs.getDate("file_date"), rs.getString("file_state_name")));
                System.out.println("Record " + rs.getString("file_name"));
            }
        }
        catch (SQLException var15) {
            var15.printStackTrace();
            return list;
        }
        finally {
            ConnectionPool.close(c);
        }
        ConnectionPool.close(c);
        return list;
    }
    
    public static void updateState_id(final long id, final int state_id) {
        Connection c = null;
        try {
            c = ConnectionPool.getConnection();
            final PreparedStatement ps = c.prepareStatement("UPDATE empc_files SET state_id = ?, WHERE id = ?");
            ps.setInt(1, state_id);
            ps.setLong(2, id);
            ps.executeUpdate();
            c.commit();
        }
        catch (SQLException var8) {
            var8.printStackTrace();
            return;
        }
        finally {
            ConnectionPool.close(c);
        }
        ConnectionPool.close(c);
    }
    
    public static String getRecordStateName(final String id) {
        String recordStateName = "";
        Connection c = null;
        try {
            System.out.println("Stat id: fileService line 220:" + id);
            c = ConnectionPool.getConnection();
            final PreparedStatement ps = c.prepareStatement("SELECT name FROM empc_expt_records_states WHERE id = ?");
            ps.setString(1, id);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                recordStateName = rs.getString("name");
            }
        }
        catch (SQLException var8) {
            var8.printStackTrace();
            return recordStateName;
        }
        finally {
            ConnectionPool.close(c);
        }
        ConnectionPool.close(c);
        return recordStateName;
    }
    
    public static EmpcExptRecord getBRecord(final long recordId) {
        EmpcExptRecord empcExptRecord = new EmpcExptRecord();
        Connection c = null;
        try {
            System.out.println("getBRecord fileService line 284");
            c = ConnectionPool.getConnection();
            final PreparedStatement ps = c.prepareStatement("SELECT * FROM empc_expt_records WHERE id = ?");
            ps.setLong(1, recordId);
            final ResultSet rs = ps.executeQuery();
            if (rs.next() && !rs.getString("tran_type").equals("110")) {
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
                System.out.println("tran_type:" + rs.getString("tran_type"));
            }
        }
        catch (Exception var9) {
            var9.printStackTrace();
            return empcExptRecord;
        }
        finally {
            ConnectionPool.close(c);
        }
        ConnectionPool.close(c);
        return empcExptRecord;
    }
    
    public static EmpcExptRecord getEmpcExptRecord(final long recordId) {
        EmpcExptRecord empcExptRecord = new EmpcExptRecord();
        Connection c = null;
        try {
            c = ConnectionPool.getConnection();
            final PreparedStatement ps = c.prepareStatement("SELECT * FROM empc_expt_records WHERE id = ? ");
            ps.setLong(1, recordId);
            final ResultSet rs = ps.executeQuery();
            if (rs.next() && !rs.getString("tran_type").equals("110")) {
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
        catch (Exception var9) {
            var9.printStackTrace();
            return empcExptRecord;
        }
        finally {
            ConnectionPool.close(c);
        }
        ConnectionPool.close(c);
        return empcExptRecord;
    }
    
    public static Long getFileType(final long empc_file_id) {
        Long fileTypeId = 0L;
        Connection c = null;
        Label_0096: {
            try {
                c = ConnectionPool.getConnection();
                final PreparedStatement ps = c.prepareStatement("SELECT file_type_id FROM v_empc_files r WHERE r.id = ?");
                ps.setLong(1, empc_file_id);
                final ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    fileTypeId = rs.getLong("file_type_id");
                }
            }
            catch (Exception var9) {
                var9.printStackTrace();
                break Label_0096;
            }
            finally {
                ConnectionPool.close(c);
            }
            ConnectionPool.close(c);
        }
        System.out.println("getFileType2");
        return fileTypeId;
    }
    
    public static List<HumoFileRecords> getFileRecords(final long empc_file_id, final long fileType) {
        final List<HumoFileRecords> list = new ArrayList<HumoFileRecords>();
        Connection c = null;
        Label_0449: {
            try {
                if (fileType == 1L) {
                    c = ConnectionPool.getConnection();
                    final PreparedStatement ps = c.prepareStatement("SELECT r.id,r.state_id,r.term_nr,r.merchant,r.abvr_name,r.card,r.mcc_code,r.tran_type,r.amount,r.batch_date,(select e.text from EMPC_B_RECORDS_PRC_ERRORS e where r.id = e.record_id) errText,s.name FROM empc_files_b_transactions r,empc_expt_records_states s WHERE r.empc_file_id = ? and r.state_id=s.id order by state_id desc");
                    ps.setLong(1, empc_file_id);
                    final ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        list.add(new HumoFileRecords(rs.getLong("id"), rs.getString("name"), rs.getString("merchant"), rs.getString("abvr_name"), rs.getString("card"), "", rs.getString("term_nr"), rs.getString("mcc_code"), rs.getString("tran_type"), rs.getString("amount"), rs.getString("batch_date"), rs.getString("errText"), ""));
                    }
                }
                else {
                    c = ConnectionPool.getConnection();
                    final PreparedStatement ps = c.prepareStatement("SELECT r.id, r.state_id, r.mcc_code, r.merchant,r.abvr_name,r.card,r.tranz_acct, r.term_id,r.tran_type,r.tran_amt,r.tran_date_time,(select e.text from EMPC_EXPT_RECORDS_PRC_ERRORS e where r.id = e.record_id) errText,s.name,r.tran_amt2 FROM empc_expt_records r,empc_expt_records_states s WHERE r.empc_file_id = ? and r.state_id=s.id order by state_id desc");
                    ps.setLong(1, empc_file_id);
                    final ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        if (!rs.getString("tran_type").equals("110")) {
                            list.add(new HumoFileRecords(rs.getLong("id"), rs.getString("name"), rs.getString("merchant"), rs.getString("abvr_name"), rs.getString("card"), rs.getString("tranz_acct"), rs.getString("term_id"), rs.getString("mcc_code"), rs.getString("tran_type"), rs.getString("tran_amt"), rs.getString("tran_date_time"), rs.getString("errText"), rs.getString("tran_amt2")));
                        }
                    }
                }
            }
            catch (Exception var9) {
                var9.printStackTrace();
                break Label_0449;
            }
            finally {
                ConnectionPool.close(c);
            }
            ConnectionPool.close(c);
        }
        System.out.println("list.toString()=" + list.toString());
        return list;
    }
    
    public static String customFormat(final String pattern, final double value) {
        final DecimalFormat myFormatter = new DecimalFormat(pattern);
        final String output = myFormatter.format(value);
        return output;
    }
    
    public static String getTietoDirectory(final long id) {
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
        }
        catch (SQLException var10) {
            var10.printStackTrace();
            return tietoDir;
        }
        finally {
            ConnectionPool.close(c);
        }
        ConnectionPool.close(c);
        return tietoDir;
    }
    
	public static String getBankType(String pbranch) {
		if (! (bank_type=="_" || bank_type.equals("_") ))
		{
			return bank_type;
		}
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select bank_type from s_mfo where bank_id = ?");
			ps.setString(1, pbranch);
			rs = ps.executeQuery();
			if (rs.next()) {
				bank_type = rs.getString("bank_type");
			}
		} catch (SQLException var11) {
			var11.printStackTrace();
		} finally {
			try {
  			  rs.close();
			} catch (SQLException ee1) {
			}
			try {
	  			  ps.close();
			} catch (SQLException ee1) {
			}
			ConnectionPool.close(c);
		}
		return bank_type;
	}

    public static Long getFileState(final long fileId) {
        long state = 1L;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("SELECT f.state_id FROM empc_files f WHERE f.id = ?");
            ps.setLong(1, fileId);
            rs = ps.executeQuery();
            if (rs.next()) {
                state = rs.getLong("state_id");
            }
        }
        catch (SQLException var11) {
            var11.printStackTrace();
            return state;
        }
        finally {
            ConnectionPool.close(c);
        }
        ConnectionPool.close(c);
        return state;
    }
    
    public static Long getRecordState(final long recordId) {
        long state = 1L;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("SELECT r.state_id FROM empc_expt_records r WHERE r.id = ?");
            ps.setLong(1, recordId);
            rs = ps.executeQuery();
            if (rs.next()) {
                state = rs.getLong("state_id");
            }
        }
        catch (SQLException var11) {
            var11.printStackTrace();
            return state;
        }
        finally {
            ConnectionPool.close(c);
        }
        ConnectionPool.close(c);
        return state;
    }
    
    public static EXPT_record getEXPT_record(final long recordId) {
        EXPT_record exptRecord = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("SELECT * FROM empc_expt_records WHERE id = ?");
            ps.setLong(1, recordId);
            rs = ps.executeQuery();
            if (rs.next()) {
                exptRecord = new EXPT_record();
                exptRecord.setId(Long.valueOf(rs.getLong("id")));
                exptRecord.setEmpc_file_id(Long.valueOf(rs.getLong("empc_file_id")));
                exptRecord.setState_id(Integer.valueOf(rs.getInt("state_id")));
                exptRecord.setRecord_type(rs.getString("record_type"));
                exptRecord.setLine_number(Long.valueOf(rs.getLong("line_number")));
                exptRecord.setClient(rs.getString("client"));
                exptRecord.setCard_acct(rs.getString("card_acct"));
                exptRecord.setAccnt_ccy(rs.getString("accnt_ccy"));
                exptRecord.setCard(rs.getString("card"));
                exptRecord.setSlip_nr(rs.getString("slip_nr"));
                exptRecord.setRef_number(rs.getString("ref_number"));
                exptRecord.setTran_date_time((Date)rs.getDate("tran_date_time"));
                exptRecord.setRec_date((Date)rs.getDate("rec_date"));
                exptRecord.setPost_date((Date)rs.getDate("post_date"));
                exptRecord.setDeal_desc(rs.getString("deal_desc"));
                exptRecord.setTran_type(rs.getString("tran_type"));
                exptRecord.setDeb_cred(rs.getString("deb_cred"));
                exptRecord.setTran_ccy(rs.getString("tran_ccy"));
                exptRecord.setTran_amt(Long.valueOf(rs.getLong("tran_amt")));
                exptRecord.setAccnt_amt(Long.valueOf(rs.getLong("accnt_amt")));
                exptRecord.setTerminal(rs.getString("terminal"));
                exptRecord.setMcc_code(rs.getString("mcc_code"));
                exptRecord.setMerchant(rs.getString("merchant"));
                exptRecord.setAbvr_name(rs.getString("abvr_name"));
                exptRecord.setCountry(rs.getString("country"));
                exptRecord.setCity(rs.getString("city"));
                exptRecord.setProc_id(Long.valueOf(rs.getLong("proc_id")));
                exptRecord.setInternal_no(Long.valueOf(rs.getLong("internal_no")));
                exptRecord.setProduct(Long.valueOf(rs.getLong("product")));
                exptRecord.setIss_mfo(rs.getString("iss_mfo"));
                exptRecord.setTerm_id(rs.getString("term_id"));
                exptRecord.setTranz_acct(rs.getString("tranz_acct"));
            }
        }
        catch (Exception var10) {
            var10.printStackTrace();
            return exptRecord;
        }
        finally {
            ConnectionPool.close(c);
        }
        ConnectionPool.close(c);
        return exptRecord;
    }
    
    public static void setParam(final Connection cMFO, final String name, final String value) throws SQLException {
        (FileService.ps_setparam = cMFO.prepareStatement("{call param.setParam(?, ?)}")).setString(1, name);
        FileService.ps_setparam.setString(2, value);
        FileService.ps_setparam.execute();
    }
    
    public static String getAmountAgro(final Long fileId, final String un, final String pwd, final String alias, final String fileName) throws SQLException {
        final Long amount = null;
        String error = "\u041a\u043b\u0438\u0440\u0438\u043d\u0433 \u0437\u0430\u0432\u0435\u0440\u0448\u0438\u043b\u0441\u044f";
        Connection c = null;
        CallableStatement cs = null;
        final ResultSet rs = null;
        try {
            final String clirInst = ConnectionPool.getValue("clir_inst");
            if (clirInst.equals("clir")) {
                c = getTimerConnection();
            }
            else if (clirInst.equals("bank")) {
                c = ConnectionPool.getConnection(un, pwd, alias);
            }
            else {
                ISLogger.getLogger().error((Object)"clir_inst \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d");
            }
            if (UpdateFileCliren(fileId.toString(), alias) != 0) {
                System.out.println(UpdateFileCliren(fileId.toString(), alias));
                ISLogger.getLogger().error((Object)("UpdateFileCliren:" + UpdateFileCliren(fileId.toString(), alias)));
                setParam(c, "PARENT_GROUP_ID", "198");
                setParam(c, "GROUP_ID", "198");
                setParam(c, "PARENT_DEAL_ID", "106");
                setParam(c, "ACTION_ID", "0");
                setParam(c, "S_DEAL_ID", "106");
                setParam(c, "FILE_NAME", fileName);
                cs = c.prepareCall("{call HUMO_CLEARING_OPERATION.Exec_out(?)}");
                System.out.println("call HUMO_CLEARING");
                ISLogger.getLogger().error((Object)"call HUMO_CLEARING");
                cs.setLong(1, fileId);
                cs.execute();
            }
            else {
                error = "\u041d\u043e\u043c\u0435\u0440 \u0444\u0430\u0439\u043b\u0430 \u043d\u0435 \u043f\u043e\u043c\u0435\u043d\u044f\u043b\u044c\u0441\u044f";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ISLogger.getLogger().error((Object)CheckNull.getPstr(e));
            error = CheckNull.getPstr(e);
            return error;
        }
        finally {
            if (c != null) {
                c.commit();
                resetTimerConnection();
                ConnectionPool.close(c);
            }
        }
        if (c != null) {
            c.commit();
            resetTimerConnection();
            ConnectionPool.close(c);
        }
        return error;
    }
    
    public static String getAmount(final Long fileId, final String un, final String pwd, final String alias, final String fileName) throws SQLException {
        final Long amount = null;
        String error = "\u041a\u043b\u0438\u0440\u0438\u043d\u0433 \u0437\u0430\u0432\u0435\u0440\u0448\u0438\u043b\u0441\u044f";
        Connection c = null;
        CallableStatement cs = null;
        final ResultSet rs = null;
        try {
            final String clirInst = ConnectionPool.getValue("clir_inst");
            ISLogger.getLogger().error("clir 000:"+clirInst);
            if (clirInst.equals("clir")) {
                ISLogger.getLogger().error("clir 001");
                c = ConnectionPool.getConnection_cliring();
            }
            else if (clirInst.equals("bank")) {
                ISLogger.getLogger().error("clir 002");
                c = ConnectionPool.getConnection(un, pwd, alias);
            }
            else {
                ISLogger.getLogger().error((Object)"clir_inst \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d");
            }
            setParam(c, "PARENT_GROUP_ID", "198");
            setParam(c, "GROUP_ID", "198");
            setParam(c, "PARENT_DEAL_ID", "106");
            setParam(c, "ACTION_ID", "0");
            setParam(c, "S_DEAL_ID", "106");
            setParam(c, "FILE_NAME", fileName);
            cs = c.prepareCall("{call HUMO_CLEARING_OPERATION.Exec_out(?)}");
            System.out.println("call HUMO_CLEARING");
            ISLogger.getLogger().error((Object)"call HUMO_CLEARING");
            cs.setLong(1, fileId);
            cs.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
            ISLogger.getLogger().error((Object)CheckNull.getPstr(e));
            error = CheckNull.getPstr(e);
            return error;
        }
        finally {
            if (c != null) {
                c.commit();
                resetTimerConnection();
                ConnectionPool.close(c);
            }
        }
        if (c != null) {
            c.commit();
            resetTimerConnection();
            ConnectionPool.close(c);
        }
        return error;
    }
    
    public static String getCountSuccessEXPT(final Connection c, final String empcFileId) {
        String count = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement("select count(*) as c from empc_expt_records t where t.empc_file_id=? and t.state_id=2");
            ps.setString(1, empcFileId);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getString("c");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    
    public static String getCountSuccessB(final Connection c, final String empcFileId) {
        String count = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement("select count(*) as c from empc_files_b_transactions t where t.empc_file_id=? and t.state_id=2");
            ps.setString(1, empcFileId);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getString("c");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    
    public static String getCount\u0421ommonB(final Connection c, final String empcFileId) {
        String count = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement("select count(*) as c from empc_files_b_transactions t where t.empc_file_id=?");
            ps.setString(1, empcFileId);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getString("c");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    
    public static String getCountCommonEXPT(final Connection c, final String empcFileId) {
        String count = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement("select count(*) as c from empc_expt_records t where t.empc_file_id=? and t.tran_type<>'110'");
            ps.setString(1, empcFileId);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getString("c");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    
    public static String getCountErrorEXPT(final Connection c, final String empcFileId) {
        String count = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement("select count(*) as c from empc_expt_records t where t.empc_file_id=? and t.state_id=3");
            ps.setString(1, empcFileId);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getString("c");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    
    public static String getCountErrorB(final Connection c, final String empcFileId) {
        String count = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement("select count(*) as c from empc_files_b_transactions t where t.empc_file_id=? and t.state_id=3");
            ps.setString(1, empcFileId);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getString("c");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    
    public static String getProcessInfo(final Connection c, final String empcFileId, final String fileTypeId) {
        String processInfo = null;
        try {
            if (fileTypeId.equals("1")) {
                processInfo = "\u041e\u0431\u0449\u0438\u0439: " + getCount\u0421ommonB(c, empcFileId) + " \u043e\u0431\u0440\u0430\u0431\u043e\u0442\u0430\u043d\u0430: " + getCountSuccessB(c, empcFileId) + " \u043e\u0448\u0438\u0431\u043a\u0430: " + getCountErrorB(c, empcFileId);
            }
            else {
                processInfo = "\u041e\u0431\u0449\u0438\u0439: " + getCountCommonEXPT(c, empcFileId) + " \u043e\u0431\u0440\u0430\u0431\u043e\u0442\u0430\u043d\u0430: " + getCountSuccessEXPT(c, empcFileId) + " \u043e\u0448\u0438\u0431\u043a\u0430: " + getCountErrorEXPT(c, empcFileId);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return processInfo;
    }
    
    public static String HUMO_PROCESSING(final Connection c, final String fileId) throws SQLException {
        String error = null;
        try {
            System.out.println("CreateJob start FilesSERVICE LINE 908 ");
            CallableStatement cs = null;
            cs = c.prepareCall("{call HUMO_PROCESSING.SepCrDoc(?)}");
            cs.setString(1, fileId);
            cs.execute();
            System.out.println("CreateJob end FilesSERVICE LINE 913 ");
        }
        catch (Exception e) {
            e.printStackTrace();
            ISLogger.getLogger().error((Object)CheckNull.getPstr(e));
            error = CheckNull.getPstr(e);
        }
        return error;
    }
    
    
    public static String HUMO_PROCESSING1(Connection c, String fileId)
	throws SQLException {
String error = null;
try {

	CallableStatement cs = null;
	// cs = c.prepareCall("{ call info.init() }");
	cs = c.prepareCall("{call HUMO2_PROCESSING.SepCrDocOne(?)}");
	cs.setString(1, fileId);
	cs.execute();
	System.out.println("CreateJob end HUMO2_PROCESSING.SepCrDocOne");

} catch (Exception e) {
	e.printStackTrace();
	ISLogger.getLogger().error(CheckNull.getPstr(e));
	error = CheckNull.getPstr(e);
}
return error;
}

public static String HUMO_PROCESSING2(Connection c, String fileId)
	throws SQLException {
String error = null;
try {

	CallableStatement cs = null;
	// cs = c.prepareCall("{ call info.init() }");
	cs = c.prepareCall("{call HUMO2_PROCESSING.SepCrDocTwo(?)}");
	cs.setString(1, fileId);
	cs.execute();
	System.out.println("CreateJob end HUMO2_PROCESSING.SepCrDocTwo");

} catch (Exception e) {
	e.printStackTrace();
	ISLogger.getLogger().error(CheckNull.getPstr(e));
	error = CheckNull.getPstr(e);
}
return error;
}

public static String HUMO_PROCESSING3(Connection c, String fileId)
	throws SQLException {
String error = null;
try {

	CallableStatement cs = null;
	// cs = c.prepareCall("{ call info.init() }");
	cs = c.prepareCall("{call HUMO2_PROCESSING.SepCrDocThree(?)}");
	cs.setString(1, fileId);
	cs.execute();
	System.out.println("CreateJob end HUMO2_PROCESSING.SepCrDocThree");

} catch (Exception e) {
	e.printStackTrace();
	ISLogger.getLogger().error(CheckNull.getPstr(e));
	error = CheckNull.getPstr(e);
}
return error;
}
    public static String getCountBState1(final Connection c, final String empcFileId) {
        String count = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement("select count(*) as c from empc_files_b_transactions t where t.empc_file_id=? and t.state_id=1");
            ps.setString(1, empcFileId);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getString("c");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    
    public static String getCountEXPTStat1(final Connection c, final String empcFileId) {
        String count = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement("select count(*) as c from empc_expt_records t where t.empc_file_id=? and t.state_id=1 and t.tran_type<>'110'");
            ps.setString(1, empcFileId);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getString("c");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    
    public static boolean isProcessingFile(final Connection c, final String empcFileId) {
        boolean process = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement("select * from user_scheduler_jobs where job_name = ?");
            ps.setString(1, "HUMO_JOB" + empcFileId);
            System.out.println("HUMO_JOB" + empcFileId);
            rs = ps.executeQuery();
            while (rs.next()) {
                process = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return process;
    }
    
    public static List<RefData> getState(final String branch) {
        return getRefData("select id  data,name  label from empc_file_state order by id", branch);
    }
    
    public static List<RefData> getRefData(final String sql, final String branch) {
        final List list = new LinkedList();
        Connection c = null;
        try {
            c = ConnectionPool.getConnection(branch);
            final Statement s = c.createStatement();
            final ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(new RefData(rs.getString("data"), rs.getString("label")));
            }
        }
        catch (SQLException e) {
            ISLogger.getLogger().error((Object)CheckNull.getPstr((Exception)e));
            e.printStackTrace();
            return (List<RefData>)list;
        }
        finally {
            ConnectionPool.close(c);
        }
        ConnectionPool.close(c);
        return (List<RefData>)list;
    }
    
    public static int UpdateFileCliren(final String file, final String alias) {
        PreparedStatement ps = null;
        Connection c = null;
        int updeteResult = 0;
        try {
            c = ConnectionPool.getConnection(alias);
            ps = c.prepareStatement("update bank9004.sim_clir_humo set file_id=?");
            ps.setString(1, file);
            updeteResult = ps.executeUpdate();
            ISLogger.getLogger().error((Object)("UpdateFileCliren: " + file + "updated:" + updeteResult));
            c.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            ISLogger.getLogger().error((Object)CheckNull.getPstr(e));
            updeteResult = 0;
            return updeteResult;
        }
        finally {
            Utils.close(ps);
            ConnectionPool.close(c);
        }
        Utils.close(ps);
        ConnectionPool.close(c);
        return updeteResult;
    }
    
    public static Connection getTimerConnection() throws SQLException {
        try {
            final Context context = new InitialContext();
            final Context envContext = (Context)context.lookup("java:/comp/env");
            final DataSource datasource = (DataSource)envContext.lookup("jdbc/clearing");
            if (datasource == null) {
                throw new Exception("No DataSource");
            }
            final PoolConfiguration p = datasource.getPoolProperties();
            p.setRemoveAbandonedTimeout(1800);
            datasource.setPoolProperties(p);
            final Connection c = datasource.getConnection();
            return c;
        }
        catch (Exception e) {
            ISLogger.getLogger().error((Object)e.getStackTrace());
            throw new RuntimeException("Database Not Available.");
        }
    }
    
    public static void resetTimerConnection() throws SQLException {
        try {
            final Context context = new InitialContext();
            final Context envContext = (Context)context.lookup("java:/comp/env");
            final DataSource datasource = (DataSource)envContext.lookup("jdbc/clearing");
            if (datasource == null) {
                throw new Exception("No DataSource");
            }
            final PoolConfiguration p = datasource.getPoolProperties();
            p.setRemoveAbandonedTimeout(60);
            datasource.setPoolProperties(p);
        }
        catch (Exception e) {
            ISLogger.getLogger().error((Object)e.getStackTrace());
            throw new RuntimeException("Database Not Available.");
        }
    }
}
