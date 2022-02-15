package com.practica.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaludoRequest {
	@NotNull(message = "El tipo es requerido")
	private String tipo;
	
	@NotNull(message = "El saludo es requerido")
	private String saludo;
}
