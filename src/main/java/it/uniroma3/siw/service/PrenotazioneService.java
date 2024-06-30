package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.repository.PrenotazioneRepository;

@Service
public class PrenotazioneService {
    
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;


    public void save(Prenotazione prenotazione) {
        prenotazioneRepository.save(prenotazione);
    }

    public Prenotazione findById(Long id) {
        return this.prenotazioneRepository.findById(id).get();
    }

    public Iterable<Prenotazione> findAll() {
        return this.prenotazioneRepository.findAll();
    }

    
}
