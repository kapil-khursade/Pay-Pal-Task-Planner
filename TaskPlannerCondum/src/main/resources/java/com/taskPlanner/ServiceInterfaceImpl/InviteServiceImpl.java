package com.taskPlanner.ServiceInterfaceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.taskPlanner.Beans.Member;
import com.taskPlanner.Beans.Project;
import com.taskPlanner.Beans.Team;
import com.taskPlanner.DAO.MemberDAO;
import com.taskPlanner.DAO.ProjectDAO;
import com.taskPlanner.DAO.TeamDAO;
import com.taskPlanner.DTO.OutputMessage;
import com.taskPlanner.Exceptions.TeamException;
import com.taskPlanner.ServiceInterface.InviteService;

public class InviteServiceImpl implements InviteService{

	@Autowired
	private ProjectDAO projectDAO;
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private TeamDAO teamDAO;
	
	@Override
	public OutputMessage joinTeamOnInvitation(Integer projectId, Integer memberId) throws TeamException {
		// TODO Auto-generated method stub
		Optional<Project> project = projectDAO.findById(projectId);
		if(project.isEmpty())throw new TeamException("Project Does Not Exist");
		
		Optional<Team> teamGet = teamDAO.findByTeamOfProject(project.get());
		if(teamGet.isEmpty())throw new TeamException("Team Does Not Exist");
		Team team = teamGet.get();
		List<Member> teamMember = team.getTeamMember();
		
		Optional<Member> member = memberDAO.findById(memberId);
		if(member.isEmpty())throw new TeamException("Member Does Not Exist");
		if(teamMember.contains(member.get()))throw new TeamException("Already Part Of The Team");
		
		teamMember.add(member.get());
		team.setTeamMember(teamMember);
		teamDAO.save(team);
		return new OutputMessage("You Joined The Team");
	}

}
