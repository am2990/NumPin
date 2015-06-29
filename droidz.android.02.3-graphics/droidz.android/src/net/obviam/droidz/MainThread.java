/**
 * 
 */
package net.obviam.droidz;

import java.util.ArrayList;
import java.util.Iterator;

import net.obviam.droidz.model.Droid;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.BaseBundle;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;


/**
 * @author impaler
 *
 * The Main thread which contains the game loop. The thread must have access to 
 * the surface view and holder to trigger events every game tick.
 */
public class MainThread extends Thread {
	
	private static final String TAG = MainThread.class.getSimpleName();

//	private static final String MyPREFERENCES = null;

	// Surface holder that can access the physical surface
	private SurfaceHolder surfaceHolder;
	Droid droidr;
	Droid droidg;
	Droid droidy;
	int bitmap_id1,bitmap_id2,bitmap_id3;
	//Context context = getActivity();
	
	//SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
	// The actual view that handles inputs
	// and draws to the surface
	private MainGamePanel gamePanel;
	static MainThread thread;
	// Context context=MainGamePanel.getContext();
	double x1,x2,x3,x4,y1,y2,y3,y4;
	Bitmap bit1,bit2,bit3;
	//private Droid droid;
	
	private Context mContext;
		// flag to hold game state 
	private static boolean running = true;

	//private Droid mdroid;
	public static void setRunning(boolean running) {
		MainThread.running = running;
	}

	public MainThread(SurfaceHolder surfaceHolder, MainGamePanel gamePanel, Context mContext) {
		super();
		this.surfaceHolder = surfaceHolder;
		this.gamePanel = gamePanel;
		this.mContext = mContext;
		//this.mdroid = mdroid;
		
		//this.thread=thread;
	}

	@Override
	public void run() {
		Canvas canvas;
		Log.d(TAG, "Starting game loop");
		while (running) {
			
			if(!running){
				return;
			}
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

	public void doSubmit() {
		// TODO Auto-generated method stub
		synchronized (surfaceHolder) {
			//droid.getX();
			ArrayList<Double> ax=new ArrayList<Double>();
			 ArrayList<Double> ay=new ArrayList<Double>();
			 ArrayList<Integer> bitmapid= new ArrayList<Integer>();
			 //ArrayList<Droid> droidobj = new ArrayList<Droid>();
			 
			//if(MainGamePanel.droid.isTouched()== true){
			if(MainGamePanel.SelectRed()!= null){
				droidr = MainGamePanel.SelectRed();
				
				x1 = (double)droidr.getX();
				y1 = (double)droidr.getY();
				bitmap_id1 = droidr.getBitmapId();
				//droidobj.add(droidr);
				
				ax.add(x1);
				ay.add(y1);
				bitmapid.add(bitmap_id1);
			}
			if(MainGamePanel.SelectGreen()!= null){
				droidr = MainGamePanel.SelectGreen();
				x2 = (double)droidr.getX();
				y2 = (double)droidr.getY();
				bitmap_id2 = droidr.getBitmapId();
				ax.add(x2);
				ay.add(y2);
				bitmapid.add(bitmap_id2);
				//droidobj.add(droidr);
			}
			if(MainGamePanel.SelectYellow()!= null){
				droidr = MainGamePanel.SelectYellow();
				x3 = (double)droidr.getX();
				y3 = (double)droidr.getY();
				bitmap_id3 = droidr.getBitmapId();
				//int id = droidr.getBitmapId();
				ax.add(x3);
				ay.add(y3);
				bitmapid.add(bitmap_id3);
				//droidobj.add(droidr);
			}
			
			System.out.println("x1-coord: "+ x1);
			System.out.println("y1-coord: "+ y1);
			System.out.println("x2-coord: "+ x2);
			System.out.println("y2-coord: "+ y2);
			System.out.println("x3-coord: "+ x3);
			System.out.println("y3-coord: "+ y3);
			
			
		
			 
			 Iterator<Double> itr=ax.iterator();//getting Iterator from arraylist to traverse elements  
			  while(itr.hasNext()){  
			   System.out.println(itr.next());  
			  } 
			  
			
				 
				 Iterator<Double> itr1=ay.iterator();//getting Iterator from arraylist to traverse elements  
				  while(itr1.hasNext()){  
				   System.out.println(itr1.next());  
				  } 
			
			
			Intent intent = new Intent(mContext, TestActivity.class);
			intent.putExtra("ax", ax);
			intent.putExtra("ay", ay);
			//intent.putExtra("bitmap", bitmap);
			intent.putExtra("bitmapid", bitmapid);
			

			mContext.startActivity(intent);
			
		}
	}

	public void doVerify() {
		// TODO Auto-generated method stub
		//if(MainGamePanel.droid.getX()==x){
			System.out.println("right!!");
		//}
		
	}

	public void doConfirm() {
		// TODO Auto-generated method stub
		
	}

	
	
}

