package com.is.nibbd.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.is.ISLogger;
import com.is.account.model.Account;
import com.is.clients.models.ClientJ;
import com.is.nibbd.util.NibbdQueries;
import com.is.utils.CheckNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class Nibbd {
	@Getter
	@Setter
	private String branch;
	@Getter
	@Setter
	private long numeric_id;
	@Getter
	@Setter
	private int query_num;
	@Getter
	@Setter
	private long str_num;
	@Getter
	@Setter
	private String reis_num;
	@Getter
	@Setter
	private String id_client;
	@Getter
	@Setter
	private String resident;
	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private String code_country;
	@Getter
	@Setter
	private String number_registration_doc;
	@Getter
	@Setter
	private String place_regist_name;
	@Getter
	@Setter
	private Date date_registration;
	@Getter
	@Setter
	private String number_tax_registration;
	@Getter
	@Setter
	private String pinfl;
	@Getter
	@Setter
	private String code_head_organization;
	@Getter
	@Setter
	private String inn_head_organization;
	@Getter
	@Setter
	private String code_subject;
	// @Getter @Setter private Date code_subject;
	@Getter
	@Setter
	private String code_type_cyr;
	@Getter
	@Setter
	private String code_type;
	@Getter
	@Setter
	private String opf;
	@Getter
	@Setter
	private String code_form;
	@Getter
	@Setter
	private String soato;
	@Getter
	@Setter
	private String okpo;
	@Getter
	@Setter
	private String code_sector;
	@Getter
	@Setter
	private String reasoning;
	@Getter
	@Setter
	private String acc_bal;
	@Getter
	@Setter
	private String currency;
	@Getter
	@Setter
	private String id_order;
	@Getter
	@Setter
	private String old_acc;
	@Getter
	@Setter
	private String new_acc;
	@Getter
	@Setter
	private String region;
	@Getter
	@Setter
	private String district;
	@Getter
	@Setter
	private Date query_date;
	@Getter
	@Setter
	private String query_inp;
	@Getter
	@Setter
	private String query_out;
	@Getter
	@Setter
	private String parent_id;
	@Getter
	@Setter
	private int state;

	@Getter
	@Setter
	private String account_action;
	@Getter
	@Setter
	private String client_account;
	@Getter
	@Setter
	private String new_bank;
	@Getter
	@Setter
	private String block_sign;
	@Getter
	@Setter
	private Date last_account_movement;

	private SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

	public Nibbd() {
	}

	public Nibbd(String branch, long str_num, int query_num, String reis_num, String query_inp, String parent_id,
			int state, Date query_date, String id_client, String query_out, String name) {
		this.branch = branch;
		this.str_num = str_num;
		this.query_num = query_num;
		this.reis_num = reis_num;
		this.query_inp = query_inp;
		this.parent_id = parent_id;
		this.state = state;
		this.query_date = query_date;
		this.id_client = id_client;
		this.query_out = query_out;
		this.name = name;
	}

	public Nibbd(String id_client, String resident, String name, String code_country, String number_registration_doc,
			String place_regist_name, Date date_registration, String number_tax_registration, String pinfl,
			String code_head_organization, String inn_head_organization, String code_type, String opf, String code_form,
			String soato, String okpo, String code_sector, String reasoning, String acc_bal, String currency,
			String id_order, String old_acc, String new_acc, String region, String district, Date query_date) {
		super();
		this.id_client = id_client;
		this.resident = resident;
		this.name = name;
		this.code_country = code_country;
		this.number_registration_doc = number_registration_doc;
		this.place_regist_name = place_regist_name;
		this.date_registration = date_registration;
		this.number_tax_registration = number_tax_registration;
		this.pinfl = pinfl;
		this.code_head_organization = code_head_organization;
		this.inn_head_organization = inn_head_organization;
		// this.code_subject = code_type;
		this.opf = opf;
		this.code_form = code_form;
		this.soato = soato;
		this.okpo = okpo;
		this.code_sector = code_sector;
		this.reasoning = reasoning;
		this.acc_bal = acc_bal;
		this.currency = currency;
		this.id_order = id_order;
		this.old_acc = old_acc;
		this.new_acc = new_acc;
		this.region = region;
		this.district = district;
		this.query_date = query_date;
	}

	public Nibbd(String id_client, String code_head_organization, String inn_head_organization, String reasoning,
			String acc_bal, String currency, String id_order, Date query_date, String account_action,
			String client_account, String new_bank, String block_sign, Date last_account_movement) {
		super();
		this.id_client = id_client;
		this.code_head_organization = code_head_organization;
		this.inn_head_organization = inn_head_organization;
		this.reasoning = reasoning;
		this.acc_bal = acc_bal;
		this.currency = currency;
		this.id_order = id_order;
		this.query_date = query_date;
		this.account_action = account_action;
		this.client_account = client_account;
		this.new_bank = new_bank;
		this.block_sign = block_sign;
		this.last_account_movement = last_account_movement;
	}

	public Nibbd(String reis_num, Date query_date) {
		super();
		this.reis_num = reis_num;
		this.query_date = query_date;
	}

	public Nibbd(ClientJ client) {
		if (client != null) {
			this.id_client = client.getId_client();
			this.code_country = client.getCode_country();
			this.code_form = client.getCode_form();
			this.code_head_organization = client.getJ_code_head_organization();
			if (client.getJ_code_sector() != null) {
				this.code_sector = client.getJ_code_sector();
			} else {
				this.code_sector = client.getJ_code_sector_old();
			}
			this.code_subject = client.getCode_type();
			this.date_registration = client.getJ_date_registration();
			this.inn_head_organization = client.getJ_inn_head_organization();
			this.name = client.getName();
			this.district = client.getJ_distr();
			this.number_registration_doc = client.getJ_number_registration_doc();
			this.number_tax_registration = client.getJ_number_tax_registration();
			this.pinfl=client.getP_pinfl();
			this.okpo = client.getJ_okpo();
			this.soato = client.getJ_soato();
			this.opf = client.getJ_opf();
			this.place_regist_name = client.getJ_place_regist_name();
			this.resident = client.getCode_resident();
			this.region = client.getJ_region();
			this.district = client.getJ_distr();
			this.parent_id = Long.toString(client.getId());
		}
	}

	public Nibbd(Account account) {
		if (account != null) {
			this.parent_id = account.getId();
			this.id_client = account.getClient();
			this.acc_bal = account.getAcc_bal();
			this.currency = account.getCurrency();
			this.id_order = account.getId_order();
			this.client_account = account.getId();
			this.last_account_movement = account.getL_date();
		}
	}

	public Nibbd(String query) {
		String[] tildaSplited = query.split("~");
		this.str_num = Integer.parseInt(tildaSplited[0]);
		this.query_num = Integer.parseInt(tildaSplited[1]);
		this.query_out = tildaSplited[2];

	}

	public String makeQuery() {
		String query = null;
		switch (NibbdQueries.getQueryType(query_num)) {
		case ACCOUNT_BLOCK:
			query = query3();
			break;
		case ACCOUNT_MOVE:
			query = query6();
			break;
		case ACCOUNT_UNBLOCK:
			query = query4();
			break;
		case ACCOUNT_CLOSE:
			query = query7();
			break;
		case ACCOUNT_OPEN:
			query = query2();
			break;
		case ACCOUNTS_OUSIDE_FILIAL:
			query = query8();
			break;
		case CLIENT_CHANGE:
			query = query5();
			break;
		case CLIENT_OPEN:
			query = openQuery();
			break;
		case CLIENT_STOP:
			query = query9();
			break;
		case IDENTIFICATION:
			query = identQuery();
			break;
		default:
			break;
		}
		return query;
	}

	private String identQuery() {
		StringBuilder sb = new StringBuilder();
		//sb.append(str_num).append("~").append(query_num).append("~").append("Ю").append(";")
		//		.append(number_tax_registration).append("~").append("Запрос N ").append(query_num);
		sb.append(str_num).append("~").append(query_num).append("~").append(makeCodeSubjectZero())
		.append(";");
		if (pinfl!=null) {
			sb.append(pinfl).append("~");
		}
		else
		{
			sb.append(number_tax_registration).append("~");
		}
		sb.append("Запрос N ").append(query_num);
		
		return sb.toString();
	}

	public String openQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append(str_num).append("~").append(query_num).append("~").append(makeCodeSubject()).append(";").append(name)
				.append(";").append(makeCodeRes()).append(";").append(code_country).append(";")
				.append(number_registration_doc != null ? number_registration_doc.trim() : "").append(";")
				.append(place_regist_name).append(";")
				.append(date_registration != null ? df.format(date_registration) : "").append(";");
		        if ( (code_type=="11" || code_type.equals("11")) && (pinfl!=null)) {
		        	sb.append(pinfl).append(";");
		        }        else 		        {
		        	sb.append(number_tax_registration).append(";");
		        }
				sb.append(code_type_cyr).append(";")
				.append(CheckNull.isEmpty(code_head_organization) ? "0" : code_head_organization.trim()).append(";")
				.append(CheckNull.isEmpty(inn_head_organization) ? "0" : inn_head_organization.trim()).append(";")
				.append(CheckNull.isEmpty(opf) ? "0" : opf.trim()).append(";")
				.append(CheckNull.isEmpty(code_form) ? "0" : code_form.trim()).append(";")
				.append(CheckNull.isEmpty(soato) ? "0" : soato.trim()).append(";")
				.append(CheckNull.isEmpty(okpo) ? "0" : okpo.trim()).append(";")
				.append(CheckNull.isEmpty(code_sector) ? "0" : code_sector.trim()).append(";").append(acc_bal)
				.append(";").append(currency).append(";").append(id_order).append(";").append(region).append(";")
				.append(district).append("~").append("Запрос N ").append(query_num);
		;

		return sb.toString();
	}

	public String query5() {
		StringBuilder sb = new StringBuilder();
		sb.append(str_num).append("~").append(query_num).append("~").append(id_client).append(";").append(name)
				.append(";").append(code_type_cyr).append(";").append(makeCodeRes()).append(";").append(code_country)
				.append(";").append(number_registration_doc != null ? number_registration_doc.trim() : "").append(";")
				.append(place_regist_name).append(";")
				.append(date_registration != null ? df.format(date_registration) : "").append(";");
		if ( (code_type=="11" || code_type.equals("11")) && (pinfl!=null)) {
        	sb.append(pinfl).append(";");
        }        else 		        {
        	sb.append(number_tax_registration).append(";");
        }
		
				sb.append(CheckNull.isEmpty(code_head_organization) ? "0" : code_head_organization.trim()).append(";")
				.append(CheckNull.isEmpty(inn_head_organization) ? "0" : inn_head_organization.trim()).append(";")
				.append(CheckNull.isEmpty(opf) ? "" : opf.trim()).append(";")
				.append(CheckNull.isEmpty(code_form) ? "" : code_form.trim()).append(";")
				.append(CheckNull.isEmpty(soato) ? "0" : soato.trim()).append(";")
				.append(CheckNull.isEmpty(okpo) ? "0" : okpo.trim()).append(";")
				.append(CheckNull.isEmpty(code_sector) ? "0" : code_sector.trim()).append(";").append(reasoning)
				.append(";").append(old_acc).append(";").append(new_acc).append(";").append(region).append(";")
				.append(district).append("~").append("Запрос N ").append(query_num);

		return sb.toString();
	}

	private String query2() {
		StringBuilder sb = new StringBuilder();
		sb.append(str_num).append("~").append(query_num).append("~").append(id_client).append(";").append(acc_bal)
				.append(";").append(currency).append(";").append(account_action).append(";").append(id_order)
				.append(";").append(code_head_organization != null ? code_head_organization.trim() : "").append(";")
				.append(inn_head_organization != null ? inn_head_organization.trim() : "").append("~")
				.append("Запрос N ").append(query_num);
		return sb.toString();
	}

	private String query3() {
		StringBuilder sb = new StringBuilder();
		sb.append(str_num).append("~").append(query_num).append("~").append(id_client).append(";").append(acc_bal)
				.append(";").append(currency).append(";").append(id_order).append(";").append(reasoning).append(";")
				.append(query_date != null ? df.format(query_date) : "").append(";").append(block_sign).append(";")
				.append(last_account_movement != null ? df.format(last_account_movement) : "").append("~")
				.append("Запрос N ").append(query_num);
		return sb.toString();
	}

	private String query4() {
		StringBuilder sb = new StringBuilder();
		sb.append(str_num).append("~").append(query_num).append("~").append(id_client).append(";").append(acc_bal)
				.append(";").append(currency).append(";").append(id_order).append(";").append(reasoning).append(";")
				.append(query_date != null ? df.format(query_date) : "").append(";").append(block_sign).append("~")
				.append("Запрос N ").append(query_num);
		return sb.toString();
	}

	private String query6() {
		StringBuilder sb = new StringBuilder();
		sb.append(str_num).append("~").append(query_num).append("~").append(id_client).append(";")
				.append(client_account).append(";").append(new_bank).append(";").append(account_action).append("~")
				.append("Запрос N ").append(query_num);
		return sb.toString();
	}

	private String query7() {
		StringBuilder sb = new StringBuilder();
		sb.append(str_num).append("~").append(query_num).append("~").append(id_client).append(";")
				.append(client_account).append(";").append(block_sign).append("~").append("Запрос N ")
				.append(query_num);
		return sb.toString();
	}

	private String query8() {
		StringBuilder sb = new StringBuilder();
		sb.append(str_num).append("~").append(query_num).append("~").append(id_client).append(";").append(acc_bal)
				.append("~").append("Запрос N ").append(query_num);
		return sb.toString();
	}

	private String query9() {
		StringBuilder sb = new StringBuilder();
		sb.append(str_num).append("~").append(query_num).append("~").append(id_client).append(";")
				.append(date_registration != null ? df.format(date_registration) : "").append(";").append(reasoning)
				.append(";").append(query_date != null ? df.format(query_date) : "").append("~").append("Запрос N ")
				.append(query_num);
		return sb.toString();
	}

	private String makeCodeSubject() {
		if (!CheckNull.isEmpty(code_subject)) {
			// return code_subject.equals("I")?"И":"Ю";
			return (code_subject.equals("11") || code_subject.equals("21") ) ? "И" : "Ю";
		}
		return "";
	}

	private String makeCodeSubjectZero() {
		if (pinfl!=null) {
			return "И";
		} else
		{
		if (number_tax_registration.startsWith("4") || number_tax_registration.startsWith("5")
				|| number_tax_registration.startsWith("6") || number_tax_registration.startsWith("7"))
			return "И";
		else
			return "Ю";
		}
	}

	private String makeCodeRes() {
		if (!CheckNull.isEmpty(resident)) {
			return resident.equals("1") ? "Д" : "Н";
		}
		return "";
	}

	public String parseQueryOut() {
		String code_error = null;
		String[] tildaSplited = query_out.split("~");
		String[] semicolonSplited = tildaSplited[2].split(";");
		code_error = semicolonSplited[0];
		try {
			this.query_num = Integer.parseInt(tildaSplited[1]);
			this.str_num = Long.parseLong(tildaSplited[0]);
		} catch (NumberFormatException e) {
			ISLogger.getLogger().error(e.getStackTrace());
			return "1";
		}

		switch (NibbdQueries.getQueryType(this.query_num)) {
		case ACCOUNT_BLOCK:
			break;
		case ACCOUNT_CLOSE:
			break;
		case ACCOUNT_MOVE:
			break;
		case ACCOUNT_OPEN:
			break;
		case ACCOUNT_UNBLOCK:
			break;
		case ACCOUNTS_OUSIDE_FILIAL:
			break;
		case CLIENT_CHANGE:
			break;
		case CLIENT_OPEN:
			this.new_acc = semicolonSplited[21];
			this.id_client = semicolonSplited[20];
			this.name = semicolonSplited[2];
			break;
		case CLIENT_STOP:
			break;
		case IDENTIFICATION:
			break;
		default:
			break;
		}
		return code_error;
	}
}
