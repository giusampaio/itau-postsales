package com.itau.postsales.service;

import com.itau.postsales.dto.mapper.ContractAdditionRequestMapper;
import com.itau.postsales.dto.request.InstallmentsQuantityRequestDTO;
import com.itau.postsales.dto.response.CalculateFeeResponseDTO;
import com.itau.postsales.enums.CalculationType;
import com.itau.postsales.exception.BusinessException;
import com.itau.postsales.model.ContractAddition;
import com.itau.postsales.model.Financial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContractAdditionService {

    @Autowired
    ContractAdditionRequestMapper additionMapper;

    @Autowired
    ValidationService validationService;

    @Autowired
    FeesApiService feesService;

    public String changeQuantity(InstallmentsQuantityRequestDTO request) throws BusinessException {

        ContractAddition addition = this.getOrFail(request);
        CalculateFeeResponseDTO fee = this.getFee(addition);
        ContractAddition response = this.addQuantity(addition, fee);

        return response.toString();
    }

    private ContractAddition getOrFail(InstallmentsQuantityRequestDTO request) throws BusinessException {

        ContractAddition contractAddition = this.additionMapper.toEntity(request);
        this.validationService.validateInstallmentsQuantity(contractAddition);

        return contractAddition;
    }

    private CalculateFeeResponseDTO getFee(ContractAddition contractAddition) {
        return this.feesService.calculateTax(
                contractAddition.getAddition().getInstallmentsQuantity(),
                contractAddition.getFinancials().get(0).getTotalValue()
        );
    }

    private ContractAddition addQuantity(ContractAddition contractAddition, CalculateFeeResponseDTO fee) {

        List<Financial> financials = contractAddition.getFinancials();
        Integer installments = contractAddition.getAddition().getInstallmentsQuantity() - 2;
        Financial financial = financials.get(0);

        Double percent = fee.getData().getFeePercent();
        Double total = fee.getData().getTotalValue();

        Financial addition = new Financial(
                new Date(),                          //Date calculationDate,
                CalculationType.ADDITION.toString(), //String calculationType,
                total,                               //Double totalValue,
                installments,                        //Integer installmentsQuantity,
                financial.getInstallmentsValue(),    // String installmentsValue,
                financial.getPaymentDay(),           // Integer paymentDay,
                percent                              //Double interestRatePercentage
        );

        financials.add(addition);
        contractAddition.setFinancials(financials);

        return contractAddition;
    }

}
