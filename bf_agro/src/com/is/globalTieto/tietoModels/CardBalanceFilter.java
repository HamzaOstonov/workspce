package com.is.globalTieto.tietoModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* CARD		string(19)	����� ����� (PAN) 
* BANK_C	string(2)	��� ����� � ��������� ������
* GROUPC	string(2)	��� ������ ���� � ��������� ��� ������� �����
*/

@NoArgsConstructor
@AllArgsConstructor
public class CardBalanceFilter {
	@Getter
	@Setter
	private String card;
	@Getter
	@Setter
	private String bank_c;
	@Getter
	@Setter
	private String groupc;
}
