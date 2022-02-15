package com.practica.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.practica.entity.Saludo;
import com.practica.request.SaludoRequest;
import com.practica.response.ErrorResponse;
import com.practica.response.SaludoResponse;
import com.practica.service.SaludoService;

@RestController
@RequestMapping("/api/saludo")
public class SaludoController {
	
	@Autowired
	SaludoService saludoService;
	
	@PostMapping("/agregar")
	public SaludoResponse createSaludo(@Valid @RequestBody SaludoRequest saludoRequest) {
		Saludo saludo = saludoService.createSaludo(saludoRequest);
		
		return new SaludoResponse(saludo);
	}
	
	@GetMapping("/ver-saludos")
	public List<SaludoResponse> getSaludos(){
		List<Saludo> saludoList = saludoService.getSaludos();
		List<SaludoResponse> saludoResposeList = new ArrayList<SaludoResponse>();
		
		saludoList.stream().forEach(sal -> {
			saludoResposeList.add(new SaludoResponse(sal));
		});
		
		return saludoResposeList;
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ErrorResponse handleDive(DataIntegrityViolationException dive) {
		ErrorResponse error = new ErrorResponse();
		error.setCod("900");
		error.setMensaje("Ese tipo de saludo ya existe");
		return error;
	}
}
