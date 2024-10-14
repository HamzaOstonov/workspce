package accounting_transaction_4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class Bf_tr_acc_DAO
{
	private Connection c;
	private HashMap<String, HashMap<Long, Bf_tr_acc>> loaded_tr_acounts = new HashMap<String, HashMap<Long,Bf_tr_acc>>();
	private PreparedStatement ps_select_by_branch_template_id;
	private ResultSet rs_select_by_branch_template_id;
	
	public Bf_tr_acc get_bf_tr_acc(String branch, Long acc_template_id) throws SQLException
	{
		
		if(this.loaded_tr_acounts.containsKey(branch) && this.loaded_tr_acounts.get(branch).containsKey(acc_template_id))
			return this.loaded_tr_acounts.get(branch).get(acc_template_id);
		try
		{
			this.ps_select_by_branch_template_id.setString(1, branch);
			this.ps_select_by_branch_template_id.setLong(2, acc_template_id);
			
			this.rs_select_by_branch_template_id = this.ps_select_by_branch_template_id.executeQuery();
			
			if(!this.rs_select_by_branch_template_id.next()) 
				throw new NoSuchElementException("setting for account with template id "+acc_template_id+" for "+branch+" branch not found in bf_tr_acc");
			
			Bf_tr_acc bf_tr_acc = new Bf_tr_acc(
					this.rs_select_by_branch_template_id.getLong("id"), 
					this.rs_select_by_branch_template_id.getString("branch"), 
					this.rs_select_by_branch_template_id.getLong("acc_template_id"), 
					this.rs_select_by_branch_template_id.getString("acc_mfo"), 
					this.rs_select_by_branch_template_id.getString("account"), 
					this.rs_select_by_branch_template_id.getString("acc_name"), 
					this.rs_select_by_branch_template_id.getString("acc_name_and_inn")
					);

			com.is.LtLogger.getLogger().error("bf_once_acc.getValue()_get_bf_tr_acc_bf_tr_acc.getId() = "+bf_tr_acc.getId());
			com.is.LtLogger.getLogger().error("bf_once_acc.getValue()_get_bf_tr_acc_bf_tr_acc.getBranch() = "+bf_tr_acc.getBranch());
			com.is.LtLogger.getLogger().error("bf_once_acc.getValue()_get_bf_tr_acc_bf_tr_acc.getAcc_mfo() = "+bf_tr_acc.getAcc_mfo());
			com.is.LtLogger.getLogger().error("bf_once_acc.getValue()_get_bf_tr_acc_bf_tr_acc.getAcc_name() = "+bf_tr_acc.getAcc_name());
			com.is.LtLogger.getLogger().error("bf_once_acc.getValue()_get_bf_tr_acc_bf_tr_acc.getAcc_name_and_inn() = "+bf_tr_acc.getAcc_name_and_inn());
			com.is.LtLogger.getLogger().error("bf_once_acc.getValue()_get_bf_tr_acc_bf_tr_acc.getAccount() = "+bf_tr_acc.getAccount());
			com.is.LtLogger.getLogger().error("bf_once_acc.getValue()_get_bf_tr_acc_bf_tr_acc.getAcc_template_id() = "+bf_tr_acc.getAcc_template_id());
			
            
			
			if(this.loaded_tr_acounts.containsKey(branch))
				this.loaded_tr_acounts.get(branch).put(acc_template_id, bf_tr_acc);
			else
			{
				HashMap<Long, Bf_tr_acc> bf_tr_acc_map = new HashMap<Long, Bf_tr_acc>();
				bf_tr_acc_map.put(acc_template_id, bf_tr_acc);
				this.loaded_tr_acounts.put(branch, bf_tr_acc_map);
			}
			return bf_tr_acc;
		}
		finally
		{
			if(this.rs_select_by_branch_template_id != null) try{this.rs_select_by_branch_template_id.close();} catch(Exception e) {}
		}
	}
	
	public Bf_tr_acc_DAO(Connection c) throws SQLException
	{
		this.c = c;
		this.ps_select_by_branch_template_id = 
			this.c.prepareStatement("select * from bf_tr_acc a where a.branch = ? and a.acc_template_id = ?");
	}
	public void close()
	{
		if(this.ps_select_by_branch_template_id != null) try{this.ps_select_by_branch_template_id.close();} catch(Exception e) {}
	}
}
