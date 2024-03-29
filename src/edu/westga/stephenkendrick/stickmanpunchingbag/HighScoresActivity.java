package edu.westga.stephenkendrick.stickmanpunchingbag;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.westga.stephenkendrick.stickmanpunchingbag.Database.HighScoresContentProviderDB;
import edu.westga.stephenkendrick.stickmanpunchingbag.Database.HighScoresContract.HighScores;
import edu.westga.stephenkendrick.stickmanpunchingbag.Database.HighScoresDBAdapter;
import edu.westga.stephenkendrick.stickmanpunchingbag.appearance.MainMenuActivityThemeChanger;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Class that handles the High Scores Activity of the Application
 * 
 * @author stephenkendrick
 * 
 */
public class HighScoresActivity extends Activity implements
		LoaderCallbacks<Cursor> {

	private static final String LOG_TAG = "HighScoresActivity";

	private SimpleCursorAdapter adapter;
	private static final String[] HIGHSCORES_PROJECTION = new String[] {
			HighScores.ID, HighScores.PLAYER_NAME, HighScores.NUMBER_OF_TAPS };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(LOG_TAG, "onCreate");

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_high_scores);

		this.loadPreferences();
		this.setupListAdapter();
	}

	private void loadPreferences() {
		Log.i(LOG_TAG, "loadPreferences");

		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		String themeString = preferences.getString("theme_scheme",
				MainMenuActivityThemeChanger.DARK_THEME);

		if (themeString
				.equalsIgnoreCase(MainMenuActivityThemeChanger.PINK_THEME)) {

			changeThemeToPink();

		} else if (themeString
				.equalsIgnoreCase(MainMenuActivityThemeChanger.LIGHT_THEME)) {

			changeThemeToLight();

		} else {

			changeThemeToDark();

		}
	}

	private void changeThemeToDark() {
		Log.i(LOG_TAG, "changeThemeToDark");
		
		Button mainMenuButton;
		this.setTheme(R.style.darkTheme);
		this.setContentView(R.layout.activity_high_scores);

		View someView = findViewById(R.id.highScoresMainLayout);

		View root = someView.getRootView();

		root.setBackgroundColor(getResources().getColor(
				android.R.color.black));

		mainMenuButton = (Button) findViewById(R.id.mainMenuButton);

		mainMenuButton
				.setBackgroundResource(R.drawable.stickman_punchingbag_button_dark);

		this.convertTextViewTextColor(Color.WHITE);
	}

	private void changeThemeToLight() {
		Log.i(LOG_TAG, "changeThemToLight");
		
		Button mainMenuButton;
		this.setTheme(R.style.lightTheme);
		this.setContentView(R.layout.activity_high_scores);

		View someView = findViewById(R.id.highScoresMainLayout);

		View root = someView.getRootView();

		root.setBackgroundColor(getResources().getColor(
				android.R.color.white));

		mainMenuButton = (Button) findViewById(R.id.mainMenuButton);

		mainMenuButton
				.setBackgroundResource(R.drawable.stickman_punchingbag_button_light);

		this.convertTextViewTextColor(Color.BLACK);
	}

	private void changeThemeToPink() {
		Log.i(LOG_TAG, "changeThemeToPink");
		
		Button mainMenuButton;
		this.setTheme(R.style.pinkTheme);
		this.setContentView(R.layout.activity_high_scores);

		View someView = findViewById(R.id.highScoresMainLayout);

		View root = someView.getRootView();

		root.setBackgroundColor(getResources().getColor(
				android.R.color.white));

		mainMenuButton = (Button) findViewById(R.id.mainMenuButton);

		mainMenuButton
				.setBackgroundResource(R.drawable.stickman_punchingbag_button_pink);

		this.convertTextViewTextColor(Color.BLACK);
	}

	private void convertTextViewTextColor(int color) {
		Log.i(LOG_TAG, "convertTextViewTextColor");

		TextView highScoresTitleTextView = (TextView) findViewById(R.id.highScoresTitleTextView);

		TextView nameColTextView = (TextView) findViewById(R.id.nameColTextView);
		TextView numberOfPunchesColTextView = (TextView) findViewById(R.id.numberOfPunchesColTextView);

		highScoresTitleTextView.setTextColor(color);

		nameColTextView.setTextColor(color);
		numberOfPunchesColTextView.setTextColor(color);

		TextView nameTextView = (TextView) findViewById(R.id.playerNameTextView1);
		TextView numberOfPunchesTextView = (TextView) findViewById(R.id.numberOfPunchesTextView1);

		nameTextView.setTextColor(color);
		numberOfPunchesTextView.setTextColor(color);

		nameTextView = (TextView) findViewById(R.id.playerNameTextView2);
		numberOfPunchesTextView = (TextView) findViewById(R.id.numberOfPunchesTextView2);

		nameTextView.setTextColor(color);
		numberOfPunchesTextView.setTextColor(color);

		nameTextView = (TextView) findViewById(R.id.playerNameTextView3);
		numberOfPunchesTextView = (TextView) findViewById(R.id.numberOfPunchesTextView3);

		nameTextView.setTextColor(color);
		numberOfPunchesTextView.setTextColor(color);

		nameTextView = (TextView) findViewById(R.id.playerNameTextView4);
		numberOfPunchesTextView = (TextView) findViewById(R.id.numberOfPunchesTextView4);

		nameTextView.setTextColor(color);
		numberOfPunchesTextView.setTextColor(color);

		nameTextView = (TextView) findViewById(R.id.playerNameTextView5);
		numberOfPunchesTextView = (TextView) findViewById(R.id.numberOfPunchesTextView5);

		nameTextView.setTextColor(color);
		numberOfPunchesTextView.setTextColor(color);

	}

	private void setupListAdapter() {

		Log.i(LOG_TAG, "setuplistAdapter");

		HighScoresDBAdapter dbAdapter = new HighScoresDBAdapter(this);
		Cursor cursor = null;
		try {
			dbAdapter.open();

			cursor = dbAdapter.fetchAllHighScores();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		cursor.moveToFirst();

		ArrayList<String> names = new ArrayList<String>();
		ArrayList<Integer> taps = new ArrayList<Integer>();

		while (!cursor.isAfterLast()) {
			names.add(cursor.getString(cursor
					.getColumnIndex(HighScores.PLAYER_NAME)));
			taps.add(cursor.getInt(cursor
					.getColumnIndex(HighScores.NUMBER_OF_TAPS)));
			cursor.moveToNext();
		}

		this.addTextToNamesTextViews(names);
		this.addTextToTapsTextViews(taps);

		if (cursor != null) {
			cursor.close();
		}

		dbAdapter.close();

	}

	private void addTextToNamesTextViews(ArrayList<String> names) {
		Log.i(LOG_TAG, "addTextToNamesTextViews");

		if (names == null || names.size() == 0) {
			return;
		}

		TextView nameTextView = (TextView) findViewById(R.id.playerNameTextView1);

		if (names.get(0) != null) {
			nameTextView.setText(names.get(0));
		}

		nameTextView = (TextView) findViewById(R.id.playerNameTextView2);

		if (names.size() >= 2 && names.get(1) != null) {
			nameTextView.setText(names.get(1));
		}

		nameTextView = (TextView) findViewById(R.id.playerNameTextView3);

		if (names.size() >= 3 && names.get(2) != null) {
			nameTextView.setText(names.get(2));
		}

		nameTextView = (TextView) findViewById(R.id.playerNameTextView4);

		if (names.size() >= 4 && names.get(3) != null) {
			nameTextView.setText(names.get(3));
		}

		nameTextView = (TextView) findViewById(R.id.playerNameTextView5);

		if (names.size() >= 5 && names.get(4) != null) {
			nameTextView.setText(names.get(4));
		}

	}

	private void addTextToTapsTextViews(ArrayList<Integer> taps) {
		Log.i(LOG_TAG, "addTextToTapsTextViews");

		if (taps == null || taps.size() == 0) {
			return;
		}

		TextView tapTextView = (TextView) findViewById(R.id.numberOfPunchesTextView1);

		if (taps.get(0) != null) {
			tapTextView.setText("" + taps.get(0));
		}

		tapTextView = (TextView) findViewById(R.id.numberOfPunchesTextView2);

		if (taps.size() >= 2 && taps.get(1) != null) {
			tapTextView.setText("" + taps.get(1));
		}

		tapTextView = (TextView) findViewById(R.id.numberOfPunchesTextView3);

		if (taps.size() >= 3 && taps.get(2) != null) {
			tapTextView.setText("" + taps.get(2));
		}

		tapTextView = (TextView) findViewById(R.id.numberOfPunchesTextView4);

		if (taps.size() >= 4 && taps.get(3) != null) {
			tapTextView.setText("" + taps.get(3));
		}

		tapTextView = (TextView) findViewById(R.id.numberOfPunchesTextView5);

		if (taps.size() >= 5 && taps.get(4) != null) {
			tapTextView.setText("" + taps.get(4));
		}

	}

	/**
	 * handles the main menu button click
	 * 
	 * @param view
	 */
	public void onMainMenuButtonClick(View view) {
		Log.i(LOG_TAG, "onMainMenuButtonClick");
		this.finish();
	}

	/**
	 * Handles the clear high scores button click
	 * <p>
	 * Precondition: none
	 * 
	 * @param view
	 */
	public void onClearButtonClick(View view) {
		Log.i(LOG_TAG, "onClearButtonClick");

		boolean allScoresDeleted = false;

		HighScoresDBAdapter dbAdapter = new HighScoresDBAdapter(this);
		try {
			dbAdapter.open();

			allScoresDeleted = dbAdapter.deleteAllHighScores();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		dbAdapter.close();

		if (allScoresDeleted) {
			finish();
		}
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		Log.i(LOG_TAG, "onCreateLoader");

		return new CursorLoader(HighScoresActivity.this,
				HighScoresContentProviderDB.CONTENT_URI, HIGHSCORES_PROJECTION,
				null, null, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		Log.i(LOG_TAG, "onCreateFinished");

		this.adapter.swapCursor(cursor);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		Log.i(LOG_TAG, "onLoaderReset");

		this.adapter.swapCursor(null);
	}

}
