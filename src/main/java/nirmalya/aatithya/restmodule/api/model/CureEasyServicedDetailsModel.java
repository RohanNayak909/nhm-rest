package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CureEasyServicedDetailsModel {
	private String serviceId; 
	private String name; 
	private String image; 
	private String description; 
	
	public CureEasyServicedDetailsModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CureEasyServicedDetailsModel(Object serviceId, Object name,  Object image,
			Object description) {
		super();
		this.serviceId = (String) serviceId;
		this.name = (String) name;
		this.image = (String) image;
		this.description = (String) description;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
