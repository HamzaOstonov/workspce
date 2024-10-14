package com.is.globalTieto.modelTransform;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.is.globalTieto.tietoModels.CardBalance;
import com.is.globalTieto.tietoModels.CardBalanceFilter;
import com.is.globalTieto.tietoModels.ExecTransactionRequest;
import com.is.globalTieto.tietoModels.ExecTransactionResponse;
import com.is.globalTieto.tietoModels.ListAccountsFilter;
import com.is.globalTieto.tietoModels.ListAccountsItem;
import com.is.globalTieto.tietoModels.ListCardsFilter;
import com.is.globalTieto.tietoModels.ListCardsItem;
import com.is.globalTieto.tietoModels.ListCustomersFilter;
import com.is.globalTieto.tietoModels.ListCustomersItem;
import com.is.globalTieto.tietoModels.ResponseInfo;

import kapitalWS.issuing_v_01_02_xsd.ItemType_Generic;
import kapitalWS.issuing_v_01_02_xsd.ListType_Generic;
import kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo;
import kapitalWS.issuing_v_01_02_xsd.RowType_AccBalanceQueryByCard_Request;
import kapitalWS.issuing_v_01_02_xsd.RowType_ExecTransaction_Request;
import kapitalWS.issuing_v_01_02_xsd.RowType_ExecTransaction_Response;
import kapitalWS.issuing_v_01_02_xsd.RowType_Generic;
import kapitalWS.issuing_v_01_02_xsd.RowType_ListAccounts_Request;
import kapitalWS.issuing_v_01_02_xsd.RowType_ListCardsByAccount_Request;
import kapitalWS.issuing_v_01_02_xsd.RowType_ListCustomers_Request;

public class KapitalModelTransformer {
	public static RowType_ListCustomers_Request getTietoListCustomersRequest(ListCustomersFilter filter) {
		Calendar bDate = Calendar.getInstance();
		Calendar supplementaryExpiry = Calendar.getInstance();
		if (filter.getB_date() != null) {
			bDate.setTime(filter.getB_date());
		}
		if (filter.getSupplementary_expiry() != null) {
			supplementaryExpiry.setTime(filter.getSupplementary_expiry());
		}
		RowType_ListCustomers_Request result = new RowType_ListCustomers_Request();

		if (filter.getA3vts_county() != null) {
			result.setA3VTS_COUNTY(filter.getA3vts_county());
		}
		if (filter.getA3vts_flag1() != null) {
			result.setA3VTS_FLAG1(filter.getA3vts_flag1());
		}
		if (filter.getA3vts_flag2() != null) {
			result.setA3VTS_FLAG2(filter.getA3vts_flag2());
		}
		if (filter.getB_date() != null) {
			result.setB_DATE(bDate);
		}
		if (filter.getBank_c() != null) {
			result.setBANK_C(filter.getBank_c());
		}
		if (filter.getCard() != null) {
			result.setCARD(filter.getCard());
		}
		if (filter.getClient() != null) {
			result.setCLIENT(filter.getClient());
		}
		if (filter.getClient_b() != null) {
			result.setCLIENT_B(filter.getClient_b());
		}
		if (filter.getEmp_name() != null) {
			result.setEMP_NAME(filter.getEmp_name());
		}
		if (filter.getF_names() != null) {
			result.setF_NAMES(filter.getF_names());
		}
		if (filter.getNotes() != null) {
			result.setNOTES(filter.getNotes());
		}
		if (filter.getPerson_code() != null) {
			result.setPERSON_CODE(filter.getPerson_code());
		}
		if (filter.getR_city() != null) {
			result.setR_CITY(filter.getR_city());
		}
		if (filter.getR_cntry() != null) {
			result.setR_CNTRY(filter.getR_cntry());
		}
		if (filter.getR_e_mails() != null) {
			result.setR_E_MAILS(filter.getR_e_mails());
		}
		if (filter.getR_mob_phone() != null) {
			result.setR_MOB_PHONE(filter.getR_mob_phone());
		}
		if (filter.getR_pcode() != null) {
			result.setR_PCODE(filter.getR_pcode());
		}
		if (filter.getR_street() != null) {
			result.setR_STREET(filter.getR_street());
		}
		if (filter.getStatus() != null) {
			result.setSTATUS(filter.getStatus());
		}
		if (filter.getSupplementary_cvv2() != null) {
			result.setSUPPLEMENTARY_CVV2(filter.getSupplementary_cvv2());
		}
		if (filter.getSupplementary_expiry() != null) {
			result.setSUPPLEMENTARY_EXPIRY(supplementaryExpiry);
		}
		if (filter.getSupplementary_pan() != null) {
			result.setSUPPLEMENTARY_PAN(filter.getSupplementary_pan());
		}
		if (filter.getSurname() != null) {
			result.setSURNAME(filter.getSurname());
		}
		if (filter.getU_cod1() != null) {
			result.setU_COD1(filter.getU_cod1());
		}

		return result;
	}

