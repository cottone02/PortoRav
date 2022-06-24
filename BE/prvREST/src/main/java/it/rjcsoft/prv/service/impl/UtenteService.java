package it.rjcsoft.prv.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;

import com.querydsl.core.types.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import it.rjcsoft.prv.config.PrvRestConfig;
import it.rjcsoft.prv.dto.utente.UtenteDTO;
import it.rjcsoft.prv.dto.utente.UtenteFullDTO;
import it.rjcsoft.prv.dto.utente.UtenteUpdateDTO;
import it.rjcsoft.prv.enums.ResponseEnum;
import it.rjcsoft.prv.enums.Status;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.InternalError;
import it.rjcsoft.prv.exceptions.PojoNotFound;
import it.rjcsoft.prv.model.HtmlMail;
import it.rjcsoft.prv.model.QUtente;
import it.rjcsoft.prv.model.Utente;
import it.rjcsoft.prv.repository.IUtenteRepository;
import it.rjcsoft.prv.service.BaseService;
import it.rjcsoft.prv.service.IHtmlMailService;
import it.rjcsoft.prv.service.IUtenteService;
import it.rjcsoft.prv.utils.PrvConverterUtils;
import it.rjcsoft.prv.utils.PrvMailUtils;

@Service
public class UtenteService extends BaseService implements UserDetailsService, IUtenteService {

    private static final Integer EXPIRE_MINS = 5;
    private static final String USER = "user={}";

    @Autowired
    private PrvRestConfig prvRestConfig;

    @Autowired
    private IUtenteRepository utenteRepository;

    @Autowired
    private IHtmlMailService htmlMailService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public Page<UtenteDTO> getUsers(Predicate predicate, Pageable pageable) {
        log.debug("START - predicate{}, pageable{}", predicate, pageable);
        Page<Utente> utentePage = utenteRepository.findAll(predicate, pageable);
        log.trace("utentePage={}", utentePage.getContent());
        Page<UtenteDTO> responsePage = utentePage.map(currentUtente -> {
            UtenteDTO dto = new UtenteDTO();
            PrvConverterUtils.copyProperties(dto, currentUtente);
            if (dto != null) {
                return dto;
            }
            return null;
        });
        return responsePage;
    }

    @Override
    public boolean delete(int id) throws BaseEx {
        log.debug("START - delete Utente={}", id);

        utenteRepository.findById(id)
                .orElseThrow(() -> new PojoNotFound("Utente with id " + id + " not found"));
        try {
            utenteRepository.deleteById(id);
            log.info("END - deleted {}", id);
            return true;
        } catch (Exception e) {
            log.error("END - not deleted {}, message={}", id, e.getMessage(), e);
            throw new InternalError("Utente id=" + id + " non deleted");
        }
    }

    @Override
    public UtenteFullDTO createUser(UtenteFullDTO userDTO) throws BaseEx {
        Utente utente = null;
        try {
            utente = new Utente();
            if (PrvConverterUtils.copyProperties(utente, userDTO)) {
                utente.setRoleValue("GUEST");
                utente.setStatusValue("PENDING");
                utente.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                utente.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                utente.setModifiedDate(new Timestamp(System.currentTimeMillis()));
                log.info("Model - Utente{}", utente);
                utente = utenteRepository.save(utente);
                log.info("fine");
                UtenteFullDTO dto = new UtenteFullDTO();
                PrvConverterUtils.copyProperties(dto, utente);
                return dto;
            }
        } catch (Exception e) {
            log.error("ERROR SAVING");
            throw new InternalError("oggetto non salvato", userDTO);
        }
        log.warn("End");
        return null;
    }

