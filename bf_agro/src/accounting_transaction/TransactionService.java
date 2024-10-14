package accounting_transaction;

import general.General;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.kernel.parameter.Parameters;


public class TransactionService
{
	private //static 
	HashMap<Long, List<Tr_template>> tr_templates = null;
	private //static 
	HashMap<String, HashMap<Long, Bf_tr_acc>> tr_accs = null;
	private HashMap<Long, Bf_tr_paytype> bf_tr_paytypes = null;
	private static Pattern p_tag = Pattern.compile("(<@.+?@>)");
	
    //public void run (Long deal_group, Long deal_id, Long action_id, Parameters parameters, Connection c)
	
	public long execute_operation(Long operation_id, Parameters parameters, Connection c) 
		throws Exception
	{
		List<Tr_template> current_templates = tr_templates.get(operation_id);
		
		Parameters tr_pay_param = new Parameters();
		
		tr_pay_param.put("branch", parameters.get("branch"));
		tr_pay_param.put("operation_id", parameters.get("operation_id"));
		tr_pay_param.put("parent_group_id", parameters.get("parent_group_id"));
		tr_pay_param.put("cs_prep", parameters.get("cs_prep"));
		
		Long tr_pay_id = Service.create_tr_pay(tr_pay_param, c);
        //System.out.println("pay "+ tr_pay_id);
        
		com.is.ISLogger.getLogger().error("AAAAAAAAAA0 "+parameters.getParametersHashmap().toString());
		
		String str1 = "";
		ObjectMapper objectMapper = new ObjectMapper();
		
		//try {
		//	str1 = objectMapper.writerWithDefaultPrettyPrinter()
		//			.writeValueAsString(tr_accs);
		//} catch (Exception e22) {
		//	str1 = " str1=error tr_accs";
		//} finally {
		//}
		//com.is.ISLogger.getLogger().error(
		//		"** not err tr_accs  ************** " + str1);
		
		parameters.put("tr_pay_id", tr_pay_id);
		if(current_templates != null)
		for(int i = 0; i < current_templates.size(); i++)
		{

			com.is.ISLogger.getLogger().error(
					"** not err i=  ************** " + i);
			
			//try {
			//	str1 = objectMapper.writerWithDefaultPrettyPrinter()
			//			.writeValueAsString(tr_accs);
			//} catch (Exception e22) {
			//	str1 = " str1=error tr_accs";
			//} finally {
			//}
			//com.is.ISLogger.getLogger().error(
			//		"** not err tr_accs  **************" + str1);
			
			Tr_template tr_template = current_templates.get(i);
			//tr_template.getPay_type()
			Parameters doc_params = new Parameters();
			
			Bf_tr_acc tr_acc_ct = tr_accs.get((String)parameters.get("branch")).
				get(tr_template.getAcc_ct());

			try {
				str1 = objectMapper.writerWithDefaultPrettyPrinter()
						.writeValueAsString(tr_acc_ct);
			} catch (Exception e22) {
				str1 = " str1=error tr_acc_ct";
			} finally {
			}
			com.is.ISLogger.getLogger().error(
					"** not err tr_acc_ct  ************** " + (String)parameters.get("branch")+" " + str1);
			
			Bf_tr_acc tr_acc_dt = tr_accs.get((String)parameters.get("branch")).
				get(tr_template.getAcc_dt());

			try {
				str1 = objectMapper.writerWithDefaultPrettyPrinter()
						.writeValueAsString(tr_acc_dt);
			} catch (Exception e22) {
				str1 = " str1=error tr_acc_dt";
			} finally {
			}
			com.is.ISLogger.getLogger().error(
					"** not err tr_acc_dt  ************** " + (String)parameters.get("branch")+" " + str1);
			
			
			if (tr_acc_ct.getAccount().matches("[0-9]+"))
				doc_params.put("ACC_CO", tr_acc_ct.getAccount());
			else doc_params.put("ACC_CO", parameters.get(tr_acc_ct.getAccount()));
			
			if (tr_acc_dt.getAccount().matches("[0-9]+"))
				doc_params.put("ACC_CL", tr_acc_dt.getAccount());
			else doc_params.put("ACC_CL", parameters.get(tr_acc_dt.getAccount()));
			
			if (tr_template.getAmount() != null){
			System.out.println("SUMMA "+parameters.get((tr_template.getAmount())));
			}
			if ((tr_template.getAmount() != null)&&(tr_template.getAmount().length() > 0))
				doc_params.put("SUMMA", (Long)((parameters.get((tr_template.getAmount())).getClass().getName().equals("java.lang.Long"))?parameters.get((tr_template.getAmount())):
					Long.parseLong(parameters.get((tr_template.getAmount())).toString())));
			else doc_params.put("SUMMA", (Long)((parameters.get("SUMMA").getClass().getName().equals("java.lang.Long"))?parameters.get("SUMMA"):
				Long.parseLong(parameters.get("SUMMA").toString())));
					
			doc_params.put("D_DATE", Service.get_day(c));
			//doc_params.put("BANK_CL", parameters.get("branch"));
			doc_params.put("BANK_CL", tr_acc_dt.getAcc_mfo());
			
			doc_params.put("NAME_CL", Service.get_name_and_inn((String)doc_params.get("ACC_CL"), c));
  		    //doc_params.put("BANK_CO", parameters.get("branch"));
  		    doc_params.put("BANK_CO", tr_acc_ct.getAcc_mfo());
				
			doc_params.put("NAME_CO", Service.get_name_and_inn((String)doc_params.get("ACC_CO"), c));
			doc_params.put("PURPOSE_CODE", tr_template.getPurpose_code());

			Matcher m = p_tag.matcher(tr_template.getPurpose());
			String res_purpose = tr_template.getPurpose();
			//com.is.ISLogger.getLogger().error(
			//		"** not err tr_template.getPurpose_code()  ************** " + tr_template.getPurpose_code());
			
			while (m.find())
			{
				res_purpose=res_purpose.replace(
						m.group(1),
						(String)parameters.get(m.group(1).substring(
										2,
										m.group(1).length() - 2
										)
								));
			}
			
			//doc_params.put("PURPOSE_CODE", res_purpose);
			doc_params.put("PURPOSE", res_purpose);
			doc_params.put("TYPE_DOC", tr_template.getDoc_type());
			doc_params.put("PDC", tr_template.getPdc());
			doc_params.put("PARENT_GROUP_ID", parameters.get("PARENT_GROUP_ID"));
			doc_params.put("PARENT_ID", parameters.get("PARENT_ID"));
			doc_params.put("tr_pay_id", parameters.get("tr_pay_id"));
			doc_params.put("CASH_CODE", tr_template.getCash_code());
			doc_params.put("id_transh", tr_template.getId_transh_purp());
			//doc_params.put("branch", parameters.get("branch"));
			doc_params.put("branch", tr_acc_dt.getAcc_mfo());
			doc_params.put("deal_group_id", bf_tr_paytypes.get(tr_template.getPay_type()).getDeal_group_id());
			//int dealId =(int) bf_tr_paytypes.get(tr_template.getPay_type()).getDeal_group_id();
			//doc_params.put("deal_id",(dealId ==14) ?1: tr_template.getTrans_type());
			doc_params.put("deal_id", tr_template.getTrans_type());
			doc_params.put("id_transh", Long.toString(tr_template.getId_transh_purp()));
			doc_params.put("cs_prep", parameters.get("cs_prep"));
			
			Long created_paydoc = Service.create_tr_paydoc(doc_params, tr_template.getOrd(), c);
            //System.out.println("paydoc "+ created_paydoc);
		}
		return tr_pay_id;
	}
	
	public List<General> input_general_documents_in_operation(long tr_pay_id, Connection c) throws Exception
	{
		return Service.action_general_doc(tr_pay_id, 1, c);
	}
	
	public List<General> input_general_documents_in_operation(Connection c, 
			HashMap<String, Object> initialised_statements, ResultSet paydocs_rs) throws Exception
	{
		return Service.action_general_doc(1, c, initialised_statements, paydocs_rs);
	}
	
	public void init(Connection c) throws Exception
	{
		//if (tr_templates == null)
			tr_templates = Service.get_tr_template(c);
		//if (tr_accs == null)
			tr_accs = Service.get_accounts(c);
			bf_tr_paytypes = Service.get_bf_tr_paytypes(c);
	}
	
	
}
