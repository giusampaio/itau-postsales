package com.itau.postsales.dto.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itau.postsales.dto.data.FinancialDTO;
import com.itau.postsales.enums.CalculationType;
import com.itau.postsales.model.ContractAddition;
import com.itau.postsales.model.Financial;
import org.junit.jupiter.api.Test;
import com.itau.postsales.model.Contract;
import org.junit.jupiter.api.DisplayName;
import com.itau.postsales.dto.data.ContractResponseDTO;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
public class ContractAdditionResponseMapperTest {

    @Autowired
    private ContractAdditionResponseMapper response;

    @Test
    @DisplayName("Deve receber os dados do contrato e deve retornar o dto de resposta")
    public void getContractResponseDtoTest() {

        ContractAddition contractAddition = this.getContractAddition();
        ContractResponseDTO received = this.response.toContractResponse(contractAddition);

        assertThat(received.getContractLastDigit()).isEqualTo(3);
        assertThat(received.getActive()).isEqualTo(true);
        assertThat(received.getContractId()).isEqualTo(123);
    }

    @Test
    @DisplayName("Deve receber os dados financeiro e deve retornar o dto de resposta")
    public void getFinancialResponseDtoTest() {

    }

    private ContractAddition getContractAddition() {

        List<Financial> financials = new ArrayList<Financial>();

        financials.add(this.getFinancial());
        Contract contract = this.getContract();

        return new ContractAddition(contract, financials,null);
    }

    private Contract getContract() {
        return new Contract(
                123,
                "40004348000171",
                new Date(),
                true,
                false
        );
    }

    private Financial getFinancial() {
        return new Financial(
                new Date(),
                CalculationType.CONTRACTING.toString(),
                10000.00,
                54,
                "950.00",
                5,
                1.99
        );
    }
}
