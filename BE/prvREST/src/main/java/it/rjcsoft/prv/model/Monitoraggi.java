package it.rjcsoft.prv.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.locationtech.jts.geom.Point;

import it.rjcsoft.prv.utils.PointDeserializer;
import it.rjcsoft.prv.utils.PointSerializer;

@Entity(name = "monitoring")
@Table(name = "monitoring")
public class Monitoraggi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "date")
    // @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Timestamp date;
    @Column(name = "place")
    private String place;
    @Column(name = "performed_by")
    private String performedBy;
    @Column(name = "description")
    private String description;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "company_id")
    private Integer companyId;
    @Column(name = "geometry")
    @JsonDeserialize(using = PointDeserializer.class)
    @JsonSerialize(using = PointSerializer.class)
    private Point geometry;

    @OneToMany(targetEntity = FileMonitoraggio.class, cascade = { CascadeType.REMOVE }, orphanRemoval = true)
    @JoinColumn(name = "id_monitoring", referencedColumnName = "id")
    private List<FileMonitoraggio> fileMonitoraggi;

    public List<FileMonitoraggio> getFileMonitoraggi() {
        return fileMonitoraggi;
    }

    public void setFileMonitoraggi(List<FileMonitoraggio> fileMonitoraggi) {
        this.fileMonitoraggi = fileMonitoraggi;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Monitoraggi{id=").append(id);
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
