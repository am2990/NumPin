/* ** activity for selecting background image ** */

package edu.iiitd.dynamikpass;


import edu.iiitd.dynamikpass.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class GalleryView extends Activity {
	Integer[] pics = { R.drawable.antartica1, R.drawable.antartica2,
			R.drawable.antartica3, R.drawable.antartica4,
			R.drawable.antartica5, R.drawable.antartica6,
			R.drawable.antartica7, R.drawable.antartica8,
			R.drawable.antartica9, R.drawable.antartica10 ,
			R.drawable.antartica3, R.drawable.antartica4,
			R.drawable.antartica5, R.drawable.antartica6,
			R.drawable.antartica7, R.drawable.antartica8,
			R.drawable.antartica9, R.drawable.antartica10 };
	LinearLayout imageView;

	private Context ctx;
	int imageBackground;
	int selectedImage = 0;
	private static final int MENU_SETBACK = 0;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_gal);
		try {
			
		} catch (Exception e) {
			e.getMessage();
		}
		Gallery ga = (Gallery) findViewById(R.id.Gallery01);
		ga.setAdapter(new ImageAdapter(this));

		imageView = (LinearLayout) findViewById(R.id.ImageView01);

		ga.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(
						getBaseContext(),
						"You have selected picture " + (arg2 + 1)
								+ " of Antartica", Toast.LENGTH_SHORT).show();
				try {
				imageView.removeAllViews();
				} catch (Exception e) {
					e.getMessage();
				}
				
				TouchImageView touchImageView = new TouchImageView(
						GalleryView.this);
				touchImageView.setImageResource(pics[arg2]);
				LayoutParams lp=new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
				imageView.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
				touchImageView.setLayoutParams(lp);
				imageView.addView(touchImageView);
				selectedImage = pics[arg2];
			}

		});
		

	}

	public class ImageAdapter extends BaseAdapter {


		


		public ImageAdapter(Context c) {
			ctx = c;
			TypedArray ta = obtainStyledAttributes(R.styleable.Gallery1);
			imageBackground = ta.getResourceId(
					R.styleable.Gallery1_android_galleryItemBackground, 1);
			ta.recycle();
		}

		@Override
		public int getCount() {

			return pics.length;
		}

		@Override
		public Object getItem(int arg0) {

			return arg0;
		}

		@Override
		public long getItemId(int arg0) {

			return arg0;
		}

		
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			System.out.println("get View");
			ImageView iv = new ImageView(ctx);
			iv.setImageResource(pics[arg0]);
			iv.setScaleType(ImageView.ScaleType.FIT_XY);
			iv.setLayoutParams(new Gallery.LayoutParams(150, 120));
			iv.setBackgroundResource(imageBackground);
			
			System.out.println("iv: "+ iv);
			return iv;
			
		}
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0, MENU_SETBACK, 0, R.string.menu_setBack);
       
       

        return true;
    }

	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		
        switch (item.getItemId()) {
            case MENU_SETBACK:
		
            	System.out.println("ib: "+selectedImage);
         	Intent intent = new Intent(getApplicationContext(), MainActivity.class);
    			intent.putExtra("ib", selectedImage);
    	
    		startActivity(intent);
    		
                return true;
         }

        return false;
    }
}