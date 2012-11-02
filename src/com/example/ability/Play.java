package com.example.ability;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Play extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
    }

    public void openPlaySimonView(View view) {
    	//Utils u = new Utils();
    	//u.openView(this, Play.class);
    	
    	Intent intent = new Intent(this, PlaySimon.class);
        startActivity(intent);
    }
    
    public void openPlayDodgingView(View view) {
    	//Utils u = new Utils();
    	//u.openView(this, Play.class);
    	
    	//Intent intent = new Intent(this, Ranking.class);
        //startActivity(intent);
    }

    
}
