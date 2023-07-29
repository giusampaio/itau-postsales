package com.itau.postsales.client;

import com.itau.postsales.dto.request.CalculateFeeRequestDTO;
import com.itau.postsales.dto.response.CalculateFeeResponseDTO;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="FeesApiClient", url="${client.fees.api.url}")
@Headers("itau-pos-venda-teste: 1123")
public interface FeesApiClient {

    @PostMapping(value = "/calculo-juros", consumes = MediaType.APPLICATION_JSON_VALUE)
    CalculateFeeResponseDTO feeCalculation(CalculateFeeRequestDTO request);
}
