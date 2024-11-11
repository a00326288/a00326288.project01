package com.a00326288.project01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class EventDetails 
{
	
	private Integer eventId;
	private String eventName;
	private String eventDescription;
	private String eventStartDate;
	private String eventEndDate;
	private Integer venueId;
	private String venueName;
	private String venueAddress;
	private String venueCity;
	private Integer priceId;
	private Integer eventPrice;

	
	private static Scanner sc = new Scanner(System.in);

	
	public EventDetails(){
		super();
		
		Integer eventId;
		String eventName;
		String eventDescription;
		String eventStartDate;
		String eventEndDate;
		Integer venueId;
		String venueName;
		String venueAddress;
		String venueCity;
		Integer priceId;
		Integer price;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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

	public Integer getVenueId() {
		return venueId;
	}

	public void setVenueId(Integer venueId) {
		this.venueId = venueId;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	public String getVenueAddress() {
		return venueAddress;
	}

	public void setVenueAddress(String venueAddress) {
		this.venueAddress = venueAddress;
	}

	public String getVenueCity() {
		return venueCity;
	}

	public void setVenueCity(String venueCity) {
		this.venueCity = venueCity;
	}

	public Integer getPriceId() {
		return priceId;
	}

	public void setPriceId(Integer priceId) {
		this.priceId = priceId;
	}

	public Integer getEventPrice() {
		return eventPrice;
	}

	public void setEventPrice(Integer eventPrice) {
		this.eventPrice = eventPrice;
	}

	private static ArrayList<EventDetails> dbVenueDetails(int eventIdSelection) {
		// TODO Auto-generated method stub
		
		ArrayList<EventDetails> event_details = new ArrayList<EventDetails>();
        
        String SQL = ("SELECT a.event_id,a.event_name,a.event_description,a.event_start_date,a.event_end_date,b.venue_id,c.venue_name,c.venue_address,c.city,d.price_id, d.price FROM events a inner JOIN events_x_venue_x_price b on a.event_id=b.event_id INNER JOIN venues c ON b.venue_id = c.venue_id INNER JOIN prices d ON b.price_id = d.price_id where a.event_id="+eventIdSelection+";");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		    statement.setQueryTimeout(30); 
  		  	ResultSet rs = statement.executeQuery(SQL);
  		  	
  		  	
  		  	while(rs.next())
  		  	{
  		  	EventDetails venue = new EventDetails();   	

  		  	venue.setEventId(rs.getInt("event_id"));
  		  	venue.setEventName(rs.getString("event_name"));
  		  	venue.setEventDescription(rs.getString("event_description"));
  		    venue.setEventStartDate(rs.getString("event_start_date"));
  		    venue.setEventEndDate(rs.getString("event_end_date"));
  		  	venue.setVenueId(rs.getInt("venue_id"));
  		  	venue.setVenueName(rs.getString("venue_name"));	            
  		  	venue.setVenueAddress(rs.getString("venue_address"));
  		  	venue.setVenueCity(rs.getString("city"));	
  		  	venue.setPriceId(rs.getInt("price_id"));	
  		  	venue.setEventPrice(rs.getInt("price"));	
  		  	
  		  	event_details.add(venue);
  		
  		  	System.out.println();
  		  	}
  		  	connection.close();
          }
        catch(SQLException e)
        {
          e.printStackTrace(System.err);
        }
		return event_details;
	}





	public static void EventVenueDetails(int eventIdSelection) {
		// TODO Auto-generated method stub
		
		
		ArrayList<EventDetails> event_details = dbVenueDetails(eventIdSelection);

		int i=0;
		
		System.out.println("-----------------------------");
        System.out.println("- Event Details  -");
        System.out.println("-----------------------------\n");
		
        
		for(i=0;i<event_details.size();i++) {
			
			
			System.out.print("Event Name	:	");
			System.out.println(event_details.get(i).getEventName());
			System.out.print("Event Description :	");
			System.out.println(event_details.get(i).getEventDescription());
			System.out.print("Event Start Date :	");
			System.out.println(event_details.get(i).getEventStartDate());
			System.out.print("Event Last Date :	");
			System.out.println(event_details.get(i).getEventEndDate());
			System.out.print("Event Venue	:	");
			System.out.println(event_details.get(i).getVenueName());
			System.out.print("Venue Address	:	");
			System.out.println(event_details.get(i).getVenueAddress());
			System.out.print("Venue City	:	");
			System.out.println(event_details.get(i).getVenueCity());
			System.out.print("Venue Price	:	");
			System.out.println(event_details.get(i).getEventPrice());
			System.out.println();
			
			
		}
		
		launchpad.user_menu_events();
		
	}

	
	
}
