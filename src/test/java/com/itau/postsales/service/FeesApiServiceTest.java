package com.itau.postsales.service;


import com.itau.postsales.dto.data.ContractFeeRequestDTO;
import com.itau.postsales.dto.response.CalculateFeeResponseDTO;
import com.itau.postsales.enums.FeeCalculationType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class FeesApiServiceTest {

    @Autowired
    private FeesApiService service;

    @Test
    @DisplayName("Teste do corpo da requisição para API de Juros")
    public void requestBodyTest() {

        Date date = new Date();
        Double value = 50000.00;

        Integer quantity = 54;
        FeeCalculationType type = FeeCalculationType.SIMPLE_TAX;

        ContractFeeRequestDTO received = service.getContractRequest(quantity, value);
        ContractFeeRequestDTO expected = new ContractFeeRequestDTO(date, type, quantity, value);

        assertThat(received).isEqualTo(expected);
    }

    @Test
    public void requestFees() {

        CalculateFeeResponseDTO response = this.service.calculateTax(54, 50000.00);

        System.out.println(response.toString());
    }
}
