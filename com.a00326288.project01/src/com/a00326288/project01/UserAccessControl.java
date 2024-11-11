package com.a00326288.project01;
import java.io.Console;
import java.util.Scanner;





public class UserAccessControl {

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

	
	private static Boolean checkLoggedIn() {
		
		if(User.readSession()!=null) {
			return false;
		}else {
			
			return true;
		}
		
	}
	
	
	

	public static void Login() {
		
		
		if(checkLoggedIn()==false) {
			System.out.println("You are already logged in.");	
			launchpad.menu();
		}else
		{
		
		
		try {
			
			
			//Get the Username and Password from Scanner
			username = cnsl.readLine( 
					"Enter username : ");
			
			char[] consolePass = cnsl.readPassword( 
					"Enter password : ");
			
			String pass = new String();
			
			password = String.valueOf(consolePass);
			
			
		
			
			
			if(User.dbCheckUser(username,password)==true) {
			
				
			
					System.out.println("-----------------------------");
					System.out.println("- Welcome Back - " + User.myuserlist.get(0).getUsername()+" -");
					System.out.println("-----------------------------\n");
					
			}else {
					System.out.println("Invalid details or User does not Exists");
			
			}
			
		
			launchpad.menu();
			
			
		}catch(Exception e){
			System.out.println("Invalid Username or Password.");
			e.printStackTrace();
			
			
		}
	}
	
	}


	public static void Register() {
		
			
		if(checkLoggedIn()==false) {
			System.out.println("You are already logged in.");	
			launchpad.menu();
		}else
		{    
		// TODO Auto-generated method stub
		System.out.println("Register");
				
		
		
		int flg = 0;
		
		while(flg==0) {
			System.out.println("Enter a username:");
			
			username = sc.nextLine();	
			
		if(User.dbCheckUser(username)==true) 
			{
			System.out.println("Username " + username + " is already taken. Please try again.");
			flg=0;
			}
			else{
				System.out.println("Username "+ username + " is accepted.");
				flg=1;
			}
		}
	
		
		flg=0;
		
		
		
		while(flg==0) {
			System.out.println("Enter a password:");
			password = sc.nextLine();	
			
			if(InputValidation.validateInput(password)==1)
			{
				User.dbCreateUser(username, password);
				flg=1;
			}else
			{
				System.out.println("Please enter between 10 - 15 characters, comprising of 1 special char, 1 digit and no spaces.");
			}
			
		}
		launchpad.main(null);		
		}
		
		
	}
	
}
