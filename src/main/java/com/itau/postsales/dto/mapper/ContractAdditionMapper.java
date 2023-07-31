package com.itau.postsales.dto.mapper;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContractAdditionMapper {

    @Autowired
    private ContractAdditionRequestMapper requestMapper;

    @Autowired
    private ContractAdditionResponseMapper responseMapper;

    public ContractAdditionRequestMapper request() {
        return this.requestMapper;
    }

    public ContractAdditionResponseMapper response() {
        return this.responseMapper;
    }
}
