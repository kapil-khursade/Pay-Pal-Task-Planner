package com.taskPlanner.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskPlanner.Beans.Project;

public interface ProjectDAO extends JpaRepository<Project, Integer>{

}
