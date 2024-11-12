package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AmbulanceAPIModel {
	private String patientId;
	private String patientName;
	private String fromLocation;
	private String toDestination;
	private String status;
	private String bookedDate;
	private String patientNote;
	private String ambulanceId;
	private String ambulanceName;
	private String orderId;
	private String fromLongitude;
	private String fromLatitude;
	private String toLongitude;
	private String toLatitude;
	private String bookingTime;

	public AmbulanceAPIModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AmbulanceAPIModel(Object orderId, Object patientId, Object patientName, Object fromLocation,
			Object toDestination, Object patientNote, Object status,Object bookedDate) {
		super();
		this.orderId = (String) orderId;
		this.patientId = (String) patientId;
		this.patientName = (String) patientName;
		this.fromLocation = (String) fromLocation;
		this.toDestination = (String) toDestination;
		this.patientNote = (String) patientNote;
		this.status = (String) status;
		this.bookedDate = (String) bookedDate;
	}

	public AmbulanceAPIModel(Object patientId, Object patientName, Object fromLocation, Object toDestination,
			Object status, Object bookedDate, Object ambulanceId, Object ambulanceName, Object patientNote,
			Object orderId) {
		super();
		this.patientId = (String) patientId;
		this.patientName = (String) patientName;
		this.fromLocation = (String) fromLocation;
		this.toDestination = (String) toDestination;
		this.status = (String) status;
		this.bookedDate = (String) bookedDate;
		this.ambulanceId = (String) ambulanceId;
		this.ambulanceName = (String) ambulanceName;
		this.patientNote = (String) patientNote;
		this.orderId = (String) orderId;
	}

	public String getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(String bookingTime) {
		this.bookingTime = bookingTime;
	}

	public String getFromLongitude() {
		return fromLongitude;
	}

	public void setFromLongitude(String fromLongitude) {
		this.fromLongitude = fromLongitude;
	}

	public String getFromLatitude() {
		return fromLatitude;
	}

	public void setFromLatitude(String fromLatitude) {
		this.fromLatitude = fromLatitude;
	}

	public String getToLongitude() {
		return toLongitude;
	}

	public void setToLongitude(String toLongitude) {
		this.toLongitude = toLongitude;
	}

	public String getToLatitude() {
		return toLatitude;
	}

	public void setToLatitude(String toLatitude) {
		this.toLatitude = toLatitude;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getAmbulanceName() {
		return ambulanceName;
	}

	public void setAmbulanceName(String ambulanceName) {
		this.ambulanceName = ambulanceName;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public String getToDestination() {
		return toDestination;
	}

	public void setToDestination(String toDestination) {
		this.toDestination = toDestination;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBookedDate() {
		return bookedDate;
	}

	public void setBookedDate(String bookedDate) {
		this.bookedDate = bookedDate;
	}

	public String getPatientNote() {
		return patientNote;
	}

	public void setPatientNote(String patientNote) {
		this.patientNote = patientNote;
	}

	public String getAmbulanceId() {
		return ambulanceId;
	}

	public void setAmbulanceId(String ambulanceId) {
		this.ambulanceId = ambulanceId;
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
