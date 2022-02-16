package com.practica.controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.practica.entity.Persona;
import com.practica.request.PersonaRequest;
import com.practica.response.ErrorResponse;
import com.practica.response.PersonaResponse;
import com.practica.response.PersonaResponseAgg;
import com.practica.service.PersonaService;

@RestController
@RequestMapping("/api/persona")
public class PersonaController {
	
	Logger logger = LoggerFactory.getLogger(PersonaController.class);
	
	@Autowired
	PersonaService personaService;
	
	@PostMapping("/agregar")
	public PersonaResponseAgg crearPersona(@RequestBody PersonaRequest personaRequest) {
		Persona persona = personaService.crearPersona(personaRequest);
		
		logger.info("Se agregó una nueva persona.");
		return new PersonaResponseAgg(persona);
	}
	
	@GetMapping("/ver-personas")
	public List<PersonaResponse> verPersonas(){
		List<Persona> personasList = personaService.getPersonas();
		List<PersonaResponse> personaResponseList = new ArrayList<PersonaResponse>();
		
		String url = getUrl();
		
		RestTemplate restTemplate = new RestTemplate();
		String saludo = restTemplate.getForObject(url, String.class);
		
		personasList.stream().forEach(persona -> {
			personaResponseList.add(new PersonaResponse(persona, saludo));
		});
		
		if(!personaResponseList.isEmpty())
			return personaResponseList;
		else {
			throw new EmptyResultDataAccessException(1);
		}
	}
	
	@GetMapping("/buscarPorNombre/{nombre}")
	public PersonaResponse getByNombre(@PathVariable String nombre) {
		String url = getUrl();
		
		RestTemplate restTemplate = new RestTemplate();
		String saludo = restTemplate.getForObject(url, String.class);
		
		return new PersonaResponse(personaService.getByNombre(nombre), saludo);
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
		logger.error("Error: La persona ya existe");
		ErrorResponse error = new ErrorResponse();
		error.setCod("800");
		error.setMensaje("Esta persona ya existe");
		return error;
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ErrorResponse handleNpe(NullPointerException npe) {
		logger.error("Error: No se conoce la persona");
		ErrorResponse error = new ErrorResponse();
		error.setCod("1000");
		error.setMensaje("Persona Desconocida");
		return error;
	}
	
	public String getUrl() {
		String url;
		
		LocalTime hora = LocalTime.now();
		int periodo = hora.getHour();
		if(periodo > 5 && periodo < 12) {
			url = "http://localhost:8080/api/saludo/ver-saludo/manana";
		}else if (periodo >= 12 && periodo < 18){
			url = "http://localhost:8080/api/saludo/ver-saludo/tarde";
		}else {
			url = "http://localhost:8080/api/saludo/ver-saludo/noche";
		}
		
		return url;
	}
}