	public static ArrayList<ListCustomersItem> getListCustomersItem(ListType_Generic inList) throws ParseException {
		ArrayList<ListCustomersItem> result = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");

		if (inList != null && inList.getRow() != null) {
			result = new ArrayList<ListCustomersItem>();
			RowType_Generic[] row = inList.getRow();
			for (RowType_Generic curRow : row) {
				ListCustomersItem addItem = new ListCustomersItem();
				ItemType_Generic[] item = curRow.getItem();
				for (ItemType_Generic curItem : item) {
					String name = curItem.getName();
					String value = curItem.getValue();
					if (name.equals("CLIENT") && !value.equals("")) {
						addItem.setClient(value);
					} else if (name.equals("STATUS") && !value.equals("")) {
						addItem.setStatus(value);
					} else if (name.equals("CLIENT_B") && !value.equals("")) {
						addItem.setClient_b(value);
					} else if (name.equals("F_NAMES") && !value.equals("")) {
						addItem.setF_names(value);
					} else if (name.equals("SURNAME") && !value.equals("")) {
						addItem.setSurname(value);
					} else if (name.equals("PERSON_CODE") && !value.equals("")) {
						addItem.setPerson_code(value);
					} else if (name.equals("B_DATE") && !value.equals("")) {
						addItem.setB_date(dateFormat.parse(value));
					} else if (name.equals("R_STREET") && !value.equals("")) {
						addItem.setR_street(value);
					} else if (name.equals("R_CITY") && !value.equals("")) {
						addItem.setR_city(value);
					} else if (name.equals("R_CNTRY") && !value.equals("")) {
						addItem.setR_cntry(value);
					} else if (name.equals("R_PCODE") && !value.equals("")) {
						addItem.setR_pcode(value);
					} else if (name.equals("R_E_MAILS") && !value.equals("")) {
						addItem.setR_e_mails(value);
					} else if (name.equals("R_MOB_PHONE") && !value.equals("")) {
						addItem.setR_mob_phone(value);
					} else if (name.equals("CARD") && !value.equals("")) {
						addItem.setCard(value);
					} else if (name.equals("BANK_C") && !value.equals("")) {
						addItem.setBank_c(value);
					} else if (name.equals("U_COD1") && !value.equals("")) {
						addItem.setU_cod1(value);
					} else if (name.equals("A3VTS_COUNTY") && !value.equals("")) {
						addItem.setA3vts_county(value);
					} else if (name.equals("A3VTS_FLAG1") && !value.equals("")) {
						addItem.setA3vts_flag1(value);
					} else if (name.equals("A3VTS_FLAG2") && !value.equals("")) {
						addItem.setA3vts_flag2(value);
					} else if (name.equals("SUPPLEMENTARY_PAN") && !value.equals("")) {
						addItem.setSupplementary_pan(value);
					} else if (name.equals("SUPPLEMENTARY_CVV2") && !value.equals("")) {
						addItem.setSupplementary_cvv2(value);
					} else if (name.equals("SUPPLEMENTARY_EXPIRY") && !value.equals("")) {
						addItem.setSupplementary_expiry(dateFormat.parse(value));
					} else if (name.equals("NOTES") && !value.equals("")) {
						addItem.setNotes(value);
					} else if (name.equals("EMP_NAME") && !value.equals("")) {
						addItem.setEmp_name(value);
					}
				}
				result.add(addItem);
			}
		}

		return result;
	}

