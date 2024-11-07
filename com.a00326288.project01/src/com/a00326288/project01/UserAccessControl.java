package com.a00326288.project01;
import java.io.Console;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
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
			
			
			//System.out.println(MyUser.getUsername());
			//System.out.println(MyUser.getPassword());
			
			launchpad.menu();
			
			
			
			
			//Check if User Found (If null = User Not Found, Throw Username or Password Invalid -> Try Again)
			
			
		}catch(Exception e){
			System.out.println("Invalid Username or Password.");
			e.printStackTrace();
			
			
		}
	}


	public static void Register() {
		
			
		    
		// TODO Auto-generated method stub
		System.out.println("Register");
		System.out.println("Enter a username:");
		
        User[] arr;

        // Allocating memory for 2 objects
        // of type student
        arr = new User[1];

       
		
		int flag=1;
		
		
		do {
			System.out.println("Please enter between 10 - 15 characters, comprising of 1 special char, 1 digit and no spaces.");
			String UsrInput = sc.nextLine();	
			flag=InputValidation.validateInput(UsrInput);
			
		}while(flag==0);
		
		if(flag==1) {
			System.out.println("Success");
		}else {
			launchpad.main(null);
		}
		
		
		

		
	}
	
	
		

}
