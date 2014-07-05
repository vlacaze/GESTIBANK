package fr.iut.csid.projet.gestibank.sqlite.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import fr.iut.csid.projet.gestibank.domain.Transaction;
import fr.iut.csid.projet.gestibank.sqlite.TransactionSQLite;

public class TransactionDAO {
//	ATTRIBUTS
	private static final int VERSION = 1;
	private static final String NOM_BDD = "transaction.db";
	
	private static final String TABLE_TRANSACTION = "table_transaction";
	private static final String COL_ID = "ID";
	private static final int NUM_COL_ID = 0;
	private static final String COL_DATE = "DATE";
	private static final int NUM_COL_DATE = 1;
	private static final String COL_LABEL = "LABEL";
	private static final int NUM_COL_LABEL = 2;
	private static final String COL_AMOUNT= "AMOUNT";
	private static final int NUM_COL_AMOUNT = 3;
	private static final String COL_ID_BUDGET = "ID_BUDGET";
	private static final int NUM_COL_ID_BUDGET = 4;
	
	private SQLiteDatabase bdd;
	private TransactionSQLite transactionBaseSQLite;
	
	
//	CONSTRUCTEURS
	public TransactionDAO(Context context) {
		transactionBaseSQLite = new TransactionSQLite(context, NOM_BDD, null, VERSION);
	}
	
	
//	ACCESSEURS
	public SQLiteDatabase getBdd() {
		return bdd;
	}


//	METHODES
	public void openForWrite() {
		bdd = transactionBaseSQLite.getWritableDatabase();
	}
	
	public void openForRead() {
		bdd = transactionBaseSQLite.getReadableDatabase();
	}
	
	public void close() {
		bdd.close();
	}
	
	public long insertTransaction(Transaction transaction) {
		ContentValues content = new ContentValues();
		content.put(COL_DATE, String.valueOf(transaction.getDate()));
		content.put(COL_LABEL, transaction.getLabel());
		content.put(COL_AMOUNT, transaction.getAmount());
//		content.put(COL_ID_ACCOUNT, id_account);
		content.put(COL_ID_BUDGET,transaction.getId_budget());
		
		return bdd.insert(TABLE_TRANSACTION, null, content);
	}
	
//	public int updateTransaction(int id, Transaction transaction) {
//		ContentValues content = new ContentValues();
//		content.put(COL_DATE, String.valueOf(transaction.getDate()));
//		content.put(COL_LABEL, transaction.getLabel());
//		content.put(COL_AMOUNT, transaction.getAmount());
//		return bdd.update(TABLE_TRANSACTION, content, COL_ID + " = " + id, null);
//	}
	
	public int removeTransaction(int id) {
		return bdd.delete(TABLE_TRANSACTION, COL_ID + " = " + id, null);
	}
	
	public ArrayList<Transaction> getAllTransaction() {
		Cursor c = bdd.query(TABLE_TRANSACTION, new String[] {COL_ID, COL_DATE, COL_LABEL, COL_AMOUNT, COL_ID_BUDGET},null, null, null, null, COL_ID);
		if (c.getCount() == 0) {
			c.close();
			return null;
		}
		ArrayList<Transaction> listTransaction = new ArrayList<Transaction>();
		while (c.moveToNext()) {
			Transaction transaction = new Transaction();
			transaction.setId(c.getInt(NUM_COL_ID));
			
			transaction.setDate(c.getString(NUM_COL_DATE));
			
			transaction.setLabel(c.getString(NUM_COL_LABEL));
			transaction.setAmount(c.getFloat(NUM_COL_AMOUNT));
			
			transaction.setId_budget(c.getInt(NUM_COL_ID_BUDGET));
			listTransaction.add(transaction);
		}
		c.close();
		return listTransaction;
	}
}
