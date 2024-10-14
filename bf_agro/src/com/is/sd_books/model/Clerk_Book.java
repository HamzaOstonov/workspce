package com.is.sd_books.model;

public class Clerk_Book {
	private String b_ser;
	private String b_num;
	
	public Clerk_Book(){
		
	}
	
	public Clerk_Book(String b_ser,String b_num){
		this.b_ser=b_ser;
		this.b_num=b_num;
		
	}
	
	public String getB_ser() {
		return b_ser;
	}
	public void setB_ser(String b_ser) {
		this.b_ser = b_ser;
	}
	public String getB_num() {
		return b_num;
	}
	public void setB_num(String b_num) {
		this.b_num = b_num;
	}
	

}
