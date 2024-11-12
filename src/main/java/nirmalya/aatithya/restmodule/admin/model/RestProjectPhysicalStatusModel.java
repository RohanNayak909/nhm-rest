package nirmalya.aatithya.restmodule.admin.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestProjectPhysicalStatusModel {
	private String userName;
	private String distrctId;
	private String blockId;
	private String projectName;
	private String institutionName;
	private String category;
	private String Agency;
	private String updatedDate;
	private String latitude;
	private String longitude;
	private String floorNumber;
	private String physicalUpdate;
	private String image1;
	private String image2;
	private String scheme;
	private String sanctionYear;
	private String approvedAmount;
	private String availableCost;
	private String expenditure;
	private String ucSubmitted;
	private String totalPageno;
	private String pages;
	private String jeName;
	private String jeLatitude;
	private String jeLongitude;
	private String projectId;
	private String userMobileNo;
	private String createdDate;
	private String id;
	private String rejectedDate;
	private String rejectedBy;
	private String rejectedStatus;
	
	public RestProjectPhysicalStatusModel(){
		super();
	}
	
	
	
	
	
	public RestProjectPhysicalStatusModel(Object projectName, Object updatedDate, Object rejectedBy, Object userMobileNo) {
		super();
		this.projectName = (String)projectName;
		this.updatedDate = (String)updatedDate;
		this.rejectedBy = (String)rejectedBy;
		this.userMobileNo = (String)userMobileNo;
	}





	public RestProjectPhysicalStatusModel(Object projectId,Object blockId, Object projectName,Object image1, Object image2,
			Object floorNumber, Object physicalUpdate,Object updatedDate,Object createdDate) {
		super();
		this.projectId = (String)projectId;
		this.blockId = (String)blockId;
		this.projectName = (String)projectName;
		this.floorNumber = (String)floorNumber;
		this.physicalUpdate = (String)physicalUpdate;
		this.image1 = (String)image1;
		this.image2 = (String)image2;
		this.projectId = (String)projectId;
		this.updatedDate = (String)updatedDate;
		this.createdDate = (String)createdDate;
	}
	

	public RestProjectPhysicalStatusModel(Object projectId,Object distrctId,Object blockId, Object projectName,Object image1, Object image2,
			Object floorNumber, Object physicalUpdate,Object updatedDate,Object createdDate,Object userMobileNo,Object jeLatitude) {
		super();
		this.projectId = (String)projectId;
		this.distrctId = (String)distrctId;
		this.blockId = (String)blockId;
		this.projectName = (String)projectName;
		this.floorNumber = (String)floorNumber;
		this.physicalUpdate = (String)physicalUpdate;
		this.image1 = (String)image1;
		this.image2 = (String)image2;
		this.projectId = (String)projectId;
		this.updatedDate = (String)updatedDate;
		this.createdDate = (String)createdDate;
		this.userMobileNo = (String)userMobileNo;
		this.jeLatitude = (String)jeLatitude;
	}




	public RestProjectPhysicalStatusModel(Object userName, Object distrctId, Object blockId, Object projectName,
			Object institutionName, Object category, Object agency, Object updatedDate, Object latitude,
			Object longitude, Object floorNumber, Object physicalUpdate, Object image1, Object image2,Object totalPageno,
			Object pages,Object jeName,Object projectId,Object id,Object rejectedStatus) {
		super();
		
		this.userName = (String)userName;
		this.distrctId = (String)distrctId;
		this.blockId = (String)blockId;
		this.projectName = (String)projectName;
		this.institutionName = (String)institutionName;
		this.category = (String)category;
		this.Agency = (String)agency;
		this.updatedDate = (String)updatedDate;
		this.latitude = (String)latitude;
		this.longitude = (String)longitude;
		this.floorNumber = (String)floorNumber;
		this.physicalUpdate = (String)physicalUpdate;
		this.image1 = (String)image1;
		this.image2 = (String)image2;
		this.totalPageno = (String)totalPageno;
		this.pages = (String)pages;
		this.jeName = (String)jeName;
		this.projectId = (String)projectId;
		this.id = (String)id;
		this.rejectedStatus = (String)rejectedStatus;
	}




	public RestProjectPhysicalStatusModel(Object userName, Object projectName, Object scheme, Object sanctionYear,
			Object approvedAmount, Object availableCost, Object expenditure, Object ucSubmitted,
			Object totalPageno,Object pages,Object jeName) {
		super();
		this.userName = (String)userName;
		this.projectName = (String)projectName;
		this.scheme = (String)scheme;
		this.sanctionYear = (String)sanctionYear;
		this.approvedAmount = (String)approvedAmount;
		this.availableCost = (String)availableCost;
		this.expenditure = (String)expenditure;
		this.ucSubmitted = (String)ucSubmitted;
		this.totalPageno = (String)totalPageno;
		this.pages = (String)pages;
		this.jeName = (String)jeName;
	}




	public RestProjectPhysicalStatusModel(  Object projectName, Object latitude,
			Object longitude,Object userName, Object jeLatitude, Object jeLongitude,
			Object image1, Object image2,Object totalPageno,Object pages) {
		super();
		
		this.projectName = (String)projectName;
		this.latitude = (String)latitude;
		this.longitude = (String)longitude;
		this.userName = (String)userName;
		this.jeLatitude = (String)jeLatitude;
		this.jeLongitude = (String)jeLongitude;
		this.image1 = (String)image1;
		this.image2 = (String)image2;
		this.totalPageno = (String)totalPageno;
		this.pages = (String)pages;
		
	}
	
	
	
	




	public RestProjectPhysicalStatusModel(Object userName, Object distrctId, Object blockId, Object projectName,
			Object institutionName, Object category, Object agency, Object updatedDate, Object latitude,
			Object longitude, Object floorNumber, Object physicalUpdate, Object image1, Object image2,
			Object userMobileNo) {
		super();
		this.userName = (String)userName;
		this.distrctId = (String)distrctId;
		this.blockId = (String)blockId;
		this.projectName = (String)projectName;
		this.institutionName = (String)institutionName;
		this.category = (String)category;
		this.Agency = (String)agency;
		this.updatedDate = (String)updatedDate;
		this.latitude = (String)latitude;
		this.longitude = (String)longitude;
		this.floorNumber = (String)floorNumber;
		this.physicalUpdate = (String)physicalUpdate;
		this.image1 = (String)image1;
		this.image2 = (String)image2;
		this.userMobileNo = (String)userMobileNo;
	}




	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDistrctId() {
		return distrctId;
	}

	public void setDistrctId(String distrctId) {
		this.distrctId = distrctId;
	}

	public String getBlockId() {
		return blockId;
	}

	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAgency() {
		return Agency;
	}

	public void setAgency(String agency) {
		Agency = agency;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
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

	public String getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(String floorNumber) {
		this.floorNumber = floorNumber;
	}

	public String getPhysicalUpdate() {
		return physicalUpdate;
	}

	public void setPhysicalUpdate(String physicalUpdate) {
		this.physicalUpdate = physicalUpdate;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}
	
	
	public String getScheme() {
		return scheme;
	}




	public void setScheme(String scheme) {
		this.scheme = scheme;
	}




	public String getSanctionYear() {
		return sanctionYear;
	}




	public void setSanctionYear(String sanctionYear) {
		this.sanctionYear = sanctionYear;
	}




	public String getApprovedAmount() {
		return approvedAmount;
	}




	public void setApprovedAmount(String approvedAmount) {
		this.approvedAmount = approvedAmount;
	}




	public String getAvailableCost() {
		return availableCost;
	}




	public void setAvailableCost(String availableCost) {
		this.availableCost = availableCost;
	}




	public String getExpenditure() {
		return expenditure;
	}




	public void setExpenditure(String expenditure) {
		this.expenditure = expenditure;
	}




	public String getUcSubmitted() {
		return ucSubmitted;
	}




	public void setUcSubmitted(String ucSubmitted) {
		this.ucSubmitted = ucSubmitted;
	}




	public String getTotalPageno() {
		return totalPageno;
	}




	public void setTotalPageno(String totalPageno) {
		this.totalPageno = totalPageno;
	}




	public String getPages() {
		return pages;
	}




	public void setPages(String pages) {
		this.pages = pages;
	}




	public String getJeName() {
		return jeName;
	}




	public void setJeName(String jeName) {
		this.jeName = jeName;
	}




	public String getJeLatitude() {
		return jeLatitude;
	}




	public void setJeLatitude(String jeLatitude) {
		this.jeLatitude = jeLatitude;
	}




	public String getJeLongitude() {
		return jeLongitude;
	}




	public void setJeLongitude(String jeLongitude) {
		this.jeLongitude = jeLongitude;
	}




	public String getProjectId() {
		return projectId;
	}




	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}




	public String getUserMobileNo() {
		return userMobileNo;
	}




	public void setUserMobileNo(String userMobileNo) {
		this.userMobileNo = userMobileNo;
	}




	public String getCreatedDate() {
		return createdDate;
	}




	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}




	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	

	public String getRejectedDate() {
		return rejectedDate;
	}

	public void setRejectedDate(String rejectedDate) {
		this.rejectedDate = rejectedDate;
	}

	public String getRejectedBy() {
		return rejectedBy;
	}

	public void setRejectedBy(String rejectedBy) {
		this.rejectedBy = rejectedBy;
	}

	public String getRejectedStatus() {
		return rejectedStatus;
	}

	public void setRejectedStatus(String rejectedStatus) {
		this.rejectedStatus = rejectedStatus;
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
