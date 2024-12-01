package com.a00326288.project01;

import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UAM {

	private static Scanner sc = new Scanner(System.in);
	static Console cnsl = System.console();
	private static String username;
	private static String password;
	
	
	public static void Main(String[] args) {
	if (cnsl == null) {
        System.out.println("No console available");
        System.exit(0);
    }
	}

	
	static void Login() {
		
		 		
		try {
			
			
			//Get the Username and Password from Scanner
			username = cnsl.readLine( 
					"Enter username : ");
			
			char[] consolePass = cnsl.readPassword( 
					"Enter password : ");
			
			password = String.valueOf(consolePass);
			
			String user = DBA.dbCheckUser(username,password); 
			
	
			if(user!=null) {
				
				System.out.println("---------------------------");
	            System.out.println("-Hello " +user + "        -");
	            System.out.println("---------------------------\n");
					
	        boolean checkPermissions = DBA.dbCheckUserPermission(username, password)   ; 
	          
			whichMenu(checkPermissions);
					
					
			}else {
					System.out.println("Invalid details or User does not Exists");
 
			}
			
			
		}catch(Exception e){
			System.out.println("Invalid Username or Password.");
			e.printStackTrace();	
		}
	}
	
	
	static void Register() {
		

		// TODO Auto-generated method stub
		System.out.println("Register");
				
		String regUsername ="";
		
		
		while(true) {
			  try {
				  System.out.println("Enter a username:");
				  regUsername = sc.next();
				  if(validateUsername(regUsername)==false) {;	
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
					DBA.dbCreateUser(regUsername, password);
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
		
	}
	
	private static Boolean validateUsername(String username) {
		  
		
			if(DBA.dbCheckUser(username)==true) 
			{
			System.out.println("Username " + username + " is already taken. Please try again.");
			return true;
			}
			else{
			System.out.println("Username "+ username + " is accepted.");
			return false;
			}
 
		
	}
		
	
	
	private static void whichMenu(boolean checkPermissions) {
		// TODO Auto-generated method stub
		
			try {
				
			
			if(checkPermissions==true) {
				
				Admin admin = new Admin();
				admin.Menu();					
			}else {
				User user = new User();
				user.Menu();
			}
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("Cannot determine user.");
			}
	}

	
    
	
	public static void returnMain() {
		// TODO Auto-generated method stub
		
		System.out.println();
		System.out.println("Please hit enter to continue.");
		sc.nextLine();
		
		
	}
    
    
		
	
}
