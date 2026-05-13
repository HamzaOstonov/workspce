package com.is.sd_books.report.basic;

public class ReportBuilder {
	AbstractReport report;
	
	public ReportBuilder(AbstractReport report){
		this.report = report;
	}
	
	public  ReportBuilder setName(String name){
		report.setName(name);
		return this;
	}
	
	public ReportBuilder setBranch(String branch){
		report.setBranch(branch);
		return this;
	}
	
	public ReportBuilder setFileName(String fileName){
		report.setFileName(fileName);
		return this;
	}
	
	public ReportBuilder setPath(String path){
		report.setPath(path);
		return this;
	}
	
	public ReportBuilder setId(int bookId){
		report.setBookId(bookId);
		return this;
	}
	
	public ReportBuilder setUid(int uid){
		report.setUid(uid);
		return this;
	}
	
	public AbstractReport build(){
		return report;
	}
}
