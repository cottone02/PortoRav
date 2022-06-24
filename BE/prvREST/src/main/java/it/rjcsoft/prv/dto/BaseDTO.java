package it.rjcsoft.prv.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.rjcsoft.prv.enums.ResponseEnum;

public class BaseDTO {
	
	@JsonIgnore
	private ResponseEnum responseEnum;
	
	public BaseDTO(ResponseEnum responseEnum) {
		this.responseEnum = responseEnum;
	}
	
	public BaseDTO() {
		super();
	}

	public ResponseEnum getResponseEnum() {
		return responseEnum;
	}

	public void setResponseEnum(ResponseEnum responseEnum) {
		this.responseEnum = responseEnum;
	}

	@JsonIgnore
	public Integer getCode() {
		if (this.responseEnum != null) {
			return this.responseEnum.getCode();
		}
		return null;
	}

	@JsonIgnore
	public String getDescription() {
		if (this.responseEnum != null) {
			return this.responseEnum.getDescription();
		}
		return null;
	}
}


