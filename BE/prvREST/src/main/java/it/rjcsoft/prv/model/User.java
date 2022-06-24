package it.rjcsoft.prv.model;

import java.util.Date;

import it.rjcsoft.prv.dto.UserDTO;

public class User {

	private Integer id;

	private Integer companyId;
	
	private String firstName;

	private String lastName;

	private String username;

	private String password;

	private String email;

	private String statusValue;

	private Date createdDate;

	private Date modifiedDate;

	private String roleValue;
	
	private Boolean contactPerson;

	public UserDTO toUserDTO() {
		UserDTO userDto = new UserDTO(this.id, this.firstName, this.lastName, this.email, this.username);
		userDto.setRoleValue(this.roleValue);
		return userDto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatusValue() {
		return statusValue;
	}

	public void setStatusValue(String statusValue) {
		this.statusValue = statusValue;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getRoleValue() {
		return roleValue;
	}

	public void setRoleValue(String roleValue) {
		this.roleValue = roleValue;
	}

	public Boolean getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(Boolean contactPerson) {
		this.contactPerson = contactPerson;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", companyId=");
		builder.append(companyId);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", email=");
		builder.append(email);
		builder.append(", statusValue=");
		builder.append(statusValue);
		builder.append(", createdDate=");
		builder.append(createdDate);
		builder.append(", modifiedDate=");
		builder.append(modifiedDate);
		builder.append(", roleValue=");
		builder.append(roleValue);
		builder.append(", contactPerson=");
		builder.append(contactPerson);
		builder.append("]");
		return builder.toString();
	}

}
