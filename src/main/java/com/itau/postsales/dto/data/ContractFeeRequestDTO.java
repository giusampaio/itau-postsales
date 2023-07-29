package com.itau.postsales.dto.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.itau.postsales.enums.FeeCalculationType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ContractFeeRequestDTO {

    @JsonProperty("definir_data_contratacao")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date contractingDate;

    @JsonProperty("definir_criterio_calculo")
    private FeeCalculationType typeCalculation;

    @JsonProperty("definir_quantidade_parcelas")
    private Integer installmentQuantity;

    @JsonProperty("definir_valor_contratacao")
    private Double contractingValue;
}
