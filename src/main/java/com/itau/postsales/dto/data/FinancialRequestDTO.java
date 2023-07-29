package com.itau.postsales.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FinancialRequestDTO {

    @JsonProperty("data_calculo")
    private String calculationDate;

    @JsonProperty("tipo_calculo")
    private String calculationType;

    @JsonProperty("valor_total")
    private Float totalValue;

    @JsonProperty("quantidade_parcelas")
    private Integer installmentsQuantity;

    @JsonProperty("valor_parcelas")
    private String installmentsValue;

    @JsonProperty("dia_pagamento")
    private Integer paymentDay;

    @JsonProperty("percentual_taxa_juro")
    private Float interestRatePercentage;
}
