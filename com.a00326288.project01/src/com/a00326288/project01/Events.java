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
		
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {	
		
		displayEvents();
		menu();
	}
	
	
	public static List<Events> eventlist =new ArrayList<Events>();

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

	public Integer getEventPrice() {
		return eventPrice;
	}

	public void setEventPrice(Integer eventPrice) {
		this.eventPrice = eventPrice;
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
  		  	
  		  	
  		  	
  		  	System.out.println(String.format("%-10s %-25s %8s %20s" , "Event ID", "Event Name", "Event Start", "Event Ends" ));
  		  	while(rs.next())
  		  	{
  		  		
  		  		
  		  		Events newEvent = new Events();
  		  		
  		  		newEvent.setEventId(rs.getInt("event_id"));
  		  		newEvent.setEventName(rs.getString("event_name"));	            	
  		  		newEvent.setEventDescription(rs.getString("event_description"));
  		  		newEvent.setEventStartDate(rs.getString("event_start_date"));
  		  		newEvent.setEventEndDate(rs.getString("event_end_date"));
  		  		
  		  		eventlist.add(newEvent);
  		  		
  		  		
  		  		
  		  		
  		  		
  		  		System.out.format("%-10s %-25s %8s %22s\n",newEvent.eventId,newEvent.eventName,newEvent.eventStartDate,newEvent.eventEndDate);
  		  		System.out.println();
  		  	}
  		  	
            connection.close();
      

        
            {
            }
     
          }
        catch(SQLException e)
        {
          // if the error message is "out of memory",
          // it probably means no database file is found
          e.printStackTrace(System.err);
        }
		 
		
		
        
       
    	
        // print the results
        //
		
		
		System.out.println();

	}
	
		
	
	public static void createEvent() {
		
		Events event = new Events();
		
		System.out.println("Input a name for the Event:");
		event.setEventName(sc.next());
		sc.nextLine();
		
		System.out.println("Input a description of the Event:");
		event.setEventDescription(sc.next());
		sc.nextLine();
		
		System.out.println("Enter a date whenever the event starts:");
		event.setEventStartDate(sc.next());
		sc.nextLine();
		
		System.out.println("Enter a date for whenever the event finishes:");
		event.setEventEndDate(sc.next());
		sc.nextLine();
		
		event.dbCreateEvent();
		
		
		
	}
	
	private void dbCreateEvent() {
		
		String SQL = ("INSERT INTO events (event_name, event_description, event_start_date, event_end_date) VALUES ('"+this.eventName+"','"+this.eventDescription+"','"+this.eventStartDate+"','"+this.eventEndDate+"');");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
  		  	Statement statement = connection.createStatement();
            statement.executeUpdate(SQL);
            connection.close();
        
            {
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }   
        
        menu();
		
	}

	
	 
	
	public static void menu() {
		
        int selection;
        
        selection = 0;
        
        /***************************************************/
        
        System.out.println("---------------------------");
        System.out.println("-Choose from the choices below -");
        System.out.println("---------------------------\n");
        System.out.println("1 - Book Event");
        System.out.println("2 - View Event"); 
        System.out.println("3 - Create Event");
        System.out.println("4 - Main Menu");

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
			  createEvent();
			break;
		  case 4:
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
		
		int selection = sc.nextInt();
		
		EventDetails.dbVenueDetails();
		
		EventDetails.EventDetails(selection);
		
	}
	

     
}

