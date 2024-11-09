package com.a00326288.project01;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;



public class Events {

	//public String username = User.getUsername();
		
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {	
		
		displayEvents();
		menu();
	}


	private Integer eventId;
	private String eventName;
	private String eventDescription;
	private String eventStartDate;
	private String eventEndDate;	
	private Integer eventPrice;	
	
	public Events() {
		
		Integer eventId = 0;
		String eventName = null; 
		String eventDescription = null;
		String eventStartDate = null;
		String eventEndDate = null;
		Integer eventPrice = 0;
		 
	}
	
	public Integer getEventId(Integer eventId) {
		return eventId;		
	}
	
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	
	public String getEventName(String eventName) {
		return eventName;		
	}
	
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	public String getEventDescription(String eventDescription) {
		return eventDescription;		
	}
	
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	
	public String getEventStartDate() {
		return eventStartDate;
	}

	public void setEventStartDate(String eventStartDate) {
		this.eventStartDate = eventStartDate;
	}

	public String getEventEndDate() {
		return eventEndDate;
	}

	public void setEventEndDate(String eventEndDate) {
		this.eventEndDate = eventEndDate;
	}

	public Integer getEventPrice() {
		return eventPrice;
	}

	public void setEventPrice(Integer eventPrice) {
		this.eventPrice = eventPrice;
	}
	
	
	public static void displayEvents() {
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
	
		
	
	private void createEvent() {
		
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
        
 
		switch(selection) {
		  case 1:
			  if(User.readSession()!=null && !User.readSession().isEmpty() ) {
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
		
		int option = sc.nextInt();
		
		
		
		
	
	}


	private static void viewEvent() {
		// TODO Auto-generated method stub
		System.out.println("Please input event ID you wish to view");
		
	}
}

