package com.Practica.JPA.persistence.repository;

import com.Practica.JPA.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {

}
