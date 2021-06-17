package com.example.reservationservice;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Flux;

public interface ReservationRepository extends ReactiveMongoRepository<Reservation, String>{

	public Flux<Reservation> findByReservationName(String name);
}
