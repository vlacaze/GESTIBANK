package fr.iut.csid.projet.gestibank.activity.transaction;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import fr.iut.csid.projet.gestibank.R;
import fr.iut.csid.projet.gestibank.activity.MainActivity;
import fr.iut.csid.projet.gestibank.domain.Budget;
import fr.iut.csid.projet.gestibank.domain.Transaction;

public class AddTransactionActivity extends ActionBarActivity {
	
	private DatePicker tx_operationDate;
	private EditText tx_operationLabel;
	private Spinner tx_operationCategorie;
	private RadioButton rb_operationRadio;
	private EditText tx_operationAmount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_transaction);
		
	
		tx_operationDate = (DatePicker) findViewById(R.id.tx_date);
		tx_operationLabel = (EditText) findViewById(R.id.tx_libelle);
		tx_operationCategorie = (Spinner) findViewById(R.id.cb_categorie);
		tx_operationAmount = (EditText) findViewById(R.id.tx_montant);
		
		addItemsOnSpinner();
		
		
//		LISTENER SUR LE BOUTON POUR ENREGISTRER LES INFOS DU COMPTE
		Button bt_operationRecord = (Button) findViewById(R.id.bt_operationRecord);
		bt_operationRecord.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				if (tx_operationLabel.getText().toString().equals("") || tx_operationAmount.getText().toString().equals("")) {
					alerteSaisie();
				}
				else {
					Budget budget = (Budget) tx_operationCategorie.getSelectedItem();
					String montant = tx_operationAmount.getText().toString();
					
					rb_operationRadio = (RadioButton) findViewById(R.id.radio0);
					if (rb_operationRadio.isChecked() == true) {
						montant = "-" + montant;
					}
					String laDate = tx_operationDate.getDayOfMonth() + "/" + tx_operationDate.getMonth() + "/" + tx_operationDate.getYear();
					Transaction transaction = new Transaction(laDate, tx_operationLabel.getText().toString(), Float.valueOf(montant), budget.getId());
					MainActivity.transactionBdd.openForWrite();
					MainActivity.transactionBdd.insertTransaction(transaction);
					MainActivity.transactionBdd.close();
					
					MainActivity.monCompte.getListTransaction().add(transaction);
					
					MainActivity.monCompte.rechercheBudgetById(budget.getId()).majBudget(transaction);
					MainActivity.budgetBdd.openForWrite();
					MainActivity.budgetBdd.updateBudget(budget.getId(), MainActivity.monCompte.rechercheBudgetById(budget.getId()));
					MainActivity.budgetBdd.close();
					
					Intent intent = new Intent(AddTransactionActivity.this, TransactionActivity.class);
					startActivity(intent);
					finish();
				}

			}
		});
		
//		LISTENER SUR LE BOUTON POUR ANNULER
		Button bt_operationAbord = (Button) findViewById(R.id.bt_operationAbord);
		bt_operationAbord.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		

	}
	
//	Méthode qui initialise la liste déroulante
	private void addItemsOnSpinner() {
		ArrayList<Budget> listBudget = MainActivity.monCompte.getListBudget();
		ArrayAdapter<Budget> dataAdapter = new ArrayAdapter<Budget>(this, android.R.layout.simple_spinner_item, listBudget);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		tx_operationCategorie.setAdapter(dataAdapter);
	}
//	Méthode qui affiche un message d'erreur si un champ n'a pas été saisie
	private void alerteSaisie() {
		Toast.makeText(this,"Veuillez saisir tous les champs!",Toast.LENGTH_SHORT).show();
	}
}
