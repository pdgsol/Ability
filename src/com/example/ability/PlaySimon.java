package com.example.ability;

import java.util.Random;
import java.util.Vector;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class PlaySimon extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_simon);
    }
    
    
    public Vector<Integer> generateSequrnce(Integer iLenghtSequence)
    {
    	Integer numberColors = 4;
    	Vector<Integer> vSequence = new Vector<Integer>(iLenghtSequence);
    	Random generator = new Random();
    	for(Integer i = 0; i < iLenghtSequence; ++i)
    	{
    		vSequence.add(generator.nextInt(numberColors));
    	}
		return vSequence;
    }



	public void shineColor(View view) {
    
    	TextView textView=(TextView)findViewById(R.id.Blue);
    	textView.setBackgroundColor(Color.BLACK); 
    }

    
    

    
}
