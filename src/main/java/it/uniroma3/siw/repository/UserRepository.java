package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.auth.User;

public interface UserRepository extends CrudRepository<User, Long>{
    
    public User findByUsername(String username);
}
