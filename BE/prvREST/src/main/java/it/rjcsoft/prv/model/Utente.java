package it.rjcsoft.prv.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;



 @Entity
 @Table(name="users")
public class Utente {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Integer id;
    
    
    @Column(name="company_id")
    private Integer companyId;
    
    @Column(name="first_name", nullable =false)
    private String firstName;
    
    @Column(name="last_name", nullable =false)
    private String lastName;
    
    @Column(name="username", nullable=false)
    private String username;
    
    @Column(name="password", nullable=false)
    private String password;
    
  
    @Column(name="email",nullable=false)
    private String email;
    
    @Column(name="status_value")
    private String statusValue;
    
    @Column(name="createddate")
    private Date createdDate;
    
    @Column(name="modifieddate")
    private Date modifiedDate;
    
    @Column(name="role_value")
    private String roleValue;
    
    @Column(name="contact_person")
    private Boolean contactPerson;

    @Column(name="otp")
    private String otp;
    
    @Column(name="expiration_otp")
    private Timestamp expirationOtp;

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Timestamp getExpirationOtp() {
        return expirationOtp;
    }

    public void setExpirationOtp(Timestamp expirationOtp) {
        this.expirationOtp = expirationOtp;
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
        StringBuilder sb = new StringBuilder();
        sb.append("Utente{id=").append(id);
        sb.append(", companyId=").append(companyId);
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", email=").append(email);
        sb.append(", statusValue=").append(statusValue);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", modifiedDate=").append(modifiedDate);
        sb.append(", roleValue=").append(roleValue);
        sb.append(", contactPerson=").append(contactPerson);
        sb.append(", otp=").append(otp);
        sb.append(", expirationOtp=").append(expirationOtp);
        sb.append('}');
        return sb.toString();
    }

    
    
    
    

}
