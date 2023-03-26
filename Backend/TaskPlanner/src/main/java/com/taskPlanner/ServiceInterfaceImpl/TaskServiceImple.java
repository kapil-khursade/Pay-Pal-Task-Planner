package com.taskPlanner.ServiceInterfaceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskPlanner.Beans.Member;
import com.taskPlanner.Beans.Project;
import com.taskPlanner.Beans.Sprint;
import com.taskPlanner.Beans.Status;
import com.taskPlanner.Beans.Task;
import com.taskPlanner.Beans.Team;
import com.taskPlanner.DAO.MemberDAO;
import com.taskPlanner.DAO.SprintDAO;
import com.taskPlanner.DAO.TaskDAO;
import com.taskPlanner.DAO.TeamDAO;
import com.taskPlanner.DTO.OutputMessage;
import com.taskPlanner.Exceptions.MemberException;
import com.taskPlanner.Exceptions.TaskException;
import com.taskPlanner.ServiceInterface.TaskService;

@Service
public class TaskServiceImple implements TaskService{

	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private SprintDAO sprintDAO;
	@Autowired
	private TaskDAO taskDAO;
	@Autowired
	private TeamDAO teamDAO;
	
	@Override
	public OutputMessage createTaskForTheSprintInProject(Task task)
			throws TaskException {
		// TODO Auto-generated method stub
		Optional<Sprint> sprint = sprintDAO.findById(task.getSprint().getSprintId());
		if(sprint.isEmpty())throw new TaskException("Sprint Does Not Exist");
		
		Project project = sprint.get().getProjectSprint();
		Optional<Team> team = teamDAO.findByTeamOfProject(project);
		List<Member> teamMember = team.get().getTeamMember();
		
		Optional<Member> assigne = memberDAO.findById(task.getAssignedTo().getMemberId());
		if(assigne.isEmpty())throw new TaskException("Member Does Not Exist");
		
		if(!teamMember.contains(assigne.get()))throw new TaskException("Member Is Not Part Of Team");
		task.setSprint(sprint.get());
		task.setAssignedTo(assigne.get());
		taskDAO.save(task);
	
		return new OutputMessage("Task Cerated");
	}

	@Override
	public OutputMessage deleteTaskById(Integer taskId) throws TaskException {
		// TODO Auto-generated method stub
		Optional<Task> taskGet = taskDAO.findById(taskId);
		if(taskGet.isEmpty())throw new TaskException("Task Does Not Exist");
		taskDAO.delete(taskGet.get());
		return new OutputMessage("Task Deleted");
	}

	@Override
	public OutputMessage updateTaskStatus(Integer taskId, Status newStatus) throws TaskException {
		// TODO Auto-generated method stub
		Optional<Task> taskGet = taskDAO.findById(taskId);
		if(taskGet.isEmpty())throw new TaskException("Task Does Not Exist");
		Task task = taskGet.get();
		task.setStatus(newStatus);
		taskDAO.save(task);
		return new OutputMessage("Task Status Updated");
	}

	@Override
	public OutputMessage reassignTaskToAnotherTeamMember(Integer taskId, Integer memberId) throws TaskException {
		// TODO Auto-generated method stub
		Optional<Task> taskGet = taskDAO.findById(taskId);
		if(taskGet.isEmpty())throw new TaskException("Task Does Not Exist");
		Task task = taskGet.get();
		Optional<Member> member = memberDAO.findById(memberId);
		if(member.isEmpty())throw new TaskException("Member Does Not Exist");
		task.setAssignedTo(member.get());
		taskDAO.save(task);
		return new OutputMessage("Task Reassigned");
	}

}
