package com.is.client_sap;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.is.ISLogger;
import com.is.client_personmap.model.FounderCapital;
import com.is.client_personmap.model.LegalEntity;
import com.is.client_personmap.model.Person;
import com.is.client_personmap.model.PersonMap;
import com.is.clients.models.ClientJ;
import com.is.clients.models.License;
import com.is.customer_.core.model.Customer;
import com.is.customer_.sap.service.endpoint.AbstractEndpointService;

import client.NCI.com.ipakyulibank.cj.BusinessOrganizationComplex;
import client.NCI.com.ipakyulibank.cj.BusinessOrganizationContent;
import client.NCI.com.ipakyulibank.cj.BusinessOrganizationContentLicense_set;
import client.NCI.com.ipakyulibank.cj.BusinessOrganizationContentLicense_setLicense;
import client.NCI.com.ipakyulibank.cj.BusinessPartnerContent;
import relationships.NCI.com.ipakyulibank.BPRelResp;
import relationships.NCI.com.ipakyulibank.BPRelWithDate;
import relationships.NCI.com.ipakyulibank.BPShareholder;

public class Mappers extends AbstractEndpointService {

	public static BusinessOrganizationComplex mapPersonToBoc(Person person) {
		if (person == null) {
			return null;
		}
		BusinessOrganizationComplex sap = new BusinessOrganizationComplex();
		if (sap.getGeneral() == null) {
			sap.setGeneral(new BusinessPartnerContent());
		}
		if (sap.getOrganization() == null) {
			sap.setOrganization(new BusinessOrganizationContent());
		}
		sap.getGeneral().setId_client(person.getId());
		sap.getGeneral().setBranch(person.getPersonMap().getBranch());
		sap.getGeneral().setP_birth_place(person.getBirth_place());
		sap.getGeneral().setP_birthday(person.getBirthday());
		sap.getGeneral().setP_code_adr_distr(person.getCode_adr_distr());
		sap.getGeneral().setP_code_adr_region(person.getCode_adr_region());
		sap.getGeneral().setP_code_citizenship(person.getCode_citizenship());
		sap.getGeneral().setP_capacity_status_date(person.getCapacity_status_date());
		sap.getGeneral().setP_capacity_status_place(person.getCapacity_status_place());
		sap.getGeneral().setP_code_birth_distr(person.getCode_birth_distr());
		sap.getGeneral().setP_code_birth_region(person.getCode_birth_region());
		sap.getGeneral().setP_code_capacity(person.getCode_capacity());
		sap.getGeneral().setCode_country(person.getCode_country());
		sap.getGeneral().setP_code_gender(person.getCode_gender());
		sap.getGeneral().setP_code_nation(person.getCode_nation());
		sap.getGeneral().setP_code_tax_org(person.getCode_tax_org());
		sap.getGeneral().setP_family(person.getFamily());
		sap.getGeneral().setP_first_name(person.getFirst_name());
		sap.getGeneral().setP_patronymic(person.getPatronymic());
		sap.getGeneral().setP_inps(person.getInps());
		sap.getGeneral().setP_number_tax_registration(person.getNumber_tax_registration());
		sap.getGeneral().setP_passport_date_expiration(person.getPassport_date_expiration());
		sap.getGeneral().setP_passport_date_registration(person.getPassport_date_registration());
		sap.getGeneral().setP_passport_number(person.getPassport_number());
		sap.getGeneral().setP_passport_place_registration(person.getPassport_place_registration());
		sap.getGeneral().setP_passport_serial(person.getPassport_serial());
		sap.getGeneral().setP_passport_type(person.getPassport_type());
		sap.getGeneral().setP_post_address(person.getPost_address());
		sap.getGeneral().setP_phone_home(person.getPhone_home());
		sap.getGeneral().setP_phone_mobile(person.getPhone_mobile());

		return sap;
	}

	public static Person mapBocToPerson(client.NCI.com.ipakyulibank.client_p.BusinessParnerComplex sap) {
		if (sap == null) {
			return null;
		}
		Person person = new Person();
		person.setId(sap.getGeneral().getId_client());
		person.setBirth_place(sap.getGeneral().getP_birth_place());
		person.setBirthday(sap.getGeneral().getP_birthday());
		person.setCode_adr_distr(sap.getGeneral().getP_code_adr_distr());
		person.setCode_adr_region(sap.getGeneral().getP_code_adr_region());
		person.setCode_resident(sap.getGeneral().getCode_resident());
		person.setCode_citizenship(sap.getGeneral().getP_code_citizenship());
		person.setCapacity_status_date(sap.getGeneral().getP_capacity_status_date());
		person.setCapacity_status_place(sap.getGeneral().getP_capacity_status_place());
		person.setCode_birth_distr(sap.getGeneral().getP_code_birth_distr());
		person.setCode_birth_region(sap.getGeneral().getP_code_birth_region());
		person.setCode_capacity(sap.getGeneral().getP_code_capacity());
		person.setCode_country(sap.getGeneral().getCode_country());
		person.setCode_gender(sap.getGeneral().getP_code_gender());
		person.setCode_nation(sap.getGeneral().getP_code_nation());
		person.setCode_tax_org(sap.getGeneral().getP_code_tax_org());
		person.setFamily(sap.getGeneral().getP_family());
		person.setFirst_name(sap.getGeneral().getP_first_name());
		person.setPatronymic(sap.getGeneral().getP_patronymic());
		person.setInps(sap.getGeneral().getP_inps());
		person.setNumber_tax_registration(sap.getGeneral().getP_number_tax_registration());
		person.setPassport_date_expiration(sap.getGeneral().getP_passport_date_expiration());
		person.setPassport_date_registration(sap.getGeneral().getP_passport_date_registration());
		person.setPassport_number(sap.getGeneral().getP_passport_number());
		person.setPassport_place_registration(sap.getGeneral().getP_passport_place_registration());
		person.setPassport_serial(sap.getGeneral().getP_passport_serial());
		person.setPassport_type(sap.getGeneral().getP_passport_type());
		person.setCode_tax_org(sap.getGeneral().getP_code_tax_org());
		person.setType_document(sap.getGeneral().getP_type_document());
		person.setPass_place_region(sap.getGeneral().getDul_region());
		person.setPass_place_district(sap.getGeneral().getDul_district());
		person.setPost_address(sap.getGeneral().getP_post_address());
		person.setPhone_home(sap.getGeneral().getP_phone_home());
		person.setPhone_mobile(sap.getGeneral().getP_phone_mobile());
		return person;
	}

