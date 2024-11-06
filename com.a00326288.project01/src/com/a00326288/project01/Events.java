package com.a00326288.project01;

import java.time.LocalDate;
import java.util.Date;

public class Events {
	
	String EventName; 
	LocalDate EventDate;
	
	public static void main(String[] args) {
	 
		Events myEvent = new Events();
		myEvent.setEvent("MyEvent");
		myEvent.setDate(LocalDate.now());
		System.out.println(myEvent.getEvent());
		System.out.println(myEvent.getDate());
	}

	
	public void setEvent(String EventName) {
		this.EventName = EventName;

	}
	
	public void setDate(LocalDate localDate) {
		this.EventDate = localDate;
		
	}
	
	public String getEvent() {
		return this.EventName;

	}
	
	public LocalDate getDate() {
		return this.EventDate;

	}
		
	
	public void printListing() {
		// TODO Auto-generated method stub
		System.out.println(EventName);
	}
	
	public void createEvent() {
		
		
	}
	
	public void updateEvent() {
		
		
	}


	public static void ViewListings() {
		// TODO Auto-generated method stub
		
	}

}
