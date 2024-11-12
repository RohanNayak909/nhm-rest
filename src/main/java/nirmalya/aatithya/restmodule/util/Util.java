package nirmalya.aatithya.restmodule.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.enums.ResponseStatus;

public class Util {

	private static final Logger logger = LoggerFactory.getLogger(Util.class);

	public static <E> boolean isNull(E object) {
		return object == null;
	}

	public static String toString(Exception object) {
		String json = null;
		try {
			StackTraceElement[] traceObj = object.getStackTrace();
			json = "FileName: " + traceObj[0].getFileName() + "\nLineNumber: " + traceObj[0].getLineNumber()
					+ "\ncause: " + object.getMessage() + "\nmethodName: " + traceObj[0].getMethodName()
					+ "\nException: " + object.fillInStackTrace();
		} catch (Exception e) {
			logger.error("toString: " + e.getMessage());
			e.printStackTrace();
		}
		return json;
	}

	public static String parseExceptionToString(Exception ex) {
		String parsedException = null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ex.printStackTrace(new PrintStream(out));
		parsedException = new String(out.toByteArray());
		return parsedException;
	}


public static String timeStampToDate(long value) {
	Timestamp stamp = new Timestamp(value);
	Date date = new Date(stamp.getTime());
	return new SimpleDateFormat("yyyy-MM-dd").format(date);
}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static JsonResponse setJsonResponse(JsonResponse response, Object body, ResponseStatus code, String message) {
				
		response.setBody(body);
		response.setMessage(message);
		response.setCode(code.name());
		
		return response;
	}
	
	
}
