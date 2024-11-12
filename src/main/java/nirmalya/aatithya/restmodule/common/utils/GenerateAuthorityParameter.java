package nirmalya.aatithya.restmodule.common.utils;

import nirmalya.aatithya.restmodule.user.model.RestProcessMasterModel;
import nirmalya.aatithya.restmodule.user.model.UserSetAuthority;

public class GenerateAuthorityParameter {

	public static String saveProcessMaster(RestProcessMasterModel id) {
		String s = "";
		if(id.gettProcess()!=null && id.gettProcess()!="") {
			s = s + "@P_Process='" + id.gettProcess() + "',";
		}
		if(id.gettProcessName()!=null && id.gettProcessName()!="") {
			s = s + "@P_ProcessName='" + id.gettProcessName() + "',";
		}
		if(id.gettProcessDescription()!=null && id.gettProcessDescription()!="") {
			s = s + "@P_ProcessDesc='" + id.gettProcessDescription() + "',";
		}
		if(id.gettProcessCreatedBy()!=null && id.gettProcessCreatedBy()!="") {
			s = s + "@P_CreatedBy='" + id.gettProcessCreatedBy() + "',";
		}
		if(id.gettProcessStatus()!=null) {
			s = s + "@P_Status=" + id.gettProcessStatus() + ",";
		}
		
		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}
		
		System.out.println(s);
		
		return s;
	}

	public static String saveAuthorityMaster(UserSetAuthority id) {
		String s = "";
		String auth = "";
		
		if(id.getProcess()!=null && id.getProcess()!="") {
			s = s + "@P_Process='" + id.getProcess() + "',";
		}
		if(id.getUserRole()!=null && id.getUserRole()!="") {
			s = s + "@P_UserRole='" + id.getUserRole() + "',";
		}
		
		if(id.getUser()!=null && id.getUser()!="") {
			String arr[] = id.getUser().split(",");
			for(int i = 0; i < arr.length; i++) {
				auth = auth + "(\"" + id.getProcess() + "\",\"" + id.getUserRole() + "\",\"" + arr[i] + "\",@P_StageNo,1,24,\"" + id.getCreatedBy() + "\"),";
			}
			
			auth = auth.substring(0, auth.length() - 1);

			s = s + "@P_AuthSubQuery='" + auth + "',";
		}
		
		if(s != "") {
			s = s.substring(0, s.length()-1);
			
			s = "SET " + s + ";" ;
		}
		
		System.out.println(s);
		return s;
	}

}
