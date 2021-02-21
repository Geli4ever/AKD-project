package com.svetlicic.filip.modelDTO;

import com.opencsv.bean.CsvBindByName;

import lombok.Data;

@Data
public class PersonDTO {
	@CsvBindByName(column = "Ime")
	private String firstname;
	@CsvBindByName(column = "Prezime")
	private String lastname;
	@CsvBindByName(column = "DatumRodjena")
	private String birthday;
}
