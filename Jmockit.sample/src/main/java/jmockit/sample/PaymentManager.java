package jmockit.sample;

public class PaymentManager {

	
	private ThirdPartyPaymentService thirdPartyPaymentService;
	
	
	public boolean pay(int i) throws PaymentFailedException{
		return thirdPartyPaymentService.pay(i);
	}

}
