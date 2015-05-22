package com.example.numericalpass;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ConfirmationActivity extends Activity {

	Intent i;
	String s;
	TextView textv;
	SQLiteDatabase db;

			public void onCreate(Bundle savedInstanceState) {
			    super.onCreate(savedInstanceState);
			    setContentView(R.layout.confirmationlayout);
			    
			    i=getIntent();
			    
				 s = i.getStringExtra("confirmstring");
				 textv = (TextView)findViewById(R.id.textv);
				 textv.setText("Final formula: "+s);
				 
				 db = openOrCreateDatabase("NumericalPass", Context.MODE_PRIVATE, null);
				 db.execSQL("CREATE TABLE IF NOT EXISTS NumPass(id INTEGER PRIMARY KEY,password VARCHAR);");
				 db.execSQL("UPDATE NumPass SET password='"+s+"'");
				 //Cursor c=db.rawQuery("SELECT * FROM login2 WHERE username = '"+username.getText().toString()+"'", null);

				 Cursor c = db.rawQuery("SELECT * FROM NumPass", null); //db.execSQL("SELECT * FROM NumPass");
				 
				 if(c != null){
					 Toast.makeText(getApplicationContext(),"Password stored for future use!!", Toast.LENGTH_SHORT).show();
				 }
				 
			}

}
