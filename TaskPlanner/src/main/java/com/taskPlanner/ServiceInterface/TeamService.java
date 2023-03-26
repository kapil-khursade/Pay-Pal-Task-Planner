package com.taskPlanner.ServiceInterface;

import java.util.List;

import com.taskPlanner.Beans.Member;
import com.taskPlanner.DTO.OutputMessage;
import com.taskPlanner.Exceptions.TeamException;

public interface TeamService {

	public OutputMessage createTeam(Integer projectId, Integer creatorId)throws TeamException;
	public OutputMessage leaveTeam(Integer projectId, Integer myMemberId)throws TeamException;
	public OutputMessage removeTeamMember(Integer projectId, Integer projectCreatorsId, Integer removedTeamMebersId)throws TeamException;
	public List<Member> getTeamMemberOfProject(Integer projectId)throws TeamException;
	public OutputMessage setTeamLead(Integer teamMemberId, Integer teamId)throws TeamException;
}
