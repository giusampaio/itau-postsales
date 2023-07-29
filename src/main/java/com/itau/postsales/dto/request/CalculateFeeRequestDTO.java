package com.itau.postsales.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itau.postsales.dto.data.ContractFeeRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CalculateFeeRequestDTO {

    @JsonProperty("contrato")
    private ContractFeeRequestDTO contract;
}
