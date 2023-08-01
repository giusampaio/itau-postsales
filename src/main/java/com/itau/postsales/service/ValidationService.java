package com.itau.postsales.service;

import com.itau.postsales.enums.BusinessErrorMessage;
import com.itau.postsales.exception.BusinessException;
import com.itau.postsales.model.Addition;
import com.itau.postsales.model.Contract;
import com.itau.postsales.model.ContractAddition;
import com.itau.postsales.model.Financial;
import jakarta.servlet.http.HttpServletRequest;
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
    public void validateInstallmentsQuantity(
            ContractAddition contractAddition,
            HttpServletRequest headers
    ) throws BusinessException {

        if (! this.isValidItauHeader(headers)) {
            throw new BusinessException(BusinessErrorMessage.INVALID_HEADER);
        }

        if (this.hasBothAddition(contractAddition)) {
            throw new BusinessException(BusinessErrorMessage.BOTH_ADDITION);
        }

        if (! this.isContractActivate(contractAddition)) {
            throw new BusinessException(BusinessErrorMessage.INACTIVE_CONTRACT);
        }

        if (! this.isQuantityOfInstallmentsSmaller(contractAddition)) {
            throw new BusinessException(BusinessErrorMessage.SMALL_INSTALLMENTS_QUANTITY);
        }
    }

    /**
     * Verifica se as regras de negócio para executar a operação de
     * nova data de pagamento.
     *
     * @param contractAddition
     * @throws BusinessException
     */
    public void validatePaymentDay(
            ContractAddition contractAddition,
            HttpServletRequest headers
    ) throws BusinessException {

        if (! this.isValidItauHeader(headers)) {
            throw new BusinessException(BusinessErrorMessage.INVALID_HEADER);
        }

        if (this.hasOverdueInstallments(contractAddition)) {
            throw new BusinessException(BusinessErrorMessage.OVERDUE_INSTALLMENTS);
        }

        if (! this.isContractActivate(contractAddition)) {
            throw new BusinessException(BusinessErrorMessage.INACTIVE_CONTRACT);
        }

        if (this.isGreaterThanPaymentDay(contractAddition)) {
            throw new BusinessException(BusinessErrorMessage.BIG_PAYMENTDAY);
        }
    }

    /**
     * Verifica se a nova data de pagamento é 10 dias superior do dia atual.
     * @param contractAddition
     * @return Boolean
     */
    public Boolean isGreaterThanPaymentDay(ContractAddition contractAddition) {

        Addition addition = contractAddition.getAddition();
        Financial financial = contractAddition.getFinancials().get(0);

        return addition.getNewPaymentDay() > financial.getPaymentDay() + 10;
    }

    /**
     * Verifica se o header itaú enviado é valido.
     * @param header
     * @returnBoolean
     */
    public Boolean isValidItauHeader(String header) {
       return this.checkUUID(header);
    }

    /**
     * Verifica se o header itaú enviado é valido.
     * @param header
     * @returnBoolean
     */
    public Boolean isValidItauHeader(HttpServletRequest header) {
        return this.checkUUID(header.getHeader("itau-pos-venda-teste"));
    }

    private Boolean checkUUID(String uuid) {
        String pattern = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}";
        return uuid != null && uuid.matches(pattern);
    }

    /**
     * Verifica se o contrato tem parcelas em atraso.
     * Em caso positivo, deve retornar true.
     *
     * @param contractAddition
     * @return Boolean
     */
    public Boolean hasOverdueInstallments(ContractAddition contractAddition) {
        return contractAddition.getContract().getOverdueInstallments();
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
