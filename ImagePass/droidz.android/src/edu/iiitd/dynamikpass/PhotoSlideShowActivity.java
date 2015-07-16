/* ** activity for slide show for selecting background image** */


package edu.iiitd.dynamikpass;

import java.io.File;

import edu.iiitd.dynamikpass.R;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

//slideShowBtn.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//
//                runnable = new Runnable() {
//
//                    @Override
//                    public void run() {
//                        handler.postDelayed(runnable, 3000);
//                        imageFrame.showNext();
//
//                    }
//                };
//                handler.postDelayed(runnable, 500);
//            }
//        });
//
//To stop it use 
//handler.removeCallbacks(runnable);
//
//
//Here is the full code which includes swipe action from codeshogun(http://www.codeshogun.com/blog/2009/04/16/how-to-implement-swipe-action-in-android/):

public class PhotoSlideShowActivity extends Activity implements OnClickListener {

	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_MAX_OFF_PATH = 250;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	private GestureDetector gestureDetector;
	View.OnTouchListener gestureListener;
	ViewFlipper imageFrame;
	RelativeLayout slideShowBtn;
	Handler handler;
	Runnable runnable;
	static Bitmap imbm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slide_show);
		imageFrame = (ViewFlipper) findViewById(R.id.imageFrames);

		// get sd card path for images

		File parentFolder = new File(Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/Pictures/CameraSample");

		addFlipperImages(imageFrame, parentFolder);
		// Gesture detection
		gestureDetector = new GestureDetector(new MyGestureDetector());
		gestureListener = new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() >= MotionEvent.ACTION_POINTER_2_DOWN) {
					try {
//						ImageView img = new ImageView(
//								PhotoSlideShowActivity.this);
//						img.setDrawingCacheEnabled(true);
//						img.setImageBitmap(BitmapFactory.decodeResource(
//								getResources(), R.drawable.antartica1));
//						img.measure(MeasureSpec.makeMeasureSpec(0,
//								MeasureSpec.UNSPECIFIED), MeasureSpec
//								.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
//						img.layout(0, 0, img.getMeasuredWidth(),
//								img.getMeasuredHeight());

//						TouchImageView touch = new TouchImageView(
//								PhotoSlideShowActivity.this);
						// BitmapDrawable d=(BitmapDrawable)img.getBackground();
//						img.buildDrawingCache(true);
//
//						Bitmap bitmap = Bitmap.createBitmap(img
//								.getDrawingCache());
//						Bitmap bitmap=null;
//						touch.setDrawingCacheEnabled(true);
//						touch.setImageBitmap(BitmapFactory.decodeResource(
//								getResources(), R.drawable.antartica1));
//						touch.measure(MeasureSpec.makeMeasureSpec(0,
//								MeasureSpec.UNSPECIFIED), MeasureSpec
//								.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
//						touch.layout(0, 0, touch.getMeasuredWidth(),
//								touch.getMeasuredHeight());
////						touch.setImageBitmap(bitmap);                                                            // imageFrame.getCurrentView().getBackground());
//						touch.buildDrawingCache(true);
//						
//						bitmap = Bitmap.createBitmap(touch.getDrawingCache());
//						((ImageView) imageFrame.getCurrentView())
//								.setImageBitmap(bitmap);
//								imageFrame.set
//						imageFrame.addView(touch);
					} catch (Exception e) {
						e.getMessage();
					}
					return true;
				} else if (gestureDetector.onTouchEvent(event))
					return true;
				else
					return false;
			}
		};
		handler = new Handler();
		imageFrame.setOnClickListener(PhotoSlideShowActivity.this);
		imageFrame.setOnTouchListener(gestureListener);
		slideShowBtn = (RelativeLayout) findViewById(R.id.slideShowBtn);
		slideShowBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				runnable = new Runnable() {

					@Override
					public void run() {
						handler.postDelayed(runnable, 3000);
						imageFrame.showNext();

					}
				};
				handler.postDelayed(runnable, 500);
			}
		});

	}

	private void addFlipperImages(ViewFlipper flipper, File parent) {
		int imageCount = parent.listFiles().length;
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.FILL_PARENT,
				RelativeLayout.LayoutParams.FILL_PARENT);
		for (int count = 0; count < imageCount - 1; count++) {
			ImageView imageView = new ImageView(this);
			String file = parent.listFiles()[count].getAbsolutePath();
			imbm = BitmapFactory.decodeFile(file);
			imageView.setImageBitmap(imbm);
			imageView.setLayoutParams(params);
			flipper.addView(imageView);
		}
		//imbm.get
		
	/*	intent.putExtra("ay", ay);
		//intent.putExtra("bitmap", bitmap);
		intent.putExtra("bitmapid", bitmapid);*/
		
		}

	class MyGestureDetector extends SimpleOnGestureListener {
		@SuppressWarnings("static-access")
		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			slideShowBtn = (RelativeLayout) findViewById(R.id.slideShowBtn);
			slideShowBtn.setVisibility(slideShowBtn.VISIBLE);
			handler.removeCallbacks(runnable);
			runnable = new Runnable() {

				@Override
				public void run() {
					slideShowBtn.setVisibility(slideShowBtn.INVISIBLE);
				}
			};
			handler.postDelayed(runnable, 2000);
			return true;
		}

		@SuppressWarnings("static-access")
		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			slideShowBtn = (RelativeLayout) findViewById(R.id.slideShowBtn);
			slideShowBtn.setVisibility(slideShowBtn.VISIBLE);
			handler.removeCallbacks(runnable);
			runnable = new Runnable() {

				@Override
				public void run() {
					slideShowBtn.setVisibility(slideShowBtn.INVISIBLE);
				}
			};
			handler.postDelayed(runnable, 2000);
			return true;
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			try {
				if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
					return false;
				// right to left swipe
				if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
						&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					handler.removeCallbacks(runnable);
					imageFrame.setInAnimation(inFromRightAnimation());
					imageFrame.setOutAnimation(outToLeftAnimation());
					imageFrame.showNext();
				} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
						&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					handler.removeCallbacks(runnable);
					imageFrame.setInAnimation(inFromLeftAnimation());
					imageFrame.setOutAnimation(outToRightAnimation());
					imageFrame.showPrevious();
				}
			} catch (Exception e) {
				// nothing
			}
			return false;
		}

	}

	@Override
	public void onClick(View view) {
	}

	private Animation inFromRightAnimation() {

		Animation inFromRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, +1.2f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromRight.setDuration(500);
		inFromRight.setInterpolator(new AccelerateInterpolator());
		return inFromRight;
	}

	private Animation outToLeftAnimation() {
		Animation outtoLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -1.2f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outtoLeft.setDuration(500);
		outtoLeft.setInterpolator(new AccelerateInterpolator());
		return outtoLeft;
	}

	private Animation inFromLeftAnimation() {
		Animation inFromLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, -1.2f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromLeft.setDuration(500);
		inFromLeft.setInterpolator(new AccelerateInterpolator());
		return inFromLeft;
	}

	private Animation outToRightAnimation() {
		Animation outtoRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, +1.2f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outtoRight.setDuration(500);
		outtoRight.setInterpolator(new AccelerateInterpolator());
		return outtoRight;
	}

}
