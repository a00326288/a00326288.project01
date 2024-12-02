package com.a00326288.project01;
import java.util.ArrayList;
import java.util.Scanner;

public class Concerts extends AbstractEvent {

	final private String eventType ="Concert";
	private Integer concertId;
	private String bandName;
	private String bandMembers;
	private String genre;
	
	
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
 

	public Concerts() {
		// TODO Auto-generated constructor stub
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	public Integer getConcertID() {
		return concertId;
	}


	public String getBandName() {
		return bandName;
	}

	private void setBandName(String bandName) {
		this.bandName= bandName;
	}

	public String getBandMembers() {
		return bandMembers;
	}

	private void setBandMembers(String bandMembers ) {
		this.bandMembers = bandMembers ;
	}

	public String getGenre() {
		return genre;
	}

	private void setGenre(String genre) {
		this.genre = genre;
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
		

		String SQL1 = "INSERT INTO EVENTS (description, name) VALUES ('"+super.name+"','"+super.description+"');";
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
		
		for(Concerts i : list) {
		 System.out.println(i.toString());
		}
	}

	@Override
	public void edit() {
		// TODO Auto-generated method stub
		list();
		
		
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
		
		Venue.viewVenues();
		
		System.out.println("Please specify a venue for the Concert.");
		
		sc.nextInt();
		
		Price.getPrices();
		
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
		
		 String SQL = "SELECT a.event_id, a.event_date, b.name FROM dates a inner join events b on a.event_id = b.event_id inner join concerts c on a.event_id = c.event_id;"; 
			
		 ArrayList<CustomType> pairs = DBA.dblistDates(SQL);
		
		  for (CustomType pair : pairs) { 
	            System.out.println("EventID: " + pair.getID() + " Date: " + pair.getDate() + " Name: " +pair.getName()); 
	        } 
		  
		 
		
	}

 

}
