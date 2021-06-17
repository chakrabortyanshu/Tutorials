package com.learning.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumerDemoAssignSeek {

    private final Logger logger = LoggerFactory.getLogger(ConsumerDemoAssignSeek.class.getName());

    public static void main(String[] args) {
        new ConsumerDemoAssignSeek().run();
    }

    private Properties getConsumerProperties(){

        logger.info("Creating Properties for the Consumer.");

        Properties properties = new Properties();
        String bootStrapServer = "127.0.0.1:9092";
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // earliest , latest , none

        return properties;
    }

    /**
     * Reads Consumer Records
     * @param consumer KafkaConsumer map
     */
    private void readConsumerRecords(KafkaConsumer<String, String> consumer) {
        int numberOfMsgsToRead = 5;
        boolean keepOnreading = true;
        int numberOfMsgsReadSoFar = 0;
        //poll for new data
        try {
            while (keepOnreading) {
                //consumer.poll(100*1000); // new in Kafka 2.0.0
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

                for (ConsumerRecord<String, String> record : records) {
                    numberOfMsgsReadSoFar += 1;
                    logger.info("Key: " + record.key() + ", Value: " + record.value());
                    logger.info("Partition: " + record.partition() + ", Offsets: " + record.offset());

                    if (numberOfMsgsReadSoFar >= numberOfMsgsToRead) {
                        keepOnreading = false;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }

    /**
     *
     * 1. Create Consumer Properties. (https://kafka.apache.org/documentation/#consumerconfigs)
     * 2. Create a Consumer.
     * 3. Receive a data.
     */
    private void run() {

        Properties properties = getConsumerProperties();

        //Create Consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        //Assign and seek are mostly used to replay data or fetch a specific message

        //assign
        String topic = "first_topic";
        TopicPartition partitionToReadFrom = new TopicPartition(topic, 0);
        long offsetToReadFrom = 15L;
        //Arrays.asList(partitionToReadFrom)
        consumer.assign(Collections.singletonList(partitionToReadFrom));

        //seek
        consumer.seek(partitionToReadFrom, offsetToReadFrom);

        readConsumerRecords(consumer);

        logger.info("Exiting Application.");
    }

}
