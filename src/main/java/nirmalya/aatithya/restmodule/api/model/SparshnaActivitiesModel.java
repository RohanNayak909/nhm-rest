package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SparshnaActivitiesModel {
	private String type;
	private String name;
	private String shortdescription;
	private String steps;
	private String benefit_description;
	private String precautions;

	public SparshnaActivitiesModel() {
		super();
	}

	public SparshnaActivitiesModel(Object type, Object name, Object shortdescription, Object steps,
			Object benefit_description, Object precautions) {
		super();
		this.type = (String) type;
		this.name = (String) name;
		this.shortdescription = (String) shortdescription;
		this.steps = (String) steps;
		this.benefit_description = (String) benefit_description;
		this.precautions = (String) precautions;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortdescription() {
		return shortdescription;
	}

	public void setShortdescription(String shortdescription) {
		this.shortdescription = shortdescription;
	}

	public String getSteps() {
		return steps;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}

	public String getBenefit_description() {
		return benefit_description;
	}

	public void setBenefit_description(String benefit_description) {
		this.benefit_description = benefit_description;
	}

	public String getPrecautions() {
		return precautions;
	}

	public void setPrecautions(String precautions) {
		this.precautions = precautions;
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
