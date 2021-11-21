package com.camposoft.exceptions;

public class TecnicalException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TecnicalException(String msg, Throwable e) {
        super(msg, e);
    }
}