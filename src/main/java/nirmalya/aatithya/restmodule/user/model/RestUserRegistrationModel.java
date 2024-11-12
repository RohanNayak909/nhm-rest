package nirmalya.aatithya.restmodule.user.model;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;

public class RestUserRegistrationModel {
	private String fileProfileimg;
	private BigInteger patientId;
	private Integer organizationId;
	private String organizationName;
	private Integer tittle;
	private String fName;
	private String lName;
	private String name;
	private String address;
	private Integer country;
	private String countryName;
	private Integer countryCode;
	private Integer state;
	private String stateName;
	private Integer stateCode;
	private Integer dist;
	private String distName;
	private Integer city;
	private String cityName;
	private String pin;
	private String hPh;
	private String fPh;
	private String mob;
	private String mail;
	private String opMail;
	private String userId;
	private String password;
	private String education;
	private Integer roleUser;
	private Integer speciality;
	private String dob;
	private Integer bloodGrp;
	private Integer gender;
	private String planName;
	private Integer plan;
	private String planValidity;
	private String planAmt;
	private String benefit;
	private String fileUpload;
	private String createdBy;
	private String experience;
	private String jobType;
	private String qRFileName;
	private String abhaId;
	private String aadhaarNoAbha;
	private List<String> aadhaarPhoto;
	
	private Integer organizationModalId;
	private String orgName;
	private Integer orgRegdNo;
	private String orgAddress;
	private Integer orgTypeId;
	private String orgTypeName;
	private String orgdocName;
	private String orgAttachment;
	private String orgGst;
	private Integer orgCountry;
	private String orgCountryName;
	private Integer orgState;
	private String orgStateName;
	private Integer orgStateCode;
	private Integer orgDist;
	private String orgDistName;
	private Integer orgCity;
	private String orgCityName;
	private String orgPin;
	//private List<RestPatientDocumentModel> documentList;
	
	private String interestId;
	private String catagory;
	private String subcatagory;
	private List<String> subcatagoryList;
	private Boolean getUpdate;
	private List<DropDownModel> userInterest;
	private Boolean isInterest;
	
	private String blockName;
	private String villageName;
	private String locationName;
	
	private String documentName;
	
	public RestUserRegistrationModel(Object interestId, Object catagory,Object subcatagory) {
		super();
		this.interestId = (String) interestId;
		this.catagory = (String) catagory;
		this.subcatagory = (String) subcatagory;
	}
	public RestUserRegistrationModel(Object catagory) {
		super();
		this.catagory = (String) catagory;
	}
	public RestUserRegistrationModel(Object organizationId, Object organizationName) {
		super();
		this.organizationId = (Integer) organizationId;
		this.organizationName = (String) organizationName;

	}
	public RestUserRegistrationModel(Object patientId,Object country, Object state, Object dist, Object city) {
		super();
		this.patientId = (BigInteger) patientId;
		this.country = (Integer) country;
		this.state = (Integer) state;
		this.dist = (Integer) dist;
		this.city = (Integer) city;
	}
	public RestUserRegistrationModel(Object plan, Object planValidity, Object planAmt, Object benefit) {
		super();
		this.plan = (Integer) plan;
		this.planValidity = (String) planValidity;
		this.planAmt = (String) planAmt;
		this.benefit = (String) benefit;

	}
	public RestUserRegistrationModel(Object organizationModalId, Object orgName, Object orgRegdNo, Object orgAddress,
			Object orgTypeId, Object orgTypeName, Object orgdocName, Object orgAttachment,Object orgGst
			,Object orgCountry,Object orgCountryName,Object orgState,Object orgStateName
			,Object orgStateCode,Object orgDist,Object orgDistName,Object orgCity,Object orgCityName,Object orgPin) {
		super();
		this.organizationModalId = (Integer)organizationModalId;
		this.orgName = (String)orgName;
		this.orgRegdNo = (Integer)orgRegdNo;
		this.orgAddress = (String)orgAddress;
		this.orgTypeId = (Integer)orgTypeId;
		this.orgTypeName = (String)orgTypeName;
		this.orgdocName = (String)orgdocName;
		this.orgAttachment = (String)orgAttachment;
		this.orgGst=(String)orgGst;
		this.orgState=(Integer)orgState;
		this.orgStateName=(String)orgStateName;
		this.orgStateCode=(Integer)orgStateCode;
		this.orgDist=(Integer)orgDist;
		this.orgDistName=(String)orgDistName;
		this.orgCity=(Integer)orgCity;
		this.orgCityName=(String)orgCityName;
		this.orgPin=(String)orgPin;

		this.orgCountry=(Integer)orgCountry;
		this.orgCountryName=(String)orgCountryName;
	}
	
	
	
