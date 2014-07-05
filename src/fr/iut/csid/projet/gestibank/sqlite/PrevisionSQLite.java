package fr.iut.csid.projet.gestibank.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class PrevisionSQLite extends SQLiteOpenHelper {
	private static final String TABLE_FORECASTTRANSACTION = "table_forecasttransaction";
	private static final String COL_ID = "ID";
	private static final String COL_DATE = "DATE";
	private static final String COL_LABEL = "LABEL";
	private static final String COL_AMOUNT = "AMOUNT";
	private static final String COL_PERIOD = "PERIOD";
	private static final String COL_NUMBER_PAIEMENT = "NUMBER_PAIEMENT";
	private static final String COL_ID_BUDGET = "ID_BUDGET";
	
	private static final String CREATE_BDD = "CREATE TABLE " + TABLE_FORECASTTRANSACTION + " (" +
			COL_ID + " INTEGER PRIMARY KEY, " +
			COL_DATE + " TEXT NOT NULL, " +
			COL_LABEL + " TEXT NOT NULL, " +
			COL_AMOUNT + " REAL NOT NULL, " +
			COL_PERIOD + " TEXT NOT NULL, " +
			COL_NUMBER_PAIEMENT + " INTEGER NOT NULL, " +
			COL_ID_BUDGET + " INTEGER NOT NULL);";
	
	public PrevisionSQLite(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_BDD);		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP_TABLE " + TABLE_FORECASTTRANSACTION);
		onCreate(db);
	}
}
