package com.a00326288.project01;
import java.util.InputMismatchException;
 
import java.util.Scanner; 

public class launchpad  {
	
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
 
		Menu();
 
	}	
	 
	public static void Menu() {
		// TODO Auto-generated method stub
		
		sc.useDelimiter("\r?\n");
		
		var exit =0;
		
        while(exit==0) {
        	
        try {
            
            System.out.println("---------------------------");
            System.out.println("-Main Menu -");
            System.out.println("---------------------------\n");
            System.out.println("1 - Login");
            System.out.println("2 - Register");
            System.out.println("3 - Quit");
        	
            Integer cursor = sc.nextInt();
          
            switch(cursor) {
      		  case 1:     			  
      			UAM.Login();
      			break;
      		  case 2:
      			UAM.Register(); 
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
