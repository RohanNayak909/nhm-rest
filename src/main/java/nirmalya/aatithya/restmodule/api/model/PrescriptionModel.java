package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PrescriptionModel {
 
		private String date;
		private String doctorId;
		private String appointmentId;
		private String specialadvice;
		private String userid;
		private List<MedicineApiModel> medicinelist;
		private List<PrescriptionTestModel> testlist;
		private String consultType;
		private String showCode;
		private String docName;
		private String extension;
		
		private String patientName;
		private String dob;
		private String gender;
		private String address;
		private String phno;
		private String apptId;
		private String appointDt;
		private String prescriptionId;
		private String status;
		private String doctorName;
		private String speciality;
		private String regdno;
		private String education;
		private String token;
		public PrescriptionModel() {
			super();
			// TODO Auto-generated constructor stub
		}
		public PrescriptionModel(Object patientName, Object doctorName,Object token) {
			super();
			this.patientName = (String)patientName;
			this.doctorName = (String)doctorName;
			this.token = (String)token;
		}
		
		public PrescriptionModel(Object patientName, Object dob, Object gender, Object address, Object phno,
				Object apptId, Object appointDt, Object prescriptionId,Object specialadvice,Object status
				, Object doctorName,Object speciality,Object regdno,Object education) {
			super();
			this.patientName = (String)patientName;
			this.dob = (String)dob;
			this.gender = (String)gender;
			this.address = (String)address;
			this.phno = (String)phno;
			this.apptId = (String)apptId;
			this.appointDt = (String)appointDt;
			this.prescriptionId = (String)prescriptionId;
			this.specialadvice = (String)specialadvice;
			this.status = (String)status;
			this.doctorName = (String)doctorName;
			this.speciality = (String)speciality;
			this.regdno = (String)regdno;
			this.education = (String)education;
		}


		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getDoctorId() {
			return doctorId;
		}
		public void setDoctorId(String doctorId) {
			this.doctorId = doctorId;
		}
		public String getAppointmentId() {
			return appointmentId;
		}
		public void setAppointmentId(String appointmentId) {
			this.appointmentId = appointmentId;
		}
		public String getSpecialadvice() {
			return specialadvice;
		}
		public void setSpecialadvice(String specialadvice) {
			this.specialadvice = specialadvice;
		}
		public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			this.userid = userid;
		}
		public List<MedicineApiModel> getMedicinelist() {
			return medicinelist;
		}
		public void setMedicinelist(List<MedicineApiModel> medicinelist) {
			this.medicinelist = medicinelist;
		}
		public List<PrescriptionTestModel> getTestlist() {
			return testlist;
		}
		public void setTestlist(List<PrescriptionTestModel> testlist) {
			this.testlist = testlist;
		}
		
		public String getConsultType() {
			return consultType;
		}
		public void setConsultType(String consultType) {
			this.consultType = consultType;
		}
		
		public String getShowCode() {
			return showCode;
		}
		public void setShowCode(String showCode) {
			this.showCode = showCode;
		}
		
		public String getDocName() {
			return docName;
		}
		public void setDocName(String docName) {
			this.docName = docName;
		}
		
		public String getExtension() {
			return extension;
		}
		public void setExtension(String extension) {
			this.extension = extension;
		}
		
		public String getPatientName() {
			return patientName;
		}


		public void setPatientName(String patientName) {
			this.patientName = patientName;
		}


		public String getDob() {
			return dob;
		}


		public void setDob(String dob) {
			this.dob = dob;
		}


		public String getGender() {
			return gender;
		}


		public void setGender(String gender) {
			this.gender = gender;
		}


		public String getAddress() {
			return address;
		}


		public void setAddress(String address) {
			this.address = address;
		}


		public String getPhno() {
			return phno;
		}


		public void setPhno(String phno) {
			this.phno = phno;
		}


		public String getApptId() {
			return apptId;
		}


		public void setApptId(String apptId) {
			this.apptId = apptId;
		}


		public String getAppointDt() {
			return appointDt;
		}


		public void setAppointDt(String appointDt) {
			this.appointDt = appointDt;
		}


		public String getPrescriptionId() {
			return prescriptionId;
		}


		public void setPrescriptionId(String prescriptionId) {
			this.prescriptionId = prescriptionId;
		}
		


		public String getStatus() {
			return status;
		}


		public void setStatus(String status) {
			this.status = status;
		}
		


		public String getDoctorName() {
			return doctorName;
		}


		public void setDoctorName(String doctorName) {
			this.doctorName = doctorName;
		}


		public String getSpeciality() {
			return speciality;
		}


		public void setSpeciality(String speciality) {
			this.speciality = speciality;
		}


		public String getRegdno() {
			return regdno;
		}


		public void setRegdno(String regdno) {
			this.regdno = regdno;
		}
		


		public String getEducation() {
			return education;
		}


		public void setEducation(String education) {
			this.education = education;
		}
		


		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
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
