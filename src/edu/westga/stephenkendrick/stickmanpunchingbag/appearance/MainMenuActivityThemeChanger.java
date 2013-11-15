package edu.westga.stephenkendrick.stickmanpunchingbag.appearance;

import edu.westga.stephenkendrick.stickmanpunchingbag.MainMenuActivity;
import edu.westga.stephenkendrick.stickmanpunchingbag.R;
import edu.westga.stephenkendrick.stickmanpunchingbag.R.drawable;
import edu.westga.stephenkendrick.stickmanpunchingbag.R.id;
import edu.westga.stephenkendrick.stickmanpunchingbag.R.layout;
import edu.westga.stephenkendrick.stickmanpunchingbag.R.style;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainMenuActivityThemeChanger {
	private static final String LOG_TAG = "MainMenuActivityThemeChanger";
	
	private Button timeTrialButton;
	private Button highScoresButton;
	private Button settingsButton;
	
	private ImageView mainMenuLogo;

	private MainMenuActivity view;
	
	private String currentTheme;

	public static final String THEME = "theme";
	public static final String DARK_THEME = "dark";
	public static final String LIGHT_THEME = "light";
	public static final String PINK_THEME = "pink";
	

	/**
	 * Creates the MainMenuActivityThemeChanger
	 * <p>
	 * Precondition: none
	 * Postcondition: none
	 * 
	 * @param mainMenuActivity
	 */
	public MainMenuActivityThemeChanger(MainMenuActivity mainMenuActivity) {
		Log.i(LOG_TAG, "MainMenuActivityThemeChanger Constructor");
		
		this.view = mainMenuActivity;
		this.setButtonVariables();
	}

	private void setButtonVariables() {
		Log.i(LOG_TAG, "setButtonVariables");
		
		this.timeTrialButton = (Button) this.view
				.findViewById(R.id.timeTrialButton);
		this.highScoresButton = (Button) this.view
				.findViewById(R.id.highScoresButton);
		this.settingsButton = (Button) this.view
				.findViewById(R.id.settingsButton);
		
		this.mainMenuLogo = (ImageView) this.view.findViewById(R.id.imageView1);
	}
	
	/**
	 * Returns the string of the application's current theme
	 * <p>
	 * Precondition: none
	 * 
	 * @return the String of the application's current theme
	 */
	public String getCurrentTheme() {
		Log.i(LOG_TAG, "getCurrentTheme");
		return currentTheme;
	}

	/**
	 * Changes the current theme to the Light theme
	 * <p>
	 * Precondition: none
	 * 
	 * Postcondition: none
	 */
	public void changeThemeToLight() {
		Log.i(LOG_TAG, "changeThemeToLight");
		
		if(this.currentTheme == LIGHT_THEME) {
			return;
		}

		this.currentTheme = LIGHT_THEME;

		this.view.setTheme(R.style.lightTheme);
		this.view.setContentView(R.layout.activity_main_menu);
		this.setButtonVariables();

		this.timeTrialButton
				.setBackgroundResource(R.drawable.stickman_punchingbag_button_light);
		this.highScoresButton
				.setBackgroundResource(R.drawable.stickman_punchingbag_button_light);
		this.settingsButton
				.setBackgroundResource(R.drawable.stickman_punchingbag_button_light);

		this.mainMenuLogo.setImageResource(R.drawable.stickman_punchingbag_main_image_light);
	}
	
	/**
	 * Changes the current theme to Pink Theme
	 * <p>
	 * Precondition: none
	 * 
	 * Postcondition: none
	 */
	public void changeThemeToPink() {
		Log.i(LOG_TAG, "changeThemeToPink");

		if(this.currentTheme == PINK_THEME) {
			return;
		}

		this.currentTheme = PINK_THEME;
		
		this.view.setTheme(R.style.pinkTheme);
		this.view.setContentView(R.layout.activity_main_menu);
		this.setButtonVariables();

		this.timeTrialButton
				.setBackgroundResource(R.drawable.stickman_punchingbag_button_pink);
		this.highScoresButton
				.setBackgroundResource(R.drawable.stickman_punchingbag_button_pink);
		this.settingsButton
				.setBackgroundResource(R.drawable.stickman_punchingbag_button_pink);
		
		this.mainMenuLogo.setImageResource(R.drawable.stickman_punchingbag_main_image_pink);

		
	}
	
	/**
	 * Changes the current them to Dark theme
	 * <p>
	 * Precondition: none
	 * 
	 * Postcondition: none
	 */
	public void changeThemeToDark() {
		Log.i(LOG_TAG, "changeThemeToDark");
		
		if(this.currentTheme == DARK_THEME) {
			return;
		}

		this.currentTheme = DARK_THEME;
		
		this.view.setTheme(R.style.darkTheme);
		this.view.setContentView(R.layout.activity_main_menu);
		this.setButtonVariables();

		this.timeTrialButton
				.setBackgroundResource(R.drawable.stickman_punchingbag_button_dark);
		this.highScoresButton
				.setBackgroundResource(R.drawable.stickman_punchingbag_button_dark);
		this.settingsButton
				.setBackgroundResource(R.drawable.stickman_punchingbag_button_dark);
		
		this.mainMenuLogo.setImageResource(R.drawable.stickman_punchingbag_main_image_dark);


	}
}