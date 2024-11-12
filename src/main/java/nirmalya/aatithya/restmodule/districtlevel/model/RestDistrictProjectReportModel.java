package nirmalya.aatithya.restmodule.districtlevel.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestDistrictProjectReportModel {
	private String district;
	private String block;
	private String institute;
	private String scheme;
	private String agency;
	private String yearOfSanction;
	private String nameOfProject;
	private String approvedAmount;
	private String availableCost;
	private String floor;
	private String phyStatus;
	private String imageOne;
	private String imageTwo;
	private String category;
	private String expenditure;
	private String ucsubmitted;
	private String currentdate;
	private String latitude;
	private String longitude;
	private String userId;
	private String totalPageno;
	private String pages;
	private String jeName;
	private String userName;
	
	
	
	public RestDistrictProjectReportModel() {
		super();
	}

	public RestDistrictProjectReportModel(Object district, Object block, Object category, Object institute, Object agency,
			Object nameOfProject, Object phyStatus,Object totalPageno,Object pages) {
		super();
		this.district = (String)district;
		this.block = (String)block;
		this.category = (String)category;
		this.institute = (String)institute;
		this.agency = (String)agency;
		this.nameOfProject = (String)nameOfProject;
		this.phyStatus = (String)phyStatus;
		this.totalPageno = (String)totalPageno;
		this.pages = (String)pages;
		
	}

	public RestDistrictProjectReportModel(Object district, Object block, Object institute, Object scheme,
			Object agency, Object yearOfSanction, Object nameOfProject, Object approvedAmount, Object availableCost,
			Object floor, Object phyStatus, Object imageOne, Object imageTwo,Object latitude,Object longitude, Object category,
			Object expenditure,Object ucsubmitted,Object currentdate) {
		super();
		this.district =(String) district;
		this.block =(String) block;
		this.institute = (String)institute;
		this.scheme = (String)scheme;
		this.agency =(String) agency;
		this.yearOfSanction =(String) yearOfSanction;
		this.nameOfProject = (String)nameOfProject;
		this.approvedAmount = (String)approvedAmount;
		this.availableCost = (String)availableCost;
		this.floor = (String)floor;
		this.phyStatus = (String)phyStatus;
		this.imageOne = (String)imageOne;
		this.imageTwo = (String)imageTwo;
		this.latitude =(String)latitude;
 		this.longitude =(String)longitude;
		this.category = (String)category;
		this.expenditure =(String)expenditure;
		this.ucsubmitted =(String)ucsubmitted;
 		this.currentdate =(String)currentdate;
 		
 		
	}

	
	public RestDistrictProjectReportModel(Object userName, Object nameOfProject, Object scheme, Object yearOfSanction,
			Object approvedAmount, Object availableCost, Object expenditure, Object ucsubmitted,
			Object totalPageno,Object pages,Object jeName) {
		super();
		this.userName = (String)userName;
		this.nameOfProject = (String)nameOfProject;
		this.scheme = (String)scheme;
		this.yearOfSanction = (String)yearOfSanction;
		this.approvedAmount = (String)approvedAmount;
		this.availableCost = (String)availableCost;
		this.expenditure = (String)expenditure;
		this.ucsubmitted = (String)ucsubmitted;
		this.totalPageno = (String)totalPageno;
		this.pages = (String)pages;
		this.jeName = (String)jeName;
	}


	public String getDistrict() {
		return district;
	}



	public void setDistrict(String district) {
		this.district = district;
	}



	public String getBlock() {
		return block;
	}



	public void setBlock(String block) {
		this.block = block;
	}



	public String getInstitute() {
		return institute;
	}



	public void setInstitute(String institute) {
		this.institute = institute;
	}



	public String getScheme() {
		return scheme;
	}



	public void setScheme(String scheme) {
		this.scheme = scheme;
	}



	public String getAgency() {
		return agency;
	}



	public void setAgency(String agency) {
		this.agency = agency;
	}



	public String getYearOfSanction() {
		return yearOfSanction;
	}



	public void setYearOfSanction(String yearOfSanction) {
		this.yearOfSanction = yearOfSanction;
	}



	public String getNameOfProject() {
		return nameOfProject;
	}



	public void setNameOfProject(String nameOfProject) {
		this.nameOfProject = nameOfProject;
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



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public String getExpenditure() {
		return expenditure;
	}



	public void setExpenditure(String expenditure) {
		this.expenditure = expenditure;
	}



	public String getUcsubmitted() {
		return ucsubmitted;
	}



	public void setUcsubmitted(String ucsubmitted) {
		this.ucsubmitted = ucsubmitted;
	}



	public String getCurrentdate() {
		return currentdate;
	}



	public void setCurrentdate(String currentdate) {
		this.currentdate = currentdate;
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



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
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
