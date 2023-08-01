package com.itau.postsales.service;

import com.itau.postsales.client.FeesApiClient;
import com.itau.postsales.dto.data.ContractFeeRequestDTO;
import com.itau.postsales.dto.data.FeeCalculationDTO;
import com.itau.postsales.dto.request.CalculateFeeRequestDTO;
import com.itau.postsales.dto.response.CalculateFeeResponseDTO;
import com.itau.postsales.enums.FeeCalculationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FeesApiService {

    @Autowired
    private FeesApiClient feesClient;

    public CalculateFeeResponseDTO calculateTax(Integer quantity, Double value) {

        CalculateFeeRequestDTO request = this.getRequest(quantity, value);

        return this.getMockResponse();
        // return this.feesClient.feeCalculation(request); // Chamada Feign Client
    }

    private CalculateFeeRequestDTO getRequest(Integer quantity, Double value) {
        ContractFeeRequestDTO request = this.getContractRequest(quantity, value);
        return new CalculateFeeRequestDTO(request);
    }

    private CalculateFeeResponseDTO getMockResponse() {

        CalculateFeeResponseDTO response = new CalculateFeeResponseDTO();
        FeeCalculationDTO data = new FeeCalculationDTO();

        data.setFeePercent(1.93);
        data.setTotalValue(52000.00);

        response.setData(data);
        return response;
    }

    private ContractFeeRequestDTO getContractRequest(Integer quantity, Double value) {
        return new ContractFeeRequestDTO(
                new Date(),
                FeeCalculationType.SIMPLE_TAX,
                quantity,
                value
        );
    }
}
