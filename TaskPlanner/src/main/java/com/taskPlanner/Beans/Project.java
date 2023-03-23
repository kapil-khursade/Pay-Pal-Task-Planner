package com.taskPlanner.Beans;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer projectId;
	@OneToOne
	private Member createdBy;
	@OneToOne
	private Team team;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	private Status status;
	@OneToOne
	private Member projectHead;
	
}
