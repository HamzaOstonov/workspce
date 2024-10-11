package com.is.tieto_capital;

import java.util.Map;

public interface OfficeDocumentsHandler {
	
	public void textReplace(Map<String, String> replaceMap);
	public void init(String path);
	public void saveDocument(String path);
	public void close();
	void downloadDocument(String name);
}