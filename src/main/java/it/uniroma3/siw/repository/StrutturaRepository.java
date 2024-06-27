package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Struttura;

public interface StrutturaRepository extends CrudRepository<Struttura, Long>{
	public List<Struttura> findByCity(String citta);
	public boolean existsByNameAndCity(String name, String city);
}
