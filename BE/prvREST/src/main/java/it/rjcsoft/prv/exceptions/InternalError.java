package it.rjcsoft.prv.exceptions;

import org.springframework.http.HttpStatus;

public class InternalError extends BaseEx {

	private static final long serialVersionUID = -6386609756683203446L;
	
	public InternalError(String message) {
		super(HttpStatus.INTERNAL_SERVER_ERROR, message);
	}
	
	public InternalError(String message, Object object) {
		super(HttpStatus.INTERNAL_SERVER_ERROR, message, object);
	}
	
}
