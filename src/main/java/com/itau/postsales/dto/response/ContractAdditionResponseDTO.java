package com.itau.postsales.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itau.postsales.dto.data.ContractResponseDTO;
import com.itau.postsales.dto.data.FinancialDTO;
import lombok.Data;
import java.util.List;

@Data
public class ContractAdditionResponseDTO {

    @JsonProperty("contrato")
    private ContractResponseDTO contract;

    @JsonProperty("financeiro")
    private List<FinancialDTO> financials;
}
