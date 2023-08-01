package com.itau.postsales.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import com.itau.postsales.dto.data.FinancialDTO;
import jakarta.servlet.http.HttpServletRequest;
import com.itau.postsales.PostSalesApplicationTests;
import com.itau.postsales.dto.data.ContractResponseDTO;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import com.itau.postsales.dto.response.ContractAdditionResponseDTO;
import com.itau.postsales.dto.request.InstallmentsQuantityRequestDTO;

import java.util.List;

@SpringBootTest
public class InstallmentsQuantityServiceTest extends PostSalesApplicationTests {

    @Autowired
    private InstallmentsQuantityService service;

    @Test
    @DisplayName("Deve receber uma requisição de alteração de quantidade de pagamento")
    public void requestTest() {

        HttpServletRequest headers = this.getHeaderMock();
        InstallmentsQuantityRequestDTO request = this.installmentRequest();
        ContractAdditionResponseDTO response = this.service.changeQuantity(request, headers);

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
        assertThat(additionFinancial.getTotalValue()).isEqualTo(52000.00);
        assertThat(additionFinancial.getInstallmentsQuantity()).isEqualTo(54);
        assertThat(additionFinancial.getInstallmentsValue()).isEqualTo("1000.00");
        assertThat(additionFinancial.getPaymentDay()).isEqualTo(23);
        assertThat(additionFinancial.getInterestRatePercentage()).isEqualTo(1.93);
    }
}
