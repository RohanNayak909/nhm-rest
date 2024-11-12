package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GEOAPIMedDetailsModel {

	private Integer medicineid;
	private String qty;
	private String morning;
	private String afternoon;
	private String evening;
	private String night;
	private String days;
	private String medicineadvice;
	private String remarks;
	
	private String testgroup;
	private String testname;

	public GEOAPIMedDetailsModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getMedicineid() {
		return medicineid;
	}

	public void setMedicineid(Integer medicineid) {
		this.medicineid = medicineid;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getMorning() {
		return morning;
	}

	public void setMorning(String morning) {
		this.morning = morning;
	}

	public String getAfternoon() {
		return afternoon;
	}

	public void setAfternoon(String afternoon) {
		this.afternoon = afternoon;
	}

	public String getEvening() {
		return evening;
	}

	public void setEvening(String evening) {
		this.evening = evening;
	}

	public String getNight() {
		return night;
	}

	public void setNight(String night) {
		this.night = night;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getMedicineadvice() {
		return medicineadvice;
	}

	public void setMedicineadvice(String medicineadvice) {
		this.medicineadvice = medicineadvice;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTestgroup() {
		return testgroup;
	}

	public void setTestgroup(String testgroup) {
		this.testgroup = testgroup;
	}

	public String getTestname() {
		return testname;
	}

	public void setTestname(String testname) {
		this.testname = testname;
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
