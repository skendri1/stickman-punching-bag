/**
 * 
 */
package edu.westga.stephenkendrick.stickmanpunchingbag.Database;

import android.provider.BaseColumns;

/**
 * @author stephenkendrick
 *
 */
public class HighScoresContract {

	/**
	 * Private Constructor
	 */
	private HighScoresContract() { }
	
	public static abstract class HighScores implements BaseColumns{
		
		public static final String HIGH_SCORES_TABLE_NAME = "highscores"; 
		public static final String ID = "_id"; 
		public static final String PLAYER_NAME = "playername"; 
		public static final String NUMBER_OF_TAPS = "numberoftaps";

		
		/**
		 * Private Constructor
		 */
		private HighScores() { }
		
		
	}
	
}
