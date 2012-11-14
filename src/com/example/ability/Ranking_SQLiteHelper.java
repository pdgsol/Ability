package com.example.ability;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class Ranking_SQLiteHelper extends SQLiteOpenHelper {
		String sqlCreate_RankingSimon = "CREATE TABLE RankingSimon (nick TEXT, date TEXT, round INTEGER, score INTEGER)";
		String sqlCreate_RankingDodging = "CREATE TABLE RankingDodging (nick TEXT, date TEXT, time INTEGER, score INTEGER)";
		public Ranking_SQLiteHelper(Context contexto, String nombre,
		CursorFactory factory, int version) {
		super(contexto, nombre, factory, version);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(sqlCreate_RankingSimon);
		db.execSQL(sqlCreate_RankingDodging);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnterior,
	int versionNueva) {

		db.execSQL("DROP TABLE IF EXISTS RankingSimon");
		db.execSQL("DROP TABLE IF EXISTS RankingDodging");

		db.execSQL(sqlCreate_RankingSimon);
		db.execSQL(sqlCreate_RankingDodging);
	}
	
	public void insertPlayerRankingSimon(SQLiteDatabase db, String nick, String date, Integer round,  Integer score )
	{
		ContentValues newInsert = new ContentValues();
		newInsert.put("nick", nick);
		newInsert.put("date",date);
		newInsert.put("round",round);
		newInsert.put("score",score);	
		db.insert("RankingSimon",null,newInsert);
		
	}
	
	public void insertPlayerRankingDodging(SQLiteDatabase db, String nick, String date, Integer time,  Integer score )
	{
		ContentValues newInsert = new ContentValues();
		newInsert.put("nick", nick);
		newInsert.put("date",date);
		newInsert.put("time",time);
		newInsert.put("score",score);	
		db.insert("RankingDodging",null,newInsert);
		;
	}
	
	
	public String[][] selectAll(SQLiteDatabase db, String nameTable)
	{		
		Cursor c = db.rawQuery(" SELECT * FROM " + nameTable + " ORDER BY score ASC", null);
		
		String[][] sResult = new String[c.getCount()][4];
		if (c.moveToFirst()) {
			int i = 0;
			do {
				sResult[i][0] = c.getString(0);
				sResult[i][1] = c.getString(1);
				sResult[i][2] = c.getString(2);
				sResult[i][3] = c.getString(3);
				++i;
			} while(c.moveToNext());
		}
		c.close();
		return sResult;
	}
	
	public void resetRankinSimon(SQLiteDatabase db)
	{
		db.delete("RankingSimon", null, null);
	}
	
	public void RankingDodging(SQLiteDatabase db)
	{
		db.delete("RankingDodging", null, null);
	}
	

}