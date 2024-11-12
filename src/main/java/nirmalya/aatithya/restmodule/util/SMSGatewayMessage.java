package nirmalya.aatithya.restmodule.util;

public class SMSGatewayMessage {
	public static String EMERGENCY_ALERT = "Hi, I'm {name} need emergency help from you, please find my location here at {msg} ."
	+ "\r\n"
	+ "Regards,\r\n"
	+ "eHealthSystem";
//	public static String OTP = "Welcome to eHealthSystem. Your OTP is {otp}";
//	public static String REGISTRATION = "Welcome to eHealthSystem. You are registered successfully! Your UserId is "
//			+ "{userid} or {mobile} and password is {password}";
//	public static String FORGOT_USERID = "Your Username for eHealthSystem for registered mobile number is {userid}";
//	public static String DOCTOR_APPOINTMENT = "Dear User,\r\n"
//			+ "\r\n"
//			+ "Your Request for Appointment sent successfully! Your Appointment Date and time is {date} "
//			+ "{time} with Dr. {doctorname}\r\n"
//			+ "\r\n"
//			+ "Regards,\r\n"
//			+ "eHealthSystem Team";
//	public static String EMERGENCY_ALERT = "Hi, I'm {name} need emergency help from you, please find my location here at {msg} ."
//			+ "\r\n"
//			+ "Regards,\r\n"
//			+ "eHealthSystem";
//	public static String TEST_REPORT = "Dear {name},\n" + "\n"
//			+ "Your Report has been generated successfully! Download from link {url}\n\n"
//			+ "Regards,\r\n" + "eHealthSystem Team";
	public static String OTP = "Dear {name}, {otp} is the OTP for your {verify} to the CureEZ Digital HealthCare Platform. Thanks for using CureEZ.";
	

	public static String APPOINTMENT="Hi {name}, gentle reminder that your {appoint}appointment is scheduled for {appointDate}. Please CTA on time. Thanks, Team CureEZ.";
	public static String ORDERCONFIRM="Hi {name}, Your {time} appointment is {status} for {paymentDate}. Thanks, Team CureEZ.";
	public static String LABORDER="Dear Partner {name}. We have received lab test order No. {orderId} on Date {paymentDate} at {paymentTime}from user {userName}. Please process the request. Thanks, Team CureEZ.";
}
