package com.a00326288.project01;
import java.io.Console;
import java.sql.Date;
import java.util.Base64;
import java.util.Scanner; 


public class UserAccessControl {
	
	
	public static String username;
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
	
	

	// Getter
		public static String getUsername() {
			return username;
		}

		 // Setter
		public static void setUsername(String username) {
			UserAccessControl.username = username;
		}
		
		// Getter
		public static String getPassword() {
			return password;
		}

		 // Setter
		public void setPassword(String password) {
			UserAccessControl.password = password;
		}
 
	
	
	public static String encode(String username, String password) {
		
		//Combine the Username and Password into Single String
		String Input = username + password;
		String hashString = Base64.getEncoder().encodeToString(Input.getBytes());
		return hashString;
	}
	
	public static String encode(String password) {
		
		String hashString = Base64.getEncoder().encodeToString(password.getBytes());
		return hashString;
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
			
			
			//Invoke DB Connection method passing both Hashed Values to Check DB.
			String dbGetUser=DBA.dbConnection(encode(getUsername(),getPassword()),encode(getPassword()));
			
			
			
			//Check if User Found (If null = User Not Found, Throw Username or Password Invalid -> Try Again)
			if (dbGetUser != null) {
				System.out.println("Hello "+username);
				User user = new User(username, password);
				launchpad.menu();
				
			}else {
				System.out.println("Invalid Username or Password");
				launchpad.menu();
			}
			
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
