package com.kelandoc.db;

public class User {
	private int UserID;
	private String Username;
	private String Password;
	private int User_Type;
	
	public static int PATIENT = 0, DOCTOR = 1;
	
	public User(){}
	
	public User(String Username, String Password, int User_Type){
		this.Username = Username;
		this.Password = Password;
		this.User_Type = User_Type;
	}
	
	public String getUsername() {
		return Username;
	}
	public void setUsername(String Username) {
		this.Username = Username;
	}
	
	public String getPassword() {
		return Password;
	}
	public void setPassword(String Password) {
		this.Password = Password;
	}
	
	public int getUserType() {
		return User_Type;
	}
	public void setUserType(int User_Type) {
		this.User_Type = User_Type;
	}
	
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int id) {
		this.UserID = id;
	}
	
	
}
