package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class APILabTestReportModel {

	private String patientid;
	private String patientname;
	private String age;
	private String gender;
	private String weight;
	private String height;
	private String testdate;
	private String phc;
	private String medtelid;

	public APILabTestReportModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public APILabTestReportModel(Object patientid, Object patientname, Object age, Object gender, Object weight,
			Object height, Object testdate, Object phc, Object medtelid) {
		super();
		this.patientid = (String) patientid;
		this.patientname = (String) patientname;
		this.age = (String) age;
		this.gender = (String) gender;
		this.weight = (String) weight;
		this.height = (String) height;
		this.testdate = (String) testdate;
		this.phc = (String) phc;
		this.medtelid = (String) medtelid;
	}

	public String getPatientid() {
		return patientid;
	}

	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}

	public String getPatientname() {
		return patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
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

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getTestdate() {
		return testdate;
	}

	public void setTestdate(String testdate) {
		this.testdate = testdate;
	}

	public String getPhc() {
		return phc;
	}

	public void setPhc(String phc) {
		this.phc = phc;
	}

	public String getMedtelid() {
		return medtelid;
	}

	public void setMedtelid(String medtelid) {
		this.medtelid = medtelid;
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