	public static Person mapBocToPerson(BusinessOrganizationComplex sap) {
		if (sap == null) {
			return null;
		}
		Person person = new Person();
		person.setId(sap.getGeneral().getId_client());
		person.setBirth_place(sap.getGeneral().getP_birth_place());
		person.setBirthday(sap.getGeneral().getP_birthday());
		person.setCode_adr_distr(sap.getGeneral().getP_code_adr_distr());
		person.setCode_adr_region(sap.getGeneral().getP_code_adr_region());
		person.setCode_citizenship(sap.getGeneral().getP_code_citizenship());
		person.setCapacity_status_date(sap.getGeneral().getP_capacity_status_date());
		person.setCapacity_status_place(sap.getGeneral().getP_capacity_status_place());
		person.setCode_birth_distr(sap.getGeneral().getP_code_birth_distr());
		person.setCode_birth_region(sap.getGeneral().getP_code_birth_region());
		person.setCode_capacity(sap.getGeneral().getP_code_capacity());
		person.setCode_country(sap.getGeneral().getCode_country());
		person.setCode_gender(sap.getGeneral().getP_code_gender());
		person.setCode_nation(sap.getGeneral().getP_code_nation());
		person.setCode_tax_org(sap.getGeneral().getP_code_tax_org());
		person.setFamily(sap.getGeneral().getP_family());
		person.setFirst_name(sap.getGeneral().getP_first_name());
		person.setPatronymic(sap.getGeneral().getP_patronymic());
		person.setInps(sap.getGeneral().getP_inps());
		person.setNumber_tax_registration(sap.getGeneral().getP_number_tax_registration());
		person.setPassport_date_expiration(sap.getGeneral().getP_passport_date_expiration());
		person.setPassport_date_registration(sap.getGeneral().getP_passport_date_registration());
		person.setPassport_number(sap.getGeneral().getP_passport_number());
		person.setPassport_place_registration(sap.getGeneral().getP_passport_place_registration());
		person.setPassport_serial(sap.getGeneral().getP_passport_serial());
		person.setPassport_type(sap.getGeneral().getP_passport_type());
		person.setPost_address(sap.getGeneral().getP_post_address());
		person.setPhone_home(sap.getGeneral().getP_phone_home());
		person.setPhone_mobile(sap.getGeneral().getP_phone_mobile());
		return person;
	}

	public static Person mapBpToPerson(Customer sap) {
		if (sap == null) {
			return null;
		}
		Person person = new Person();
		person.setId(sap.getId());
		person.setIdSap(sap.getIdSap());
		person.setBranch(sap.getBranch());
		person.setName(sap.getName());
		person.setBirth_place(sap.getP_birth_place());
		person.setBirthday(sap.getP_birthday());
		person.setCode_resident(sap.getCode_resident());
		person.setCode_country(sap.getCode_country());
		person.setCode_gender(sap.getP_code_gender());
		person.setCode_nation(sap.getP_code_nation());
		person.setCode_adr_distr(sap.getP_code_adr_distr());
		person.setCode_adr_region(sap.getP_code_adr_region());
		person.setCode_citizenship(sap.getP_code_citizenship());
		// person.setCapacity_status_date(sap.getCapacity_status_date());
		// person.setCapacity_status_place(sap.getGeneral().getP_capacity_status_place());
		person.setCode_birth_distr(sap.getP_code_birth_distr());
		person.setCode_birth_region(sap.getP_code_birth_region());
		person.setCode_capacity(sap.getP_code_capacity());
		person.setCode_tax_org(sap.getP_code_tax_org());
		person.setFamily(sap.getP_family());
		person.setFirst_name(sap.getP_first_name());
		person.setPatronymic(sap.getP_patronymic());
		person.setFamily_local(sap.getP_family_local());
		person.setFirst_name_local(sap.getP_first_name_local());
		person.setPatronymic_local(sap.getP_patronymic_local());

		person.setInps(sap.getP_inps());
		person.setNumber_tax_registration(sap.getP_number_tax_registration());
		person.setPassport_date_expiration(sap.getP_passport_date_expiration());
		person.setPassport_date_registration(sap.getP_passport_date_registration());
		person.setPassport_number(sap.getP_passport_number());
		person.setPassport_place_registration(sap.getP_passport_place_registration());
		person.setPassport_serial(sap.getP_passport_serial());
		person.setPassport_type(sap.getP_passport_type());
		person.setPost_address(sap.getP_post_address());
		person.setPass_place_district(sap.getP_pass_place_district());
		person.setPass_place_region(sap.getP_pass_place_region());
		person.setPhone_home(sap.getP_phone_home());
		person.setPhone_mobile(sap.getP_phone_mobile());
		person.setType_document(sap.getP_type_document());
		return person;
	}

