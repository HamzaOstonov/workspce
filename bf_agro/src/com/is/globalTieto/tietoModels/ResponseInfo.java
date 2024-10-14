package com.is.globalTieto.tietoModels;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* RESPONSE_CODE			integer	�������� ��� ��������
* ERROR_DESCRIPTION		string	�������� ������
* ERROR_ACTION			string	��������, ����������� ��� ������� �������� (� ������ ������)
* EXTERNAL_SESSION_ID	string	������������� ������� ������, ��������������� ����������� �������
 */
@NoArgsConstructor
@AllArgsConstructor
public class ResponseInfo {
	@Getter
	@Setter
	private BigDecimal response_code;
	@Getter
	@Setter
	private String error_description;
	@Getter
	@Setter
	private String error_action;
	@Getter
	@Setter
	private String external_session_id;

}
