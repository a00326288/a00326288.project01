package com.a00326288.project01;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public abstract class Dashboard implements UserMenuInterface{
 
	private Integer userId;
	private String username;
	private String userType;
	private UUID session;
	
	Dashboard(Integer userId, String username, String userType, UUID session){
		this.userId = userId;
		this.username = username;
		this.userType = userType;
		this.session = session;
	}
	
	
	protected Integer getUserID() {
		return userId;
	}
	protected String getUserName() {
		return username;
	}
	
	protected String getUserType() {
		return userType;
	}
	
	protected UUID getSession() {
		return session;
	}

	protected void setUserType(String userType) {
		this.userType = userType;
	}	
}

class UserDashboard extends Dashboard {
 
	private static Scanner sc = new Scanner(System.in);
	private int Menu_cursor=0;
 
 
 
	public UserDashboard(Integer userId, String username, String userType, UUID session) {
		super(userId, userType, userType, session);
 
	}
 
	private int getMenu_cursor() {
		return Menu_cursor;
	}

	private void setMenu_cursor(int menu_cursor) {
		Menu_cursor = menu_cursor;
	}
		
	@Override
	public void Menu() {
		
		sc.useDelimiter("\r?\n");
		 
			setMenu_cursor(0);
			
			while(getMenu_cursor()!=-1) {
				
				System.out.println("---------------------------");
		        System.out.println("-Choose from the following options -");
		        System.out.println("---------------------------\n");
		        System.out.println("1 - View Events & Bookings");
		        System.out.println("2 - My Profile");
		        System.out.println("3 - Edit Profile");
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
		     	    		System.out.println("View my profile.");
		     	    		System.out.println();
		     	    		UAM.viewProfile(getUserID(), getUserType());
		     	    		break;
		     	    	case 3:
		     	    		System.out.println("Edit my profile.");
		     	    		System.out.println();
		     	    		UAM.modifyUser(getUserID(), getUserType());
		     	    		break;
		     	    	case 4:
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
		
		
		
		private void menuSelector(Integer selector, String type) {	
			 
			if(type =="Conferences") {
			 
				if(selector ==1) {
					int selection = Event.menu(type, getUserType());
					Event myConference = new Conferences();
					if(selection==1) {
						myConference.list();
					}else if(selection==2) {
						myConference.listDates();
						}else if(selection==3) {
							Bookings.makeBooking(getUserID(), getUserName(), getUserType(), getSession(), type);
						}else if(selection==4) {
							Bookings.viewBookings(getUserID(),type,getUserType());
						} 
						
				}

			}
			else if(type =="Concerts") {
				Event concert = new Concerts();
				if(selector ==2) {
					int selection = Event.menu(type, getUserType());
					if(selection==1) {
						concert.list();
					}else if(selection==2) {
						concert.listDates();
					}else if(selection==3) {
						Bookings.makeBooking(getUserID(), getUserName(), getUserType(), getSession(), type);
					}else if(selection==4) {
						Bookings.viewBookings(getUserID(),type,getUserType());
					} 
				}
			}	
		}

 
}

class AdminDashboard extends Dashboard {

	private static Scanner sc = new Scanner(System.in);
	private Integer menu_cursor;
 
	public AdminDashboard(Integer userId, String username, String userType, UUID session) {
		super(userId,username,userType,session);
 
	}
 
	private Integer getMenu_cursor() {
		return menu_cursor;
	}

	
	private void setMenu_cursor(Integer menu_cursor) {
		this.menu_cursor = menu_cursor;
	}

	
		 
	@Override
	public void Menu() {

		sc.useDelimiter("\r?\n");
		
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
	     	    	case 5:
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
	
	
	private void menuSelector(Integer selector, String type) {	
		
		if(type=="Venues") {
			if(selector ==1) {
			Venue.createVenue();
			}else if(selector ==2) {
			Venue.viewVenues();	
			}else if (selector==3) {
			Venue.modifyVenue();
			}else if(selector ==4) {
			Venue.deleteVenue();	
			} 
	
		}else if(type =="Users") {
			if(selector ==2) {
			UAM.viewProfile(getUserID(), getUserType());	
			}else if(selector ==3) {
			UAM.modifyUser(getUserID(), getUserType());
			}else if(selector ==4) {
			UAM.deleteUser(getUserID(), getUserType());
			}
			
		}
		
		
		
		else if(type =="Prices") {
			if(selector ==1) {
			Price.addPrice();
			}else if(selector ==2) {
			Price.viewPrices();	
			}else if(selector ==3) {
			Price.deletePrice();
			}
			
		}else if(type =="Conferences") {
			Event conference = new Conferences();
			if(selector ==1) {
				int selection = Event.menu(type,getUserType());
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
					Bookings.makeBooking(getUserID(), getUserName(), getUserType(), getSession(), type);
				}else if(selection==9) {
					Bookings.viewBookings(getUserID(),type,getUserType());
				}else if (selection==10) {
					Bookings.cancelBooking(getUserID(),type,getUserType());	
				}
				
			}

		}else if(type =="Concerts") {
			Event concert = new Concerts();
			if(selector ==2) {
				int selection = Event.menu(type,getUserType());
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
				}else if(selection==8){
				Bookings.makeBooking(getUserID(), getUserName(), getUserType(), getSession(),type);
				}else if(selection ==9) {
				Bookings.viewBookings(getUserID(),type,getUserType());
				}else if (selection==10) {
				Bookings.cancelBooking(getUserID(),type,getUserType());	
			}
		}
		}
	}
}