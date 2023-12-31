package com.itau.postsales.dto.mapper;

import com.itau.postsales.dto.data.ContractResponseDTO;
import com.itau.postsales.dto.data.FinancialDTO;
import com.itau.postsales.dto.response.ContractAdditionResponseDTO;
import com.itau.postsales.model.Contract;
import com.itau.postsales.model.ContractAddition;
import com.itau.postsales.model.Financial;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContractAdditionResponseMapper {

    public ContractAdditionResponseDTO toResponse(ContractAddition contractAddition) {

        ContractAdditionResponseDTO response = new ContractAdditionResponseDTO();

        List<FinancialDTO> financials = this.toFinancialDtoList(contractAddition);
        ContractResponseDTO contract = this.toContractResponse(contractAddition);

        response.setContract(contract);
        response.setFinancials(financials);

        return response;
    }

    public ContractResponseDTO toContractResponse(ContractAddition contractAddition) {
        Contract contract = contractAddition.getContract();
        return new ContractResponseDTO(
                this.getContractId(contract),
                this.getContractLastDigit(contract),
                contract.getContractDate(),
                contract.getCustomerCpfCnpj(),
                contract.getActive(),
                contract.getOverdueInstallments()
        );
    }

    private List<FinancialDTO> toFinancialDtoList(ContractAddition contractAddition) {
        return contractAddition
                .getFinancials()
                .stream()
                .map(this::toFinancialDTO)
                .collect(Collectors.toList());
    }

    private FinancialDTO toFinancialDTO(Financial financial) {
        FinancialDTO dto = new FinancialDTO();

        dto.setTotalValue(financial.getTotalValue());
        dto.setCalculationDate(financial.getCalculationDate());
        dto.setCalculationType(financial.getCalculationType());
        dto.setInstallmentsValue(financial.getInstallmentsValue());
        dto.setInstallmentsQuantity(financial.getInstallmentsQuantity());
        dto.setInterestRatePercentage(financial.getInterestRatePercentage());
        dto.setInterestRatePercentage(financial.getInterestRatePercentage());
        dto.setPaymentDay(financial.getPaymentDay());

        return dto;
    }

    private Integer getContractLastDigit(Contract contract) {
        String number = contract.getContractId().toString();
        String digit = StringUtils.right(number, 1);
        return Integer.parseInt(digit);
    }

    private Integer getContractId(Contract contract) {
        String number = contract.getContractId().toString();
        String contractId = StringUtils.left(number, number.length() - 1);
        return Integer.parseInt(contractId);
    }
}
