package com.is.tieto_globuz.tieto;

import java.math.BigDecimal;
import java.sql.Date;

public class GeneralInfo
{
	private Long ID;
	private String BRANCH;
	private String DOC_NUM;
	private Date D_DATE;
	private String BANK_CL;
	private String ACC_CL;
	private String NAME_CL;
	private String BANK_CO;
	private String ACC_CO;
	private String NAME_CO;
	private String PURPOSE;
	private BigDecimal SUMMA;
	private String CURRENCY;
	private String TYPE_DOC;
	private Long S_DEAL_ID;
	private Date V_DATE;
	private String PDC;
	private String CASH_CODE;
	private Long STATE;
	private Long PARENT_GROUP_ID;
	private Long PARENT_ID;
	private Long CHILD_ID;
	private Long KOD_ERR;
	private String FILE_NAME;
	private String ERR_GENERAL;
	private Long EMP_ID;
	private Long ID_TRANSH;
	private Long ID_TRANSH_PURP;
	private Date VAL_DATE;
	private Long BATCH_TYPE_ID;
	/**
	 * 
	 */
	public GeneralInfo()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param iD
	 * @param bRANCH
	 * @param dOC_NUM
	 * @param d_DATE
	 * @param bANK_CL
	 * @param aCC_CL
	 * @param nAME_CL
	 * @param bANK_CO
	 * @param aCC_CO
	 * @param nAME_CO
	 * @param pURPOSE
	 * @param sUMMA
	 * @param cURRENCY
	 * @param tYPE_DOC
	 * @param s_DEAL_ID
	 * @param v_DATE
	 * @param pDC
	 * @param cASH_CODE
	 * @param sTATE
	 * @param pARENT_GROUP_ID
	 * @param pARENT_ID
	 * @param cHILD_ID
	 * @param kOD_ERR
	 * @param fILE_NAME
	 * @param eRR_GENERAL
	 * @param eMP_ID
	 * @param iD_TRANSH
	 * @param iD_TRANSH_PURP
	 * @param vAL_DATE
	 * @param bATCH_TYPE_ID
	 */
	public GeneralInfo(Long iD, String bRANCH, String dOC_NUM, Date d_DATE, String bANK_CL, String aCC_CL, String nAME_CL, String bANK_CO, String aCC_CO, String nAME_CO, String pURPOSE, BigDecimal sUMMA, String cURRENCY, String tYPE_DOC, Long s_DEAL_ID, Date v_DATE, String pDC, String cASH_CODE, Long sTATE, Long pARENT_GROUP_ID, Long pARENT_ID, Long cHILD_ID, Long kOD_ERR, String fILE_NAME, String eRR_GENERAL, Long eMP_ID, Long iD_TRANSH, Long iD_TRANSH_PURP, Date vAL_DATE, Long bATCH_TYPE_ID)
	{
		super();
		this.ID = iD;
		this.BRANCH = bRANCH;
		this.DOC_NUM = dOC_NUM;
		this.D_DATE = d_DATE;
		this.BANK_CL = bANK_CL;
		this.ACC_CL = aCC_CL;
		this.NAME_CL = nAME_CL;
		this.BANK_CO = bANK_CO;
		this.ACC_CO = aCC_CO;
		this.NAME_CO = nAME_CO;
		this.PURPOSE = pURPOSE;
		this.SUMMA = sUMMA;
		this.CURRENCY = cURRENCY;
		this.TYPE_DOC = tYPE_DOC;
		this.S_DEAL_ID = s_DEAL_ID;
		this.V_DATE = v_DATE;
		this.PDC = pDC;
		this.CASH_CODE = cASH_CODE;
		this.STATE = sTATE;
		this.PARENT_GROUP_ID = pARENT_GROUP_ID;
		this.PARENT_ID = pARENT_ID;
		this.CHILD_ID = cHILD_ID;
		this.KOD_ERR = kOD_ERR;
		this.FILE_NAME = fILE_NAME;
		this.ERR_GENERAL = eRR_GENERAL;
		this.EMP_ID = eMP_ID;
		this.ID_TRANSH = iD_TRANSH;
		this.ID_TRANSH_PURP = iD_TRANSH_PURP;
		this.VAL_DATE = vAL_DATE;
		this.BATCH_TYPE_ID = bATCH_TYPE_ID;
	}
	public Long getID()
	{
		return this.ID;
	}
	public void setID(Long iD)
	{
		this.ID = iD;
	}
	public String getBRANCH()
	{
		return this.BRANCH;
	}
	public void setBRANCH(String bRANCH)
	{
		this.BRANCH = bRANCH;
	}
	public String getDOC_NUM()
	{
		return this.DOC_NUM;
	}
	public void setDOC_NUM(String dOC_NUM)
	{
		this.DOC_NUM = dOC_NUM;
	}
	public Date getD_DATE()
	{
		return this.D_DATE;
	}
	public void setD_DATE(Date d_DATE)
	{
		this.D_DATE = d_DATE;
	}
	public String getBANK_CL()
	{
		return this.BANK_CL;
	}
	public void setBANK_CL(String bANK_CL)
	{
		this.BANK_CL = bANK_CL;
	}
	public String getACC_CL()
	{
		return this.ACC_CL;
	}
	public void setACC_CL(String aCC_CL)
	{
		this.ACC_CL = aCC_CL;
	}
	public String getNAME_CL()
	{
		return this.NAME_CL;
	}
	public void setNAME_CL(String nAME_CL)
	{
		this.NAME_CL = nAME_CL;
	}
	public String getBANK_CO()
	{
		return this.BANK_CO;
	}
	public void setBANK_CO(String bANK_CO)
	{
		this.BANK_CO = bANK_CO;
	}
	public String getACC_CO()
	{
		return this.ACC_CO;
	}
	public void setACC_CO(String aCC_CO)
	{
		this.ACC_CO = aCC_CO;
	}
	public String getNAME_CO()
	{
		return this.NAME_CO;
	}
	public void setNAME_CO(String nAME_CO)
	{
		this.NAME_CO = nAME_CO;
	}
	public String getPURPOSE()
	{
		return this.PURPOSE;
	}
	public void setPURPOSE(String pURPOSE)
	{
		this.PURPOSE = pURPOSE;
	}
	public BigDecimal getSUMMA()
	{
		return this.SUMMA;
	}
	public void setSUMMA(BigDecimal sUMMA)
	{
		this.SUMMA = sUMMA;
	}
	public String getCURRENCY()
	{
		return this.CURRENCY;
	}
	public void setCURRENCY(String cURRENCY)
	{
		this.CURRENCY = cURRENCY;
	}
	public String getTYPE_DOC()
	{
		return this.TYPE_DOC;
	}
	public void setTYPE_DOC(String tYPE_DOC)
	{
		this.TYPE_DOC = tYPE_DOC;
	}
	public Long getS_DEAL_ID()
	{
		return this.S_DEAL_ID;
	}
	public void setS_DEAL_ID(Long s_DEAL_ID)
	{
		this.S_DEAL_ID = s_DEAL_ID;
	}
	public Date getV_DATE()
	{
		return this.V_DATE;
	}
	public void setV_DATE(Date v_DATE)
	{
		this.V_DATE = v_DATE;
	}
	public String getPDC()
	{
		return this.PDC;
	}
	public void setPDC(String pDC)
	{
		this.PDC = pDC;
	}
	public String getCASH_CODE()
	{
		return this.CASH_CODE;
	}
	public void setCASH_CODE(String cASH_CODE)
	{
		this.CASH_CODE = cASH_CODE;
	}
	public Long getSTATE()
	{
		return this.STATE;
	}
	public void setSTATE(Long sTATE)
	{
		this.STATE = sTATE;
	}
	public Long getPARENT_GROUP_ID()
	{
		return this.PARENT_GROUP_ID;
	}
	public void setPARENT_GROUP_ID(Long pARENT_GROUP_ID)
	{
		this.PARENT_GROUP_ID = pARENT_GROUP_ID;
	}
	public Long getPARENT_ID()
	{
		return this.PARENT_ID;
	}
	public void setPARENT_ID(Long pARENT_ID)
	{
		this.PARENT_ID = pARENT_ID;
	}
	public Long getCHILD_ID()
	{
		return this.CHILD_ID;
	}
	public void setCHILD_ID(Long cHILD_ID)
	{
		this.CHILD_ID = cHILD_ID;
	}
	public Long getKOD_ERR()
	{
		return this.KOD_ERR;
	}
	public void setKOD_ERR(Long kOD_ERR)
	{
		this.KOD_ERR = kOD_ERR;
	}
	public String getFILE_NAME()
	{
		return this.FILE_NAME;
	}
	public void setFILE_NAME(String fILE_NAME)
	{
		this.FILE_NAME = fILE_NAME;
	}
	public String getERR_GENERAL()
	{
		return this.ERR_GENERAL;
	}
	public void setERR_GENERAL(String eRR_GENERAL)
	{
		this.ERR_GENERAL = eRR_GENERAL;
	}
	public Long getEMP_ID()
	{
		return this.EMP_ID;
	}
	public void setEMP_ID(Long eMP_ID)
	{
		this.EMP_ID = eMP_ID;
	}
	public Long getID_TRANSH()
	{
		return this.ID_TRANSH;
	}
	public void setID_TRANSH(Long iD_TRANSH)
	{
		this.ID_TRANSH = iD_TRANSH;
	}
	public Long getID_TRANSH_PURP()
	{
		return this.ID_TRANSH_PURP;
	}
	public void setID_TRANSH_PURP(Long iD_TRANSH_PURP)
	{
		this.ID_TRANSH_PURP = iD_TRANSH_PURP;
	}
	public Date getVAL_DATE()
	{
		return this.VAL_DATE;
	}
	public void setVAL_DATE(Date vAL_DATE)
	{
		this.VAL_DATE = vAL_DATE;
	}
	public Long getBATCH_TYPE_ID()
	{
		return this.BATCH_TYPE_ID;
	}
	public void setBATCH_TYPE_ID(Long bATCH_TYPE_ID)
	{
		this.BATCH_TYPE_ID = bATCH_TYPE_ID;
	}
	
	
}
