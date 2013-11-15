package edu.westga.stephenkendrick.stickmanpunchingbag;

import edu.westga.stephenkendrick.stickmanpunchingbag.appearance.MainMenuActivityThemeChanger;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Menu;
import android.view.View;

/**
 * Main Menu Activity
 * 
 * @author stephenkendrick
 */
public class MainMenuActivity extends Activity {

	private static final String LOG_TAG = "MainMenuActivity";

	private MainMenuActivityThemeChanger theme;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(LOG_TAG, "onCreate");

		super.onCreate(savedInstanceState);

		this.theme = new MainMenuActivityThemeChanger(this);
		this.loadPreferences();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		Log.i(LOG_TAG, "onCreateOptionsMenu");
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	private void savePreferences() {
		Log.i(LOG_TAG, "savePreferences");
		
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		
		Editor editor = preferences.edit();
		editor.putString(MainMenuActivityThemeChanger.THEME, this.theme.getCurrentTheme());
		editor.commit();
	}

	private void loadPreferences() {
		Log.i(LOG_TAG, "loadPreferences");
		
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		String themeString = preferences.getString(MainMenuActivityThemeChanger.THEME, MainMenuActivityThemeChanger.DARK_THEME);
		
		if (themeString.equalsIgnoreCase(MainMenuActivityThemeChanger.PINK_THEME)) {
			
			this.theme.changeThemeToPink();
			
		} else if (themeString.equalsIgnoreCase(MainMenuActivityThemeChanger.LIGHT_THEME)) {
			
			this.theme.changeThemeToLight();
			
		} else {
			
			this.theme.changeThemeToDark();
			
		}
	}

	/**
	 * Handles the Time Trial Button Click
	 * <p>
	 * Precondition: none
	 * 
	 * Postcondition: none
	 * 
	 * @param view
	 */
	public void onTimeTrialButtonClick(View view) {
		Log.i(LOG_TAG, "onTimeTrialButtonClick");

		this.theme.changeThemeToLight();
		this.savePreferences();
		this.finish();
	}

	/**
	 * Handles the High Scores button click
	 * <p>
	 * Precondition: none
	 * 
	 * Postcondition: none
	 * 
	 * @param view
	 */
	public void onHighScoresButtonClick(View view) {
		Log.i(LOG_TAG, "onHighScoresButtonClick");

		this.theme.changeThemeToPink();
		this.savePreferences();
		this.finish();
	}

	/**
	 * Handles the Settings button click
	 * <p>
	 * Precondition: none
	 * 
	 * Postcondition: none
	 * 
	 * @param view
	 */
	public void onSettingsButtonClick(View view) {
		Log.i(LOG_TAG, "onSettingsButtonClick");

		this.theme.changeThemeToDark();
		this.savePreferences();
		this.finish();
	}

}
