package com.a00326288.project01;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Customer extends Person implements UserMenuInterface {

	private static Scanner sc = new Scanner(System.in);
	private Integer user_id;
	private String username;
	private String password;
	private String customerFirstName;
	private String customerSurname;
	private String customerAddress;
	private String eventType;
	final private String usertype = "Customer";

	
	private Integer menu_cursor;
  
	private Integer getMenu_cursor() {
		return menu_cursor;
	}

	
	private void setMenu_cursor(Integer menu_cursor) {
		this.menu_cursor = menu_cursor;
	}

	
	public Customer(Integer user_id, String username, String password, String customerFirstName, String customerSurname, String customerAddress) {
		// TODO Auto-generated constructor stub
	super();
	super.user_id = user_id;
	super.username = username;
	super.password = password;
	this.customerFirstName = customerFirstName;
	this.customerSurname = customerSurname;
	this.customerAddress = customerAddress;
	
	}
 
 

	public Customer() {
		// TODO Auto-generated constructor stub
	}


	private void menuSelector(Integer selector, String type) {	
 
		if(type =="Conferences") {
			Conferences conference = new Conferences();
			if(selector ==1) {
				int selection = AbstractEvent.menu(type, usertype);
				if(selection==1) {
					conference.list();
				}else if(selection==2) {
					conference.listDates();
					}else if(selection==3) {
						Booking booking = new Booking();
						booking.createBooking(getUser_id(), "Conferences");
					}else if(selection==4) {
						Booking booking = new Booking();
						booking.viewBookings(getUser_id(),"Conferences");
					}else if(selection==5) {
						Booking booking = new Booking();
						booking.cancelBooking(getUser_id(),"Conferences");	
					}
					
			}

		}
		else if(type =="Concerts") {
			Concerts concert = new Concerts();
			if(selector ==2) {
				int selection = AbstractEvent.menu(type, usertype);
				if(selection==1) {
					concert.list();
				}else if(selection==2) {
					concert.listDates();
				}else if(selection==3) {
					Booking booking = new Booking();
					booking.createBooking(getUser_id(), "Concerts");
				}else if(selection==4) {
					Booking booking = new Booking();
					booking.viewBookings(getUser_id(),"Concerts");
				}else if(selection==5) {
					Booking booking = new Booking();
					booking.cancelBooking(getUser_id(),"Concerts");	
				}
			}
		}	

	}
 
	@Override
	public void Menu() {

		setMenu_cursor(0);
	
		while(getMenu_cursor()!=-1) {
			
			System.out.println("---------------------------");
	        System.out.println("-Choose from the following options -");
	        System.out.println("---------------------------\n");
	        System.out.println("1 - View Events");
	        System.out.println("2 - Bookings"); 
	        System.out.println("3 - My Profile"); 
	        System.out.println("4 - Log Out");
			
			try {     
	     	    setMenu_cursor(sc.nextInt());
	     	    
	     	    switch(getMenu_cursor()) {  
	     	    	case 1:
	     	    		System.out.println("---------------------------");
	     	    		System.out.println("-Choose from the following options -");
	     	    		System.out.println("---------------------------\n");
	     	    		System.out.println("1 - Conferences");
	     	    		System.out.println("2 - Concerts");
	     	    		System.out.println("3 - Main Menu"); 
	     	     
	     	    		setMenu_cursor(sc.nextInt());
	     	    		switch(getMenu_cursor()) {
	     	    			case 1:
	     	    				menuSelector(getMenu_cursor(),"Conferences");
	     	    				break;
	     	    			case 2:
	     	    				menuSelector(getMenu_cursor(),"Concerts");
	     	    				break;
	     	    			case 3:
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
	     	    		System.out.println("3 - Main Menu");
	     	      
	     	    		setMenu_cursor(sc.nextInt());
	     	    		switch(getMenu_cursor()) {
	     	    			case 1:
	     	    				menuSelector(getMenu_cursor(),"Bookings");
	     	    				break;
	     	    			case 2:
	     	    				menuSelector(getMenu_cursor(),"Bookings");
	     	    				break;
	     	    			case 3:
	     	    				break;
	     	    			default:
	     	    				System.out.println("Input a valid option.");
	     	    		}
	     	    		break;
	     	    	case 3:
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


	public Integer getUser_id() {
		return user_id;
	}


	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}


 
}
