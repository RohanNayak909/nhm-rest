package nirmalya.aatithya.restmodule.api.model;

public class ApiMemberQuestionDetails {
	private String questionCode;
    private String answer;
    private String remarks;
	public ApiMemberQuestionDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getQuestionCode() {
		return questionCode;
	}
	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
    
}
