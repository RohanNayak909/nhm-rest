package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GEOAPIMedicationModel {

	private String userid;
	private String date;
	private Integer doctor;
	private String doctorname;
	private List<GEOAPIMedDetailsModel> childDetail = new ArrayList<GEOAPIMedDetailsModel>();

	public GEOAPIMedicationModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getDoctor() {
		return doctor;
	}

	public void setDoctor(Integer doctor) {
		this.doctor = doctor;
	}

	public String getDoctorname() {
		return doctorname;
	}

	public void setDoctorname(String doctorname) {
		this.doctorname = doctorname;
	}

	public List<GEOAPIMedDetailsModel> getChildDetail() {
		return childDetail;
	}

	public void setChildDetail(List<GEOAPIMedDetailsModel> childDetail) {
		this.childDetail = childDetail;
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
