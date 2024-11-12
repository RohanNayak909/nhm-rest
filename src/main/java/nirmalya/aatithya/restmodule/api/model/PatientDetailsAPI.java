package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PatientDetailsAPI {

	private String patient_id;
	private String patientName;
	private String gender;
	private String age;
	private String mobile;
	private String city;
	private String aadhar;

	public PatientDetailsAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public PatientDetailsAPI(Object patient_id, Object patientName, Object gender, Object age, Object mobile,
			Object city, Object aadhar) {
		super();
		this.patient_id = (String) patient_id;
		this.patientName = (String) patientName;
		this.gender = (String) gender;
		this.age = (String) age;
		this.mobile = (String) mobile;
		this.city = (String) city;
		this.aadhar = (String) aadhar;
	}

	public String getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
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
