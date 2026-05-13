package com.is.ReportEMPC;

import java.util.Date;

public class ReportEMPCFilter {
	private String bank;
    private String terminal;
    private String terminal_name;
    private String tran_type;
    private Date period_begin;
    private Date period_end;
    
    public ReportEMPCFilter() {
    	super();
    }

	public ReportEMPCFilter(String bank, String terminal, String terminal_name, String tran_type, Date period_begin, Date period_end) {
		super();
		this.bank = bank;
		this.terminal = terminal;
		this.terminal_name = terminal_name;
		this.tran_type = tran_type;
		this.period_begin = period_begin;
		this.period_end = period_end;
	}

	public Date getPeriod_begin() {
		return period_begin;
	}

	public void setPeriod_begin(Date period_begin) {
		this.period_begin = period_begin;
	}

	public Date getPeriod_end() {
		return period_end;
	}

	public void setPeriod_end(Date period_end) {
		this.period_end = period_end;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getTerminal_name() {
		return terminal_name;
	}

	public void setTerminal_name(String terminal_name) {
		this.terminal_name = terminal_name;
	}

	public String getTran_type() {
		return tran_type;
	}

	public void setTran_type(String tran_type) {
		this.tran_type = tran_type;
	}

	

}