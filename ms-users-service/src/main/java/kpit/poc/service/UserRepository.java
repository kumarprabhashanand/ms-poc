package kpit.poc.service;

import org.springframework.data.repository.CrudRepository;

import kpit.poc.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
