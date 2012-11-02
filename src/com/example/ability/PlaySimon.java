package com.example.ability;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

public class PlaySimon extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_simon);
    }

    public void shineColor(View view) {
    
       Resources res = getResources();
       TextView color=(TextView)findViewById(R.id.Blue);
       color.setBackgroundColor(0x008000); 
    }

    
    

    
}
