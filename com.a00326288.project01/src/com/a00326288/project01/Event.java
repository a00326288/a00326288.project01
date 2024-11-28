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
import java.util.Scanner;
import java.util.stream.Collectors;



public class Event {
	
	private Integer eventId;
	public String eventName;
	public String eventDescription;
	private String event_date;
	private Integer venue_id;
	private String venue_name;
	private Integer price_id;
	private Integer price;
	
	
	private static Scanner sc = new Scanner(System.in);
	
	private static ArrayList<Event> eventList = new ArrayList<Event>();
	private static ArrayList<Event> eventDateList = new ArrayList<Event>();
	private static List<Integer> lookupEventList = new ArrayList<Integer>();
	private static List<Integer> lookupEventVenueList = new ArrayList<Integer>();
	private static List<Integer> lookupEventDateVenueList = new ArrayList<Integer>();
	private static List<String> lookupEventDateList = new ArrayList<String>();
	
	
	public static void main(String[] args) {		
	
	}
 
	public Event(int eventId, String eventName, String eventDescription, String event_date,Integer venue_id, String venue_name, Integer price_id, Integer price) {
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventDescription = eventDescription;
		this.event_date=event_date;
		this.venue_id = venue_id;
		this.venue_name = venue_name;
		this.price_id = price_id;
		this.price = price;
	}
	
	public Event() {
		// TODO Auto-generated constructor stub
	}

	public Integer getEventId() {
		return eventId;
	}


	public String getEventName() {
		return eventName;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	
	public String getEvent_date() {
		return event_date;
	}

	public String getVenue_name() {
		return venue_name;
	}

 
	public Integer getPrice() {
		return price;
	}

	public Integer getVenue_id() {
		return venue_id;
	}

	public Integer getPrice_id() {
		return price_id;
	}

	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", eventName=" + eventName + ", eventDescription=" + eventDescription
				+ ", event_date=" + event_date + ", venue_name=" + venue_name + ", price=" + price + "]";
	}

	
	public static void createEvent() {
		
		sc.useDelimiter("\r?\n");
		
		/*
		System.out.println("Input a name for the Event:");
		String eventname = sc.next();
		
		System.out.println("Input a description of the Event:");
		String eventDescription=sc.next();
		
		*/
		
		String eventname ="";
		do {
			System.out.println("Input a name for the Event:"); 
			eventname = sc.next();
		}while(InputValidation.checkfieldNull(eventname)==true);
		
		String eventDescription="";
		do {
			System.out.println("Input a description for the Event:");
			eventDescription=sc.next();
		}while(InputValidation.checkfieldNull(eventDescription)==true);
		
		
		
		Event event = new Event(0,eventname,eventDescription,null,null,null,null,null);
		dbCreateEvent(event);
		System.out.println("Event has been created! Press enter to return to Main Menu.");
	}
	

