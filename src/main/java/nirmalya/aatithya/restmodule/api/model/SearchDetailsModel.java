package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SearchDetailsModel {

	private String id;
	private String name;
	private String address;
	private String pin;
	private String image;
	private String type;

	private String longi;
	private String lati;
	private String addr;
	private String city;
	private String healthpro;

	public SearchDetailsModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SearchDetailsModel(Object id, Object name, Object address, Object pin, Object image, Object type) {
		super();
		this.id = (String) id;
		this.name = (String) name;
		this.address = (String) address;
		this.pin = (String) pin;
		this.image = (String) image;
		this.type = (String) type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLongi() {
		return longi;
	}

	public void setLongi(String longi) {
		this.longi = longi;
	}

	public String getLati() {
		return lati;
	}

	public void setLati(String lati) {
		this.lati = lati;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getHealthpro() {
		return healthpro;
	}

	public void setHealthpro(String healthpro) {
		this.healthpro = healthpro;
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
