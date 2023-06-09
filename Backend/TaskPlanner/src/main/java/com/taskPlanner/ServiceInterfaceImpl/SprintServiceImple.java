package com.taskPlanner.ServiceInterfaceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskPlanner.Beans.Project;
import com.taskPlanner.Beans.Sprint;
import com.taskPlanner.Beans.Status;
import com.taskPlanner.DAO.ProjectDAO;
import com.taskPlanner.DAO.SprintDAO;
import com.taskPlanner.DTO.OutputMessage;
import com.taskPlanner.Exceptions.ProjectException;
import com.taskPlanner.Exceptions.SprintException;
import com.taskPlanner.ServiceInterface.SprintService;

@Service
public class SprintServiceImple implements SprintService{

	@Autowired
	private ProjectDAO projectDAO;
	@Autowired
	private SprintDAO sprintDAO;
	
	@Override
	public OutputMessage addSprintToProject(Sprint sprint, Integer projectId) throws SprintException {
		// TODO Auto-generated method stub
		Optional<Project> projectGet = projectDAO.findById(projectId);
		if(projectGet.isEmpty())throw new SprintException("Project Does Not Exist");
		Project project = projectGet.get();
		sprint.setProjectSprint(project);
		
		Sprint savedSprint = sprintDAO.save(sprint);
		if(savedSprint==null)throw new SprintException("Sprint Could Not Be Saved");
		return new OutputMessage("Sprint Cerated");
	}

	@Override
	public OutputMessage deleteSprintById(Integer sprintId) throws SprintException {
		// TODO Auto-generated method stub
		Optional<Sprint> sprintGet = sprintDAO.findById(sprintId);
		if(sprintGet.isEmpty())throw new SprintException("Sprint Does Not Exist");
		Sprint sprint = sprintGet.get();
		sprintDAO.delete(sprint);
		return new OutputMessage("Sprint Deleted");
	}

	@Override
	public OutputMessage updateSprintStatus(Integer sprintId, Status newStatus) throws SprintException {
		// TODO Auto-generated method stub
		Optional<Sprint> sprintGet = sprintDAO.findById(sprintId);
		if(sprintGet.isEmpty())throw new SprintException("Sprint Does Not Exist");
		Sprint sprint = sprintGet.get();
		sprint.setStatus(newStatus);
		sprintDAO.save(sprint);
		return new OutputMessage("Sprint Status Updated");
	}

	@Override
	public List<Sprint> getAllSprintByProjectId(Integer projectId) throws SprintException {
		// TODO Auto-generated method stub
		Optional<Project> projectGet = projectDAO.findById(projectId);
		if(projectGet.isEmpty())throw new SprintException("Project Does Not Exist");
		Project project = projectGet.get();
		
		Optional<List<Sprint>> allSprintOfProjectGet = sprintDAO.findByProjectSprint(project);
		if(allSprintOfProjectGet.isEmpty())throw new SprintException("No Sprint Exist");
		List<Sprint> allSprintOfProject = allSprintOfProjectGet.get();
		return allSprintOfProject;
	}

}
