package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class OtherUsersProfileModel {

	private String id;
	private String useraccount;
	private String name;
	private String profileimage;
	private String gender;
	private String mobile;
	private String birthdate;
	private String email;
	private String state;
	private String country;
	private String city;
	private String district;
	private String education;
	private String speciality;
	private String organization;
	private String aadhaar;
	private String imano;
	private String pancardno;
	private String passportno;
	private String votercardno;
	private String licenceno;
	private String role;
	private String bldGr;
	private String gendername;
	private String bldGrname;
	
	private String licenseauthority;
	private String address;
	private String experience;
	private String address1;
	private String digsign;
	
	private String stateName;
	private String countryName;
	private String cityName;
	private String districtName;
	private String pincode;

	private String profpicture;
	public OtherUsersProfileModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OtherUsersProfileModel(Object id, Object useraccount, Object name, Object profileimage, Object gender,
			Object mobile, Object birthdate, Object email, Object state, Object country, Object education,
			Object speciality, Object organization, Object aadhaar, Object imano, Object pancardno, Object passportno,
			Object votercardno, Object licenceno, Object role,Object bldGr,Object gendername,Object bldGrname,
			Object licenseauthority,Object address,Object experience,Object address1,Object digsign,Object city,Object district,
			Object countryName,Object stateName,Object districtName,Object cityName,Object pincode) {
		super();
		this.id = (String) id;
		this.useraccount = (String) useraccount;
		this.name = (String) name;
		this.profileimage = (String) profileimage;
		this.gender = (String) gender;
		this.mobile = (String) mobile;
		this.birthdate = (String) birthdate;
		this.email = (String) email;
		this.state = (String) state;
		this.country = (String) country;
		this.education = (String) education;
		this.speciality = (String) speciality;
		this.organization = (String) organization;
		this.aadhaar = (String) aadhaar;
		this.imano = (String) imano;
		this.pancardno = (String) pancardno;
		this.passportno = (String) passportno;
		this.votercardno = (String) votercardno;
		this.licenceno = (String) licenceno;
		this.role = (String) role;
		this.bldGr = (String) bldGr;
		this.gendername = (String) gendername;
		this.bldGrname = (String) bldGrname;
		this.licenseauthority = (String) licenseauthority;
		this.address = (String) address;
		this.experience = (String) experience;
		this.address1 = (String) address1;
		this.digsign = (String) digsign;
		this.city = (String) city;
		this.district = (String) district;
		this.stateName = (String) stateName;
		this.countryName = (String) countryName;
		this.cityName = (String) cityName;
		this.districtName = (String) districtName;
		this.pincode = (String) pincode;
	}

	
	
	
	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getDigsign() {
		return digsign;
	}

	public void setDigsign(String digsign) {
		this.digsign = digsign;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getLicenseauthority() {
		return licenseauthority;
	}

	public void setLicenseauthority(String licenseauthority) {
		this.licenseauthority = licenseauthority;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getGendername() {
		return gendername;
	}

	public void setGendername(String gendername) {
		this.gendername = gendername;
	}

	public String getBldGrname() {
		return bldGrname;
	}

	public void setBldGrname(String bldGrname) {
		this.bldGrname = bldGrname;
	}

	public String getBldGr() {
		return bldGr;
	}

	public void setBldGr(String bldGr) {
		this.bldGr = bldGr;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUseraccount() {
		return useraccount;
	}

	public void setUseraccount(String useraccount) {
		this.useraccount = useraccount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfileimage() {
		return profileimage;
	}

	public void setProfileimage(String profileimage) {
		this.profileimage = profileimage;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getAadhaar() {
		return aadhaar;
	}

	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
	}

	public String getImano() {
		return imano;
	}

	public void setImano(String imano) {
		this.imano = imano;
	}

	public String getPancardno() {
		return pancardno;
	}

	public void setPancardno(String pancardno) {
		this.pancardno = pancardno;
	}

	public String getPassportno() {
		return passportno;
	}

	public void setPassportno(String passportno) {
		this.passportno = passportno;
	}

	public String getVotercardno() {
		return votercardno;
	}

	public void setVotercardno(String votercardno) {
		this.votercardno = votercardno;
	}

	public String getLicenceno() {
		return licenceno;
	}

	public void setLicenceno(String licenceno) {
		this.licenceno = licenceno;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getProfpicture() {
		return profpicture;
	}

	public void setProfpicture(String profpicture) {
		this.profpicture = profpicture;
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
