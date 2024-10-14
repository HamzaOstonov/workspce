package com.is.tieto_visae.tieto;

import java.math.BigDecimal;

/**
* RESPONSE_CODE			integer	Ответный код операции
* ERROR_DESCRIPTION		string	Описание ошибки
* ERROR_ACTION			string	Действие, необходимое для решения проблемы (в случае ошибки)
* EXTERNAL_SESSION_ID	string	Идентификатор внешней сессии, предоставляемый аппликацией клиента
 */
public class ResponseInfo {
	public BigDecimal getResponse_code() {
		return response_code;
	}
	public void setResponse_code(BigDecimal response_code) {
		this.response_code = response_code;
	}
	public String getError_description() {
		return error_description;
	}
	public void setError_description(String error_description) {
		this.error_description = error_description;
	}
	public String getError_action() {
		return error_action;
	}
	public void setError_action(String error_action) {
		this.error_action = error_action;
	}
	public String getExternal_session_id() {
		return external_session_id;
	}
	public void setExternal_session_id(String external_session_id) {
		this.external_session_id = external_session_id;
	}
	public ResponseInfo() {
		super();
	}
	public ResponseInfo(BigDecimal response_code, String error_description, String error_action,
			String external_session_id) {
		super();
		this.response_code = response_code;
		this.error_description = error_description;
		this.error_action = error_action;
		this.external_session_id = external_session_id;
	}
	private BigDecimal response_code;
	private String error_description;
	private String error_action;
	private String external_session_id;

}
