package com.is.openwayj.model;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class ObjectForUpdateSMS {

  @JsonProperty("datas")
    private List<DataUpdateSMS> datas;

  @JsonProperty("datas")
  public List<DataUpdateSMS> getDatas() {
	return datas;
  }
  
  @JsonProperty("datas")
  public void setDatas(List<DataUpdateSMS> datas) {
	this.datas = datas;
  }

  
}
