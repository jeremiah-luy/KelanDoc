package com.kelandoc.db;

public class Patient {
	private int UserID, PatientID;
	private String Last_Name, First_Name;
	
	public Patient(){
		
	}
	
	public String toString(){
		return "ID: " + PatientID + " Last Name: " + Last_Name + " First Name: " + First_Name;
	}
	
	public Patient(int UserID, String lastName, String firstName){
		this.UserID = UserID;
		this.Last_Name = lastName;
		this.First_Name = firstName;
	}
	
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int id) {
		this.UserID = id;
	}
	
	public int getPatientID() {
		return PatientID;
	}
	public void setPatientID(int id) {
		this.PatientID = id;
	}
	
	public String getLastName() {
		return Last_Name;
	}
	public void setLastName(String lastName) {
		this.Last_Name = lastName;
	}
	
	public String getFirstName() {
		return First_Name;
	}
	public void setFirstName(String firstName) {
		this.First_Name = firstName;
	}
}
