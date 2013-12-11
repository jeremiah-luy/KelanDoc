package com.kelandoc.db;

public class Doctor {
	private int UserID, DoctorID;
	private String Last_Name, First_Name, Specialization, Hospital;
	
	public Doctor(){
		
	}
	
	public Doctor(int userID, String lastName, String firstName, String specialization, String hospital){
		this.UserID = userID;
		this.Last_Name = lastName;
		this.First_Name = firstName;
		this.Specialization = specialization;
		this.Hospital = hospital;
	}
	
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int id) {
		this.UserID = id;
	}
	
	public int getDoctorID() {
		return DoctorID;
	}
	public void setDoctorID(int id) {
		this.DoctorID = id;
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

	public String getHospital() {
		return Hospital;
	}
	public void setHospital(String hospital) {
		this.Hospital = hospital;
	}
	
	public String getSpecialization() {
		return Specialization;
	}
	public void setSpecialization(String specialization) {
		this.Specialization = specialization;
	}
	
}
