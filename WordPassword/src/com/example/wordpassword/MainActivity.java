package com.example.wordpassword;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.text.method.LinkMovementMethod;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.BufferType;


public class MainActivity extends Activity{

	
	EditText wordedit;
	Button bsubmit;
	String Wordphrase;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        bsubmit=(Button) findViewById(R.id.bsubmit);
        wordedit = (EditText) findViewById(R.id.et1);
     
        bsubmit.setOnClickListener(new View.OnClickListener() {
			@ Override
	        public void onClick(View v) {
				
				
				Wordphrase = wordedit.getText().toString();
				System.out.println("Word phrase: "+ Wordphrase);
				//String[] strword = Wordphrase.split("\\,");
				

				 String[] arr = Wordphrase.split(" ");    
				
				 ArrayList<Object> wordArrayList = new ArrayList<Object>(); 
				 // ArrayList<String> wordArrayList = new ArrayList<String>();
				 for ( String word : arr) {

				       System.out.println("hello from word "+word);
				       
				       wordArrayList.add(word); 
				       System.out.println("hello from array list "+wordArrayList);
				  }
				 
				/* Intent intent = new Intent(MainActivity.this, WordCloudActivity.class);
			        intent.putStringArrayListExtra("wordArrayList", wordArrayList);
			        startActivity(intent);*/
			        
			        
			        
			     // Object class does not implement Serializable interface

			     Bundle extra = new Bundle();
			     extra.putSerializable("wordArrayList", wordArrayList);

			     Intent intent = new Intent(getBaseContext(), SampleTagCloud.class);
			   //  Intent intent2 = new Intent(getBaseContext(), TagCloudView.class);
			     intent.putExtra("extra", extra);
			   // intent2.putExtra("extra", extra);
			     
			     startActivity(intent);
			    // startActivity(intent2);
				 
				/* Intent intent = new Intent(MainActivity.this, SampleTagCloud.class);
				 startActivity(intent);*/
				}	
				
			});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
   
	/*@Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        //user has long pressed your TextView
        menu.add(0, v.getId(), 0, "text that you want to show in the context menu - I use simply Copy");

        //cast the received View to TextView so that you can get its text
         EditText yourTextView = wordedit;

        //place your TextView's text in clipboard
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE); 
        clipboard.setPrimaryClip((ClipData) yourTextView.getText());
    }*/
}
