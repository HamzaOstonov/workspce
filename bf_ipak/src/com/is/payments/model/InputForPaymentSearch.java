package com.is.payments.model;

import com.is.customer_.search.SAPSearch.model.Input;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

import java.util.ArrayList;
import java.util.List;

public class InputForPaymentSearch extends Input
{
    private InputForPaymentSearch(Input input){
        this.setDocumentNumber(input.getDocumentNumber());
        this.setDocumentSerial(input.getDocumentSerial());
        this.setDocumentType(input.getDocumentType());
        this.setSent(false);
        this.setBirthday(input.getBirthday());
        this.setFirstName(input.getFirstName());
        this.setLastName(input.getLastName());
        this.setMiddleName(input.getMiddleName());
    }

    public static Input wrap(Input input){
        return new InputForPaymentSearch(input);
    }

    @Override
    public List<FilterField> getFilterFields(){
        List<FilterField> list = new ArrayList<FilterField>();
        if (!CheckNull.isEmpty(getLastName()))
            list.add(new FilterField(
                    getCond(list) +
                            "family like ?",
                    getLastName().replace("*","%")));
        if (!CheckNull.isEmpty(getFirstName()))
            list.add(new FilterField(getCond(list) +
                    "first_name like ?", getFirstName().replace("*","%").toUpperCase()));
        if (!CheckNull.isEmpty(getMiddleName()))
            list.add(new FilterField(getCond(list) +
                    "patronymic like ?", getMiddleName().replace("*","%").toUpperCase()));
        if (!CheckNull.isEmpty(getDocumentSerial()))
            list.add(new FilterField(getCond(list) +
                    "passport_serial=?",getDocumentSerial().toUpperCase()));
        if (!CheckNull.isEmpty(getDocumentNumber()))
            list.add(new FilterField(getCond(list)
                    + "passport_number=?",getDocumentNumber()));
        if (!CheckNull.isEmpty(getDocumentType()))
            list.add(new FilterField(getCond(list) + "type_document=?",
                    getDocumentType()));
        if (!CheckNull.isEmpty(getBirthday()))
            list.add(new FilterField(getCond(list) + "birth_date=?",getBirthday()));

        return list;
    }
}
