package nirmalya.aatithya.restmodule.api.model;

public class APIDoctorMonthlyOverviewModel {

	private String booked;
	private String requested;
	private String treated;
	private String fromdate;
	private String todate;
	private String patientname;
	private String reqDate;
	private String patNote;

	public APIDoctorMonthlyOverviewModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public APIDoctorMonthlyOverviewModel(Object booked, Object requested, Object treated,  Object todate,Object fromdate) {
		super();
		this.booked = (String) booked;
		this.requested = (String) requested;
		this.treated = (String) treated;
		this.fromdate = (String) fromdate;
		this.todate = (String) todate;
	}

	
	
	public APIDoctorMonthlyOverviewModel(Object patientname, Object reqDate, Object patNote) {
		super();
		this.patientname = (String) patientname;
		this.reqDate = (String) reqDate;
		this.patNote = (String) patNote;
	}

	public String getPatientname() {
		return patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}

	public String getReqDate() {
		return reqDate;
	}

	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}

	public String getPatNote() {
		return patNote;
	}

	public void setPatNote(String patNote) {
		this.patNote = patNote;
	}

	public String getBooked() {
		return booked;
	}

	public void setBooked(String booked) {
		this.booked = booked;
	}

	public String getRequested() {
		return requested;
	}

	public void setRequested(String requested) {
		this.requested = requested;
	}

	public String getTreated() {
		return treated;
	}

	public void setTreated(String treated) {
		this.treated = treated;
	}

	public String getFromdate() {
		return fromdate;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public String getTodate() {
		return todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}

}
