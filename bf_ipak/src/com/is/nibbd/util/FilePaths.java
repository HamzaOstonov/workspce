package com.is.nibbd.util;

public enum FilePaths {
	INP("C:\\NIBBD\\inp\\"),
	OUT("C:\\NIBBD\\out\\"),
	SAVE("C:\\NIBBD\\save\\");
	
	private String path;
	
	private FilePaths(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}
}
