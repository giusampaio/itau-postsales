package com.itau.postsales.service;


import java.util.Date;
import java.util.List;
import com.itau.postsales.model.Financial;
import org.springframework.stereotype.Service;
import com.itau.postsales.enums.CalculationType;
import com.itau.postsales.model.ContractAddition;
import com.itau.postsales.exception.BusinessException;
import com.itau.postsales.dto.mapper.ContractAdditionMapper;
import com.itau.postsales.dto.response.CalculateFeeResponseDTO;
import com.itau.postsales.dto.request.InstallmentsQuantityRequestDTO;
import com.itau.postsales.dto.response.InstallmentsQuantityResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ContractAdditionService {

    @Autowired
    private ContractAdditionMapper mapper;

    @Autowired
    private ValidationService validation;

    @Autowired
    private FeesApiService feesApi;

    public InstallmentsQuantityResponseDTO changeQuantity(
            InstallmentsQuantityRequestDTO request
    ) throws BusinessException {

        ContractAddition addition = this.getOrFail(request);
        ContractAddition response = this.addQuantity(addition);

        return this.mapper.response().toResponse(response);
    }

    private ContractAddition getOrFail(InstallmentsQuantityRequestDTO request) throws BusinessException {

        ContractAddition contractAddition = this.mapper.request().toEntity(request);
        this.validation.validateInstallmentsQuantity(contractAddition);

        return contractAddition;
    }

    private CalculateFeeResponseDTO getFee(ContractAddition contractAddition) {
        return this.feesApi.calculateTax(
                contractAddition.getAddition().getInstallmentsQuantity(),
                contractAddition.getFinancials().get(0).getTotalValue()
        );
    }

    private ContractAddition addQuantity(ContractAddition contractAddition) {

        List<Financial> financials = contractAddition.getFinancials();
        Financial financial = financials.get(0);

        Financial addition = this.getFinancial(financial, contractAddition);
        financials.add(addition);

        contractAddition.setFinancials(financials);
        return contractAddition;
    }

    private Integer getInstallmentsQuantity(ContractAddition addition) {
        return addition.getAddition().getInstallmentsQuantity() - 2;
    }

    private Financial getFinancial(Financial financial, ContractAddition addition) {

        CalculateFeeResponseDTO fee = this.getFee(addition);

        return new Financial(
                new Date(),
                CalculationType.ADDITION.toString(),
                fee.getData().getTotalValue(),
                this.getInstallmentsQuantity(addition),
                financial.getInstallmentsValue(),
                financial.getPaymentDay(),
                fee.getData().getFeePercent()
        );
    }
}