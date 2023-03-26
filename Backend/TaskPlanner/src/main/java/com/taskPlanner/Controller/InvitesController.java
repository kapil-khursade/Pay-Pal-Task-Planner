package com.taskPlanner.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskPlanner.Beans.Invites;
import com.taskPlanner.DTO.OutputMessage;
import com.taskPlanner.Exceptions.InviteException;
import com.taskPlanner.ServiceInterface.InviteService;

@RestController
@RequestMapping("taskplanner/invites")
public class InvitesController {

	@Autowired
	private InviteService inviteService;
	
	@GetMapping("/getMyInvites/{memberId}")
	public ResponseEntity<List<Invites>> getMyInvitesHandler(@PathVariable("memberId") Integer memberId)throws InviteException{
		return new ResponseEntity<List<Invites>>(inviteService.getMyInvites(memberId), HttpStatus.OK);
	}
	
	@PutMapping("/action/{inviteeId}/{inviteId}/{action}")
	public ResponseEntity<OutputMessage> actionOnInvitation(@PathVariable("inviteeId") Integer inviteeId, 
			@PathVariable("inviteId") Integer inviteId, @PathVariable("action")  boolean action)throws InviteException{
		return new ResponseEntity<OutputMessage>(inviteService.actionOnInvitation(inviteeId, inviteId, action), 
				HttpStatus.OK);
	}
	
	@PutMapping("/send/{inviteId}/{creatorId}/{projectId}")
	public ResponseEntity<OutputMessage> sendInvitesHandler(@PathVariable("inviteId") Integer inviteId,
			@PathVariable("creatorId") Integer creatorId,@PathVariable("projectId") Integer projectId)throws InviteException{
		return new ResponseEntity<OutputMessage>(inviteService.sendInvites(inviteId, creatorId, projectId), 
				HttpStatus.CREATED);
	}
	
}
