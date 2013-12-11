package edu.westga.stephenkendrick.stickmanpunchingbag;

import edu.westga.stephenkendrick.stickmanpunchingbag.appearance.MainMenuActivityThemeChanger;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * This class will handle the splash screen of the application.
 * 
 * @author stephenkendrick
 *
 */
public class SplashScreenActivity extends Activity {

	private static int SPLASH_TIME_OUT = 3000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		changeLogoBasedOnTheme();
		
		new Handler().postDelayed(new Runnable() {
 
            @Override
            public void run() {
            	
                // This method will be executed once the timer is over
            	// This will start the main menu activity
            	
                Intent i = new Intent(SplashScreenActivity.this, MainMenuActivity.class);
                startActivity(i);
 
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
		
	}

	private void changeLogoBasedOnTheme() {
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		String themeString = preferences.getString("theme_scheme",
				MainMenuActivityThemeChanger.DARK_THEME);

		ImageView logo = (ImageView) findViewById(R.id.imgLogo);
		
		if (themeString
				.equalsIgnoreCase(MainMenuActivityThemeChanger.PINK_THEME)) {

			logo.setImageResource(R.drawable.stickman_punchingbag_main_image_pink);

		} else if (themeString
				.equalsIgnoreCase(MainMenuActivityThemeChanger.LIGHT_THEME)) {

			logo.setImageResource(R.drawable.stickman_punchingbag_main_image_light);

		} else {

			logo.setImageResource(R.drawable.stickman_punchingbag_main_image_dark);

		}
	}

}
