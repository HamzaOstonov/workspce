package com.is.delta;

import com.is.delta.core.FilterInterface;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DELTARecordFilter extends DELTARecord implements FilterInterface<FilterField> {
	private Date dateFrom;
	private Date dateTo;
	
	public DELTARecordFilter() {
		super();
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	
	public List<FilterField> getFilterFields(){
		List<FilterField> filterFields = new ArrayList<FilterField>();
		if (!CheckNull.isEmpty(branch))
			filterFields.add(new FilterField(getCond(filterFields) + "branch=?",branch));
		if (!CheckNull.isEmpty(client_id))
			filterFields.add(new FilterField(getCond(filterFields) + "client_id=?",client_id));
		if (!CheckNull.isEmpty(customer_type))
			filterFields.add(new FilterField(getCond(filterFields) + "customer_type=?", customer_type));
		if (!CheckNull.isEmpty(action_type))
			filterFields.add(new FilterField(getCond(filterFields) + "action_type=?",action_type));
        if (!CheckNull.isEmpty(user_id))
            filterFields.add(new FilterField(getCond(filterFields) + "user_id=?",user_id));
		if (!CheckNull.isEmpty(state))
			filterFields.add(new FilterField(getCond(filterFields) + "state_id=?",state));
		return filterFields;
	}

	private String getCond(List<FilterField> filterFields) {
		if (filterFields.size() > 0) {
			return " and ";
		} else
			return " where ";
	}
}
