package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AppointmentListModel {

	private String userid;
	private String doctorName;
	private String speciality;
	private String appdate;
	private String apptime;
	private String notes;
	private String status;
	
	private String appmonth;
	private String appyear;
	private String patname;
	
	private String appno;
	
	private String doctorid;
	private String docedu;
	private String docexp;
	private String docrate;
	private String dochospital;
	
	private String age;
	private String gender;
	private String address;
	private String drName;
	private String typeofconsult;
	
	private String rating;
	private String review;
	

	public AppointmentListModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AppointmentListModel(Object userid, Object patname, Object appdate, Object appno) {
		super();
		this.userid = (String) userid;
		this.patname = (String) patname;
		this.appdate = (String) appdate;
		this.appno = (String) appno;
	}
	
	
	public AppointmentListModel(Object userid, Object patname, Object appdate, Object appmonth, Object appyear,
			Object notes, Object status, Object doctorName, Object age, Object gender, Object address,
			Object typeofconsult, Object docexp ,Object apptime) {
		super();
		this.userid = (String) userid;
		this.patname = (String) patname;
		this.appdate = (String) appdate;
		this.appmonth = (String) appmonth;
		this.appyear = (String) appyear;
		this.notes = (String) notes;
		this.status = (String) status;
		this.doctorName = (String) doctorName;
		this.age = (String) age;
		this.gender = (String) gender;
		this.address = (String) address;
		this.typeofconsult = (String) typeofconsult;
		this.docexp = (String) docexp;
		this.apptime = (String) apptime;
	}
	
	public AppointmentListModel(Object userid, Object patname, Object appdate, Object appmonth, Object appyear,
			Object notes, Object status, Object doctorName, Object age, Object gender, Object address,Object drName) {
		super();
		this.userid = (String) userid;
		this.patname = (String) patname;
		this.appdate = (String) appdate;
		this.appmonth = (String) appmonth;
		this.appyear = (String) appyear;
		this.notes = (String) notes;
		this.status = (String) status;
		this.doctorName = (String) doctorName;
		this.age = (String) age;
		this.gender = (String) gender;
		this.address = (String) address;
		this.drName = (String) drName;
		
	}

	public AppointmentListModel(Object userid, Object doctorName, Object speciality, Object appdate, Object apptime,
			Object notes, Object status, Object appno, Object patname, Object doctorid, Object docedu, Object docexp, 
			Object docrate, Object dochospital,Object typeofconsult) {
		super();
		this.userid = (String) userid;
		this.doctorName = (String) doctorName;
		this.speciality = (String) speciality;
		this.appdate = (String) appdate;
		this.apptime = (String) apptime;
		this.notes = (String) notes;
		this.status = (String) status;
		this.appno = (String) appno;
		this.patname = (String) patname;
		this.doctorid = (String) doctorid;
		this.docedu = (String) docedu;
		this.docexp = (String) docexp;
		this.docrate = (String) docrate;
		this.dochospital = (String) dochospital;
		this.typeofconsult = (String) typeofconsult;
	}
	
	public AppointmentListModel(Object userid, Object doctorName, Object speciality, Object appdate, Object apptime,
			Object notes, Object status, Object appno, Object patname, Object doctorid, Object docedu, Object docexp, 
			Object docrate, Object dochospital,Object typeofconsult, Object rating, Object review) {
		super();
		this.userid = (String) userid;
		this.doctorName = (String) doctorName;
		this.speciality = (String) speciality;
		this.appdate = (String) appdate;
		this.apptime = (String) apptime;
		this.notes = (String) notes;
		this.status = (String) status;
		this.appno = (String) appno;
		this.patname = (String) patname;
		this.doctorid = (String) doctorid;
		this.docedu = (String) docedu;
		this.docexp = (String) docexp;
		this.docrate = (String) docrate;
		this.dochospital = (String) dochospital;
		this.typeofconsult = (String) typeofconsult;
		this.rating = (String) rating;
		this.review = (String) review;
	}

	
	
	public String getTypeofconsult() {
		return typeofconsult;
	}

	public void setTypeofconsult(String typeofconsult) {
		this.typeofconsult = typeofconsult;
	}

	public String getDrName() {
		return drName;
	}

	public void setDrName(String drName) {
		this.drName = drName;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getAppdate() {
		return appdate;
	}

	public void setAppdate(String appdate) {
		this.appdate = appdate;
	}

	public String getApptime() {
		return apptime;
	}

	public void setApptime(String apptime) {
		this.apptime = apptime;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAppmonth() {
		return appmonth;
	}

	public void setAppmonth(String appmonth) {
		this.appmonth = appmonth;
	}

	public String getAppyear() {
		return appyear;
	}

	public void setAppyear(String appyear) {
		this.appyear = appyear;
	}

	public String getPatname() {
		return patname;
	}

	public void setPatname(String patname) {
		this.patname = patname;
	}

	public String getAppno() {
		return appno;
	}

	public void setAppno(String appno) {
		this.appno = appno;
	}

	public String getDoctorid() {
		return doctorid;
	}

	public void setDoctorid(String doctorid) {
		this.doctorid = doctorid;
	}

	public String getDocedu() {
		return docedu;
	}

	public void setDocedu(String docedu) {
		this.docedu = docedu;
	}

	public String getDocexp() {
		return docexp;
	}

	public void setDocexp(String docexp) {
		this.docexp = docexp;
	}

	public String getDocrate() {
		return docrate;
	}

	public void setDocrate(String docrate) {
		this.docrate = docrate;
	}

	public String getDochospital() {
		return dochospital;
	}

	public void setDochospital(String dochospital) {
		this.dochospital = dochospital;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
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
