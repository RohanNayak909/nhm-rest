package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class APIBioMedicalModel {

	private String bioMName;
	private String bioMReason;
	private String bioMDate;
	
	private String userid;
	private String id;
	private String bioid;

	public APIBioMedicalModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public APIBioMedicalModel(Object bioMName, Object bioMReason, Object bioMDate, Object id, Object bioid) {
		super();
		this.bioMName = (String) bioMName;
		this.bioMReason = (String) bioMReason;
		this.bioMDate = (String) bioMDate;
		this.id = (String) id;
		this.bioid = (String) bioid;
	}

	public String getBioMName() {
		return bioMName;
	}

	public void setBioMName(String bioMName) {
		this.bioMName = bioMName;
	}

	public String getBioMReason() {
		return bioMReason;
	}

	public void setBioMReason(String bioMReason) {
		this.bioMReason = bioMReason;
	}

	public String getBioMDate() {
		return bioMDate;
	}

	public void setBioMDate(String bioMDate) {
		this.bioMDate = bioMDate;
	}
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBioid() {
		return bioid;
	}

	public void setBioid(String bioid) {
		this.bioid = bioid;
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
