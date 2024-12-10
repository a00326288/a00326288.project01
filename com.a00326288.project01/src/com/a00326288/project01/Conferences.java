package com.a00326288.project01;

import java.sql.Connection;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

non-sealed class Conferences extends Event implements database{

 
	private static Scanner sc = new Scanner(System.in);
	private Integer event_id;
	private String eventDate;
	private String name;
	private String description;
	final private String eventType ="Conference";
	private int conference_id;
	private String conferenceSpeakers;
	private String sponsor;
	private Integer venueId;
	private String venueName;
	private Integer priceId;
	private Integer price;
	
	
	Conferences(Integer event_id,String name, String description, int conferenceId, String conferenceSpeakers, String sponsor) {
		super(event_id,name,description);
		this.event_id = event_id;
		this.name = name;
		this.description = description;
		this.conference_id = conferenceId;
		this.conferenceSpeakers = conferenceSpeakers;
		this.sponsor = sponsor;
 
	}	
 
	Conferences() {
		// TODO Auto-generated constructor stub
	}

  
	Conferences(Integer event_id,String name, String description) {
		super(event_id, name, description);
	}	

	private int getEventId() {
		// TODO Auto-generated method stub
		return event_id;
	}

	private	void setEventId(Integer event_id) {
		// TODO Auto-generated method stub
		this.event_id = event_id;
	}


	private String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	private void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}

	private	String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}


	private	void setDescription(String description) {
		// TODO Auto-generated method stub
		this.description = description;
	}

	private String getConferenceSpeakers() {
		return conferenceSpeakers;
	}

	private void setConferenceSpeakers(String conferenceSpeakers) {
		this.conferenceSpeakers = conferenceSpeakers;
	}

	private String getSponsor() {
		return sponsor;
	}

	private void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	private int getConference_id() {
		return conference_id;
	}

	private void setConference_id(int conference_id) {
		this.conference_id = conference_id;
	}
 
 
	private String getEventDate() {
		return eventDate;
	}

	private void setEventDate(String date) {
		this.eventDate = date;
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

	private Integer getPriceId() {
		return priceId;
	}

	private void setPriceId(Integer priceId) {
		this.priceId = priceId;
	}

	private Integer getPrice() {
		return price;
	}

	private void setPrice(Integer price) {
		this.price = price;
	}

	private String getEventType() {
		return eventType;
	}

	@Override
	public String toString() {
		return "Conference [event_id=" + event_id + " name=" + name + ", description=" + description + " conferenceSpeakers=" + conferenceSpeakers + ", sponsor=" + sponsor + "]";
	}


	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		
		sc.useDelimiter("\r?\n");
		
		Conferences newConference = new Conferences(super.event_id,super.name,super.description);
 
		System.out.println("Please input the name of the Conference.");
		newConference.setName(sc.next());
		
		System.out.println("Please input a description of the Conference");
		newConference.setDescription(sc.next());
 	
		System.out.println("Please add the details of the speakers.");
		newConference.setConferenceSpeakers(sc.next());
		
		System.out.println("Please specify the sponsor for the Conference.");
		newConference.setSponsor(sc.next());
		
		newConference.createConference(newConference.getName(), newConference.getDescription(), newConference.getConferenceSpeakers(), newConference.getSponsor());
		
		StringBuilder msg = new StringBuilder();
		
		msg.append(eventType + " " + newConference.getName() +  " has been added.");	
		
		System.out.println(msg);
		
	}
	
 
	@Override
	public void list() {
		// TODO Auto-generated method stub
		
		try {
		ArrayList<Conferences> list = conferenceList();
 
		if(list.size()<=0) {
			System.out.println("No conferences available yet.");
		}else {
			for(Conferences i : list) {
				System.out.println(i.toString());
			}
		}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("No conferences available yet.");
			sc.next();
		}
		
		
	}


	@Override
	public void edit() {
		
		sc.useDelimiter("\r?\n");

		list();
 
		ArrayList<Conferences> list = conferenceList();
		
		Integer Index;
		
		while(true) {
		
		System.out.println("Please enter the Event ID for the Conference to update.");
		Integer ID = sc.nextInt();
		
		final List<Integer> lookupEventList = list.stream()
	  			.map(Conferences::getEventId)
	  			.collect(Collectors.toList());
	
		
		if(lookupEventList.contains(ID)) {
			Index=lookupEventList.indexOf(ID);
			break;
			
		}else {
			System.out.println("Please input a valid Venue ID");
			sc.next();
		}
		}


		System.out.println("Which property do you want to modify?");
		
		System.out.println("1. Event Name: "+ list.get(Index).getName());
		System.out.println("2. Description: "+list.get(Index).getDescription());
		System.out.println("3. Conference Speakers: "+ list.get(Index).getConferenceSpeakers());
		System.out.println("4. Conference Sponsor: "+ list.get(Index).getSponsor());
		 
		Integer cursor = sc.nextInt();
		
		String newString;

		 
		int exit =0;
		
        while(exit==0) {
		try {
		
		switch(cursor) {
		
		case 1:
			System.out.println("Please enter a new Event Name:");
			newString = sc.next();
			list.get(Index).setName(newString);
			exit=1;
			break;
		case 2:
			System.out.println("Please enter a new desription:");
			newString = sc.next();
			list.get(Index).setDescription(newString);
			exit=1;
			break;
		case 3:
			System.out.println("Please enter new Conference Speakers:");
			newString = sc.next();
			list.get(Index).setConferenceSpeakers(newString);
			exit=1;
			break;
		case 4:
			System.out.println("Please enter the Sponsor:");
			newString = sc.next();
			list.get(Index).setSponsor(newString);
			exit=1;
			break;
 
		default:
			System.out.println("Please enter a valid value");
			break;
		}
		
		}catch(InputMismatchException e) {
			e.printStackTrace();
			sc.next();
		}

        }
        
        conferenceUpdate(list.get(Index).getEventId(), list.get(Index).getName(), list.get(Index).getDescription(), list.get(Index).getConferenceSpeakers(), list.get(Index).getSponsor());
        
 
	}


	

	@Override
	public void delete() {
 
		sc.useDelimiter("\r?\n");
		
		list();
		
		System.out.println("Please enter the Event ID for the Conference to delete.");
		
		final ArrayList<Conferences> list = conferenceList();
		
		Integer ID = sc.nextInt();
		
		final List<Integer> lookupEventList = list.stream()
		  			.map(Conferences::getEventId)
		  			.collect(Collectors.toList());
		
		Integer eventIdx;
		
		
		if(lookupEventList.contains(ID)) {
			eventIdx=lookupEventList.indexOf(ID);
			deleteConference(lookupEventList.get(eventIdx));			
			StringBuilder msg = new StringBuilder();
			
			msg.append(eventType + ": " +list.get(eventIdx).getName()+ " with Event ID: " + ID + " has been removed.");
			
			System.out.println(msg);
			
		}else {
			System.out.println("Please enter a valid ID");
		}
		
	}

 
	

	@Override
	public void addDate() {
		
		sc.useDelimiter("\r?\n");


		list();
		
		final ArrayList<Conferences> list = conferenceList();
 	
		final List<Integer> lookupEventList = list.stream()
		  			.map(Conferences::getEventId)
		  			.collect(Collectors.toList());
		
		
		System.out.println("Please enter the Event ID for the Conference.");
		
		Integer ID ;

		
		while(true) {
			
			try {
			ID = sc.nextInt();
			
			
			if(lookupEventList.contains(ID)) { 
				break;
			}else {
				System.out.println("Please enter a valid ID");
			}
			}catch(InputMismatchException e) {
				e.printStackTrace();
				System.out.println("Please input a valid ID");
				sc.next();
			}
			}
		
	
		System.out.println("Please specify a date for the Conference in the format dd/MM/yyyy.");
 
		String Date;
		while(true) {
		
		
		try {
			Date = InputValidation.StringToDate(sc.next());
			if(true) {
				break;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Expected date format is dd/MM/yyyy please re-enter");
	
		}
	
		}
		
		Venue.viewVenues();
		
		final ArrayList<Integer> venueIDList = dbGetVenues();		
 
		System.out.println("Please specify a venue for the Conference.");
		
		Integer VenueSelection;
		
		Integer venueIdx;
		
		
		while(true) {
			
			VenueSelection =sc.nextInt();
			
			try {
			if(venueIDList.contains(VenueSelection)) {
				venueIdx=venueIDList.indexOf(VenueSelection);
				break;
			}else {
				System.out.println("Please input a valid venue ID");
			}
			}catch(InputMismatchException e){
				e.printStackTrace();
				System.out.println("Please input a valid venue ID");
				sc.next();
			}
			
		}
		
		Price.viewPrices();
		
		ArrayList<Integer> priceList = dbGetPrices();
		
		System.out.println("Please specify a price for the Conference.");
		
		Integer PriceSelection;
		
		Integer priceIdx;
		
		while(true) {
			
			PriceSelection =sc.nextInt();
			
			try {
			if(priceList.contains(PriceSelection)) {
				priceIdx=priceList.indexOf(PriceSelection);
				break;
			}else {
				System.out.println("Please input a valid price ID");
			}
			}catch(InputMismatchException e){
				e.printStackTrace();
				System.out.println("Please input a valid price ID");
				sc.next();
			}
			
		}
		
		newDate(Date,ID,venueIDList.get(venueIdx), priceList.get(priceIdx));
		
		
		StringBuilder msg = new StringBuilder();
		
		msg.append(eventType + " for date :" + Date + "has been added");
 
		System.out.println(msg);

	}

	
	
 
 
	@Override
	public void removeDate() {


		sc.useDelimiter("\r?\n");
		
		listDates();
		
		ArrayList<Conferences> conferenceList = conferenceList();
		
		final List<Integer> conferenceListIdx = conferenceList.stream()
	  			.map(Conferences::getEventId)
	  			.collect(Collectors.toList());
		
		
		System.out.println("Please enter the Event ID for the Conference.");
		
		Integer EventID;
		Integer eventIdx;
		
		while(true) {
			
			try {
			
			EventID = sc.nextInt();
			
			if(conferenceListIdx.contains(EventID)) {
				eventIdx=conferenceListIdx.indexOf(EventID);
				
				break;
			}else {
				System.out.println("Event ID not found. Try Again.");
			}
			
			}catch(InputMismatchException e) {
				e.printStackTrace();
				System.out.println("Please input a valid Event ID.");
				sc.next();
			}
			
		}
		
		
		System.out.println("Please enter the Date you want to remove.");
		
		
		ArrayList<Conferences> conferenceDateList = conferencesDateList();
		
		final List<String> conferenceDateListIdx = conferenceDateList.stream()
	  			.map(Conferences::getEventDate)
	  			.collect(Collectors.toList());
		
		String Date;
		Integer DateIdx;
		
		while(true) {
		
		try {
				
			Date = InputValidation.StringToDate(sc.next());
		
			
			if(conferenceDateListIdx.contains(Date)) {
				DateIdx=conferenceDateListIdx.indexOf(Date);
				break;
			}else {
				System.out.println("Date not found");
			}
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("Expected date format is dd/MM/yyyy please re-enter");
		 
		}
		}
 
		deleteDate(conferenceListIdx.get(eventIdx),conferenceDateList.get(DateIdx).getEventDate());
 
		StringBuilder msg = new StringBuilder();
		
		msg.append(eventType + " for date :" + conferenceDateList.get(DateIdx).getEventDate() + "has been removed.");
		
		System.out.println(msg);
		
	}
 
 
	

	@Override
	public void listDates() {
		// TODO Auto-generated method stub
		
		 try {
		 ArrayList<Conferences> list = conferencesDateList();
		 
		 if(list.get(0).getName().isBlank()) 
			{
				System.out.println("No listing dates are available yet for this event - please check back soon.");
			}else{
				System.out.println(String.format("%-10s %-20s %-40s %-20s %-15s %-30s", "Option", "Event ID", "Event Name","Event Date", "Price", "Venue"));	
				for(int i = 0;i < list.size();i++) 
				{	
				System.out.format("%-10s %-20s %-40s %-20s %-15s %-30s\n",i,list.get(i).getEventId(),list.get(i).getName(),list.get(i).getEventDate(),list.get(i).getPrice(),list.get(i).getVenueName());
				}	
			}
		 }catch(IndexOutOfBoundsException e) {
			 System.out.println("No dates available yet.");
		
		 } 

	 
	}
	
	
	private void createConference(String name, String description, String conferenceSpeakers, String sponsor) {
		 
		String SQL1 = "INSERT INTO EVENTS (name, description) VALUES ('"+name+"','"+description+"');";
		String SQL2 = "INSERT INTO CONFERENCES (event_id, speakers, sponsor) VALUES ((SELECT MAX(event_id) from EVENTS), '"+conferenceSpeakers+"','"+sponsor+"');";
		database.create(SQL1, SQL2);
	}
	
	private void deleteConference(Integer ID) {
		String SQL = "DELETE FROM CONFERENCES WHERE event_id="+ID+";";
		database.delete(SQL);
		
	}
	
	private void newDate(String date, Integer ID, Integer venue_id, Integer price_id) {
		String SQL = "INSERT INTO DATES (event_date, event_id, venue_id, price_id) VALUES ('"+date+"',"+ID+","+venue_id+","+price_id+");";
		database.addDate(SQL);
	}
	
	private void deleteDate(Integer ID, String Date ) {
		String SQL = "DELETE FROM dates WHERE event_id = "+ID+" and event_date='"+Date+"';";
		database.removeDate(SQL);
 
		
	}
	
	
	private static ArrayList<Conferences> conferenceList() {
		
		String SQL = "SELECT a.event_id,a.name, a.description,b.conference_id, b.speakers,b.sponsor FROM events a inner join conferences b on a.event_id = b.event_id;";
		
		ArrayList<Conferences> list = new ArrayList<Conferences>();
		
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		 	ResultSet rs = statement.executeQuery(SQL);
		  	
		  	list.clear();

		  	while(rs.next())
		  	{	
		  		
		  		Conferences conference = new Conferences();
		  		
		  		
		  		
		  		conference.setEventId(rs.getInt("event_id"));
		  		conference.setName(rs.getString("name"));
		  		conference.setDescription(rs.getString("description"));
		  		conference.setConference_id(rs.getInt("conference_id"));
		  		conference.setConferenceSpeakers(rs.getString("speakers"));
		  		conference.setSponsor(rs.getString("sponsor"));
		  		
		  		
		  		list.add(conference);
		  		
		  	}
		  	
          connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 	
        return list;
		
	}
	
	private static ArrayList<Conferences> conferencesDateList() {
		
		ArrayList<Conferences> list = new ArrayList<Conferences>();
		
		String SQL = "SELECT a.event_id, a.name, a.description, c.event_date, d.venue_id,d.venue_name, e.price_id,e.price FROM events a INNER JOIN conferences b ON a.event_id  = b.event_id INNER JOIN dates c ON b.event_id = c.event_id INNER JOIN venues d ON c.venue_id = d.venue_id INNER JOIN prices e ON c.price_id = e.price_id;"; 
			
		
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		 	ResultSet rs = statement.executeQuery(SQL);
		  	
		  	list.clear();
		  	
		  	while(rs.next())
		  	{	
		  		
		  		Conferences conference = new Conferences();

		  		conference.setEventId(rs.getInt("event_id"));
		  		conference.setName(rs.getString("name"));
		  		conference.setDescription(rs.getString("description"));
		  		conference.setEventDate(rs.getString("event_date"));
		  		conference.setVenueId(rs.getInt("venue_id"));
		  		conference.setVenueName(rs.getString("venue_name"));
		  		conference.setPriceId(rs.getInt("price_id"));
		  		conference.setPrice(rs.getInt("price"));
		  		list.add(conference);
		  	}

		  	
          connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 	
        return list;
		
	}
	
	private void conferenceUpdate(Integer event_id, String name, String description, String conferenceSpeakers, String sponsor) {
		// TODO Auto-generated method stub
		
			String SQL = ("UPDATE events SET name ='"+name+ "', description ='"+description+"' WHERE event_id="+event_id+";");
			
			String SQL1 = ("UPDATE conferences SET speakers ='"+conferenceSpeakers+ "', sponsor='"+sponsor+"' WHERE event_id="+event_id+";");
	        try {
	        	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
	  		  	Statement statement = connection.createStatement();
	            statement.executeUpdate(SQL);
	            statement.executeUpdate(SQL1);
	            connection.close();
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } 
	}
	

	
private static ArrayList<Integer> dbGetVenues() {
		
		ArrayList<Integer> venueIdList = new ArrayList<Integer>();
        
		String SQL = ("SELECT venue_id from venues;");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		    statement.setQueryTimeout(30); 
  		  	ResultSet rs = statement.executeQuery(SQL);
  		  	
  		  	venueIdList.clear();
  		  	
  		  	while(rs.next())
  		  	{
  		  	venueIdList.add(rs.getInt("venue_id"));
  		  	}
  		  	connection.close();
          }
        catch(SQLException e)
        {
          e.printStackTrace(System.err);
        }
		return venueIdList;		
	}
	
	private static ArrayList<Integer> dbGetPrices() {
		
		ArrayList<Integer> priceIdList = new ArrayList<Integer>();
        
		String SQL = ("SELECT price_id from prices;");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		    statement.setQueryTimeout(30); 
  		  	ResultSet rs = statement.executeQuery(SQL);
  		  	
  		  	priceIdList.clear();
  		  	
  		  	while(rs.next())
  		  	{
  		  	priceIdList.add(rs.getInt("price_id"));
  		  	}
  		  	connection.close();
          }
        catch(SQLException e)
        {
          e.printStackTrace(System.err);
        }
		return priceIdList;		
	}
	
	




}
