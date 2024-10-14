// 
// Decompiled by Procyon v0.5.36
// 

package com.is.file_reciever_srv.recievers.EMPC.EXPT;

import java.sql.Statement;
import com.is.file_reciever_srv.exception.Reciever_exception;
import com.is.ConnectionPool;
import com.is.utils.CheckNull;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Connection;
import com.is.ISLogger;
import org.apache.log4j.Logger;
import java.sql.CallableStatement;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.util.HashMap;
import java.sql.PreparedStatement;

public class Expt_records_processing
{
    public static String temp;
    private PreparedStatement terminal;
    private PreparedStatement merchant;
    private PreparedStatement agrementMerchant;
    private PreparedStatement updateState;
    private PreparedStatement updateFileState;
    private PreparedStatement insertError;
    private PreparedStatement deleteError;
    private PreparedStatement exsistMCC;
    private PreparedStatement psHistryCard;
    private static HashMap<String, String> transactions_config;
    private static HashMap<String, String> branchMap;
    private static HashMap<String, String> branchAccInHashmap;
    private static HashMap<String, String> cardBranchInHashmap;
    private PreparedStatement psBranch;
    private ResultSet rsBranch;
    private boolean file_has_errors;
    private String login;
    private String parol;
    private String alias;
    private SimpleDateFormat sdf;
    PreparedStatement ps_setparam;
    CallableStatement cs;
    String accMerch;
    String branch;
    private PreparedStatement terminalHostToHostPs;
    private PreparedStatement terminalHostToHostPsBranch;
    private ResultSet rsTerm;
    private static final Logger logger;
    
    static {
        Expt_records_processing.temp = "";
        Expt_records_processing.transactions_config = null;
        Expt_records_processing.branchMap = null;
        Expt_records_processing.branchAccInHashmap = null;
        Expt_records_processing.cardBranchInHashmap = null;
        logger = ISLogger.getLogger();
    }
    
    public Expt_records_processing() {
        this.terminal = null;
        this.merchant = null;
        this.agrementMerchant = null;
        this.updateState = null;
        this.updateFileState = null;
        this.insertError = null;
        this.deleteError = null;
        this.exsistMCC = null;
        this.psHistryCard = null;
        this.psBranch = null;
        this.rsBranch = null;
        this.file_has_errors = false;
        this.sdf = new SimpleDateFormat("dd.MM.yyyy");
        this.ps_setparam = null;
        this.cs = null;
        this.accMerch = null;
        this.branch = null;
        this.terminalHostToHostPs = null;
        this.terminalHostToHostPsBranch = null;
        this.rsTerm = null;
    }
    
    public void init(final Connection c) throws SQLException {
        this.updateState = c.prepareStatement("update empc_expt_records set state_id = ? where id = ?");
        this.updateFileState = c.prepareStatement("update empc_files set state_id = ? where id = ?");
        this.insertError = c.prepareStatement("insert into empc_expt_records_prc_errors (record_id, text) values(?, ?)");
        this.deleteError = c.prepareStatement("delete from empc_expt_records_prc_errors where record_id = ?");
        this.exsistMCC = c.prepareStatement("select mcc from ss_empc_merch_mcc t where t.mcc=?");
        this.psHistryCard = c.prepareStatement("select t.tranz_acct, t.branch from humo_cards_history t where t.real_card=?");
    }
    
