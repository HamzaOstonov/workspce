package accounting_transaction_4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Bf_tr_pay_DAO
{
	private Connection c;
	private PreparedStatement ps_get_next_id;
	private ResultSet rs_get_next_id;
	private PreparedStatement ps_insert_pay;
	
	public Long get_next_id() throws SQLException
	{
		try
		{
			this.rs_get_next_id = this.ps_get_next_id.executeQuery();
			if(rs_get_next_id.next())
				return this.rs_get_next_id.getLong("res");
			else throw new IllegalArgumentException("next id for bf_tr_pay not found");
		}
		finally
		{
			if(this.rs_get_next_id != null) try{this.rs_get_next_id.close();} catch(Exception e) {}
		}
	}
	
	public void insert(Bf_tr_pay bf_tr_pay) throws SQLException
	{
		this.ps_insert_pay.setLong(1, bf_tr_pay.getId());
		this.ps_insert_pay.setString(2, bf_tr_pay.getBranch());
		this.ps_insert_pay.setLong(3, bf_tr_pay.getOperation_id());
		this.ps_insert_pay.setLong(4, bf_tr_pay.getAmount());
		this.ps_insert_pay.setString(5, bf_tr_pay.getCard_acc());
		this.ps_insert_pay.setString(6, bf_tr_pay.getCur_acc());
		this.ps_insert_pay.setDate(7, new java.sql.Date(bf_tr_pay.getDate_created().getTime()));
		this.ps_insert_pay.setLong(8, bf_tr_pay.getParent_group_id());
		this.ps_insert_pay.setLong(9, bf_tr_pay.getState());
		this.ps_insert_pay.setString(10, bf_tr_pay.getAccount_no());
		this.ps_insert_pay.setString(11, bf_tr_pay.getCl_name());
		this.ps_insert_pay.setLong(12, bf_tr_pay.getEmp_id());
		this.ps_insert_pay.setLong(13, bf_tr_pay.getTieto_type());
		this.ps_insert_pay.setString(14, bf_tr_pay.getPan());
		this.ps_insert_pay.setLong(15, bf_tr_pay.getDeal_group());
		this.ps_insert_pay.setLong(16, bf_tr_pay.getDeal_id());
		this.ps_insert_pay.setString(17, bf_tr_pay.getDoc_num());
		this.ps_insert_pay.setLong(18, bf_tr_pay.getEqv_amount());
		this.ps_insert_pay.setLong(19, bf_tr_pay.getSub_id());
		this.ps_insert_pay.setLong(20, bf_tr_pay.getAmount_t());
		this.ps_insert_pay.setString(21, bf_tr_pay.getSubbranch_id());
		this.ps_insert_pay.execute();
	}
	
	public Bf_tr_pay_DAO(Connection c) throws SQLException
	{
		this.c = c;
		this.ps_get_next_id = c.prepareStatement("select seq_bf_tr_pay.nextval res from dual");
		this.ps_insert_pay = c.prepareStatement("insert info bf_tr_pay( " +
				"id, " +
				"branch, " +
				"operation_id, " +
				"amount, " +
				"card_acc, " +
				"cur_acc, " +
				"date_created, " +
				"parent_group_id, " +
				"state, " +
				"account_no, " +
				"cl_name, " +
				"emp_id, " +
				"tieto_type, " +
				"pan, " +
				"deal_group, " +
				"deal_id, " +
				"doc_num, " +
				"eqv_amount, " +
				"sub_id, " +
				"amount_t, " +
				"subbranch_id " +
				"values " +
				"( " +
				"?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? " +
				")");
	}
	
	public void close()
	{
		if(this.ps_get_next_id != null) try{this.ps_get_next_id.close();} catch(Exception e) {}
		if(this.ps_insert_pay != null) try{this.ps_insert_pay.close();} catch(Exception e) {}
	}
}
