package edu.westga.stephenkendrick.stickmanpunchingbag;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainMenuActivity extends Activity {

	private static final String LOG_TAG = "MainMenuActivity";

	private Button timeTrialButton;
	private Button highScoresButton;
	private Button settingsButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(LOG_TAG, "onCreate");

		super.onCreate(savedInstanceState);
		setTheme(R.style.darkTheme);
		setContentView(R.layout.activity_main_menu);

		this.setButtonVariables();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		Log.i(LOG_TAG, "onCreateOptionsMenu");
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	private void setButtonVariables() {
		this.timeTrialButton = (Button) findViewById(R.id.timeTrialButton);
		this.highScoresButton = (Button) findViewById(R.id.highScoresButton);
		this.settingsButton = (Button) findViewById(R.id.settingsButton);
	}

	public void onTimeTrialButtonClick(View view) {
		Log.i(LOG_TAG, "onTimeTrialButtonClick");

		changeThemeToLight();
	}

	public void onHighScoresButtonClick(View view) {
		Log.i(LOG_TAG, "onTimeTrialButtonClick");

		changeThemeToPink();
	}

	public void onSettingsButtonClick(View view) {
		Log.i(LOG_TAG, "onTimeTrialButtonClick");

		changeThemeToDark();
	}

	private void changeThemeToLight() {
		Log.i(LOG_TAG, "changeThemeToLight");

		setTheme(R.style.lightTheme);
		setContentView(R.layout.activity_main_menu);
		this.setButtonVariables();

		this.timeTrialButton
				.setBackgroundResource(R.drawable.stickman_punchingbag_button_light);
		this.highScoresButton
				.setBackgroundResource(R.drawable.stickman_punchingbag_button_light);
		this.settingsButton
				.setBackgroundResource(R.drawable.stickman_punchingbag_button_light);

		Toast.makeText(this, "Change Theme To Light", Toast.LENGTH_LONG).show();
	}

	private void changeThemeToPink() {
		Log.i(LOG_TAG, "changeThemeToPink");

		setTheme(R.style.pinkTheme);
		setContentView(R.layout.activity_main_menu);
		this.setButtonVariables();

		this.timeTrialButton
				.setBackgroundResource(R.drawable.stickman_punchingbag_button_pink);
		this.highScoresButton
				.setBackgroundResource(R.drawable.stickman_punchingbag_button_pink);
		this.settingsButton
				.setBackgroundResource(R.drawable.stickman_punchingbag_button_pink);

		Toast.makeText(this, "Change Theme To Pink", Toast.LENGTH_LONG).show();
	}

	private void changeThemeToDark() {
		Log.i(LOG_TAG, "changeThemeToDark");

		setTheme(R.style.darkTheme);
		setContentView(R.layout.activity_main_menu);
		this.setButtonVariables();

		this.timeTrialButton
				.setBackgroundResource(R.drawable.stickman_punchingbag_button_dark);
		this.highScoresButton
				.setBackgroundResource(R.drawable.stickman_punchingbag_button_dark);
		this.settingsButton
				.setBackgroundResource(R.drawable.stickman_punchingbag_button_dark);

		Toast.makeText(this, "Change Theme To Dark", Toast.LENGTH_LONG).show();
	}

}
