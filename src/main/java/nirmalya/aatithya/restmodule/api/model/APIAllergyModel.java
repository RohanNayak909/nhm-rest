package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class APIAllergyModel {

	
	private String allName;
	private String allFood;
	private String severity;
	private String reaction;
	
	private String userid;
	private String allnameid;
	private String alltypeid;
	private String updatedby;
	private String id;

	public APIAllergyModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public APIAllergyModel(Object allName, Object allFood, Object severity, Object reaction) {
		super();
		this.allName = (String) allName;
		this.allFood = (String) allFood;
		this.severity = (String) severity;
		this.reaction = (String) reaction;
	}
	
	public APIAllergyModel(Object allnameid, Object alltypeid, Object severity, Object reaction, Object updatedby,
			Object allName, Object allFood, Object id) {
		super();
		this.allnameid = (String) allnameid;
		this.alltypeid = (String) alltypeid;
		this.severity = (String) severity;
		this.reaction = (String) reaction;
		this.updatedby = (String) updatedby;
		this.allName = (String) allName;
		this.allFood = (String) allFood;
		this.id = (String) id;
		
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAllName() {
		return allName;
	}

	public void setAllName(String allName) {
		this.allName = allName;
	}

	public String getAllFood() {
		return allFood;
	}

	public void setAllFood(String allFood) {
		this.allFood = allFood;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getReaction() {
		return reaction;
	}

	public void setReaction(String reaction) {
		this.reaction = reaction;
	}
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getAllnameid() {
		return allnameid;
	}

	public void setAllnameid(String allnameid) {
		this.allnameid = allnameid;
	}

	public String getAlltypeid() {
		return alltypeid;
	}

	public void setAlltypeid(String alltypeid) {
		this.alltypeid = alltypeid;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
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
