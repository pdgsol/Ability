package com.example.ability;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class Utils extends Activity {
	
    public void openView(Context contextPackge, Class classDestination) {
        // Do something in response to button
    	Intent intent = new Intent(contextPackge, classDestination);
        startActivity(intent);
    }

}
