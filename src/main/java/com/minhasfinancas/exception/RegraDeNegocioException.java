package com.minhasfinancas.exception;

import org.springframework.http.HttpStatus;

public class RegraDeNegocioException extends RuntimeException {
 
    private static final long serialVersionUID = 1L;

    public RegraDeNegocioException(HttpStatus badRequest, String msg){
        super(msg);
    }

}
