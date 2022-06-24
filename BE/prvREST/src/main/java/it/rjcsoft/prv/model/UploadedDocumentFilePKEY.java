package it.rjcsoft.prv.model;

import java.io.Serializable;

public class UploadedDocumentFilePKEY implements Serializable{
	
	private Integer idDocument;

	private String fileName;

	public UploadedDocumentFilePKEY(/*Integer idDocument, String fileName*/) {
		/*super();
		this.idDocument = idDocument;
		this.fileName = fileName;*/
	}
	
	public UploadedDocumentFilePKEY(Integer idDocument, String fileName) {
		super();
		this.idDocument = idDocument;
		this.fileName = fileName;
	}
	
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
		builder.append("UploadedDocumentFilePKEY [idDocument=");
		builder.append(idDocument);
		builder.append(", fileName=");
		builder.append(fileName);
		builder.append("]");
		return builder.toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idDocument == null) ? 0 : idDocument.hashCode());
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UploadedDocumentFilePKEY other = (UploadedDocumentFilePKEY) obj;
		if (idDocument == null) {
			if (other.idDocument != null)
				return false;
		} else if (!idDocument.equals(other.idDocument))
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		return true;
	}

}
