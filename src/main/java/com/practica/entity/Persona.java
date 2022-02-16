package com.practica.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.practica.request.PersonaRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "persona")
public class Persona {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "apellido")
	private String apellido;
	
	@Column(name = "edad")
	private int edad;
	
	@OneToOne
	@JoinColumn(name = "idEmpresa")
	private Empresa empresa;
	
	public Persona(PersonaRequest personaRequest) {
		this.nombre = personaRequest.getNombre();
		this.apellido = personaRequest.getApellido();
		this.edad = personaRequest.getEdad();
	}
	
}
