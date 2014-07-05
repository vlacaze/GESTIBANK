package fr.iut.csid.projet.gestibank.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class BudgetSQLite extends SQLiteOpenHelper {
	private static final String TABLE_BUDGET = "table_budget";
	private static final String COL_ID = "ID";
	private static final String COL_LABEL = "LABEL";
	private static final String COL_MAX_AMOUNT = "MAX_AMOUNT";
	private static final String COL_ACTUAL_AMOUNT = "ACTUAL_AMOUNT";
	
	private static final String CREATE_BDD = "CREATE TABLE " + TABLE_BUDGET + " (" +
			COL_ID + " INTEGER PRIMARY KEY, " +
			COL_LABEL + " TEXT NOT NULL, " +
			COL_MAX_AMOUNT + " TEXT NOT NULL, " +
			COL_ACTUAL_AMOUNT + " TEXT NOT NULL);";
	
	public BudgetSQLite(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_BDD);	
		db.execSQL("INSERT INTO " + TABLE_BUDGET + " (" + COL_LABEL + ", " + COL_MAX_AMOUNT + ", " + COL_ACTUAL_AMOUNT + ") VALUES" +
				"('ALIMENTAIRE', '0.0', '0.0');");
		db.execSQL("INSERT INTO " + TABLE_BUDGET + " (" + COL_LABEL + ", " + COL_MAX_AMOUNT + ", " + COL_ACTUAL_AMOUNT + ") VALUES" +
				"('DIVERS', '0.0', '0.0');");
		db.execSQL("INSERT INTO " + TABLE_BUDGET + " (" + COL_LABEL + ", " + COL_MAX_AMOUNT + ", " + COL_ACTUAL_AMOUNT + ") VALUES" +
				"('LOISIRS', '0.0', '0.0');");
		db.execSQL("INSERT INTO " + TABLE_BUDGET + " (" + COL_LABEL + ", " + COL_MAX_AMOUNT + ", " + COL_ACTUAL_AMOUNT + ") VALUES" +
				"('TRANSPORTS', '0.0', '0.0');");
		db.execSQL("INSERT INTO " + TABLE_BUDGET + " (" + COL_LABEL + ", " + COL_MAX_AMOUNT + ", " + COL_ACTUAL_AMOUNT + ") VALUES" +
				"('VETEMENTS', '0.0', '0.0');");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP_TABLE " + TABLE_BUDGET);
		onCreate(db);
	}
}
