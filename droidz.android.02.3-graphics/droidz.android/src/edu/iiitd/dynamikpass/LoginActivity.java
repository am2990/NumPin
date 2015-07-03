package edu.iiitd.dynamikpass;


	

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class LoginActivity extends Activity {
    /** Called when the activity is first created. */
	
	private static final String TAG = RegistrationActivity.class.getSimpleName();
	private static final int MENU_SUBMIT = 0;
	private static final int MENU_VERIFY = 1;
	private static final int MENU_CONFIRM = 2;
	
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
        // requesting to turn the title OFF
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // making it full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // set our MainGamePanel as the View
       // setContentView(new MainGamePanel1(this)null,null);
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
    
    
}


  
