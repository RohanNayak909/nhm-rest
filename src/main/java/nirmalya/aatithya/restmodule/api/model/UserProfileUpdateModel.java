package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserProfileUpdateModel {
	private String userId;
	private String userProfileImg;
	private String extension;
	public UserProfileUpdateModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserProfileUpdateModel(Object userId, Object userProfileImg) {
		super();
		this.userId = (String) userId;
		this.userProfileImg = (String) userProfileImg;
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
	
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
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
