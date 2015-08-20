package net.rushlakeconsulting.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{

	public DatabaseHelper(Context context)
	{
		super(context, "WordPower.db", null, 1);
	}
	
	public void onCreate(SQLiteDatabase db)
	{
		String ssql = "create table Words (WordId integer primary key autoincrement, Word text);"; 
				
		db.execSQL(ssql);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
			
}
