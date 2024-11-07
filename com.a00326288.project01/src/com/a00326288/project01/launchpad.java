package com.a00326288.project01;

import java.util.Scanner; 

public class launchpad {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		menu();
	
		
	}
 
	
	public static void menu() {

        int selection;
        
        /***************************************************/
        
        System.out.println("---------------------------");
        System.out.println("-Choose from these choices-");
        System.out.println("---------------------------\n");
        System.out.println("1 - Login");
        System.out.println("2 - Register");
        System.out.println("3 - View Events");
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
	
}
