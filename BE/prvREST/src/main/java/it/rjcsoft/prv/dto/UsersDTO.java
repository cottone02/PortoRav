package it.rjcsoft.prv.dto;

import java.util.List;

import it.rjcsoft.prv.model.User;

public class UsersDTO extends BaseDTO {

	private Integer rowCounter;
	
	private List<User> users;

	public Integer getRowCounter() {
		return rowCounter;
	}

	public void setRowCounter(Integer rowCounter) {
		this.rowCounter = rowCounter;
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
		builder.append("UsersDTO [rowCounter=");
		builder.append(rowCounter);
		builder.append(", users=");
		builder.append(users);
		builder.append("]");
		return builder.toString();
	}
	
}
