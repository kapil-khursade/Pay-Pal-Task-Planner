package com.taskPlanner.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskPlanner.Beans.Task;

public interface TaskDAO extends JpaRepository<Task, Integer>{

}
