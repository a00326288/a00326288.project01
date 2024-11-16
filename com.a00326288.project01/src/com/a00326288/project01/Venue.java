package com.a00326288.project01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

import com.a00326288.project01.EventDetails.EventDetail;

public class Venue {
	
	private static Scanner sc = new Scanner(System.in);

	public Venue() {
		// TODO Auto-generated constructor stub
	}
	
	private Integer venueId;
	private String venueName;
	private String venueAddress;
	private String venueCity;
	 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sc.useDelimiter("\r?\n");
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



	public static void createVenue() {
		// TODO Auto-generated method stub
		
		System.out.println("Input the name of the Venue:");
		String venueName = sc.next();
		
		System.out.println("Input the address of the Venue:");
		String venueAddress=sc.next();
		
		System.out.println("Input the city of the Venue:");
		String venueCity = sc.next();

	
		dbVenue(venueName,venueAddress,venueCity);
		
	}

	private static void dbVenue(String venueName,String venueAddress, String venueCity) {
		
		String SQL = ("INSERT INTO venues (venue_name, venue_address, city) VALUES ('"+venueName+"','"+venueAddress+"','"+venueCity+"';");
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
	
	private static Venue dbVenue(int venueId) {
		
		Venue venue = new Venue();
		
		String SQL = ("SELECT venue_id, venue_name, venue_address, city FROM venues WHERE venue_id = "+venueId+";");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
  		  	Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            statement.setQueryTimeout(30); 
            while (rs.next()) 
            
            {
            	
            	
            	
            	venue.setVenueId(rs.getInt("venue_id"));
            	venue.setVenueName(rs.getString("venue_name"));
            	venue.setVenueAddress(rs.getString("venue_name"));
            	venue.setVenueCity(rs.getString("venue_address"));
 
            }
            
            statement.closeOnCompletion();
            connection.close();
            
            
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return venue;   
        
        
		
	}
	
	private static void dbVenue() {
		
		String SQL = ("SELECT * FROM venues;");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		  	ResultSet rs = statement.executeQuery(SQL);
  		  	statement.setQueryTimeout(30); 
  		  	while (rs.next()) 
          
  		  	{
          	
          	Venue venue = new Venue();
          	
          	venue.setVenueId(rs.getInt("venue_id"));
          	venue.setVenueName(rs.getString("venue_name"));
          	venue.setVenueAddress(rs.getString("venue_name"));
          	venue.setVenueCity(rs.getString("venue_address"));

  		  	}
          
  		  	statement.closeOnCompletion();
  		  	connection.close();          
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }   
		
	}

	public static void viewVenue() {
		// TODO Auto-generated method stub
		
		int venueSelection = sc.nextInt();
		System.out.println(dbVenue(venueSelection).toString());
  
		
	}



	public static void modifyVenue() {
		// TODO Auto-generated method stub
		
	}



	public static void deleteVenue() {
		// TODO Auto-generated method stub
		
	}
	
 
	
}
