package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DoctorRegistrationModel {

	private String dctrid;
	private String organizationid;
	private String titleid;
	private String docname;
	private String educationid;
	private String speciality;
	private String dob;
	private String bloodgroup;
	private String gender;
	private String address;
	private String countryid;
	private String stateid;
	private String districtid;
	private String cityid;
	private String pincode;
	private String homephone;
	private String officephone;
	private String mobno;
	private String email;
	private String alteremail;
	private String userid;
	private String password;
	private List<String> profileImage = new ArrayList<String>();
	private String profileImageType;
	private String profileImageName;
	private String role;

	private String experience;
	private String jobtype;

	private String imaNo;
	private String adhaarNo;
	private String passportNo;
	private String votterId;
	private String liceneceNo;
	private String panNo;
	private String liceneceAuthority;
	

	private List<String> digSign = new ArrayList<String>();
	private String digSignType;
	private String digSignName;

	public DoctorRegistrationModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<String> getDigSign() {
		return digSign;
	}

	public void setDigSign(List<String> digSign) {
		this.digSign = digSign;
	}

	public String getDigSignType() {
		return digSignType;
	}

	public void setDigSignType(String digSignType) {
		this.digSignType = digSignType;
	}

	public String getDigSignName() {
		return digSignName;
	}

	public void setDigSignName(String digSignName) {
		this.digSignName = digSignName;
	}

	public String getAdhaarNo() {
		return adhaarNo;
	}

	public void setAdhaarNo(String adhaarNo) {
		this.adhaarNo = adhaarNo;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public String getVotterId() {
		return votterId;
	}

	public void setVotterId(String votterId) {
		this.votterId = votterId;
	}

	public String getLiceneceNo() {
		return liceneceNo;
	}

	public void setLiceneceNo(String liceneceNo) {
		this.liceneceNo = liceneceNo;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getLiceneceAuthority() {
		return liceneceAuthority;
	}

	public void setLiceneceAuthority(String liceneceAuthority) {
		this.liceneceAuthority = liceneceAuthority;
	}

	public String getImaNo() {
		return imaNo;
	}

	public void setImaNo(String imaNo) {
		this.imaNo = imaNo;
	}

	public String getDctrid() {
		return dctrid;
	}

	public void setDctrid(String dctrid) {
		this.dctrid = dctrid;
	}

	public String getOrganizationid() {
		return organizationid;
	}

	public void setOrganizationid(String organizationid) {
		this.organizationid = organizationid;
	}

	public String getTitleid() {
		return titleid;
	}

	public void setTitleid(String titleid) {
		this.titleid = titleid;
	}

	public String getDocname() {
		return docname;
	}

	public void setDocname(String docname) {
		this.docname = docname;
	}

	public String getEducationid() {
		return educationid;
	}

	public void setEducationid(String educationid) {
		this.educationid = educationid;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getBloodgroup() {
		return bloodgroup;
	}

	public void setBloodgroup(String bloodgroup) {
		this.bloodgroup = bloodgroup;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountryid() {
		return countryid;
	}

	public void setCountryid(String countryid) {
		this.countryid = countryid;
	}

	public String getStateid() {
		return stateid;
	}

	public void setStateid(String stateid) {
		this.stateid = stateid;
	}

	public String getDistrictid() {
		return districtid;
	}

	public void setDistrictid(String districtid) {
		this.districtid = districtid;
	}

	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getHomephone() {
		return homephone;
	}

	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}

	public String getOfficephone() {
		return officephone;
	}

	public void setOfficephone(String officephone) {
		this.officephone = officephone;
	}

	public String getMobno() {
		return mobno;
	}

	public void setMobno(String mobno) {
		this.mobno = mobno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAlteremail() {
		return alteremail;
	}

	public void setAlteremail(String alteremail) {
		this.alteremail = alteremail;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(List<String> profileImage) {
		this.profileImage = profileImage;
	}

	public String getProfileImageType() {
		return profileImageType;
	}

	public void setProfileImageType(String profileImageType) {
		this.profileImageType = profileImageType;
	}

	public String getProfileImageName() {
		return profileImageName;
	}

	public void setProfileImageName(String profileImageName) {
		this.profileImageName = profileImageName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getJobtype() {
		return jobtype;
	}

	public void setJobtype(String jobtype) {
		this.jobtype = jobtype;
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
