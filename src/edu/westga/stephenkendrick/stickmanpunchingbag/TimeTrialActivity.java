package edu.westga.stephenkendrick.stickmanpunchingbag;

import java.util.Observable;
import java.util.Observer;

import edu.westga.stephenkendrick.stickmanpunchingbag.Controllers.TimeTrialGameController;
import edu.westga.stephenkendrick.stickmanpunchingbag.Database.HighScoresContentProviderDB;
import edu.westga.stephenkendrick.stickmanpunchingbag.Database.HighScoresContract.HighScores;
import edu.westga.stephenkendrick.stickmanpunchingbag.appearance.MainMenuActivityThemeChanger;

import android.os.Bundle;
import android.os.Handler;
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
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Handles the Time Trial Activity view (Main part of the game)
 * 
 * @author stephenkendrick
 *
 */
public class TimeTrialActivity extends Activity implements Observer {

	private static final String LOG_TAG = "TimerTrialActivity";
	
	private String punchMode;

	private String themeString;

	private AnimationDrawable animation;

	private ImageView animationImageView;

	private TextView numberOfPunchesCounterTextView;
	private TextView timerTextView;

	private TimeTrialGameController gameController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(LOG_TAG, "onCreate");

		super.onCreate(savedInstanceState);

		this.setTheme();

		this.setUpTextViewsAndButtons();
		
		this.createGameController();

		this.setUpAnimations();

	}

	@Override
	protected void onDestroy() {
		Log.i(LOG_TAG, "onDestroy");
		super.onDestroy();
		System.gc();
		
		
	}

	@Override
	protected void onPause() {
		Log.i(LOG_TAG, "onPause");
		this.gameController.pauseTimer();
		super.onPause();

	}

	@Override
	protected void onRestart() {
		Log.i(LOG_TAG, "onRestart");
		super.onRestart();
		this.gameController.startTimer();
	}

	@Override
	protected void onResume() {
		Log.i(LOG_TAG, "onResume");
		super.onResume();
	}

	/**
	 * Creates the game controller for the game
	 */
	private void createGameController() {
		Log.i(LOG_TAG, "createGameController");

		Bundle intent = this.getIntent().getExtras();
		
		this.punchMode = intent.getString("punchMode");
		
		this.gameController = new TimeTrialGameController( this.timerTextView,
				this.numberOfPunchesCounterTextView, this.punchMode);
		
		this.gameController.addObserver(this);
	}

	/**
	 * Sets the Theme of this activity
	 */
	private void setTheme() {
		Log.i(LOG_TAG, "setTheme");

		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		this.themeString = preferences.getString("theme_scheme",
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

		this.numberOfPunchesCounterTextView = (TextView) findViewById(R.id.numberOfPunchesCounterTextView);
		this.timerTextView = (TextView) findViewById(R.id.timerTextView);

	}

	private void setUpAnimations() {

		Log.i(LOG_TAG, "setUpAnimations");

		this.animationImageView = (ImageView) findViewById(R.id.animationImageView);

		this.RunLeftAnimation();
		this.RunRightAnimation();

	}

	private void RunLeftAnimation() {
		Log.i(LOG_TAG, "RunLeftAnimation");

		if (this.themeString
				.equalsIgnoreCase(MainMenuActivityThemeChanger.PINK_THEME)) {

			this.animationImageView
					.setBackgroundResource(R.drawable.animation_pink_left_images);

		} else if (this.themeString
				.equalsIgnoreCase(MainMenuActivityThemeChanger.LIGHT_THEME)) {

			this.animationImageView
					.setBackgroundResource(R.drawable.animation_light_left_images);

		} else {

			this.animationImageView
					.setBackgroundResource(R.drawable.animation_dark_left_images);
		}

		this.animation = (AnimationDrawable) this.animationImageView
				.getBackground();

		if (this.gameController.isTimerStarted()) {

			this.runAnimation();
		}

	}

	private void RunRightAnimation() {
		Log.i(LOG_TAG, "RunRightAnimation");

		if (this.themeString
				.equalsIgnoreCase(MainMenuActivityThemeChanger.PINK_THEME)) {

			this.animationImageView
					.setBackgroundResource(R.drawable.animation_pink_right_images);

		} else if (this.themeString
				.equalsIgnoreCase(MainMenuActivityThemeChanger.LIGHT_THEME)) {

			this.animationImageView
					.setBackgroundResource(R.drawable.animation_light_right_images);

		} else {

			this.animationImageView
					.setBackgroundResource(R.drawable.animation_dark_right_images);
		}

		this.animation = (AnimationDrawable) this.animationImageView
				.getBackground();

		if (this.gameController.isTimerStarted()) {

			runAnimation();
		}

	}

	private void runAnimation() {
		this.animation.stop();
		this.animation.start();

		int iDuration = 0;

		for (int i = 0; i < this.animation.getNumberOfFrames(); i++) {
			iDuration += this.animation.getDuration(i);
		}

		Handler mAnimationHandler = new Handler();
		mAnimationHandler.postDelayed(new Runnable() {

			public void run() {
				animation.stop();
			}
		}, iDuration * 2);

		new Thread(new Runnable() {
			public void run() {

			}
		}).start();
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
	public void onLeftPunchButtonOrPunchingBagClick(View view) {
		Log.i(LOG_TAG, "onLeftPunchButtonOrPunchingBagClick");

		if (!this.gameController.isTimerStarted()) {
			this.gameController.startTimer();
		}

		this.gameController.punchLeft();

		if (this.animation == null || !this.animation.isRunning()) {
			this.RunLeftAnimation();
		}

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
	public void onRightPunchButtonOrPunchingBagClick(View view) {
		Log.i(LOG_TAG, "onRightPunchButtonOrPunchingBagClick");

		if (!this.gameController.isTimerStarted()) {
			this.gameController.startTimer();
		}

		this.gameController.punchRight();

		if (this.animation == null || !this.animation.isRunning()) {
			this.RunRightAnimation();
		}

	}

	/**
	 * Handles the pause button click
	 * 
	 * @param view
	 */
	public void onPauseButtonClick(View view) {
		Log.i(LOG_TAG, "onPauseButtonClick");
		
		if(!this.gameController.isTimerStarted()) {
			return;
		}
		
		final long timeLeft = this.gameController.getTimerDurationRemaining();
		this.gameController.pauseTimer();

		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

				gameController.restartTimer(timeLeft);
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
