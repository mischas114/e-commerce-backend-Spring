package com.example.ecommercebackend.exception;

import org.springframework.security.core.AuthenticationException;

public class InkorrektesPasswortException extends RuntimeException {
    public InkorrektesPasswortException(String msg) {
        super(msg);
    }
    public InkorrektesPasswortException(String msg, Throwable cause) {
        super(msg, cause);
    }
}