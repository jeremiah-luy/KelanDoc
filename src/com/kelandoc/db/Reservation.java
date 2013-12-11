package com.kelandoc.db;

public class Reservation {
	private int Time;
	private int ReservationID;
	private String date;
	private int DoctorID;
	private int PatientID;
	private boolean Accomplished;
	private Doctor d;
	private Patient p;
	
	public String toString(){
		return "Time: " + Time + " Date: " + date + " Doc: " + DoctorID + " Pat: " + PatientID;
	}
	
	public Doctor getD() {
		return d;
	}

	public void setD(Doctor d) {
		this.d = d;
	}

	public Patient getP() {
		return p;
	}

	public void setP(Patient p) {
		this.p = p;
	}

	public Reservation(){
		
	}
	
	public Reservation(int Time, String Date, int DocID, int PatID){
		setTime(Time);
		setDate(Date);
		setDoctorID(DocID);
		setPatientID(PatID);
		setAccomplished(false);
	}
	
	public int getTime() {
		return Time;
	}
	public void setTime(int time) {
		Time = time;
	}
	public int getReservationID() {
		return ReservationID;
	}
	public void setReservationID(int reservationID) {
		ReservationID = reservationID;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getDoctorID() {
		return DoctorID;
	}
	public void setDoctorID(int doctor) {
		DoctorID = doctor;
	}
	public int getPatientID() {
		return PatientID;
	}
	public void setPatientID(int patient) {
		PatientID = patient;
	}

	public boolean isAccomplished(){
		return Accomplished;
	}
	public void setAccomplished(boolean Accomplished){
		this.Accomplished = Accomplished;
	}
}
