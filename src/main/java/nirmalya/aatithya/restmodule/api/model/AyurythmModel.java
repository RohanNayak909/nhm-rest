package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AyurythmModel {

	AyurythmDataModel data = new AyurythmDataModel();
	SparshnaDataModel sparshna = new SparshnaDataModel();
	PrakritiVikritiModel vikriti = new PrakritiVikritiModel();
	PrakritiVikritiModel prakriti = new PrakritiVikritiModel();

	public AyurythmModel() {
		super();
	}

	public AyurythmDataModel getData() {
		return data;
	}

	public void setData(AyurythmDataModel data) {
		this.data = data;
	}

	public SparshnaDataModel getSparshna() {
		return sparshna;
	}

	public void setSparshna(SparshnaDataModel sparshna) {
		this.sparshna = sparshna;
	}

	public PrakritiVikritiModel getVikriti() {
		return vikriti;
	}

	public void setVikriti(PrakritiVikritiModel vikriti) {
		this.vikriti = vikriti;
	}

	public PrakritiVikritiModel getPrakriti() {
		return prakriti;
	}

	public void setPrakriti(PrakritiVikritiModel prakriti) {
		this.prakriti = prakriti;
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
