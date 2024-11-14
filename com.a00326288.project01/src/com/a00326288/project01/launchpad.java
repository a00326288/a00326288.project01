package com.a00326288.project01;

import java.util.InputMismatchException;
import java.util.Scanner; 

 

public class launchpad  {

	
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		User.clearSession();
		menu();
	
		
	}
 
	
	public static void menu() {

        int selection = 0;
        
        
        
        
        while(true) {
        	
        try {
        	/***************************************************/
            
            System.out.println("---------------------------");
            System.out.println("-Main Menu -");
            System.out.println("---------------------------\n");
            System.out.println("1 - Login");
            System.out.println("2 - Register");
            System.out.println("3 - Quit");
        	
            	selection = sc.nextInt();
          
            	switch(selection) {
      		  case 1:
      			  UserAccessControl.Login();
      		    break;
      		  case 2:
      			  UserAccessControl.Register(); 
      		  case 3: 
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
