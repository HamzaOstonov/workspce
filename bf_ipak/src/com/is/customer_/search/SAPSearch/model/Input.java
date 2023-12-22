package com.is.customer_.search.SAPSearch.model;

import com.is.customer_.core.model.Customer;
import com.is.delta.core.FilterInterface;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 09.05.2017.
 * 16:26
 */
public class Input implements FilterInterface<FilterField> {
    private String lastName;
    private String firstName;
    private String middleName;
    private Date birthday;
    private String documentSerial;
    private String documentNumber;
    private String documentType;
    private boolean isSent;

    public Input() {
        super();
    }

    public Input(String lastName, String firstName, String middleName,
                 Date birthday, String documentSerial, String documentNumber,
                 String documentType, boolean isSent)
    {
        super();
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthday = birthday;
        this.documentSerial = documentSerial;
        this.documentNumber = documentNumber;
        this.documentType = documentType;
        this.isSent = isSent;
    }

    public String getName() {
        return "";
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }

    public void clear() {
        this.setBirthday(null);
        this.setDocumentNumber(null);
        this.setDocumentSerial(null);
        this.setDocumentType(null);
        this.setFirstName(null);
        this.setLastName(null);
        this.setMiddleName(null);
    }

    public boolean isEmpty() {
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

    @Override
    public List<FilterField> getFilterFields() {
        List<FilterField> list = new ArrayList<FilterField>();
        if (!CheckNull.isEmpty(birthday))
            list.add(new FilterField(getCond(list) + "p_birthday=?", birthday));

        /*if (!CheckNull.isEmpty(lastName))
            list.add(new FilterField(getCond(list) + "p_family like ?", lastName.replace("*","%")));
        if (!CheckNull.isEmpty(firstName))
            list.add(new FilterField(getCond(list) + "p_first_name like ?", firstName.replace("*","%")));
        if (!CheckNull.isEmpty(middleName))
            list.add(new FilterField(getCond(list) + "p_patronymic like ?", middleName.replace("*","%")));*/

        /*String name = String.format("%s%s%s",
                    lastName != null && !lastName.isEmpty() ? "%" + lastName.trim() + "%" : "",
                    firstName != null && !firstName.isEmpty()? "%" + firstName.trim() + "%" : "",
                    middleName != null && !middleName.isEmpty()? "%" + middleName.trim() + "%": ""
                );*/
        String name = String.format("%s %s %s",
                lastName != null && !lastName.isEmpty() ? lastName.trim(): "",
                firstName != null && !firstName.isEmpty()? firstName.trim() : "",
                middleName != null && !middleName.isEmpty()? middleName.trim(): ""
        );
        name = name.trim().toUpperCase();
        if (!CheckNull.isEmpty(name)) {
            name = "%" + name + "%";
            list.add(new FilterField(getCond(list) + "name like ? ", name.replace("*", "%")));
        }
       /* if (list.size() > 0){
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
        else {*/
            if (!CheckNull.isEmpty(documentSerial))
                list.add(new FilterField(getCond(list) + "p_passport_serial=?",documentSerial));
            if (!CheckNull.isEmpty(documentNumber))
                list.add(new FilterField(getCond(list) + "p_passport_number=?",documentNumber));
            if (!CheckNull.isEmpty(documentType))
                list.add(new FilterField(getCond(list) + "p_type_document=?",documentType));
        //}
        return list;
    }

    private String getOrCondition(int size) {
        return size > 0 ? " or " : "";
    }

    private boolean nameIsEmpty() {
        return false;
    }

    protected String getCond(List<FilterField> filterFields) {
        if (filterFields.size() > 0) {
            return " and ";
        } else
            return " where ";
    }

    private boolean nameAndBirthdateEmpty(List<FilterField> filterFields) {
        return !lastName.isEmpty() && !firstName.isEmpty() && birthday != null ? false : true;
    }

    public Customer toCustomer() {
        return null;
    }
}
