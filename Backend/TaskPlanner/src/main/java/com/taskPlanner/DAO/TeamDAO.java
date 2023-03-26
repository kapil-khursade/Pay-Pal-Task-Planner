package com.taskPlanner.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskPlanner.Beans.Project;
import com.taskPlanner.Beans.Team;

public interface TeamDAO extends JpaRepository<Team, Integer>{

	Optional<Team> findByTeamOfProject(Project project);

}
