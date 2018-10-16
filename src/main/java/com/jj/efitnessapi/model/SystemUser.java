package com.jj.efitnessapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.jj.efitnessapi.model.constants.Profile;

@Entity
@Table(name = "system_user")
public class SystemUser extends Person {

	private static final long serialVersionUID = 4352764025292636644L;
	
	@NotBlank
	@Column(nullable = false)
	private String login;
	
	@NotBlank
	@Column(nullable = false)
	private String password;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Profile profile;

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

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}
