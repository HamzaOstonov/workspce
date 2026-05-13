package com.is.globalTieto.tietoModels;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* RESPONSE_CODE			integer	Ответный код операции
* ERROR_DESCRIPTION		string	Описание ошибки
* ERROR_ACTION			string	Действие, необходимое для решения проблемы (в случае ошибки)
* EXTERNAL_SESSION_ID	string	Идентификатор внешней сессии, предоставляемый аппликацией клиента
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
