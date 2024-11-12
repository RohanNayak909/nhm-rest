package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestPatientReportModel {
	private String patientId;
	private String nurseName;
	private String coOrdinatorName;
	private String dateOfVisit;
	private String patientName;
	private String age;
	
	private String phno;
	private String gender;
	private String nationality;
	private String address;
	public RestPatientReportModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RestPatientReportModel(Object patientId, Object nurseName, Object coOrdinatorName, Object dateOfVisit,
			Object patientName, Object age, Object phno, Object gender, Object nationality, Object address) {
		super();
		this.patientId = (String)patientId;
		this.nurseName = (String)nurseName;
		this.coOrdinatorName = (String)coOrdinatorName;
		this.dateOfVisit = (String)dateOfVisit;
		this.patientName = (String)patientName;
		this.age = (String)age;
		this.phno = (String)phno;
		this.gender = (String)gender;
		this.nationality = (String)nationality;
		this.address = (String)address;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getNurseName() {
		return nurseName;
	}
	public void setNurseName(String nurseName) {
		this.nurseName = nurseName;
	}
	public String getCoOrdinatorName() {
		return coOrdinatorName;
	}
	public void setCoOrdinatorName(String coOrdinatorName) {
		this.coOrdinatorName = coOrdinatorName;
	}
	public String getDateOfVisit() {
		return dateOfVisit;
	}
	public void setDateOfVisit(String dateOfVisit) {
		this.dateOfVisit = dateOfVisit;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPhno() {
		return phno;
	}
	public void setPhno(String phno) {
		this.phno = phno;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
