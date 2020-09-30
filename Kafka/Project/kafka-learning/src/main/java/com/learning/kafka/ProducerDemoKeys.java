package com.learning.kafka;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerDemoKeys {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		/**
		 * 1. Create PRoducer Properties.
		 * (https://kafka.apache.org/documentation/#producerconfigs) 2. Create a
		 * Producer. 3. Send data.
		 */

		final Logger logger = LoggerFactory.getLogger(ProducerDemoKeys.class);

		String bootStrapServers = "127.0.0.1:9092";

		Properties properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
		// help the producer know what value to serialize.
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

		for (int i = 0; i < 100; i++) {

			String topic = "first_topic";
			String value = "Hello World from Code!!!" + i;
			String key   = "ID_";//+i; //If the key id is same. it will always to go the same partition.
			
			ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic,key,value)	;

			// ASynchronous
			producer.send(record, new Callback() {

				public void onCompletion(RecordMetadata metadata, Exception exception) {

					if (exception == null) {
						logger.info("Received new Metadata. \n" + "Topic: " + metadata.topic() + "\n" + "Partition: "
								+ metadata.partition() + "\n" + "Offset: " + metadata.offset() + "\n" + "Timestamp: "
								+ metadata.timestamp() + "\n"
								);
					} else {
						logger.error("Error while producing" + exception);
					}
				}
			}).get(); //block the .send() to make it synchronous. don't do this in Production.

		}
		producer.flush();
		producer.close();

		/**
		 * Open a consumer and you should see. Zookeeper should continue running. Kafka
		 * Server should be running.
		 * 
		 * kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic first_topic
		 * --group my-second-group Hello World from Code!!!
		 */

	}

}
