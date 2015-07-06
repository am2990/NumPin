/**
 * 
 */
package edu.iiitd.dynamikpass.model;


import java.io.Serializable;
import java.util.List;

import edu.iiitd.dynamikpass.R;
import edu.iiitd.dynamikpass.utils.CircleLine;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

/**
 * @author impaler
 *
 */
public class Image implements Serializable{

	/**
	 * 
	 */
	//String str;
	private static final long serialVersionUID = 8438662189730257314L;
	private Bitmap bitmap;
	private int bitmap_id;// the actual bitmap
	private int x;			// the X coordinate
	private int y;			// the Y coordinate
	private boolean touched, longPressed;	// if droid is touched/picked up
	private String col;
	private Resources res;
	private static final String TAG = "DroidObject";

	public static int rad;
	public static boolean host=false;

	public Image(){
		/*this.bitmap = bitmap;
		this.bitmap_id = bitmap_id;
		this.x = x;
		this.y = y;
		this.col = col;
		this.res=res;*/

	}
	public Image(Bitmap bitmap, int bitmap_id, int x, int y, String col, Resources res){
		this.bitmap = bitmap;
		this.bitmap_id = bitmap_id;
		this.x = x;
		this.y = y;
		this.col = col;
		this.res=res;

	}
	
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	public int getBitmapId(){
		return bitmap_id;
	}
	public void setBitmapId(int bitmap_id){
		this.bitmap_id = bitmap_id;
		setBitmap(BitmapFactory.decodeResource(res, bitmap_id));
	}
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public boolean isTouched() {
		return touched;
	}

	

	public String getColor(){
		
		System.out.println("getColor: "+getBitmapId());
		switch(getBitmapId()){
		
		case R.drawable.droid_1:
			
			 col = "dblue";
			 break;
		case R.drawable.droid_2:
			 col = "dyellow";
			 break;
		case R.drawable.droid_3:
			 col = "dred";
			 break;
		case R.drawable.droid_4:
			 col = "dgreen";
			 break;
		case R.drawable.triangle_blue:
			 col = "tblue";
			 break;
		case R.drawable.triangle_yellow:
			 col = "tyellow";
			 break;
		case R.drawable.triangle_green:
			 col = "tgreen";
			 break;
		case R.drawable.triangle_red:
			 col = "tred";
			 break;
			 
			
		}
		return col;
	}
	
	
	public void setColor(String col){
		
		switch(col){
		
		case "dblue":
			setBitmapId( R.drawable.droid_1);
			break;
		case "dred":
			setBitmapId( R.drawable.droid_3);
			break;
		case "dyellow":
			setBitmapId( R.drawable.droid_2);
			break;
		case "dgreen":
			setBitmapId( R.drawable.droid_4);
			break;
		case "tblue":
			setBitmapId( R.drawable.triangle_blue);
			break;
		case "tred":
			setBitmapId( R.drawable.triangle_red);
			break;
		case "tyellow":
			setBitmapId( R.drawable.triangle_yellow);
			break;
		case "tgreen":
			setBitmapId( R.drawable.triangle_green);
			break;
			
		}
		
	}
	public void setTouched(boolean touched) {
		this.touched = touched;
	}
	
	public boolean isLongTouched() {
		return longPressed;
	}

	public void setLongPressed(boolean lPressed) {
		this.longPressed = lPressed;
	}
	
	
	public Image getRange(float f, float g){
		
		Image d = null;
		int h= bitmap.getHeight();
		int w = bitmap.getWidth();
		
		
		 rad = ((int) (Math.sqrt(((h/2)*(h/2))+((w/2)*(w/2))))+10);
		
		System.out.println("get xcoord: "+f);
		System.out.println("get ycoord: "+g);
		double eqn = (((f-getX())*(f-getX()))+((g-getY())*(g-getY()))-(rad*rad));
		System.out.println("eqn droid : "+eqn);
		
		

		
		if(eqn <= 0){
			System.out.println("inside circle");
			d =  this;
		
		}
		else{
			System.out.println("outside circle");
		}
			
		return d;
		
		
		
	}
	
	public Image getCircleLine(int event1x, int event1y, int event2x, int event2y){
		
		CircleLine.Point center = new CircleLine.Point(getX(), getY());
		int h= bitmap.getHeight();
		int w = bitmap.getWidth();
		//int rad = (y-(bitmap.getHeight()/2));
		CircleLine.Point pointA = new CircleLine.Point(event1x,event1y);
		CircleLine.Point pointB = new CircleLine.Point(event2x,event2y);
		CircleLine.Point p = null;
		 int radius = ((int) (Math.sqrt(((h/2)*(h/2))+((w/2)*(w/2))))+10);
		List<CircleLine.Point> lp = CircleLine.getCircleLineIntersectionPoint(pointA, pointB, center, radius);
		/*for(int i=0;i<lp.size();i++){
			lp.get(i);
		}*/
		Log.d(TAG, "LP Size " + lp.size());
		try{
			Image sp = null ;
			for(int i =0;i<lp.size();i++){
		sp = getRange((float)lp.get(0).x, (float)lp.get(0).y);
		
			}
			return sp;
		}
		catch(Exception e){
			Log.d(TAG, "nullll");
			return null;
		}
	
		
	}
	
   
	public void draw(Canvas canvas) {
		
		
		
		canvas.drawBitmap(bitmap, x - (bitmap.getWidth() / 2), y - (bitmap.getHeight() / 2), null);
		if(host = true){
		Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(getX(), getY(), rad, paint);
        
        
		}
		
		
	}
	


	/**
	 * Handles the {@link MotionEvent.ACTION_DOWN} event. If the event happens on the 
	 * bitmap surface then the touched state is set to <code>true</code> otherwise to <code>false</code>
	 * @param eventX - the event's X coordinate
	 * @param eventY - the event's Y coordinate
	 */
	public void handleActionDown(int eventX, int eventY) {
		Integer x = this.x;
		if (eventX >= (x - bitmap.getWidth() / 2) && (eventX <= (x + bitmap.getWidth()/2))) {
			if (eventY >= (y - bitmap.getHeight() / 2) && (eventY <= (y + bitmap.getHeight() / 2))) {
				// droid touched
				setTouched(true);
				setLongPressed(true);
			} else {
				setTouched(false);
				setLongPressed(false);
			}
		} else {
			setTouched(false);
			setLongPressed(false);
		}

	}
}
