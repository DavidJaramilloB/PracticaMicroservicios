package com.practica.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practica.entity.Persona;
import com.practica.request.PersonaRequest;
import com.practica.response.ErrorResponse;
import com.practica.response.PersonaResponse;
import com.practica.service.PersonaService;

@RestController
@RequestMapping("/api/persona")
public class PersonaController {
	
	@Autowired
	PersonaService personaService;
	
	@PostMapping("/agregar")
	public PersonaResponse crearPersona(@RequestBody PersonaRequest personaRequest) {
		Persona persona = personaService.crearPersona(personaRequest);
		
		return new PersonaResponse(persona);
	}
	
	@GetMapping("/ver-personas")
	public List<PersonaResponse> verPersonas(){
		List<Persona> personasList = personaService.getPersonas();
		List<PersonaResponse> personaResponseList = new ArrayList<PersonaResponse>();
		
		personasList.stream().forEach(persona -> {
			personaResponseList.add(new PersonaResponse(persona));
		});
		
		if(!personaResponseList.isEmpty())
			return personaResponseList;
		else {
			throw new EmptyResultDataAccessException(1);
		}
	}
	
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ErrorResponse handleErdae(EmptyResultDataAccessException erdae) {
		ErrorResponse error = new ErrorResponse();
		error.setCod("1500");
		error.setMensaje("No hay personas en la base de datos");
		return error;
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ErrorResponse handleDive(DataIntegrityViolationException dive) {
		ErrorResponse error = new ErrorResponse();
		error.setCod("800");
		error.setMensaje("Esta persona ya existe");
		return error;
	}
}
