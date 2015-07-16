/**
 * 
 */
package net.obviam.droidz;

import net.obviam.droidz.model.Droid;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.view.View.OnTouchListener;

/**
 * @author impaler
 * This is the main surface that handles the ontouch events and draws
 * the image to the screen.
 */
public class MainGamePanel extends SurfaceView implements 
		SurfaceHolder.Callback {

	
	
	private static final String TAG = MainGamePanel.class.getSimpleName();
	private GestureDetectorCompat mDetector; 
	static MainThread thread;
	static Droid droid;

	static Droid droid2;
	ActionMode mActionMode;
	static Droid droid3;
	private SurfaceView surfaceView;
	static Droid droid4;
	static Droid tri_b,tri_g,tri_y,tri_r;
	private Bitmap mBackgroundImage;
	static int wx,wy;
	  private int mCanvasHeight = 1;
	  private Context mContext;
	  static Droid sr;
	  static Droid sg;
	  static Droid sy;
	  static Droid sb;
	  static boolean touch;

      /**
       * Current width of the surface/canvas.
       *
       * @see #setSurfaceSize
       */
      private int mCanvasWidth = 1;
      private Callback mActionModeCallback;
      

	public MainGamePanel(Context context, Callback mActionModeCallback) {
		super(context);
		mContext = context;
		this.mActionModeCallback = mActionModeCallback;
		// adding the callback (this) to the surface holder to intercept events
		getHolder().addCallback(this);

		// create droid and load bitmap
//		
		
		droid = new Droid(BitmapFactory.decodeResource(getResources(), R.drawable.droid_1),R.drawable.droid_1, 50, 50,"dblue", getResources());
		tri_b = new Droid(BitmapFactory.decodeResource(getResources(), R.drawable.triangle_blue),R.drawable.triangle_blue, 100, 100,"tblue", getResources());
//		
		new BitmapFactory();
		mBackgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.surreal);
		
		// create the game loop thread
		thread = new MainThread(getHolder(), this, mContext);
		mContext=context;
		mDetector = new GestureDetectorCompat(mContext, new MyGestureListener());
		surfaceView = this;
		mDetector.setIsLongpressEnabled(true);
		// make the GamePanel focusable so it can handle events
		
		surfaceView.setOnTouchListener(new OnTouchListener() {
	        @Override
	        public boolean onTouch(View v, MotionEvent event) {
	        	if (event.getAction() == MotionEvent.ACTION_DOWN) {
	    			// delegating event handling to the droid
	    			droid.handleActionDown((int)event.getX(), (int)event.getY());
	    			tri_b.handleActionDown((int)event.getX(), (int)event.getY());
//	    			
	    			// check if in the lower part of the screen we exit
	    			if (event.getY() > getHeight() - 50) {
	    				thread.setRunning(false);
	    				((Activity)getContext()).finish();
	    			} else {
	    				Log.d(TAG, "Coords: x=" + event.getX() + ",y=" + event.getY());
	    			}
	    		} if (event.getAction() == MotionEvent.ACTION_MOVE) {
	    			// the gestures
	    			if (droid.isTouched()) {
	    				// the droid was picked up and is being dragged
	    				droid.setX((int)event.getX());
	    				
	    				droid.setY((int)event.getY());
	    			}
	    			if(tri_b.isTouched()){
	    				tri_b.setX((int)event.getX());
	    				
	    				tri_b.setY((int)event.getY());
	    			}
//	    			/*if (droid2.isTouched()) {
//	    				// the droid was picked up and is being dragged
//	    				droid2.setX((int)event.getX());
//	    				droid2.setY((int)event.getY());
//	    			}
	    		/*	if (droid3.isTouched()) {
	    				// the droid was picked up and is being dragged
	    				droid3.setX((int)event.getX());
	    				droid3.setY((int)event.getY());
	    			}
	    			if (droid4.isTouched()) {
	    				// the droid was picked up and is being dragged
	    				droid4.setX((int)event.getX());
	    				droid4.setY((int)event.getY());
	    			}*/
	    		} if (event.getAction() == MotionEvent.ACTION_UP) {
	    			// touch was released
	    			if (droid.isTouched()) {
	    				droid.setTouched(false);
	    			}
	    			if (tri_b.isTouched()) {
	    				tri_b.setTouched(false);
	    			}
//	    			if (droid2.isTouched()) {
//	    				droid2.setTouched(false);
//	    			}
	    			/*if (droid3.isTouched()) {
	    				droid3.setTouched(false);
	    			}
	    			if (droid4.isTouched()) {
	    				droid4.setTouched(false);
	    			}*/
	    		}
	    		
	            mDetector.onTouchEvent(event);
	            return true;
	        }

			
	    });
		setFocusable(true);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}
	  public void setSurfaceSize() {
          // synchronized to make sure these all change atomically
          synchronized (getHolder()) {
              mCanvasWidth = getWidth();
              
              mCanvasHeight = getHeight();

              mBackgroundImage = Bitmap.createScaledBitmap(
                      mBackgroundImage, getWidth(), getHeight(), true);
          }
      }

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// at this point the surface is created and
		// we can safely start the game loop
		thread.setRunning(true);
		thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d(TAG, "Surface is being destroyed");
		// tell the thread to shut down and wait for it to finish
		// this is a clean shutdown
		boolean retry = true;
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
				// try again shutting down the thread
			}
		}
		Log.d(TAG, "Thread was shut down cleanly");
	}
	
	
