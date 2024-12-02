package com.a00326288.project01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Conferences extends AbstractEvent{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private static Scanner sc = new Scanner(System.in);
	private String conferenceSpeakers;
	private String sponsor;
	private int conference_id;
	final private String eventType ="Conference";
	private Integer eventId;
	private String name;
	private String description;
	private String eventDate;
	private Integer venueId;
	private String venueName;
	private Integer priceId;
	private Integer price;
	
	
	public Conferences(Integer event_id,String name, String description, int conferenceId, String conferenceSpeakers, String sponsor) {
		super();
		super.event_id = event_id;
		super.name = name;
		super.description = description;
		this.conference_id = conferenceId;
		this.conferenceSpeakers = conferenceSpeakers;
		this.sponsor = sponsor;
 
	}
	
	public Conferences(Integer event_id, String name, String description, String eventDate, int venue_id, String venueName, int price_id, int price) {
	
	}

	public Conferences() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	

	private int getConference_id() {
		return conference_id;
	}
 
	void setConference_id(int conference_id) {
		this.conference_id = conference_id;
	}

	public void setConferenceSpeakers(String conferenceSpeakers) {
		this.conferenceSpeakers = conferenceSpeakers;
	}
 
	public String getConferenceSpeakers() {
		return conferenceSpeakers;
	}

	
	public String getSponsor() {
		return sponsor;
	}


	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
	
	public void setEventDate(String EventDate) {
		this.eventDate = EventDate;
	}


	private Integer getEventId() {
		return event_id;
	}

	void setEventId(Integer event_id) {
		this.event_id = event_id;
	}

	private String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	private String getDescription() {
		return description;
	}

	void setDescription(String description) {
		this.description = description;
	}

	private Integer getVenueId() {
		return venueId;
	}

	void setVenueId(Integer venueId) {
		this.venueId = venueId;
	}

	private String getVenueName() {
		return venueName;
	}

	void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	private Integer getPriceId() {
		return priceId;
	}

	void setPriceId(Integer priceId) {
		this.priceId = priceId;
	}

	private Integer getPrice() {
		return price;
	}

	void setPrice(Integer price) {
		this.price = price;
	}

	private String getEventDate() {
		return eventDate;
	}

	@Override
	public String toString() {
		return "Conference [event_id=" + event_id + " name=" + name + ", description=" + description + " conferenceSpeakers=" + conferenceSpeakers + ", sponsor=" + sponsor + "]";
	}

 
	@Override
	public void create() {
		// TODO Auto-generated method stub
		
		
		sc.useDelimiter("\r?\n");
		
		System.out.println("Please input the name of the Conference.");
		super.name = sc.next();
		
		System.out.println("Please input a description of the Conference");
		super.description = sc.next();
 	
		System.out.println("Please add the details of the speakers.");
		setConferenceSpeakers(sc.next());
		
		System.out.println("Please specify the sponsor for the Conference.");
		setSponsor(sc.next());
		
		String SQL1 = "INSERT INTO EVENTS (name, description) VALUES ('"+super.name+"','"+super.description+"');";
		String SQL2 = "INSERT INTO CONFERENCES (event_id, speakers, sponsor) VALUES ((SELECT MAX(event_id) from EVENTS), '"+getConferenceSpeakers()+"','"+getSponsor()+"');";
		
		DBA.create(SQL1, SQL2);
		
		StringBuilder msg = new StringBuilder();
		
		msg.append(eventType + " " + super.name +  " has been added.");	
		
		System.out.println(msg);
 
	}


	@Override
	public void list() {
		// TODO Auto-generated method stub
		
		String SQL = "SELECT a.event_id,a.name, a.description,b.conference_id, b.speakers,b.sponsor FROM events a inner join conferences b on a.event_id = b.event_id;";
		
		ArrayList<Conferences> list = DBA.conferenceList(SQL);
 
		if(list.size()<1) {
			System.out.println("No conferences.");
		}else {
		for(Conferences i : list) {
			System.out.println(i.toString());
		 
		}
		}
		
	}


	@Override
	public void edit() {
		// TODO Auto-generated method stub
		list();
		
		System.out.println("Please enter the Event ID for the Conference to edit.");
		Integer ID = sc.nextInt();
 
	}


	@Override
	public void delete() {
		// TODO Auto-generated method stub
		list();
		
		
		
		System.out.println("Please enter the Event ID for the Conference to delete.");
		Integer ID = sc.nextInt();
		
		String SQL = "DELETE FROM CONFERENCES WHERE event_id="+ID+";";

		DBA.delete(SQL);
		
		StringBuilder msg = new StringBuilder();
		
		msg.append(eventType + " with Event ID: " + ID + " has been removed.");
		
		System.out.println(msg);
		
	}

 
	@Override
	public void addDate() {
		// TODO Auto-generated method stub
		list();
		
		System.out.println("Please enter the Event ID for the Conference.");
		Integer ID = sc.nextInt();
		
		System.out.println("Please specify a date for the Conference.");
 
		String Date = sc.next();
		
		Venue venues = new Venue();
		
		venues.viewVenues();
		
		System.out.println("Please enter the venue ID for the Concert.");
		
		String venue = sc.next();
		
		Price prices = new Price();
		
		prices.viewPrices();
		
		System.out.println("Please specify a price ID for the Concert.");
		
		int price = sc.nextInt();
		
		
		String SQL = "INSERT INTO DATES (event_date, event_id, venue_id, price_id) VALUES ('"+Date+"',"+ID+","+venue+","+price+");";

		DBA.addDate(SQL);
		
		
		StringBuilder msg = new StringBuilder();
		
		msg.append(eventType + " for date :" + Date + "has been added");
 
		System.out.println(msg);
 
	}

 
	@Override
	public void removeDate() {
		// TODO Auto-generated method stub
		
		listDates();
		
		System.out.println("Please enter the Event ID for the Conference.");
		Integer ID = sc.nextInt();
 
		
		System.out.println("Please enter the Date you want to remove.");
		String Date = sc.next();

		String SQL = "DELETE FROM dates WHERE event_id = "+ID+" and event_date='"+Date+"';";
		String SQL1 = "DELETE FROM events WHERE event_id = "+ID+";";
		DBA.removeDate(SQL);
		DBA.removeDate(SQL1);
		
		StringBuilder msg = new StringBuilder();
		
		msg.append(eventType + " for date :" + Date + "has been removed.");
		
		System.out.println(msg);
		
	}
 

	@Override
	public void listDates() {
		// TODO Auto-generated method stub
		
		
		 String SQL = "SELECT a.event_id, a.name, a.description, c.event_date, d.venue_id,d.venue_name, e.price_id,e.price FROM events a INNER JOIN conferences b ON a.event_id  = b.event_id INNER JOIN dates c ON b.event_id = c.event_id INNER JOIN venues d ON c.venue_id = d.venue_id INNER JOIN prices e ON c.price_id = e.price_id;"; 
			
		 try {
		 ArrayList<Conferences> list = DBA.conferencesDateList(SQL);
		 
		 if(list.get(0).getEventDate().isBlank()) 
			{
				System.out.println("No listing dates are available yet for this event - please check back soon.");
			}else{
				System.out.println(String.format("%-10s %-40s %-20s %-15s %-30s", "Option", "Event Name","Event Date", "Price", "Venue"));	
				for(int i = 0;i < list.size();i++) 
				{	
				System.out.format("%-10s %-40s %-20s %-15s %-30s\n",i,list.get(i).getName(),list.get(i).getEventDate(),list.get(i).getPrice(),list.get(i).getVenueName());
				}	
			}
		 }catch(IndexOutOfBoundsException e) {
			 System.out.println("No dates available yet.");
		
		 } 

	 
	}
}
