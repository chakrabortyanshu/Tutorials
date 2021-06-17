package venkat.subramaniam.speaking.one;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Sample1 {
	public static void main(String[] args) {
		//Method1();
		//Method2();
		//Method3();
		//Method4();
		//Method5();
		//Method6();
		//Method7();
		//Method8();
		//Method9();
		//Method10();
		Method11();
	}

	private static void Method1() {
		List<Integer> values = Arrays.asList(1,2,3,4,5,6);
		
		for (int i = 0; i < values.size(); i++) {
			System.out.println(values.get(i));
		}
	}
	
	private static void Method2() {
		List<Integer> values = Arrays.asList(1,2,3,4,5,6);
		
		for (int i : values) {
			System.out.println(i);
		}
	}
	
	private static void Method3() {
		List<Integer> values = Arrays.asList(1,2,3,4,5,6);
		
		values.forEach(e -> System.out.println(e));
	}
	
	private static void Method4() {
		List<Integer> values = Arrays.asList(1,2,3,4,5,6);
		
		values.forEach(System.out::println);
	}
	
	private static void Method5() {
		List<Integer> values = Arrays.asList(1,2,3,4,5,6);
		
		values.forEach(new Consumer<Integer>() {
			@Override
			public void accept(Integer i) {
				System.out.println(i);
			}
		});
	}
	
	private static void Method6() {
		List<Integer> values = Arrays.asList(1,2,3,4,5,6);
		
		values.forEach((Integer i) -> System.out.println(i));
	}
	
	private static void Method7() {
		List<Integer> values = Arrays.asList(1,2,3,4,5,6);
		
		values.forEach((i) -> System.out.println(i));
	}
	
	private static void Method8() {
		List<Integer> values = Arrays.asList(1,2,3,4,5,6);
		
		int total = 0;
		
		for (int e: values) {
			total+= e*2;
		}
		
		System.out.println(total);
	}
	
	private static void Method9() {
		List<Integer> values = Arrays.asList(1,2,3,4,5,6);
		
		System.out.println(values.stream()
								 .map(e -> e*2)
								 .reduce(0, (c, e) -> c+e));
	}
	
	private static void Method10() {
		List<Integer> values = Arrays.asList(1,2,3,4,5,6);
		
		System.out.println(totalValues(values, e -> true)); //Add all numbers
		System.out.println(totalValues(values, e -> e%2 == 0)); //Add Even numbers
		System.out.println(totalValues(values, e -> e%2 != 0)); //Add Odd numbers
	}

	private static int totalValues(List<Integer> values, Predicate<Integer> selector) {
		/*int total = 0;
		for(Integer i : values) {
			if(selector.test(i)) {
				total+=i;
			}
		}
		return total;*/
		
		
		return values.stream()
			  .filter(selector)
			  .reduce(0, (c,e) -> c+e);
	}
	
	private static void Method11() {
		List<Integer> values = Arrays.asList(1,2,3,5,6,7,8,9,10);
		//the double of the first even number in the list greater than 3.
/*		int result = 0;
		for(int e : values) {
			if(e>3 && e%2==0) {
				result = e*2;
				break;
			}
		}
		System.out.println(result);
		
*/	
		
	System.out.println(
		values.stream()
			  .filter(e -> e>3)
			  .filter(e -> e%2 == 0)
			  .map(e -> e*2)
			  .findFirst()
			  //.orElse(0)
			);
	
	}
	
}
