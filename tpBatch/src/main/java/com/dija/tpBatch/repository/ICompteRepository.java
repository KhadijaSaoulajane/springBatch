package com.dija.tpBatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dija.tpBatch.entities.Compte;


public interface ICompteRepository extends JpaRepository<Compte, Integer>{

	
	@Query("select c from Compte c where c.idCompte=:x")
	public Compte getCompteById(@Param("x")int id);
}
