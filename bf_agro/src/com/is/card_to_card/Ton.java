package com.is.card_to_card;

public class Ton {
	private long code;
	private String name;
	private String node;

	public Ton() {
		super();
	}

	public Ton(long code, String name, String node) {
		super();
		this.code = code;
		this.name = name;
		this.node = node;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

}