	public List<String> getAadhaarPhoto() {
		return aadhaarPhoto;
	}
	public void setAadhaarPhoto(List<String> aadhaarPhoto) {
		this.aadhaarPhoto = aadhaarPhoto;
	}
	public String getAbhaId() {
		return abhaId;
	}
	public void setAbhaId(String abhaId) {
		this.abhaId = abhaId;
	}

	/*
	 * public List<RestPatientDocumentModel> getDocumentList() { return
	 * documentList; } public void setDocumentList(List<RestPatientDocumentModel>
	 * documentList) { this.documentList = documentList; }
	 */
	public String getqRFileName() {
		return qRFileName;
	}
	public void setqRFileName(String qRFileName) {
		this.qRFileName = qRFileName;
	}
	public RestUserRegistrationModel() {
		super();
	}

	public String getFileProfileimg() {
		return fileProfileimg;
	}

	public void setFileProfileimg(String fileProfileimg) {
		this.fileProfileimg = fileProfileimg;
	}

	public BigInteger getPatientId() {
		return patientId;
	}

	public void setPatientId(BigInteger patientId) {
		this.patientId = patientId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public Integer getTittle() {
		return tittle;
	}

	public void setTittle(Integer tittle) {
		this.tittle = tittle;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Integer getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Integer getStateCode() {
		return stateCode;
	}

	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}

	public Integer getDist() {
		return dist;
	}

	public void setDist(Integer dist) {
		this.dist = dist;
	}

	public String getDistName() {
		return distName;
	}

	public void setDistName(String distName) {
		this.distName = distName;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String gethPh() {
		return hPh;
	}

	public void sethPh(String hPh) {
		this.hPh = hPh;
	}

	public String getfPh() {
		return fPh;
	}

	public void setfPh(String fPh) {
		this.fPh = fPh;
	}

	public String getMob() {
		return mob;
	}

	public void setMob(String mob) {
		this.mob = mob;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getOpMail() {
		return opMail;
	}

	public void setOpMail(String opMail) {
		this.opMail = opMail;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public Integer getRoleUser() {
		return roleUser;
	}

	public void setRoleUser(Integer roleUser) {
		this.roleUser = roleUser;
	}

	public Integer getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Integer speciality) {
		this.speciality = speciality;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Integer getBloodGrp() {
		return bloodGrp;
	}

	public void setBloodGrp(Integer bloodGrp) {
		this.bloodGrp = bloodGrp;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public Integer getPlan() {
		return plan;
	}

	public void setPlan(Integer plan) {
		this.plan = plan;
	}

	public String getPlanValidity() {
		return planValidity;
	}

	public void setPlanValidity(String planValidity) {
		this.planValidity = planValidity;
	}

	public String getPlanAmt() {
		return planAmt;
	}

	public void setPlanAmt(String planAmt) {
		this.planAmt = planAmt;
	}

	public String getBenefit() {
		return benefit;
	}

	public void setBenefit(String benefit) {
		this.benefit = benefit;
	}

	public String getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(String fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public Integer getOrganizationModalId() {
		return organizationModalId;
	}

	public void setOrganizationModalId(Integer organizationModalId) {
		this.organizationModalId = organizationModalId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getOrgRegdNo() {
		return orgRegdNo;
	}

	public void setOrgRegdNo(Integer orgRegdNo) {
		this.orgRegdNo = orgRegdNo;
	}

	public String getOrgAddress() {
		return orgAddress;
	}

	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}

	public Integer getOrgTypeId() {
		return orgTypeId;
	}

	public void setOrgTypeId(Integer orgTypeId) {
		this.orgTypeId = orgTypeId;
	}

	public String getOrgTypeName() {
		return orgTypeName;
	}

	public void setOrgTypeName(String orgTypeName) {
		this.orgTypeName = orgTypeName;
	}

	public String getOrgdocName() {
		return orgdocName;
	}

	public void setOrgdocName(String orgdocName) {
		this.orgdocName = orgdocName;
	}

	public String getOrgAttachment() {
		return orgAttachment;
	}

	public void setOrgAttachment(String orgAttachment) {
		this.orgAttachment = orgAttachment;
	}

	public String getOrgGst() {
		return orgGst;
	}

	public void setOrgGst(String orgGst) {
		this.orgGst = orgGst;
	}

	public Integer getOrgCountry() {
		return orgCountry;
	}

	public void setOrgCountry(Integer orgCountry) {
		this.orgCountry = orgCountry;
	}

	public String getOrgCountryName() {
		return orgCountryName;
	}

	public void setOrgCountryName(String orgCountryName) {
		this.orgCountryName = orgCountryName;
	}

	public Integer getOrgState() {
		return orgState;
	}

	public void setOrgState(Integer orgState) {
		this.orgState = orgState;
	}

	public String getOrgStateName() {
		return orgStateName;
	}

	public void setOrgStateName(String orgStateName) {
		this.orgStateName = orgStateName;
	}

	public Integer getOrgStateCode() {
		return orgStateCode;
	}

	public void setOrgStateCode(Integer orgStateCode) {
		this.orgStateCode = orgStateCode;
	}

	public Integer getOrgDist() {
		return orgDist;
	}

	public void setOrgDist(Integer orgDist) {
		this.orgDist = orgDist;
	}

	public String getOrgDistName() {
		return orgDistName;
	}

	public void setOrgDistName(String orgDistName) {
		this.orgDistName = orgDistName;
	}

	public Integer getOrgCity() {
		return orgCity;
	}

	public void setOrgCity(Integer orgCity) {
		this.orgCity = orgCity;
	}

	public String getOrgCityName() {
		return orgCityName;
	}

	public void setOrgCityName(String orgCityName) {
		this.orgCityName = orgCityName;
	}

	public String getOrgPin() {
		return orgPin;
	}

	public void setOrgPin(String orgPin) {
		this.orgPin = orgPin;
	}

	public String getInterestId() {
		return interestId;
	}

	public void setInterestId(String interestId) {
		this.interestId = interestId;
	}

	public String getCatagory() {
		return catagory;
	}

	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}

	public String getSubcatagory() {
		return subcatagory;
	}

	public void setSubcatagory(String subcatagory) {
		this.subcatagory = subcatagory;
	}

	public List<String> getSubcatagoryList() {
		return subcatagoryList;
	}
	public void setSubcatagoryList(List<String> subcatagoryList) {
		this.subcatagoryList = subcatagoryList;
	}
	
	public List<DropDownModel> getUserInterest() {
		return userInterest;
	}
	public void setUserInterest(List<DropDownModel> userInterest) {
		this.userInterest = userInterest;
	}
	public Boolean getGetUpdate() {
		return getUpdate;
	}
	public void setGetUpdate(Boolean getUpdate) {
		this.getUpdate = getUpdate;
	}
	public Boolean getIsInterest() {
		return isInterest;
	}
	public void setIsInterest(Boolean isInterest) {
		this.isInterest = isInterest;
	}
	
	public String getAadhaarNoAbha() {
		return aadhaarNoAbha;
	}
	public void setAadhaarNoAbha(String aadhaarNoAbha) {
		this.aadhaarNoAbha = aadhaarNoAbha;
	}
	
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
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
