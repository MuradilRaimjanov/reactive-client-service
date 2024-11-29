package com.example.reactive.controller;

import com.example.reactive.model.Client;
import com.example.reactive.repository.ClientRepository;
import com.example.reactive.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public Mono<Client> create(@RequestBody Client client) {
        return clientService.create(client);
    }

    @PutMapping("/{id}")
    public Mono<Client> update(@PathVariable Long id, @RequestBody Client client) {
        return clientService.update(id, client);
    }
    @GetMapping("/{id}")
    public Mono<Client> findById(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @GetMapping
    public Flux<Client> findAll() {
        return clientService.findAll();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable Long id) {
        return clientService.deleteById(id);
    }

    @DeleteMapping
    public Mono<Void> deleteAll() {
        return clientService.deleteAll();
    }

    @GetMapping("/exist/{id}")
    public Mono<ResponseEntity<Void>> checkClientExists(@PathVariable Long id) {
        return clientService.clientExistsById(id)
                .map(exists -> exists ? ResponseEntity.ok().build() : ResponseEntity.notFound().build());
    }
}