	public static void createEventDate() {
		
		Integer eventId=null;
		Integer venueId=null;
		String eventDate=null;
		Integer eventPrice=null;
		
		sc.useDelimiter("\r?\n");
		
		dbGetEvents();
		
		System.out.println(String.format("%-10s %-40s %30s" , "Event ID", "Event Name", "Event Description" ));
		
		for(int i = 0; i < eventList.size(); i++) {
			System.out.format("%-10s %-40s %30s\n",eventList.get(i).getEventId(),eventList.get(i).getEventName(),eventList.get(i).getEventDescription());
			
		}
		
		//Integer minEventID = eventList.stream().min(Comparator.comparing(v -> v.getEventId())).get().getEventId();

		//Integer maxEventID = eventList.stream().max(Comparator.comparing(v -> v.getEventId())).get().getEventId();
		
		System.out.println();
		
		System.out.println("Specify Event Id:");
		
		
		while(true) {	
			
			try {
				
				eventId=sc.nextInt();

				//if(eventId!=0 && (eventId >= minEventID && eventId <= maxEventID) && lookupEventList.contains(eventId))
				if(lookupEventList.contains(eventId))	
					{	
						System.out.println("You selected event option: " + eventId);
						break;
					}else
					{	
						System.out.println("No event found for that ID. Please enter another Event ID from the list above.");
					}	
				}catch(InputMismatchException e) {
					e.printStackTrace();
					System.out.println("Input a valid event ID");
					sc.next();
				}
				
			}
		
		

		for(int i=0;i < Venue.dbGetVenues().size();i++) {
			
			System.out.println(Venue.dbGetVenues().get(i).toString());
		}
		
		//Integer minVenueID = Venue.dbGetVenues().stream().min(Comparator.comparing(v -> v.getVenueId())).get().getVenueId();

		//Integer maxVenueID = Venue.dbGetVenues().stream().max(Comparator.comparing(v -> v.getVenueId())).get().getVenueId();
			
		
		System.out.println("Specify a Venue ID:");

		
		while(true) {	
			
			try {


				venueId=sc.nextInt();

				
				//if(venueId!=0 && (venueId >= minVenueID && venueId <= maxVenueID) && lookupVenueList.contains(venueId)) 
				if(lookupEventVenueList.contains(venueId))
					{	
						System.out.println("You selected venue option: " + venueId);
						break;
					}else
					{	
						System.out.println("No venue found for that ID. Please enter another Venue ID from the list above.");
					}	
				}catch(InputMismatchException e) {
					e.printStackTrace();
					System.out.println("Input a valid venue ID");
					sc.next();
				}
				
			}
		
		
		System.out.println("Specify a Date :");
				
		while(true) {

		try {
		
			eventDate=sc.next();
			InputValidation.StringToDate(eventDate);
			break;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Date input was invalid please input in format dd/MM/yyyy");
			sc.next();
		}
		
		}
	
		
		System.out.println(String.format("%-5s %5s" , "ID", "Price " ));
		
		for(int i=0;i < Price.getPrices().size();i++) {
			
			System.out.println(Price.getPrices().get(i).getPriceId() + "\t" + "€" + Price.getPrices().get(i).getEventPrice());
		}
	
		System.out.println("Specify a Price:");
		
		
		//Integer minPriceID = Price.getPrices().stream().min(Comparator.comparing(v -> v.getPriceId())).get().getPriceId();

		//Integer maxPriceID = Price.getPrices().stream().max(Comparator.comparing(v -> v.getPriceId())).get().getPriceId();
		

		while(true) {	
			
			try {
				
				eventPrice=sc.nextInt();
				
				System.out.println("You selected price option: "+eventPrice);
				
				List<Integer> lookupPriceList = Price.getPrices().stream()
                        .map(Price::getPriceId)
                        .collect(Collectors.toList());
				
				//if(eventPrice!=0 && (eventPrice >= minPriceID  && eventPrice <= maxPriceID) && lookupPriceList.contains(eventPrice)) 
				if(lookupPriceList.contains(eventPrice)) 
					{	
						break;
					}else
					{	
						System.out.println("No Price ID found. Please enter another Price ID from the list above.");
					}	
				}catch(InputMismatchException e) {
					e.printStackTrace();
					System.out.println("Input a valid Price ID");
					sc.next();
				}
				
			}
		
		
		try {
		dbCreateEventDate(eventId, eventDate,venueId, eventPrice);
		System.out.println("Event Date Added!");
		}catch(Exception e) {
			System.out.println("Adding date for event failed!");
			
		}
	
		
	}

