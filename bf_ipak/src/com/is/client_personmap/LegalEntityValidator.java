package com.is.client_personmap;

import java.sql.CallableStatement;
import java.sql.Connection;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.base.Validator;
import com.is.base.utils.DbUtils;
import com.is.base.utils.Util;
import com.is.client_personmap.model.LegalEntity;
import com.is.utils.CheckNull;

public class LegalEntityValidator extends Validator<LegalEntity>{
	private boolean fullCheck;
	private LegalEntityValidator(String alias, boolean fullCheck) {
		this.alias = alias;
		this.fullCheck = fullCheck;
	}
	
	public static LegalEntityValidator instance(String alias, boolean fullCheck) {
		return new LegalEntityValidator(alias, fullCheck);
	}
	
	@Override
	public boolean isValid(LegalEntity legal){
		
		if(CheckNull.isEmpty(legal.getName())) {
			message = "������� ������������";
			return false;
		}
		
		if(fullCheck && CheckNull.isEmpty(legal.getCode_country())) {
			message = "������� ������ �����������";
			return false;
		}
		if(fullCheck && CheckNull.isEmpty(legal.getCode_resident())) {
			message = "������� �������������";
			return false;
		}
		if(fullCheck && CheckNull.isEmpty(legal.getNumber_tax_registration())) {
			message = "������� ���";
			return false;
		}
		
		if(legal.getNumber_tax_registration() != null ){
			if((legal.getCode_country() != null && legal.getCode_country().equals("860")) || (legal.getCode_resident() != null && legal.getCode_resident().equalsIgnoreCase("1"))) {
				if(legal.getNumber_tax_registration().length()!=9){
					message = "����� ���� ��� ������ ���� ����� 9 �������� ��� ���������� ���";
					return false;
				}
				if(legal.getNumber_tax_registration()!=null && legal.getNumber_tax_registration().charAt(0)!='2'&&
																	legal.getNumber_tax_registration().charAt(0)!='3'){
					message = "��� ������������ ���� ������ ���������� �� 2 ��� 3";
					return false;
				}
			} else {
				if(legal.getNumber_tax_registration().length() > 20){
					message = "����� ���� ��� ������ ���� �� ������ 20 �������� ��� ������������ ���";
					return false;
				}
			}
		}
		if(legal.getCode_head_organization() != null
				&& !legal.getCode_head_organization().equals("0")
				&& legal.getCode_head_organization().length()!=8){
			message = "��� ��������� ����������� ����� ���� - 0, ��� 8 ������";
			return false;
		}
		if(legal.getCode_head_organization() != null && legal.getInn_head_organization() != null &&
				((legal.getCode_head_organization().equals("0")&&
						!legal.getInn_head_organization().equals("0"))
						||
				(!legal.getCode_head_organization().equals("0")&&
				legal.getInn_head_organization().length()!=9))){
			
			message = "��� ��������� ����������� ������ ���� - 0, ���� ��� ��������� ����������� 0, ����� 9 ������(��������)";
			return false;
		}
		if(legal.getOkpo()!=null && legal.getOkpo().length()>10){
			message = "����� ���� ��� ������������ ���� �� ������ ��������� 10 ��������";
			return false;
		}
		
		if(legal.getFax()!=null && legal.getFax().length()>12){
			message = "����� ���� ���� �� ������ ��������� 12 ��������";
			return false;
		}
		if(legal.getEmail()!=null && legal.getEmail().length()>40){
			message = "����� ���� ����� �� ������ ��������� 40 ��������";
			return false;
		}
		if(!CheckNull.isEmpty(legal.getPhone()) && !isPhoneCorrect(legal.getPhone())){
			message = "�� ������ ������ ������ �������� - ���� ������ 12 ���� (998....)";
			return false;
		}
		if (!checkInStopList(legal)) {
			return false;
		}
		return true;
	}
	
	private boolean isPhoneCorrect(String phone ) {
		return phone.startsWith("998") 
			&& phone.length() == 12 
			&& Util.isDigit(phone);
	}
	
	private boolean checkInStopList(LegalEntity legal) {
		Connection c = null;
		CallableStatement cs = null;
		boolean ok = true;
		try {
			c = ConnectionPool.getConnection(alias);
			cs = c.prepareCall("{call stoplist.reaction(?,?,?,?,?,?,?,?,?)}");
			cs.setString(1, legal.getBranch());
			cs.setString(2, "");
			cs.setString(3, legal.getName());
			cs.setString(4, "");
			cs.setString(5, "");
			cs.setString(6, "");
			cs.setString(7, "");
			cs.setString(8, "");
			cs.setString(9, "");
			//ISLogger.getLogger().error("legalValidation checkInStopList before exectute");
			cs.execute();
			//ISLogger.getLogger().error("legalValidation checkInStopList after exectute");
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			message = e.getMessage();
			ok = false;
		} finally {
			DbUtils.closeStmt(cs);
			ConnectionPool.close(c);
		}
		return ok;
	}
}
