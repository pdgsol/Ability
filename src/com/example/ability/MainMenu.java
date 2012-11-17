package com.example.ability;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
    	Intent intent = new Intent(this, Play.class);
        startActivity(intent);
    }
    
    public void openRankingView(View view) {
    	Intent intent = new Intent(this, Ranking.class);
        startActivity(intent);
    }
    
    public void openHelpView(View view) {
    	Intent intent = new Intent(this, Ranking.class);
        startActivity(intent);
    }
    
    public void openSettingsView(View view) {
    	Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
    

    public void exitAlert(View view) {

    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage("Are you sure you want to exit?")
    	.setCancelable(false)
    	.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	public void onClick(DialogInterface dialog, int id) {
    		MainMenu.this.finish();
    	}
    	})
    	.setNegativeButton("No", new DialogInterface.OnClickListener() {
    	public void onClick(DialogInterface dialog, int id) {
    	dialog.cancel();
    	}
    	});
    	AlertDialog alert = builder.create();
    	alert.show();
    }
    
    
}
