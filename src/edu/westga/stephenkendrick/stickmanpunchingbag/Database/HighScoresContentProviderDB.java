package edu.westga.stephenkendrick.stickmanpunchingbag.Database;

import java.util.Arrays;
import java.util.HashSet;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

/**
 * Content Provider for the High Scores db
 * 
 * @author stephenkendrick
 *
 */
public class HighScoresContentProviderDB extends ContentProvider{

private static final String LOG_TAG = "HighScoresContentProviderDB";
	
	private static final int ALL_HIGH_SCORES = 1;
	private static final int HIGH_SCORES_ID = 2;

	private static final String AUTHORITY = "edu.westga.stephenkendrick.stickmanpunchingbag.Database";
	private static final String BASE_PATH = "highscores";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
			+ "/" + BASE_PATH);

	private static final UriMatcher sURIMatcher = new UriMatcher(
			UriMatcher.NO_MATCH);

	static {
		sURIMatcher.addURI(AUTHORITY, BASE_PATH, ALL_HIGH_SCORES);
		sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", HIGH_SCORES_ID);
	}

	private HighScoresDBHelper dbHelper;

	@Override
	public boolean onCreate() {
		Log.i(LOG_TAG, "onCreate");
		this.dbHelper = new HighScoresDBHelper(getContext());
		return true;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		Log.i(LOG_TAG, "insert");
		int uriType = sURIMatcher.match(uri);
		SQLiteDatabase sqlDB = this.dbHelper.getWritableDatabase();
		long id = 0; 
		
		switch (uriType) { 
			case ALL_HIGH_SCORES:
				id = sqlDB.insert(HighScoresContract.HighScores.HIGH_SCORES_TABLE_NAME, null, values);
				break; 
			default:
				throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		
		// Notify potential listeners
		getContext().getContentResolver().notifyChange(uri, null); 
		
		return Uri.parse(BASE_PATH + "/" + id);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		Log.i(LOG_TAG, "delete");
		int uriType = sURIMatcher.match(uri);
		SQLiteDatabase sqlDB = this.dbHelper.getWritableDatabase();
		
		int rowsDeleted = 0;
		
		switch (uriType) {
			case ALL_HIGH_SCORES:
				rowsDeleted = sqlDB.delete(HighScoresContract.HighScores.HIGH_SCORES_TABLE_NAME, 
						selection, selectionArgs);
				break;
			
			case HIGH_SCORES_ID:
				String id = uri.getLastPathSegment();
				
				if (TextUtils.isEmpty(selection)) {
					rowsDeleted = sqlDB.delete(HighScoresContract.HighScores.HIGH_SCORES_TABLE_NAME, 
							HighScoresContract.HighScores.ID + "=" + id , null);
				} else {
					rowsDeleted = sqlDB.delete(HighScoresContract.HighScores.HIGH_SCORES_TABLE_NAME, 
							HighScoresContract.HighScores.ID + "=" + id + " and " + selection, 
							selectionArgs);
				}
				break;
				
			default:
				throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		
		//Notify potential Listeners
		getContext().getContentResolver().notifyChange(uri, null);
		
		return rowsDeleted;
	}

	@Override
	public String getType(Uri uri) {
		Log.i(LOG_TAG, "getType");
		return null;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		Log.i(LOG_TAG, "query");
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		
		this.checkColumns(projection);
		
		queryBuilder.setTables(HighScoresContract.HighScores.HIGH_SCORES_TABLE_NAME);
		
		int uriType = sURIMatcher.match(uri);
		
		switch (uriType) {
			case ALL_HIGH_SCORES:
				break;
			case HIGH_SCORES_ID:
				queryBuilder.appendWhere(HighScoresContract.HighScores.ID + "=" + uri.getLastPathSegment());
				break;
			default:
				throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		
		SQLiteDatabase db = this.dbHelper.getReadableDatabase();
		Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
		
		//Notify potential listeners
		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		
		Log.i(LOG_TAG, "update");
		int uriType = sURIMatcher.match(uri);
		SQLiteDatabase sqlDB = this.dbHelper.getWritableDatabase();
		
		int rowsUpdated = 0;
		switch (uriType) {
			case ALL_HIGH_SCORES:
				rowsUpdated = sqlDB.update(HighScoresContract.HighScores.HIGH_SCORES_TABLE_NAME, values, 
						selection, selectionArgs);
				break;
			
			case HIGH_SCORES_ID:
				String id = uri.getLastPathSegment();
				
				if(TextUtils.isEmpty(selection)) {
					
					rowsUpdated = sqlDB.update(HighScoresContract.HighScores.HIGH_SCORES_TABLE_NAME, values, 
							HighScoresContract.HighScores.ID + "=" + id, null);
				
				} else {
					rowsUpdated = sqlDB.update(HighScoresContract.HighScores.HIGH_SCORES_TABLE_NAME, values, 
							HighScoresContract.HighScores.ID + "=" + id + " and " + selection, selectionArgs);
				}
				break;
			
			default: 
				throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		
		//Notify potential listeners
		getContext().getContentResolver().notifyChange(uri, null);
		
		return rowsUpdated;
	}
	
	private void checkColumns(String[] projection) {
		
		Log.i(LOG_TAG, "checkColumns");
		
		String[] available = { HighScoresContract.HighScores.ID, 
				HighScoresContract.HighScores.PLAYER_NAME, HighScoresContract.HighScores.NUMBER_OF_TAPS};
		
		if(projection != null) {
			
			HashSet<String> requestedColumns = new HashSet<String>(Arrays.asList(projection));
			HashSet<String> availableColumns = new HashSet<String>(Arrays.asList(available));
			
			if (!availableColumns.containsAll(requestedColumns)) { 
				throw new IllegalArgumentException("Unknown columns in projection");
			}
		             
		}
	}
	
}
