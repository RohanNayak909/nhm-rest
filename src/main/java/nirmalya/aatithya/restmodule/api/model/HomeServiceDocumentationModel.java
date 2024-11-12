package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HomeServiceDocumentationModel {
	private String homeserviceId;
	private String homeserviceType;
	private String patientId;
	private String nurseName;
	private String coOrdinatorName;
	private String dateOfVisit;
	private String patientName;
	private String phno;
	private String age;
	
	
	private String gender;
	private String nationality;
	private String mainComplaint;
	private String currentMedication;
	private String drugAllergy;
	private String recentAdmsn;
	private String treatmentProcedure;
	private String bp;
	private String tempPulse;
	
	private String respiratoryRate;
	private String generalBuild;
	private String localExam;
	private String diagnosis;
	private String additionalComment;
	private String treatment;
	
	private String givenPlan;
	private String durationCare;
	private String allShift;
	private String durationShift;
	private String otherDetails;
	private String patientCare;
	
	private String informedSme;
	private String commentSme;
	private String feedback;
	
	private String nurse12HrsPrice;
	private String nurse24HrsPrice;
	private String nurseRegdFee;
	private String nurseGst;
	private String pca12HrsPrice;
	private String pca24HrsPrice;
	private String pcaRegdFee;
	private String pcaGst;
	private String address;
	
	private  ArrayList nurseList; 
	private  ArrayList pcaList;
	private String homeServiceOrderId;
	
	private String documentationStatus;
	
	private String genderId;
	private String nationalityId;
	private String followDate;
	public HomeServiceDocumentationModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HomeServiceDocumentationModel(Object homeserviceId, Object homeserviceType, Object patientId,
			Object nurseName, Object coOrdinatorName, Object dateOfVisit, Object patientName, Object phno, Object age,
			Object gender, Object nationality, Object mainComplaint, Object currentMedication, Object drugAllergy,
			Object recentAdmsn, Object treatmentProcedure, Object bp, Object tempPulse, Object respiratoryRate,
			Object generalBuild, Object localExam, Object diagnosis, Object additionalComment, Object treatment,
			Object givenPlan, Object durationCare, Object allShift, Object durationShift, Object otherDetails,
			Object patientCare, Object informedSme, Object commentSme, Object feedback
			, Object nurse12HrsPrice, Object nurse24HrsPrice, Object nurseRegdFee, Object nurseGst,
			Object pca12HrsPrice, Object pca24HrsPrice, Object pcaRegdFee, Object pcaGst, Object address) {
		super();
		this.homeserviceId = (String)homeserviceId;
		this.homeserviceType = (String)homeserviceType;
		this.patientId = (String)patientId;
		this.nurseName = (String)nurseName;
		this.coOrdinatorName = (String)coOrdinatorName;
		this.dateOfVisit = (String)dateOfVisit;
		this.patientName = (String)patientName;
		this.phno = (String)phno;
		this.age = (String)age;
		this.gender = (String)gender;
		this.nationality = (String)nationality;
		this.mainComplaint = (String)mainComplaint;
		this.currentMedication = (String)currentMedication;
		this.drugAllergy = (String)drugAllergy;
		this.recentAdmsn = (String)recentAdmsn;
		this.treatmentProcedure = (String)treatmentProcedure;
		this.bp = (String)bp;
		this.tempPulse = (String)tempPulse;
		this.respiratoryRate = (String)respiratoryRate;
		this.generalBuild = (String)generalBuild;
		this.localExam = (String)localExam;
		this.diagnosis = (String)diagnosis;
		this.additionalComment = (String)additionalComment;
		this.treatment = (String)treatment;
		this.givenPlan = (String)givenPlan;
		this.durationCare = (String)durationCare;
		this.allShift = (String)allShift;
		this.durationShift = (String)durationShift;
		this.otherDetails = (String)otherDetails;
		this.patientCare = (String)patientCare;
		this.informedSme = (String)informedSme;
		this.commentSme = (String)commentSme;
		this.feedback = (String)feedback;
		

		this.nurse12HrsPrice = (String)nurse12HrsPrice;
		this.nurse24HrsPrice = (String)nurse24HrsPrice;
		this.nurseRegdFee = (String)nurseRegdFee;
		this.nurseGst = (String)nurseGst;
		this.pca12HrsPrice = (String)pca12HrsPrice;
		this.pca24HrsPrice = (String)pca24HrsPrice;
		this.pcaRegdFee = (String)pcaRegdFee;
		this.pcaGst = (String)pcaGst;
		this.address = (String)address;
	}
	
	
	public HomeServiceDocumentationModel(Object homeserviceId, Object patientId, Object nurseName,
			Object coOrdinatorName, Object dateOfVisit, Object patientName, Object phno, Object age, Object genderId, Object gender,
			Object nationalityId,Object nationality, Object mainComplaint, Object currentMedication, Object drugAllergy, Object recentAdmsn,
			Object treatmentProcedure, Object bp, Object tempPulse, Object respiratoryRate, Object generalBuild,
			Object localExam, Object diagnosis, Object additionalComment, Object treatment, Object givenPlan,
			Object durationCare, Object allShift, Object durationShift, Object otherDetails, Object patientCare,
			Object informedSme, Object commentSme, Object feedback, Object nurse12HrsPrice, Object nurse24HrsPrice,
			Object nurseRegdFee, Object nurseGst, Object pca12HrsPrice, Object pca24HrsPrice, Object pcaRegdFee,
			Object pcaGst, Object address, Object homeServiceOrderId,Object followDate) {
		super();
		this.homeserviceId = (String)homeserviceId;
		this.patientId = (String)patientId;
		this.nurseName = (String)nurseName;
		this.coOrdinatorName = (String)coOrdinatorName;
		this.dateOfVisit = (String)dateOfVisit;
		this.patientName = (String)patientName;
		this.phno = (String)phno;
		this.age = (String)age;
		this.genderId  = (String)genderId;
		this.gender = (String)gender;
		this.nationalityId = (String)nationalityId;
		this.nationality = (String)nationality;
		this.mainComplaint = (String)mainComplaint;
		this.currentMedication = (String)currentMedication;
		this.drugAllergy = (String)drugAllergy;
		this.recentAdmsn = (String)recentAdmsn;
		this.treatmentProcedure = (String)treatmentProcedure;
		this.bp = (String)bp;
		this.tempPulse = (String)tempPulse;
		this.respiratoryRate = (String)respiratoryRate;
		this.generalBuild = (String)generalBuild;
		this.localExam = (String)localExam;
		this.diagnosis = (String)diagnosis;
		this.additionalComment = (String)additionalComment;
		this.treatment = (String)treatment;
		this.givenPlan = (String)givenPlan;
		this.durationCare = (String)durationCare;
		this.allShift = (String)allShift;
		this.durationShift = (String)durationShift;
		this.otherDetails = (String)otherDetails;
		this.patientCare =(String)patientCare;
		this.informedSme = (String)informedSme;
		this.commentSme = (String)commentSme;
		this.feedback = (String)feedback;
		this.nurse12HrsPrice = (String)nurse12HrsPrice;
		this.nurse24HrsPrice = (String)nurse24HrsPrice;
		this.nurseRegdFee =(String)nurseRegdFee;
		this.nurseGst = (String)nurseGst;
		this.pca12HrsPrice = (String)pca12HrsPrice;
		this.pca24HrsPrice = (String)pca24HrsPrice;
		this.pcaRegdFee = (String)pcaRegdFee;
		this.pcaGst = (String)pcaGst;
		this.address = (String)address;
		this.homeServiceOrderId = (String)homeServiceOrderId;
		this.followDate = (String)followDate;
	}
	public String getHomeserviceId() {
		return homeserviceId;
	}
	public void setHomeserviceId(String homeserviceId) {
		this.homeserviceId = homeserviceId;
	}
	public String getHomeserviceType() {
		return homeserviceType;
	}
	public void setHomeserviceType(String homeserviceType) {
		this.homeserviceType = homeserviceType;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getNurseName() {
		return nurseName;
	}
	public void setNurseName(String nurseName) {
		this.nurseName = nurseName;
	}
	public String getCoOrdinatorName() {
		return coOrdinatorName;
	}
	public void setCoOrdinatorName(String coOrdinatorName) {
		this.coOrdinatorName = coOrdinatorName;
	}
	public String getDateOfVisit() {
		return dateOfVisit;
	}
	public void setDateOfVisit(String dateOfVisit) {
		this.dateOfVisit = dateOfVisit;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getPhno() {
		return phno;
	}
	public void setPhno(String phno) {
		this.phno = phno;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getMainComplaint() {
		return mainComplaint;
	}
	public void setMainComplaint(String mainComplaint) {
		this.mainComplaint = mainComplaint;
	}
	public String getCurrentMedication() {
		return currentMedication;
	}
	public void setCurrentMedication(String currentMedication) {
		this.currentMedication = currentMedication;
	}
	public String getDrugAllergy() {
		return drugAllergy;
	}
	public void setDrugAllergy(String drugAllergy) {
		this.drugAllergy = drugAllergy;
	}
	public String getRecentAdmsn() {
		return recentAdmsn;
	}
	public void setRecentAdmsn(String recentAdmsn) {
		this.recentAdmsn = recentAdmsn;
	}
	public String getTreatmentProcedure() {
		return treatmentProcedure;
	}
	public void setTreatmentProcedure(String treatmentProcedure) {
		this.treatmentProcedure = treatmentProcedure;
	}
	public String getBp() {
		return bp;
	}
	public void setBp(String bp) {
		this.bp = bp;
	}
	public String getTempPulse() {
		return tempPulse;
	}
	public void setTempPulse(String tempPulse) {
		this.tempPulse = tempPulse;
	}
	public String getRespiratoryRate() {
		return respiratoryRate;
	}
	public void setRespiratoryRate(String respiratoryRate) {
		this.respiratoryRate = respiratoryRate;
	}
	public String getGeneralBuild() {
		return generalBuild;
	}
	public void setGeneralBuild(String generalBuild) {
		this.generalBuild = generalBuild;
	}
	public String getLocalExam() {
		return localExam;
	}
	public void setLocalExam(String localExam) {
		this.localExam = localExam;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getAdditionalComment() {
		return additionalComment;
	}
	public void setAdditionalComment(String additionalComment) {
		this.additionalComment = additionalComment;
	}
	public String getTreatment() {
		return treatment;
	}
	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}
	public String getGivenPlan() {
		return givenPlan;
	}
	public void setGivenPlan(String givenPlan) {
		this.givenPlan = givenPlan;
	}
	public String getDurationCare() {
		return durationCare;
	}
	public void setDurationCare(String durationCare) {
		this.durationCare = durationCare;
	}
	public String getAllShift() {
		return allShift;
	}
	public void setAllShift(String allShift) {
		this.allShift = allShift;
	}
	public String getDurationShift() {
		return durationShift;
	}
	public void setDurationShift(String durationShift) {
		this.durationShift = durationShift;
	}
	public String getOtherDetails() {
		return otherDetails;
	}
	public void setOtherDetails(String otherDetails) {
		this.otherDetails = otherDetails;
	}
	public String getPatientCare() {
		return patientCare;
	}
	public void setPatientCare(String patientCare) {
		this.patientCare = patientCare;
	}
	public String getInformedSme() {
		return informedSme;
	}
	public void setInformedSme(String informedSme) {
		this.informedSme = informedSme;
	}
	public String getCommentSme() {
		return commentSme;
	}
	public void setCommentSme(String commentSme) {
		this.commentSme = commentSme;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
	
	public String getNurse12HrsPrice() {
		return nurse12HrsPrice;
	}
	public void setNurse12HrsPrice(String nurse12HrsPrice) {
		this.nurse12HrsPrice = nurse12HrsPrice;
	}
	public String getNurse24HrsPrice() {
		return nurse24HrsPrice;
	}
	public void setNurse24HrsPrice(String nurse24HrsPrice) {
		this.nurse24HrsPrice = nurse24HrsPrice;
	}
	public String getNurseRegdFee() {
		return nurseRegdFee;
	}
	public void setNurseRegdFee(String nurseRegdFee) {
		this.nurseRegdFee = nurseRegdFee;
	}
	public String getNurseGst() {
		return nurseGst;
	}
	public void setNurseGst(String nurseGst) {
		this.nurseGst = nurseGst;
	}
	public String getPca12HrsPrice() {
		return pca12HrsPrice;
	}
	public void setPca12HrsPrice(String pca12HrsPrice) {
		this.pca12HrsPrice = pca12HrsPrice;
	}
	public String getPca24HrsPrice() {
		return pca24HrsPrice;
	}
	public void setPca24HrsPrice(String pca24HrsPrice) {
		this.pca24HrsPrice = pca24HrsPrice;
	}
	public String getPcaRegdFee() {
		return pcaRegdFee;
	}
	public void setPcaRegdFee(String pcaRegdFee) {
		this.pcaRegdFee = pcaRegdFee;
	}
	public String getPcaGst() {
		return pcaGst;
	}
	public void setPcaGst(String pcaGst) {
		this.pcaGst = pcaGst;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public ArrayList getNurseList() {
		return nurseList;
	}
	public void setNurseList(ArrayList nurseList) {
		this.nurseList = nurseList;
	}
	public ArrayList getPcaList() {
		return pcaList;
	}
	public void setPcaList(ArrayList pcaList) {
		this.pcaList = pcaList;
	}
	
	public String getHomeServiceOrderId() {
		return homeServiceOrderId;
	}
	public void setHomeServiceOrderId(String homeServiceOrderId) {
		this.homeServiceOrderId = homeServiceOrderId;
	}
	
	public String getDocumentationStatus() {
		return documentationStatus;
	}
	public void setDocumentationStatus(String documentationStatus) {
		this.documentationStatus = documentationStatus;
	}
	
	public String getGenderId() {
		return genderId;
	}
	public void setGenderId(String genderId) {
		this.genderId = genderId;
	}
	public String getNationalityId() {
		return nationalityId;
	}
	public void setNationalityId(String nationalityId) {
		this.nationalityId = nationalityId;
	}
	
	public String getFollowDate() {
		return followDate;
	}
	public void setFollowDate(String followDate) {
		this.followDate = followDate;
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
