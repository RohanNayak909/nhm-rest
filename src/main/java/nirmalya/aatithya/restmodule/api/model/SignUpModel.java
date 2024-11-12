package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SignUpModel {

	private String id;
	private Integer title;
	private String fName;
	private String lName;
	private String mobile;
	private Integer country;
	private Integer state;
	private Integer gender;
	private Integer age;
	private Double height;
	private Double weight;
	private String email;
	private String aadhar;
	private String enteredBy;
	private List<String> profileImage = new ArrayList<String>();
	private String profileImageName;
	private String profileImageType;
	private String userId;
	private String countryCode;
	private String stateCode;
	private String password;

	private String countryName;
	private String stateName;
	private String genderName;
	private String dob;
	private String ageYears;

	private String districtid;
	private String cityid;

	private List<String> documentUpload;
	private String documentExt;
    private String documentName;
	public SignUpModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SignUpModel(Object id, Object fName, Object mobile, Object countryName, Object stateName, Object genderName,
			Object age, Object height, Object weight, Object email, Object aadhar, Object profileImageName, Object dob,
			Object ageYears, Object lName) {
		super();
		this.id = (String) id;
		this.fName = (String) fName;
		this.mobile = (String) mobile;
		this.countryName = (String) countryName;
		this.stateName = (String) stateName;
		this.genderName = (String) genderName;
		this.age = (Integer) age;
		this.height = (Double) height;
		this.weight = (Double) weight;
		this.email = (String) email;
		this.aadhar = (String) aadhar;
		this.profileImageName = (String) profileImageName;
		this.dob = (String) dob;
		this.ageYears = (String) ageYears;
		this.lName = (String) lName;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public List<String> getDocumentUpload() {
		return documentUpload;
	}

	public void setDocumentUpload(List<String> documentUpload) {
		this.documentUpload = documentUpload;
	}

	public String getDocumentExt() {
		return documentExt;
	}

	public void setDocumentExt(String documentExt) {
		this.documentExt = documentExt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getTitle() {
		return title;
	}

	public void setTitle(Integer title) {
		this.title = title;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public String getEnteredBy() {
		return enteredBy;
	}

	public void setEnteredBy(String enteredBy) {
		this.enteredBy = enteredBy;
	}

	public List<String> getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(List<String> profileImage) {
		this.profileImage = profileImage;
	}

	public String getProfileImageName() {
		return profileImageName;
	}

	public void setProfileImageName(String profileImageName) {
		this.profileImageName = profileImageName;
	}

	public String getProfileImageType() {
		return profileImageType;
	}

	public void setProfileImageType(String profileImageType) {
		this.profileImageType = profileImageType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAgeYears() {
		return ageYears;
	}

	public void setAgeYears(String ageYears) {
		this.ageYears = ageYears;
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
