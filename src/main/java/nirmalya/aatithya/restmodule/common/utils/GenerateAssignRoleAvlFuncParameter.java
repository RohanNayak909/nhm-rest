package nirmalya.aatithya.restmodule.common.utils;

import java.util.List;

import nirmalya.aatithya.restmodule.user.model.AvailableFunctionModel;

public class GenerateAssignRoleAvlFuncParameter {

	public static String assignRole(AvailableFunctionModel id) {
		
		String s = "";
		
		if(id.getRoleId()!=null && id.getRoleId()!="") {
			s = s + "@P_RoleId='" + id.getRoleId() + "',";
		}
		if(id.getAvlFunction()!=null && id.getAvlFunction()!="") {
			s = s + "@P_AvlFunc='" + id.getAvlFunction() + "',";
		}
		if(id.getPerLevel()!=null && id.getPerLevel()!="") {
			s = s + "@P_PermsnLevel='" + id.getPerLevel() + "',";
		}
		if(id.getDataFilter()!=null && id.getDataFilter()!="") {
			s = s + "@P_DataFilter='" + id.getDataFilter() + "',";
		}
		if(id.getCreatedBy()!=null && id.getCreatedBy()!="") {
			s = s + "@P_CreatedBy='" + id.getCreatedBy() + "',";
		}
		
		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}
		
		System.out.println(s);
		
		return s;
	}

	public static String getActivityRoleList(List<DropDownModel> data) {
		
		String s = "";
		String roleList = "";
		
		if(data.get(0).getKey()!=null && data.get(0).getKey()!="") {
			s = s + "@P_Activity='" + data.get(0).getKey() + "',";
		}
		
		for(DropDownModel m : data) {
			roleList = roleList + "\"" + m.getName() + "\",";
		}
		
		roleList = roleList.substring(0, roleList.length() - 1);

		s = s + "@P_RoleList='(" + roleList + ")',";
		
		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}
		
		System.out.println(s);
		
		return s;
	}

}