    public void process_file(final String fileName, final Long file_id, final String login, final String parol, final String alias, final Connection c) {
        ISLogger.getLogger().error((Object)"start file");
        this.file_has_errors = false;
        PreparedStatement ps_records = null;
        ResultSet rs = null;
        Label_0833: {
            try {
                this.load_config_map(this.login = login, this.parol = parol, this.alias = alias, c);
                this.getRealCardBranchAcc(login, parol, alias, c);
                ps_records = c.prepareStatement("select * from empc_expt_records r where r.empc_file_id = ? and state_id != ? order by iss_mfo");
                ps_records.setLong(1, file_id);
                ps_records.setLong(2, 2L);
                rs = ps_records.executeQuery();
                this.updateState = c.prepareStatement("update empc_expt_records set state_id = ? where id = ?");
                this.updateFileState = c.prepareStatement("update empc_files set state_id = ? where id = ?");
                this.insertError = c.prepareStatement("insert into empc_expt_records_prc_errors (record_id, text) values(?, ?)");
                this.deleteError = c.prepareStatement("delete from empc_expt_records_prc_errors where record_id = ?");
                this.terminal = c.prepareStatement("select acc,acceptor_id,ACC_ATM from bf_globuz_acc_tr_all where terminal_id = ?");
                this.merchant = c.prepareStatement("select acc,merchant from bf_globuz_merchants_all where merchant = ?");
                this.agrementMerchant = c.prepareStatement("select bank_account, merchant from bf_globuz_agreements_all where merchant= ? and card_type='01'");
                int index = 0;
                while (rs.next()) {
                    ++index;
                    final EXPT_record record = new EXPT_record(rs.getLong("id"), rs.getLong("empc_file_id"), rs.getInt("state_id"), rs.getString("record_type"), rs.getLong("line_number"), rs.getString("client"), rs.getString("card_acct"), rs.getString("accnt_ccy"), rs.getString("card"), rs.getString("slip_nr"), rs.getString("ref_number"), rs.getDate("tran_date_time"), rs.getDate("rec_date"), rs.getDate("post_date"), rs.getString("deal_desc"), rs.getString("tran_type"), rs.getString("deb_cred"), rs.getString("tran_ccy"), rs.getLong("tran_amt"), rs.getLong("accnt_amt"), rs.getString("terminal"), rs.getString("mcc_code"), rs.getString("merchant"), rs.getString("abvr_name"), rs.getString("country"), rs.getString("city"), rs.getLong("proc_id"), rs.getLong("internal_no"), rs.getLong("product"), rs.getString("iss_mfo"), rs.getString("term_id"), rs.getString("tranz_acct"), rs.getString("tran_type2"), rs.getString("tran_amt2"));
                    ISLogger.getLogger().error((Object)("index = " + index));
                    ISLogger.getLogger().error((Object)record);
                    this.process_record(c, record, login, parol, alias, fileName);
                }
                if (!this.file_has_errors) {
                    this.updateFileState.setLong(1, 2L);
                    this.updateFileState.setLong(2, file_id);
                    this.updateFileState.executeUpdate();
                }
                c.commit();
            }
            catch (Exception e) {
                try {
                    c.rollback();
                }
                catch (SQLException exeption) {
                    ISLogger.getLogger().error((Object)e);
                }
                ISLogger.getLogger().error((Object)("Processing HUMO file error: " + CheckNull.getPstr(e)));
                close(rs);
                close(ps_records);
                if (c != null) {
                    try {
                        c.close();
                    }
                    catch (SQLException e2) {
                        ISLogger.getLogger().error((Object)e2);
                    }
                }
                break Label_0833;
            }
            finally {
                close(rs);
                close(ps_records);
                if (c != null) {
                    try {
                        c.close();
                    }
                    catch (SQLException e2) {
                        ISLogger.getLogger().error((Object)e2);
                    }
                }
            }
            close(rs);
            close(ps_records);
            if (c != null) {
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    ISLogger.getLogger().error((Object)e2);
                }
            }
        }
        ISLogger.getLogger().error((Object)"end file");
    }
    
    public String getBranch(final String branchRecord, final String currentBranch) {
        if (branchRecord.equals(currentBranch)) {
            return "1";
        }
        final String headerCurBranch = Expt_records_processing.branchMap.get(currentBranch);
        final String headerRecordBranch = Expt_records_processing.branchMap.get(branchRecord);
        if (headerCurBranch.equals(headerRecordBranch)) {
            return "2";
        }
        return "3";
    }
    
    public boolean isExsistMCC(final String mcc, final Connection c) throws SQLException {
        boolean isExsistMCC = false;
        this.exsistMCC.setString(1, mcc);
        final ResultSet rs = this.exsistMCC.executeQuery();
        if (rs.next()) {
            isExsistMCC = true;
        }
        close(rs);
        return isExsistMCC;
    }
    
    public Long getOperationId(final EXPT_record record, final String login, final String parol, final String alias, final Connection c) throws SQLException {
        String termBranch = null;
        final String trType = record.getTran_type();
        String operationId = "1";
        if (!trType.equals("11V")) {
            String searchOperation = "";
            String searchOperationTERM0 = "";
            final String termType = record.getTerm_id().substring(2, 3);
            final String oper = record.getTerm_id().substring(3, 4);
            final String cardBranch = "1";
            String mcc = record.getMcc_code();
            String komis = null;
            final String terminal = record.getTerm_id();
            if (record.getTran_amt2() == null) {
                komis = "0";
            }
            else {
                komis = "1";
            }
            if (record.getTerm_id().substring(0, 2).matches("[0-9]+")) {
                termBranch = this.getBranch(record.getMerchant().substring(0, 5), record.getIss_mfo());
                if (!this.isExsistMCC(mcc, c)) {
                    mcc = "9999";
                }
                ISLogger.getLogger().error((Object)("termType:" + termType));
                ISLogger.getLogger().error((Object)("oper:" + oper));
                ISLogger.getLogger().error((Object)("cardBranch:" + cardBranch));
                ISLogger.getLogger().error((Object)("termBranch:" + termBranch));
                ISLogger.getLogger().error((Object)("trType:" + trType));
                ISLogger.getLogger().error((Object)("mcc:" + mcc));
                ISLogger.getLogger().error((Object)("komis:" + komis));
                ISLogger.getLogger().error((Object)("terminal:" + terminal));
                searchOperation = String.valueOf(termType) + "@" + oper + "@" + cardBranch + "@" + termBranch + "@" + trType + "@" + mcc + "@" + komis + "@" + terminal;
                searchOperationTERM0 = String.valueOf(termType) + "@" + oper + "@" + cardBranch + "@" + termBranch + "@" + trType + "@" + mcc + "@" + komis + "@" + "0";
                ISLogger.getLogger().error((Object)("777 " + searchOperation));
                ISLogger.getLogger().error((Object)("777 " + searchOperationTERM0));
            }
            else {
                final String humo_host_term = ConnectionPool.getValue("humo_host_term");
                if (!humo_host_term.equals("")) {
                    if (this.terminalHostToHostPsBranch == null) {
                        this.terminalHostToHostPsBranch = c.prepareStatement("select ACC_UZS,BRANCH from TI_TERMINAL_TRANS_ACC where TERMINAL_ID = ?");
                    }
                    this.terminalHostToHostPsBranch.setString(1, record.getTerm_id());
                    final ResultSet rsTerm = this.terminalHostToHostPsBranch.executeQuery();
                    String branch = null;
                    if (rsTerm.next()) {
                        branch = rsTerm.getString("BRANCH");
                    }
                    termBranch = this.getBranch(branch, record.getIss_mfo());
                    searchOperation = "7@0@1@" + termBranch + "@" + trType + "@" + mcc + "@" + komis + "@" + terminal;
                    searchOperationTERM0 = "7@0@1@" + termBranch + "@" + trType + "@" + mcc + "@" + komis + "@" + "0";
                }
                else {
                    searchOperation = "7@0@" + cardBranch + "@" + 3 + "@" + trType + "@" + mcc + "@" + komis + "@" + terminal;
                    searchOperationTERM0 = "7@0@" + cardBranch + "@" + 3 + "@" + trType + "@" + mcc + "@" + komis + "@" + "0";
                    ISLogger.getLogger().error((Object)("888 " + searchOperation));
                }
            }
            operationId = Expt_records_processing.transactions_config.get(searchOperation);
            if (operationId == null) {
                operationId = Expt_records_processing.transactions_config.get(searchOperationTERM0);
                if (operationId == null) {
                    operationId = "1";
                    ISLogger.getLogger().error((Object)searchOperation);
                    ISLogger.getLogger().error((Object)searchOperationTERM0);
                }
            }
        }
        else {
            operationId = Expt_records_processing.transactions_config.get("0@0@1@0@11V@0@0@0");
        }
        return Long.parseLong(operationId);
    }
    
    public void process_record(final Connection cMFO, final EXPT_record record, final String login, final String parol, final String alias, final String fileName) throws SQLException {
        this.clearParam(cMFO);
        boolean err = false;
        boolean mfoErr = false;
        ISLogger.getLogger().error((Object)"start record");
        final CallableStatement cs_prep = null;
        ResultSet rsTerm = null;
        ResultSet rsMerch = null;
        ResultSet rsAgreMerch = null;
        ResultSet rsHistryCard = null;
        Label_3138: {
            try {
                if (!record.getTran_type().equals("110")) {
                    this.branch = Expt_records_processing.cardBranchInHashmap.get(record.getCard());
                    if (this.branch != null) {
                        if (!this.branch.equals(record.getIss_mfo())) {
                            mfoErr = true;
                            this.updateState.setInt(1, 3);
                            this.updateState.setLong(2, record.getId());
                            this.updateState.executeQuery();
                            this.deleteError.setLong(1, record.getId());
                            this.deleteError.executeUpdate();
                            this.insertError.setLong(1, record.getId());
                            this.insertError.setString(2, "\u041c\u0424\u041e \u043a\u0430\u0440\u0442\u044b " + record.getCard() + " \u0432 \u0431\u0430\u043d\u043a\u0438 \u0438 \u0432 HUMO \u0440\u0430\u0437\u043b\u0438\u0447\u0430\u0435\u0442\u0441\u044f");
                            this.insertError.executeUpdate();
                            this.file_has_errors = true;
                        }
                        alter_session_init(cMFO, this.branch);
                    }
                    else {
                        this.psHistryCard.setString(1, record.getCard());
                        rsHistryCard = this.psHistryCard.executeQuery();
                        if (rsHistryCard.next()) {
                            alter_session_init(cMFO, this.branch = rsHistryCard.getString("branch"));
                        }
                        else {
                            ISLogger.getLogger().error((Object)("\u043a\u0430\u0440\u0442\u0430 " + record.getCard() + " \u0432 \u0442\u0430\u0431\u043b. humo_cards_history \u043d\u0435 \u0441\u0443\u0449\u0435\u0441\u0442\u0432\u0443\u0435\u0442"));
                        }
                    }
                    final Long operation_id = this.getOperationId(record, login, parol, alias, cMFO);
                    System.out.println("operation_id:" + operation_id);
                    if (operation_id == 1L || operation_id == 0L) {
                        ISLogger.getLogger().error((Object)"\u041d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u0430 \u043e\u043f\u0435\u0440\u0430\u0446\u0438\u044f");
                        System.out.println("\u041d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u0430 \u043e\u043f\u0435\u0440\u0430\u0446\u0438\u044f");
                        this.updateState.setInt(1, 3);
                        this.updateState.setLong(2, record.getId());
                        this.updateState.executeQuery();
                        this.deleteError.setLong(1, record.getId());
                        this.deleteError.executeUpdate();
                        this.insertError.setLong(1, record.getId());
                        this.insertError.setString(2, "\u041d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u0430 \u043e\u043f\u0435\u0440\u0430\u0446\u0438\u044f");
                        this.insertError.executeUpdate();
                        this.file_has_errors = true;
                    }
                    else {
                        System.out.println("Tranz_acct " + record.getTranz_acct() + " Card " + record.getCard() + " Hashmap " + Expt_records_processing.branchAccInHashmap.get(record.getCard()) + "  " + Expt_records_processing.branchAccInHashmap.size());
                        ISLogger.getLogger().error((Object)("ACC_CARD:" + Expt_records_processing.branchAccInHashmap.get(record.getCard())));
                        ISLogger.getLogger().error((Object)("record.getTerm_id()=" + record.getTerm_id()));
                        this.terminal.setString(1, record.getTerm_id());
                        Expt_records_processing.temp = "boool = " + (Expt_records_processing.branchAccInHashmap.get(record.getCard()) != null) + " ";
                        System.out.println("");
                        rsTerm = this.terminal.executeQuery();
                        String emptyTerm = " yes '" + record.getTerm_id() + "' ";
                        if (rsTerm.next()) {
                            emptyTerm = " no '" + rsTerm.getString("ACC") + "' ";
                            ISLogger.getLogger().error((Object)("ACC_TERM:" + rsTerm.getString("ACC") + " record id:" + record.getId()));
                            this.setParam(cMFO, "ACC_TERM", rsTerm.getString("ACC"));
                            this.setParam(cMFO, "ACC_ATM", rsTerm.getString("ACC_ATM"));
                            this.setParam(cMFO, "ACC_ATM_BRANCH", "0" + rsTerm.getString("acceptor_id").substring(1, 5));
                            this.setParam(cMFO, "ACC_TERM_BRANCH", "0" + rsTerm.getString("acceptor_id").substring(1, 5));
                            Expt_records_processing.logger.info((Object)("ACC_TERM=" + rsTerm.getString("ACC")));
                        }
                        else if (!ConnectionPool.getValue("humo_host_term").equals("")) {
                            if (this.terminalHostToHostPs == null) {
                                this.terminalHostToHostPs = cMFO.prepareStatement("select ACC_UZS,BRANCH,ACC_17401 from TI_TERMINAL_TRANS_ACC where TERMINAL_ID = ?");
                            }
                            this.terminalHostToHostPs.setString(1, record.getTerm_id());
                            rsTerm = this.terminalHostToHostPs.executeQuery();
                            if (rsTerm.next()) {
                                this.setParam(cMFO, "ACC_TERM", rsTerm.getString("ACC_UZS"));
                                this.setParam(cMFO, "ACC_17401", rsTerm.getString("ACC_17401"));
                                this.setParam(cMFO, "ACC_ATM", rsTerm.getString("ACC_UZS"));
                                this.setParam(cMFO, "ACC_TERM_BRANCH", rsTerm.getString("BRANCH"));
                                this.setParam(cMFO, "ACC_17401_BRANCH", rsTerm.getString("BRANCH"));
                                this.setParam(cMFO, "ACC_ATM_BRANCH", rsTerm.getString("BRANCH"));
                                System.out.println("ACC_TERM_BRANCH" + rsTerm.getString("BRANCH"));
                                ISLogger.getLogger().error((Object)("ACC_TERM_BRANCH" + rsTerm.getString("BRANCH")));
                                System.out.println("ACC_TERM: " + rsTerm.getString("ACC_UZS"));
                                ISLogger.getLogger().error((Object)("ACC_ATM: " + rsTerm.getString("ACC_UZS")));
                                ISLogger.getLogger().error((Object)("ACC_17401: " + rsTerm.getString("ACC_17401")));
                            }
                        }
                        String merchantId = "";
                        merchantId = String.valueOf(merchantId) + record.getMerchant();
                        merchantId = merchantId.substring(1);
                        merchantId = "J" + merchantId;
                        Expt_records_processing.logger.info((Object)("merchantId=" + merchantId));
                        this.merchant.setString(1, merchantId);
                        rsMerch = this.merchant.executeQuery();
                        final String isEmpty = " yes '" + merchantId + "' ";
                        if (rsMerch.next()) {
                            if (rsMerch.getString("acc") == null) {
                                this.agrementMerchant.setString(1, merchantId);
                                rsAgreMerch = this.agrementMerchant.executeQuery();
                                if (rsAgreMerch.next()) {
                                    if (rsAgreMerch.getString("bank_account").length() > 20) {
                                        this.setParam(cMFO, "ACC_MERCH", rsAgreMerch.getString("bank_account").substring(5));
                                        this.setParam(cMFO, "ACC_MERCH_BRANCH", rsAgreMerch.getString("bank_account").substring(0, 5));
                                        ISLogger.getLogger().error((Object)("ACC_MERCH:" + rsAgreMerch.getString("bank_account").substring(5) + " record id:" + record.getId()));
                                    }
                                    else {
                                        this.setParam(cMFO, "ACC_MERCH", rsAgreMerch.getString("bank_account"));
                                        this.setParam(cMFO, "ACC_MERCH_BRANCH", record.getMerchant().substring(0, 5));
                                        ISLogger.getLogger().error((Object)("ACC_MERCH:" + rsAgreMerch.getString("bank_account") + " record id:" + record.getId()));
                                    }
                                }
                            }
                            else {
                                this.setParam(cMFO, "ACC_MERCH", rsMerch.getString("acc"));
                                this.setParam(cMFO, "ACC_MERCH_BRANCH", record.getMerchant().substring(0, 5));
                                ISLogger.getLogger().error((Object)("ACC_MERCH:" + rsMerch.getString("acc") + " record id:" + record.getId()));
                            }
                        }
                        if (record.getCard() != null) {
                            if (Expt_records_processing.branchAccInHashmap.get(record.getCard()) != null) {
                                this.setParam(cMFO, "ACC_CARD", Expt_records_processing.branchAccInHashmap.get(record.getCard()));
                            }
                            else {
                                this.psHistryCard.setString(1, record.getCard());
                                rsHistryCard = this.psHistryCard.executeQuery();
                                if (rsHistryCard.next()) {
                                    this.setParam(cMFO, "ACC_CARD", rsHistryCard.getString("tranz_acct"));
                                }
                                else {
                                    ISLogger.getLogger().error((Object)("\u041a\u0430\u0440\u0442\u0430 " + record.getCard() + " \u0432 \u0442\u0430\u0431\u043b. humo_cards \u0438 humo_cards_history \u043d\u0435 \u0441\u0443\u0449\u0435\u0441\u0442\u0432\u0443\u0435\u0442"));
                                    err = true;
                                    this.updateState.setInt(1, 3);
                                    this.updateState.setLong(2, record.getId());
                                    this.updateState.executeQuery();
                                    this.deleteError.setLong(1, record.getId());
                                    this.deleteError.executeUpdate();
                                    this.insertError.setLong(1, record.getId());
                                    this.insertError.setString(2, "\u041a\u0430\u0440\u0442\u0430 " + record.getCard() + " \u0432 \u0442\u0430\u0431\u043b. humo_cards \u0438 humo_cards_history \u043d\u0435 \u0441\u0443\u0449\u0435\u0441\u0442\u0432\u0443\u0435\u0442");
                                    this.insertError.executeUpdate();
                                    this.file_has_errors = true;
                                }
                            }
                        }
                        else {
                            err = true;
                            this.updateState.setInt(1, 3);
                            this.updateState.setLong(2, record.getId());
                            this.updateState.executeQuery();
                            this.deleteError.setLong(1, record.getId());
                            this.deleteError.executeUpdate();
                            this.insertError.setLong(1, record.getId());
                            this.insertError.setString(2, "\u041d\u043e\u043c\u0435\u0440 \u043a\u0430\u0440\u0442\u044b \u0432 \u0442\u0430\u0431\u043b. humo_cards \u043f\u0443\u0441\u0442\u043e\u0439");
                            this.insertError.executeUpdate();
                            this.file_has_errors = true;
                        }
                        this.setParam(cMFO, "TRAN_DATE_TIME", this.sdf.format(record.getTran_date_time()));
                        this.setParam(cMFO, "REC_DATE", this.sdf.format(record.getRec_date()));
                        this.setParam(cMFO, "OPERATION_ID", operation_id.toString());
                        this.setParam(cMFO, "BRANCH", record.getIss_mfo());
                        this.setParam(cMFO, "PARENT_GROUP_ID", "198");
                        this.setParam(cMFO, "GROUP_ID", "198");
                        this.setParam(cMFO, "PARENT_DEAL_ID", "106");
                        this.setParam(cMFO, "SUMMA", record.getTran_amt().toString());
                        this.setParam(cMFO, "PARENT_ID", record.getId().toString());
                        this.setParam(cMFO, "ACTION_ID", "0");
                        this.setParam(cMFO, "S_DEAL_ID", "106");
                        this.setParam(cMFO, "ACC_CARD_BRANCH", this.branch);
                        this.setParam(cMFO, "CARD", record.getCard());
                        this.setParam(cMFO, "TERMINAL", record.getTerm_id());
                        this.setParam(cMFO, "FILE", fileName);
                        this.setParam(cMFO, "MERCHANT", merchantId);
                        this.setParam(cMFO, "REF_NUMBER", record.getRef_number());
                        if (record.getTran_amt2() != null) {
                            this.setParam(cMFO, "KOMIS", record.getTran_amt2().toString());
                        }
                        else {
                            this.setParam(cMFO, "KOMIS", "0");
                            ISLogger.getLogger().error((Object)"Tran_amt2 \u043f\u0443\u0441\u0442\u043e\u0439");
                        }
                        System.out.println("FILE:" + fileName);
                        ISLogger.getLogger().error((Object)("FILE:" + fileName));
                        ISLogger.getLogger().error((Object)("ACC_CARD_BRANCH: " + Expt_records_processing.cardBranchInHashmap.get(record.getCard())));
                        if (this.branch == null) {
                            ISLogger.getLogger().error((Object)("\u0432 \u0442\u0440\u0430\u043d\u0437\u0430\u043a\u0446\u0438\u0438 " + record.getId().toString() + " \u043f\u043e\u043b\u044f Branch \u043f\u0443\u0441\u0442\u043e\u0439"));
                        }
                        if (!err && !mfoErr) {
                            (this.cs = cMFO.prepareCall("{call HUMO_TR_DOC.PAYDOC_TRANS(?,?,?,?,?,?)}")).setString(1, this.branch);
                            this.cs.setString(2, operation_id.toString());
                            this.cs.setString(3, record.getTran_amt().toString());
                            this.cs.setString(4, record.getId().toString());
                            this.cs.setString(5, "198");
                            this.cs.registerOutParameter(6, 12);
                            this.cs.executeUpdate();
                            ISLogger.getLogger().error((Object)("DOC PARAM:" + this.cs.getString(6)));
                            System.out.println("DOC PARAM:" + this.cs.getString(6));
                        }
                        if (!err && !mfoErr) {
                            ISLogger.getLogger().error((Object)"start HUMO_TR_DOC");
                            Expt_records_processing.logger.info((Object)("crBankDoc: " + crBankDoc(cMFO, this.cs.getString(6), 19)));
                            ISLogger.getLogger().error((Object)"end HUMO_TR_DOC");
                        }
                        if (!err && !mfoErr) {
                            this.updateState.setInt(1, 2);
                            this.updateState.setLong(2, record.getId());
                            this.updateState.executeUpdate();
                            this.deleteError.setLong(1, record.getId());
                            this.deleteError.executeUpdate();
                        }
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                Expt_records_processing.logger.error((Object)CheckNull.getPstr(e));
                cMFO.rollback();
                this.updateState.setInt(1, 3);
                this.updateState.setLong(2, record.getId());
                this.updateState.executeQuery();
                this.file_has_errors = true;
                this.updateFileState.setLong(1, 3L);
                this.updateFileState.setLong(2, record.getEmpc_file_id());
                this.updateFileState.executeUpdate();
                this.deleteError.setLong(1, record.getId());
                this.deleteError.executeUpdate();
                this.insertError.setLong(1, record.getId());
                this.insertError.setString(2, e.getMessage());
                if (this.insertError.executeUpdate() == 0) {
                    System.out.println("Error text is not inserted.");
                }
                break Label_3138;
            }
            finally {
                close(rsMerch);
                close(rsTerm);
                cMFO.commit();
            }
            close(rsMerch);
            close(rsTerm);
            cMFO.commit();
        }
        ISLogger.getLogger().error((Object)"end record");
    }
    
    public void setParam(final Connection cMFO, final String name, final String value) throws SQLException {
        (this.ps_setparam = cMFO.prepareStatement("{call param.setParam(?, ?)}")).setString(1, name);
        this.ps_setparam.setString(2, value);
        this.ps_setparam.execute();
    }
    
    public void clearParam(final Connection cMFO) throws SQLException {
        (this.ps_setparam = cMFO.prepareCall("{ call Param.clearparam() }")).execute();
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
        close(cs);
        return r;
    }
    
    public void close() throws SQLException {
        if (this.terminal != null) {
            this.terminal.close();
        }
        if (this.merchant != null) {
            this.merchant.close();
        }
        if (this.updateState != null) {
            this.updateState.close();
        }
        if (this.updateFileState != null) {
            this.updateFileState.close();
        }
        if (this.insertError != null) {
            this.insertError.close();
        }
        if (this.deleteError != null) {
            this.deleteError.close();
        }
        if (this.exsistMCC != null) {
            this.exsistMCC.close();
        }
    }
    
    public void load_branch(final Connection c) throws SQLException {
        try {
            this.init(c);
            if (Expt_records_processing.branchMap == null) {
                Expt_records_processing.branchMap = new HashMap<String, String>();
                this.psBranch = c.prepareStatement("select bank_id,header_id from s_mfo order by bank_id");
                this.rsBranch = this.psBranch.executeQuery();
                while (this.rsBranch.next()) {
                    Expt_records_processing.branchMap.put(this.rsBranch.getString("bank_id"), this.rsBranch.getString("header_id"));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }
        finally {
            close(this.rsBranch);
            close(this.psBranch);
        }
        close(this.rsBranch);
        close(this.psBranch);
    }
    
    public void load_config_map(final String login, final String parol, final String alias, final Connection c) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            this.login = login;
            this.parol = parol;
            this.alias = alias;
            Expt_records_processing.transactions_config = new HashMap<String, String>();
            ps = c.prepareStatement("SELECT * FROM HUMO_OPER_TYPE");
            rs = ps.executeQuery();
            while (rs.next()) {
                Expt_records_processing.transactions_config.put(String.valueOf(rs.getString("TERM_TYPE")) + "@" + rs.getString("OPER") + "@" + rs.getString("CARD_BRANCH") + "@" + rs.getString("TERM_BRANCH") + "@" + rs.getString("tr_type_expt") + "@" + rs.getString("MCC") + "@" + rs.getString("KOMIS") + "@" + rs.getString("TERMINAL"), rs.getString("id"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        finally {
            close(rs);
            close(ps);
        }
        close(rs);
        close(ps);
    }
    
    public static void alter_session_init(final Connection c, final String branch) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement st = null;
        try {
            ps = c.prepareStatement("select t.user_name res from ss_dblink_branch t where t.branch = ?");
            ps.setString(1, branch);
            rs = ps.executeQuery();
            String alias = null;
            if (!rs.next()) {
                throw new Reciever_exception("Wrong branch: " + branch);
            }
            alias = rs.getString("res");
            ISLogger.getLogger().error((Object)("ALIAS:" + alias));
            st = c.createStatement();
            st.executeUpdate("alter session set nls_date_format='dd.mm.yyyy'");
            st.executeUpdate("ALTER SESSION SET CURRENT_SCHEMA=" + alias);
            st.execute("{call info.init()}");
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (st != null) {
                st.close();
            }
        }
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (st != null) {
            st.close();
        }
    }
    
    public void updateFileState(final Long fileId, final Connection c) {
        long errNumber = 0L;
        PreparedStatement ps = null;
        PreparedStatement updateFileState = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement("SELECT count(*)nr FROM empc_expt_records WHERE empc_file_id = ? AND state_id != ?");
            ps.setLong(1, fileId);
            ps.setLong(2, 2L);
            rs = ps.executeQuery();
            if (rs.next()) {
                errNumber = rs.getLong("nr");
                if (errNumber == 0L) {
                    updateFileState = c.prepareStatement("update empc_files set state_id = ? where id = ?");
                    updateFileState.setLong(1, 2L);
                    updateFileState.setLong(2, fileId);
                    updateFileState.executeUpdate();
                    c.commit();
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        finally {
            close(rs);
            close(ps);
            close(updateFileState);
        }
        close(rs);
        close(ps);
        close(updateFileState);
    }
    
    public void getRealCardBranchAcc(final String login, final String parol, final String alias, final Connection c) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (Expt_records_processing.branchAccInHashmap == null) {
                Expt_records_processing.branchAccInHashmap = new HashMap<String, String>();
                Expt_records_processing.cardBranchInHashmap = new HashMap<String, String>();
                ps = c.prepareStatement("select c.real_card,c.branch, t.tranz_acct from BF_EMPC_ACCOUNTS t , humo_cards c where c.account_no = t.account_no");
                rs = ps.executeQuery();
                int i = 0;
                while (rs.next()) {
                    System.out.println("hashmap:" + i++);
                    final String realCard = rs.getString("real_card");
                    final String branch = rs.getString("branch");
                    final String tranzAcct = rs.getString("tranz_acct");
                    if (realCard != null) {
                        Expt_records_processing.branchAccInHashmap.put(realCard, tranzAcct);
                        Expt_records_processing.cardBranchInHashmap.put(realCard, branch);
                    }
                    else {
                        ISLogger.getLogger().error((Object)"\u041f\u043e\u043b\u044f Real_card \u043f\u0443\u0441\u0442\u043e\u0439");
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ISLogger.getLogger().error((Object)CheckNull.getPstr(e));
            return;
        }
        finally {
            closeRs(rs);
            closePs(ps);
        }
        closeRs(rs);
        closePs(ps);
    }
    
    public static String getCardAcc1(final Connection c, final String ACCOUNT_NO) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String cardAcct = null;
        try {
            ps = c.prepareStatement("select CARD_ACCT from bf_EMPC_accounts where ACCOUNT_NO=?");
            ps.setString(1, ACCOUNT_NO);
            System.out.println("ACCOUNT_NO:" + ACCOUNT_NO);
            rs = ps.executeQuery();
            while (rs.next()) {
                cardAcct = rs.getString("CARD_ACCT");
                System.out.println("cardAcct:" + cardAcct);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ISLogger.getLogger().error((Object)e);
            return cardAcct;
        }
        finally {
            close(rs);
            close(ps);
        }
        close(rs);
        close(ps);
        return cardAcct;
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
    
    public static void close(final PreparedStatement ps) {
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
}
