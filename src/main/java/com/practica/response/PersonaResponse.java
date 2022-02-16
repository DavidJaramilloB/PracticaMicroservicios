package com.practica.response;

import com.practica.entity.Persona;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaResponse {
	
	private Long id;
	
	private String nombre;
	
	private String apellido;
	
	private int edad;
	
	private String empresa;
	
	private String direccion;
	
	public PersonaResponse(Persona persona) {
		this.id = persona.getId();
		this.nombre = persona.getNombre();
		this.apellido = persona.getApellido();
		this.edad = persona.getEdad();
		
		this.empresa = persona.getEmpresa().getEmpresa();
		this.direccion = persona.getEmpresa().getDireccion();
	}
	
	
}
