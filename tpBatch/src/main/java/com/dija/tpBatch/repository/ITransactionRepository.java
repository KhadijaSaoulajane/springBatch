package com.dija.tpBatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dija.tpBatch.entities.Transaction;

public interface ITransactionRepository extends JpaRepository<Transaction, Integer>{

}
