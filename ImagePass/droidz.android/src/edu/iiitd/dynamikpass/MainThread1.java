/* ** Thread for Login Activity** */
/**
 * 
 */
package edu.iiitd.dynamikpass;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;


/**
 * @author impaler
 *
 * The Main thread which contains the game loop. The thread must have access to 
 * the surface view and holder to trigger events every game tick.
 */
public class MainThread1 extends Thread {
	
	private static final String TAG = MainThread.class.getSimpleName();

	// Surface holder that can access the physical surface
	private SurfaceHolder surfaceHolder;
	// The actual view that handles inputs
	// and draws to the surface
	private LoginPanel gamePanel;
	// Context context=MainGamePanel.getContext();
	double x,y;
	//private Droid droid;
	

	// flag to hold game state 
	private boolean running;

	//private Object mContext;
	public void setRunning(boolean running) {
		this.running = running;
	}

	public MainThread1(SurfaceHolder surfaceHolder, LoginPanel gamePanel,Context mContext) {
		super();
		this.surfaceHolder = surfaceHolder;
		this.gamePanel = gamePanel;
	}

	@Override
	public void run() {
		Canvas canvas;
		Log.d(TAG, "Starting game loop");
		while (running) {
			canvas = null;
			// try locking the canvas for exclusive pixel editing
			// in the surface
			try {
				canvas = this.surfaceHolder.lockCanvas();
				synchronized (surfaceHolder) {
					// update game state 
					// render state to the screen
					// draws the canvas on the panel
					this.gamePanel.onDraw(canvas);				
				}
			} finally {
				// in case of an exception the surface is not left in 
				// an inconsistent state
				if (canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}	// end finally
		}
	}

	public void doVerify() {
		// TODO Auto-generated method stub
		if(RegistrationPanel.image.getX()==x){
			System.out.println("right!!");
		}
		
	}

	public void doConfirm() {
		// TODO Auto-generated method stub
		
	}
	
}
