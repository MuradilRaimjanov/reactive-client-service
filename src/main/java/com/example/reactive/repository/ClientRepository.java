package com.example.reactive.repository;

import com.example.reactive.model.Client;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ClientRepository extends ReactiveCrudRepository<Client, Long> {
    Flux<Client> findByNameContaining(String firstName);

    Mono<Boolean> existsById(Long id);
}
