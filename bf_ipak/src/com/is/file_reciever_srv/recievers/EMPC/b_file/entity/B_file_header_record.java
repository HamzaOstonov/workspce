package com.is.file_reciever_srv.recievers.EMPC.b_file.entity;

public class B_file_header_record
{
	private long id;
	private Long rec_num;
	private Long EMPC_file_id;
    private String mtid;
    private String rec_centr;
    private String send_centr;
    private String file;
    private String card_id;
    private String version;
    
	public long getId()
	{
		return id;
	}
	public void setId(long id)
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
	public Long getEMPC_file_id()
	{
		return EMPC_file_id;
	}
	public void setEMPC_file_id(Long eMPC_file_id)
	{
		EMPC_file_id = eMPC_file_id;
	}
	public String getMtid()
	{
		return mtid;
	}
	public void setMtid(String mtid)
	{
		this.mtid = mtid;
	}
	public String getRec_centr()
	{
		return rec_centr;
	}
	public void setRec_centr(String rec_centr)
	{
		this.rec_centr = rec_centr;
	}
	public String getSend_centr()
	{
		return send_centr;
	}
	public void setSend_centr(String send_centr)
	{
		this.send_centr = send_centr;
	}
	public String getFile()
	{
		return file;
	}
	public void setFile(String file)
	{
		this.file = file;
	}
	public String getCard_id()
	{
		return card_id;
	}
	public void setCard_id(String card_id)
	{
		this.card_id = card_id;
	}
	public String getVersion()
	{
		return version;
	}
	public void setVersion(String version)
	{
		this.version = version;
	}
	
	public B_file_header_record(long eMPC_file_id, Long rec_num,
			long id, String mtid, String rec_centr,
			String send_centr, String file, String card_id, String version)
	{
		EMPC_file_id = eMPC_file_id;
		this.rec_num = rec_num;
		this.id = id;
		this.mtid = mtid;
		this.rec_centr = rec_centr;
		this.send_centr = send_centr;
		this.file = file;
		this.card_id = card_id;
		this.version = version;
	}
	
	public B_file_header_record()
	{
		super();
	}
	
	public B_file_header_record(long eMPC_file_id)
	{
		EMPC_file_id = eMPC_file_id;
	}
	
}
