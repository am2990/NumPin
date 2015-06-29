package net.obviam.droidz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class DroidzActivity extends Activity {
    /** Called when the activity is first created. */
	
	private static final String TAG = DroidzActivity.class.getSimpleName();
	private static final int MENU_SUBMIT = 0;
	private static final int MENU_VERIFY = 1;
	private static final int MENU_CONFIRM = 2;
	
	public ActionMode mActionMode;
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0, MENU_SUBMIT, 0, R.string.menu_submit);
        menu.add(0, MENU_VERIFY, 0, R.string.menu_verify);
        menu.add(0, MENU_CONFIRM, 0, R.string.menu_confirm);
       

        return true;
    }

	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		MainThread.setRunning(false);
        switch (item.getItemId()) {
            case MENU_SUBMIT:
            	System.out.println("do submit");
                //doSubmit();
            	
            	MainGamePanel.thread.doSubmit();
                return true;
                
            case MENU_VERIFY:
                MainGamePanel.thread.doVerify();
                return true;
            case MENU_CONFIRM:
                MainGamePanel.thread.doConfirm();
                return true;
            
            	//System.out.println("hello thread");
            
        }

        return false;
    }
  

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // requesting to turn the title OFF
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // making it full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // set our MainGamePanel as the View
        setContentView(new MainGamePanel(this, mActionModeCallback));
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
	
	private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

	    // Called when the action mode is created; startActionMode() was called
	    @Override
	    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
	        // Inflate a menu resource providing context menu items
	        MenuInflater inflater = mode.getMenuInflater();
	        inflater.inflate(R.menu.main, menu);
	        
	        return true;
	    }
	

	    // Called each time the action mode is shown. Always called after onCreateActionMode, but
	    // may be called multiple times if the mode is invalidated.
	    @Override
	    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
	        return false; // Return false if nothing is done
	    }

	    // Called when the user selects a contextual menu item
	    @Override
	   
	    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
	    	
	        switch (item.getItemId()) {
	           
	        case R.id.blue:
                Toast.makeText(getBaseContext(), "Selected Blue ", Toast.LENGTH_LONG).show();
               MainGamePanel.SelectBlue();
               mode.finish();
                return true;
	        case R.id.red:
                Toast.makeText(getBaseContext(), "Selected Red ", Toast.LENGTH_LONG).show();
                MainGamePanel.SelectRed();
                mode.finish();
                return true;
            case R.id.green:
                Toast.makeText(getBaseContext(), "Selected Green ", Toast.LENGTH_LONG).show();
               MainGamePanel.SelectGreen();
               mode.finish();
                return true;
                
            case R.id.yellow:
                Toast.makeText(getBaseContext(), "Selected Yellow ", Toast.LENGTH_LONG).show();
                MainGamePanel.SelectYellow();
                mode.finish();
                return true;
             
                default:
                	return false;
	        }
	    
			
	   }
	  
		

	   

	// Called when the user exits the action mode
	   @Override
	    public void onDestroyActionMode(ActionMode mode) {
	        mActionMode = null;
	    }
	};
	
    
}