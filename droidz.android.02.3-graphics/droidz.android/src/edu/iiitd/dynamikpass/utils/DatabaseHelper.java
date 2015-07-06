package edu.iiitd.dynamikpass.utils;



import java.util.ArrayList;
import java.util.List;



import edu.iiitd.dynamikpass.RegistrationActivity;
import edu.iiitd.dynamikpass.RegistrationPanel;
import edu.iiitd.dynamikpass.model.Image;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.net.nsd.NsdManager.RegistrationListener;
import android.provider.ContactsContract.CommonDataKinds.Im;
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
	    private static final String KEY_RES = "resources";
	    
	    // Map Table - column names
	    private static final String KEY_POSITION_M = "position_m";
	    private static final String KEY_COLOR_M = "color_m";
	    private static final String KEY_GESTURE_M = "gesture_m";
	    
	    
	    // Gesture Table - column names
	    private static final String KEY_GID= "g_id";
	   	private static final String KEY_GESTURE = "gesture";
	    
	    
	 // Table Create Statements
	    // DROID table create statement
	    private static final String CREATE_TABLE_DROID = "CREATE TABLE "
	    		+ TABLE_DROID + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_X + " INTEGER," + KEY_BITMAP_ID + " INTEGER," + KEY_Y + " INTEGER," 
	    		+ KEY_COLOR + " TEXT" + " )";
	    
	 // MAP table create statement
	    private static final String CREATE_TABLE_MAP = "CREATE TABLE " + TABLE_MAP
	    		+ "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_POSITION_M + " INTEGER,"
	    		+ KEY_COLOR_M + " INTEGER," + KEY_GESTURE_M + " TEXT" + " )";
	    
	 // GESTURE table create statement
	    private static final String CREATE_TABLE_GESTURE = "CREATE TABLE " + TABLE_GESTURE
	    		+ "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_GID + " INTEGER," + KEY_GESTURE + " TEXT" + ")";
	    
	    
	    
	    
	    public DatabaseHelper(Context context) {
	    	super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    	
	    	
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
    
    /* Inserting values in gesture table : hardcoded */

    
    
    /*
     * Adding a droid
     */
    public long saveOrUpdateImage(Image img) {
        SQLiteDatabase db = this.getWritableDatabase();
        long droid_id = 0;
      int id = img.getBitmapId();
        
      //TODO using bitmap id check if an image already exits
       String whereClause = KEY_BITMAP_ID +"= ? "; 
        
        String[] whereArgs = {  Integer.toString(id) };  
        String selQuery = "SELECT * FROM " + TABLE_DROID +" WHERE " + KEY_BITMAP_ID+ "=" + id;
        //int id = img.getBitmapId();
//        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selQuery, null);
        
//        if (exits ) update that object
        //else
        if(c.getCount() == 1){
        	c.moveToFirst();
        	
        	ContentValues values = new ContentValues();
        	values.put(KEY_BITMAP_ID, img.getBitmapId());
        	values.put(KEY_X, img.getX());
        	values.put(KEY_Y,img.getY());
        	values.put(KEY_COLOR, img.getColor());
        	droid_id =  db.update(TABLE_DROID, values, whereClause, whereArgs);
        	
        }
        else{
        	ContentValues values = new ContentValues();
        	values.put(KEY_BITMAP_ID, img.getBitmapId());
        	values.put(KEY_X, img.getX());
        	values.put(KEY_Y,img.getY());
        	values.put(KEY_COLOR, img.getColor());
        
//        values.put(KEY_BITMAP_ID, id);
//        values.put(KEY_X, x);
//        values.put(KEY_Y, y);
//        
//        values.put(KEY_COLOR, col);
       
        // insert row
        	droid_id = db.insert(TABLE_DROID, null, values);
        	
        }
        Log.d("DB HELPER", "droid_id"+ droid_id);
		return droid_id;
    }
   
    /**
     * getting all droids under single tag
     * */
    public List<Image> getAllDroids() {
        List<Image> droids = new ArrayList<Image>();
 
        String selectQuery = "SELECT  * FROM " + TABLE_DROID;
        
        Log.e(LOG, selectQuery);
 
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Image image = new Image();
                image.setBitmapId(c.getInt(c.getColumnIndex(KEY_BITMAP_ID)));
                image.setX((c.getInt(c.getColumnIndex(KEY_X))));
                image.setY((c.getInt(c.getColumnIndex(KEY_Y))));
              
                image.setColor((c.getString(c.getColumnIndex(KEY_COLOR))));
               
               droids.add(image);
            } while (c.moveToNext());
        }
 
        return droids;
    
    }
    /* Adding maps to table */
    public long saveMap(int pos, int col, String item1) {
        SQLiteDatabase db = this.getWritableDatabase();
        long map_id;
        String whereClause = KEY_POSITION_M + "= ?" +  " AND " + KEY_COLOR_M + "= ?"; 
        
        String[] whereArgs = new String[]{  Integer.toString(pos), Integer.toString(col) };  
        
        String selQuery = "SELECT * FROM " + TABLE_MAP +" WHERE " + KEY_POSITION_M+ "=" + pos + " AND " + KEY_COLOR_M + "=" + col;
        //int id = img.getBitmapId();
//        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selQuery, null);
        
//        if (exits ) update that object
        //else
        if(c.getCount() == 1){
        	c.moveToFirst();
        	
        	ContentValues values = new ContentValues();
        	values.put(KEY_POSITION_M, pos);
            values.put(KEY_COLOR_M, col);
            values.put(KEY_GESTURE_M,item1);
        	map_id =  db.update(TABLE_MAP, values, whereClause, whereArgs);
        	
        }
        else{
        ContentValues values = new ContentValues();
        values.put(KEY_POSITION_M, pos);
        values.put(KEY_COLOR_M, col);
        values.put(KEY_GESTURE_M,item1);
        
     
        // insert row
       map_id = db.insert(TABLE_MAP, null, values);
      //  System.out.println("pat_id: "+ patient_id);
       
        }
        System.out.println("map_id: "+map_id);
        return map_id;
    }
    
    
    // closing database
    public void closeDb() {
    	SQLiteDatabase db = this.getReadableDatabase();
    	if (db != null && db.isOpen())
    		db.close();
    }

	



	
	
}
