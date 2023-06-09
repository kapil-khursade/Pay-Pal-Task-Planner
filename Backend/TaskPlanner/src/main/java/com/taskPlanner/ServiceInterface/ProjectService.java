package com.taskPlanner.ServiceInterface;

import java.util.List;

import com.taskPlanner.Beans.Project;
import com.taskPlanner.Beans.Status;
import com.taskPlanner.DTO.OutputMessage;
import com.taskPlanner.Exceptions.ProjectException;

public interface ProjectService {

	public OutputMessage createProject(Project project, Integer creatorId)throws ProjectException;
	public OutputMessage deleteProjectById(Integer projectId) throws ProjectException;
	public OutputMessage updateProjectStatus(Integer projectId, Status newStatus)throws ProjectException;
	public List<Project> getProjectByCreatorsId(Integer creatorId)throws ProjectException;
	
}
