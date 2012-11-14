package com.example.ability;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class Ranking_DataController {
	
	private Ranking_SQLiteHelper ranking_SQLiteHelper;
	private SQLiteDatabase db;
	public Ranking_DataController(Context context)
	{
    	ranking_SQLiteHelper = new Ranking_SQLiteHelper(context, "Ranking", null, 1);
	}
	
	public void newtPlayerRankingSimon(String nick, String date, Integer round,  Integer score)
	{
		db = ranking_SQLiteHelper.getWritableDatabase();
		if(db != null) {
			ranking_SQLiteHelper.insertPlayerRankingSimon(db,nick,date,round,score);
		}
		db.close();
	}
	
	public void newPlayerRankingDodging(String nick, String date, Integer time,  Integer score)
	{
		db = ranking_SQLiteHelper.getWritableDatabase();
		if(db != null) {
		ranking_SQLiteHelper.insertPlayerRankingDodging(db,nick,date,time,score);
		}
		db.close();
	}
	
	public String[][] getRanking(String ranking)
	{
		String[][] sResult  = null;
		db = ranking_SQLiteHelper.getWritableDatabase();
		if(db != null) {
			sResult = ranking_SQLiteHelper.selectAll(db,ranking);
		}
		db.close();
	  	return sResult;
	}
}
