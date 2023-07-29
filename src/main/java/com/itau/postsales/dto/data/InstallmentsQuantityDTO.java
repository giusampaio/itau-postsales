package com.itau.postsales.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
public class InstallmentsQuantityDTO {

    @NotEmpty
    @JsonProperty("nova_quantidade_parcelas")
    private Integer installmentsQuantity;
}
