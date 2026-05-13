package com.is.humo;

public class SS_HUMO_TYPE_OF_CARD {

    private String code;
    private String description;
    private String branch;
    private Long range_id;
    private String branch_id;
    private String bin;
    private String group_c;
    private String bank_c;
    private Long chip_app_id;

    public SS_HUMO_TYPE_OF_CARD() {

    }

    public SS_HUMO_TYPE_OF_CARD( String code, String description, String branch, Long range_id, String branch_id, String bin, String group_c, String bank_c, Long chip_app_id) {

                this.code = code;
                this.description = description;
                this.branch = branch;
                this.range_id = range_id;
                this.branch_id = branch_id;
                this.bin = bin;
                this.group_c = group_c;
                this.bank_c = bank_c;
                this.chip_app_id = chip_app_id;
        }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Long getRange_id() {
		return range_id;
	}

	public void setRange_id(Long range_id) {
		this.range_id = range_id;
	}

	public String getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}

	public String getBin() {
		return bin;
	}

	public void setBin(String bin) {
		this.bin = bin;
	}

	public String getGroup_c() {
		return group_c;
	}

	public void setGroup_c(String group_c) {
		this.group_c = group_c;
	}

	public String getBank_c() {
		return bank_c;
	}

	public void setBank_c(String bank_c) {
		this.bank_c = bank_c;
	}

	public Long getChip_app_id() {
		return chip_app_id;
	}

	public void setChip_app_id(Long chip_app_id) {
		this.chip_app_id = chip_app_id;
	}

	@Override
	public String toString() {
		return "SS_HUMO_TYPE_OF_CARD ["
				+ (code != null ? "code=" + code + ",/n " : "")
				+ (description != null ? "description=" + description + ",/n "
						: "")
				+ (branch != null ? "branch=" + branch + ",/n " : "")
				+ (range_id != null ? "range_id=" + range_id + ",/n " : "")
				+ (branch_id != null ? "branch_id=" + branch_id + ",/n " : "")
				+ (bin != null ? "bin=" + bin + ",/n " : "")
				+ (group_c != null ? "group_c=" + group_c + ",/n " : "")
				+ (bank_c != null ? "bank_c=" + bank_c + ",/n " : "")
				+ (chip_app_id != null ? "chip_app_id=" + chip_app_id : "")
				+ "]";
	}


}

