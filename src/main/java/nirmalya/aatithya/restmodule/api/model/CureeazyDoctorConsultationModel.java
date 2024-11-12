package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CureeazyDoctorConsultationModel {
	private String doctorId;
	private String doctorName;
	private String doctorSpecialization;
	private String doctorExperience;
	private String doctorEducation;
	private String doctorLanguage;
	private String clinicName;
	private String about;
	private String profileImg;
	private String userId;
	private String favStatus;
	private String reviewCount;
	private String ratingCount;
	
	public CureeazyDoctorConsultationModel(Object doctorId, Object doctorName, Object doctorSpecialization, Object doctorExperience, Object doctorEducation,
			Object doctorLanguage,Object clinicName, Object about,Object profileImg,Object favStatus
			,Object reviewCount,Object ratingCount) {
		super();
		this.doctorId = (String) doctorId;
		this.doctorName = (String) doctorName;
		this.doctorSpecialization = (String) doctorSpecialization;
		this.doctorExperience = (String) doctorExperience;
		this.doctorEducation = (String) doctorEducation;
		this.doctorLanguage = (String) doctorLanguage;
		this.clinicName = (String) clinicName;
		this.about = (String) about; 
		this.profileImg = (String) profileImg; 
		this.favStatus = (String) favStatus; 
		this.reviewCount = (String) reviewCount; 
		this.ratingCount = (String) ratingCount; 
	}
	
	public CureeazyDoctorConsultationModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getDoctorSpecialization() {
		return doctorSpecialization;
	}
	public void setDoctorSpecialization(String doctorSpecialization) {
		this.doctorSpecialization = doctorSpecialization;
	}
	public String getDoctorExperience() {
		return doctorExperience;
	}
	public void setDoctorExperience(String doctorExperience) {
		this.doctorExperience = doctorExperience;
	}
	public String getDoctorEducation() {
		return doctorEducation;
	}
	public void setDoctorEducation(String doctorEducation) {
		this.doctorEducation = doctorEducation;
	}
	public String getDoctorLanguage() {
		return doctorLanguage;
	}
	public void setDoctorLanguage(String doctorLanguage) {
		this.doctorLanguage = doctorLanguage;
	}
	public String getClinicName() {
		return clinicName;
	}
	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	
	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFavStatus() {
		return favStatus;
	}

	public void setFavStatus(String favStatus) {
		this.favStatus = favStatus;
	}
	






	

	public String getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(String reviewCount) {
		this.reviewCount = reviewCount;
	}

	public String getRatingCount() {
		return ratingCount;
	}

	public void setRatingCount(String ratingCount) {
		this.ratingCount = ratingCount;
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
