package com.vscg.itemmanagerapiv3.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vscg.itemmanagerapiv3.model.DAOUser ;

@Repository
public interface UserDao extends CrudRepository<DAOUser, Integer> {

    DAOUser findByUsername(String username);


}