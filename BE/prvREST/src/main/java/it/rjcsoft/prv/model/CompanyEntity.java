package it.rjcsoft.prv.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.collections.CollectionUtils;
import org.apache.tomcat.util.buf.StringUtils;

@Entity(name = "companies")
@Table(name = "companies")
public class CompanyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(sequenceName = "companies_id_seq", allocationSize = 1, name = "companies_id_seq")
	@Column(name = "id", nullable = false)
	private Integer Id;

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Column(name = "vat_number", nullable = true, length = 50)
	private String vatNumber;

	@Column(name = "address", nullable = true, length = 100)
	private String address;

	@Column(name = "business_name", nullable = true, length = 50)
	private String businessName;

	@Column(name = "city", nullable = true, length = 50)
	private String city;

	@OneToMany(cascade = { CascadeType.REFRESH }, targetEntity = UserEntity.class)
	@JoinColumn(name = "company_id", referencedColumnName = "id")
	private List<UserEntity> dipendenti;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
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

	public List<UserEntity> getDipendenti() {
		return dipendenti;
	}

	public void setDipendenti(List<UserEntity> dipendenti) {
		this.dipendenti = dipendenti;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompanyEntity [Id=");
		builder.append(Id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", vatNumber=");
		builder.append(vatNumber);
		builder.append(", address=");
		builder.append(address);
		builder.append(", businessName=");
		builder.append(businessName);
		if (CollectionUtils.isNotEmpty(dipendenti)) {
			builder.append(", dipendenti=");
			builder.append(
					StringUtils.join(dipendenti.stream().map(UserEntity::getEmail).collect(Collectors.toList())));
		}
		builder.append(", city=");
		builder.append(city);
		builder.append("]");
		return builder.toString();
	}

}
