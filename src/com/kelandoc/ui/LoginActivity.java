package com.kelandoc.ui;

import java.util.List;

import mobicom.saandoc.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kelandoc.db.Doctor;
import com.kelandoc.db.Patient;
import com.kelandoc.db.SQL;
import com.kelandoc.db.Timeslot;
import com.kelandoc.db.User;

public class LoginActivity extends Activity {

	SQL sql;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		sql = new SQL(LoginActivity.this);

		initLocalDB();

		Typeface centuryGothic = Typeface.createFromAsset(getAssets(), "fonts/GOTHIC.TTF");

		final TextView txtUsername = (TextView) findViewById(R.id.reg_username_txt);
		txtUsername.setTypeface(centuryGothic);

		final TextView txtPassword = (TextView) findViewById(R.id.edit_firstname_txt);
		txtPassword.setTypeface(centuryGothic);

		Button loginButton = (Button) findViewById(R.id.main_login_button1);
		loginButton.setTypeface(centuryGothic);
		loginButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent activityChangeIntent = null;

				List<User> userList = sql.getAllUsers();

				Log.d("HELLO", "List Size: " + userList.size());

				for(User u : userList) {
					Log.d("HELLO", u.getUsername() + " = " + txtUsername.getText().toString() + " " + u.getUsername().equals(txtUsername.getText().toString()));
					Log.d("HELLO", u.getPassword() + " = " + txtPassword.getText().toString() + " " + u.getPassword().equals(txtPassword.getText().toString()));
					if(txtUsername.getText().toString().equals(u.getUsername())){
						Log.d("HELLO", "Username Accepted");
						if(txtPassword.getText().toString().equals(u.getPassword())){
							Log.d("HELLO", "Password Accepted");
							if(u.getUserType() == User.PATIENT) {
								int userID = sql.getUserID(txtUsername.getText().toString());
								activityChangeIntent = new Intent(LoginActivity.this, PatientMainpageActivity.class);
								activityChangeIntent.putExtra("PAT_ID", sql.getPatientUser(userID).getPatientID());
								Log.d("HELLO", "Patient Type");
							} else {
								int userID = sql.getUserID(txtUsername.getText().toString());
								Log.d("HELLO", "USER FOUND: " + userID);
								activityChangeIntent = new Intent(LoginActivity.this, DoctorMainpageActivity.class);
								activityChangeIntent.putExtra("DOCTOR_ID", sql.getDoctorUser(userID).getDoctorID());
								Log.d("HELLO", "Doctor Type");
							}
						}
					}
				}
				if(activityChangeIntent != null)
					startActivity(activityChangeIntent);
			}
		});

		Button registerButton = (Button) findViewById(R.id.main_register_button);
		registerButton.setTypeface(centuryGothic);
		registerButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Log.d("HELLO", "hgdfghjgfvvg");
				Intent activityChangeIntent = new Intent(LoginActivity.this, RegisterActivity.class);
				startActivity(activityChangeIntent);
			}
		});
	}
	
	private void initLocalDB() {
		// TODO Auto-generated method stub
		
		if(!SQL.create)
			return;
		
		if(sql.countUsers() > 0)
			return;
		
		sql.addUser(new User("wingw", "admin", User.PATIENT));
		sql.addUser(new User("carlc", "admin", User.PATIENT));

		sql.addUser(new User("jeremiahl", "admin", User.DOCTOR));
		
		sql.addPatient(new Patient(sql.getUserID("wingw"), "Wong", "Wing"));
		sql.addPatient(new Patient(sql.getUserID("carlc"), "Chan", "Carl"));
		
		sql.addDoctor(new Doctor(sql.getUserID("jeremiahl"), "Luy", "Jeremiah", "GP", "Hospital"));
		
		sql.addTimeslot(new Timeslot(sql.getDoctorID("Jeremiah", "Luy"), 4));
		sql.addTimeslot(new Timeslot(sql.getDoctorID("Jeremiah", "Luy"), 5));
		sql.addTimeslot(new Timeslot(sql.getDoctorID("Jeremiah", "Luy"), 6));
		sql.addTimeslot(new Timeslot(sql.getDoctorID("Jeremiah", "Luy"), 7));
		
		List<User> users = sql.getAllUsers();
		for(User u : users)
			Log.d("HELLO", "User: " + u.getUserID() + " Username: " + u.getUsername() + " Password: " + u.getPassword() + " Type: " + u.getUserType());
		
		List<Doctor> docs = sql.getAllDoctors();
		for(Doctor d : docs)
			Log.d("HELLO", "User: " + d.getUserID() + " Doctor: " + d.getDoctorID() + " First Name: " + d.getFirstName() + " Last Name: " + d.getLastName() + " Hospital: " + d.getHospital() + " Spec: " + d.getSpecialization());
		
		List<Timeslot> timeslots = sql.getAllTimeslot();
		for(Timeslot t : timeslots)
			Log.d("HELLO", "Timeslot: " + t.getTime() + " Doctor: " + t.getDoctorID());
		
		//		sql.deleteAllDoctors();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
