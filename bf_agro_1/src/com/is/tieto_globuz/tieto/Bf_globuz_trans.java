package com.is.tieto_globuz.tieto;

import java.math.BigDecimal;
import java.sql.Date;

public class Bf_globuz_trans
{
	private String BRANCH;
	private Long GENID;
	private Long GLOBID;
	private String EMPID;
	private Date TRDATE;
	private int STATE;
	private BigDecimal SUMMA;
	private String account;
	/**
	 * 
	 */
	public Bf_globuz_trans()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param bRANCH
	 * @param gENID
	 * @param gLOBID
	 * @param eMPID
	 * @param tRDATE
	 * @param sTATE
	 */
	
	
	public String getBRANCH()
	{
		return this.BRANCH;
	}
	/**
	 * @param bRANCH
	 * @param gENID
	 * @param gLOBID
	 * @param eMPID
	 * @param tRDATE
	 * @param sTATE
	 * @param sUMMA
	 */
	
	public Bf_globuz_trans(String bRANCH, Long gENID, Long gLOBID, String eMPID, Date tRDATE, int sTATE, BigDecimal sUMMA)
	{
		super();
		this.BRANCH = bRANCH;
		this.GENID = gENID;
		this.GLOBID = gLOBID;
		this.EMPID = eMPID;
		this.TRDATE = tRDATE;
		this.STATE = sTATE;
		this.SUMMA = sUMMA;
	}
	
	public void setBRANCH(String bRANCH)
	{
		this.BRANCH = bRANCH;
	}
	public Long getGENID()
	{
		return this.GENID;
	}
	public void setGENID(Long gENID)
	{
		this.GENID = gENID;
	}
	public Long getGLOBID()
	{
		return this.GLOBID;
	}
	public void setGLOBID(Long gLOBID)
	{
		this.GLOBID = gLOBID;
	}
	public String getEMPID()
	{
		return this.EMPID;
	}
	public void setEMPID(String eMPID)
	{
		this.EMPID = eMPID;
	}
	public Date getTRDATE()
	{
		return this.TRDATE;
	}
	public void setTRDATE(Date tRDATE)
	{
		this.TRDATE = tRDATE;
	}
	public int getSTATE()
	{
		return this.STATE;
	}
	public void setSTATE(int sTATE)
	{
		this.STATE = sTATE;
	}
	public BigDecimal getSUMMA()
	{
		return this.SUMMA;
	}
	public void setSUMMA(BigDecimal sUMMA)
	{
		this.SUMMA = sUMMA;
	}
	public String getAccount()
	{
		return this.account;
	}
	public void setAccount(String account)
	{
		this.account = account;
	}
	
	
	
}
