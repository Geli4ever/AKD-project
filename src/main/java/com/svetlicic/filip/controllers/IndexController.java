package com.svetlicic.filip.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.svetlicic.filip.modelDTO.PersonDTO;
import com.svetlicic.filip.services.PersonService;

@Controller
public class IndexController {
	
	private final PersonService personService;
	
	public IndexController(PersonService personService) {
		this.personService = personService;
	}

    @GetMapping({"", "/", "index", "index.html"})
    public String index(){
        return "index";
    }
    
    @PostMapping("/upload-csv-file")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) {

        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {

            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                CsvToBean<PersonDTO> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(PersonDTO.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                List<PersonDTO> persons = csvToBean.parse();
                
                for(PersonDTO person : persons) {
                	personService.savePersonDTO(person);
                }

                // save users list on model
                model.addAttribute("persons", persons);
                model.addAttribute("status", true);

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }

        return "file-upload-status";
    }
}
