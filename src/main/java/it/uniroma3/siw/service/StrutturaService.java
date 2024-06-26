package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Struttura;
import it.uniroma3.siw.repository.StrutturaRepository;

@Service
public class StrutturaService {
	
	@Autowired
	private StrutturaRepository strutturaRepository;
	
	public Struttura findById (Long id) {
		return strutturaRepository.findById(id).get();
	}
	
	public Iterable<Struttura> findAll () {
		return strutturaRepository.findAll();
	}
	
	public void save(Struttura struttura) {
		strutturaRepository.save(struttura);
	}
	
	public List<Struttura> findByCity (String city) {
		return strutturaRepository.findByCity(city);
	}
}
