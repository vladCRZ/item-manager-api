package com.vscg.itemmanagerapiv3.item;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long>{

    //Optional<Item> findById(Long id);
}