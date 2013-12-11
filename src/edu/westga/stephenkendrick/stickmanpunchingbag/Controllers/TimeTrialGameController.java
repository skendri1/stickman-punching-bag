/**
 * 
 */
package edu.westga.stephenkendrick.stickmanpunchingbag.Controllers;

import java.util.Observable;

import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

/**
 * Handles the Time Trial Game actions and controls the game
 * 
 * @author stephenkendrick
 *
 */
public class TimeTrialGameController extends Observable{
	
	private static final String LOG_TAG = "TimeTrialGameController";
	
	private static long INTERVAL = 1000;
	
	public static long DEFAULT_START_TIME = 30000;
	
	public static final String GAME_MODE_FREE = "FreeMode";
	public static final String GAME_MODE_ALT = "ALT";
	public static final String GAME_MODE_LEFT = "LEFT";
	public static final String GAME_MODE_RIGHT = "RIGHT";
	
	private boolean isTimerRunning;
	private GameTimer gameTimer;
	private long msLeft;
	
	private TextView timerTextView;
	
	private TextView numberOfPunchesTextView;
	
	private int numberOfPunches;
	
	private String punchMode;
	
	private String lastButtonPressed;
	
	
	/**
	 * Creates the TimeTrialGameController class that will handle the game mechanics.
	 * <p>
	 * Precondition: none
	 * 
	 * Postcondition: none
	 */
	public TimeTrialGameController(TextView timerTextView, TextView numberOfPunchesTextView, String punchMode) {
		
		Log.i(LOG_TAG, "TimeTrialGameController Constructor");
		
		if(timerTextView == null) {
			throw new IllegalArgumentException("timer text view cannot be null");
		}
		
		if(numberOfPunchesTextView == null) {
			throw new IllegalArgumentException("number of punches text view cannot be null");
		}
		
		this.timerTextView = timerTextView;
		
		this.numberOfPunchesTextView = numberOfPunchesTextView;
		
		this.setPunchMode(punchMode);
		
		this.createTimer(DEFAULT_START_TIME);
		
	}
	
	//****************************** Timer Methods ***********************************
	
	/**
	 * Creates the Timer 
	 * <p>
	 * Precondition: none
	 * 
	 * Postcondition: none
	 */
	public void createTimer(long startTimeInMS) {
		Log.i(LOG_TAG, "createTimer");
		
		this.gameTimer = new GameTimer(startTimeInMS, INTERVAL);
		
	}
	
	/**
	 * Starts the Timer of the game
	 * <p>
	 * Precondition: none
	 * 
	 * Postcondition: timer has started
	 */
	public void startTimer(){
		Log.i(LOG_TAG, "startTimer");
		if(this.isTimerRunning) {
			return;
		}
		
		this.isTimerRunning = true;
		this.gameTimer.start();

	}
	
	/**
	 * restarts the Timer of the game with the given startTime
	 * <p>
	 * Precondition: none
	 * 
	 * Postcondition: timer has started
	 */
	public void restartTimer(long startTime){
		Log.i(LOG_TAG, "restartTimer");
		
		this.isTimerRunning = true;
		this.createTimer(startTime);
		this.gameTimer.start();

	}
	
	/**
	 * Pause the Timer of the game
	 * <p>
	 * Precondition: timer has started
	 * 
	 * Postcondition: timer has paused
	 */
	public void pauseTimer(){
		Log.i(LOG_TAG, "pauseTimer");
		if(!this.isTimerRunning) {
			return;
		}
		
		this.isTimerRunning = false;
		this.gameTimer.cancel();

	}
	
	/**
	 * Starts the Timer of the game
	 * <p>
	 * Precondition: none
	 * 
	 * Postcondition: timer has started
	 */
	public long getTimerDurationRemaining(){
		Log.i(LOG_TAG, "getTimerDurationRemaining");
		
		return this.msLeft;

	}
	
	private void notifyForTimerFinished() {
		setChanged();
		notifyObservers();
	}

	/**
	 * Returns true if the timer is running
	 * <p>
	 * Precondition: none
	 * 
	 * @return true if the timer is running, false otherwise
	 */
	public boolean isTimerStarted() {
		Log.i(LOG_TAG, "isTimerStarted");
		return this.isTimerRunning;
	}

	/**
	 * Inner class that will handle the game countdown timer
	 * @author stephenkendrick
	 *
	 */
	private class GameTimer extends CountDownTimer
	{

		public GameTimer(long startTime, long interval)
		{
			super(startTime, interval);
			Log.i(LOG_TAG, "GameTimer Constructor");
		}

		@Override
		public void onFinish()
		{
			Log.i(LOG_TAG, "onFinish");
			timerTextView.setText("00:00");
			
			notifyForTimerFinished();
			
			isTimerRunning = false;
		}

		@Override
		public void onTick(long millisUntilFinished)
		{
			Log.i(LOG_TAG, "onTick");
			
			msLeft = millisUntilFinished;
			int secs = (int) (millisUntilFinished / 1000);
			int mins = secs / 60;
			
			timerTextView.setText("" + String.format("%02d",mins ) + ":" + String.format("%02d", secs) );
				
		}
	}
	
