package com.is.file_reciever_srv.recievers.cb_cash_operaions;

import java.util.Date;

public class Cb_cash_request_reversal
{
	private Long id;
	private Long ext_record_id;
	private String cb_request_number;
	private String request_type_code;
	private Date oper_day;
	private String reciever_bank_code;
	private String request_bank_code;
	private String reversal_doc_num;
	private Date reversal_doc_date;
	private Long amount;
	private String additional_info;
	private Integer state_id;
	private Boolean is_sent_in_file;
	private String error_code;
	
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
	public String getReversal_doc_num()
	{
		return reversal_doc_num;
	}
	public void setReversal_doc_num(String reversal_doc_num)
	{
		this.reversal_doc_num = reversal_doc_num;
	}
	public Date getReversal_doc_date()
	{
		return reversal_doc_date;
	}
	public void setReversal_doc_date(Date reversal_doc_date)
	{
		this.reversal_doc_date = reversal_doc_date;
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
	public Boolean getIs_sent_in_file()
	{
		return is_sent_in_file;
	}
	public void setIs_sent_in_file(Boolean is_sent_in_file)
	{
		this.is_sent_in_file = is_sent_in_file;
	}
	public String getError_code()
	{
		return error_code;
	}
	public void setError_code(String error_code)
	{
		this.error_code = error_code;
	}
	
	public Cb_cash_request_reversal(Long id, Long ext_record_id,
			String cb_request_number, String request_type_code, Date oper_day,
			String reciever_bank_code, String request_bank_code,
			String reversal_doc_num, Date reversal_doc_date, Long amount,
			String additional_info, Integer state_id, Boolean is_sent_in_file,
			String error_code)
	{
		super();
		this.id = id;
		this.ext_record_id = ext_record_id;
		this.cb_request_number = cb_request_number;
		this.request_type_code = request_type_code;
		this.oper_day = oper_day;
		this.reciever_bank_code = reciever_bank_code;
		this.request_bank_code = request_bank_code;
		this.reversal_doc_num = reversal_doc_num;
		this.reversal_doc_date = reversal_doc_date;
		this.amount = amount;
		this.additional_info = additional_info;
		this.state_id = state_id;
		this.is_sent_in_file = is_sent_in_file;
		this.error_code = error_code;
	}
	
	public Cb_cash_request_reversal()
	{
		super();
	}
	@Override
	public String toString()
	{
		return "Cb_cash_request_reversal [id=" + id + ", ext_record_id="
				+ ext_record_id + ", cb_request_number=" + cb_request_number
				+ ", request_type_code=" + request_type_code + ", oper_day="
				+ oper_day + ", reciever_bank_code=" + reciever_bank_code
				+ ", request_bank_code=" + request_bank_code
				+ ", reversal_doc_num=" + reversal_doc_num
				+ ", reversal_doc_date=" + reversal_doc_date + ", amount="
				+ amount + ", additional_info=" + additional_info
				+ ", state_id=" + state_id + ", is_sent_in_file="
				+ is_sent_in_file + ", error_code=" + error_code + "]";
	}
	
}
