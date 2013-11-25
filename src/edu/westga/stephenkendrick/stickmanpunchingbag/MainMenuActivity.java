package edu.westga.stephenkendrick.stickmanpunchingbag;

import edu.westga.stephenkendrick.stickmanpunchingbag.appearance.MainMenuActivityThemeChanger;
import edu.westga.stephenkendrick.stickmanpunchingbag.settings.SettingsActivity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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
		menu.add(Menu.NONE, 0, 0, "Settings");
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                startActivityForResult(new Intent(this, SettingsActivity.class), 0);
                return true;
        }
        return false;
    }

	private void loadPreferences() {
		Log.i(LOG_TAG, "loadPreferences");
		
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		String themeString = preferences.getString("theme_scheme", MainMenuActivityThemeChanger.DARK_THEME);
		
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
		
		startActivityForResult(new Intent(this, TimeTrialActivity.class), 0);
		
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

		startActivityForResult(new Intent(this, HighScoresActivity.class), 0);
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
		
		startActivityForResult(new Intent(this, SettingsActivity.class), 0);

	}
	
	@Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		this.loadPreferences();
	 }

}
