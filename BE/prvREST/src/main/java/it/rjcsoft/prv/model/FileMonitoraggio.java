
package it.rjcsoft.prv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="monitoring_files")
@Table(name="monitoring_files")
public class FileMonitoraggio {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    
    @Column(name="id_monitoring")
    private Integer idMonitoring;
    
    @Column(name="file_name")
    private String fileName;
    
    @Column(name="processed")
    private Boolean processed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdMonitoring() {
        return idMonitoring;
    }

    public void setIdMonitoring(Integer idMonitoring) {
        this.idMonitoring = idMonitoring;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    

    public Boolean getProcessed() {
        return processed;
    }

    public void setProcessed(Boolean processed) {
        this.processed = processed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FileMonitoraggio{id=").append(id);
        sb.append(", idMonitoring=").append(idMonitoring);
        sb.append(", fileName=").append(fileName);
        sb.append(", processed=").append(processed);
        sb.append('}');
        return sb.toString();
    }
    
    
}
