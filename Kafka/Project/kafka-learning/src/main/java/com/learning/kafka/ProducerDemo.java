package com.learning.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class ProducerDemo {
	
	public static void main(String[] args) {
		/**
		 * 1. Create PRoducer Properties. (https://kafka.apache.org/documentation/#producerconfigs)
		 * 2. Create a Producer.
		 * 3. Send data.
		 */ 
		
		String bootStrapServers = "127.0.0.1:9092";
		
		Properties properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
		//help the producer know what value to serialize.
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); 
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
		
		ProducerRecord<String, String> record = new ProducerRecord<String, String>("first_topic", "Hello World from Code!!!");
		
		//ASynchronous
		producer.send(record);
		
		
		producer.flush();
		producer.close();
		
		/**
		 * Open a consumer and you should see.
		 * Zookeeper should continue running.
		 * Kafka Server should be running.
		 * 
		  kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic first_topic --group my-second-group
		  Hello World from Code!!!
		 */
		
		
		
	}
	
	
	
  	
	
	
}
