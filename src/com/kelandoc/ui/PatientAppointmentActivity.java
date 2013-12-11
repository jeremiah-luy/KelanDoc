package com.kelandoc.ui;

import java.util.List;

import mobicom.saandoc.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import com.kelandoc.db.Patient;
import com.kelandoc.db.Reservation;
import com.kelandoc.db.SQL;
import com.kelandoc.db.User;

public class PatientAppointmentActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_appointments);
		
		Bundle b = getIntent().getExtras();
		int patID = b.getInt("PAT_ID");
		Log.d("HELLO", patID + "");
		SQL sql = new SQL(this);

		Patient pat = sql.getPatient(patID);
		
		List<Reservation> res = sql.getPatReservation(patID);
		for(Reservation r : res){
			r.setD(sql.getDoctor(r.getDoctorID()));
			r.setP(pat);
		}
		
		ListView l = (ListView) findViewById(R.id.resList);
		ReservationListAdapter adapter = new ReservationListAdapter(getLayoutInflater(), res);
		adapter.setOutputType(User.DOCTOR);
		l.setAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.patient_appointments, menu);
		return true;
	}

}
