package nirmalya.aatithya.restmodule.admin.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestAdminDownloadExcelModel {
	private String district;
	private String block;
	private String institute;
	private String scheme;
	private String agency;
	private String yearOfSanction;
	private String nameOfProject;
	private String approvedAmount;
	private String availableCost;
	private String category;
	private String latitude;
	private String longitude;
	private String projectId;
	private String fundRelease;
	public RestAdminDownloadExcelModel() {
		super();
	}

	public RestAdminDownloadExcelModel(Object district, Object block, Object institute, Object scheme, Object agency,
			Object yearOfSanction, Object nameOfProject, Object approvedAmount, Object availableCost, Object category,
			Object latitude, Object longitude, Object projectId, Object fundRelease) {
		super();
		this.district = (String)district;
		this.block = (String)block;
		this.institute = (String)institute;
		this.scheme = (String)scheme;
		this.agency = (String)agency;
		this.yearOfSanction = (String)yearOfSanction;
		this.nameOfProject = (String)nameOfProject;
		this.approvedAmount = (String)approvedAmount;
		this.availableCost = (String)availableCost;
		this.category = (String)category;
		this.latitude = (String)latitude;
		this.longitude = (String)longitude;
		this.projectId = (String)projectId;
		this.fundRelease = (String)fundRelease;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getFundRelease() {
		return fundRelease;
	}

	public void setFundRelease(String fundRelease) {
		this.fundRelease = fundRelease;
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
