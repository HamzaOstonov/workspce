// 
// Decompiled by Procyon v0.5.36
// 

package com.is.file_reciever_srv.recievers.EMPC.b_file;

import java.util.ArrayList;
import com.is.file_reciever_srv.exception.Reciever_exception;
import com.is.ConnectionPool;
import com.is.file_reciever_srv.recievers.EMPC.b_file.entity.B_file_transaction_record;
import java.sql.SQLException;
import com.is.utils.CheckNull;
import com.is.ISLogger;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.util.List;
import java.sql.Connection;
import org.apache.log4j.Logger;
import java.util.HashMap;
import java.sql.CallableStatement;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class B_file_processing
{
    private PreparedStatement sessionInitPs;
    private Statement sessionInitPsSt;
    private PreparedStatement updFileStatePs;
    private PreparedStatement updateFileStatePs;
    private PreparedStatement configMapPs;
    private PreparedStatement recordsPs;
    private PreparedStatement tranTypesMapPs;
    private CallableStatement callableStatementPrep;
    private PreparedStatement updateRecordStatePs;
    private PreparedStatement deleteRecordErrorPs;
    private PreparedStatement insertRecordErrorPs;
    private PreparedStatement terminalHostToHostPs;
    private PreparedStatement terminalHostToHostPs2;
    private PreparedStatement merchantPs;
    private PreparedStatement terminalPs;
    private PreparedStatement dblinkPs;
    private String branch;
    private PreparedStatement terminal;
    private PreparedStatement merchant;
    private PreparedStatement agrementMerchant;
    private PreparedStatement operation;
    private PreparedStatement exsistMCC;
    private String login;
    private String parol;
    private String alias;
    private PreparedStatement ps_setparam;
    private static HashMap<String, String> branchAccInHashmap;
    private static HashMap<String, String> cardBranchInHashmap;
    private String termBranch;
    private PreparedStatement psHistryCard;
    private CallableStatement cs;
    private static final Logger logger;
    private Connection c;
    private static HashMap<String, String> transactions_config;
    private static HashMap<String, String> tranTypesMap;
    private List<String> bankBranchesMap;
    private static HashMap<String, String> branchMap;
    private static HashMap<String, String> aliasMap;
    private static PreparedStatement psBranch;
    private static ResultSet rsBranch;
    private static PreparedStatement psAlias;
    private static ResultSet rsAlias;
    private boolean fileHasErrors;
    private SimpleDateFormat sdf;
    String acc_berch;
    
    static {
        B_file_processing.branchAccInHashmap = null;
        B_file_processing.cardBranchInHashmap = null;
        logger = ISLogger.getLogger();
        B_file_processing.transactions_config = null;
        B_file_processing.tranTypesMap = null;
        B_file_processing.branchMap = null;
        B_file_processing.aliasMap = null;
        B_file_processing.psBranch = null;
        B_file_processing.rsBranch = null;
        B_file_processing.psAlias = null;
        B_file_processing.rsAlias = null;
    }
    
    public B_file_processing() {
        this.sessionInitPs = null;
        this.sessionInitPsSt = null;
        this.updFileStatePs = null;
        this.updateFileStatePs = null;
        this.configMapPs = null;
        this.recordsPs = null;
        this.tranTypesMapPs = null;
        this.callableStatementPrep = null;
        this.updateRecordStatePs = null;
        this.deleteRecordErrorPs = null;
        this.insertRecordErrorPs = null;
        this.terminalHostToHostPs = null;
        this.terminalHostToHostPs2 = null;
        this.merchantPs = null;
        this.terminalPs = null;
        this.dblinkPs = null;
        this.branch = "";
        this.terminal = null;
        this.merchant = null;
        this.agrementMerchant = null;
        this.operation = null;
        this.exsistMCC = null;
        this.ps_setparam = null;
        this.termBranch = null;
        this.psHistryCard = null;
        this.cs = null;
        this.c = null;
        this.bankBranchesMap = null;
        this.fileHasErrors = false;
        this.sdf = new SimpleDateFormat("dd.MM.yyyy");
        this.acc_berch = null;
    }
    
    public void process_file(final String fileName, final Long file_id, final String login, final String parol, final String alias, final Connection c) {
        B_file_processing.logger.error((Object)("GlobUz Process file: " + file_id + " started."));
        this.fileHasErrors = false;
        this.login = login;
        this.parol = parol;
        this.alias = alias;
        final boolean mfoErr = false;
        ResultSet rs = null;
        PreparedStatement recordsPs = null;
        final String currentBranch = "";
        B_file_transaction_record record = null;
        try {
            this.load_config_map(login, parol, alias, c);
            this.getRealCardBranchAcc(login, parol, alias, c);
            this.load_alias(c);
            recordsPs = c.prepareStatement("SELECT * FROM empc_files_b_transactions r WHERE r.empc_file_id = ? AND r.state_id != 2 ORDER BY merchant");
            recordsPs.setLong(1, file_id);
            rs = recordsPs.executeQuery();
            System.out.println("empc_file_id:" + file_id);
            while (rs.next()) {
                System.out.println("rs:" + rs.getString("merchant"));
                this.branch = rs.getString("merchant").substring(0, 5);
                record = this.fillRecord(rs);
                System.out.println("rcord" + record.getId());
                this.process_record(record, login, parol, alias, c, fileName);
            }
            if (!this.fileHasErrors) {
                System.out.println("B_file_processing.process_file()");
                this.updateFileState(c, 2L, file_id);
            }
            c.commit();
            B_file_processing.logger.error((Object)"File processed good.");
        }
        catch (Exception e) {
            try {
                c.rollback();
            }
            catch (SQLException exeption) {
                B_file_processing.logger.error((Object)CheckNull.getPstr((Exception)exeption));
            }
            e.printStackTrace();
            B_file_processing.logger.error((Object)("\nProcessing EMPC GlobUz B-file error: \n" + CheckNull.getPstr(e)));
            return;
        }
        finally {
            close(rs);
        }
        close(rs);
    }
    
    public void process_record(final B_file_transaction_record record, final String login, final String parol, final String alias, final Connection c, final String fileName) throws SQLException {
        this.clearParam(c);
        System.out.println("process_record() line 177");
        B_file_processing.logger.info((Object)("Process record: " + record.getId() + " started.\n" + "Tran type = " + record.getTran_type() + "\n" + "Acq bank = " + record.getAcq_bank() + "\n" + "Card bank code = " + record.getCard().substring(4, 6) + "\n"));
        String merchantId = "";
        ResultSet rsMerch = null;
        ResultSet rsAgreMerch = null;
        ResultSet rsTerm = null;
        try {
            final Long operation_id = this.getOperationId(record, c);
            System.out.println("operation_id:" + operation_id);
            if (operation_id == 0L || operation_id == 1L) {
                ISLogger.getLogger().error((Object)"\u041d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u0430 \u043e\u043f\u0435\u0440\u0430\u0446\u0438\u044f");
                System.out.println("\u041d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u0430 \u043e\u043f\u0435\u0440\u0430\u0446\u0438\u044f");
                this.deleteRecordError(c, record.getId());
                this.insertRecordError(c, record.getId(), "\u041d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u0430 \u043e\u043f\u0435\u0440\u0430\u0446\u0438\u044f");
                this.updateRecordState(c, 3L, record.getId());
                this.fileHasErrors = true;
            }
            else if (operation_id == 2L) {
                ISLogger.getLogger().error((Object)"\u0421\u0432\u043e\u0439 \u043a\u0430\u0440\u0442\u0430 \u0438 \u0441\u0432\u043e\u0439 \u0442\u0435\u0440\u043c\u0438\u043d\u0430\u043b \u043d\u0435 \u0431\u0443\u0434\u0435\u0442 \u043e\u0431\u0440\u0430\u0431\u0430\u0442\u044b\u0432\u0430\u0442\u044c\u0441\u044f \u043f\u043e B \u0444\u0430\u0439\u043b\u0443");
                System.out.println("\u0421\u0432\u043e\u0439 \u043a\u0430\u0440\u0442\u0430 \u0438 \u0441\u0432\u043e\u0439 \u0442\u0435\u0440\u043c\u0438\u043d\u0430\u043b \u043d\u0435 \u0431\u0443\u0434\u0435\u0442 \u043e\u0431\u0440\u0430\u0431\u0430\u0442\u044b\u0432\u0430\u0442\u044c\u0441\u044f \u043f\u043e B \u0444\u0430\u0439\u043b\u0443");
                this.deleteRecordError(c, record.getId());
                this.insertRecordError(c, record.getId(), "\u0421\u0432\u043e\u0439 \u043a\u0430\u0440\u0442\u0430 \u0438 \u0441\u0432\u043e\u0439 \u0442\u0435\u0440\u043c\u0438\u043d\u0430\u043b \u043d\u0435 \u0431\u0443\u0434\u0435\u0442 \u043e\u0431\u0440\u0430\u0431\u0430\u0442\u044b\u0432\u0430\u0442\u044c\u0441\u044f \u043f\u043e B \u0444\u0430\u0439\u043b\u0443");
                this.updateRecordState(c, 2L, record.getId());
            }
            else if (operation_id == 3L) {
                ISLogger.getLogger().error((Object)("\u0422\u0435\u0440\u043c\u0438\u043d\u0430\u043b: " + record.getTerm_nr() + " \u043d\u0435 \u0431\u0443\u0434\u0435\u0442 \u043e\u0431\u0440\u0430\u0431\u0430\u0442\u044b\u0432\u0430\u0442\u044c\u0441\u044f \u043f\u043e B \u0444\u0430\u0439\u043b\u0443"));
                System.out.println("\u0422\u0435\u0440\u043c\u0438\u043d\u0430\u043b: " + record.getTerm_nr() + " \u043d\u0435 \u0431\u0443\u0434\u0435\u0442 \u043e\u0431\u0440\u0430\u0431\u0430\u0442\u044b\u0432\u0430\u0442\u044c\u0441\u044f \u043f\u043e B \u0444\u0430\u0439\u043b\u0443");
                this.deleteRecordError(c, record.getId());
                this.insertRecordError(c, record.getId(), "\u0422\u0435\u0440\u043c\u0438\u043d\u0430\u043b: " + record.getTerm_nr() + " \u043d\u0435 \u0431\u0443\u0434\u0435\u0442 \u043e\u0431\u0440\u0430\u0431\u0430\u0442\u044b\u0432\u0430\u0442\u044c\u0441\u044f \u043f\u043e B \u0444\u0430\u0439\u043b\u0443");
                this.updateRecordState(c, 2L, record.getId());
            }
            else {
                if (this.terminalPs == null) {
                    this.terminalPs = c.prepareStatement("select ACC,acceptor_id,ACC_ATM from bf_globuz_acc_tr_all where TERMINAL_ID = ?");
                }
                this.terminalPs.setString(1, record.getTerm_nr());
                rsTerm = this.terminalPs.executeQuery();
                if (rsTerm.next()) {
                    System.out.println("record.getMerchant().substring(0,5): " + record.getMerchant().substring(0, 5));
                    this.alter_session_init(c, B_file_processing.aliasMap.get(record.getMerchant().substring(0, 5)));
                    ISLogger.getLogger().error((Object)("ACC_TERM:" + rsTerm.getString("ACC") + " record id:" + record.getId()));
                    this.setParam(c, "ACC_TERM", rsTerm.getString("ACC"));
                    this.setParam(c, "ACC_ATM", rsTerm.getString("ACC_ATM"));
                    this.setParam(c, "ACC_ATM_BRANCH", "0" + rsTerm.getString("acceptor_id").substring(1, 5));
                    this.setParam(c, "ACC_TERM_BRANCH", "0" + rsTerm.getString("acceptor_id").substring(1, 5));
                    System.out.println("ACC_TERM_BRANCH0" + rsTerm.getString("acceptor_id").substring(1, 5));
                    ISLogger.getLogger().error((Object)("ACC_TERM_BRANCH0" + rsTerm.getString("acceptor_id").substring(1, 5)));
                    this.termBranch = "0" + rsTerm.getString("acceptor_id").substring(1, 5);
                    ISLogger.getLogger().error((Object)("termBranch: " + this.termBranch));
                }
                else if (!ConnectionPool.getValue("humo_host_term").equals("")) {
                    if (this.terminalHostToHostPs == null) {
                        this.terminalHostToHostPs = c.prepareStatement("select ACC_UZS,BRANCH,ACC_17401 from TI_TERMINAL_TRANS_ACC where TERMINAL_ID = ?");
                    }
                    this.terminalHostToHostPs.setString(1, record.getTerm_nr());
                    rsTerm = this.terminalHostToHostPs.executeQuery();
                    if (rsTerm.next()) {
                        System.out.println("rsTerm.getString(BRANCH): " + rsTerm.getString("BRANCH"));
                        this.alter_session_init(c, B_file_processing.aliasMap.get(rsTerm.getString("BRANCH")));
                        this.setParam(c, "ACC_ATM", rsTerm.getString("ACC_UZS"));
                        this.setParam(c, "ACC_17401", rsTerm.getString("ACC_17401"));
                        this.setParam(c, "ACC_TERM", rsTerm.getString("ACC_UZS"));
                        this.setParam(c, "ACC_TERM_BRANCH", rsTerm.getString("BRANCH"));
                        this.setParam(c, "ACC_17401_BRANCH", rsTerm.getString("BRANCH"));
                        this.setParam(c, "ACC_ATM_BRANCH", rsTerm.getString("BRANCH"));
                        System.out.println("ACC_TERM_BRANCH" + rsTerm.getString("BRANCH"));
                        ISLogger.getLogger().error((Object)("ACC_TERM_BRANCH" + rsTerm.getString("BRANCH")));
                        System.out.println("ACC_TERM" + rsTerm.getString("ACC_UZS"));
                        ISLogger.getLogger().error((Object)("ACC_TERM" + rsTerm.getString("ACC_UZS")));
                        ISLogger.getLogger().error((Object)("ACC_17401" + rsTerm.getString("ACC_17401")));
                        this.termBranch = rsTerm.getString("BRANCH");
                        ISLogger.getLogger().error((Object)("termBranch: " + this.termBranch));
                    }
                }
                merchantId = String.valueOf(merchantId) + record.getMerchant();
                merchantId = merchantId.substring(1);
                merchantId = "J" + merchantId;
                if (this.merchantPs == null) {
                    this.merchantPs = c.prepareStatement("select acc,merchant from bf_globuz_merchants_all where merchant = ?");
                }
                if (this.agrementMerchant == null) {
                    this.agrementMerchant = c.prepareStatement("select bank_account, merchant from bf_globuz_agreements_all where merchant= ? and card_type='01'");
                }
                this.merchantPs.setString(1, merchantId);
                rsMerch = this.merchantPs.executeQuery();
                if (rsMerch.next()) {
                    if (rsMerch.getString("acc") == null) {
                        this.agrementMerchant.setString(1, merchantId);
                        rsAgreMerch = this.agrementMerchant.executeQuery();
                        if (rsAgreMerch.next()) {
                            if (rsAgreMerch.getString("bank_account").length() > 20) {
                                this.setParam(c, "ACC_MERCH", rsAgreMerch.getString("bank_account").substring(5));
                                this.setParam(c, "ACC_MERCH_BRANCH", rsAgreMerch.getString("bank_account").substring(0, 5));
                                ISLogger.getLogger().error((Object)("ACC_MERCH:" + rsAgreMerch.getString("bank_account") + " record id:" + record.getId()));
                                System.out.println("ACC_MERCH_BRANCH " + record.getMerchant().substring(0, 5));
                                ISLogger.getLogger().error((Object)("ACC_MERCH_BRANCH " + record.getMerchant().substring(0, 5)));
                            }
                            else {
                                this.setParam(c, "ACC_MERCH", rsAgreMerch.getString("bank_account"));
                                ISLogger.getLogger().error((Object)("ACC_MERCH:" + rsMerch.getString("acc") + " record id:" + record.getId()));
                                this.setParam(c, "ACC_MERCH_BRANCH", record.getMerchant().substring(0, 5));
                            }
                        }
                    }
                    else {
                        this.setParam(c, "ACC_MERCH", rsMerch.getString("acc"));
                        this.setParam(c, "ACC_MERCH_BRANCH", record.getMerchant().substring(0, 5));
                        ISLogger.getLogger().error((Object)("ACC_MERCH:" + rsMerch.getString("acc") + " record id:" + record.getId()));
                    }
                }
                this.setParam(c, "TRAN_DATE_TIME", record.getBatch_date());
                this.setParam(c, "FDATE", record.getFile_date());
                this.setParam(c, "OPERATION_ID", operation_id.toString());
                ISLogger.getLogger().error((Object)("OPERATION_ID:" + operation_id.toString()));
                this.setParam(c, "BRANCH", this.termBranch);
                this.setParam(c, "PARENT_GROUP_ID", "198");
                this.setParam(c, "PARENT_DEAL_ID", "106");
                this.setParam(c, "S_DEAL_ID", "106");
                this.setParam(c, "GROUP_ID", "198");
                this.setParam(c, "SUMMA", record.getAmount());
                this.setParam(c, "PARENT_ID", record.getId().toString());
                this.setParam(c, "ACTION_ID", "0");
                this.setParam(c, "ACC_CARD", B_file_processing.branchAccInHashmap.get(record.getCard()));
                this.setParam(c, "ACC_CARD_BRANCH", B_file_processing.cardBranchInHashmap.get(record.getCard()));
                this.setParam(c, "CARD", record.getCard());
                this.setParam(c, "TERMINAL", record.getTerm_nr());
                this.setParam(c, "FILE", fileName);
                this.setParam(c, "MERCHANT", merchantId);
                this.setParam(c, "REF_NUMBER", record.getRef_number());
                System.out.println("FILE:" + fileName);
                ISLogger.getLogger().error((Object)("FILE:" + fileName));
                ISLogger.getLogger().error((Object)("ACC_CARD_BRANCH: " + B_file_processing.cardBranchInHashmap.get(record.getCard())));
                if (record.getMerchant() == null) {
                    ISLogger.getLogger().error((Object)("\u0432 \u0442\u0440\u0430\u043d\u0437\u0430\u043a\u0446\u0438\u0438 " + record.getId().toString() + " \u043f\u043e\u043b\u044f Branch \u043f\u0443\u0441\u0442\u043e\u0439"));
                }
                (this.cs = c.prepareCall("{call HUMO_TR_DOC.PAYDOC_TRANS(?,?,?,?,?,?)}")).setString(1, this.termBranch);
                this.cs.setString(2, operation_id.toString());
                this.cs.setString(3, record.getAmount());
                this.cs.setString(4, record.getId().toString());
                this.cs.setString(5, "198");
                this.cs.registerOutParameter(6, 12);
                this.cs.executeUpdate();
                ISLogger.getLogger().error((Object)("DOC PARAM:" + this.cs.getString(6)));
                System.out.println("DOC PARAM:" + this.cs.getString(6));
                B_file_processing.logger.info((Object)("crBankDoc: " + crBankDoc(c, this.cs.getString(6), 19)));
                this.updateRecordState(c, 2L, record.getId());
                this.deleteRecordError(c, record.getId());
                B_file_processing.logger.info((Object)"Record processed good.");
            }
        }
        catch (Exception e) {
            c.rollback();
            this.updateRecordState(c, 3L, record.getId());
            e.printStackTrace();
            this.fileHasErrors = true;
            this.updateFileState(c, 3L, record.getEMPC_file_id());
            this.deleteRecordError(c, record.getId());
            this.insertRecordError(c, record.getId(), e);
            B_file_processing.logger.error((Object)CheckNull.getPstr(e));
            return;
        }
        finally {
            c.commit();
            if (rsMerch != null) {
                rsMerch.close();
            }
            if (rsTerm != null) {
                rsTerm.close();
            }
        }
        c.commit();
        if (rsMerch != null) {
            rsMerch.close();
        }
        if (rsTerm != null) {
            rsTerm.close();
        }
    }
    
    public void setParam(final Connection cMFO, final String name, final String value) throws SQLException {
        (this.ps_setparam = cMFO.prepareStatement("{call param.setParam(?, ?)}")).setString(1, name);
        this.ps_setparam.setString(2, value);
        this.ps_setparam.execute();
    }
    
    public void clearParam(final Connection c) throws SQLException {
        (this.ps_setparam = c.prepareCall("{ call Param.clearparam() }")).execute();
    }
    
    public static long crBankDoc(final Connection c, final String tr_id, final int cr_type) throws SQLException {
        CallableStatement cs = null;
        Long r = null;
        cs = c.prepareCall("{call HUMO_TR_DOC.CREATE_DOC_BANK(?,?,?,?)}");
        cs.setString(1, tr_id);
        cs.registerOutParameter(2, 12);
        cs.registerOutParameter(3, 4);
        cs.setInt(4, cr_type);
        cs.executeUpdate();
        r = cs.getLong(3);
        return r;
    }
    
    public void alter_session_init(final Connection c, final String branch) throws Exception {
        String alias = null;
        ResultSet rs = null;
        try {
            if (this.sessionInitPs == null) {
                this.sessionInitPs = c.prepareStatement("select t.user_name res from ss_dblink_branch t where t.user_name = ?");
            }
            System.out.println("branch: " + branch);
            ISLogger.getLogger().error((Object)("branch: " + branch));
            this.sessionInitPs.setString(1, branch);
            rs = this.sessionInitPs.executeQuery();
            if (!rs.next()) {
                throw new Reciever_exception("Wrong branch: " + branch);
            }
            alias = rs.getString("res");
            if (this.sessionInitPsSt == null) {
                this.sessionInitPsSt = c.createStatement();
            }
            this.sessionInitPsSt.executeUpdate("alter session set nls_date_format='dd.mm.yyyy'");
            this.sessionInitPsSt.executeUpdate("ALTER SESSION SET CURRENT_SCHEMA=" + alias);
            this.sessionInitPsSt.execute("{call info.init()}");
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        finally {
            if (rs != null) {
                rs.close();
            }
        }
        if (rs != null) {
            rs.close();
        }
    }
    
    public void updateFileState(final Connection c, final Long fileId) throws SQLException {
        long errNumber = 0L;
        ResultSet rs = null;
        try {
            if (this.updFileStatePs == null) {
                this.updFileStatePs = c.prepareStatement("SELECT count(*)nr FROM empc_files_b_transactions WHERE empc_file_id = ? AND state_id != ?");
            }
            this.updFileStatePs.setLong(1, fileId);
            this.updFileStatePs.setLong(2, 2L);
            rs = this.updFileStatePs.executeQuery();
            if (rs.next()) {
                errNumber = rs.getLong("nr");
                if (errNumber == 0L) {
                    if (this.updateFileStatePs == null) {
                        System.out.println("B_file_processing line 370");
                        this.updateFileStatePs = c.prepareStatement("update empc_files set state_id = ? where id = ?");
                    }
                    this.updateFileStatePs.setLong(1, 2L);
                    this.updateFileStatePs.setLong(2, fileId);
                    this.updateFileStatePs.executeUpdate();
                }
            }
        }
        catch (SQLException e) {
            B_file_processing.logger.error((Object)CheckNull.getPstr((Exception)e));
            return;
        }
        finally {
            if (rs != null) {
                rs.close();
            }
        }
        if (rs != null) {
            rs.close();
        }
    }
    
    private void updateFileState(final Connection c, final Long state, final Long file_id) throws SQLException {
        if (this.updateFileStatePs == null) {
            System.out.println("Dilshod  =  " + state + "  >>  fileId  ==  " + file_id);
            this.updateFileStatePs = c.prepareStatement("update empc_files set state_id = ? where id = ?");
        }
        this.updateFileStatePs.setLong(1, state);
        this.updateFileStatePs.setLong(2, file_id);
        this.updateFileStatePs.executeUpdate();
    }
    
    private void updateRecordState(final Connection c, final Long state, final Long recordId) throws SQLException {
        if (this.updateRecordStatePs == null) {
            this.updateRecordStatePs = c.prepareStatement("update empc_files_b_transactions set state_id = ? where id = ?");
        }
        System.out.println("B_file_processing.updateRecordState() line 476 " + state + " recordId" + recordId);
        ISLogger.getLogger().error((Object)("B_file_processing.updateRecordState() line 476 " + state + " recordId" + recordId));
        this.updateRecordStatePs.setLong(1, state);
        this.updateRecordStatePs.setLong(2, recordId);
        this.updateRecordStatePs.executeUpdate();
        c.commit();
    }
    
    private void deleteRecordError(final Connection c, final Long recordId) throws SQLException {
        if (this.deleteRecordErrorPs == null) {
            this.deleteRecordErrorPs = c.prepareStatement("delete from empc_b_records_prc_errors where record_id = ?");
        }
        this.deleteRecordErrorPs.setLong(1, recordId);
        this.deleteRecordErrorPs.executeUpdate();
    }
    
    private void insertRecordError(final Connection c, final Long recordId, final Exception e) throws SQLException {
        if (this.insertRecordErrorPs == null) {
            this.insertRecordErrorPs = c.prepareStatement("insert into empc_b_records_prc_errors (record_id, text) values(?, ?)");
        }
        this.insertRecordErrorPs.setLong(1, recordId);
        this.insertRecordErrorPs.setString(2, (CheckNull.getPstr(e).length() > 500) ? CheckNull.getPstr(e).substring(0, 500) : CheckNull.getPstr(e));
        this.insertRecordErrorPs.executeUpdate();
    }
    
    private void insertRecordError(final Connection c, final Long recordId, final String s) throws SQLException {
        if (this.insertRecordErrorPs == null) {
            this.insertRecordErrorPs = c.prepareStatement("insert into empc_b_records_prc_errors (record_id, text) values(?, ?)");
        }
        this.insertRecordErrorPs.setLong(1, recordId);
        this.insertRecordErrorPs.setString(2, s);
        this.insertRecordErrorPs.executeUpdate();
    }
    
    private B_file_transaction_record fillRecord(final ResultSet rs) throws SQLException {
        final B_file_transaction_record record = new B_file_transaction_record(rs.getLong("id"), rs.getLong("rec_num"), rs.getLong("EMPC_file_id"), rs.getString("Mtid"), rs.getString("Rec_centr"), rs.getString("Send_centr"), rs.getString("ISS_CMI"), rs.getString("Send_CMI"), rs.getString("Settl_CMI"), rs.getString("Acq_bank"), rs.getString("Acq_branch"), rs.getString("Member"), rs.getString("Clearing_group"), rs.getString("Merchant_accept"), rs.getString("Batch_nr"), rs.getString("Slip_nr"), rs.getString("Card"), rs.getString("Exp_date"), rs.getString("E_Date"), rs.getString("E_Time"), rs.getString("Tran_type"), rs.getString("Appr_code"), rs.getString("Appr_src"), rs.getString("Stan"), rs.getString("Ref_number"), rs.getString("Amount"), rs.getString("Cash_back"), rs.getString("Fee"), rs.getString("Currency"), rs.getString("Ccy_exp"), rs.getString("Sb_amount"), rs.getString("Sb_cshback"), rs.getString("Sb_fee"), rs.getString("Sbnk_ccy"), rs.getString("Sb_ccyexp"), rs.getString("Sb_cnvrate"), rs.getString("Sb_cnvdate"), rs.getString("I_amount"), rs.getString("I_cshback"), rs.getString("I_fee"), rs.getString("Ibnk_ccy"), rs.getString("I_ccyexp"), rs.getString("I_cnvrate"), rs.getString("I_cnvdate"), rs.getString("Abvr_name"), rs.getString("City"), rs.getString("Country"), rs.getString("Point_code"), rs.getString("MCC_code"), rs.getString("Terminal"), rs.getString("Batch_id"), rs.getString("Settl_nr"), rs.getString("Settl_date"), rs.getString("Acqref_nr"), rs.getString("File_id"), rs.getString("Ms_number"), rs.getString("File_date"), rs.getString("Source_algorithm"), rs.getString("Term_nr"), rs.getString("ECMC_Fee"), rs.getString("Tran_info"), rs.getString("Pr_amount"), rs.getString("Pr_cshback"), rs.getString("Pr_fee"), rs.getString("Prnk_ccy"), rs.getString("Pr_ccyexp"), rs.getString("Pr_cnvrate"), rs.getString("Pr_cnvdate"), rs.getString("E_Region"), rs.getString("Card_Type"), rs.getString("Proc_Class"), rs.getString("CARD_SEQ_NR"), rs.getString("Msg_type"), rs.getString("Org_msg_type"), rs.getString("Proc_code"), rs.getString("Msg_category"), rs.getString("Merchant"), rs.getString("MOTO_IND"), rs.getString("Susp_status"), rs.getString("Transact_row"), rs.getString("Authoriz_row"), rs.getString("FLD_043"), rs.getString("FLD_098"), rs.getString("FLD_102"), rs.getString("FLD_103"), rs.getString("FLD_104"), rs.getString("FLD_039"), rs.getString("FLD_SH6"), rs.getString("Batch_date"), rs.getString("Tr_fee"), rs.getString("FLD_040"), rs.getString("FLD_123_1"), rs.getString("EPI_42_48"), rs.getString("FLD_003"), rs.getString("MSC"), rs.getString("Account_nr"), rs.getString("EPI_42_48_FULL"), rs.getString("Other_Code"), rs.getString("FLD_015"), rs.getString("FLD_095"), rs.getString("Audit_date"), rs.getString("Other_fee1"), rs.getString("Other_fee2"), rs.getString("Other_fee3"), rs.getString("Other_fee4"), rs.getString("Other_fee5"));
        return record;
    }
    
    private String abc(final String terminalBranch, final String cardBranch) {
        final String own = "own";
        final String bank = "bank";
        final String other = "other";
        String result = null;
        if (terminalBranch.equals(cardBranch)) {
            result = own;
        }
        else {
            for (int i = 0; i < this.bankBranchesMap.size(); ++i) {
                if (this.bankBranchesMap.get(i).equals(cardBranch)) {
                    result = bank;
                    break;
                }
            }
        }
        if (result == null) {
            result = other;
        }
        return result;
    }
    
    private void loadBranchesMap(final Connection c) throws SQLException {
        if (this.bankBranchesMap != null) {
            return;
        }
        ResultSet rs = null;
        try {
            this.bankBranchesMap = new ArrayList<String>();
            if (this.dblinkPs == null) {
                this.dblinkPs = c.prepareStatement("SELECT branch FROM ss_dblink_branch");
            }
            rs = this.dblinkPs.executeQuery();
            while (rs.next()) {
                this.bankBranchesMap.add(rs.getString("branch"));
            }
        }
        finally {
            if (rs != null) {
                rs.close();
            }
        }
        if (rs != null) {
            rs.close();
        }
    }
    
    public void load_alias(final Connection c) throws SQLException {
        try {
            if (B_file_processing.aliasMap == null) {
                B_file_processing.aliasMap = new HashMap<String, String>();
                B_file_processing.psAlias = c.prepareStatement("select branch,user_name from ss_dblink_branch");
                B_file_processing.rsAlias = B_file_processing.psAlias.executeQuery();
                while (B_file_processing.rsAlias.next()) {
                    B_file_processing.aliasMap.put(B_file_processing.rsAlias.getString("branch"), B_file_processing.rsAlias.getString("user_name"));
                    System.out.println("rsAlias: " + B_file_processing.rsAlias.getString("branch"));
                }
                if (B_file_processing.psAlias != null) {
                    B_file_processing.psAlias.close();
                }
                if (B_file_processing.rsAlias != null) {
                    B_file_processing.rsAlias.close();
                }
            }
        }
        catch (Exception e) {
            B_file_processing.logger.error((Object)CheckNull.getPstr(e));
            e.printStackTrace();
        }
    }
    
    public void load_branch(final Connection c) throws SQLException {
        PreparedStatement psBranch = null;
        ResultSet rsBranch = null;
        try {
            if (B_file_processing.branchMap == null) {
                B_file_processing.branchMap = new HashMap<String, String>();
                psBranch = c.prepareStatement("select bank_id,header_id from s_mfo order by bank_id");
                rsBranch = psBranch.executeQuery();
                while (rsBranch.next()) {
                    B_file_processing.branchMap.put(rsBranch.getString("bank_id"), rsBranch.getString("header_id"));
                }
                psBranch.close();
                rsBranch.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Long getOperationId(final B_file_transaction_record record, final Connection c) throws SQLException {
        String operationId = "1";
        String searchOperation = "";
        String searchOperationTERM0 = "";
        String terminal = null;
        final String termType = record.getTerm_nr().substring(2, 3);
        final String oper = record.getTerm_nr().substring(3, 4);
        String cardBranch = null;
        terminal = record.getTerm_nr();
        if (record.getFLD_102() != null) {
            if (record.getTerm_nr().substring(0, 1).matches("[0-9]+")) {
                cardBranch = this.getBranch(record.getFLD_102().substring(2, 7), record.getMerchant().substring(0, 5));
            }
            else {
                if (this.terminalHostToHostPs2 == null) {
                    this.terminalHostToHostPs2 = c.prepareStatement("select ACC_UZS,BRANCH,ACC_17401 from TI_TERMINAL_TRANS_ACC where TERMINAL_ID = ?");
                }
                this.terminalHostToHostPs2.setString(1, record.getTerm_nr());
                final ResultSet rsTerm2 = this.terminalHostToHostPs2.executeQuery();
                if (rsTerm2.next()) {
                    cardBranch = this.getBranch(record.getFLD_102().substring(2, 7), rsTerm2.getString("BRANCH"));
                }
            }
        }
        else {
            cardBranch = "0";
        }
        final String termBranch = "1";
        final String trType = record.getTran_type();
        String mcc = record.getMCC_code();
        if (cardBranch.equals("1")) {
            return 2L;
        }
        if (terminal.equals("03610082") || terminal.equals("03610088")) {
            return 3L;
        }
        if (record.getTerm_nr().substring(0, 2).matches("[0-9]+")) {
            if (!this.isExsistMCC(mcc, c)) {
                mcc = "9999";
            }
            searchOperation = String.valueOf(termType) + "@" + oper + "@" + cardBranch + "@" + termBranch + "@" + trType + "@" + mcc + "@" + terminal;
            searchOperationTERM0 = String.valueOf(termType) + "@" + oper + "@" + cardBranch + "@" + termBranch + "@" + trType + "@" + mcc + "@" + "0";
        }
        else {
            searchOperation = "7@0@" + cardBranch + "@" + termBranch + "@" + trType + "@" + mcc + "@" + terminal;
            searchOperationTERM0 = "7@0@" + cardBranch + "@" + termBranch + "@" + trType + "@" + mcc + "@" + "0";
        }
        operationId = B_file_processing.transactions_config.get(searchOperation);
        if (operationId == null) {
            operationId = B_file_processing.transactions_config.get(searchOperationTERM0);
            if (operationId == null) {
                operationId = "1";
            }
        }
        ISLogger.getLogger().error((Object)searchOperation);
        ISLogger.getLogger().error((Object)searchOperationTERM0);
        return Long.parseLong(operationId);
    }
    
    public String getBranch(final String branchRecord, final String currentBranch) {
        if (branchRecord.equals(currentBranch)) {
            return "1";
        }
        final String headerCurBranch = B_file_processing.branchMap.get(currentBranch);
        final String headerRecordBranch = B_file_processing.branchMap.get(branchRecord);
        if (headerCurBranch.equals(headerRecordBranch)) {
            return "2";
        }
        return "3";
    }
    
    public void init(final Connection c) throws SQLException {
        this.c = c;
        this.operation = c.prepareStatement("select COUNT (*) from BF_TR_TEMPLATE t where t.operation_id= ?");
        this.exsistMCC = c.prepareStatement("select mcc from ss_empc_merch_mcc t where t.mcc=?");
    }
    
    public boolean isExsistMCC(final String mcc, final Connection c) throws SQLException {
        boolean isExsistMCC = false;
        this.exsistMCC.setString(1, (mcc != null) ? mcc : "9999");
        final ResultSet rs = this.exsistMCC.executeQuery();
        if (rs.next()) {
            isExsistMCC = true;
        }
        close(rs);
        return isExsistMCC;
    }
    
    public void initMFO(final Connection c) throws SQLException {
        this.c = c;
        this.terminal = c.prepareStatement("select acc from bf_globuz_acc_tr_all where terminal_id = ?");
        this.merchant = c.prepareStatement("select acc from bf_globuz_merchants_all where merchant = ?");
    }
    
    public static void close(final ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ISLogger.getLogger().error((Object)CheckNull.getPstr(e));
        }
    }
    
    public static void close(final CallableStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ISLogger.getLogger().error((Object)CheckNull.getPstr(e));
        }
    }
    
    public void getRealCardBranchAcc(final String login, final String parol, final String alias, final Connection c) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (B_file_processing.branchAccInHashmap == null) {
                B_file_processing.branchAccInHashmap = new HashMap<String, String>();
                B_file_processing.cardBranchInHashmap = new HashMap<String, String>();
                ps = c.prepareStatement("select c.real_card,c.branch, t.tranz_acct from BF_EMPC_ACCOUNTS t , humo_cards c where c.account_no = t.account_no");
                rs = ps.executeQuery();
                while (rs.next()) {
                    final String realCard = rs.getString("real_card");
                    final String branch = rs.getString("branch");
                    final String tranzAcct = rs.getString("tranz_acct");
                    if (realCard != null) {
                        B_file_processing.branchAccInHashmap.put(realCard, tranzAcct);
                        B_file_processing.cardBranchInHashmap.put(realCard, branch);
                    }
                    else {
                        ISLogger.getLogger().error((Object)"\u041f\u043e\u043b\u044f Real_card \u043f\u0443\u0441\u0442\u043e\u0439");
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ISLogger.getLogger().error((Object)e);
            return;
        }
        finally {
            closeRs(rs);
            closePs(ps);
        }
        closeRs(rs);
        closePs(ps);
    }
    
    public static void closePs(final PreparedStatement p) {
        try {
            if (p != null) {
                p.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ISLogger.getLogger().error((Object)e);
        }
    }
    
    public static void closeRs(final ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ISLogger.getLogger().error((Object)e);
        }
    }
    
    public static void closeCon(final Connection c) {
        try {
            if (c != null) {
                ConnectionPool.close(c);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ISLogger.getLogger().error((Object)e);
        }
    }
    
    public void load_config_map(final String login, final String parol, final String alias, final Connection c) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            this.login = login;
            this.parol = parol;
            this.alias = alias;
            B_file_processing.transactions_config = new HashMap<String, String>();
            ps = c.prepareStatement("SELECT * FROM HUMO_OPER_TYPE");
            rs = ps.executeQuery();
            while (rs.next()) {
                B_file_processing.transactions_config.put(String.valueOf(rs.getString("TERM_TYPE")) + "@" + rs.getString("OPER") + "@" + rs.getString("CARD_BRANCH") + "@" + rs.getString("TERM_BRANCH") + "@" + rs.getString("tr_type_b") + "@" + rs.getString("MCC") + "@" + rs.getString("TERMINAL"), rs.getString("id"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        finally {
            close(rs);
        }
        close(rs);
    }
}
