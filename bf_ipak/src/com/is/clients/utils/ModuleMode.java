package com.is.clients.utils;

import java.util.HashMap;

public enum ModuleMode {
	CREATION(1,"createContactPerson"),
	NCI_CREATE_SAP_EDIT(2, "nciCreateSapEdit"),
	NCI_EDIT_SAP_CREATE(3, "nciEditSapCreate"),
	EDIT(4, "edit"),
	DELTA(5, "delta");
	
	private int mode;
	private String action;
	private static HashMap<Integer, ModuleMode> lookup = new HashMap<Integer, ModuleMode>();
	private ModuleMode(int mode) {
		this.mode = mode;
	}
	private ModuleMode(int mode, String action) {
		this.mode = mode;
		this.action = action;
	}
	public int getModeNumber() {
		return mode;
	}
	public String getAction() {
		return action;
	}
	static {
		for(ModuleMode mode: ModuleMode.values() ) {
			lookup.put(mode.getModeNumber(), mode);
		}
	}
	
	public static ModuleMode getMode(int mode) {
		return lookup.get(mode);
	}
}
