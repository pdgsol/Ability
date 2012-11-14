package com.example.ability;

import java.util.Random;
import java.util.Vector;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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
    private Integer iInitialLevel;
    private Vector<eSimonColor> vSequence;
    private Integer iActualarIDColor;
    private Integer iIndexNextColor;
    private Integer iIndexSequence;
    private boolean bPlayerTurn = false;
    private Integer iTimeBlink;
    private Integer iTimeBetween;
    private Integer iIncrementScore = 10;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_simon);
        initConfig();    
    }
    
    private void initConfig()
    {
        iInitialLevel = 2;
        iIndexNextColor = 0;
        generateSequence((iInitialLevel));  
        setAllColorsClickable(false);
        iRound = 1;
        iScore = 0;
        iTimeBlink = 500;
        iTimeBetween = 1500;
        iMaxRound = 1;
        setRound();
        setScore();
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
    	        iScore +=iIncrementScore;
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
		    		//Save Value in Ranking
		    		//Open_Alert_Ranking

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
			    	initConfig();
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
    

    
}
