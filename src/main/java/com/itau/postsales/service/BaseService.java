package com.itau.postsales.service;

import com.itau.postsales.dto.mapper.ContractAdditionMapper;
import com.itau.postsales.model.ContractAddition;
import com.itau.postsales.model.Financial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseService {

    @Autowired
    protected ContractAdditionMapper mapper;

    @Autowired
    protected ValidationService validation;

    @Autowired
    protected FeesApiService feesApi;

    protected Financial getContractFinancial(ContractAddition contractAddition) {
        List<Financial> financials = contractAddition.getFinancials();
        return financials.get(0);
    }
}
