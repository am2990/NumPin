/**
 * 
 */
package edu.iiitd.dynamikpass;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import edu.iiitd.dynamikpass.model.Image;
import edu.iiitd.dynamikpass.utils.DatabaseHelper;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

/**
 * @author impaler
 * This is the main surface that handles the ontouch events and draws
 * the image to the screen.
 */
public class LoginPanel extends SurfaceView implements OnGestureListener,
OnDoubleTapListener, SurfaceHolder.Callback {

	
	private static final String TAG = LoginPanel.class.getSimpleName();
	
	static MainThread1 thread;
	//static Image image;
	boolean flag;
	boolean check;
	String getgest=null;
	int countdroidST=0;
	int countdroidDT=0;
	boolean colflag;
	ArrayList<Image> drawimg = new ArrayList<Image>();
	List<Image> ls = new ArrayList<Image>();
	List<String> gestures = null;
	//Droid droidz = null;
	Image f = null;
	int o=0;
	int l=0;

	private Image droid2;

	private Image droid3;

	private Image droid4;
	private Bitmap mBackgroundImage;
	static int wx,wy;
	int reach=0;
	ArrayList<Image> templist;
	ArrayList<Image> fling = new ArrayList<Image>();
	ArrayList<Image> singletap = new ArrayList<Image>();
	ArrayList<Image> doubletap = new ArrayList<Image>();
	Map<Image, Integer> hm = new HashMap<Image, Integer>();
	  private int mCanvasHeight = 1;
	  private Context mContext;
	  int fry;
	  Image tri_b,tri_g,tri_y,tri_r;
	
		
      /**
       * Current width of the surface/canvas.
       *
       * @see #setSurfaceSize
       */
      private int mCanvasWidth = 1;
boolean flag1,flag2,flag3,flag4;
     // private ArrayList<Droid> wrongDroid ;
     
	 //ArrayList<Droid> droidobj1;
	 ArrayList<Integer> bitmapid1;
	 //ArrayList<Bitmap> bitmap;

	 private GestureDetectorCompat mDetector; 
	 private SurfaceView surfaceView;
	
	public LoginPanel(Context context, int backgroundImage) {
		super(context);
		mContext = context;
		
		

		new BitmapFactory();
		mBackgroundImage = BitmapFactory.decodeResource(getResources(), backgroundImage);
		
		 
		DatabaseHelper db = new DatabaseHelper(mContext);
		ls = db.getAllDroids();
		
		
		gestures = db.getAllGestures();
		//System.out.println("ls: "+ ls.size());
		System.out.println("gestures: "+ gestures.size());
		
		
		
		
		// create droid and load bitmap
		
	
		for(Image i :ls){
			i = new Image(BitmapFactory.decodeResource(getResources(), i.getBitmapId()),i.getBitmapId(),i.getX(),i.getY(),i.getColor(),getResources());
			//i.setBitmap(BitmapFactory.decodeResource(getResources(), i.getBitmapId()));
			//int r = randomN(ls.size());
		//	System.out.println("r: "+r);
			int r = 2;
			switch(r){
				case 1:
				{
					System.out.println("case1:BOTH RIGHT");
					hm.put(i,1);
					break;
				}
				case 2:
				{
					System.out.println("case2:CHANGE COLOR");
					
					ChangeColor(i);
					hm.put(i,2);
					break;
				}
				case 3:
				{
					System.out.println("case3:CHANGE POSITION");
					
					ChangePosition(i);
					hm.put(i,3);
					break;
				}
				case 4:
				{
					System.out.println("case4:BOTH WRONG");
					
					ChangeColor(i);
					ChangePosition(i);
					hm.put(i,4);
					break;
				}
			
			}
			//i = new Image(BitmapFactory.decodeResource(getResources(), i.getBitmapId()),i.getBitmapId(),i.getX(),i.getY(),i.getColor(),getResources());
			//i.setBitmap(BitmapFactory.decodeResource(getResources(), i.getBitmapId()));
			
			//int vount =0;
			
			
		
			
		
		}
		Iterator iter = hm.keySet().iterator();
		 while (iter.hasNext()) {
				// for(int y=0;y<=3;y++)
				// vount++;
				 Image image = (Image) iter.next();
				 drawimg.add(image);
				 //i.draw(canvas);
				 
			 }
			
		//Iterator it = hm.keySet().iterator();
		 //while (it.hasNext()) {
		//for(Image i:ls){
			//String getgest=null;
			//Log.d(TAG, "Size: " + ls.size());
			//int j = (int) it.next();
			
		//Image i = (Image) it.next();
		
		for(Image i: drawimg){
		int j = hm.get(i);
			Log.d(TAG, "hm value"+ j);
			
			switch(j){
				case 1:{
					getgest = gestures.get(0);
					System.out.println("gestgest: "+getgest);
					substitutegesture(i);
					break;
				}
				case 2:{
					getgest = gestures.get(1);
					System.out.println("gestgest: "+getgest);
					substitutegesture(i);
					break;
				}
				case 3:{
					getgest = gestures.get(2);
					System.out.println("gestgest: "+getgest);
					substitutegesture(i);
					break;
				}
				case 4:{
					//getgest = gestures.get(2);
					break;
				}
			}
		}
		
		
		//image = new Image(BitmapFactory.decodeResource(getResources(), R.drawable.droid_1),R.drawable.droid_1, 50, 50,"blue", getResources());
		//tri_b = new Image(BitmapFactory.decodeResource(getResources(), R.drawable.triangle_blue),R.drawable.triangle_blue, 100, 100,"blue", getResources());
		
		
		
		//int count = ax.size();
	
	//int r = randomN(ax.size()-1);
	
		
			
		    
		    
		   
		    

		 
	
		
		// make the GamePanel focusable so it can handle events
		thread = new MainThread1(getHolder(), this, context);
		getHolder().addCallback(this);
		surfaceView = this;
		
		mContext=context;
		mDetector = new GestureDetectorCompat(mContext, new MyGestureListener());
		mDetector.setIsLongpressEnabled(true);
		mDetector.setOnDoubleTapListener(this);
		
		
		//mDetector.setOnDoubleTapListener(listener)
		
		
		surfaceView.setOnTouchListener(new OnTouchListener() {
	        @Override
	        public boolean onTouch(View v, MotionEvent event) {
	            mDetector.onTouchEvent(event);
	            return true;
	        }

			
	    });
		setFocusable(true);
		
		
	}

	private void substitutegesture(Image i) {
		// TODO Auto-generated method stub
		if(getgest == "Single Tap"){
			fling.add(i);
		}
		if(getgest == "Double Tap"){
			doubletap.add(i);
		}
		if(getgest == "Fling"){
			singletap.add(i);
		}
		
	}

	private void ChangePosition(Image img) {
		Random ran = new Random();
		//ran.setSeed((long)i);
		int randomNumx = ran.nextInt((350 - 25) + 1) + 25;
		int randomNumy = ran.nextInt((350 - 25) + 1) + 25;
		img.setX(randomNumx);
		img.setY(randomNumy);
		
	}
	private void ChangeColor(Image img) {
		// TODO Auto-generated method stub
		Random rand= new Random();
		
		//int col = (rand.nextInt((100-1)+1)%5+1);
		int col  = 2;
		Log.d(TAG, "Color" + col);
		switch(col){
		case 1:
		{
			img.setColor("BLUE");
			break;

		}
		case 2:
		{
			img.setColor("GREEN");
			//img.setBitmap(BitmapFactory.decodeResource(getResources(), img.getBitmapId()));
			break;

		}
		case 3:
		{
			img.setColor("YELLOW");
			break;

		}
		case 4:
		{
			img.setColor("RED");
			break;

		}

		}
		
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}
	  public void setSurfaceSize() {
          // synchronized to make sure these all change atomically
          synchronized (getHolder()) {
              mCanvasWidth = getWidth();
              
              mCanvasHeight = getHeight();

              // don't forget to resize the background image
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
	

	
	
	
//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		
//		
//		if (event.getAction() == MotionEvent.ACTION_DOWN) {
//			// delegating event handling to the droid
//			droid.handleActionDown((int)event.getX(), (int)event.getY());
//			droid2.handleActionDown((int)event.getX(), (int)event.getY());
//			droid3.handleActionDown((int)event.getX(), (int)event.getY());
//			droid4.handleActionDown((int)event.getX(), (int)event.getY());
//			// check if in the lower part of the screen we exit
//			if (event.getY() > getHeight() - 50) {
//				thread.setRunning(false);
//				((Activity)getContext()).finish();
//			} else {
//				Log.d(TAG, "Coords: x=" + event.getX() + ",y=" + event.getY());
//				
//				
//				
//			}
//			
//		}
//		return true;
//		}
//		
		/*if (event.getAction() == MotionEvent.ACTION_MOVE) {
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
		}*/
		////return true;
//	}*/
	/*
	public boolean onSingleTapUp(MotionEvent event) {
		
		droid.getX();
		droid.getY();
		if((ax.contains(droid.getX()+10))||(ax.contains(droid2.getX()+10))||(ax.contains(droid3.getX()+10))||(ax.contains(droid4.getX()+10))){
			Toast.makeText(mContext,"yes!", 
	                Toast.LENGTH_SHORT).show();
		}
        Log.d(TAG, "onSingleTapUp: " + event.toString());
        return true;
    }*/


	private int randomN(int length) {
		// TODO Auto-generated method stub
		
		Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((length - 1) + 1) + 1;
	    System.out.println("random no"+ randomNum);
	    return randomNum;

		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// fills the canvas with black
		//canvas.drawColor(Color.BLACK);
		mBackgroundImage = Bitmap.createScaledBitmap(
                mBackgroundImage, getWidth(), getHeight(), true);
		canvas.drawBitmap(mBackgroundImage, 0,0, null);
		
		//image.draw(canvas);
		//tri_b.draw(canvas);
		
		//for(Image i: ls){
		
		 
		 for(Image i: drawimg){
			 i.draw(canvas);
		 }
		//}
		//droid2.draw(canvas);
		//droid3.draw(canvas);
		//droid4.draw(canvas);
		
	}

	class MyGestureListener extends SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures"; 
        
        @Override
        public boolean onDown(MotionEvent event) { 
            Log.d(DEBUG_TAG,"onDown: "); 
            return true;
        }
        
        @Override
    	public void onLongPress(MotionEvent arg0) {
    		// TODO Auto-generated method stub
    		 
    		 System.out.println("on Long Press");
    			Log.d("hello","onLP: ");
    		
    	}

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, 
                float velocityX, float velocityY) {
        	int one =0;
        	ArrayList<Image> FlingDroid = fling;
    		Image droidz = null;
    		 for( Image f : fling){
    			 droidz = f.getCircleLine((int)event1.getX(), (int)event1.getY(), (int)event2.getX(), (int)event2.getY());
    			System.out.println("droidz: "+droidz);
    			  System.out.println("droidz Fling: "+ droidz);	
    		 }
    			 FlingDroid.remove(droidz);
    			 
    			 if(FlingDroid.size()==0){
    				 one++;
    				 
    			 }
    		 
    	
    		 
    		 if(one == 1){
    			 Toast.makeText(mContext,"you are right Fling", 
    		                Toast.LENGTH_SHORT).show();
    			 System.out.println("yes");
    		 }
    		 
    		 
     
        	
        	
        	
            Log.d(DEBUG_TAG, "onFling: ");
            return true;
        }
    }

	@Override
	public boolean onDoubleTapEvent(MotionEvent arg0) {
			Log.d("hello","onDTE: ");
		
		return false;
	}
	
	@Override
	public boolean onSingleTapConfirmed(MotionEvent arg0) {
		//droid.changeColor(canvas);
		colflag = true;
		int one = 0;
		
		ArrayList<Image> SingleDroid = singletap;
		Image droidz = null;
		
		 for( Image f : singletap){
			 droidz = f.getRange(arg0.getX(), arg0.getY());
			 System.out.println("droidz ST: "+ droidz);
			
			 SingleDroid.remove(droidz);
			 
			 if(SingleDroid.size()==0){
				 one++;
				 
			 }
		
		 }
		
		 
		 if(one == 1){
			 Toast.makeText(mContext,"you are right ST", 
		                Toast.LENGTH_SHORT).show();
			 System.out.println("yes");
		 }
		 

		System.out.println("on single");
		Log.d("hello","onSTC: ");
		return false;
		
	}

	

	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		
		// TODO Auto-generated method stub
		
		return false;
	}

	

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onDoubleTap(MotionEvent e) {
		int one = 0;
		ArrayList<Image> DoubleDroid = doubletap;
		Image droidz = null;
		// f = listdroid.get(l);
		 for( Image f : doubletap){
			 droidz = f.getRange(e.getX(), e.getY());
			 System.out.println("droidz DT: "+ droidz);
			 //templist.add(droidz);
			// while(l<reach){
			 DoubleDroid.remove(droidz);
			 
			 if(DoubleDroid.size()==0){
				 one++;
				 
			 }
			 
		 }
		 if(DoubleDroid.size()==0){
			 Toast.makeText(mContext,"you are right DT", 
		                Toast.LENGTH_SHORT).show();
			 System.out.println("yes");
		 }
	
		Log.d("hello","onDT: ");
		return false;
	}

	

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	


	

}

