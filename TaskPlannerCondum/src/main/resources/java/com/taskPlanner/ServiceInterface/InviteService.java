package com.taskPlanner.ServiceInterface;

import com.taskPlanner.DTO.OutputMessage;
import com.taskPlanner.Exceptions.TeamException;

public interface InviteService {
	
	public OutputMessage joinTeamOnInvitation(Integer projectId, Integer memberId)throws TeamException;
	
}
