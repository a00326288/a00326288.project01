package com.a00326288.project01;
import java.util.ArrayList;
import java.util.Scanner;

public class Concerts extends Events {

	final private String eventType ="Concert";
	private Integer concertId;
	private String bandName;
	private String bandMembers;
	private String genre;
	private Integer eventId;
	private String name;
	private String description;
	private String eventDate;
	private Integer venueId;
	private String venueName;
	private Integer priceId;
	private Integer price;
	
	
	private static Scanner sc = new Scanner(System.in);
	
	Concerts(int event_id, String name, String description, Integer concertId, String bandName, String bandMembers, String genre) {
		// TODO Auto-generated constructor stub
	super();
	super.event_id = event_id;
	super.name = name;
	super.description =description;
	this.bandName = bandName;
	this.bandMembers = bandMembers; 
	this.genre = genre; 
	}
 
	public Concerts(int event_id, String name, String description, String eventDate, int venue_id, String venueName, int price_id, int price) {
		
	}

	

	public Concerts() {
		// TODO Auto-generated constructor stub
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	private Integer getConcertID() {
		return concertId;
	}


	public String getBandName() {
		return bandName;
	}

	public void setBandName(String bandName) {
		this.bandName= bandName;
	}

	public String getBandMembers() {
		return bandMembers;
	}

	public void setBandMembers(String bandMembers ) {
		this.bandMembers = bandMembers ;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}


 


	private Integer getConcertId() {
		return concertId;
	}

	void setConcertId(Integer concertId) {
		this.concertId = concertId;
	}

	private Integer getEventId() {
		return eventId;
	}

	void setEventId(Integer eventId) {
		this.eventId = eventId;
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

	private String getEventDate() {
		return eventDate;
	}

	void setEventDate(String eventDate) {
		this.eventDate = eventDate;
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

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
		sc.useDelimiter("\r?\n");
		
		System.out.println("Please input the name of the Concert.");
		super.name = sc.next();
		
		System.out.println("Please input a description of the Concert");
		super.description = sc.next();
 	
		System.out.println("Please input the name of the band.");
		setBandName(sc.next());
		
		System.out.println("Please input the members of the band.");
		setBandMembers(sc.next());
		
		System.out.println("Please input the genre of the band.");
		setGenre(sc.next());
		

		String SQL1 = "INSERT INTO EVENTS (name, description) VALUES ('"+super.name+"','"+super.description+"');";
		String SQL2 = "INSERT INTO CONCERTS (event_id, band_name, band_members, GENRE) VALUES ((SELECT MAX(event_id) from EVENTS), '"+getBandName()+"','"+getBandMembers()+"','"+getGenre()+"');";
		
		DBA.create(SQL1, SQL2);
		
		StringBuilder msg = new StringBuilder();
		
		msg.append(eventType + " " + super.name +  " has been added.");	
		
		System.out.println(msg);
	 
		
	}

	@Override
	public void list() {
		// TODO Auto-generated method stub
		String SQL = "SELECT a.event_id,a.name, a.description,b.concert_id, b.band_name,b.band_members, b.genre FROM events a inner join concerts b on a.event_id = b.event_id;";
				
		ArrayList<Concerts> list = DBA.concertList(SQL);
		
		if(list.size()<1) {
			System.out.println("No concerts.");
		}else {
		
		for(Concerts i : list) {
			System.out.println(i.toString()); 
	
		}
		}
		
	}
	
	@Override
	public void edit() {
		// TODO Auto-generated method stub
		list();
		
		System.out.println("Please enter the Event ID for the Concert to delete.");
		Integer ID = sc.nextInt();
		
	
		
		
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		list();
		
		System.out.println("Please enter the Event ID for the Concert to delete.");
		Integer ID = sc.nextInt();
		
		String SQL = "DELETE FROM CONCERTS WHERE event_id="+ID+";";

		DBA.delete(SQL);
		
		StringBuilder msg = new StringBuilder();
		
		msg.append(eventType + " with Event ID: " + ID + " has been removed.");
		
		System.out.println(msg);
		
	}


	@Override
	public void addDate() {
		// TODO Auto-generated method stub
		list();
		
		System.out.println("Please enter the Event ID for the Concert.");
		Integer ID = sc.nextInt();
		
		System.out.println("Please specify a date for the Concert.");
 
		String Date = sc.next();
		
		Venue venue = new Venue();
		
		venue.viewVenues();
		
		System.out.println("Please specify a venue for the Concert.");
		
		sc.nextInt();
		
		Price price = new Price();
		
		price.viewPrices();
		
		System.out.println("Please specify a price for the Concert.");
		
		sc.nextInt();
		
		
		String SQL = "INSERT INTO DATES (event_date, event_id) VALUES ('"+Date+"',"+ID+");";

		DBA.addDate(SQL);
		
		StringBuilder msg = new StringBuilder();
		
		msg.append(eventType + " for date :" + Date + "has been added");
 
		System.out.println(msg);

		
	}


	@Override
	public void removeDate() {
		// TODO Auto-generated method stub
		
		listDates();
		
		System.out.println("Please enter the Event ID for the Concert.");
		Integer ID = sc.nextInt();
 
		
		System.out.println("Please enter the Date you want to remove.");
		String Date = sc.next();

		String SQL = "DELETE FROM dates WHERE event_id = "+ID+" and event_date='"+Date+"';";
		
		DBA.removeDate(SQL);
		
		StringBuilder msg = new StringBuilder();
		
		msg.append(eventType + " for date :" + Date + "has been removed.");
		
		System.out.println(msg);
		
	}


	@Override
	public void listDates() {
		// TODO Auto-generated method stub
		
		 String SQL = "SELECT a.event_id, a.name, a.description, c.event_date, d.venue_id,d.venue_name, e.price_id,e.price FROM events a INNER JOIN concerts b ON a.event_id  = b.event_id INNER JOIN dates c ON b.event_id = c.event_id INNER JOIN venues d ON c.venue_id = d.venue_id INNER JOIN prices e ON c.price_id = e.price_id;"; 
			
		 ArrayList<Concerts> list = DBA.concertDateList(SQL);
			
		 System.out.println(String.format("%-25s %-15s %-30s %10s" , "Event Name", "Event Date", "Venue", "Price" ));
		 
		 for(int i = 0; i < list.size(); i++) {
				System.out.println("Option "+i+ " : " + list.get(i).getName() + "\t" + list.get(i).getEventDate() + "\t" + list.get(i).getVenueName() + "\t" + list.get(i).getPrice());	
			}
		  
		 
	}

 
}
