package com.itau.postsales.dto.mapper;

import com.itau.postsales.dto.data.ContractRequestDTO;
import com.itau.postsales.dto.data.FinancialRequestDTO;
import com.itau.postsales.dto.data.InstallmentsQuantityDTO;
import com.itau.postsales.dto.data.NewPaymentDayDTO;
import com.itau.postsales.dto.request.InstallmentsQuantityRequestDTO;
import com.itau.postsales.model.Addition;
import com.itau.postsales.model.Contract;
import com.itau.postsales.model.ContractAddition;
import com.itau.postsales.model.Financial;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContractAdditionRequestMapper {

    public ContractAddition toEntity(InstallmentsQuantityRequestDTO request) {
        return new ContractAddition(
                this.toContract(request.getContract()),
                this.toFinancials(request.getFinancials()),
                this.toAddition(request.getAddition())
        );
    }

    private Contract toContract(ContractRequestDTO contract) {
        return new Contract(
                contract.getContractId(),
                contract.getCustomerCpfCnpj(),
                contract.getContractingDate(),
                contract.getActive(),
                contract.getOverdueInstallments()
        );
    }

    private List<Financial> toFinancials(List<FinancialRequestDTO> financials) {
        return financials
                .stream()
                .map(this::toFinancial)
                .collect(Collectors.toList());
    }

    private Financial toFinancial(FinancialRequestDTO request) {
        return new Financial(
                request.getCalculationDate(),
                request.getCalculationType(),
                request.getTotalValue(),
                request.getInstallmentsQuantity(),
                request.getInstallmentsValue(),
                request.getPaymentDay(),
                request.getInterestRatePercentage()
        );
    }

    private Addition toAddition(InstallmentsQuantityDTO request) {
        return new Addition(
                request.getInstallmentsQuantity(),
                null
        );
    }

    private Addition toAddition(NewPaymentDayDTO request) {
        return new Addition(
                null,
                request.getNewPaymentDay()
        );
    }
}
