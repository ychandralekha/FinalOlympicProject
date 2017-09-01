package com.cts.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Olympic_User")
public class OlympicUserPojo {
@Id
private String username;
private String password;

private String firstName;
private String lastName;

private String datePicker;
private String email;
private String phoneNumber;

private String role;
private int status;
private int display;

public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getDatePicker() {
	return datePicker;
}
public void setDatePicker(String datePicker) {
	this.datePicker = datePicker;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public int getDisplay() {
	return display;
}
public void setDisplay(int display) {
	this.display = display;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

}
