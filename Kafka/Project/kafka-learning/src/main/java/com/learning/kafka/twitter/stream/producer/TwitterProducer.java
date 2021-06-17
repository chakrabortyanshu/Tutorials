package com.learning.kafka.twitter.stream.producer;

import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TwitterProducer {

    private final Logger logger = LoggerFactory.getLogger(TwitterProducer.class);

    public static void main(String[] args) {
        new TwitterProducer().run();
    }

    private void run() {

        BlockingQueue<String> msgQueue = new LinkedBlockingQueue<>(1000);
        List<String> terms = Lists.newArrayList("#covid-19","#java","kafka","bitcoin","usa","politics","sports","cricket");

        Client hoseBirdClient = createTwitterClient(msgQueue, terms);

        // Attempts to establish a connection.
        hoseBirdClient.connect();

        //create a kafka producer
        KafkaProducer<String, String> producer= createKafkaProducer();

        Runtime.getRuntime().addShutdownHook(new Thread(()-> {
            logger.info("Stopping Application");
            logger.info("Shutting down Client from Twitter");
            hoseBirdClient.stop();
            logger.info("Closing Kafka Producer");
            producer.close();
            logger.info("Done!");
        }));


        //loop to send tweets to Kafka
        // on a different thread, or multiple different threads....
        try {
            while (!hoseBirdClient.isDone()) {

                String msg = msgQueue.take();

                /*
                JsonParser jsonParser = new JsonParser();
                jsonParser.parse(msg);
                jsonParser.toString();
                 */

                JSONObject jsonObj = new JSONObject(msg);
                System.out.println(jsonObj.toString(4));

                sendToKafkaTopicAsProducer(producer, null, msg);
            }
        } catch (InterruptedException e) {
            logger.error("InterruptedException Occurred.", e);
        } catch (JSONException e) {
            logger.error("JSONException Occurred.", e);
        }/*finally{
            hoseBirdClient.stop();
            producer.flush();
            producer.close();
        }*/
    }

    private void sendToKafkaTopicAsProducer(KafkaProducer<String,String> producer, String key , String msg) {
        String kafkaTopic = "first_topic";

        logger.info("Sending message to Kafka as Producer to Topic " + kafkaTopic );

        ProducerRecord<String, String> record = new ProducerRecord<>(kafkaTopic,null, msg);

        //ASynchronous Callback.
        producer.send(record, this::onCompletion);
        producer.flush();

    }
    private void onCompletion(RecordMetadata metadata, Exception exception) {
        if (exception != null) {
            //NotEnoughReplicasException : the leader broker hasn't been able to replicate
            //                             to defined number of replics. min.insync.replicas
            //

            //
            logger.error("Something went wrong ", exception);
        }
    }

    private KafkaProducer<String, String> createKafkaProducer() {
        String bootStrapServers = "127.0.0.1:9092";

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
        //help the producer know what value to serialize.
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //create safe Producer.
        properties.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, Boolean.TRUE.toString());
        properties.setProperty(ProducerConfig.ACKS_CONFIG,"all");
        properties.setProperty(ProducerConfig.RETRIES_CONFIG, Integer.toString(Integer.MAX_VALUE));
        properties.setProperty(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "5"); //kafka 2.0 >= 1.1
        //So we can keep this value to 5. Use 1 otherwise.

        // high through put producer (at the expense of a bit of latency and CPU usage)
        properties.setProperty(ProducerConfig.COMPRESSION_TYPE_CONFIG,"snappy");
        properties.setProperty(ProducerConfig.LINGER_MS_CONFIG,"20"); //milliseconds
        properties.setProperty(ProducerConfig.BATCH_SIZE_CONFIG,Integer.toString(32*1024)); //32KB



        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        return producer;
    }

    private Client createTwitterClient(BlockingQueue<String> msgQueue, List<String> terms) {

        logger.info("Setting up the Twitter Client.");

        //create a twitter client
        /**Set up your blocking queues: Be sure to size these properly based on expected TPS of your stream */
        //BlockingQueue<String> msgQueue = new LinkedBlockingQueue<String>(100000);

        /** Declare the host you want to connect to, the endpoint, and authentication (basic auth or oauth) */
        Hosts hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
        StatusesFilterEndpoint hosebirdEndpoint = new StatusesFilterEndpoint();
        // Optional: set up some followings and track terms

        hosebirdEndpoint.trackTerms(terms);

        // These secrets should be read from a config file
        Authentication hosebirdAuth = new OAuth1("Gf1KZfEcdg6vvZ9Y4lvdym5N4",
                "z2unGMD41tpa9gih4chtfdkYFuNotLMDxIrEE1hWyrFbFg2Z5P",
                "1907209993-pCv4Ptm1pTdpFdq7aBLnuOCe97GqANoBtdE5J87",
                "GSbkfS1xUorcyM20lFgfUV4LISWaUzDseVUvylIw3Eg0k");

        ClientBuilder builder = new ClientBuilder()
                .name("Hosebird-Client-01")                              // optional: mainly for the logs
                .hosts(hosebirdHosts)
                .authentication(hosebirdAuth)
                .endpoint(hosebirdEndpoint)
                .processor(new StringDelimitedProcessor(msgQueue));

        return builder.build();

    }


}
