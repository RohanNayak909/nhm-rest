package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;


public class DashboardAPIModel {
	private String patientId;
	private String name;
	private String mob;
	private List<RestPatientTestNameModelAPI> testgroup;
	public DashboardAPIModel(Object patientId, Object name, Object mob) {
		super();
		this.patientId = (String) patientId;
		this.name = (String) name;
		this.mob = (String) mob;
	
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMob() {
		return mob;
	}
	public void setMob(String mob) {
		this.mob = mob;
	}

	public List<RestPatientTestNameModelAPI> getTestgroup() {
		return testgroup;
	}
	public void setTestgroup(List<RestPatientTestNameModelAPI> testgroup) {
		this.testgroup = testgroup;
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
