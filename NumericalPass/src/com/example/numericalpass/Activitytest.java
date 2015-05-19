package com.example.numericalpass;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.ValidationResult;






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


public class Activitytest extends Activity{

	
	EditText edittext;
	Button bdone;
	String str,evaluate;
	double result1;
	boolean checkisvalid = false;
	//tr;
	int num;
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        bdone=(Button) findViewById(R.id.bdone);
        edittext = (EditText) findViewById(R.id.edittext);
      
       
	   
	
	bdone.setOnClickListener(new View.OnClickListener() {
		@ Override
        public void onClick(View v) {
			
			
			str = edittext.getText().toString();
			int len = str.length();            //length of string (formulae)
	    	    
	     	   Toast.makeText(getApplicationContext(),str, Toast.LENGTH_SHORT).show();
	     	  int i = 0;
	     	  int j = 0;
	     	  char[] arraystring= new char[len];   //array for the complete string
	     	  char[] arrayvariables= new char[len/2+1];
	     	 char[] temp= new char[len/2+1];
	     	 
	     	  for(i=0;i<=len-1;i++){
	      		 
	      		 arraystring[i] = str.charAt(i);
	      		 
	      		 System.out.println("hi from i");
	      		 System.out.println(i);
	      		 System.out.println(arraystring[i]);
	      		
	      		
	      		Random r = new Random();
	     		 int maximum = 57;
	     		 int minimum = 49;
	     		int range = maximum - minimum + 1;
	           	num = r.nextInt(range)+minimum;

	      			 System.out.println("inside loop");
	      			 
	      			if(arraystring[i]=='a' || arraystring[i]=='b' || arraystring[i]=='c' ||arraystring[i]=='d'||arraystring[i]=='e'||arraystring[i]=='f'||arraystring[i]=='g'||arraystring[i]=='h'||arraystring[i]=='i'||arraystring[i]=='j')
	      		 {	 
	      				
	      				  if(j>=0){
	      			
	      					 arrayvariables[j]=arraystring[i];
	      					temp[j]=arrayvariables[j];
	      					temp[j]=(char)(num);
	           		 System.out.println("after array");
	           		System.out.println(arrayvariables[j]);
	           		System.out.println(temp[j]);
	           		j++;
	      				  }
	           		
	           	}
	      			 	

	      		 if(arraystring[i]=='a' || arraystring[i]=='b' || arraystring[i]=='c' ||arraystring[i]=='d'||arraystring[i]=='e'||arraystring[i]=='f'||arraystring[i]=='g'||arraystring[i]=='h'||arraystring[i]=='i'||arraystring[i]=='j'){
	      		            //	arrayvariables[i]=(char)(num);
	            	arraystring[i]=(char)(num);
	            	
	            	System.out.println(num);
	            	System.out.println(arraystring[i]);
	      		 }
	      		 else
	      			continue;
	      		 
	      		try{
	           	  evaluate = new String(arraystring); 
	           	 Expression calc = new ExpressionBuilder(evaluate).build();
	           	Log.d("MainActivity", Arrays.toString(arraystring));
	              Log.d("MainActivity", evaluate);
	              Log.d("Mainhere", Arrays.toString(arrayvariables));
	           	 result1=calc.evaluate();//string to evaluate
	           	 System.out.println(result1);
	           	// String strans = result1.toString();
	           	 checkisvalid = true;
	           	  }
	           	  
	           	  catch(Exception e){
	           		  
	           		 Toast.makeText(getApplicationContext(),"invalid string", Toast.LENGTH_SHORT).show(); 
	           		 checkisvalid = false;
	           	  } 
	           	 if(checkisvalid == true){
	      	          Intent setintent = new Intent();
	      	      	  setintent.setClass(Activitytest.this, RegistrationActivity.class);
	      	      	 setintent.putExtra("c",str);
	      	      	 setintent.putExtra("something", arrayvariables);
	      	      	setintent.putExtra("values", temp);
	      	      	setintent.putExtra("result", result1);
	      	      	// Toast.makeText(getApplicationContext(),result1, Toast.LENGTH_SHORT).show();
	      	                       startActivity(setintent);
	      	     		 }
	      		 
	      
	           
	           }
		

        }
		
    });

	}	

}
	
