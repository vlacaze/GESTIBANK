package fr.iut.csid.projet.gestibank.activity.budget;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import fr.iut.csid.projet.gestibank.R;
import fr.iut.csid.projet.gestibank.activity.MainActivity;

public class BudgetActivity extends ActionBarActivity {
	
	private TextView tv_alimentaire;
	private TextView tv_divers;
	private TextView tv_loisirs;
	private TextView tv_transports;
	private TextView tv_vetements;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_budget);
		
		tv_alimentaire = (TextView) findViewById(R.id.tv_alimentaire);
		tv_divers = (TextView) findViewById(R.id.tv_divers);
		tv_loisirs = (TextView) findViewById(R.id.tv_loisirs);
		tv_transports = (TextView) findViewById(R.id.tv_transports);
		tv_vetements = (TextView) findViewById(R.id.tv_vetements);
		
		afficherBudget();
		
//		LISTENER SUR LE BOUTON POUR ANNULER
		Button bt_budgetReturn = (Button) findViewById(R.id.bt_budgetReturn);
		bt_budgetReturn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	
//	Méthode qui affiche le solde restant pour chaque budget
	private void afficherBudget() {
		DecimalFormat dec = new DecimalFormat("###.##");
		tv_alimentaire.setText("Alimentaire : " + String.valueOf(dec.format(MainActivity.monCompte.rechercheBudgetByLabel("ALIMENTAIRE").getActualAmount())) + " € restants");
		tv_divers.setText("Divers : " + String.valueOf(dec.format(MainActivity.monCompte.rechercheBudgetByLabel("DIVERS").getActualAmount())) + " € restants");
		tv_loisirs.setText("Loisirs : " + String.valueOf(dec.format(MainActivity.monCompte.rechercheBudgetByLabel("LOISIRS").getActualAmount())) + " € restants");
		tv_transports.setText("Transports : " + String.valueOf(dec.format(MainActivity.monCompte.rechercheBudgetByLabel("TRANSPORTS").getActualAmount())) + " € restants");
		tv_vetements.setText("Vêtements : " + String.valueOf(dec.format(MainActivity.monCompte.rechercheBudgetByLabel("VETEMENTS").getActualAmount())) + " € restants");
	}
}
