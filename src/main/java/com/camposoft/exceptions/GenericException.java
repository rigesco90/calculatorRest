package com.camposoft.exceptions;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.http.HttpStatus;

import com.camposoft.dto.GenericRespuesta;

public class GenericException extends RuntimeException {
	private static final long serialVersionUID = -4522850618140183651L;

	private HttpStatus status;
	private GenericRespuesta rtaDTO;

	public GenericException(GenericRespuesta rta, HttpStatus badRequest) {
		this.status = badRequest;
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

	public GenericRespuesta getRtaDTO() {
		return rtaDTO;
	}

	public void setRtaDTO(GenericRespuesta rtaDTO) {
		this.rtaDTO = rtaDTO;
	}

}
