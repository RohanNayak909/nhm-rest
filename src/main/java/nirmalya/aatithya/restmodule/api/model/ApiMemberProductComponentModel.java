package nirmalya.aatithya.restmodule.api.model;

import java.util.List;

public class ApiMemberProductComponentModel {
		private String planCode;  
	    private List<ApiMemberQuestionDetail> memberQuestionDetails;
	    private String sumInsured;
		public ApiMemberProductComponentModel() {
			super();
			// TODO Auto-generated constructor stub
		}
		public String getPlanCode() {
			return planCode;
		}
		public void setPlanCode(String planCode) {
			this.planCode = planCode;
		}
		public List<ApiMemberQuestionDetail> getMemberQuestionDetails() {
			return memberQuestionDetails;
		}
		public void setMemberQuestionDetails(List<ApiMemberQuestionDetail> memberQuestionDetails) {
			this.memberQuestionDetails = memberQuestionDetails;
		}
		public String getSumInsured() {
			return sumInsured;
		}
		public void setSumInsured(String sumInsured) {
			this.sumInsured = sumInsured;
		}
	    
}
