
package it.rjcsoft.prv.dto.utente;

import java.util.Date;


public class UtenteUpdateDTO {

    private Integer Id;
    
    private Integer companyId;
	
    private String firstName;
    
    private String lastName;
    
    private String username;
    
    private Date modifiedDate;
	
    private Boolean contactPerson;
    
    private String roleValue;
    
    private String statusValue;

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
    
    

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Boolean getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(Boolean contactPerson) {
        this.contactPerson = contactPerson;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UtenteUpdateDTO{Id=").append(Id);
        sb.append(", companyId=").append(companyId);
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", username=").append(username);
        sb.append(", modifiedDate=").append(modifiedDate);
        sb.append(", contactPerson=").append(contactPerson);
        sb.append(", roleValue=").append(roleValue);
        sb.append(", statusValue=").append(statusValue);
        sb.append('}');
        return sb.toString();
    }
    
}
