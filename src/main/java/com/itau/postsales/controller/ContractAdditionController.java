package com.itau.postsales.controller;

import com.itau.postsales.dto.request.PaymentDayChangeRequestDTO;
import com.itau.postsales.dto.response.ContractAdditionResponseDTO;
import com.itau.postsales.service.PaymentDayChangeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import com.itau.postsales.dto.request.InstallmentsQuantityRequestDTO;
import com.itau.postsales.service.InstallmentsQuantityService;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/aditamento")
public class ContractAdditionController {

    @Autowired
    private InstallmentsQuantityService installment;

    @Autowired
    private PaymentDayChangeService payment;

    @PostMapping("/altera-quantidade-parcelas")
    public @ResponseBody ContractAdditionResponseDTO changeInstallmentsQuantity(
            @RequestBody InstallmentsQuantityRequestDTO request,
            HttpServletRequest headers
    ) {
        return this.installment.changeQuantity(request, headers);
    }

    @PostMapping("/altera-dia-pagamento")
    public @ResponseBody ContractAdditionResponseDTO changePaymentDay(
            @RequestBody PaymentDayChangeRequestDTO request,
            HttpServletRequest headers
    ) {
        return this.payment.changePaymentDay(request, headers);
    }
}
