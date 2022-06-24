package it.rjcsoft.prv.service;

import java.util.Map;

import it.rjcsoft.prv.exceptions.PojoNotFound;
import it.rjcsoft.prv.model.HtmlMail;

public interface IHtmlMailService {

	public HtmlMail fetchHtmlMail(String mailId, String lang) throws PojoNotFound;

	public void initBody(HtmlMail htmlMail, Map<String, String> placeholders);

	public boolean sendHtmlEmail(HtmlMail htmlMail, String... to);

}
