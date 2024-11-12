package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserProfileModel {
	private String userid;
	private String fname;
	private String lName;
	private String mobile;
	private String address;
	private String email;
	private String langaugeKnown;
	private String img;
	private String dob;
	private String gender;
	private String genderid;
	
	public UserProfileModel() {
		super();
		// TODO Auto-generated constructor stub
	}


	public UserProfileModel(Object userid, Object fname, Object lName, Object mobile, Object email,
			Object address,Object img,Object langaugeKnown,Object dob,Object gender,Object genderid){
		super();
		this.userid = (String) userid;
		this.fname = (String) fname;
		this.lName = (String) lName;
		this.mobile = (String) mobile;
		this.email = (String) email;
		this.address = (String) address;
		this.img = (String) img;
		this.langaugeKnown = (String) langaugeKnown;
		this.dob = (String) dob;
		this.gender = (String) gender;
		this.genderid = (String) genderid;
	}











	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getFname() {
		return fname;
	}


	public void setFname(String fname) {
		this.fname = fname;
	}


	public String getlName() {
		return lName;
	}


	public void setlName(String lName) {
		this.lName = lName;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getLangaugeKnown() {
		return langaugeKnown;
	}


	public void setLangaugeKnown(String langaugeKnown) {
		this.langaugeKnown = langaugeKnown;
	}
	


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}
	


	public String getDob() {
		return dob;
	}


	public void setDob(String dob) {
		this.dob = dob;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}
	


	public String getGenderid() {
		return genderid;
	}


	public void setGenderid(String genderid) {
		this.genderid = genderid;
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
