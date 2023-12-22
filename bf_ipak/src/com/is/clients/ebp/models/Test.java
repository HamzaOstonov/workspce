package com.is.clients.ebp.models;

import java.text.SimpleDateFormat;

import com.is.ISLogger;
import com.is.client_personmap.model.Person;
import com.is.clients.ebp.models.legalentitydetails.LegalEntityDetails;
import com.is.utils.CheckNull;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}
	private static SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	public static Person getAccountantFromLegalEntityDetails(LegalEntityDetails entityDetails) {
		Person person = new Person();
		if(entityDetails.getClient_accountant() != null) {
			person.setPost_address(entityDetails.getClient_accountant().getAddress_accountant());
			person.setBirth_place(entityDetails.getClient_accountant().getBirth_place_accountant());
			person.setCode_citizenship(entityDetails.getClient_accountant().getCitizen_accountant());
			person.setName(entityDetails.getClient_accountant().getName_accountant());
			try {
				person.setBirthday(df.parse(entityDetails.getClient_accountant().getBirth_date_accountant()));
				person.setPassport_date_expiration(df.parse(entityDetails.getClient_accountant().getDoc_date_expire_accountant()));
				person.setPassport_date_registration(df.parse(entityDetails.getClient_accountant().getDoc_date_issue_accountant()));
			} catch(Exception e) {
				ISLogger.getLogger().error(CheckNull.getPstr(e));
			}
			person.setPassport_number(entityDetails.getClient_accountant().getDoc_number_accountant());
			person.setPassport_place_registration(entityDetails.getClient_accountant().getDoc_issuer_accountant());
			person.setPassport_serial(entityDetails.getClient_accountant().getDoc_series_accountant());
			person.setType_document(entityDetails.getClient_accountant().getDoc_type_accountant());
		}
		return person;
	}
}
