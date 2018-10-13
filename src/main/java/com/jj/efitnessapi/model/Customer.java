package com.jj.efitnessapi.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "customer")
public class Customer extends Person {

	private static final long serialVersionUID = -860200076441900067L;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate birth;

	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "beginning_training", nullable = false)
	private LocalDate beginningOfTraining;

	@NotBlank
	@Column(name = "phone_number")
	private String phoneNumber;
	
	private String photo;

	@Embedded
	private Address address;

	public LocalDate getBirth() {
		return birth;
	}

	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}

	public LocalDate getBeginningOfTraining() {
		return beginningOfTraining;
	}

	public void setBeginningOfTraining(LocalDate beginningOfTraining) {
		this.beginningOfTraining = beginningOfTraining;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
