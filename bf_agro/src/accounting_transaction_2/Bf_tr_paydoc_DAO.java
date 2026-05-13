package accounting_transaction_2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.is.ISLogger;
import com.is.utils.CheckNull;

public class Bf_tr_paydoc_DAO
{
	private Connection c;
	private PreparedStatement ps_get_next_id;
	private ResultSet rs_get_next_id;
	private PreparedStatement ps_insert_paydoc;
	private PreparedStatement ps_get_paydocs_by_pay;
	
	public Long get_next_id() throws SQLException
	{
		try
		{
			this.rs_get_next_id = this.ps_get_next_id.executeQuery();
			if(rs_get_next_id.next())
				return this.rs_get_next_id.getLong("res");
			else throw new IllegalArgumentException("next id for bf_tr_paydocs not found");
		}
		finally
		{
			if(this.rs_get_next_id != null) try{this.rs_get_next_id.close();} catch(Exception e) {ISLogger.getLogger().error(CheckNull.getPstr(e));}
		}
	}
	
	public Bf_tr_paydoc[] get_paydocs_by_pay(Long pay_id) throws SQLException
	{
		ResultSet rs_get_paydocs_by_pay = null;
		try
		{
			List<Bf_tr_paydoc> res_list = new ArrayList<Bf_tr_paydoc>();
			ps_get_paydocs_by_pay.setLong(1, pay_id);
			rs_get_paydocs_by_pay = ps_get_paydocs_by_pay.executeQuery();
			while(rs_get_paydocs_by_pay.next())
			{
				res_list.add(new Bf_tr_paydoc(
						rs_get_paydocs_by_pay.getLong("id"),
						rs_get_paydocs_by_pay.getLong("pay_id"),
						rs_get_paydocs_by_pay.getString("branch"),
						rs_get_paydocs_by_pay.getDate("d_date"),
						rs_get_paydocs_by_pay.getString("bank_cl"),
						rs_get_paydocs_by_pay.getString("acc_cl"),
						rs_get_paydocs_by_pay.getString("name_cl"),
						rs_get_paydocs_by_pay.getString("bank_co"),
						rs_get_paydocs_by_pay.getString("acc_co"),
						rs_get_paydocs_by_pay.getString("name_co"),
						rs_get_paydocs_by_pay.getLong("summa"),
						rs_get_paydocs_by_pay.getString("purpose"),
						rs_get_paydocs_by_pay.getString("type_doc"),
						rs_get_paydocs_by_pay.getString("pdc"),
						rs_get_paydocs_by_pay.getLong("parent_group_id"),
						rs_get_paydocs_by_pay.getLong("parent_id"),
						rs_get_paydocs_by_pay.getString("cash_code"),
						rs_get_paydocs_by_pay.getLong("id_transh_purp"),
						rs_get_paydocs_by_pay.getString("schema_name"),
						rs_get_paydocs_by_pay.getLong("ord"),
						rs_get_paydocs_by_pay.getString("g_branch"),
						rs_get_paydocs_by_pay.getLong("g_docid"),
						rs_get_paydocs_by_pay.getString("purp_code"),
						rs_get_paydocs_by_pay.getLong("pay_type"),
						rs_get_paydocs_by_pay.getLong("trans_type"),
						rs_get_paydocs_by_pay.getLong("acc_dt_id"),
						rs_get_paydocs_by_pay.getLong("acc_ct_id"),
						rs_get_paydocs_by_pay.getLong("deal_group_id"),
						rs_get_paydocs_by_pay.getLong("deal_id"),
						rs_get_paydocs_by_pay.getInt("parent_deal_id")
						));
			}
			if(res_list.size() == 0)
				throw new NoSuchElementException("paydoc for pay id "+pay_id+" not found");
			Bf_tr_paydoc[] res = new Bf_tr_paydoc[res_list.size()];
			res = res_list.toArray(res);
			return res;
		}
		finally
		{
			try{rs_get_paydocs_by_pay.close();}catch(Exception e){}
		}
	}
	
