package com.is.providers;
public class AddInfo {
	private long id;
	private AddInfoRow ai;

	public AddInfo() {
		super();
	}

	public AddInfo(long id, AddInfoRow ai) {
		super();
		this.id = id;
		this.ai = ai;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AddInfoRow getAi() {
		return ai;
	}

	public void setAi(AddInfoRow ai) {
		this.ai = ai;
	}

}
