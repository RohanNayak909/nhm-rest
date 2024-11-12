package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DashboardlabDetails {
	private String labId;
	private String labName;
	private String address;
	private String userId;
	public DashboardlabDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DashboardlabDetails(Object labId, Object labName, Object address, Object userId) {
		super();
		this.labId = (String) labId;
		this.labName = (String) labName;
		this.address = (String) address;
		this.userId = (String) userId;
	}
	
	public String getLabId() {
		return labId;
	}
	public void setLabId(String labId) {
		this.labId = labId;
	}

	public String getLabName() {
		return labName;
	}
	public void setLabName(String labName) {
		this.labName = labName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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