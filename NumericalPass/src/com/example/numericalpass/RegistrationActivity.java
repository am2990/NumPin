package com.example.numericalpass;
import java.lang.reflect.Array;
import java.util.Arrays;

import android.R.array;
import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class RegistrationActivity extends Activity{

	String s,str;
	String temp ="";
	Intent i,ip;
	TextView tv,tv1;
	//String arr[];
	int j=0;
	int k=0;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testform);
        
        i=getIntent();
        //ip = getIntent();
   	 s = i.getStringExtra("c");
   	 //str = ip.getStringExtra("p");
   	Bundle extras = getIntent().getExtras();
    char[] myArr= extras.getCharArray("something");
    Log.d("RegistrationActivity", "Received Array from intent"+ Arrays.toString(myArr));
   	tv=(TextView)findViewById(R.id.tv);
   	tv.setText("Test your Formulae:  " + s);
   	tv1=(TextView)findViewById(R.id.tv1);
   	tv1.setText("Variables  " + Arrays.toString(myArr));
   	for(int item:myArr){
        Log.i("SecondActivity", String.valueOf(item));
       
     }

	}
}
