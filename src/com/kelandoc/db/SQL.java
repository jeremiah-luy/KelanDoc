package com.kelandoc.db;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.kelandoc.ui.LoginActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQL extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "UserDB";
	public static boolean create = true;

	public SQL(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		onCreate(this.getWritableDatabase());
	}
	public void onCreate(SQLiteDatabase db) {
		if(!create)
			return;
		
		// SQL statement to create user table
		String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS User ( " +
				"ID INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				"Username VARCHAR, "+
				"Password VARCHAR, " +
				"Type INTEGER)";

		// create users table
		db.execSQL(CREATE_USER_TABLE);

		String CREATE_DOCTOR_TABLE = "CREATE TABLE IF NOT EXISTS Doctor ( " +
				"ID INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				"User_ID INTEGER, " + 
				"First_Name VARCHAR, " + 
				"Last_Name VARCHAR, " + 
				"Specialization VARCHAR, " + 
				"Hospital VARCHAR)";
		
		// create users table
		db.execSQL(CREATE_DOCTOR_TABLE);

		String CREATE_TIMESLOT_TABLE = "CREATE TABLE IF NOT EXISTS Timeslot ( " +
				"Timeslot_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"DoctorID INTEGER," +
				"Time INTEGER)";

		// create users table
		db.execSQL(CREATE_TIMESLOT_TABLE);

		String CREATE_RES_TABLE = "CREATE TABLE IF NOT EXISTS Reservation ( " +
				"ReservationID INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"Time INTEGER," +
				"Date DATE," +
				"DoctorID INTEGER," +
				"PatientID INTEGER," +
				"Accomplished VARCHAR)";

		// create users table
		db.execSQL(CREATE_RES_TABLE);

		String CREATE_PATIENT_TABLE = "CREATE TABLE IF NOT EXISTS Patient ( " +
				"PatientID INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"UserID INTEGER," +
				"Date DATE," +
				"Firstname VARCHAR," +
				"Lastname VARCHAR)";

		// create users table
		db.execSQL(CREATE_PATIENT_TABLE);
		
	}
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older users table if existed
		db.execSQL("DROP TABLE IF EXISTS user");

		// create fresh users table
		this.onCreate(db);
	}

	//Doctor Table
	private static final String TABLE_DOCTOR = "Doctor";

	private static final String TABLE_DOCTOR_DOCTORID = "ID";
	private static final String TABLE_DOCTOR_USERID = "User_ID";
	private static final String TABLE_DOCTOR_FIRSTNAME = "First_Name";
	private static final String TABLE_DOCTOR_LASTNAME = "Last_Name";
	private static final String TABLE_DOCTOR_SPECIALIZATION = "Specialization";
	private static final String TABLE_DOCTOR_HOSPITAL = "Hospital";

	//Doctor Table Columns
	private static final String[] TABLE_DOCTOR_COLUMNS = {TABLE_DOCTOR_DOCTORID,
		TABLE_DOCTOR_USERID,
		TABLE_DOCTOR_FIRSTNAME,
		TABLE_DOCTOR_LASTNAME,
		TABLE_DOCTOR_SPECIALIZATION,
		TABLE_DOCTOR_HOSPITAL};

	//ADD, EDIT, DELETE, RETURN
	public void addDoctor(Doctor u){

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues v = new ContentValues();

		v.put(TABLE_DOCTOR_USERID, u.getUserID());
		v.put(TABLE_DOCTOR_FIRSTNAME, u.getFirstName());
		v.put(TABLE_DOCTOR_LASTNAME, u.getLastName());
		v.put(TABLE_DOCTOR_SPECIALIZATION, u.getSpecialization());
		v.put(TABLE_DOCTOR_HOSPITAL, u.getHospital());

		db.insert(TABLE_DOCTOR, null, v);

		db.close();

	}
	public Doctor getDoctor(int id){

		SQLiteDatabase db = this.getReadableDatabase();

		Cursor c = 
				db.query(TABLE_DOCTOR, 					// TABLE NAME
						TABLE_DOCTOR_COLUMNS,								// COLUMNS
						" id = ?",								// WHERE CLAUSE
						new String[] { String.valueOf(id) },	//SELECTION ARGS
						null,									// GROUP BY CLAUSE
						null,									// HAVING CLAUSE
						null,									// ORDER BY CLAUSE
						null);									// LIMIT (?)

		if (c != null)
			c.moveToFirst();

		Doctor user = new Doctor();
		user.setDoctorID(Integer.parseInt(c.getString(0)));
		user.setUserID(Integer.parseInt(c.getString(1)));
		user.setFirstName(c.getString(2));
		user.setLastName(c.getString(3));
		user.setSpecialization(c.getString(4));
		user.setHospital(c.getString(5));

		db.close();

		return user;
	}
	public Doctor getDoctorUser(int id){

		SQLiteDatabase db = this.getReadableDatabase();

		Cursor c = 
				db.query(TABLE_DOCTOR, 					// TABLE NAME
						TABLE_DOCTOR_COLUMNS,								// COLUMNS
						" User_ID = ?",								// WHERE CLAUSE
						new String[] { String.valueOf(id) },	//SELECTION ARGS
						null,									// GROUP BY CLAUSE
						null,									// HAVING CLAUSE
						null,									// ORDER BY CLAUSE
						null);									// LIMIT (?)

		if (c != null)
			c.moveToFirst();

		Doctor user = new Doctor();
		user.setDoctorID(Integer.parseInt(c.getString(0)));
		user.setUserID(Integer.parseInt(c.getString(1)));
		user.setFirstName(c.getString(2));
		user.setLastName(c.getString(3));
		user.setSpecialization(c.getString(4));
		user.setHospital(c.getString(5));

		db.close();

		return user;
	}
	public int getDoctorID(String firstName, String lastName){

		SQLiteDatabase db = this.getReadableDatabase();

		Cursor c = 
				db.query(TABLE_DOCTOR, 								// TABLE NAME
						TABLE_DOCTOR_COLUMNS,						// COLUMNS
						" first_name = ? AND last_name = ?",		// WHERE CLAUSE
						new String[] { String.valueOf(firstName),
						String.valueOf(lastName)},	//SELECTION ARGS
						null,										// GROUP BY CLAUSE
						null,										// HAVING CLAUSE
						null,										// ORDER BY CLAUSE
						null);										// LIMIT (?)

		if (c != null)
			c.moveToFirst();

		db.close();

		return Integer.parseInt(c.getString(0));

	}
	public List<Doctor> getAllDoctors() {

		List<Doctor> userList = new LinkedList<Doctor>();
		String query = "SELECT * FROM " + TABLE_DOCTOR;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery(query, null);

		Doctor user = null;
		if (c.moveToFirst()) {
			do {

				user = new Doctor();
				user.setDoctorID(Integer.parseInt(c.getString(0)));
				user.setUserID(Integer.parseInt(c.getString(1)));
				user.setFirstName(c.getString(2));
				user.setLastName(c.getString(3));
				user.setSpecialization(c.getString(4));
				user.setHospital(c.getString(5));

				userList.add(user);
			} while (c.moveToNext());
		}

		db.close();

		return userList;
	}
	public int updateDoctor(Doctor d) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues v = new ContentValues();


		v.put(TABLE_DOCTOR_USERID, d.getUserID());
		v.put(TABLE_DOCTOR_FIRSTNAME, d.getFirstName());
		v.put(TABLE_DOCTOR_LASTNAME, d.getLastName());
		v.put(TABLE_DOCTOR_SPECIALIZATION, d.getSpecialization());
		v.put(TABLE_DOCTOR_HOSPITAL, d.getHospital());

		int i = db.update(TABLE_DOCTOR, 						// TABLE NAME
				v,											// VALUES
				TABLE_DOCTOR_DOCTORID + " = ?",									// WHERE CLAUSE
				new String[] { String.valueOf(d.getDoctorID()) });	// SELECTION ARGS

		db.close();

		return i;
	}
	public void deleteDoctor(Doctor d) {

		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(TABLE_DOCTOR,										// TABLE NAME
				TABLE_DOCTOR_DOCTORID + " = ?",									// WHERE CLAUSE
				new String[] { String.valueOf(d.getDoctorID()) });		// SELECTION ARGS

		db.close();

	}
	public void deleteAllDoctors() {

		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(TABLE_DOCTOR, null, null);

		db.close();

	}

	//User Table
	private static final String TABLE_USER = "User";

	private static final String TABLE_USER_USERID = "ID";
	private static final String TABLE_USER_USERNAME = "Username";
	private static final String TABLE_USER_PASSWORD = "Password";
	private static final String TABLE_USER_USERTYPE = "Type";

	//User Table Columns
	private static final String[] TABLE_USER_COLUMNS = {TABLE_USER_USERID,
		TABLE_USER_USERNAME,
		TABLE_USER_PASSWORD,
		TABLE_USER_USERTYPE};

	//ADD, EDIT, DELETE, RETURN
	public int countUsers(){
		
		SQLiteDatabase db = this.getReadableDatabase();
		
		int users = 0;
		Cursor c = 
				db.query(TABLE_USER, 					// TABLE NAME
						TABLE_USER_COLUMNS,								// COLUMNS
						"",								// WHERE CLAUSE
						null,	//SELECTION ARGS
						null,									// GROUP BY CLAUSE
						null,									// HAVING CLAUSE
						null,									// ORDER BY CLAUSE
						null);									// LIMIT (?)

		if (c != null)
			c.moveToFirst();
		do {
			users++;
		} while(c.moveToNext());
		
		db.close();

		return users;
	}
	public void addUser(User u){

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues v = new ContentValues();

		Log.d("HELLO", "ASDSD");

		v.put(TABLE_USER_USERTYPE, u.getUserType());
		v.put(TABLE_USER_PASSWORD, u.getPassword());
		v.put(TABLE_USER_USERNAME, u.getUsername());

		db.insert(TABLE_USER, null, v);

		db.close();

	}
	public User getUser(int id){

		SQLiteDatabase db = this.getReadableDatabase();

		Cursor c = 
				db.query(TABLE_USER, 					// TABLE NAME
						TABLE_USER_COLUMNS,								// COLUMNS
						" id = ?",								// WHERE CLAUSE
						new String[] { String.valueOf(id) },	//SELECTION ARGS
						null,									// GROUP BY CLAUSE
						null,									// HAVING CLAUSE
						null,									// ORDER BY CLAUSE
						null);									// LIMIT (?)

		if (c != null)
			c.moveToFirst();

		User user = new User();
		user.setUserID(Integer.parseInt(c.getString(0)));
		user.setUsername(c.getString(1));
		user.setPassword(c.getString(2));
		user.setUserType(Integer.parseInt(c.getString(3)));

		db.close();

		return user;
	}
	public int getUserID(String username){

		SQLiteDatabase db = this.getReadableDatabase();

		Cursor c = 
				db.query(TABLE_USER, 					// TABLE NAME
						TABLE_USER_COLUMNS,								// COLUMNS
						" username = ?",								// WHERE CLAUSE
						new String[] { String.valueOf(username) },	//SELECTION ARGS
						null,									// GROUP BY CLAUSE
						null,									// HAVING CLAUSE
						null,									// ORDER BY CLAUSE
						null);									// LIMIT (?)

		if (c != null)
			c.moveToFirst();

		db.close();

		return Integer.parseInt(c.getString(0));
	}
	public List<User> getAllUsers() {

		List<User> userList = new LinkedList<User>();
		String query = "SELECT * FROM " + TABLE_USER;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);

		User user = null;
		if (cursor.moveToFirst()) {
			do {
				user = new User();

				user.setUserID(Integer.parseInt(cursor.getString(0)));
				user.setUsername(cursor.getString(1));
				user.setPassword(cursor.getString(2));
				user.setUserType(Integer.parseInt(cursor.getString(3)));

				Log.d("HELLO", "ID: " + user.getUserID() + " " +
						"Username: " + user.getUsername() + " " +
						"Password: " + user.getPassword() + " " +
						"Type: " + user.getUserType() + " " );

				userList.add(user);
			} while (cursor.moveToNext());
		}

		db.close();

		return userList;
	}
	public int updateUser(User u) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues v = new ContentValues();

		v.put(TABLE_USER_USERNAME, u.getUsername());
		v.put(TABLE_USER_PASSWORD, u.getPassword());
		v.put(TABLE_USER_USERTYPE, u.getUserType());

		int i = db.update(TABLE_USER, 							// TABLE NAME
				v,											// VALUES
				TABLE_USER_USERID + " = ?",									// WHERE CLAUSE
				new String[] { String.valueOf(u.getUserID()) });	// SELECTION ARGS

		db.close();

		return i;
	}
	public void deleteUser(User u) {

		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(TABLE_USER,										// TABLE NAME
				TABLE_USER_USERID + " = ?",									// WHERE CLAUSE
				new String[] { String.valueOf(u.getUserID()) });		// SELECTION ARGS

		db.close();

	}
	public void deleteAllUsers() {

		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(TABLE_USER, null, null);

		db.close();

	}

	//Timeslot Table
	private static final String TABLE_TIMESLOT = "Timeslot";
	private static final String TABLE_TIMESLOT_DOCTORID = "DoctorID";
	private static final String TABLE_TIMESLOT_TIME = "Time";

	//Timeslot Table Columns
	private static final String[] TABLE_TIMESLOT_COLUMNS = {TABLE_TIMESLOT_DOCTORID,
		TABLE_TIMESLOT_TIME};

	//ADD, EDIT, DELETE, RETURN
	public void addTimeslot(Timeslot t){

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues v = new ContentValues();

		Log.d("HELLO", "Adding Timeslot: " + t.getTime() + " " + t.getDoctorID());

		v.put(TABLE_TIMESLOT_DOCTORID, t.getDoctorID());
		v.put(TABLE_TIMESLOT_TIME, t.getTime());

		db.insert(TABLE_TIMESLOT, null, v);

		db.close();

	}
	public Timeslot getTimeslot(int id){

		SQLiteDatabase db = this.getReadableDatabase();

		Cursor c = 
				db.query(TABLE_TIMESLOT, 						// TABLE NAME
						TABLE_TIMESLOT_COLUMNS,					// COLUMNS
						" id = ?",								// WHERE CLAUSE
						new String[] { String.valueOf(id) },	//SELECTION ARGS
						null,									// GROUP BY CLAUSE
						null,									// HAVING CLAUSE
						null,									// ORDER BY CLAUSE
						null);									// LIMIT (?)

		if (c != null)
			c.moveToFirst();

		Timeslot t = new Timeslot(Integer.parseInt(c.getString(0)), Integer.parseInt(c.getString(1)));

		db.close();

		return t;
	}
	public ArrayList<Integer> getDocTimes(int docID){

		SQLiteDatabase db = this.getReadableDatabase();

		ArrayList<Integer> t = new ArrayList<Integer>();

		Cursor c = 
				db.query(TABLE_TIMESLOT, 						// TABLE NAME
						TABLE_TIMESLOT_COLUMNS,					// COLUMNS
						"",										// WHERE CLAUSE
						null,									//SELECTION ARGS
						null,									// GROUP BY CLAUSE
						null,									// HAVING CLAUSE
						null,									// ORDER BY CLAUSE
						null);									// LIMIT (?)

		if (c != null)
			c.moveToFirst();
		
		Log.d("HELLO", "Column Count: " + c.getColumnCount());
		for(String s : c.getColumnNames())
			Log.d("HELLO", "Column Names: " + s);
		
		do{
			int ID = c.getInt(0), TimeIndex = Integer.valueOf(c.getInt(1));
			if(ID == docID)
				t.add(TimeIndex);
			String s = "Doctor: " + ID + " == " + docID + " Time: " + TimeIndex;
			Log.d("HELLO", s);
		} while(c.moveToNext());

		db.close();

		return t;
	}
	public List<Timeslot> getAllTimeslot() {

		List<Timeslot> timeList = new LinkedList<Timeslot>();
		String query = "SELECT * FROM " + TABLE_TIMESLOT;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery(query, null);

		Timeslot t = null;
		if (c.moveToFirst()) {
			do {

				t = new Timeslot(Integer.parseInt(c.getString(1)), Integer.parseInt(c.getString(2)));

				timeList.add(t);
			} while (c.moveToNext());
		}

		db.close();

		return timeList;
	}
	public int updateTimeslot(Timeslot t) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues v = new ContentValues();

		v.put(TABLE_TIMESLOT_DOCTORID, t.getDoctorID());
		v.put(TABLE_TIMESLOT_TIME, t.getTime());

		int i = db.update(TABLE_TIMESLOT, 							// TABLE NAME
				v,											// VALUES
				TABLE_TIMESLOT_DOCTORID + " = ?",									// WHERE CLAUSE
				new String[] { String.valueOf(t.getDoctorID()) });	// SELECTION ARGS

		db.close();

		return i;
	}
	public void deleteTimeslot(Timeslot t) {

		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(TABLE_TIMESLOT,										// TABLE NAME
				TABLE_TIMESLOT_DOCTORID + " = ? AND " + TABLE_TIMESLOT_TIME + " = ?",								// WHERE CLAUSE
				new String[] { String.valueOf(t.getDoctorID()), String.valueOf(t.getTime())});		// SELECTION ARGS

		db.close();

	}
	public void deleteAllTimeslots() {

		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(TABLE_TIMESLOT, null, null);

		db.close();

	}


	// ---------------------------- RESERVATION -----------------

	private static final String TABLE_RESERVATION_TIME = "Time";
	private static final String TABLE_RESERVATION_RESERVATIONID = "ReservationID";
	private static final String TABLE_RESERVATION_DATE = "Date";
	private static final String TABLE_RESERVATION_DOCTORID = "DoctorID";
	private static final String TABLE_RESERVATION_PATIENTID = "PatientID";
	private static final String TABLE_RESERVATION_ACCOMPLISHED = "Accomplished";

	//User Table
	private static final String TABLE_RESERVATION = "Reservation";
	//User Table Columns
	private static final String[] TABLE_RESERVATION_COLUMNS = {TABLE_RESERVATION_TIME,
		TABLE_RESERVATION_RESERVATIONID,
		TABLE_RESERVATION_DATE,
		TABLE_RESERVATION_DOCTORID,
		TABLE_RESERVATION_PATIENTID,
		TABLE_RESERVATION_ACCOMPLISHED};


	//ADD, EDIT, DELETE, RETURN
	public void addReservation(Reservation r){

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues v = new ContentValues();

		v.put(TABLE_RESERVATION_TIME, r.getTime());
		v.put(TABLE_RESERVATION_DATE, r.getDate());
		v.put(TABLE_RESERVATION_DOCTORID, r.getDoctorID());
		v.put(TABLE_RESERVATION_PATIENTID, r.getPatientID());
		v.put(TABLE_RESERVATION_ACCOMPLISHED, r.isAccomplished());

		Log.d("HELLO", "[Adding Reservation] Time: " + r.getTime() + ", Date: " + r.getDate() + ", Doc: " + r.getDoctorID() + ", Pat: " + r.getPatientID());

		db.insert(TABLE_RESERVATION, null, v);

		db.close();

	}
	public Reservation getReservation(int id){

		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = 
				db.query(TABLE_RESERVATION, 					// TABLE NAME
						TABLE_RESERVATION_COLUMNS,						// COLUMNS
						" id = ?",								// WHERE CLAUSE
						new String[] { String.valueOf(id) },	//SELECTION ARGS
						null,									// GROUP BY CLAUSE
						null,									// HAVING CLAUSE
						null,									// ORDER BY CLAUSE
						null);									// LIMIT (?)

		if (cursor != null)
			cursor.moveToFirst();

		Reservation reservation = new Reservation();
		reservation.setTime(cursor.getInt(0));
		reservation.setReservationID(Integer.parseInt(cursor.getString(1)));
		reservation.setDate(cursor.getString(2));
		reservation.setDoctorID(Integer.parseInt(cursor.getString(3)));
		reservation.setPatientID(Integer.parseInt(cursor.getString(4)));
		reservation.setAccomplished(cursor.getString(5).equals("true"));

		db.close();

		return reservation;
	}
	public List<Reservation> getAllReservation() {

		List<Reservation> reservationList = new LinkedList<Reservation>();
		String query = "SELECT * FROM " + TABLE_RESERVATION;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);

		Reservation reservation = null;
		if (cursor.moveToFirst()) {
			do {
				reservation = new Reservation();
				reservation.setTime(cursor.getInt(0));
				reservation.setReservationID(Integer.parseInt(cursor.getString(1)));
				reservation.setDate(cursor.getString(2));
				reservation.setDoctorID(Integer.parseInt(cursor.getString(3)));
				reservation.setPatientID(Integer.parseInt(cursor.getString(4)));
				reservation.setAccomplished(cursor.getString(5).equals("true"));

				reservationList.add(reservation);
			} while (cursor.moveToNext());
		}

		db.close();

		return reservationList;
	}
	public List<Reservation> getDocReservation(int id) {

		List<Reservation> reservationList = new LinkedList<Reservation>();
		String query = "SELECT * FROM " + TABLE_RESERVATION;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);

		Reservation reservation = null;
		if (cursor.moveToFirst()) {
			do {
				reservation = new Reservation();
				reservation.setReservationID(Integer.parseInt(cursor.getString(0)));
				reservation.setTime(cursor.getInt(1));
				reservation.setDate(cursor.getString(2));
				reservation.setDoctorID(Integer.parseInt(cursor.getString(3)));
				reservation.setPatientID(Integer.parseInt(cursor.getString(4)));
				reservation.setAccomplished(cursor.getString(5).equals("true"));
				
				if(Integer.parseInt(cursor.getString(3)) == id) {
					reservationList.add(reservation);
					Log.d("HELLO", reservation.toString());
				}
				
			} while (cursor.moveToNext());
		}

		db.close();

		return reservationList;
	}
	public List<Reservation> getPatReservation(int id) {

		List<Reservation> reservationList = new ArrayList<Reservation>();
		String query = "SELECT * FROM " + TABLE_RESERVATION + " WHERE " + TABLE_RESERVATION_PATIENTID + "=" + id;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);

		Reservation reservation = null;
		
		if (cursor != null && cursor.moveToFirst()) {
			do {
				reservation = new Reservation();
				reservation.setReservationID(Integer.parseInt(cursor.getString(0)));
				reservation.setTime(cursor.getInt(1));
				reservation.setDate(cursor.getString(2));
				reservation.setDoctorID(Integer.parseInt(cursor.getString(3)));
				reservation.setPatientID(Integer.parseInt(cursor.getString(4)));
				reservation.setAccomplished(cursor.getString(5).equals("true"));
				
				reservationList.add(reservation);
				if(Integer.parseInt(cursor.getString(4)) == id) {
					Log.d("HELLO", reservation.toString());
				}
				
			} while (cursor.moveToNext());
		}

		db.close();

		return reservationList;
	}
	
	public int updateReservation(Reservation r) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues v = new ContentValues();

		v.put(TABLE_RESERVATION_TIME, r.getTime());
		v.put(TABLE_RESERVATION_DATE, r.getDate());
		v.put(TABLE_RESERVATION_DOCTORID, r.getDoctorID());
		v.put(TABLE_RESERVATION_PATIENTID, r.getPatientID());
		v.put(TABLE_RESERVATION_ACCOMPLISHED, r.isAccomplished());


		int i = db.update(TABLE_RESERVATION, 							// TABLE NAME
				v,											// VALUES
				TABLE_RESERVATION_RESERVATIONID +" = ?",									// WHERE CLAUSE
				new String[] { String.valueOf(r.getDoctorID()) });	// SELECTION ARGS

		db.close();

		return i;
	}
	public void deleteReservation(Reservation reservation) {

		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(TABLE_RESERVATION,										// TABLE NAME
				TABLE_RESERVATION_RESERVATIONID + " = ?",									// WHERE CLAUSE
				new String[] { String.valueOf(reservation.getReservationID()) });		// SELECTION ARGS

		db.close();

	}
	public void deleteAllReservations() {

		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(TABLE_RESERVATION, null, null);

		db.close();

	}




	//patients----------------------------------------------------
	private static final String TABLE_PATIENT_USERID = "UserID";
	private static final String TABLE_PATIENT_PATIENTID = "PatientID";
	private static final String TABLE_PATIENT_FIRSTNAME = "Firstname";
	private static final String TABLE_PATIENT_LASTNAME = "Lastname";


	//User Table
	private static final String TABLE_PATIENT = "Patient";
	//User Table Columns
	private static final String[] TABLE_PATIENT_COLUMNS = {TABLE_PATIENT_USERID,
		TABLE_PATIENT_PATIENTID,
		TABLE_PATIENT_FIRSTNAME,
		TABLE_PATIENT_LASTNAME};


	//ADD, EDIT, DELETE, RETURN
	public void addPatient(Patient p){

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues v = new ContentValues();

		v.put(TABLE_PATIENT_USERID, p.getUserID());
		v.put(TABLE_PATIENT_FIRSTNAME, p.getFirstName());
		v.put(TABLE_PATIENT_LASTNAME, p.getLastName());

		db.insert(TABLE_PATIENT, null, v);

		db.close();

	}
	public Patient getPatient(int id){

		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = 
				db.query(TABLE_PATIENT, 					// TABLE NAME
						TABLE_PATIENT_COLUMNS,								// COLUMNS
						" PatientID = ?",								// WHERE CLAUSE
						new String[] { String.valueOf(id) },	//SELECTION ARGS
						null,									// GROUP BY CLAUSE
						null,									// HAVING CLAUSE
						null,									// ORDER BY CLAUSE
						null);									// LIMIT (?)

		if (cursor != null)
			cursor.moveToFirst();

		Patient patient = new Patient();
		patient.setUserID(Integer.parseInt(cursor.getString(0)));
		patient.setPatientID(Integer.parseInt(cursor.getString(1)));
		patient.setFirstName(cursor.getString(2));
		patient.setLastName(cursor.getString(3));

		db.close();

		return patient;
	}
	public Patient getPatientUser(int user_id){

		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = 
				db.query(TABLE_PATIENT, 					// TABLE NAME
						TABLE_PATIENT_COLUMNS,						// COLUMNS
						" UserID = ?",											// WHERE CLAUSE
						new String[] { Integer.toString(user_id) },										//SELECTION ARGS
						null,										// GROUP BY CLAUSE
						null,										// HAVING CLAUSE
						null,										// ORDER BY CLAUSE
						null);										// LIMIT (?)

		if (cursor != null)
			cursor.moveToFirst();

		Patient patient = new Patient();
		patient.setUserID(Integer.parseInt(cursor.getString(0)));
		patient.setPatientID(Integer.parseInt(cursor.getString(1)));
		patient.setFirstName(cursor.getString(2));
		patient.setLastName(cursor.getString(3));

		db.close();

		return patient;
	}
	public int getPatientID(String firstName, String lastName){

		SQLiteDatabase db = this.getReadableDatabase();

		Cursor c = 
				db.query(TABLE_PATIENT, 								// TABLE NAME
						TABLE_PATIENT_COLUMNS,						// COLUMNS
						" FirstName = ? AND LastName = ?",		// WHERE CLAUSE
						new String[] { String.valueOf(firstName),
						String.valueOf(lastName)},	//SELECTION ARGS
						null,										// GROUP BY CLAUSE
						null,										// HAVING CLAUSE
						null,										// ORDER BY CLAUSE
						null);										// LIMIT (?)

		if (c != null)
			c.moveToFirst();

		db.close();

		return Integer.parseInt(c.getString(0));

	}

	public List<Patient> getAllPatient() {

		List<Patient> PatientList = new LinkedList<Patient>();
		String query = "SELECT * FROM " + TABLE_PATIENT;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);


		if (cursor.moveToFirst()) {
			do {
				Patient patient = new Patient();
				patient.setUserID(Integer.parseInt(cursor.getString(0)));
				patient.setPatientID(Integer.parseInt(cursor.getString(1)));
				patient.setFirstName(cursor.getString(2));
				patient.setLastName(cursor.getString(3));

				PatientList.add(patient);
			} while (cursor.moveToNext());
		}

		db.close();

		return PatientList;
	}
	public int updatePatient(Patient p) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues v = new ContentValues();

		v.put(TABLE_PATIENT_USERID, p.getUserID());
		v.put(TABLE_PATIENT_PATIENTID, p.getPatientID());
		v.put(TABLE_PATIENT_FIRSTNAME, p.getFirstName());
		v.put(TABLE_PATIENT_LASTNAME, p.getLastName());

		int i = db.update(TABLE_PATIENT, 							// TABLE NAME
				v,											// VALUES
				TABLE_PATIENT_PATIENTID +" = ?",									// WHERE CLAUSE
				new String[] { String.valueOf(p.getPatientID()) });	// SELECTION ARGS

		db.close();

		return i;
	}
	public void deletePatient(Patient patient) {

		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(TABLE_PATIENT,										// TABLE NAME
				TABLE_PATIENT_PATIENTID + " = ?",									// WHERE CLAUSE
				new String[] { String.valueOf(patient.getPatientID()) });		// SELECTION ARGS

		db.close();

	}
	public void deleteAllPatient() {

		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(TABLE_PATIENT, null, null);

		db.close();

	}


}