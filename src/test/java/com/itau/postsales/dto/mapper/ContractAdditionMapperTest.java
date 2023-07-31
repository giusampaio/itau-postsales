package com.itau.postsales.dto.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ContractAdditionMapperTest {

    @Autowired
    private ContractAdditionMapper mapper;

    @Test
    @DisplayName("Deve")
    public void getResponseMapper() {
        ContractAdditionResponseMapper response = this.response();
    }

    private ContractAdditionResponseMapper response() {
        return this.mapper.response();
    }
}
