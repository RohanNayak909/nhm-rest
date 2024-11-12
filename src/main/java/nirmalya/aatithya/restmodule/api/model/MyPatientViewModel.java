package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MyPatientViewModel {

	private String patientId;
	private String patName;
	private String address;
	private String mobile;
	private String date;

	
	
	public MyPatientViewModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public MyPatientViewModel(Object patientId, Object patName, Object address, Object mobile, Object date) {
		super();
		this.patientId = (String) patientId;
		this.patName = (String) patName;
		this.address = (String) address;
		this.mobile = (String) mobile;
		this.date = (String) date;
	}



	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatName() {
		return patName;
	}

	public void setPatName(String patName) {
		this.patName = patName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
