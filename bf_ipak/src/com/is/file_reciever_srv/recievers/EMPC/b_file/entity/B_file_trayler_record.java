package com.is.file_reciever_srv.recievers.EMPC.b_file.entity;

public class B_file_trayler_record
{
	private Long id;
	private Long rec_num;
	private Long empc_file_id;
    private String Mtid;
    private String Rec_centr;
    private String Send_centr;
    private String File;
    private String number;
    private String Sign;
    private String Sum;
    private String Control;
    
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public Long getRec_num()
	{
		return rec_num;
	}
	public void setRec_num(Long rec_num)
	{
		this.rec_num = rec_num;
	}
	public Long getEmpc_file_id()
	{
		return empc_file_id;
	}
	public void setEmpc_file_id(Long empc_file_id)
	{
		this.empc_file_id = empc_file_id;
	}
	public String getMtid()
	{
		return Mtid;
	}
	public void setMtid(String mtid)
	{
		Mtid = mtid;
	}
	public String getRec_centr()
	{
		return Rec_centr;
	}
	public void setRec_centr(String rec_centr)
	{
		Rec_centr = rec_centr;
	}
	public String getSend_centr()
	{
		return Send_centr;
	}
	public void setSend_centr(String send_centr)
	{
		Send_centr = send_centr;
	}
	public String getFile()
	{
		return File;
	}
	public void setFile(String file)
	{
		File = file;
	}
	public String getNumber()
	{
		return number;
	}
	public void setNumber(String number)
	{
		this.number = number;
	}
	public String getSign()
	{
		return Sign;
	}
	public void setSign(String sign)
	{
		Sign = sign;
	}
	public String getSum()
	{
		return Sum;
	}
	public void setSum(String sum)
	{
		Sum = sum;
	}
	public String getControl()
	{
		return Control;
	}
	public void setControl(String control)
	{
		Control = control;
	}
	
	public B_file_trayler_record(Long id, Long rec_num, Long empc_file_id, String mtid,
			String rec_centr, String send_centr, String file, String number,
			String sign, String sum, String control)
	{
		super();
		this.rec_num = rec_num;
		this.id = id;
		this.empc_file_id = empc_file_id;
		Mtid = mtid;
		Rec_centr = rec_centr;
		Send_centr = send_centr;
		File = file;
		this.number = number;
		Sign = sign;
		Sum = sum;
		Control = control;
	}
	public B_file_trayler_record()
	{
		super();
	}
	public B_file_trayler_record(Long empc_file_id)
	{
		super();
		this.empc_file_id = empc_file_id;
	}
	
}
