/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.rjcsoft.prv.dto.utente;

import java.util.Date;

/**
 *
 * @author andre
 */
public class UtenteUserUpdateDTO {
    
    private Integer Id;
	
    private String firstName;
    
    private String lastName;
    
    private String username;
    
    private Date modifiedDate;
	
    private Boolean contactPerson;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UtenteUserUpdateDTO{Id=").append(Id);
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", username=").append(username);
        sb.append(", modifiedDate=").append(modifiedDate);
        sb.append(", contactPerson=").append(contactPerson);
        sb.append('}');
        return sb.toString();
    }

    
    
    
    
}
