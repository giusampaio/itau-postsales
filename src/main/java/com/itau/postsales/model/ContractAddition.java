package com.itau.postsales.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class ContractAddition {

    private Contract contract;

    private List<Financial> financials;

    private Addition addition;
}
