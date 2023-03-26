package com.taskPlanner.ServiceInterfaceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskPlanner.Beans.Invites;
import com.taskPlanner.Beans.Member;
import com.taskPlanner.Beans.Project;
import com.taskPlanner.Beans.Team;
import com.taskPlanner.DAO.InvitesDAO;
import com.taskPlanner.DAO.MemberDAO;
import com.taskPlanner.DAO.ProjectDAO;
import com.taskPlanner.DAO.TeamDAO;
import com.taskPlanner.DTO.OutputMessage;
import com.taskPlanner.Exceptions.InviteException;
import com.taskPlanner.ServiceInterface.InviteService;
@Service
public class InviteServiceImpl implements InviteService{

	@Autowired
	private ProjectDAO projectDAO;
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private TeamDAO teamDAO;
	@Autowired
	private InvitesDAO invitesDAO;
	
	@Override
	public List<Invites> getMyInvites(Integer memberId) throws InviteException {
		// TODO Auto-generated method stub
		Optional<Member> memberGet = memberDAO.findById(memberId);
		if(memberGet.isEmpty())throw new InviteException("Member Does Not Exist");
		
		Member member = memberGet.get();
		List<Invites> myInvitesList= member.getListOfInvites();
		return myInvitesList;
	}
	
	

	@Override
	public OutputMessage sendInvites(Integer inviteId, Integer creatorId, Integer projectId) throws InviteException {
		// TODO Auto-generated method stub
		Optional<Project> projectGet = projectDAO.findById(projectId);
		if(projectGet.isEmpty())throw new InviteException("Project Does Not Exist");
		Project project = projectGet.get();
		
		Optional<Member> senderMemberGet = memberDAO.findById(creatorId);
		if(senderMemberGet.isEmpty())throw new InviteException("Sender Does Not Exist");
		Member senderMember = senderMemberGet.get();
		
		if(!project.getCreatedBy().equals(senderMember))throw new InviteException("Not Authorised To Send Invite");
		
		Optional<Member> inviteMemberGet = memberDAO.findById(inviteId);
		if(inviteMemberGet.isEmpty())throw new InviteException("Invite Does Not Exist");
		Member inviteMember = inviteMemberGet.get();
		Invites invites = new Invites();
		invites.setProject(project);
		invites.setSendTo(inviteMember);
		invites.setSentBy(senderMember);
		
		List<Invites> listOfInvitesOfInvitee = inviteMember.getListOfInvites();
		listOfInvitesOfInvitee.add(invites);
		
		inviteMember.setListOfInvites(listOfInvitesOfInvitee);
		memberDAO.save(inviteMember);
		invitesDAO.save(invites);
		
		return new OutputMessage("Invitation Sent");
	}



	@Override
	public OutputMessage actionOnInvitation(Integer inviteeId, Integer inviteId, boolean action)throws InviteException {
		// TODO Auto-generated method stub
		
		
		Optional<Member> inviteMemberGet = memberDAO.findById(inviteeId);
		if(inviteMemberGet.isEmpty())throw new InviteException("Invitee Does Not Exist");
		Member inviteMember = inviteMemberGet.get();
		
		List<Invites> listOfInvites = inviteMember.getListOfInvites();
		
		Optional<Invites> invitesGet = invitesDAO.findById(inviteId);
		if(invitesGet.isEmpty())throw new InviteException("Invite Does Not Exist");
		Invites invites = invitesGet.get();
		
		if(!listOfInvites.contains(invites))throw new InviteException("You Dont Have This Invite");
		
		listOfInvites.remove(invites);
		inviteMember.setListOfInvites(listOfInvites);
		memberDAO.save(inviteMember);
		invitesDAO.delete(invites);
		
		Project project = invites.getProject();
		Optional<Team> teamGet = teamDAO.findByTeamOfProject(project);
		Team team = teamGet.get();
		
		if(action) {
		List<Member> teamMember = team.getTeamMember();
		if(teamMember.contains(inviteMember))throw new InviteException("You Are Already A Team Member");
		teamMember.add(inviteMember);
		team.setTeamMember(teamMember);
		teamDAO.save(team);
		return new OutputMessage("Invitation Accpted");
		}else {
			return new OutputMessage("Invitation Rejected And Removed");
		}
	}

	

}
