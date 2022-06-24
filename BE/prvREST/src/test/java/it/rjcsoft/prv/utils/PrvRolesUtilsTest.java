package it.rjcsoft.prv.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class PrvRolesUtilsTest {

	@DisplayName("Test del ruolo ADMIN")
	@Test
	void ifRoleIsAdminAndCallUtilityMethodsThenAllWillBeChecked() {	
		Authentication authentication = createAuthenticationObject("ROLE_ADMIN");
		assertTrue(PrvRolesUtils.isAdmin(authentication));
		assertTrue(PrvRolesUtils.isWriteExt(authentication));
		assertTrue(PrvRolesUtils.isWriteInt(authentication));
		assertTrue(PrvRolesUtils.isReaderExt(authentication));
		assertTrue(PrvRolesUtils.isReaderInt(authentication));
	}
	
	@DisplayName("Test del ruolo WRITER")
	@Test
	void ifRoleIsWriterIntAndCallUtilityMethodsThenAllWillBeChecked() {	
		Authentication authentication = createAuthenticationObject("ROLE_WRITER");
		assertFalse(PrvRolesUtils.isAdmin(authentication));
		assertTrue(PrvRolesUtils.isWriteExt(authentication));
		assertTrue(PrvRolesUtils.isWriteInt(authentication));
		assertTrue(PrvRolesUtils.isReaderExt(authentication));
		assertTrue(PrvRolesUtils.isReaderInt(authentication));
	}
	
	@DisplayName("Test del ruolo WRITER EXT")
	@Test
	void ifRoleIsWriterExtAndCallUtilityMethodsThenAllWillBeChecked() {	
		Authentication authentication = createAuthenticationObject("ROLE_WRITER_EXT");
		assertFalse(PrvRolesUtils.isAdmin(authentication));
		assertTrue(PrvRolesUtils.isWriteExt(authentication));
		assertFalse(PrvRolesUtils.isWriteInt(authentication));
		assertTrue(PrvRolesUtils.isReaderExt(authentication));
		assertFalse(PrvRolesUtils.isReaderInt(authentication));
	}
	
	@DisplayName("Test del ruolo READER")
	@Test
	void ifRoleIsReaderIntAndCallUtilityMethodsThenAllWillBeChecked() {	
		Authentication authentication = createAuthenticationObject("ROLE_READER");
		assertFalse(PrvRolesUtils.isAdmin(authentication));
		assertFalse(PrvRolesUtils.isWriteExt(authentication));
		assertFalse(PrvRolesUtils.isWriteInt(authentication));
		assertTrue(PrvRolesUtils.isReaderExt(authentication));
		assertTrue(PrvRolesUtils.isReaderInt(authentication));
	}
	@DisplayName("Test del ruolo READER_EXT")
	@Test
	void ifRoleIsReaderExtAndCallUtilityMethodsThenAllWillBeChecked() {	
		Authentication authentication = createAuthenticationObject("ROLE_READER_EXT");
		assertFalse(PrvRolesUtils.isAdmin(authentication));
		assertFalse(PrvRolesUtils.isWriteExt(authentication));
		assertFalse(PrvRolesUtils.isWriteInt(authentication));
		assertTrue(PrvRolesUtils.isReaderExt(authentication));
		assertFalse(PrvRolesUtils.isReaderInt(authentication));
	}
	@DisplayName("Test del ruolo GUEST")
	@Test
	void ifRoleIsGuestAndCallUtilityMethodsThenAllWillBeChecked() {	
		Authentication authentication = createAuthenticationObject("ROLE_GUEST");
		assertFalse(PrvRolesUtils.isAdmin(authentication));
		assertFalse(PrvRolesUtils.isWriteExt(authentication));
		assertFalse(PrvRolesUtils.isWriteInt(authentication));
		assertFalse(PrvRolesUtils.isReaderExt(authentication));
		assertFalse(PrvRolesUtils.isReaderInt(authentication));
	}
	private Authentication createAuthenticationObject(final String currRole) {
		return new TestingAuthenticationToken("", "",currRole);
	}
}
