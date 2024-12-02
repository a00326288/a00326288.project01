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
	
	public Conferences(Integer event_id,String name, String description, int conferenceId, String conferenceSpeakers, String sponsor) {
		super();
		super.event_id = event_id;
		super.name = name;
		super.description = description;
		this.conference_id = conferenceId;
		this.conferenceSpeakers = conferenceSpeakers;
		this.sponsor = sponsor;
 
	}
 
	public Conferences() {
 	}

 
	private int getConference_id() {
		return conference_id;
	}
 
	private void setConference_id(int conference_id) {
		this.conference_id = conference_id;
	}



	private void setConferenceSpeakers(String conferenceSpeakers) {
		this.conferenceSpeakers = conferenceSpeakers;
	}

	private String getConferenceSpeakers() {
		return conferenceSpeakers;
	}

	
	private String getSponsor() {
		return sponsor;
	}


	private void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
 

	@Override
	public String toString() {
		return "Conference [conferenceSpeakers=" + conferenceSpeakers + ", sponsor=" + sponsor + ", conference_id="
				+ conference_id + ", event_id=" + event_id + ", name=" + name + ", description=" + description + "]";
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
		
		String SQL1 = "INSERT INTO EVENTS (description, name) VALUES ('"+super.name+"','"+super.description+"');";
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
		
		for(Conferences i : list) {
		 System.out.println(i.toString());
	
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
		
		System.out.println("Please enter the Event ID for the Conference.");
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
		
		String SQL = "SELECT a.event_id, a.id, a.event_date, b.name FROM dates a inner join events b on a.event_id = b.event_id inner join conferences c on a.event_id = c.event_id;"; 
		
		 ArrayList<CustomType> pairs = DBA.dblistDates(SQL);
		
		  for (CustomType pair : pairs) { 
	            System.out.println("EventID: " + pair.getID() + " Date: " + pair.getDate() + " Name: " +pair.getName()); 
	        } 
		 
	}
 
}
