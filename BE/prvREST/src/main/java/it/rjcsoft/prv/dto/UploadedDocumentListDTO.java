package it.rjcsoft.prv.dto;

import java.util.List;

import it.rjcsoft.prv.model.UploadedDocument;

public class UploadedDocumentListDTO extends BaseDTO {

	private List<UploadedDocument> documents;
	
	private Integer rowCounter;

	public List<UploadedDocument> getDocuments() {
		return documents;
	}

	public void setDocuments(List<UploadedDocument> documents) {
		this.documents = documents;
	}

	public Integer getRowCounter() {
		return rowCounter;
	}

	public void setRowCounter(Integer rowCounter) {
		this.rowCounter = rowCounter;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UploadedDocumentListDTO [documents=");
		builder.append(documents);
		builder.append(", rowCounter=");
		builder.append(rowCounter);
		builder.append("]");
		return builder.toString();
	}
	
	
}
