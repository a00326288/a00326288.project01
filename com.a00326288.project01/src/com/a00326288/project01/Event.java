package com.a00326288.project01;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.InputMismatchException;

import java.util.Scanner;


public class Event {
	
	private Integer eventId;
	public String eventName;
	public String eventDescription;
 
	
	
	private static Scanner sc = new Scanner(System.in);

	
	public static void main(String[] args) {		
	
	}
 
	public Event(int eventId, String eventName, String eventDescription) {
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventDescription = eventDescription;
	 
	}	
 

	protected Integer getEventId() {
		return eventId;
	}



	private void setEventId(Integer eventId) {
		this.eventId = eventId;
	}



	public String getEventName() {
		return eventName;
	}



	private void setEventName(String eventName) {
		this.eventName = eventName;
	}



	public String getEventDescription() {
		return eventDescription;
	}



	private void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	
	public static void dbGetEvents() {
		// TODO Auto-generated method stub
		
		
		System.out.println("-----------------------------");
        System.out.println("- Event Listings -");
        System.out.println("-----------------------------\n");
        
        String SQL = ("SELECT * FROM events;");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		    statement.setQueryTimeout(30); 
  		  	ResultSet rs = statement.executeQuery(SQL);
  		  	
  		  	
  		  	
  		  	System.out.println(String.format("%-10s %-40s %20s" , "Event ID", "Event Name", "Event Description" ));
  		  	while(rs.next())
  		  	{
  		  		
  		  		Event event = new Event(rs.getInt("event_id"),rs.getString("event_name"),rs.getString("event_description"));
  		  		
  		  		System.out.format("%-10s %-40s %21s\n",event.getEventId(),event.getEventName(),event.getEventDescription());
  		  	}
  		  	
            connection.close();
          }
        catch(SQLException e)
        {
          e.printStackTrace(System.err);
        }
		System.out.println();
		
	}
	
	
	public static Event dbGetEvent(Integer eventId) {
		// TODO Auto-generated method stub
 
		Event event = new Event(0,null,null);
		
        String SQL = ("SELECT * FROM events where event_id="+eventId+";");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		    statement.setQueryTimeout(30); 
  		  	ResultSet rs = statement.executeQuery(SQL);
  		  	
  		  	
  		  	
  		  	System.out.println(String.format("%-10s %-40s" , "Event ID", "Event Name"));
  		  	while(rs.next())
  		  	{
  		  		
  		  		event.setEventId(rs.getInt("event_id"));
  		  		event.setEventName(rs.getString("event_name"));
  		  		
 
	  		
  		  		System.out.format("%-10s %-40s\n",event.getEventId(),event.getEventName());
  		  	
            connection.close();
            
  		  	}
          }
        catch(SQLException e)
        {
          e.printStackTrace(System.err);
        }
		System.out.println();
		return event;
		
	}
 
	
	
	public static void createEvent() {
		
		sc.useDelimiter("\r?\n");
		
		System.out.println("Input a name for the Event:");
		String eventname = sc.next();
		
		System.out.println("Input a description of the Event:");
		String eventDescription=sc.next();
		
		
		Event event = new Event(0,eventname,eventDescription);
		
		dbCreateEvent(event);
		 
		System.out.println("Event Created! Press enter to return to Main Menu.");
		
		UserAccessControl.whichMenu();
		
	}
	

	public static void createEventDate() {
		
		int eventId=0;
		int venueId=0;
		String eventDate=null;
		int  eventPrice=0;
		
		sc.useDelimiter("\r?\n");
		
		System.out.println("Specify Event Id:");
		
		while (true) {
			  try {
				eventId=sc.nextInt();
			    break;
			  } catch (InputMismatchException e ) {
				  sc.next();
				  System.out.println("Please enter valid number");
		
			  }
			}
		
		
		Venue.dbGetVenues();
		
		System.out.println("Specify a Venue ID:");
		
		while (true) {
			  try {
				venueId=sc.nextInt();
			    break;
			  } catch (InputMismatchException e ) {
				  sc.next();
				  System.out.println("Please enter valid number");
		
			  }
			}
		
		System.out.println("Specify a Date :");
		
		while (true) {
			  try {
				eventDate=sc.next();
			    break;
			  } catch (InputMismatchException e ) {
				  sc.next();
				  System.out.println("Please enter valid number");
		
			  }
			}
	
		System.out.println("Specify a Price:");
		
		while (true) {
			  try {
				eventPrice=sc.nextInt();
			    break;
			  } catch (InputMismatchException e ) {
				  sc.next();
				  System.out.println("Please enter valid number");
		
			  }
			}
		
		
		
		dbCreateEventDate(eventId, eventDate,venueId, eventPrice);
		 
		System.out.println("Event Created! Press enter to return to Main Menu.");
		
		UserAccessControl.whichMenu();
		
	}

	private static void dbCreateEventDate(int eventId, String eventDate, int venueId, int priceId) {
		// TODO Auto-generated method stub
		String SQL = ("INSERT INTO dates (event_date, event_id, venue_id, price_id) VALUES ("+eventId+",'"+eventDate+"',"+venueId+","+priceId+");");
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

	private static void dbCreateEvent(Event event) {
		
		String SQL = ("INSERT INTO events (event_name, event_description) VALUES ('"+event.getEventName()+"','"+event.getEventDescription()+"');");
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

	
	public static void viewEvents() {
		
		dbGetEvents();
		System.out.println("Above is the List of Events! Press enter to return to Main Menu.");
		UserAccessControl.returnMain();
		
	}

	public static void modifyEvent() {
		// TODO Auto-generated method stub		
		
		dbGetEvents();
		
		sc.useDelimiter("\r?\n");
		
		
		System.out.println("Enter the venue ID to Modify: ");
		
		int eventSelection = sc.nextInt();
		 
		Event event = dbGetEvent(eventSelection);
		
		
		
		System.out.println("Event ID: " + event.getEventId());
		System.out.println("Event Name: "+ event.getEventName());

		
		System.out.println();
		
		HashMap<Integer, String> eventmap = new HashMap<>();
		eventmap.put(1, "Event Name");
		eventmap.put(2, "Event Description");
 
		HashMap<Integer, String> eventmapUpdate = new HashMap<>();
		eventmapUpdate.put(1, event.getEventName());
		eventmapUpdate.put(2, event.getEventDescription());
 
		
		System.out.println("Which property do you want to modify?");
		
		
		for(int i=1;i<=eventmap.size();i++) {
			System.out.println(i+". " + eventmap.get(i));
			
		}
		
		int cursor = sc.nextInt();
		
		System.out.println("Please enter new value for "+eventmap.get(cursor)+":");
		
		String newval = sc.next();
		
		eventmapUpdate.put(cursor, newval);
		
		dbUpdateEvent(eventmapUpdate,eventSelection);
		
		System.out.println("Update Complete! Press enter to return to Main Menu.");
		
		UserAccessControl.returnMain();
		
	}

 
	
	private static void dbUpdateEvent(HashMap<Integer, String> eventmapUpdate,Integer eventSelection) {
		// TODO Auto-generated method stub
		
		
		String SQL = ("UPDATE events SET event_name ='"+eventmapUpdate.get(1)
				+ "', event_description='"+eventmapUpdate.get(2)+"' WHERE event_id="+eventSelection+";");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
  		  	Statement statement = connection.createStatement();
            statement.executeUpdate(SQL);
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
		
		
	}
	

	public static void deleteEvent() {
		// TODO Auto-generated method stub
		
		dbGetEvents();
		
		System.out.println("Please enter the ID of the event to delete:");
		
		int eventSelection = sc.nextInt();
		
		dbDeleteEvent(eventSelection);
		
		System.out.println("Delete Complete! Press enter to return to Main Menu.");
		
		UserAccessControl.returnMain();
		
	}
	

	private static void dbDeleteEvent(Integer eventSelection) {
		// TODO Auto-generated method stub
		
		
		String SQL = ("DELETE FROM events WHERE event_id="+eventSelection+";");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
  		  	Statement statement = connection.createStatement();
            statement.executeUpdate(SQL);
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
		
		
	}
	  
}