	public static ResponseInfo getResponseInfo(OperationResponseInfo info) {
		ResponseInfo result = new ResponseInfo();

		if (info.getResponse_code() != null) {
			result.setResponse_code(new BigDecimal(info.getResponse_code()));
		}
		if (info.getError_description() != null) {
			result.setError_description(info.getError_description());
		}
		if (info.getError_action() != null) {
			result.setError_action(info.getError_action());
		}
		if (info.getEXTERNAL_SESSION_ID() != null) {
			result.setExternal_session_id(info.getEXTERNAL_SESSION_ID());
		}

		return result;
	}

	public static ListAccountsFilter getAccFilterByCustomer(ListCustomersItem filter) {
		ListAccountsFilter result = new ListAccountsFilter();

		if (filter.getClient_b() != null && !filter.getClient_b().equals("")) {
			result.setClient_b(filter.getClient_b());
		}
		if (filter.getClient() != null && !filter.getClient().equals("")) {
			result.setClient(filter.getClient());
		}
		if (filter.getF_names() != null && !filter.getF_names().equals("")) {
			result.setF_names(filter.getF_names());
		}
		if (filter.getSurname() != null && !filter.getSurname().equals("")) {
			result.setSurname(filter.getSurname());
		}

		return result;
	}

	public static RowType_ListAccounts_Request getTietoListAccountsRequest(ListAccountsFilter filter) {
		RowType_ListAccounts_Request result = new RowType_ListAccounts_Request();

		if (filter.getCard_acct() != null && !filter.getCard_acct().equals("")) {
			result.setCARD_ACCT(filter.getCard_acct());
		}
		if (filter.getTranz_acct() != null && !filter.getTranz_acct().equals("")) {
			result.setTRANZ_ACCT(filter.getTranz_acct());
		}
		if (filter.getStatus() != null && !filter.getStatus().equals("")) {
			result.setSTATUS(filter.getStatus());
		}
		if (filter.getAcc_prty() != null && !filter.getAcc_prty().equals("")) {
			result.setACC_PRTY(filter.getAcc_prty());
		}
		if (filter.getC_accnt_type() != null && !filter.getC_accnt_type().equals("")) {
			result.setC_ACCNT_TYPE(filter.getC_accnt_type());
		}
		if (filter.getCcy() != null && !filter.getCcy().equals("")) {
			result.setCCY(filter.getCcy());
		}
		if (filter.getCond_set() != null && !filter.getCond_set().equals("")) {
			result.setCOND_SET(filter.getCond_set());
		}
		if (filter.getClient_b() != null && !filter.getClient_b().equals("")) {
			result.setCLIENT_B(filter.getClient_b());
		}
		if (filter.getClient() != null && !filter.getClient().equals("")) {
			result.setCLIENT(filter.getClient());
		}
		if (filter.getF_names() != null && !filter.getF_names().equals("")) {
			result.setF_NAMES(filter.getF_names());
		}
		if (filter.getSurname() != null && !filter.getSurname().equals("")) {
			result.setSURNAME(filter.getSurname());
		}
		if (filter.getB_br_id() != null) {
			result.setB_BR_ID(filter.getB_br_id());
		}
		if (filter.getOffice_id() != null) {
			result.setOFFICE_ID(filter.getOffice_id());
		}
		if (filter.getMain_row() != null) {
			result.setMAIN_ROW(filter.getMain_row());
		}
		if (filter.getAccount_no() != null) {
			result.setACCOUNT_NO(filter.getAccount_no());
		}

		return result;
	}

