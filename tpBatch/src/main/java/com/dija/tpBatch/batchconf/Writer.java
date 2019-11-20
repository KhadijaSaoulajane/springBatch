package com.dija.tpBatch.batchconf;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dija.tpBatch.entities.Compte;
import com.dija.tpBatch.entities.Transaction;
import com.dija.tpBatch.repository.ICompteRepository;
import com.dija.tpBatch.repository.ITransactionRepository;

@Component
public class Writer implements ItemWriter<Transaction> {

	@Autowired
	private ITransactionRepository transactionRepository;
	@Autowired 
	private ICompteRepository compteRepository;
	
	@Transactional
	@Override
	public void write(List<? extends Transaction> items) throws Exception {
		System.out.println("Writing Data...");

		for(Transaction t : items) {
			
			transactionRepository.save(t);
			Compte c = new Compte();
			c= t.getCompte();
			c.setSolde(c.getSolde()-t.getMontant());
            compteRepository.save(c);
            
		}
		System.out.println("Data written successfull...");
		
	}

}
