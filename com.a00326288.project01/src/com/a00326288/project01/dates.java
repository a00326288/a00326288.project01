package com.a00326288.project01;

import java.time.LocalDate;
import java.util.stream.Collectors;

public class dates {

	public static void Main(String[] args) {
		
		String eventStartDate ="10/10/2023";
		String eventEndDate = "10/11/2023";
		
		dbAddEventDates(eventStartDate,eventEndDate);
	}
	
	public dates() {
		// TODO Auto-generated constructor stub
	}


	private static void dbAddEventDates(String eventStartDate, String eventEndDate) {
		// TODO Auto-generated method stub
			
		LocalDate EventStartDate = LocalDate.parse(eventStartDate);
		LocalDate EventEndDate = LocalDate.parse(eventEndDate);
  	 
	 	System.out.println(EventStartDate.datesUntil(EventEndDate).collect(Collectors.toList()));
	 	
	 
	
	}
	
}
