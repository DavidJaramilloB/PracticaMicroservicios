package com.practica.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.UniqueElements;

import com.practica.request.SaludoRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "saludo")
public class Saludo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "tipo")
	private String tipo;
	
	@Column(name = "saludo")
	private String saludo;
	
	public Saludo(SaludoRequest saludoRequest) {
		this.tipo = saludoRequest.getTipo();
		this.saludo = saludoRequest.getSaludo();
	}
}
