package com.practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practica.entity.Saludo;

@Repository
public interface SaludoRepository extends JpaRepository<Saludo, Long> {
	Saludo findByTipo(String tipo);
}