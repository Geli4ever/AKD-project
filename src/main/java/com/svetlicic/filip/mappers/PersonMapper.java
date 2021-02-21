package com.svetlicic.filip.mappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.svetlicic.filip.model.Person;
import com.svetlicic.filip.modelDTO.PersonDTO;

@Mapper
public interface PersonMapper {
	
	PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(source = "birthday", target = "birthday", qualifiedByName = "stringToDate")
	Person personDtoToPerson(PersonDTO persontDTO);
	@Mapping(source = "birthday", target = "birthday", qualifiedByName = "dateToString")
	PersonDTO personToPersonDTO(Person person);
	
	@Named("stringToDate")
    public static LocalDate stringToDate(String birthday) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy"); 
        return LocalDate.parse(birthday, formatter);
    }
	
	@Named("dateToString")
    public static String dateToString(LocalDate birthday) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy"); 
        return birthday.format(formatter);
    }

}
