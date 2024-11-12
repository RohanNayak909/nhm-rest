package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PrakritiVikritiModel {
	
	private String kapha;
	private String pitta;
	private String vata;

	public PrakritiVikritiModel() {
		super();
	}
	
	public PrakritiVikritiModel(Object kapha, Object pitta,Object vata) {
		super();
		this.kapha = (String) kapha;
		this.pitta = (String) pitta;
		this.vata = (String) vata;
	}


	public String getKapha() {
		return kapha;
	}

	public void setKapha(String kapha) {
		this.kapha = kapha;
	}

	public String getPitta() {
		return pitta;
	}

	public void setPitta(String pitta) {
		this.pitta = pitta;
	}

	public String getVata() {
		return vata;
	}

	public void setVata(String vata) {
		this.vata = vata;
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
