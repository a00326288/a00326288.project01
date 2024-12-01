package com.a00326288.project01;

import java.util.InputMismatchException; 
import java.util.Scanner;
 

public class Admin {

	public Admin() {
		// TODO Auto-generated constructor stub
	}
	
	
	private Integer menu_cursor;
	 
	private static Scanner sc = new Scanner(System.in);
	

	private Integer getMenu_cursor() {
		return menu_cursor;
	}

	
	private void setMenu_cursor(Integer menu_cursor) {
		this.menu_cursor = menu_cursor;
	}

	
	public static void main(String[] args) {	
			
		
	}
 
	
	private static void menuSelector(Integer selector, String type) {	
		
		if(type=="Events"){
			Event event = new Event();
			if(selector ==1) {
			event.createEvent();
			}else if(selector ==2) {
			event.createEventDate();	
			}else if (selector==3) {
			event.viewEvents();
			}else if(selector ==4) {
			event.modifyEvent();	
			}else if (selector==5) {
			event.deleteEvent();
			}else if (selector==5) {
			event.deleteEventDate();	
			}
			
		}else if(type=="Venues") {
			Venue venue = new Venue();
			if(selector ==1) {
			venue.createVenue();
			}else if(selector ==2) {
			venue.viewVenues();	
			}else if (selector==3) {
			venue.modifyVenue();
			}else if(selector ==4) {
			venue.deleteVenue();	
			} 
	
		}else if(type=="Users") {
			User user = new User();
			if(selector ==2) {
			user.viewUserProfile();
			}else if(selector ==3) {
			user.modifyUserProfile();	
			}else if(selector ==4) {
			user.deleteUserProfile();	
			}
			
		}else if(type =="Prices") {
			Price price = new Price();
			
			if(selector ==1) {
			price.addPrice();
			}else if(selector ==2) {
			price.viewPrices();	
			}else if(selector ==3) {
			price.modifyPrice();	
			}else if(selector ==4) {
			price.deletePrice();	
			}
		}else if(type =="Bookings") {
			Booking booking = new Booking();
			if(selector ==1) {
			booking.createBooking();
			}else if(selector ==2) {
			booking.viewBookings();	
			}else if (selector==3) {
			booking.cancelBooking();
			}
			
		}
		

	}
	
	
	public void Menu() {

		setMenu_cursor(0);
	
		while(getMenu_cursor()!=-1) {
			
			System.out.println("---------------------------");
	 	    System.out.println("-Choose from the following options -");
	 	    System.out.println("---------------------------\n");
	 	    System.out.println("1 - Manage Events");
	 	    System.out.println("2 - Manage Bookings");
	 	    System.out.println("3 - Manage Venues"); 
	 	    System.out.println("4 - Manage Users"); 
	 	    System.out.println("5 - Manage Prices");
	 	    System.out.println("6 - Log Out");
			
			try {     
	     	    setMenu_cursor(sc.nextInt());
	     	    
	     	    switch(getMenu_cursor()) {  
	     	    	case 1:
	     	    		
	     	    		System.out.println("---------------------------");
	     	    		System.out.println("-Choose from the following options -");
	     	    		System.out.println("---------------------------\n");
	     	    		System.out.println("1 - Add an Event");
	     	    		System.out.println("2 - Add Event Dates");
	     	    		System.out.println("3 - View Event and Dates");
	     	    		System.out.println("4 - Modify an Event");
	     	    		System.out.println("5 - Delete an Event");
	     	    		System.out.println("6 - Delete Event Dates");
	     	    		System.out.println("7 - Main Menu");
	     	     
	     	    		menu_cursor = sc.nextInt();
	     	    		switch(menu_cursor) {
	     	    			case 1:
	     	    				menuSelector(getMenu_cursor(),"Events");
	     	    				break;
	     	    			case 2:
	     	    				menuSelector(getMenu_cursor(),"Events");
	     	    				break;
	     	    			case 3:
	     	    				menuSelector(getMenu_cursor(),"Events");
	     	    				break;
	     	    			case 4:
	     	    				menuSelector(getMenu_cursor(),"Events");
	     	    				break;
	     	    			case 5:
	     	    				menuSelector(getMenu_cursor(),"Events");
	     	    				break;
	     	    			case 6:
	     	    				menuSelector(getMenu_cursor(),"Events");
	     	    				break;
	     	    			case 7:
	     	    				break;
	     	    			default:
	     	    				System.out.println("Input a valid option.");
	     	    		}
	     	    		
	     	    		
	     	    		break;
	     	    	case 2:
		
	     	    		System.out.println("---------------------------");
	     	    		System.out.println("-Choose from the following options -");
	     	    		System.out.println("---------------------------\n");
	     	    		System.out.println("1 - Make a Booking");
	     	    		System.out.println("2 - View Bookings");
	     	    		System.out.println("3 - Cancel a Booking");
	     	    		System.out.println("4 - Main Menu");
	     	      
	     	    		setMenu_cursor(sc.nextInt());
	     	    		switch(getMenu_cursor()) {
	     	    			case 1:
	     	    				menuSelector(getMenu_cursor(),"Bookings");
	     	    				break;
	     	    			case 2:
	     	    				menuSelector(getMenu_cursor(),"Bookings");
	     	    				break;
	     	    			case 3:;
	     	    				menuSelector(getMenu_cursor(),"Bookings");;
	     	    				break;
	     	    			case 4:
	     	    				break;
	     	    			default:
	     	    				System.out.println("Input a valid option.");
	     	    		}
	     	    		break;
	     	    	case 3:
				
	     	    		System.out.println("---------------------------");
	     	    		System.out.println("-Choose from the following options -");
	     	    		System.out.println("---------------------------\n");
	     	    		System.out.println("1 - Add a Venue");
	     	    		System.out.println("2 - View Venues");
	     	    		System.out.println("3 - Modify a Venue");
	     	    		System.out.println("4 - Remove a Venue");
	     	    		System.out.println("5 - Main Menu");
	     	      
	     	    		setMenu_cursor(sc.nextInt());
	     	    		switch(getMenu_cursor()) {
	     	    			case 1:
	     	    				menuSelector(getMenu_cursor(),"Venues");
	     	    				break;
	     	    			case 2:
	     	    				menuSelector(getMenu_cursor(),"Venues");
	     	    				break;
	     	    			case 3:
	     	    				menuSelector(getMenu_cursor(),"Venues");
	     	    				break;
	     	    			case 4:
	     	    				menuSelector(getMenu_cursor(),"Venues");
	     	    				break;
	     	    			case 5:
	     	    				break;
	     	    			default:
	     	    				System.out.println("Input a valid option.");
	     	    		}
	     	    		break;
	     	    	case 4:
				  
	     	    		System.out.println("---------------------------");
	     	    		System.out.println("-Choose from the following options -");
	     	    		System.out.println("---------------------------\n");
	     	    		System.out.println("1 - Create a User");
	     	    		System.out.println("2 - View Users");
	     	    		System.out.println("3 - Modify a User");
	     	    		System.out.println("4 - Delete a User");
	     	    		System.out.println("5 - Main Menu");
	     	      
	     	    		setMenu_cursor(sc.nextInt());
	     	    		switch(getMenu_cursor()) {
	     	    			case 1:
	     	    				UAM.Register();
	     	    				break;
	     	    			case 2:
	     	    				menuSelector(getMenu_cursor(),"Users");
	     	    				break;
	     	    			case 3:
	     	    				menuSelector(getMenu_cursor(),"Users");
	     	    				break;
	     	    			case 4:
	     	    				menuSelector(getMenu_cursor(),"Users");
	     	    				break;
	     	    			case 5:
	     	    				break;
	     	    			default:
	     	    				System.out.println("Input a valid option.");
	     	    			}
	     	    		break;
	     	    	case 5:
	     	    		System.out.println("---------------------------");
	     	    		System.out.println("-Choose from the following options -");
	     	    		System.out.println("---------------------------\n");
	     	    		System.out.println("1 - Add a Price");
	     	    		System.out.println("2 - View Prices");
	     	    		System.out.println("3 - Update a Price");
	     	    		System.out.println("4 - Delete a Price");
	     	    		System.out.println("5 - Main Menu");
	     	      
	     	    		setMenu_cursor(sc.nextInt());
	     	    		switch(getMenu_cursor()) {
	     	    			case 1:
	     	    				menuSelector(getMenu_cursor(),"Prices");
	     	    				break;
	     	    			case 2:
	     	    				menuSelector(getMenu_cursor(),"Prices");
	     	    				break;
	     	    			case 3:
	     	    				menuSelector(getMenu_cursor(),"Prices");
	     	    				break;
	     	    			case 4:
	     	    				menuSelector(getMenu_cursor(),"Prices");
	     	    				break;
	     	    			case 5:
	     	    				break;
	     	    			default:
	     	    				System.out.println("Input a valid option.");
	     	    			}
	     	    		break;
	     	    	case 6:
	     	    		setMenu_cursor(-1);
	     	    		System.out.println("You have logged out.");
	     	    		break;
	     	    	default:
	     	    		System.out.println("Please select a valid option");
	     	    		break;
	     	    }	        
	       }catch(InputMismatchException e) {
	    	   e.printStackTrace();
	    	   System.out.println("Please select a valid option");
	    	   sc.next();
	       }
		}
	}
	
}
