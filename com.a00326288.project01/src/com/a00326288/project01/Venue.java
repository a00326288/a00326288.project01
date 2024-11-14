package com.a00326288.project01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import com.a00326288.project01.EventDetails.EventDetail;

public class Venue {

	public Venue() {
		// TODO Auto-generated constructor stub
	}
	
	private Integer venueId;
	private String venueName;
	private String venueAddress;
	private String venueCity;
	 

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	

	public Venue(Integer venueId, String venueName, String venueAddress, String venueCity) {
		this.venueId = venueId;
		this.venueName = venueName;
		this.venueAddress = venueAddress;
		this.venueCity = venueCity;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(venueAddress, venueCity, venueId, venueName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venue other = (Venue) obj;
		return Objects.equals(venueAddress, other.venueAddress) && Objects.equals(venueCity, other.venueCity)
				&& Objects.equals(venueId, other.venueId) && Objects.equals(venueName, other.venueName);
	}



	@Override
	public String toString() {
		return "Venue [venueId=" + venueId + ", venueName=" + venueName + ", venueAddress=" + venueAddress
				+ ", venueCity=" + venueCity + "]";
	}
	
	
	public static void getVenues() {
		
	        String SQL = ("SELECT * from venues;");
	        try {
	        	Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
	  		  	Statement statement = connection.createStatement();
	  		    statement.setQueryTimeout(30); 
	  		  	ResultSet rs = statement.executeQuery(SQL);
	  		  	
	  		  	
	  		  	while(rs.next())
	  		  	{
	  		  
	   
	  		  	Venue venue = new Venue(rs.getInt("venue_id"), rs.getString("venue_name"), rs.getString("venue_address"), rs.getString("city"));
	  		  	
	  			System.out.println("-----------------------------");
	  	        System.out.println("- Event Details  -");
	  	        System.out.println("-----------------------------\n");

				System.out.print("Venue Id	:	");
				System.out.println(venue.getVenueId());
				System.out.print("Venue Name:	");
				System.out.println(venue.getVenueName());
				System.out.print("Venue Address :	");
				System.out.println(venue.getVenueAddress());
				System.out.print("Venue City:	");
				System.out.println(venue.getVenueCity());
	 
 
				System.out.println();
	  		  	
	  
	  		  	}
	  		  	connection.close();
	          }
	        catch(SQLException e)
	        {
	          e.printStackTrace(System.err);
	        }
		}
	
 
	
}
