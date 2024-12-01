package com.a00326288.project01;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;



public class Event {

	private Integer eventId;
	private String eventName;
	private String eventDescription;
	private String eventDate;
	private int venueId;
	private String venueName;
	private int priceId;
	private int price;
	
	
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {		
	
	}
 
	public Event() {
		this.eventId = null;
		this.eventName = null;
		this.eventDescription = null;

	}

	public Event(Integer eventId, String eventName, String eventDescription) {
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventDescription = eventDescription;
		
	}
	
	public Event(int eventId, String eventName, String eventDescription, String eventDate, int venueId, String venueName, int priceId, int price) {
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventDescription = eventDescription;
		this.eventDate = eventDate ;
		this.venueId = venueId;
		this.venueName = venueName;
		this.priceId = priceId;
		this.price = price;
	}

	Integer getEventId() {
		return eventId;
	}

	private void setEventName(String eventName) {
		this.eventName = eventName;
	}

	String getEventName() {
		return eventName;
	}

	String getEventDescription() {
		return eventDescription;
	}

	private void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	
	String getEventDate() {
		return eventDate;
	}

	private void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	int getVenueId() {
		return venueId;
	}

	private void setVenueId(int venueId) {
		this.venueId = venueId;
	}

	String getVenueName() {
		return venueName;
	}

	private void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	int getPriceId() {
		return priceId;
	}

	private void setPriceId(int priceId) {
		this.priceId = priceId;
	}


	int getPrice() {
		return price;
	}


	private void setPrice(int price) {
		this.price = price;
	}

	
	
	
	public void createEvent() {
		
		
		Event createEvent = new Event();
		
		sc.useDelimiter("\r?\n");
		
		do {
			System.out.println("Input a name for the Event:"); 
					
			createEvent.setEventName(sc.next());
  
		}while(InputValidation.checkfieldNull(createEvent.getEventName())==true);


		do {
			System.out.println("Input a description for the Event:");
			createEvent.setEventDescription(sc.next());
		}while(InputValidation.checkfieldNull(createEvent.getEventDescription())==true);
		
	
		DBA.dbCreateEvent(createEvent);
		System.out.println("Event has been created! Press enter to return to Main Menu.");
	}
	
	

	

