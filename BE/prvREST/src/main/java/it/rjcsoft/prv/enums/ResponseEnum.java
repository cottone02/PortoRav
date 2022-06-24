package it.rjcsoft.prv.enums;

public enum ResponseEnum {

	OK(200, "OK"), 
	DELETED(200, "DELETE SUCCESSFULLY COMPLETED"),
	SERVER_ERROR(500, "SERVER ERROR - Database communication error"), 
	NOT_FOUND(204, "NOT FOUND"),
	ID_NOT_FOUND(404, "NOT FOUND - Impossible to create a foreign key with a not existing pk"),
	INVALID_DATE(403, "FORBIDDEN - Invalid date"), 
	USER_EXISTS(403, "Username already exists"),
	PROTECTED_PROPERTY(403, "Try to update a protected property"), 
	EMAIL_EXISTS(403, "Email already exists"),
	MAIL_ERROR(503, "Error sending email"), 
	MAIL_ERROR_ADMIN(503, "Error sending email to admins"), 
	OTP_EXPIRED(400, "OTP is no more available, try again"),
	COMPANY_EXISTS(403, "Company already exists"),
	NOT_DELETED(400, "NOT DELETED"),
	EMAIL_NOT_FOUND(404, "EMAIL NOT FOUND"), 
	INACTIVE_ACCOUNT(403, "INACTIVE ACCOUNT"),
	NO_COMPANY(403, "NO COMPANY ASSOCIATED"),
	COMPANY_NOT_MODIFIED(406, "Company name not valid");

	Integer code;
	String description;

	ResponseEnum(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	void setCode(Integer code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return description+"["+code+"]";
	}

}
