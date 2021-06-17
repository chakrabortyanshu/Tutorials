package com.example.reservationservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ReservationDocumentTest {

	@Autowired
	ReactiveMongoTemplate mongoTemplate;

	@Test
	public void testShouldStoreAndRetrieve() {
		
		Publisher<Reservation> reservationPublisher = mongoTemplate.save(new Reservation(null, "JOSH"));
		
		
		StepVerifier.create(reservationPublisher)
		.expectNextMatches(e -> e.getReservationName().equals("JOSH"))
		.verifyComplete();
		
	}

}
