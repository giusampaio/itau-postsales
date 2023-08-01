package com.itau.postsales.service;

import org.mockito.Mockito;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import com.itau.postsales.dto.data.FeeCalculationDTO;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import com.itau.postsales.dto.response.CalculateFeeResponseDTO;

@SpringBootTest
public class FeesApiServiceTest {

    @Autowired
    private FeesApiService service;

    @Test
    @DisplayName("Deve retornar o API do Juros Itaú")
    public void requestFeesApiTest() {

        CalculateFeeResponseDTO received = this.service.calculateTax(54, 50000.00);
        CalculateFeeResponseDTO expected = Mockito.mock(CalculateFeeResponseDTO.class);
        FeeCalculationDTO data = Mockito.mock(FeeCalculationDTO.class);

        Mockito.when(data.getFeePercent()).thenReturn(1.93);
        Mockito.when(data.getTotalValue()).thenReturn(52000.00);
        Mockito.when(expected.getData()).thenReturn(data);

        assertThat(received.getData().getFeePercent()).isEqualTo(expected.getData().getFeePercent());
        assertThat(received.getData().getTotalValue()).isEqualTo(expected.getData().getTotalValue());
    }
}
