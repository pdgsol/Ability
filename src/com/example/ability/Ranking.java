package com.example.ability;





import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class Ranking extends Activity {

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
    }

    
}
