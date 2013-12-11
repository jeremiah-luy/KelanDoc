package com.kelandoc.ui;

import java.util.List;

import mobicom.saandoc.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import com.kelandoc.db.Doctor;
import com.kelandoc.db.Reservation;
import com.kelandoc.db.SQL;
import com.kelandoc.db.User;

public class DoctorAppointmentActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor_appointment);

		Bundle b = getIntent().getExtras();
		int docID = b.getInt("DOCTOR_ID");
		Log.d("HELLO", docID + "");
		SQL sql = new SQL(this);

		Doctor doc = sql.getDoctor(docID);
		
		List<Reservation> res = sql.getDocReservation(docID);
		for(Reservation r : res){
			r.setD(doc);
			r.setP(sql.getPatient(r.getPatientID()));
		}
		
		ListView l = (ListView) findViewById(R.id.appDocList);
		ReservationListAdapter adapter = new ReservationListAdapter(getLayoutInflater(), res);
		adapter.setOutputType(User.PATIENT);
		l.setAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.doctor_appointment, menu);
		return true;
	}

}
