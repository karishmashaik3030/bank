package com.bank.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bank.bank.enums.Role;

@Entity
@Table(name="player_details")
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private int id;
private String name;
private int number;
@Enumerated(EnumType.STRING)
private Role role;
private int totalMatches;
private String teamName;
private String state;
@Column(length=700)
private String description;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getNumber() {
	return number;
}
public void setNumber(int number) {
	this.number = number;
}
public Role getRole() {
	return role;
}
public void setRole(Role role) {
	this.role = role;
}
public int getTotalMatches() {
	return totalMatches;
}
public void setTotalMatches(int totalMatches) {
	this.totalMatches = totalMatches;
}
public String getTeamName() {
	return teamName;
}
public void setTeamName(String teamName) {
	this.teamName = teamName;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
@Override
public String toString() {
	return "Player [id=" + id + ", name=" + name + ", number=" + number + ", role=" + role + ", totalMatches="
			+ totalMatches + ", teamName=" + teamName + ", state=" + state + ", description=" + description + "]";
}

}
