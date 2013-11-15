/**
 * 
 */
package edu.westga.stephenkendrick.stickmanpunchingbag;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author stephenkendrick
 * 
 */
public class SettingsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		getFragmentManager().beginTransaction()
				.replace(android.R.id.content, new SettingsFragment()).commit();
	}

}
