package com.taskPlanner.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskPlanner.Beans.Member;
import com.taskPlanner.DTO.OutputMessage;
import com.taskPlanner.Exceptions.TeamException;
import com.taskPlanner.ServiceInterface.TeamService;

@RestController
@RequestMapping("taskplanner/team")
public class TeamController {

	@Autowired
	private TeamService teamService;
	
	@PostMapping("/creater/{projectId}/{creatorId}")
	public ResponseEntity<OutputMessage> createTeamHandler(@PathVariable("projectId") Integer projectId, @PathVariable("creatorId") Integer creatorId)throws TeamException{
		return new ResponseEntity<OutputMessage>(teamService.createTeam(projectId, creatorId), 
				HttpStatus.CREATED);
	}
	
	@PutMapping("/leave/{projectId}/{myMemberId}")
	public ResponseEntity<OutputMessage> leaveTeamHandler(@PathVariable("projectId") Integer projectId,@PathVariable("myMemberId") Integer myMemberId)throws TeamException{
		return new ResponseEntity<OutputMessage>(teamService.leaveTeam(projectId, myMemberId), 
				HttpStatus.OK);
	}
	
	@PutMapping("remove/{projectId}/{projectCreatorsId}/{removedTeamMebersId}")
	public ResponseEntity<OutputMessage> removeTeamMemberHandler(@PathVariable("projectId") Integer projectId, 
			@PathVariable("projectCreatorsId") Integer projectCreatorsId, 
			@PathVariable("removedTeamMebersId") Integer removedTeamMebersId)throws TeamException{
		return new ResponseEntity<OutputMessage>(teamService.removeTeamMember(projectId, projectCreatorsId, removedTeamMebersId), 
				HttpStatus.OK);
	}
	
	@GetMapping("/getByProjectId/{projectId}")
	public ResponseEntity<List<Member>> getTeamMemberOfProjectHandler(@PathVariable("projectId") Integer projectId)throws TeamException{
		return new ResponseEntity<List<Member>>(teamService.getTeamMemberOfProject(projectId), 
				HttpStatus.OK);
	}
	
	@PutMapping("/setTeamLeader/{teamMemberId}/{teamId}")
	public ResponseEntity<OutputMessage> setTeamLeadHandler(@PathVariable("teamMemberId") Integer teamMemberId, 
			@PathVariable("teamId") Integer teamId)throws TeamException{
		return new ResponseEntity<OutputMessage>(teamService.setTeamLead(teamMemberId, teamId), 
				HttpStatus.OK);
	}
}
