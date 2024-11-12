package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestVenderApiTestModel {
	private String vleId;
	private String patientId;
	private String testId;
	private String testName;
	private String status;
	private String venderId;
	private String venderName;
	private String type;
	
	public RestVenderApiTestModel() {
		super();
		// TODO Auto-generated constructor stub
	}



	public RestVenderApiTestModel(Object vleId, Object patientId, Object testId, Object testName, Object status,Object type,
			Object venderId, Object venderName) {
		super();
		this.vleId = (String) vleId;
		this.patientId = (String) patientId;
		this.testId = (String) testId;
		this.testName = (String) testName;
		this.status = (String) status;
		this.type = (String) type;
		this.venderId = (String) venderId;
		this.venderName = (String) venderName;
	}
	

	  
	public RestVenderApiTestModel( Object testName,Object testId,Object type,Object patientId,
			Object venderId, Object venderName, Object status) {
				super();
				
				this.testName = (String) testName;
				this.testId = (String) testId;
				this.type = (String) type;
				this.patientId = (String) patientId;
				this.venderId = (String) venderId;
				this.venderName = (String) venderName;
				this.status = (String) status;
				
			
			}
	public String getVleId() {
		return vleId;
	}
	public void setVleId(String vleId) {
		this.vleId = vleId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVenderId() {
		return venderId;
	}
	public void setVenderId(String venderId) {
		this.venderId = venderId;
	}
	public String getVenderName() {
		return venderName;
	}
	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
