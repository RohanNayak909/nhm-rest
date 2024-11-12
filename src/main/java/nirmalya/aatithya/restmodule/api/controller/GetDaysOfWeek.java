package nirmalya.aatithya.restmodule.api.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import nirmalya.aatithya.restmodule.api.model.OPDResponseModel;

public class GetDaysOfWeek {

	private Calendar c;
	private List<OPDResponseModel> output;

	public GetDaysOfWeek() {
		c = Calendar.getInstance();
		output = new ArrayList<OPDResponseModel>();
	}

	public List<OPDResponseModel> getCalendar() {
		switch (c.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SUNDAY:
			c.add(Calendar.DATE, 1);
			break;

		case Calendar.MONDAY:
			break;

		case Calendar.TUESDAY:
			c.add(Calendar.DATE, -1);
			break;

		case Calendar.WEDNESDAY:
			c.add(Calendar.DATE, -2);
			break;

		case Calendar.THURSDAY:
			c.add(Calendar.DATE, -3);
			break;

		case Calendar.FRIDAY:
			c.add(Calendar.DATE, -4);
			break;

		case Calendar.SATURDAY:
			c.add(Calendar.DATE, 2);
			break;
		}

		Date curdate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		String cdate = format.format(curdate);
		for (int x = 1; x < 8; x++) {
			Date date2 = c.getTime();
			c.add(Calendar.DATE, 1);
			String newdate = format.format(date2);
			String dayWeekText = new SimpleDateFormat("EEEE").format(date2);
			Boolean status = compareDates(newdate,cdate);
			OPDResponseModel dd = new OPDResponseModel();
			dd.setKey(newdate);
			dd.setName(dayWeekText);
			dd.setCode(status);
			output.add(dd);
		}
		return this.output;
	}

	public Boolean compareDates(String d1, String d2) {
		
		Boolean status = true;
		
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = sdf.parse(d1);
			Date date2 = sdf.parse(d2);
			
			if (date1.after(date2)) {
				status = true;
			}
			if (date1.before(date2)) {
				status = false;
			}
			if (date1.equals(date2)) {
				status = true;
			}

		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		
		return status;
	}
}
