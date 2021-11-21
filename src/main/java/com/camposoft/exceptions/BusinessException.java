package com.camposoft.exceptions;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BusinessException(String msg, Throwable e) {
        super(msg, e);
    }

    public BusinessException(String msg) {
        super(msg);
    }
}
