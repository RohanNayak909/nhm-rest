package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestProjectPhysicalStatusModel {
	private String uniqueid;
	private String userId;
	private String institute;
	private String category;
	private String projectName;
	private String agency;
	private String floor;
	private String phyStatus;
	private String curDate;
	private String imageOne;
	private String imageTwo;
	private String extension;
	private String blockId;
	private String districtId;
	private String latitude;
	private String longitude;
	private String curPhyStatus;
	private String userName;
	private String dataEntryStatus;
	
	
	
	
	public RestProjectPhysicalStatusModel() {
		super();
	}




	public String getUniqueid() {
		return uniqueid;
	}




	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}




	public String getUserId() {
		return userId;
	}




	public void setUserId(String userId) {
		this.userId = userId;
	}




	public String getInstitute() {
		return institute;
	}




	public void setInstitute(String institute) {
		this.institute = institute;
	}




	public String getCategory() {
		return category;
	}




	public void setCategory(String category) {
		this.category = category;
	}




	public String getProjectName() {
		return projectName;
	}




	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}




	public String getAgency() {
		return agency;
	}




	public void setAgency(String agency) {
		this.agency = agency;
	}




	




	public String getFloor() {
		return floor;
	}




	public void setFloor(String floor) {
		this.floor = floor;
	}




	public String getPhyStatus() {
		return phyStatus;
	}




	public void setPhyStatus(String phyStatus) {
		this.phyStatus = phyStatus;
	}




	public String getCurDate() {
		return curDate;
	}




	public void setCurDate(String curDate) {
		this.curDate = curDate;
	}




	public String getImageOne() {
		return imageOne;
	}




	public void setImageOne(String imageOne) {
		this.imageOne = imageOne;
	}




	public String getImageTwo() {
		return imageTwo;
	}




	public void setImageTwo(String imageTwo) {
		this.imageTwo = imageTwo;
	}




	public String getExtension() {
		return extension;
	}




	public void setExtension(String extension) {
		this.extension = extension;
	}




	public String getBlockId() {
		return blockId;
	}




	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}




	public String getDistrictId() {
		return districtId;
	}




	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}




	public String getLatitude() {
		return latitude;
	}




	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}




	public String getLongitude() {
		return longitude;
	}




	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}




	public String getCurPhyStatus() {
		return curPhyStatus;
	}




	public void setCurPhyStatus(String curPhyStatus) {
		this.curPhyStatus = curPhyStatus;
	}
	
	
	
	
	public String getUserName() {
		return userName;
	}




	public void setUserName(String userName) {
		this.userName = userName;
	}




	public String getDataEntryStatus() {
		return dataEntryStatus;
	}




	public void setDataEntryStatus(String dataEntryStatus) {
		this.dataEntryStatus = dataEntryStatus;
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
