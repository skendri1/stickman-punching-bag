/**
 * 
 */
package edu.westga.stephenkendrick.stickmanpunchingbag.settings;

import edu.westga.stephenkendrick.stickmanpunchingbag.R;
import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * The Settings Fragment that the settings activity uses
 * 
 * Extends PreferenceFragment
 * 
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
