package com.is.dper_info.settings;

public class Scales {
	private String veoper;
    private String currency;
    private Long kind;
    private Long region;
    private Long scale;
    private double percent;
    private Long summa;
    private Long commission_profit_percent;
    private Long commission_profit_summa;
    private Long profit_percent;
    private Long profit_summa;
    private String rowid;
    
    public Scales() {
	}
    
	public Scales(String veoper, String currency, Long kind, Long region,
			Long scale, double percent, Long summa,
			Long commission_profit_percent, Long commission_profit_summa,
			Long profit_percent, Long profit_summa) {
		super();
		this.veoper = veoper;
		this.currency = currency;
		this.kind = kind;
		this.region = region;
		this.scale = scale;
		this.percent = percent;
		this.summa = summa;
		this.commission_profit_percent = commission_profit_percent;
		this.commission_profit_summa = commission_profit_summa;
		this.profit_percent = profit_percent;
		this.profit_summa = profit_summa;
	}
	public String getVeoper() {
		return veoper;
	}
	public void setVeoper(String veoper) {
		this.veoper = veoper;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Long getKind() {
		return kind;
	}
	public void setKind(Long kind) {
		this.kind = kind;
	}
	public Long getRegion() {
		return region;
	}
	public void setRegion(Long region) {
		this.region = region;
	}
	public Long getScale() {
		return scale;
	}
	public void setScale(Long scale) {
		this.scale = scale;
	}
	public double getPercent() {
		return percent;
	}
	public void setPercent(double percent) {
		this.percent = percent;
	}
	public Long getSumma() {
		return summa;
	}
	public void setSumma(Long summa) {
		this.summa = summa;
	}
	public Long getCommission_profit_percent() {
		return commission_profit_percent;
	}
	public void setCommission_profit_percent(Long commission_profit_percent) {
		this.commission_profit_percent = commission_profit_percent;
	}
	public Long getCommission_profit_summa() {
		return commission_profit_summa;
	}
	public void setCommission_profit_summa(Long commission_profit_summa) {
		this.commission_profit_summa = commission_profit_summa;
	}
	public Long getProfit_percent() {
		return profit_percent;
	}
	public void setProfit_percent(Long profit_percent) {
		this.profit_percent = profit_percent;
	}
	public Long getProfit_summa() {
		return profit_summa;
	}
	public void setProfit_summa(Long profit_summa) {
		this.profit_summa = profit_summa;
	}

	public String getRowid() {
		return rowid;
	}

	public void setRowid(String rowid) {
		this.rowid = rowid;
	}
    
}
