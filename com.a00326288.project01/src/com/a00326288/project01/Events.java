package com.a00326288.project01;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;




public class Events {
		
	
	
	private Integer eventId;
	private String eventName;
	private String eventDescription;
	private String eventStartDate;
	private String eventEndDate;
	
	
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {	
		sc.useDelimiter("\r?\n");
		displayEvents();
		launchpad.user_menu_events();
	}
	
	public Events(int eventId, String eventName, String eventDescription, String eventStartDate, String eventEndDate) {
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventDescription = eventDescription;
		this.eventStartDate =  eventStartDate;
		this.eventEndDate = eventEndDate;
		
	}
	
	
	
	//public static List<Events> eventlist =new ArrayList<Events>();
	
	
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
  		  		
  		  		Events events = new Events(rs.getInt("event_id"),rs.getString("event_name"),rs.getString("event_description"),rs.getString("event_start_date"),rs.getString("event_end_date"));
  		  		
  		  		System.out.format("%-10s %-40s %8s %21s\n",events.getEventId(),events.getEventName(),events.getEventStartDate(),events.getEventEndDate());
  		  	}
  		  	
            connection.close();
          }
        catch(SQLException e)
        {
          e.printStackTrace(System.err);
        }
		System.out.println();
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

		Events events = new Events((Integer) null,eventname,eventDescription,eventStartDate,eventEndDate);
		
		dbCreateEvent(events);
	}
	
	private static void dbCreateEvent(Events events) {
		
		String SQL = ("INSERT INTO events (event_name, event_description, event_start_date, event_end_date) VALUES ('"+events.getEventName()+"','"+events.getEventDescription()+"','"+events.getEventStartDate()+"','"+events.getEventEndDate()+"');");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
  		  	Statement statement = connection.createStatement();
            statement.executeUpdate(SQL);
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }   
        launchpad.user_menu_events();	
	}

	
	 
	
	
	


	public static void bookEvent() {
		// TODO Auto-generated method stub
		System.out.println("-----------------------------");
        System.out.println("- Book Event -");
        System.out.println("-----------------------------\n");
        
		System.out.println("Please input the ID of the event you wish to book:");
		System.out.println();
		
		int option = sc.nextInt();
		
		
		
		
	
	}


	public static void viewEvent() {
		// TODO Auto-generated method stub
		System.out.println("Please input the ID of the event you wish to view");
		
		int selection = sc.nextInt();
		
		EventDetails.EventVenueDetails(selection);
		
	}
	

     
}

