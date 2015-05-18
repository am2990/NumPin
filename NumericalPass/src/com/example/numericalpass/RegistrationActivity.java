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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class RegistrationActivity extends Activity{

	String s,str;
	Double ans;
	String temp ="";
	Intent i,ianswer;
	TextView tv,tv1,tv2;
	EditText et1;
	Button button1;
	//String arr[];
	int j=0;
	int k=0;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testform);
        
        i=getIntent();
        //ip = getIntent();
   	 s = i.getStringExtra("c");
   	 ianswer=getIntent();
	 ans= ianswer.getDoubleExtra("result", 0.0);
	System.out.println(ans);
	et1=(EditText)findViewById(R.id.et1);
	button1=(Button)findViewById(R.id.button1);
   	 //str = ip.getStringExtra("p");
   	Bundle extras = getIntent().getExtras();
    char[] myArr= extras.getCharArray("something");
    char[] myArr1= extras.getCharArray("values");
    Log.d("RegistrationActivity", "Received Array from intent"+ Arrays.toString(myArr));
   	tv=(TextView)findViewById(R.id.tv);
   	tv.setText("Test your Formulae:  " + s);
   	tv1=(TextView)findViewById(R.id.tv1);
   	tv1.setText("Variables  " + Arrays.toString(myArr));
   	for(int item:myArr){
        Log.i("SecondActivity", String.valueOf(item));
   	} 
        tv2=(TextView)findViewById(R.id.tv2);
       	tv2.setText("Values  " + Arrays.toString(myArr1));
       	for(int item1:myArr1){
            Log.i("SecondActivity1", String.valueOf(item1));
       	}
   	//tv1.setText("Check if you get the formulae right" + Arrays.toString(myArr = myArr1));
       
	   
	
	button1.setOnClickListener(new View.OnClickListener() {
		@ Override
        public void onClick(View v) {
			
			
			if(et1.getText().toString().equals(ans.toString())){
				
				Toast.makeText(getApplicationContext(),"You are right", Toast.LENGTH_SHORT).show();
				
			}
			else
				Toast.makeText(getApplicationContext(),"Let's try again!!", Toast.LENGTH_SHORT).show();
		

        }
		
    });

	}	

}
	
