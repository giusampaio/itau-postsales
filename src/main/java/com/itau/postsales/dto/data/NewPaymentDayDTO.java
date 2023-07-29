package com.itau.postsales.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class NewPaymentDayDTO {

    @NotEmpty
    @JsonProperty("nova_data_pagamento")
    private Integer newPaymentDay;
}
