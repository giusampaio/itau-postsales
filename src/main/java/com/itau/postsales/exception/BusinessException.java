package com.itau.postsales.exception;

public class BusinessException extends RuntimeException {

    public BusinessException() {
        super("Registro não encontrado com o id: ");
    }
}
