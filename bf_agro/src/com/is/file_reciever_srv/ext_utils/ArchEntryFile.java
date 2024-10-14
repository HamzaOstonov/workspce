package com.is.file_reciever_srv.ext_utils;

public class ArchEntryFile {
	private String archName;
	private String fileName;
	private byte[] fileByte;
	private int hash;
	private int size;
	private long file_id;
	
	public ArchEntryFile(String archName, String fileName, byte[] fileByte,
			int hash, int size, long file_id) {
		super();
		this.archName = archName;
		this.fileName = fileName;
		this.fileByte = fileByte;
		this.hash = hash;
		this.size = size;
		this.file_id = file_id;
	}

	public String getArchName() {
		return archName;
	}

	public void setArchName(String archName) {
		this.archName = archName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileByte() {
		return fileByte;
	}

	public void setFileByte(byte[] fileByte) {
		this.fileByte = fileByte;
	}

	public int getHash() {
		return hash;
	}

	public void setHash(int hash) {
		this.hash = hash;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public long getFile_id() {
		return file_id;
	}

	public void setFile_id(long file_id) {
		this.file_id = file_id;
	}
	
}
