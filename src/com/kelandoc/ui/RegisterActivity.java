package com.kelandoc.ui;

import mobicom.saandoc.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.kelandoc.db.Doctor;
import com.kelandoc.db.Patient;
import com.kelandoc.db.SQL;
import com.kelandoc.db.Timeslot;
import com.kelandoc.db.User;

public class RegisterActivity extends Activity {

	SQL sql;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		Log.d("HELLO", "bbdc");

		final EditText txtUsername = (EditText) findViewById(R.id.reg_username_txt),
				txtFirstName = (EditText) findViewById(R.id.reg_firstname_txt),
				txtLastName = (EditText) findViewById(R.id.reg_lastname_txt),
				txtPassword = (EditText) findViewById(R.id.reg_password_txt);
		final RadioButton rdbPat = (RadioButton) findViewById(R.id.reg_pat_rdb),
				rdbDoc = (RadioButton) findViewById(R.id.reg_doc_rdb);

		final TextView spec = (TextView) findViewById(R.id.tvSpec),
				hospital = (TextView) findViewById(R.id.tvHospital);
		final EditText etSpec = (EditText) findViewById(R.id.etSpec),
				etHospital = (EditText) findViewById(R.id.etHospital);

		spec.setVisibility(View.GONE);
		hospital.setVisibility(View.GONE);
		etHospital.setVisibility(View.GONE);
		etSpec.setVisibility(View.GONE);
		
		sql = new SQL(this);
		
		rdbDoc.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				spec.setVisibility(View.VISIBLE);
				hospital.setVisibility(View.VISIBLE);
				etHospital.setVisibility(View.VISIBLE);
				etSpec.setVisibility(View.VISIBLE);
			}
			
		});
		
		rdbPat.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				spec.setVisibility(View.GONE);
				hospital.setVisibility(View.GONE);
				etHospital.setVisibility(View.GONE);
				etSpec.setVisibility(View.GONE);
			}
			
		});
		
		Button registerButton = (Button) findViewById(R.id.reg_next_btn);
		registerButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent activityChangeIntent = null;
				if(rdbPat.isChecked()){
					activityChangeIntent = new Intent(RegisterActivity.this, PatientMainpageActivity.class);
					sql.addUser(new User(txtUsername.getText().toString(), txtPassword.getText().toString(), User.PATIENT));
					sql.addPatient(new Patient(sql.getUserID(txtUsername.getText().toString()), txtLastName.getText().toString(), txtFirstName.getText().toString()));
					int patID = sql.getPatientID(txtFirstName.getText().toString(), txtLastName.getText().toString());
					
					activityChangeIntent.putExtra("PAT_ID", patID);
				} else if(rdbDoc.isChecked()) {
					activityChangeIntent = new Intent(RegisterActivity.this, TimeslotSelectionActivity.class);
					sql.addUser(new User(txtUsername.getText().toString(), txtPassword.getText().toString(), User.DOCTOR));
					sql.addDoctor(new Doctor(sql.getUserID(txtUsername.getText().toString()), txtLastName.getText().toString(), txtFirstName.getText().toString(),
							etSpec.getText().toString(), etHospital.getText().toString()));
					int doctorID = sql.getDoctorID(txtFirstName.getText().toString(), txtLastName.getText().toString());
					
					activityChangeIntent.putExtra("DOCTOR_ID", doctorID);
					activityChangeIntent.putExtra("REGISTER", true);
				}
				startActivity(activityChangeIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

}
