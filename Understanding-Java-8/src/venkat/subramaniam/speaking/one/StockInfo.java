package venkat.subramaniam.speaking.one;

public class StockInfo {

	public final String ticker;
	public final double price;
	
	
	public StockInfo(final String symbol, final double thePrice) {
		this.ticker = symbol;
		this.price = thePrice;
	}
	
	public String toString() {
		return String.format("ticker: %s price: %g", ticker, price);
	}
	
}
