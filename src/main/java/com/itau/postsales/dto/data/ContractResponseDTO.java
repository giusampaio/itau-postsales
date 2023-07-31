package com.itau.postsales.dto.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@Data
@AllArgsConstructor
public class ContractResponseDTO {
    
    @JsonProperty("id_contrato")
    private Integer contractId;
    
    @JsonProperty("ultimo_digito_contrato")
    private Integer contractLastDigit;

    @JsonProperty("data_contratacao")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date contractingDate;
    
    @JsonProperty("numero_cpf_cnpj_cliente")
    private String customerCpfCnpj;
    
    @JsonProperty("ativo")
    private Boolean active;
    
    @JsonProperty("parcelas_em_atraso")
    private Boolean overdueInstallments;
}
