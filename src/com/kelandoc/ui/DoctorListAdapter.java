package com.kelandoc.ui;

import java.util.ArrayList;
import java.util.List;

import mobicom.saandoc.R;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.kelandoc.db.Doctor;

public class DoctorListAdapter extends BaseAdapter implements Filterable {

	/** The inflator used to inflate the XML layout */
	private LayoutInflater inflator;

	/** A list containing some sample data to show. */
	private List cbList;
	private List dataList;

	public DoctorListAdapter(LayoutInflater inflator, List<Doctor> list) {
		super();
		this.inflator = inflator;
		cbList = new ArrayList<CheckBox>();
		dataList = new ArrayList<DoctorData>();
		for(Doctor i : list) {
			cbList.add(new DoctorData(i, false));
			dataList.add(new DoctorData(i, false));
		}
	}

	@Override
	public int getCount() {
		return cbList.size();
	}

	@Override
	public Object getItem(int position) {
		return cbList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {

		// We only create the view if its needed
		if (view == null) {
			view = inflator.inflate(R.layout.doctor_list, null);
		}

		DoctorData data = (DoctorData) getItem(position);

		// Set the example text and the state of the checkbox
		TextView t = (TextView) view.findViewById(R.id.listName);
		t.setText(data.getName().getLastName() + ", " + data.getName().getFirstName());
		TextView t1 = (TextView) view.findViewById(R.id.listHosp);
		t1.setText(data.getName().getHospital());
		TextView t2 = (TextView) view.findViewById(R.id.listSpec);
		t2.setText(data.getName().getSpecialization());

		return view;
	}

	public class DoctorData {

		private Doctor d;

		private boolean selected;

		public DoctorData(Doctor d, boolean selected) {
			super();
			this.d = d;
			this.selected = selected;
		}

		public Doctor getName() {
			return d;
		}

		public void setName(Doctor d) {
			this.d = d;
		}

		public boolean isSelected() {
			return selected;
		}

		public void setSelected(boolean selected) {
			this.selected = selected;
		}

	}

	@Override
	public Filter getFilter() {

		Filter filter = new Filter() {

			@SuppressWarnings("unchecked")
			@Override
			protected void publishResults(CharSequence constraint, FilterResults results) {

				cbList = (List<DoctorData>) results.values;
				notifyDataSetChanged();
			}

			@Override
			protected FilterResults performFiltering(CharSequence constraint) {

				FilterResults results = new FilterResults();
				ArrayList<DoctorData> FilteredArrayNames = new ArrayList<DoctorData>();

				// perform your search here using the searchConstraint String.
				
				if(constraint.length() == 0) {
					for (int i = 0; i < dataList.size(); i++) {
						FilteredArrayNames.add((DoctorData) dataList.get(i));
					}
					results.count = FilteredArrayNames.size();
					results.values = FilteredArrayNames;
					
					return results;
				}
				
				constraint = constraint.toString().toLowerCase();
				for (int i = 0; i < dataList.size(); i++) {
					Doctor dataNames = ((DoctorData) dataList.get(i)).getName();
					if (dataNames.getFirstName().toLowerCase().startsWith(constraint.toString()))  {
						FilteredArrayNames.add((DoctorData) dataList.get(i));
					} else if (dataNames.getLastName().toLowerCase().startsWith(constraint.toString()))  {
						FilteredArrayNames.add((DoctorData) dataList.get(i));
					} else if (dataNames.getHospital().toLowerCase().startsWith(constraint.toString()))  {
						FilteredArrayNames.add((DoctorData) dataList.get(i));
					} else if (dataNames.getSpecialization().toLowerCase().startsWith(constraint.toString()))  {
						FilteredArrayNames.add((DoctorData) dataList.get(i));
					}
				}

				results.count = FilteredArrayNames.size();
				results.values = FilteredArrayNames;
				Log.e("VALUES", results.values.toString());

				return results;
			}

		};

		return filter;
	}
}