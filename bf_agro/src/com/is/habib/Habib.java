package com.is.habib;

import java.util.Date;

public class Habib {
    private int id;
    private String title;
    private int year;
    private Float score;

    public Habib() {
    	super();
    }

	public Habib(int id, String title, int year, Float score) {
		super();
		this.id = id;
		this.title = title;
		this.year = year;
		this.score = score;
	}

	public int getid() {
		return id;
	}

	public void setid(int id) {
		this.id = id;
	}

	public String gettitle() {
		return title;
	}

	public void settitle(String title) {
		this.title = title;
	}

	public int getyear() {
		return year;
	}

	public void setyear(int year) {
		this.year = year;
	}

	public Float getscore() {
		return score;
	}

	public void setscore(Float score) {
		this.score = score;
	}

}