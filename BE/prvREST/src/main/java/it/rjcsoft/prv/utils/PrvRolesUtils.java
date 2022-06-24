package it.rjcsoft.prv.utils;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class PrvRolesUtils {
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_WRITER = "ROLE_WRITER";
	public static final String ROLE_READER = "ROLE_READER";
	public static final String ROLE_WRITER_EXT = "ROLE_WRITER_EXT";
	public static final String ROLE_READER_EXT = "ROLE_READER_EXT";
	private static final String[] ROLES_ADMIN = { ROLE_ADMIN };
	private static final String[] ROLES_WRITER_INT = { ROLE_ADMIN, ROLE_WRITER };
	private static final String[] ROLES_READER_INT = { ROLE_ADMIN, ROLE_WRITER, ROLE_READER };
	private static final String[] ROLES_WRITER_EXT = { ROLE_ADMIN, ROLE_WRITER, ROLE_WRITER_EXT };
	private static final String[] ROLES_READER_EXT = { ROLE_ADMIN, ROLE_WRITER, ROLE_READER, ROLE_WRITER_EXT,
			ROLE_READER_EXT };

	private PrvRolesUtils() {
	}

	public static boolean isAdmin(Authentication authentication) {
		return checkRoles(authentication, ROLES_ADMIN);
	}

	public static boolean isWriteInt(Authentication authentication) {
		return checkRoles(authentication, ROLES_WRITER_INT);
	}

	public static boolean isReaderInt(Authentication authentication) {
		return checkRoles(authentication, ROLES_READER_INT);
	}

	public static boolean isWriteExt(Authentication authentication) {
		return checkRoles(authentication, ROLES_WRITER_EXT);
	}

	public static boolean isReaderExt(Authentication authentication) {
		return checkRoles(authentication, ROLES_READER_EXT);
	}

	private static boolean checkRoles(Authentication authentication, String[] roles) {
		if (authentication == null || roles == null || roles.length == 0) {
			return false;
		}
		Collection<? extends GrantedAuthority> roleObj = authentication.getAuthorities();
		if (roleObj == null) {
			return false;
		}
		for (GrantedAuthority grantedAuthority : roleObj) {
			if (StringUtils.equalsAny(grantedAuthority.getAuthority(), roles)) {
				return true;
			}
		}
		return false;
	}

}
