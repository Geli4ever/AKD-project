package com.svetlicic.filip.services;

import org.springframework.stereotype.Service;

import com.svetlicic.filip.mappers.PersonMapper;
import com.svetlicic.filip.model.Person;
import com.svetlicic.filip.modelDTO.PersonDTO;
import com.svetlicic.filip.repositories.PersonRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService {
	
	private final PersonRepository personRepository;
	private final PersonMapper personMapper;
	
	public PersonServiceImpl(PersonRepository personRepository, PersonMapper personMapper) {
		this.personRepository = personRepository;
		this.personMapper = personMapper;
	}

	@Override
	public PersonDTO savePersonDTO(PersonDTO personDTO) {
		
		Person person = personMapper.personDtoToPerson(personDTO);
		log.info("Spremamo osobu " + person.getFirstname() + " " + person.getLastname() + " rođenu " + person.getBirthday());
		return personMapper.personToPersonDTO(personRepository.save(person));
	}

}
