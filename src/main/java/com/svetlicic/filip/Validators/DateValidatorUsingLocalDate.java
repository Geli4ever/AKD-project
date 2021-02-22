package com.svetlicic.filip.Validators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DateValidatorUsingLocalDate implements DateValidator {
	
	private final DateTimeFormatter dateTimeFormatter;
	
	public DateValidatorUsingLocalDate() {
		String europeanDatePattern = "dd.MM.yyyy";
		this.dateTimeFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
	}

	@Override
	public boolean isValid(String dateStr) {
        try {
            LocalDate.parse(dateStr, this.dateTimeFormatter);
        } catch (DateTimeParseException e) {
        	log.error("Something went wrong with date validation");
            return false;
        }
        return true;
	}

}
