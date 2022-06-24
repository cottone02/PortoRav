package it.rjcsoft.prv.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UnAuthorized extends RuntimeException {

	private static final long serialVersionUID = -828094285649084463L;

	public UnAuthorized(String message) {
        super(message);
    }

    public ResponseEntity<Object> getResponseEntity() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(getMessage());
    }
}
