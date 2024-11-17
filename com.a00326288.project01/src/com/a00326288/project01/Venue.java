package com.a00326288.project01;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import java.util.Objects;
import java.util.Scanner;

enum modify {venue_name,venue_desc,venue_city}


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
	  	        System.out.println("Venue ID: " +venue.getVenueId());
	  	        System.out.println("Venue Name: "+venue.getVenueName());
	  	        System.out.println("Venue Address: " +venue.getVenueAddress());
	  	        System.out.println("Venue City: "+venue.getVenueCity());
				
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

	
		dbCreateVenue(venueName,venueAddress,venueCity);
		
	}

	private static void dbCreateVenue(String venueName,String venueAddress, String venueCity) {
		
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
            	venue.setVenueAddress(rs.getString("venue_address"));
            	venue.setVenueCity(rs.getString("city"));
 
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
          	venue.setVenueAddress(rs.getString("venue_address"));
          	venue.setVenueCity(rs.getString("city"));

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
		
		sc.useDelimiter("\r?\n");
		
		getVenues();
		
		
		System.out.println("Enter the venue ID to Modify: ");
		
		int venueSelection = sc.nextInt();
		 
		Venue venue = dbVenue(venueSelection);
		
		
		
		System.out.println("Venue ID: " + venue.getVenueId());
		System.out.println("Venue Name: "+ venue.getVenueName());
		System.out.println("Venue Address: "+venue.getVenueAddress());
		System.out.println("Venue City: "+ venue.getVenueCity());
		
		System.out.println();
		
		HashMap<Integer, String> venuesmap = new HashMap<>();
		venuesmap.put(1, "Venue Name");
		venuesmap.put(2, "Venue Address");
		venuesmap.put(3, "Venue City");
		
		HashMap<Integer, String> venuesmapUpdate = new HashMap<>();
		venuesmapUpdate.put(1, venue.getVenueName());
		venuesmapUpdate.put(2, venue.getVenueAddress());
		venuesmapUpdate.put(3, venue.getVenueCity());
		
		System.out.println("Which property do you want to modify?");
		
		
		for(int i=1;i<=venuesmap.size();i++) {
			System.out.println(i+". " + venuesmap.get(i));
			
		}
		
		int cursor = sc.nextInt();
		
		System.out.println("Please enter new value for "+venuesmap.get(cursor)+":");
		
		String newval = sc.next();
		
		venuesmapUpdate.put(cursor, newval);
		
		dbUpdateVenue(venuesmapUpdate,venueSelection);
		
		
		

	}

	private static void dbUpdateVenue(HashMap<Integer, String> venuesmap_update,Integer venueSelection) {
		// TODO Auto-generated method stub
		
		String SQL = ("UPDATE venues SET venue_name ='"+venuesmap_update.get(1)
				+ "', venue_address='"+venuesmap_update.get(2)+"', city='"+venuesmap_update.get(3)+"' WHERE venue_id="+venueSelection+";");
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



	public static void deleteVenue() {
		// TODO Auto-generated method stub
		
	}
	
 
	
}
