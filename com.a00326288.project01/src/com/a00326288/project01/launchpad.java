package com.a00326288.project01;

import java.util.InputMismatchException;
import java.util.Scanner; 

 

public class launchpad  {
	
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		sc.useDelimiter("\r?\n");
		UserAccessControl.clearSession();
		menu();
		
	}

	
	
	
	public static void menu() {
        
        while(true) {
        	
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
      		    break;
      		  case 2:
      			  UserAccessControl.Register(); 
      		  case 3:
      			  System.out.println("Goodbye");
      			  break;
      		  default:
            	System.out.println("Please select a valid option");
            }
        break;
        }catch(InputMismatchException e){
        	e.printStackTrace();
        	System.out.println("Please select a valid option");
        	sc.next();
        }
        	
        }
        
        
  
        
    }
		
	
}
