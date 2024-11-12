package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PatientImmunizationDetailsModel {

	private String patientId;
	private String patientName;
	private String immunizationId;
	private String immunizationDetails;
	private String status;
	private String immunizationDate;
	private String doctorName;
	private String slno;

	public PatientImmunizationDetailsModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PatientImmunizationDetailsModel(Object patientId, Object patientName, Object immunizationId,
			Object immunizationDetails, Object status, Object immunizationDate, Object doctorName, Object slno) {
		super();
		this.patientId = (String) patientId;
		this.patientName = (String) patientName;
		this.immunizationId = (String) immunizationId;
		this.immunizationDetails = (String) immunizationDetails;
		this.status = (String) status;
		this.immunizationDate = (String) immunizationDate;
		this.doctorName = (String) doctorName;
		this.slno = (String) slno;
	}

	/*
	 * public PatientImmunizationDetailsModel(Object patientId, Object slno) {
	 * super(); this.patientId = (String) patientId; this.slno = (String) slno; }
	 */
	public String getSlno() {
		return slno;
	}

	public void setSlno(String slno) {
		this.slno = slno;
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

	public String getImmunizationId() {
		return immunizationId;
	}

	public void setImmunizationId(String immunizationId) {
		this.immunizationId = immunizationId;
	}

	public String getImmunizationDetails() {
		return immunizationDetails;
	}

	public void setImmunizationDetails(String immunizationDetails) {
		this.immunizationDetails = immunizationDetails;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImmunizationDate() {
		return immunizationDate;
	}

	public void setImmunizationDate(String immunizationDate) {
		this.immunizationDate = immunizationDate;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
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
