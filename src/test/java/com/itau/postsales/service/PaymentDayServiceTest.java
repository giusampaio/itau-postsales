package com.itau.postsales.service;

import com.itau.postsales.PostSalesApplicationTests;
import com.itau.postsales.dto.data.ContractResponseDTO;
import com.itau.postsales.dto.data.FinancialDTO;
import com.itau.postsales.dto.request.PaymentDayChangeRequestDTO;
import com.itau.postsales.dto.response.ContractAdditionResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PaymentDayServiceTest extends PostSalesApplicationTests {

    @Autowired
    private PaymentDayChangeService service;

    @Test
    @DisplayName("Deve receber uma requisição de alteração de dia de pagamento")
    public void requestTest() {

        HttpServletRequest headers = this.getHeaderMock();
        PaymentDayChangeRequestDTO request = this.paymentDayRequest();
        ContractAdditionResponseDTO response = this.service.changePaymentDay(request, headers);

        this.assertContract(response.getContract());
        this.assertFinancials(response.getFinancials());
    }

    private void assertContract(ContractResponseDTO contract) {
        assertThat(contract.getContractId()).isEqualTo(3795);
        assertThat(contract.getContractLastDigit()).isEqualTo(9);
        assertThat(contract.getCustomerCpfCnpj()).isEqualTo("66273815089");
        assertThat(contract.getOverdueInstallments()).isEqualTo(false);
        assertThat(contract.getActive()).isEqualTo(true);
    }

    private void assertFinancials(List<FinancialDTO> financials) {
        FinancialDTO contractFinancial = financials.get(0);
        FinancialDTO additionFinancial = financials.get(1);

        assertThat(contractFinancial.getCalculationType()).isEqualTo("CONTRATACAO");
        assertThat(contractFinancial.getTotalValue()).isEqualTo(50000.00);
        assertThat(contractFinancial.getInstallmentsQuantity()).isEqualTo(50);
        assertThat(contractFinancial.getInstallmentsValue()).isEqualTo("1000.00");
        assertThat(contractFinancial.getPaymentDay()).isEqualTo(23);
        assertThat(contractFinancial.getInterestRatePercentage()).isEqualTo(1.99);

        assertThat(additionFinancial.getCalculationType()).isEqualTo("ADITAMENTO");
        assertThat(additionFinancial.getTotalValue()).isEqualTo(50000.00);
        assertThat(additionFinancial.getInstallmentsQuantity()).isEqualTo(50);
        assertThat(additionFinancial.getInstallmentsValue()).isEqualTo("1000.00");
        assertThat(additionFinancial.getPaymentDay()).isEqualTo(15);
        assertThat(additionFinancial.getInterestRatePercentage()).isEqualTo(1.99);
    }
}