	public static ArrayList<ListAccountsItem> getListAccountsItem(ListType_Generic inList) {
		ArrayList<ListAccountsItem> result = null;

		if (inList != null && inList.getRow() != null) {
			result = new ArrayList<ListAccountsItem>();
			RowType_Generic[] row = inList.getRow();
			for (RowType_Generic curRow : row) {
				ListAccountsItem addItem = new ListAccountsItem();
				ItemType_Generic[] item = curRow.getItem();
				for (ItemType_Generic curItem : item) {
					String name = curItem.getName();
					String value = curItem.getValue();
					if (name.equals("CARD_ACCT") && !value.equals("")) {
						addItem.setCard_acct(value);
					} else if (name.equals("TRANZ_ACCT") && !value.equals("")) {
						addItem.setTranz_acct(value);
					} else if (name.equals("STATUS") && !value.equals("")) {
						addItem.setStatus(value);
					} else if (name.equals("ACC_PRTY") && !value.equals("")) {
						addItem.setAcc_prty(value);
					} else if (name.equals("C_ACCNT_TYPE") && !value.equals("")) {
						addItem.setC_accnt_type(value);
					} else if (name.equals("CCY") && !value.equals("")) {
						addItem.setCcy(value);
					} else if (name.equals("COND_SET") && !value.equals("")) {
						addItem.setCond_set(value);
					} else if (name.equals("CLIENT_B") && !value.equals("")) {
						addItem.setClient_b(value);
					} else if (name.equals("CLIENT") && !value.equals("")) {
						addItem.setClient(value);
					} else if (name.equals("F_NAMES") && !value.equals("")) {
						addItem.setF_names(value);
					} else if (name.equals("SURNAME") && !value.equals("")) {
						addItem.setSurname(value);
					} else if (name.equals("B_BR_ID") && !value.equals("")) {
						addItem.setB_br_id(BigDecimal.valueOf(Long.valueOf(value)));
					} else if (name.equals("OFFICE_ID") && !value.equals("")) {
						addItem.setOffice_id(BigDecimal.valueOf(Long.valueOf(value)));
					} else if (name.equals("MAIN_ROW") && !value.equals("")) {
						addItem.setMain_row(BigDecimal.valueOf(Long.valueOf(value)));
					} else if (name.equals("ACCOUNT_NO") && !value.equals("")) {
						addItem.setAccount_no(BigDecimal.valueOf(Long.valueOf(value)));
					} else if (name.equals("BANK_C") && !value.equals("")) {
						addItem.setBank_c(value);
					} else if (name.equals("GROUPC") && !value.equals("")) {
						addItem.setGroupc(value);
					}
				}
				result.add(addItem);
			}
		}

		return result;
	}

	public static ListCardsFilter getCardFilterByAcc(ListAccountsItem filter) {
		ListCardsFilter result = new ListCardsFilter();

		if (filter.getAccount_no() != null) {
			result.setAccount_no(filter.getAccount_no());
		}
		if (filter.getCard_acct() != null && !filter.getCard_acct().equals("")) {
			result.setCard_acct(filter.getCard_acct());
		}
		if (filter.getCcy() != null && !filter.getCcy().equals("")) {
			result.setCcy(filter.getCcy());
		}

		return result;
	}

	public static RowType_ListCardsByAccount_Request getTietoListCardsRequest(ListCardsFilter filter) {
		RowType_ListCardsByAccount_Request result = new RowType_ListCardsByAccount_Request();

		if (filter.getAccount_no() != null) {
			result.setACCOUNT_NO(filter.getAccount_no().toBigInteger());
		}
		if (filter.getCard_acct() != null && !filter.getCard_acct().equals("")) {
			result.setCARD_ACCT(filter.getCard_acct());
		}
		if (filter.getCcy() != null && !filter.getCcy().equals("")) {
			result.setCCY(filter.getCcy());
		}

		return result;
	}

