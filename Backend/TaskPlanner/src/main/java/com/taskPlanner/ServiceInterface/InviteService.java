package com.taskPlanner.ServiceInterface;

import java.util.List;

import com.taskPlanner.Beans.Invites;
import com.taskPlanner.DTO.OutputMessage;
import com.taskPlanner.Exceptions.InviteException;

public interface InviteService {
	
	public OutputMessage sendInvites(Integer inviteId, Integer creatorId, Integer projectId)throws InviteException;
	public List<Invites> getMyInvites(Integer memberId)throws InviteException;
	public OutputMessage actionOnInvitation(Integer inviteeId, Integer inviteId, boolean action)throws InviteException;
	
}
