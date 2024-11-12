package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class APIVitalSignModel {

	private String userid;
	private Double height;
	private Double weight;
	private Double bmi;
	private Double tempcel;
	private Double tempfar;
	private Integer sysbp;
	private Integer diabp;
	private Integer pulse;
	private Integer respiartion;
	private Integer oxygen;

	public APIVitalSignModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public APIVitalSignModel(Object height, Object weight, Object bmi, Object tempcel, Object tempfar, Object sysbp,
			Object diabp, Object pulse, Object respiartion, Object oxygen) {
		super();
		this.height = (Double) height;
		this.weight = (Double) weight;
		this.bmi = (Double) bmi;
		this.tempcel = (Double) tempcel;
		this.tempfar = (Double) tempfar;
		this.sysbp = (Integer) sysbp;
		this.diabp = (Integer) diabp;
		this.pulse = (Integer) pulse;
		this.respiartion = (Integer) respiartion;
		this.oxygen = (Integer) oxygen;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getBmi() {
		return bmi;
	}

	public void setBmi(Double bmi) {
		this.bmi = bmi;
	}

	public Double getTempcel() {
		return tempcel;
	}

	public void setTempcel(Double tempcel) {
		this.tempcel = tempcel;
	}

	public Double getTempfar() {
		return tempfar;
	}

	public void setTempfar(Double tempfar) {
		this.tempfar = tempfar;
	}

	public Integer getSysbp() {
		return sysbp;
	}

	public void setSysbp(Integer sysbp) {
		this.sysbp = sysbp;
	}

	public Integer getDiabp() {
		return diabp;
	}

	public void setDiabp(Integer diabp) {
		this.diabp = diabp;
	}

	public Integer getPulse() {
		return pulse;
	}

	public void setPulse(Integer pulse) {
		this.pulse = pulse;
	}

	public Integer getRespiartion() {
		return respiartion;
	}

	public void setRespiartion(Integer respiartion) {
		this.respiartion = respiartion;
	}

	public Integer getOxygen() {
		return oxygen;
	}

	public void setOxygen(Integer oxygen) {
		this.oxygen = oxygen;
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
