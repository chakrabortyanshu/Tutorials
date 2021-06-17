package jmockit.sample;

import java.util.List;
import java.util.Map;

public interface PriceService {

	Map<Long,Double> price(List<Long> skus);

}
