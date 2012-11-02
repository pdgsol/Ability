package com.example.ability;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainMenu extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

 
    
    public void openPlayView(View view) {
    	//Utils u = new Utils();
    	//u.openView(this, Play.class);
    	
    	Intent intent = new Intent(this, Play.class);
        startActivity(intent);
    }
    
    public void openRankingView(View view) {
    	//Utils u = new Utils();
    	//u.openView(this, Play.class);
    	
    	Intent intent = new Intent(this, Ranking.class);
        startActivity(intent);
    }
    
    public void openHelpView(View view) {
    	//Utils u = new Utils();
    	//u.openView(this, Play.class);
    	
    	Intent intent = new Intent(this, Ranking.class);
        startActivity(intent);
    }

    
    
}
