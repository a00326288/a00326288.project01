package com.a00326288.project01;

import java.util.List;

public class Conference extends Event {

	private List<String> conferenceSpeakers;
	private String companyName;
	private String companyIndustry;
	private String companyAddress;
	
	
	public Conference(int eventId, String eventName, String eventDescription,String eventDate, Integer venue_id, String venueName, Integer price_id, Integer price, List<String> conferenceSpeakers) {
		super(eventId, eventName, eventDescription, eventDate, eventId,venueName, price_id, price);
		
		this.conferenceSpeakers = conferenceSpeakers;
		this.companyName = companyName;
		this.companyIndustry = companyIndustry;
		this.companyAddress = companyAddress;
		
		
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public List<String> getConferenceSpeakers() {
		return conferenceSpeakers;
	}




	public void setConferenceSpeakers(List<String> conferenceSpeakers) {
		this.conferenceSpeakers = conferenceSpeakers;
	}




	public String getCompanyName() {
		return companyName;
	}




	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}




	public String getCompanyIndustry() {
		return companyIndustry;
	}




	public void setCompanyIndustry(String companyIndustry) {
		this.companyIndustry = companyIndustry;
	}




	public String getCompanyAddress() {
		return companyAddress;
	}




	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}





	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	public static void createConference() {
		
		
		
	}
	
	

}
