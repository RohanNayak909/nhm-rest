package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;

public class BloodBankAPIModel {
	private String userId;
	private String communityName;
	private String communityDesc;
	private List<DropDownModel> communityList;
	private String unitId;
	private String bldGroupId;

	public BloodBankAPIModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public BloodBankAPIModel(Object userId, Object communityName, Object communityDesc) {
		super();
		this.userId = (String)userId;
		this.communityName = (String)communityName;
		this.communityDesc = (String)communityDesc;
	}

	

	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getCommunityName() {
		return communityName;
	}


	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}


	public String getCommunityDesc() {
		return communityDesc;
	}


	public void setCommunityDesc(String communityDesc) {
		this.communityDesc = communityDesc;
	}
	
	


	public List<DropDownModel> getCommunityList() {
		return communityList;
	}


	public void setCommunityList(List<DropDownModel> communityList) {
		this.communityList = communityList;
	}


	public String getUnitId() {
		return unitId;
	}


	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}


	public String getBldGroupId() {
		return bldGroupId;
	}


	public void setBldGroupId(String bldGroupId) {
		this.bldGroupId = bldGroupId;
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
