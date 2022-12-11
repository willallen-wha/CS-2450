package Auth;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import Common.*;

public class Authenticator {
	public static void main (String[] args) throws IOException {
		//Creating Variables
		boolean user = false;
		String userName = null;
		String userPass = null;
		
		//Making the Scanner
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		//Asking the user to input user name
		System.out.println("Hello User!");
			do {
				//Gets the user name and password from the user
				System.out.println("Please enter your user name!");
				userName = in.nextLine();
				System.out.println("Please enter your password!");
				userPass = in.nextLine();
				user = users(userName, userPass);
				
				if(user == true) {
					System.out.println("Welcome " + userName + "!");
				}
				else {
					System.out.println("Authentication failed, please try again.");
				}
			} while (user == false);
	}

	public static Boolean users(String inputedUser, String inputedPass) throws IOException {
		//Creating Variables
		boolean found = false;
		String fileUser = null;
		
		//Finds the file
		File users = new File(AdjustOS.USERSPATH);
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
		return found;
	}
	
	public static void createNewUser(String inputedUser, String inputedPass) throws IOException{
		//Finds the file
		File users = new File(AdjustOS.USERSPATH);
		//Creating file writer
		FileWriter fileWriter = new FileWriter(users, true);
		
		//Write the new user to the file
		fileWriter.write(System.getProperty("line.separator") + inputedUser + " " + inputedPass);
		
		//Closes the file writer
		fileWriter.close();
	}
}
