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

	String s,s1,str,s9;
	Double ans;
	Integer ans1;
	String temp ="";
	Intent i,ianswer,ianswer1,ip;
	
	TextView tv,tv1;
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
   	 ip=getIntent();
     //ip = getIntent();
	 s9 = ip.getStringExtra("yo");
   	 ianswer=getIntent();
	 ans= ianswer.getDoubleExtra("result", 0.0);
	 
   	 ianswer1=getIntent();
	 ans1= ianswer1.getIntExtra("result2", 0);
	System.out.println(ans1);
	et1=(EditText)findViewById(R.id.et1);
	button1=(Button)findViewById(R.id.button1);
   	 //str = ip.getStringExtra("p");

	
   	Bundle extras = getIntent().getExtras();
    char[] myArr= extras.getCharArray("something");
    char[] myArr1= extras.getCharArray("values");
   // String[] myArr2= extras.getStringArray("yo");

    Log.d("RegistrationActivity", "Received Array from intent"+ Arrays.toString(myArr));
   	tv=(TextView)findViewById(R.id.tv);
   	tv.setText("Test your Formulae:  " + s);
   /*	tv1=(TextView)findViewById(R.id.tv1);
   	tv1.setText("Variables  " + Arrays.toString(myArr));*/
   
   	tv1=(TextView)findViewById(R.id.tv1);
   	tv1.setText("Values for variables  " + s9);
   
  
       
	   
	
	button1.setOnClickListener(new View.OnClickListener() {
		@ Override
        public void onClick(View v) {
			
			
			if(et1.getText().toString().equals(ans.toString()) || et1.getText().toString().equals(ans1.toString())) {
				
				Toast.makeText(getApplicationContext(),"You are right", Toast.LENGTH_SHORT).show();
				
			}
			else
				Toast.makeText(getApplicationContext(),"Let's try again!!", Toast.LENGTH_SHORT).show();
		

        }
		
    });

	}	

}
	
