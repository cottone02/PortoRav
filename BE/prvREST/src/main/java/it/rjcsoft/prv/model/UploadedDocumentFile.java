package it.rjcsoft.prv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Table(name = "uploaded_documents_files")
@Entity(name = "uploaded_documents_files")
@IdClass(UploadedDocumentFilePKEY.class)
public class UploadedDocumentFile {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@SequenceGenerator(sequenceName = "uploaded_documents_files_id_seq", allocationSize = 1, name = "uploaded_documents_files_id_seq")
    //@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, targetEntity = UploadedDocument.class)
    //@JoinColumn(name = "id_document", referencedColumnName = "id")
    @Column(name = "id_document", nullable = false)
    private Integer idDocument;

    @Id
    @Column(name = "file_name", nullable = false)
    private String fileName;

    public Integer getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(Integer idDocument) {
        this.idDocument = idDocument;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UploadedDocumentFile [idDocument=");
        builder.append(idDocument);
        builder.append(", fileName=");
        builder.append(fileName);
        builder.append("]");
        return builder.toString();
    }

}
