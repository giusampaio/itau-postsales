package com.itau.postsales.service;

import com.itau.postsales.client.FeesApiClient;
import com.itau.postsales.dto.data.ContractFeeRequestDTO;
import com.itau.postsales.dto.request.CalculateFeeRequestDTO;
import com.itau.postsales.dto.response.CalculateFeeResponseDTO;
import com.itau.postsales.enums.FeeCalculationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FeesApiService {

    @Autowired
    FeesApiClient feesClient;

    public CalculateFeeResponseDTO calculateTax(Integer quantity, Double value) {
        CalculateFeeRequestDTO request = this.getRequest(quantity, value);
        return this.feesClient.feeCalculation(request);
    }

    public CalculateFeeRequestDTO getRequest(Integer quantity, Double value) {
        return new CalculateFeeRequestDTO(this.getContractRequest(quantity, value));
    }

    public ContractFeeRequestDTO getContractRequest(Integer quantity, Double value) {
        return new ContractFeeRequestDTO(
                new Date(),
                FeeCalculationType.SIMPLE_TAX,
                quantity,
                value
        );
    }

    private void requestApi() {

    }
}
