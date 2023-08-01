package com.itau.postsales.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import jakarta.servlet.http.HttpServletRequest;
import com.itau.postsales.model.ContractAddition;
import com.itau.postsales.PostSalesApplicationTests;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
public class ValidationServiceTest extends PostSalesApplicationTests {

    @Autowired
    private ValidationService validation;

    @Test
    @DisplayName("Deve receber uma nova data de pagamento OK e validar corretamente")
    public void validatePaymentDay() {

        ContractAddition contractAddition = this.getContractAdditionWithPayment();
        Boolean response = this.validation.isGreaterThanPaymentDay(contractAddition);

        assertThat(response).isFalse();
    }

    @Test
    @DisplayName("Deve receber uma nova data acima de 10 dias e validar corretamente")
    public void validatePaymentGreaterThan10Days() {

        ContractAddition contractAddition = this.getContractAdditionWithPayment();

        contractAddition.getAddition().setNewPaymentDay(30);
        contractAddition.getFinancials().get(0).setPaymentDay(1);

        Boolean response = this.validation.isGreaterThanPaymentDay(contractAddition);
        assertThat(response).isTrue();
    }

    @Test
    @DisplayName("Deve receber uma string no formato uuid e validar corretamente")
    public void validateUuidStringTest() {

        String headerUuidV1 = "a4ee61a4-3078-11ee-be56-0242ac120002";
        String headerUuidV4 = "49206f4e-c351-42c6-94f1-3d2645d2e1d2";
        String invalidUuid  = "Invalid-UUID";

        Boolean responseV1 = this.validation.isValidItauHeader(headerUuidV1);
        Boolean responseV4 = this.validation.isValidItauHeader(headerUuidV4);
        Boolean responseNo = this.validation.isValidItauHeader(invalidUuid);

        assertThat(responseV1).isTrue();
        assertThat(responseV4).isTrue();
        assertThat(responseNo).isFalse();
    }

    @Test
    @DisplayName("Deve receber um objeto header no formato uuid e validar corretamente")
    public void validateUuidHeaderTest() {

        HttpServletRequest request = this.getHeaderMock();
        Boolean responseV1 = this.validation.isValidItauHeader(request);

        assertThat(responseV1).isTrue();
    }
}