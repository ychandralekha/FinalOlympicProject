package com.cts.pojo;

public class SearchFilter {
private Integer startYear;
private Integer endYear;
private String athlete;
private String sport;
private String country;
private String gender;
private String sortSelect;

public Integer getStartYear() {
	return startYear;
}
public void setStartYear(Integer startYear) {
	this.startYear = startYear;
}
public Integer getEndYear() {
	return endYear;
}
public void setEndYear(Integer endYear) {
	this.endYear = endYear;
}
public String getAthlete() {
	return athlete;
}
public void setAthlete(String athlete) {
	this.athlete = athlete;
}
public String getSport() {
	return sport;
}
public void setSport(String sport) {
	this.sport = sport;
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
public String getSortSelect() {
	return sortSelect;
}
public void setSortSelect(String sortSelect) {
	this.sortSelect = sortSelect;
}
@Override
public String toString() {
	return "startYear=" + startYear + ", endYear=" + endYear
			+ ", athlete=" + athlete + ", sport=" + sport + ", country="
			+ country + ", gender=" + gender + ", sortSelect=" + sortSelect;
}

}
