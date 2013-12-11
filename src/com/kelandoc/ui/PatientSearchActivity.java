package com.kelandoc.ui;

import java.util.List;

import mobicom.saandoc.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

import com.kelandoc.db.Doctor;
import com.kelandoc.db.SQL;

public class PatientSearchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.patient_search);
		
		ListView lv = (ListView) findViewById(R.id.searchList);
        // This is the array adapter, it takes the context of the activity as a first
		// parameter, the type of list view as a second parameter and your array as a third parameter
        
		final Bundle b = getIntent().getExtras();
		
		SQL a = new SQL(this);
		
		final List<Doctor> allDoctors = a.getAllDoctors();
		
		final DoctorListAdapter da = new DoctorListAdapter(this.getLayoutInflater(), allDoctors);
        lv.setAdapter(da); 
        lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {
				Intent activityChangeIntent = null;
				activityChangeIntent = new Intent(PatientSearchActivity.this, PatientReservationActivity.class);
				activityChangeIntent.putExtra("PAT_ID", b.getInt("PAT_ID"));
				activityChangeIntent.putExtra("DOCTOR_ID", allDoctors.get(index).getDoctorID());
				startActivity(activityChangeIntent);
			}
		});
        
        EditText searchBar = (EditText) findViewById(R.id.searchbar);
        searchBar.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
				da.getFilter().filter(cs);
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.patient_search, menu);
		return true;
	}

}
