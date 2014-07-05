package fr.iut.csid.projet.gestibank.activity.budget;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import fr.iut.csid.projet.gestibank.R;
import fr.iut.csid.projet.gestibank.activity.MainActivity;
import fr.iut.csid.projet.gestibank.domain.Budget;

public class AddBudgetActivity extends ActionBarActivity {
	
	private EditText txt_budgetAlimentaire;
	private EditText txt_budgetDivers;
	private EditText txt_budgetLoisirs;
	private EditText txt_budgetTransports;
	private EditText txt_budgetVetements;
//	private TextView tv_budgetRestant;
//	private DecimalFormat dec;
	private Budget budget;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_budget);
		
//		dec = new DecimalFormat("###.##");
		
		txt_budgetAlimentaire = (EditText) findViewById(R.id.txt_budgetAlimentaire);
		txt_budgetDivers = (EditText) findViewById(R.id.txt_budgetDivers);
		txt_budgetLoisirs = (EditText) findViewById(R.id.txt_budgetLoisirs);
		txt_budgetTransports = (EditText) findViewById(R.id.txt_budgetTransports);
		txt_budgetVetements = (EditText) findViewById(R.id.txt_budgetVetements);
//		tv_budgetRestant = (TextView) findViewById(R.id.tv_budgetRestant);
		
//		txt_budgetAlimentaire.setText(String.valueOf(MainActivity.monCompte.rechercheBudgetByLabel("ALIMENTAIRE").getMaxAmount()));
//		txt_budgetDivers.setText(String.valueOf(MainActivity.monCompte.rechercheBudgetByLabel("DIVERS").getMaxAmount()));
//		txt_budgetLoisirs.setText(String.valueOf(MainActivity.monCompte.rechercheBudgetByLabel("LOISIRS").getMaxAmount()));
//		txt_budgetTransports.setText(String.valueOf(MainActivity.monCompte.rechercheBudgetByLabel("TRANSPORTS").getMaxAmount()));
//		txt_budgetVetements.setText(String.valueOf(MainActivity.monCompte.rechercheBudgetByLabel("VETEMENTS").getMaxAmount()));
		afficherBudget();
//		afficherBudgetRestant();
		
//		LISTENER SUR LE BOUTON POUR ENREGISTRER LE PARAMETRAGE DES BUDGETS
		Button bt_budgetRecord = (Button) findViewById(R.id.bt_budgetRecord);
		bt_budgetRecord.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				if (txt_budgetAlimentaire.getText().toString().equals("") || txt_budgetDivers.getText().toString().equals("")
						|| txt_budgetLoisirs.getText().toString().equals("") || txt_budgetTransports.getText().toString().equals("")
						|| txt_budgetVetements.getText().toString().equals("")) {
					alerteSaisie("Veuillez saisir tous les champs!");
				}
				else {
//					Float totalBudget = Float.valueOf(txt_budgetAlimentaire.getText().toString()) +
//							Float.valueOf(txt_budgetDivers.getText().toString()) +
//							Float.valueOf(txt_budgetLoisirs.getText().toString()) +
//							Float.valueOf(txt_budgetTransports.getText().toString()) +
//							Float.valueOf(txt_budgetVetements.getText().toString());
//					
//					if (totalBudget < 0) {
//						alerteSaisie("Le total des budgets dépasse votre solde disponible!");
//					}
//					else {
						MainActivity.budgetBdd.openForWrite();
						
						budget = MainActivity.monCompte.rechercheBudgetByLabel("ALIMENTAIRE");
						budget.setMaxAmount(Float.valueOf(txt_budgetAlimentaire.getText().toString()));
						budget.setActualAmount(Float.valueOf(txt_budgetAlimentaire.getText().toString()));
						MainActivity.budgetBdd.updateBudget(budget.getId(), budget);
						
						budget = MainActivity.monCompte.rechercheBudgetByLabel("DIVERS");
						budget.setMaxAmount(Float.valueOf(txt_budgetDivers.getText().toString()));
						budget.setActualAmount(Float.valueOf(txt_budgetDivers.getText().toString()));
						MainActivity.budgetBdd.updateBudget(budget.getId(), budget);
						
						budget = MainActivity.monCompte.rechercheBudgetByLabel("LOISIRS");
						budget.setMaxAmount(Float.valueOf(txt_budgetLoisirs.getText().toString()));
						budget.setActualAmount(Float.valueOf(txt_budgetLoisirs.getText().toString()));
						MainActivity.budgetBdd.updateBudget(budget.getId(), budget);
						
						budget = MainActivity.monCompte.rechercheBudgetByLabel("TRANSPORTS");
						budget.setMaxAmount(Float.valueOf(txt_budgetTransports.getText().toString()));
						budget.setActualAmount(Float.valueOf(txt_budgetTransports.getText().toString()));
						MainActivity.budgetBdd.updateBudget(budget.getId(), budget);
						
						budget = MainActivity.monCompte.rechercheBudgetByLabel("VETEMENTS");
						budget.setMaxAmount(Float.valueOf(txt_budgetVetements.getText().toString()));
						budget.setActualAmount(Float.valueOf(txt_budgetVetements.getText().toString()));
						MainActivity.budgetBdd.updateBudget(budget.getId(), budget);
						
						MainActivity.budgetBdd.close();
						
						finish();
//					}
				}
			}
		});
		
//		LISTENER SUR LE BOUTON POUR ANNULER
		Button bt_budgetAbord = (Button) findViewById(R.id.bt_budgetAbord);
		bt_budgetAbord.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	
//	Méthode qui affiche un message d'erreur si un champ n'a pas été saisie
	private void alerteSaisie(String message) {
		Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
	}
	
//	Méthode qui affiche le budget restant à allouer aux catégories	
//	private void afficherBudgetRestant() {
//		tv_budgetRestant.setText("Budget disponible : " + String.valueOf(dec.format(MainActivity.monCompte.calculSoldeRestantPourBudget())) + " €");
//	}

//	Méthode qui affiche le solde restant pour chaque budget
	private void afficherBudget() {
		txt_budgetAlimentaire.setText(String.valueOf(MainActivity.monCompte.rechercheBudgetByLabel("ALIMENTAIRE").getMaxAmount()));
		txt_budgetDivers.setText(String.valueOf(MainActivity.monCompte.rechercheBudgetByLabel("DIVERS").getMaxAmount()));
		txt_budgetLoisirs.setText(String.valueOf(MainActivity.monCompte.rechercheBudgetByLabel("LOISIRS").getMaxAmount()));
		txt_budgetTransports.setText(String.valueOf(MainActivity.monCompte.rechercheBudgetByLabel("TRANSPORTS").getMaxAmount()));
		txt_budgetVetements.setText(String.valueOf(MainActivity.monCompte.rechercheBudgetByLabel("VETEMENTS").getMaxAmount()));
	}
}
