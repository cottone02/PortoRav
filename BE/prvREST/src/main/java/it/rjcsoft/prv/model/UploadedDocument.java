package it.rjcsoft.prv.model;

import java.util.List;
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

@Table(name = "uploaded_documents")
@Entity(name = "uploaded_documents")
public class UploadedDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(sequenceName = "uploaded_documents_id_seq", allocationSize = 1, name = "uploaded_documents_id_seq")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "company_id", nullable = false)
    private Integer companyId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    //private Integer numFile;
    //private String companyName;
    @Column(name = "description")
    private String description;

    @OneToMany(cascade = {CascadeType.REFRESH}, targetEntity = UploadedDocumentFile.class)
    @JoinColumn(name = "id_document", referencedColumnName = "id")
    private List<UploadedDocumentFile> fileDocumenti;

    public List<UploadedDocumentFile> getFileDocumenti() {
        return fileDocumenti;
    }

    public void setFileDocumenti(List<UploadedDocumentFile> fileDocumenti) {
        this.fileDocumenti = fileDocumenti;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UploadedDocument{id=").append(id);
        sb.append(", companyId=").append(companyId);
        sb.append(", userId=").append(userId);
        sb.append(", description=").append(description);
        sb.append(", fileDocumenti=").append(fileDocumenti);
        sb.append('}');
        return sb.toString();
    }

}
