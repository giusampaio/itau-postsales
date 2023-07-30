package com.itau.postsales.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FeeCalculationDTO {

    @JsonProperty("percentual_juros")
    private Double feePercent;

    @JsonProperty("valor_total")
    private Double totalValue;
}
