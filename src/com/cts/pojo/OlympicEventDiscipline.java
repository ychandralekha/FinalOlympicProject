package com.cts.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="eventDiscipline")
public class OlympicEventDiscipline{

@Id@GeneratedValue
private int eventId;
private String sport;
private String event;
private String discipline;

@OneToMany(cascade=CascadeType.ALL,mappedBy="eventObject")
private List<OlympicAthlete>athleteList=new ArrayList<OlympicAthlete>();

public List<OlympicAthlete> getAthleteList() {
	return athleteList;
}

public void setAthleteList(List<OlympicAthlete> athleteList) {
	this.athleteList = athleteList;
}
public String getEvent() {
	return event;
}
public void setEvent(String event) {
	this.event = event;
}
public String getSport() {
	return sport;
}
public void setSport(String sport) {
	this.sport = sport;
}

public String getDiscipline() {
	return discipline;
}
public void setDiscipline(String discipline) {
	this.discipline = discipline;
}
public int getEventId() {
	return eventId;
}
public void setEventId(int eventId) {
	this.eventId = eventId;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
			+ ((discipline == null) ? 0 : discipline.hashCode());
	result = prime * result + ((event == null) ? 0 : event.hashCode());
	result = prime * result + eventId;
	result = prime * result + ((sport == null) ? 0 : sport.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	OlympicEventDiscipline other = (OlympicEventDiscipline) obj;
	if (discipline == null) {
		if (other.discipline != null)
			return false;
	} else if (!discipline.equals(other.discipline))
		return false;
	if (event == null) {
		if (other.event != null)
			return false;
	} else if (!event.equals(other.event))
		return false;
	if (eventId != other.eventId)
		return false;
	if (sport == null) {
		if (other.sport != null)
			return false;
	} else if (!sport.equals(other.sport))
		return false;
	return true;
}




}
