package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;


public class RestPatientTestNameModelAPI {
	private String testGroupName;
	private String testDate;
	private List<RestPatientTestResultModelAPI> testlist;

	public RestPatientTestNameModelAPI(Object testGroupName, Object testDate) {
		super();
		this.testGroupName = (String) testGroupName;
		this.testDate = (String) testDate;
	}

	public String getTestGroupName() {
		return testGroupName;
	}

	public void setTestGroupName(String testGroupName) {
		this.testGroupName = testGroupName;
	}

	public String getTestDate() {
		return testDate;
	}

	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}

	public List<RestPatientTestResultModelAPI> getTestlist() {
		return testlist;
	}

	public void setTestlist(List<RestPatientTestResultModelAPI> testlist) {
		this.testlist = testlist;
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
