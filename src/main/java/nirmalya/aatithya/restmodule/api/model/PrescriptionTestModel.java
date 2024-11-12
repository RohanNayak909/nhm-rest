package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PrescriptionTestModel {

	
	private String testid; 
	private String testName;
	public PrescriptionTestModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public PrescriptionTestModel(Object testid, Object testName) {
		super();
		this.testid = (String)testid;
		this.testName = (String)testName;
	}


	public String getTestid() {
		return testid;
	}
	public void setTestid(String testid) {
		this.testid = testid;
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
