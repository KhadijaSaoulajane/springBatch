package com.dija.tpBatch.batchconf;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;

import com.dija.tpBatch.entities.Transaction;
import com.dija.tpBatch.repository.ICompteRepository;

public class RecordFieldSetMapper implements FieldSetMapper<Transaction> {
	 @Autowired
     ICompteRepository compteRepository;
	@Override
	public Transaction mapFieldSet(FieldSet fieldSet) throws BindException {
		   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        Transaction transaction = new Transaction();
	      
	       
	        transaction.setIdTransaction(fieldSet.readInt("idTransaction"));
	        transaction.setCompte(compteRepository.getCompteById(fieldSet.readInt("idCompte")));
	        transaction.setMontant(fieldSet.readLong("montant"));
	        String dateString = fieldSet.readString("dateTransaction");
	        try {
	            transaction.setDateTransaction(dateFormat.parse(dateString));
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        return transaction;
	}

}
