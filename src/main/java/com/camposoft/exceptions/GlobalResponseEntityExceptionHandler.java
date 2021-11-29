package com.camposoft.exceptions;

import java.util.Map;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalResponseEntityExceptionHandler {

	@ExceptionHandler
	private ResponseEntity<Object> errorResponse(GenericException e) {
		return new ResponseEntity<>(Map.of("messageId", e.getMessageId(), "requestDateTime", e.getRequestDateTime(),
				"Error", e.getRtaDTO()), e.getStatus());
	}

}
