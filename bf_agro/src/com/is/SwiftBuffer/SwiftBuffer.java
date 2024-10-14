package com.is.SwiftBuffer;

import java.util.Date;

public class SwiftBuffer {
	private String branch;
    private Long id;
    private String message_type;
    private String application_id;
    private String service_id;
    private String bic_from;
    private String bic_to;
    private String direction;
    private String country_from;
    private String country_to;
    private Date value_date;
    private Double amount;
    private String currency;
    private String reference;
    private String operation_code;
    private String order_party;
    private String ben_party;
    private String narrative;
    private String detailsOfCharges;
    private String message_text;
    private Date insert_date;
    private int state;
    private int deal_id;
    private int parent_group_id;
    private Long parent_id;
    private Long batch_id;
    private String file_name;
    private String order_party_acc;
    private String ben_party_acc;
    private String corr_acc;


    public SwiftBuffer() {
    	super();
    }

	public SwiftBuffer(String branch, Long id, String message_type,
			String application_id, String service_id, String bic_from,
			String bic_to, String direction, String country_from,
			String country_to, Date value_date, Double amount, String currency,
			String reference, String operation_code, String order_party,
			String ben_party, String narrative, String message_text,
			Date insert_date, int state, int deal_id,
			int parent_group_id, Long parent_id, Long batch_id,
			String file_name) {
		super();
		this.branch = branch;
		this.id = id;
		this.message_type = message_type;
		this.application_id = application_id;
		this.service_id = service_id;
		this.bic_from = bic_from;
		this.bic_to = bic_to;
		this.direction = direction;
		this.country_from = country_from;
		this.country_to = country_to;
		this.value_date = value_date;
		this.amount = amount;
		this.currency = currency;
		this.reference = reference;
		this.operation_code = operation_code;
		this.order_party = order_party;
		this.ben_party = ben_party;
		this.narrative = narrative;
		this.message_text = message_text;
		this.insert_date = insert_date;
		this.state = state;
		this.deal_id = deal_id;
		this.parent_group_id = parent_group_id;
		this.parent_id = parent_id;
		this.batch_id = batch_id;
		this.file_name = file_name;
	}
	
	

	public SwiftBuffer(String branch, Long id, String message_type,
			String application_id, String service_id, String bic_from,
			String bic_to, String direction, String country_from,
			String country_to, Date value_date, Double amount, String currency,
			String reference, String operation_code, String order_party,
			String ben_party, String narrative, String detailsOfCharges,
			String message_text, Date insert_date, int state,
			int deal_id, int parent_group_id, Long parent_id,
			Long batch_id, String file_name) {
		super();
		this.branch = branch;
		this.id = id;
		this.message_type = message_type;
		this.application_id = application_id;
		this.service_id = service_id;
		this.bic_from = bic_from;
		this.bic_to = bic_to;
		this.direction = direction;
		this.country_from = country_from;
		this.country_to = country_to;
		this.value_date = value_date;
		this.amount = amount;
		this.currency = currency;
		this.reference = reference;
		this.operation_code = operation_code;
		this.order_party = order_party;
		this.ben_party = ben_party;
		this.narrative = narrative;
		this.detailsOfCharges = detailsOfCharges;
		this.message_text = message_text;
		this.insert_date = insert_date;
		this.state = state;
		this.deal_id = deal_id;
		this.parent_group_id = parent_group_id;
		this.parent_id = parent_id;
		this.batch_id = batch_id;
		this.file_name = file_name;
	}
	
	
	

