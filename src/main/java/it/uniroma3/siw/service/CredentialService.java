package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.auth.Credentials;
import it.uniroma3.siw.repository.CredentialRepository;

@Service
public class CredentialService {

    @Autowired
    private CredentialRepository credentialRepository;

    public Credentials getCredentialById(Long id) {
        return this.credentialRepository.findById(id).get();
    }

    public Optional<Credentials> getCredentialByUsername(String username) {
        return this.credentialRepository.findByUsername(username);
    }

    public void save(Credentials credentials) {
        this.credentialRepository.save(credentials);
    }

}
