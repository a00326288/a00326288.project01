package com.a00326288.project01;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;

import java.util.Scanner;


public class Event {
	
	private Integer eventId;
	public String eventName;
	public String eventDescription;
	private String event_date;
	private String venue_name;
	private Integer price;
	
	private static Scanner sc = new Scanner(System.in);
	private static ArrayList<Event> eventList = new ArrayList<Event>();
	
	
	public static void main(String[] args) {		
	
	}
 
	public Event(int eventId, String eventName, String eventDescription, String event_date, String venue_name, Integer price) {
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventDescription = eventDescription;
		this.event_date=null;
		this.venue_name = null;
		this.price = 0;
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
		
        String SQL = ("SELECT * FROM events;");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		    statement.setQueryTimeout(30); 
  		  	ResultSet rs = statement.executeQuery(SQL);
  		  	
  		  	
  		  	
  		  	System.out.println(String.format("%-10s %-40s %20s" , "Event ID", "Event Name", "Event Description" ));
  		  	while(rs.next())
  		  	{
  		  		
  		  		Event event = new Event(rs.getInt("event_id"),rs.getString("event_name"),rs.getString("event_description"), null, null,null);
  		  		
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
	
	
	public static void dbGetEventDates(Integer selection) {
		// TODO Auto-generated method stub
		 
		
		
        String SQL = ("SELECT a.event_id, a.event_name, b.event_date,c.venue_name, d.price  FROM events a left join dates b on a.event_id = b.event_id left join venues c on b.venue_id = c.venue_id left join prices d on b.price_id = d.price_id where a.event_id="+selection+";");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		    statement.setQueryTimeout(30); 
  		  	ResultSet rs = statement.executeQuery(SQL);
  		  	
  		  	
  		  	while(rs.next())
  		  	{
  		  		
  		  		Event event = new Event(rs.getInt("event_id"),rs.getString("event_name"),null, rs.getString("event_date"),rs.getString("venue_name"),rs.getInt("price"));
  		  		eventList.add(event);
  		  		
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
 
		Event event = new Event(0,null,null,null,null,null);
		
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
		
		
		Event event = new Event(0,eventname,eventDescription,null,null,null);
		
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
		
		System.out.println("Above is the list of avaialble Events! ");
		
		System.out.println("To find out more details on available dates, venues and prices please input an event or enter '0' to exit.");
		
		int selection = sc.nextInt();
		
		if(selection==0) {
			UserAccessControl.returnMain();
		}else {
			dbGetEventDates(selection);
			
			//System.out.println(String.format("%-10s %-10s %-25s %-10s %20s"  , "Event ID", "Event Name","Event Date", "Venue", "Price", "Remaining Tickets"));
			
			System.out.println(eventList.get(selection).event_date.toString());
			System.out.println(eventList.get(selection).eventName.toString());
			System.out.println(eventList.get(selection).venue_name.toString());
			System.out.println(eventList.get(selection).price.toString());
			
			//System.out.format("%-10s %-10s %-25s %-10s %20s\n",event.getEventId(),event.getEventName(),event.getEventDescription());
			
		}
		
		
		
		
		
		
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

	public static void deleteEventDate() {
		// TODO Auto-generated method stub
		
	}
	  
}
