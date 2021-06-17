package jmockit.sample;

public interface ThirdPartyPaymentService {

	public boolean pay(int amount) throws PaymentFailedException;
	
}
