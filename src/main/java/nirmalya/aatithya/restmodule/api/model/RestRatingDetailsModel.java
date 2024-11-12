package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestRatingDetailsModel {

	private String pid;
	private String doctorId;
	private String userName;
	private String totalRating;
	private String description;
	private String title;
	
	
	public  RestRatingDetailsModel() {
		super();
	}


	
	
	public RestRatingDetailsModel(Object pid, Object doctorId, Object userName, Object totalRating, Object description,
			Object title) {
		super();
		this.pid = (String)pid;
		this.doctorId = (String)doctorId;
		this.userName = (String)userName;
		this.totalRating = (String)totalRating;
		this.description = (String)description;
		this.title = (String)title;
	}




	public String getPid() {
		return pid;
	}


	public void setPid(String pid) {
		this.pid = pid;
	}


	public String getDoctorId() {
		return doctorId;
	}


	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getTotalRating() {
		return totalRating;
	}


	public void setTotalRating(String totalRating) {
		this.totalRating = totalRating;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
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
