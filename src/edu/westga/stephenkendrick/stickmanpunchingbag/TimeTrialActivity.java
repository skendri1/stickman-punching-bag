package edu.westga.stephenkendrick.stickmanpunchingbag;

import java.util.Observable;
import java.util.Observer;

import edu.westga.stephenkendrick.stickmanpunchingbag.Controllers.TimeTrialGameController;
import edu.westga.stephenkendrick.stickmanpunchingbag.Database.HighScoresContentProviderDB;
import edu.westga.stephenkendrick.stickmanpunchingbag.Database.HighScoresContract.HighScores;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TimeTrialActivity extends Activity implements Observer {

	private static final String LOG_TAG = "TimerTrialActivity";

	private Bundle savedInstanceState;

	private Button pauseButton;
	private TextView numberOfPunchesCounterTextView;
	private TextView timerTextView;

	private TimeTrialGameController gameController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(LOG_TAG, "onCreate");

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_trial);

		this.savedInstanceState = savedInstanceState;

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

	private void setUpTextViewsAndButtons() {
		Log.i(LOG_TAG, "setUpTextViewsAndButtons");

		this.pauseButton = (Button) findViewById(R.id.pauseButton);

		this.numberOfPunchesCounterTextView = (TextView) findViewById(R.id.numberOfPunchesCounterTextView);
		this.timerTextView = (TextView) findViewById(R.id.timerTextView);
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
	}

	/**
	 * Handles the pause button click
	 * 
	 * @param view
	 */
	public void onPauseButtonClick(View view) {
		Log.i(LOG_TAG, "onPauseButtonClick");

		this.gameController.pauseTimer();
	}

	/**
	 * This will handle when the game is quit or timer runs out.
	 */
	@Override
	public void update(Observable observable, Object data) {
		Log.i(LOG_TAG, "update");

		final EditText input = new EditText(this);

		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

				addHighScore(input.getText().toString(),
						gameController.getNumberOfPunches());
				finish();
			}
		};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(getString(R.string.player_name_dialog_title))
				.setPositiveButton("OK", dialogClickListener);
		builder.setView(input);
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