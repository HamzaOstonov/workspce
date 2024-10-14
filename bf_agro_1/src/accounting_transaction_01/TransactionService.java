package accounting_transaction_01;

import general.General;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	//private //static 
	//HashMap<String, HashMap<Long, Bf_tr_acc>> tr_accs = null;
	private static
	HashMap<String, HashMap<Long, Bf_tr_acc>> my_tr_accs = null;
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
			//String branch1="";
			//String branch_terminal=(String)parameters.get("A_BANK");
			//String branch_card=(String)parameters.get("A_CARD_BANK");
			String branch_dt="";
			String branch_ct="";
			com.is.ISLogger.getLogger().error("not err i=  ************** " + i);
			
			Tr_template tr_template = current_templates.get(i);

			try {
				str1 = objectMapper.writerWithDefaultPrettyPrinter()
						.writeValueAsString(tr_template);
			} catch (Exception e22) {
				str1 = " str1=error tr_template";
			} finally {
			}
			com.is.ISLogger.getLogger().error("not err tr_template  **************" + str1);
			
			//tr_template.getPay_type()
			Parameters doc_params = new Parameters();
			
			if (parameters.contains("A_BANK")) {
				branch_dt=(String)parameters.get("A_BANK");
				branch_ct=(String)parameters.get("A_BANK");
			}
			else {
				branch_dt=(String)parameters.get("A_CARD_BANK");
			    branch_ct=(String)parameters.get("A_CARD_BANK");
			}
			//branch_ct=(String)parameters.get("A_BANK");
			
			//branch1=(String)parameters.get("branch");
			if (tr_template.getAcc_dt()==29) {
				branch_dt=(String)parameters.get("A_CARD_BANK");
				if (tr_template.getAcc_ct()==24)
					if (parameters.contains("A_BANK")) 
					    branch_ct=(String)parameters.get("A_BANK");
			} 
			//if (tr_template.getAcc_ct()==29) {
			//	branch_ct=(String)parameters.get("A_CARD_BANK");
			//}			
			
			
			HashMap<Long, Bf_tr_acc> my_tracc = my_tr_accs.get(branch_dt);
			com.is.ISLogger.getLogger().error("** not err murojaat ? "+branch_dt);
			if (my_tracc == null || my_tracc.isEmpty() )
			{
				com.is.ISLogger.getLogger().error("** not err murojaat ketopti "+branch_dt);
				load_my_tr_acc(c, branch_dt);
			}
			
			my_tracc = my_tr_accs.get(branch_ct);
			com.is.ISLogger.getLogger().error("** not err murojaat ? "+branch_ct);
			if (my_tracc == null || my_tracc.isEmpty() )
			{
				com.is.ISLogger.getLogger().error("** not err murojaat ketopti "+branch_ct);
				load_my_tr_acc(c, branch_ct);
			}			

			
			Bf_tr_acc tr_acc_dt = my_tr_accs.get(branch_dt).get(tr_template.getAcc_dt());
			System.out.println("tr_template.getAcc_dt="+tr_template.getAcc_dt()); 
			try {
				str1 = objectMapper.writerWithDefaultPrettyPrinter()
						.writeValueAsString(tr_acc_dt);
			} catch (Exception e22) {
				str1 = " str1=error tr_acc_dt";
			} finally {
			}
			com.is.ISLogger.getLogger().error("not err tr_acc_dt  ************** " + branch_dt+ ", " +tr_template.getAcc_dt()+" : "+ str1);
			
			Bf_tr_acc tr_acc_ct = my_tr_accs.get(branch_ct).get(tr_template.getAcc_ct());
			try {
				str1 = objectMapper.writerWithDefaultPrettyPrinter()
						.writeValueAsString(tr_acc_ct);
			} catch (Exception e22) {
				str1 = " str1=error tr_acc_ct";
			} finally {
			}
			com.is.ISLogger.getLogger().error("not err tr_acc_ct  ************** " + branch_ct+"," + tr_template.getAcc_ct() +": " + str1);
			
			
			if (tr_acc_dt.getAccount().matches("[0-9]+"))
				doc_params.put("ACC_CL", tr_acc_dt.getAccount());
			else doc_params.put("ACC_CL", parameters.get(tr_acc_dt.getAccount()));

			if (tr_acc_ct.getAccount().matches("[0-9]+"))
				doc_params.put("ACC_CO", tr_acc_ct.getAccount());
			else doc_params.put("ACC_CO", parameters.get(tr_acc_ct.getAccount()));

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
			
			doc_params.put("NAME_CL", Service.get_name_and_inn((String)doc_params.get("ACC_CL"), tr_acc_dt.getAcc_mfo(), c));
  		    //doc_params.put("BANK_CO", parameters.get("branch"));
  		    doc_params.put("BANK_CO", tr_acc_ct.getAcc_mfo());
				
			doc_params.put("NAME_CO", Service.get_name_and_inn((String)doc_params.get("ACC_CO"), tr_acc_ct.getAcc_mfo(), c));
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
			//tr_accs = Service.get_accounts(c);
        if (my_tr_accs==null) {
    		my_tr_accs = new HashMap<String, HashMap<Long, Bf_tr_acc>>();
        	//HashMap<Long,Bf_tr_acc> current_tr_acc = new HashMap<Long, Bf_tr_acc>();
        	//			current_tr_acc.put(0l, new Bf_tr_acc(
        	//					0L, "00000", 0L, "", "", ""));
            //my_tr_accs.put("00000",current_tr_acc) ; 			
        }
		bf_tr_paytypes = Service.get_bf_tr_paytypes(c);
	}
	
	public void load_my_tr_acc(Connection c, String branch) throws Exception {
		//my_tr_accs=null;
		
		//HashMap<String, HashMap<Long, Bf_tr_acc>> accounts = new HashMap<String, HashMap<Long, Bf_tr_acc>>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ps = c.prepareStatement(
					"select * from bf_tr_acc t where t.branch=? order by t.branch, t.acc_template_id");
			ps.setString(1, branch);
			rs = ps.executeQuery();
			
			HashMap<Long,Bf_tr_acc> current_tr_acc = new HashMap<Long, Bf_tr_acc>();
			while (rs.next())
			{
				Bf_tr_acc new_tr_acc = new Bf_tr_acc(
						rs.getLong("id"), 
						rs.getString("branch"), 
						rs.getLong("acc_template_id"), 
						rs.getString("acc_mfo"), 
						rs.getString("account"), 
						rs.getString("acc_name"));
				current_tr_acc.put(new_tr_acc.getAcc_template_id(), new_tr_acc);
			}
			my_tr_accs.put(branch, current_tr_acc);
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			try{if(rs!=null)rs.close();}catch(Exception e){}
			try{if(ps!=null)ps.close();}catch(Exception e){}
		}
		
	}
	
	
}
