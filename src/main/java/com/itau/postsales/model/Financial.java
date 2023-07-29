package com.itau.postsales.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Financial {

    private String calculationDate;

    private String calculationType;

    private Float totalValue;

    private Integer installmentsQuantity;

    private String installmentsValue;

    private Integer paymentDay;

    private Float interestRatePercentage;
}