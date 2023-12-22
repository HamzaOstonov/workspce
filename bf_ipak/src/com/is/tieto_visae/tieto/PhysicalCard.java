package com.is.tieto_visae.tieto;

import java.math.BigDecimal;
import java.util.Date;


/**
 * @author D
 * STATUS1 String(1) Статус карты. Тег STATUS1 не влияет на статус карты. При исполнении addInfo4Agreement.
 * STATUS2 String(1) Статус карты – возобновление
 * STOP_CAUSE String(1) Причина остановки карты
 * EXPIRY1 dateTime Дата прекращения срока действия карты
 * EXPIRITY2 dateTime Дата прекращения срока действия возобновленной карты
 * RENEW String(1) Признак обновления карты (по умолчанию 0)
 * CARD_NAME String(27) Имя держателя карты на карте
 * MC_NAME String(26) Имя держателя карты закодированное на магнитной дорожке
 * RENEWED_CARD String(19) Предыдущий номер карты
 * DESIGN_ID Decimal(10,0) Идентификатор дизайна карт
 * INSTANT String(10) Код профиля Instant продукта.
 */

public class PhysicalCard {
	
	
	
	private String STATUS1;
	
	
	private String STATUS2;
	
	
	private String STOP_CAUSE;
	
	
	private Date EXPIRY1;
	
	
	private Date EXPIRITY2;
	
	
	private String RENEW;
	
	
	private String CARD_NAME;
	
	
	private String MC_NAME;
	
	
	private String RENEWED_CARD;
	
	
	private BigDecimal DESIGN_ID;
	
	
	public String getSTATUS1() {
		return STATUS1;
	}


	public void setSTATUS1(String sTATUS1) {
		STATUS1 = sTATUS1;
	}


	public String getSTATUS2() {
		return STATUS2;
	}


	public void setSTATUS2(String sTATUS2) {
		STATUS2 = sTATUS2;
	}


	public String getSTOP_CAUSE() {
		return STOP_CAUSE;
	}


	public void setSTOP_CAUSE(String sTOP_CAUSE) {
		STOP_CAUSE = sTOP_CAUSE;
	}


	public Date getEXPIRY1() {
		return EXPIRY1;
	}


	public void setEXPIRY1(Date eXPIRY1) {
		EXPIRY1 = eXPIRY1;
	}


	public Date getEXPIRITY2() {
		return EXPIRITY2;
	}


	public void setEXPIRITY2(Date eXPIRITY2) {
		EXPIRITY2 = eXPIRITY2;
	}


	public String getRENEW() {
		return RENEW;
	}


	public void setRENEW(String rENEW) {
		RENEW = rENEW;
	}


	public String getCARD_NAME() {
		return CARD_NAME;
	}


	public void setCARD_NAME(String cARD_NAME) {
		CARD_NAME = cARD_NAME;
	}


	public String getMC_NAME() {
		return MC_NAME;
	}


	public void setMC_NAME(String mC_NAME) {
		MC_NAME = mC_NAME;
	}


	public String getRENEWED_CARD() {
		return RENEWED_CARD;
	}


	public void setRENEWED_CARD(String rENEWED_CARD) {
		RENEWED_CARD = rENEWED_CARD;
	}


	public BigDecimal getDESIGN_ID() {
		return DESIGN_ID;
	}


	public void setDESIGN_ID(BigDecimal dESIGN_ID) {
		DESIGN_ID = dESIGN_ID;
	}


	public String getINSTANT() {
		return INSTANT;
	}


	public void setINSTANT(String iNSTANT) {
		INSTANT = iNSTANT;
	}


	public PhysicalCard(String sTATUS1, String sTATUS2, String sTOP_CAUSE,
			Date eXPIRY1, Date eXPIRITY2, String rENEW, String cARD_NAME,
			String mC_NAME, String rENEWED_CARD, BigDecimal dESIGN_ID,
			String iNSTANT) {
		super();
		STATUS1 = sTATUS1;
		STATUS2 = sTATUS2;
		STOP_CAUSE = sTOP_CAUSE;
		EXPIRY1 = eXPIRY1;
		EXPIRITY2 = eXPIRITY2;
		RENEW = rENEW;
		CARD_NAME = cARD_NAME;
		MC_NAME = mC_NAME;
		RENEWED_CARD = rENEWED_CARD;
		DESIGN_ID = dESIGN_ID;
		INSTANT = iNSTANT;
	}


	public PhysicalCard() {
		super();
	}


	private String INSTANT;
	
}
