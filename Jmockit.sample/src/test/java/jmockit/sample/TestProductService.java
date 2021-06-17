package jmockit.sample;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;

public class TestProductService {

	@Tested //initializes class under the test
	private ProductService productService;
	
	@Injectable //It means a mocked object will be injected.
	private SearchService searchService;
	
	@Injectable
	private PriceService priceService;
	
	private List<Product> products;
	
	private Map<Long,Double> priceMap;
	
	@Before
	public void setup() {
		products = new ArrayList<Product>();
		
		Product p1 = new Product();
		p1.setSku(1L);
		
		Product p2 = new Product();
		p2.setSku(2L);
		
		products.add(p1);
		products.add(p2);
		
		priceMap = new HashMap<Long, Double>();
		priceMap.put(1L, 111.90);
		priceMap.put(2L, 15.40);
		
		
	}
	
	@Test
	public void shouldFindProducts() {
		
		//https://www.youtube.com/watch?v=JiCBS5YtcRM
		new Expectations() {{
			searchService.search("Java Threading");
			returns(products);
			times = 1; //expecting it to be invoked only once.
			
			priceService.price((List<Long>) any);
			returns(priceMap);
			times = 1;
			
		}};
		
		List<Product> products = productService.find("Java Threading");
		assertEquals(2, products.size());
		
		Product product1 = products.get(0);
		assertEquals(1L, product1.getSku());
		assertEquals(111.90, product1.getPrice(), 0 );
		
		Product product2 = products.get(1);
		assertEquals(2L, product2.getSku());
		assertEquals(15.40, product2.getPrice(), 0 );
		
		
	}
	
	
}
