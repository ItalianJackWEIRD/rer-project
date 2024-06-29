package it.uniroma3.siw.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.auth.Credentials;

public interface CredentialRepository extends CrudRepository<Credentials, Long> {

    public Optional<Credentials> findByUsername(String username);
}
