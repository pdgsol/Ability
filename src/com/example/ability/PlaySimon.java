package com.example.ability;

import java.util.Calendar;
import java.util.Random;
import java.util.Vector;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PlaySimon extends Activity {

    enum eSimonColor
    {
    	Green,
    	Blue,
    	Red,
    	Yellow
    }
	
	private Integer numberColors = 4;
    private Integer iIDColorPressed;
    private Integer iRound;
    private Integer iMaxRound;
    private Integer iScore;
    private Vector<eSimonColor> vSequence;
    private Integer iActualarIDColor;
    private Integer iIndexNextColor;
    private Integer iIndexSequence;
    private boolean bPlayerTurn = false;
    private Integer iTimeBlink;
    private Integer iTimeBetween;
    private SharedPreferences prefs;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_simon);
        initConfig(); 
		RelativeLayout tl = (RelativeLayout)findViewById(R.id.relative_layout_simon); 
		registerForContextMenu(tl);
    }
    
    private void restartGame()
    {
    	prefs = getSharedPreferences("Preferences",this.MODE_PRIVATE);
    	SharedPreferences.Editor editor = prefs.edit();
    	editor.putBoolean("simon_save_game", false);
    	initConfig();
    }
    
    private void initConfig()
    {
    	prefs = getSharedPreferences("Preferences",this.MODE_PRIVATE);

    	if(prefs.getBoolean("simon_save_game", false)) {
	        iRound = prefs.getInt("simon_save_party_round", 1);
	        iScore = prefs.getInt("simon_save_party_score", 0);
    	} else {
    		iRound = 1;
    		iScore = 0;
    	}
    	
        iIndexNextColor = 0;
        generateSequence(iRound+1);  
        setAllColorsClickable(false);
        iTimeBlink = 500;
        iTimeBetween = 1500;
        iMaxRound =  prefs.getInt("simon_max_rounds", 10);
        setRound();
        setScore();
        
		SharedPreferences.Editor editor = prefs.edit();
		editor.putBoolean("simon_save_game", false);
		editor.commit();
    }
    
    private void saveSimonGame()
    {
    	prefs = getSharedPreferences("Preferences",this.MODE_PRIVATE);
    	SharedPreferences.Editor editor = prefs.edit();
    	editor.putBoolean("simon_save_game", true);
    	editor.putInt("simon_save_party_round", iRound);
    	editor.putInt("simon_save_party_score", iScore);
    	editor.commit();
    }
    
    public Vector<eSimonColor> generateSequence(Integer iLenghtSequence)
    {	
    	vSequence = new Vector<eSimonColor>(iLenghtSequence);
    	Random generator = new Random();
    	for(Integer i = 0; i < iLenghtSequence; ++i)
    	{
    		eSimonColor eColor = convertEnumColor(generator.nextInt(numberColors));
    		vSequence.add(eColor);
    	}
		return vSequence;
    }
    
    private void increaseLevel()
    {
    	Random generator = new Random();
    	vSequence.addElement(convertEnumColor(generator.nextInt(numberColors)));
    }
    
    
    private eSimonColor convertEnumColor (Integer iColor) 
    {
    	eSimonColor eColor = eSimonColor.Green;
    	switch (iColor)
    	{
    	case 0 :
    		eColor = eSimonColor.Green;
    		break;
    	case 1 :
    		eColor = eSimonColor.Red;
    		break;
    	case 2 :
    		eColor = eSimonColor.Blue;
    		break;
    	case 3 :
    		eColor = eSimonColor.Yellow;
    		break;
    	
    	}
		return eColor;
    }

    
    private Integer iCodificationColor(eSimonColor eColor, boolean shine)
    {
    	Integer iCodificationColor = 0;
    	iCodificationColor = Color.rgb(255,255,255);
    	switch (eColor)
    	{
    	case Green :
    		if(shine) iCodificationColor = Color.rgb(0, 0, 0);
    		else iCodificationColor = Color.rgb(0,255,0);
    		break;
    	case Red : 
    		if(shine) iCodificationColor = Color.rgb(0, 0, 0);
			else iCodificationColor = Color.rgb(255, 0, 0);
    		break;
    	case Blue :
    		if(shine) iCodificationColor = Color.rgb(0, 0, 0);
    		else iCodificationColor = Color.rgb(0,0, 255);
    		break;
    	case Yellow :
    		if(shine) Color.rgb(0, 0, 0);
    		else iCodificationColor =  Color.rgb(255,255,51);
    		break;
    	
    	}
		return iCodificationColor;
    }
    
    
    
    private Integer getIDColor (eSimonColor eColor) 
    {
    	Integer iIDColor = 0;
    	switch (eColor)
    	{
    	case Green :
    		iIDColor = R.id.SimonGreen;
    		break;
    	case Red :
    		iIDColor = R.id.SimonRed;
    		break;
    	case Blue :
    		iIDColor = R.id.SimonBlue;
    		break;
    	case Yellow :
    		iIDColor = R.id.SimonYellow;
    		break;
    	
    	}
		return iIDColor;
    }
    
    private eSimonColor eColor(Integer iIDColor)
    {
    	eSimonColor color = eSimonColor.Red;
    	switch (iIDColor)
    	{
    	case R.id.SimonGreen :
    		color = eSimonColor.Green;
    		break;
    	case R.id.SimonRed :
    		color = eSimonColor.Red;
    		break;
    	case R.id.SimonBlue :
    		color = eSimonColor.Blue;
    		break;
    	case R.id.SimonYellow :
    		color = eSimonColor.Yellow;
    		break;
    	
    	}
		return color;
    }
    
    private void setRound()
    {
    	String sAux = " " + iRound;
    	sAux += " of ";
		sAux +=  "" + iMaxRound;
    	TextView textView1=(TextView)findViewById(R.id.SimonRound);
    	textView1.setText(sAux);
    }
    
    private void setScore()
    {
    	String sAux = " " + iScore;
    	TextView textView1=(TextView)findViewById(R.id.SimonScore);
    	textView1.setText(sAux);	
    }
    
    
    private void setAllColorsClickable(boolean bClickable)
    {
    	TextView textView1=(TextView)findViewById(R.id.SimonGreen);
    	textView1.setClickable(bClickable);
    	textView1=(TextView)findViewById(R.id.SimonRed);
    	textView1.setClickable(bClickable);
    	textView1=(TextView)findViewById(R.id.SimonBlue);
    	textView1.setClickable(bClickable);
    	textView1=(TextView)findViewById(R.id.SimonYellow);
    	textView1.setClickable(bClickable);
    }

    
    private Handler handler = new Handler() {
    	  @Override
    	  public void handleMessage(Message msg) {
    		  iActualarIDColor = (Integer) msg.obj; //getIDColor(vSequence.get((Integer) msg.obj));
    		  TextView textView=(TextView)findViewById(iActualarIDColor);//getIDColor(vSequence.get((Integer)msg.obj)));
			  Integer iCodificationColor = iCodificationColor(eColor(iActualarIDColor),true);
			  textView.setBackgroundColor(iCodificationColor); 
    		  
    	  }
    	 };

    public void startSimon(View v) {
		
		Thread th1 = new Thread(new Runnable() {
			public void run() {

				for(iIndexSequence = 0; iIndexSequence < vSequence.size(); ++iIndexSequence) {
					
					Message msg = new Message();
					msg.obj = getIDColor(vSequence.get((Integer) iIndexSequence));
					handler.sendMessage(msg);
					//Thread.sleep(500);
			        handler.postDelayed(new Runnable() { 
			             public void run() { 
			   			  TextView textView=(TextView)findViewById(iActualarIDColor);
						  Integer iCodificationColor = iCodificationColor(eColor(iActualarIDColor),false);
						  textView.setBackgroundColor(iCodificationColor);
						  if(iIndexSequence.equals(vSequence.size()-1)) {
							  bPlayerTurn = true;
							  setAllColorsClickable(true);
						  }
						  
			             } 
			        }, iTimeBlink);
		
					try {
						Thread.sleep(iTimeBetween);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
	    	    }        
		       }
		      });

		th1.start();

    }
    
    
    private void blinkColorTouch()
    {
		Thread th1 = new Thread(new Runnable() {
			public void run() {

				Message msg = new Message();
				msg.obj = (Integer)iIDColorPressed;
				handler.sendMessage(msg);
				//Thread.sleep(500);
		        handler.postDelayed(new Runnable() { 
		             public void run() { 
		   			  TextView textView=(TextView)findViewById(iActualarIDColor);
					  Integer iCodificationColor = iCodificationColor(eColor(iActualarIDColor),false);
					  textView.setBackgroundColor(iCodificationColor);
					  if(iIndexSequence.equals(vSequence.size()-1)) {
						  bPlayerTurn = true;
						  setAllColorsClickable(true);
					  }
					  
		             } 
		        }, iTimeBlink);
	
				try {
					Thread.sleep(iTimeBetween);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
    	    }        
	       
	      });

		th1.start();
    	
    }
    
    public void colorClicked(View v) {

    	if (!bPlayerTurn) return;
    	iIDColorPressed = v.getId();
 		blinkColorTouch();
 	   
    	if(vSequence.get(iIndexNextColor) == eColor(iIDColorPressed)) {
    		//Hit
    		iIndexNextColor++;
    		if(iIndexNextColor.equals(vSequence.size()))
    		{
    			increaseLevel();
    			bPlayerTurn = false;
    			iIndexNextColor = 0;
    	        ++iRound;
    	        iScore +=iRound;
	        	setScore();
    	        if(iRound <=iMaxRound)
    	        {
    	        	setRound();
    	        } else {
    	        	//Display You Win
    	        	 endGameAlert(v, "Congratulations\nYou have completed all rounds", "You Score is : " + iScore);
    	        }
    		}	
    	} else {
    		//Fail
    		bPlayerTurn = false;
    		iIndexNextColor = 0;
    		endGameAlert(v, "You lose\nYou haven't completed all rounds\n","You Score is : " + iScore);
    	} 	
    }

    

    public void endGameAlert(View view, String sTitle, String sScore) {

    	
		// get prompts.xml view
		LayoutInflater li = LayoutInflater.from(this);
		View alertEndGameView = li.inflate(R.layout.alert_end_game, null);
		

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				this);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(alertEndGameView);


		 TextView textView = (TextView) alertEndGameView
				.findViewById(R.id.end_game_textView_title);
		 textView.setText(sTitle);
		
		 textView = (TextView) alertEndGameView
				.findViewById(R.id.end_game_textView_score);
		 textView.setText(sScore);
		 
		 final CheckBox checkBox = (CheckBox) alertEndGameView
					.findViewById(R.id.end_game_checkBox);

	 	final EditText userInput = (EditText) alertEndGameView
				.findViewById(R.id.end_game_nick_ranking);
    	final Intent rankingActivity = new Intent(this, Ranking.class);
	 	final Ranking_DataController ranking_DataController = new Ranking_DataController(this);

	 	final Integer score = iScore;
	 	final Integer round = iRound;


	 	final Calendar c = Calendar.getInstance();
 	    final String date =  c.get(Calendar.DAY_OF_MONTH)+"-"+ c.get(Calendar.MONTH)+"-"+ c.get(Calendar.YEAR)%100;
	 	    
	 	// set dialog message
		alertDialogBuilder
			.setCancelable(false)
			.setPositiveButton("Continue",
			  new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog,int id) {
				// get user input and set it to result
				// edit text
			    	if(checkBox.isChecked())
			    	{
			    		String sNick = userInput.getText().toString();
			    		if(sNick.equals("")) {
			    			sNick = "unknown";
			    		} 
			    		
			    		ranking_DataController.newtPlayerRankingSimon(sNick, date, round, score);
			    		startActivity(rankingActivity);
			    	}

			    }
			  })
			.setNegativeButton("Try Again",
			  new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog,int id) {
			    	
			    	if(checkBox.isChecked())
			    	{
			    		//Save Value in Ranking
			    	}
			    	restartGame();
			    	dialog.cancel();
			    }
			  });

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
    	
    	/*LayoutInflater li = LayoutInflater.from(this);
		View promptsView = li.inflate(R.layout.prompts, null);
    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage(sText)
    	.setCancelable(false)
    	.setView(view)
    	.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
    	public void onClick(DialogInterface dialog, int id) {
    	
    	}
    	})
    	.setNegativeButton("Try again", new DialogInterface.OnClickListener() {
    	public void onClick(DialogInterface dialog, int id) {
    		initConfig();
    	}
    	});
    	AlertDialog alert = builder.create();
    	//final EditText input = new EditText(this);
    	
    	
    	//CheckBox c = new CheckBox(this);
    		
    	
    	//alert.setContentView(R.layout.alert_view);
    	//alert.setView(input);
    	//alert.setView(c);
    	alert.show();*/

    }
    

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
	ContextMenuInfo menuInfo)
	{
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.game_menu, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.game_menu_return:
				//lblMensaje.setText("Etiqueta: Opcion 1 pulsada!");
				return true;
			case R.id.game_menu_restart:
				restartGameAlert();
				return true;
			case R.id.game_menu_help:
				saveSimonGame();
				//Intent helpActivity = new Intent(this, Help.class);
				//startActivity(helpActivity);
				return true;
			case R.id.game_menu_options:
				//Intent settingsActivity = new Intent(this, Settings.class);
				//startActivity(settingsActivity);
				return true;
			case R.id.game_menu_exit:
				exitGameAlert();
				
				return true;
			default:
			return super.onContextItemSelected(item);
		}
	}
	
	public void exitGameAlert() {
		final Intent mainMenuActivity = new Intent(this, MainMenu.class);
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage("Are you sure you want to exit?")
    	.setCancelable(false)
    	.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	    	public void onClick(DialogInterface dialog, int id) {
	    		restartGame();
	    		startActivity(mainMenuActivity);
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
    
    
	public void restartGameAlert() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage("Are you sure you want restart Game?")
    	.setCancelable(false)
    	.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	    	public void onClick(DialogInterface dialog, int id) {
	    		restartGame();
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
    
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	    	saveSimonGame();
	    	Intent playActivity = new Intent(this, Play.class);
	    	startActivity(playActivity);
	    	//moveTaskToBack(true);
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
	
	
}
