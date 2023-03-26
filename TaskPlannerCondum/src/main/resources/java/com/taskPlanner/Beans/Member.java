package com.taskPlanner.Beans;

import java.util.List;

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
	@OneToMany(mappedBy = "sendTo")
	List<Invites> listOfInvites;
	
}
