package com.a00326288.project01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Price  {

	private Integer priceId;
	private Integer eventPrice;
	
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	public Price(Integer priceId, Integer eventPrice) {

		this.priceId = priceId;
		this.eventPrice = eventPrice;

	}


	public Price() {
		// TODO Auto-generated constructor stub
	}


	private Integer getPriceId() {
		return priceId;
	}

 

	public Integer getEventPrice() {
		return eventPrice;
	}
 
	
	
	public ArrayList<Price>  viewPrices() {
		
		ArrayList<Price> priceList = getPrices();
		
		System.out.println(String.format("%-10s %20s" , "Price ID","Price"));
		
		for(int i =0; i < priceList.size(); i++) {

			System.out.printf("%-10s %20s\n" , priceList.get(i).getPriceId(),priceList.get(i).getEventPrice());

		}
		return priceList;
		
	}
	
	
	
	public void addPrice() {
		
		Integer newPrice = null;
		
		while(true) {
			
			System.out.println("Please enter a new event price:");
			try {
				newPrice = sc.nextInt();
				dbAddPrice(newPrice);
				System.out.println("Price has been added.");
				UAM.returnMain();
				break;
			}catch(InputMismatchException e) {
				e.printStackTrace();
				System.out.println("Please input a valid number for a price.");
				sc.next();
				
			}
			
		}

		
	}
	
	
	
	public void deletePrice() {
		// TODO Auto-generated method stub
		
		ArrayList<Price> priceList = viewPrices();
		
		List<Integer> priceLookup = priceList.stream()
				.map(Price::getPriceId)
				.collect(Collectors.toList());
		
		Integer priceSelection =null;
		
		while(true) {
			
			try {
				System.out.println("Please input the ID of the price you wish to delete:");
				
				priceSelection = sc.nextInt();
				
				if(priceLookup.contains(priceSelection)) {
					
					dbDeletePrice(priceSelection);
					System.out.println("Price has been deleted.");
					UAM.returnMain();
					break;
				}else {
					
					System.out.println("Invalid Price ID input. Please try again.");
				}
				
				
			}catch(InputMismatchException e) {
				e.printStackTrace();
				System.out.println("Invalid Price ID input. Please try again.");
				sc.next();
			}
			
		}
		
		
	}
	
	private static ArrayList<Price> getPrices() {
		
		ArrayList<Price> priceList = new ArrayList<Price>();
		
		String SQL = ("SELECT * from prices;");
        try {
        	Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
  		  	Statement statement = connection.createStatement();
  		    statement.setQueryTimeout(30); 
  		  	ResultSet rs = statement.executeQuery(SQL);
  		  	
  		  	priceList.clear();
  		  	
  		  	while(rs.next())
  		  	{
  		  
  		  	Price price = new Price(rs.getInt("price_id"), rs.getInt("price"));
  	      
  		  	priceList.add(price);
  	
  
  		  	}
  		  	connection.close();
          }
        catch(SQLException e)
        {
          e.printStackTrace(System.err);
        }	
		return priceList;	
	}
	

	private static void dbDeletePrice(Integer priceSelection) {
		// TODO Auto-generated method stub
		String SQL = ("DELETE FROM prices where price_id="+priceSelection+";");
		try {	
			Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
			Statement statement = connection.createStatement();
			statement.executeUpdate(SQL);
			 connection.close();
			}catch (SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
			} 	
	}


	private static void dbAddPrice(Integer newPrice) {
		// TODO Auto-generated method stub
	
		String SQL = ("INSERT into prices (price) values("+newPrice+");");
	try {	
		Connection connection = DriverManager.getConnection("jdbc:sqlite:src/com/a00326288/project01/db/a00326288.db");
		Statement statement = connection.createStatement();
		statement.executeUpdate(SQL);
		 connection.close();
		}catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
		} 	
		
		
	}
	



}