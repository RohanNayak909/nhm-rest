package nirmalya.aatithya.restmodule.user.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserProfileModel {
	
	private String userId;
	private String userProfileImg;
	private String userName;
	private String userMobileNumber;
	private String userEmail;
	private String userLanguagesId;
	private String image;
	private String userLanguagesName;
	private String dob;
	private String gender;
	private String genderid;
	private String deviceTokenId;
	public UserProfileModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserProfileModel(Object userId, Object userProfileImg, Object userName, Object userMobileNumber,
			Object userEmail, Object userLanguagesId,Object image, Object userLanguagesName
			, Object dob,Object gender, Object genderid,Object deviceTokenId) {
		super();
		this.userId = (String) userId;
		this.userProfileImg = (String) userProfileImg;
		this.userName = (String) userName;
		this.userMobileNumber = (String) userMobileNumber;
		this.userEmail = (String) userEmail;
		this.userLanguagesId = (String) userLanguagesId;
		this.image = (String) image;
		this.userLanguagesName = (String) userLanguagesName;
		this.dob = (String) dob;
		this.gender = (String) gender;
		this.genderid = (String) genderid;
		this.deviceTokenId = (String) deviceTokenId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserProfileImg() {
		return userProfileImg;
	}
	public void setUserProfileImg(String userProfileImg) {
		this.userProfileImg = userProfileImg;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserMobileNumber() {
		return userMobileNumber;
	}
	public void setUserMobileNumber(String userMobileNumber) {
		this.userMobileNumber = userMobileNumber;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getUserLanguagesId() {
		return userLanguagesId;
	}
	public void setUserLanguagesId(String userLanguagesId) {
		this.userLanguagesId = userLanguagesId;
	}
	public String getUserLanguagesName() {
		return userLanguagesName;
	}
	public void setUserLanguagesName(String userLanguagesName) {
		this.userLanguagesName = userLanguagesName;
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
	
	public String getDeviceTokenId() {
		return deviceTokenId;
	}
	public void setDeviceTokenId(String deviceTokenId) {
		this.deviceTokenId = deviceTokenId;
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
