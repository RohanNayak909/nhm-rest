package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserRegistrationGeoModel {

	private String firstname;
	private String lastname;

	private String fileprofileimg;

	private Integer countryid;
	private String countrycode;
	private String countryname;

	private Integer stateid;
	private String statecode;
	private String statename;

	private Integer districtid;
	private Integer cityid;

	private String address;
	private String pincode;

	private String mobile;
	private String mailid;

	private String bloodgroup;
	private String occupation;
	private Integer age;
	private String dob;
	private String maritialstatus;

	private String pancard;
	private String votercard;
	private String aadhaarcard;
	private String licenceno;

	private Double height;
	private Double weight;
	private Double bmi;
	private Double pulse;
	private Double respiration;
	private Double systolicbp;
	private Double diastolicbp;
	private Double oxygensaturation;
	private Double tempincel;

	private String qualification;
	private String specialization;
	private String emername;
	private String emercontact;
	private Integer relation;
	private String familydoctorname;
	private String familydoctorcontactno;
	private Integer speciality;

	private String userid;
	private String patientid;

	private String gender;
	private Integer hospitalId;

	public UserRegistrationGeoModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFileprofileimg() {
		return fileprofileimg;
	}

	public void setFileprofileimg(String fileprofileimg) {
		this.fileprofileimg = fileprofileimg;
	}

	public Integer getCountryid() {
		return countryid;
	}

	public void setCountryid(Integer countryid) {
		this.countryid = countryid;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public String getCountryname() {
		return countryname;
	}

	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}

	public Integer getStateid() {
		return stateid;
	}

	public void setStateid(Integer stateid) {
		this.stateid = stateid;
	}

	public String getStatecode() {
		return statecode;
	}

	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}

	public String getStatename() {
		return statename;
	}

	public void setStatename(String statename) {
		this.statename = statename;
	}

	public Integer getDistrictid() {
		return districtid;
	}

	public void setDistrictid(Integer districtid) {
		this.districtid = districtid;
	}

	public Integer getCityid() {
		return cityid;
	}

	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMailid() {
		return mailid;
	}

	public void setMailid(String mailid) {
		this.mailid = mailid;
	}

	public String getBloodgroup() {
		return bloodgroup;
	}

	public void setBloodgroup(String bloodgroup) {
		this.bloodgroup = bloodgroup;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getMaritialstatus() {
		return maritialstatus;
	}

	public void setMaritialstatus(String maritialstatus) {
		this.maritialstatus = maritialstatus;
	}

	public String getPancard() {
		return pancard;
	}

	public void setPancard(String pancard) {
		this.pancard = pancard;
	}

	public String getVotercard() {
		return votercard;
	}

	public void setVotercard(String votercard) {
		this.votercard = votercard;
	}

	public String getAadhaarcard() {
		return aadhaarcard;
	}

	public void setAadhaarcard(String aadhaarcard) {
		this.aadhaarcard = aadhaarcard;
	}

	public String getLicenceno() {
		return licenceno;
	}

	public void setLicenceno(String licenceno) {
		this.licenceno = licenceno;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getBmi() {
		return bmi;
	}

	public void setBmi(Double bmi) {
		this.bmi = bmi;
	}

	public Double getPulse() {
		return pulse;
	}

	public void setPulse(Double pulse) {
		this.pulse = pulse;
	}

	public Double getRespiration() {
		return respiration;
	}

	public void setRespiration(Double respiration) {
		this.respiration = respiration;
	}

	public Double getSystolicbp() {
		return systolicbp;
	}

	public void setSystolicbp(Double systolicbp) {
		this.systolicbp = systolicbp;
	}

	public Double getDiastolicbp() {
		return diastolicbp;
	}

	public void setDiastolicbp(Double diastolicbp) {
		this.diastolicbp = diastolicbp;
	}

	public Double getOxygensaturation() {
		return oxygensaturation;
	}

	public void setOxygensaturation(Double oxygensaturation) {
		this.oxygensaturation = oxygensaturation;
	}

	public Double getTempincel() {
		return tempincel;
	}

	public void setTempincel(Double tempincel) {
		this.tempincel = tempincel;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getEmername() {
		return emername;
	}

	public void setEmername(String emername) {
		this.emername = emername;
	}

	public String getEmercontact() {
		return emercontact;
	}

	public void setEmercontact(String emercontact) {
		this.emercontact = emercontact;
	}

	public Integer getRelation() {
		return relation;
	}

	public void setRelation(Integer relation) {
		this.relation = relation;
	}

	public String getFamilydoctorname() {
		return familydoctorname;
	}

	public void setFamilydoctorname(String familydoctorname) {
		this.familydoctorname = familydoctorname;
	}

	public String getFamilydoctorcontactno() {
		return familydoctorcontactno;
	}

	public void setFamilydoctorcontactno(String familydoctorcontactno) {
		this.familydoctorcontactno = familydoctorcontactno;
	}

	public Integer getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Integer speciality) {
		this.speciality = speciality;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPatientid() {
		return patientid;
	}

	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
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
