package com.itau.postsales;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import org.mockito.Mockito;
import org.junit.jupiter.api.Test;
import com.itau.postsales.model.Addition;
import com.itau.postsales.model.Contract;
import com.itau.postsales.model.Financial;
import com.itau.postsales.dto.data.FinancialDTO;
import com.itau.postsales.enums.CalculationType;
import com.itau.postsales.model.ContractAddition;
import com.itau.postsales.dto.data.PaymentDayDTO;
import com.itau.postsales.dto.data.ContractRequestDTO;
import com.itau.postsales.dto.data.InstallmentsQuantityDTO;
import org.springframework.boot.test.context.SpringBootTest;
import com.itau.postsales.dto.request.PaymentDayChangeRequestDTO;
import com.itau.postsales.dto.request.InstallmentsQuantityRequestDTO;

@SpringBootTest
public class PostSalesApplicationTests {

	protected InstallmentsQuantityRequestDTO installmentRequest() {

		InstallmentsQuantityRequestDTO request = Mockito.mock(InstallmentsQuantityRequestDTO.class);

		ContractRequestDTO contract = this.getContractMock();
		List<FinancialDTO> financials = this.getFinancialMockList();
		InstallmentsQuantityDTO addition = this.getInstallmentQuantityMock();

		Mockito.when(request.getContract()).thenReturn(contract);
		Mockito.when(request.getAddition()).thenReturn(addition);
		Mockito.when(request.getFinancials()).thenReturn(financials);

		return request;
	}

	protected PaymentDayChangeRequestDTO paymentDayRequest() {

		PaymentDayChangeRequestDTO request = Mockito.mock(PaymentDayChangeRequestDTO.class);

		ContractRequestDTO contract = this.getContractMock();
		List<FinancialDTO> financials = this.getFinancialMockList();
		PaymentDayDTO addition = this.getPaymentDayMock();

		Mockito.when(request.getContract()).thenReturn(contract);
		Mockito.when(request.getFinancials()).thenReturn(financials);
		Mockito.when(request.getAddition()).thenReturn(addition);

		return request;
	}

	protected ContractRequestDTO getContractMock() {

		ContractRequestDTO request = Mockito.mock(ContractRequestDTO.class);

		Mockito.when(request.getContractId()).thenReturn(37959);
		Mockito.when(request.getCustomerCpfCnpj()).thenReturn("66273815089");
		Mockito.when(request.getContractingDate()).thenReturn(new Date());
		Mockito.when(request.getActive()).thenReturn(true);
		Mockito.when(request.getOverdueInstallments()).thenReturn(false);

		return request;
	}

	protected List<FinancialDTO> getFinancialMockList() {

		FinancialDTO request = Mockito.mock(FinancialDTO.class);

		Mockito.when(request.getCalculationType()).thenReturn("CONTRATACAO");
		Mockito.when(request.getCalculationDate()).thenReturn(new Date());
		Mockito.when(request.getTotalValue()).thenReturn(50000.00);
		Mockito.when(request.getInstallmentsQuantity()).thenReturn(50);
		Mockito.when(request.getInstallmentsValue()).thenReturn("1000.00");
		Mockito.when(request.getPaymentDay()).thenReturn(23);
		Mockito.when(request.getInterestRatePercentage()).thenReturn(1.99);

		List<FinancialDTO> list = new ArrayList<>();
		list.add(request);

		return list;
	}

	protected HttpServletRequest getHeaderMock() {

		String field = "itau-pos-venda-teste";
		String value = "a4ee61a4-3078-11ee-be56-0242ac120002";

		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		Mockito.when(request.getHeader(field)).thenReturn(value);

		return request;
	}

	protected InstallmentsQuantityDTO getInstallmentQuantityMock() {

		InstallmentsQuantityDTO request = Mockito.mock(InstallmentsQuantityDTO.class);
		Mockito.when(request.getInstallmentsQuantity()).thenReturn(56);

		return request;
	}

	protected PaymentDayDTO getPaymentDayMock() {

		PaymentDayDTO request = Mockito.mock(PaymentDayDTO.class);
		Mockito.when(request.getPaymentDay()).thenReturn(15);

		return request;
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
				37959,
				"66273815089",
				new Date(),
				true,
				false
		);
	}

	protected Financial getFinancial() {
		return new Financial(
				new Date(),
				CalculationType.CONTRACTING.toString(),
				50000.00,
				50,
				"1000.00",
				23,
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