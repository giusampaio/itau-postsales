package com.itau.postsales.exception;

public class BusinessException extends RuntimeException {

    public BusinessException() {
        super("Erro de violação de regra de negócio. " +
                "Verifique os dados da requisição e tente novamente");
    }
}
