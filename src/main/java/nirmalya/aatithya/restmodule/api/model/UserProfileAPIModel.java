package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserProfileAPIModel {

	private String eCardNo;
	private String profileImage;
	private String fullName;
	private String dob;
	private String bloodGroup;
	private String gender;
	private String mobile;
	private String eName;
	private String eRelation;
	private String eMobile;
	private String fDoctor;
	private String speciality;
	private String docMobile;
	private String title;
	private String fName;
	private String lName;
	private String profileImageName;
	private String profileImageType;
	private List<String> pImage = new ArrayList<String>();
	private String address;
	private String pAddress;
	
	private String genderId;
	private String eRelationId;
	private String specialityId;
	private String bloodGroupId;
	private String maritialstatus;
	private String occupation;
	private String qualification;
	private String specialization;
	private String pancardno;
	private String passportno;
	private String adharno;
	private String votercardno;
	private String licenceno;
	private String licenceauthority;
	private String email;
	private String pincode;
	private String cityid;
	private String distid;
	private String stateid;
	private String countryid;
	private String city;
	private String dist;
	private String state;
	private String country;
	private String mstausid;
	private String userId;
	private List<APIEmergencyModel> emergenceList = new ArrayList<APIEmergencyModel>();
	private List<APIEmergencyModel> familyDoctorList = new ArrayList<APIEmergencyModel>();
	private List<PatientFamilyDetailsModel> familyDetailsList = new ArrayList<PatientFamilyDetailsModel>();
	public UserProfileAPIModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserProfileAPIModel(Object eCardNo, Object profileImage, Object fullName, Object dob, Object bloodGroup,
			Object gender, Object mobile, Object title, Object fName, Object lName, Object address, Object bloodGroupId,
			Object pAddress, Object maritialstatus, Object occupation, Object qualification, Object specialization,
			Object pancardno, Object passportno, Object adharno, Object votercardno, Object licenceno,
			Object licenceauthority, Object email, Object pincode, Object cityid, Object distid,
			Object stateid, Object countryid, Object city, Object dist, Object state, Object country, Object genderId,
			Object mstausid) {
		super();
		this.eCardNo = (String) eCardNo;
		this.profileImage = (String) profileImage;
		this.fullName = (String) fullName;
		this.dob = (String) dob;
		this.bloodGroup = (String) bloodGroup;
		this.gender = (String) gender;
		this.mobile = (String) mobile;
		
		this.title = (String) title;
		this.fName = (String) fName;
		this.lName = (String) lName;
		this.address = (String) address;
		this.bloodGroupId = (String) bloodGroupId;
		this.pAddress = (String) pAddress;
		this.maritialstatus = (String) maritialstatus;
		this.occupation = (String) occupation;
		this.qualification = (String) qualification;
		this.specialization = (String) specialization;
		this.pancardno = (String) pancardno;
		this.passportno = (String) passportno;
		this.adharno = (String) adharno;
		this.votercardno = (String) votercardno;
		this.licenceno = (String) licenceno;
		this.licenceauthority = (String) licenceauthority;
		this.email = (String) email;
		this.pincode = (String) pincode;
		this.cityid = (String) cityid;
		this.distid = (String) distid;
		this.stateid = (String) stateid;
		this.countryid = (String) countryid;
		this.city = (String) city;
		this.dist = (String) dist;
		this.state = (String) state;
		this.country = (String) country;
		this.genderId = (String) genderId;
		this.mstausid = (String) mstausid;
	}

	public UserProfileAPIModel(Object eCardNo, Object fName, Object lName, Object mobile, Object email,
			Object address){
		super();
		this.eCardNo = (String) eCardNo;
		this.fName = (String) fName;
		this.lName = (String) lName;
		this.mobile = (String) mobile;
		this.email = (String) email;
		this.address = (String) address;
		
	}
	
	public String getpAddress() {
		return pAddress;
	}

	public void setpAddress(String pAddress) {
		this.pAddress = pAddress;
	}

	public String geteCardNo() {
		return eCardNo;
	}

	public void seteCardNo(String eCardNo) {
		this.eCardNo = eCardNo;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
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

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public String geteRelation() {
		return eRelation;
	}

	public void seteRelation(String eRelation) {
		this.eRelation = eRelation;
	}

	public String geteMobile() {
		return eMobile;
	}

	public void seteMobile(String eMobile) {
		this.eMobile = eMobile;
	}

	public String getfDoctor() {
		return fDoctor;
	}

	public void setfDoctor(String fDoctor) {
		this.fDoctor = fDoctor;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getDocMobile() {
		return docMobile;
	}

	public void setDocMobile(String docMobile) {
		this.docMobile = docMobile;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
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

	public List<String> getpImage() {
		return pImage;
	}

	public void setpImage(List<String> pImage) {
		this.pImage = pImage;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGenderId() {
		return genderId;
	}

	public void setGenderId(String genderId) {
		this.genderId = genderId;
	}

	public String geteRelationId() {
		return eRelationId;
	}

	public void seteRelationId(String eRelationId) {
		this.eRelationId = eRelationId;
	}

	public String getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(String specialityId) {
		this.specialityId = specialityId;
	}

	public String getBloodGroupId() {
		return bloodGroupId;
	}

	public void setBloodGroupId(String bloodGroupId) {
		this.bloodGroupId = bloodGroupId;
	}

	public String getMaritialstatus() {
		return maritialstatus;
	}

	public void setMaritialstatus(String maritialstatus) {
		this.maritialstatus = maritialstatus;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
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

	public String getAdharno() {
		return adharno;
	}

	public void setAdharno(String adharno) {
		this.adharno = adharno;
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

	public String getLicenceauthority() {
		return licenceauthority;
	}

	public void setLicenceauthority(String licenceauthority) {
		this.licenceauthority = licenceauthority;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}

	public String getDistid() {
		return distid;
	}

	public void setDistid(String distid) {
		this.distid = distid;
	}

	public String getStateid() {
		return stateid;
	}

	public void setStateid(String stateid) {
		this.stateid = stateid;
	}

	public String getCountryid() {
		return countryid;
	}

	public void setCountryid(String countryid) {
		this.countryid = countryid;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDist() {
		return dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
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

	public String getMstausid() {
		return mstausid;
	}

	public void setMstausid(String mstausid) {
		this.mstausid = mstausid;
	}

	public List<APIEmergencyModel> getEmergenceList() {
		return emergenceList;
	}

	public void setEmergenceList(List<APIEmergencyModel> emergenceList) {
		this.emergenceList = emergenceList;
	}

	public List<APIEmergencyModel> getFamilyDoctorList() {
		return familyDoctorList;
	}

	public void setFamilyDoctorList(List<APIEmergencyModel> familyDoctorList) {
		this.familyDoctorList = familyDoctorList;
	}

	public List<PatientFamilyDetailsModel> getFamilyDetailsList() {
		return familyDetailsList;
	}

	public void setFamilyDetailsList(List<PatientFamilyDetailsModel> familyDetailsList) {
		this.familyDetailsList = familyDetailsList;
	}
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
