package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SparshnaMasterModel {

	private String title;
	private String what_it_means;
	private String what_to_do;

	public SparshnaMasterModel() {
		super();
	}
	
	public SparshnaMasterModel(Object title, Object what_it_means, Object what_to_do) {
		super();
		this.title = (String) title;
		this.what_it_means = (String) what_it_means;;
		this.what_to_do = (String) what_to_do;;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	public String getWhat_to_do() {
		return what_to_do;
	}

	public void setWhat_to_do(String what_to_do) {
		this.what_to_do = what_to_do;
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
