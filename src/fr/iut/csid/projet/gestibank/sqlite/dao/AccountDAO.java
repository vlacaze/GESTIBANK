package fr.iut.csid.projet.gestibank.sqlite.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import fr.iut.csid.projet.gestibank.domain.Account;
import fr.iut.csid.projet.gestibank.sqlite.AccountSQLite;

public class AccountDAO {
//	ATTRIBUTS
	private static final int VERSION = 1;
	private static final String NOM_BDD = "account.db";
	
	private static final String TABLE_ACCOUNT = "table_account";
	private static final String COL_ID = "ID";
	private static final int NUM_COL_ID = 0;
	private static final String COL_LABEL = "LABEL";
	private static final int NUM_COL_LABEL = 1;
	private static final String COL_BALANCE = "BALANCE";
	private static final int NUM_COL_BALANCE = 2;
	
	private SQLiteDatabase bdd;
	private AccountSQLite accountBaseSQLite;
	
	
//	CONSTRUCTEURS
	public AccountDAO(Context context) {
		accountBaseSQLite = new AccountSQLite(context, NOM_BDD, null, VERSION);
	}
	
	
//	ACCESSEURS
	public SQLiteDatabase getBdd() {
		return bdd;
	}


//	METHODES
	public void openForWrite() {
		bdd = accountBaseSQLite.getWritableDatabase();
	}
	
	public void openForRead() {
		bdd = accountBaseSQLite.getReadableDatabase();
	}
	
	public void close() {
		bdd.close();
	}
	
	public long insertAccount(Account account) {
		ContentValues content = new ContentValues();
		content.put(COL_LABEL, account.getLabel());
		content.put(COL_BALANCE, account.getBalance());
		return bdd.insert(TABLE_ACCOUNT, null, content);
	}
	
	public int updateAccount(int id, Account account) {
		ContentValues content = new ContentValues();
		content.put(COL_LABEL, account.getLabel());
		content.put(COL_BALANCE, account.getBalance());
		return bdd.update(TABLE_ACCOUNT, content, COL_ID + " = " + id, null);
	}
	
	public int removeAccount(int id) {
		return bdd.delete(TABLE_ACCOUNT, COL_ID + " = " + id, null);
	}
	
	public ArrayList<Account> getAllAccount() {
		Cursor c = bdd.query(TABLE_ACCOUNT, new String[] {COL_ID, COL_LABEL, COL_BALANCE},null, null, null, null, COL_ID);
		if (c.getCount() == 0) {
			c.close();
			return null;
		}
		ArrayList<Account> listAccount = new ArrayList<Account>();
		while (c.moveToNext()) {
			Account account = new Account();
			account.setId(c.getInt(NUM_COL_ID));
			account.setLabel(c.getString(NUM_COL_LABEL));
			account.setBalance(c.getFloat(NUM_COL_BALANCE));
			listAccount.add(account);
		}
		c.close();
		return listAccount;
	}
	
	
	
}
