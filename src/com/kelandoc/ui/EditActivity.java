package com.kelandoc.ui;

import mobicom.saandoc.R;
import mobicom.saandoc.R.layout;
import mobicom.saandoc.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class EditActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit, menu);
		return true;
	}

}
