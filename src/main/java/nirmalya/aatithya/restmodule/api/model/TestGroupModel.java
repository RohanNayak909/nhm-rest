package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestGroupModel {

	private String testgroup;
	private String testname;
	private String testresult;

	public TestGroupModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TestGroupModel(Object testgroup, Object testname, Object testresult) {
		super();
		this.testgroup = (String) testgroup;
		this.testname = (String) testname;
		this.testresult = (String) testresult;
	}

	public String getTestgroup() {
		return testgroup;
	}

	public void setTestgroup(String testgroup) {
		this.testgroup = testgroup;
	}

	public String getTestname() {
		return testname;
	}

	public void setTestname(String testname) {
		this.testname = testname;
	}

	public String getTestresult() {
		return testresult;
	}

	public void setTestresult(String testresult) {
		this.testresult = testresult;
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
