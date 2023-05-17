package com.gbsolorzano.Taller2_Diferido.Models.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private String ID;
	private String username;	
	private String email; 
	private String name;
	private String password;
	private String hiredate;
	private String rol;
	private String status;

	
	public User(String iD, String username, String email, String name, String password, String status, String hiredate,
			String rol) {
		super();
		ID = iD;
		this.username = username;
		this.email = email;
		this.name = name;
		this.password = password;
		this.status = status;
		this.hiredate = hiredate;
		this.rol = rol;
	}
	
	

	public User() {
		
	}

	public String getID() {
		return ID;
	}
	
	public String findOneById() {
		return ID;
	}
	

	public void setID(String iD) {
		ID = iD;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}


	
	
}