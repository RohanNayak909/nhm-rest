package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class APIPharmacyModel {
	private String userid;
	private String pharmacistid;
	private String patientnote;
	private String orderid;
	private String appno;
	private List<String> mednamelist = new ArrayList<String>();
	private List<CountryModel> meddetails = new ArrayList<CountryModel>();

	public APIPharmacyModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public APIPharmacyModel(Object userid, Object pharmacistid, Object patientnote) {
		super();
		this.userid = (String) userid;
		this.pharmacistid = (String) pharmacistid;
		this.patientnote = (String) patientnote;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPharmacistid() {
		return pharmacistid;
	}

	public void setPharmacistid(String pharmacistid) {
		this.pharmacistid = pharmacistid;
	}

	public String getPatientnote() {
		return patientnote;
	}

	public void setPatientnote(String patientnote) {
		this.patientnote = patientnote;
	}

	public List<CountryModel> getMeddetails() {
		return meddetails;
	}

	public void setMeddetails(List<CountryModel> meddetails) {
		this.meddetails = meddetails;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public List<String> getMednamelist() {
		return mednamelist;
	}

	public void setMednamelist(List<String> mednamelist) {
		this.mednamelist = mednamelist;
	}

	public String getAppno() {
		return appno;
	}

	public void setAppno(String appno) {
		this.appno = appno;
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
