package nirmalya.aatithya.restmodule.util;

import java.io.FileWriter;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;

public class FileWrite {

	@Autowired
	EnvironmentVaribles env;

	public static void fileWrite(String filePath, String text) {

		FileWriter fWriter = null;
		try {
			fWriter = new FileWriter(filePath,true);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		try {
			fWriter.append(text);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// Closing the file writing connection
		try {
			fWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Display message for successful execution of
		// program on the console
		System.out.println("File is created successfully with the content.");

	}
	public static void ecgLogWrite(String filePath, String patientId, String message) {
		String text1 = patientId + " - " + CurrentDateTime.getCurrentDate() + " " + CurrentDateTime.getCurrentTime()
				+ " : "+message+".\n\n";
		FileWrite.fileWrite(filePath, text1);
	}
}
