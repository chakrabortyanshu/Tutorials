package jmockit.sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductService {

	
	private SearchService searchService;
	private PriceService priceService;
	
	public List<Product> find(String token) {
		
		List<Product> products = searchService.search(token);
		List<Long> skus = new ArrayList<Long>();
		
		for(Product product: products) {
			skus.add(product.getSku());
		}
		
		Map<Long, Double> priceMap = priceService.price(skus);
		
		for(Product product: products) {
			product.setPrice(priceMap.get(product.getSku()));
		}
		
		
		return products;
	}

}
