package it.rjcsoft.prv.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import it.rjcsoft.prv.config.PrvRestConfig;
import it.rjcsoft.prv.dto.companyentity.CompanyEntityCompleteDTO;
import it.rjcsoft.prv.dto.companyentity.CompanyEntityDTO;
import it.rjcsoft.prv.dto.email.EmailDTO;
import it.rjcsoft.prv.exceptions.PojoNotFound;
import it.rjcsoft.prv.model.CompanyEntity;
import it.rjcsoft.prv.model.HtmlMail;
import it.rjcsoft.prv.model.HtmlMailPKEY;
import it.rjcsoft.prv.model.UserEntity;
import it.rjcsoft.prv.repository.IHtmlMailRepository;
import it.rjcsoft.prv.repository.IUtenteRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.ICompanyEntityService;
import it.rjcsoft.prv.service.IHtmlMailService;
import it.rjcsoft.prv.utils.PrvConverterUtils;
import it.rjcsoft.prv.utils.PrvMailUtils;

@Service
public class HtmlMailService extends BaseService implements IHtmlMailService {

    @Autowired
    private PrvRestConfig prvRestConfig;
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private IHtmlMailRepository htmlMailRepository;
    @Autowired
    private IUtenteRepository utenteRepository;
    @Autowired
    private ICompanyEntityService companyEntity;

    public boolean customEmail(EmailDTO emailDto) throws Exception {

        try {
            log.info("Start emailDTO={}", emailDto.toString());
            if (CollectionUtils.isEmpty(emailDto.getAziende())) {
                log.warn("emailDto is null");
                return false;
            }
            List<CompanyEntityDTO> aziende = emailDto.getAziende();
            HtmlMail email = fetchHtmlMail(PrvMailUtils.GENERAL, PrvMailUtils.getLanguage());
            ;
            email.setFlagReferenti(Boolean.TRUE);
            for (CompanyEntityDTO company : aziende) {
                HtmlMail mail = new HtmlMail();
                PrvConverterUtils.copyProperties(mail, email);
                CompanyEntityCompleteDTO azienda = new CompanyEntityCompleteDTO();
                CompanyEntity cEnt = companyEntity.getCompanyById(company.getId());
                azienda = PrvConverterUtils.initCompanyEntityCompleteDTO(cEnt);
                if (azienda != null) {

                    Map<String, String> placeholders = PrvMailUtils.initPlaceholders(PrvMailUtils.GENERAL, prvRestConfig.getBaseUrl(),
                            azienda.getName(), emailDto.getBody());

                    initBody(mail, placeholders);
                    
                    if (CollectionUtils.isEmpty(azienda.getDipendenti())) {
                        log.info("Azienda {} vuota",azienda);
                    }
                    else{//Qui scoppia se non ci sono dipendenti
                    List<String> mails = azienda.getDipendenti().stream().map(UserEntity::getEmail)
                            .collect(Collectors.toList());
                    String[] dipendentiArray = mails.toArray(String[]::new);

                    if (StringUtils.isNotBlank(emailDto.getObject())) {
                        mail.setOggetto(emailDto.getObject());
                    }
                    sendHtmlEmail(mail, dipendentiArray);
                    }
                }
            }
            return true;
        } catch (Exception e) {
            log.error("Error during html customEmail {}", e.getMessage(), e);
            throw e;
        }
    }

    public HtmlMail fetchHtmlMail(String mailId, String lang) throws PojoNotFound {
        if (StringUtils.isBlank(mailId) || StringUtils.isBlank(lang)) {
            return null;
        }
        HtmlMailPKEY idpk = new HtmlMailPKEY();
        idpk.setHtmlMailId(mailId);
        idpk.setLingua(lang);
        Optional<HtmlMail> htmlMailOptional = htmlMailRepository.findById(idpk);
        if (!htmlMailOptional.isPresent()) {
            throw new PojoNotFound("Record mail not found");
        }

        return htmlMailOptional.get();
    }

    public void initBody(HtmlMail htmlMail, Map<String, String> placeholders) {

        log.info("Start initBody={}, placeholders={}", htmlMail, placeholders.toString());
        if (htmlMail == null || CollectionUtils.isEmpty(placeholders)) {
            log.warn("htmlMail or placeholders is null");
            return;
        }

        String body = htmlMail.getBody();
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            body = StringUtils.replace(body, entry.getKey(), entry.getValue());
        }
        htmlMail.setBody(body);
    }

    @Override
    public boolean sendHtmlEmail(HtmlMail htmlMail, String... to) {
        log.info("Start htmlMail={}, to={}", htmlMail, StringUtils.join(to));
        String[] contactPersonEmailsArray = null;

        if (htmlMail == null || to == null) {
            log.warn("htmlMail or to is null");
            return false;
        }

        if (Boolean.TRUE.equals(htmlMail.getFlagReferenti())) {
            List<String> contactPersonEmails = utenteRepository.findContactPersonMailByMail(to);
            List<String> lista=new ArrayList<>();
            if (!CollectionUtils.isEmpty(contactPersonEmails)) {
                //RemoveAll toglie da contactPerson tutti quelli che non sono in to
                Collections.addAll(lista, to);
                lista.removeAll(contactPersonEmails);
                contactPersonEmailsArray = lista.toArray(new String[lista.size()]);
            }
        }

        try {
            String emailFrom = prvRestConfig.getEmailFrom();

            log.debug("EmailFrom={}", emailFrom);
//			MimeMessagePreparator preparator = emailSender.createMimeMessage();
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
//			Calendar calendar = Calendar.getInstance();
//			calendar.set(2021, 5, 25, 16, 30);
//			MimeMessageHelper helper = new MimeMessageHelper(preparator, true, "UTF-8");
//			helper.setSentDate(calendar.getTime());
            BodyPart messageBodyPart = new MimeBodyPart();
            File logoFile = new File(prvRestConfig.getLogoPath());
            if (logoFile.exists()) {
                DataSource fds = new FileDataSource(logoFile);
                messageBodyPart.setDataHandler(new DataHandler(fds));
                messageBodyPart.setHeader("Content-ID", "<image_01>");
                helper.addAttachment(logoFile.getName(), fds);
            }

            helper.setFrom(emailFrom);
            helper.setTo(to);
            helper.setSubject(htmlMail.getOggetto());
            helper.setText(htmlMail.getBody(), true);
            if (prvRestConfig.getSimulationEmailFlag()) {
                log.debug("Sending email simulation started {}", htmlMail);
            } else {
                if (prvRestConfig.getEmailDevelopers() != null) {
                    helper.setBcc(prvRestConfig.getEmailDevelopers());
                }
                if (contactPersonEmailsArray != null) {
                    helper.setCc(contactPersonEmailsArray);
                }
                emailSender.send(message);
            }

        } catch (Exception e) {
            log.error("Error during html mail sending {}", e.getMessage(), e);
            return false;
        }
        return true;
    }

}
