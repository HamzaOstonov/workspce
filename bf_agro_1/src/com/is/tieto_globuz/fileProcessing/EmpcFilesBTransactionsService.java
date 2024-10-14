// 
// Decompiled by Procyon v0.5.36
// 

package com.is.tieto_globuz.fileProcessing;

import java.sql.PreparedStatement;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import com.is.ConnectionPool;
import java.util.ArrayList;
import java.util.List;

public class EmpcFilesBTransactionsService
{
    private static String psql1;
    private static String psql2;
    private static String msql;
    
    static {
        EmpcFilesBTransactionsService.psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
        EmpcFilesBTransactionsService.psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
        EmpcFilesBTransactionsService.msql = "SELECT * FROM TF_EmpcFilesBTransactions ";
    }
    
    public List<EmpcFilesBTransactions> getEmpcFilesBTransactions() {
        final List<EmpcFilesBTransactions> list = new ArrayList<EmpcFilesBTransactions>();
        Connection c = null;
        try {
            c = ConnectionPool.getConnection();
            final Statement s = c.createStatement();
            final ResultSet rs = s.executeQuery("SELECT * FROM TF_EmpcFilesBTransactions");
            while (rs.next()) {
                list.add(new EmpcFilesBTransactions(rs.getLong("id"), rs.getString("rec_num"), rs.getString("empc_file_id"), rs.getString("mtid"), rs.getString("rec_centr"), rs.getString("send_centr"), rs.getString("iss_cmi"), rs.getString("send_cmi"), rs.getString("settl_cmi"), rs.getString("acq_bank"), rs.getString("acq_branch"), rs.getString("member"), rs.getString("clearing_group"), rs.getString("merchant_accept"), rs.getString("batch_nr"), rs.getString("slip_nr"), rs.getString("card"), rs.getString("exp_date"), rs.getString("e_date"), rs.getString("e_time"), rs.getString("tran_type"), rs.getString("appr_code"), rs.getString("appr_src"), rs.getString("stan"), rs.getString("ref_number"), rs.getString("amount"), rs.getString("cash_back"), rs.getString("fee"), rs.getString("currency"), rs.getString("ccy_exp"), rs.getString("sb_amount"), rs.getString("sb_cshback"), rs.getString("sb_fee"), rs.getString("sbnk_ccy"), rs.getString("sb_ccyexp"), rs.getString("sb_cnvrate"), rs.getString("sb_cnvdate"), rs.getString("i_amount"), rs.getString("i_cshback"), rs.getString("i_fee"), rs.getString("ibnk_ccy"), rs.getString("i_ccyexp"), rs.getString("i_cnvrate"), rs.getString("i_cnvdate"), rs.getString("abvr_name"), rs.getString("city"), rs.getString("country"), rs.getString("point_code"), rs.getString("mcc_code"), rs.getString("terminal"), rs.getString("batch_id"), rs.getString("settl_nr"), rs.getString("settl_date"), rs.getString("acqref_nr"), rs.getString("file_id"), rs.getString("ms_number"), rs.getString("file_date"), rs.getString("source_algorithm"), rs.getString("term_nr"), rs.getString("ecmc_fee"), rs.getString("tran_info"), rs.getString("pr_amount"), rs.getString("pr_cshback"), rs.getString("pr_fee"), rs.getString("prnk_ccy"), rs.getString("pr_ccyexp"), rs.getString("pr_cnvrate"), rs.getString("pr_cnvdate"), rs.getString("e_region"), rs.getString("card_type"), rs.getString("proc_class"), rs.getString("card_seq_nr"), rs.getString("msg_type"), rs.getString("org_msg_type"), rs.getString("proc_code"), rs.getString("msg_category"), rs.getString("merchant"), rs.getString("moto_ind"), rs.getString("susp_status"), rs.getString("transact_row"), rs.getString("authoriz_row"), rs.getString("fld_043"), rs.getString("fld_098"), rs.getString("fld_102"), rs.getString("fld_103"), rs.getString("fld_104"), rs.getString("fld_039"), rs.getString("fld_sh6"), rs.getString("batch_date"), rs.getString("tr_fee"), rs.getString("fld_040"), rs.getString("fld_123_1"), rs.getString("epi_42_48"), rs.getString("fld_003"), rs.getString("msc"), rs.getString("account_nr"), rs.getString("epi_42_48_full"), rs.getString("other_code"), rs.getString("fld_015"), rs.getString("fld_095"), rs.getString("audit_date"), rs.getString("other_fee1"), rs.getString("other_fee2"), rs.getString("other_fee3"), rs.getString("other_fee4"), rs.getString("other_fee5"), rs.getString("state_id")));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return list;
        }
        finally {
            ConnectionPool.close(c);
        }
        ConnectionPool.close(c);
        return list;
    }
    
    private static String getCond(final List<FilterField> flfields) {
        if (flfields.size() > 0) {
            return " and ";
        }
        return " where ";
    }
    
    private static List<FilterField> getFilterFields(final EmpcFilesBTransactionsFilter filter) {
        final List<FilterField> flfields = new ArrayList<FilterField>();
        if (!CheckNull.isEmpty(filter.getId())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "id=?", (Object)filter.getId()));
        }
        if (!CheckNull.isEmpty(filter.getRec_num())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "rec_num=?", (Object)filter.getRec_num()));
        }
        if (!CheckNull.isEmpty(filter.getEmpc_file_id())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "empc_file_id=?", (Object)filter.getEmpc_file_id()));
        }
        if (!CheckNull.isEmpty(filter.getMtid())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "mtid=?", (Object)filter.getMtid()));
        }
        if (!CheckNull.isEmpty(filter.getRec_centr())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "rec_centr=?", (Object)filter.getRec_centr()));
        }
        if (!CheckNull.isEmpty(filter.getSend_centr())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "send_centr=?", (Object)filter.getSend_centr()));
        }
        if (!CheckNull.isEmpty(filter.getIss_cmi())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "iss_cmi=?", (Object)filter.getIss_cmi()));
        }
        if (!CheckNull.isEmpty(filter.getSend_cmi())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "send_cmi=?", (Object)filter.getSend_cmi()));
        }
        if (!CheckNull.isEmpty(filter.getSettl_cmi())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "settl_cmi=?", (Object)filter.getSettl_cmi()));
        }
        if (!CheckNull.isEmpty(filter.getAcq_bank())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "acq_bank=?", (Object)filter.getAcq_bank()));
        }
        if (!CheckNull.isEmpty(filter.getAcq_branch())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "acq_branch=?", (Object)filter.getAcq_branch()));
        }
        if (!CheckNull.isEmpty(filter.getMember())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "member=?", (Object)filter.getMember()));
        }
        if (!CheckNull.isEmpty(filter.getClearing_group())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "clearing_group=?", (Object)filter.getClearing_group()));
        }
        if (!CheckNull.isEmpty(filter.getMerchant_accept())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "merchant_accept=?", (Object)filter.getMerchant_accept()));
        }
        if (!CheckNull.isEmpty(filter.getBatch_nr())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "batch_nr=?", (Object)filter.getBatch_nr()));
        }
        if (!CheckNull.isEmpty(filter.getSlip_nr())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "slip_nr=?", (Object)filter.getSlip_nr()));
        }
        if (!CheckNull.isEmpty(filter.getCard())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "card=?", (Object)filter.getCard()));
        }
        if (!CheckNull.isEmpty(filter.getExp_date())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "exp_date=?", (Object)filter.getExp_date()));
        }
        if (!CheckNull.isEmpty(filter.getE_date())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "e_date=?", (Object)filter.getE_date()));
        }
        if (!CheckNull.isEmpty(filter.getE_time())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "e_time=?", (Object)filter.getE_time()));
        }
        if (!CheckNull.isEmpty(filter.getTran_type())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "tran_type=?", (Object)filter.getTran_type()));
        }
        if (!CheckNull.isEmpty(filter.getAppr_code())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "appr_code=?", (Object)filter.getAppr_code()));
        }
        if (!CheckNull.isEmpty(filter.getAppr_src())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "appr_src=?", (Object)filter.getAppr_src()));
        }
        if (!CheckNull.isEmpty(filter.getStan())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "stan=?", (Object)filter.getStan()));
        }
        if (!CheckNull.isEmpty(filter.getRef_number())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "ref_number=?", (Object)filter.getRef_number()));
        }
        if (!CheckNull.isEmpty(filter.getAmount())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "amount=?", (Object)filter.getAmount()));
        }
        if (!CheckNull.isEmpty(filter.getCash_back())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "cash_back=?", (Object)filter.getCash_back()));
        }
        if (!CheckNull.isEmpty(filter.getFee())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "fee=?", (Object)filter.getFee()));
        }
        if (!CheckNull.isEmpty(filter.getCurrency())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "currency=?", (Object)filter.getCurrency()));
        }
        if (!CheckNull.isEmpty(filter.getCcy_exp())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "ccy_exp=?", (Object)filter.getCcy_exp()));
        }
        if (!CheckNull.isEmpty(filter.getSb_amount())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "sb_amount=?", (Object)filter.getSb_amount()));
        }
        if (!CheckNull.isEmpty(filter.getSb_cshback())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "sb_cshback=?", (Object)filter.getSb_cshback()));
        }
        if (!CheckNull.isEmpty(filter.getSb_fee())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "sb_fee=?", (Object)filter.getSb_fee()));
        }
        if (!CheckNull.isEmpty(filter.getSbnk_ccy())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "sbnk_ccy=?", (Object)filter.getSbnk_ccy()));
        }
        if (!CheckNull.isEmpty(filter.getSb_ccyexp())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "sb_ccyexp=?", (Object)filter.getSb_ccyexp()));
        }
        if (!CheckNull.isEmpty(filter.getSb_cnvrate())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "sb_cnvrate=?", (Object)filter.getSb_cnvrate()));
        }
        if (!CheckNull.isEmpty(filter.getSb_cnvdate())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "sb_cnvdate=?", (Object)filter.getSb_cnvdate()));
        }
        if (!CheckNull.isEmpty(filter.getI_amount())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "i_amount=?", (Object)filter.getI_amount()));
        }
        if (!CheckNull.isEmpty(filter.getI_cshback())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "i_cshback=?", (Object)filter.getI_cshback()));
        }
        if (!CheckNull.isEmpty(filter.getI_fee())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "i_fee=?", (Object)filter.getI_fee()));
        }
        if (!CheckNull.isEmpty(filter.getIbnk_ccy())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "ibnk_ccy=?", (Object)filter.getIbnk_ccy()));
        }
        if (!CheckNull.isEmpty(filter.getI_ccyexp())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "i_ccyexp=?", (Object)filter.getI_ccyexp()));
        }
        if (!CheckNull.isEmpty(filter.getI_cnvrate())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "i_cnvrate=?", (Object)filter.getI_cnvrate()));
        }
        if (!CheckNull.isEmpty(filter.getI_cnvdate())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "i_cnvdate=?", (Object)filter.getI_cnvdate()));
        }
        if (!CheckNull.isEmpty(filter.getAbvr_name())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "abvr_name=?", (Object)filter.getAbvr_name()));
        }
        if (!CheckNull.isEmpty(filter.getCity())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "city=?", (Object)filter.getCity()));
        }
        if (!CheckNull.isEmpty(filter.getCountry())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "country=?", (Object)filter.getCountry()));
        }
        if (!CheckNull.isEmpty(filter.getPoint_code())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "point_code=?", (Object)filter.getPoint_code()));
        }
        if (!CheckNull.isEmpty(filter.getMcc_code())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "mcc_code=?", (Object)filter.getMcc_code()));
        }
        if (!CheckNull.isEmpty(filter.getTerminal())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "terminal=?", (Object)filter.getTerminal()));
        }
        if (!CheckNull.isEmpty(filter.getBatch_id())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "batch_id=?", (Object)filter.getBatch_id()));
        }
        if (!CheckNull.isEmpty(filter.getSettl_nr())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "settl_nr=?", (Object)filter.getSettl_nr()));
        }
        if (!CheckNull.isEmpty(filter.getSettl_date())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "settl_date=?", (Object)filter.getSettl_date()));
        }
        if (!CheckNull.isEmpty(filter.getAcqref_nr())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "acqref_nr=?", (Object)filter.getAcqref_nr()));
        }
        if (!CheckNull.isEmpty(filter.getFile_id())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "file_id=?", (Object)filter.getFile_id()));
        }
        if (!CheckNull.isEmpty(filter.getMs_number())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "ms_number=?", (Object)filter.getMs_number()));
        }
        if (!CheckNull.isEmpty(filter.getFile_date())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "file_date=?", (Object)filter.getFile_date()));
        }
        if (!CheckNull.isEmpty(filter.getSource_algorithm())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "source_algorithm=?", (Object)filter.getSource_algorithm()));
        }
        if (!CheckNull.isEmpty(filter.getTerm_nr())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "term_nr=?", (Object)filter.getTerm_nr()));
        }
        if (!CheckNull.isEmpty(filter.getEcmc_fee())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "ecmc_fee=?", (Object)filter.getEcmc_fee()));
        }
        if (!CheckNull.isEmpty(filter.getTran_info())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "tran_info=?", (Object)filter.getTran_info()));
        }
        if (!CheckNull.isEmpty(filter.getPr_amount())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "pr_amount=?", (Object)filter.getPr_amount()));
        }
        if (!CheckNull.isEmpty(filter.getPr_cshback())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "pr_cshback=?", (Object)filter.getPr_cshback()));
        }
        if (!CheckNull.isEmpty(filter.getPr_fee())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "pr_fee=?", (Object)filter.getPr_fee()));
        }
        if (!CheckNull.isEmpty(filter.getPrnk_ccy())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "prnk_ccy=?", (Object)filter.getPrnk_ccy()));
        }
        if (!CheckNull.isEmpty(filter.getPr_ccyexp())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "pr_ccyexp=?", (Object)filter.getPr_ccyexp()));
        }
        if (!CheckNull.isEmpty(filter.getPr_cnvrate())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "pr_cnvrate=?", (Object)filter.getPr_cnvrate()));
        }
        if (!CheckNull.isEmpty(filter.getPr_cnvdate())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "pr_cnvdate=?", (Object)filter.getPr_cnvdate()));
        }
        if (!CheckNull.isEmpty(filter.getE_region())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "e_region=?", (Object)filter.getE_region()));
        }
        if (!CheckNull.isEmpty(filter.getCard_type())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "card_type=?", (Object)filter.getCard_type()));
        }
        if (!CheckNull.isEmpty(filter.getProc_class())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "proc_class=?", (Object)filter.getProc_class()));
        }
        if (!CheckNull.isEmpty(filter.getCard_seq_nr())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "card_seq_nr=?", (Object)filter.getCard_seq_nr()));
        }
        if (!CheckNull.isEmpty(filter.getMsg_type())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "msg_type=?", (Object)filter.getMsg_type()));
        }
        if (!CheckNull.isEmpty(filter.getOrg_msg_type())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "org_msg_type=?", (Object)filter.getOrg_msg_type()));
        }
        if (!CheckNull.isEmpty(filter.getProc_code())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "proc_code=?", (Object)filter.getProc_code()));
        }
        if (!CheckNull.isEmpty(filter.getMsg_category())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "msg_category=?", (Object)filter.getMsg_category()));
        }
        if (!CheckNull.isEmpty(filter.getMerchant())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "merchant=?", (Object)filter.getMerchant()));
        }
        if (!CheckNull.isEmpty(filter.getMoto_ind())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "moto_ind=?", (Object)filter.getMoto_ind()));
        }
        if (!CheckNull.isEmpty(filter.getSusp_status())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "susp_status=?", (Object)filter.getSusp_status()));
        }
        if (!CheckNull.isEmpty(filter.getTransact_row())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "transact_row=?", (Object)filter.getTransact_row()));
        }
        if (!CheckNull.isEmpty(filter.getAuthoriz_row())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "authoriz_row=?", (Object)filter.getAuthoriz_row()));
        }
        if (!CheckNull.isEmpty(filter.getFld_043())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "fld_043=?", (Object)filter.getFld_043()));
        }
        if (!CheckNull.isEmpty(filter.getFld_098())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "fld_098=?", (Object)filter.getFld_098()));
        }
        if (!CheckNull.isEmpty(filter.getFld_102())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "fld_102=?", (Object)filter.getFld_102()));
        }
        if (!CheckNull.isEmpty(filter.getFld_103())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "fld_103=?", (Object)filter.getFld_103()));
        }
        if (!CheckNull.isEmpty(filter.getFld_104())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "fld_104=?", (Object)filter.getFld_104()));
        }
        if (!CheckNull.isEmpty(filter.getFld_039())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "fld_039=?", (Object)filter.getFld_039()));
        }
        if (!CheckNull.isEmpty(filter.getFld_sh6())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "fld_sh6=?", (Object)filter.getFld_sh6()));
        }
        if (!CheckNull.isEmpty(filter.getBatch_date())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "batch_date=?", (Object)filter.getBatch_date()));
        }
        if (!CheckNull.isEmpty(filter.getTr_fee())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "tr_fee=?", (Object)filter.getTr_fee()));
        }
        if (!CheckNull.isEmpty(filter.getFld_040())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "fld_040=?", (Object)filter.getFld_040()));
        }
        if (!CheckNull.isEmpty(filter.getFld_123_1())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "fld_123_1=?", (Object)filter.getFld_123_1()));
        }
        if (!CheckNull.isEmpty(filter.getEpi_42_48())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "epi_42_48=?", (Object)filter.getEpi_42_48()));
        }
        if (!CheckNull.isEmpty(filter.getFld_003())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "fld_003=?", (Object)filter.getFld_003()));
        }
        if (!CheckNull.isEmpty(filter.getMsc())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "msc=?", (Object)filter.getMsc()));
        }
        if (!CheckNull.isEmpty(filter.getAccount_nr())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "account_nr=?", (Object)filter.getAccount_nr()));
        }
        if (!CheckNull.isEmpty(filter.getEpi_42_48_full())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "epi_42_48_full=?", (Object)filter.getEpi_42_48_full()));
        }
        if (!CheckNull.isEmpty(filter.getOther_code())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "other_code=?", (Object)filter.getOther_code()));
        }
        if (!CheckNull.isEmpty(filter.getFld_015())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "fld_015=?", (Object)filter.getFld_015()));
        }
        if (!CheckNull.isEmpty(filter.getFld_095())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "fld_095=?", (Object)filter.getFld_095()));
        }
        if (!CheckNull.isEmpty(filter.getAudit_date())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "audit_date=?", (Object)filter.getAudit_date()));
        }
        if (!CheckNull.isEmpty(filter.getOther_fee1())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "other_fee1=?", (Object)filter.getOther_fee1()));
        }
        if (!CheckNull.isEmpty(filter.getOther_fee2())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "other_fee2=?", (Object)filter.getOther_fee2()));
        }
        if (!CheckNull.isEmpty(filter.getOther_fee3())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "other_fee3=?", (Object)filter.getOther_fee3()));
        }
        if (!CheckNull.isEmpty(filter.getOther_fee4())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "other_fee4=?", (Object)filter.getOther_fee4()));
        }
        if (!CheckNull.isEmpty(filter.getOther_fee5())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "other_fee5=?", (Object)filter.getOther_fee5()));
        }
        if (!CheckNull.isEmpty(filter.getState_id())) {
            flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "state_id=?", (Object)filter.getState_id()));
        }
        flfields.add(new FilterField(String.valueOf(getCond(flfields)) + "rownum<?", (Object)1001));
        return flfields;
    }
    
    public int getCount(final EmpcFilesBTransactionsFilter filter) {
        Connection c = null;
        int n = 0;
        final List<FilterField> flFields = getFilterFields(filter);
        final StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM TF_EmpcFilesBTransactions ");
        if (flFields.size() > 0) {
            for (int i = 0; i < flFields.size(); ++i) {
                sql.append(flFields.get(i).getSqlwhere());
            }
        }
        try {
            c = ConnectionPool.getConnection();
            final PreparedStatement ps = c.prepareStatement(sql.toString());
            for (int k = 0; k < flFields.size(); ++k) {
                ps.setObject(k + 1, flFields.get(k).getColobject());
            }
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                n = rs.getInt(1);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return n;
        }
        finally {
            ConnectionPool.close(c);
        }
        ConnectionPool.close(c);
        return n;
    }
    
    public static List<EmpcFilesBTransactions> getEmpcFilesBTransactionssFl(final int pageIndex, final int pageSize, final EmpcFilesBTransactionsFilter filter) {
        final List<EmpcFilesBTransactions> list = new ArrayList<EmpcFilesBTransactions>();
        Connection c = null;
        final int v_lowerbound = pageIndex + 1;
        final int v_upperbound = v_lowerbound + pageSize - 1;
        final List<FilterField> flFields = getFilterFields(filter);
        final StringBuffer sql = new StringBuffer();
        sql.append(EmpcFilesBTransactionsService.psql1);
        sql.append(EmpcFilesBTransactionsService.msql);
        if (flFields.size() > 0) {
            for (int i = 0; i < flFields.size(); ++i) {
                sql.append(flFields.get(i).getSqlwhere());
            }
        }
        sql.append(EmpcFilesBTransactionsService.psql2);
        try {
            c = ConnectionPool.getConnection();
            final PreparedStatement ps = c.prepareStatement(sql.toString());
            int params;
            for (params = 0; params < flFields.size(); ++params) {
                ps.setObject(params + 1, flFields.get(params).getColobject());
            }
            ++params;
            ps.setInt(params++, v_upperbound);
            ps.setInt(params++, v_lowerbound);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new EmpcFilesBTransactions(rs.getLong("id"), rs.getString("rec_num"), rs.getString("empc_file_id"), rs.getString("mtid"), rs.getString("rec_centr"), rs.getString("send_centr"), rs.getString("iss_cmi"), rs.getString("send_cmi"), rs.getString("settl_cmi"), rs.getString("acq_bank"), rs.getString("acq_branch"), rs.getString("member"), rs.getString("clearing_group"), rs.getString("merchant_accept"), rs.getString("batch_nr"), rs.getString("slip_nr"), rs.getString("card"), rs.getString("exp_date"), rs.getString("e_date"), rs.getString("e_time"), rs.getString("tran_type"), rs.getString("appr_code"), rs.getString("appr_src"), rs.getString("stan"), rs.getString("ref_number"), rs.getString("amount"), rs.getString("cash_back"), rs.getString("fee"), rs.getString("currency"), rs.getString("ccy_exp"), rs.getString("sb_amount"), rs.getString("sb_cshback"), rs.getString("sb_fee"), rs.getString("sbnk_ccy"), rs.getString("sb_ccyexp"), rs.getString("sb_cnvrate"), rs.getString("sb_cnvdate"), rs.getString("i_amount"), rs.getString("i_cshback"), rs.getString("i_fee"), rs.getString("ibnk_ccy"), rs.getString("i_ccyexp"), rs.getString("i_cnvrate"), rs.getString("i_cnvdate"), rs.getString("abvr_name"), rs.getString("city"), rs.getString("country"), rs.getString("point_code"), rs.getString("mcc_code"), rs.getString("terminal"), rs.getString("batch_id"), rs.getString("settl_nr"), rs.getString("settl_date"), rs.getString("acqref_nr"), rs.getString("file_id"), rs.getString("ms_number"), rs.getString("file_date"), rs.getString("source_algorithm"), rs.getString("term_nr"), rs.getString("ecmc_fee"), rs.getString("tran_info"), rs.getString("pr_amount"), rs.getString("pr_cshback"), rs.getString("pr_fee"), rs.getString("prnk_ccy"), rs.getString("pr_ccyexp"), rs.getString("pr_cnvrate"), rs.getString("pr_cnvdate"), rs.getString("e_region"), rs.getString("card_type"), rs.getString("proc_class"), rs.getString("card_seq_nr"), rs.getString("msg_type"), rs.getString("org_msg_type"), rs.getString("proc_code"), rs.getString("msg_category"), rs.getString("merchant"), rs.getString("moto_ind"), rs.getString("susp_status"), rs.getString("transact_row"), rs.getString("authoriz_row"), rs.getString("fld_043"), rs.getString("fld_098"), rs.getString("fld_102"), rs.getString("fld_103"), rs.getString("fld_104"), rs.getString("fld_039"), rs.getString("fld_sh6"), rs.getString("batch_date"), rs.getString("tr_fee"), rs.getString("fld_040"), rs.getString("fld_123_1"), rs.getString("epi_42_48"), rs.getString("fld_003"), rs.getString("msc"), rs.getString("account_nr"), rs.getString("epi_42_48_full"), rs.getString("other_code"), rs.getString("fld_015"), rs.getString("fld_095"), rs.getString("audit_date"), rs.getString("other_fee1"), rs.getString("other_fee2"), rs.getString("other_fee3"), rs.getString("other_fee4"), rs.getString("other_fee5"), rs.getString("state_id")));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return list;
        }
        finally {
            ConnectionPool.close(c);
        }
        ConnectionPool.close(c);
        return list;
    }
    
    public static EmpcFilesBTransactions getEmpcFilesBTransactions(final Long empcfilesbtransactionsId) {
        EmpcFilesBTransactions empcfilesbtransactions = new EmpcFilesBTransactions();
        Connection c = null;
        try {
            c = ConnectionPool.getConnection();
            final PreparedStatement ps = c.prepareStatement("SELECT * FROM EMPC_FILES_B_TRANSACTIONS WHERE id=?");
            ps.setLong(1, empcfilesbtransactionsId);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                empcfilesbtransactions = new EmpcFilesBTransactions();
                empcfilesbtransactions.setId(rs.getLong("id"));
                empcfilesbtransactions.setRec_num(rs.getString("rec_num"));
                empcfilesbtransactions.setEmpc_file_id(rs.getString("empc_file_id"));
                empcfilesbtransactions.setMtid(rs.getString("mtid"));
                empcfilesbtransactions.setRec_centr(rs.getString("rec_centr"));
                empcfilesbtransactions.setSend_centr(rs.getString("send_centr"));
                empcfilesbtransactions.setIss_cmi(rs.getString("iss_cmi"));
                empcfilesbtransactions.setSend_cmi(rs.getString("send_cmi"));
                empcfilesbtransactions.setSettl_cmi(rs.getString("settl_cmi"));
                empcfilesbtransactions.setAcq_bank(rs.getString("acq_bank"));
                empcfilesbtransactions.setAcq_branch(rs.getString("acq_branch"));
                empcfilesbtransactions.setMember(rs.getString("member"));
                empcfilesbtransactions.setClearing_group(rs.getString("clearing_group"));
                empcfilesbtransactions.setMerchant_accept(rs.getString("merchant_accept"));
                empcfilesbtransactions.setBatch_nr(rs.getString("batch_nr"));
                empcfilesbtransactions.setSlip_nr(rs.getString("slip_nr"));
                empcfilesbtransactions.setCard(rs.getString("card"));
                empcfilesbtransactions.setExp_date(rs.getString("exp_date"));
                empcfilesbtransactions.setE_date(rs.getString("e_date"));
                empcfilesbtransactions.setE_time(rs.getString("e_time"));
                empcfilesbtransactions.setTran_type(rs.getString("tran_type"));
                empcfilesbtransactions.setAppr_code(rs.getString("appr_code"));
                empcfilesbtransactions.setAppr_src(rs.getString("appr_src"));
                empcfilesbtransactions.setStan(rs.getString("stan"));
                empcfilesbtransactions.setRef_number(rs.getString("ref_number"));
                empcfilesbtransactions.setAmount(rs.getString("amount"));
                empcfilesbtransactions.setCash_back(rs.getString("cash_back"));
                empcfilesbtransactions.setFee(rs.getString("fee"));
                empcfilesbtransactions.setCurrency(rs.getString("currency"));
                empcfilesbtransactions.setCcy_exp(rs.getString("ccy_exp"));
                empcfilesbtransactions.setSb_amount(rs.getString("sb_amount"));
                empcfilesbtransactions.setSb_cshback(rs.getString("sb_cshback"));
                empcfilesbtransactions.setSb_fee(rs.getString("sb_fee"));
                empcfilesbtransactions.setSbnk_ccy(rs.getString("sbnk_ccy"));
                empcfilesbtransactions.setSb_ccyexp(rs.getString("sb_ccyexp"));
                empcfilesbtransactions.setSb_cnvrate(rs.getString("sb_cnvrate"));
                empcfilesbtransactions.setSb_cnvdate(rs.getString("sb_cnvdate"));
                empcfilesbtransactions.setI_amount(rs.getString("i_amount"));
                empcfilesbtransactions.setI_cshback(rs.getString("i_cshback"));
                empcfilesbtransactions.setI_fee(rs.getString("i_fee"));
                empcfilesbtransactions.setIbnk_ccy(rs.getString("ibnk_ccy"));
                empcfilesbtransactions.setI_ccyexp(rs.getString("i_ccyexp"));
                empcfilesbtransactions.setI_cnvrate(rs.getString("i_cnvrate"));
                empcfilesbtransactions.setI_cnvdate(rs.getString("i_cnvdate"));
                empcfilesbtransactions.setAbvr_name(rs.getString("abvr_name"));
                empcfilesbtransactions.setCity(rs.getString("city"));
                empcfilesbtransactions.setCountry(rs.getString("country"));
                empcfilesbtransactions.setPoint_code(rs.getString("point_code"));
                empcfilesbtransactions.setMcc_code(rs.getString("mcc_code"));
                empcfilesbtransactions.setTerminal(rs.getString("terminal"));
                empcfilesbtransactions.setBatch_id(rs.getString("batch_id"));
                empcfilesbtransactions.setSettl_nr(rs.getString("settl_nr"));
                empcfilesbtransactions.setSettl_date(rs.getString("settl_date"));
                empcfilesbtransactions.setAcqref_nr(rs.getString("acqref_nr"));
                empcfilesbtransactions.setFile_id(rs.getString("file_id"));
                empcfilesbtransactions.setMs_number(rs.getString("ms_number"));
                empcfilesbtransactions.setFile_date(rs.getString("file_date"));
                empcfilesbtransactions.setSource_algorithm(rs.getString("source_algorithm"));
                empcfilesbtransactions.setTerm_nr(rs.getString("term_nr"));
                empcfilesbtransactions.setEcmc_fee(rs.getString("ecmc_fee"));
                empcfilesbtransactions.setTran_info(rs.getString("tran_info"));
                empcfilesbtransactions.setPr_amount(rs.getString("pr_amount"));
                empcfilesbtransactions.setPr_cshback(rs.getString("pr_cshback"));
                empcfilesbtransactions.setPr_fee(rs.getString("pr_fee"));
                empcfilesbtransactions.setPrnk_ccy(rs.getString("prnk_ccy"));
                empcfilesbtransactions.setPr_ccyexp(rs.getString("pr_ccyexp"));
                empcfilesbtransactions.setPr_cnvrate(rs.getString("pr_cnvrate"));
                empcfilesbtransactions.setPr_cnvdate(rs.getString("pr_cnvdate"));
                empcfilesbtransactions.setE_region(rs.getString("e_region"));
                empcfilesbtransactions.setCard_type(rs.getString("card_type"));
                empcfilesbtransactions.setProc_class(rs.getString("proc_class"));
                empcfilesbtransactions.setCard_seq_nr(rs.getString("card_seq_nr"));
                empcfilesbtransactions.setMsg_type(rs.getString("msg_type"));
                empcfilesbtransactions.setOrg_msg_type(rs.getString("org_msg_type"));
                empcfilesbtransactions.setProc_code(rs.getString("proc_code"));
                empcfilesbtransactions.setMsg_category(rs.getString("msg_category"));
                empcfilesbtransactions.setMerchant(rs.getString("merchant"));
                empcfilesbtransactions.setMoto_ind(rs.getString("moto_ind"));
                empcfilesbtransactions.setSusp_status(rs.getString("susp_status"));
                empcfilesbtransactions.setTransact_row(rs.getString("transact_row"));
                empcfilesbtransactions.setAuthoriz_row(rs.getString("authoriz_row"));
                empcfilesbtransactions.setFld_043(rs.getString("fld_043"));
                empcfilesbtransactions.setFld_098(rs.getString("fld_098"));
                empcfilesbtransactions.setFld_102(rs.getString("fld_102"));
                empcfilesbtransactions.setFld_103(rs.getString("fld_103"));
                empcfilesbtransactions.setFld_104(rs.getString("fld_104"));
                empcfilesbtransactions.setFld_039(rs.getString("fld_039"));
                empcfilesbtransactions.setFld_sh6(rs.getString("fld_sh6"));
                empcfilesbtransactions.setBatch_date(rs.getString("batch_date"));
                empcfilesbtransactions.setTr_fee(rs.getString("tr_fee"));
                empcfilesbtransactions.setFld_040(rs.getString("fld_040"));
                empcfilesbtransactions.setFld_123_1(rs.getString("fld_123_1"));
                empcfilesbtransactions.setEpi_42_48(rs.getString("epi_42_48"));
                empcfilesbtransactions.setFld_003(rs.getString("fld_003"));
                empcfilesbtransactions.setMsc(rs.getString("msc"));
                empcfilesbtransactions.setAccount_nr(rs.getString("account_nr"));
                empcfilesbtransactions.setEpi_42_48_full(rs.getString("epi_42_48_full"));
                empcfilesbtransactions.setOther_code(rs.getString("other_code"));
                empcfilesbtransactions.setFld_015(rs.getString("fld_015"));
                empcfilesbtransactions.setFld_095(rs.getString("fld_095"));
                empcfilesbtransactions.setAudit_date(rs.getString("audit_date"));
                empcfilesbtransactions.setOther_fee1(rs.getString("other_fee1"));
                empcfilesbtransactions.setOther_fee2(rs.getString("other_fee2"));
                empcfilesbtransactions.setOther_fee3(rs.getString("other_fee3"));
                empcfilesbtransactions.setOther_fee4(rs.getString("other_fee4"));
                empcfilesbtransactions.setOther_fee5(rs.getString("other_fee5"));
                empcfilesbtransactions.setState_id(rs.getString("state_id"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return empcfilesbtransactions;
        }
        finally {
            ConnectionPool.close(c);
        }
        ConnectionPool.close(c);
        return empcfilesbtransactions;
    }
    
    public static EmpcFilesBTransactions create(final EmpcFilesBTransactions empcfilesbtransactions) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("SELECT SQ_TF_empcfilesbtransactions.NEXTVAL id FROM DUAL");
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                empcfilesbtransactions.setId(rs.getLong("id"));
            }
            ps = c.prepareStatement("INSERT INTO TF_empcfilesbtransactions (id, rec_num, empc_file_id, mtid, rec_centr, send_centr, iss_cmi, send_cmi, settl_cmi, acq_bank, acq_branch, member, clearing_group, merchant_accept, batch_nr, slip_nr, card, exp_date, e_date, e_time, tran_type, appr_code, appr_src, stan, ref_number, amount, cash_back, fee, currency, ccy_exp, sb_amount, sb_cshback, sb_fee, sbnk_ccy, sb_ccyexp, sb_cnvrate, sb_cnvdate, i_amount, i_cshback, i_fee, ibnk_ccy, i_ccyexp, i_cnvrate, i_cnvdate, abvr_name, city, country, point_code, mcc_code, terminal, batch_id, settl_nr, settl_date, acqref_nr, file_id, ms_number, file_date, source_algorithm, term_nr, ecmc_fee, tran_info, pr_amount, pr_cshback, pr_fee, prnk_ccy, pr_ccyexp, pr_cnvrate, pr_cnvdate, e_region, card_type, proc_class, card_seq_nr, msg_type, org_msg_type, proc_code, msg_category, merchant, moto_ind, susp_status, transact_row, authoriz_row, fld_043, fld_098, fld_102, fld_103, fld_104, fld_039, fld_sh6, batch_date, tr_fee, fld_040, fld_123_1, epi_42_48, fld_003, msc, account_nr, epi_42_48_full, other_code, fld_015, fld_095, audit_date, other_fee1, other_fee2, other_fee3, other_fee4, other_fee5, state_id, ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,)");
            ps.setLong(1, empcfilesbtransactions.getId());
            ps.setString(2, empcfilesbtransactions.getRec_num());
            ps.setString(3, empcfilesbtransactions.getEmpc_file_id());
            ps.setString(4, empcfilesbtransactions.getMtid());
            ps.setString(5, empcfilesbtransactions.getRec_centr());
            ps.setString(6, empcfilesbtransactions.getSend_centr());
            ps.setString(7, empcfilesbtransactions.getIss_cmi());
            ps.setString(8, empcfilesbtransactions.getSend_cmi());
            ps.setString(9, empcfilesbtransactions.getSettl_cmi());
            ps.setString(10, empcfilesbtransactions.getAcq_bank());
            ps.setString(11, empcfilesbtransactions.getAcq_branch());
            ps.setString(12, empcfilesbtransactions.getMember());
            ps.setString(13, empcfilesbtransactions.getClearing_group());
            ps.setString(14, empcfilesbtransactions.getMerchant_accept());
            ps.setString(15, empcfilesbtransactions.getBatch_nr());
            ps.setString(16, empcfilesbtransactions.getSlip_nr());
            ps.setString(17, empcfilesbtransactions.getCard());
            ps.setString(18, empcfilesbtransactions.getExp_date());
            ps.setString(19, empcfilesbtransactions.getE_date());
            ps.setString(20, empcfilesbtransactions.getE_time());
            ps.setString(21, empcfilesbtransactions.getTran_type());
            ps.setString(22, empcfilesbtransactions.getAppr_code());
            ps.setString(23, empcfilesbtransactions.getAppr_src());
            ps.setString(24, empcfilesbtransactions.getStan());
            ps.setString(25, empcfilesbtransactions.getRef_number());
            ps.setString(26, empcfilesbtransactions.getAmount());
            ps.setString(27, empcfilesbtransactions.getCash_back());
            ps.setString(28, empcfilesbtransactions.getFee());
            ps.setString(29, empcfilesbtransactions.getCurrency());
            ps.setString(30, empcfilesbtransactions.getCcy_exp());
            ps.setString(31, empcfilesbtransactions.getSb_amount());
            ps.setString(32, empcfilesbtransactions.getSb_cshback());
            ps.setString(33, empcfilesbtransactions.getSb_fee());
            ps.setString(34, empcfilesbtransactions.getSbnk_ccy());
            ps.setString(35, empcfilesbtransactions.getSb_ccyexp());
            ps.setString(36, empcfilesbtransactions.getSb_cnvrate());
            ps.setString(37, empcfilesbtransactions.getSb_cnvdate());
            ps.setString(38, empcfilesbtransactions.getI_amount());
            ps.setString(39, empcfilesbtransactions.getI_cshback());
            ps.setString(40, empcfilesbtransactions.getI_fee());
            ps.setString(41, empcfilesbtransactions.getIbnk_ccy());
            ps.setString(42, empcfilesbtransactions.getI_ccyexp());
            ps.setString(43, empcfilesbtransactions.getI_cnvrate());
            ps.setString(44, empcfilesbtransactions.getI_cnvdate());
            ps.setString(45, empcfilesbtransactions.getAbvr_name());
            ps.setString(46, empcfilesbtransactions.getCity());
            ps.setString(47, empcfilesbtransactions.getCountry());
            ps.setString(48, empcfilesbtransactions.getPoint_code());
            ps.setString(49, empcfilesbtransactions.getMcc_code());
            ps.setString(50, empcfilesbtransactions.getTerminal());
            ps.setString(51, empcfilesbtransactions.getBatch_id());
            ps.setString(52, empcfilesbtransactions.getSettl_nr());
            ps.setString(53, empcfilesbtransactions.getSettl_date());
            ps.setString(54, empcfilesbtransactions.getAcqref_nr());
            ps.setString(55, empcfilesbtransactions.getFile_id());
            ps.setString(56, empcfilesbtransactions.getMs_number());
            ps.setString(57, empcfilesbtransactions.getFile_date());
            ps.setString(58, empcfilesbtransactions.getSource_algorithm());
            ps.setString(59, empcfilesbtransactions.getTerm_nr());
            ps.setString(60, empcfilesbtransactions.getEcmc_fee());
            ps.setString(61, empcfilesbtransactions.getTran_info());
            ps.setString(62, empcfilesbtransactions.getPr_amount());
            ps.setString(63, empcfilesbtransactions.getPr_cshback());
            ps.setString(64, empcfilesbtransactions.getPr_fee());
            ps.setString(65, empcfilesbtransactions.getPrnk_ccy());
            ps.setString(66, empcfilesbtransactions.getPr_ccyexp());
            ps.setString(67, empcfilesbtransactions.getPr_cnvrate());
            ps.setString(68, empcfilesbtransactions.getPr_cnvdate());
            ps.setString(69, empcfilesbtransactions.getE_region());
            ps.setString(70, empcfilesbtransactions.getCard_type());
            ps.setString(71, empcfilesbtransactions.getProc_class());
            ps.setString(72, empcfilesbtransactions.getCard_seq_nr());
            ps.setString(73, empcfilesbtransactions.getMsg_type());
            ps.setString(74, empcfilesbtransactions.getOrg_msg_type());
            ps.setString(75, empcfilesbtransactions.getProc_code());
            ps.setString(76, empcfilesbtransactions.getMsg_category());
            ps.setString(77, empcfilesbtransactions.getMerchant());
            ps.setString(78, empcfilesbtransactions.getMoto_ind());
            ps.setString(79, empcfilesbtransactions.getSusp_status());
            ps.setString(80, empcfilesbtransactions.getTransact_row());
            ps.setString(81, empcfilesbtransactions.getAuthoriz_row());
            ps.setString(82, empcfilesbtransactions.getFld_043());
            ps.setString(83, empcfilesbtransactions.getFld_098());
            ps.setString(84, empcfilesbtransactions.getFld_102());
            ps.setString(85, empcfilesbtransactions.getFld_103());
            ps.setString(86, empcfilesbtransactions.getFld_104());
            ps.setString(87, empcfilesbtransactions.getFld_039());
            ps.setString(88, empcfilesbtransactions.getFld_sh6());
            ps.setString(89, empcfilesbtransactions.getBatch_date());
            ps.setString(90, empcfilesbtransactions.getTr_fee());
            ps.setString(91, empcfilesbtransactions.getFld_040());
            ps.setString(92, empcfilesbtransactions.getFld_123_1());
            ps.setString(93, empcfilesbtransactions.getEpi_42_48());
            ps.setString(94, empcfilesbtransactions.getFld_003());
            ps.setString(95, empcfilesbtransactions.getMsc());
            ps.setString(96, empcfilesbtransactions.getAccount_nr());
            ps.setString(97, empcfilesbtransactions.getEpi_42_48_full());
            ps.setString(98, empcfilesbtransactions.getOther_code());
            ps.setString(99, empcfilesbtransactions.getFld_015());
            ps.setString(100, empcfilesbtransactions.getFld_095());
            ps.setString(101, empcfilesbtransactions.getAudit_date());
            ps.setString(102, empcfilesbtransactions.getOther_fee1());
            ps.setString(103, empcfilesbtransactions.getOther_fee2());
            ps.setString(104, empcfilesbtransactions.getOther_fee3());
            ps.setString(105, empcfilesbtransactions.getOther_fee4());
            ps.setString(106, empcfilesbtransactions.getOther_fee5());
            ps.setString(107, empcfilesbtransactions.getState_id());
            ps.executeUpdate();
            c.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            return empcfilesbtransactions;
        }
        finally {
            ConnectionPool.close(c);
        }
        ConnectionPool.close(c);
        return empcfilesbtransactions;
    }
    
    public static void update(final EmpcFilesBTransactions empcfilesbtransactions) {
        Connection c = null;
        try {
            c = ConnectionPool.getConnection();
            final PreparedStatement ps = c.prepareStatement("UPDATE TF_empcfilesbtransactions SET id=?, rec_num=?, empc_file_id=?, mtid=?, rec_centr=?, send_centr=?, iss_cmi=?, send_cmi=?, settl_cmi=?, acq_bank=?, acq_branch=?, member=?, clearing_group=?, merchant_accept=?, batch_nr=?, slip_nr=?, card=?, exp_date=?, e_date=?, e_time=?, tran_type=?, appr_code=?, appr_src=?, stan=?, ref_number=?, amount=?, cash_back=?, fee=?, currency=?, ccy_exp=?, sb_amount=?, sb_cshback=?, sb_fee=?, sbnk_ccy=?, sb_ccyexp=?, sb_cnvrate=?, sb_cnvdate=?, i_amount=?, i_cshback=?, i_fee=?, ibnk_ccy=?, i_ccyexp=?, i_cnvrate=?, i_cnvdate=?, abvr_name=?, city=?, country=?, point_code=?, mcc_code=?, terminal=?, batch_id=?, settl_nr=?, settl_date=?, acqref_nr=?, file_id=?, ms_number=?, file_date=?, source_algorithm=?, term_nr=?, ecmc_fee=?, tran_info=?, pr_amount=?, pr_cshback=?, pr_fee=?, prnk_ccy=?, pr_ccyexp=?, pr_cnvrate=?, pr_cnvdate=?, e_region=?, card_type=?, proc_class=?, card_seq_nr=?, msg_type=?, org_msg_type=?, proc_code=?, msg_category=?, merchant=?, moto_ind=?, susp_status=?, transact_row=?, authoriz_row=?, fld_043=?, fld_098=?, fld_102=?, fld_103=?, fld_104=?, fld_039=?, fld_sh6=?, batch_date=?, tr_fee=?, fld_040=?, fld_123_1=?, epi_42_48=?, fld_003=?, msc=?, account_nr=?, epi_42_48_full=?, other_code=?, fld_015=?, fld_095=?, audit_date=?, other_fee1=?, other_fee2=?, other_fee3=?, other_fee4=?, other_fee5=?, state_id=?,  WHERE id=?");
            ps.setLong(1, empcfilesbtransactions.getId());
            ps.setString(2, empcfilesbtransactions.getRec_num());
            ps.setString(3, empcfilesbtransactions.getEmpc_file_id());
            ps.setString(4, empcfilesbtransactions.getMtid());
            ps.setString(5, empcfilesbtransactions.getRec_centr());
            ps.setString(6, empcfilesbtransactions.getSend_centr());
            ps.setString(7, empcfilesbtransactions.getIss_cmi());
            ps.setString(8, empcfilesbtransactions.getSend_cmi());
            ps.setString(9, empcfilesbtransactions.getSettl_cmi());
            ps.setString(10, empcfilesbtransactions.getAcq_bank());
            ps.setString(11, empcfilesbtransactions.getAcq_branch());
            ps.setString(12, empcfilesbtransactions.getMember());
            ps.setString(13, empcfilesbtransactions.getClearing_group());
            ps.setString(14, empcfilesbtransactions.getMerchant_accept());
            ps.setString(15, empcfilesbtransactions.getBatch_nr());
            ps.setString(16, empcfilesbtransactions.getSlip_nr());
            ps.setString(17, empcfilesbtransactions.getCard());
            ps.setString(18, empcfilesbtransactions.getExp_date());
            ps.setString(19, empcfilesbtransactions.getE_date());
            ps.setString(20, empcfilesbtransactions.getE_time());
            ps.setString(21, empcfilesbtransactions.getTran_type());
            ps.setString(22, empcfilesbtransactions.getAppr_code());
            ps.setString(23, empcfilesbtransactions.getAppr_src());
            ps.setString(24, empcfilesbtransactions.getStan());
            ps.setString(25, empcfilesbtransactions.getRef_number());
            ps.setString(26, empcfilesbtransactions.getAmount());
            ps.setString(27, empcfilesbtransactions.getCash_back());
            ps.setString(28, empcfilesbtransactions.getFee());
            ps.setString(29, empcfilesbtransactions.getCurrency());
            ps.setString(30, empcfilesbtransactions.getCcy_exp());
            ps.setString(31, empcfilesbtransactions.getSb_amount());
            ps.setString(32, empcfilesbtransactions.getSb_cshback());
            ps.setString(33, empcfilesbtransactions.getSb_fee());
            ps.setString(34, empcfilesbtransactions.getSbnk_ccy());
            ps.setString(35, empcfilesbtransactions.getSb_ccyexp());
            ps.setString(36, empcfilesbtransactions.getSb_cnvrate());
            ps.setString(37, empcfilesbtransactions.getSb_cnvdate());
            ps.setString(38, empcfilesbtransactions.getI_amount());
            ps.setString(39, empcfilesbtransactions.getI_cshback());
            ps.setString(40, empcfilesbtransactions.getI_fee());
            ps.setString(41, empcfilesbtransactions.getIbnk_ccy());
            ps.setString(42, empcfilesbtransactions.getI_ccyexp());
            ps.setString(43, empcfilesbtransactions.getI_cnvrate());
            ps.setString(44, empcfilesbtransactions.getI_cnvdate());
            ps.setString(45, empcfilesbtransactions.getAbvr_name());
            ps.setString(46, empcfilesbtransactions.getCity());
            ps.setString(47, empcfilesbtransactions.getCountry());
            ps.setString(48, empcfilesbtransactions.getPoint_code());
            ps.setString(49, empcfilesbtransactions.getMcc_code());
            ps.setString(50, empcfilesbtransactions.getTerminal());
            ps.setString(51, empcfilesbtransactions.getBatch_id());
            ps.setString(52, empcfilesbtransactions.getSettl_nr());
            ps.setString(53, empcfilesbtransactions.getSettl_date());
            ps.setString(54, empcfilesbtransactions.getAcqref_nr());
            ps.setString(55, empcfilesbtransactions.getFile_id());
            ps.setString(56, empcfilesbtransactions.getMs_number());
            ps.setString(57, empcfilesbtransactions.getFile_date());
            ps.setString(58, empcfilesbtransactions.getSource_algorithm());
            ps.setString(59, empcfilesbtransactions.getTerm_nr());
            ps.setString(60, empcfilesbtransactions.getEcmc_fee());
            ps.setString(61, empcfilesbtransactions.getTran_info());
            ps.setString(62, empcfilesbtransactions.getPr_amount());
            ps.setString(63, empcfilesbtransactions.getPr_cshback());
            ps.setString(64, empcfilesbtransactions.getPr_fee());
            ps.setString(65, empcfilesbtransactions.getPrnk_ccy());
            ps.setString(66, empcfilesbtransactions.getPr_ccyexp());
            ps.setString(67, empcfilesbtransactions.getPr_cnvrate());
            ps.setString(68, empcfilesbtransactions.getPr_cnvdate());
            ps.setString(69, empcfilesbtransactions.getE_region());
            ps.setString(70, empcfilesbtransactions.getCard_type());
            ps.setString(71, empcfilesbtransactions.getProc_class());
            ps.setString(72, empcfilesbtransactions.getCard_seq_nr());
            ps.setString(73, empcfilesbtransactions.getMsg_type());
            ps.setString(74, empcfilesbtransactions.getOrg_msg_type());
            ps.setString(75, empcfilesbtransactions.getProc_code());
            ps.setString(76, empcfilesbtransactions.getMsg_category());
            ps.setString(77, empcfilesbtransactions.getMerchant());
            ps.setString(78, empcfilesbtransactions.getMoto_ind());
            ps.setString(79, empcfilesbtransactions.getSusp_status());
            ps.setString(80, empcfilesbtransactions.getTransact_row());
            ps.setString(81, empcfilesbtransactions.getAuthoriz_row());
            ps.setString(82, empcfilesbtransactions.getFld_043());
            ps.setString(83, empcfilesbtransactions.getFld_098());
            ps.setString(84, empcfilesbtransactions.getFld_102());
            ps.setString(85, empcfilesbtransactions.getFld_103());
            ps.setString(86, empcfilesbtransactions.getFld_104());
            ps.setString(87, empcfilesbtransactions.getFld_039());
            ps.setString(88, empcfilesbtransactions.getFld_sh6());
            ps.setString(89, empcfilesbtransactions.getBatch_date());
            ps.setString(90, empcfilesbtransactions.getTr_fee());
            ps.setString(91, empcfilesbtransactions.getFld_040());
            ps.setString(92, empcfilesbtransactions.getFld_123_1());
            ps.setString(93, empcfilesbtransactions.getEpi_42_48());
            ps.setString(94, empcfilesbtransactions.getFld_003());
            ps.setString(95, empcfilesbtransactions.getMsc());
            ps.setString(96, empcfilesbtransactions.getAccount_nr());
            ps.setString(97, empcfilesbtransactions.getEpi_42_48_full());
            ps.setString(98, empcfilesbtransactions.getOther_code());
            ps.setString(99, empcfilesbtransactions.getFld_015());
            ps.setString(100, empcfilesbtransactions.getFld_095());
            ps.setString(101, empcfilesbtransactions.getAudit_date());
            ps.setString(102, empcfilesbtransactions.getOther_fee1());
            ps.setString(103, empcfilesbtransactions.getOther_fee2());
            ps.setString(104, empcfilesbtransactions.getOther_fee3());
            ps.setString(105, empcfilesbtransactions.getOther_fee4());
            ps.setString(106, empcfilesbtransactions.getOther_fee5());
            ps.setString(107, empcfilesbtransactions.getState_id());
            ps.executeUpdate();
            c.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        finally {
            ConnectionPool.close(c);
        }
        ConnectionPool.close(c);
    }
    
    public static void remove(final EmpcFilesBTransactions empcfilesbtransactions) {
        Connection c = null;
        try {
            c = ConnectionPool.getConnection();
            final PreparedStatement ps = c.prepareStatement("DELETE FROM TF_empcfilesbtransactions WHERE id=?");
            ps.setLong(1, empcfilesbtransactions.getId());
            ps.executeUpdate();
            c.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }
        finally {
            ConnectionPool.close(c);
        }
        ConnectionPool.close(c);
    }
}
