package kpit.poc.service;

import org.springframework.data.repository.CrudRepository;

import kpit.poc.model.Item;

public interface ItemRepository extends CrudRepository<Item, Integer> {

}
