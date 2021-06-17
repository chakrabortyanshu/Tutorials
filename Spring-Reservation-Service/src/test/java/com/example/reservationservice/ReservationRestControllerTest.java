package com.example.reservationservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;

@RunWith(SpringRunner.class)
@WebFluxTest
@Import(ProducerRestConfiguration.class)
public class ReservationRestControllerTest {

	@MockBean
	private ReservationRepository reservationRepository;

	@Autowired
	private WebTestClient webTestClient;

	@Test
	public void getAllReservations() {

		Mockito.when(this.reservationRepository.findAll())
		       .thenReturn(Flux.just(new Reservation("1", "Mario")));

		this.webTestClient
		    .get()
		    .uri("/reservations")
		    .exchange()
		    .expectStatus().isOk()
		    .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
		    .expectBody().jsonPath("@.[0].reservationName").isEqualTo("Mario");
	}

}
