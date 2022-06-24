package it.rjcsoft.prv.dto;

import java.util.List;

import it.rjcsoft.prv.model.User;

public class UserDTO extends BaseDTO{
	
	private Integer userId;
	
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private String roleValue;
	private String statusValue;
	
	private Integer companyId;
	
	private Boolean contactPerson;
	
	private User user;
	private List<User> users;
	
	public UserDTO() {
		super();
	}

	public UserDTO(Integer userId, String firstName, String lastName, String email) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public UserDTO(Integer userId, String firstName, String lastName, String email, String username) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
	}

	public UserDTO(Integer userId, String firstName, String lastName, String username, String password, String email,
			String role) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.roleValue = role;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getRoleValue() {
		return roleValue;
	}

	public void setRoleValue(String roleValue) {
		this.roleValue = roleValue;
	}

	public String getStatusValue() {
		return statusValue;
	}

	public void setStatusValue(String statusValue) {
		this.statusValue = statusValue;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Boolean getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(Boolean contactPerson) {
		this.contactPerson = contactPerson;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserDTO [userId=");
		builder.append(userId);
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
		builder.append(", roleValue=");
		builder.append(roleValue);
		builder.append(", statusValue=");
		builder.append(statusValue);
		builder.append(", companyId=");
		builder.append(companyId);
		builder.append(", contactPerson=");
		builder.append(contactPerson);
		builder.append(", user=");
		builder.append(user);
		builder.append(", users=");
		builder.append(users);
		builder.append("]");
		return builder.toString();
	}

}
