package com.dija.tpBatch.entities;

import java.util.Date;

public class TransactionInput {

	private int idTransaction;
	private int idCompte;
	private long montant;
	private Date dateTransaction;
	public int getIdTransaction() {
		return idTransaction;
	}
	public void setIdTransaction(int idTransaction) {
		this.idTransaction = idTransaction;
	}
	public int getIdCompte() {
		return idCompte;
	}
	public void setIdCompte(int idCompte) {
		this.idCompte = idCompte;
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
	public TransactionInput(int idTransaction, int idCompte, long montant, Date dateTransaction) {
		super();
		this.idTransaction = idTransaction;
		this.idCompte = idCompte;
		this.montant = montant;
		this.dateTransaction = dateTransaction;
	}
	public TransactionInput() {
		super();
	}
	
	
}
