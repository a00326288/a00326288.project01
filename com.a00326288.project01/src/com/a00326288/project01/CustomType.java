package com.a00326288.project01;

public class CustomType {
	    private int eventId; 
	    private String date;
	    private String name;
	    
	 
	    public CustomType(int eventId, String date, String name) { 
	        this.eventId = eventId; 
	        this.date = date; 
	        this.name = name;
	    } 
	 

	    public int getID() { 
	        return eventId; 
	    } 
	 
	    public String getDate() { 
	        return date; 
	    } 
	    
	    public String getName() {
	    	return name;
	    }
	 
}

