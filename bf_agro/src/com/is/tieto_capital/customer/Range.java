package com.is.tieto_capital.customer;

public class Range {
	private int rangeStart;
	private int rangeEnd;
		
	public Range() {
		super();
	}

	public Range(int rangeStart, int rangeEnd) {
		super();
		this.rangeStart = rangeStart;
		this.rangeEnd = rangeEnd;
	}

	public int getRangeStart() {
		return rangeStart;
	}

	public void setRangeStart(int rangeStart) {
		this.rangeStart = rangeStart;
	}

	public int getRangeEnd() {
		return rangeEnd;
	}

	public void setRangeEnd(int rangeEnd) {
		this.rangeEnd = rangeEnd;
	}	
}
