package accounting_transaction_2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import com.is.ISLogger;
import com.is.utils.CheckNull;

public class Bf_tr_template_DAO
{
	private Connection c;
	private HashMap<Long, Bf_tr_template[]> loaded_templates = new HashMap<Long, Bf_tr_template[]>();
	private PreparedStatement ps_select_templates_by_operation;
	private ResultSet rs_select_templates_by_operation;
	
	public Bf_tr_template[] get_templates_for_operation(Long operation_id) throws SQLException
	{
		if(this.loaded_templates.containsKey(operation_id))
			return this.loaded_templates.get(operation_id);
		
		try
		{
			this.ps_select_templates_by_operation.setLong(1, operation_id);
			this.rs_select_templates_by_operation = this.ps_select_templates_by_operation.executeQuery();
			
			List<Bf_tr_template> templates_list = new ArrayList<Bf_tr_template>();
			while(this.rs_select_templates_by_operation.next())
			{
				templates_list.add(new Bf_tr_template(
						this.rs_select_templates_by_operation.getLong("id"), 
						this.rs_select_templates_by_operation.getLong("operation_id"), 
						this.rs_select_templates_by_operation.getLong("acc_dt"), 
						this.rs_select_templates_by_operation.getLong("acc_ct"), 
						this.rs_select_templates_by_operation.getString("currency"), 
						this.rs_select_templates_by_operation.getString("doc_type"), 
						this.rs_select_templates_by_operation.getString("cash_code"), 
						this.rs_select_templates_by_operation.getString("purpose"), 
						this.rs_select_templates_by_operation.getString("purpose_code"), 
						this.rs_select_templates_by_operation.getInt("ord"), 
						this.rs_select_templates_by_operation.getString("id_transh_purp"), 
						this.rs_select_templates_by_operation.getInt("pay_type"), 
						this.rs_select_templates_by_operation.getLong("trans_type"), 
						this.rs_select_templates_by_operation.getLong("perc_for_tr"), 
						this.rs_select_templates_by_operation.getString("pdc"), 
//						this.rs_select_templates_by_operation.getInt("rounding_type"),
            null,
						this.rs_select_templates_by_operation.getString("amount")
						));
			}
			if(templates_list.size() == 0)
				throw new NoSuchElementException("settings for transaction template with operation id "+operation_id+" not found in bf_tr_template");
			
			Bf_tr_template[] templates = new Bf_tr_template[templates_list.size()];
			templates = templates_list.toArray(templates);
			
			this.loaded_templates.put(operation_id, templates);
			return templates;
		}
		finally
		{
			if(this.rs_select_templates_by_operation != null) try{this.rs_select_templates_by_operation.close();} catch(Exception e) {}
		}
	}
	
	public Bf_tr_template_DAO(Connection c) throws SQLException
	{
		this.c = c;
		this.ps_select_templates_by_operation = this.c.prepareStatement("select * from bf_tr_template t where t.operation_id = ? order by t.ord");
	}
	
	public void close()
	{
		if(this.ps_select_templates_by_operation != null) try{this.ps_select_templates_by_operation.close();} catch(Exception e) {}
	}
}
