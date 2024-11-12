package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CureEasyDrAppointBookingAPIModel {
	private String appointmentId; 
	private String doctorId; 
	private String doctorName; 
	private String bookingType; 
	private String docName;
	private String extension;
	private String healthIssue;
	private String slotId;
	private String slotDate;
	private String slotTime;
	private String createdBy;
	private String consultingCharges;
	private String coupon;
	private String couponAmount;
	private String otherCharges;
	private String totalCharges;
	private String paymentId;
	private String doctorSpecialization;
	private String doctorEducation;
	private String doctorLanguage;
	private String clinicName;
	private String appointmentDate;
	private String appointmentTime;
	private String about;
	private String profileing;
	private String userid;
	private String favStatus;
	private String approvestatus;
	private String doctorAddress;
	private String orderid;
	private String bookStatus;
	private String booking;
	private String showcode;
	private String consultType;

	public CureEasyDrAppointBookingAPIModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public CureEasyDrAppointBookingAPIModel(Object appointmentId,Object doctorId,Object doctorName,Object doctorSpecialization,
			Object doctorEducation,Object doctorLanguage,Object clinicName,Object appointmentDate,Object appointmentTime,
			Object profileing,Object userid,Object bookingType,Object approvestatus,Object doctorAddress,Object orderid
			,Object showcode,Object consultType,Object about) {
		super();
		this.appointmentId = (String) appointmentId;
		this.doctorId = (String) doctorId;
		this.doctorName = (String) doctorName;
		this.doctorSpecialization = (String) doctorSpecialization; 
		this.doctorEducation = (String) doctorEducation; 
		this.doctorLanguage = (String) doctorLanguage; 
		this.clinicName = (String) clinicName; 
		this.appointmentDate = (String) appointmentDate; 
		this.appointmentTime = (String) appointmentTime;
		
		this.profileing = (String) profileing;
		this.userid = (String) userid;
		this.bookingType = (String) bookingType;
		this.approvestatus = (String) approvestatus;
		this.doctorAddress = (String) doctorAddress;
		this.orderid = (String) orderid;
		this.showcode = (String) showcode;
		this.consultType = (String) consultType;
		this.about = (String) about;
	}
	
	
	
	
	
	public CureEasyDrAppointBookingAPIModel(Object doctorSpecialization,Object doctorEducation,Object doctorLanguage,Object clinicName,
			Object appointmentDate,Object appointmentTime,Object about,Object profileing,Object userid,Object favStatus ) {
		super();
		this.doctorSpecialization = (String) doctorSpecialization; 
		this.doctorEducation = (String) doctorEducation; 
		this.doctorLanguage = (String) doctorLanguage; 
		this.clinicName = (String) clinicName; 
		this.appointmentDate = (String) appointmentDate; 
		this.appointmentTime = (String) appointmentTime;
		this.about = (String) about; 
		this.profileing = (String) profileing;
		this.userid = (String) userid;
		this.favStatus = (String) favStatus;
		
	}

	
	
	public CureEasyDrAppointBookingAPIModel(Object appointmentId,Object slotDate,Object slotTime,Object healthIssue,
			Object bookingType,Object consultingCharges,Object coupon,Object otherCharges,Object totalCharges,Object orderid,Object bookStatus,Object booking) {
		super();
		this.appointmentId = (String) appointmentId; 
		this.slotDate = (String) slotDate; 
		this.slotTime = (String) slotTime; 
		this.healthIssue = (String) healthIssue; 
		this.bookingType = (String) appointmentId; 
		this.consultingCharges = (String) consultingCharges;
		this.coupon = (String) coupon; 
		this.otherCharges = (String) otherCharges;
		this.totalCharges = (String) totalCharges;
		this.orderid = (String) orderid;
		this.bookStatus = (String) bookStatus;
		this.booking = (String) booking;
		
	}
	
	public CureEasyDrAppointBookingAPIModel(Object doctorId,Object doctorName,Object doctorSpecialization,Object doctorEducation,
			Object doctorLanguage,Object clinicName,Object appointmentDate,Object appointmentTime,Object profileing,Object consultingCharges,
			Object bookingType,Object healthIssue,Object doctorAddress,Object approvestatus,Object showcode,Object appointmentId,Object booking) {
		   
		super();
		this.doctorId = (String) doctorId; 
		this.doctorName = (String) doctorName; 
		this.doctorSpecialization = (String) doctorSpecialization; 
		this.doctorEducation = (String) doctorEducation; 
		this.doctorLanguage = (String) doctorLanguage; 
		this.clinicName = (String) clinicName;
		this.appointmentDate = (String) appointmentDate; 
		this.appointmentTime = (String) appointmentTime;
		this.profileing = (String) profileing;
		this.consultingCharges = (String) consultingCharges;
		this.bookingType = (String) bookingType;
		this.healthIssue = (String) healthIssue;
		this.doctorAddress = (String) doctorAddress;
		this.approvestatus = (String) approvestatus;
		this.showcode = (String) showcode;
		this.appointmentId = (String) appointmentId;
		this.booking = (String) booking;
	}
	public String getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(String appointmentId) {
		this.appointmentId = appointmentId;
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
	public String getBookingType() {
		return bookingType;
	}
	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}
 
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getHealthIssue() {
		return healthIssue;
	}
	public void setHealthIssue(String healthIssue) {
		this.healthIssue = healthIssue;
	}
	public String getSlotId() {
		return slotId;
	}
	public void setSlotId(String slotId) {
		this.slotId = slotId;
	}
	public String getSlotDate() {
		return slotDate;
	}
	public void setSlotDate(String slotDate) {
		this.slotDate = slotDate;
	}
	public String getSlotTime() {
		return slotTime;
	}
	public void setSlotTime(String slotTime) {
		this.slotTime = slotTime;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
 
	public String getConsultingCharges() {
		return consultingCharges;
	}
	public void setConsultingCharges(String consultingCharges) {
		this.consultingCharges = consultingCharges;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getCoupon() {
		return coupon;
	}
	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}
	public String getOtherCharges() {
		return otherCharges;
	}
	public void setOtherCharges(String otherCharges) {
		this.otherCharges = otherCharges;
	}
	public String getTotalCharges() {
		return totalCharges;
	}
	public void setTotalCharges(String totalCharges) {
		this.totalCharges = totalCharges;
	}
	public String getCouponAmount() {
		return couponAmount;
	}
	public void setCouponAmount(String couponAmount) {
		this.couponAmount = couponAmount;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	
	public String getDoctorSpecialization() {
		return doctorSpecialization;
	}

	public void setDoctorSpecialization(String doctorSpecialization) {
		this.doctorSpecialization = doctorSpecialization;
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

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getProfileing() {
		return profileing;
	}

	public void setProfileing(String profileing) {
		this.profileing = profileing;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFavStatus() {
		return favStatus;
	}

	public void setFavStatus(String favStatus) {
		this.favStatus = favStatus;
	}
	

	public String getApprovestatus() {
		return approvestatus;
	}


	public void setApprovestatus(String approvestatus) {
		this.approvestatus = approvestatus;
	}


	public String getDoctorAddress() {
		return doctorAddress;
	}


	public void setDoctorAddress(String doctorAddress) {
		this.doctorAddress = doctorAddress;
	}
	


	public String getOrderid() {
		return orderid;
	}


	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}


	public String getBookStatus() {
		return bookStatus;
	}


	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}


	public String getBooking() {
		return booking;
	}


	public void setBooking(String booking) {
		this.booking = booking;
	}
	


	public String getShowcode() {
		return showcode;
	}


	public void setShowcode(String showcode) {
		this.showcode = showcode;
	}

	

	public String getConsultType() {
		return consultType;
	}


	public void setConsultType(String consultType) {
		this.consultType = consultType;
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
