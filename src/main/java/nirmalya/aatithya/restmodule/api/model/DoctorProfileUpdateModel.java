package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DoctorProfileUpdateModel {
	private String doctorId;
	private String doctorProfileImg;
	private String extension;
	public DoctorProfileUpdateModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DoctorProfileUpdateModel(Object doctorId, Object doctorProfileImg, Object extension) {
		super();
		this.doctorId = (String) doctorId;
		this.doctorProfileImg = (String) doctorProfileImg;
		this.extension = (String) extension;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorProfileImg() {
		return doctorProfileImg;
	}
	public void setDoctorProfileImg(String doctorProfileImg) {
		this.doctorProfileImg = doctorProfileImg;
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
