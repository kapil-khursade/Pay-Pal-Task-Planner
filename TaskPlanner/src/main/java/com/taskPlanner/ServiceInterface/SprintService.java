package com.taskPlanner.ServiceInterface;

import java.util.List;

import com.taskPlanner.Beans.Sprint;
import com.taskPlanner.Beans.Status;
import com.taskPlanner.DTO.OutputMessage;
import com.taskPlanner.Exceptions.SprintException;

public interface SprintService {

	public OutputMessage addSprintToProject(Sprint sprint, Integer projectId)throws SprintException;
	public OutputMessage deleteSprintById(Integer sprintId)throws SprintException;
	public OutputMessage updateSprintStatus(Integer sprintId, Status newStatus)throws SprintException;
	public List<Sprint> getAllSprintByProjectId(Integer projectId)throws SprintException;
}
