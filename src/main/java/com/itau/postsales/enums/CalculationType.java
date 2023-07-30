package com.itau.postsales.enums;

import lombok.Getter;

@Getter
public enum CalculationType {

    CONTRACTING("CONTRATACAO"),
    ADDITION("ADITAMENTO");

    private final String type;

    CalculationType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
