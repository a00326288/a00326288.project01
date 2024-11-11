package com.a00326288.project01;

import java.util.Scanner; 

public class launchpad {
	
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		User.clearSession();
		
		menu();
	
		
	}
 
	
	public static void menu() {

        int selection;
        
     
        
        
        
        /***************************************************/
        
        System.out.println("---------------------------");
        System.out.println("-Main Menu -");
        System.out.println("---------------------------\n");
        
        
        
        System.out.println("1 - Login");
        System.out.println("2 - Register");
        System.out.println("3 - View Events");
        System.out.println("4 - Quit");
        
        selection = sc.nextInt();
        
        switch(selection) {
		  case 1:
			  UserAccessControl.Login();
		    break;
		  case 2:
			  UserAccessControl.Register(); 
		    break;
		  case 3:
			  Events.main(null);
			  break;
		  case 4:
			 break;
		  case 5:
		  default:
		    // code block
		}
        
        
    }
		
	public static void user_menu_events () {
		
		
        int selection = 0;
        
        System.out.println("---------------------------");
        System.out.println("-Choose from the following options -");
        System.out.println("---------------------------\n");
        System.out.println("1 - Book Event");
        System.out.println("2 - View Event"); 
        System.out.println("3 - Create Event");
        System.out.println("4 - Main Menu");

        selection = sc.nextInt();
 
		switch(selection) {
		  case 1:
			  if(User.readSession()!=null ) {
				  Events.bookEvent();
			  }else {
				  System.out.println("Please login/register before trying to book.");
				  launchpad.menu();
			  }
		    break;
		  case 2:
			  Events.viewEvent();
		    break;
		  case 3:
			  Events.createEvent();
			break;
		  case 4:
			  launchpad.menu();
			break;
		  default:
		    // code block
		}
        
        
    }
	
}
