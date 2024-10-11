package com.is.globalTieto.tietoModels;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author D
 * STATUS1 String(1) ������ �����. ��� STATUS1 �� ������ �� ������ �����. ��� ���������� addInfo4Agreement.
 * STATUS2 String(1) ������ ����� � �������������
 * STOP_CAUSE String(1) ������� ��������� �����
 * EXPIRY1 dateTime ���� ����������� ����� �������� �����
 * EXPIRITY2 dateTime ���� ����������� ����� �������� �������������� �����
 * RENEW String(1) ������� ���������� ����� (�� ��������� 0)
 * CARD_NAME String(27) ��� ��������� ����� �� �����
 * MC_NAME String(26) ��� ��������� ����� �������������� �� ��������� �������
 * RENEWED_CARD String(19) ���������� ����� �����
 * DESIGN_ID Decimal(10,0) ������������� ������� ����
 * INSTANT String(10) ��� ������� Instant ��������.
 */
@NoArgsConstructor
@AllArgsConstructor
public class PhysicalCard {
	
	@Getter
	@Setter
	private String STATUS1;
	@Getter
	@Setter
	private String STATUS2;
	@Getter
	@Setter
	private String STOP_CAUSE;
	@Getter
	@Setter
	private Date EXPIRY1;
	@Getter
	@Setter
	private Date EXPIRITY2;
	@Getter
	@Setter
	private String RENEW;
	@Getter
	@Setter
	private String CARD_NAME;
	@Getter
	@Setter
	private String MC_NAME;
	@Getter
	@Setter
	private String RENEWED_CARD;
	@Getter
	@Setter
	private BigDecimal DESIGN_ID;
	@Getter
	@Setter
	private String INSTANT;
	
}
