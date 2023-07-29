package com.itau.postsales.enums;

import lombok.Getter;

@Getter
public enum CalculationType {

    CONTRACTING("CONTRATACAO");

    private final String type;

    CalculationType(String type) {
        this.type = type;
    }
}
