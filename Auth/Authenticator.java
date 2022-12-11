package Auth;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import Common.*;

public class Authenticator {

	public static Boolean users(String inputedUser, String inputedPass) throws IOException {
		//Creating Variables
		boolean found = false;
		String fileUser = null;
		
		//Finds the file
		InputStream users = Authenticator.class.getResourceAsStream(AdjustOS.USERSPATH);
		//Creating file reader
		Scanner fileReader = new Scanner(users);

		//Goes through the file
		while(fileReader.hasNextLine()) {
			fileUser = fileReader.nextLine();
			//Tests each line of the file against inputed password
			if(fileUser.equals(inputedUser + " " + inputedPass)) {
				found = true;
			}
		}
		
		//Closes FileReader
		fileReader.close();
		
		//Returns if the password was found or not
		if(found) return found;
		
		//Check for custom users
		else {
			try {
				//Checks for any custom users
				InputStream usersCustom = Authenticator.class.getResourceAsStream(AdjustOS.USERSPATH.replaceFirst("/.*/", "/"));
				Scanner fileReaderCustom = new Scanner(usersCustom);
				//Goes through the file
				while(fileReaderCustom.hasNextLine()) {
					fileUser = fileReaderCustom.nextLine();
					//Tests each line of the file against inputed password
					if(fileUser.equals(inputedUser + " " + inputedPass)) {
						found = true;
					}
				}
				
				//Closes FileReader
				fileReaderCustom.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return found;
		}
	}
	
	public static void createNewUser(String inputedUser, String inputedPass) throws IOException{
		//Finds the file by removing the initial slash
		//Necessary for new users
		File users = new File(AdjustOS.USERSPATH.replaceFirst("/.*/", ""));
		//Creating file writer
		FileWriter fileWriter = new FileWriter(users, true);
		
		//Write the new user to the file
		fileWriter.write(System.getProperty("line.separator") + inputedUser + " " + inputedPass);
		
		//Closes the file writer
		fileWriter.close();
	}
}
