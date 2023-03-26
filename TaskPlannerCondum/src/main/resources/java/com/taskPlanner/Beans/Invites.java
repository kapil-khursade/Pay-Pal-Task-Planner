package com.taskPlanner.Beans;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invites {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer inviteId;
	@OneToOne
	private Project project;
	@OneToOne
	private Member sentBy;
	@ManyToOne(cascade = CascadeType.ALL)
	private Member sendTo;
	
}
