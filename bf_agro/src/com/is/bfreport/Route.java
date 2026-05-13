package com.is.bfreport;

public class Route {
	private int state_begin;
	private int state_end;
	
	public Route(int state_begin, int state_end) {
		super();
		this.state_begin = state_begin;
		this.state_end = state_end;
	}

	public int getState_begin() {
		return state_begin;
	}

	public void setState_begin(int state_begin) {
		this.state_begin = state_begin;
	}

	public int getState_end() {
		return state_end;
	}

	public void setState_end(int state_end) {
		this.state_end = state_end;
	}

	
	
}
