package fr.iut.csid.projet.gestibank.activity.setting;

import java.util.ArrayList;

import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import fr.iut.csid.projet.gestibank.R;
import fr.iut.csid.projet.gestibank.activity.MainActivity;
import fr.iut.csid.projet.gestibank.domain.Account;
import fr.iut.csid.projet.gestibank.domain.Budget;

public class SettingActivity extends ActionBarActivity {
	
	private EditText edit_accountLabel;
	private EditText edit_accountAmount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);		
//		DecimalFormat dec = new DecimalFormat("###.##");
		
//		Si le compte est déjà paramétré on récupère les informations
		if (MainActivity.monCompte != null) {
			edit_accountLabel = (EditText) findViewById(R.id.txt_accountLabel);
			edit_accountLabel.setText(MainActivity.monCompte.getLabel());
			edit_accountAmount = (EditText) findViewById(R.id.txt_accountAmount);
			String montant = String.valueOf(MainActivity.monCompte.getBalance());
//			montant.replace(".", ",");
			edit_accountAmount.setText(montant);
		}
		
//		LISTENER SUR LE BOUTON POUR ENREGISTRER LES INFOS DU COMPTE
		Button bt_accountRecord = (Button) findViewById(R.id.bt_accountRecord);
		bt_accountRecord.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				edit_accountLabel = (EditText) findViewById(R.id.txt_accountLabel);
				edit_accountAmount = (EditText) findViewById(R.id.txt_accountAmount);
				if (edit_accountLabel.getText().toString().equals("") || edit_accountAmount.getText().toString().equals("")) {
					alerteSaisie();
				}
				else {
					float montant = Float.valueOf(edit_accountAmount.getText().toString());
					Account account = new Account(edit_accountLabel.getText().toString(), montant);
					MainActivity.accountBdd.openForWrite();
					if (MainActivity.monCompte == null) {
						MainActivity.accountBdd.insertAccount(account);
						MainActivity.monCompte = account;
						MainActivity.budgetBdd.openForRead();
						ArrayList<Budget> listBudget = MainActivity.budgetBdd.getAllBudget();
						MainActivity.budgetBdd.close();
						if (listBudget != null) {
							for (Budget budget : listBudget) {
								MainActivity.monCompte.getListBudget().add(budget);
							}
						}
					}
					else {
						MainActivity.accountBdd.updateAccount(MainActivity.monCompte.getId(), account);
						MainActivity.monCompte.setLabel(account.getLabel());
						MainActivity.monCompte.setBalance(account.getBalance());
					}
					MainActivity.accountBdd.close();
					finish();
				}
			}
		});
		
//		LISTENER SUR LE BOUTON POUR ANNULER
		Button bt_accountbord = (Button) findViewById(R.id.bt_accountAbord);
		bt_accountbord.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

//	Méthode qui affiche un message d'erreur si un champ n'a pas été saisie
	private void alerteSaisie() {
		Toast.makeText(this,"Veuillez saisir tous les champs!",Toast.LENGTH_SHORT).show();
	}
}
