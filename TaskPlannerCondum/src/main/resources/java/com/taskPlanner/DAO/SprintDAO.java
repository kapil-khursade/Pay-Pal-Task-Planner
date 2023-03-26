package com.taskPlanner.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskPlanner.Beans.Sprint;

public interface SprintDAO extends JpaRepository<Sprint, Integer> {

}
