package com.jj.efitnessapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.jj.efitnessapi.model.constants.Profile;

@Entity
@Table(name = "owner")
public class Owner extends Person {

	private static final long serialVersionUID = 4352764025292636644L;
	
	@Column(nullable = false)
	private String login;
	
	@Column(nullable = false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Profile profiles;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Profile getProfiles() {
		return profiles;
	}

	public void setProfiles(Profile profiles) {
		this.profiles = profiles;
	}

}
