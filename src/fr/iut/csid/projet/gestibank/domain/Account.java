package fr.iut.csid.projet.gestibank.domain;

import java.util.ArrayList;

public class Account {
//	ATTRIBUTS
	private int id;
	private String label;
	private float balance;
	private ArrayList<Transaction> listTransaction;
	private ArrayList<Prevision> listForecastTransaction;
	private ArrayList<Budget> listBudget;
	
//	CONSTRUCTEURS
	public Account() {
		listBudget = new ArrayList<Budget>();
		listTransaction = new ArrayList<Transaction>();
		listForecastTransaction = new ArrayList<Prevision>();	
	}
	
	public Account(String label, float balance) {
		super();
		this.label = label;
		this.balance = balance;
		listBudget = new ArrayList<Budget>();
		listTransaction = new ArrayList<Transaction>();
		listForecastTransaction = new ArrayList<Prevision>();		
	}
	
	public Account(int id, String label, float balance) {
		super();
		this.id = id;
		this.label = label;
		this.balance = balance;
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


	public float getBalance() {
		return balance;
	}


	public void setBalance(float balance) {
		this.balance = balance;
	}


	public ArrayList<Transaction> getListTransaction() {
		return listTransaction;
	}


	public void setListTransaction(ArrayList<Transaction> listTransaction) {
		this.listTransaction = listTransaction;
	}


	public ArrayList<Prevision> getListForecastTransaction() {
		return listForecastTransaction;
	}


	public void setListForecastTransaction(ArrayList<Prevision> listForecastTransaction) {
		this.listForecastTransaction = listForecastTransaction;
	}


	public ArrayList<Budget> getListBudget() {
		return listBudget;
	}


	public void setListBudget(ArrayList<Budget> listBudget) {
		this.listBudget = listBudget;
	}
	
	
//	METHODES
	public Budget rechercheBudgetById(int budgetId) {
		for (Budget budget : listBudget) {
			if (budget.getId() == budgetId) {
				return budget;
			}
		}
		return null;
	}
	
	public Budget rechercheBudgetByLabel(String budgetLabel) {
		for (Budget budget : listBudget) {
			if (budget.getLabel().equalsIgnoreCase(budgetLabel)) {
				return budget;
			}
		}
		return null;
	}
	
	public Float calculSoldeRestant() {
		Float res = balance;
		for (Transaction transaction : listTransaction) {
			res = res + transaction.getAmount();
		}
		return res;
	}
	
	public Float calculSoldeRestantPourBudget() {
		Float res = balance;
		for (Budget budget : listBudget) {
			res = res - budget.getMaxAmount();
		}
		return res;
	}
}