	public static Customer mapPersonToBp(Person sap) {
		if (sap == null) {
			return new Customer();
		}
		Customer person = new Customer();
		person.setId("A" + sap.getUnion_id());
		person.setIdSap(sap.getIdSap());
		person.setCode_country(sap.getCode_country());
		person.setCode_resident(sap.getCode_resident());
		person.setP_code_citizenship(sap.getCode_citizenship());
		person.setP_birth_place(sap.getBirth_place());
		person.setP_birthday(sap.getBirthday());
		person.setP_code_adr_distr(sap.getCode_adr_distr());
		person.setP_code_adr_region(sap.getCode_adr_region());
		person.setP_code_citizenship(sap.getCode_citizenship());
		person.setP_code_birth_distr(sap.getCode_birth_distr());
		person.setP_code_birth_region(sap.getCode_birth_region());
		person.setP_code_capacity(sap.getCode_capacity());
		person.setP_code_gender(sap.getCode_gender());
		person.setP_code_nation(sap.getCode_nation());
		person.setP_code_tax_org(sap.getCode_tax_org());
		person.setP_family(sap.getFamily());
		person.setP_first_name(sap.getFirst_name());
		person.setP_patronymic(sap.getPatronymic());
		person.setP_family_local(sap.getFamily_local());
		person.setP_first_name_local(sap.getFirst_name_local());
		person.setP_patronymic_local(sap.getPatronymic_local());

		person.setP_inps(sap.getInps());
		person.setP_number_tax_registration(sap.getNumber_tax_registration());
		person.setP_passport_date_expiration(sap.getPassport_date_expiration());
		person.setP_passport_date_registration(sap.getPassport_date_registration());
		person.setP_passport_number(sap.getPassport_number());
		person.setP_passport_place_registration(sap.getPassport_place_registration());
		person.setP_passport_serial(sap.getPassport_serial());
		person.setP_passport_type(sap.getPassport_type());
		person.setP_post_address(sap.getPost_address());
		person.setP_pass_place_district(sap.getPass_place_district());
		person.setP_pass_place_region(sap.getPass_place_region());
		person.setP_phone_home(sap.getPhone_home());
		person.setP_phone_mobile(sap.getPhone_mobile());
		person.setP_type_document(sap.getType_document());
		return person;
	}

	public static BusinessOrganizationComplex mapLegalToBoc(LegalEntity legalEntity, int operation) {
		BusinessOrganizationComplex sap = new BusinessOrganizationComplex();
		if (sap.getGeneral() == null) {
			sap.setGeneral(new BusinessPartnerContent());
		}
		if (sap.getOrganization() == null) {
			sap.setOrganization(new BusinessOrganizationContent());
		}
		//sap.getGeneral().setId_client("A" + legalEntity.getId());
		sap.getGeneral().setId_client("A" + legalEntity.getUnion_id());
		sap.getGeneral().setOperation(Integer.toString(operation));
		sap.getGeneral().setBranch(legalEntity.getBranch());
		sap.getGeneral().setId_client_sap(legalEntity.getIdSap());
		sap.getGeneral().setCode_country(legalEntity.getCode_country());
		sap.getGeneral().setCode_resident(legalEntity.getCode_resident());

		sap.getOrganization().setGeneral_residency(legalEntity.getCode_resident());
		sap.getOrganization().setCountry(legalEntity.getCode_country());

		// legalEntity.getBranch() +
		// Executions.getCurrent().getSession().getAttribute("uid")
		sap.getOrganization().setGeneral_profile_author(getProfileAuthor());

		sap.getOrganization().setGeneral_name(legalEntity.getName());
		sap.getOrganization().setGeneral_short_name(legalEntity.getShort_name());

		sap.getOrganization().setNumber_registration_doc(legalEntity.getNumber_registration_doc());

		sap.getOrganization().setPlace_regist_name(legalEntity.getPlace_regist_name());
		sap.getOrganization().setDate_registration(legalEntity.getDate_registration());
		sap.getOrganization().setCode_tax_org(legalEntity.getCode_tax_org());
		sap.getOrganization().setGeneral_date_by_statute(legalEntity.getDate_registration());

		sap.getOrganization().setNumber_tax_registration(legalEntity.getNumber_tax_registration());

		// legalEntity.setCode_sector_old(sap.getOrganization().getCode_sector());
		// sap.getOrganization().setCode_class_credit(legalEntity.getCode_class_credit());

		sap.getOrganization().setPost_address(legalEntity.getPost_address());
		sap.getOrganization().setPhone(legalEntity.getPhone());
		sap.getOrganization().setFax(legalEntity.getFax());
		sap.getOrganization().setEmail(legalEntity.getEmail());

		sap.getOrganization().setSign_trade(legalEntity.getSign_trade());
		if (legalEntity.getOpf() != null) {
			sap.getOrganization().setOpf(legalEntity.getOpf().trim());
		}
		if (legalEntity.getSoato() != null) {
			sap.getOrganization().setSoato(legalEntity.getSoato().trim());
		}
		if (legalEntity.getOkpo() != null) {
			sap.getOrganization().setOkpo(legalEntity.getOkpo().trim());
		}
		if (legalEntity.getCode_organ_direct() != null) {
			sap.getOrganization().setCode_organ_direct(legalEntity.getCode_organ_direct().trim());
		}
		if (legalEntity.getCode_head_organization() != null) {
			sap.getOrganization().setCode_head_organization(legalEntity.getCode_head_organization().trim());
		}
		if (legalEntity.getInn_head_organization() != null) {
			sap.getOrganization().setInn_head_organization(legalEntity.getInn_head_organization().trim());
		}
		if (legalEntity.getCode_sector() != null) {
			sap.getOrganization().setCode_sector(legalEntity.getCode_sector().trim());
		}

		sap.getOrganization().setRegion(legalEntity.getPost_address_region());
		sap.getOrganization().setDistr(legalEntity.getPost_address_distr());
		sap.getGeneral().setCode_type(legalEntity.getCode_type());
		sap.getGeneral().setCode_form(legalEntity.getCode_form());
		return sap;
	}

