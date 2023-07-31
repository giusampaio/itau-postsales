package com.itau.postsales.dto.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class FinancialDTO {

    @JsonProperty("data_calculo")
    @JsonFormat(pattern="yyyy-MM-dd", timezone="America/Sao_Paulo")
    private Date calculationDate;

    @JsonProperty("tipo_calculo")
    private String calculationType;

    @JsonProperty("valor_total")
    private Double totalValue;

    @JsonProperty("quantidade_parcelas")
    private Integer installmentsQuantity;

    @JsonProperty("valor_parcelas")
    private String installmentsValue;

    @JsonProperty("dia_pagamento")
    private Integer paymentDay;

    @JsonProperty("percentual_taxa_juro")
    private Double interestRatePercentage;
}