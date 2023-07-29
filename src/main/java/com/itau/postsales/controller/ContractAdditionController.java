package com.itau.postsales.controller;

import org.springframework.web.bind.annotation.*;
import com.itau.postsales.dto.mapper.ContractAdditionRequestMapper;
import com.itau.postsales.dto.request.InstallmentsQuantityRequestDTO;
import com.itau.postsales.service.ContractAdditionService;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/aditamento")
public class ContractAdditionController {

    @Autowired
    private ContractAdditionService changeService;

    @Autowired
    private ContractAdditionRequestMapper mapper;

    @PostMapping
    public @ResponseBody String changeInstallmentsQuantity(
            @RequestBody InstallmentsQuantityRequestDTO request
    ) {
        return this.changeService.changeQuantity(request);
    }
}
