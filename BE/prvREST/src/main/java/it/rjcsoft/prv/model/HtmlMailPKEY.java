package it.rjcsoft.prv.model;

import java.io.Serializable;

public class HtmlMailPKEY implements Serializable {

	private static final long serialVersionUID = -2998183773178381736L;

	private String htmlMailId;
	
	private String lingua;
	
	
	public String getHtmlMailId() {
		return htmlMailId;
	}
	public void setHtmlMailId(String htmlMailId) {
		this.htmlMailId = htmlMailId;
	}
	public String getLingua() {
		return lingua;
	}
	public void setLingua(String lingua) {
		this.lingua = lingua;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((htmlMailId == null) ? 0 : htmlMailId.hashCode());
		result = prime * result + ((lingua == null) ? 0 : lingua.hashCode());
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
		HtmlMailPKEY other = (HtmlMailPKEY) obj;
		if (htmlMailId == null) {
			if (other.htmlMailId != null)
				return false;
		} else if (!htmlMailId.equals(other.htmlMailId))
			return false;
		if (lingua == null) {
			if (other.lingua != null)
				return false;
		} else if (!lingua.equals(other.lingua))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HtmlMailPKEY [htmlMailId=");
		builder.append(htmlMailId);
		builder.append(", lingua=");
		builder.append(lingua);
		builder.append("]");
		return builder.toString();
	}
	
}
