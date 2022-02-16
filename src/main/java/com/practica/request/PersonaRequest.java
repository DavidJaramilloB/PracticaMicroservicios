package com.practica.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaRequest {
	private Long id;
	
	private String nombre;
	
	private String apellido;
	
	private int edad;
	
	private String empresa;
	
	private String direccion;
}
