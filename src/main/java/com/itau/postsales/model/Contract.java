package com.itau.postsales.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Contract {

    private Integer contractId;

    private String customerCpfCnpj;

    private Date contractDate;

    private Boolean active;

    private Boolean overdueInstallments;
}
