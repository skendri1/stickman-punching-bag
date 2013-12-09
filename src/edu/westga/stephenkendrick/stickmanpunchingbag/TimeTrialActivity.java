package edu.westga.stephenkendrick.stickmanpunchingbag;

import java.util.Observable;
import java.util.Observer;

import edu.westga.stephenkendrick.stickmanpunchingbag.Controllers.TimeTrialGameController;
import edu.westga.stephenkendrick.stickmanpunchingbag.Database.HighScoresContentProviderDB;
import edu.westga.stephenkendrick.stickmanpunchingbag.Database.HighScoresContract.HighScores;
import edu.westga.stephenkendrick.stickmanpunchingbag.appearance.MainMenuActivityThemeChanger;

import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class TimeTrialActivity extends Activity implements Observer {

	private static final String LOG_TAG = "TimerTrialActivity";

	public static final String GAME_MODE_FREE = "FreeMode";
	public static final String GAME_MODE_RLR = "RLR";
	public static final String GAME_MODE_LRL = "LRL";

	private Bundle savedInstanceState;

	private AnimationDrawable animation;
	private ImageView animationImageView;

	private Button pauseButton;

	private TextView numberOfPunchesCounterTextView;
	private TextView timerTextView;

	private TimeTrialGameController gameController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(LOG_TAG, "onCreate");

		super.onCreate(savedInstanceState);

		this.savedInstanceState = savedInstanceState;

		this.setTheme();
		this.setUpAnimations();
		this.setUpTextViewsAndButtons();
		this.createGameController();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Log.i(LOG_TAG, "onCreateOptionsMenu");

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.time_trial, menu);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Log.i(LOG_TAG, "onDestroy");
		super.onDestroy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		Log.i(LOG_TAG, "onPause");
		this.gameController.pauseTimer();
		super.onPause();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onRestart()
	 */
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		Log.i(LOG_TAG, "onRestart");
		super.onRestart();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		Log.i(LOG_TAG, "onResume");
		super.onResume();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onStart()
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		Log.i(LOG_TAG, "onStart");
		super.onStart();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onStop()
	 */
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		Log.i(LOG_TAG, "onStop");
		super.onStop();
	}

	/**
	 * Creates the game controller for the game
	 */
	public void createGameController() {
		Log.i(LOG_TAG, "createGameController");

		this.gameController = new TimeTrialGameController(
				this.savedInstanceState, this.timerTextView,
				this.numberOfPunchesCounterTextView);
		this.gameController.addObserver(this);
	}

	private void setTheme() {
		Log.i(LOG_TAG, "setTheme");

		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		String themeString = preferences.getString("theme_scheme",
				MainMenuActivityThemeChanger.DARK_THEME);

		if (themeString
				.equalsIgnoreCase(MainMenuActivityThemeChanger.PINK_THEME)) {

			this.setTheme(R.style.pinkTheme);
			setContentView(R.layout.activity_time_trial);

		} else if (themeString
				.equalsIgnoreCase(MainMenuActivityThemeChanger.LIGHT_THEME)) {

			this.setTheme(R.style.lightTheme);
			setContentView(R.layout.activity_time_trial);

		} else {

			this.setTheme(R.style.darkTheme);
			setContentView(R.layout.activity_time_trial);

		}
	}

	private void setUpTextViewsAndButtons() {
		Log.i(LOG_TAG, "setUpTextViewsAndButtons");

		this.pauseButton = (Button) findViewById(R.id.pauseButton);

		this.numberOfPunchesCounterTextView = (TextView) findViewById(R.id.numberOfPunchesCounterTextView);
		this.timerTextView = (TextView) findViewById(R.id.timerTextView);

	}

	private void setUpAnimations() {

		Log.i(LOG_TAG, "setUpAnimations");

		this.animationImageView = (ImageView) findViewById(R.id.animationImageView);

		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		String themeString = preferences.getString("theme_scheme",
				MainMenuActivityThemeChanger.DARK_THEME);

		if (themeString
				.equalsIgnoreCase(MainMenuActivityThemeChanger.PINK_THEME)) {

			this.animationImageView
					.setBackgroundResource(R.drawable.animation_pink_right_images);

		} else if (themeString
				.equalsIgnoreCase(MainMenuActivityThemeChanger.LIGHT_THEME)) {

			this.animationImageView
					.setBackgroundResource(R.drawable.animation_light_right_images);

		} else {

			this.animationImageView
					.setBackgroundResource(R.drawable.animation_dark_right_images);

		}

		this.animation = (AnimationDrawable) this.animationImageView
				.getBackground();

	}

	/**
	 * Handles the punch or punching bag click
	 * <p>
	 * Precondition: none
	 * 
	 * Postcondition: none
	 * 
	 * @param view
	 */
	public void onPunchButtonOrPunchingBagClick(View view) {
		Log.i(LOG_TAG, "onPunchButtonOrPunchingBagClick");

		if (!this.gameController.isTimerStarted()) {
			this.gameController.startTimer();
		}

		this.gameController.addOnePunchToPunchCounter();

		if (this.animation.isRunning()) {
			this.animation.stop();
		} else {
			this.animation.stop();
			this.animation.start();
		}

	}

	/**
	 * Handles the pause button click
	 * 
	 * @param view
	 */
	public void onPauseButtonClick(View view) {
		Log.i(LOG_TAG, "onPauseButtonClick");

		this.gameController.pauseTimer();

		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

				gameController.startTimer();
			}
		};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		LayoutInflater inflater = this.getLayoutInflater();
		
		builder.setView(inflater.inflate(R.layout.dialog_paused_game, null))
				.setPositiveButton("OK", dialogClickListener);
		
		builder.setCancelable(false);
		
		builder.show();
	}

	/**
	 * This will handle when the game is quit or timer runs out.
	 */
	@Override
	public void update(Observable observable, Object data) {
		Log.i(LOG_TAG, "update");

		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

				Dialog inputDialog = (Dialog) dialog;
				EditText input = (EditText) inputDialog
						.findViewById(R.id.playerNameEditText);

				addHighScore(input.getText().toString(),
						gameController.getNumberOfPunches());
				finish();
			}
		};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		LayoutInflater inflater = this.getLayoutInflater();
		
		builder.setView(inflater.inflate(R.layout.dialog_get_player_name, null))
				.setPositiveButton("OK", dialogClickListener);
		
		builder.setCancelable(false);
		
		builder.show();

	}

	private void addHighScore(String playerName, int numberOfPunches) {
		ContentValues values = new ContentValues();
		values.put(HighScores.PLAYER_NAME, playerName);
		values.put(HighScores.NUMBER_OF_TAPS, numberOfPunches);

		getContentResolver().insert(HighScoresContentProviderDB.CONTENT_URI,
				values);
	}

}
