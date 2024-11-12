package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestReportAPIModel {

	private String uniqueid;
	private String name;
	private String mobile;
	private String aadhaar;
	private String gender;
	private String age;
	private String address;
	private String phc;
	private String screentestdate;
	private String createddate;
	private List<TestReportDetailsAPIModel> screentestreportlist = new ArrayList<TestReportDetailsAPIModel>();

	public TestReportAPIModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUniqueid() {
		return uniqueid;
	}

	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAadhaar() {
		return aadhaar;
	}

	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhc() {
		return phc;
	}

	public void setPhc(String phc) {
		this.phc = phc;
	}

	public String getScreentestdate() {
		return screentestdate;
	}

	public void setScreentestdate(String screentestdate) {
		this.screentestdate = screentestdate;
	}

	public String getCreateddate() {
		return createddate;
	}

	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}

	public List<TestReportDetailsAPIModel> getScreentestreportlist() {
		return screentestreportlist;
	}

	public void setScreentestreportlist(List<TestReportDetailsAPIModel> screentestreportlist) {
		this.screentestreportlist = screentestreportlist;
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
