/**
 * 
 */
package edu.westga.stephenkendrick.stickmanpunchingbag.settings;

import edu.westga.stephenkendrick.stickmanpunchingbag.R;
import edu.westga.stephenkendrick.stickmanpunchingbag.R.xml;
import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * @author stephenkendrick
 * 
 */
public class SettingsFragment extends PreferenceFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		// Load the preferences from an XML resource
		addPreferencesFromResource(R.xml.preferences);

	}

}
