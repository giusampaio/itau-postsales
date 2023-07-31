package com.itau.postsales.service;

import com.itau.postsales.exception.BusinessException;
import com.itau.postsales.model.Addition;
import com.itau.postsales.model.ContractAddition;
import com.itau.postsales.model.Financial;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ValidationService {

    /**
     * Verifica as regras de negócio para executar a operação de
     * alteração de parcelas
     *
     * @throws BusinessException
     */
    public void validateInstallmentsQuantity(ContractAddition contractAddition) throws BusinessException {

        if (this.hasBothAddition(contractAddition)) {
            throw new BusinessException();
        }

        if (! this.isContractActivate(contractAddition)) {
            throw new BusinessException();
        }

        if (! this.isQuantityOfInstallmentsSmaller(contractAddition)) {
            throw new BusinessException();
        }
    }

    /**
     * Verifica se o contrato está ativo.
     * Em caso positivo, deve retornar true.
     *
     * @param addition
     * @return Boolean
     */
    public Boolean isContractActivate(ContractAddition addition) {
        return addition.getContract().getActive();
    }

    /**
     * Verifica se a quantidade de parcelas do contrato é menor
     * do que a quantidade de parcelas da requisição de aditamento.
     * Em caso positivo, deve retornar true.
     *
     * @param addition ContractAddition
     * @return Boolean
     */
    public Boolean isQuantityOfInstallmentsSmaller(ContractAddition addition) {

        Integer installments = addition.getAddition().getInstallmentsQuantity();
        Optional<Financial> item = addition.getFinancials().stream().findFirst();

        if (item.isEmpty()) return false;

        Financial financial = item.get();

        return financial.getInstallmentsQuantity() < installments;
    }

    /**
     * Verifica se na requisição possui duas chamadas de aditamento.
     * Se possuir, deve retornar true.
     *
     * @param contractAddition
     * @return Boolean
     */
    public Boolean hasBothAddition(ContractAddition contractAddition)  {

        Addition addition = contractAddition.getAddition();
        Integer installments = addition.getInstallmentsQuantity();
        Integer payment = addition.getNewPaymentDay();

        return installments != null && payment != null;
    }
}
