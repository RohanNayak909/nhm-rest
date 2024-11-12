package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LabDashboardCountModel {
	
	private String pending;
	private String accepted;
	private String completed;
	private String cancelled;
	private String assigned;
	private String samplecollected;
	private String resultpending;
	private String sampleunderprocessing;
	public LabDashboardCountModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LabDashboardCountModel(Object pending, Object accepted, Object completed, Object cancelled, Object assigned,
			Object samplecollected, Object resultpending, Object sampleunderprocessing) {
		super();
		this.pending = (String) pending;
		this.accepted = (String) accepted;
		this.completed = (String) completed;
		this.cancelled = (String) cancelled;
		this.assigned = (String) assigned;
		this.samplecollected = (String) samplecollected;
		this.resultpending = (String) resultpending;
		this.sampleunderprocessing = (String) sampleunderprocessing;
	}
	public String getPending() {
		return pending;
	}
	public void setPending(String pending) {
		this.pending = pending;
	}
	public String getAccepted() {
		return accepted;
	}
	public void setAccepted(String accepted) {
		this.accepted = accepted;
	}
	public String getCompleted() {
		return completed;
	}
	public void setCompleted(String completed) {
		this.completed = completed;
	}
	public String getCancelled() {
		return cancelled;
	}
	public void setCancelled(String cancelled) {
		this.cancelled = cancelled;
	}
	public String getAssigned() {
		return assigned;
	}
	public void setAssigned(String assigned) {
		this.assigned = assigned;
	}
	public String getSamplecollected() {
		return samplecollected;
	}
	public void setSamplecollected(String samplecollected) {
		this.samplecollected = samplecollected;
	}
	public String getResultpending() {
		return resultpending;
	}
	public void setResultpending(String resultpending) {
		this.resultpending = resultpending;
	}
	public String getSampleunderprocessing() {
		return sampleunderprocessing;
	}
	public void setSampleunderprocessing(String sampleunderprocessing) {
		this.sampleunderprocessing = sampleunderprocessing;
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
