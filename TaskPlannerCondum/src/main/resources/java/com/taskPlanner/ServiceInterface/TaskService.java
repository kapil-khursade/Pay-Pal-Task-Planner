package com.taskPlanner.ServiceInterface;

import com.taskPlanner.Beans.Status;
import com.taskPlanner.Beans.Task;
import com.taskPlanner.DTO.OutputMessage;
import com.taskPlanner.Exceptions.TaskException;

public interface TaskService {

	public OutputMessage createTaskForTheSprintInProject(Task task)throws TaskException;
	public OutputMessage deleteTaskById(Integer taskId)throws TaskException;
	public OutputMessage updateTaskStatus(Integer taskId, Status newStatus)throws TaskException;
	public OutputMessage reassignTaskToAnotherTeamMember(Integer taskId, Integer memnerId)throws TaskException;
	
}
