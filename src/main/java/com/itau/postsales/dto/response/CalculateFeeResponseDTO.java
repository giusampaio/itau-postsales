package com.itau.postsales.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itau.postsales.dto.data.ContractFeeRequestDTO;
import com.itau.postsales.dto.data.FeeCalculationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CalculateFeeResponseDTO {

    @JsonProperty("data")
    private FeeCalculationDTO data;
}
