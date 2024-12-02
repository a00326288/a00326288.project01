package com.a00326288.project01;

import java.util.InputMismatchException; 
import java.util.Scanner;
 

public class Admin extends Person implements UserMenuInterface{

	private static Integer user_id;
	private static String username;
	private static String password;
	private static Integer acc_type;
	
	
	public static void main(String[] args) {	
		  
		 
	}
	
	Admin() {
		super();
		super.user_id = getUser_id();
		super.username = username;
		super.password = password;
		super.acc_type = acc_type;
	}
	
	
	private Integer menu_cursor;

	final private String usertype = "Admin";
	private static Scanner sc = new Scanner(System.in);
	

	private Integer getMenu_cursor() {
		return menu_cursor;
	}

	
	private void setMenu_cursor(Integer menu_cursor) {
		this.menu_cursor = menu_cursor;
	}

	
	public static Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		Admin.user_id = user_id;
	}

	
	private void menuSelector(Integer selector, String type) {	
		
		
 
	 
		
		if(type=="Venues") {
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
			
		}else if(type =="Prices") {
			Price price = new Price();
			if(selector ==1) {
			price.addPrice();
			}else if(selector ==2) {
			price.viewPrices();	
			}else if(selector ==3) {
			price.deletePrice();
			}
			
		}else if(type =="Conferences") {
			Conferences conference = new Conferences();
			if(selector ==1) {
				int selection = AbstractEvent.menu(type,usertype);
				if(selection==1) {
					conference.list();
				}else if(selection==2) {
					conference.create();
				}else if(selection==3) {
					conference.edit();
				}else if(selection==4) {
					conference.delete();
				}else if(selection==5) {
					conference.listDates();
				}else if(selection==6) {
					conference.addDate();
				}else if(selection==7) {
					conference.removeDate();
				}else if(selection==8) {
					Booking booking = new Booking();
					booking.createBooking(getUser_id(),"Conferences");
				}else if(selector ==9) {
					Booking booking = new Booking();
					booking.viewBookings(getUser_id(),"Conferences");	
				}else if (selector==10) {
					Booking booking = new Booking();
					booking.cancelBooking(getUser_id(),"Conferences");
				}
				
			}

		}else if(type =="Concerts") {
			Concerts concert = new Concerts();
			if(selector ==2) {
				int selection = AbstractEvent.menu(type,usertype);
				if(selection==1) {
					concert.list();
				}else if(selection==2) {
					concert.create();
				}else if(selection==3) {
					concert.edit();
				}else if(selection==4) {
					concert.delete();
				}else if(selection==5) {
					concert.listDates();
				}else if(selection==6) {
					concert.addDate();
				}else if(selection==7) {
					concert.removeDate();
				}else if(selection==8);
				}
				Booking booking = new Booking();
				booking.createBooking(getUser_id(),"Concerts");
				}else if(selector ==9) {
				Booking booking = new Booking();
				booking.viewBookings(getUser_id(),"Concerts");	
				}else if (selector==10) {
				Booking booking = new Booking();
				booking.cancelBooking(getUser_id(),"Concerts");
				}
	}
									
	 
	
	@Override
	public void Menu() {
		
		

		setMenu_cursor(0);
	
		while(getMenu_cursor()!=-1) {
			
			System.out.println("---------------------------");
	 	    System.out.println("-Choose from the following options -");
	 	    System.out.println("---------------------------\n");
	 	    System.out.println("1 - Manage Events & Bookings");
	 	    System.out.println("2 - Manage Venues"); 
	 	    System.out.println("3 - Manage Users"); 
	 	    System.out.println("4 - Manage Prices");
	 	    System.out.println("5 - Log Out");
			
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
	     	    	case 3:
				  
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
	     	    	case 4:
	     	    		System.out.println("---------------------------");
	     	    		System.out.println("-Choose from the following options -");
	     	    		System.out.println("---------------------------\n");
	     	    		System.out.println("1 - Add a Price");
	     	    		System.out.println("2 - View Prices");
	     	    		System.out.println("3 - Delete a Price");
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