	public static LegalEntity mapBocToLegal(BusinessOrganizationComplex sap) {
		if (sap == null) {
			return null;
		}
		LegalEntity legalEntity = new LegalEntity();
		if (sap.getGeneral() != null) {
			legalEntity.setIdSap(sap.getGeneral().getId_client_sap());
			// legalEntity.setId(sap.getGeneral().getId_client());
			legalEntity.setBranch(sap.getGeneral().getBranch());
		}
		if (sap.getOrganization() != null) {
			// cl.setJ_short_name(sap.getOrganization().get );
			legalEntity.setName(sap.getOrganization().getGeneral_name());
			legalEntity.setShort_name(sap.getOrganization().getGeneral_short_name());
			legalEntity.setCode_resident(sap.getOrganization().getGeneral_residency());
			legalEntity.setCode_country(sap.getOrganization().getCountry());

			legalEntity.setNumber_registration_doc(sap.getOrganization().getNumber_registration_doc());

			legalEntity.setPlace_regist_name(sap.getOrganization().getPlace_regist_name());
			legalEntity.setDate_registration(sap.getOrganization().getDate_registration());
			legalEntity.setCode_tax_org(sap.getOrganization().getCode_tax_org());
			legalEntity.setDate_registration(sap.getOrganization().getDate_registration());

			legalEntity.setNumber_tax_registration(sap.getOrganization().getNumber_tax_registration());
			legalEntity.setCode_sector(sap.getOrganization().getCode_sector());
			// legalEntity.setCode_sector_old(sap.getOrganization().getCode_sector());
			legalEntity.setCode_organ_direct(sap.getOrganization().getCode_organ_direct());
			legalEntity.setCode_head_organization(sap.getOrganization().getCode_head_organization());
			// legalEntity.setCode_class_credit(sap.getOrganization()
			// .getCode_class_credit());

			legalEntity.setPost_address(sap.getOrganization().getPost_address());
			legalEntity.setPhone(sap.getOrganization().getPhone());
			legalEntity.setFax(sap.getOrganization().getFax());
			legalEntity.setEmail(sap.getOrganization().getEmail());

			legalEntity.setSign_trade(sap.getOrganization().getSign_trade());

			legalEntity.setOpf(sap.getOrganization().getOpf());
			legalEntity.setSoato(sap.getOrganization().getSoato());
			legalEntity.setOkpo(sap.getOrganization().getOkpo());
			legalEntity.setInn_head_organization(sap.getOrganization().getInn_head_organization());
			legalEntity.setPost_address_region(sap.getOrganization().getRegion());
			legalEntity.setPost_address_distr(sap.getOrganization().getDistr());
			legalEntity.setCode_form(sap.getGeneral().getCode_form());
			/*
			 * legalEntity.setCode_type(sap.getOrganization().getGeneral_type())
			 * ; if (legalEntity.getCode_type() == null) {
			 * legalEntity.setCode_type("09"); }
			 */
			legalEntity.setCode_type(sap.getGeneral().getCode_type());
		}
		return legalEntity;
	}

