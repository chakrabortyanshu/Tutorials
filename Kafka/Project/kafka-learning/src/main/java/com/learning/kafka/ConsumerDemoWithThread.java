package com.learning.kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerDemoWithThread {
	
	final Logger logger = LoggerFactory.getLogger(ConsumerDemoWithThread.class.getName());
	
	public static void main(String[] args) {
		new ConsumerDemoWithThread().run();
	}
	
	private ConsumerDemoWithThread() {
	}
	
	private void run() {

		/**
		 *
		 * 1. Create Consumer Properties.
		 * (https://kafka.apache.org/documentation/#consumerconfigs) 2. Create a
		 * Consumer. 3. Receive a data.
		 */
		String bootStrapServer = "127.0.0.1:9092";
		String groupId = "my-sixth-application";
		String topic = "first_topic";

		//Latch for threads.
		CountDownLatch latch = new CountDownLatch(1);
		
		logger.info("creating the consumer.");
		
		final ConsumerRunnable myConsumerRunnable = new ConsumerRunnable(bootStrapServer, groupId, topic, latch);
		
		Thread myThread = new Thread(myConsumerRunnable);
		myThread.start();
		
		//Add shutdown hook.
		Runtime.getRuntime().addShutdownHook(addShutdownHook(myConsumerRunnable, latch)); 
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			logger.error("InterruptedException " + e);
		}finally {
			logger.info("Application is Closing.");
		}
		
	}
	
	private Thread addShutdownHook(ConsumerRunnable myConsumerRunnable, CountDownLatch latch){
		return new Thread( () -> {
			logger.info("Caught Shutdown Hook");
			myConsumerRunnable.shutdown();
			try {
				latch.await();
			} catch (InterruptedException e) {
				logger.error("InterruptedException " + e);
			}
		  });
	}
	

	private class ConsumerRunnable implements Runnable{

		Logger logger = LoggerFactory.getLogger(ConsumerRunnable.class.getName());
		
		CountDownLatch latch;
		KafkaConsumer<String, String> consumer ;
		
		public ConsumerRunnable(String bootStrapServer, String groupId, String topic, CountDownLatch latch) {
			
			Properties properties = new Properties();

			properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
			properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
			properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
			properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
			properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // earliest , latest , none

			// Create Consumer
			this.consumer = new KafkaConsumer<String, String>(properties);			
			// Subscribe Consumer
			// consumer.subscribe(Collections.singleton("first_topic"));
			consumer.subscribe(Arrays.asList(topic));
			
			this.latch = latch;
		}
		
		
		public void run() {
			
			try {
				while (true) {
					// poll for new data
					// consumer.poll(100*1000); // new in Kafka 2.0.0
					ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

					for (ConsumerRecord<String, String> record : records) {
						logger.info("Key: " + record.key() + ", Value: " + record.value());
						logger.info("Partition: " + record.partition() + ", Offsets: " + record.offset());
					}

				}

			}catch (WakeupException e) {
				logger.info("Received Shutdown Signal");
				
			}finally {
				consumer.close();
				//tell the main code that we are done with consumer.
				latch.countDown();
			}
		}
		
		public void shutdown() {
			//this method is a special method to interrupt consumer.poll()
			//it will throw the exception org.apache.kafka.common.errors.WakeupException.
			consumer.wakeup();
		}
		
	}

}
