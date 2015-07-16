/* **Table layout for selecting the gestures ** */
package edu.iiitd.dynamikpass;


import java.util.ArrayList;

import edu.iiitd.dynamikpass.utils.DatabaseHelper;
import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class TableLayoutExampleActivity extends Activity implements OnItemSelectedListener, OnClickListener {
    /** Called when the activity is first created. */
	
	private Spinner spinner1, spinner2,spinner3;
	private String item1,item2,item3;
	String g1,g2,g3;
	Button submit;
	int imageBack;
	ArrayList<Image> images = new ArrayList<Image>();
	DatabaseHelper db = new DatabaseHelper(this);
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.main_1);
    	Intent i = getIntent();
    	imageBack = i.getIntExtra("ib",0);


    	spinner1 = (Spinner) findViewById(R.id.spinner1);
    	spinner2 = (Spinner) findViewById(R.id.spinner2);
    	spinner3 = (Spinner) findViewById(R.id.spinner3);
    	submit = (Button) findViewById(R.id.submit);

    	ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
    			R.array.planets_array, android.R.layout.simple_spinner_item);
    	// Specify the layout to use when the list of choices appears
    	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	// Apply the adapter to the spinner
    	spinner1.setAdapter(adapter);
    	spinner2.setAdapter(adapter);
    	spinner3.setAdapter(adapter);
    	spinner1 = (Spinner) findViewById(R.id.spinner1);
    	spinner2 = (Spinner) findViewById(R.id.spinner2);
    	spinner3 = (Spinner) findViewById(R.id.spinner3);
    	spinner1.setOnItemSelectedListener(this);
    	spinner2.setOnItemSelectedListener(this);
    	spinner3.setOnItemSelectedListener(this);
    	submit.setOnClickListener((OnClickListener) this);
    	
    	spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    			item1 = parent.getItemAtPosition(position).toString();
    			System.out.println("object1: "+item1);
    			
    			db.saveMap(1,1,item1);
    		}
    		public void onNothingSelected(AdapterView<?> parent) {
    		}
    	});	
    	spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    	    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    	       item2 = parent.getItemAtPosition(position).toString();
    	        System.out.println("object2: "+item2);
    	        db.saveMap(1,0,item2);
    	        //g2=item2;
    	    }
    	    public void onNothingSelected(AdapterView<?> parent) {
    	    }
    	});	
    	spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    	    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    	        item3 = parent.getItemAtPosition(position).toString();
    	        System.out.println("object3: "+item3);
    	        db.saveMap(0,1,item3);
    	      
    	    }
    	    public void onNothingSelected(AdapterView<?> parent) {
    	    }
    	});	

    }
    
   
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {

		
	}
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
		intent.putExtra("ib", imageBack);
		 startActivity(intent); 
	}
  
	
}
