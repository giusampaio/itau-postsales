package com.itau.postsales.dto.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Date;

@Data
public class ContractRequestDTO {

    @NotEmpty
    @JsonProperty("id_contrato")
    private Integer contractId;

    @NotEmpty
    @JsonProperty("numero_cpf_cnpj_cliente")
    private String customerCpfCnpj;

    @NotEmpty
    @JsonProperty("data_contratacao")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date contractingDate;

    @NotEmpty
    @JsonProperty("ativo")
    private Boolean active;

    @NotEmpty
    @JsonProperty("parcelas_em_atraso")
    private Boolean overdueInstallments;
}
