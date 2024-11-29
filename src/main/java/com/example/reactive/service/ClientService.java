package com.example.reactive.service;

import com.example.reactive.model.Client;
import com.example.reactive.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Mono<Client> create(Client client) {
        return clientRepository.save(client);
    }

    public Mono<Client> update(Long id, Client client) {
        return clientRepository.findById(id)
                .flatMap(exClient -> {
                    exClient.setName(client.getName());
                    exClient.setEmail(client.getEmail());
                    return clientRepository.save(exClient);
                });
    }

    public Flux<Client> findAll() {
        return clientRepository.findAll();
    }

    public Mono<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    public Mono<Void> deleteById(Long id) {
        return clientRepository.deleteById(id);
    }

    public Mono<Void> deleteAll() {
        return clientRepository.deleteAll();
    }

    public Mono<Boolean> clientExistsById(Long id) {
        return clientRepository.existsById(id);
    }
}
