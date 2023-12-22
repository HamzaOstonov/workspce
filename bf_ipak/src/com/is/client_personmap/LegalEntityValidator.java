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
			message = "Введите наименование";
			return false;
		}
		
		if(fullCheck && CheckNull.isEmpty(legal.getCode_country())) {
			message = "Введите страну регистрации";
			return false;
		}
		if(fullCheck && CheckNull.isEmpty(legal.getCode_resident())) {
			message = "Введите резидентность";
			return false;
		}
		if(fullCheck && CheckNull.isEmpty(legal.getNumber_tax_registration())) {
			message = "Введите ИНН";
			return false;
		}
		
		if(legal.getNumber_tax_registration() != null ){
			if((legal.getCode_country() != null && legal.getCode_country().equals("860")) || (legal.getCode_resident() != null && legal.getCode_resident().equalsIgnoreCase("1"))) {
				if(legal.getNumber_tax_registration().length()!=9){
					message = "Длина поля ИНН должна быть равна 9 символов для резидентов РУз";
					return false;
				}
				if(legal.getNumber_tax_registration()!=null && legal.getNumber_tax_registration().charAt(0)!='2'&&
																	legal.getNumber_tax_registration().charAt(0)!='3'){
					message = "ИНН юридического лица должно начинаться на 2 или 3";
					return false;
				}
			} else {
				if(legal.getNumber_tax_registration().length() > 20){
					message = "Длина поля ИНН должна быть не больше 20 символов для нерезидентов РУз";
					return false;
				}
			}
		}
		if(legal.getCode_head_organization() != null
				&& !legal.getCode_head_organization().equals("0")
				&& legal.getCode_head_organization().length()!=8){
			message = "код головного предприятия может быть - 0, или 8 знаков";
			return false;
		}
		if(legal.getCode_head_organization() != null && legal.getInn_head_organization() != null &&
				((legal.getCode_head_organization().equals("0")&&
						!legal.getInn_head_organization().equals("0"))
						||
				(!legal.getCode_head_organization().equals("0")&&
				legal.getInn_head_organization().length()!=9))){
			
			message = "ИНН головного предприятия должен быть - 0, если код головного предприятия 0, иначе 9 знаков(цифровых)";
			return false;
		}
		if(legal.getOkpo()!=null && legal.getOkpo().length()>10){
			message = "Длина поля Код юридического лица не должна превышать 10 символов";
			return false;
		}
		
		if(legal.getFax()!=null && legal.getFax().length()>12){
			message = "Длина поля Факс не должна превышать 12 символов";
			return false;
		}
		if(legal.getEmail()!=null && legal.getEmail().length()>40){
			message = "Длина поля Почта не должна превышать 40 символов";
			return false;
		}
		if(!CheckNull.isEmpty(legal.getPhone()) && !isPhoneCorrect(legal.getPhone())){
			message = "Не верный формат номера телефона - ввод только 12 цифр (998....)";
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
