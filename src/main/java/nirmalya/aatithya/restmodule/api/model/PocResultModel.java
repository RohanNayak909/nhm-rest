package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PocResultModel {

	private String spo2;
	private String spo2_pulse; 
	private String spo2_pi;
	private String bp;
	private String pulse;
	private String glucose;
	private String glucoseCategory;
	private String spi_fvc;
	private String spi_fev1;
	private String spi_fev6;
	private String spi_fev1_fvc;
	private String spi_fef_25_75;
	private String spi_pef;
	private String thermometer;
	private String thermometer_unit;
	private String hemoglobin;
	private String url;
	private String triglycerides;
	private String total_cholesterol;
	private String hdl_cholesterol;
	private String ldl;
	private String vldl;
	private String tc_hdl_ratio;

	public PocResultModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PocResultModel(String spo2, String spo2_pulse, String spo2_pi, String bp, String pulse, String glucose,
			String glucoseCategory, String spi_fvc, String spi_fev1, String spi_fev6, String spi_fev1_fvc,
			String spi_fef_25_75, String spi_pef, String thermometer, String thermometer_unit, String hemoglobin,
			String url, String triglycerides, String total_cholesterol, String hdl_cholesterol, String ldl, String vldl,
			String tc_hdl_ratio) {
		super();
		this.spo2 = spo2;
		this.spo2_pulse = spo2_pulse;
		this.spo2_pi = spo2_pi;
		this.bp = bp;
		this.pulse = pulse;
		this.glucose = glucose;
		this.glucoseCategory = glucoseCategory;
		this.spi_fvc = spi_fvc;
		this.spi_fev1 = spi_fev1;
		this.spi_fev6 = spi_fev6;
		this.spi_fev1_fvc = spi_fev1_fvc;
		this.spi_fef_25_75 = spi_fef_25_75;
		this.spi_pef = spi_pef;
		this.thermometer = thermometer;
		this.thermometer_unit = thermometer_unit;
		this.hemoglobin = hemoglobin;
		this.url = url;
		this.triglycerides = triglycerides;
		this.total_cholesterol = total_cholesterol;
		this.hdl_cholesterol = hdl_cholesterol;
		this.ldl = ldl;
		this.vldl = vldl;
		this.tc_hdl_ratio = tc_hdl_ratio;
	}

	public String getSpo2() {
		return spo2;
	}

	public void setSpo2(String spo2) {
		this.spo2 = spo2;
	}

	public String getSpo2_pulse() {
		return spo2_pulse;
	}

	public void setSpo2_pulse(String spo2_pulse) {
		this.spo2_pulse = spo2_pulse;
	}

	public String getSpo2_pi() {
		return spo2_pi;
	}

	public void setSpo2_pi(String spo2_pi) {
		this.spo2_pi = spo2_pi;
	}

	public String getBp() {
		return bp;
	}

	public void setBp(String bp) {
		this.bp = bp;
	}

	public String getPulse() {
		return pulse;
	}

	public void setPulse(String pulse) {
		this.pulse = pulse;
	}

	public String getGlucose() {
		return glucose;
	}

	public void setGlucose(String glucose) {
		this.glucose = glucose;
	}

	public String getGlucoseCategory() {
		return glucoseCategory;
	}

	public void setGlucoseCategory(String glucoseCategory) {
		this.glucoseCategory = glucoseCategory;
	}

	public String getSpi_fvc() {
		return spi_fvc;
	}

	public void setSpi_fvc(String spi_fvc) {
		this.spi_fvc = spi_fvc;
	}

	public String getSpi_fev1() {
		return spi_fev1;
	}

	public void setSpi_fev1(String spi_fev1) {
		this.spi_fev1 = spi_fev1;
	}

	public String getSpi_fev6() {
		return spi_fev6;
	}

	public void setSpi_fev6(String spi_fev6) {
		this.spi_fev6 = spi_fev6;
	}

	public String getSpi_fev1_fvc() {
		return spi_fev1_fvc;
	}

	public void setSpi_fev1_fvc(String spi_fev1_fvc) {
		this.spi_fev1_fvc = spi_fev1_fvc;
	}

	public String getSpi_fef_25_75() {
		return spi_fef_25_75;
	}

	public void setSpi_fef_25_75(String spi_fef_25_75) {
		this.spi_fef_25_75 = spi_fef_25_75;
	}

	public String getSpi_pef() {
		return spi_pef;
	}

	public void setSpi_pef(String spi_pef) {
		this.spi_pef = spi_pef;
	}

	public String getThermometer() {
		return thermometer;
	}

	public void setThermometer(String thermometer) {
		this.thermometer = thermometer;
	}

	public String getThermometer_unit() {
		return thermometer_unit;
	}

	public void setThermometer_unit(String thermometer_unit) {
		this.thermometer_unit = thermometer_unit;
	}

	public String getHemoglobin() {
		return hemoglobin;
	}

	public void setHemoglobin(String hemoglobin) {
		this.hemoglobin = hemoglobin;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTriglycerides() {
		return triglycerides;
	}

	public void setTriglycerides(String triglycerides) {
		this.triglycerides = triglycerides;
	}

	public String getTotal_cholesterol() {
		return total_cholesterol;
	}

	public void setTotal_cholesterol(String total_cholesterol) {
		this.total_cholesterol = total_cholesterol;
	}

	public String getHdl_cholesterol() {
		return hdl_cholesterol;
	}

	public void setHdl_cholesterol(String hdl_cholesterol) {
		this.hdl_cholesterol = hdl_cholesterol;
	}

	public String getLdl() {
		return ldl;
	}

	public void setLdl(String ldl) {
		this.ldl = ldl;
	}

	public String getVldl() {
		return vldl;
	}

	public void setVldl(String vldl) {
		this.vldl = vldl;
	}

	public String getTc_hdl_ratio() {
		return tc_hdl_ratio;
	}

	public void setTc_hdl_ratio(String tc_hdl_ratio) {
		this.tc_hdl_ratio = tc_hdl_ratio;
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
