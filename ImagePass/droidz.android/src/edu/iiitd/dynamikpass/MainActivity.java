/* ** activity to select objects from grid view ** */

package edu.iiitd.dynamikpass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.iiitd.dynamikpass.utils.DatabaseHelper;







import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {
	 GridView gridView;
	 Object obj;
	int imageBack;
	private boolean mChecked;
	ArrayList<String> images = new ArrayList();
	
	 private static final int MENU_SELECTIMG = 0;
	 
    String[] countries = new String[] {
    		"Droid",
    		"Square",
    		"Smiley",
    		"Football"
           
    };

    // Array of integers points to images stored in /res/drawable-ldpi/
    int[] flags = new int[]{
                R.drawable.droid_1,
                R.drawable.triangle_blue,
                R.drawable.smiley_b,
                R.drawable.football_b
               
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Intent ii = getIntent();
        imageBack = ii.getIntExtra("ib",0);
        System.out.println("image: "+ imageBack);
        
        
        DatabaseHelper db = new DatabaseHelper(this);
        db.createImage("droid", R.drawable.droid_1, R.drawable.droid_4, R.drawable.droid_3, R.drawable.droid_2);
        db.createImage("triangle", R.drawable.triangle_blue,R.drawable.triangle_green,R.drawable.triangle_red, R.drawable.triangle_yellow);
        db.createImage("smiley", R.drawable.smiley_b, R.drawable.smiley_g, R.drawable.smiley_r, R.drawable.smiley_y);
        db.createImage("football", R.drawable.football_b, R.drawable.football_g, R.drawable.football_r, R.drawable.football_y);
        
        // Each row in the list stores country name, currency and flag
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<4;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("txt", countries[i]);            
            hm.put("flag", Integer.toString(flags[i]) );
            aList.add(hm);
          
        }

        
        // Keys used in Hashmap
        String[] from = { "flag","txt"};

        int[] to = { R.id.flag,R.id.txt};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.gridview_layout, from, to);        
        
        // Getting a reference to gridview of MainActivity
        gridView = (GridView) findViewById(R.id.gridview);
        
        
        // Setting an adapter containing images to the gridview
        gridView.setAdapter(adapter);
        gridView.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE_MODAL);
        gridView.setMultiChoiceModeListener(new MultiChoiceModeListener());
        
        
    }

    public View getView(int position, View convertView, ViewGroup parent) {
    	
		CheckableLayout l;
		ImageView i;
		//l = new CheckableLayout(MainActivity.this);
		if (convertView == null) {
			i = new ImageView(MainActivity.this);
			i.setScaleType(ImageView.ScaleType.FIT_CENTER);
			i.setLayoutParams(new ViewGroup.LayoutParams(50, 50));
			l = new CheckableLayout(MainActivity.this);
			l.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.WRAP_CONTENT,
					GridView.LayoutParams.WRAP_CONTENT));
			l.addView(i);
		} else {
			l = (CheckableLayout) convertView;
			i = (ImageView) l.getChildAt(0);
		}

	
		return l;
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	 super.onCreateOptionsMenu(menu);
       
        menu.add(0, MENU_SELECTIMG, 0, R.string.menu_selectimage);
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
 
	public class CheckableLayout extends FrameLayout implements Checkable {
		

		public CheckableLayout(Context context) {
			super(context);
			//gridView.setBackgroundColor(R.drawable.red);
		}

		@SuppressWarnings("deprecation")
		public void setChecked(boolean checked) {
			
			mChecked = checked;
			setBackgroundDrawable(checked ? getResources().getDrawable(
					R.drawable.blue) : null);
			//gridView.setBackgroundColor(R.drawable.red);
		}

		public boolean isChecked() {
			return mChecked;
		}

		public void toggle() {
			setChecked(!mChecked);
		}

	}
    
	public class MultiChoiceModeListener implements
	GridView.MultiChoiceModeListener {
public boolean onCreateActionMode(ActionMode mode, Menu menu) {
	mode.setTitle("Select Items");
	mode.setSubtitle("One item selected");
	
	return true;
}

public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
	return true;
}

public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
	return true;
}

public void onDestroyActionMode(ActionMode mode) {
}

public void onItemCheckedStateChanged(ActionMode mode, int position,
		long id, boolean checked){
	
	HashMap<String,String> obj = (HashMap<String,String>)gridView.getItemAtPosition(position);
	System.out.println("obj: "+ obj.get("flag"));
	
	
	int selectCount = gridView.getCheckedItemCount();
	if(checked){
		System.out.println("in mChecked");
		
		images.add(obj.get("flag"));
	}
	else if(!checked){
		images.remove(obj.get("flag"));
	}
	
	switch (selectCount) {
	case 1:
		mode.setSubtitle("One item selected");
		break;
	default:
		mode.setSubtitle("" + selectCount + " items selected");
		break;
	}
	
	for(int i = 0; i < images.size(); i++) {
		
        System.out.println("list elem: "+images.get(i).toString());
    }
}

}
	
	
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		
        switch (item.getItemId()) {
            case MENU_SELECTIMG:
		
			
         	Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
         	
    			intent.putExtra("ib", imageBack);
    			intent.putExtra("imageobjs", images);
    		
    		startActivity(intent);
    		
                return true;
           
        }

        return false;
    }
}
