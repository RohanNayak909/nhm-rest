package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestDoctorModel {
	private String testId;
	private String testName;
	private String doctorId;
	private String doctorName;

	public RestDoctorModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestDoctorModel(Object testId, Object testName) {
		super();
		this.testId = (String) testId;
		this.testName = (String) testName;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
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
