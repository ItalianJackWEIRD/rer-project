package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Host;
import it.uniroma3.siw.repository.HostRepository;

@Service
public class HostService {
	@Autowired
	private HostRepository hostRepository;

	public Host findById(Long id) {
		return hostRepository.findById(id).get();
	}

	public Iterable<Host> findAll() {
		return hostRepository.findAll();
	}

	public void save(Host host) {
		hostRepository.save(host);
	}

	public Host findByNameAndSurname(String name, String surname) {
		return this.hostRepository.findByNameAndSurname(name, surname);
	}
}