	public SwiftBuffer(String branch, Long id, String message_type,
			String application_id, String service_id, String bic_from,
			String bic_to, String direction, String country_from,
			String country_to, Date value_date, Double amount, String currency,
			String reference, String operation_code, String order_party,
			String ben_party, String narrative, String detailsOfCharges,
			String message_text, Date insert_date, int state, int deal_id,
			int parent_group_id, Long parent_id, Long batch_id,
			String file_name, String order_party_acc, String ben_party_acc,
			String corr_acc) {
		super();
		this.branch = branch;
		this.id = id;
		this.message_type = message_type;
		this.application_id = application_id;
		this.service_id = service_id;
		this.bic_from = bic_from;
		this.bic_to = bic_to;
		this.direction = direction;
		this.country_from = country_from;
		this.country_to = country_to;
		this.value_date = value_date;
		this.amount = amount;
		this.currency = currency;
		this.reference = reference;
		this.operation_code = operation_code;
		this.order_party = order_party;
		this.ben_party = ben_party;
		this.narrative = narrative;
		this.detailsOfCharges = detailsOfCharges;
		this.message_text = message_text;
		this.insert_date = insert_date;
		this.state = state;
		this.deal_id = deal_id;
		this.parent_group_id = parent_group_id;
		this.parent_id = parent_id;
		this.batch_id = batch_id;
		this.file_name = file_name;
		this.order_party_acc = order_party_acc;
		this.ben_party_acc = ben_party_acc;
		this.corr_acc = corr_acc;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage_type() {
		return message_type;
	}

	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}

	public String getApplication_id() {
		return application_id;
	}

	public void setApplication_id(String application_id) {
		this.application_id = application_id;
	}

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public String getBic_from() {
		return bic_from;
	}

	public void setBic_from(String bic_from) {
		this.bic_from = bic_from;
	}

	public String getBic_to() {
		return bic_to;
	}

	public void setBic_to(String bic_to) {
		this.bic_to = bic_to;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getCountry_from() {
		return country_from;
	}

	public void setCountry_from(String country_from) {
		this.country_from = country_from;
	}

	public String getCountry_to() {
		return country_to;
	}

	public void setCountry_to(String country_to) {
		this.country_to = country_to;
	}

	public Date getValue_date() {
		return value_date;
	}

	public void setValue_date(Date value_date) {
		this.value_date = value_date;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getOperation_code() {
		return operation_code;
	}

	public void setOperation_code(String operation_code) {
		this.operation_code = operation_code;
	}

	public String getOrder_party() {
		return order_party;
	}

	public void setOrder_party(String order_party) {
		this.order_party = order_party;
	}

	public String getBen_party() {
		return ben_party;
	}

	public void setBen_party(String ben_party) {
		this.ben_party = ben_party;
	}

	public String getNarrative() {
		return narrative;
	}

	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}

	public String getMessage_text() {
		return message_text;
	}

	public void setMessage_text(String message_text) {
		this.message_text = message_text;
	}

	public Date getInsert_date() {
		return insert_date;
	}

	public void setInsert_date(Date insert_date) {
		this.insert_date = insert_date;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getDeal_id() {
		return deal_id;
	}

	public void setDeal_id(int deal_id) {
		this.deal_id = deal_id;
	}

	public int getParent_group_id() {
		return parent_group_id;
	}

	public void setParent_group_id(int parent_group_id) {
		this.parent_group_id = parent_group_id;
	}

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}

	public Long getBatch_id() {
		return batch_id;
	}

	public void setBatch_id(Long batch_id) {
		this.batch_id = batch_id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getDetailsOfCharges() {
		return detailsOfCharges;
	}

	public void setDetailsOfCharges(String detailsOfCharges) {
		this.detailsOfCharges = detailsOfCharges;
	}

	public String getOrder_party_acc() {
		return order_party_acc;
	}

	public void setOrder_party_acc(String order_party_acc) {
		this.order_party_acc = order_party_acc;
	}

	public String getBen_party_acc() {
		return ben_party_acc;
	}

	public void setBen_party_acc(String ben_party_acc) {
		this.ben_party_acc = ben_party_acc;
	}

	public String getCorr_acc() {
		return corr_acc;
	}

	public void setCorr_acc(String corr_acc) {
		this.corr_acc = corr_acc;
	}
	
	
    
}
