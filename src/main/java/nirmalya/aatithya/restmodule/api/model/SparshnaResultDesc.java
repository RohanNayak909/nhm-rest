package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SparshnaResultDesc {
	
	private String title;
	private String short_description;
	private String what_it_means;

	public SparshnaResultDesc() {
		super();
	}
	
	

	public SparshnaResultDesc(Object title, Object short_description, Object what_it_means) {
		super();
		this.title = (String) title;
		this.short_description = (String) short_description;;
		this.what_it_means = (String) what_it_means;;
	}



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShort_description() {
		return short_description;
	}

	public void setShort_description(String short_description) {
		this.short_description = short_description;
	}

	public String getWhat_it_means() {
		return what_it_means;
	}

	public void setWhat_it_means(String what_it_means) {
		this.what_it_means = what_it_means;
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
