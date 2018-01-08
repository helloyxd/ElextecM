package com.elextec.bi.common.entity;

import com.elextec.bi.common.entity.constant.ResponseCodeEnum;

import java.util.List;
import java.util.Map;

public class ReportVoResponse {
	private Integer code;
	private Boolean success;
	private String message;
	private String title;
	private List<Map<String,Object>> colConf;
	private Object data;

	public ReportVoResponse(){
		this.setCode(ResponseCodeEnum.CodeSuccess);
		this.setSuccess(Boolean.TRUE);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Map<String, Object>> getColConf() {
		return colConf;
	}

	public void setColConf(List<Map<String, Object>> colConf) {
		this.colConf = colConf;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
	public void setNull(ReportVoResponse voRes){
		voRes.setCode(ResponseCodeEnum.CodeNullException);
		voRes.setSuccess(false);
	}
	public void setFail(ReportVoResponse voRes){
		voRes.setCode(ResponseCodeEnum.CodeFail);
		voRes.setSuccess(false);
	}
}