	public static ClientJ mapToClientJ(BusinessOrganizationComplex sap) {
		if (sap == null) {
			return null;
		}
		ClientJ cl = new ClientJ();
		if (sap.getGeneral() != null) {
			cl.setBranch(sap.getGeneral().getBranch());
			// if(sap.getOrganization().getNibdd_id() != null) {
			// cl.setId_client(sap.getOrganization().getNibdd_id());
			// } else if(sap.getGeneral().getId_client() != null &&
			// sap.getGeneral().getId_client().startsWith("J")){
			// cl.setId_client(sap.getGeneral().getId_client().substring(1));
			// }
			cl.setId_sap(sap.getGeneral().getId_client_sap());
			// cl.setDate_open(sap.getGeneral().getD)
			cl.setCode_form(sap.getGeneral().getCode_form());
			cl.setCode_subject(sap.getGeneral().getCode_subject());
			cl.setCode_country(sap.getGeneral().getCode_country());
			// cl.setCode_subject(sap.getGeneral().getCode_subject());
			// cl.setCode_type(sap.getGeneral().getGen);
			cl.setCode_resident(sap.getGeneral().getCode_resident());

			// if (sap.getGeneral().getSign_registr() != null) {
			// cl.setSign_registr(sap.getGeneral().getSign_registr());
			// }
			// if (sap.getGeneral().getState() != null) {
			// cl.setState(Integer.toString(sap.getGeneral().getState()));
			// }

			cl.setP_birth_place(sap.getGeneral().getP_birth_place());
			cl.setP_birthday(sap.getGeneral().getP_birthday());
			cl.setP_capacity_status_date(sap.getGeneral().getP_capacity_status_date());
			cl.setP_capacity_status_place(sap.getGeneral().getP_capacity_status_place());
			cl.setP_code_adr_distr(sap.getGeneral().getP_code_adr_distr());
			cl.setP_code_adr_region(sap.getGeneral().getP_code_adr_region());
			cl.setP_code_bank(sap.getGeneral().getP_code_bank());
			cl.setP_code_birth_distr(sap.getGeneral().getP_code_birth_distr());
			cl.setP_code_birth_region(sap.getGeneral().getP_code_birth_region());
			cl.setP_code_capacity(sap.getGeneral().getP_code_capacity());
			cl.setP_code_citizenship(sap.getGeneral().getP_code_citizenship());
			cl.setP_code_class_credit(sap.getGeneral().getP_code_class_credit());
			cl.setP_code_gender(sap.getGeneral().getP_code_gender());
			cl.setP_code_nation(sap.getGeneral().getP_code_nation());
			cl.setP_code_tax_org(sap.getGeneral().getP_code_tax_org());
			cl.setP_email_address(sap.getGeneral().getP_email_address());
			cl.setP_family(sap.getGeneral().getP_family());
			cl.setP_first_name(sap.getGeneral().getP_first_name());
			cl.setP_inps(sap.getGeneral().getP_inps());
			cl.setP_num_certif_capacity(sap.getGeneral().getP_num_certif_capacity());
			cl.setP_number_tax_registration(sap.getGeneral().getP_number_tax_registration());
			cl.setP_passport_date_expiration(sap.getGeneral().getP_passport_date_expiration());
			cl.setP_passport_date_registration(sap.getGeneral().getP_passport_date_registration());
			cl.setP_passport_number(sap.getGeneral().getP_passport_number());
			cl.setP_passport_place_registration(sap.getGeneral().getP_passport_place_registration());
			cl.setP_passport_serial(sap.getGeneral().getP_passport_serial());
			cl.setP_passport_type(sap.getGeneral().getP_passport_type());
			cl.setP_patronymic(sap.getGeneral().getP_patronymic());
			cl.setP_pension_sertif_serial(sap.getGeneral().getP_pension_sertif_serial());
			cl.setP_phone_home(sap.getGeneral().getP_phone_home());
			cl.setP_phone_mobile(sap.getGeneral().getP_phone_mobile());
			cl.setP_post_address(sap.getGeneral().getP_post_address());
			cl.setP_type_document(sap.getGeneral().getP_type_document());
		}
		if (sap.getOrganization() != null) {
			cl.setCode_type(sap.getOrganization().getGeneral_type());
			if (cl.getCode_type() == null) {
				cl.setCode_type("09");
			}
			// cl.setJ_short_name(sap.getOrganization().get );
			cl.setName(sap.getOrganization().getGeneral_name());
			cl.setJ_short_name(sap.getOrganization().getGeneral_short_name());
			cl.setCode_resident(sap.getOrganization().getGeneral_residency());

			cl.setAddressCountry(sap.getOrganization().getCountry());

			cl.setJ_code_bank(sap.getOrganization().getCode_bank());
			cl.setDate_open(sap.getOrganization().getGeneral_date_by_statute());
			cl.setDate_close(sap.getOrganization().getGeneral_date_of_termination());
			// cl.setState(sap.getOrganization().get)
			// cl.setJ_patent_expiration(sap.getOrganization().getLicense_valid_to());

			cl.setJ_number_registration_doc(sap.getOrganization().getNumber_registration_doc());

			cl.setJ_place_regist_name(sap.getOrganization().getPlace_regist_name());
			cl.setJ_code_tax_org(sap.getOrganization().getCode_tax_org());
			cl.setJ_date_registration(sap.getOrganization().getDate_registration());

			cl.setJ_number_tax_registration(sap.getOrganization().getNumber_tax_registration());
			if (!cl.getCode_type().equals("11"))
				cl.setJ_code_sector(sap.getOrganization().getCode_sector());
			else
				cl.setJ_type_activity(sap.getOrganization().getCode_sector());
			cl.setJ_code_organ_direct(sap.getOrganization().getCode_organ_direct());
			cl.setJ_code_head_organization(sap.getOrganization().getCode_head_organization());
			cl.setJ_code_class_credit(sap.getOrganization().getCode_class_credit());
			cl.setJ_director_name(sap.getOrganization().getDirector_name());
			cl.setJ_director_passport(sap.getOrganization().getDirector_passport());
			cl.setJ_chief_accounter_passport(sap.getOrganization().getChief_accounter_passport());
			cl.setJ_code_bank(sap.getOrganization().getCode_bank());
			cl.setJ_account(sap.getOrganization().getAccount());
			cl.setJ_post_address(sap.getOrganization().getPost_address());
			cl.setJ_phone(sap.getOrganization().getPhone());
			cl.setJ_fax(sap.getOrganization().getFax());
			cl.setJ_email(sap.getOrganization().getEmail());

			cl.setJ_sign_trade(sap.getOrganization().getSign_trade());
			cl.setJ_small_business(sap.getOrganization().getSmall_business());
			if (sap.getOrganization().getOpf() != null) {
				cl.setJ_opf(sap.getOrganization().getOpf().trim());
			}
			cl.setJ_soato(sap.getOrganization().getSoato());
			cl.setJ_okpo(sap.getOrganization().getOkpo());
			cl.setJ_inn_head_organization(sap.getOrganization().getInn_head_organization());
			cl.setJ_region(sap.getOrganization().getRegion());
			cl.setJ_distr(sap.getOrganization().getDistr());
			cl.setJ_director_type_document(sap.getOrganization().getDirector_type_document());
			cl.setJ_director_passp_serial(sap.getOrganization().getDirector_passp_serial());
			cl.setJ_director_passp_number(sap.getOrganization().getDirector_passp_number());
			cl.setJ_director_passp_date_reg(sap.getOrganization().getDirector_passp_date_reg());
			cl.setJ_director_passp_place_reg(sap.getOrganization().getDirector_passp_place_reg());
			cl.setJ_director_passp_date_end(sap.getOrganization().getDirector_passp_date_end());
			cl.setJ_director_code_citizenship(sap.getOrganization().getDirector_code_citizenship());
			cl.setJ_director_birthday(sap.getOrganization().getDirector_birthday());
			cl.setJ_director_birth_place(sap.getOrganization().getDirector_birth_place());
			cl.setJ_director_address(sap.getOrganization().getDirector_address());
			cl.setJ_accountant_type_document(sap.getOrganization().getAccountant_type_document());
			cl.setJ_accountant_passp_serial(sap.getOrganization().getAccountant_passp_serial());
			cl.setJ_accountant_passp_number(sap.getOrganization().getAccountant_passp_number());
			cl.setJ_accountant_passp_date_reg(sap.getOrganization().getAccountant_passp_date_reg());
			cl.setJ_accountant_passp_place_reg(sap.getOrganization().getAccountant_passp_place_reg());
			cl.setJ_accountant_passp_date_end(sap.getOrganization().getAccountant_passp_date_end());
			cl.setJ_accountant_code_citizenship(sap.getOrganization().getAccountant_code_citizenship());
			cl.setJ_accountant_birthday(sap.getOrganization().getAccountant_birthday());
			cl.setJ_accountant_birth_place(sap.getOrganization().getAccountant_birth_place());
			cl.setJ_accountant_address(sap.getOrganization().getAccountant_address());
			// cl.setJ_327(sap.getOrganization().get);
			cl.setJ_patent_expiration(sap.getOrganization().getPatent_expiration());
			cl.setJ_responsible_emp(sap.getOrganization().getGeneral_profile_author());

			cl.setAddressCountry(sap.getOrganization().getCountry());
			// list.add(cl);
		}
		// }

		return cl;
	}