	//****************************** Getter and Setter Methods ***********************************
	
	/**
	 * Returns the timer text view 
	 * <p>
	 * Precondition: none
	 * 
	 * @return the timerTextView
	 */
	public TextView getTimerTextView() {
		Log.i(LOG_TAG, "getTimerTextView");
		return timerTextView;
	}


	/**
	 * Sets the timer text view to the given text view
	 * <p>
	 * Precondition: timerTextView != null
	 * 
	 * Postcondition: none
	 * 
	 * @param timerTextView the timerTextView to set
	 */
	public void setTimerTextView(TextView timerTextView) {
		Log.i(LOG_TAG, "setTimerTextView");
		if(timerTextView == null) {
			throw new IllegalArgumentException("timerTextView cannot be null");
		}
		
		this.timerTextView = timerTextView;
	}


	/**
	 * Returns the text view for the number of punches
	 * <p>
	 * Precondition: none
	 * 
	 * @return the numberOfPunchesTextView
	 */
	public TextView getNumberOfPunchesTextView() {
		Log.i(LOG_TAG, "getNumberOfPunchesTextView");
		return numberOfPunchesTextView;
	}


	/**
	 * Sets the number of punches text view to the given
	 * <p>
	 * Precondition: numberOfPunchesTextView != null
	 * 
	 * @param numberOfPunchesTextView the numberOfPunchesTextView to set
	 */
	public void setNumberOfPunchesTextView(TextView numberOfPunchesTextView) {
		Log.i(LOG_TAG, "setNumberOfPunchesTextView");
		
		if(numberOfPunchesTextView == null) {
			throw new IllegalArgumentException("number of punches text view cannot be null");
		}
		
		this.numberOfPunchesTextView = numberOfPunchesTextView;
	}


	/**
	 * Returns the current punch mod
	 * <p>
	 * Precondition: none
	 * 
	 * @return the punchMode
	 */
	public String getPunchMode() {
		return punchMode;
	}

	/**
	 * Sets the punch mode
	 * 
	 * @param punchMode the punchMode to set
	 */
	public void setPunchMode(String punchMode) {
		this.punchMode = punchMode;
	}

	/**
	 * Returns the number of punches the game currently has counted
	 * <p>
	 * Precondition: none
	 * 
	 * @return the numberOfPunches
	 */
	public int getNumberOfPunches() {
		Log.i(LOG_TAG, "getNumberOfPunches");
		return numberOfPunches;
	}


	/**
	 * Sets the number of punches to the given number
	 * <p>
	 * Precondition: numberOfPunches > 0
	 * 
	 * @param numberOfPunches the numberOfPunches to set
	 */
	public void setNumberOfPunches(int numberOfPunches) {
		Log.i(LOG_TAG, "setNumberOfPunches");
		if(numberOfPunches < 0) {
			throw new IllegalArgumentException("nuberOfPunches cannot be less than 0");
		}
		
		this.numberOfPunches = numberOfPunches;
	}
	
	/**
	 * Handles when the user punches right
	 */
	public void punchRight() {
		Log.i(LOG_TAG, "punchRight");
		
		if(this.punchMode.equalsIgnoreCase(GAME_MODE_LEFT)) {
			return;
		}
		
		if(this.punchMode.equalsIgnoreCase(GAME_MODE_RIGHT) || this.punchMode.equalsIgnoreCase(GAME_MODE_FREE)) {
			this.addOnePunchToPunchCounter();
		}
		
		if(this.punchMode.equalsIgnoreCase(GAME_MODE_ALT) && (this.lastButtonPressed == null || this.lastButtonPressed.equalsIgnoreCase(GAME_MODE_LEFT) ) ) {
			this.addOnePunchToPunchCounter();
		}
		
		this.lastButtonPressed = GAME_MODE_RIGHT;
	}
	
	/**
	 * Handles when the user punches left
	 */
	public void punchLeft() {
		Log.i(LOG_TAG, "punchLeft");
		
		if(this.punchMode.equalsIgnoreCase(GAME_MODE_RIGHT)) {
			return;
		}
		
		if(this.punchMode.equalsIgnoreCase(GAME_MODE_LEFT) || this.punchMode.equalsIgnoreCase(GAME_MODE_FREE)) {
			this.addOnePunchToPunchCounter();
		}
		
		if(this.punchMode.equalsIgnoreCase(GAME_MODE_ALT) && (this.lastButtonPressed == null || this.lastButtonPressed.equalsIgnoreCase(GAME_MODE_RIGHT) ) ) {
			this.addOnePunchToPunchCounter();
		}
		
		this.lastButtonPressed = GAME_MODE_LEFT;
	}
	
	/**
	 * Adds one to the punch counter
	 * <p>
	 * Precondition: none
	 * 
	 * Postcondition: none
	 */
	public void addOnePunchToPunchCounter() {
		Log.i(LOG_TAG, "addOnePunchToPunchCounter");
		
		this.numberOfPunches += 1;
		this.numberOfPunchesTextView.setText("" + this.numberOfPunches);
	}
	
	public void resetPunchCounter() {
		Log.i(LOG_TAG, "resetPunchCounter");
		
		this.numberOfPunches = 0;
	}

}
