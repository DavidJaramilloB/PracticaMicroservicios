package com.practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practica.entity.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

}
