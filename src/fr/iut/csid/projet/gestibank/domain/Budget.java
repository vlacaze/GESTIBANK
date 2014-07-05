package fr.iut.csid.projet.gestibank.domain;

import java.util.ArrayList;

public class Budget {
//	ATTRIBUTS
	private int id;
	private String label;
	private Float maxAmount;
	private Float actualAmount;
	private ArrayList<Transaction> listTransaction;
	
//	CONSTRUCTEURS
	public Budget() {
		listTransaction = new ArrayList<Transaction>();
	}
	
	public Budget(int id, String label) {
		super();
		this.id = id;
		this.label = label;
		listTransaction = new ArrayList<Transaction>();
	}
	
		
//	ACCESSEURS
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public Float getMaxAmount() {
		return maxAmount;
	}


	public void setMaxAmount(Float maxAmount) {
		this.maxAmount = maxAmount;
	}


	public Float getActualAmount() {
		return actualAmount;
	}


	public void setActualAmount(Float actualAmount) {
		this.actualAmount = actualAmount;
	}


	public ArrayList<Transaction> getListTransaction() {
		return listTransaction;
	}


	public void setListTransaction(ArrayList<Transaction> listTransaction) {
		this.listTransaction = listTransaction;
	}
	
	
//	METHODES
	@Override
	public String toString() {
	    return this.label;
	}
	
	public void majBudget(Transaction transaction) {
		listTransaction.add(transaction);
		actualAmount = actualAmount + transaction.getAmount();
	}
}
