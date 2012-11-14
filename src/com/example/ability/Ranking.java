package com.example.ability;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

public class Ranking extends Activity {

	private Ranking_DataController ranking_DataController;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        
        
        Resources res = getResources();
        
        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();
	     
	    TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
	    spec.setContent(R.id.tab_simon_ranking);
	    
	    spec.setIndicator("Simon",
	            res.getDrawable(android.R.drawable.ic_btn_speak_now));
	    tabs.addTab(spec);
	     
	    spec=tabs.newTabSpec("mitab2");
	    spec.setContent(R.id.tab_dodging_ranking);
	    spec.setIndicator("Dodging",
	            res.getDrawable(android.R.drawable.ic_dialog_map));
	    tabs.addTab(spec);
	    

	    tabs.setCurrentTab(0);
	    
	    ranking_DataController = new Ranking_DataController(this);
        refreshRankingSimon();
	    refreshRankingDodging();
	
    }


    public void refreshRankingSimon()
    {
		String[][] sResult = ranking_DataController.getRanking("RankingSimon");
		createRanking(sResult, R.id.simon_ranking_tableLayout);
    }
    
    public void refreshRankingDodging()
    {
		String[][] sResult = ranking_DataController.getRanking("RankingDodging");
		createRanking(sResult, R.id.dodging_ranking_tableLayout);
    }
    
    public void createRanking(String[][] sResult, Integer idRanking)
    {
    
			TableLayout tl = (TableLayout)findViewById(idRanking);       
	    	for(int i = 0; i < sResult.length; ++i)
	    	{
			TableRow tr = new TableRow(this);
			 tr.setLayoutParams(new LayoutParams(
			                LayoutParams.FILL_PARENT,
			                LayoutParams.WRAP_CONTENT));
			      /* Create a Button to be the row-content. */
			 for(int j = 0; j < sResult[i].length; ++j)
			 {
			    TextView textview = new TextView(this);
			  	textview.setText(sResult[i][j]);
			  	textview.setLayoutParams(new LayoutParams(
			             LayoutParams.FILL_PARENT,
			             LayoutParams.WRAP_CONTENT));
			  	tr.addView(textview);
			  
			 }
			tl.addView(tr);
	    	
		}
    }
    
    
    
}
