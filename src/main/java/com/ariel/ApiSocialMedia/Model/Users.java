package com.ariel.ApiSocialMedia.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
		uniqueConstraints = {
				@UniqueConstraint(columnNames = {"username"})
		}
)
public class Users {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
    
	@NotNull(message = "Name is mandatory")
	private String name;

	@NotNull(message = "Last name is mandatory")
	private String lastName;
	
	@NotNull(message = "Username is mandatory")
	private String username;
	
	@NotNull(message = "Password is mandatory")
	private String password;
	
	private Boolean state;
	

	@PrePersist
	public void init(){
		state = true;
	}

	@PreUpdate
	public void update(){
		state = true;
	}
}
