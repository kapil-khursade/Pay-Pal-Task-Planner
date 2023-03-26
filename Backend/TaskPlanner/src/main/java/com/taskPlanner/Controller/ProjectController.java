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

import com.taskPlanner.Beans.Project;
import com.taskPlanner.Beans.Status;
import com.taskPlanner.DTO.OutputMessage;
import com.taskPlanner.Exceptions.ProjectException;
import com.taskPlanner.ServiceInterface.ProjectService;

@RestController
@RequestMapping("taskplanner/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@PostMapping("/create/{creatorId}")
	public ResponseEntity<OutputMessage> createProjectHandler(@RequestBody Project project,
			@PathVariable("creatorId") Integer creatorId)throws ProjectException{
		return new ResponseEntity<OutputMessage>(projectService.createProject(project, creatorId), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{projectId}")
	public ResponseEntity<OutputMessage> deleteProjectByIdHandler(@PathVariable("projectId") Integer projectId) throws ProjectException{
		return new ResponseEntity<OutputMessage>(projectService.deleteProjectById(projectId), HttpStatus.OK);
	}
	
	@PutMapping("/updateStatus/{projectId}/{newStatus}")
	public ResponseEntity<OutputMessage> updateProjectStatusHandler(@PathVariable("projectId") Integer projectId,
			@PathVariable("newStatus") Status newStatus)throws ProjectException{
		return new ResponseEntity<OutputMessage>(projectService.updateProjectStatus(projectId, newStatus),
				HttpStatus.OK);
	}
	
	@GetMapping("/byCreator/{creatorId}")
	public ResponseEntity<List<Project>> getProjectByCreatorsId(@PathVariable("creatorId") Integer creatorId) throws ProjectException{
		return new ResponseEntity<List<Project>>(projectService.getProjectByCreatorsId(creatorId), 
				HttpStatus.OK);
	}
}
