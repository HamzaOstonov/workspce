package com.is.humo.client;

import java.util.Date;

import org.apache.axis2.addressing.AddressingConstants.Final;

public class HumoClient {
	private long id;
	private String branch;
	private String bankClientId;
	private String name;
	private String codeCountry;
	private String codeType;
	private String codeResident;
	private String codeSubject;
	private String codeProduct;
	private int signRegistr;
	private String codeForm;
	private Date dateOpen;
	private Date dateClose;
	private int state;
	private Date birthday;
	private String postAddress;
	private String passportType;
	private String passportSerial;
	private String passportNumber;
	private String passportPlaceRegistration;
	private Date passportDateRegistration;
	private String pCodeTaxOrg;
	private String numberTaxRegistration;
	private String codeBank;
	private String codeClassCredit;
	private String codeCitizenship;
	private String birthPlace;
	private String codeCapacity;
	private Date capacityStatusDate;
	private String capacityStatusPlace;
	private String numCertifCapacity;
	private String phoneHome;
	private String phoneMobile;
	private String emailAddress;
	private String pensionSertifSerial;
	private String codeGender;
	private String codeNation;
	private String codeBirthRegion;
	private String codeBirthDistr;
	private String typeDocument;
	private Date passportDateExpiration;
	private String codeAdrRegion;
	private String codeAdrDistr;
	private String inps;
	private String family;
	private String firstName;
	private String patronymic;
	private String humoClientId;
	private String headCustomerId;
	private String acodeTel;
	private String rCity;
	private String rStreet;
	private String rEmails;
	private String rMobPhone;
	private String rPhone;
	private String rCntry;
	private String issuedBy;
	private String personCode;
	private String pinfl;

	public HumoClient() {
	}

	public HumoClient(long id, String branch, String bankClientId, String name,
			String codeCountry, String codeType, String codeResident,
			String codeSubject, String codeProduct, int signRegistr,
			String codeForm, Date dateOpen, Date dateClose, int state,
			Date birthday, String postAddress, String passportType,
			String passportSerial, String passportNumber,
			String passportPlaceRegistration, Date passportDateRegistration,
			String pCodeTaxOrg, String numberTaxRegistration, String codeBank,
			String codeClassCredit, String codeCitizenship, String birthPlace,
			String codeCapacity, Date capacityStatusDate,
			String capacityStatusPlace, String numCertifCapacity,
			String phoneHome, String phoneMobile, String emailAddress,
			String pensionSertifSerial, String codeGender, String codeNation,
			String codeBirthRegion, String codeBirthDistr, String typeDocument,
			Date passportDateExpiration, String codeAdrRegion,
			String codeAdrDistr, String inps, String family, String firstName,
			String patronymic, String humoClientId, String headCustomerId,
			String acodeTel, String rCity, String rStreet, String rEmails,
			String rMobPhone, String rPhone, String rCntry, String issuedBy,
			String personCode, String pinfl) {
		super();
		this.id = id;
		this.branch = branch;
		this.bankClientId = bankClientId;
		this.name = name;
		this.codeCountry = codeCountry;
		this.codeType = codeType;
		this.codeResident = codeResident;
		this.codeSubject = codeSubject;
		this.codeProduct = codeProduct;
		this.signRegistr = signRegistr;
		this.codeForm = codeForm;
		this.dateOpen = dateOpen;
		this.dateClose = dateClose;
		this.state = state;
		this.birthday = birthday;
		this.postAddress = postAddress;
		this.passportType = passportType;
		this.passportSerial = passportSerial;
		this.passportNumber = passportNumber;
		this.passportPlaceRegistration = passportPlaceRegistration;
		this.passportDateRegistration = passportDateRegistration;
		this.pCodeTaxOrg = pCodeTaxOrg;
		this.numberTaxRegistration = numberTaxRegistration;
		this.codeBank = codeBank;
		this.codeClassCredit = codeClassCredit;
		this.codeCitizenship = codeCitizenship;
		this.birthPlace = birthPlace;
		this.codeCapacity = codeCapacity;
		this.capacityStatusDate = capacityStatusDate;
		this.capacityStatusPlace = capacityStatusPlace;
		this.numCertifCapacity = numCertifCapacity;
		this.phoneHome = phoneHome;
		this.phoneMobile = phoneMobile;
		this.emailAddress = emailAddress;
		this.pensionSertifSerial = pensionSertifSerial;
		this.codeGender = codeGender;
		this.codeNation = codeNation;
		this.codeBirthRegion = codeBirthRegion;
		this.codeBirthDistr = codeBirthDistr;
		this.typeDocument = typeDocument;
		this.passportDateExpiration = passportDateExpiration;
		this.codeAdrRegion = codeAdrRegion;
		this.codeAdrDistr = codeAdrDistr;
		this.inps = inps;
		this.family = family;
		this.firstName = firstName;
		this.patronymic = patronymic;
		this.humoClientId = humoClientId;
		this.headCustomerId = headCustomerId;
		this.acodeTel = acodeTel;
		this.rCity = rCity;
		this.rStreet = rStreet;
		this.rEmails = rEmails;
		this.rMobPhone = rMobPhone;
		this.rPhone = rPhone;
		this.rCntry = rCntry;
		this.issuedBy = issuedBy;
		this.personCode = personCode;
		this.pinfl = pinfl;
	}

