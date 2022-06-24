package it.rjcsoft.prv.dto;

import it.rjcsoft.prv.model.UploadedDocumentFile;
import java.util.List;

public class UploadedDocumentDTO {

    private Integer id;
    private Integer companyId;
    private Integer userId;

    private String companyName;
    private String description;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UploadedDocumentDTO{id=").append(id);
        sb.append(", companyId=").append(companyId);
        sb.append(", userId=").append(userId);
        sb.append(", companyName=").append(companyName);
        sb.append(", description=").append(description);
        sb.append(", fileDocumenti=").append(fileDocumenti);
        sb.append('}');
        return sb.toString();
    }

}
