package com.itau.postsales.dto.request;

import java.util.List;

import com.itau.postsales.dto.data.ContractRequestDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.itau.postsales.dto.data.FinancialDTO;
import com.itau.postsales.dto.data.InstallmentsQuantityDTO;
import lombok.*;

@Data
public class InstallmentsQuantityRequestDTO {

    @JsonProperty("contrato")
    private ContractRequestDTO contract;

    @JsonProperty("financeiro")
    private List<FinancialDTO> financials;

    @JsonProperty("aditamento")
    private InstallmentsQuantityDTO addition;
}