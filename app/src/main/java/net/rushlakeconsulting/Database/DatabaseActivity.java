package net.rushlakeconsulting.Database;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class DatabaseActivity extends Activity implements View.OnClickListener {
    /** Called when the activity is first created. */
	
	DatabaseHelper helper;
	SQLiteDatabase db;
	private static final String TAG = "myDebug";
	Button btnGetRecords;
	Button btnDeleteRecords;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btnGetRecords = (Button)findViewById(R.id.btnGetRecords);
        btnGetRecords.setOnClickListener(this);
        
        btnDeleteRecords = (Button)findViewById(R.id.btnDeleteRecords);
        btnDeleteRecords.setOnClickListener(this);
        
        
        // Create a database
        helper = new DatabaseHelper(this.getApplicationContext());
        db = helper.getWritableDatabase();
        
        //add a record
        ContentValues cv = new ContentValues();
        cv.put("Word", "superfluous");
        db.insert("Words", null, cv);
        
        //add another record
        cv.put("Word", "awesome");
        db.insert("Words", null, cv);
        
        // Execute query and get first record 
        
        Cursor result = db.rawQuery("select * from words",null);
        
        result.moveToFirst();
        int id = result.getInt(0);
        String sWord = result.getString(1);
       
        Log.println(Log.DEBUG, TAG, sWord);
        result.close();
        
    }
    
    public void onClick(View view)
    {
    	int iBtn = view.getId();
    	
    	if (iBtn == R.id.btnDeleteRecords) 
    	{
    		DeleteWords();
    	}
    	else
    	{
    		ShowWords();	
    	}
    		
    }
    
    private void DeleteWords()
    {
    	Cursor result = db.rawQuery("delete from words;",null);

    	// startManagingCursor(result);

    	result.close();  //Comment
    	Log.println(Log.DEBUG, TAG, "DeleteAll");
        result.close();
    }
    
    private void ShowWords()
    {
    	Cursor cursor = db.query("Words",null, null, null, null, null, null);
    	//startManagingCursor(cursor);

    	while (cursor.moveToNext())
    	{
    		long id = cursor.getLong(0);
    		String word = cursor.getString(1);
    		Log.println(Log.DEBUG, TAG, word + " (" + id + ")");
    	        
    	}
        cursor.close();
    }
    
    @Override
    public void onDestroy()
    {
    	helper.close();
    }
}

