package com.cts.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="athlete")
public class OlympicAthlete {
	@Id @GeneratedValue
	private int athleteId;
private String athlete;
private String country;
private String gender;
private String medal;
private String display;

@ManyToOne
@JoinColumn(name="eventId")
private OlympicEventDiscipline eventObject;
@ManyToOne
@JoinColumn(name="year")
private OlympicHost hostObject;


public String getDisplay() {
	return display;
}

public OlympicAthlete() {
}

public OlympicAthlete(int athleteId, String athlete, String country,
		String gender, String medal, OlympicEventDiscipline eventObject,
		OlympicHost hostObject) {
	this.athleteId = athleteId;
	this.athlete = athlete;
	this.country = country;
	this.gender = gender;
	this.medal = medal;
	this.eventObject = eventObject;
	this.hostObject = hostObject;
	this.display="1";
}
public void setDisplay(String display) {
	this.display = display;
}



public int getAthleteId() {
	return athleteId;
}
public void setAthleteId(int athleteId) {
	this.athleteId = athleteId;
}
public String getAthlete() {
	return athlete;
}
public void setAthlete(String athlete) {
	this.athlete = athlete;
}


public OlympicEventDiscipline getEventObject() {
	return eventObject;
}
public void setEventObject(OlympicEventDiscipline eventObject) {
	this.eventObject = eventObject;
}
public OlympicHost getHostObject() {
	return hostObject;
}
public void setHostObject(OlympicHost hostObject) {
	this.hostObject = hostObject;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getMedal() {
	return medal;
}
public void setMedal(String medal) {
	this.medal = medal;
}


}
