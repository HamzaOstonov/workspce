package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "UFXMsg")
public class UFXMsgReqClientResp {
	 @JsonProperty("MsgId")
	 private String msgId;
	 @JsonProperty("Source")
	 private Source SourceObject;
	 @JsonProperty("MsgData")
	 private MsgDataReq MsgDataObject;
	 @JsonProperty("direction")
	 private String _direction;
	 @JsonProperty("msg_type")
	 private String _msg_type;
	 @JsonProperty("scheme")
	 private String _scheme;
	 @JsonProperty("version")
	 private String _version;
	 @JsonProperty("resp_class")
	 private String _resp_class;
	 @JsonProperty("resp_code")
	 private String _resp_code;
	 @JsonProperty("resp_text")
	 private String _resp_text;


	 // Getter Methods 
	 @JsonProperty("MsgId")
	 public String getMsgId() {
	  return msgId;
	 }

	 public Source getSource() {
	  return SourceObject;
	 }

	 public MsgDataReq getMsgData() {
	  return MsgDataObject;
	 }

	 public String get_direction() {
	  return _direction;
	 }

	 public String get_msg_type() {
	  return _msg_type;
	 }

	 public String get_scheme() {
	  return _scheme;
	 }

	 public String get_version() {
	  return _version;
	 }

	 public String get_resp_class() {
	  return _resp_class;
	 }

	 public String get_resp_code() {
	  return _resp_code;
	 }

	 public String get_resp_text() {
	  return _resp_text;
	 }

	 // Setter Methods 
	 @JsonProperty("MsgId")
	 public void setMsgId(String MsgId) {
	  this.msgId = MsgId;
	 }

	 public void setSource(Source SourceObject) {
	  this.SourceObject = SourceObject;
	 }

	 public void setMsgData(MsgDataReq MsgDataObject) {
	  this.MsgDataObject = MsgDataObject;
	 }

	 public void set_direction(String _direction) {
	  this._direction = _direction;
	 }

	 public void set_msg_type(String _msg_type) {
	  this._msg_type = _msg_type;
	 }

	 public void set_scheme(String _scheme) {
	  this._scheme = _scheme;
	 }

	 public void set_version(String _version) {
	  this._version = _version;
	 }

	 public void set_resp_class(String _resp_class) {
	  this._resp_class = _resp_class;
	 }

	 public void set_resp_code(String _resp_code) {
	  this._resp_code = _resp_code;
	 }

	 public void set_resp_text(String _resp_text) {
	  this._resp_text = _resp_text;
	 }
	}