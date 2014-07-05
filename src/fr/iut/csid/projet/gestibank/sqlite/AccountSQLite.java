package fr.iut.csid.projet.gestibank.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class AccountSQLite extends SQLiteOpenHelper{
	private static final String TABLE_ACCOUNT = "table_account";
	private static final String COL_ID = "ID";
	private static final String COL_LABEL = "LABEL";
	private static final String COL_BALANCE = "BALANCE";
	
	private static final String CREATE_BDD = "CREATE TABLE " + TABLE_ACCOUNT + " (" +
												COL_ID + " INTEGER PRIMARY KEY, " +
												COL_LABEL + " TEXT NOT NULL, " +
												COL_BALANCE + " REAL NOT NULL);";
	
	public AccountSQLite(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_BDD);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP_TABLE " + TABLE_ACCOUNT);
		onCreate(db);
	}

}
