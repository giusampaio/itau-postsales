package com.itau.postsales.dto.request;


import lombok.Data;
import java.util.List;
import com.itau.postsales.dto.data.FinancialDTO;
import com.itau.postsales.dto.data.PaymentDayDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.itau.postsales.dto.data.ContractRequestDTO;

@Data
public class PaymentDayChangeRequestDTO {

    @JsonProperty("contrato")
    private ContractRequestDTO contract;

    @JsonProperty("financeiro")
    private List<FinancialDTO> financials;

    @JsonProperty("aditamento")
    private PaymentDayDTO addition;
}
