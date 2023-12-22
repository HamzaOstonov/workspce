package com.is.tieto_globuz.agreements;

import java.io.Serializable;
import java.util.Date;

public class WithMerchant implements Serializable 
{
	
    static final long serialVersionUID = 103844514947365244L;

    private String card_type;
    private String merchant;
    private String acq_bank;
    private String acq_branch;
    private String tr_ccy;
    private String imprint_fee;
    private String imprint_min;
    private String imprint_max;
    private String pos_fee;
    private String pos_min;
    private String pos_max;
    private String cashback_fee;
    private String casback_min;
    private String cashback_max;
    private String atm_fee;
    private String atm_min;
    private String atm_max;
    private String pos_limit;
    private String pos_limit_act;
    private String imprint_limit;
    private String status;
    private String agr_number;
    private Date agr_date;
    private String algorithm;
    private String order_period;
    private String bank_pos_fee;
    private String bank_pos_min;
    private String bank_pos_max;
    private String bank_imp_fee;
    private String bank_imp_min;
    private String bank_imp_max;
    private String bank_atm_fee;
    private String bank_atm_min;
    private String bank_atm_max;
    private String bank_algorithm;
    private String bank_calc_mode;
    private String bank_account;    
    private String action;    
    private Long id_agreement;
    
	public WithMerchant()
	{
		super();
	}

	public Long getId_agreement() {
		return id_agreement;
	}

	public void setId_agreement(Long id_agreement) {
		this.id_agreement = id_agreement;
	}

	public WithMerchant(String card_type, String merchant, String acq_bank, String acq_branch, String tr_ccy, String imprint_fee, String imprint_min, String imprint_max, String pos_fee, String pos_min, String pos_max, String cashback_fee, String casback_min, String cashback_max, String atm_fee, String atm_min, String atm_max, String pos_limit, String pos_limit_act, String imprint_limit, String status, String agr_number, Date agr_date, String algorithm, String order_period, String bank_pos_fee, String bank_pos_min, String bank_pos_max, String bank_imp_fee, String bank_imp_min, String bank_imp_max, String bank_atm_fee, String bank_atm_min, String bank_atm_max, String bank_algorithm, String bank_calc_mode, String bank_account, String action, Long id_agreement)
	{
		super();
		this.card_type = card_type;
		this.merchant = merchant;
		this.acq_bank = acq_bank;
		this.acq_branch = acq_branch;
		this.tr_ccy = tr_ccy;
		this.imprint_fee = imprint_fee;
		this.imprint_min = imprint_min;
		this.imprint_max = imprint_max;
		this.pos_fee = pos_fee;
		this.pos_min = pos_min;
		this.pos_max = pos_max;
		this.cashback_fee = cashback_fee;
		this.casback_min = casback_min;
		this.cashback_max = cashback_max;
		this.atm_fee = atm_fee;
		this.atm_min = atm_min;
		this.atm_max = atm_max;
		this.pos_limit = pos_limit;
		this.pos_limit_act = pos_limit_act;
		this.imprint_limit = imprint_limit;
		this.status = status;
		this.agr_number = agr_number;
		this.agr_date = agr_date;
		this.algorithm = algorithm;
		this.order_period = order_period;
		this.bank_pos_fee = bank_pos_fee;
		this.bank_pos_min = bank_pos_min;
		this.bank_pos_max = bank_pos_max;
		this.bank_imp_fee = bank_imp_fee;
		this.bank_imp_min = bank_imp_min;
		this.bank_imp_max = bank_imp_max;
		this.bank_atm_fee = bank_atm_fee;
		this.bank_atm_min = bank_atm_min;
		this.bank_atm_max = bank_atm_max;
		this.bank_algorithm = bank_algorithm;
		this.bank_calc_mode = bank_calc_mode;
		this.bank_account = bank_account;
		this.action = action;
		this.id_agreement = id_agreement;
	}

	public String getCard_type()
	{
		return this.card_type;
	}

	public void setCard_type(String card_type)
	{
		this.card_type = card_type;
	}

	public String getMerchant()
	{
		return this.merchant;
	}

	public void setMerchant(String merchant)
	{
		this.merchant = merchant;
	}

	public String getAcq_bank()
	{
		return this.acq_bank;
	}

	public void setAcq_bank(String acq_bank)
	{
		this.acq_bank = acq_bank;
	}

	public String getAcq_branch()
	{
		return this.acq_branch;
	}

	public void setAcq_branch(String acq_branch)
	{
		this.acq_branch = acq_branch;
	}

	public String getTr_ccy()
	{
		return this.tr_ccy;
	}

	public void setTr_ccy(String tr_ccy)
	{
		this.tr_ccy = tr_ccy;
	}

	public String getImprint_fee()
	{
		return this.imprint_fee;
	}

	public void setImprint_fee(String imprint_fee)
	{
		this.imprint_fee = imprint_fee;
	}

	public String getImprint_min()
	{
		return this.imprint_min;
	}

	public void setImprint_min(String imprint_min)
	{
		this.imprint_min = imprint_min;
	}

	public String getImprint_max()
	{
		return this.imprint_max;
	}

	public void setImprint_max(String imprint_max)
	{
		this.imprint_max = imprint_max;
	}

