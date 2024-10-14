package com.is.providers;
public class CheckPerResp {

	private Res res;
	private ResR rr;

	public CheckPerResp() {
		super();
	}

	public CheckPerResp(Res res, ResR rr) {
		super();
		this.res = res;
		this.rr = rr;
	}

	public Res getRes() {
		return res;
	}

	public void setRes(Res res) {
		this.res = res;
	}

	public ResR getRr() {
		return rr;
	}

	public void setRr(ResR rr) {
		this.rr = rr;
	}

}
