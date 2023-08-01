package com.itau.postsales.exception;

import com.itau.postsales.enums.BusinessErrorMessage;

public class BusinessException extends RuntimeException {

    public BusinessException(BusinessErrorMessage message) {
        super("Erro de violação de regra de negócio: " + message.toString());
    }
}
