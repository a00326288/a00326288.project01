package com.a00326288.project01;

import com.a00326288.project01.User;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Scanner;



public class Events {

	//public String username = User.getUsername();
	static String username = "";
	
	int	EventID;
	String EventName; 
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {	
		
		Events();
		menu();
	}

	
	
	public static void Events() {
		// TODO Auto-generated method stub
		
		
		String SQL = "SELECT * FROM events;";
		
		System.out.println("-----------------------------");
        System.out.println("- Current Event Listings -");
        System.out.println("-----------------------------\n");

		
		
		
		List<Map<String, Object>> eventlist = new ArrayList<Map<String, Object>>();
		
		eventlist.addAll(DBA.dbConnection(SQL));
	
		
		for(int i=0; i < eventlist.size(); i++ )
 		 {
			System.out.println(eventlist.get(i));
		
		}
		
		System.out.println();

	}
	
		
	
	private void createEvents() {
		
		System.out.println("Input name for Event:");
		String event_name = sc.next();
	
		
		
	}
	
	public static void updateEvent() {
		
		
	}
	
	public static void menu() {

        int selection;
        
        /***************************************************/
        
        System.out.println("---------------------------");
        System.out.println("-Choose from the choices below -");
        System.out.println("---------------------------\n");
        System.out.println("1 - Book Event");
        System.out.println("2 - View Event");
        System.out.println("3 - Main Menu");

        selection = sc.nextInt();
        
       // System.out.println(username);
 
		switch(selection) {
		  case 1:
			  if(username !=null && !username.isEmpty() ) {
				  bookEvent();
			  }else {
				  System.out.println("Please login/register first before trying to book.");
				  launchpad.menu();
			  }
		    break;
		  case 2:
			  viewEvent();
		    break;
		  case 3:
			  launchpad.menu();
			  break;
		  default:
		    // code block
		}
        
        
    }
	


	private static void bookEvent() {
		// TODO Auto-generated method stub
		System.out.println("-----------------------------");
        System.out.println("- Book Event -");
        System.out.println("-----------------------------\n");
        
		System.out.println("Please input event ID you wish to book:");
		System.out.println();
		
		
	
	}


	private static void viewEvent() {
		// TODO Auto-generated method stub
		System.out.println("Please input event ID you wish to view");
		
	}
}

