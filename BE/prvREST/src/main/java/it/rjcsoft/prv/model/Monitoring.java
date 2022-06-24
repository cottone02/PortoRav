package it.rjcsoft.prv.model;

import java.util.Date;

import org.locationtech.jts.geom.Point;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import it.rjcsoft.prv.utils.PointSerializer;

public class Monitoring {
	
	private Integer id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date date;
	
	private String place;
	private String performedBy;
	private String companyName;
	private String description;
	
	private Integer userId;
	private Integer companyId;
	private Integer numFile;
	
	@JsonSerialize(using = PointSerializer.class)
	private Point geometry;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getNumFile() {
		return numFile;
	}

	public void setNumFile(Integer numFile) {
		this.numFile = numFile;
	}

	public Point getGeometry() {
		return geometry;
	}

	public void setGeometry(Point geometry) {
		this.geometry = geometry;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Monitoring [id=");
		builder.append(id);
		builder.append(", date=");
		builder.append(date);
		builder.append(", place=");
		builder.append(place);
		builder.append(", performedBy=");
		builder.append(performedBy);
		builder.append(", companyName=");
		builder.append(companyName);
		builder.append(", description=");
		builder.append(description);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", companyId=");
		builder.append(companyId);
		builder.append(", numFile=");
		builder.append(numFile);
		builder.append(", geometry=");
		builder.append(geometry);
		builder.append("]");
		return builder.toString();
	}
}
