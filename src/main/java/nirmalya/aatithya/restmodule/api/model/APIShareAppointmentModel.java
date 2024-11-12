package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class APIShareAppointmentModel {

	private String id;
	private String patientid;
	private String drid;
	private String notes;
	private String refdrid;
	private String appno;
	private String patientname;
	private String refdrname;
	private String sdate;
	private String stime;
	private String hosid;

	public APIShareAppointmentModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public APIShareAppointmentModel(Object id, Object patientid, Object notes, Object refdrid, Object patientname,
			Object refdrname, Object sdate, Object stime, Object hosid) {
		super();
		this.id = (String) id;
		this.patientid = (String) patientid;
		this.notes = (String) notes;
		this.refdrid = (String) refdrid;
		this.patientname = (String) patientname;
		this.refdrname = (String) refdrname;
		this.sdate = (String) sdate;
		this.stime = (String) stime;
		this.hosid = (String) hosid;
	}

	public String getPatientname() {
		return patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}

	public String getRefdrname() {
		return refdrname;
	}

	public void setRefdrname(String refdrname) {
		this.refdrname = refdrname;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPatientid() {
		return patientid;
	}

	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}

	public String getDrid() {
		return drid;
	}

	public void setDrid(String drid) {
		this.drid = drid;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getRefdrid() {
		return refdrid;
	}

	public void setRefdrid(String refdrid) {
		this.refdrid = refdrid;
	}

	public String getAppno() {
		return appno;
	}

	public void setAppno(String appno) {
		this.appno = appno;
	}

	public String getHosid() {
		return hosid;
	}

	public void setHosid(String hosid) {
		this.hosid = hosid;
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
