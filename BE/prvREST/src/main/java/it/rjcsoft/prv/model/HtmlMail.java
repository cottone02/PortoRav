package it.rjcsoft.prv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity(name = "html_mails")
@Table(name = "html_mails")
@IdClass(HtmlMailPKEY.class)
public class HtmlMail {
	
	@Id
	@Column(name = "html_mail_id", nullable = false, length = 50)
	private String htmlMailId;
	
	@Id
	@Column(name = "lingua", nullable = false, length = 2)
	private String lingua;
	
	@Column(name = "oggetto", nullable = false, length = 50)
	private String oggetto;
	
	@Column(name = "flag_referenti", nullable = false)
	private Boolean flagReferenti;
	
	@Column(name = "body", nullable = false)
	private String body;

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

	public String getOggetto() {
		return oggetto;
	}

	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}

	public Boolean getFlagReferenti() {
		return flagReferenti;
	}

	public void setFlagReferenti(Boolean flagReferenti) {
		this.flagReferenti = flagReferenti;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
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
		HtmlMail other = (HtmlMail) obj;
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
		builder.append("HtmlMail [htmlMailId=");
		builder.append(htmlMailId);
		builder.append(", lingua=");
		builder.append(lingua);
		builder.append(", oggetto=");
		builder.append(oggetto);
		builder.append(", flagReferenti=");
		builder.append(flagReferenti);
		builder.append(", body=");
		builder.append(body);
		builder.append("]");
		return builder.toString();
	}
	
}
