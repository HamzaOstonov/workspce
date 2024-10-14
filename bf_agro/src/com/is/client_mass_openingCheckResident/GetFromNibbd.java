package com.is.client_mass_openingCheckResident;

import java.sql.Date;

	public class GetFromNibbd {
		
	
		private String branch		;	
		private String id_client		;	
		private	String message			;
		private String subject_state		;	
		private	String inn	;
		private String inn_registrated		;
		private	String inn_registrated_gni		;	
		private	String pin	;
		private	String last_name		;
		private	String first_name			;
		private	String patronym			;
		private	String surname		; 
		private	String givenname			;
		private	String birth_date	;
		private	String sex	;
		private	String passport_seria	;  //	???	
		private	String passport_number;		
		private	String date_issue	;
		private	String date_expiry		;
		private	String give_place		;
		private	String give_place_name		;	
		private	String birth_country	;		
		private	String birth_country_name;			
		private	String birth_place		;	
		private	String 	nationality		;
		private	String nationality_desc		;	
		private	String citizenship	;
		private	String citizenship_name	;
		private	String domicile_country		;
		private	String domicile_region	;
		private	String domicile_district	;
		private	String domicile_place_desc		;
		private	String domicile_street_desc	;		
		private	String domicile_address		;	
		private	String 	domicile_house	;
		private	String domicile_block	;	
		private	String domicile_flat		;
		private	String 	domicile_begin		;
		private	String temp_country		;	
		private	String 	temp_region	;
		private	String 	temp_district	;		
		private	String 	temp_place_desc	;		
		private	String temp_street_desc	;
		private	String temp_address	;	
		private	String temp_house	;		
		private	String 	temp_block	;		
		private	String temp_flat	;		
		private	String temp_begin	;	
		private	String temp_end	;


	    public GetFromNibbd() {

	    }


		public GetFromNibbd(String branch, String id_client, String message, String subject_state, String inn,
				String inn_registrated, String inn_registrated_gni, String pin, String last_name, String first_name,
				String patronym, String surname, String givenname, String birth_date, String sex, String passport_seria,
				String passport_number, String date_issue, String date_expiry, String give_place,
				String give_place_name, String birth_country, String birth_country_name, String birth_place,
				String nationality, String nationality_desc, String citizenship, String citizenship_name,
				String domicile_country, String domicile_region, String domicile_district, String domicile_place_desc,
				String domicile_street_desc, String domicile_address, String domicile_house, String domicile_block,
				String domicile_flat, String domicile_begin, String temp_country, String temp_region,
				String temp_district, String temp_place_desc, String temp_street_desc, String temp_address,
				String temp_house, String temp_block, String temp_flat, String temp_begin, String temp_end) {
			super();
			this.branch = branch;
			this.id_client = id_client;
			this.message = message;
			this.subject_state = subject_state;
			this.inn = inn;
			this.inn_registrated = inn_registrated;
			this.inn_registrated_gni = inn_registrated_gni;
			this.pin = pin;
			this.last_name = last_name;
			this.first_name = first_name;
			this.patronym = patronym;
			this.surname = surname;
			this.givenname = givenname;
			this.birth_date = birth_date;
			this.sex = sex;
			this.passport_seria = passport_seria;
			this.passport_number = passport_number;
			this.date_issue = date_issue;
			this.date_expiry = date_expiry;
			this.give_place = give_place;
			this.give_place_name = give_place_name;
			this.birth_country = birth_country;
			this.birth_country_name = birth_country_name;
			this.birth_place = birth_place;
			this.nationality = nationality;
			this.nationality_desc = nationality_desc;
			this.citizenship = citizenship;
			this.citizenship_name = citizenship_name;
			this.domicile_country = domicile_country;
			this.domicile_region = domicile_region;
			this.domicile_district = domicile_district;
			this.domicile_place_desc = domicile_place_desc;
			this.domicile_street_desc = domicile_street_desc;
			this.domicile_address = domicile_address;
			this.domicile_house = domicile_house;
			this.domicile_block = domicile_block;
			this.domicile_flat = domicile_flat;
			this.domicile_begin = domicile_begin;
			this.temp_country = temp_country;
			this.temp_region = temp_region;
			this.temp_district = temp_district;
			this.temp_place_desc = temp_place_desc;
			this.temp_street_desc = temp_street_desc;
			this.temp_address = temp_address;
			this.temp_house = temp_house;
			this.temp_block = temp_block;
			this.temp_flat = temp_flat;
			this.temp_begin = temp_begin;
			this.temp_end = temp_end;
		}

		public String getBranch() {
			return branch;
		}


		public void setBranch(String branch) {
			this.branch = branch;
		}


		public String getId_client() {
			return id_client;
		}


		public void setId_client(String id_client) {
			this.id_client = id_client;
		}


		public String getMessage() {
			return message;
		}


		public void setMessage(String message) {
			this.message = message;
		}


		public String getSubject_state() {
			return subject_state;
		}


		public void setSubject_state(String subject_state) {
			this.subject_state = subject_state;
		}


		public String getInn() {
			return inn;
		}


		public void setInn(String inn) {
			this.inn = inn;
		}


		public String getInn_registrated() {
			return inn_registrated;
		}


		public void setInn_registrated(String inn_registrated) {
			this.inn_registrated = inn_registrated;
		}


		public String getInn_registrated_gni() {
			return inn_registrated_gni;
		}


		public void setInn_registrated_gni(String inn_registrated_gni) {
			this.inn_registrated_gni = inn_registrated_gni;
		}


		public String getPin() {
			return pin;
		}


		public void setPin(String pin) {
			this.pin = pin;
		}


		public String getLast_name() {
			return last_name;
		}


		public void setLast_name(String last_name) {
			this.last_name = last_name;
		}


		public String getFirst_name() {
			return first_name;
		}


		public void setFirst_name(String first_name) {
			this.first_name = first_name;
		}


		public String getPatronym() {
			return patronym;
		}


		public void setPatronym(String patronym) {
			this.patronym = patronym;
		}


		public String getSurname() {
			return surname;
		}


		public void setSurname(String surname) {
			this.surname = surname;
		}


		public String getGivenname() {
			return givenname;
		}


		public void setGivenname(String givenname) {
			this.givenname = givenname;
		}


		public String getBirth_date() {
			return birth_date;
		}


		public void setBirth_date(String birth_date) {
			this.birth_date = birth_date;
		}


		public String getSex() {
			return sex;
		}


		public void setSex(String sex) {
			this.sex = sex;
		}


		public String getPassport_seria() {
			return passport_seria;
		}


		public void setPassport_seria(String passport_seria) {
			this.passport_seria = passport_seria;
		}


		public String getPassport_number() {
			return passport_number;
		}


		public void setPassport_number(String passport_number) {
			this.passport_number = passport_number;
		}


		public String getDate_issue() {
			return date_issue;
		}


		public void setDate_issue(String date_issue) {
			this.date_issue = date_issue;
		}


		public String getDate_expiry() {
			return date_expiry;
		}


		public void setDate_expiry(String date_expiry) {
			this.date_expiry = date_expiry;
		}


		public String getGive_place() {
			return give_place;
		}


		public void setGive_place(String give_place) {
			this.give_place = give_place;
		}


		public String getGive_place_name() {
			return give_place_name;
		}


		public void setGive_place_name(String give_place_name) {
			this.give_place_name = give_place_name;
		}


		public String getBirth_country() {
			return birth_country;
		}


		public void setBirth_country(String birth_country) {
			this.birth_country = birth_country;
		}


		public String getBirth_country_name() {
			return birth_country_name;
		}


		public void setBirth_country_name(String birth_country_name) {
			this.birth_country_name = birth_country_name;
		}


		public String getBirth_place() {
			return birth_place;
		}


		public void setBirth_place(String birth_place) {
			this.birth_place = birth_place;
		}


		public String getNationality() {
			return nationality;
		}


		public void setNationality(String nationality) {
			this.nationality = nationality;
		}


		public String getNationality_desc() {
			return nationality_desc;
		}


		public void setNationality_desc(String nationality_desc) {
			this.nationality_desc = nationality_desc;
		}


		public String getCitizenship() {
			return citizenship;
		}


		public void setCitizenship(String citizenship) {
			this.citizenship = citizenship;
		}


		public String getCitizenship_name() {
			return citizenship_name;
		}


		public void setCitizenship_name(String citizenship_name) {
			this.citizenship_name = citizenship_name;
		}


		public String getDomicile_country() {
			return domicile_country;
		}


		public void setDomicile_country(String domicile_country) {
			this.domicile_country = domicile_country;
		}


		public String getDomicile_region() {
			return domicile_region;
		}


		public void setDomicile_region(String domicile_region) {
			this.domicile_region = domicile_region;
		}


		public String getDomicile_district() {
			return domicile_district;
		}


		public void setDomicile_district(String domicile_district) {
			this.domicile_district = domicile_district;
		}


		public String getDomicile_place_desc() {
			return domicile_place_desc;
		}


		public void setDomicile_place_desc(String domicile_place_desc) {
			this.domicile_place_desc = domicile_place_desc;
		}


		public String getDomicile_street_desc() {
			return domicile_street_desc;
		}


		public void setDomicile_street_desc(String domicile_street_desc) {
			this.domicile_street_desc = domicile_street_desc;
		}


		public String getDomicile_address() {
			return domicile_address;
		}


		public void setDomicile_address(String domicile_address) {
			this.domicile_address = domicile_address;
		}


		public String getDomicile_house() {
			return domicile_house;
		}


		public void setDomicile_house(String domicile_house) {
			this.domicile_house = domicile_house;
		}


		public String getDomicile_block() {
			return domicile_block;
		}


		public void setDomicile_block(String domicile_block) {
			this.domicile_block = domicile_block;
		}


		public String getDomicile_flat() {
			return domicile_flat;
		}


		public void setDomicile_flat(String domicile_flat) {
			this.domicile_flat = domicile_flat;
		}


		public String getDomicile_begin() {
			return domicile_begin;
		}


		public void setDomicile_begin(String domicile_begin) {
			this.domicile_begin = domicile_begin;
		}


		public String getTemp_country() {
			return temp_country;
		}


		public void setTemp_country(String temp_country) {
			this.temp_country = temp_country;
		}


		public String getTemp_region() {
			return temp_region;
		}


		public void setTemp_region(String temp_region) {
			this.temp_region = temp_region;
		}


		public String getTemp_district() {
			return temp_district;
		}


		public void setTemp_district(String temp_district) {
			this.temp_district = temp_district;
		}


		public String getTemp_place_desc() {
			return temp_place_desc;
		}


		public void setTemp_place_desc(String temp_place_desc) {
			this.temp_place_desc = temp_place_desc;
		}


		public String getTemp_street_desc() {
			return temp_street_desc;
		}


		public void setTemp_street_desc(String temp_street_desc) {
			this.temp_street_desc = temp_street_desc;
		}


		public String getTemp_address() {
			return temp_address;
		}


		public void setTemp_address(String temp_address) {
			this.temp_address = temp_address;
		}


		public String getTemp_house() {
			return temp_house;
		}


		public void setTemp_house(String temp_house) {
			this.temp_house = temp_house;
		}


		public String getTemp_block() {
			return temp_block;
		}


		public void setTemp_block(String temp_block) {
			this.temp_block = temp_block;
		}


		public String getTemp_flat() {
			return temp_flat;
		}


		public void setTemp_flat(String temp_flat) {
			this.temp_flat = temp_flat;
		}


		public String getTemp_begin() {
			return temp_begin;
		}


		public void setTemp_begin(String temp_begin) {
			this.temp_begin = temp_begin;
		}


		public String getTemp_end() {
			return temp_end;
		}


		public void setTemp_end(String temp_end) {
			this.temp_end = temp_end;
		}


		@Override
		public String toString() {
			return "GetFromNibbd [branch=" + branch + ", id_client=" + id_client + ", message=" + message
					+ ", subject_state=" + subject_state + ", inn=" + inn + ", inn_registrated=" + inn_registrated
					+ ", inn_registrated_gni=" + inn_registrated_gni + ", pin=" + pin + ", last_name=" + last_name
					+ ", first_name=" + first_name + ", patronym=" + patronym + ", surname=" + surname + ", givenname="
					+ givenname + ", birth_date=" + birth_date + ", sex=" + sex + ", passport_seria=" + passport_seria
					+ ", passport_number=" + passport_number + ", date_issue=" + date_issue + ", date_expiry="
					+ date_expiry + ", give_place=" + give_place + ", give_place_name=" + give_place_name
					+ ", birth_country=" + birth_country + ", birth_country_name=" + birth_country_name
					+ ", birth_place=" + birth_place + ", nationality=" + nationality + ", nationality_desc="
					+ nationality_desc + ", citizenship=" + citizenship + ", citizenship_name=" + citizenship_name
					+ ", domicile_country=" + domicile_country + ", domicile_region=" + domicile_region
					+ ", domicile_district=" + domicile_district + ", domicile_place_desc=" + domicile_place_desc
					+ ", domicile_street_desc=" + domicile_street_desc + ", domicile_address=" + domicile_address
					+ ", domicile_house=" + domicile_house + ", domicile_block=" + domicile_block + ", domicile_flat="
					+ domicile_flat + ", domicile_begin=" + domicile_begin + ", temp_country=" + temp_country
					+ ", temp_region=" + temp_region + ", temp_district=" + temp_district + ", temp_place_desc="
					+ temp_place_desc + ", temp_street_desc=" + temp_street_desc + ", temp_address=" + temp_address
					+ ", temp_house=" + temp_house + ", temp_block=" + temp_block + ", temp_flat=" + temp_flat
					+ ", temp_begin=" + temp_begin + ", temp_end=" + temp_end + "]";
		}
	    
	    

	
	}


