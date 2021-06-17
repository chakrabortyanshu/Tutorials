package venkat.subramaniam.speaking.two;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Sample1 {
	public static void main(String[] args) {
			//method1();
			//method2();
			//method3();
			//method4();
			method5();
	}
	
	public static void method1() {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		
		for (int i = 0; i < 10; i++) {
			final int index = i;
			
			executorService.submit(new Runnable() {
				
				@Override
				public void run() {
					System.out.println("Running task " + index);
				}
			});
		}
		
		System.out.println("Tasks Started !!!");
		executorService.shutdown();
		
	}
	
	public static void method2() {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		IntStream.range(0, 10)
			     .forEach(i -> 
			     executorService.submit(() -> 
			     	System.out.println("Running task "+ i)));
		System.out.println("Tasks Started !!!");
		executorService.shutdown();
	}
	
	public static boolean isPrime(int number) {
		
		//int number = 100; //97; //variable or may be an argument;
		
		/*boolean flag = false;
		
		for (int i =2; i< number ; i++) {
			if(number % i == 0 ) {
				flag = true;
				break;
			}
		}
		
		System.out.println(number > 1 && !flag);
		*/
		
		return  number > 1 &&
				IntStream.range(2, number)
						 .noneMatch(i -> number % i == 0);
	}
	
	public static void method4() {
		/*List<Double> sqrtOfFirst100Primes = new ArrayList<>();
		
		int index = 1;
		while(sqrtOfFirst100Primes.size() < 100) {
			if(isPrime(index)) {
				sqrtOfFirst100Primes.add(Math.sqrt(index));
			}
			index ++;
		}*/
		
		List<Double> sqrtOfFirst100Primes =
				Stream.iterate(1, e -> e+1)
					  .filter(Sample1::isPrime)
					  .map(Math::sqrt)
					  .limit(100)
					  .collect(Collectors.toList());
		
		System.out.println(sqrtOfFirst100Primes.toString());
	}
	
	public static void method5() {
		
		//names in upper case, and comma separated.
		
		File dir = new File(".");
		File[] children = dir.listFiles();
		
		if(children != null) {
			System.out.println(
					Stream.of(children)
						  .map(File::getName)
						  .map(String::toUpperCase)
						  .collect(Collectors.joining(", "))
					);
						//.sorted(usingComparator); for sorting using Comparator
						//.sorted(Comparator.comparing(Person::getName).thenComparing(Person::getAge));
		}
		
	}
	
}
