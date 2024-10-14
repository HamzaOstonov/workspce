package com.is.clienta;

import java.io.Serializable;

public class SsDbLinkBranch  implements Serializable {
	
	private static final long serialVersionUID = 451645646841L;
	
	private int id;
	private String branch;
	private String schema;
	private int count;
	private int filtered_id;
	private int mincount;
	private int maxcount;
	
	public SsDbLinkBranch() {
		super();
	}

	public SsDbLinkBranch(int id, String branch, String schema, int count,
			int filtered_id) {
		super();
		this.id = id;
		this.branch = branch;
		this.schema = schema;
		this.count = count;
		this.filtered_id = filtered_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFiltered_id() {
		return filtered_id;
	}

	public void setFiltered_id(int filtered_id) {
		this.filtered_id = filtered_id;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getMincount() {
		return mincount;
	}

	public void setMincount(int mincount) {
		this.mincount = mincount;
	}

	public int getMaxcount() {
		return maxcount;
	}

	public void setMaxcount(int maxcount) {
		this.maxcount = maxcount;
	}

}
