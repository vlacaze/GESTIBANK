package fr.iut.csid.projet.gestibank.domain;

import java.text.DateFormat;

public class Prevision {
//	ATTRIBUTS
	private int id;
	private DateFormat date;
	private String label;
	private float amount;
	private String period;
	private int numberPayment;
	
//	CONSTRUCTEURS
	public Prevision() {
		
	}
	
	public Prevision(int id, DateFormat date, String label, float amount, String period, int numberDatePayment) {
		super();
		this.id = id;
		this.date = date;
		this.label = label;
		this.amount = amount;
		this.period = period;
		this.numberPayment = numberDatePayment;
	}
	
		
//	ACCESSEURS
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public DateFormat getDate() {
		return date;
	}


	public void setDate(DateFormat date) {
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


	public String getPeriod() {
		return period;
	}


	public void setPeriod(String period) {
		this.period = period;
	}


	public int getNumberPayment() {
		return numberPayment;
	}


	public void setNumberPayment(int numberPayment) {
		this.numberPayment = numberPayment;
	}
	
		
//	METHODES
	
	
	
}
