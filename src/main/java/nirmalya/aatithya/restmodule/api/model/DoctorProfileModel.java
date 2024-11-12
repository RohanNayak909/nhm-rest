package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DoctorProfileModel {
   
	private String doctorId;
	private String email;
	private String education;
	private String regId;
	private String phone;
	private String experience;
	private String specialization;
	private String language;
	private String doctorName;
	private String address;
	private String profileImage;
	
	private String clinicName;
	private String age;
	private String qualification;
	private String aboutMe;
	private String city;
	private String audioFee;
	private String videoFee;
	private String physicalFee;
	private String openingTime;
	private String closingTime;
	private String breakFrom;
	private String breakTime;
	private String slotBooking;
	private String offDay;
	private String accountName;
	private String accountNumber;
	private String ifscCode;
	private String gender;
	private  ArrayList timeSlotList; 
	private  ArrayList breakList;
	private String genderId;
	private String durationId;
	private String offDayId;
	private String doctorSignature;
	private String extension;
	public DoctorProfileModel() {
		super();
		// TODO Auto-generated constructor stub
	}








	public DoctorProfileModel(Object doctorId, Object email, Object education, Object regId, Object phone,
			Object experience, Object specialization, Object language, Object doctorName, Object address,
			Object profileImage, Object clinicName, Object age, Object qualification, Object aboutMe, Object city,
			Object audioFee, Object videoFee, Object physicalFee, Object openingTime, Object closingTime,
			Object breakFrom, Object breakTime, Object durationId, Object offDayId, Object accountName,
			Object accountNumber, Object ifscCode, Object gender, Object genderId, Object slotBooking,Object offDay,Object doctorSignature) {
		super();
		this.doctorId = (String)doctorId;
		this.email = (String)email;
		this.education = (String)education;
		this.regId = (String)regId;
		this.phone = (String)phone;
		this.experience = (String)experience;
		this.specialization = (String)specialization;
		this.language = (String)language;
		this.doctorName = (String)doctorName;
		this.address = (String)address;
		this.profileImage = (String)profileImage;
		this.clinicName = (String)clinicName;
		this.age = (String)age;
		this.qualification = (String)qualification;
		this.aboutMe = (String)aboutMe;
		this.city = (String)city;
		this.audioFee = (String)audioFee;
		this.videoFee = (String)videoFee;
		this.physicalFee = (String)physicalFee;
		this.openingTime = (String)openingTime;
		this.closingTime = (String)closingTime;
		this.breakFrom = (String)breakFrom;
		this.breakTime = (String)breakTime;
		this.durationId = (String)durationId;
		this.offDayId = (String)offDayId;
		this.accountName = (String)accountName;
		this.accountNumber = (String)accountNumber;
		this.ifscCode = (String)ifscCode;
		this.gender = (String)gender;
		this.genderId = (String)genderId;
		this.slotBooking = (String)slotBooking;
		this.offDay = (String)offDay;
		this.doctorSignature = (String)doctorSignature;
	}










	public String getDoctorId() {
		return doctorId;
	}









	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}









	public String getEmail() {
		return email;
	}









	public void setEmail(String email) {
		this.email = email;
	}









	public String getEducation() {
		return education;
	}









	public void setEducation(String education) {
		this.education = education;
	}









	public String getRegId() {
		return regId;
	}









	public void setRegId(String regId) {
		this.regId = regId;
	}









	public String getPhone() {
		return phone;
	}









	public void setPhone(String phone) {
		this.phone = phone;
	}









	public String getExperience() {
		return experience;
	}









	public void setExperience(String experience) {
		this.experience = experience;
	}









	public String getSpecialization() {
		return specialization;
	}









	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}









	public String getLanguage() {
		return language;
	}









	public void setLanguage(String language) {
		this.language = language;
	}









	public String getDoctorName() {
		return doctorName;
	}









	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}









	public String getAddress() {
		return address;
	}









	public void setAddress(String address) {
		this.address = address;
	}









	public String getProfileImage() {
		return profileImage;
	}









	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}









	public String getClinicName() {
		return clinicName;
	}









	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}









	public String getAge() {
		return age;
	}









	public void setAge(String age) {
		this.age = age;
	}









	public String getQualification() {
		return qualification;
	}









	public void setQualification(String qualification) {
		this.qualification = qualification;
	}









	public String getAboutMe() {
		return aboutMe;
	}









	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}









	public String getCity() {
		return city;
	}









	public void setCity(String city) {
		this.city = city;
	}









	public String getAudioFee() {
		return audioFee;
	}









	public void setAudioFee(String audioFee) {
		this.audioFee = audioFee;
	}









	public String getVideoFee() {
		return videoFee;
	}









	public void setVideoFee(String videoFee) {
		this.videoFee = videoFee;
	}









	public String getPhysicalFee() {
		return physicalFee;
	}









	public void setPhysicalFee(String physicalFee) {
		this.physicalFee = physicalFee;
	}









	public String getOpeningTime() {
		return openingTime;
	}









	public void setOpeningTime(String openingTime) {
		this.openingTime = openingTime;
	}









	public String getClosingTime() {
		return closingTime;
	}









	public void setClosingTime(String closingTime) {
		this.closingTime = closingTime;
	}









	public String getBreakFrom() {
		return breakFrom;
	}









	public void setBreakFrom(String breakFrom) {
		this.breakFrom = breakFrom;
	}









	public String getBreakTime() {
		return breakTime;
	}









	public void setBreakTime(String breakTime) {
		this.breakTime = breakTime;
	}









	public String getSlotBooking() {
		return slotBooking;
	}









	public void setSlotBooking(String slotBooking) {
		this.slotBooking = slotBooking;
	}









	public String getOffDay() {
		return offDay;
	}









	public void setOffDay(String offDay) {
		this.offDay = offDay;
	}









	public String getAccountName() {
		return accountName;
	}









	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}









	public String getAccountNumber() {
		return accountNumber;
	}









	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}









	public String getIfscCode() {
		return ifscCode;
	}









	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}









	public String getGender() {
		return gender;
	}









	public void setGender(String gender) {
		this.gender = gender;
	}

	








	public ArrayList getTimeSlotList() {
		return timeSlotList;
	}









	public void setTimeSlotList(ArrayList timeSlotList) {
		this.timeSlotList = timeSlotList;
	}









	public ArrayList getBreakList() {
		return breakList;
	}









	public void setBreakList(ArrayList breakList) {
		this.breakList = breakList;
	}


	public String getGenderId() {
		return genderId;
	}

	public void setGenderId(String genderId) {
		this.genderId = genderId;
	}

	public String getDurationId() {
		return durationId;
	}


	public void setDurationId(String durationId) {
		this.durationId = durationId;
	}


	public String getOffDayId() {
		return offDayId;
	}

	public void setOffDayId(String offDayId) {
		this.offDayId = offDayId;
	}

	public String getDoctorSignature() {
		return doctorSignature;
	}


	public void setDoctorSignature(String doctorSignature) {
		this.doctorSignature = doctorSignature;
	}
	


	public String getExtension() {
		return extension;
	}









	public void setExtension(String extension) {
		this.extension = extension;
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
