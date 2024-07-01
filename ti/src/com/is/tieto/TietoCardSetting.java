package com.is.tieto;

import java.math.BigDecimal;

public class TietoCardSetting {
    private int code;
    private String name;
    private String bin;
    private String risk_level;
    private String financial_conditions;
    private BigDecimal minimum_balance;
    private BigDecimal id_chip_app;
    private String id_order_account;
    private String group_c;
    private String card_condition;
    private BigDecimal design_id;
    private String acc_name_postfix;
    private int ho_acc_group;
    private int br_acc_group;
    private String id_order_max;
    private int allow_multiple_card_per_acc;
    private int range_id;
    private String account_ccy;
    private String card_ccy;
    

	private String bank_c;

    public TietoCardSetting() {
    	super();
    }

	public TietoCardSetting(int code, String name, String bin,
			String risk_level, String financial_conditions,
			BigDecimal minimum_balance, BigDecimal id_chip_app,
			String id_order_account, BigDecimal design_id,
			String acc_name_postfix, int ho_acc_group, int br_acc_group, String id_order_max, int range_id) {
		super();
		this.code = code;
		this.name = name;
		this.bin = bin;
		this.risk_level = risk_level;
		this.financial_conditions = financial_conditions;
		this.minimum_balance = minimum_balance;
		this.id_chip_app = id_chip_app;
		this.id_order_account = id_order_account;
		this.design_id = design_id;
		this.acc_name_postfix = acc_name_postfix;
		this.ho_acc_group = ho_acc_group;
		this.br_acc_group = br_acc_group;
		this.id_order_max = id_order_max;
		this.range_id = range_id;
	}

	public String getAcc_name_postfix() {
		return acc_name_postfix;
	}

	public void setAcc_name_postfix(String acc_name_postfix) {
		this.acc_name_postfix = acc_name_postfix;
	}

	public int getHo_acc_group() {
		return ho_acc_group;
	}

	public void setHo_acc_group(int ho_acc_group) {
		this.ho_acc_group = ho_acc_group;
	}

	public int getBr_acc_group() {
		return br_acc_group;
	}

	public void setBr_acc_group(int br_acc_group) {
		this.br_acc_group = br_acc_group;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBin() {
		return bin;
	}

	public void setBin(String bin) {
		this.bin = bin;
	}

	public String getRisk_level() {
		return risk_level;
	}

	public void setRisk_level(String risk_level) {
		this.risk_level = risk_level;
	}

	public String getFinancial_conditions() {
		return financial_conditions;
	}

	public void setFinancial_conditions(String financial_conditions) {
		this.financial_conditions = financial_conditions;
	}

	public BigDecimal getMinimum_balance() {
		return minimum_balance;
	}

	public void setMinimum_balance(BigDecimal minimum_balance) {
		this.minimum_balance = minimum_balance;
	}

	public BigDecimal getId_chip_app() {
		return id_chip_app;
	}

	public void setId_chip_app(BigDecimal id_chip_app) {
		this.id_chip_app = id_chip_app;
	}

	public String getId_order_account() {
		return id_order_account;
	}

	public void setId_order_account(String id_order_account) {
		this.id_order_account = id_order_account;
	}
	
	public String getGroup_c() {
		return group_c;
	}

	public void setGroup_c(String group_c) {
		this.group_c = group_c;
	}

	public String getBank_c() {
		return bank_c;
	}

	public void setBank_c(String bank_c) {
		this.bank_c = bank_c;
	}

	public String getCard_condition() {
		return card_condition;
	}

	public void setCard_condition(String card_condition) {
		this.card_condition = card_condition;
	}

	public BigDecimal getDesign_id() {
		return design_id;
	}

	public void setDesign_id(BigDecimal design_id) {
		this.design_id = design_id;
	}
	
	public String getId_order_max()
	{
		return id_order_max;
	}

	public void setId_order_max(String id_order_max)
	{
		this.id_order_max = id_order_max;
	}

	public int getAllow_multiple_card_per_acc()
	{
		return allow_multiple_card_per_acc;
	}

	public void setAllow_multiple_card_per_acc(int allow_multiple_card_per_acc)
	{
		this.allow_multiple_card_per_acc = allow_multiple_card_per_acc;
	}

	public int getRange_id()
	{
		return range_id;
	}

	public void setRange_id(int range_id)
	{
		this.range_id = range_id;
	}

	public void setAccount_ccy(String account_ccy) {
		this.account_ccy = account_ccy;
	}

	public String getAccount_ccy() {
		return account_ccy;
	}

	public void setCard_ccy(String card_ccy) {
		this.card_ccy = card_ccy;
	}

	public String getCard_ccy() {
		return card_ccy;
	}
    
}
