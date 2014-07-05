package fr.iut.csid.projet.gestibank.activity.transaction;

import java.text.DecimalFormat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import fr.iut.csid.projet.gestibank.R;
import fr.iut.csid.projet.gestibank.activity.MainActivity;
import fr.iut.csid.projet.gestibank.domain.Transaction;

public class TransactionActivity extends ActionBarActivity {
	
	private TextView tv_solde;
	private DecimalFormat dec;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction);
		
		dec = new DecimalFormat("###.##");
		
		tv_solde = (TextView) findViewById(R.id.tv_solde);
		
		afficherSolde();
		
		afficherOperation();
		
//		LISTENER SUR LE BOUTON POUR SAISIR LES OPERATIONS DU COMPTE
		Button bt_ajouter = (Button) findViewById(R.id.bt_ajouter);
		bt_ajouter.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(TransactionActivity.this, AddTransactionActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
	
//	Méthode qui affiche le solde actuel du compte
	private void afficherSolde() {
		tv_solde.setText("Solde : " + String.valueOf(dec.format(MainActivity.monCompte.calculSoldeRestant())) + " €");
	}
	
//	Méthode qui affiche la liste des transactions dans le tableLayout
	private void afficherOperation() {
		String couleur = "#a2dbff";
		TableLayout tableLayout = (TableLayout) findViewById(R.id.tableOperation);
		
		for (Transaction transaction : MainActivity.monCompte.getListTransaction()) {
			
			TableRow tableRow = new TableRow(this);
			tableRow.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

			TextView date = new TextView(this);
			date.setText(transaction.getDate());
			date.setBackgroundColor(Color.parseColor(couleur));
			date.setPadding(15, 15, 15, 15);
			
			TextView libelle = new TextView(this);
			libelle.setText(transaction.getLabel());
			libelle.setBackgroundColor(Color.parseColor(couleur));
			libelle.setPadding(15, 15, 15, 15);
			
			TextView montant = new TextView(this);
			montant.setText(String.valueOf(dec.format(transaction.getAmount())));
			montant.setBackgroundColor(Color.parseColor(couleur));
			montant.setPadding(15, 15, 15, 15);
			
			TextView categorie = new TextView(this);
			categorie.setText(MainActivity.monCompte.rechercheBudgetById(transaction.getId_budget()).getLabel());
			categorie.setBackgroundColor(Color.parseColor(couleur));
			categorie.setPadding(15, 15, 15, 15);
			
			tableRow.addView(date);
			tableRow.addView(libelle);
			tableRow.addView(montant);
			tableRow.addView(categorie);
			tableLayout.addView(tableRow);
			
			if (couleur == "#a2dbff") {
				couleur = "#39a7e6";
			}
			else {
				couleur = "#a2dbff";
			}
		}		
	}
}
