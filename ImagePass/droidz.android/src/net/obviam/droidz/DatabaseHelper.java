package net.obviam.droidz;



import java.util.ArrayList;

import java.util.List;


import net.obviam.droidz.model.Droid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class DatabaseHelper extends SQLiteOpenHelper{

	
	 private static final String LOG = "DatabaseHelper";
	 
	    // Database Version
	    private static final int DATABASE_VERSION = 1;
	 
	    // Database Name
	    private static final String DATABASE_NAME = "droid_db";
	 
	    // Table Names
	    private static final String TABLE_DROID = "table_droid";
	    private static final String TABLE_MAP = "table_map";
	    private static final String TABLE_GESTURE = "t_gesture";
	    
	    private static final String KEY_ID = "_id"; //primary key
	    private static final String KEY_CREATED_AT = "created_at";
	 
	    // Droid Table - column nmaes
	   // private static final String KEY_DID = "did";
	    private static final String KEY_X = "x";
	    private static final String KEY_Y = "y";
	    private static final String KEY_BITMAP_ID = "bitmap_id";
	    private static final String KEY_COLOR = "color";
	    
	    // Map Table - column names
	    private static final String KEY_POSITION_M = "position";
	    private static final String KEY_COLOR_M = "color_m";
	    private static final String KEY_GESTURE_M = "gesture_m";
	    
	    
	    // Gesture Table - column names
	    private static final String KEY_GID= "g_id";
	   	private static final String KEY_GESTURE = "gesture";
	    
	    
	 // Table Create Statements
	    // DROID table create statement
	    private static final String CREATE_TABLE_DROID = "CREATE TABLE "
	    		+ TABLE_DROID + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_X + " INTEGER," + KEY_Y + " INTEGER," + KEY_BITMAP_ID + " INTEGER," 
	    		+ KEY_COLOR + " TEXT" + ")";
	    
	 // MAP table create statement
	    private static final String CREATE_TABLE_MAP = "CREATE TABLE " + TABLE_MAP
	    		+ "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_POSITION_M + " INTEGER,"
	    		+ KEY_COLOR_M + " INTEGER," + KEY_GESTURE_M + " INTEGER," + ")";
	    
	 // GESTURE table create statement
	    private static final String CREATE_TABLE_GESTURE = "CREATE TABLE " + TABLE_MAP
	    		+ "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_GID + " INTEGER," + KEY_GESTURE + " TEXT," + ")";
	    
	    
	    
	    
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	// Logcat tag
   


    @Override
    public void onCreate(SQLiteDatabase db) {

    	// creating required tables
    	db.execSQL(CREATE_TABLE_DROID);
    	db.execSQL(CREATE_TABLE_MAP);
    	db.execSQL(CREATE_TABLE_GESTURE);
    	
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	// on upgrade drop older tables
    	db.execSQL("DROP TABLE IF EXISTS " + TABLE_DROID);
    	db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAP);
    	db.execSQL("DROP TABLE IF EXISTS " + TABLE_GESTURE);

    	// create new tables
    	onCreate(db);
    }
    
    /*
     * Adding a droid
     */
    public long createDroid(Droid droid) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(KEY_X, droid.getX());
        values.put(KEY_Y, droid.getY());
        values.put(KEY_BITMAP_ID, droid.getBitmapId());
        values.put(KEY_COLOR, droid.getColor());
       // values.put(KEY_CREATED_AT, getDateTime());
     
        // insert row
        long droid_id = db.insert(TABLE_DROID, null, values);
     
        return droid_id;
    }
    
    /**
     * getting all droids under single tag
     * */
    public List<String> getAllDroids() {
        List<String> droids = new ArrayList<String>();
 
        String selectQuery = "SELECT  * FROM " + TABLE_DROID;
        
        Log.e(LOG, selectQuery);
 
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Droid droid = new Droid(null, 0, 0, 0, selectQuery, null);
              
                droid.setX((c.getInt(c.getColumnIndex(KEY_X))));
                droid.setY((c.getInt(c.getColumnIndex(KEY_Y))));
                droid.setBitmapId(c.getInt(c.getColumnIndex(KEY_BITMAP_ID)));
                droid.setColor((c.getString(c.getColumnIndex(KEY_COLOR))));
               
               droids.add(droid.toString());
            } while (c.moveToNext());
        }
 
        return droids;
    
    }
	
}