	public long getId() {
		return id;
	}

	public String getBranch() {
		return branch;
	}

	public String getBankClientId() {
		return bankClientId;
	}

	public String getName() {
		return name;
	}

	public String getCodeCountry() {
		return codeCountry;
	}

	public String getCodeType() {
		return codeType;
	}

	public String getCodeResident() {
		return codeResident;
	}

	public String getCodeSubject() {
		return codeSubject;
	}

	public String getCodeProduct() {
		return codeProduct;
	}

	public int getSignRegistr() {
		return signRegistr;
	}

	public String getCodeForm() {
		return codeForm;
	}

	public Date getDateOpen() {
		return dateOpen;
	}

	public Date getDateClose() {
		return dateClose;
	}

	public int getState() {
		return state;
	}

	public Date getBirthday() {
		return birthday;
	}

	public String getPostAddress() {
		return postAddress;
	}

	public String getPassportType() {
		return passportType;
	}

	public String getPassportSerial() {
		return passportSerial;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public String getPassportPlaceRegistration() {
		return passportPlaceRegistration;
	}

	public Date getPassportDateRegistration() {
		return passportDateRegistration;
	}

	public String getpCodeTaxOrg() {
		return pCodeTaxOrg;
	}

	public String getNumberTaxRegistration() {
		return numberTaxRegistration;
	}

	public String getCodeBank() {
		return codeBank;
	}

	public String getCodeClassCredit() {
		return codeClassCredit;
	}

	public String getCodeCitizenship() {
		return codeCitizenship;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public String getCodeCapacity() {
		return codeCapacity;
	}

	public Date getCapacityStatusDate() {
		return capacityStatusDate;
	}

	public String getCapacityStatusPlace() {
		return capacityStatusPlace;
	}

	public String getNumCertifCapacity() {
		return numCertifCapacity;
	}

	public String getPhoneHome() {
		return phoneHome;
	}

	public String getPhoneMobile() {
		return phoneMobile;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getPensionSertifSerial() {
		return pensionSertifSerial;
	}

	public String getCodeGender() {
		return codeGender;
	}

	public String getCodeNation() {
		return codeNation;
	}

	public String getCodeBirthRegion() {
		return codeBirthRegion;
	}

	public String getCodeBirthDistr() {
		return codeBirthDistr;
	}

	public String getTypeDocument() {
		return typeDocument;
	}

	public Date getPassportDateExpiration() {
		return passportDateExpiration;
	}

	public String getCodeAdrRegion() {
		return codeAdrRegion;
	}

	public String getCodeAdrDistr() {
		return codeAdrDistr;
	}

	public String getInps() {
		return inps;
	}

	public String getFamily() {
		return family;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public String getHumoClientId() {
		return humoClientId;
	}

	public String getHeadCustomerId() {
		return headCustomerId;
	}

	public String getAcodeTel() {
		return acodeTel;
	}

	public String getrCity() {
		return rCity;
	}

	public String getrStreet() {
		return rStreet;
	}

	public String getrEmails() {
		return rEmails;
	}

	public String getrMobPhone() {
		return rMobPhone;
	}

	public String getrPhone() {
		return rPhone;
	}

	public String getrCntry() {
		return rCntry;
	}

	public String getIssuedBy() {
		return issuedBy;
	}

	public String getPersonCode() {
		return personCode;
	}

	public String getPinfl() {
		return pinfl;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public void setBankClientId(String bankClientId) {
		this.bankClientId = bankClientId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCodeCountry(String codeCountry) {
		this.codeCountry = codeCountry;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public void setCodeResident(String codeResident) {
		this.codeResident = codeResident;
	}

	public void setCodeSubject(String codeSubject) {
		this.codeSubject = codeSubject;
	}

	public void setCodeProduct(String codeProduct) {
		this.codeProduct = codeProduct;
	}

	public void setSignRegistr(int signRegistr) {
		this.signRegistr = signRegistr;
	}

	public void setCodeForm(String codeForm) {
		this.codeForm = codeForm;
	}

	public void setDateOpen(Date dateOpen) {
		this.dateOpen = dateOpen;
	}

	public void setDateClose(Date dateClose) {
		this.dateClose = dateClose;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setPostAddress(String postAddress) {
		this.postAddress = postAddress;
	}

	public void setPassportType(String passportType) {
		this.passportType = passportType;
	}

	public void setPassportSerial(String passportSerial) {
		this.passportSerial = passportSerial;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public void setPassportPlaceRegistration(String passportPlaceRegistration) {
		this.passportPlaceRegistration = passportPlaceRegistration;
	}

	public void setPassportDateRegistration(Date passportDateRegistration) {
		this.passportDateRegistration = passportDateRegistration;
	}

	public void setpCodeTaxOrg(String pCodeTaxOrg) {
		this.pCodeTaxOrg = pCodeTaxOrg;
	}

	public void setNumberTaxRegistration(String numberTaxRegistration) {
		this.numberTaxRegistration = numberTaxRegistration;
	}

	public void setCodeBank(String codeBank) {
		this.codeBank = codeBank;
	}

	public void setCodeClassCredit(String codeClassCredit) {
		this.codeClassCredit = codeClassCredit;
	}

	public void setCodeCitizenship(String codeCitizenship) {
		this.codeCitizenship = codeCitizenship;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public void setCodeCapacity(String codeCapacity) {
		this.codeCapacity = codeCapacity;
	}

	public void setCapacityStatusDate(Date capacityStatusDate) {
		this.capacityStatusDate = capacityStatusDate;
	}

	public void setCapacityStatusPlace(String capacityStatusPlace) {
		this.capacityStatusPlace = capacityStatusPlace;
	}

	public void setNumCertifCapacity(String numCertifCapacity) {
		this.numCertifCapacity = numCertifCapacity;
	}

	public void setPhoneHome(String phoneHome) {
		this.phoneHome = phoneHome;
	}

	public void setPhoneMobile(String phoneMobile) {
		this.phoneMobile = phoneMobile;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setPensionSertifSerial(String pensionSertifSerial) {
		this.pensionSertifSerial = pensionSertifSerial;
	}

	public void setCodeGender(String codeGender) {
		this.codeGender = codeGender;
	}

	public void setCodeNation(String codeNation) {
		this.codeNation = codeNation;
	}

	public void setCodeBirthRegion(String codeBirthRegion) {
		this.codeBirthRegion = codeBirthRegion;
	}

	public void setCodeBirthDistr(String codeBirthDistr) {
		this.codeBirthDistr = codeBirthDistr;
	}

	public void setTypeDocument(String typeDocument) {
		this.typeDocument = typeDocument;
	}

	public void setPassportDateExpiration(Date passportDateExpiration) {
		this.passportDateExpiration = passportDateExpiration;
	}

	public void setCodeAdrRegion(String codeAdrRegion) {
		this.codeAdrRegion = codeAdrRegion;
	}

	public void setCodeAdrDistr(String codeAdrDistr) {
		this.codeAdrDistr = codeAdrDistr;
	}

	public void setInps(String inps) {
		this.inps = inps;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public void setHumoClientId(String humoClientId) {
		this.humoClientId = humoClientId;
	}

	public void setHeadCustomerId(String headCustomerId) {
		this.headCustomerId = headCustomerId;
	}

	public void setAcodeTel(String acodeTel) {
		this.acodeTel = acodeTel;
	}

	public void setrCity(String rCity) {
		this.rCity = rCity;
	}

	public void setrStreet(String rStreet) {
		this.rStreet = rStreet;
	}

	public void setrEmails(String rEmails) {
		this.rEmails = rEmails;
	}

	public void setrMobPhone(String rMobPhone) {
		this.rMobPhone = rMobPhone;
	}

	public void setrPhone(String rPhone) {
		this.rPhone = rPhone;
	}

	public void setrCntry(String rCntry) {
		this.rCntry = rCntry;
	}

	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public void setPinfl(String pinfl) {
		this.pinfl = pinfl;
	}

}
