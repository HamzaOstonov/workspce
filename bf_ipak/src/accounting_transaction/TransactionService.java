package accounting_transaction;

import com.is.ISLogger;
import com.is.kernel.KernelException;
import com.is.kernel.parameter.Parameters;
import general.General;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class TransactionService {
    //private static Logger logger = ;
    private HashMap<Long, List<Tr_template>> tr_templates = null;
    private HashMap<String, HashMap<Long, Bf_tr_acc>> tr_accs = null;
    private HashMap<Long, Bf_tr_paytype> bf_tr_paytypes = null;
    private static Pattern p_tag = Pattern.compile("(<@.+?@>)");

    public long execute_operation(Long operation_id, Parameters parameters, Connection c)
            throws Exception {
        List<Tr_template> current_templates = (List) this.tr_templates.get(operation_id);

        Parameters tr_pay_param = new Parameters();

        tr_pay_param.put("branch", parameters.get("branch"));
        tr_pay_param.put("operation_id", parameters.get("operation_id"));
        tr_pay_param.put("parent_group_id", parameters.get("parent_group_id"));
        tr_pay_param.put("cs_prep", parameters.get("cs_prep"));
        tr_pay_param.put("parent_deal_id",
                parameters.contains("parent_deal_id") ? parameters.get("parent_deal_id") : 1);

        Long tr_pay_id = Service.create_tr_pay(tr_pay_param, c);

        parameters.put("tr_pay_id", tr_pay_id);
        if (current_templates != null) {
            for (int i = 0; i < current_templates.size(); i++) {
                Tr_template tr_template = (Tr_template) current_templates.get(i);

                Parameters doc_params = new Parameters();

                if (tr_template.getCash_code() != null && !tr_template.getCash_code().matches("[0-9]+"))
                    tr_template.setCash_code((String) parameters.get(tr_template.getCash_code()));
                if (tr_template.getId_transh_purp() != null && !tr_template.getId_transh_purp().matches("[0-9]+"))
                    tr_template.setId_transh_purp((String) parameters.get(tr_template.getId_transh_purp()));

                if (!((HashMap) this.tr_accs.get((String) parameters.get("branch"))).containsKey(Long.valueOf(tr_template.getAcc_ct())))
                    throw new KernelException("Account with template_id " + tr_template.getAcc_ct() + " branch " + (String) parameters.get("branch") + " not found in bf_tr_acc");
                if (!((HashMap) this.tr_accs.get((String) parameters.get("branch"))).containsKey(Long.valueOf(tr_template.getAcc_dt())))
                    throw new KernelException("Account with template_id " + tr_template.getAcc_dt() + " branch " + (String) parameters.get("branch") + " not found in bf_tr_acc");

                Bf_tr_acc tr_acc_ct =
                        (Bf_tr_acc) ((HashMap) this.tr_accs.get((String)
                                parameters.get("branch"))).get(
                                Long.valueOf(tr_template.getAcc_ct()));

                Bf_tr_acc tr_acc_dt =
                        (Bf_tr_acc) ((HashMap) this.tr_accs.get
                                ((String) parameters.get("branch"))).
                                get(Long.valueOf(tr_template.getAcc_dt()));

                if (tr_acc_ct.getAccount() == null || tr_acc_ct.getAccount().length() == 0)
                    throw new KernelException("Settings for template_id " + tr_template.getAcc_ct() + " branch " + (String) parameters.get("branch") + " not specified");
                if (tr_acc_dt.getAccount() == null || tr_acc_dt.getAccount().length() == 0)
                    throw new KernelException("Settings for template_id " + tr_template.getAcc_dt() + " branch " + (String) parameters.get("branch") + " not specified");

                if (!tr_acc_ct.getAccount().matches("[0-9]+")) {
                    ISLogger.getLogger().error("ACC CREDIT " + tr_acc_ct.getAccount());
                    if (!parameters.contains(tr_acc_ct.getAccount()))
                        throw new KernelException("Parameter " + tr_acc_ct.getAccount() + " branch " + (String) parameters.get("branch") + " specified for account but not provided");
                    tr_acc_ct.setAccount((String) parameters.get(tr_acc_ct.getAccount()));
                }
                doc_params.put("ACC_CO", tr_acc_ct.getAccount());
                if (!tr_acc_dt.getAccount().matches("[0-9]+")) {
                    if (!parameters.contains(tr_acc_dt.getAccount()))
                        throw new KernelException("Parameter " + tr_acc_dt.getAccount() + " branch " + (String) parameters.get("branch") + " specified for account but not provided");
                    tr_acc_dt.setAccount((String) parameters.get(tr_acc_dt.getAccount()));
                }
                doc_params.put("ACC_CL", tr_acc_dt.getAccount());
                if ((tr_template.getAmount() != null) && (tr_template.getAmount().length() > 0)) {
                    if (!parameters.get(tr_template.getAmount()).getClass().getName().equals("java.lang.Long") &&
                            !parameters.contains(tr_template.getAmount()))
                        throw new KernelException("Parameter " + tr_template.getAmount() + " specified for amount but not provided");
                    doc_params.put("SUMMA",
                            (Long) (parameters.get(tr_template.getAmount()).getClass().getName().equals("java.lang.Long") ? parameters.get(tr_template.getAmount()) : Long.valueOf(Long.parseLong(parameters.get(tr_template.getAmount()).toString()))));
                } else {
                    doc_params.put("SUMMA",
                            (Long) (parameters.get("SUMMA").getClass().getName().equals("java.lang.Long") ? parameters.get("SUMMA") : Long.valueOf(Long.parseLong(parameters.get("SUMMA").toString()))));
                }
                doc_params.put("D_DATE", Service.get_day(c));
                //doc_params.put("BANK_CL", ((String)doc_params.get("ACC_CL")).substring(12, 17));

                HashMap<String, String> bank_filials = Service.get_bank_filials();


                if (!tr_acc_dt.getAcc_mfo().matches("[0-9]+")) {
                    if (!parameters.contains(tr_acc_dt.getAcc_mfo()))
                        throw new KernelException("Parameter " + tr_acc_dt.getAcc_mfo() + " branch " + (String) parameters.get("branch") + " specified for branch but not provided");
                    if (!parameters.get(tr_acc_dt.getAcc_mfo()).getClass().equals(String.class))
                        throw new KernelException("Parameter " + tr_acc_dt.getAcc_mfo() + " branch " + (String) parameters.get("branch") + " has wrong type");
                    tr_acc_dt.setAcc_mfo((String) parameters.get(tr_acc_dt.getAcc_mfo()));
                }
                if (!tr_acc_ct.getAcc_mfo().matches("[0-9]+")) {
                    if (!parameters.contains(tr_acc_ct.getAcc_mfo()))
                        throw new KernelException("Parameter " + tr_acc_ct.getAcc_mfo() + " branch " + (String) parameters.get("branch") + " specified for branch but not provided");
                    if (!parameters.get(tr_acc_ct.getAcc_mfo()).getClass().equals(String.class))
                        throw new KernelException("Parameter " + tr_acc_ct.getAcc_mfo() + " branch " + (String) parameters.get("branch") + " has wrong type");
                    tr_acc_ct.setAcc_mfo((String) parameters.get(tr_acc_ct.getAcc_mfo()));
                }

                doc_params.put("BANK_CL", tr_acc_dt.getAcc_mfo());
                if (bank_filials.containsKey(tr_acc_dt.getAcc_mfo()))
                    doc_params.put("NAME_CL", Service.get_name_and_inn((String) doc_params.get("ACC_CL"),// c,
                            tr_acc_dt.getAcc_mfo()));
                else
                	if(tr_acc_dt.getAcc_name_and_inn().startsWith("@P_"))
                		doc_params.put("NAME_CL", parameters.get(tr_acc_dt.getAcc_name_and_inn().substring(3)));
                	else
                		doc_params.put("NAME_CL", tr_acc_dt.getAcc_name_and_inn());
                //doc_params.put("BANK_CO", ((String)doc_params.get("ACC_CO")).substring(12, 17));

                doc_params.put("BANK_CO", tr_acc_ct.getAcc_mfo());
                if (!bank_filials.containsKey(tr_acc_ct.getAcc_mfo())) {
                    if(tr_acc_ct.getAcc_name_and_inn().startsWith("@P_"))
                        doc_params.put("NAME_CO", parameters.get(tr_acc_ct.getAcc_name_and_inn().substring(3)));
                    else
                        doc_params.put("NAME_CO", tr_acc_ct.getAcc_name_and_inn());
                }
                else{
                    doc_params.put("NAME_CO", Service.get_name_and_inn((String) doc_params.get("ACC_CO"),// c,
                            tr_acc_ct.getAcc_mfo()));
                }



                Matcher m = p_tag.matcher(tr_template.getPurpose());
                String res_purpose = tr_template.getPurpose();
                while (m.find()) {
                    res_purpose = res_purpose.replace(
                            m.group(1),
                            (String) parameters.get(m.group(1).substring(
                                    2,
                                    m.group(1).length() - 2)));
                }
                doc_params.put("PURPOSE", res_purpose);
                //doc_params.put("PURPOSE_CODE", tr_template.getPurpose_code());
                if(!tr_template.getPurpose_code().matches("[0-9]+"))
                    doc_params.put("PURPOSE_CODE", parameters.get(tr_template.getPurpose_code()));
                else
                    doc_params.put("PURPOSE_CODE", tr_template.getPurpose_code());

                doc_params.put("TYPE_DOC", tr_template.getDoc_type());
                doc_params.put("PDC", tr_template.getPdc());
                doc_params.put("PARENT_GROUP_ID", parameters.get("PARENT_GROUP_ID"));
                doc_params.put("PARENT_ID", parameters.get("PARENT_ID"));
                doc_params.put("parent_deal_id", parameters.contains("parent_deal_id") ? parameters.get("parent_deal_id") : 1);
                doc_params.put("tr_pay_id", parameters.get("tr_pay_id"));
                doc_params.put("CASH_CODE", tr_template.getCash_code());
                doc_params.put("id_transh", tr_template.getId_transh_purp() == null ? 0 :
                        Long.parseLong(tr_template.getId_transh_purp()));
                doc_params.put("branch", parameters.get("branch"));
                if (!tr_acc_ct.getAcc_mfo().equals(tr_acc_dt.getAcc_mfo()) &&
                        !tr_acc_ct.getAccount().substring(5, 8).equals("000") &&
                        !tr_acc_ct.getAccount().substring(5, 8).equals("860") &&
                        (Long.valueOf(tr_template.getPay_type()).equals(1l) ||
                                Long.valueOf(tr_template.getPay_type()).equals(2l)))
                    tr_template.setPay_type(2l);
                if (tr_acc_ct.getAcc_mfo().equals(tr_acc_dt.getAcc_mfo()) &&
                        !tr_acc_ct.getAccount().substring(5, 8).equals("000") &&
                        !tr_acc_ct.getAccount().substring(5, 8).equals("860") &&
                        (Long.valueOf(tr_template.getPay_type()).equals(1l) ||
                                Long.valueOf(tr_template.getPay_type()).equals(2l)))
                    tr_template.setPay_type(1l);
                doc_params.put("deal_group_id", Long.valueOf(((Bf_tr_paytype) this.bf_tr_paytypes.get(Long.valueOf(tr_template.getPay_type()))).getDeal_group_id()));
                doc_params.put("deal_id", Long.valueOf(tr_template.getTrans_type()));
                //doc_params.put("id_transh", tr_template.getId_transh_purp());
                doc_params.put("id_transh_purp", tr_template.getId_transh_purp() == null ? "0" :
                        tr_template.getId_transh_purp());
                doc_params.put("cs_prep", parameters.get("cs_prep"));

                ISLogger.getLogger().error("DOC PARAMS " + doc_params.getParametersHashmap().toString());
                Long localLong1 = Service.create_tr_paydoc(doc_params, tr_template.getOrd(), c);
            }
        }
        return tr_pay_id.longValue();
    }

    public List<General> input_general_documents_in_operation(long tr_pay_id, Connection c)
            throws Exception {
        return Service.action_general_doc(Long.valueOf(tr_pay_id), 1L, c);
    }

    public List<General> input_general_documents_in_operation(Connection c, HashMap<String, Object> initialised_statements, ResultSet paydocs_rs)
            throws Exception {
        return Service.action_general_doc(1L, c, initialised_statements, paydocs_rs);
    }

    public void init(Connection c)
            throws Exception {
        this.tr_templates = Service.get_tr_template(c);
        this.tr_accs = Service.get_accounts(c);
        this.bf_tr_paytypes = Service.get_bf_tr_paytypes(c);
    }
}
