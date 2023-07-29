package com.itau.postsales.service;

import com.itau.postsales.dto.mapper.ContractAdditionRequestMapper;
import com.itau.postsales.dto.request.InstallmentsQuantityRequestDTO;
import com.itau.postsales.exception.BusinessException;
import com.itau.postsales.model.ContractAddition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractAdditionService {

    @Autowired
    ContractAdditionRequestMapper additionMapper;

    @Autowired
    ValidationService validationService;

    public String changeQuantity(InstallmentsQuantityRequestDTO request) throws BusinessException {

        ContractAddition addition = this.additionMapper.toEntity(request);

        this.validationService.validateInstallmentsQuantity(addition);

        return "";
    }
}
