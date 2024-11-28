package com.a00326288.project01;

import java.util.InputMismatchException;
import java.util.Scanner; 

 

public class launchpad  {
	
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		sc.useDelimiter("\r?\n");
		UserAccessControl.Logout();
		menu();	
		
	}

	
	public static void menu() {
        
		int exit =0;
		
        while(exit==0) {
        	
        try {
        	/***************************************************/
            
            System.out.println("---------------------------");
            System.out.println("-Main Menu -");
            System.out.println("---------------------------\n");
            System.out.println("1 - Login");
            System.out.println("2 - Register");
            System.out.println("3 - Quit");
        	
            
            
            Integer cursor = sc.nextInt();
          
            switch(cursor) {
      		  case 1:
      			  
      			UserAccessControl.Login();
      			UserAccessControl.whichMenu();
      			UserAccessControl.Logout();
      			break;
      			
      			  
      		  case 2:
      			
      			if(UserAccessControl.checkLoggedIn()==false) {
      				System.out.println("You are already logged in.");	
      			}else
      			{    
      			  UserAccessControl.Register(); 
      			}
      			break;
      			
      		  case 3:
      			  System.out.println("Goodbye");
      			  exit=1;
      			  break;
      		  default:
            	System.out.println("Please select a valid option");
            	break;
            }
            
        }catch(InputMismatchException e){
        	e.printStackTrace();
        	System.out.println("Please select a valid option");
        	sc.next();
        }
        
        }
  
        
    }
		
	
}
