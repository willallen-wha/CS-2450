package Auth;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Common.*;

public class Authenticator {

	private File usersFile;

	public Authenticator() {
		try {
			usersFile = new File(getClass().getResource(AdjustOS.USERSPATH).toURI());
		} catch (Exception e) {
			e.printStackTrace();
			usersFile = null;
		}
	}

	public Boolean users(String inputedUser, String inputedPass) throws IOException {
		//Creating Variables
		boolean found = false;
		String fileUser = null;
		
		//Finds the file if not defined
		if(usersFile == null) {
			usersFile = new File(AdjustOS.USERSPATH);
		}
		//Creating file reader
		Scanner fileReader = new Scanner(usersFile);
		
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
		return found;
	}
	
	public void createNewUser(String inputedUser, String inputedPass) throws IOException{
		//Finds the file if not defined
		if(usersFile == null) {
			usersFile = new File(AdjustOS.USERSPATH);
		}
		//Creating file writer
		FileWriter fileWriter = new FileWriter(usersFile, true);
		
		//Write the new user to the file
		fileWriter.write(System.getProperty("line.separator") + inputedUser + " " + inputedPass);
		
		//Closes the file writer
		fileWriter.close();
	}
}
