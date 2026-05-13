package com.is.ReportEMPC;

import java.util.Date;

public class ReportEMPC {
    private String bank;
    private String terminal;
    private String terminal_name;
    private String tran_name;
    private Date period_begin;
    private Date period_end;

    public ReportEMPC() {
    	super();
    }

	public ReportEMPC(String bank, String terminal, String terminal_name, String tran_name, Date period_begin, Date period_end) {
		super();
		this.bank = bank;
		this.terminal = terminal;
		this.terminal_name = terminal_name;
		this.tran_name = tran_name;
		this.period_begin = period_begin;
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

	public String getTran_name() {
		return tran_name;
	}

	public void setTran_name(String tran_name) {
		this.tran_name = tran_name;
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
}