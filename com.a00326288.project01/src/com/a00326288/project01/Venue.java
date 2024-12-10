package com.a00326288.project01;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Venue {
	
	private static Scanner sc = new Scanner(System.in);

	private Integer venueId;
	private String venueName;
	private String venueAddress;
	private String venueCity;
	private Integer capacity;
 
	
	public Venue() {
		
	}

	
	public Venue(Integer venueId, String venueName, String venueAddress, String venueCity, Integer capacity) {
		this.venueId = venueId;
		this.venueName = venueName;
		this.venueAddress = venueAddress;
		this.venueCity = venueCity;
		this.capacity = capacity;
	}


	private Integer getVenueId() {
		return venueId;
	}

	private String getVenueName() {
		return venueName;
	}

	private void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	private String getVenueAddress() {
		return venueAddress;
	}

	private void setVenueAddress(String venueAddress) {
		this.venueAddress = venueAddress;
	}

	private String getVenueCity() {
		return venueCity;
	}

	private void setVenueCity(String venueCity) {
		this.venueCity = venueCity;
	}
	
	private Integer getCapacity() {
		return capacity;
	}

	private void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	
	


	@Override
	public String toString() {
		
		return "Venue [venueId=" + venueId + ", venueName=" + venueName + ", venueAddress=" + venueAddress
				+ ", venueCity=" + venueCity + ", capacity=" + capacity + "]";
	}
	
	
	public static void createVenue() {
		// TODO Auto-generated method stub
		sc.useDelimiter("\r?\n");	
		
		Venue venue = new Venue();	
 		
	 
		System.out.println("Input the name of the Venue:");		
		venue.setVenueName(sc.next());
  
		System.out.println("Input the address of the Venue:");
		venue.setVenueAddress(sc.next());
		 
		
		 
		System.out.println("Input the city of the Venue:");
		venue.setVenueCity(sc.next());
		 
		
		while(true) { 
		System.out.println("Please input the capacity of the venue:");
		try {
			venue.setCapacity(sc.nextInt());
		
		if(venue.getCapacity()>0) {
			break;
		}else {
			System.out.println("Please ensure the value input is greater than 0.");
		}
		
		}catch(InputMismatchException e) {
			e.printStackTrace();
			System.out.println("Please input a valid value");
			sc.next();
		}
		
		}
 		
		Venue.dbCreateVenue(venue.getVenueName(),venue.getVenueAddress(),venue.getVenueCity(),venue.getCapacity());

		System.out.println("Venue Created! Press enter to return to Main Menu.");
 
		venue = null;
	}
	
	public static void viewVenues() {
		// TODO Auto-generated method stub
		
		final ArrayList<Venue> venueList = dbGetVenues();
		
		if(venueList.isEmpty()) {
			System.out.println("No venues exist");
		}else {
		
		System.out.println(String.format("%-15s %-25s %-70s %-20s %10s", "Venue ID", "Venue Name", "Venue Address", "City", "Capacity"));

		for(Venue venue: venueList) {	
			System.out.format("%-15s %-25s %-70s %-20s %10s\n", venue.getVenueId(),venue.getVenueName(),venue.getVenueAddress(),venue.getVenueCity(),venue.getCapacity());			
		}
		}
		
				
	}
	
	
	
	
	public static void deleteVenue() {
		// TODO Auto-generated method stub
		
		sc.useDelimiter("\r?\n");
		
		System.out.println("Existing Venue List:");
		
		viewVenues();
		
		final ArrayList<Venue> venueList = dbGetVenues(); 
		
		final List<Integer> venueIdLookup = venueList.stream()
			  	.map(Venue::getVenueId)
			  	.collect(Collectors.toList());
		
		Integer venueSelection;
		
		while(true) {
			
			try {
 	
			System.out.println("Enter the venue ID to delete: ");
			venueSelection = sc.nextInt();
			
			if(venueIdLookup.contains(venueSelection)) {
				
				dbDeleteVenue(venueSelection);
				System.out.println("Delete Complete! Press enter to return to Main Menu.");
				break;
			}else {
				System.out.println("Please input a valid ID.");
			}
			
			
			}catch(InputMismatchException e) {
			 e.printStackTrace();
			 System.out.println("Please input a valid ID.");
			 sc.next();
			}
			
			
		}
	}
	
	

	public static void modifyVenue() {
		// TODO Auto-generated method stub
		
		sc.useDelimiter("\r?\n");
		
		viewVenues();
		
		ArrayList<Venue> venueList = dbGetVenues();
		
		final List<Integer> venueIdLookup = venueList.stream()
		  	.map(Venue::getVenueId)
		  	.collect(Collectors.toList());
		
		Integer venueSelection = null;
	
		while(true) {
		try {
		
		System.out.println("Enter the venue ID to Modify: ");
		
		venueSelection = sc.nextInt();
		
		if(venueIdLookup.contains(venueSelection)) {
			break;
			
		}else {
			System.out.println("Please input a valid Venue ID");
			sc.next();
		}
		
		}catch(InputMismatchException e){
			e.printStackTrace();
			System.out.println("Please input a valid Venue ID");
			sc.next();
			
		}
		}
 
		
		Integer venueIndex=venueIdLookup.indexOf(venueSelection);
		
		
		System.out.println("Which property do you want to modify?");
		
		System.out.println("1. Venue Name: "+ venueList.get(venueIndex).getVenueName());
		System.out.println("2. Venue Address: "+venueList.get(venueIndex).getVenueAddress());
		System.out.println("3. Venue City: "+ venueList.get(venueIndex).getVenueCity());
		System.out.println("4. Capacity: "+ venueList.get(venueIndex).getCapacity());

		Integer cursor = sc.nextInt();
	
		String newString;
		Integer newInt;
		 
		int exit =0;
		
        while(exit==0) {
		try {
		
		switch(cursor) {
		
		case 1:
			System.out.println("Please enter new Venue Name");
			newString = sc.next();
			venueList.get(venueIndex).setVenueName(newString);
			exit=1;
			break;
		case 2:
			System.out.println("Please enter new Venue Address");
			newString = sc.next();
			venueList.get(venueIndex).setVenueName(newString);
			exit=1;
			break;
		case 3:
			System.out.println("Please enter new Venue City");
			newString = sc.next();
			venueList.get(venueIndex).setVenueName(newString);
			exit=1;
			break;
		case 4:
			System.out.println("Please enter new Capacity");
			newInt = sc.nextInt();
			if(newInt<=0) {
				exit=0;
			}else {
				venueList.get(venueIndex).setCapacity(newInt);
				exit=1;
			}
			break;
		default:
			System.out.println("Please enter a valid value");
			break;
		}
		
		}catch(InputMismatchException e) {
			e.printStackTrace();
			sc.nextLine();
		}
		}

        
        
        dbUpdateVenue(venueList.get(venueIndex).getVenueId(),venueList.get(venueIndex).getVenueName(),venueList.get(venueIndex).getVenueAddress(),venueList.get(venueIndex).getVenueCity(),venueList.get(venueIndex).getCapacity());
        
		System.out.println("Update Complete! Press enter to return to Main Menu.");	
	
 
	}
	
	private static void dbCreateVenue(String venueName,String venueAddress, String venueCity, Integer capacity) {
		
		String SQL = ("INSERT INTO venues (venue_name, venue_address, city, capacity) VALUES ('"+venueName+"','"+venueAddress+"','"+venueCity+"',"+capacity+");");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
  		  	Statement statement = connection.createStatement();
            statement.executeUpdate(SQL);
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }   
		
	}
	
	
	private static ArrayList<Venue> dbGetVenues() {
		
		ArrayList<Venue> venueList = new ArrayList<Venue>();
        
		String SQL = ("SELECT * from venues;");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		    statement.setQueryTimeout(30); 
  		  	ResultSet rs = statement.executeQuery(SQL);
  		  	
  		  	venueList.clear();
  		  	
  		  	while(rs.next())
  		  	{
  		  	Venue venue = new Venue(rs.getInt("venue_id"), rs.getString("venue_name"), rs.getString("venue_address"), rs.getString("city"), rs.getInt("capacity"));
  		  	venueList.add(venue);
  		  	}
  		  	connection.close();
          }
        catch(SQLException e)
        {
          e.printStackTrace(System.err);
        }
		return venueList;		
	}
	
	
	private static void dbDeleteVenue(Integer venueSelection) {
		// TODO Auto-generated method stub
		
		String SQL = ("DELETE FROM venues WHERE venue_id="+venueSelection+";");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
  		  	Statement statement = connection.createStatement();
            statement.executeUpdate(SQL);
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 	
	}

	
	private static void dbUpdateVenue(Integer venueId, String venueName, String venueAddress, String venueCity, Integer venueCapacity) {
		// TODO Auto-generated method stub
		
		String SQL = ("UPDATE venues SET venue_name ='"+venueName+ "', venue_address='"+venueAddress+"', city='"+venueCity+"',capacity="+venueCapacity+"  WHERE venue_id="+venueId+";");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
  		  	Statement statement = connection.createStatement();
            statement.executeUpdate(SQL);
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
	}
	
}
