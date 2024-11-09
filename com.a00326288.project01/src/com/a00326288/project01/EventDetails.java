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

public class EventDetails extends Events

{
	public static List<EventDetails> venuelist = new ArrayList<EventDetails>();
	
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	private Integer eventId;
	private Integer venueId;
	private String venueName;
	private String venueAddress;
	private String venueCity;
	
	
	public EventDetails(){
		super();

		Integer	venueId = 0;
		String venueName = "";
		String venueAddress = "";
		String venueCity = "";
		
	
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

	
	
	
	public static void dbVenueDetails() {
		// TODO Auto-generated method stub
		
        
        String SQL = ("SELECT a.event_id, b.* FROM bookings a inner join venue b on a.venue_id=b.venue_id;");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		    statement.setQueryTimeout(30); 
  		  	ResultSet rs = statement.executeQuery(SQL);
  		  	
  		  	
  		  	
  		  	System.out.println(String.format("%-10s %-10s %-25s %8s %20s" , "Event ID","Venue ID", "Venue Name", "Venue Address", "Venue City" ));
  		  	while(rs.next())
  		  	{
  		  		
  		  		
  		  	EventDetails venue = new EventDetails();
  		  		
  		  	venue.setEventId(rs.getInt("event_id"));
  		  	
  		  	venue.setVenueId(rs.getInt("venue_id"));
  		  	
  		  	venue.setVenueName(rs.getString("venue_name"));	            	
  		  	
  		  	venue.setVenueAddress(rs.getString("venue_address"));
  		  	
  		  	venue.setVenueCity(rs.getString("city"));
  		  		
  		  		
  		  	venuelist.add(venue);
  		  	
  		  	
  		  	
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



	public static void EventDetails(int eventIdSelection) {
		// TODO Auto-generated method stub
		
		int eventIdx = 0;

		for (Events e : eventlist) {
		    if (e.getEventId().equals(eventIdSelection)) {
		     eventIdx=eventlist.indexOf(e);
		    break;
		    }
		}
		
		System.out.println("Below are the details of the event;");
		System.out.println(eventlist.get(eventIdx).getEventId());
		System.out.println(eventlist.get(eventIdx).getEventName());
		System.out.println(eventlist.get(eventIdx).getEventDescription());
		System.out.println(eventlist.get(eventIdx).getEventStartDate());
		System.out.println(eventlist.get(eventIdx).getEventEndDate());
	
		
		
		List<Events> newList = Stream.concat(eventlist.stream(),venuelist.stream()).toList();  	
		  	
		
		/*
		System.out.println(venuelist.get(venueIdx).getVenueId());
		System.out.println(venuelist.get(venueIdx).getVenueName());
		System.out.println(venuelist.get(venueIdx).getVenueAddress());
		System.out.println(venuelist.get(venueIdx).getVenueCity());
		*/
		/*
		
		int venueIdx = 0;
		
		for (EventDetails e : venuelist) {
			System.out.println(e.getEventId());
		    if (e.getEventId().equals(eventlist.get(eventIdx).getEventId())) {
		    	venueIdx=venuelist.indexOf(e);
		    	System.out.println(venueIdx);
		    break;
		    }
		}
		
		*/
		
		//System.out.println(venuelist.get(eventIdx).getVenueName());
		
		
		
		menu();
		
		
	}

	
	
}
