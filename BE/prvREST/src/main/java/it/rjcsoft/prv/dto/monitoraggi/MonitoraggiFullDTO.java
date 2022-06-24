package it.rjcsoft.prv.dto.monitoraggi;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.locationtech.jts.geom.Point;

import it.rjcsoft.prv.model.FileMonitoraggio;
import it.rjcsoft.prv.utils.PointDeserializer;
import it.rjcsoft.prv.utils.PointSerializer;

public class MonitoraggiFullDTO {

    private Integer id;

    // @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Timestamp date;

    private String place;

    private String performedBy;

    private String description;

    private Integer userId;

    private Integer companyId;

    @JsonDeserialize(using = PointDeserializer.class)
    @JsonSerialize(using = PointSerializer.class)
    private Point geometry;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private List<FileMonitoraggio> fileMonitoraggi;

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Point getGeometry() {
        return geometry;
    }

    public void setGeometry(Point geometry) {
        this.geometry = geometry;
    }

    public List<FileMonitoraggio> getFileMonitoraggi() {
        return fileMonitoraggi;
    }

    public void setFileMonitoraggi(List<FileMonitoraggio> fileMonitoraggi) {
        this.fileMonitoraggi = fileMonitoraggi;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MonitoraggiFullDTO{id=").append(id);
        sb.append(", date=").append(date);
        sb.append(", place=").append(place);
        sb.append(", performedBy=").append(performedBy);
        sb.append(", description=").append(description);
        sb.append(", userId=").append(userId);
        sb.append(", companyId=").append(companyId);
        sb.append(", geometry=").append(geometry);
        sb.append('}');
        return sb.toString();
    }

}
