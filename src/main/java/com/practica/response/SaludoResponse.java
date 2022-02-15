package com.practica.response;

import com.practica.entity.Saludo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SaludoResponse {
	private Long id;
	
	private String tipo;
	
	private String saludar;
	
	public SaludoResponse(Saludo saludo) {
		this.id = saludo.getId();
		this.tipo = saludo.getTipo();
		this.saludar = saludo.getSaludo();
	}
}