    @Override
    public boolean sendEmailToAdmin(String mailId, String... email) {
        // boolean esito = true;
        // String emailFrom = prvRestConfig.getEmailFrom();
        // String baseUrl = prvRestConfig.getBaseUrl();

        // log.debug("SENT mailId={}, from={}, phValues={}", mailId, emailFrom, StringUtils.join(phValues));
        // List<String> adminEmails = userRepository.selectAdminEmails();
        // if (adminEmails.isEmpty()) {
        // 	log.warn("No admins found");
        // 	return esito;
        // }
        // HtmlMail htmlMail = null;
        // try {
        // 	htmlMail = htmlMailService.fetchHtmlMail(mailId, PrvMailUtils.getLanguage());
        // } catch (PojoNotFound e) {
        // 	log.error("END - Html mail not found");
        // 	return false;
        // }
        // if (htmlMail == null)
        // 	return false;
        // Map<String, String> placeholders = PrvMailUtils.initPlaceholders(mailId, phValues);
        // htmlMailService.initBody(htmlMail, placeholders);
        // esito = htmlMailService.sendHtmlEmail(htmlMail, adminEmails.toArray(new String[adminEmails.size()]));
        // log.info("EMAIL SENT TO ADMIN {}, text={}, resp={}", StringUtils.join(adminEmails), htmlMail.getBody(), esito);
        // return esito;
        return false;
    }

    @Override
    public void update(UtenteUpdateDTO userDTO) throws BaseEx {
        log.debug("START - update {}", userDTO);
        Utente utente = new Utente();
        Integer id = userDTO.getId();
        String username = userDTO.getUsername();
        if (id != null) {
            utente = utenteRepository.findById(id)
                    .orElseThrow(() -> new PojoNotFound("Utente con id " + id + " non trovato!"));
        } else if (username != null) {
            utente = utenteRepository.findByUsername(username)
                    .orElseThrow(() -> new PojoNotFound("Utente con username " + username + "non trovato!"));
        }

        try {
            if (PrvConverterUtils.copyPropertiesNotNull(utente, userDTO)) {
                log.info("Model - Utente={}", utente);
                utente.setModifiedDate(new Timestamp(System.currentTimeMillis()));
                utente = utenteRepository.save(utente);
                log.info("UPDATED");
                // return PrvConverterUtils.initUtenteDTO(utente);
            }
        } catch (Exception e) {

            log.error("ERRORE salvataggio", e);
            throw new InternalError("oggetto non salvato ");
        }
        log.warn("END copyProperties FAILED");

    }

    @Override
    public Boolean sendEmail(String email) {

        String emailFrom = prvRestConfig.getEmailFrom();
        String baseUrl = prvRestConfig.getBaseUrl();
        log.debug("SENT mail to={}, from={}", email, emailFrom);

        Optional<Utente> utente = utenteRepository.findByEmail(email);
        if (!utente.isPresent()) {
            log.error("Utente con mail={} non esiste", email);
            return false;
        }

        Utente esistente = utente.get();
        if (esistente.getStatusValue().equals("INACTIVE")) {
            log.info("Utente con mail={} INATTIVO", email);
            return false;
        }

        String otp = UUID.randomUUID().toString();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
        calendar.add(Calendar.MINUTE, EXPIRE_MINS);
        
        esistente.setOtp(otp);
        esistente.setExpirationOtp(new Timestamp(calendar.getTimeInMillis()));
        utenteRepository.save(esistente);

        HtmlMail htmlMail = null;
        try {
            htmlMail = htmlMailService.fetchHtmlMail(PrvMailUtils.RESET_PASSWORD, PrvMailUtils.getLanguage());
        } catch (PojoNotFound e) {
            log.error("END - Html mail not found");
            return false;
        }
        if (htmlMail == null) {
            return false;
        }

        Map<String, String> placeholders = PrvMailUtils.initPlaceholders(PrvMailUtils.RESET_PASSWORD, baseUrl,
                baseUrl, otp,
                email, calendar.getTime().toString());
        htmlMailService.initBody(htmlMail, placeholders);

        if (!htmlMailService.sendHtmlEmail(htmlMail, email)) {
            return false;
        }

        return true;

    }

    @Override
    public boolean setOtpByEmail(String Email, String otp, Calendar calendar) {
        log.info("START - setOtpByEmail");
        Optional<Utente> utenteOpt = utenteRepository.findByEmail(Email);
        if (!utenteOpt.isPresent()) {
            return false;
        }

        Utente utente = utenteOpt.get();
        utente.setOtp(otp);
        utente.setExpirationOtp(new Timestamp(calendar.getTimeInMillis()));
        try {
            utenteRepository.save(utente);
            return true;
        } catch (Exception e) {
            log.error("Salvataggio utente={} non riuscito", utente);
            return false;
        }
    }

