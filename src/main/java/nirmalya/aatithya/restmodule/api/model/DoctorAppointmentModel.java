package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DoctorAppointmentModel {

	private String userid;
	private String date;
	private String casepaper;
	private String opdid;
	private String time;
	private String doctor;
	private String notes;
	private String hospitalid;
	private String appointid;
	private String apptype;

	public DoctorAppointmentModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DoctorAppointmentModel(String userid, String date, String casepaper, String opdid, String time, String doctor, String notes,
			String hospitalid) {
		super();
		this.userid = userid;
		this.date = date;
		this.casepaper = casepaper;
		this.opdid = opdid;
		this.time = time;
		this.doctor = doctor;
		this.notes = notes;
		this.hospitalid = hospitalid;
	}


	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCasepaper() {
		return casepaper;
	}

	public void setCasepaper(String casepaper) {
		this.casepaper = casepaper;
	}

	public String getOpdid() {
		return opdid;
	}

	public void setOpdid(String opdid) {
		this.opdid = opdid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getHospitalid() {
		return hospitalid;
	}

	public void setHospitalid(String hospitalid) {
		this.hospitalid = hospitalid;
	}

	public String getAppointid() {
		return appointid;
	}

	public void setAppointid(String appointid) {
		this.appointid = appointid;
	}

	public String getApptype() {
		return apptype;
	}

	public void setApptype(String apptype) {
		this.apptype = apptype;
	}

	@Override
	public String toString() {
		ObjectMapper mapperObj = new ObjectMapper();
		String jsonStr;
		try {
			jsonStr = mapperObj.writeValueAsString(this);
		} catch (IOException ex) {

			jsonStr = ex.toString();
		}
		return jsonStr;
	}
}
