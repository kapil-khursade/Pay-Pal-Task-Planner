package com.taskPlanner.ServiceInterface;

import com.taskPlanner.Beans.Sprint;
import com.taskPlanner.Beans.Status;
import com.taskPlanner.DTO.OutputMessage;
import com.taskPlanner.Exceptions.SprintException;

public interface SprintService {

	public OutputMessage addSprintToProject(Sprint sprint)throws SprintException;
	public OutputMessage deleteSprintById(Integer sprintId)throws SprintException;
	public OutputMessage updateSprintStatus(Integer sprintId, Status newStatus)throws SprintException;
	
}
