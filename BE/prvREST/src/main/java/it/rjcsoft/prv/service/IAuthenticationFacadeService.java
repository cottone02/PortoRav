package it.rjcsoft.prv.service;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacadeService {

	Authentication getAuthentication();
	
}
