package com.taskPlanner.ServiceInterfaceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskPlanner.Beans.Member;
import com.taskPlanner.Beans.Project;
import com.taskPlanner.Beans.Status;
import com.taskPlanner.DAO.MemberDAO;
import com.taskPlanner.DAO.ProjectDAO;
import com.taskPlanner.DTO.OutputMessage;
import com.taskPlanner.Exceptions.ProjectException;
import com.taskPlanner.ServiceInterface.ProjectService;
@Service
public class ProjectServiceImpl implements ProjectService{
	
	@Autowired
	private ProjectDAO projectDAO;
	@Autowired
	private MemberDAO memberDAO;

	@Override
	public OutputMessage createProject(Project project) throws ProjectException {
		// TODO Auto-generated method stub
		
		Optional<Member> member = memberDAO.findById(project.getCreatedBy().getMemberId());
		if(member.isEmpty())throw new ProjectException("Project Creator Does Not Exist");
		
		Project savedProject = projectDAO.save(project);
		if(savedProject==null)throw new ProjectException("Project Cannot Be Added");
		return new OutputMessage("Project Cerated");
	}

	@Override
	public OutputMessage deleteProjectById(Integer projectId) throws ProjectException {
		// TODO Auto-generated method stub
		Optional<Project> project = projectDAO.findById(projectId);
		if(project.isEmpty())throw new ProjectException("Project Does Not Exist");
		projectDAO.delete(project.get());
		return new OutputMessage("Project Deleted");
	}

	@Override
	public OutputMessage updateProjectStatus(Integer projectId, Status newStatus) throws ProjectException {
		// TODO Auto-generated method stub
		Optional<Project> project = projectDAO.findById(projectId);
		if(project.isEmpty())throw new ProjectException("Project Does Not Exist");
		Project projectWithUpdatedStatus = project.get();
		projectWithUpdatedStatus.setStatus(newStatus);
		projectDAO.save(projectWithUpdatedStatus);
		return new OutputMessage("Project Status Updated");
	}

}
