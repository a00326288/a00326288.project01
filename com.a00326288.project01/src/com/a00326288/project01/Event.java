package com.a00326288.project01;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Event {

	private static Scanner sc = new Scanner(System.in);
	int event_id;
	String name="";
	String description="";

	Event() {	
	}
	
	Event(Integer event_id, String name, String description) {
		 this.event_id = event_id;
		 this.name = name;
		 this.description = description;
		 
	}

	public static Integer menu(String type, String usertype) {
	 
		while(true) {
 
		if(usertype == "User") {
			
			try {
			 	int selection = 0;
				System.out.println("---------------------------");
				System.out.println("-Choose from the following options -");
				System.out.println("---------------------------\n");
				System.out.println("1 - List "+type);
				System.out.println("2 - List Dates for "+type);
				System.out.println("3 - Make Bookings for "+type);
				System.out.println("4 - View My Bookings for "+type);
				System.out.println("5 - Return to Main Menu");
				selection = sc.nextInt();
				if(selection < 1 || selection > 5) {
					System.out.println("Please input value between valid range.");
				}else {
					return selection;
				}
			
			}catch(InputMismatchException e) {
				e.printStackTrace();
				System.out.println("Please input value between valid range.");
				sc.next();
			}	
			
		}else {
			
		try {
		 	int selection = 0;
			System.out.println("---------------------------");
			System.out.println("-Choose from the following options -");
			System.out.println("---------------------------\n");
			System.out.println("1 - List "+type);
			System.out.println("2 - Create "+ type);
			System.out.println("3 - Edit "+type);
			System.out.println("4 - Delete "+type);
			System.out.println("5 - List Dates for "+type);
			System.out.println("6 - Add Dates for "+type);
			System.out.println("7 - Remove Dates for "+type);
			System.out.println("8 - Add Bookings for "+type);
			System.out.println("9 - View Bookings for "+type);
			System.out.println("10 - Cancel Bookings for "+type);
			System.out.println("11 - Return to Main Menu");
			selection = sc.nextInt();
			if(selection < 1 || selection > 11) {
				System.out.println("Please input value between valid range.");
			}else {
				return selection;
			}
		
		}catch(InputMismatchException e) {
			e.printStackTrace();
			System.out.println("Please input value between valid range.");
			sc.next();
		}
		}
		}
	}

	public abstract void list();
	public abstract void create();
	public abstract void edit();
	public abstract void delete();
	public abstract void listDates();
	public abstract void addDate();
	public abstract void removeDate();
 

}
