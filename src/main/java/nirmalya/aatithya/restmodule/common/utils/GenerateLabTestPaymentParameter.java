package nirmalya.aatithya.restmodule.common.utils;

import java.util.List;

import nirmalya.aatithya.restmodule.api.model.CureeazyAddtoCartModel;
import nirmalya.aatithya.restmodule.util.GenerateRandomValue;

public class GenerateLabTestPaymentParameter {

	public static String getPayamentDetails(List<CureeazyAddtoCartModel> data) {

		String s = "";
		String addtocart = "";
		String order_id1="";
		for (CureeazyAddtoCartModel a : data) {
			addtocart = addtocart + "(order_id1,\'" + a.getTestId() + "\',\'" + a.getPrice()
					+ "\',\'" + a.getTestName() + "\',\'"+a.getDiscountPrice() + "\',\'"+a.getTat()+ "\',\'"+a.getDescription()+"\',\'"+a.getImage()+"\'),";
		}
		
	

		addtocart = addtocart.substring(0, addtocart.length() - 1);

		s = s + "" + addtocart + ",";

		if (s != "") {
			s = s.substring(0, s.length() - 1);

		}


		return s;
	}
	
}
