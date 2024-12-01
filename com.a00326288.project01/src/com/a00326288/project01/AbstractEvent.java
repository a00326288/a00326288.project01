package com.a00326288.project01;

public abstract class AbstractEvent {

	int eventID;
	String eventName;
	String eventDate;
	
	public abstract void createEvent();
	public abstract void deleteEvent();
	public abstract void updateEvent();
	public abstract void removeEvent();
	
}
