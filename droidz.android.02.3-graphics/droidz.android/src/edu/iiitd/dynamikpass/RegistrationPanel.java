/**
 * 
 */
package edu.iiitd.dynamikpass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import edu.iiitd.dynamikpass.model.Image;
import edu.iiitd.dynamikpass.utils.DatabaseHelper;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
public class RegistrationPanel extends SurfaceView implements 
		SurfaceHolder.Callback {

	
	
	private static final String TAG = RegistrationPanel.class.getSimpleName();
	private GestureDetectorCompat mDetector; 
	static MainThread thread;
	static Image image;
	static ArrayList<Image> imglist = new ArrayList<Image>();
	ArrayList<String> imageslp = new ArrayList<String>();
	static Image droid2;
	ActionMode mActionMode;
	static Image droid3;
	private SurfaceView surfaceView;
	static Image droid4;
	static Image tri_b,tri_g,tri_y,tri_r;
	private Bitmap mBackgroundImage;
	static int wx,wy;
	  private int mCanvasHeight = 1;
	  private Context mContext;
	  static Image sr;
	  static Image sg;
	  static Image sy;
	  static Image sb;
	  static boolean touch;

      /**
       * Current width of the surface/canvas.
       *
       * @see #setSurfaceSize
       */
      private int mCanvasWidth = 1;
      private Callback mActionModeCallback;
      

	public RegistrationPanel(Context context, Callback mActionModeCallback, int backgroundImage, ArrayList<String> images) {
		super(context);
		mContext = context;
		this.mActionModeCallback = mActionModeCallback;
		// adding the callback (this) to the surface holder to intercept events
		System.out.println("RP : "+images.get(0));
		getHolder().addCallback(this);

		// create droid and load bitmap
//		
		/*HashMap<String,Bitmap> bitmap1 = new HashMap();
		bitmap1.put("BLUE",BitmapFactory.decodeResource(getResources(), R.drawable.droid_1));
		bitmap1.put("YELLOW",BitmapFactory.decodeResource(getResources(), R.drawable.droid_2));
		bitmap1.put("GREEN",BitmapFactory.decodeResource(getResources(), R.drawable.droid_4));
		bitmap1.put("RED",BitmapFactory.decodeResource(getResources(), R.drawable.droid_3));
		
		HashMap<String,Bitmap> bitmap2 = new HashMap();
		bitmap2.put("BLUE",BitmapFactory.decodeResource(getResources(), R.drawable.triangle_blue));
		bitmap2.put("YELLOW",BitmapFactory.decodeResource(getResources(), R.drawable.triangle_yellow));
		bitmap2.put("GREEN",BitmapFactory.decodeResource(getResources(), R.drawable.triangle_green));
		bitmap2.put("RED",BitmapFactory.decodeResource(getResources(), R.drawable.triangle_red));
		//image = new Image(BitmapFactory.decodeResource(getResources(), R.drawable.droid_1),R.drawable.droid_1, 50, 50,"dblue", getResources());
		//image = new Image(("BLUE",BitmapFactory.decodeResource(getResources(), R.drawable.droid_1)),R.drawable.triangle_blue, 100, 100,"tblue", getResources()));
		image = new Image(bitmap1,R.drawable.droid_1,50,50,"BLUE",getResources());
		tri_b = new Image(bitmap2,R.drawable.triangle_blue, 100, 100,"BLUE", getResources());*/
		
		DatabaseHelper db = new DatabaseHelper(mContext);
		
		
		int image_id = 0;
		for(String s: images){
			Random ran = new Random();
			int ranx = ran.nextInt((300 - 25) + 1) + 25;
			int rany = ran.nextInt((300 - 25) + 1) + 25;

			image_id = Integer.parseInt(s);
			System.out.println("image_id: "+image_id);
			String w = "droid_1";
			HashMap<String,Bitmap> bitmap1 = new HashMap<String, Bitmap>();
			bitmap1.put("BLUE",BitmapFactory.decodeResource(getResources(),db.getBlueImage(image_id)));
			bitmap1.put("YELLOW",BitmapFactory.decodeResource(getResources(), db.getYellowImage(image_id)));
			bitmap1.put("GREEN",BitmapFactory.decodeResource(getResources(), db.getGreenImage(image_id)));
			bitmap1.put("RED",BitmapFactory.decodeResource(getResources(), db.getRedImage(image_id)));

			image = new Image(bitmap1,image_id,ranx,rany,"BLUE",getResources());
			imglist.add(image);

		}
		
		System.out.println("image list: "+imglist.size());
				new BitmapFactory();
		
		

		mBackgroundImage = BitmapFactory.decodeResource(getResources(), backgroundImage);

        
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
	        		for(Image img: imglist){
	        			img.handleActionDown((int)event.getX(), (int)event.getY());
	        		}
	    			//tri_b.handleActionDown((int)event.getX(), (int)event.getY());
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
	    		for(Image img: imglist){
	    			if (img.isTouched()) {
	    				// the droid was picked up and is being dragged
	    				
	    					img.setX((int)event.getX());
	    				
	    					img.setY((int)event.getY());
	    				}
	    			
	    		}
	    			/*if(tri_b.isTouched()){
	    				tri_b.setX((int)event.getX());
	    				
	    				tri_b.setY((int)event.getY());
	    			}*/
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
	    			
	    			for(Image img: imglist){
	    				
	    			
		    			if (img.isTouched()) {
		    				img.setTouched(false);
		    			}
	    			}
	    			/*if (tri_b.isTouched()) {
	    				tri_b.setTouched(false);
	    			}*/
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
		
		mBackgroundImage = Bitmap.createScaledBitmap(
                mBackgroundImage, getWidth(), getHeight(), true);
		
		canvas.drawBitmap(mBackgroundImage, 0,0, null);
		
		for(Image img : imglist){
			img.draw(canvas);
		}
		//tri_b.draw(canvas);
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
    		ArrayList<Image> imagelist = new ArrayList<Image>();
    		for(Image img: imglist){
    			imagelist.add(img);
    		
    		//imagelist.add(tri_b);
	    		Image droidz = null;
	    		//for( Image f : imagelist){
	   			// droidz = f.getRange(arg0.getX(), arg0.getY());
	   			 //System.out.println("droidz ST: "+ droidz);
	    		//}
	    		Image droidz1 = img.getRange(arg0.getX(), arg0.getY());
	    		//Image droidz2 = tri_b.getRange(arg0.getX(), arg0.getY());
	    		
	    	
	        	 System.out.println("on Long Press");
	 			Log.d("hello","onLP: ");
	        	
	    		if (mActionMode != null) {
	    			Log.v(TAG, "mActionMode is not null");
	//                return;
	            }
	    		
	            // Start the CAB using the ActionMode.Callback defined above
	    		if((droidz1 != null)){
		            mActionMode = ((Activity)mContext).startActionMode(mActionModeCallback);
		            surfaceView.setSelected(true);
		            System.out.println("on Long Press");
		            return;
	    		}
    		}
    		
    			
    		
    	}
        
	}

	

	public static Image SelectRed() {
		
		
		for(Image img: imglist){
			
			if(img.isLongTouched()){
				
				img.setColor("RED");
				
				img.setLongPressed(false);
				sr= img;
				return sr;
				//droid.setTouched(true);
			}
		}
			/*if(tri_b.isLongTouched()){
				
				tri_b.setColor("RED");
				tri_b.setLongPressed(false);
				sr = tri_b;
			}*/
			
			return null;
	}
	public static Image SelectBlue() {

		for(Image img: imglist){
		if(img.isLongTouched()){
			img.setColor("BLUE");
			img.setLongPressed(false);
			sb = img;
			//droid.setTouched(true);
			return sb;
		}
	}	
			
		return sb;		
	}
	public static Image SelectGreen() {
		// TODO Auto-generated method stub
		
		for(Image img: imglist){
			if(img.isLongTouched()){
				img.setColor("GREEN");
				img.setLongPressed(false);
				sg = img;
				//droid.setTouched(true);
				return sg;
			}
	}	
			
		return sg;		
	}
	public static Image SelectYellow() {
		// TODO Auto-generated method stub
		for(Image img: imglist){
		if(img.isLongTouched()){
			img.setColor("YELLOW");
			img.setLongPressed(false);
			sy = img;
			//droid.setTouched(true);
			return sy;
		}
	}
		return sy;		
	}
	
	
	
/*	public void onBackPressed() {
	   Log.d("CDA", "onBackPressed Called");
	   Intent setIntent = new Intent(Intent.ACTION_MAIN);
	   setIntent.addCategory(Intent.CATEGORY_HOME);
	   setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	   mContext.startActivity(setIntent);
	}*/
}

