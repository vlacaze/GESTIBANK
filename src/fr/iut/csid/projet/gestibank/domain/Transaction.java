package fr.iut.csid.projet.gestibank.domain;


public class Transaction {
//	ATTRIBUTS
	private int id;
	private String date;
	private String label;
	private float amount;
	private int id_budget;
	
//	CONSTRUCTEURS
	public Transaction() {
		
	}
	
	public Transaction(String date, String label, float amount, int id_budget) {
		super();
		this.date = date;
		this.label = label;
		this.amount = amount;
		this.id_budget = id_budget;
	}
	
	public Transaction(int id, String date, String label, float amount, int id_budget) {
		super();
		this.id = id;
		this.date = date;
		this.label = label;
		this.amount = amount;
		this.id_budget = id_budget;
	}
	
		
//	ACCESSEURS
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public float getAmount() {
		return amount;
	}


	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getId_budget() {
		return id_budget;
	}

	public void setId_budget(int id_budget) {
		this.id_budget = id_budget;
	}
	
	
//	METHODES
	
	
	
}