	public static ClientJ makeClientJFromLegal(LegalEntity sap) {
		if (sap == null) {
			return null;
		}
		ClientJ cl = new ClientJ();
		cl.setBranch(sap.getBranch());
		cl.setId_client("A" + sap.getId());
		cl.setId_sap(sap.getIdSap());
		// cl.setDate_open(sap.getD)
		cl.setCode_form(sap.getCode_form());
		// cl.setCode_subject(sap.getCode_subject());
		cl.setCode_country(sap.getCode_country());
		// cl.setCode_subject(sap.getCode_subject());
		cl.setCode_type(sap.getCode_type());
		cl.setCode_resident(sap.getCode_resident());
		cl.setJ_number_registration_doc(sap.getNumber_registration_doc());

		cl.setJ_place_regist_name(sap.getPlace_regist_name());
		// cl.setJ_date_registration(sap
		// .getDate_registration());
		cl.setJ_code_tax_org(sap.getCode_tax_org());
		cl.setJ_date_registration(sap.getDate_registration());

		cl.setJ_number_tax_registration(sap.getNumber_tax_registration());
		cl.setJ_code_sector(sap.getCode_sector());
		cl.setJ_code_sector_old(sap.getCode_sector());
		cl.setJ_code_organ_direct(sap.getCode_organ_direct());
		cl.setJ_code_head_organization(sap.getCode_head_organization());
		// cl.setJ_code_class_credit(sap
		// .getCode_class_credit());
		cl.setJ_code_bank(sap.getCode_bank());
		cl.setJ_account(sap.getAccount());
		cl.setJ_post_address(sap.getPost_address());
		cl.setJ_phone(sap.getPhone());
		cl.setJ_fax(sap.getFax());
		cl.setJ_email(sap.getEmail());

		cl.setJ_sign_trade(sap.getSign_trade());
		if (sap.getOpf() != null) {
			cl.setJ_opf(sap.getOpf().trim());
		}
		cl.setJ_soato(sap.getSoato());
		cl.setJ_okpo(sap.getOkpo());
		cl.setJ_inn_head_organization(sap.getInn_head_organization());
		cl.setJ_region(sap.getPost_address_region());
		cl.setJ_distr(sap.getPost_address_distr());

		return cl;
	}

