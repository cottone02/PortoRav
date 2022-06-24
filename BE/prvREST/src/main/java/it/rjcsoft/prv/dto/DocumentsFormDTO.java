package it.rjcsoft.prv.dto;

import java.util.List;

import it.rjcsoft.prv.model.DocumentForm;

public class DocumentsFormDTO extends BaseDTO {
	
	private List<DocumentForm> documents;
	
	private Integer rowCounter;

	public List<DocumentForm> getDocuments() {
		return documents;
	}

	public void setDocuments(List<DocumentForm> documents) {
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
		builder.append("DocumentsFormDTO [documents=");
		builder.append(documents);
		builder.append(", rowCounter=");
		builder.append(rowCounter);
		builder.append("]");
		return builder.toString();
	} 

}
