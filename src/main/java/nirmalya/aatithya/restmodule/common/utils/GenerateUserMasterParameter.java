package nirmalya.aatithya.restmodule.common.utils;

import java.util.List;

import nirmalya.aatithya.restmodule.user.model.UserAccessModel;

public class GenerateUserMasterParameter {

	public static String saveUserMaster(UserAccessModel id) {
		String s = "";
		String data = "";
		
		if(id.getUserId()!=null && id.getUserId()!="") {
			s = s + "@p_userId='" + id.getUserId() + "',";
		}
		if(id.getUserFirstName()!=null && id.getUserFirstName()!="") {
			s = s + "@p_userFirstName='" + id.getUserFirstName() + "',";
		}
		if(id.getUserLastName()!=null && id.getUserLastName()!="") {
			s = s + "@p_userLastName='" + id.getUserLastName() + "',";
		}
		if(id.getUserType()!=null && id.getUserType()!="") {
			s = s + "@p_userType='" + id.getUserType() + "',";
		}
		if(id.getUserEmail()!=null && id.getUserEmail()!="") {
			s = s + "@p_userEmail='" + id.getUserEmail() + "',";
		}
		if(id.getUserPhone()!=null && id.getUserPhone()!="") {
			s = s + "@p_userPhone='" + id.getUserPhone() + "',";
		}
		if(id.getPassword()!=null && id.getPassword()!="") {
			s = s + "@p_userPassword='" + id.getPassword() + "',";
		}
		if(id.getUserEmployee()!=null && id.getUserEmployee()!="") {
			s = s + "@p_userEmployee='" + id.getUserEmployee() + "',";
		}
		if(id.getCreatedBy()!=null && id.getCreatedBy()!="") {
			s = s + "@p_createdBy='" + id.getCreatedBy() + "',";
		}
		if(id.getUserStatus()!=null) {
			s = s + "@p_isUserActive=" + id.getUserStatus() + ",";
		}
		
		if(id.getRoleList().size() > 0) {
			for(String m : id.getRoleList()) {
				data = data + "(@p_userId,\"" + m + "\"),";
			}
			
			data = data.substring(0, data.length() - 1);
		}
		s = s + "@p_userRoleList='" + data + "';";
		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}
		
		System.out.println(s);
		
		return s;
	}

	public static String getUserIdList(List<DropDownModel> id) {
		
		String s = "";
		String a = "";
		String section = "";
		
		if(id.get(0).getName()!=null && id.get(0).getName()!="") {
			s = s + "@P_ModifiedBy='" + id.get(0).getName() + "',";
		}
		
		if(id.size() > 0) {
			for(DropDownModel m : id) {
				section = section + "\"" + m.getKey() + "\",";
			}
			
			section = section.substring(0, section.length() - 1);
		} else {
			s = s.substring(0, s.length() - 1);
		}
		
		a = "(" + section + ")";
		
		s = s + "@p_userListSubQuery='" + a + "';";
		
		s = "SET " + s ;
		
		System.out.println(s);
		
		return s;
	}

}
