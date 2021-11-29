package com.camposoft.dto;

public class GenericRespuesta {

	private int status;
	private String title;
	private String code;
	private String detail;

	public GenericRespuesta(int status, String title, String code, String detail) {
		this.status = status;
		this.title = title;
		this.code = code;
		this.detail = detail;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
