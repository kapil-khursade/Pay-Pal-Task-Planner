package com.taskPlanner.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskPlanner.Beans.Member;
import com.taskPlanner.Beans.Project;

public interface ProjectDAO extends JpaRepository<Project, Integer>{

	Optional<List<Project>> findByCreatedBy(Member creator);

}
