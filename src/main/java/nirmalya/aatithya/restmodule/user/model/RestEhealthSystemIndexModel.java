package nirmalya.aatithya.restmodule.user.model;

public class RestEhealthSystemIndexModel {
	private Integer state;
	private String stateName;
	
	private Integer district;
	private String districtName;

	private String logo;
	private String image;

	private String city;
	private String language;
	public RestEhealthSystemIndexModel(Object state, Object stateName, Object district, Object districtName
			, Object logo, Object image, Object city, Object language) {
		super();
		this.state = (Integer)state;
		this.stateName = (String)stateName;
		this.stateName = (String)stateName;
		this.district = (Integer)district;
		this.districtName = (String)districtName;
		this.logo = (String)logo;
		this.image = (String)image;
		this.city = (String)city;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public Integer getDistrict() {
		return district;
	}
	public void setDistrict(Integer district) {
		this.district = district;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	
}
