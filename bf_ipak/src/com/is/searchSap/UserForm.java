package com.is.searchSap;

import com.is.delta.core.FilterInterface;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserForm implements FilterInterface<FilterField>{
	private String lastName;
	private String firstName;
	private String middleName;
	private Date birthday;
	private String documentSerial;
	private String documentNumber;
	private String documentType;
	
	public String getName(){
		return "";
	}
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public UserForm() {
		super();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getDocumentSerial() {
		return documentSerial;
	}

	public void setDocumentSerial(String documentSerial) {
		this.documentSerial = documentSerial;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

    public boolean nameIsEmpty(){
        if (StringUtils.isBlank(lastName))
            return true;
        if (StringUtils.isBlank(firstName))
            return true;
        if (birthday == null)
            return true;
        return false;
    }

    public boolean documentEmpty(){
        if (StringUtils.isBlank(documentNumber))
            return true;
        if (StringUtils.isBlank(documentType))
            return true;
        return false;
    }

	public boolean isEmpty(){
		boolean isEmpty = true;
		if (!CheckNull.isEmpty(this.getBirthday())) {
			isEmpty = false;
		}
		if (!CheckNull.isEmpty(this.getDocumentNumber())) {
			isEmpty = false;
		}
		if (!CheckNull.isEmpty(this.getDocumentSerial())) {
			isEmpty = false;
		}
		if (!CheckNull.isEmpty(this.getFirstName())) {
			isEmpty = false;
		}
		if (!CheckNull.isEmpty(this.getLastName())) {
			isEmpty = false;
		}
		if (!CheckNull.isEmpty(this.getMiddleName())) {
			isEmpty = false;
		}
		return isEmpty;
	}

	public void clear() {
		this.setBirthday(null);
		this.setDocumentNumber(null);
		this.setDocumentSerial(null);
		this.setDocumentType(null);
		this.setFirstName(null);
		this.setMiddleName(null);
		this.setLastName(null);
	}

	@Override
	public List<FilterField> getFilterFields() {
		List<FilterField> list = new ArrayList<FilterField>();
		if (!CheckNull.isEmpty(birthday))
			list.add(new FilterField(getCond(list) + "p_birthday=?",birthday));
		if (!CheckNull.isEmpty(lastName))
			list.add(new FilterField(getCond(list) + "p_last_name_cyr like ?",lastName));
		if (!CheckNull.isEmpty(firstName))
			list.add(new FilterField(getCond(list) + "p_first_name_cyr like ?",firstName));
		if (!CheckNull.isEmpty(middleName))
			list.add(new FilterField(getCond(list) + "p_patronymic_cyr like ?",middleName));

        if (list.size() > 0){
            if (!CheckNull.isEmpty(documentNumber)){
                if (!CheckNull.isEmpty(documentSerial)) {
                    list.add(new FilterField("or ( p_passport_number = ? ", documentNumber));
                    list.add(new FilterField(" and passport_serial = ? )",documentSerial));
                }
                else{
                    list.add(new FilterField(" or p_passport_number = ? ",documentNumber));
                }
            }
        }
        else {
            if (!CheckNull.isEmpty(documentSerial))
                list.add(new FilterField(getCond(list) + "p_passport_serial=?",documentSerial));
            if (!CheckNull.isEmpty(documentNumber))
                list.add(new FilterField(getCond(list) + "p_passport_number=?",documentNumber));
            if (!CheckNull.isEmpty(documentType))
                list.add(new FilterField(getCond(list) + "p_type_document=?",documentType));
        }
        return list;
	}

	private String getCond(List<FilterField> filterFields) {
		if (filterFields.size() > 0) {
			return " and ";
		} else
			return " where ";
	}
}
