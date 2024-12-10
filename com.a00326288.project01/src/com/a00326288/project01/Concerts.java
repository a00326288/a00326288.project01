package com.a00326288.project01;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

non-sealed class Concerts extends Event implements database {

	private static Scanner sc = new Scanner(System.in);
	private Integer event_id;
	private String name;
	private String description;
	private String eventDate;
	final private String eventType ="Concert";
	private Integer concertId;
	private String bandName;
	private String bandMembers;
	private String genre;
	private Integer venueId;
	private String venueName;
	private Integer priceId;
	private Integer price;
	
	Concerts() {
	}
	
	Concerts(Integer event_id, String name, String description) {
		// TODO Auto-generated constructor stub
		super(event_id,name,description);
	}
	
	Concerts(int event_id, String name, String description, Integer concertId, String bandName, String bandMembers, String genre) {
	// TODO Auto-generated constructor stub
	super(event_id, name,description);
	this.event_id = event_id;
	this.name = name;
	this.description =description;
	this.bandName = bandName;
	this.bandMembers = bandMembers; 
	this.genre = genre; 
	}


	private void setConcertId(Integer concertId) {
		this.concertId = concertId;
	}

 
	private int getEventId() {
		// TODO Auto-generated method stub
		return event_id;
	}
 
	private void setEventId(Integer event_id) {
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

 
	private String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

 
	private void setDescription(String description) {
		// TODO Auto-generated method stub
		this.description = description;
	}

	
	private String getBandName() {
		return bandName;
	}

	private void setBandName(String bandName) {
		this.bandName= bandName;
	}

	private String getBandMembers() {
		return bandMembers;
	}

	private void setBandMembers(String bandMembers ) {
		this.bandMembers = bandMembers ;
	}

	private String getGenre() {
		return genre;
	}

	private void setGenre(String genre) {
		this.genre = genre;
	}
	 
	private String getEventDate() {
		return eventDate;
	}

	private void setEventDate(String eventDate) {
		this.eventDate = eventDate;
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

	private Integer getPrice() {
		return price;
	}
 
	private void setPriceId(Integer priceId) {
		this.priceId = priceId;
	}

	private void setPrice(Integer price) {
		this.price = price;
	}

	
	@Override
	public String toString() {
		return "Concerts [Event ID:" + event_id + ",  name=" + name+ ", description=" + description + "," + "bandName=" + bandName +", bandMembers=" + bandMembers + ", genre=" + genre + "]";
	}
	
	
	
	@Override
	public void create() {
	
		sc.useDelimiter("\r?\n");
		
		Concerts newConcert = new Concerts(super.event_id,super.name,super.description);
		
		sc.useDelimiter("\r?\n");
		
		System.out.println("Please input the name of the Concert.");
		newConcert.setName(sc.next());
		
		System.out.println("Please input a description of the Concert");
		newConcert.setDescription(sc.next());
 	
		System.out.println("Please input the name of the band.");
		newConcert.setBandName(sc.next());
		
		System.out.println("Please input the members of the band.");
		newConcert.setBandMembers(sc.next());
		
		System.out.println("Please input the genre of the band.");
		newConcert.setGenre(sc.next());
		
		try {
		
		newConcert.createConcert(newConcert.getName(), newConcert.getDescription(), newConcert.getBandName(), newConcert.getBandMembers(), newConcert.getGenre());
		StringBuilder msg = new StringBuilder();
		msg.append(eventType + " " + newConcert.getName() +  " has been added.");	
		System.out.println(msg);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Oops something went wrong.");
			
		}
	}
	
	

	@Override
	public void list() {
		// TODO Auto-generated method stub
		
		try {
		ArrayList<Concerts> list = concertList();
		
		if(list.size()<=0) {
			System.out.println("No concerts available yet.");
		}else {
		
		for(Concerts i : list) {
			System.out.println(i.toString()); 
	
		}
		}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("No concerts available yet.");
			sc.next();
		}
		
	}
	
	@Override
	public void edit() {
		// TODO Auto-generated method stub
		
		sc.useDelimiter("\r?\n");
		
		list();
		
		ArrayList<Concerts> list = concertList();
		
		Integer Index;
		
		while(true) {
		
		System.out.println("Please enter the Event ID for the Concert to update.");
		Integer ID = sc.nextInt();
		
		final List<Integer> lookupEventList = list.stream()
	  			.map(Concerts::getEventId)
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
		System.out.println("3. Band Name: "+ list.get(Index).getBandName());
		System.out.println("4. Band Members: "+ list.get(Index).getBandMembers());
		System.out.println("5. Genre: "+ list.get(Index).getGenre());
		
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
			System.out.println("Please enter the Band Name:");
			newString = sc.next();
			list.get(Index).setBandName(newString);
			exit=1;
			break;
		case 4:
			System.out.println("Please enter the Band Members:");
			newString = sc.next();
			list.get(Index).setBandMembers(newString);
			exit=1;
			break;
			
		case 5:
			System.out.println("Please enter a new genre:");
			newString = sc.next();
			list.get(Index).setGenre(newString);
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
        
        try {
        
        concertUpdate(list.get(Index).getEventId(), list.get(Index).getName(), list.get(Index).getDescription(), list.get(Index).getBandName(), list.get(Index).getBandMembers(), list.get(Index).getGenre());
        System.out.println("Concert updated.");
        }catch(Exception e){
        	e.printStackTrace();
        	System.out.println("Oops something went wrong.");
        	sc.next();
        }
        
	}

	
	@Override
	public void delete() {

		sc.useDelimiter("\r?\n");
		
		list();
		
		System.out.println("Please enter the Event ID for the Concert to delete.");
		
		final ArrayList<Concerts> list = concertList();
		
		Integer ID = sc.nextInt();
		Integer eventIdx;
		
		//Get a list of the Event ID's.
		
		final List<Integer> lookupEventList = list.stream()
		  			.map(Concerts::getEventId)
		  			.collect(Collectors.toList());
		
		try {
			
		if(lookupEventList.contains(ID)) {
			//get the ID entered by the user and then lookup the Index of the value and then delete.
			eventIdx=lookupEventList.indexOf(ID);
			deleteConcert(lookupEventList.get(eventIdx));			
			StringBuilder msg = new StringBuilder();
			msg.append(eventType + ": " +list.get(eventIdx).getName()+ " with Event ID: " + ID + " has been removed.");
			System.out.println(msg);
		}else {
			System.out.println("Please enter a valid ID");
		}
		}catch(Exception e) {
			System.out.println("Oops something went wrong.");
		}
		
	}

	
	

	@Override
	public void addDate() {
		
		sc.useDelimiter("\r?\n");
	 
		list();
		
		final ArrayList<Concerts> list = concertList();
 	
		final List<Integer> lookupEventList = list.stream()
		  			.map(Concerts::getEventId)
		  			.collect(Collectors.toList());
		
		
		System.out.println("Please enter the Event ID for the Concert.");
		
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
			sc.nextLine();
		}
		}
		
		
	
		System.out.println("Please specify a date for the Concert in the format dd/MM/yyyy.");
 
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
			sc.nextLine();
		}
	
		}
		
		Venue.viewVenues();
		
		final ArrayList<Integer> venueIDList = dbGetVenues();		
 
		System.out.println("Please specify a venue for the Concert.");
		
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
		
		System.out.println("Please specify a price for the Concert.");
		
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
		
		newDate(Date,ID, venueIDList.get(venueIdx), priceList.get(priceIdx));
 
		StringBuilder msg = new StringBuilder();
		
		msg.append(eventType + " for date :" + Date + "has been added");
 
		System.out.println(msg);

		
	}
	
	


	@Override
	public void removeDate() {

		sc.useDelimiter("\r?\n");
		
		listDates();
		
		ArrayList<Concerts> concertlist = concertList();
		
		final List<Integer> concertListIdx = concertlist.stream()
	  			.map(Concerts::getEventId)
	  			.collect(Collectors.toList());
		
		
		System.out.println("Please enter the Event ID for the Concert.");
		
		Integer EventID;
		Integer eventIdx;
		
		while(true) {
			
			try {
			
			EventID = sc.nextInt();
			
			if(concertListIdx.contains(EventID)) {
				eventIdx=concertListIdx.indexOf(EventID);
				
				break;
			}else {
				System.out.println("Event ID not found. Try Again.");
			}
			
			}catch(InputMismatchException e) {
				e.printStackTrace();
				System.out.println("Please input a valid Event ID");
				sc.next();
			}
			
		}
		
		
		System.out.println("Please enter the Date you want to remove.");
		
		
		ArrayList<Concerts> concertDateList = concertDateList();
		
		final List<String> concertDateListIdx = concertDateList.stream()
	  			.map(Concerts::getEventDate)
	  			.collect(Collectors.toList());
		
		String Date;
		Integer DateIdx;
		
		while(true) {

		try {
			Date = InputValidation.StringToDate(sc.next());
			if(concertDateListIdx.contains(Date)) {
				DateIdx=concertDateListIdx.indexOf(Date);
				break;
			}else {
				System.out.println("Date not found");
			}
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("Expected date format is dd/MM/yyyy please re-enter");
		
		}
		
		}
 
		deleteDate(concertListIdx.get(eventIdx),concertDateList.get(DateIdx).getEventDate());
 
		StringBuilder msg = new StringBuilder();
		
		msg.append(eventType + " for date :" + concertDateList.get(DateIdx).getEventDate() + "has been removed.");
		
		System.out.println(msg);
		
	}




	@Override
	public void listDates() {
		// TODO Auto-generated method stub
		 
		try {
			 ArrayList<Concerts> list = concertDateList();
			 
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
  
	private void createConcert(String name, String description, String BandName, String BandMembers, String Genre ) {
		String SQL1 = "INSERT INTO EVENTS (name, description) VALUES ('"+name+"','"+description+"');";
		String SQL2 = "INSERT INTO CONCERTS (event_id, band_name, band_members, GENRE) VALUES ((SELECT MAX(event_id) from EVENTS), '"+BandName+"','"+BandMembers+"','"+Genre+"');";
		database.create(SQL1, SQL2);
	}
	
	private void deleteConcert(Integer ID) {
		
		String SQL = "DELETE FROM CONCERTS WHERE event_id="+ID+";";
		database.delete(SQL);
	}
	
	private void newDate(String date, Integer ID, Integer venue_id, Integer price_id) {
		String SQL = "INSERT INTO DATES (event_date, event_id, venue_id, price_id) VALUES ('"+date+"',"+ID+","+venue_id+","+price_id+");";
		database.addDate(SQL);
		
	}
	
	
	private void deleteDate(Integer event_id, String date) {
		String SQL = "DELETE FROM dates WHERE event_id = "+event_id+" and event_date='"+date+"';";
		database.removeDate(SQL);
		
	
	}
	
	
	

	
	private static ArrayList<Concerts> concertList() {
		
		String SQL = "SELECT a.event_id,a.name, a.description,b.concert_id, b.band_name,b.band_members, b.genre FROM events a inner join concerts b on a.event_id = b.event_id;";
		
		
		ArrayList<Concerts> list = new ArrayList<Concerts>();
		
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		 	ResultSet rs = statement.executeQuery(SQL);
		  	
		  	list.clear();
 
		  	while(rs.next())
		  	{	
		  		Concerts concert = new Concerts();
		  		
		  		concert.setEventId(rs.getInt("event_id"));
		  		concert.setName(rs.getString("name"));
		  		concert.setDescription(rs.getString("description"));
		  		concert.setConcertId(rs.getInt("concert_id"));
		  		concert.setBandName(rs.getString("band_name"));
		  		concert.setBandMembers(rs.getString("band_members"));
		  		concert.setGenre(rs.getString("genre"));
		  		
		  		list.add(concert);
		  	}
		  	
          connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 	
        return list;
		
	}
	
	
	private static ArrayList<Concerts> concertDateList() {
		
		String SQL = "SELECT a.event_id, a.name, a.description, c.event_date, d.venue_id,d.venue_name, e.price_id,e.price FROM events a INNER JOIN concerts b ON a.event_id  = b.event_id INNER JOIN dates c ON b.event_id = c.event_id INNER JOIN venues d ON c.venue_id = d.venue_id INNER JOIN prices e ON c.price_id = e.price_id;"; 
	
		
		ArrayList<Concerts> list = new ArrayList<Concerts>();
		
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		 	ResultSet rs = statement.executeQuery(SQL);
		  	
		  	list.clear();
		  	
		  	while(rs.next())
		  	{	
		  		
		  		Concerts concert = new Concerts();
		  		
		  		concert.setEventId(rs.getInt("event_id"));
		  		
		  		concert.setName(rs.getString("name"));
		  		
		  		concert.setDescription(rs.getString("description"));
		  		
		  		concert.setEventDate(rs.getString("event_date"));
		  		
		  		concert.setVenueId(rs.getInt("venue_id"));
		  		
		  		concert.setVenueName(rs.getString("venue_name"));
		  		
		  		concert.setPriceId(rs.getInt("price_id"));
		  		
		  		concert.setPrice(rs.getInt("price"));
		  	 
		  		list.add(concert);
		  	}

		  	
          connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 	
        return list;
		
	}
	
	private void concertUpdate(Integer event_id, String eventName, String eventDescription, String BandName,
			String BandMembers, String Genre) {
		// TODO Auto-generated method stub
	
		String SQL = ("UPDATE events SET name ='"+eventName+ "', description ='"+eventDescription+"' WHERE event_id="+event_id+";");
		
		String SQL1 = ("UPDATE concerts SET band_name ='"+BandName+ "', band_members='"+BandMembers+"', GENRE='"+Genre+"' WHERE event_id="+event_id+";");
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
