package com.learning.kafka.twitter.stream.consumer;

import com.google.gson.JsonParser;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ElasticSearchConsumer {

    private final Logger logger = LoggerFactory.getLogger(ElasticSearchConsumer.class);

    public static void main(String[] args) {
        new ElasticSearchConsumer().run();
    }

    private void run() {
        RestHighLevelClient client = createElasticSearchClient();

        //poll for new data
        try (KafkaConsumer<String, String> kafkaConsumer = createKafkaConsumer()) {
            IndexRequest indexRequest = new IndexRequest("twitter");
            while (true) {
                //consumer.poll(100*1000); // new in Kafka 2.0.0
                ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));

                int recordCount = records.count();
                logger.info("Received " + recordCount + " records.");

                if (recordCount == 0) { //No record received.
                    continue;
                }

                processConsumerRecords(kafkaConsumer, client, indexRequest, records);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void processConsumerRecords(KafkaConsumer<String, String> kafkaConsumer,
                                        RestHighLevelClient client,
                                        IndexRequest indexRequest,
                                        ConsumerRecords<String, String> records) {

        //For processing records in bulk.
        BulkRequest bulkRequest = new BulkRequest();

        for (ConsumerRecord<String, String> record : records) {
            //where we insert data into elasticsearch
            //String jsonString = record.value();

            //Two strategies
            //1. Kafka Generic ID - Every message has a unique combination of topic, partition and offsets.
            //String id = record.topic()+"_"+record.partition()+"_"+record.offset();
            //2. Twitter feed specific id from Twitter which is unique per tweet.

            addTweetsToBulkRequest(record, indexRequest, bulkRequest);

            //For single record processing.
            //processIndexRequest(client,indexRequest);

        }

        sendBulkRequest(kafkaConsumer, client, bulkRequest);

    }

    private void sendBulkRequest(KafkaConsumer<String, String> kafkaConsumer, RestHighLevelClient client, BulkRequest bulkRequest) {
        try {
            BulkResponse bulkResponses = client.bulk(bulkRequest, RequestOptions.DEFAULT);
            bulkResponses.forEach(e -> System.out.println("Received Response " + e.getId()));

            logger.info("Committing offsets...");
            kafkaConsumer.commitSync();
            logger.info("Offsets have been committed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processIndexRequest(RestHighLevelClient client, IndexRequest indexRequest) throws IOException {

        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        logger.info("Id Received from ElasticSearch - " + indexResponse.getId());

    }

    private void addTweetsToBulkRequest(ConsumerRecord<String, String> record, IndexRequest indexRequest, BulkRequest bulkRequest) {
        try {
            String jsonString = record.value();
            String idOfTweet = extractIdFromTweet(jsonString);

            indexRequest.id(idOfTweet); //This is to make our consumer idempotent. That means no two commits of the same data.
            indexRequest.source(jsonString, XContentType.JSON);

            //Adding the indexRequest to a Bulk so that it can be processed together. It takes no time.
            //It is more efficient than the single indexRequest processing.
            bulkRequest.add(indexRequest);
        } catch (Exception e) {
            logger.warn(record.value());
            logger.warn("Above data was skipped from processing", e);
        }
    }

    private String extractIdFromTweet(String record) {
        //GSon library from Google.
        JsonParser jsonParser = new JsonParser();
        return jsonParser.parse(record)
                .getAsJsonObject()
                .get("id_str")
                .getAsString();
    }

    private RestHighLevelClient createElasticSearchClient() {
        //https://wuBML3CSW9:vrV3Ct6UZKd5hiaXTjLNm@kafka-course-7287302234.us-east-1.bonsaisearch.net:443

        String hostname = "kafka-course-7287302234.us-east-1.bonsaisearch.net";
        String userName = "wuBML3CSW9";
        String password = "vrV3Ct6UZKd5hiaXTjLNm";

        //Don't do this if you run a local ES
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(userName, password));

        RestClientBuilder restClientBuilder = RestClient.builder(
                new HttpHost(hostname, 443, "https"))
                .setHttpClientConfigCallback(httpAsyncClientBuilder -> httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        return new RestHighLevelClient(restClientBuilder);
    }


    public KafkaConsumer<String, String> createKafkaConsumer() {
        String bootStrapServer = "127.0.0.1:9092";
        String groupId = "my-fifth-application";

        Properties properties = new Properties();

        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // earliest , latest , none
        // Consumer Offset Commits Strategies. disable auto commit of offsets.
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        //Restrict the number of records to be received to 20.
        properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "100");


        //Create Consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Collections.singletonList("first_topic")); //Arrays.asList("first_topic")

        return consumer;
    }
}
