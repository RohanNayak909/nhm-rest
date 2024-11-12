package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestPatientTestResultModelAPI {
	private String testName;
	private String result;
	private String refRange;
	private String unit;
	
	public RestPatientTestResultModelAPI(Object testName, Object result, Object unit,Object refRange) {
		super();
		this.testName = (String) testName;
		this.result = (String) result;
		this.unit = (String) unit;
		this.refRange = (String) refRange;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getRefRange() {
		return refRange;
	}

	public void setRefRange(String refRange) {
		this.refRange = refRange;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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
