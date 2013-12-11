package com.kelandoc.ui;

import java.util.ArrayList;

import mobicom.saandoc.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.kelandoc.db.Doctor;
import com.kelandoc.db.Reservation;
import com.kelandoc.db.SQL;
import com.kelandoc.ui.CheckBoxListAdapter.SampleData;

public class PatientReservationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_reservation);

		final Bundle b = getIntent().getExtras();
		final SQL sql = new SQL(this);
		
		final int docID = b.getInt("DOCTOR_ID"), patID = b.getInt("PAT_ID");
		Doctor d = sql.getDoctor(docID);
		
		TextView name = (TextView) findViewById(R.id.resDName);
		TextView hosp = (TextView) findViewById(R.id.resDHosp);
		TextView spec = (TextView) findViewById(R.id.resDSpec);
		final EditText date = (EditText) findViewById(R.id.resDate);
		
		
		name.setText(d.getLastName() + ", " + d.getFirstName());
		hosp.setText(d.getHospital());
		spec.setText(d.getSpecialization());
		
		final ArrayList<Integer> a = sql.getDocTimes(docID);
		final CheckBoxListAdapter la = new CheckBoxListAdapter(getLayoutInflater(), a);
		ListView list = (ListView) findViewById(R.id.resList);
		list.setAdapter(la);
		
		Button butt = (Button) findViewById(R.id.resSubmit);
		butt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				for(int i = 0; i < la.getCount(); i++)
					if(((SampleData)la.getItem(i)).isSelected())
						sql.addReservation(new Reservation(((SampleData)la.getItem(i)).getIndex(), date.getText().toString(), docID, patID));

				Intent activityChangeIntent = new Intent(PatientReservationActivity.this, PatientMainpageActivity.class);
				activityChangeIntent.putExtra("PAT_ID", patID);
				startActivity(activityChangeIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.patient_reservation, menu);
		return true;
	}

}
