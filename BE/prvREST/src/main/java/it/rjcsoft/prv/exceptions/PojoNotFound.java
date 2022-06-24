package it.rjcsoft.prv.exceptions;

import org.springframework.http.HttpStatus;

public class PojoNotFound extends BaseEx {

	private static final long serialVersionUID = -6386609756683203446L;

	public PojoNotFound(String message) {
		super(HttpStatus.NO_CONTENT, message);
	}
	
	public PojoNotFound(String message, Object object) {
		super(HttpStatus.NO_CONTENT, message, object);
	}
}
