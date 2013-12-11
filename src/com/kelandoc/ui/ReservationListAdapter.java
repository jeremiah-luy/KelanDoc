package com.kelandoc.ui;

import java.util.ArrayList;
import java.util.List;

import mobicom.saandoc.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.kelandoc.db.Doctor;
import com.kelandoc.db.Reservation;
import com.kelandoc.db.Timeslot;
import com.kelandoc.db.User;

public class ReservationListAdapter extends BaseAdapter {
	 
	/** The inflator used to inflate the XML layout */
	private LayoutInflater inflator;
 
	/** A list containing some sample data to show. */
	private List dataList;
	
	private int outputType = User.PATIENT;
 
	public int getOutputType() {
		return outputType;
	}

	public void setOutputType(int outputType) {
		this.outputType = outputType;
	}

	public ReservationListAdapter(LayoutInflater inflator, List<Reservation> list) {
		super();
		this.inflator = inflator;
		dataList = new ArrayList<CheckBox>();
		for(Reservation i : list)
			dataList.add(new ReservationData(i));
	}
 
	@Override
	public int getCount() {
		return dataList.size();
	}
 
	@Override
	public Object getItem(int position) {
		return dataList.get(position);
	}
 
	@Override
	public long getItemId(int position) {
		return position;
	}
 
	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
 
		// We only create the view if its needed
		if (view == null) {
			view = inflator.inflate(R.layout.appointment, null);
		}
 
		ReservationData data = (ReservationData) getItem(position);
		
		// Set the example text and the state of the checkbox
		Reservation res = data.getRes();
		TextView t = (TextView) view.findViewById(R.id.appTimeAndDate);
		t.setText(Timeslot.toTime(res.getTime()) + " " + res.getDate());

		TextView t1 = (TextView) view.findViewById(R.id.appDoc);
		TextView t2 = (TextView) view.findViewById(R.id.appHosp);
		TextView t3 = (TextView) view.findViewById(R.id.appSpec);
		if(outputType == User.DOCTOR) {
			t1.setText(res.getD().getLastName() + ", " + res.getD().getFirstName());
			t2.setText(res.getD().getHospital());		
			t3.setText(res.getD().getSpecialization());
			t2.setVisibility(View.VISIBLE);
			t3.setVisibility(View.VISIBLE);
		} else if(outputType == User.PATIENT) {
			t1.setText(res.getP().getLastName() + ", " + res.getP().getFirstName());
			t2.setVisibility(View.GONE);
			t3.setVisibility(View.GONE);
		}
		return view;
	}
	
	public class ReservationData {
		 
		private Reservation d;
	 
		public ReservationData(Reservation d) {
			super();
			this.d = d;
		}
	 
		public Reservation getRes() {
			return d;
		}
	 
		public void setRes(Reservation d) {
			this.d = d;
		}
	 
	}
}