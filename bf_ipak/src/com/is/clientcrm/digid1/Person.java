package com.is.clientcrm.digid1;

public class Person {
	private String RequestGuid;
	private String Pinpp;
	private String SurnameC;
	private String NameC;
	private String PatronymC;
	private String SurnameL;
	private String NameL;
	private String PatronymL;
	private String SurnameE;
	private String NameE;
	private String BirthDate;
	private String Sex;
	private String SexName;
	private String SexNameUz;
	private String BirthCountry;
	private String BirthCountryName;
	private String BirthCountryNameUz;
	private String BirthPlace;
	private String Nationality;
	private String NationalityName;
	private String NationalityNameUz;
	private String DocumentType;
	private String DocumentTypeName;
	private String DocumentTypeNameUz;
	private String DocumentSerialNumber;
	private String DocumentDateIssue;
	private String DocumentDateValid;
	private String DocumentIssuedBy;
	private String PersonStatus;
	private String PersonStatusValue;
	private String Citizenship;
	private String CitizenshipName;
	private String CitizenshipNameUz;
	private String Additional;
	public Person(String requestGuid, String pinpp, String surnameC, String nameC, String patronymC, String surnameL,
			String nameL, String patronymL, String surnameE, String nameE, String birthDate, String sex, String sexName,
			String sexNameUz, String birthCountry, String birthCountryName, String birthCountryNameUz,
			String birthPlace, String nationality, String nationalityName, String nationalityNameUz,
			String documentType, String documentTypeName, String documentTypeNameUz, String documentSerialNumber,
			String documentDateIssue, String documentDateValid, String documentIssuedBy, String personStatus,
			String personStatusValue, String citizenship, String citizenshipName, String citizenshipNameUz,
			String additional) {
		super();
		RequestGuid = requestGuid;
		Pinpp = pinpp;
		SurnameC = surnameC;
		NameC = nameC;
		PatronymC = patronymC;
		SurnameL = surnameL;
		NameL = nameL;
		PatronymL = patronymL;
		SurnameE = surnameE;
		NameE = nameE;
		BirthDate = birthDate;
		Sex = sex;
		SexName = sexName;
		SexNameUz = sexNameUz;
		BirthCountry = birthCountry;
		BirthCountryName = birthCountryName;
		BirthCountryNameUz = birthCountryNameUz;
		BirthPlace = birthPlace;
		Nationality = nationality;
		NationalityName = nationalityName;
		NationalityNameUz = nationalityNameUz;
		DocumentType = documentType;
		DocumentTypeName = documentTypeName;
		DocumentTypeNameUz = documentTypeNameUz;
		DocumentSerialNumber = documentSerialNumber;
		DocumentDateIssue = documentDateIssue;
		DocumentDateValid = documentDateValid;
		DocumentIssuedBy = documentIssuedBy;
		PersonStatus = personStatus;
		PersonStatusValue = personStatusValue;
		Citizenship = citizenship;
		CitizenshipName = citizenshipName;
		CitizenshipNameUz = citizenshipNameUz;
		Additional = additional;
	}
	public String getRequestGuid() {
		return RequestGuid;
	}
	public void setRequestGuid(String requestGuid) {
		RequestGuid = requestGuid;
	}
	public String getPinpp() {
		return Pinpp;
	}
	public void setPinpp(String pinpp) {
		Pinpp = pinpp;
	}
	public String getSurnameC() {
		return SurnameC;
	}
	public void setSurnameC(String surnameC) {
		SurnameC = surnameC;
	}
	public String getNameC() {
		return NameC;
	}
	public void setNameC(String nameC) {
		NameC = nameC;
	}
	public String getPatronymC() {
		return PatronymC;
	}
	public void setPatronymC(String patronymC) {
		PatronymC = patronymC;
	}
	public String getSurnameL() {
		return SurnameL;
	}
	public void setSurnameL(String surnameL) {
		SurnameL = surnameL;
	}
	public String getNameL() {
		return NameL;
	}
	public void setNameL(String nameL) {
		NameL = nameL;
	}
	public String getPatronymL() {
		return PatronymL;
	}
	public void setPatronymL(String patronymL) {
		PatronymL = patronymL;
	}
	public String getSurnameE() {
		return SurnameE;
	}
	public void setSurnameE(String surnameE) {
		SurnameE = surnameE;
	}
	public String getNameE() {
		return NameE;
	}
	public void setNameE(String nameE) {
		NameE = nameE;
	}
	public String getBirthDate() {
		return BirthDate;
	}
	public void setBirthDate(String birthDate) {
		BirthDate = birthDate;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getSexName() {
		return SexName;
	}
	public void setSexName(String sexName) {
		SexName = sexName;
	}
	public String getSexNameUz() {
		return SexNameUz;
	}
	public void setSexNameUz(String sexNameUz) {
		SexNameUz = sexNameUz;
	}
	public String getBirthCountry() {
		return BirthCountry;
	}
	public void setBirthCountry(String birthCountry) {
		BirthCountry = birthCountry;
	}
	public String getBirthCountryName() {
		return BirthCountryName;
	}
	public void setBirthCountryName(String birthCountryName) {
		BirthCountryName = birthCountryName;
	}
	public String getBirthCountryNameUz() {
		return BirthCountryNameUz;
	}
	public void setBirthCountryNameUz(String birthCountryNameUz) {
		BirthCountryNameUz = birthCountryNameUz;
	}
	public String getBirthPlace() {
		return BirthPlace;
	}
	public void setBirthPlace(String birthPlace) {
		BirthPlace = birthPlace;
	}
	public String getNationality() {
		return Nationality;
	}
	public void setNationality(String nationality) {
		Nationality = nationality;
	}
	public String getNationalityName() {
		return NationalityName;
	}
	public void setNationalityName(String nationalityName) {
		NationalityName = nationalityName;
	}
	public String getNationalityNameUz() {
		return NationalityNameUz;
	}
	public void setNationalityNameUz(String nationalityNameUz) {
		NationalityNameUz = nationalityNameUz;
	}
	public String getDocumentType() {
		return DocumentType;
	}
	public void setDocumentType(String documentType) {
		DocumentType = documentType;
	}
	public String getDocumentTypeName() {
		return DocumentTypeName;
	}
	public void setDocumentTypeName(String documentTypeName) {
		DocumentTypeName = documentTypeName;
	}
	public String getDocumentTypeNameUz() {
		return DocumentTypeNameUz;
	}
	public void setDocumentTypeNameUz(String documentTypeNameUz) {
		DocumentTypeNameUz = documentTypeNameUz;
	}
	public String getDocumentSerialNumber() {
		return DocumentSerialNumber;
	}
	public void setDocumentSerialNumber(String documentSerialNumber) {
		DocumentSerialNumber = documentSerialNumber;
	}
	public String getDocumentDateIssue() {
		return DocumentDateIssue;
	}
	public void setDocumentDateIssue(String documentDateIssue) {
		DocumentDateIssue = documentDateIssue;
	}
	public String getDocumentDateValid() {
		return DocumentDateValid;
	}
	public void setDocumentDateValid(String documentDateValid) {
		DocumentDateValid = documentDateValid;
	}
	public String getDocumentIssuedBy() {
		return DocumentIssuedBy;
	}
	public void setDocumentIssuedBy(String documentIssuedBy) {
		DocumentIssuedBy = documentIssuedBy;
	}
	public String getPersonStatus() {
		return PersonStatus;
	}
	public void setPersonStatus(String personStatus) {
		PersonStatus = personStatus;
	}
	public String getPersonStatusValue() {
		return PersonStatusValue;
	}
	public void setPersonStatusValue(String personStatusValue) {
		PersonStatusValue = personStatusValue;
	}
	public String getCitizenship() {
		return Citizenship;
	}
	public void setCitizenship(String citizenship) {
		Citizenship = citizenship;
	}
	public String getCitizenshipName() {
		return CitizenshipName;
	}
	public void setCitizenshipName(String citizenshipName) {
		CitizenshipName = citizenshipName;
	}
	public String getCitizenshipNameUz() {
		return CitizenshipNameUz;
	}
	public void setCitizenshipNameUz(String citizenshipNameUz) {
		CitizenshipNameUz = citizenshipNameUz;
	}
	public String getAdditional() {
		return Additional;
	}
	public void setAdditional(String additional) {
		Additional = additional;
	}

	
}
