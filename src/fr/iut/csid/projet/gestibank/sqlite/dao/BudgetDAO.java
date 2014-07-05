package fr.iut.csid.projet.gestibank.sqlite.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import fr.iut.csid.projet.gestibank.domain.Budget;
import fr.iut.csid.projet.gestibank.sqlite.BudgetSQLite;

public class BudgetDAO {
//	ATTRIBUTS
	private static final int VERSION = 1;
	private static final String NOM_BDD = "budget.db";
	
	private static final String TABLE_BUDGET = "table_budget";
	private static final String COL_ID = "ID";
	private static final int NUM_COL_ID = 0;
	private static final String COL_LABEL = "LABEL";
	private static final int NUM_COL_LABEL = 1;
	private static final String COL_MAX_AMOUNT = "MAX_AMOUNT";
	private static final int NUM_COL_MAX_AMOUNT = 2;
	private static final String COL_ACTUAL_AMOUNT = "ACTUAL_AMOUNT";
	private static final int NUM_COL_ACTUAL_AMOUNT = 3;	
	private SQLiteDatabase bdd;
	private BudgetSQLite budgetBaseSQLite;
	
	
//	CONSTRUCTEURS
	public BudgetDAO(Context context) {
		budgetBaseSQLite = new BudgetSQLite(context, NOM_BDD, null, VERSION);
	}
	
	
//	ACCESSEURS
	public SQLiteDatabase getBdd() {
		return bdd;
	}


//	METHODES
	public void openForWrite() {
		bdd = budgetBaseSQLite.getWritableDatabase();
	}
	
	public void openForRead() {
		bdd = budgetBaseSQLite.getReadableDatabase();
	}
	
	public void close() {
		bdd.close();
	}
	
	public long insertBudget(Budget budget) {
		ContentValues content = new ContentValues();
		content.put(COL_LABEL, budget.getLabel());
		content.put(COL_MAX_AMOUNT, budget.getMaxAmount());
		content.put(COL_ACTUAL_AMOUNT, budget.getActualAmount());
		return bdd.insert(TABLE_BUDGET, null, content);
	}
	
	public int updateBudget(int id, Budget budget) {
		ContentValues content = new ContentValues();
		content.put(COL_LABEL, budget.getLabel());
		content.put(COL_MAX_AMOUNT, budget.getMaxAmount());
		content.put(COL_ACTUAL_AMOUNT, budget.getActualAmount());
		return bdd.update(TABLE_BUDGET, content, COL_ID + " = " + id, null);
	}
	
	public int removeBudget(int id) {
		return bdd.delete(TABLE_BUDGET, COL_ID + " = " + id, null);
	}
	
	public ArrayList<Budget> getAllBudget() {
		Cursor c = bdd.query(TABLE_BUDGET, new String[] {COL_ID, COL_LABEL, COL_MAX_AMOUNT, COL_ACTUAL_AMOUNT},null, null, null, null, COL_ID);
		if (c.getCount() == 0) {
			c.close();
			return null;
		}
		ArrayList<Budget> listBudget = new ArrayList<Budget>();
		while (c.moveToNext()) {
			Budget budget = new Budget();
			budget.setId(c.getInt(NUM_COL_ID));
			budget.setLabel(c.getString(NUM_COL_LABEL));
			budget.setMaxAmount(c.getFloat(NUM_COL_MAX_AMOUNT));
			budget.setActualAmount(c.getFloat(NUM_COL_ACTUAL_AMOUNT));
			listBudget.add(budget);
		}
		c.close();
		return listBudget;
	}
}
