package com.a00326288.project01;

import java.util.Scanner; 

public class launchpad {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
	
		int ScannerValue = start();
		System.out.println(ScannerValue );
		
		switch(ScannerValue) {
		  case 1:
			  UserAccessControl.Login();
		    break;
		  case 2:
		      UserAccessControl.Register();
		    break;
		  case 3:
			  Events.ViewListings();
			  break;
		  case 4:
			 break;
		  case 5:
		  default:
		    // code block
		}
		
		
	}
 
	
	public static int start() {

        int selection;
        
        /***************************************************/
        
        System.out.println("---------------------------");
        System.out.println("-Choose from these choices-");
        System.out.println("---------------------------\n");
        System.out.println("1 - Login");
        System.out.println("2 - Register");
        System.out.println("3 - View Listings");
        System.out.println("4 - Quit");

        selection = sc.nextInt();
        return selection;    
        
        
    }
}
