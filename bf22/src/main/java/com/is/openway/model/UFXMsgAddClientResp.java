package com.is.openway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonRootName(value = "UFXMsg")
public class UFXMsgAddClientResp {

	 @JsonProperty("MsgId")
	 private String msgId;
	 @JsonProperty("Source")
	 private Source SourceObject;
	 @JsonProperty("MsgData")
	 private MsgDataAddClientResp MsgDataObject;
	 @JacksonXmlProperty(isAttribute = true)
	 private String direction;
	 @JsonProperty("msg_type")
	 @JacksonXmlProperty(isAttribute = true)
	 private String msg_type;
	 @JsonProperty("scheme")
	 @JacksonXmlProperty(isAttribute = true)
	 private String scheme;
	 @JsonProperty("version")
	 @JacksonXmlProperty(isAttribute = true)
	 private String version;
	 @JsonProperty("resp_class")
	 @JacksonXmlProperty(isAttribute = true)
  	 private String resp_class;
	 @JsonProperty("resp_code")
	 @JacksonXmlProperty(isAttribute = true)
  	 private String resp_code;
	 @JsonProperty("resp_text")
	 @JacksonXmlProperty(isAttribute = true)
	 private String resp_text;
	 
	 public UFXMsgAddClientResp() {
		 this.direction="Rq";
		 this.msg_type="Application";
		 this.scheme="WAY4Appl";
		 this.version="2.3.81";
		    
	 }

	 // Getter Methods 
	 @JsonProperty("MsgId")
	 public String getMsgId() {
	  return msgId;
	 }
	 @JsonProperty("Source")
	 public Source getSource() {
	  return SourceObject;
	 }

	 @JsonProperty("MsgData")
	 public MsgDataAddClientResp getMsgDataReq() {
	  return MsgDataObject;
	 }

	 public String getDirection() {
	  return direction;
	 }

	 public String getMsg_type() {
	  return msg_type;
	 }

	 public String getScheme() {
	  return scheme;
	 }

	 public String getVersion() {
	  return version;
	 }
	 @JsonProperty("resp_class")
	 public String getResp_class() {
			return resp_class;
		}
	 @JsonProperty("resp_code")
	public String getResp_code() {
		return resp_code;
	}
	 @JsonProperty("resp_text")
	public String getResp_text() {
		return resp_text;
	}

	

	 // Setter Methods 
	 @JsonProperty("MsgId")
	 public void setMsgId(String MsgId) {
	  this.msgId = MsgId;
	 }
	 
	 @JsonProperty("Source")
	 public void setSource(Source SourceObject) {
	  this.SourceObject = SourceObject;
	 }
	 
	 @JsonProperty("MsgData")
	 public void setMsgDataReq(MsgDataAddClientResp MsgDataObject) {
	  this.MsgDataObject = MsgDataObject;
	 }

	 public void setDirection(String direction) {
	  this.direction = direction;
	 }

	 public void setMsg_type(String msg_type) {
	  this.msg_type = msg_type;
	 }

	 public void setScheme(String scheme) {
	  this.scheme = scheme;
	 }

	 public void setVersion(String version) {
	  this.version = version;
	 }
	 @JsonProperty("resp_class")
	 public void setResp_class(String resp_class) {
			this.resp_class = resp_class;
		}
	 @JsonProperty("resp_code")
		public void setResp_code(String resp_code) {
			this.resp_code = resp_code;
		}
	 @JsonProperty("resp_text")
		public void setResp_text(String resp_text) {
			this.resp_text = resp_text;
		}


}
