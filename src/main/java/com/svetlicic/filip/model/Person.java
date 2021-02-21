package com.svetlicic.filip.model;

import javax.persistence.*;

import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstname;
	private String lastname;
	private LocalDate birthday;
}
