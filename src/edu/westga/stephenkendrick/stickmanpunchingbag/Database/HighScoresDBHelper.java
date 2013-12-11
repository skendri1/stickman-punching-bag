/**
 * 
 */
package edu.westga.stephenkendrick.stickmanpunchingbag.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import edu.westga.stephenkendrick.stickmanpunchingbag.Database.HighScoresContract.HighScores;

/**
 * DB helper for connecting to the high scores database
 * 
 * @author stephenkendrick
 *
 */
public class HighScoresDBHelper extends SQLiteOpenHelper{

	private static final String LOG_TAG = "HighScoresDBHelper";

	private static final String DATABASE_NAME = "students.db";
	private static final int DATABASE_VERSION = 1;

	private static final String COMMA_SEP = ", ";
	private static final String TEXT_TYPE = " TEXT";
	private static final String INTEGER_TYPE = " INTEGER";
	
	private static final String SQL_CREATE_DATABASE = "CREATE TABLE "
			+ HighScores.HIGH_SCORES_TABLE_NAME + " (" + HighScores.ID
			+ INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP
			+ HighScores.PLAYER_NAME + TEXT_TYPE + COMMA_SEP 
			+ HighScores.NUMBER_OF_TAPS + INTEGER_TYPE  + ");";

	/**
	 * Creates the high scores db helper class
	 * <p>
	 * Precondition: none Postcondition: none
	 * 
	 * @param context
	 */
	public HighScoresDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		Log.i(LOG_TAG, "Constructor");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i(LOG_TAG, "onCreate");
		db.execSQL(SQL_CREATE_DATABASE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i(LOG_TAG, "onUpgrade");

	}
	
}
