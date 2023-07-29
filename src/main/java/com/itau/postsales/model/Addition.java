package com.itau.postsales.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Addition {

    private Integer installmentsQuantity;

    private Integer newPaymentDay;
}
