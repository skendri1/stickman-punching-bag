package edu.westga.stephenkendrick.stickmanpunchingbag.Database;

import java.sql.SQLException;

import edu.westga.stephenkendrick.stickmanpunchingbag.Database.HighScoresContract.HighScores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class HighScoresDBAdapter {

private static final String LOG_TAG = "HighScoresDBAdapter";
	
	private HighScoresDBHelper databaseHelper = null; 
	private SQLiteDatabase theDB = null; 
	private Context context = null; 

	//private int currentLowestScore;
	
	/**
	 * Creates the HighScoresDBAdapter class with the given context
	 * <p>
	 * Precondition: none
	 * 
	 * Postcondition: none
	 * @param context
	 */
	public HighScoresDBAdapter(Context context) {
		Log.i(LOG_TAG, "Constructor");
		this.context = context;
	}
	
	/**
	 * Opens the database
	 * <p>
	 * Precondition: none
	 * 
	 * Postcondition: none
	 */
	public HighScoresDBAdapter open() throws SQLException{
		Log.i(LOG_TAG, "open");
		
		this.databaseHelper = new HighScoresDBHelper(this.context);
		this.theDB = this.databaseHelper.getWritableDatabase();
		return this;
	}
	
	/**
	 * Closes the DB and DBHelper 
	 * <p>
	 * Precondition: none
	 * 
	 * Postcondition: none
	 */
	public void close() {
		Log.i(LOG_TAG, "close");
		if ( this.theDB == null ) {
			throw new IllegalStateException("The DB does not exist");
		}
		if ( this.databaseHelper == null) {
			throw new IllegalStateException("The dbHelper does not exist");
		}
		
		this.theDB.close();
		this.databaseHelper.close();
		
	}
	
	/**
	 * Inserts the given info into the DB and returns the id to the info
	 * <p>
	 * Precondition: none
	 * 
	 * @param playerName
	 * @param time
	 * 
	 * @return the id of the inserted high score
	 */
	public long insertHighScore(String playerName, int numberOfTaps) {
		Log.i(LOG_TAG, "insertHighScore");
		
		ContentValues initialValues = new ContentValues(); 
		
		initialValues.put(HighScores.PLAYER_NAME, playerName); 
		initialValues.put(HighScores.NUMBER_OF_TAPS, numberOfTaps); 
		 
		return theDB.insert(HighScores.HIGH_SCORES_TABLE_NAME, null, initialValues); 

	}
	
	/**
	 * Returns true if the high score with the given ID is deleted
	 * <p>
	 * Precondition: none
	 * 
	 * @return true if the high score with the given ID is deleted, false otherwise
	 */
	public boolean deleteHighScore(long highScoreID) {
		Log.i(LOG_TAG, "deleteHighScore");
		
		return this.theDB.delete(HighScores.HIGH_SCORES_TABLE_NAME, HighScores.ID + "=" + highScoreID , null) > 0;
	}
	
	/**
	 * Returns true if all high scores were deleted
	 * <p>
	 * Precondition: none
	 * 
	 * @return true if all high scores are deleted, false otherwise
	 */
	public boolean deleteAllHighScores() {
		Log.i(LOG_TAG, "deleteAllHighScores");
		return this.theDB.delete(HighScores.HIGH_SCORES_TABLE_NAME, null, null) > 0;
	}
	
	/**
	 * Returns the high score that corresponds to the given ID
	 * <p>
	 * Precondition: none
	 * 
	 * @param highScoreID
	 * 
	 * @return the high score that corresponds to the given ID
	 */
	public Cursor fetchHighScore(long highScoreID) {
		Log.i(LOG_TAG, "fetchHighScore");
		
		String[] highScoreColumns = new String[] { HighScores.ID, HighScores.PLAYER_NAME, HighScores.NUMBER_OF_TAPS};
		Cursor cursor = this.theDB.query(true, HighScores.HIGH_SCORES_TABLE_NAME, highScoreColumns, HighScores.ID + "=" + highScoreID, null, null, null, null, null);
		
		if ( cursor != null) {
			cursor.moveToFirst();
		}
		
		return cursor;
				  

	}
	
	/**
	 * Returns a Cursor for all high scores in the database
	 * <p>
	 * Precondition: none
	 * 
	 * Postcondition: none
	 */
	public Cursor fetchAllHighScores() {
		Log.i(LOG_TAG, "fetchAllHighScores");
		
		String[] highScoreColumns = new String[] { HighScores.ID, HighScores.PLAYER_NAME, HighScores.NUMBER_OF_TAPS };
		return this.theDB.query(HighScores.HIGH_SCORES_TABLE_NAME, highScoreColumns, null, null, null, null, HighScores.NUMBER_OF_TAPS+" DESC");

	}
	
	/**
	 * Updates the high score with the given ID with the given info
	 * <p>
	 * Precondition: none
	 * 
	 * @return true if the high score was updated, false otherwise
	 */
	public boolean updateHighScore(long highScoreID, String playerName, String numberOfTaps) {
		Log.i(LOG_TAG, "updateHighScore");
		
		ContentValues values = new ContentValues();
		values.put(HighScores.PLAYER_NAME, playerName);
		values.put(HighScores.NUMBER_OF_TAPS, numberOfTaps);
		
		return this.theDB.update(HighScores.HIGH_SCORES_TABLE_NAME, values, HighScores.ID + "=" + highScoreID, null) > 0;

	}
	
}
