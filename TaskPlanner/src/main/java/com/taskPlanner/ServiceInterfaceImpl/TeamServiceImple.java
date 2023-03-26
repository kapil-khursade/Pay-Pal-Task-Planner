package com.taskPlanner.ServiceInterfaceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskPlanner.Beans.Member;
import com.taskPlanner.Beans.Project;
import com.taskPlanner.Beans.Sprint;
import com.taskPlanner.Beans.Task;
import com.taskPlanner.Beans.Team;
import com.taskPlanner.DAO.MemberDAO;
import com.taskPlanner.DAO.ProjectDAO;
import com.taskPlanner.DAO.SprintDAO;
import com.taskPlanner.DAO.TaskDAO;
import com.taskPlanner.DAO.TeamDAO;
import com.taskPlanner.DTO.OutputMessage;
import com.taskPlanner.Exceptions.MemberException;
import com.taskPlanner.Exceptions.ProjectException;
import com.taskPlanner.Exceptions.TeamException;
import com.taskPlanner.ServiceInterface.TeamService;

@Service
public class TeamServiceImple implements TeamService{

	@Autowired
	private ProjectDAO projectDAO;
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private TeamDAO teamDAO;
	
	@Override
	public OutputMessage createTeam(Integer projectId, Integer creatorId) throws TeamException {
		// TODO Auto-generated method stub
		Optional<Project> projectGet = projectDAO.findById(projectId);
		if(projectGet.isEmpty())throw new TeamException("Project Does Not Exist");
		Project project = projectGet.get();
		
		Optional<Member> creatorGet = memberDAO.findById(creatorId);
		if(creatorGet.isEmpty())throw new TeamException("Member Does Not Exist");
		Member creator = creatorGet.get();
		
		List<Project> projectorking = creator.getProjectWorkingOn();
		projectorking.add(project);
		creator.setProjectWorkingOn(projectorking);
		memberDAO.save(creator);
		
		Team team = new Team();
		team.setTeamOfProject(project);
		team.setTeamMember(new ArrayList<>());
		List<Member> teamMember = new ArrayList();
		teamMember.add(creator);
		team.setTeamMember(teamMember);
		team.setTeamLead(creator);
		teamDAO.save(team);
		return new OutputMessage("Team Is Created");
	}


	@Override
	public OutputMessage leaveTeam(Integer projectId, Integer myMemberId) throws TeamException {
		// TODO Auto-generated method stub
		Optional<Project> project = projectDAO.findById(projectId);
		if(project.isEmpty())throw new TeamException("Project Does Not Exist");
		
		Optional<Team> teamGet = teamDAO.findByTeamOfProject(project.get());
		if(teamGet.isEmpty())throw new TeamException("Team Does Not Exist");
		Team team = teamGet.get();
		List<Member> teamMember = team.getTeamMember();
		
		Optional<Member> member = memberDAO.findById(myMemberId);
		if(member.isEmpty())throw new TeamException("Member Does Not Exist");
		
		if(!teamMember.contains(member.get()))throw new TeamException("You Are Not Part Of The Team");
		
		teamMember.remove(member.get());
		team.setTeamMember(teamMember);
		teamDAO.save(team);
		
		return new OutputMessage("You left The Team");
	}

	@Override
	public OutputMessage removeTeamMember(Integer projectId, Integer projectCreatorsId, Integer removedTeamMebersId)
			throws TeamException {
		// TODO Auto-generated method stub
		Optional<Project> projectGet = projectDAO.findById(projectId);
		if(projectGet.isEmpty())throw new TeamException("Project Does Not Exist");
		Project project = projectGet.get();
		if(project.getCreatedBy().getMemberId()!=projectCreatorsId)throw new TeamException("You Are Not Authorised To Delete This Project");
		
		Optional<Team> teamGet = teamDAO.findByTeamOfProject(project);
		if(teamGet.isEmpty())throw new TeamException("Team Does Not Exist");
		Team team = teamGet.get();
		List<Member> teamMember = team.getTeamMember();
		
		Optional<Member> member = memberDAO.findById(removedTeamMebersId);
		if(member.isEmpty())throw new TeamException("Member Does Not Exist");
		
		if(!teamMember.contains(member.get()))throw new TeamException("Not Part Of The Team");
		
		teamMember.remove(member.get());
		team.setTeamMember(teamMember);
		teamDAO.save(team);
		return new OutputMessage("Member Removed");
	}


	@Override
	public List<Member> getTeamMemberOfProject(Integer projectId) throws TeamException {
		// TODO Auto-generated method stub
		Optional<Project> projectGet = projectDAO.findById(projectId);
		if(projectGet.isEmpty())throw new TeamException("Project Does Not Exist");
		Project project = projectGet.get();
		
		Optional<Team> teamGet = teamDAO.findByTeamOfProject(project);
		if(teamGet.isEmpty())throw new TeamException("Team Does Not Exist");
		Team team = teamGet.get();
		
		List<Member> teamMember = team.getTeamMember();
		
		return teamMember;
	}


	@Override
	public OutputMessage setTeamLead(Integer teamMemberId, Integer teamId) throws TeamException {
		// TODO Auto-generated method stub
		Optional<Member> newTeamLeadGet = memberDAO.findById(teamMemberId);
		if(newTeamLeadGet.isEmpty())throw new TeamException("Member Does Not Exist");
		Member newTeamLead = newTeamLeadGet.get();
		
		Optional<Team> teamGet = teamDAO.findById(teamId);
		if(teamGet.isEmpty())throw new TeamException("Team Does Not Exist");
		Team team = teamGet.get();
		
		List<Member> listOfTeamMember = team.getTeamMember();
		if(!listOfTeamMember.contains(newTeamLead))throw new TeamException("Is Not The Team Member");
		team.setTeamLead(newTeamLead);
		teamDAO.save(team);
		
		return new OutputMessage("Team Lead Is Updated");
	}


}
