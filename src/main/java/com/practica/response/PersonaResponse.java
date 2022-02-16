package com.practica.response;

import org.json.JSONObject;

import com.practica.entity.Persona;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaResponse {
	
	private String saludo;			//Rest Template
	
	private Long id;
	
	private String nombre;
	
	private String apellido;
	
	private int edad;
	
	private String empresa;
	
	private String direccion;
	
	public PersonaResponse(Persona persona, String saludo) {
		JSONObject json = new JSONObject(saludo);
		this.saludo = json.getString("saludar"); 		//Rest Template
		
		this.id = persona.getId();
		this.nombre = persona.getNombre();
		this.apellido = persona.getApellido();
		this.edad = persona.getEdad();
		
		this.empresa = persona.getEmpresa().getEmpresa();
		this.direccion = persona.getEmpresa().getDireccion();
	}
}
