package com.a00326288.project01;

public class CustomType {

	private Integer event_id ;
	private String event_name;
	private String event_description;
	private String event_date;
	private Integer venue_id;
	private String venue_name;
	private Integer price_id;
	private Integer price;

	public CustomType(Integer event_id, String event_name, String event_description){
		this.event_id = event_id;
		this.event_name = event_name;
		this.event_date = event_description;
 
		
		
	}
 
	
	public CustomType(Integer event_id, String event_name, String event_description, String event_date, Integer venue_id, String venue_name, Integer price_id, Integer price){
		this.event_id = event_id;
		this.event_name = event_name;
		this.event_description = event_description;
		this.event_date = event_date;
		this.venue_id = venue_id;
		this.venue_name = venue_name;
		this.price_id = price_id;
		this.price = price;
	}


	public CustomType() {
	
	}


	public int getEvent_id() {
		return event_id;
	}


	public void setEvent_id(Integer event_id) {
		this.event_id = event_id;
	}


	public String getEvent_name() {
		return event_name;
	}


	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}


	public String getEvent_description() {
		return event_description;
	}


	public void setEvent_description(String event_description) {
		this.event_description = event_description;
	}


	public String getEvent_date() {
		return event_date;
	}


	public void setEvent_date(String event_date) {
		this.event_date = event_date;
	}


	public int getVenue_id() {
		return venue_id;
	}


	public void setVenue_id(Integer venue_id) {
		this.venue_id = venue_id;
	}


	public String getVenue_name() {
		return venue_name;
	}


	public void setVenue_name(String venue_name) {
		this.venue_name = venue_name;
	}


	public int getPrice_id() {
		return price_id;
	}


	public void setPrice_id(int price_id) {
		this.price_id = price_id;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	
	
	
	
	
	
		
}
