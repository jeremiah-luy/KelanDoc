package com.kelandoc.db;

public class Timeslot {
	private int Time;
	private int DoctorID;
	
	public static final int TIMESLOTS[] = {0, 30, 100, 130, 200, 230,
									300, 330, 400, 430, 500, 530,
									600, 630, 700, 730, 800, 830,
									900, 930, 1000, 1030, 1100, 1130,
									1200, 1230, 1300, 1330, 1400, 1430,
									1500, 1530, 1600, 1630, 1700, 1730,
									1800, 1830, 1900, 1930, 2000, 2030,
									2100, 2130, 2200, 2230, 2300, 2330};
	
	public Timeslot(int DoctorID, int TimeIndex){
		this.DoctorID = DoctorID;
		this.Time = TimeIndex;
	}
	
	public static String toTime(int index){
		String s = "";
		if(index < TIMESLOTS.length)
			s += TIMESLOTS[index] / 100;
		else s += "0";
		s += ":";
		if(index < TIMESLOTS.length)
			s += (TIMESLOTS[index] % 100 > 0)? "30": "00";
		else s += "00";
		return s;
	}
	
	public int getTime() {
		return Time;
	}
	public void setTime(int time) {
		Time = time;
	}
	
	public int getDoctorID() {
		return DoctorID;
	}
	public void setDoctorID(int doctor) {
		DoctorID = doctor;
	}
}