	public void viewEvents() {
		
		ArrayList<Event> eventList = DBA.dbGetEvents();
		
		System.out.println(String.format("%-10s %-40s %30s" , "Event ID", "Event Name", "Event Description" ));
		
		for(int i = 0; i < eventList.size(); i++) {
			System.out.format("%-10s %-40s %30s\n",eventList.get(i).getEventId(),eventList.get(i).getEventName(),eventList.get(i).getEventDescription());
			
		}
		
		System.out.println();
		
		System.out.println("To find out more details on available dates, venues and prices please input an event or enter '0' to exit.");
 
		
		List<Integer> lookupEventList = eventList.stream()
				.map(Event::getEventId)
				.collect(Collectors.toList());
		
		Integer selection = null;
		
		while(true)
		{
		
		try {
			
		selection = sc.nextInt();
		
		if(selection==0) {
			break;
		}else {
			
 
			if(lookupEventList.contains(selection)) 
			{	
				//EventDates eventDate = new EventDates(t=, eventDescription, eventDescription, eventDescription, selection, eventDescription, selection, selection);
				
				Event.viewEventDates(selection);
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
	
	
	
	
	public void modifyEvent() {
		// TODO Auto-generated method stub		
		
		ArrayList<Event> eventList = DBA.dbGetEvents();
		
		System.out.println(String.format("%-10s %-40s %30s" , "Event ID", "Event Name", "Event Description" ));
		
		for(int i = 0; i < eventList.size(); i++) {
			System.out.format("%-10s %-40s %30s\n",eventList.get(i).getEventId(),eventList.get(i).getEventName(),eventList.get(i).getEventDescription());
			
		}
		
		System.out.println();
		
		sc.useDelimiter("\r?\n");
		
		List<Integer> lookupEventList = eventList.stream()
				.map(Event::getEventId)
				.collect(Collectors.toList());
				
				
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
		DBA.dbUpdateEvent(eventmapUpdate,eventSelection);
		System.out.println("Update Complete! Press enter to return to Main Menu.");
		
		UAM.returnMain();
	}
	

	

	
	public void deleteEvent() {
		// TODO Auto-generated method stub
			
		ArrayList<Event> eventList =DBA.dbGetEvents();
		//call to get events because if not done then eventList doesn't exist.
		
		System.out.println(String.format("%-10s %-40s %30s" , "Event ID", "Event Name", "Event Description" ));
		
		for(int i = 0; i < eventList.size(); i++) {
			System.out.format("%-10s %-40s %30s\n",eventList.get(i).getEventId(),eventList.get(i).getEventName(),eventList.get(i).getEventDescription());
			
		}
		
		System.out.println("Please enter the ID of the event to delete:");

		List<Integer> lookupEventList = eventList.stream()
				.map(Event::getEventId)
				.collect(Collectors.toList());
			
		
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
		
		DBA.dbDeleteEvent(eventSelection);
		
		System.out.println("Delete Complete! Press enter to return to Main Menu.");
		UAM.returnMain();
		
	}

 
	public void createEventDate() {
		
		Integer eventId=null;
		Integer venueId=null;
		String eventDate=null;
		Integer eventPrice=null;
		
		sc.useDelimiter("\r?\n");
		
		ArrayList<Event> eventList = DBA.dbGetEvents();
		
		System.out.println(String.format("%-10s %-40s %30s" , "Event ID", "Event Name", "Event Description" ));
		
		for(int i = 0; i < eventList.size(); i++) {
			System.out.format("%-10s %-40s %30s\n",eventList.get(i).getEventId(),eventList.get(i).getEventName(),eventList.get(i).getEventDescription());
			
		}
		
		
		System.out.println();
		
		System.out.println("Specify Event Id:");
		
		List<Integer> lookupEventList = eventList.stream()
				.map(Event::getEventId)
				.collect(Collectors.toList());
		
		while(true) {	
			
			try {
				
				eventId=sc.nextInt();

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
		
		

		for(int i=0;i < DBA.dbGetVenues().size();i++) {
			
			System.out.println(DBA.dbGetVenues().get(i).toString());
		}	
		
		System.out.println("Specify a Venue ID:");

		
		List<Integer> lookupEventVenueList = DBA.dbGetVenues().stream()
				.map(Venue::getVenueId)
				.collect(Collectors.toList());
		
		while(true) {	
			
			try {


				venueId=sc.nextInt();
 
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
		DBA.dbCreateEventDate(eventId, eventDate,venueId, eventPrice);
		System.out.println("Event Date Added!");
		}catch(Exception e) {
			System.out.println("Adding date for event failed!");
			
		}
	
		
	}
	
	
	public void deleteEventDate() {
		// TODO Auto-generated method stub

		ArrayList<Event> eventList =DBA.dbGetEvents();
		
		System.out.println(String.format("%-10s %-40s %30s" , "Event ID", "Event Name", "Event Description" ));
		
		for(int i = 0; i < eventList.size(); i++) {
			System.out.format("%-10s %-40s %30s\n",eventList.get(i).getEventId(),eventList.get(i).getEventName(),eventList.get(i).getEventDescription());
			
		}
		
		System.out.println();
		
		List<Integer> lookupEventList = eventList.stream()
				.map(Event::getEventId)
				.collect(Collectors.toList());
		
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
		
		
		List<Integer> lookupEventDateVenueList = DBA.dbGetVenues().stream()
				.map(Venue::getVenueId)
				.collect(Collectors.toList());
		
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
		
		
		ArrayList<Event> EventDateList = DBA.dbGetEventDates(selection);
		
		List<String> lookupEventDateList = EventDateList.stream()
				.map(Event::getEventDate)
				.collect(Collectors.toList());
		
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
		
		DBA.dbDeleteEvent(selection,deleteVenueOption,deleteDateOption);
	
		System.out.println("Date for event deleted!");
	}
	
	
	public static ArrayList<Event> viewEventDates(Integer selection) {
		
		ArrayList<Event> eventDateList = DBA.dbGetEventDates(selection);
		
		if(eventDateList.get(0).getEventDate()==null) 
		{
			System.out.println("No listing dates are available yet for this event - please check back soon.");
		}else{
			System.out.println(String.format("%-10s %-30s %-20s %-15s %-30s", "Option", "Event Name","Event Date", "Price", "Venue"));	
			for(int i = 0;i < eventDateList.size();i++) 
			{	
			System.out.format("%-10s %-30s %-20s %-15s %-30s\n",i,eventDateList.get(i).getEventName(),eventDateList.get(i).getEventDate(),eventDateList.get(i).getPrice(),eventDateList.get(i).getVenueName());
			}	
		}
		
		UAM.returnMain();
		return eventDateList;
		
	}

	
}
