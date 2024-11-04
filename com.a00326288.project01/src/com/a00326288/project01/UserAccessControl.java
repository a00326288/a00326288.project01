package com.a00326288.project01;
import java.util.Base64;
import java.util.Scanner; 



public class UserAccessControl {
	
	
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//DBA.dbConnection();
		//Login();
		
		Register();
	}
 


	public static void Login() {
		
		System.out.println("Login");
		
		try {
			//Get the Username and Password from Scanner
			System.out.println("Enter you username:");
			String Username = sc.next();
			System.out.println("Enter you password:");
			String Password = sc.next();
			
			//Combine the Username and Password into Single String
			String originalInput = Username + Password;
			
			//Hash the Username and Password into an Encoded String (will be used as Unique Identifier).
			String hashStringUID = Base64.getEncoder().encodeToString(originalInput.getBytes());
			
			//Hash the password to insert into DB to avoid plaintext. 
			String hashPass = Base64.getEncoder().encodeToString(Password.getBytes());

			//Invoke DB Connection method passing both Hashed Values to Check DB.
			String username=DBA.dbConnection(hashPass,hashStringUID);
			
			//Check if User Found (If null = User Not Found, Throw Username or Password Invalid -> Try Again)
			if (username != null) {
				System.out.println("Welcome back "+username);			    
			}else {
				System.out.println("Invalid Username or Password");
				launchpad.main(null);
			}
			
		}catch(Exception e){
			System.out.println("Invalid Username or Password.");
			e.printStackTrace();
			
			
		}
	}


	public static void Register() {
		
		
	    
		// TODO Auto-generated method stub
		System.out.println("Register");
		
		
		try(Scanner in = new Scanner(System.in)){
			System.out.println("Enter a username:");
			String UsrInput = in.next(); 
			inputValidation.validateInput(UsrInput);
				
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		/*
		
		int length = Username.length();
		while(Username != null && length < 10 &&  ) {
			
			
		}*/
			
		
		
		
		
	}

}
