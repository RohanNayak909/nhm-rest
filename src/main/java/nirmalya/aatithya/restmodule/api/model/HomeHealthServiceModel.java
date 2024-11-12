package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HomeHealthServiceModel {
	private String pending;
	private String assigned;
	private String accepted;
	private String rejected;
	private String documentationDone;
	private String inProgress;
	private String feedbackTaken;
	private String serviceCompleted;
	private String serviceClosed;
	
	
	private String orderId;
	private String username;
	private String serviceName;
	private String adress;
	private String location;
	private String city;
	private String pincode;
	private String status;
	private String description;
	
	private String stratDate;
	private String endDate;
	private String homeServiceType;
	private String duration;
	private String servicePrice;
	private String totalPrice;
	
	private String homeServiceId;
	private String remark;
	private String userStatus;
	private String patientMobNo;
	private String patientId;
	private String patientname;
	private String email;
	private String userToken;
	
	private String documentationStatus;
	
	private String caseStudy;
	private String patientDetails;
	private String nurseAide;
	private String payDetails;
	private String docEditStatus;
	
	public HomeHealthServiceModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public HomeHealthServiceModel(Object pending, Object assigned, Object accepted, Object rejected,
			Object documentationDone, Object inProgress, Object feedbackTaken, Object serviceCompleted,
			Object serviceClosed) {
		super();
		this.pending = (String)pending;
		this.assigned = (String)assigned;
		this.accepted = (String)accepted;
		this.rejected = (String)rejected;
		this.documentationDone = (String)documentationDone;
		this.inProgress = (String)inProgress;
		this.feedbackTaken = (String)feedbackTaken;
		this.serviceCompleted = (String)serviceCompleted;
		this.serviceClosed = (String)serviceClosed;
	}
	
	public HomeHealthServiceModel(Object orderId, Object patientname, Object patientMobNo, Object serviceName,
			Object email, Object userToken,Object status,Object patientId) {
		super();
		this.orderId = (String)orderId;
		this.patientname = (String)patientname;
		this.patientMobNo = (String)patientMobNo;
		this.serviceName = (String)serviceName;
		this.email = (String)email;
		this.userToken = (String)userToken;
		this.status = (String)status;
		this.patientId = (String)patientId;
	}
	public HomeHealthServiceModel(Object orderId, Object totalPrice) {
		super();
		this.orderId = (String)orderId;
		this.totalPrice = (String)totalPrice;
	}
	public HomeHealthServiceModel(Object orderId, Object username, Object serviceName, Object adress,
			Object location, Object city, Object pincode, Object status,
			Object description,Object stratDate,Object endDate,Object homeServiceType,Object duration,Object servicePrice
			,Object totalPrice,Object patientMobNo,Object patientId,Object patientname,Object documentationStatus
			,Object caseStudy,Object patientDetails,Object nurseAide,Object payDetails,Object docEditStatus) {
		super();
		this.orderId = (String)orderId;
		this.username = (String)username;
		this.serviceName = (String)serviceName;
		this.adress = (String)adress;
		this.location = (String)location;
		this.city = (String)city;
		this.pincode = (String)pincode;
		this.status = (String)status;
		this.description = (String)description;
		this.stratDate = (String)stratDate;
		this.endDate = (String)endDate;
		this.homeServiceType = (String)homeServiceType;
		this.duration = (String)duration;
		this.servicePrice = (String)servicePrice;
		this.totalPrice = (String)totalPrice;
		this.patientMobNo = (String)patientMobNo;
		this.patientId = (String)patientId;
		this.patientname = (String)patientname;
		this.documentationStatus = (String)documentationStatus;
		this.caseStudy = (String)caseStudy;
		this.patientDetails = (String)patientDetails;
		this.nurseAide = (String)nurseAide;
		this.payDetails = (String)payDetails;
		this.docEditStatus = (String)docEditStatus;
		
	}
	public String getPending() {
		return pending;
	}
	public void setPending(String pending) {
		this.pending = pending;
	}
	public String getAssigned() {
		return assigned;
	}
	public void setAssigned(String assigned) {
		this.assigned = assigned;
	}
	public String getAccepted() {
		return accepted;
	}
	public void setAccepted(String accepted) {
		this.accepted = accepted;
	}
	public String getRejected() {
		return rejected;
	}
	public void setRejected(String rejected) {
		this.rejected = rejected;
	}
	public String getDocumentationDone() {
		return documentationDone;
	}
	public void setDocumentationDone(String documentationDone) {
		this.documentationDone = documentationDone;
	}
	public String getInProgress() {
		return inProgress;
	}
	public void setInProgress(String inProgress) {
		this.inProgress = inProgress;
	}
	public String getFeedbackTaken() {
		return feedbackTaken;
	}
	public void setFeedbackTaken(String feedbackTaken) {
		this.feedbackTaken = feedbackTaken;
	}
	public String getServiceCompleted() {
		return serviceCompleted;
	}
	public void setServiceCompleted(String serviceCompleted) {
		this.serviceCompleted = serviceCompleted;
	}
	public String getServiceClosed() {
		return serviceClosed;
	}
	public void setServiceClosed(String serviceClosed) {
		this.serviceClosed = serviceClosed;
	}
	
	
	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getServiceName() {
		return serviceName;
	}


	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}


	public String getAdress() {
		return adress;
	}


	public void setAdress(String adress) {
		this.adress = adress;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getPincode() {
		return pincode;
	}


	public void setPincode(String pincode) {
		this.pincode = pincode;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getStratDate() {
		return stratDate;
	}


	public void setStratDate(String stratDate) {
		this.stratDate = stratDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public String getHomeServiceType() {
		return homeServiceType;
	}


	public void setHomeServiceType(String homeServiceType) {
		this.homeServiceType = homeServiceType;
	}


	public String getDuration() {
		return duration;
	}


	public void setDuration(String duration) {
		this.duration = duration;
	}


	public String getServicePrice() {
		return servicePrice;
	}


	public void setServicePrice(String servicePrice) {
		this.servicePrice = servicePrice;
	}


	public String getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	


	public String getHomeServiceId() {
		return homeServiceId;
	}


	public void setHomeServiceId(String homeServiceId) {
		this.homeServiceId = homeServiceId;
	}
	


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}
	


	public String getUserStatus() {
		return userStatus;
	}


	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	



	public String getPatientMobNo() {
		return patientMobNo;
	}


	public void setPatientMobNo(String patientMobNo) {
		this.patientMobNo = patientMobNo;
	}
	


	public String getPatientId() {
		return patientId;
	}


	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}


	public String getPatientname() {
		return patientname;
	}


	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}
	


	public String getDocumentationStatus() {
		return documentationStatus;
	}


	public void setDocumentationStatus(String documentationStatus) {
		this.documentationStatus = documentationStatus;
	}
	


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUserToken() {
		return userToken;
	}


	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	


	public String getCaseStudy() {
		return caseStudy;
	}


	public void setCaseStudy(String caseStudy) {
		this.caseStudy = caseStudy;
	}


	public String getPatientDetails() {
		return patientDetails;
	}


	public void setPatientDetails(String patientDetails) {
		this.patientDetails = patientDetails;
	}


	public String getNurseAide() {
		return nurseAide;
	}


	public void setNurseAide(String nurseAide) {
		this.nurseAide = nurseAide;
	}
	


	public String getPayDetails() {
		return payDetails;
	}


	public void setPayDetails(String payDetails) {
		this.payDetails = payDetails;
	}
	


	public String getDocEditStatus() {
		return docEditStatus;
	}


	public void setDocEditStatus(String docEditStatus) {
		this.docEditStatus = docEditStatus;
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
