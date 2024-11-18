package com.a00326288.project01;

import java.sql.Date;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class Booking {

	private static Scanner sc = new Scanner(System.in);
	private int booking_id;
	private Date booking_dte;
	private int num_of_tickets;
	private int event_id;
	private int venue_id;
	private int user_id;
	private String event_date;
	private String cardNumber;
	
	
	public Booking() {
		// TODO Auto-generated constructor stub

	}
	
	public Booking(int booking_id, Date booking_dte, int num_of_seats) {
		super();
		this.booking_id = booking_id;
		this.booking_dte = booking_dte;
		this.num_of_tickets = num_of_tickets;
		this.event_id = event_id;
		this.venue_id = venue_id;
		this.user_id = user_id;
		this.event_date = event_date;
		this.cardNumber = cardNumber;
		
		
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sc.useDelimiter("\r?\n");
	}

	public int getBooking_id() {
		return booking_id;
	}

	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}

	public Date getBooking_dte() {
		return booking_dte;
	}

	public void setBooking_dte(Date booking_dte) {
		this.booking_dte = booking_dte;
	}

	public int getEvent_id() {
		return event_id;
	}

	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}

	public int getVenue_id() {
		return venue_id;
	}

	public void setVenue_id(int venue_id) {
		this.venue_id = venue_id;
	}

	public int getNum_of_tickets() {
		return num_of_tickets;
	}

	public void setNum_of_tickets(int num_of_tickets) {
		this.num_of_tickets = num_of_tickets;
	}
 
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(booking_dte, booking_id, event_id, num_of_tickets, venue_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Booking other = (Booking) obj;
		return Objects.equals(booking_dte, other.booking_dte) && booking_id == other.booking_id
				&& event_id == other.event_id && num_of_tickets == other.num_of_tickets && venue_id == other.venue_id;
	}

	public static void modifyBooking() {
		// TODO Auto-generated method stub
		
	}

	public static void createBooking() {
		// TODO Auto-generated method stub
		Booking booking = new Booking();
		
		System.out.println();
		
		Event.dbGetEvents();
		
		System.out.println("-----------------------------");
        System.out.println("- Book Event -");
        System.out.println("-----------------------------\n");
        
		System.out.println("Please input the ID of the event you wish to book:");
		
		booking.setEvent_id(sc.nextInt());
		
		System.out.println();
		
		Venue.dbGetVenues();
				
		System.out.println("Please input the ID of the venue from the available venue options above:");
		
		booking.setVenue_id(sc.nextInt());
		
		
		System.out.println("Please input the ID of the corresponding dates for your booking: ");
		
		booking.setEventDate(sc.next());
		
		System.out.println("Available number of tickets for this date at this venue are : ");
		
		System.out.println("Please input the number of tickets you want : ");
		
		booking.setNum_of_tickets(sc.nextInt());
		
		System.out.println("Not enough tickets please try at a later time or try another venue/date : ");
		
		System.out.println("The total cost of your booking is : ");
		
		
		
		System.out.println("Please input the card number for the booking guarntee : ");
		
		
		
		System.out.println("Thanks your booking ref is : ");
		
		System.out.println();
		
		
		
		
	}

	private void setEventDate(String next) {
		// TODO Auto-generated method stub
		
	}

	public static void cancelBooking() {
		// TODO Auto-generated method stub
		
	}

	public static void viewBooking() {
		// TODO Auto-generated method stub
		
	}

}
