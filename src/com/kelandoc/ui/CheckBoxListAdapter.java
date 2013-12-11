package com.kelandoc.ui;

import java.util.ArrayList;
import java.util.List;

import mobicom.saandoc.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.kelandoc.db.Timeslot;
import com.kelandoc.db.User;

public class CheckBoxListAdapter extends BaseAdapter implements OnClickListener {
	 
	/** The inflator used to inflate the XML layout */
	private LayoutInflater inflator;
 
	/** A list containing some sample data to show. */
	private List dataList;
 
	public CheckBoxListAdapter(LayoutInflater inflator, List<Integer> list) {
		super();
		this.inflator = inflator;
		dataList = new ArrayList<CheckBox>();
		for(Integer i : list)
			dataList.add(new SampleData(i, false));
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
			view = inflator.inflate(R.layout.timeslot_cb, null);
 
			// Set the click listener for the checkbox
			view.findViewById(R.id.ts_CB).setOnClickListener(this);
		}
 
		SampleData data = (SampleData) getItem(position);
 
		// Set the example text and the state of the checkbox
		CheckBox cb = (CheckBox) view.findViewById(R.id.ts_CB);
		cb.setChecked(data.isSelected());
		int i = data.getIndex();
		cb.setText(Timeslot.toTime(i) + " - " + Timeslot.toTime(i + 1));
		// We tag the data object to retrieve it on the click listener.
		cb.setTag(data);
		
		return view;
	}
 
	@Override
	/** Will be called when a checkbox has been clicked. */
	public void onClick(View view) {
		SampleData data = (SampleData) view.getTag();
		data.setSelected(((CheckBox) view).isChecked());
	}
	
	public class SampleData {
		 
		private int index;
	 
		private boolean selected;
	 
		public SampleData(int index, boolean selected) {
			super();
			this.index = index;
			this.selected = selected;
		}
	 
		public int getIndex() {
			return index;
		}
	 
		public void setIndex(int index) {
			this.index = index;
		}
	 
		public boolean isSelected() {
			return selected;
		}
	 
		public void setSelected(boolean selected) {
			this.selected = selected;
		}
	 
	}
}