package edu.westga.stephenkendrick.stickmanpunchingbag;

import edu.westga.stephenkendrick.stickmanpunchingbag.appearance.MainMenuActivityThemeChanger;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;


/**
 * Main Menu Activity
 * @author stephenkendrick
 */
public class MainMenuActivity extends Activity {

	private static final String LOG_TAG = "MainMenuActivity";

	private MainMenuActivityThemeChanger data; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(LOG_TAG, "onCreate");

		super.onCreate(savedInstanceState);
		
		setTheme(R.style.darkTheme);
		setContentView(R.layout.activity_main_menu);

		this.data = new MainMenuActivityThemeChanger(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		Log.i(LOG_TAG, "onCreateOptionsMenu");
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
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

		this.data.changeThemeToLight();
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

		this.data.changeThemeToPink();
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

		this.data.changeThemeToDark();
	}


}