	public static LegalEntity makeLegalFromClientJ(ClientJ sap) {
		if (sap == null) {
			return null;
		}
		LegalEntity cl = new LegalEntity();
		cl.setBranch(sap.getBranch());
		cl.setIdSap(sap.getId_sap());
		// cl.setDate_open(sap.getD)
		// cl.setCode_form(sap.getCode_form());
		// cl.setCode_subject(sap.getCode_subject());
		cl.setCode_country(sap.getCode_country());
		// cl.setCode_subject(sap.getCode_subject());
		// cl.setCode_type(sap.getCode_type());
		cl.setCode_resident(sap.getCode_resident());
		cl.setNumber_registration_doc(sap.getJ_number_registration_doc());

		cl.setPlace_regist_name(sap.getJ_place_regist_name());
		// cl.setJ_date_registration(sap
		// .getDate_registration());
		cl.setCode_tax_org(sap.getJ_code_tax_org());
		cl.setDate_registration(sap.getJ_date_registration());

		cl.setNumber_tax_registration(sap.getJ_number_tax_registration());
		cl.setCode_sector(sap.getJ_code_sector());
		cl.setCode_organ_direct(sap.getJ_code_organ_direct());
		cl.setCode_head_organization(sap.getJ_code_head_organization());
		// cl.setJ_code_class_credit(sap
		// .getCode_class_credit());
		cl.setPost_address(sap.getJ_post_address());
		cl.setPhone(sap.getJ_phone());
		cl.setFax(sap.getJ_fax());
		cl.setEmail(sap.getJ_email());

		cl.setSoato(sap.getJ_soato());
		cl.setOkpo(sap.getJ_okpo());
		cl.setInn_head_organization(sap.getJ_inn_head_organization());
		// cl.setJ_region(sap.getRegion());
		// cl.setJ_distr(sap.getDistr());

		return cl;
	}

