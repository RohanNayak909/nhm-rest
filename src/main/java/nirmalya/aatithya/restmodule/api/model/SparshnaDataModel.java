package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SparshnaDataModel {

	private Double bmr;
	private Double dp;
	private Double rythm;
	private Double o2r;
	private Double pitta2;
	private Double vata2;
	private Double kath;
	private Double pbreath;
	private Double bala;
	private Double tbpm;
	private Double sp;
	private Double bpm;
	private Double kapha2;
	private Double bmi;
	private String gati;

	public SparshnaDataModel() {
		super();
	}
	
	

	public SparshnaDataModel(Object bmr, Object dp, Object rythm, Object o2r, Object pitta2, Object vata2, Object kath,
			Object pbreath, Object bala, Object tbpm, Object sp,Object kapha2, Object bmi, Object gati) {
		super();
		this.bmr = (Double) bmr;
		this.dp = (Double) dp;
		this.rythm = (Double) rythm;
		this.o2r = (Double) o2r;
		this.pitta2 = (Double) pitta2;
		this.vata2 = (Double) vata2;
		this.kath = (Double) kath;
		this.pbreath = (Double) pbreath;
		this.bala = (Double) bala;
		this.tbpm = (Double) tbpm;
		this.sp = (Double) sp;
		this.kapha2 = (Double) bmr;
		this.bmi = (Double) bmi;
		this.gati = (String) gati;
		
	}



	public Double getBmr() {
		return bmr;
	}

	public void setBmr(Double bmr) {
		this.bmr = bmr;
	}

	public Double getDp() {
		return dp;
	}

	public void setDp(Double dp) {
		this.dp = dp;
	}

	public Double getRythm() {
		return rythm;
	}

	public void setRythm(Double rythm) {
		this.rythm = rythm;
	}

	public Double getO2r() {
		return o2r;
	}

	public void setO2r(Double o2r) {
		this.o2r = o2r;
	}

	public Double getPitta2() {
		return pitta2;
	}

	public void setPitta2(Double pitta2) {
		this.pitta2 = pitta2;
	}

	public Double getVata2() {
		return vata2;
	}

	public void setVata2(Double vata2) {
		this.vata2 = vata2;
	}

	public Double getKath() {
		return kath;
	}

	public void setKath(Double kath) {
		this.kath = kath;
	}

	public Double getPbreath() {
		return pbreath;
	}

	public void setPbreath(Double pbreath) {
		this.pbreath = pbreath;
	}

	public Double getBala() {
		return bala;
	}

	public void setBala(Double bala) {
		this.bala = bala;
	}

	public Double getTbpm() {
		return tbpm;
	}

	public void setTbpm(Double tbpm) {
		this.tbpm = tbpm;
	}

	public Double getSp() {
		return sp;
	}

	public void setSp(Double sp) {
		this.sp = sp;
	}

	public Double getBpm() {
		return bpm;
	}

	public void setBpm(Double bpm) {
		this.bpm = bpm;
	}

	public Double getKapha2() {
		return kapha2;
	}

	public void setKapha2(Double kapha2) {
		this.kapha2 = kapha2;
	}

	public Double getBmi() {
		return bmi;
	}

	public void setBmi(Double bmi) {
		this.bmi = bmi;
	}

	public String getGati() {
		return gati;
	}

	public void setGati(String gati) {
		this.gati = gati;
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
