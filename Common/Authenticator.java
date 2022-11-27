package checkUsername;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Authenticator {
	public static void main (String[] args) throws IOException {
		//Creating Variables
		boolean user = false;
		boolean password = false;
		String userName = null;
		String userPass = null;
		
		//Making the Scanner
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		//Asking the user to input user name
		System.out.println("Hello User! Please enter your Username!");
			//Do While to put in user names until one is correct
			do {
			userName = in.next();
			
			//If user wishes to exit
			if(userName.toLowerCase().equals("exit")) {
				System.out.println("User has chosen to exit. Goodbye!");
				System.exit(0);
			}
			
			//Calling the user names checker
			user = fileUsernames(userName);
			//If user name is correct
			if(user == true) {
				System.out.println("We have found your Username!");
			}
			//If user name is incorrect
			else {
				System.out.println("We did not find your Username! Please try again!");
			}
		//Repeats until correct user name input
		} while(user == false);
		
		//Asks user to input their password
		System.out.println("Now we just need your password!");
		userPass = in.next();
		
		//Calling the password checker
		password = filePasswords(userPass);
		
		//If password is correct
		if(password == true) {
			System.out.println("Hello " + userName + "! Welcome to the chicken calculator!");
		}
		//If password is incorrect
		else {
			System.out.println("Sorry! That is the wrong password! Goodbye!");
			//Exits the system
			System.exit(0);
		}
	}

	public static Boolean fileUsernames (String inputedUser) throws IOException {
		//Creating Variables
		boolean userFound = false;
		String fileUsernames = null;
		
		//Finds the file
		File usernames = new File ("C:\\Users\\bluej\\eclipse-workspace\\Autentication\\src\\checkUsername\\Usernames.txt");
		//Creating file reader
		Scanner fileReader = new Scanner(usernames);
		
		//Goes through the file
		while(fileReader.hasNextLine()) {
			fileUsernames = fileReader.nextLine();
			//Tests each line of the file against inputed user name
			if(fileUsernames.equals(fileUsernames)) {
				userFound = true;
			}
		}
		
		//Closes fileReader
		fileReader.close();
		
		//Returns if the user name was found or not
		return userFound;
	}
	
	public static Boolean filePasswords (String inputedPass) throws IOException {
		//Creating Variables
		boolean correctPass = false;
		String filePass = null;
		
		//Finds the file
		File passwords = new File ("C:\\Users\\bluej\\eclipse-workspace\\Autentication\\src\\checkUsername\\Passwords.txt");
		//Creating file reader
		Scanner fileReader = new Scanner(passwords);
		
		//Goes through the file
		while(fileReader.hasNextLine()) {
			filePass = fileReader.nextLine();
			//Tests each line of the file against inputed password
			if(inputedPass.equals(filePass)) {
				correctPass = true;
			}
		}
		
		//Closes FileReader
		fileReader.close();
		
		//Returns if the password was found or not
		return correctPass;
	}
}
