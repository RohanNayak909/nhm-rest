package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DoctorRatingModel {

	private String userid;
	private String drid;
	private String rating;
	private String appno;
	private String reviews;
	private  ArrayList likeType; 

	public DoctorRatingModel() {
		super();
	}

	public DoctorRatingModel(Object userid, Object drid, Object rating, Object appno, Object reviews) {
		super();
		this.userid = (String) userid;
		this.drid = (String) drid;
		this.rating = (String) rating;
		this.appno = (String) appno;
		this.reviews = (String) reviews;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getDrid() {
		return drid;
	}

	public void setDrid(String drid) {
		this.drid = drid;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getAppno() {
		return appno;
	}

	public void setAppno(String appno) {
		this.appno = appno;
	}

	public String getReviews() {
		return reviews;
	}

	public void setReviews(String reviews) {
		this.reviews = reviews;
	}
	

	public ArrayList getLikeType() {
		return likeType;
	}

	public void setLikeType(ArrayList likeType) {
		this.likeType = likeType;
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
