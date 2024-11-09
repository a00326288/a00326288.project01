package com.a00326288.project01;

import java.util.Scanner;

public class EventDetails extends Events

{
	
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	
	
	EventDetails(Integer eventId){
		super();
	
		
	
		
		
	}




	public static void EventDetails(int i) {
		// TODO Auto-generated method stub
		
		int eventIdx = 0;

		// Loop adds several Parties to the cave's party list
		//cave.parties.add(new Party("FirstParty")); // all anonymously added
		//cave.parties.add(new Party("SecondParty"));
		//cave.parties.add(new Party("ThirdParty"));

		for (Events e : eventlist) {
		    if (e.getEventId().equals(i)) {
		     eventIdx=eventlist.indexOf(e);
		    break;
		    }
		}
		
		System.out.println("Below are the details of the event;");
		
		System.out.println(eventlist.get(eventIdx).getEventName());
		System.out.println(eventlist.get(eventIdx).getEventDescription());
		System.out.println(eventlist.get(eventIdx).getEventStartDate());
		System.out.println(eventlist.get(eventIdx).getEventEndDate());
		System.out.println(eventlist.get(eventIdx).getEventPrice());
		
		menu();
		
		
	}

	
	
}
