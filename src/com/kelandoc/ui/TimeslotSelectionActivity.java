package com.kelandoc.ui;

import java.util.ArrayList;
import java.util.List;

import mobicom.saandoc.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.kelandoc.db.SQL;
import com.kelandoc.db.Timeslot;
import com.kelandoc.ui.CheckBoxListAdapter.SampleData;

public class TimeslotSelectionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timeslot_selection);
		
		final Bundle b = getIntent().getExtras();
		
		int docID = b.getInt("DOCTOR_ID"), patID;
		
		SQL sql = new SQL(this);
		
		Log.d("HELLO", "TIMESLOT");
		Log.d("HELLO", "Doctor ID: " + docID);
		List<Integer> times;
		if(!b.getBoolean("REGISTER")) {
			times = sql.getDocTimes(docID);
			b.getInt("PAT_ID");
			Log.d("HELLO", "Timeslots: " + times.size());
		} else {
			times = new ArrayList<Integer>();
			for(int i = 0; i < Timeslot.TIMESLOTS.length; i++)
				times.add(i);
		}
		
		final CheckBoxListAdapter la = new CheckBoxListAdapter(this.getLayoutInflater(), times);
		
		ListView list = (ListView) findViewById(R.id.timeslotList);
		list.setAdapter(la);
		
		Button tsB = (Button) findViewById(R.id.timeslotButton1);
		tsB.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent activityChangeIntent = null;
				SQL sql = new SQL(TimeslotSelectionActivity.this);
				if(b.getBoolean("REGISTER")) {
					for(int i = 0; i < la.getCount(); i++)
						if(((SampleData)la.getItem(i)).isSelected())
							sql.addTimeslot(new Timeslot(b.getInt("DOCTOR_ID"), i));
					activityChangeIntent = new Intent(TimeslotSelectionActivity.this, DoctorMainpageActivity.class);
				} else {
//					for(int i = 0; i < 16; i++)
//						if(rdbTimeslots[i].isChecked())
//							sql.addReservation(new Reservation(toTimeIndex(rdbTimeslots[i].getText().toString()), b.getInt("DOCTOR_ID"), i));
//					activityChangeIntent = new Intent(TimeslotSelectionActivity.this, PatientAppointmentActivity.class);
				}
				
				startActivity(activityChangeIntent);
			}
		});
	}

	private int toTimeIndex(String string) {
		int time = Integer.parseInt(string);
		
		for(int i = 0; i < Timeslot.TIMESLOTS.length; i++)
			if(Timeslot.TIMESLOTS[i] == time)
				return i;
		return -1;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.timeslot_seletion, menu);
		return true;
	}

}
