package fr.iut.csid.projet.gestibank.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import fr.iut.csid.projet.gestibank.R;
import fr.iut.csid.projet.gestibank.activity.budget.AddBudgetActivity;
import fr.iut.csid.projet.gestibank.activity.budget.BudgetActivity;
import fr.iut.csid.projet.gestibank.activity.setting.SettingActivity;
import fr.iut.csid.projet.gestibank.activity.transaction.TransactionActivity;
import fr.iut.csid.projet.gestibank.domain.Account;
import fr.iut.csid.projet.gestibank.domain.Budget;
import fr.iut.csid.projet.gestibank.domain.Transaction;
import fr.iut.csid.projet.gestibank.sqlite.dao.AccountDAO;
import fr.iut.csid.projet.gestibank.sqlite.dao.BudgetDAO;
import fr.iut.csid.projet.gestibank.sqlite.dao.TransactionDAO;

public class MainActivity extends ActionBarActivity {
	public static Account monCompte;
	
	public static AccountDAO accountBdd;
	public static BudgetDAO budgetBdd;
	public static TransactionDAO transactionBdd;
//	private ForecastTransactionBdd forecastTransactionBdd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		CHARGER BASE
		ChargerBase();
		
//		LISTENER SUR LE BOUTON POUR AFFICHER LES OPERATIONS DU COMPTE
		Button bt_compte = (Button) findViewById(R.id.bt_compte);
		bt_compte.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (monCompte != null ) {
					Intent intent = new Intent(MainActivity.this, TransactionActivity.class);
					startActivity(intent);
				}
			}
		});
		
//		LISTENER SUR LE BOUTON POUR AFFICHER LES OPERATIONS PREVISIONNELLES
//		Button bt_previ = (Button) findViewById(R.id.bt_previ);
//		bt_previ.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(MainActivity.this, AccountPrevisionActivity.class);
//				startActivity(intent);
//			}
//		});
		
//		LISTENER SUR LE BOUTON POUR AFFICHER LES BUDGETS
		Button bt_budget = (Button) findViewById(R.id.bt_budget);
		bt_budget.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (monCompte != null ) {
					Intent intent = new Intent(MainActivity.this, BudgetActivity.class);
					startActivity(intent);
				}
			}
		});
		
//		LISTENER SUR LE BOUTON POUR PARAMETRER LES BUDGETS		
		Button bt_budget_param = (Button) findViewById(R.id.bt_budget_param);
		bt_budget_param.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (monCompte != null ) {
					Intent intent = new Intent(MainActivity.this, AddBudgetActivity.class);
					startActivity(intent);
				}
			}
		});
		
//		LISTENER SUR LE BOUTON POUR AFFICHER LE PARAMETRAGE DU COMPTE
		Button bt_compte_param = (Button) findViewById(R.id.bt_compte_param);
		bt_compte_param.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, SettingActivity.class);
				startActivity(intent);
			}
		});
		
//		LISTENER SUR LE BOUTON POUR QUITTER L'APPLICATION
		Button bt_quitter = (Button) findViewById(R.id.bt_quitter);
		bt_quitter.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
	            System.exit(0);
			}
		});
	}

//	Méthode pour charger la base de données
	private void ChargerBase(){
		accountBdd = new AccountDAO(this);
		budgetBdd = new BudgetDAO(this);
		transactionBdd = new TransactionDAO(this);
//		ForecastTransactionBdd = new ForecastTransactionBdd(this);
		
		accountBdd.openForRead();
		ArrayList<Account> listAccount = accountBdd.getAllAccount();
		accountBdd.close();
//		Si aucun compte paramétré, on force le paramétrage
		if (listAccount == null) {
			Intent intent = new Intent(MainActivity.this, SettingActivity.class);
			startActivity(intent);
		}
		else {
			monCompte = listAccount.get(0);
			
			budgetBdd.openForRead();
			ArrayList<Budget> listBudget = budgetBdd.getAllBudget();
			budgetBdd.close();
			if (listBudget != null) {
				for (Budget budget : listBudget) {
					monCompte.getListBudget().add(budget);
				}
			}
			
			transactionBdd.openForRead();
			ArrayList<Transaction> listTransaction = transactionBdd.getAllTransaction();
			transactionBdd.close();
			if (listTransaction != null) {
				for (Transaction transaction : listTransaction) {
					monCompte.getListTransaction().add(transaction);
					monCompte.rechercheBudgetById(transaction.getId_budget()).getListTransaction().add(transaction);
				}
			}
			
//			forecastTransactionBdd.openForRead();
		}
		
	}
}
