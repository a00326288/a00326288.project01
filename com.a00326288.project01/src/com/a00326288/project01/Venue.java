package com.a00326288.project01;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Venue {
	
	private Integer venueId;
	private String venueName;
	private String venueAddress;
	private String venueCity;
	private Integer capacity;
	private static Scanner sc = new Scanner(System.in);

	public Venue() {
		// TODO Auto-generated constructor stub
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}


	public Venue(Integer venueId, String venueName, String venueAddress, String venueCity, Integer capacity) {
		this.venueId = venueId;
		this.venueName = venueName;
		this.venueAddress = venueAddress;
		this.venueCity = venueCity;
		this.capacity = capacity;
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
	
	private Integer getCapacity() {
		return capacity;
	}

	private void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}



	@Override
	public int hashCode() {
		return Objects.hash(venueAddress, venueCity, venueId, venueName,capacity);
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
				&& Objects.equals(venueId, other.venueId) && Objects.equals(venueName, other.venueName) && Objects.equals(capacity, other.capacity);
	}


	@Override
	public String toString() {
		
		return "Venue [venueId=" + venueId + ", venueName=" + venueName + ", venueAddress=" + venueAddress
				+ ", venueCity=" + venueCity + ", capacity=" + capacity + "]";
	}
	
	
	public static void createVenue() {
		// TODO Auto-generated method stub
		sc.useDelimiter("\r?\n");		
		
		String venueName = "";
		String venueAddress = "";
		String venueCity="";
		Integer capacity = null;
		
		
		while(true) {
		
		System.out.println("Input the name of the Venue:");		
		venueName = sc.next();
		if(InputValidation.checkfieldNull(venueName)==true) {
			System.out.println("Please input a value");
		}else {
			break;	
			}
		}
		
		while(true) {
		
		System.out.println("Input the address of the Venue:");
		venueAddress=sc.next();
		if(InputValidation.checkfieldNull(venueAddress)==true) {
			System.out.println("Please input a value");
		}else {
			break;	
			}
		}
		
		while(true) {
			
		System.out.println("Input the city of the Venue:");
		venueCity = sc.next();
		if(InputValidation.checkfieldNull(venueCity)==true) {
			System.out.println("Please input a value");
		}else {
			break;	
			}
		}
		
		while(true) {
		
		System.out.println("Please input the capacity of the venue:");
		try {
		capacity = sc.nextInt();
		
		if(capacity>0) {
			
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
 
		
		
		dbCreateVenue(venueName,venueAddress,venueCity,capacity);
		
		System.out.println("Venue Created! Press enter to return to Main Menu.");
		UserAccessControl.returnMain();
		
	}
	
	public static ArrayList<Venue> viewVenues() {
		// TODO Auto-generated method stub
		
		ArrayList<Venue> venueList = dbGetVenues();
		
		System.out.println(String.format("%-15s %-25s %-70s %-20s %10s", "Venue ID", "Venue Name", "Venue Address", "City", "Capacity"));

		for(int i=0;i < venueList.size();i++) {	
			System.out.format("%-15s %-25s %-70s %-20s %10s\n", venueList.get(i).getVenueId(),venueList.get(i).getVenueName(),venueList.get(i).getVenueAddress(),venueList.get(i).getVenueCity(),venueList.get(i).getCapacity());			
		}
	
		UserAccessControl.returnMain();
		return venueList;		
	}
	
	
	
	
	public static void deleteVenue() {
		// TODO Auto-generated method stub
		
		ArrayList<Venue> venueList = viewVenues();
		
		List<Integer> venueIdLookup = venueList.stream()
			  	.map(Venue::getVenueId)
			  	.collect(Collectors.toList());
		
		Integer venueSelection = null;
		
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
		
		
		
		
		UserAccessControl.returnMain();
	}
	
	

	public static void modifyVenue() {
		// TODO Auto-generated method stub
		
		sc.useDelimiter("\r?\n");
		
		ArrayList<Venue> venueList = viewVenues();
				
		List<Integer> venueIdLookup = venueList.stream()
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
		
		
		System.out.println("Venue ID: " + venueList.get(venueIndex).getVenueId());
		System.out.println("Venue Name: "+ venueList.get(venueIndex).getVenueName());
		System.out.println("Venue Address: "+venueList.get(venueIndex).getVenueAddress());
		System.out.println("Venue City: "+ venueList.get(venueIndex).getVenueCity());
		System.out.println("Capacity: "+ venueList.get(venueIndex).getCapacity());
		System.out.println();
		
		HashMap<Integer, String> venuesmap = new HashMap<>();
		venuesmap.put(1, "Venue Name");
		venuesmap.put(2, "Venue Address");
		venuesmap.put(3, "Venue City");
		venuesmap.put(4, "Capacity");
		
		HashMap<Integer, String> venuesmapUpdate = new HashMap<>();
		venuesmapUpdate.put(1, venueList.get(venueIndex).getVenueName());
		venuesmapUpdate.put(2, venueList.get(venueIndex).getVenueAddress());
		venuesmapUpdate.put(3, venueList.get(venueIndex).getVenueCity());
		venuesmapUpdate.put(4, venueList.get(venueIndex).getCapacity().toString());
		
		System.out.println("Which property do you want to modify?");
		
		for(int i=1;i<=venuesmap.size();i++) {
			System.out.println(i+". " + venuesmap.get(i));	
		}
		
		 
		Integer cursor = null;
		
		
		while(true) {
		
		try {
		cursor = sc.nextInt();
		
		if(venuesmap.containsKey(cursor)==true) {
			break;
			
		}else {
			System.out.println("Cannot find a property matching the ID. Please try again.");
			
		}
		
		
		}catch(InputMismatchException e) {
			e.printStackTrace();
			System.out.println("Cannot find a property matching the ID. Please try again.");
			sc.next();
			
		}}
		
		
		System.out.println("Please enter new value for "+venuesmap.get(cursor)+":");
		
		
		String newval = "";		
		
		while(true) {
		

				if(cursor==4) {
					
						try {
							newval = sc.next();
							Integer.parseInt(newval);
							
							if(Integer.parseInt(newval) >0) {
							break;}
							else {
								System.out.println("Please ensure the value input is greater than 0.");
							}
						}catch(Exception e) {
							e.printStackTrace();
							System.out.println("Please input valid value for capacity.");
						}
					}else {
						newval = sc.next();
						break;
					}
		}
		
		while(true) {		
			try {
			
			if(InputValidation.checkfieldNull(newval)==true) {
				System.out.println("Field cannot be blank please add value.");
				
			}else{
				break;
			}
		
			}catch(InputMismatchException e) {
				e.printStackTrace();
				System.out.println("Please input a valdi value.");
				sc.next();
				
			}
		
		}
	
		
		venuesmapUpdate.put(cursor, newval);
				
		dbUpdateVenue(venuesmapUpdate,venueSelection);
		System.out.println("Update Complete! Press enter to return to Main Menu.");
		UserAccessControl.returnMain();
	}

	private static void dbUpdateVenue(HashMap<Integer, String> venuesmapUpdate,Integer venueSelection) {
		// TODO Auto-generated method stub
		
		String SQL = ("UPDATE venues SET venue_name ='"+venuesmapUpdate.get(1)
				+ "', venue_address='"+venuesmapUpdate.get(2)+"', city='"+venuesmapUpdate.get(3)+"',capacity="+Integer.parseInt(venuesmapUpdate.get(4))+"  WHERE venue_id="+venueSelection+";");
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

	private static void dbDeleteVenue(Integer venueSelection) {
		// TODO Auto-generated method stub
		
		String SQL = ("DELETE FROM venues WHERE venue_id="+venueSelection+";");
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
	
	private static void dbCreateVenue(String venueName,String venueAddress, String venueCity, Integer capacity) {
		
		String SQL = ("INSERT INTO venues (venue_name, venue_address, city, capacity) VALUES ('"+venueName+"','"+venueAddress+"','"+venueCity+"',"+capacity+");");
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
	
 
	
	public static ArrayList<Venue> dbGetVenues() {
		ArrayList<Venue> venueList = new ArrayList<Venue>();
        String SQL = ("SELECT * from venues;");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
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

	
}
