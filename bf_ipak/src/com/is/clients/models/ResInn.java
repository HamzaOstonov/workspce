package com.is.clients.models;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "code", "message" })
@Generated("jsonschema2pojo")
public class ResInn {

	@JsonProperty("code")
	private String code;
	@JsonProperty("message")
	private String message;
	@JsonProperty("client")
	private String client;
	@JsonProperty("client_type")
	private String client_type;
	@JsonProperty("client state")
	private String client_state;
	@JsonProperty("account_state")
	private String account_state;
	@JsonProperty("name")
	private String name;
	@JsonProperty("opened")
	private String opened;
	@JsonProperty("closed")
	private String closed;

	@JsonProperty("code")
	public String getCode() {
		return code;
	}

	@JsonProperty("code")
	public void setCode(String code) {
		this.code = code;
	}

	@JsonProperty("message")
	public String getMessage() {
		return message;
	}

	@JsonProperty("message")
	public void setMessage(String message) {
		this.message = message;
	}

	@JsonProperty("client")
	public String getClient() {
		return client;
	}

	@JsonProperty("client")
	public void setClient(String client) {
		this.client = client;
	}

	@JsonProperty("client_type")
	public String getClient_type() {
		return client_type;
	}

	@JsonProperty("client_type")
	public void setClient_type(String client_type) {
		this.client_type = client_type;
	}

	@JsonProperty("client state")
	public String getClient_state() {
		return client_state;
	}

	@JsonProperty("client state")
	public void setClient_state(String client_state) {
		this.client_state = client_state;
	}

	@JsonProperty("account_state")
	public String getAccount_state() {
		return account_state;
	}

	@JsonProperty("account_state")
	public void setAccount_state(String account_state) {
		this.account_state = account_state;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("opened")
	public String getOpened() {
		return opened;
	}
	
	@JsonProperty("opened")
	public void setOpened(String opened) {
		this.opened = opened;
	}

	@JsonProperty("closed")
	public String getClosed() {
		return closed;
	}

	@JsonProperty("closed")
	public void setClosed(String closed) {
		this.closed = closed;
	}

	@Override
	public String toString() {
		return  "Код: " + code + " Cообщение: " + message + "\n"
				+ "Клиент: " + client + "\n"
				+ "Тип клиента: " + client_type +"\n"
				+ "Статус клиента: " + client_state + "\n"
				+ "Состояние счета: " + account_state + "\n"
				+ "Наименование: " + name + "/n"
				+ "Дата открытие: " + opened + "/n"
				+ "Дата закрытие: " + closed;
	}

}