	public static void viewEvents() {
		
		dbGetEvents();
		
		System.out.println(String.format("%-10s %-40s %30s" , "Event ID", "Event Name", "Event Description" ));
		
		for(int i = 0; i < eventList.size(); i++) {
			System.out.format("%-10s %-40s %30s\n",eventList.get(i).getEventId(),eventList.get(i).getEventName(),eventList.get(i).getEventDescription());
			
		}
		
		System.out.println();
		
		System.out.println("To find out more details on available dates, venues and prices please input an event or enter '0' to exit.");


		//Integer minEventID = eventList.stream().min(Comparator.comparing(v -> v.getEventId())).get().getEventId();

		//Integer maxEventID = eventList.stream().max(Comparator.comparing(v -> v.getEventId())).get().getEventId();

		Integer selection = null;
		
		while(true)
		{
		
		try {
			
		selection = sc.nextInt();
		
		if(selection==0) {
			break;
		}else {
			
			//if(selection!=0 && (selection >= minEventID && selection <= maxEventID) && eventList.get(selection).getEventId()!=null) 
			if(lookupEventList.contains(selection)) 
			{	
				viewEventDates(selection);
				break;
			}else
			{	
				System.out.println("No event found for that ID. Please enter another Event ID from the list above.");
			}
		}
		
		}catch(InputMismatchException e) {
			e.printStackTrace();
			System.out.println("Input a valid event ID");
			sc.next();
		}
	
		
		}

	}
	
	
	public static void viewEventDates(Integer selection) {
		
		dbGetEventDates(selection);
		
		if(eventDateList.get(0).getEvent_date()==null) 
		{
			System.out.println("No listing dates are available yet for this event - please check back soon.");
		}else{
			System.out.println(String.format("%-10s %-30s %-20s %-15s %-30s", "Option", "Event Name","Event Date", "Price", "Venue"));	
			for(int i = 0;i < eventDateList.size();i++) 
			{	
			System.out.format("%-10s %-30s %-20s %-15s %-30s\n",i,eventDateList.get(i).getEventName(),eventDateList.get(i).getEvent_date(),eventDateList.get(i).getPrice(),eventDateList.get(i).getVenue_name());
			}	
		}
		
		UserAccessControl.returnMain();
		
	}
	
	public static void deleteEventDate() {
		// TODO Auto-generated method stub

		dbGetEvents();
		
		System.out.println(String.format("%-10s %-40s %30s" , "Event ID", "Event Name", "Event Description" ));
		
		for(int i = 0; i < eventList.size(); i++) {
			System.out.format("%-10s %-40s %30s\n",eventList.get(i).getEventId(),eventList.get(i).getEventName(),eventList.get(i).getEventDescription());
			
		}
		
		System.out.println();
		
		Integer selection = null;
		
		while(true) {
		try{
			System.out.println("Enter the Event ID to list : ");
			
			selection = sc.nextInt();
		
			if(lookupEventList.contains(selection)) {
	
				break;
			}else {
				System.out.println("Event ID not found.");
			}
			
		}catch(InputMismatchException e){
			System.out.println("Invalid input. Please try again.");
			sc.next();
		}
		}
				
		viewEventDates(selection);

		System.out.println();
		
		System.out.println("Please specify the venue ID to delete.");
		
		Integer deleteVenueOption = null;

		while(true) {
			
			try {
				deleteVenueOption = sc.nextInt();	
				
				if(lookupEventDateVenueList.contains(deleteVenueOption) ) {
				
					break;
				}else {
					System.out.println("Invalid input. Please input a valid option.");
				}
			}catch(InputMismatchException e){
				e.printStackTrace();
				System.out.println("Invalid input. Please input a valid option.");
			}	
		}
		
			
		String deleteDateOption = null;

		System.out.println("Specify a Date :");
		
		while(true) {

		try {
			deleteDateOption=sc.next();
			InputValidation.StringToDate(deleteDateOption);
			if(lookupEventDateList.contains(deleteDateOption)) {
				break;
				}else
					{
					System.out.println("Date input doesn't match any of the events.");
					}
			}
			catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Date input was invalid please input in format dd/MM/yyyy");
			sc.next();
		}
		}
		
		dbDeleteEvent(selection,deleteVenueOption,deleteDateOption);
	
