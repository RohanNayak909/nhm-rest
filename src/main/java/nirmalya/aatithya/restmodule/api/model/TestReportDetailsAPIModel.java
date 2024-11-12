package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestReportDetailsAPIModel {

	private String testgroup;
	private String testname;
	private String testresult;
	private String unit;
	private String fromrange;
	private String torange;
	private String refrange;
	private String status;

	public TestReportDetailsAPIModel() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getFromrange() {
		return fromrange;
	}

	public void setFromrange(String fromrange) {
		this.fromrange = fromrange;
	}

	public String getTorange() {
		return torange;
	}

	public void setTorange(String torange) {
		this.torange = torange;
	}

	public String getRefrange() {
		return refrange;
	}

	public void setRefrange(String refrange) {
		this.refrange = refrange;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