    @Override
    public Boolean checkOtpValidity(String email, String otp) {
        log.info("START - check Opt Validity");

        Optional<Utente> utenteOpt = utenteRepository.findByEmail(email);
        if (utenteOpt.isPresent()) {
            Utente utente = utenteOpt.get();
            return (utente.getOtp().equals(otp))
                    && (utente.getExpirationOtp().after(new Timestamp(System.currentTimeMillis())));
        }
        return false;
    }

    @Override
    public Boolean changePasswordByOtp(String email, String otp, String password) {

        log.info("START - changePassowrdByOtp");

        Optional<Utente> utenteOpt = utenteRepository.findByEmail(email);
        if (!utenteOpt.isPresent()) {
            log.error("Utente con email={} non presente", email);
            return false;
        }

        Boolean validOtp = checkOtpValidity(email, otp);
        if (!validOtp) {
            log.error("Otp non valido email={}, otp={}", email, otp);
            return false;
        }

        Utente utente = utenteOpt.get();
        utente.setPassword(passwordEncoder.encode(password));
        utente.setOtp("");
        utente.setExpirationOtp(null);
        utenteRepository.save(utente);
        return true;

    }

    @Override
    public Boolean changepwd(String username, String password) throws InternalError {
        log.debug("START - CHANGE PWD");
        Optional<Utente> utenteOpt = utenteRepository.findByUsername(username);
        if (utenteOpt.isPresent()) {
            try {
                Utente utenteModifica = utenteOpt.get();
                utenteModifica.setPassword(passwordEncoder.encode(password));
                utenteRepository.save(utenteModifica);
                return true;

            } catch (Exception e) {
                log.error("Errore nel salvataggio dell'utente={}", username);
                return false;
            }
        }
        log.error("Utente={} non trovato", username);
        return false;
    }

    @Override
    public Boolean sendEmailActivateAccount(String email) {

        String emailFrom = prvRestConfig.getEmailFrom();
        String baseUrl = prvRestConfig.getBaseUrl();
        log.debug("SENT_ACTIVATE mail to={}, from={}", email, emailFrom);

        String otp = UUID.randomUUID().toString();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
        calendar.add(Calendar.MINUTE, EXPIRE_MINS);

        HtmlMail htmlMail = null;
        try {
            htmlMail = htmlMailService.fetchHtmlMail(PrvMailUtils.ACCOUNT_ACTIVATION, PrvMailUtils.getLanguage());
        } catch (PojoNotFound e) {
            log.error("END - Html mail not found");
            return false;
        }
        if (htmlMail == null) {
            return false;
        }

        Map<String, String> placeholders = PrvMailUtils.initPlaceholders(PrvMailUtils.ACCOUNT_ACTIVATION, baseUrl,
                baseUrl, otp,
                email, calendar.getTime().toString());
        htmlMailService.initBody(htmlMail, placeholders);

        if (!htmlMailService.sendHtmlEmail(htmlMail, email)) {
            return false;
        }

        Optional<Utente> userOptional = utenteRepository.findByEmail(email);
        if (!userOptional.isPresent()) {
            return false;
        }
        Utente utente = userOptional.get();
        utente.setOtp(otp);
        utente.setExpirationOtp(new Timestamp(calendar.getTimeInMillis()));
        utenteRepository.save(utente);

        // sendEmailToAdmin(PrvMailUtils.ADMIN_USER_ACTIVATION, baseUrl, email);
        return true;
    }

    @Override
    public Boolean setInactiveUser(int id) {
        /*log.info("START - set incative utente con id={}", id);

        Optional<Utente> utenteOpt = utenteRepository.findById(id);
        if (!utenteOpt.isPresent()) {
            return false;
        }

        log.info("Utente con id={} presente", id);
        Utente utente = utenteOpt.get();
        utente.setStatusValue("INACTIVE");
        utenteRepository.save(utente);*/
        return true;
    }

