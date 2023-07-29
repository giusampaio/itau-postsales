package com.itau.postsales.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Contract {

    private Integer contractId;

    private String customerCpfCnpj;

    private String contractDate;

    private Boolean active;

    private Boolean overdueInstallments;
}
