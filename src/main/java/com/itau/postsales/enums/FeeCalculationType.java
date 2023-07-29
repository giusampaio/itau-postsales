package com.itau.postsales.enums;

import lombok.Getter;

@Getter
public enum FeeCalculationType {

    SIMPLE_TAX("JUROS_SIMPLES");

    private final String type;

    FeeCalculationType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
