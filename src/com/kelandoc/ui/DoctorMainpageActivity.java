package com.kelandoc.ui;

import mobicom.saandoc.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class DoctorMainpageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doctor_mainpage_v1);
		
		final Bundle b = getIntent().getExtras();
		
		Button btnEditprofile = (Button) findViewById(R.id.btnEditdoctor);
		btnEditprofile.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent activityChangeIntent = new Intent(DoctorMainpageActivity.this, EditActivity.class);
				startActivity(activityChangeIntent);
			}
		});
		
		Button btnLogout = (Button) findViewById(R.id.btnLogout);
		btnLogout.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent activityChangeIntent = new Intent(DoctorMainpageActivity.this, LoginActivity.class);
				startActivity(activityChangeIntent);
			}
		});
		
		Button btnApp = (Button) findViewById(R.id.patient_appt);
		btnApp.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent activityChangeIntent = new Intent(DoctorMainpageActivity.this, DoctorAppointmentActivity.class);
				activityChangeIntent.putExtra("DOCTOR_ID", b.getInt("DOCTOR_ID"));
				startActivity(activityChangeIntent);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.doctor_mainpage, menu);
		return true;
	}
	
	@Override
    public void onBackPressed() {
		//Disables back button
    }
}
