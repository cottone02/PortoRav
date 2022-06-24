package it.rjcsoft.prv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import it.rjcsoft.prv.dto.utente.UtenteDTO;
import it.rjcsoft.prv.exceptions.BaseEx;
import it.rjcsoft.prv.service.IAuthenticationFacadeService;
import it.rjcsoft.prv.service.IUtenteService;

//@CrossOrigin(origins = "http://localhost:4200")
public abstract class BaseController {

	@Autowired
	private IAuthenticationFacadeService authenticationFacadeService;

	@Autowired
	private IUtenteService userService;

	protected final Logger log = LoggerFactory.getLogger(getClass());

	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_WRITER = "ROLE_WRITER";
	public static final String ROLE_READER = "ROLE_READER";
	public static final String ROLE_WRITER_EXT = "ROLE_WRITER_EXT";
	public static final String ROLE_READER_EXT = "ROLE_READER_EXT";
	public static final String ROLE_GUEST = "ROLE_GUEST";

	public static final String PRV_TID = "PRV_TID";

	protected Integer getLoggedUserId() {
		Integer id = null;
		Authentication info = authenticationFacadeService.getAuthentication();
		if (info != null) {
			String userString = info.getPrincipal().toString();
			UtenteDTO user;
			try {
				user = userService.loadUserByUsernameOrEmail(userString);
				if (user != null) {
					log.trace("userFound {}", user);
					id = user.getId();
				}
			} catch (BaseEx e) {
				return null;
			}
		}
		return id;
	}
}