/*	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			// delegating event handling to the droid
			droid.handleActionDown((int)event.getX(), (int)event.getY());
			droid2.handleActionDown((int)event.getX(), (int)event.getY());
			droid3.handleActionDown((int)event.getX(), (int)event.getY());
			droid4.handleActionDown((int)event.getX(), (int)event.getY());
			// check if in the lower part of the screen we exit
			if (event.getY() > getHeight() - 50) {
				thread.setRunning(false);
				((Activity)getContext()).finish();
			} else {
				Log.d(TAG, "Coords: x=" + event.getX() + ",y=" + event.getY());
			}
		} if (event.getAction() == MotionEvent.ACTION_MOVE) {
			// the gestures
			if (droid.isTouched()) {
				// the droid was picked up and is being dragged
				droid.setX((int)event.getX());
				
				droid.setY((int)event.getY());
			}
			if (droid2.isTouched()) {
				// the droid was picked up and is being dragged
				droid2.setX((int)event.getX());
				droid2.setY((int)event.getY());
			}
			if (droid3.isTouched()) {
				// the droid was picked up and is being dragged
				droid3.setX((int)event.getX());
				droid3.setY((int)event.getY());
			}
			if (droid4.isTouched()) {
				// the droid was picked up and is being dragged
				droid4.setX((int)event.getX());
				droid4.setY((int)event.getY());
			}
		} if (event.getAction() == MotionEvent.ACTION_UP) {
			// touch was released
			if (droid.isTouched()) {
				droid.setTouched(false);
			}
			if (droid2.isTouched()) {
				droid2.setTouched(false);
			}
			if (droid3.isTouched()) {
				droid3.setTouched(false);
			}
			if (droid4.isTouched()) {
				droid4.setTouched(false);
			}
		}
		
		return true;
	}*/

	@Override
	protected void onDraw(Canvas canvas) {
		
		
		canvas.drawBitmap(mBackgroundImage, 0,0, null);
		
		droid.draw(canvas);
		tri_b.draw(canvas);
//		droid2.draw(canvas);
//		droid3.draw(canvas);
//		droid4.draw(canvas);
	}

//	@Override
//	public boolean onDown(MotionEvent arg0) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
//			float arg3) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//	
//	@Override
//	public void onLongPress(MotionEvent arg0) {
//		
//		// TODO Auto-generated method stub
//		
//		
//		if (mActionMode != null) {
//			Log.v(TAG, "mActionMode is not null");
//            return;
//        }
//
//        // Start the CAB using the ActionMode.Callback defined above
//        mActionMode = ((Activity)mContext).startActionMode(mActionModeCallback);
//        surfaceView.setSelected(true);
//        System.out.println("on Long Press");
//        return;
//    	}
        
    
//
//	@Override
//	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
//			float arg3) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public void onShowPress(MotionEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public boolean onSingleTapUp(MotionEvent arg0) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//	
	class MyGestureListener extends SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures"; 
        
        public boolean onTouchEvent(MotionEvent event) { 
            Log.d(DEBUG_TAG,"onTe: "); 
            return true;
        }
        public void onLongPress(MotionEvent arg0) {
    		// TODO Auto-generated method stub
    		
        	 System.out.println("on Long Press");
 			Log.d("hello","onLP: ");
        	
    		if (mActionMode != null) {
    			Log.v(TAG, "mActionMode is not null");
//                return;
            }
    		
            // Start the CAB using the ActionMode.Callback defined above
            mActionMode = ((Activity)mContext).startActionMode(mActionModeCallback);
            surfaceView.setSelected(true);
            System.out.println("on Long Press");
            return;
            
    		
    			
    		
    	}
        
	}

	

	public static Droid SelectRed() {
		// TODO Auto-generated method stub
			
			if(droid.isLongTouched()){
				
				droid.setColor("dred");
				
				droid.setLongPressed(false);
				sr= droid;
				
				//droid.setTouched(true);
			}
			if(tri_b.isLongTouched()){
				
				tri_b.setColor("tred");
				tri_b.setLongPressed(false);
				sr = tri_b;
			}
			
			return sr;
	}
	public static Droid SelectBlue() {
		// TODO Auto-generated method stub
		
		if(droid.isLongTouched()){
			droid.setColor("dblue");
			droid.setLongPressed(false);
			sb = droid;
			//droid.setTouched(true);
		}
		if(tri_b.isLongTouched()){
			
			tri_b.setColor("tblue");
			tri_b.setLongPressed(false);
			sb=tri_b;
		}
			
		return sb;		
	}
	public static Droid SelectGreen() {
		// TODO Auto-generated method stub
		
		if(droid.isLongTouched()){
			droid.setColor("dgreen");
			droid.setLongPressed(false);
			sg = droid;
			//droid.setTouched(true);
		}
		if(tri_b.isLongTouched()){
			
			tri_b.setColor("tgreen");
			tri_b.setLongPressed(false);
			sg=tri_b;
		}
			
		return sg;		
	}
	public static Droid SelectYellow() {
		// TODO Auto-generated method stub
		
		if(droid.isLongTouched()){
			droid.setColor("dyellow");
			droid.setLongPressed(false);
			sy = droid;
			//droid.setTouched(true);
		}
		if(tri_b.isLongTouched()){
			
			tri_b.setColor("tyellow");
			tri_b.setLongPressed(false);
			sy =tri_b;
		}
			
		return sy;		
	}
	

}

