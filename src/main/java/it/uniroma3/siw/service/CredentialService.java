package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.auth.Credential;
import it.uniroma3.siw.repository.CredentialRepository;

@Service
public class CredentialService {
    
    @Autowired
    private CredentialRepository credentialRepository;
    
    public Credential getCredentialById(Long id) {
        return this.credentialRepository.findById(id).get();
    }

    public Optional<Credential> getCredentialByUsername(String username) {
        return this.credentialRepository.findByUsername(username);
    }

    public void save(Credential credenziali) {
        this.credentialRepository.save(credenziali);
    }


}