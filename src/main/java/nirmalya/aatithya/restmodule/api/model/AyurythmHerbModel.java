package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AyurythmHerbModel {

	private String herb_type;
	private String herbs_list;

	public AyurythmHerbModel() {
		super();
	}
	
	public AyurythmHerbModel(Object herb_type, Object herbs_list) {
		super();
		this.herb_type = (String) herb_type;
		this.herbs_list = (String) herbs_list;;
	}


	public String getHerb_type() {
		return herb_type;
	}

	public void setHerb_type(String herb_type) {
		this.herb_type = herb_type;
	}

	public String getHerbs_list() {
		return herbs_list;
	}

	public void setHerbs_list(String herbs_list) {
		this.herbs_list = herbs_list;
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
