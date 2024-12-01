package com.a00326288.project01;

import java.util.List;
import java.util.Scanner;

public class Conference extends Event {

	private static Scanner sc = new Scanner(System.in);
	private List<String> conferenceSpeakers;
	private String companyName;
	private String companyIndustry;
	private String companyAddress;
	
	
	public Conference(int eventId, String eventName, String eventDescription,String eventDate, Integer venue_id, String venueName, Integer price_id, Integer price, List<String> conferenceSpeakers) {
		super(eventId, eventName, eventDescription);
		this.conferenceSpeakers = conferenceSpeakers;
		this.companyName = companyName;
		this.companyIndustry = companyIndustry;
		this.companyAddress = companyAddress;
		
		
		// TODO Auto-generated constructor stub
	}
 
	
	private List<String> getConferenceSpeakers() {
		return conferenceSpeakers;
	}

	private void setConferenceSpeakers(List<String> conferenceSpeakers) {
		this.conferenceSpeakers = conferenceSpeakers;
	}

	private String getCompanyName() {
		return companyName;
	}

	private void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	private String getCompanyIndustry() {
		return companyIndustry;
	}

	private void setCompanyIndustry(String companyIndustry) {
		this.companyIndustry = companyIndustry;
	}


	private String getCompanyAddress() {
		return companyAddress;
	}


	private void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	public void createEvent() {
		
		
		sc.useDelimiter("\r?\n");
		
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
		

	 //	Conference Conference = new Conference(0,eventname,eventDescription,null,null,null,null,null, conferenceSpeakers);
	//	dbCreateEvent(Conference);
		System.out.println("Event has been created! Press enter to return to Main Menu.");
	}		
	
	private static void dbCreateConference(Conference conference) {
		
	}
	

}
