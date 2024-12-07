package com.a00326288.project01;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Venue {
	
	private static Scanner sc = new Scanner(System.in);

	private Integer venueId;
	private String venueName;
	private String venueAddress;
	private String venueCity;
	private Integer capacity;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}	
	
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

	private void setVenueId(Integer venueId) {
		this.venueId = venueId;
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
	
	
	public void createVenue() {
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
 
		
		
		DBA.dbCreateVenue(venueName,venueAddress,venueCity,capacity);
		
		System.out.println("Venue Created! Press enter to return to Main Menu.");
		UAM.returnMain();
		
	}
	
	public ArrayList<Venue> viewVenues() {
		// TODO Auto-generated method stub
		
		ArrayList<Venue> venueList = DBA.dbGetVenues();
		
		System.out.println(String.format("%-15s %-25s %-70s %-20s %10s", "Venue ID", "Venue Name", "Venue Address", "City", "Capacity"));

		for(int i=0;i < venueList.size();i++) {	
			System.out.format("%-15s %-25s %-70s %-20s %10s\n", venueList.get(i).getVenueId(),venueList.get(i).getVenueName(),venueList.get(i).getVenueAddress(),venueList.get(i).getVenueCity(),venueList.get(i).getCapacity());			
		}
		return venueList;		
	}
	
	
	
	
	public void deleteVenue() {
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
				DBA.dbDeleteVenue(venueSelection);
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
	
		
		UAM.returnMain();
	}
	
	

	public void modifyVenue() {
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
				
		DBA.dbUpdateVenue(venuesmapUpdate,venueSelection);
		System.out.println("Update Complete! Press enter to return to Main Menu.");
		UAM.returnMain();
	}

	

	
}
