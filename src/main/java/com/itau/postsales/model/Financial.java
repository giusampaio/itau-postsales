package com.itau.postsales.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Financial  {

    private Date calculationDate;

    private String calculationType;

    private Double totalValue;

    private Integer installmentsQuantity;

    private String installmentsValue;

    private Integer paymentDay;

    private Double interestRatePercentage;
}