package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;

public class APIProjectStatusModel {
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
	private String userId;
	private String category;
	private String projectName;
	private String floorNo;
	private String imageOne;
	private String imageTwo;
	private String extension;
	private String id;
	private String expenditure;
	private String ucSubmitted;
	private String uniqueid;
	private String curDate;
	private String curStatus;
	private String blockId;
	private String instituteId;
	private String categoryId;
	private String agencyId;
	private String schemeId;
	private String districtId;
	private String latitude;
	private String longitude;
	private String assignedBlock;
	private String blockName;
	private String fundRelease;
	private String userName;
	private List<ProjectApiModel> projectlist;
	private List<APIProjectStatusModel> projectPhylist;
	private List<RestFinancialModel> financialList;
	private List<RestProjectPhysicalStatusModel> physicalList;

	
	public List<RestFinancialModel> getFinancialList() {
		return financialList;
	}




	public void setFinancialList(List<RestFinancialModel> financialList) {
		this.financialList = financialList;
	}




	public APIProjectStatusModel() {
		super();
	}

	
	
	
	public APIProjectStatusModel(Object block , Object institute,Object category,Object projectName,
			Object agency, Object curStatus,Object blockId,Object instituteId,
			Object categoryId,Object agencyId,Object district,Object districtId,Object floorNo) {
		super();
		this.block = (String)block;
		this.institute = (String)institute;
		this.category = (String)category;
		this.projectName = (String)projectName;
		this.agency = (String)agency;
		this.curStatus = (String)curStatus;
		this.blockId = (String)blockId;
		this.instituteId = (String)instituteId;
		this.categoryId = (String)categoryId;
		this.agencyId = (String)agencyId;
		this.district = (String)district;
		this.districtId = (String)districtId;
		this.floorNo = (String)floorNo;
	}

	public APIProjectStatusModel(Object projectName,Object scheme,Object yearOfSanction,Object approvedAmount
			, Object availableCost,Object schemeId,Object fundRelease) {
		super();
		this.projectName = (String)projectName;
		this.scheme = (String)scheme;
		this.yearOfSanction = (String)yearOfSanction;
		this.approvedAmount = (String)approvedAmount;
		this.availableCost = (String)availableCost;
		this.schemeId = (String)schemeId;
		this.fundRelease = (String)fundRelease;
	}
	
	
	
	

	public APIProjectStatusModel(Object assignedBlock,Object blockName) {
		super();
		
		this.assignedBlock = (String)assignedBlock;
		this.blockName = (String)blockName;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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




	public String getUniqueid() {
		return uniqueid;
	}




	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	
	
	public String getCurDate() {
		return curDate;
	}




	public void setCurDate(String curDate) {
		this.curDate = curDate;
	}




	public String getCurStatus() {
		return curStatus;
	}




	public void setCurStatus(String curStatus) {
		this.curStatus = curStatus;
	}

	
	
	
	



	public String getBlockId() {
		return blockId;
	}




	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}




	public String getInstituteId() {
		return instituteId;
	}




	public void setInstituteId(String instituteId) {
		this.instituteId = instituteId;
	}




	public String getCategoryId() {
		return categoryId;
	}




	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}




	public String getAgencyId() {
		return agencyId;
	}




	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}




	public String getSchemeId() {
		return schemeId;
	}




	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
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




	public String getAssignedBlock() {
		return assignedBlock;
	}




	public void setAssignedBlock(String assignedBlock) {
		this.assignedBlock = assignedBlock;
	}




	public String getBlockName() {
		return blockName;
	}




	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}




	public List<ProjectApiModel> getProjectlist() {
		return projectlist;
	}




	public void setProjectlist(List<ProjectApiModel> projectlist) {
		this.projectlist = projectlist;
	}




	public List<APIProjectStatusModel> getProjectPhylist() {
		return projectPhylist;
	}




	public void setProjectPhylist(List<APIProjectStatusModel> projectData) {
		this.projectPhylist = projectData;
	}




	public List<RestProjectPhysicalStatusModel> getPhysicalList() {
		return physicalList;
	}




	public void setPhysicalList(List<RestProjectPhysicalStatusModel> physicalList) {
		this.physicalList = physicalList;
	}




	public String getFundRelease() {
		return fundRelease;
	}




	public void setFundRelease(String fundRelease) {
		this.fundRelease = fundRelease;
	}




	public String getUserName() {
		return userName;
	}




	public void setUserName(String userName) {
		this.userName = userName;
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
