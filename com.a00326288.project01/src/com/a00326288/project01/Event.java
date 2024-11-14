package com.a00326288.project01;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Event {
	
	private Integer eventId;
	private String eventName;
	private String eventDescription;
	private String eventStartDate;
	private String eventEndDate;
	
	
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {	
		sc.useDelimiter("\r?\n");
		displayEvents();
		
		
		
		
	}
	
	public Event(int eventId, String eventName, String eventDescription, String eventStartDate, String eventEndDate) {
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventDescription = eventDescription;
		this.eventStartDate =  eventStartDate;
		this.eventEndDate = eventEndDate;
		
		
	}
	

	
	
	public Integer getEventId() {
		return eventId;
	}



	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}



	public String getEventName() {
		return eventName;
	}



	public void setEventName(String eventName) {
		this.eventName = eventName;
	}



	public String getEventDescription() {
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


	
	public static void displayEvents() {
		// TODO Auto-generated method stub
		
		
		System.out.println("-----------------------------");
        System.out.println("- Current Event Listings -");
        System.out.println("-----------------------------\n");
        
        String SQL = ("SELECT * FROM events;");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		    statement.setQueryTimeout(30); 
  		  	ResultSet rs = statement.executeQuery(SQL);
  		  	
  		  	
  		  	
  		  	System.out.println(String.format("%-10s %-40s %8s %20s" , "Event ID", "Event Name", "Event Start", "Event Ends" ));
  		  	while(rs.next())
  		  	{
  		  		
  		  		Event event = new Event(rs.getInt("event_id"),rs.getString("event_name"),rs.getString("event_description"),rs.getString("event_start_date"),rs.getString("event_end_date"));
  		  		
  		  		System.out.format("%-10s %-40s %8s %21s\n",event.getEventId(),event.getEventName(),event.getEventStartDate(),event.getEventEndDate());
  		  	}
  		  	
            connection.close();
          }
        catch(SQLException e)
        {
          e.printStackTrace(System.err);
        }
		System.out.println();
		
		UserAccessControl.whichMenu();
	}
	
		
	
	public static void createEvent() {
		
		System.out.println("Input a name for the Event:");
		String eventname = sc.next();
		
		System.out.println("Input a description of the Event:");
		String eventDescription=sc.next();
		
		System.out.println("Enter a date whenever the event starts:");
		String eventStartDate = sc.next();

		System.out.println("Enter a date for whenever the event finishes:");
		String eventEndDate=sc.next();

		Venue.getVenues();
		
		System.out.println("Specify a venue:");
		
		while (true) {
			  try {
				int venueId=sc.nextInt();
			    break;
			  } catch (InputMismatchException e ) {
				  sc.next();
				  System.out.println("Please enter valid number");
		
			  }
			}
	
		System.out.println("Specify a Price:");
		int price=sc.nextInt();
		
		Event event = new Event(0,eventname,eventDescription,eventStartDate,eventEndDate);
		
 
		//Prices price = new Prices(price, venueId);
		
		dbCreateEvent(event);
		
		UserAccessControl.whichMenu();
		
	}
	
	private static void dbCreateEvent(Event event) {
		
		String SQL = ("INSERT INTO events (event_name, event_description, event_start_date, event_end_date) VALUES ('"+event.getEventName()+"','"+event.getEventDescription()+"','"+event.getEventStartDate()+"','"+event.getEventEndDate()+"');");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
  		  	Statement statement = connection.createStatement();
            statement.executeUpdate(SQL);
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }   
        //User.Menu();	
	}

	


	public static void viewEvent() {
		// TODO Auto-generated method stub
		System.out.println("Please input the ID of the event you wish to view");
		
		int selection = sc.nextInt();
		
		EventDetails.EventVenueDetails(selection);
		
	}

	public static void myBooking() {
		// TODO Auto-generated method stub
		
	}

	public static void modifyEvent() {
		// TODO Auto-generated method stub
		
	}
	

     
}

