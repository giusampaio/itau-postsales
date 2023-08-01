package com.itau.postsales.enums;

import lombok.Getter;

@Getter
public enum BusinessErrorMessage {

    INVALID_HEADER("Header itau-pos-venda-teste é nulo ou inválido."),
    BOTH_ADDITION("Não é permitido realizar simultaneamente os dois aditamentos"),
    INACTIVE_CONTRACT("O contrato está inativo."),
    OVERDUE_INSTALLMENTS("O contrato possui parcelas em atraso"),
    SMALL_INSTALLMENTS_QUANTITY("Quantidade de parcelas não pode ser inferior à atual"),
    BIG_PAYMENTDAY("Dia de pagamento não pode estar mais que 10 dias adiante do dia atual de pagamento");

    private final String message;

    BusinessErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
