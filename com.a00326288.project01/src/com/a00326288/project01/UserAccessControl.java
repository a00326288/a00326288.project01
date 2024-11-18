package com.a00326288.project01;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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

	
	public static Boolean checkLoggedIn() {
		
		if(readSession()!=null) {
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
			
			String user= User.dbCheckUser(username,password); 
			
			
			if(user!=null) {
			
					System.out.println("-----------------------------");
					System.out.println("- Welcome Back - " + user + " -");
					System.out.println("-----------------------------\n");
					
					whichMenu();
					
			}else {
					System.out.println("Invalid details or User does not Exists");
					launchpad.menu();
			}
			
			
		}catch(Exception e){
			System.out.println("Invalid Username or Password.");
			e.printStackTrace();
			
			
		}
	}
	
	}

	
	public static Boolean validateUsername(String username) {
			  
		try {
			if(User.dbCheckUser(username)==true) 
			{
			System.out.println("Username " + username + " is already taken. Please try again.");
			return true;
			}
			else{
				System.out.println("Username "+ username + " is accepted.");
				return false;
				}
			  }catch(Exception e){
				  
			  }
		return null;
		
	}
	
	public static void Register() {
		
		
			

		// TODO Auto-generated method stub
		System.out.println("Register");
				
		
		
		
		while(true) {
			  try {
				  System.out.println("Enter a username:");
				  if(validateUsername(sc.next())==false) {;	
				  break;
				  }
			  }catch(Exception e) {
				  e.printStackTrace();
				  
			  }
			  
			  
		}
		
		while(true) {
		
				System.out.println("Enter a password:");
				password = sc.next();
		 
				int flag = InputValidation.validateInput(password);
				
				if(flag==1)
				{
					User.dbCreateUser(username, password);
					System.out.println("User registered. Please login.");
					break;
					
				}else
				if(flag==-1){
					break;
				}else
				{
					System.out.println("Please enter between 10 - 15 characters, comprising of 1 special char, 1 digit and no spaces.");
					}
				}
		
		try {
		
		if(checkLoggedIn()!=false&&User.userlist.get(0).getId()!=null) {
			launchpad.menu();
		}else {
			whichMenu();
		}
		}catch(Exception e) {
			launchpad.menu();
		}
		
	}
	
	
	public static void Logout() {
		
		try{
			User.userlist.clear();
			clearSession();
		}catch(Exception e) {
		}
		
		whichMenu();
		
	}
		
		
 

	public static void whichMenu() {
		// TODO Auto-generated method stub
		
			
			if(User.userlist.get(0).getAdmin_flg()==true) {
				
				Admin admin = new Admin(User.userlist.get(0).getUsername(), User.userlist.get(0).getPassword());
				admin.Menu();
				
			}if(User.userlist.get(0).getAdmin_flg()==false)
			{
				User.userlist.get(0).Menu();
								
			}else {
				launchpad.menu();
			
			}
	
	}
	

	
	
	public static String readSession() {
    	
    	try {
    		FileReader fileReader = new FileReader("session.txt");
    		fileReader.close();
    		return fileReader.toString();
    		
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		return null;
    	}
    	
    }
	
    	    

    public static void clearSession() {
    	
    	File sessionfile = new File("session.txt");
		sessionfile.delete();
    }


	public static void writeSession(String session) {
		// TODO Auto-generated method stub
		
		PrintWriter writer;
		try {
			writer = new PrintWriter("session.txt", "UTF-8");
			writer.println(session);
	    	writer.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public static void returnMain() {
		// TODO Auto-generated method stub
		
		System.out.println("Please hit enter to return.");
		sc.nextLine();
		whichMenu();
		
		
	}
	
}