		System.out.println("Date for event deleted!");
	}

	public void modifyEvent() {
		// TODO Auto-generated method stub		
		
		dbGetEvents();
		
		System.out.println(String.format("%-10s %-40s %30s" , "Event ID", "Event Name", "Event Description" ));
		
		for(int i = 0; i < eventList.size(); i++) {
			System.out.format("%-10s %-40s %30s\n",eventList.get(i).getEventId(),eventList.get(i).getEventName(),eventList.get(i).getEventDescription());
			
		}
		
		System.out.println();
		
		sc.useDelimiter("\r?\n");
		
		Integer eventSelection = null; 

		while(true) {
		
		try{
			System.out.println("Enter the Event ID to Modify: ");
			
			eventSelection = sc.nextInt();
		
			if(lookupEventList.contains(eventSelection)) {
				System.out.println("Event ID: " + eventList.get(lookupEventList.indexOf(eventSelection)).getEventId());
				System.out.println("Event Name: "+ eventList.get(lookupEventList.indexOf(eventSelection)).getEventName());
				break;
			}else {
				System.out.println("Event ID not found.");
			}
			
		}catch(InputMismatchException e){
			System.out.println("Invalid input. Please try again.");
			sc.next();
		}
		}
	
		System.out.println();
		
		HashMap<Integer, String> eventmap = new HashMap<>();
		eventmap.put(1, "Event Name");
		eventmap.put(2, "Event Description");
 
		HashMap<Integer, String> eventmapUpdate = new HashMap<>();
		eventmapUpdate.put(1, eventList.get(lookupEventList.indexOf(eventSelection)).getEventName());
		eventmapUpdate.put(2, eventList.get(lookupEventList.indexOf(eventSelection)).getEventDescription());
		
		System.out.println("Which property do you want to modify?");
		
		for(int i=1;i<=eventmap.size();i++) {
			System.out.println(i+". " + eventmap.get(i));
		}
		
		
		Integer cursor = null;
		
		while(true) {
		
			try {
		
				cursor= sc.nextInt();
				if(eventmap.containsKey(cursor)) {
					System.out.println("Please enter new value for "+eventmap.get(cursor)+":");
					break;
				}else{
					System.out.println("Invalid option please retry.");
					}
		
		
		}catch(InputMismatchException e) {
			System.out.println("Invalid input please try again.");
			sc.next();
		}
		}

		String newval = "";
		
		while(true) {
			
			try {
				newval = sc.next();
				if(InputValidation.checkfieldNull(newval)==true) {
					
				}else {
					break;
				}
				
			}catch(InputMismatchException e){
				e.printStackTrace();
			}
		}
		 
		
		eventmapUpdate.put(cursor, newval);
		dbUpdateEvent(eventmapUpdate,eventSelection);
		System.out.println("Update Complete! Press enter to return to Main Menu.");
		
		UserAccessControl.returnMain();
	}

	
	public static void deleteEvent() {
		// TODO Auto-generated method stub
			
		dbGetEvents();
		//call to get events because if not done then eventList doesn't exist.
		
		System.out.println(String.format("%-10s %-40s %30s" , "Event ID", "Event Name", "Event Description" ));
		
		for(int i = 0; i < eventList.size(); i++) {
			System.out.format("%-10s %-40s %30s\n",eventList.get(i).getEventId(),eventList.get(i).getEventName(),eventList.get(i).getEventDescription());
			
		}
		
		System.out.println("Please enter the ID of the event to delete:");

		Integer eventSelection = null;
		
		while(true) {
		try {
			eventSelection = sc.nextInt();
			
			if(lookupEventList.contains(eventSelection)) {		
			break;
			}else {
				System.out.println("Incorect ID. Please input the correct ID.");
			}
		}catch(InputMismatchException e) {
			e.printStackTrace();
			System.out.println("Incorect ID. Please input the correct ID.");
			sc.next();
		}
		}
		
		dbDeleteEvent(eventSelection);
		
		System.out.println("Delete Complete! Press enter to return to Main Menu.");
		UserAccessControl.returnMain();
		
	}
	

	private static void dbDeleteEvent(Integer eventSelection) {
		// TODO Auto-generated method stub
		
		
		String SQL = ("DELETE FROM events WHERE event_id="+eventSelection+";");
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
	
	private static void dbDeleteEvent(Integer eventSelection, Integer venueOption, String eventDate) {
		// TODO Auto-generated method stub
		
		String SQL = ("DELETE FROM dates WHERE event_id="+eventSelection+" and venue_id="+venueOption+" and event_date='"+eventDate+"';");
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



	public static ArrayList<Event> dbGetEvents() {
		// TODO Auto-generated method stub
		
        String SQL = ("SELECT * FROM events;");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		    statement.setQueryTimeout(30); 
  		  	ResultSet rs = statement.executeQuery(SQL);
  		  	
  		  	eventList.clear();
  		  	
  		  	
  		  	while(rs.next())
  		  	{
  		  		Event event = new Event(rs.getInt("event_id"),rs.getString("event_name"),rs.getString("event_description"),null,null,null,null,null);
  		  		
  		  		eventList.add(event);
  		  	}
  		  	
  		  	lookupEventList = Event.eventList.stream()
                  .map(Event::getEventId)
                  .collect(Collectors.toList());
			
  		  	lookupEventVenueList = Venue.dbGetVenues().stream()
	                .map(Venue::getVenueId)
	                .collect(Collectors.toList());
			
            connection.close();
          }
        catch(SQLException e)
        {
          e.printStackTrace(System.err);
        }
		
		return eventList;
		
	}
	
	
	
	public static ArrayList<Event> dbGetEventDates(Integer selection) {
		// TODO Auto-generated method stub
		
        String SQL = ("SELECT DISTINCT a.event_id, a.event_name, b.event_date,c.venue_id, c.venue_name, d.price_id, d.price  FROM events a left join dates b on a.event_id = b.event_id left join venues c on b.venue_id = c.venue_id left join prices d on b.price_id = d.price_id where a.event_id="+selection+";");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		    statement.setQueryTimeout(30); 
  		  	ResultSet rs = statement.executeQuery(SQL);
  		  	
  		  	eventDateList.clear();
  		  	
  		  	while(rs.next())
  		  	{	
  		  		
  		  		Event event = new Event(rs.getInt("event_id"),rs.getString("event_name"),null, rs.getString("event_date"),rs.getInt("venue_id"),rs.getString("venue_name"),rs.getInt("price_id"), rs.getInt("price"));			
  		  		eventDateList.add(event);
  		  	}
  		  	
  		  	lookupEventDateVenueList = eventDateList.stream()
	                .map(Event::getVenue_id)
	                .collect(Collectors.toList());
			
  		  	lookupEventDateList = eventDateList.stream()
  		  			.map(Event::getEvent_date)
  		  			.collect(Collectors.toList());
  		  	
            connection.close();
          }
        catch(SQLException e)
        {
          e.printStackTrace(System.err);
        }
		return eventDateList;
	}

	private static void dbCreateEventDate(Integer eventId, String eventDate, Integer venueId, Integer priceId) {
		// TODO Auto-generated method stub
		String SQL = ("INSERT INTO dates (event_date, event_id, venue_id, price_id) VALUES ('"+eventDate+"',"+eventId+","+venueId+","+priceId+");");
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

	private static void dbCreateEvent(Event event) {
		
		String SQL = ("INSERT INTO events (event_name, event_description) VALUES ('"+event.getEventName()+"','"+event.getEventDescription()+"');");
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
	
	private static void dbUpdateEvent(HashMap<Integer, String> eventmapUpdate,Integer eventSelection) {
		// TODO Auto-generated method stub
		
		
		String SQL = ("UPDATE events SET event_name ='"+eventmapUpdate.get(1)
				+ "', event_description='"+eventmapUpdate.get(2)+"' WHERE event_id="+eventSelection+";");
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
}
