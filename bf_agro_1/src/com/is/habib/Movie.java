package com.is.habib;

import java.util.Date;

public class Movie {
	private int id;
	private String title;
	private int year;
	private Float score;

	public Movie() {
		super();
	}

	public Movie(int id, String title, int year, Float score) {
		super();
		this.id = id;
		this.title = title;
		this.year = year;
		this.score = score;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
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