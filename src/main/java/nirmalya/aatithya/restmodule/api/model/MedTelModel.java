package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MedTelModel {

	private String medteluniqueid;
	private String thp_id;
	private String thp_name;
	private String name;
	private String mobile;
	private String gender;
	private String age;
	private String screening_date;
	private String patient_uniqueid;
	private String report_url;
	private String uniqueid;
	private String phc;
	private String smsstatus;
	private List<Object> screening_details = new ArrayList<Object>();

	public MedTelModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MedTelModel(Object medteluniqueid, Object thp_id, Object thp_name, Object name, Object mobile, Object gender,
			Object age, Object screening_date, Object patient_uniqueid, Object report_url,Object smsstatus) {
		super();
		this.medteluniqueid = (String) medteluniqueid;
		this.thp_id = (String) thp_id;
		this.thp_name = (String) thp_name;
		this.name = (String) name;
		this.mobile = (String) mobile;
		this.gender = (String) gender;
		this.age = (String) age;
		this.screening_date = (String) screening_date;
		this.patient_uniqueid = (String) patient_uniqueid;
		this.report_url = (String) report_url;
		this.smsstatus = (String) smsstatus;
	}
	
	public MedTelModel(Object patient_uniqueid, Object name, Object screening_date, Object mobile, Object report_url,
			Object phc) {
		super();
		this.patient_uniqueid = (String) patient_uniqueid;
		this.name = (String) name;
		this.screening_date = (String) screening_date;
		this.mobile = (String) mobile;
		this.report_url = (String) report_url;
		this.phc = (String) phc;
	}

	public String getMedteluniqueid() {
		return medteluniqueid;
	}

	public void setMedteluniqueid(String medteluniqueid) {
		this.medteluniqueid = medteluniqueid;
	}

	public String getThp_id() {
		return thp_id;
	}

	public void setThp_id(String thp_id) {
		this.thp_id = thp_id;
	}

	public String getThp_name() {
		return thp_name;
	}

	public void setThp_name(String thp_name) {
		this.thp_name = thp_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getScreening_date() {
		return screening_date;
	}

	public void setScreening_date(String screening_date) {
		this.screening_date = screening_date;
	}

	public String getPatient_uniqueid() {
		return patient_uniqueid;
	}

	public void setPatient_uniqueid(String patient_uniqueid) {
		this.patient_uniqueid = patient_uniqueid;
	}

	public String getReport_url() {
		return report_url;
	}

	public void setReport_url(String report_url) {
		this.report_url = report_url;
	}

	public List<Object> getScreening_details() {
		return screening_details;
	}

	public void setScreening_details(List<Object> screening_details) {
		this.screening_details = screening_details;
	}

	public String getUniqueid() {
		return uniqueid;
	}

	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}

	public String getPhc() {
		return phc;
	}

	public void setPhc(String phc) {
		this.phc = phc;
	}

	public String getSmsstatus() {
		return smsstatus;
	}

	public void setSmsstatus(String smsstatus) {
		this.smsstatus = smsstatus;
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
