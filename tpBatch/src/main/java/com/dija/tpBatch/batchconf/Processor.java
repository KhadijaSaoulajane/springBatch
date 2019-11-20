package com.dija.tpBatch.batchconf;


import java.util.Date;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dija.tpBatch.entities.Transaction;
import com.dija.tpBatch.entities.TransactionInput;
import com.dija.tpBatch.repository.ICompteRepository;

@Component
public class Processor implements ItemProcessor<TransactionInput, Transaction> {

    @Autowired
	ICompteRepository compteRepository;
	@Override
	public Transaction process(TransactionInput item) throws Exception {
		System.out.println("Processing...");
	        Transaction transaction = new Transaction();
	      
	        transaction.setIdTransaction(item.getIdTransaction());
	        transaction.setCompte(compteRepository.getCompteById(item.getIdCompte()));
	        transaction.setMontant(item.getMontant());
	        transaction.setDateTransaction(item.getDateTransaction());
	        
	       transaction.setDateDebit(new Date());
	       System.out.println("Processing good...");
	        return transaction;
	}

}
