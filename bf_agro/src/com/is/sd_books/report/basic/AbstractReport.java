package com.is.sd_books.report.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.zkoss.util.media.AMedia;

import com.is.ISLogger;
import com.is.utils.CheckNull;

public abstract class AbstractReport {
	private String fileName;
	private String path;
	private String branch;
	private String name;
	private int bookId;
	private int uid;
	
	protected final static SimpleDateFormat df;
	
	static{
		df = new SimpleDateFormat("dd.MM.yyyy");
	}
	public abstract AMedia getAmedia();

	public abstract void getReport();
	
	protected String getUsername(Connection c){
		String user = null;
		PreparedStatement ps = null;
		try{
			ps = c.prepareStatement("SELECT FULL_NAME FROM USERS WHERE BRANCH = ? and ID = ?");
			ps.setString(1,branch);
			ps.setInt(2, uid);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				user = rs.getString(1);
		}
		catch (SQLException e){
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		return user;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}
}
