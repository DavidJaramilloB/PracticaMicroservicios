package com.practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practica.entity.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long>{
	Persona findByNombre(String nombre);
}
