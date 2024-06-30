package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Host;

public interface HostRepository extends CrudRepository<Host, Long> {
    public Host findByNameAndSurname(String name, String surname);
}