    @Override
    public Boolean changeAccountStatus(int id, Status status) {
        log.info("START - set incative utente con id={}", id);

        Optional<Utente> utenteOpt = utenteRepository.findById(id);
        if (!utenteOpt.isPresent()) {
            return false;
        }

        log.info("Utente con id={} presente", id);
        Utente utente = utenteOpt.get();
        utente.setStatusValue(status.getCode());
        utenteRepository.save(utente);
        return true;
    }

    // A QUI INUTILI
    @Override
    public boolean sendMailToDev(String subject, String text) {
        // try {
        // 	String from = prvRestConfig.getEmailFrom();
        // 	String[] to = prvRestConfig.getEmailDevelopers();
        // 	log.debug("START - subject={}, text={}, from={}, to={}", subject, text, from, StringUtils.join(to));
        // 	SimpleMailMessage message = new SimpleMailMessage();
        // 	message.setFrom(from);
        // 	message.setTo(to);
        // 	message.setSubject("SIM Porto Ravenna - " + subject);
        // 	message.setText(text);
        // 	emailSender.send(message);
        // 	log.info("Mail sent - text={}, from={}, to={}", text, from, StringUtils.join(to));
        // } catch (Exception e) {
        // 	log.error("Error during mail sending {}", e.getMessage(), e);
        // 	return false;
        // }
        // return true;
        return false;
    }

    @Override
    public ResponseEnum sendEmailRoleChanged(String email, String newRole) {
        // HtmlMail htmlMail = null;
        // String mailRole = PrvMailUtils.getRoleKey(newRole);
        // String baseUrl = prvRestConfig.getBaseUrl();
        // try {
        // 	htmlMail = htmlMailService.fetchHtmlMail(mailRole, PrvMailUtils.getLanguage());
        // } catch (PojoNotFound e) {
        // 	log.error("END - Html mail not found");
        // 	return ResponseEnum.MAIL_ERROR;
        // }
        // if (htmlMail == null)
        // 	return ResponseEnum.MAIL_ERROR;

        // Map<String, String> placeholders = PrvMailUtils.initPlaceholders(mailRole, baseUrl);
        // htmlMailService.initBody(htmlMail, placeholders);
        // if (!htmlMailService.sendHtmlEmail(htmlMail, email)) {
        // 	log.error("Email with the new  role={} not sent to={}", newRole, email);
        // }
        // return ResponseEnum.OK;
        return ResponseEnum.OK;
    }

    @Override
    public UtenteDTO loadUserByUsernameOrEmail(String username) throws BaseEx {
        QUtente utenteEntity = QUtente.utente;
        Predicate usernameOrEmail = utenteEntity.username.eq(username).or(utenteEntity.email.eq(username));
        Optional<Utente> utenteOptional = utenteRepository.findOne(usernameOrEmail);
        UtenteDTO user = new UtenteDTO();
        Utente utente = utenteOptional.get();
        try {

            PrvConverterUtils.copyProperties(user, utente);
            log.info("COPYPROPERTIES DONE - from= {} to ={}", utenteOptional.get(), user);
            return user;
        } catch (Exception e) {
            log.error("COPYPROPERTIES ERROR");
            return user;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QUtente utenteEntity = QUtente.utente;
        Predicate usernameOrEmail = utenteEntity.username.eq(username).or(utenteEntity.email.eq(username));
        Optional<Utente> utenteOptional = utenteRepository.findOne(usernameOrEmail);

        if (utenteOptional.isPresent()) {
            Utente utente = utenteOptional.get();
            log.info("User with username={} trying to log-in", utente.getUsername());

            if (!utente.getStatusValue().equals("ACTIVE")) {
                if (utente.getStatusValue().equals("PENDING")) {
                    sendEmailActivateAccount(utente.getEmail());
                }
                throw new UsernameNotFoundException("Invalid account username" + utente.getUsername());
            }

            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(
                    String.format("ROLE_%s", utente.getRoleValue().toUpperCase()));

            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

            grantedAuthorities.add(grantedAuthority);

            return new org.springframework.security.core.userdetails.User(utente.getUsername(), utente.getPassword(),
                    grantedAuthorities);
        }

        throw new UsernameNotFoundException("Invalid username or password.");
    }

    @Override
    public Boolean activateAccount(String email, String otp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
