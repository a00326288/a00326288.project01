package com.a00326288.project01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class EventDetails 
{

	
	record EventDetail(Integer eventId,String eventName, String eventDescription, String eventStartDate, String eventEndDate, Integer venueId, String venueName, String  venueAddress, String venueCity, Integer priceId, Integer eventPrice ) {
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
	}
	
	

	private static void dbVenueDetails(int eventIdSelection) {
		// TODO Auto-generated method stub
		
        
        String SQL = ("SELECT a.event_id,a.event_name,a.event_description,a.event_start_date,a.event_end_date,b.venue_id,c.venue_name,c.venue_address,c.city,d.price_id, d.price FROM events a inner JOIN events_x_venue_x_price b on a.event_id=b.event_id INNER JOIN venues c ON b.venue_id = c.venue_id INNER JOIN prices d ON b.price_id = d.price_id where a.event_id="+eventIdSelection+";");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		    statement.setQueryTimeout(30); 
  		  	ResultSet rs = statement.executeQuery(SQL);
  		  	
  		  	
  		  	while(rs.next())
  		  	{
  		  
  		  	EventDetail event = new EventDetail(rs.getInt("event_id"), rs.getString("event_name"), rs.getString("event_description"), rs.getString("event_start_date"), rs.getString("event_end_date"), rs.getInt("venue_id"), rs.getString("venue_name"), rs.getString("venue_address"), rs.getString("city"), rs.getInt("price_id"), rs.getInt("price"));
  		  	
  			System.out.println("-----------------------------");
  	        System.out.println("- Event Details  -");
  	        System.out.println("-----------------------------\n");

			System.out.print("Event Name	:	");
			System.out.println(event.eventName());
			System.out.print("Event Description :	");
			System.out.println(event.eventDescription());
			System.out.print("Event Start Date :	");
			System.out.println(event.eventStartDate());
			System.out.print("Event Last Date :	");
			System.out.println(event.eventEndDate());
			System.out.print("Event Venue	:	");
			System.out.println(event.venueName());
			System.out.print("Venue Address	:	");
			System.out.println(event.venueAddress());
			System.out.print("Venue City	:	");
			System.out.println(event.venueCity());
			System.out.print("Venue Price	:	");
			System.out.println(event.eventPrice());
			System.out.println();
  		  	
  
  		  	}
  		  	connection.close();
          }
        catch(SQLException e)
        {
          e.printStackTrace(System.err);
        }
	}





	public static void EventVenueDetails(int eventIdSelection) {
		// TODO Auto-generated method stub
		
		dbVenueDetails(eventIdSelection);
		
		UserAccessControl.whichMenu();
		
	}

	
	
}
