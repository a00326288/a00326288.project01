package com.a00326288.project01;

import java.sql.Date;
import java.util.Objects;
import java.util.Scanner;

public class Booking {

	private static Scanner sc = new Scanner(System.in);
	private int booking_id;
	private Date booking_dte;
	private int num_of_seats;
	
	public Booking() {
		// TODO Auto-generated constructor stub

	}
	
	public Booking(int booking_id, Date booking_dte, int num_of_seats) {
		super();
		this.booking_id = booking_id;
		this.booking_dte = booking_dte;
		this.num_of_seats = num_of_seats;
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

	public int getNum_of_seats() {
		return num_of_seats;
	}

	public void setNum_of_seats(int num_of_seats) {
		this.num_of_seats = num_of_seats;
	}

	@Override
	public int hashCode() {
		return Objects.hash(booking_dte, booking_id, num_of_seats);
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
				&& num_of_seats == other.num_of_seats;
	}

	@Override
	public String toString() {
		return "Bookings [booking_id=" + booking_id + ", booking_dte=" + booking_dte + ", num_of_seats=" + num_of_seats
				+ "]";
	}

	
	
	public static void bookEvent() {
		// TODO Auto-generated method stub
		System.out.println("-----------------------------");
        System.out.println("- Book Event -");
        System.out.println("-----------------------------\n");
        
		System.out.println("Please input the ID of the event you wish to book:");
		System.out.println();
		
		int option = sc.nextInt();
		
		
	
	}
	
	public static void bookConference() {
		// TODO Auto-generated method stub
		System.out.println("-----------------------------");
        System.out.println("- Book Event -");
        System.out.println("-----------------------------\n");
        
		System.out.println("Please input the ID of the event you wish to book:");
		System.out.println();
		
		int option = sc.nextInt();
		
		
		
		
	
	}

	public static void modifyBooking() {
		// TODO Auto-generated method stub
		
	}

	public static void createBooking() {
		// TODO Auto-generated method stub
		
	}

	public static void cancelBooking() {
		// TODO Auto-generated method stub
		
	}

	public static void viewBooking() {
		// TODO Auto-generated method stub
		
	}

}