	public static BusinessOrganizationComplex makeBOC(ClientJ cl, int action) {
		BusinessOrganizationComplex bpo = null;
		// if (action == 1){
		// }else {

		bpo = new BusinessOrganizationComplex();
		if (bpo.getGeneral() == null) {
			bpo.setGeneral(new BusinessPartnerContent());
		}
		if (bpo.getOrganization() == null) {
			bpo.setOrganization(new BusinessOrganizationContent());
		}
		// }
		bpo.getGeneral().setOperation(Integer.toString(action));
		bpo.getGeneral().setBranch(cl.getBranch());
		bpo.getGeneral().setId_client_sap(cl.getId_sap());
		// prefix J
		bpo.getGeneral().setId_client("J" + cl.getId_client());
		if (cl.getCode_form() != null) {
			bpo.getGeneral().setCode_form(cl.getCode_form().trim());
		}
		if (!cl.getId_client().startsWith("NEW")) {
			bpo.getOrganization().setGeneral_id(cl.getId_client());
			bpo.getOrganization().setNibdd_id(cl.getId_client());
		}
		bpo.getGeneral().setCode_type(cl.getCode_type());
		// Страна регистрации
		bpo.getGeneral().setCode_country(cl.getCode_country());
		bpo.getGeneral().setCode_resident(cl.getCode_resident());
		bpo.getGeneral().setCode_subject(cl.getCode_subject());
		bpo.getGeneral().setSign_registr(cl.getSign_registr());
		if (cl.getState() != null) {
			bpo.getGeneral().setState(Integer.parseInt(cl.getState()));
			bpo.getOrganization().setAccount(cl.getJ_account());
		}
		bpo.getOrganization().setGeneral_profile_author(
				cl.getNibbd_emp_id() == 0 ? getProfileAuthor() : cl.getBranch() + String.valueOf(cl.getNibbd_emp_id()));

		bpo.getOrganization().setGeneral_residency(cl.getCode_resident());

		// Страна адреса
		bpo.getOrganization().setCountry(cl.getAddressCountry());

		bpo.getOrganization().setGeneral_type(cl.getCode_type());

		bpo.getOrganization().setPlace_regist_name(cl.getJ_place_regist_name());
		bpo.getOrganization().setDate_registration(cl.getJ_date_registration());
		bpo.getOrganization().setNumber_registration_doc(cl.getJ_number_registration_doc());
		if (!cl.getCode_type().equals("11")) {
			bpo.getOrganization().setCode_tax_org(cl.getJ_code_tax_org());
			bpo.getOrganization().setNumber_tax_registration(cl.getJ_number_tax_registration());
		}
		if (cl.getJ_opf() != null) {
			bpo.getOrganization().setOpf(cl.getJ_opf().trim());
		}
		if (cl.getJ_code_sector() != null) {
			bpo.getOrganization().setCode_sector(cl.getJ_code_sector().trim());
		}
		if (cl.getCode_type().equals("11") && cl.getJ_type_activity() != null) {
			bpo.getOrganization().setCode_sector(cl.getJ_type_activity().trim());
			ISLogger.getLogger().error("not Error " + cl.getJ_type_activity());
		}
		else 
			ISLogger.getLogger().error("not Error code type = " + cl.getCode_type());
		
		if (cl.getJ_code_organ_direct() != null) {
			bpo.getOrganization().setCode_organ_direct(cl.getJ_code_organ_direct().trim());
		}
		bpo.getOrganization().setInn_head_organization(cl.getJ_inn_head_organization());
		bpo.getOrganization().setCode_head_organization(cl.getJ_code_head_organization());
		bpo.getOrganization().setCode_class_credit(cl.getJ_code_class_credit());
		bpo.getOrganization().setDirector_name(cl.getJ_director_name());
		bpo.getOrganization().setDirector_passport(cl.getJ_director_passport());
		bpo.getOrganization().setChief_accounter_passport(cl.getJ_chief_accounter_passport());
		bpo.getOrganization().setCode_bank(cl.getJ_code_bank());
		bpo.getOrganization().setAccount(cl.getJ_account());

		try {
			if (cl.getName() != null) {
				bpo.getGeneral().setName(new String(cl.getName().getBytes("utf-8"), "utf-8"));
			}

			if (cl.getJ_place_regist_name() != null) {
				bpo.getOrganization()
						.setPlace_regist_name(new String(cl.getJ_place_regist_name().getBytes("utf-8"), "UTF-8"));
			}
			if (cl.getJ_post_address() != null) {
				bpo.getOrganization().setPost_address(new String(cl.getJ_post_address().getBytes("utf-8"), "UTF-8"));
			}
			if (cl.getName() != null) {
				bpo.getOrganization().setGeneral_name(new String(cl.getName().getBytes("utf-8"), "UTF-8"));
			}
			if (cl.getJ_short_name() != null) {
				bpo.getOrganization()
						.setGeneral_short_name(new String(cl.getJ_short_name().getBytes("utf-8"), "UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			ISLogger.getLogger().error("Error occured while encoding post_address with UTF-8 \n" + e.getStackTrace());
			e.printStackTrace();
		}
		bpo.getOrganization().setPhone(cl.getJ_phone());
		bpo.getOrganization().setFax(cl.getJ_fax());
		bpo.getOrganization().setEmail(cl.getJ_email());
		bpo.getOrganization().setSign_trade(cl.getJ_sign_trade());
		bpo.getOrganization().setSmall_business(cl.getJ_small_business());

		bpo.getOrganization().setSoato(cl.getJ_soato());
		bpo.getOrganization().setOkpo(cl.getJ_okpo());
		bpo.getOrganization().setInn_head_organization(cl.getJ_inn_head_organization());
		if (cl.getJ_region() != null) {
			bpo.getOrganization().setRegion(cl.getJ_region().trim());
		}
		if (cl.getJ_distr() != null) {
			bpo.getOrganization().setDistr(cl.getJ_distr().trim());
		}
		bpo.getOrganization().setPatent_expiration(cl.getJ_patent_expiration());
		return bpo;
	}

	public static BusinessOrganizationComplex setUvk(BusinessOrganizationComplex bpo, Map<String, Object> uvkMap) {

		@SuppressWarnings("unchecked")
		List<License> licList = (List<License>) uvkMap.get("licensies");

		BusinessOrganizationContentLicense_set[] licenceSet = new BusinessOrganizationContentLicense_set[licList
				.size()];
		for (License lic : licList) {
			licenceSet[licList.indexOf(lic)] = new BusinessOrganizationContentLicense_set(
					new BusinessOrganizationContentLicense_setLicense(lic.getLicense_type_id(), lic.getLicense_id(),
							lic.getLicense_valid_to(), lic.getLicense_issue_date(), lic.getLicense_issued_by(),
							lic.getLicense_type(), lic.getLicense_type_other(), lic.getLicense_issued_by_other()));
		}

		bpo.getOrganization().setLicense_set(licenceSet);

		bpo.getOrganization().setSo_note((String) uvkMap.get("so_note"));
		bpo.getOrganization().setPo_note((String) uvkMap.get("po_note"));
		bpo.getOrganization().setUvk_risk_level_date((Date) uvkMap.get("uvk_date"));
		bpo.getOrganization().setUvk_risk_level_reason((String) uvkMap.get("uvk_level_reason"));
		bpo.getOrganization().setUvk_risk_level(Integer.toString(((Integer) uvkMap.get("uvk_level"))));
		bpo.getOrganization().setUvk_valid_from((Date) uvkMap.get("uvk_date_from"));
		bpo.getOrganization().setUvk_valid_to((Date) uvkMap.get("uvk_date_to"));

		return bpo;
	}

	public static FounderCapital mapResponseToCapital(PersonMap personMap, BPRelResp[] relations) {
		ClientJ clientJ = Mappers.mapToClientJ(SapFactory.instance().getOrganizationService()
				.getDetailsByNciId(personMap.getClient_id(), personMap.getBranch()));
		FounderCapital founderCapital = new FounderCapital();
		if (relations != null) {
			for (int i = 0; i < relations.length; i++) {
				BPRelResp relation = relations[i];
				boolean rels = relation.getBp_id_01().equals(clientJ.getId_sap())
						&& relation.getBp_relationships().getShareholder() != null;
				if (relation.getBp_id_02().equals(clientJ.getId_sap())
						&& relation.getBp_relationships().getShareholder() != null) {
					BPShareholder shareholder = relation.getBp_relationships().getShareholder();
					BPRelWithDate shares = relation.getBp_relationships();
					founderCapital.setCurrency(shareholder.getCmpy_part_cur());
					founderCapital.setPart_of_capital(shareholder.getCmpy_part_per());
					founderCapital.setSum_a(shareholder.getCmpy_part_amo());
				}
			}
		}
		return founderCapital;
	}
}
