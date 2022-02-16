package com.practica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practica.entity.Saludo;
import com.practica.repository.SaludoRepository;
import com.practica.request.SaludoRequest;

@Service
public class SaludoService {
	
	@Autowired
	SaludoRepository saludoRepository;

	public Saludo createSaludo(SaludoRequest saludoRequest) {
		Saludo saludo = new Saludo(saludoRequest);
			
		saludo = saludoRepository.save(saludo);
		return saludo;
	}
	
	public List<Saludo> getSaludos(){
		return saludoRepository.findAll();
	}
	
	public Saludo getByTipo(String tipo) {
		return saludoRepository.findByTipo(tipo);
	}
}