	public static ArrayList<ListCardsItem> getListCardsItem(ListType_Generic inList) throws ParseException {
		ArrayList<ListCardsItem> result = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");

		if (inList != null && inList.getRow() != null) {
			result = new ArrayList<ListCardsItem>();
			RowType_Generic[] row = inList.getRow();
			for (RowType_Generic curRow : row) {
				ListCardsItem addItem = new ListCardsItem();
				ItemType_Generic[] item = curRow.getItem();
				for (ItemType_Generic curItem : item) {
					String name = curItem.getName();
					String value = curItem.getValue();
					if (name.equals("ACCOUNT_NO") && !value.equals("")) {
						addItem.setAccount_no(BigDecimal.valueOf(Long.valueOf(value)));
					} else if (name.equals("CARD_ACCT") && !value.equals("")) {
						addItem.setCard_acct(value);
					} else if (name.equals("CCY") && !value.equals("")) {
						addItem.setCcy(value);
					} else if (name.equals("CARD") && !value.equals("")) {
						addItem.setCard(value);
					} else if (name.equals("BASE_SUPP") && !value.equals("")) {
						addItem.setBase_supp(value);
					} else if (name.equals("STATUS") && !value.equals("")) {
						addItem.setStatus(value);
					} else if (name.equals("STATUS2") && !value.equals("")) {
						addItem.setStatus2(value);
					} else if (name.equals("STOP_CAUSE") && !value.equals("")) {
						addItem.setStop_cause(value);
					} else if (name.equals("EXPIRY") && !value.equals("")) {
						addItem.setExpiry(dateFormat.parse(value));
					} else if (name.equals("EXPIRY2") && !value.equals("")) {
						addItem.setExpiry2(dateFormat.parse(value));
					} else if (name.equals("COND_SET") && !value.equals("")) {
						addItem.setCond_set(value);
					} else if (name.equals("RISK_LEVEL") && !value.equals("")) {
						addItem.setRisk_level(value);
					} else if (name.equals("CLIENT_ID") && !value.equals("")) {
						addItem.setClient_id(value);
					} else if (name.equals("CL_ROLE") && !value.equals("")) {
						addItem.setCl_role(value);
					} else if (name.equals("AGREEMENT_KEY") && !value.equals("")) {
						addItem.setAgreement_key(BigDecimal.valueOf(Long.valueOf(value)));
					} else if (name.equals("CARD_NAME") && !value.equals("")) {
						addItem.setCard_name(value);
					}
				}
				result.add(addItem);
			}
		}

		return result;
	}

	public static CardBalance getBalance(ListType_Generic inList) throws ParseException {
		CardBalance result = new CardBalance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
		if (inList != null) {
			ItemType_Generic[] item = inList.getRow(0).getItem();

			for (ItemType_Generic curItem : item) {
				String name = curItem.getName();
				String value = curItem.getValue();
				if (name.equals("CARD") && !value.equals("")) {
					result.setCard(value);
				} else if (name.equals("EXPIRY1") && !value.equals("")) {
					result.setExpiry1(dateFormat.parse(value));
				} else if (name.equals("CARD_STATUS") && !value.equals("")) {
					result.setCard_status(value);
				} else if (name.equals("ACCOUNT_NO") && !value.equals("")) {
					result.setAccount_no(BigDecimal.valueOf(Long.valueOf(value)));
				} else if (name.equals("CARD_ACCT") && !value.equals("")) {
					result.setCard_acct(value);
				} else if (name.equals("ACC_STATUS") && !value.equals("")) {
					result.setAcc_status(value);
				} else if (name.equals("CCY") && !value.equals("")) {
					result.setCcy(value);
				} else if (name.equals("END_BAL") && !value.equals("")) {
					result.setEnd_bal(BigDecimal.valueOf(Long.valueOf(value)));
				} else if (name.equals("LOCKED_AMOUNT") && !value.equals("")) {
					result.setLocked_amount(BigDecimal.valueOf(Long.valueOf(value)));
				} else if (name.equals("AVAIL_AMOUNT") && !value.equals("")) {
					result.setAvail_amount(BigDecimal.valueOf(Long.valueOf(value)));
				} else if (name.equals("C_ACCNT_TYPE") && !value.equals("")) {
					result.setC_accnt_type(value);
				} else if (name.equals("STOP_CAUSE") && !value.equals("")) {
					result.setStop_cause(value);
				} else if (name.equals("MAIN_ROW") && !value.equals("")) {
					result.setMain_row(BigDecimal.valueOf(Long.valueOf(value)));
				} else if (name.equals("BANK_C") && !value.equals("")) {
					result.setBank_c(value);
				} else if (name.equals("GROUPC") && !value.equals("")) {
					result.setGroupc(value);
				} else if (name.equals("CCY_EXP") && !value.equals("")) {
					result.setCcy_exp(value);
				} else if (name.equals("CRD") && !value.equals("")) {
					result.setCrd(BigDecimal.valueOf(Long.valueOf(value)));
				} else if (name.equals("CRD_EXPIRY") && !value.equals("")) {
					result.setCrd_expiry(dateFormat.parse(value));
				}
			}
		}
		return result;
	}

