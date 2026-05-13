package com.is.file_reciever_srv.recievers.cb_cash_operaions;

import java.util.Date;

public class Cb_cash_request
{
	private Long id;
	private Long ext_record_id;
	private String cb_request_number;
	private String request_type_code;
	private Date oper_day;
	private String reciever_bank_code;
	private String request_bank_code;
	private String request_number;
	private Date request_date;
	private Long amount;
	private String additional_info;
	private Integer state_id;
	private boolean is_sent_in_file;
	private boolean is_sent_inkassa;
	private String error_code;
	private Date in_date;
	
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public Long getExt_record_id()
	{
		return ext_record_id;
	}
	public void setExt_record_id(Long ext_record_id)
	{
		this.ext_record_id = ext_record_id;
	}
	public String getCb_request_number()
	{
		return cb_request_number;
	}
	public void setCb_request_number(String cb_request_number)
	{
		this.cb_request_number = cb_request_number;
	}
	public String getRequest_type_code()
	{
		return request_type_code;
	}
	public void setRequest_type_code(String request_type_code)
	{
		this.request_type_code = request_type_code;
	}
	public Date getOper_day()
	{
		return oper_day;
	}
	public void setOper_day(Date oper_day)
	{
		this.oper_day = oper_day;
	}
	public String getReciever_bank_code()
	{
		return reciever_bank_code;
	}
	public void setReciever_bank_code(String reciever_bank_code)
	{
		this.reciever_bank_code = reciever_bank_code;
	}
	public String getRequest_bank_code()
	{
		return request_bank_code;
	}
	public void setRequest_bank_code(String request_bank_code)
	{
		this.request_bank_code = request_bank_code;
	}
	public String getRequest_number()
	{
		return request_number;
	}
	public void setRequest_number(String request_number)
	{
		this.request_number = request_number;
	}
	public Date getRequest_date()
	{
		return request_date;
	}
	public void setRequest_date(Date request_date)
	{
		this.request_date = request_date;
	}
	public Long getAmount()
	{
		return amount;
	}
	public void setAmount(Long amount)
	{
		this.amount = amount;
	}
	public String getAdditional_info()
	{
		return additional_info;
	}
	public void setAdditional_info(String additional_info)
	{
		this.additional_info = additional_info;
	}
	public Integer getState_id()
	{
		return state_id;
	}
	public void setState_id(Integer state_id)
	{
		this.state_id = state_id;
	}
	public boolean isIs_sent_in_file()
	{
		return is_sent_in_file;
	}
	public void setIs_sent_in_file(boolean is_sent_in_file)
	{
		this.is_sent_in_file = is_sent_in_file;
	}
	public boolean isIs_sent_inkassa()
	{
		return is_sent_inkassa;
	}
	public void setIs_sent_inkassa(boolean is_sent_inkassa)
	{
		this.is_sent_inkassa = is_sent_inkassa;
	}
	public String getError_code()
	{
		return error_code;
	}
	public void setError_code(String error_code)
	{
		this.error_code = error_code;
	}
	public Date getIn_date()
	{
		return in_date;
	}
	public void setIn_date(Date in_date)
	{
		this.in_date = in_date;
	}
	
	public Cb_cash_request(Long id, Long ext_record_id,
			String cb_request_number, String request_type_code, Date oper_day,
			String reciever_bank_code, String request_bank_code,
			String request_number, Date request_date, Long amount,
			String additional_info, Integer state_id, boolean is_sent_in_file,
			boolean is_sent_inkassa, String error_code, Date in_date)
	{
		super();
		this.id = id;
		this.ext_record_id = ext_record_id;
		this.cb_request_number = cb_request_number;
		this.request_type_code = request_type_code;
		this.oper_day = oper_day;
		this.reciever_bank_code = reciever_bank_code;
		this.request_bank_code = request_bank_code;
		this.request_number = request_number;
		this.request_date = request_date;
		this.amount = amount;
		this.additional_info = additional_info;
		this.state_id = state_id;
		this.is_sent_in_file = is_sent_in_file;
		this.is_sent_inkassa = is_sent_inkassa;
		this.error_code = error_code;
		this.in_date = in_date;
	}
	
	public Cb_cash_request()
	{
		super();
	}
	
	@Override
	public String toString()
	{
		return "Cb_cash_request [id=" + id + ", ext_record_id=" + ext_record_id
				+ ", cb_request_number=" + cb_request_number
				+ ", request_type_code=" + request_type_code + ", oper_day="
				+ oper_day + ", reciever_bank_code=" + reciever_bank_code
				+ ", request_bank_code=" + request_bank_code
				+ ", request_number=" + request_number + ", request_date="
				+ request_date + ", amount=" + amount + ", additional_info="
				+ additional_info + ", state_id=" + state_id
				+ ", is_sent_in_file=" + is_sent_in_file + ", is_sent_inkassa="
				+ is_sent_inkassa + ", error_code=" + error_code + "]";
	}
}
