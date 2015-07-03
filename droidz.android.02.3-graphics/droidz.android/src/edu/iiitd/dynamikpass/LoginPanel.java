/**
 * 
 */
package edu.iiitd.dynamikpass;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import edu.iiitd.dynamikpass.model.Image;
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

	
	private static final String TAG = RegistrationPanel.class.getSimpleName();
	
	static MainThread1 thread;
	static Image image;
	boolean flag;
	boolean check;
	int countdroidST=0;
	int countdroidDT=0;
	boolean colflag;
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
      private ArrayList<Image> correctDroid ;
      private ArrayList<Image> listdroid ;
	 ArrayList<Double> ax1;

	 ArrayList<Double> ay1;
	 //ArrayList<Droid> droidobj1;
	 ArrayList<Integer> bitmapid1;
	 //ArrayList<Bitmap> bitmap;

	 private GestureDetectorCompat mDetector; 
	 private SurfaceView surfaceView;
	
	public LoginPanel(Context context, ArrayList<Double> ax, ArrayList<Double> ay, ArrayList<Integer> bitmapid) {
		super(context);
		ax1=ax;
		ay1 = ay;
		bitmapid1 = bitmapid;
		//bitmap = BitMap;
		
		// adding the callback (this) to the surface holder to intercept events
		
		
		
		
		
	  
		Double[] arrayx = new Double[ax.size()]; 
		Double[] arrayy = new Double[ay.size()];
		//Bitmap[] bm = new Bitmap[BitMap.size()];
		Integer[] bm_id = new Integer[bitmapid.size()];
		
		
		// create droid and load bitmap
		image = new Image(BitmapFactory.decodeResource(getResources(), R.drawable.droid_1),R.drawable.droid_1, 50, 50,"blue", getResources());
		tri_b = new Image(BitmapFactory.decodeResource(getResources(), R.drawable.triangle_blue),R.drawable.triangle_blue, 100, 100,"blue", getResources());
		
		new BitmapFactory();
		mBackgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.surreal);
		
		// create the game loop thread
		listdroid= new ArrayList<Image>();
		//for(i)
	    listdroid.add(image);
	    listdroid.add(tri_b);
	   // listdroid.add(droid2);
	   // listdroid.add(droid3);
	    //listdroid.add(droid4);
		correctDroid = new ArrayList<Image>(listdroid);
	   // wrongDroid = new ArrayList<Droid>();

		for(int j =0;j<ax.size();j++){
			  arrayx[j] = ax.get(j);
			}
		for(int l =0;l<ay.size();l++){
			  arrayy[l] = ay.get(l);
			}
		for(int l =0;l<bitmapid.size();l++){
			  bm_id[l] = bitmapid.get(l);
			}
		
		int count = ax.size();
		// System.out.println("count"+ count);*/
		/*for(int l =0;l<bitmapid.size();l++){
			  drob[l] = bitmapid.get(l);
			}*/
		//int count = bitmapid.size();
	int r = randomN(ax.size()-1);
	//	int r=1;
		//if(r>0){
		for(int i=0;i<r;i++){
			
			Random ran = new Random();
			Random rando = new Random();
			rando.setSeed((long)i);
			int rr = rando.nextInt(((count) - 0) + 1) + 0;
		//	int rr=2;

		    // nextInt is normally exclusive of the top value,
		    // so add 1 to make it inclusive
			//int rr = ran.nextInt(((ax.size()-1) - 1) + 1) + 1;
			ran.setSeed((long)i);
			
		    int randomNumx = ran.nextInt((350 - 25) + 1) + 25;
		    int randomNumy = ran.nextInt((350 - 25) + 1) + 25;
		   // System.out.println("rr"+ rr);
		   // System.out.println("random no x"+ randomNumx);
		   // System.out.println("random no y"+ randomNumy);
		   // System.out.println(ax);
		  //  System.out.println(ay);
		                
			

			/*Displaying Array elements*/
			/*for(double k: arrayx)
			{
				System.out.println(k);
			}*/
			

				/*Displaying Array elements*/
				/*for(double p: arrayy)
				{
					System.out.println(p);
				}*/
			System.out.println("rr: "+ rr);
		    arrayx[rr]=(double) randomNumx;
		    arrayy[rr]=(double) randomNumy;
			
		    
		    
		    //int y= listdroid.size();
		    //System.out.println("y: "+y);
		    

		    switch(rr){

		    case 0:
		    	correctDroid.remove(image);
		    	reach++;
		    	continue;
		    case 1:
		    	correctDroid.remove(tri_b);
		    	reach++;
		    	continue;
		   /* case 2:
		    	correctDroid.remove(droid3);
		    	reach++;
		    	continue;
		    case 3:
		    	correctDroid.remove(droid4);
		    	reach++;
		    	continue;*/

		    }  
		   
		    //((Object) correctDroid).subtract(listdroid,wrongDroid);
		  //  correctDroid.removeAll(wrongDroid);
		   // System.out.println("reach: "+ reach);
		   // System.out.println("correct size: "+ correctDroid.size());
		    
		    
		    // find the right droids
		    //correctDroid.add(object)
		}
	//}
	/*	else{
			//
			System.out.println("do nothing");
			
		}*/
		/*for(Droid d: correctDroid){
	    	System.out.println("corr droid: "+d.getX());
	    }*/
		image.setX(arrayx[0].intValue());//= arrayx[0].intValue();
		image.setY(arrayy[0].intValue());
		image.setBitmapId( bm_id[0]);
//		droid.setBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.droid_3));
		//droid.setBitmap(bm[0]);
//		tri_b.setX(arrayx[1].intValue());//= arrayx[0].intValue();
//		tri_b.setY(arrayy[1].intValue());
//		tri_b.setBitmapId(getResources(), bm_id[1]);

				//droid2.setX(arrayx[1].intValue());//= arrayx[0].intValue();
				//droid2.setY(arrayy[1].intValue());
				//droid3.setX(arrayx[2].intValue());//= arrayx[0].intValue();
				//droid3.setY(arrayy[2].intValue());
				//droid4.setX(arrayx[3].intValue());//= arrayx[0].intValue();
				//droid4.setY(arrayy[3].intValue());
		/*droid2 = new Droid(BitmapFactory.decodeResource(getResources(), R.drawable.droid_2), arrayx[1].intValue(), arrayy[1].intValue());
		droid3 = new Droid(BitmapFactory.decodeResource(getResources(), R.drawable.droid_3), arrayx[2].intValue(), arrayy[2].intValue());
		droid4 = new Droid(BitmapFactory.decodeResource(getResources(), R.drawable.droid_4), arrayx[3].intValue(), arrayy[3].intValue());*/
		
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
	    int randomNum = rand.nextInt((length - 0) + 1) + 0;
	    System.out.println("random no"+ randomNum);
	    return randomNum;

		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// fills the canvas with black
		//canvas.drawColor(Color.BLACK);
		
		canvas.drawBitmap(mBackgroundImage, 0,0, null);
		
		image.draw(canvas);
		tri_b.draw(canvas);
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
        	ArrayList<Image> FlingDroid = correctDroid;
    		Image droidz = null;
    		 for( Image f : listdroid){
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
		
		ArrayList<Image> SingleDroid = correctDroid;
		Image droidz = null;
		
		 for( Image f : listdroid){
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
		ArrayList<Image> DoubleDroid = correctDroid;
		Image droidz = null;
		// f = listdroid.get(l);
		 for( Image f : listdroid){
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

