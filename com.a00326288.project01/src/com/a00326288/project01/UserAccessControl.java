package com.a00326288.project01;
import java.io.Console;
 
import java.util.Scanner;





public class UserAccessControl extends User {
	
	
	public UserAccessControl(String username, String password) {
		super(username, password);
		// TODO Auto-generated constructor stub
		
		
	}


	private static String username;
	private static String password;
	
	
	static Console cnsl = System.console();
	static Scanner sc = new Scanner(System.in);


	
	public static void main(String[] args) {
	
		if (cnsl == null) {
	         System.out.println("No console available");
	         // exit(): Terminates the currently running Java Virtual Machine.
	         // The argument serves as a status code; by convention,
	         // a nonzero status code indicates abnormal termination.
	         System.exit(0);
	     }
	

		// Declaring an array of student

	}

	
	
	

	public static void Login() {
		
		System.out.println("Login");
		
	
		
		try {
			//Get the Username and Password from Scanner
			username = cnsl.readLine( 
					"Enter username : ");
			
			char[] consolePass = cnsl.readPassword( 
					"Enter password : ");
			
			String pass = new String();
			
			password = String.valueOf(consolePass);
			
			
			User user  = new User(username,password);
			
			
			if(user.dbCheckUser()==true) {
					System.out.println("Welcome back " + user.getUsername() );
			}else {
					System.out.println("Invalid details or User does not Exists");
			
			}
			
		
			launchpad.menu();
			
			
		}catch(Exception e){
			System.out.println("Invalid Username or Password.");
			e.printStackTrace();
			
			
		}
	}


	public static void Register() {
		
			
		    
		// TODO Auto-generated method stub
		System.out.println("Register");
				
		int flg = 0;
		
		while(flg==0) {
			System.out.println("Enter a username:");
			username = sc.nextLine();	
			if(dbCheckUser(username)==true) {
				System.out.println("Username "+ username + " is accepted.");
				flg=1;
			}else
			{
			System.out.println("Username " + username + " is already taken. Please try again.");
			flg=0;
			}
		}
		
	
		
		flg=0;
		
		
		
		while(flg==0) {
			System.out.println("Enter a password:");
			password = sc.nextLine();	
			if(InputValidation.validateInput(password)==1)
			{
				User newUser = new User(username, password);
				newUser.dbCreateUser(username, password);
				flg=1;
			}else
			{
				System.out.println("Please enter between 10 - 15 characters, comprising of 1 special char, 1 digit and no spaces.");
			}
			
		}
		launchpad.main(null);		

	}
	
}