	public String getPos_fee()
	{
		return this.pos_fee;
	}

	public void setPos_fee(String pos_fee)
	{
		this.pos_fee = pos_fee;
	}

	public String getPos_min()
	{
		return this.pos_min;
	}

	public void setPos_min(String pos_min)
	{
		this.pos_min = pos_min;
	}

	public String getPos_max()
	{
		return this.pos_max;
	}

	public void setPos_max(String pos_max)
	{
		this.pos_max = pos_max;
	}

	public String getCashback_fee()
	{
		return this.cashback_fee;
	}

	public void setCashback_fee(String cashback_fee)
	{
		this.cashback_fee = cashback_fee;
	}

	public String getCasback_min()
	{
		return this.casback_min;
	}

	public void setCasback_min(String casback_min)
	{
		this.casback_min = casback_min;
	}

	public String getCashback_max()
	{
		return this.cashback_max;
	}

	public void setCashback_max(String cashback_max)
	{
		this.cashback_max = cashback_max;
	}

	public String getAtm_fee()
	{
		return this.atm_fee;
	}

	public void setAtm_fee(String atm_fee)
	{
		this.atm_fee = atm_fee;
	}

	public String getAtm_min()
	{
		return this.atm_min;
	}

	public void setAtm_min(String atm_min)
	{
		this.atm_min = atm_min;
	}

	public String getAtm_max()
	{
		return this.atm_max;
	}

	public void setAtm_max(String atm_max)
	{
		this.atm_max = atm_max;
	}

	public String getPos_limit()
	{
		return this.pos_limit;
	}

	public void setPos_limit(String pos_limit)
	{
		this.pos_limit = pos_limit;
	}

	public String getPos_limit_act()
	{
		return this.pos_limit_act;
	}

	public void setPos_limit_act(String pos_limit_act)
	{
		this.pos_limit_act = pos_limit_act;
	}

	public String getImprint_limit()
	{
		return this.imprint_limit;
	}

	public void setImprint_limit(String imprint_limit)
	{
		this.imprint_limit = imprint_limit;
	}

	public String getStatus()
	{
		return this.status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getAgr_number()
	{
		return this.agr_number;
	}

	public void setAgr_number(String agr_number)
	{
		this.agr_number = agr_number;
	}

	public Date getAgr_date()
	{
		return this.agr_date;
	}

	public void setAgr_date(Date agr_date)
	{
		this.agr_date = agr_date;
	}

	public String getAlgorithm()
	{
		return this.algorithm;
	}

	public void setAlgorithm(String algorithm)
	{
		this.algorithm = algorithm;
	}

	public String getOrder_period()
	{
		return this.order_period;
	}

	public void setOrder_period(String order_period)
	{
		this.order_period = order_period;
	}

	public String getBank_pos_fee()
	{
		return this.bank_pos_fee;
	}

	public void setBank_pos_fee(String bank_pos_fee)
	{
		this.bank_pos_fee = bank_pos_fee;
	}

	public String getBank_pos_min()
	{
		return this.bank_pos_min;
	}

	public void setBank_pos_min(String bank_pos_min)
	{
		this.bank_pos_min = bank_pos_min;
	}

	public String getBank_pos_max()
	{
		return this.bank_pos_max;
	}

	public void setBank_pos_max(String bank_pos_max)
	{
		this.bank_pos_max = bank_pos_max;
	}

	public String getBank_imp_fee()
	{
		return this.bank_imp_fee;
	}

	public void setBank_imp_fee(String bank_imp_fee)
	{
		this.bank_imp_fee = bank_imp_fee;
	}

	public String getBank_imp_min()
	{
		return this.bank_imp_min;
	}

	public void setBank_imp_min(String bank_imp_min)
	{
		this.bank_imp_min = bank_imp_min;
	}

	public String getBank_imp_max()
	{
		return this.bank_imp_max;
	}

	public void setBank_imp_max(String bank_imp_max)
	{
		this.bank_imp_max = bank_imp_max;
	}

	public String getBank_atm_fee()
	{
		return this.bank_atm_fee;
	}

	public void setBank_atm_fee(String bank_atm_fee)
	{
		this.bank_atm_fee = bank_atm_fee;
	}

	public String getBank_atm_min()
	{
		return this.bank_atm_min;
	}

	public void setBank_atm_min(String bank_atm_min)
	{
		this.bank_atm_min = bank_atm_min;
	}

	public String getBank_atm_max()
	{
		return this.bank_atm_max;
	}

	public void setBank_atm_max(String bank_atm_max)
	{
		this.bank_atm_max = bank_atm_max;
	}

	public String getBank_algorithm()
	{
		return this.bank_algorithm;
	}

	public void setBank_algorithm(String bank_algorithm)
	{
		this.bank_algorithm = bank_algorithm;
	}

	public String getBank_calc_mode()
	{
		return this.bank_calc_mode;
	}

	public void setBank_calc_mode(String bank_calc_mode)
	{
		this.bank_calc_mode = bank_calc_mode;
	}

	public String getBank_account()
	{
		return this.bank_account;
	}

	public void setBank_account(String bank_account)
	{
		this.bank_account = bank_account;
	}

	public String getAction()
	{
		return this.action;
	}

	public void setAction(String action)
	{
		this.action = action;
	}

}