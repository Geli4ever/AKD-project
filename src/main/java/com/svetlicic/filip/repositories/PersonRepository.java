package com.svetlicic.filip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svetlicic.filip.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
}
