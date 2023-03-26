package com.taskPlanner.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskPlanner.Beans.Sprint;
import com.taskPlanner.Beans.Status;
import com.taskPlanner.DTO.OutputMessage;
import com.taskPlanner.Exceptions.SprintException;
import com.taskPlanner.ServiceInterface.SprintService;


@RestController
@RequestMapping("taskplanner/sprint")
public class SprintController {

	@Autowired
	private SprintService sprintService;
	
	@PostMapping("/add/{projectId}")
	public ResponseEntity<OutputMessage> addSprintToProjectHadler(@RequestBody Sprint sprint,
			@PathVariable("projectId") Integer projectId)throws SprintException{
		return new ResponseEntity<OutputMessage>(sprintService.addSprintToProject(sprint, projectId), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{sprintId}")
	public ResponseEntity<OutputMessage> deleteSprintByIdHadler(@PathVariable("sprintId") Integer sprintId)throws SprintException{
		return new ResponseEntity<OutputMessage>(sprintService.deleteSprintById(sprintId), HttpStatus.OK);
	}
	
	@PutMapping("/updateStatus/{sprintId}/{newStatus}")
	public ResponseEntity<OutputMessage> updateSprintStatusHadler(@PathVariable("sprintId") Integer sprintId,@PathVariable("newStatus") Status newStatus)throws SprintException{
		return new ResponseEntity<OutputMessage>(sprintService.updateSprintStatus(sprintId, newStatus), HttpStatus.OK);
	}
	
	@GetMapping("/getByProjectId/{projectId}")
	public ResponseEntity<List<Sprint>> getAllSprintByProjectIdHadler(@PathVariable("projectId") Integer projectId)throws SprintException{
		return new ResponseEntity<List<Sprint>>(sprintService.getAllSprintByProjectId(projectId), 
				HttpStatus.OK);
	}
	
}
