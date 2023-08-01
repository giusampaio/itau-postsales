package com.itau.postsales.service;

import java.util.Date;
import com.itau.postsales.model.Addition;
import com.itau.postsales.model.Financial;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import com.itau.postsales.enums.CalculationType;
import com.itau.postsales.model.ContractAddition;
import com.itau.postsales.exception.BusinessException;
import com.itau.postsales.dto.request.PaymentDayChangeRequestDTO;
import com.itau.postsales.dto.response.ContractAdditionResponseDTO;


@Service
public class PaymentDayChangeService extends BaseService {

    public ContractAdditionResponseDTO changePaymentDay(
            PaymentDayChangeRequestDTO request,
            HttpServletRequest headers
    ) throws BusinessException {

        this.validation.isValidItauHeader(headers);

        ContractAddition contractAddition = this.getOrFail(request);
        Addition addition = contractAddition.getAddition();
        Integer paymentDay = addition.getNewPaymentDay();

        Financial contract = this.getContractFinancial(contractAddition);
        Financial financial = this.getFinancial(contract, paymentDay);
        contractAddition.getFinancials().add(financial);

        return this.mapper.response().toResponse(contractAddition);
    }

    private ContractAddition getOrFail(PaymentDayChangeRequestDTO request) {

        ContractAddition contractAddition = this.mapper.request().toEntity(request);
        this.validation.validatePaymentDay(contractAddition);

        return contractAddition;
    }

    private Financial getFinancial(Financial financial, Integer paymentDay) {
        return new Financial(
                new Date(),
                CalculationType.ADDITION.toString(),
                financial.getTotalValue(),
                financial.getInstallmentsQuantity(),
                financial.getInstallmentsValue(),
                paymentDay,
                financial.getInterestRatePercentage()
        );
    }
}
