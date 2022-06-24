
package it.rjcsoft.prv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="companies")
public class Azienda {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @SequenceGenerator(name="id")
	private Integer id;
    @Column(name="name")
	private String name;
    @Column(name="vat_number")
	private String vatNumber;
    @Column(name="address")
	private String address;
    @Column(name="business_name")
	private String businessName;
    @Column(name="city")
	private String city;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Azienda{id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", vatNumber=").append(vatNumber);
        sb.append(", address=").append(address);
        sb.append(", businessName=").append(businessName);
        sb.append(", city=").append(city);
        sb.append('}');
        return sb.toString();
    }

    
}
