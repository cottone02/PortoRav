
package it.rjcsoft.prv.controller;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import com.querydsl.core.types.Predicate;

import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.rjcsoft.prv.dto.utente.UtenteDTO;
import it.rjcsoft.prv.dto.utente.UtenteFullDTO;
import it.rjcsoft.prv.dto.utente.UtenteUpdateDTO;
import it.rjcsoft.prv.dto.utente.UtenteUserUpdateDTO;
import it.rjcsoft.prv.enums.Status;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.exceptions.InternalError;
import it.rjcsoft.prv.model.Utente;
import it.rjcsoft.prv.repository.IUtenteRepository;
import it.rjcsoft.prv.service.IAuthenticationFacadeService;
import it.rjcsoft.prv.service.IUtenteService;
import it.rjcsoft.prv.utils.PrvConverterUtils;
import it.rjcsoft.prv.utils.PrvUtils;
import java.util.Optional;

@RestController
@RequestMapping("/utente")
public class UtenteController extends BaseController {

    @Autowired
    private IUtenteService utenteService;

    @Autowired
    private IAuthenticationFacadeService authenticationFacadeService;

    @Autowired
    private IUtenteRepository utenteRepository;

    @Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT, ROLE_GUEST })
    @GetMapping("")
    public ResponseEntity<Page<UtenteDTO>> getAll(
            @QuerydslPredicate(root = Utente.class, bindings = IUtenteRepository.class) Predicate predicate,
            @PageableDefault(sort = { "id" }) Pageable pageable) {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.info("Getting all users user={}", authenticationFacadeService.getAuthentication().getPrincipal());
        log.debug("START - predicate{}, pageable{}", predicate, pageable);

        Page<UtenteDTO> page = utenteService.getUsers(predicate, pageable);
        if (page.isEmpty()) {
            log.info("END - no content");
            return ResponseEntity.noContent().build();
        } else {
            log.info("END - 200, page{}", page);
            return ResponseEntity.ok(page);
        }
    }

    @Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT, ROLE_GUEST })
    @PatchMapping("/delete/{id}") //PATCH
    public ResponseEntity<Object> setInactiveUtenteById(@PathVariable int id) {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.debug("Start - delete utente {}", id);

        //Boolean done = utenteService.setInactiveUser(id);
        Boolean done=utenteService.changeAccountStatus(id, Status.INACTIVE);
        if (done)
            return new ResponseEntity<>(HttpStatus.OK);

        log.info("ERRORE set incative user result={}", done);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Secured({ ROLE_ADMIN })
    @DeleteMapping(value = "/deleteAccount/{id}")
    public ResponseEntity<Object> deleteUsersAccount(@PathVariable(value = "id") int id) {

        MDC.put(PRV_TID, PrvUtils.generateTID());

        log.info("Deleting user id={},from user={}", id,
                authenticationFacadeService.getAuthentication().getPrincipal());

        try {
            utenteService.delete(id);
        } catch (BaseEx e) {
            log.error("END - DELETE Internal error, message={}", e.getMessage(), e);
            return e.getResponseEntity();
        }
        return ResponseEntity.ok().build();

    }

    // @Secured({ROLE_ADMIN})
    @PostMapping("newUser")
    public ResponseEntity<Object> newUtente(@Valid @RequestBody UtenteFullDTO nuovoUtente) throws InternalError {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.debug("START - newUtente={}", nuovoUtente);

        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!\\\"#$%&'\\(\\)*+,-.\\/:;<=>?@\\[\\]^_`{|}~])[A-Za-z\\\\d!\\\"#$%&'\\(\\)*+,-.\\/:;<=>?@\\[\\]\\^_`{\\|}~].{7,30}";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = null;

        if (nuovoUtente.getPassword() != null) {
            matcher = pattern.matcher(nuovoUtente.getPassword());
        }

        if (matcher == null || !matcher.matches()) {
            return new ResponseEntity<>("INVALID PASSWORD", HttpStatus.METHOD_NOT_ALLOWED);
        }

        // if (nuovoUtente.getId() == null) {
        // log.warn("END - id NOT NULL, id={}", nuovoUtente.getId());
        // return ResponseEntity.badRequest().build();
        // }
        Boolean existUser=utenteRepository.findByUsername(nuovoUtente.getUsername()).isPresent();
        Boolean existMail=utenteRepository.findByEmail(nuovoUtente.getEmail()).isPresent();
        if(existMail || existUser)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UtenteFullDTO savedUtente = null;
        try {
            savedUtente = utenteService.createUser(nuovoUtente);

        } catch (BaseEx e) {
            log.error("END - Internal error, message={}", e.getMessage(), e);
            return e.getResponseEntity();
        }

        if (savedUtente == null) {
            log.warn("END - not saved!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } else {
            log.info("END - saved {}", savedUtente);
            return new ResponseEntity<>(savedUtente, HttpStatus.CREATED);
        }

    }

    @Secured({ ROLE_ADMIN })
    @PutMapping("/patchFromAdmin")
    public ResponseEntity<Object> updateFromAdmin(@RequestBody UtenteUpdateDTO updateUtenteDTO) {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.debug("START - updateUtenteFromADMIN = {}", updateUtenteDTO);

        try {
            utenteService.update(updateUtenteDTO);

        } catch (BaseEx e) {
            log.error("END - UPDATE Internal error, message={}", e.getMessage(), e);
            return e.getResponseEntity();
        }
        return ResponseEntity.ok().build();
    }

    @Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT, ROLE_GUEST })
    @PutMapping("/patchFromUser/{requestUsername}")
    public ResponseEntity<Object> updateFromUser(@PathVariable String requestUsername,
            @RequestBody UtenteUserUpdateDTO updUtente) {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        log.info("START - updateUtenteFromUser = {} requestBy={}", requestUsername,
                authenticationFacadeService.getAuthentication().getPrincipal());

        Utente exist = utenteRepository.findByUsername(updUtente.getUsername()).get();

        if (!requestUsername.equals(updUtente.getUsername()))
            return new ResponseEntity<>("MODIFICA NON CONSENTITA UTENTE DIVERSO", HttpStatus.BAD_REQUEST);

        if (exist != null)
            try {
                PrvConverterUtils.copyPropertiesNotNull(exist, updUtente);
                log.info("COPY PROPERTIES NOT NULL DONE exist={}", exist);

                UtenteUpdateDTO full = new UtenteUpdateDTO();
                PrvConverterUtils.copyPropertiesNotNull(full, exist);
                log.info("COPY PROPERTIES NOT NULL DONE full={}", full);
                utenteService.update(full);

                return new ResponseEntity<>(full, HttpStatus.OK);
            } catch (Exception e) {
                log.error("COPY PROPERTIES NOT NULL NOT DONE exist={}", exist);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{username}")
    public UtenteDTO getUtenteByEmailOrId(@PathVariable String username) {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        // log.info("Getting Username by email or Id to user={}",
        // authenticationFacadeService.getAuthentication().getPrincipal());
        log.debug("START - GetUser by = {}", username);
        UtenteDTO utente = new UtenteDTO();
        try {
            utente = utenteService.loadUserByUsernameOrEmail(username);
            log.info("loadBy DONE");
            // Utente utenteret=new Utente();
            // PrvConverterUtils.copyProperties(utenteret, utente);
            return utente;

        } catch (BaseEx e) {
            log.error("LoadBy NOT DONE!");
            return null;
        }
    }

    @Secured({ ROLE_ADMIN })
    @PatchMapping("changepwdFromAdmin/{requestUsername}")
    public ResponseEntity<Object> updateUserpwdFromAdmin(@PathVariable String requestUsername,
            @RequestBody Map<String, String> json) {

        MDC.put(PRV_TID, PrvUtils.generateTID());
        Boolean done = false;
        String username = json.get("username");
        String password = json.get("password");

        Boolean esiste = utenteRepository.findByUsername(username).isPresent();
        if (esiste) {
            try {

                done = utenteService.changepwd(username, password);
                log.info("Change pwd result = {}", done);

            } catch (Exception e) {

                log.error("Error, message = {}", e.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        if (done)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Secured({ ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT, ROLE_READER_EXT, ROLE_GUEST })
    @PatchMapping("changepwdFromUser/{requestUsername}")
    public ResponseEntity<Object> updateUserpwdFromUser(@PathVariable String requestUsername,
            @RequestBody Map<String, String> json) {

        MDC.put(PRV_TID, PrvUtils.generateTID());

        String username = json.get("username");
        String password = json.get("password");

        Boolean esiste = utenteRepository.findByUsername(username).isPresent();
        Boolean same = requestUsername.equals(username);

        if (!same)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (!esiste)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        try {

            Boolean done = utenteService.changepwd(username, password);
            log.info("Change pwd result = {}", done);
            if (done)
                return new ResponseEntity<>(HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {

            log.error("Error, message = {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/sendEmail")
    public ResponseEntity<Object> sendEmailResetPassword(@RequestBody String email) {
        MDC.put(PRV_TID, PrvUtils.generateTID());

        Boolean inviata = utenteService.sendEmail(email);

        if (inviata) {
            log.info("Email inviata={}", inviata);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            log.error("Mail non inviata all'indirizzo={}", email);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/sendEmailActivateAccount")
    public ResponseEntity<Object> sendEmailActivateAccount(@RequestBody String email) {

        MDC.put(PRV_TID, PrvUtils.generateTID());

        Boolean inviata = utenteService.sendEmailActivateAccount(email);

        if (inviata) {
            log.info("Email inviata={}", inviata);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            log.error("Mail non inviata all'indirizzo={}", email);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/isOtpCorrect")
    public ResponseEntity<Boolean> isOtpCorrect(@RequestParam String email, @RequestParam String otp) {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        boolean response = utenteService.checkOtpValidity(email, otp);
        log.info("isOtpCorrect response={}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    
    @PatchMapping("/changepwd")
    public ResponseEntity<Object> changepwdByOtp(@RequestBody String password, @RequestParam String email,
            @RequestParam String otp) {

        MDC.put(PRV_TID, PrvUtils.generateTID());

        Boolean done = utenteService.changePasswordByOtp(email, otp, password);

        log.info("changePasswordByOtp result={}", done);

        if (done) {
            log.info("Cambio password by otp riuscito su utente con mail={}", email);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            log.info("Cambio passowrd non riuscito su utente con mail={}", email);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
    @PatchMapping("/activateAccount") //PATCH
    public ResponseEntity<Object> activateAccount(@RequestParam String email,@RequestParam String otp)
    {
        MDC.put(PRV_TID, PrvUtils.generateTID());
        Optional<Utente> exist=utenteRepository.findByEmail(email);
        if(exist.isPresent())
        {
            Utente user=exist.get();
            if(user.getOtp().equals(otp))
            {
               Boolean done=utenteService.changeAccountStatus(user.getId(), Status.ACTIVE);
               if(done) return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
