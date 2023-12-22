package com.is.file_reciever_srv.recievers.cb_cash_operaions;

import java.util.Date;

public class Cb_cash_inq_approval
{
	private Long id; 
	private Date reciever_bank_oper_date;
	private String request_bank_code; 
	private String reciever_bank_code;
	private Long request_bank_inv_id;
	private Long reciever_bank_inv_id;
	private Long amount;
	private String inv_number;
	private Date inv_date;
	private String req_number;
	private Date req_date;
	private Date in_date;
	private Integer state_id;
	private Long ext_record_id;
	
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public Date getReciever_bank_oper_date()
	{
		return reciever_bank_oper_date;
	}
	public void setReciever_bank_oper_date(Date reciever_bank_oper_date)
	{
		this.reciever_bank_oper_date = reciever_bank_oper_date;
	}
	public String getRequest_bank_code()
	{
		return request_bank_code;
	}
	public void setRequest_bank_code(String request_bank_code)
	{
		this.request_bank_code = request_bank_code;
	}
	public String getReciever_bank_code()
	{
		return reciever_bank_code;
	}
	public void setReciever_bank_code(String reciever_bank_code)
	{
		this.reciever_bank_code = reciever_bank_code;
	}
	public Long getRequest_bank_inv_id()
	{
		return request_bank_inv_id;
	}
	public void setRequest_bank_inv_id(Long request_bank_inv_id)
	{
		this.request_bank_inv_id = request_bank_inv_id;
	}
	public Long getReciever_bank_inv_id()
	{
		return reciever_bank_inv_id;
	}
	public void setReciever_bank_inv_id(Long reciever_bank_inv_id)
	{
		this.reciever_bank_inv_id = reciever_bank_inv_id;
	}
	public Long getAmount()
	{
		return amount;
	}
	public void setAmount(Long amount)
	{
		this.amount = amount;
	}
	public String getInv_number()
	{
		return inv_number;
	}
	public void setInv_number(String inv_number)
	{
		this.inv_number = inv_number;
	}
	public Date getInv_date()
	{
		return inv_date;
	}
	public void setInv_date(Date inv_date)
	{
		this.inv_date = inv_date;
	}
	public String getReq_number()
	{
		return req_number;
	}
	public void setReq_number(String req_number)
	{
		this.req_number = req_number;
	}
	public Date getReq_date()
	{
		return req_date;
	}
	public void setReq_date(Date req_date)
	{
		this.req_date = req_date;
	}
	public Date getIn_date()
	{
		return in_date;
	}
	public void setIn_date(Date in_date)
	{
		this.in_date = in_date;
	}
	public Integer getState_id()
	{
		return state_id;
	}
	public void setState_id(Integer state_id)
	{
		this.state_id = state_id;
	}
	public Long getExt_record_id()
	{
		return ext_record_id;
	}
	public void setExt_record_id(Long ext_record_id)
	{
		this.ext_record_id = ext_record_id;
	}
	
	public Cb_cash_inq_approval(Long id, Date reciever_bank_oper_date,
			String request_bank_code, String reciever_bank_code,
			Long request_bank_inv_id, Long reciever_bank_inv_id,
			Long amount, String inv_number, Date inv_date, String req_number,
			Date req_date, Date in_date, Integer state_id, Long ext_record_id)
	{
		super();
		this.id = id;
		this.reciever_bank_oper_date = reciever_bank_oper_date;
		this.request_bank_code = request_bank_code;
		this.reciever_bank_code = reciever_bank_code;
		this.request_bank_inv_id = request_bank_inv_id;
		this.reciever_bank_inv_id = reciever_bank_inv_id;
		this.amount = amount;
		this.inv_number = inv_number;
		this.inv_date = inv_date;
		this.req_number = req_number;
		this.req_date = req_date;
		this.in_date = in_date;
		this.state_id = state_id;
		this.ext_record_id = ext_record_id;
	}
	
	public Cb_cash_inq_approval()
	{
		super();
	}
}
