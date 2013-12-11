package com.kelandoc.ui;

import mobicom.saandoc.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class PatientMainpageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.patient_mainpage);
		
		final Bundle b = getIntent().getExtras();
		
        final Button doctorButton = (Button) findViewById(R.id.patient_search);
        doctorButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(PatientMainpageActivity.this, PatientSearchActivity.class);
                activityChangeIntent.putExtra("PAT_ID", b.getInt("PAT_ID"));
                PatientMainpageActivity.this.startActivity(activityChangeIntent);
            }
        });
        
        Button btnLogout = (Button) findViewById(R.id.btnLogout);
		btnLogout.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent activityChangeIntent = new Intent(PatientMainpageActivity.this, LoginActivity.class);
				startActivity(activityChangeIntent);
			}
		});
		
		Button btnEditpatient = (Button) findViewById(R.id.btnEditpatient);
		btnEditpatient.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent activityChangeIntent = new Intent(PatientMainpageActivity.this, EditActivity.class);
				startActivity(activityChangeIntent);
			}
		});
		
		Button btnAppt = (Button) findViewById(R.id.patient_appt);
		btnAppt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent activityChangeIntent = new Intent(PatientMainpageActivity.this, PatientAppointmentActivity.class);
				activityChangeIntent.putExtra("PAT_ID", b.getInt("PAT_ID"));
				startActivity(activityChangeIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.patient_mainpage, menu);
		return true;
	}

	@Override
    public void onBackPressed() {
		//Disables back button
    }

}
