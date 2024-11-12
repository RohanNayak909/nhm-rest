package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ProjectPhysicalStatusModel {
	private String block;
	private String institute;
	private String category;
	private String projectName;
	private String agency;
	private String curStatus;
	private String blockId;
	private String instituteId;
	private String categoryId;
	private String agencyId;
	private String district;
	private String districtId;
	private String floorNo;
	
	private String scheme;
	private String yearOfSanction;
	private String approvedAmount;
	private String availableCost;
	private String schemeId;
	private String fundRelease;
	public ProjectPhysicalStatusModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProjectPhysicalStatusModel(Object block , Object institute,Object category,Object projectName,
			Object agency, Object curStatus,Object blockId,Object instituteId,Object categoryId,Object agencyId
			,Object district,Object districtId,Object floorNo) {
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
	
	public ProjectPhysicalStatusModel(Object projectName,Object scheme,Object yearOfSanction,Object approvedAmount
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
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	
	public String getFloorNo() {
		return floorNo;
	}
	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}
	
	
	
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public String getYearOfSanction() {
		return yearOfSanction;
	}
	public void setYearOfSanction(String yearOfSanction) {
		this.yearOfSanction = yearOfSanction;
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
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
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
