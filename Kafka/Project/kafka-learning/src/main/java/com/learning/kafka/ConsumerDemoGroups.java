package com.learning.kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerDemoGroups {
public static void main(String[] args) {
	Logger logger = LoggerFactory.getLogger(ConsumerDemoGroups.class.getName());
	
	/**
	 *
	 * 1. Create Consumer Properties. (https://kafka.apache.org/documentation/#consumerconfigs)
	 * 2. Create a Consumer.
	 * 3. Receive a data.
	 * 
	 * Run multiple instances of Consumer Group and you will see that the partitions are balanced between them.
	 * 
	 */
	String bootStrapServer = "127.0.0.1:9092";
	String groupId = "my-fifth-application";
	
	Properties properties = new Properties();
	
	properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
	properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
	properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
	properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
	properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest") ; // earliest , latest , none
	
	
	//Create Consumer
	KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
	
	//Subscribe Consumer
	//consumer.subscribe(Collections.singleton("first_topic"));
	consumer.subscribe(Arrays.asList("first_topic"));
	
	//poll for new data
	try {
		while(true) {
			//consumer.poll(100*1000); // new in Kafka 2.0.0
			ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
			
			for (ConsumerRecord<String, String> record: records) {
				logger.info("Key: " + record.key() +", Value: "+ record.value());
				logger.info("Partition: "+ record.partition() + ", Offsets: "+ record.offset());
			}
			
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		consumer.close();
	}
}
}
