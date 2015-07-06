package edu.iiitd.dynamikpass;


	



import java.util.ArrayList;
import java.util.Iterator;

import edu.iiitd.dynamikpass.model.Image;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;

public class LoginActivity extends Activity {
    /** Called when the activity is first created. */
	
	private static final String TAG = RegistrationActivity.class.getSimpleName();
	public static Object ax;
	public static Object ay;
	
	
	
	
	
	/*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0, MENU_SUBMIT, 0, R.string.menu_submit);
        menu.add(0, MENU_VERIFY, 0, R.string.menu_verify);
        menu.add(0, MENU_CONFIRM, 0, R.string.menu_confirm);
       

        return true;
    }*/

	
	/*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
          //  case MENU_SUBMIT:
            //    MainGamePanel.thread.doSubmit();
                //return true;
                
            case MENU_VERIFY:
                MainGamePanel1.thread.doVerify();
                return true;
            case MENU_CONFIRM:
                MainGamePanel1.thread.doConfirm();
                return true;
            
            	//System.out.println("hello thread");
            
        }

        return false;
    }*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
      /* @SuppressWarnings("unchecked")
		ArrayList<Double> ax = (ArrayList<Double>) getIntent().getSerializableExtra("ax");
        @SuppressWarnings("unchecked")
		ArrayList<Double> ay = (ArrayList<Double>) getIntent().getSerializableExtra("ay");

        @SuppressWarnings("unchecked")
		ArrayList<Integer> bitmapid = (ArrayList<Integer>) getIntent().getSerializableExtra("bitmapid");*/
       
        // requesting to turn the title OFF
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // making it full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // set our MainGamePanel as the View
        setContentView(new LoginPanel(this));
        Log.d(TAG, "View added");
        
  	
    }
   
    

	@Override
	protected void onDestroy() {
		Log.d(TAG, "Destroying...");
		super.onDestroy();
	}

	@Override
	protected void onStop() {
		Log.d(TAG, "Stopping...");
		super.onStop();
	}
	@Override
	public void onBackPressed() {
	    moveTaskToBack(true);
	}
	
    
}
