package com.taskPlanner.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskPlanner.Beans.Project;
import com.taskPlanner.Beans.Sprint;

public interface SprintDAO extends JpaRepository<Sprint, Integer> {

	Optional<List<Sprint>> findByProjectSprint(Project project);

}
