package it.rjcsoft.prv.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseEx extends Exception {

	private static final long serialVersionUID = -762810232942248753L;
	private ResponseEntity<Object> responseEntity;

	public BaseEx(HttpStatus status, String message, Object object) {
		super(message);
		Map<String, Object> respMap = new HashMap<>();
		respMap.put("message", message);
		if(object!=null) {
			respMap.put("object", object);	
		}
		setResponseEntity(ResponseEntity.status(status).body(respMap));
	}
	
	public BaseEx(HttpStatus status, String message) {
		super(message);
		Map<String, Object> respMap = new HashMap<>();
		respMap.put("message", message);
		setResponseEntity(ResponseEntity.status(status).body(respMap));
	}

	
	public ResponseEntity<Object> getResponseEntity() {
		return responseEntity;
	}

	public void setResponseEntity(ResponseEntity<Object> responseEntity) {
		this.responseEntity = responseEntity;
	}
	

}
