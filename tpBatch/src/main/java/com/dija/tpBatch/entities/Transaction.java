package com.dija.tpBatch.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor @NoArgsConstructor @Data @ToString
public class Transaction {
	@Id
	private int idTransaction;
	private long montant;
	@ManyToOne
	@JoinColumn(name="idCompte")
	private Compte compte;

	private Date dateTransaction;
	private Date dateDebit;
	public int getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(int idTransaction) {
		this.idTransaction = idTransaction;
	}

	
	
	public long getMontant() {
		return montant;
	}

	public void setMontant(long montant) {
		this.montant = montant;
	}

	public Date getDateTransaction() {
		return dateTransaction;
	}

	public void setDateTransaction(Date dateTransaction) {
		this.dateTransaction = dateTransaction;
	}

	public Date getDateDebit() {
		return dateDebit;
	}

	public void setDateDebit(Date dateDebit) {
		this.dateDebit = dateDebit;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	
	
	

}
