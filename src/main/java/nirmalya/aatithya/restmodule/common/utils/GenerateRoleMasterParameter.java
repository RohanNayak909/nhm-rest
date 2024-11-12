package nirmalya.aatithya.restmodule.common.utils;

import java.util.List;

public class GenerateRoleMasterParameter {

	public static String getRoleIdList(List<DropDownModel> id) {
		
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
		
		s = s + "@p_roleListSubQuery='" + a + "';";
		
		s = "SET " + s ;
		
		System.out.println(s);
		
		return s;
	}

}
