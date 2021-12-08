package com.camposoft.exceptions;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.http.HttpStatus;

import com.camposoft.dto.GenericRespuestaDTO;

public class GenericException extends RuntimeException {
	private static final long serialVersionUID = -4522850618140183651L;

	private HttpStatus status;
	private GenericRespuestaDTO rtaDTO;

	public GenericException(GenericRespuestaDTO rta, HttpStatus httpStatus) {
		this.status = httpStatus;
		this.rtaDTO = rta;
	}

	public String getMessageId() {
		return UUID.randomUUID().toString();
	}

	public LocalDateTime getRequestDateTime() {
		return LocalDateTime.now();
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public GenericRespuestaDTO getRtaDTO() {
		return rtaDTO;
	}

	public void setRtaDTO(GenericRespuestaDTO rtaDTO) {
		this.rtaDTO = rtaDTO;
	}

}
