package com.example.reservationserviceclient;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;


@Component
public class ReservationClient {

	private final WebClient webClient;

	public ReservationClient(WebClient webClient) {
		super();
		this.webClient = webClient;
	}
	
	public Flux<Reservation> getAllReservations(){
		return webClient.get()
		.uri("http://localhost:8080/reservations")
		.retrieve()
		.bodyToFlux(Reservation.class);
	}
	
	
}
