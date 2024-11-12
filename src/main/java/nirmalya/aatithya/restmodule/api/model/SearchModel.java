package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SearchModel {

	private String regNo;
	private String name;
	private String mobileNo;
	private String state;
	private String gender;

	public SearchModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SearchModel(Object regNo, Object name, Object mobileNo, Object state, Object gender) {
		super();
		this.regNo = (String) regNo;
		this.name = (String) name;
		this.mobileNo = (String) mobileNo;
		this.state = (String) state;
		this.gender = (String) gender;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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