	public static RowType_AccBalanceQueryByCard_Request getTietoBalanceRequest(CardBalanceFilter filter) {
		RowType_AccBalanceQueryByCard_Request result = new RowType_AccBalanceQueryByCard_Request();

		if (filter.getBank_c() != null && !filter.getBank_c().equals("")) {
			result.setBANK_C(filter.getBank_c());
		}
		if (filter.getGroupc() != null && !filter.getGroupc().equals("")) {
			result.setGROUPC(filter.getGroupc());
		}
		if (filter.getCard() != null && !filter.getCard().equals("")) {
			result.setCARD(filter.getCard());
		}

		return result;
	}

	public static RowType_ExecTransaction_Request getTietoExecTransactionRequest(ExecTransactionRequest filter) {
		RowType_ExecTransaction_Request result = new RowType_ExecTransaction_Request();
		Calendar calendar = Calendar.getInstance();

		if (filter.getPayment_mode() != null && !filter.getPayment_mode().equals("")) {
			result.setPAYMENT_MODE(filter.getPayment_mode());
		}
		if (filter.getAccount_no() != null) {
			result.setACCOUNT_NO(filter.getAccount_no());
		}
		if (filter.getCard_acct() != null && !filter.getCard_acct().equals("")) {
			result.setCARD_ACCT(filter.getCard_acct());
		}
		if (filter.getCard_acct_ccy() != null && !filter.getCard_acct_ccy().equals("")) {
			result.setCARD_ACCT_CCY(filter.getCard_acct_ccy());
		}
		if (filter.getCard() != null && !filter.getCard().equals("")) {
			result.setCARD(filter.getCard());
		}
		if (filter.getExecute_on() != null) {
			calendar.setTime(filter.getExecute_on());
			result.setEXECUTE_ON(calendar);
		}
		if (filter.getTran_type() != null && !filter.getTran_type().equals("")) {
			result.setTRAN_TYPE(filter.getTran_type());
		}
		if (filter.getTran_ccy() != null && !filter.getTran_ccy().equals("")) {
			result.setTRAN_CCY(filter.getTran_ccy());
		}
		if (filter.getTran_amnt() != null) {
			result.setTRAN_AMNT(filter.getTran_amnt());
		}
		if (filter.getBranch() != null && !filter.getBranch().equals("")) {
			result.setBRANCH(filter.getBranch());
		}
		if (filter.getBatch_nr() != null && !filter.getBatch_nr().equals("")) {
			result.setBATCH_NR(filter.getBatch_nr());
		}
		if (filter.getSlip_nr() != null && !filter.getSlip_nr().equals("")) {
			result.setSLIP_NR(filter.getSlip_nr());
		}
		if (filter.getDeal_desc() != null && !filter.getDeal_desc().equals("")) {
			result.setDEAL_DESC(filter.getDeal_desc());
		}
		if (filter.getCounterparty() != null && !filter.getCounterparty().equals("")) {
			result.setCOUNTERPARTY(filter.getCounterparty());
		}
		if (filter.getInternal_no() != null) {
			result.setINTERNAL_NO(filter.getInternal_no());
		}
		if (filter.getBank_c() != null && !filter.getBank_c().equals("")) {
			result.setBANK_C(filter.getBank_c());
		}
		if (filter.getGroupc() != null && !filter.getGroupc().equals("")) {
			result.setGROUPC(filter.getGroupc());
		}
		if (filter.getTran_date_time() != null) {
			calendar.setTime(filter.getTran_date_time());
			result.setTRAN_DATE_TIME(calendar);
		}
		if (filter.getExecution_type() != null) {
			result.setEXECUTION_TYPE(filter.getExecution_type());
		}
		if (filter.getBooking_msg() != null && !filter.getBooking_msg().equals("")) {
			result.setBOOKING_MSG(filter.getBooking_msg());
		}
		if (filter.getTr_code() != null && !filter.getTr_code().equals("")) {
			result.setTR_CODE(filter.getTr_code());
		}
		if (filter.getTr_fee() != null) {
			result.setTR_FEE(filter.getTr_fee());
		}
		if (filter.getTr_code2() != null && !filter.getTr_code2().equals("")) {
			result.setTR_CODE2(filter.getTr_code2());
		}
		if (filter.getTr_fee2() != null) {
			result.setTR_FEE2(filter.getTr_fee2());
		}
		if (filter.getTr_code3() != null && !filter.getTr_code3().equals("")) {
			result.setTR_CODE3(filter.getTr_code3());
		}
		if (filter.getTr_fee3() != null) {
			result.setTR_FEE3(filter.getTr_fee3());
		}
		if (filter.getTr_code4() != null && !filter.getTr_code4().equals("")) {
			result.setTR_CODE4(filter.getTr_code4());
		}
		if (filter.getTr_fee4() != null) {
			result.setTR_FEE4(filter.getTr_fee4());
		}
		if (filter.getTr_code5() != null && !filter.getTr_code5().equals("")) {
			result.setTR_CODE5(filter.getTr_code5());
		}
		if (filter.getTr_fee5() != null) {
			result.setTR_FEE5(filter.getTr_fee5());
		}
		if (filter.getTr_code6() != null && !filter.getTr_code6().equals("")) {
			result.setTR_CODE6(filter.getTr_code6());
		}
		if (filter.getTr_fee6() != null) {
			result.setTR_FEE6(filter.getTr_fee6());
		}
		if (filter.getTr_code7() != null && !filter.getTr_code7().equals("")) {
			result.setTR_CODE7(filter.getTr_code7());
		}
		if (filter.getTr_fee7() != null) {
			result.setTR_FEE7(filter.getTr_fee7());
		}
		if (filter.getTr_code8() != null && !filter.getTr_code8().equals("")) {
			result.setTR_CODE8(filter.getTr_code8());
		}
		if (filter.getTr_fee8() != null) {
			result.setTR_FEE8(filter.getTr_fee8());
		}
		if (filter.getTr_code9() != null && !filter.getTr_code9().equals("")) {
			result.setTR_CODE9(filter.getTr_code9());
		}
		if (filter.getTr_fee9() != null) {
			result.setTR_FEE9(filter.getTr_fee9());
		}
		if (filter.getTr_code10() != null && !filter.getTr_code10().equals("")) {
			result.setTR_CODE10(filter.getTr_code10());
		}
		if (filter.getTr_fee10() != null) {
			result.setTR_FEE10(filter.getTr_fee10());
		}
		if (filter.getCheck_dupl() != null) {
			result.setCHECK_DUPL(filter.getCheck_dupl().toBigInteger());
		}
		if (filter.getInstl_agr_no() != null) {
			result.setINSTL_AGR_NO(filter.getInstl_agr_no());
		}
		if (filter.getAccnt_type() != null) {
			result.setACCNT_TYPE(filter.getAccnt_type().toBigInteger());
		}

		return result;
	}

	public static ExecTransactionResponse getExecTransactionResponse(RowType_ExecTransaction_Response response) {
		ExecTransactionResponse result = new ExecTransactionResponse();

		if (response.getINTERNAL_NO() != null) {
			result.setInternal_no(response.getINTERNAL_NO());
		}

		return result;
	}
}
