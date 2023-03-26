package com.taskPlanner.Beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer memberId;
	private String firstname;
	private String lastname;
	private String email;
	@Column(unique = true)
	private String username;
	@Column(unique = true)
	private String password;
	@OneToMany(mappedBy = "sendTo", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("sendTo")
	List<Invites> listOfInvites;
	@OneToMany
	@JsonIgnoreProperties("createdBy")
	List<Project> projectWorkingOn;
}
