/**
 * 
 */
package edu.westga.stephenkendrick.stickmanpunchingbag.settings;

import android.app.Activity;
import android.os.Bundle;

/**
 * Settings activity.
 * 
 * @author stephenkendrick
 * 
 */
public class SettingsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		getFragmentManager().beginTransaction()
				.replace(android.R.id.content, new SettingsFragment()).commit();
	}

}