	public void insert(Bf_tr_paydoc bf_tr_paydoc) throws SQLException
	{
		
		ISLogger.getLogger().error(bf_tr_paydoc.toString());
		
		this.ps_insert_paydoc.setLong(1, bf_tr_paydoc.getId());
		this.ps_insert_paydoc.setLong(2, bf_tr_paydoc.getPay_id());
		this.ps_insert_paydoc.setString(3, bf_tr_paydoc.getBranch());
		this.ps_insert_paydoc.setDate(4, new java.sql.Date(bf_tr_paydoc.getD_date().getTime()));
		this.ps_insert_paydoc.setString(5, bf_tr_paydoc.getBank_cl());
		this.ps_insert_paydoc.setString(6, bf_tr_paydoc.getAcc_cl());
		this.ps_insert_paydoc.setString(7, bf_tr_paydoc.getName_cl());
		this.ps_insert_paydoc.setString(8, bf_tr_paydoc.getBank_co());
		this.ps_insert_paydoc.setString(9, bf_tr_paydoc.getAcc_co());
		this.ps_insert_paydoc.setString(10, bf_tr_paydoc.getName_co());
		this.ps_insert_paydoc.setLong(11, bf_tr_paydoc.getSumma());
		this.ps_insert_paydoc.setString(12, bf_tr_paydoc.getPurpose());
		this.ps_insert_paydoc.setString(13, bf_tr_paydoc.getType_doc());
		this.ps_insert_paydoc.setString(14, bf_tr_paydoc.getPdc());
		this.ps_insert_paydoc.setLong(15, bf_tr_paydoc.getParent_group_id());
		this.ps_insert_paydoc.setLong(16, bf_tr_paydoc.getParent_id());
		this.ps_insert_paydoc.setString(17, bf_tr_paydoc.getCash_code());
		this.ps_insert_paydoc.setLong(18, bf_tr_paydoc.getId_transh_purp());
		this.ps_insert_paydoc.setString(19, bf_tr_paydoc.getSchema_name());
		this.ps_insert_paydoc.setLong(20, bf_tr_paydoc.getOrd());
		this.ps_insert_paydoc.setString(21, bf_tr_paydoc.getG_branch());
		
		if(bf_tr_paydoc.getG_docid() == null)
			this.ps_insert_paydoc.setNull(22, java.sql.Types.NUMERIC);
		else
			this.ps_insert_paydoc.setLong(22, bf_tr_paydoc.getG_docid());
		
		this.ps_insert_paydoc.setString(23, bf_tr_paydoc.getPurp_code());
		this.ps_insert_paydoc.setLong(24, bf_tr_paydoc.getPay_type());
		this.ps_insert_paydoc.setLong(25, bf_tr_paydoc.getTrans_type());
		this.ps_insert_paydoc.setLong(26, bf_tr_paydoc.getAcc_dt_id());
		this.ps_insert_paydoc.setLong(27, bf_tr_paydoc.getAcc_ct_id());
		this.ps_insert_paydoc.setLong(28, bf_tr_paydoc.getDeal_group_id());
		this.ps_insert_paydoc.setLong(29, bf_tr_paydoc.getDeal_id());
		this.ps_insert_paydoc.setInt(30, bf_tr_paydoc.getParent_deal_id());
		this.ps_insert_paydoc.execute();
	}
	
	public Bf_tr_paydoc_DAO(Connection c) throws SQLException
	{
		this.c = c;
		this.ps_get_next_id = c.prepareStatement("select seq_bf_tr_paydocs.nextval res from dual");
		this.ps_insert_paydoc = this.c.prepareStatement(
				"insert into bf_tr_paydocs( " +
				"id, " +
				"pay_id, " +
				"branch, " +
				"d_date, " +
				"bank_cl, " +
				"acc_cl, " +
				"name_cl, " +
				"bank_co, " +
				"acc_co, " +
				"name_co, " +
				"summa, " +
				"purpose, " +
				"type_doc, " +
				"pdc, " +
				"parent_group_id, " +
				"parent_id, " +
				"cash_code, " +
				"id_transh_purp, " +
				"schema_name, " +
				"ord, " +
				"g_branch, " +
				"g_docid, " +
				"purp_code, " +
				"pay_type, " +
				"trans_type, " +
				"acc_dt_id, " +
				"acc_ct_id, " +
				"deal_group_id, " +
				"deal_id, " +
				"parent_deal_id) " +
				"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		this.ps_get_paydocs_by_pay = 
			this.c.prepareStatement("select * from bf_tr_paydocs p where p.pay_id = ? order by p.ord");
	}
	
	public void close()
	{
		if(this.ps_get_next_id != null) try{this.ps_get_next_id.close();} catch(Exception e) {}
		if(this.ps_insert_paydoc != null) try{this.ps_insert_paydoc.close();} catch(Exception e) {}
		if(this.ps_get_paydocs_by_pay != null) try{this.ps_get_paydocs_by_pay.close();} catch(Exception e) {}
	}
}
