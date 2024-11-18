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
	private String eventName;
	private String eventDescription;
	private String eventStartDate;
	private String eventEndDate;
	
	
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {		
		
		
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


	
	public static void dbGetEvents() {
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
		
	}
	
	
	public static Event dbGetEvent(Integer eventId) {
		// TODO Auto-generated method stub
 
		Event event = new Event(0,null,null,null, null);
		
        String SQL = ("SELECT * FROM events where event_id="+eventId+";");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		    statement.setQueryTimeout(30); 
  		  	ResultSet rs = statement.executeQuery(SQL);
  		  	
  		  	
  		  	
  		  	System.out.println(String.format("%-10s %-40s %8s %20s" , "Event ID", "Event Name", "Event Start", "Event Ends" ));
  		  	while(rs.next())
  		  	{
  		  		
  		  		event.setEventId(rs.getInt("event_id"));
  		  		event.setEventName(rs.getString("event_name"));
  		  		event.setEventStartDate(rs.getString("event_start_date"));
  		  		event.setEventEndDate(rs.getString("event_end_date"));
 
	  		
  		  		System.out.format("%-10s %-40s %8s %21s\n",event.getEventId(),event.getEventName(),event.getEventStartDate(),event.getEventEndDate());
  		  	}
  		  	
            connection.close();
            
 
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
		
		System.out.println("Event Created! Press enter to return to Main Menu.");
		
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
		
		
		
		System.out.println("Venue ID: " + event.getEventId());
		System.out.println("Venue Name: "+ event.getEventName());
		System.out.println("Venue Address: "+event.getEventStartDate());
		System.out.println("Venue City: "+ event.getEventEndDate());
		
		System.out.println();
		
		HashMap<Integer, String> eventmap = new HashMap<>();
		eventmap.put(1, "Event Name");
		eventmap.put(2, "Event Description");
		eventmap.put(3, "Event Start Date");
		eventmap.put(4, "Event End Date");
		
		HashMap<Integer, String> eventmapUpdate = new HashMap<>();
		eventmapUpdate.put(1, event.getEventName());
		eventmapUpdate.put(2, event.getEventDescription());
		eventmapUpdate.put(3, event.getEventStartDate());
		eventmapUpdate.put(4, event.getEventEndDate());
		
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
				+ "', event_description='"+eventmapUpdate.get(2)+"', event_start_date='"+eventmapUpdate.get(3)+"', event_end_date='"+eventmapUpdate.get(4)+"' WHERE event_id="+eventSelection+";");
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

