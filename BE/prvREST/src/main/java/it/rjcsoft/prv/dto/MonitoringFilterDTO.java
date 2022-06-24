package it.rjcsoft.prv.dto;

import java.util.Date;

import org.locationtech.jts.geom.Geometry;
import org.springframework.format.annotation.DateTimeFormat;

public class MonitoringFilterDTO {

	private Integer id;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date startDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date endDate;

	private String place;
	private String performedBy;

	private Integer companyId;

	private Geometry geometry;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getPerformedBy() {
		return performedBy;
	}

	public void setPerformedBy(String performedBy) {
		this.performedBy = performedBy;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MonitoringFilterDTO [id=");
		builder.append(id);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", place=");
		builder.append(place);
		builder.append(", performedBy=");
		builder.append(performedBy);
		builder.append(", companyId=");
		builder.append(companyId);
		builder.append(", geometry=");
		builder.append(geometry);
		builder.append("]");
		return builder.toString();
	}

}
