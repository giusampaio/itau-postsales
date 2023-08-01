package com.itau.postsales;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import com.itau.postsales.model.Addition;
import com.itau.postsales.model.Contract;
import com.itau.postsales.model.Financial;
import com.itau.postsales.enums.CalculationType;
import com.itau.postsales.model.ContractAddition;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostSalesApplicationTests {

	@Test
	void contextLoads() {
	}

	protected ContractAddition getContractAddition() {

		List<Financial> financials = new ArrayList<Financial>();

		financials.add(this.getFinancial());
		Contract contract = this.getContract();

		return new ContractAddition(contract, financials,null);
	}

	protected ContractAddition getContractAdditionWithInstallments() {

		List<Financial> financials = new ArrayList<Financial>();
		financials.add(this.getFinancial());

		Addition addition = this.getInstallmentsAddition();
		Contract contract = this.getContract();

		return new ContractAddition(contract, financials, addition);
	}

	protected ContractAddition getContractAdditionWithPayment() {

		List<Financial> financials = new ArrayList<Financial>();
		financials.add(this.getFinancial());

		Addition addition = this.getPaymentAddition();
		Contract contract = this.getContract();

		return new ContractAddition(contract, financials, addition);
	}

	protected Contract getContract() {
		return new Contract(
				123,
				"40004348000171",
				new Date(),
				true,
				false
		);
	}

	protected Financial getFinancial() {
		return new Financial(
				new Date(),
				CalculationType.CONTRACTING.toString(),
				10000.00,
				54,
				"950.00",
				5,
				1.99
		);
	}

	protected Addition getInstallmentsAddition() {
		return new Addition(50, null);
	}

	protected Addition getPaymentAddition() {
		return new Addition(null, 1);
	}
}