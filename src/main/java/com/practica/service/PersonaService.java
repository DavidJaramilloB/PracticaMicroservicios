package com.practica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practica.entity.Empresa;
import com.practica.entity.Persona;
import com.practica.repository.EmpresaRepository;
import com.practica.repository.PersonaRepository;
import com.practica.request.PersonaRequest;

@Service
public class PersonaService {
	@Autowired
	PersonaRepository personaRepository;
	
	@Autowired
	EmpresaRepository empresaRepository;
	
	public Persona crearPersona(PersonaRequest personaRequest){
		Persona persona = new Persona(personaRequest);
		
		Empresa empresa = new Empresa();
		empresa.setEmpresa(personaRequest.getEmpresa());
		empresa.setDireccion(personaRequest.getDireccion());
		
		empresa = empresaRepository.save(empresa);
		
		persona.setEmpresa(empresa);
		persona = personaRepository.save(persona);
		
		return persona;
	}
	
	public List<Persona> getPersonas(){
		return personaRepository.findAll();
	}
	
	public Persona getByNombre(String nombre) {
		return personaRepository.findByNombre(nombre);
	}
}
