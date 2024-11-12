package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class APIAiplNadiModel {

	private String uh_Id;
	private String prakruti_Type;
	private String prakruti_Detail;
	private String prakruti_Vata;
	private String prakruti_Pitta;
	private String prakruti_Kapha;
	private String dosha;
	private String pulse_Rate;
	private String rhythm;
	private String digestion_Score;
	private String stress_Score;
	private String toxins_Score;
	private String body_Immunity;
	private String hydration_Score;
	private String bala;
	private String agni;
	private String bala_Description;
	private String agni_Description;
	private String laghu;
	private String guru;
	private String laghu_Guru_Description;
	private String kathina;
	private String mrudu;
	private String kathina_Mrudu_Description;
	private String sthula;
	private String sukshma;
	private String sthula_Sukshma_Description;
	private String tikshna;
	private String manda;
	private String tikshna_Manda_Description;
	private String snigdha;
	private String ruksha;
	private String snigdha_Ruksha_Description;
	private String wellnessParameter_Description;
	private String thoughts;
	private String thought_Description;
	private String stress;
	private String stress_Description;
	private String summary;

	private String createdDt;
	private String fileName;

	private String testDate;
	private String name;
	private String age;
	private String weight;
	private String adharCard;
	private String height;
	private String gender;
	private String testTime;

	public APIAiplNadiModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public APIAiplNadiModel(Object uh_Id, Object prakruti_Type, Object prakruti_Detail, Object prakruti_Vata,
			Object prakruti_Pitta, Object prakruti_Kapha, Object dosha, Object pulse_Rate, Object rhythm,
			Object digestion_Score, Object stress_Score, Object toxins_Score, Object body_Immunity,
			Object hydration_Score, Object testDate, Object name, Object age, Object weight, Object adharCard,
			Object height, Object gender, Object testTime) {
		super();
		this.uh_Id = (String) uh_Id;
		this.prakruti_Type = (String) prakruti_Type;
		this.prakruti_Detail = (String) prakruti_Detail;
		this.prakruti_Vata = (String) prakruti_Vata;
		this.prakruti_Pitta = (String) prakruti_Pitta;
		this.prakruti_Kapha = (String) prakruti_Kapha;
		this.dosha = (String) dosha;
		this.pulse_Rate = (String) pulse_Rate;
		this.rhythm = (String) rhythm;
		this.digestion_Score = (String) digestion_Score;
		this.stress_Score = (String) stress_Score;
		this.toxins_Score = (String) toxins_Score;
		this.body_Immunity = (String) body_Immunity;
		this.hydration_Score = (String) hydration_Score;

		this.testDate = (String) testDate;
		this.name = (String) name;
		this.age = (String) age;
		this.weight = (String) weight;
		this.adharCard = (String) adharCard;
		this.height = (String) height;
		this.gender = (String) gender;
		this.testTime = (String) testTime;
	}

	public String getUh_Id() {
		return uh_Id;
	}

	public void setUh_Id(String uh_Id) {
		this.uh_Id = uh_Id;
	}

	public String getPrakruti_Type() {
		return prakruti_Type;
	}

	public void setPrakruti_Type(String prakruti_Type) {
		this.prakruti_Type = prakruti_Type;
	}

	public String getPrakruti_Detail() {
		return prakruti_Detail;
	}

	public void setPrakruti_Detail(String prakruti_Detail) {
		this.prakruti_Detail = prakruti_Detail;
	}

	public String getPrakruti_Vata() {
		return prakruti_Vata;
	}

	public void setPrakruti_Vata(String prakruti_Vata) {
		this.prakruti_Vata = prakruti_Vata;
	}

	public String getPrakruti_Pitta() {
		return prakruti_Pitta;
	}

	public void setPrakruti_Pitta(String prakruti_Pitta) {
		this.prakruti_Pitta = prakruti_Pitta;
	}

	public String getPrakruti_Kapha() {
		return prakruti_Kapha;
	}

	public void setPrakruti_Kapha(String prakruti_Kapha) {
		this.prakruti_Kapha = prakruti_Kapha;
	}

	public String getDosha() {
		return dosha;
	}

	public void setDosha(String dosha) {
		this.dosha = dosha;
	}

	public String getPulse_Rate() {
		return pulse_Rate;
	}

	public void setPulse_Rate(String pulse_Rate) {
		this.pulse_Rate = pulse_Rate;
	}

	public String getRhythm() {
		return rhythm;
	}

	public void setRhythm(String rhythm) {
		this.rhythm = rhythm;
	}

	public String getDigestion_Score() {
		return digestion_Score;
	}

	public void setDigestion_Score(String digestion_Score) {
		this.digestion_Score = digestion_Score;
	}

	public String getStress_Score() {
		return stress_Score;
	}

	public void setStress_Score(String stress_Score) {
		this.stress_Score = stress_Score;
	}

	public String getToxins_Score() {
		return toxins_Score;
	}

	public void setToxins_Score(String toxins_Score) {
		this.toxins_Score = toxins_Score;
	}

	public String getBody_Immunity() {
		return body_Immunity;
	}

	public void setBody_Immunity(String body_Immunity) {
		this.body_Immunity = body_Immunity;
	}

	public String getHydration_Score() {
		return hydration_Score;
	}

	public void setHydration_Score(String hydration_Score) {
		this.hydration_Score = hydration_Score;
	}

	public String getBala() {
		return bala;
	}

	public void setBala(String bala) {
		this.bala = bala;
	}

	public String getAgni() {
		return agni;
	}

	public void setAgni(String agni) {
		this.agni = agni;
	}

	public String getBala_Description() {
		return bala_Description;
	}

	public void setBala_Description(String bala_Description) {
		this.bala_Description = bala_Description;
	}

	public String getAgni_Description() {
		return agni_Description;
	}

	public void setAgni_Description(String agni_Description) {
		this.agni_Description = agni_Description;
	}

	public String getLaghu() {
		return laghu;
	}

	public void setLaghu(String laghu) {
		this.laghu = laghu;
	}

	public String getGuru() {
		return guru;
	}

	public void setGuru(String guru) {
		this.guru = guru;
	}

	public String getLaghu_Guru_Description() {
		return laghu_Guru_Description;
	}

	public void setLaghu_Guru_Description(String laghu_Guru_Description) {
		this.laghu_Guru_Description = laghu_Guru_Description;
	}

	public String getKathina() {
		return kathina;
	}

	public void setKathina(String kathina) {
		this.kathina = kathina;
	}

	public String getMrudu() {
		return mrudu;
	}

	public void setMrudu(String mrudu) {
		this.mrudu = mrudu;
	}

	public String getKathina_Mrudu_Description() {
		return kathina_Mrudu_Description;
	}

	public void setKathina_Mrudu_Description(String kathina_Mrudu_Description) {
		this.kathina_Mrudu_Description = kathina_Mrudu_Description;
	}

	public String getSthula() {
		return sthula;
	}

	public void setSthula(String sthula) {
		this.sthula = sthula;
	}

	public String getSukshma() {
		return sukshma;
	}

	public void setSukshma(String sukshma) {
		this.sukshma = sukshma;
	}

	public String getSthula_Sukshma_Description() {
		return sthula_Sukshma_Description;
	}

	public void setSthula_Sukshma_Description(String sthula_Sukshma_Description) {
		this.sthula_Sukshma_Description = sthula_Sukshma_Description;
	}

	public String getTikshna() {
		return tikshna;
	}

	public void setTikshna(String tikshna) {
		this.tikshna = tikshna;
	}

	public String getManda() {
		return manda;
	}

	public void setManda(String manda) {
		this.manda = manda;
	}

	public String getTikshna_Manda_Description() {
		return tikshna_Manda_Description;
	}

	public void setTikshna_Manda_Description(String tikshna_Manda_Description) {
		this.tikshna_Manda_Description = tikshna_Manda_Description;
	}

	public String getSnigdha() {
		return snigdha;
	}

	public void setSnigdha(String snigdha) {
		this.snigdha = snigdha;
	}

	public String getRuksha() {
		return ruksha;
	}

	public void setRuksha(String ruksha) {
		this.ruksha = ruksha;
	}

	public String getSnigdha_Ruksha_Description() {
		return snigdha_Ruksha_Description;
	}

	public void setSnigdha_Ruksha_Description(String snigdha_Ruksha_Description) {
		this.snigdha_Ruksha_Description = snigdha_Ruksha_Description;
	}

	public String getWellnessParameter_Description() {
		return wellnessParameter_Description;
	}

	public void setWellnessParameter_Description(String wellnessParameter_Description) {
		this.wellnessParameter_Description = wellnessParameter_Description;
	}

	public String getThoughts() {
		return thoughts;
	}

	public void setThoughts(String thoughts) {
		this.thoughts = thoughts;
	}

	public String getThought_Description() {
		return thought_Description;
	}

	public void setThought_Description(String thought_Description) {
		this.thought_Description = thought_Description;
	}

	public String getStress() {
		return stress;
	}

	public void setStress(String stress) {
		this.stress = stress;
	}

	public String getStress_Description() {
		return stress_Description;
	}

	public void setStress_Description(String stress_Description) {
		this.stress_Description = stress_Description;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(String createdDt) {
		this.createdDt = createdDt;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTestDate() {
		return testDate;
	}

	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getAdharCard() {
		return adharCard;
	}

	public void setAdharCard(String adharCard) {
		this.adharCard = adharCard;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTestTime() {
		return testTime;
	}

	public void setTestTime(String testTime) {
		this.testTime = testTime;
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
